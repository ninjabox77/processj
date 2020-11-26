#ifndef PJ_PROCESS_HPP
#define PJ_PROCESS_HPP

#include <iostream>
#include <ostream>
#include <mutex>

#include <sys/types.h>

namespace pj_runtime
{
    class pj_process
    {
    public:
        pj_process()
        {
            
        }

        virtual ~pj_process()
        {

        }

        bool is_ready()
        {
            return ready;
        }

        void set_ready()
        {
            std::unique_lock<std::mutex> lock(this->mtx);
            if(!ready)
            {
                ready = true;
            }
        }

        void set_not_ready()
        {
            std::unique_lock<std::mutex> lock(this->mtx);
            if(ready)
            {
                ready = false;
            }
        }

        void terminate()
        {
            terminated = true;
        }

        bool is_terminated()
        {
            return terminated;
        }

        virtual void run()
        {

        }

        virtual void finalize()
        {

        }
        
        virtual void set_label(uint32_t label)
        {
            run_label = label;
        }

        virtual uint32_t get_label()
        {
            return run_label;
        }

        friend std::ostream& operator<<(std::ostream& o, pj_process& p)
        {
            return o << "base process operator<< called (nothing overwritten)";
        }

    protected:
        std::mutex mtx;

    private:
        uint32_t run_label      = 0;
        bool     ready       = true;
        bool     terminated = false;
    };
}

#endif