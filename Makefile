# begin makefile -- remember make -j(max threads running + 2)

SOURCEDIR := src/cppruntime

CPPFLAGS := -Wall -g -I$(SOURCEDIR)/include -pthread -std=c++17

OFLAGS := $(CPPFLAGS) -c -fPIC

RUNTIMEFLAGS := -L$(SOURCEDIR)/lib -lprocessj

BUILDFLAGS := -Wall -g -I$(SOURCEDIR)/include -Ilib/C++ -pthread -std=c++17 -fsanitize=address

# OBJFILES := obj/runtime/scheduler.o\
# obj/runtime/barrier.o\
# obj/runtime/par.o\
# obj/utilities/delay_queue.o\
# obj/utilities/logger.o

OBJFILES := $(SOURCEDIR)/obj/runtime/process.o\
$(SOURCEDIR)/obj/runtime/timer.o\
$(SOURCEDIR)/obj/runtime/timer_queue.o\
$(SOURCEDIR)/obj/runtime/scheduler.o\
$(SOURCEDIR)/obj/runtime/policy_sched.o\
$(SOURCEDIR)/obj/runtime/barrier.o\
$(SOURCEDIR)/obj/runtime/par.o\
$(SOURCEDIR)/obj/runtime/channel_type.o\
$(SOURCEDIR)/obj/utilities/run_queue.o\
$(SOURCEDIR)/obj/utilities/delay_queue.o\
$(SOURCEDIR)/obj/utilities/logger.o

all: directoryexists objects $(SOURCEDIR)/lib/libprocessj.a

testbed: $(SOURCEDIR)/src/testbed/testbed.cpp
	g++ $(CPPFLAGS) -o $@ $< $(RUNTIMEFLAGS)

build: $(NAME)

$(NAME): builds/$(NAME).cpp
	g++ $(BUILDFLAGS) -o $@ $< $(RUNTIMEFLAGS)

directoryexists:
	if [ ! -d "$(SOURCEDIR)/lib" ]; then mkdir -p lib; fi
	if [ ! -d "$(SOURCEDIR)/obj" ]; then mkdir -p obj; fi
	if [ ! -d "$(SOURCEDIR)/obj/runtime" ]; then mkdir -p obj/runtime; fi
	if [ ! -d "$(SOURCEDIR)/obj/utilities" ]; then mkdir -p obj/utilities; fi

objects: $(OBJFILES)

$(SOURCEDIR)/lib/libprocessj.a: $(OBJFILES)
	ar cvq $@ $^
	ranlib $@
	rm -f $^

$(SOURCEDIR)/obj/runtime/process.o: $(SOURCEDIR)/src/runtime/process.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/timer.o: $(SOURCEDIR)/src/runtime/timer.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/timer_queue.o: $(SOURCEDIR)/src/runtime/timer_queue.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/scheduler.o: $(SOURCEDIR)/src/runtime/scheduler.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/policy_sched.o: $(SOURCEDIR)/src/runtime/policy_sched.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/barrier.o: $(SOURCEDIR)/src/runtime/barrier.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/par.o : $(SOURCEDIR)/src/runtime/par.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/runtime/channel_type.o: $(SOURCEDIR)/src/runtime/channel_type.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/utilities/run_queue.o: $(SOURCEDIR)/src/runtime/run_queue.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/utilities/delay_queue.o: $(SOURCEDIR)/src/utilities/delay_queue.cpp
	g++ $(OFLAGS) -o $@ $<

$(SOURCEDIR)/obj/utilities/logger.o: $(SOURCEDIR)/src/utilities/logger.cpp
	g++ $(OFLAGS) -o $@ $<

# NOTE: this will be changed to the actual name of the runtime binary
# when the runtime is fully implemented, and is essentially how
# programs will be built using this system
# pj_runtime: src/runtime/pj_runtime.cpp include/runtime/pj_runtime.hpp
# 	g++ $(CPPFLAGS) -o $@ $< $(RUNTIMEFLAGS)

build:


clean:
	rm -rf $(SOURCEDIR)/lib/libprocessj.a $(SOURCEDIR)/obj/**/*.o $(SOURCEDIR)/testbed
