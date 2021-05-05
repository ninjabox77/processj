#include <runtime/barrier.hpp>

namespace pj
{

// init with one enrolled
barrier::barrier()
: m_enrolled(1)
{
	std::stringstream ss;
	ss << "barrier " << this << " created.";
	logger::log(logger::DEBUG) << ss.str();
}

barrier::~barrier()
{
    std::stringstream ss;
    ss << "barrier " << this << " destroyed.";
    logger::log(logger::DEBUG) << ss.str();
}

// enroll n - 1, where n is the number of processes that wish to
// syncronize on the barrier
void barrier::enroll(unsigned int proc_count)
{
    logger::log(logger::DEBUG) << "barrier::enroll called.";
    std::lock_guard<std::mutex> lock(m_mtx);
    std::stringstream ss;
    ss << "incrementing enrolled from " << m_enrolled
       << " to ";
    m_enrolled += (proc_count - 1);
    ss << m_enrolled << ".";
    logger::log(logger::DEBUG) << ss.str();
}

// resign from the barrier by decr'ing enrolled
void barrier::resign()
{
    logger::log(logger::DEBUG) << "barrer::resign called.";
    std::lock_guard<std::mutex> lock(m_mtx);
    std::stringstream ss;
    ss << "resigning from barrier, enrolled decremented from "
       << m_enrolled << " to ";
    if(m_enrolled > 1)
    {
        --m_enrolled;
    }
    ss << m_enrolled << ".";
    logger::log(logger::DEBUG) << ss.str();
}

// syncronize on the barrier. if every process is syncronized,
// then set them ready and clear the vector.
void barrier::sync(process* p)
{
    logger::log(logger::DEBUG) << "barrier::sync called.";
    std::lock_guard<std::mutex> lock(m_mtx);
    std::stringstream ss;
    ss << "sync called from process " << p << ".";
    logger::log(logger::DEBUG) << ss.str();
    p->set_not_ready();
    m_synced.push_back(p);
    if(m_synced.size() == m_enrolled)
    {
    	ss.str(std::string());
    	ss << "setting processes enrolled on barrier "
    	   << this << " ready.";
    	logger::log(logger::DEBUG) << ss.str();

        for(std::size_t i = 0; i < m_synced.size(); ++i)
        {
            m_synced[i]->set_ready();
        }
        m_synced.clear();
    }
}

} // namespace pj