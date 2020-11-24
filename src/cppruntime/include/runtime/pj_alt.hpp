#ifndef PJ_ALT_HPP
#define PJ_ALT_HPP

#include <runtime/pj_process.hpp>
#include <runtime/pj_channel.hpp>
#include <utilities/rtti.hpp>

#include <iostream>
#include <vector>
#include <variant>
#include <string>

#include <sys/types.h>

namespace pj_runtime
{
    typedef std::variant<std::string, pj_channel*, pj_timer*> pj_alt_guard_type;

    class pj_alt
    {
    public:
        /* TODO: find out if we really need this/as a string */
        /* TODO: need to make this work */
        inline static const std::string SKIP = "skip";

        pj_alt() = delete;

        pj_alt(uint64_t count, pj_process* p)
        {
            this->process = p;
        }

        /* TODO: specialized ctors */

        ~pj_alt()
        {
            pj_logger::log("pj_alt destructor called");
        }

        /* NOTE: if all bool guards are false: runtime_error */
        bool set_guards(std::vector<bool>            b_guards,
                        std::vector<pj_alt_guard_type> guards)
        {
            this->b_guards = b_guards;
            this->guards   = guards;

            for(uint32_t i = 0; i < this->b_guards.size(); ++i)
            {
                if(b_guards[i])
                {
                    return true;
                }
            }

            return false;
        }

        /* NOTE: must be int32_t to return a -1 in case nothing
         * is enabled
         */
        /* TODO: remove debug logging */
        int32_t enable(void)
        {
            pj_logger::log("in enable:");
            for(uint32_t i = 0; i < this->guards.size(); ++i)
            {
                if(!this->b_guards[i])
                {
                    continue;
                }

                if(std::holds_alternative<std::string>(this->guards[i]))
                {
                    pj_logger::log("guard is a SKIP");
                    if(std::get<std::string>(this->guards[i]) == SKIP)
                    {
                        this->process->set_ready();
                        return static_cast<int32_t>(i);
                    }
                }
                else if(std::holds_alternative<pj_runtime::pj_channel*>(this->guards[i]))
                {
                    pj_logger::log("guard is a channel");
                    if(std::get<pj_runtime::pj_channel*>(this->guards[i])->alt_get_writer(this->process) != nullptr)
                    {
                        pj_logger::log("alt_get_writer not null");
                        this->process->set_ready();
                        return static_cast<int32_t>(i);
                    }
                    else { pj_logger::log("alt_get_writer null"); }
                }
                else if(std::holds_alternative<pj_runtime::pj_timer*>(this->guards[i]))
                {
                    pj_logger::log("guard is a timer");
                    if(std::get<pj_runtime::pj_timer*>(this->guards[i])->get_real_delay() <= std::chrono::time_point_cast<std::chrono::milliseconds>(std::chrono::system_clock::now()))
                    {
                        pj_logger::log("timer timed out");
                        this->process->set_ready();
                        std::get<pj_runtime::pj_timer*>(this->guards[i])->expire();
                        return static_cast<int32_t>(i);
                    }
                    else
                    {
                        pj_logger::log("timer not timed out");
                        std::get<pj_runtime::pj_timer*>(this->guards[i])->start();
                    }
                }
            }
            return -1;
        }

        /* TODO: remove debug logging */
        int32_t disable(int32_t i)
        {
            pj_logger::log("in disable:");
            int32_t selected = -1;
            if(i == -1)
            {
                i = this->guards.size() - 1;
            }

            for(int32_t j = i; j >= 0; --j)
            {
                if(!this->b_guards[i])
                {
                    continue;
                }

                if(std::holds_alternative<std::string>(this->guards[j]))
                {
                    pj_logger::log("guard is a SKIP");
                    if(std::get<std::string>(this->guards[j]) == SKIP)
                    {
                        selected = j;
                    }
                }
                else if(std::holds_alternative<pj_runtime::pj_channel*>(this->guards[j]))
                {
                    pj_logger::log("guard is a channel");
                    if(std::get<pj_runtime::pj_channel*>(this->guards[j])->set_reader_get_writer(nullptr) != nullptr)
                    {
                        pj_logger::log("alt_get_writer not null");
                        selected = j;
                    }
                    else { pj_logger::log("alt_get_writer null"); }
                }
                else if(std::holds_alternative<pj_runtime::pj_timer*>(this->guards[j]))
                {
                    pj_logger::log("guard is a timer");
                    if(std::get<pj_runtime::pj_timer*>(this->guards[j])->expired())
                    {
                        pj_logger::log("timer expired");
                        selected = j;
                    }
                    else
                    {
                        pj_logger::log("timer not expired");
                        std::get<pj_runtime::pj_timer*>(this->guards[j])->kill();
                    }
                }
            }
            return selected;
        }

    private:
        /* TODO: could make it a vector of std::pairs of something, boolean
         * instead of two separate vectors if wanted
         */
        pj_process* process;
        std::vector<pj_alt_guard_type> guards;
        std::vector<bool> b_guards;
    };
}

#endif