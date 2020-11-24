#ifndef PJ_TIMER_QUEUE_HPP
#define PJ_TIMER_QUEUE_HPP

#include <runtime/pj_timer.hpp>
#include <utilities/delay_queue.hpp>

#include <thread>
#include <atomic>

namespace pj_runtime
{    
    class pj_timer_queue
    {

    friend class pj_timer;

    public:
        pj_timer_queue()
        : exit_value(1)
        {
            pj_logger::log("timerqueue constructor called");
        }

        ~pj_timer_queue()
        {
            pj_logger::log("timerqueue destructor called");
            if(timer_thread.joinable())
            {
                pj_logger::log("attempting to join timer_thread");
                timer_thread.join();
            }

            /* make sure we delete our kill_timer sanely */
            if(kill_timer)
            {
                pj_logger::log("kill_timer populated, deleting");
                delete kill_timer;
            }

            pj_logger::log("timer_thread joined, end of timerqueue constructor");
        }

        void insert(pj_timer* timer)
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            pj_logger::log("inserting timer ", *timer, " into timerqueue");
            dq.enqueue(timer, timer->get_real_delay());
            pj_logger::log("done inserting");
        }

        void start()
        {
            pj_logger::log("starting timer_thread");
            /* kick off a thread running our timer algorithm
             * ---
             * should use the overloaded operator()
             */
            timer_thread = std::thread([this]()
            {
                while(1)
                {
                    pj_timer* timer = dq.dequeue();

                    pj_logger::log("grabbed a timer: ", *timer);

                    timer->expire();

                    pj_process* p = timer->get_process();

                    pj_logger::log("DEBUG: process of timer is: ", timer->get_process());

                    if(!p)
                    {
                        pj_logger::log("this timer has no process associated with it");
                    }
                    else
                    {
                        pj_logger::log("this timer should not cause the thread to exit");
                    }

                    /* check if we can safely exit as a thread */
                    if(!p && exit_value)
                    {
                        pj_logger::log("timer_thread exiting");
                        return;
                    }

                    if(p)
                    {
                        p->set_ready();
                    }

                    delete timer;
                }
            });

            pj_logger::log("timer_thread started");
        }

        void kill()
        {
            pj_logger::log("timer_thread, it's time to die");

            /* we're ready to die */
            kill_flag.exchange(true);

            /* make our kill_timer and place it in the queue */
            kill_timer = new pj_timer();

            /* drop the bomb */
            this->insert(kill_timer);

            pj_logger::log("timer_thread told to die");
        }

        size_t size()
        {
            pj_logger::log("size() called on timerqueue");
            return dq.size();
        }

    private:
        /* the pj_timer_queue essentially needs to grab a timer, and wait for
         * it to timeout before setting it's _expired flag to true, and
         * handling the the process that had that timer appropriately
         * ---
         * allow a "fake timer" to be placed in the timer queue, which will
         * both disallow permanent waiting for a timer from the delayqueue,
         * and make it so that when that is placed in the queue, it is
         * taken out immediately, the flag is then checked, and if 
         * it is set to true the timerqueue thread dies, else we have
         * not received a fake timer, and we should handle that timer
         * however we are supposed to
         */
        pj_utilities::delay_queue<pj_timer*> dq;
        std::thread                timer_thread;
        std::mutex                          mtx;

        /* TODO: need to implement exit_value counter
         * ---
         * exit_value must be 1 initially (first process does not
         * increment), and must be 0 for the TimerQueue to be
         * allowed to exit properly
         * ---
         * incrememted on fork, decremented on process end 
         */
        std::atomic<int32_t> exit_value;
        std::atomic<bool>    kill_flag;

        pj_timer* kill_timer = nullptr;
    };
}

#endif