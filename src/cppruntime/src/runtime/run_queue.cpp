#include <runtime/scheduler.hpp>

namespace pj
{

run_queue::run_queue()
{

}

run_queue::run_queue(run_queue&& that)
{

}

run_queue::~run_queue()
{

}

run_queue& run_queue::operator =(run_queue&& that)
{
	m_q = std::move(that.m_q);

	return *this;
}

void run_queue::push(process* p)
{
	std::lock_guard<std::mutex> lg(m_rq_mtx);
	m_q.push(p);
}

process* run_queue::pop()
{
	std::lock_guard<std::mutex> lg(m_rq_mtx);
	process* p = m_q.front();
	m_q.pop();
	return p;
}

bool run_queue::empty()
{
	return m_q.empty();
}

} // namespace pj