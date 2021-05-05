#ifndef PJ_LOGGER_HPP
#define PJ_LOGGER_HPP

#include <iostream>
#include <queue>
#include <thread>
#include <mutex>
#include <atomic>

#include <utilities/isolatable.hpp>

namespace pj
{

namespace logger
{

enum log_type
{
	DEBUG,
	INFO,
	WARN,
	ERR
};

struct settings
{
	bool headers = false;
	enum log_type level = INFO;
};

extern settings LOG_SETTINGS;

class ilog
{
public:
	virtual ~ilog() noexcept = default;
};

class log : ilog
{
public:
	log();

	log(enum log_type level);

	~log();

	log& operator <<(std::string msg);

	std::string get_header();

protected:
private:
	log_type m_level;
};

namespace log_handler
{

extern void start_logging();

extern void end_logging();

} // namespace log_handler

} // namespace logger

} // namespace pj

#endif