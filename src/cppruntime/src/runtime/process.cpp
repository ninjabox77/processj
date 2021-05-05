#include <runtime/scheduler.hpp>

namespace pj
{

process::process()
: m_terminated(false), m_ready(true), m_run_label(0)
{

}

process::~process()
{

}

void process::operator ()()
{

}

void process::finalize()
{

}

void process::terminate()
{
	m_terminated = true;
}

bool process::is_terminated()
{
	return m_terminated;
}

void process::set_ready()
{
	m_ready = true;
}

void process::set_not_ready()
{
	m_ready = false;
}

bool process::is_ready_to_run()
{
	return m_ready;
}

unsigned int process::get_run_label()
{
	return m_run_label;
}

void process::set_run_label(unsigned int value)
{
	m_run_label = value;
}

} // namespace pj