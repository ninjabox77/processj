#ifndef PJ_PROTOCOL_HPP
#define PJ_PROTOCOL_HPP

namespace pj_runtime
{
    // Every protocol case inherits from this struct
    struct pj_protocol_case
    {
        int tag;
    };

    /* If we have some protocol case Foo, then
     * the Protocol P that includes this as 
     * one of its protocol cases is defined as
     * using P = std::variant<Foo*>;
     */
}

#endif