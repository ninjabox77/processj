#ifndef PROTOCOL_TEST_HPP
#define PROTOCOL_TEST_HPP

namespace pj_tests
{
    struct request : public pj_runtime::pj_protocol_case
    {
<<<<<<< HEAD
        request(int amount, double value)
        : amount(amount), value(value)
        {

        }

        int amount;
        double value;
    };

    struct reply : public pj_runtime::pj_protocol_case
    {
        reply(bool ack)
        : ack(ack)
        {

        }

        bool ack;
=======
    public:

        request() = default;

        request(int number, double amount)
        : number(number), amount(amount)
        {

        }

        ~request() = default;

        int number;
        double amount;
    };

    struct reply : public pj_runtime::pj_protocol_case
    {
    public:

        reply() = default;

        reply(bool status)
        : status(status)
        {

        }

        ~reply() = default;

        bool status;
>>>>>>> added new runtime with better protocols, need to add codegen
    };

    using protocol_p = std::variant<request*, reply*>;

    class protocol_writer : public pj_runtime::pj_process
    {
    public:
        protocol_writer() = delete;
<<<<<<< HEAD
        protocol_writer(pj_runtime::pj_scheduler*                  sched,
=======
        protocol_writer(pj_runtime::pj_scheduler*                   sched,
>>>>>>> added new runtime with better protocols, need to add codegen
                        pj_runtime::pj_one2one_channel<protocol_p>* chan)
        : sched(sched), chan(chan)
        {

        }

        void run()
        {
            switch(get_label())
            {
                case 0: goto L0; break;
<<<<<<< HEAD
                case 1: goto L1; break;
                case 2: goto LEND; break;
            }

        L0:
            std::cout << "creating protocol" << std::endl;
            static protocol_p myP = new request{.amount = 69, .value = 4.20};
            if(!chan->is_ready_to_write(this))
            {
                set_label(1);
                return;
            }
        L1:
            std::cout << "writing protocol to channel" << std::endl;
            chan->write(this, myP);
            set_label(2);
            return;
        LEND:
            std::cout << "writer dying" << std::endl;
=======
                case 1: goto LEND; break;
            }

        L0:
            static protocol_p myP;
            std::cout << "writing protocol" << std::endl;
            myP = new request{.number = 69, .amount = 4.20};
            chan->write(this, myP);
            set_label(1);
            return;
        LEND:
            std::cout << "writer done" << std::endl;
>>>>>>> added new runtime with better protocols, need to add codegen
            terminate();
            return;
        }

    protected:
    private:
<<<<<<< HEAD
        pj_runtime::pj_scheduler*                  sched;
=======
        pj_runtime::pj_scheduler*                   sched;
>>>>>>> added new runtime with better protocols, need to add codegen
        pj_runtime::pj_one2one_channel<protocol_p>* chan;
    };

    class protocol_reader : public pj_runtime::pj_process
    {
    public:
        protocol_reader() = delete;
<<<<<<< HEAD
        protocol_reader(pj_runtime::pj_scheduler*                  sched,
=======
        protocol_reader(pj_runtime::pj_scheduler*                   sched,
>>>>>>> added new runtime with better protocols, need to add codegen
                        pj_runtime::pj_one2one_channel<protocol_p>* chan)
        : sched(sched), chan(chan)
        {

        }

        void run()
        {
            switch(get_label())
            {
                case 0: goto L0; break;
                case 1: goto LEND; break;
            }

        L0:
<<<<<<< HEAD
            if(!chan->is_ready_to_read(this))
            {
=======
            static protocol_p myP;
            std::cout << "reading channel" << std::endl;
            if(!chan->is_ready_to_read(this))
            {
                std::cout << "chan not ready" << std::endl;
>>>>>>> added new runtime with better protocols, need to add codegen
                set_label(1);
                return;
            }
        LEND:
<<<<<<< HEAD
            std::cout << "reading protocol from channel" << std::endl;
            static protocol_p myP = chan->read(this);
            if(std::holds_alternative<request*>(myP))
            {
                request* myR = std::get<request*>(myP);
                std::cout << "REQUEST: " << myR->amount
                          << ", " << myR->value << std::endl;
                delete myR;
            } else if(std::holds_alternative<reply*>(myP))
            {
                reply* myR = std::get<reply*>(myP);
                std::cout << "REPLY: " << std::boolalpha
                          << myR->ack << std::endl;
                delete myR;
            }
            std::cout << "reader dying" << std::endl;
=======
            myP = chan->read(this);

            if(std::holds_alternative<request*>(myP))
            {
                request* myReq = std::get<request*>(myP);
                std::cout << "got request: " << myReq->number << ", " << myReq->amount << std::endl;
                delete myReq;
            } else if(std::holds_alternative<reply*>(myP))
            {
                reply* myRep = std::get<reply*>(myP);
                std::cout << "got reply: " << std::boolalpha << myRep->status << std::endl;
                delete myRep;
            }
>>>>>>> added new runtime with better protocols, need to add codegen
            terminate();
            return;
        }
    protected:
    private:
        pj_runtime::pj_scheduler*                  sched;
        pj_runtime::pj_one2one_channel<protocol_p>* chan;
    };

    class protocol_test
    {
    public:
        protocol_test()
        {
            std::cout << "instantiating test...\n";
        }

<<<<<<< HEAD
=======
        /* NOTE: just for basics, see below. this should be the idea for
         * generated code
         */
>>>>>>> added new runtime with better protocols, need to add codegen
        void run()
        {
            std::cout << "\n *** CREATING SCHEDULER *** \n\n";
            pj_runtime::pj_scheduler sched;

            std::cout << "\n *** CREATING CHANNEL FOR PROTOCOL *** \n\n";
            pj_runtime::pj_one2one_channel<protocol_p> oto_ch;

<<<<<<< HEAD
            std::cout << "\n *** CREATING PROCESSES *** \n\n";
=======
            std::cout << "\n *** CREATING READER AND WRITER PROCESSES *** \n\n";
>>>>>>> added new runtime with better protocols, need to add codegen
            auto* oto_w = new protocol_writer(&sched, &oto_ch);
            auto* oto_r = new protocol_reader(&sched, &oto_ch);

            std::cout << "\n *** SCHEDULING PROCESSES *** \n\n";
            sched.insert(oto_w);
            sched.insert(oto_r);

            std::cout << "\n *** STARTING SCHEDULER *** \n\n";
            sched.start();
        }

        // void run()
        // {
        //     std::cout << "\n *** CREATING SCHEDULER *** \n\n";
        //     pj_runtime::pj_scheduler sched;

        //     std::cout << "\n *** CREATING CHANNEL FOR PROTOCOL *** \n\n";
        //     pj_runtime::pj_one2one_channel<pj_runtime::pj_protocol<protocol_p::request, protocol_p::reply>> oto_ch;

        //     std::cout << "\n *** CREATING READER AND WRITER PROCESSES *** \n\n";
        //     auto* oto_w =
        //         new protocol_writer<pj_runtime::pj_protocol<protocol_p::request, protocol_p::reply>>(0, &oto_ch);
        //     auto* oto_r =
        //         new protocol_reader<pj_runtime::pj_protocol<protocol_p::request, protocol_p::reply>>(1, &oto_ch);

        //     std::cout << "\n *** SCHEDULING PROCESSES *** \n\n";
        //     sched.insert(oto_w);
        //     sched.insert(oto_r);

        //     std::cout << "\n *** STARTING SCHEDULER *** \n\n";
        //     sched.start();
        // }
    };
}

#endif