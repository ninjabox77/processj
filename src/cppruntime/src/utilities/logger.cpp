#include <utilities/logger.hpp>

namespace pj
{

namespace logger
{

static std::mutex sg_logger_mtx;
static std::queue<std::string> sg_msg_queue;

log::log()
: m_level(INFO)
{

}

log::log(enum log_type level)
: m_level(level)
{

}

log::~log()
{

}

log& log::operator <<(std::string msg)
{
	std::lock_guard<std::mutex> sg(sg_logger_mtx);
	if (!(m_level < LOG_SETTINGS.level))
	{
		if (LOG_SETTINGS.headers)
		{
			sg_msg_queue.push(get_header() + msg);
		}
		else
		{
			sg_msg_queue.push(msg);
		}
	}

	return *this;
}

std::string log::get_header()
{
	switch(m_level)
	{
		case DEBUG: return "[DEBUG]:";   break;
		case INFO:  return "[INFO]:";    break;
		case WARN:  return "[WARN]:";    break;
		case ERR:   return "[ERR]:";     break;
		default:    return "[UNKNOWN]:"; break;
	}
}

namespace log_handler
{

static std::thread s_log_th;
static std::atomic<bool> m_kill = false;

void start_logging()
{
	s_log_th = std::thread([] ()
	{
		isolate_thread(sg_logger_mtx,
			           s_log_th.native_handle(),
			           std::thread::hardware_concurrency() - 2,
			           std::thread::hardware_concurrency());

		std::unique_lock<std::mutex> ul(sg_logger_mtx, std::defer_lock);
		while (true)
		{
			if (!sg_msg_queue.empty())
			{
				ul.lock();
				std::string msg = sg_msg_queue.front();
				sg_msg_queue.pop();
				ul.unlock();

				std::cout << msg << std::endl;
			}
			else if (m_kill)
			{
				return;
			}
		}
	});
}

void end_logging()
{
	m_kill.exchange(true);

	if (s_log_th.joinable())
	{
		s_log_th.join();
	}
}

} // namespace log_handler

} // namespace logger

} // namespace pj