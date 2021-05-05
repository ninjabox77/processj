#ifndef PJ_SCHEDULER_MOVE_TEST_HPP
#define PJ_SCHEDULER_MOVE_TEST_HPP

#include <runtime/runtime.hpp>

#include <sstream>

namespace pj
{

namespace testbed
{

class scheduler_move_test
{
public:
	scheduler_move_test()
	{

	}

	~scheduler_move_test()
	{

	}

	void operator ()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating scheduler to test move semantics.";
		scheduler sched;

		logger::log(logger::DEBUG) << "moving scheduler.";
		scheduler sched2 = std::move(sched);
	}
protected:
private:
};

} // namespace testbed

} // namespace pj

#endif