#ifndef PJ_MANY2MANY_CHANNEL_TEST_HPP
#define PJ_MANY2MANY_CHANNEL_TEST_HPP

namespace pj
{

namespace testbed
{

class m2m_channel_writer : public process
{
public:

	int m_data;

	m2m_channel_writer() = delete;

	m2m_channel_writer(std::shared_ptr<many2many_channel<int>> chan)
	: m_chan(chan)
	{

	}

	virtual ~m2m_channel_writer() = default;

	void operator ()()
	{
		switch(get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
			case 2: goto L2; break;
		}	

	L0:
		if (!m_chan->claim_write(this))
		{
			std::cout << "a writer is waiting for the write end" << std::endl;
			set_run_label(1);
			return;
		}
		else
		{
			std::cout << "a writer claimed the write end" << std::endl;
		}
	L1:
		std::cout << "a writer is writing to the channel" << std::endl;
		m_data = 69;
		m_chan->write(this, m_data);
		std::cout << "a writer wrote data to the channel" << std::endl;
		set_run_label(2);
		return;
	L2:
		std::cout << "a writer is terminating" << std::endl;
		m_chan->unclaim_write();
		terminate();
		return;
	}

protected:

	std::shared_ptr<many2many_channel<int>> m_chan;
private:
};

class m2m_channel_reader : public process
{
public:

	int m_data;

	m2m_channel_reader() = delete;

	m2m_channel_reader(std::shared_ptr<many2many_channel<int>> chan)
	: m_chan(chan)
	{

	}

	virtual ~m2m_channel_reader() = default;

	void operator ()()
	{
		switch(get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
			case 2: goto L2; break;
		}

	L0:
		if (!m_chan->claim_read(this))
		{
			std::cout << "a reader is waiting for the read end" << std::endl;
			set_run_label(1);
			return;
		}
		else
		{
			std::cout << "a reader has claimed the read end" << std::endl;
		}

	L1:
		if (!m_chan->is_ready_to_read(this))
		{
			std::cout << "reader process waiting for channel to be ready" << std::endl;
			set_run_label(2);
			return;
		}
		else
		{
			std::cout << "reader process reading data from channel" << std::endl;
		}
	L2:
		m_data = m_chan->read(this);
		m_chan->unclaim_read();
		std::cout << "reader process read data " << m_data
		          << "\nreader process terminating" << std::endl;
		terminate();
		return;
	}

protected:

	std::shared_ptr<many2many_channel<int>> m_chan;
private:
};

class m2m_channel_main : public process
{
public:

	std::shared_ptr<many2many_channel<int>> m_chan;
	std::shared_ptr<par> m_par;
	int m_count;

	m2m_channel_main()
	{

	}

	virtual ~m2m_channel_main() = default;

	void operator ()()
	{
		switch(get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
		}

	L0:
		std::cout << "m2m_channel_main start" << std::endl;

		m_chan = std::make_shared<many2many_channel<int>>();
		m_par = std::make_shared<par>(this, 6);

		class m2m_channel_writer_finalize : public m2m_channel_writer
		{
		public:
			m2m_channel_main* m_parent;

			m2m_channel_writer_finalize(std::shared_ptr<many2many_channel<int>> chan,
				                        m2m_channel_main*                     parent)
			: m2m_channel_writer{chan}, m_parent(parent)
			{

			}

			virtual ~m2m_channel_writer_finalize() = default;

			void finalize()
			{
				m_parent->m_par->decrement();
			}
		protected:
		private:
		};

		for (m_count = 0; m_count < 3; ++m_count)
		{
			m_sched->enqueue(new m2m_channel_writer_finalize(m_chan, this));
		}

		class m2m_channel_reader_finalize : public m2m_channel_reader
		{
		public:
			m2m_channel_main* m_parent;

			m2m_channel_reader_finalize(std::shared_ptr<many2many_channel<int>> chan,
				                        m2m_channel_main*                     parent)
			: m2m_channel_reader{chan}, m_parent(parent)
			{

			}

			virtual ~m2m_channel_reader_finalize() = default;

			void finalize()
			{
				m_parent->m_par->decrement();
			}
		};

		for (m_count = 0; m_count < 3; ++m_count)
		{
			m_sched->enqueue(new m2m_channel_reader_finalize(m_chan, this));
		}

		if (m_par->should_yield())
		{
			set_run_label(1);
			return;
		}
	L1:
		std::cout << "m2m_channel_main process terminating" << std::endl;
		terminate();
		return;
	}

protected:
private:
};

class m2m_channel_test
{
public:
	m2m_channel_test()
	{

	}

	virtual ~m2m_channel_test() = default;

	void operator ()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating scheduler for m2m_channel test.";
		policy_sched sched(1);
		sched.init();

		logger::log(logger::DEBUG) << "inserting main process into scheduler.";
		sched.insert(new m2m_channel_main());

		logger::log(logger::DEBUG) << "starting scheduler.";
		sched.start();
	}

protected:
private:
};

} // namespace testbed

} // namespace pj

#endif