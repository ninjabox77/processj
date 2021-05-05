#include <runtime/scheduler.hpp>

#include <sstream>

namespace pj
{

// default ctor, exit_value is always set
timer_queue::timer_queue()
: m_exit_value(1), m_cpu(0)
{

}

// ctor with cpu to be isolated to, exit_value is always set
timer_queue::timer_queue(unsigned int cpu)
: m_exit_value(1), m_cpu(cpu)
{

}

timer_queue::timer_queue(timer_queue&& that)
: m_dq(std::move(that.m_dq)),
  m_timer_th(std::move(that.m_timer_th)),
  m_kill_timer(std::move(that.m_kill_timer)),
  m_cpu(that.m_cpu)
{

}

// clean up the timer thread, and the kill timer
// if necessary
timer_queue::~timer_queue()
{
    if(m_timer_th.joinable())
    {
        m_timer_th.join();
        logger::log(logger::DEBUG) << "timer thread joined.";
    }
}

timer_queue& timer_queue::operator =(timer_queue&& that)
{
    m_dq = std::move(that.m_dq);
    m_timer_th = std::move(that.m_timer_th);
    m_kill_timer = std::move(that.m_kill_timer);
    m_cpu = that.m_cpu;

    return *this;
}

// insert a timer into the timer queue
void timer_queue::insert(std::shared_ptr<timer> t)
{
    std::lock_guard<std::mutex> lock(this->m_timer_mtx);
    std::stringstream ss;
    ss  << "inserting timer with delay of "
        << t->get_delay()
        << " into timer queue.";
    logger::log(logger::DEBUG) << ss.str();
    m_dq.enqueue(t, t->get_real_delay());
}

// timer queue behavior
void timer_queue::start()
{
    std::stringstream ss;
    ss << "timer_queue::start called from thread "
       << std::this_thread::get_id()
       << ".";
    logger::log(logger::DEBUG) << ss.str();

    m_timer_th = std::thread(&timer_queue::run, this);
    ss.str(std::string());
    ss << "timer thread started with thread id "
       << m_timer_th.get_id()
       << ".";
    logger::log(logger::DEBUG) << ss.str();
}

void timer_queue::run()
{
    std::stringstream ss;
	ss << "timer_queue::run called from thread "
	   << std::this_thread::get_id()
	   << ".";
    logger::log(logger::DEBUG) << ss.str();

    unsigned long nh = 0;
    while (!nh)
    {
        nh = m_timer_th.native_handle();
    }
    ss.str(std::string());
    ss << "native handle is " << nh << std::endl;
    logger::log(logger::DEBUG) << ss.str();
    ss.str(std::string());

    isolate_thread(m_timer_mtx,
                   nh,
                   m_cpu,
                   std::thread::hardware_concurrency());

	 while(true)
    {
        // get a timer out of the delay queue
        // ---
        // this blocks until the timer is ready
        // to be removed (the timer has timed out)
        logger::log(logger::DEBUG) << "timer thread grabbing a timer.";
        std::shared_ptr<timer> t = m_dq.dequeue();
        logger::log(logger::DEBUG) << "timer grabbed.";

        // expire the timer
        logger::log(logger::DEBUG) << "expiring timer.";
        t->expire();

        // get the timer's process
        logger::log(logger::DEBUG) << "grabbing timer's process.";
        process* p = t->get_process();

        // check if we can safely exit as a thread
        if (!p && m_exit_value)
        {
            // this is the kill timer, we're done
            logger::log(logger::DEBUG) << "kill timer detected, ending thread.";
            return;
        }

        if (p)
        {
            // wake up the process waiting on the timer
            logger::log(logger::DEBUG) << "waking process.";
            p->set_ready();
        }
    }
}

// kill the timer queue by placing a special timer in,
// to let the timer thread know that we're done
void timer_queue::kill()
{
	logger::log(logger::DEBUG) << "timer_queue::kill called.";
    // we're ready to end execution
    logger::log(logger::DEBUG) << "setting kill flag to true.";
    m_kill_flag.exchange(true);

    // make our kill_timer and place it in the queue
    logger::log(logger::DEBUG) << "creating kill timer.";
    m_kill_timer = std::make_shared<timer>();

    // drop the kill timer in so the timer queue knows
    // it's time to stop
    logger::log(logger::DEBUG) << "inserting kill timer.";
    insert(m_kill_timer);
}

// query the size of the timer queue's delay queue
size_t timer_queue::size()
{
    return m_dq.size();
}

} // namespace pj