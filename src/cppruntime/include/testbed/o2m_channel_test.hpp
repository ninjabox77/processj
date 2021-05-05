#ifndef PJ_ONE2MANY_CHANNEL_TEST_HPP
#define PJ_ONE2MANY_CHANNEL_TEST_HPP

namespace pj
{

namespace testbed
{

class o2m_channel_reader : public process
{
public:

	int m_data;

	o2m_channel_reader() = delete;

	o2m_channel_reader(std::shared_ptr<one2many_channel<int>> chan)
	: m_chan(chan)
	{

	}

	virtual ~o2m_channel_reader() = default;

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

	std::shared_ptr<one2many_channel<int>> m_chan;
private:
};

class o2m_channel_writer : public process
{
public:

	int m_data;

	o2m_channel_writer() = delete;

	o2m_channel_writer(std::shared_ptr<one2many_channel<int>> chan)
	: m_chan(chan)
	{

	}

	virtual ~o2m_channel_writer() = default;

	void operator ()()
	{
		switch(get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
			case 2: goto L2; break;
			case 3: goto L3; break;
		}
	L0:
		std::cout << "writer process writing data to channel" << std::endl;
		m_data = 69;
		m_chan->write(this, m_data);
		set_run_label(1);
		return;
	L1:
		std::cout << "writer process writing data to channel" << std::endl;
		m_data = 69;
		m_chan->write(this, m_data);
		set_run_label(2);
		return;
	L2:
		std::cout << "writer process writing data to channel" << std::endl;
		m_data = 69;
		m_chan->write(this, m_data);
		set_run_label(3);
		return;
	L3:
		std::cout << "writer process terminating" << std::endl;
		terminate();
		return;
	}

protected:

	std::shared_ptr<one2many_channel<int>> m_chan;
private:
};

class o2m_channel_main : public process
{
public:

	std::shared_ptr<one2many_channel<int>> m_chan;
	std::shared_ptr<par> m_par;
	int m_readercount;

	o2m_channel_main()
	{

	}

	virtual ~o2m_channel_main() = default;

	void operator ()()
	{
		switch(get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
		}

	L0:
		std::cout << "o2m_channel_main start" << std::endl;

		m_chan = std::make_shared<one2many_channel<int>>();
		m_par = std::make_shared<par>(this, 4);

		class o2m_channel_writer_finalize : public o2m_channel_writer
		{
		public:
			o2m_channel_main* m_parent;

			o2m_channel_writer_finalize(std::shared_ptr<one2many_channel<int>> chan,
				                        o2m_channel_main*                    parent)
			: o2m_channel_writer{chan}, m_parent(parent)
			{

			}

			virtual ~o2m_channel_writer_finalize() = default;

			void finalize()
			{
				m_parent->m_par->decrement();
			}
		protected:
		private:
		};

		m_sched->enqueue(new o2m_channel_writer_finalize(m_chan, this));

		class o2m_channel_reader_finalize : public o2m_channel_reader
		{
		public:
			o2m_channel_main* m_parent;

			o2m_channel_reader_finalize(std::shared_ptr<one2many_channel<int>> chan,
				                        o2m_channel_main*                    parent)
			: o2m_channel_reader{chan}, m_parent(parent)
			{

			}

			virtual ~o2m_channel_reader_finalize() = default;

			void finalize()
			{
				m_parent->m_par->decrement();
			}
		};

		for (m_readercount = 0; m_readercount < 3; ++m_readercount)
		{
			m_sched->enqueue(new o2m_channel_reader_finalize(m_chan, this));
		}

		if (m_par->should_yield())
		{
			set_run_label(1);
			return;
		}
	L1:
		std::cout << "o2m_channel_main process terminating" << std::endl;
		terminate();
		return;
	}

protected:
private:
};

class o2m_channel_test
{
public:

	o2m_channel_test()
	{

	}

	virtual ~o2m_channel_test() = default;

	void operator ()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating scheduler for o2m_channel test.";
		policy_sched sched(1);
		sched.init();

		logger::log(logger::DEBUG) << "inserting main process into scheduler.";
		sched.insert(new o2m_channel_main());

		logger::log(logger::DEBUG) << "starting scheduler.";
		sched.start();
	}

protected:
private:
};

} // namespace testbed

} // namespace pj

#endif