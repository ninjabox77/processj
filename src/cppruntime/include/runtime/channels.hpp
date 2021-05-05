#ifndef PJ_CHANNELS_HPP
#define PJ_CHANNELS_HPP

#include <runtime/scheduler.hpp>

#include <queue>

namespace pj
{

enum channel_types
{
	NONE,
	ONE2ONE,
	ONE2MANY,
	MANY2ONE,
	MANY2MANY
};

class channel_type
{
public:

	channel_type();

	channel_type(channel_types type);

	~channel_type();

	channel_types get_type();

	std::string get_type_string();

protected:
private:

	channel_types m_type;
	std::string m_type_str;
};

template <typename T>
class channel
{
public:

	channel()
	: m_reader(nullptr),
	  m_writer(nullptr),
	  m_type(NONE),
	  m_data(reinterpret_cast<T>(0))
	{

	}

	channel(channel_types type)
	: m_reader(nullptr),
	  m_writer(nullptr),
	  m_type(type),
	  m_data(reinterpret_cast<T>(0))
	{

	}

	channel(channel_type type)
	: m_reader(nullptr),
	  m_writer(nullptr),
	  m_type(type),
	  m_data(reinterpret_cast<T>(0))
	{

	}

	~channel()
	{

	}

	bool is_ready_to_read(process* p)
	{
		std::lock_guard<std::mutex> lg(m_chan_mtx);

		if (m_writer)
		{
			return true;
		}

		m_reader = p;
		m_reader->set_not_ready();
		return false;
	}

	bool is_ready_to_write(process* p)
	{
		return true;
	}

	void write(process* p, T data)
	{
		std::lock_guard<std::mutex> lg(m_chan_mtx);
		m_data = data;

		m_writer = p;
		m_writer->set_not_ready();

		if (m_reader)
		{
			m_reader->set_ready();
		}
	}

	T read(process* p)
	{
		std::lock_guard<std::mutex> lg(m_chan_mtx);
		m_writer->set_ready();
		m_writer = nullptr;

		m_reader = nullptr;

		return m_data;
	}

	channel_type get_type()
	{
		return m_type;
	}

	process* alt_get_writer(process* p)
	{
		std::lock_guard<std::mutex> lg(m_chan_mtx);
		if (!m_writer)
		{
			m_reader = p;
		}

		return m_writer;
	}

	process* set_reader_get_writer(process* p)
	{
		m_reader = p;
		return m_writer;
	}

	virtual bool claim_read(process* p)
	{
		return false;
	}

	virtual void unclaim_read(process* p)
	{

	}

	virtual bool claim_write(process* p)
	{
		return false;
	}

	virtual void unclaim_write(process* p)
	{

	}

protected:

	std::mutex m_chan_mtx;
	process* m_reader;
	process* m_writer;
	channel_type m_type;
	T m_data;
private:
};

template <typename T>
class one2one_channel : public channel<T>
{
public:

	one2one_channel()
	{
		this->m_type = ONE2ONE;
	}

	~one2one_channel()
	{

	}

	T pre_read_rendezvous(process* p)
	{
		T data = this->m_data;
		this->m_data = reinterpret_cast<T>(0);

		return data;
	}

	void post_read_rendezvous(process* p)
	{
		this->m_writer->set_ready();
		this->m_writer = nullptr;
		this->m_reader = nullptr;
	}

protected:
private:
};

template <typename T>
class one2many_channel : public channel<T>
{
public:

	one2many_channel()
	: m_read_claim(nullptr)
	{
		this->m_type = ONE2MANY;
	}

	~one2many_channel()
	{
		m_read_claim = nullptr;
	}

	bool claim_read(process* p)
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (!m_read_claim || m_read_claim == p)
		{
			m_read_claim = p;
			return true;
		}

		p->set_not_ready();
		m_read_queue.push(p);

		return false;
	}

	void unclaim_read()
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (m_read_queue.empty())
		{
			m_read_claim = nullptr;
		}
		else
		{
			process* p = m_read_queue.front();
			m_read_queue.pop();
			m_read_claim = p;
			m_read_claim->set_ready();
		}
	}

protected:

	process* m_read_claim;
	std::queue<process*> m_read_queue;
private:
};

template <typename T>
class many2one_channel : public channel<T>
{
public:
	many2one_channel()
	: m_write_claim(nullptr)
	{
		this->m_type = MANY2ONE;
	}

	~many2one_channel()
	{
		m_write_claim = nullptr;
	}

	bool claim_write(process* p)
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (!m_write_claim || m_write_claim == p)
		{
			m_write_claim = p;
			return true;
		}

		p->set_not_ready();
		m_write_queue.push(p);

		return false;
	}

	void unclaim_write()
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (m_write_queue.empty())
		{
			m_write_claim = nullptr;
		}
		else
		{
			process* p = m_write_queue.front();
			m_write_queue.pop();
			m_write_claim = p;
			m_write_claim->set_ready();
		}
	}
protected:

	process* m_write_claim;
	std::queue<process*> m_write_queue;
private:
};

template <typename T>
class many2many_channel : public channel<T>
{
public:
	many2many_channel()
	: m_write_claim(nullptr),
	  m_read_claim(nullptr)
	{
		this->m_type = MANY2MANY;
	}

	~many2many_channel()
	{

	}

	bool claim_write(process* p)
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (!m_write_claim || m_write_claim == p)
		{
			m_write_claim = p;
			return true;
		}

		p->set_not_ready();
		m_write_queue.push(p);

		return false;
	}

	void unclaim_write()
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (m_write_queue.empty())
		{
			m_write_claim = nullptr;
		}
		else
		{
			process* p = m_write_queue.front();
			m_write_queue.pop();
			m_write_claim = p;
			m_write_claim->set_ready();
		}
	}

	bool claim_read(process* p)
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (!m_read_claim || m_read_claim == p)
		{
			m_read_claim = p;
			return true;
		}

		p->set_not_ready();
		m_read_queue.push(p);

		return false;
	}

	void unclaim_read()
	{
		std::lock_guard<std::mutex> lg(this->m_chan_mtx);

		if (m_read_queue.empty())
		{
			m_read_claim = nullptr;
		}
		else
		{
			process* p = m_read_queue.front();
			m_read_queue.pop();
			m_read_claim = p;
			m_read_claim->set_ready();
		}
	}
	
protected:

	process* m_write_claim;
	process* m_read_claim;
	std::queue<process*> m_write_queue;
	std::queue<process*> m_read_queue;
private:
};

} // namespace pj

#endif