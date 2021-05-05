#ifndef PJ_BARRIER_HPP
#define PJ_BARRIER_HPP

#include <runtime/scheduler.hpp>

#include <utilities/logger.hpp>

#include <vector>
#include <sstream>
#include <mutex>

namespace pj
{

class barrier
{
public:
    // vector of synced processes on this barrier
    std::vector<process*> m_synced;
    // number enrolled on the barrier
    unsigned int m_enrolled;

    // init with one enrolled
    barrier();

    ~barrier();

    // enroll n - 1, where n is the number of processes that wish to
    // syncronize on the barrier
    void enroll(unsigned int proc_count);

    // resign from the barrier by decr'ing enrolled
    void resign();

    // syncronize on the barrier. if every process is syncronized,
    // then set them ready and clear the vector.
    void sync(process* process);

protected:
private:
    std::mutex m_mtx;
};

} // namespace pj

#endif