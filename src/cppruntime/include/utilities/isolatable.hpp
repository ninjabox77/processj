#ifndef I_ISOLATABLE_HPP
#define I_ISOLATABLE_HPP

#include <thread>
#include <mutex>
#include <sstream>

#include <pthread.h>
#include <sched.h>

namespace pj
{

// isolate the thread of execution for the scheduler behavior to a
// cpu, defined by the cpu variable.
// ---
// this only currently works on posix-compliant machines
inline void isolate_thread(std::mutex&    mtx,
                           pthread_t       th,
                           unsigned int   cpu,
                           unsigned int  cpus)
{
    std::unique_lock<std::mutex> lock(mtx, std::defer_lock);

    cpu_set_t cur_set;
    cpu_set_t new_set;
    unsigned int    i;

    CPU_ZERO(&new_set);

    if(!cpus)
    {
        lock.lock();
        std::cerr << "hardware_concurrency not set/determinable." << std::endl;
        lock.unlock();
        abort();
    }

    unsigned char arr_cur_set[cpus];
    unsigned char arr_new_set[cpus];

    for(i = 0; i < cpus; ++i)
    {
        arr_cur_set[i] = 0;
        arr_new_set[i] = 0;
    }

    CPU_ZERO(&cur_set);

    if(pthread_getaffinity_np(th,
                              sizeof(cpu_set_t),
                              &cur_set))
    {
        lock.lock();
        perror("pthread_getaffinity_np");
        lock.unlock();
        abort();
    }

    CPU_SET(cpu, &new_set);
    arr_new_set[cpu] = 1;

    if(pthread_setaffinity_np(th,
                              sizeof(cpu_set_t),
                              &new_set))
    {
        lock.lock();
        perror("pthread_setaffinity_np");
        lock.unlock();
        abort();
    }

    CPU_ZERO(&cur_set);
    if(pthread_getaffinity_np(th,
                              sizeof(cpu_set_t),
                              &cur_set))
    {
        lock.lock();
        perror("pthread_getaffinity_np");
        lock.unlock();
        abort();
    }

    lock.lock();
    for(i = 0; i < cpus; ++i)
    {
        if(CPU_ISSET(i, &cur_set))
        {
            arr_cur_set[i] = 1;
        }
    }

    for(i = 0; i < cpus; ++i)
    {
        if(arr_cur_set[i] != arr_new_set[i])
        {
            lock.unlock();
            abort();
        }
    }
    lock.unlock();
}

} // namespace pj

#endif