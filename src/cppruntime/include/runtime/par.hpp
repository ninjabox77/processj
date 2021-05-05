#ifndef PJ_PAR_HPP
#define PJ_PAR_HPP

#include <runtime/scheduler.hpp>

#include <utilities/logger.hpp>

#include <mutex>
#include <sstream>

namespace pj
{

class par
{
public:
    par() = delete;

    // initialize with the process declaring a par
    // (p) and the number of processes in the par
    par(process* p, std::size_t count);

    ~par();

    // proc count setter
    void set_process_count(std::size_t count);

    // decrement the number of processes still executing
    // that were invoked from inside the par. if there
    // are no more, then set the process declaring the par
    // ready
    void decrement();

    // returns true if there are processes in the par,
    // setting the par process not ready, and false otherwise
    bool should_yield();

protected:
private:
    process* m_process;
    std::size_t m_count;
    std::mutex m_mtx;
};

} // namespace pj

#endif