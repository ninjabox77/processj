#ifndef PJ_PROTOCOL_HPP
#define PJ_PROTOCOL_HPP

namespace pj_runtime
{
<<<<<<< HEAD
    // Every protocol case inherits from this struct
=======
    // pj_protocol_cases are structs
>>>>>>> added new runtime with better protocols, need to add codegen
    struct pj_protocol_case
    {

    };

<<<<<<< HEAD
    /* If we have some protocol case Foo, then
     * the Protocol P that includes this as 
     * one of its protocol cases is defined as
     * using P = std::variant<Foo*>;
=======
    /* pj_protocols are then mapped as such
     * ---
     * suppose we have a pj_protocol A consisting of
     * struct foo : pj_protocol_case, and struct
     * bar : pj_protocol_case. then the pj_protocol
     * that would result of those two would be defined
     * as
     *
     * using A = std::variant<foo*, bar*>;
>>>>>>> added new runtime with better protocols, need to add codegen
     */
}

#endif