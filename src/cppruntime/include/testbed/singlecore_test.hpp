#ifndef PJ_SINGLECORE_TEST_HPP
#define PJ_SINGLECORE_TEST_HPP

#include <runtime/runtime.hpp>

#include <sstream>

namespace pj
{

namespace testbed
{

class some_object
{
public:

	int m_x;

	some_object()
	: m_x(0)
	{

	}

	some_object(int x)
	: m_x(x)
	{

	}

	~some_object()
	{

	}

protected:
private:
};

class singlecore_test_process : public process
{
public:

	std::shared_ptr<timer> t;

	singlecore_test_process(std::shared_ptr<some_object> obj)
	: m_obj(obj)
	{

	}

	virtual ~singlecore_test_process()
	{

	}

	virtual void operator ()()
	{
		switch (get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
		}

	L0:
		std::cout << "singlecore_test_process with object value "
		          << m_obj->m_x
		          << std::endl;

      	t = std::make_shared<timer>(this, 3000);
      	t->start();
      	m_sched->insert(t);
      	set_not_ready();
      	set_run_label(1);
      	return;

    L1:
    	std::cout << "singlecore_test_process ending.\n";
		terminate();
	}

protected:
	std::shared_ptr<some_object> m_obj;
private:
};

class singlecore_test
{
public:

	singlecore_test()
	{

	}

	~singlecore_test()
	{

	}

	void operator ()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating scheduler for singlecore test.";
		policy_sched sched(1);
		sched.init();

		logger::log(logger::DEBUG) << "creating shared pointer for singlecore process.";
		std::shared_ptr<some_object> p_obj = std::make_shared<some_object>(69);
		ss << "object created with value " << p_obj->m_x << ".";
		logger::log(logger::DEBUG) << ss.str();

		logger::log(logger::DEBUG) << "instantiating process for singlecore test.";
		singlecore_test_process* p = new singlecore_test_process(p_obj);

		logger::log(logger::DEBUG) << "inserting process into scheduler.";
		sched.insert(p);

		logger::log(logger::DEBUG) << "starting scheduler.";
		sched.start();
	}

protected:
private:
};

} // namespace testbed

} // namespace pj

#endif