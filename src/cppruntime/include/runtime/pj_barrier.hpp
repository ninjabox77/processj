#ifndef PJ_BARRIER_HPP
#define PJ_BARRIER_HPP

#include <runtime/pj_process.hpp>

#include <iostream>
#include <vector>
#include <queue>

#include <sys/types.h>

/* (from ProcessJ java impl)
 * ProcessJ code will have something of this sort:
 * 
 * enroll(b):                                                                                                                     
 *                                                                                                                                 
 * b.addProc(this);                                                                                                               
 *                                                                                                                                  
 * b.sync():                                                                                                                       
 *                                                                                                                                
 * b.decrement();                                                                                                                
 * yield(......., X);                                                                                                            
 * X: ...                                                                                                                           
 */

namespace pj_runtime
{
    class pj_barrier
    {
    public:
        std::vector<pj_process*> synced;
        uint32_t enrolled = 0;

        pj_barrier()
        : enrolled(1)
        {
            pj_logger::log("pj_barrier constructor called");
        }

        ~pj_barrier()
        {
            pj_logger::log("pj_barrier destructor called");
        }

        void enroll(uint32_t proc_count)
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            this->enrolled += (proc_count - 1);
            pj_logger::log("total ", this->enrolled, " on barrier ", this);
        }

        void resign()
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            pj_logger::log("resigning from barrier ", this);
            if(this->enrolled > 1)
            {
                --this->enrolled;
            }
        }

        void sync(pj_process* process)
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            pj_logger::log("syncing on barrier ", this);
            process->set_not_ready();
            this->synced.push_back(process);
            pj_logger::log("have ", this->synced.size(), ", need ", enrolled);
            if(this->synced.size() == enrolled)
            {
                pj_logger::log("sync done, setting ready");
                for(uint32_t i = 0; i < this->synced.size(); ++i)
                {
                    this->synced[i]->set_ready();
                    pj_logger::log("process ", this->synced[i], " set ready");
                }
                synced.clear();
            }
        }

    protected:
    private:
        std::mutex mtx;
    };
}

#endif