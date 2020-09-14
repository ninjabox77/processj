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
            std::cout << "pj_barrier constructor called\n";
        }

        ~pj_barrier()
        {
            std::cout << "pj_barrier destructor called\n";
        }

        void enroll(uint32_t proc_count)
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            std::cout << "enroll() called with arg " << proc_count << std::endl;
            this->enrolled += (proc_count - 1);
            std::cout << "enrolled is now " << enrolled << std::endl;
        }

        void resign()
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            std::cout << "resign() called" << std::endl;
            if(this->enrolled > 1)
            {
                --this->enrolled;
            }
            std::cout << "resign() enrolled is now " << enrolled << std::endl;
        }

        void sync(pj_process* process)
        {
            std::lock_guard<std::mutex> lock(this->mtx);
            std::cout << "sync() called" << std::endl;
            process->set_not_ready();
            this->synced.push_back(process);
            // TODO: size() == enrolled is not right, push_back doesn't
            // align with size() calls.
            if(this->synced.size() == enrolled)
            {
                std::cout << "size == enrolled" << std::endl;
                for(uint32_t i = 0; i < this->synced.size(); ++i)
                {
                    std::cout << "setting process " << i << " ready" << std::endl;
                    this->synced[i]->set_ready();
                }
            }
        }

    protected:
    private:
        std::mutex mtx;
    };
}

#endif