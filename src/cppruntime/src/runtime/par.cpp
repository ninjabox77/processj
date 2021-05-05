#include <runtime/par.hpp>

namespace pj
{

// initialize with the process declaring a par
// (p) and the number of processes in the par
par::par(process* p, std::size_t count)
: m_process(p), m_count(count)
{
	std::stringstream ss;
	ss << "par " << this << " created.";
	logger::log(logger::DEBUG) << ss.str();
}

par::~par()
{
	std::stringstream ss;
	ss << "par " << this << " destroyed.";
	logger::log(logger::DEBUG) << ss.str();
}

// proc count setter
void par::set_process_count(std::size_t count)
{
    logger::log(logger::DEBUG) << "par::set_process_count called.";
	std::stringstream ss;
	ss << "setting par " << this << "from "
	   << m_count;
    m_count = count;
    ss << " to " << m_count << ".";
    logger::log(logger::DEBUG) << ss.str();
}

// decrement the number of processes still executing
// that were invoked from inside the par. if there
// are no more, then set the process declaring the par
// ready
void par::decrement()
{
    logger::log(logger::DEBUG) << "par::decrement called.";
    std::lock_guard<std::mutex> lock(m_mtx);
    std::stringstream ss;
    ss << "decrementing par " << this << " count from " << m_count;
    m_count--;
    ss << " to " << m_count << ".";
    logger::log(logger::DEBUG) << ss.str();

    if(m_count == 0)
    {
    	ss.str(std::string());
    	ss << "setting process " << m_process << " ready.";
    	logger::log(logger::DEBUG) << ss.str();
        m_process->set_ready();
    }
}

// returns true if there are processes in the par,
// setting the par process not ready, and false otherwise
bool par::should_yield()
{
    logger::log(logger::DEBUG) << "par::should_yield called.";
    std::lock_guard<std::mutex> lock(m_mtx);
    std::stringstream ss;
    ss << "process " << m_process << " of par " << this;
    if(m_count == 0)
    {
    	ss << " does not require a yield.";
    	logger::log(logger::DEBUG) << ss.str();
        return false;
    }

    ss << " requires a yield. setting process "
       << m_process << " not ready.";
    logger::log(logger::DEBUG) << ss.str();
    m_process->set_not_ready();
    return true;
}

} // namespace pj