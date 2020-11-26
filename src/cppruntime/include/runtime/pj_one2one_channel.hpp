#ifndef PJ_ONE2ONE_CHANNEL_HPP
#define PJ_ONE2ONE_CHANNEL_HPP

#include <runtime/pj_channel.hpp>

namespace pj_runtime
{
	template <typename T>
	class pj_one2one_channel : public pj_runtime::pj_channel
	{
	public:
		pj_one2one_channel()
		{
			this->type = pj_channel_types::ONE2ONE;
		}

		~pj_one2one_channel()
		{

		}

		void write(pj_process* p, T data)
		{
			std::lock_guard<std::mutex> lock(this->mtx);
			this->data = data;
			writer = p;
			writer->set_not_ready();
			if(reader)
			{
				reader->set_ready();
			}
		}

		T read(pj_process* p)
		{
			std::lock_guard<std::mutex> lock(this->mtx);
			writer->set_ready();
			writer = nullptr;
			reader = nullptr;
			return this->data;
		}

		bool is_ready_to_read(pj_process* p)
		{
			std::lock_guard<std::mutex> lock(this->mtx);
			if(writer)
			{
				return true;
			}
			else
			{
				reader = p;
				reader->set_not_ready();
			}
			return false;
		}

		bool is_ready_to_write(pj_process* p)
		{
			return true;
		}

		T pre_read_rendezvous(pj_process* p)
		{
			T data = this->data;
			this->data = reinterpret_cast<T>(0);
			return data;
		}

		void post_read_rendezvous(pj_process* p)
		{
			writer->set_ready();
			writer = nullptr;
			reader = nullptr;
		}

	protected:
	T data;
	};
}

#endif