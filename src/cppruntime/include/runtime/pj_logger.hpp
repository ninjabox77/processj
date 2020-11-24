#ifndef PJ_LOGGER_HPP
#define PJ_LOGGER_HPP

#include <iostream>
#include <mutex>

namespace pj_runtime
{
    class pj_logger
    {
    public:
        static std::mutex log_mtx;
        static void log()
        {
            std::lock_guard<std::mutex> log_lck(log_mtx);
            std::cout << std::endl;
        }

        template <typename T>
        static void log(T value)
        {
            std::lock_guard<std::mutex> log_lck(log_mtx);
            std::cout << value << std::endl;
        }

        template <typename T, typename... Ts>
        static void log(T value, Ts... values)
        {
            std::unique_lock<std::mutex> log_lck(log_mtx);
            std::cout << value;
            log_lck.unlock();
            log(values...);
        }
    };
}

#endif