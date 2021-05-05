#include <runtime/scheduler.hpp>

namespace pj
{

// default ctor, immediate timeout, no process (the kill timer)
timer::timer()
: m_started(false),
  m_expired(false),
  m_delay(0),
  m_real_delay(std::chrono::time_point_cast<std::chrono::milliseconds>(std::chrono::system_clock::time_point::min())),
  m_killed(false),
  m_process(static_cast<process*>(0))
{

}

// specialized ctor, with specified timeout value
timer::timer(long timeout)
: m_started(false),
  m_expired(false),
  m_delay(timeout),
  m_real_delay(std::chrono::time_point_cast<std::chrono::milliseconds>(std::chrono::system_clock::time_point::min())),
  m_killed(false),
  m_process(static_cast<process*>(0))
{

}

// specialized ctor, specified process and timeout value
timer::timer(process* process, long timeout)
: m_started(false),
  m_expired(false),
  m_delay(timeout),
  m_real_delay(std::chrono::time_point_cast<std::chrono::milliseconds>(std::chrono::system_clock::time_point::min())),
  m_killed(false),
  m_process(process)
{

}

// calculate the timeout point and mark the timer as started
void timer::start()
{
    m_real_delay = std::chrono::system_clock::time_point(std::chrono::milliseconds(timer::read() + this->get_delay()));
    m_started = true;
}

// timeout value setter
void timer::timeout(long timeout)
{
    m_delay = timeout;
}

// kill flag setter
void timer::kill()
{
    m_killed = true;
}

// kill flag getter
bool timer::killed()
{
    return m_killed;
}

// expired flag setter
void timer::expire()
{
    m_expired = true;
}

// expired flag getter
bool timer::expired()
{
    return m_expired;
}

// delay getter
long timer::get_delay()
{
    return m_delay;
}

// timer process pointer setter
void timer::set_process(process* p)
{
    m_process = p;
}

// timer process pointer getter. returns the process pointer
// iff the timer is not killed yet, else a nullptr is returned
process* timer::get_process()
{
    return (m_killed) ? static_cast<process*>(0) : m_process;
}

// _real_ delay value getter for alt
std::chrono::system_clock::time_point timer::get_real_delay()
{
    return std::chrono::time_point_cast<std::chrono::milliseconds>(m_real_delay);
}

} // namespace pj