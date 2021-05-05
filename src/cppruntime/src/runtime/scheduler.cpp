#include <runtime/scheduler.hpp>

#include <sstream>

namespace pj
{

/* scheduler member functions */

scheduler::scheduler()
: m_cpu(0),
  m_cpus(std::thread::hardware_concurrency()),
  m_kill(false),
  m_running(true)
{

}

scheduler::scheduler(unsigned int cpu, policy_sched* p)
: p_sched(p),
  m_cpu(cpu),
  m_cpus(std::thread::hardware_concurrency()),
  m_kill(false),
  m_running(true)
{

}

scheduler::scheduler(scheduler&& that)
: m_rq(run_queue()),
  m_tq(timer_queue()),
  m_sched_th(std::move(that.m_sched_th)),
  m_cpu(that.m_cpu),
  m_cpus(that.m_cpus),
  m_kill(false),
  m_running(true)
{

}

scheduler::~scheduler()
{
    if (m_sched_th.joinable())
    {
        m_sched_th.join();
        logger::log(logger::DEBUG) << "scheduler thread joined.";
    }

    /* timer queue should only be killed once */
    logger::log(logger::DEBUG) << "scheduler killing timer queue.";
    m_tq.kill();
}

scheduler& scheduler::operator =(scheduler&& that)
{
	m_rq = std::move(that.m_rq);
	m_tq = std::move(that.m_tq);
	m_sched_th = std::move(that.m_sched_th);
	m_cpu = that.m_cpu;
	m_cpus = that.m_cpus;
	m_kill = false;
	m_running = true;
	
	return *this;
}

void scheduler::start()
{
	std::stringstream ss;
	ss << "scheduler::start called from thread "
	   << std::this_thread::get_id()
	   << ".";
   	logger::log(logger::DEBUG) << ss.str();
	m_tq.start();
	m_sched_th = std::thread(&scheduler::run, this);
	ss.str(std::string());
	ss << "scheduler thread started with thread id "
	   << m_sched_th.get_id()
	   << ".";
   	logger::log(logger::DEBUG) << ss.str();
}

void scheduler::run()
{
	std::stringstream ss;
	ss << "scheduler::run called from thread "
	   << std::this_thread::get_id()
	   << ".";
	logger::log(logger::DEBUG) << ss.str();

	unsigned long nh = 0;
	while (!nh)
	{
		nh = m_sched_th.native_handle();
	}
	ss.str(std::string());
	ss << "native handle is " << nh << std::endl;
	logger::log(logger::DEBUG) << ss.str();
	ss.str(std::string());

	isolate_thread(m_sched_mtx,
		           nh,
		           m_cpu,
		           m_cpus);

	while (true)
	// while (!m_rq.empty())
	{
		if (!m_rq.empty())
		{
			logger::log(logger::DEBUG) << "run queue not empty.";

			if (!m_running)
			{
				m_running = p_sched->notify_running(m_cpu);
			}

			process* p = m_rq.pop();

			if (p->is_ready_to_run())
			{
				ss.str(std::string());
				ss << "running process " << p << ".";
				logger::log(logger::DEBUG) << ss.str();
				(*p)();
				ss.str(std::string());
				ss << "process " << p << " finished.";
				logger::log(logger::DEBUG) << ss.str();
				if (p->is_terminated())
				{
					ss.str(std::string());
					ss << "process " << p << " terminated, finalizing process.";
					logger::log(logger::DEBUG) << ss.str();
					p->finalize();
					delete p;
				}
				else
				{
					m_rq.push(p);
				}
			}
			else
			{
				m_rq.push(p);
			}
		}
		else
		{
			if (m_kill)
			{
				ss.str(std::string());
				ss << "ending scheduler::run on thread "
				   << std::this_thread::get_id()
				   << ".";
				logger::log(logger::DEBUG) << ss.str();
				return;
			}

			if (m_running)
			{
				m_running = p_sched->notify_empty(m_cpu);
			}
		}
	}

}

void scheduler::enqueue(process* p)
{
	std::stringstream ss;
	ss << "Enqueuing process " << p << " into policy scheduler.";
	logger::log(logger::DEBUG) << ss.str();
	p_sched->insert(p);
}

void scheduler::insert(process* p)
{
	std::stringstream ss;
	ss << "inserting process " << p << " into scheduler.";
	logger::log(logger::DEBUG) << ss.str();
	m_rq.push(p);
}

void scheduler::insert(std::shared_ptr<timer> t)
{
	std::stringstream ss;
	ss << "inserting timer " << t << " into scheduler.";
	logger::log(logger::DEBUG) << ss.str();
	m_tq.insert(t);
}

void scheduler::kill()
{
	std::stringstream ss;
	ss << "scheduler::kill called from thread "
	   << std::this_thread::get_id() << ".";
	logger::log(logger::DEBUG) << ss.str();
	m_kill = true;
}

} // namespace pj

