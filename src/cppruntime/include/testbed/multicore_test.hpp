#ifndef PJ_MULTICORE_TEST_HPP
#define PJ_MULTICORE_TEST_HPP

#include <runtime/runtime.hpp>

#include <vector>
#include <sstream>

namespace pj
{
namespace testbed
{

class two_billion : public process
{
public:
	two_billion() = delete;
	
	two_billion(unsigned int value)
	: value(value)
	{

	}

	~two_billion() = default;

	void operator ()()
	{
		for (unsigned int i = 0; i < 2000000000; ++i)
		{

		}
		terminate();
		return;
	}

protected:
	unsigned int value;
private:
};

class multicore_main : public process
{
public:
	std::shared_ptr<par> m_par;
	unsigned int i;
	unsigned int range;

	multicore_main()
	{

	}

	void operator ()()
	{
		switch (get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
		}

	L0:
		std::cout << "multicore_main process " << this << " starting.\n";

		range = 8;

		std::cout << "range is " << range << std::endl;

		m_par = std::make_shared<par>(this, range);
		
		class two_billion_finalize : public two_billion
		{
		public:
			multicore_main* m_parent;

			two_billion_finalize(unsigned int     value,
			                     multicore_main* parent)
			: two_billion{value}, m_parent(parent)
			{

			}

			virtual ~two_billion_finalize() = default;

			virtual void finalize()
			{
				m_parent->m_par->decrement();
			}
		protected:
		private:
		};

		for (i = 0; i < range; ++i)
		{
			m_sched->enqueue(new two_billion_finalize(i, this));
		}

		if (m_par->should_yield())
		{
			set_run_label(1);
			return;
		}

	L1:
		std::cout << "multicore_main process " << this << " ending.\n";
		terminate();

	}
protected:
private:
};

class multicore_test
{
	public:
	multicore_test()
	{
	}

	~multicore_test()
	{
	}

	void operator()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating master scheduler for multicore_test.";
		
		policy_sched m_sched(8);
		m_sched.init();

		logger::log(logger::DEBUG) << "inserting main process into master scheduler.";
		m_sched.insert(new multicore_main());

		logger::log(logger::DEBUG) << "starting scheduler.";
		m_sched.start();
	}
};

} // namespace testbed

} // namespace pj

#endif