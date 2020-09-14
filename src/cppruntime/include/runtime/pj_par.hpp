#ifndef PJ_PAR_HPP
#define PJ_PAR_HPP

#include <runtime/pj_process.hpp>

#include <sys/types.h>

namespace pj_runtime
{
    class pj_par
    {
    public:
        pj_par() = delete;

        pj_par(int process_count, pj_process* p)
        : process_count(process_count)
        {
            std::cout << "pj_par constructor called with arg " << process_count << std::endl;
            this->process = p;
        }

        ~pj_par()
        {
            std::cout << "pj_par destructor called\n";
        }

        void set_process_count(int32_t count)
        {
            this->process_count = count;
        }

        void decrement()
        {
            std::lock_guard<std::mutex> lock(this->mtx);

            this->process_count--;

            if(this->process_count == 0)
            {
                this->process->set_ready();
            }
        }

        bool should_yield()
        {
            std::lock_guard<std::mutex> lock(this->mtx);

            if(this->process_count == 0)
            {
                return false;
            }

            this->process->set_not_ready();
            return true;
        }

    protected:
    private:
        pj_process* process;
        int32_t process_count;
        std::mutex mtx;
    };
}

#endif