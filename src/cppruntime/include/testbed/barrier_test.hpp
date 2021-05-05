#ifndef PJ_BARRIER_TEST_HPP
#define PJ_BARRIER_TEST_HPP

#include <runtime/runtime.hpp>

#include <sstream>

namespace pj
{

namespace testbed
{

class barrier_process : public process
{
public:
	barrier_process(std::shared_ptr<barrier> b)
	: m_bar(b)
	{

	}

	virtual ~barrier_process()
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
		std::cout << "barrier_process " << this << " starting.\n";

		m_bar->sync(this);
		set_run_label(1);
		return;

	L1:
		std::cout << "barrier_process " << this << " ending.\n";
		terminate();
	}

protected:
	std::shared_ptr<barrier> m_bar;
private:
};

class main_process : public process
{
public:
	std::shared_ptr<barrier> m_bar;
	std::shared_ptr<par> m_par;
	int i;

	main_process()
	{

	}

	virtual ~main_process()
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
		std::cout << "main_process process " << this << " starting.\n";
		m_bar = std::make_shared<barrier>();
		m_par = std::make_shared<par>(this, 2);
		m_bar->enroll(2);
		
		class barrier_process_finalize : public barrier_process
		{
		public:
			main_process* m_parent;

			barrier_process_finalize(std::shared_ptr<barrier> b, main_process* parent)
			: barrier_process{b}, m_parent(parent)
			{

			}

			virtual ~barrier_process_finalize() = default;

			virtual void finalize()
			{
				m_parent->m_par->decrement();
				m_bar->resign();
			}
		protected:
		private:
		};

		for (i = 0; i < 2; ++i)
		{
			m_sched->insert(new barrier_process_finalize(m_bar, this));
		}

		if (m_par->should_yield())
		{
			set_run_label(1);
			return;
		}

	L1:
		std::cout << "main_process process " << this << " ending.\n";
		terminate();
	}
protected:
private:
};

class barrier_test
{
public:
	barrier_test()
	{

	}

	~barrier_test()
	{

	}

	void operator ()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating scheduler for barrier test.";
		policy_sched sched(1);
		sched.init();

		logger::log(logger::DEBUG) << "instantiating process for barrier test.";
		main_process* p = new main_process();

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