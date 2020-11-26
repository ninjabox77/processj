#ifndef PJ_PROTOCOL_HPP
#define PJ_PROTOCOL_HPP

namespace pj_runtime
{
    // Every protocol case inherits from this struct
    struct pj_protocol_case
    {
    public:
        int tag;
    };
}

#endif