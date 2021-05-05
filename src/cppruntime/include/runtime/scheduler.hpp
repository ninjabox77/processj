#ifndef PJ_SCHEDULER_HPP
#define PJ_SCHEDULER_HPP

#include <mutex>
#include <thread>
#include <queue>
#include <vector>
#include <atomic>

#include <sched.h>
#include <pthread.h>

#include <utilities/logger.hpp>
#include <utilities/isolatable.hpp>
#include <utilities/delay_queue.hpp>

namespace pj
{

class process;
class run_queue;
class timer;
class timer_queue;
class scheduler;
class policy_sched;

class process
{

friend class scheduler;
friend class policy_sched;

public:

	process();

	virtual ~process();

	virtual void operator ()();

	virtual void finalize();

	void terminate();

	bool is_terminated();

	void set_ready();

	void set_not_ready();

	bool is_ready_to_run();

	unsigned int get_run_label();

	void set_run_label(unsigned int value);

protected:

	scheduler* m_sched;
    
private:

	bool m_terminated;
	bool m_ready;

	unsigned int m_run_label;
};

class run_queue
{
public:

	run_queue();

	run_queue(const run_queue& that) = delete;

	run_queue(run_queue&& that);

	~run_queue();

	run_queue& operator =(const run_queue&) = delete;

	run_queue& operator =(run_queue&& that);

	void push(process* p);

	process* pop();

	bool empty();

protected:
private:

	std::mutex m_rq_mtx;
	std::queue<process*> m_q;
};

class timer
{
    friend class timer_queue;
    // friend class alt;

public:
    bool m_started;
    bool m_expired;

    // default ctor, immediate timeout, no process (the kill timer)
    timer();

    // specialized ctor, with specified timeout value
    timer(long timeout);

    // specialized ctor, specified process and timeout value
    timer(process* process, long timeout);

    ~timer() = default;

    // calculate the timeout point and mark the timer as started
    void start();

    // timeout value setter
    void timeout(long timeout);

    // read the current time
    static long read()
    {
        auto now = std::chrono::system_clock::now();
        auto now_ms = std::chrono::time_point_cast<std::chrono::milliseconds>(now);
        auto now_epoch = now_ms.time_since_epoch();
        auto value = std::chrono::duration_cast<std::chrono::milliseconds>(now_epoch);
        return static_cast<long>(value.count());
    }

    // kill flag setter
    void kill();

    // kill flag getter
    bool killed();

    // expired flag setter
    void expire();

    // expired flag getter
    bool expired();

    // delay getter
    long get_delay();

    // timer process pointer setter
    void set_process(process* p);

    // timer process pointer getter. returns the process pointer
    // iff the timer is not killed yet, else a nullptr is returned
    process* get_process();

protected:
    // _real_ delay value getter for alt
    std::chrono::system_clock::time_point get_real_delay();

private:
    long m_delay;
    std::chrono::system_clock::time_point m_real_delay;
    long m_timeout;
    bool m_killed;
    process* m_process;
};

class timer_queue
{

public:
    // default ctor, exit_value is always set
    timer_queue();

    // ctor with cpu to be isolated to, exit_value is always set
    timer_queue(unsigned int cpu);

    timer_queue(const timer_queue&) = delete;

    // move ctor
    timer_queue(timer_queue&& that);

    // clean up the timer thread, and the kill timer
    // if necessary
    ~timer_queue();

    timer_queue& operator =(const timer_queue&) = delete;

    timer_queue& operator =(timer_queue&& that);

    // insert a timer into the timer queue
    void insert(std::shared_ptr<timer> t);

    // timer queue behavior
    void start();

    void run();

    // kill the timer queue by placing a special timer in,
    // to let the timer thread know that we're done
    void kill();

    // query the size of the timer queue's delay queue
    size_t size();

private:
    utilities::delay_queue<std::shared_ptr<timer>> m_dq;
    std::thread              m_timer_th;
    std::mutex              m_timer_mtx;
    std::atomic<int>       m_exit_value;
    std::atomic<bool>       m_kill_flag;

    std::shared_ptr<timer> m_kill_timer;

    unsigned int m_cpu;
};

class scheduler
{

public:

	scheduler();

	scheduler(unsigned int cpu, policy_sched* p);

	scheduler(scheduler&& that);

	~scheduler();

	scheduler& operator =(scheduler&& that);

	void start();

	void run();

	void enqueue(process* p);

	void insert(process* p);

	void insert(std::shared_ptr<timer> t);

	void kill();

protected:
private:

	std::mutex m_sched_mtx;
	run_queue m_rq;
	timer_queue m_tq;
	std::thread m_sched_th;
	policy_sched* p_sched;

	// unsigned int id;
	unsigned int m_cpu;
	unsigned int m_cpus;
	std::atomic<bool> m_kill;
	std::atomic<bool> m_running;
};

class policy_sched
{
private:
	std::thread m_sched;
	std::mutex m_s_mutex;

	unsigned int NUM_CPU;
	unsigned int* sched_flag;
	std::vector<scheduler*> sched_set;
	run_queue process_set;

public:
	policy_sched(unsigned int CPU);

	~policy_sched();
	
	void init();
	
	void start();

	void run();

	void insert(process* p);

	bool killed_children();

	bool notify_empty(unsigned int cpu);

	bool notify_running(unsigned int cpu);
};

} // namespace pj

#endif