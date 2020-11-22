#!/usr/bin/env bash

TESTNAMES=()
i=0

pushd tests

for test in *.pj; do
	# === TESTS THAT DO NOT MATTER ===
	# no main
	if [ $test == "bytecode.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "integrate.pj" ]; then
		continue
	fi
	# no main
	if [ $test == "localDecl.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "sharedchan.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "sharedread.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "sharedwrite.pj" ]; then
		continue
	fi
	# breaks on read call
	if [ $test == "simple.pj" ]; then
		continue
	fi
	# no main
	if [ $test == "test22.pj" ]; then
		continue
	fi

	# === TESTS THAT NEED WORK ===
	# no library
	if [ $test == "mandelbrot.pj" ]; then
		continue;
	fi
	# no library
	if [ $test == "mandelbrot2.pj" ]; then
		continue;
	fi
	# TODO: figure this one out
	if [ $test == "NetworkProc.pj" ]; then
		continue
	fi
	# TODO: figure this one out
	if [ $test == "santa.pj" ]; then
		continue
	fi
	# channel read in for loop issue
	if [ $test == "silly.pj" ]; then
		continue
	fi

	# echo $test
	TESTNAMES[i]=$test
	i=$i+1
done

popd
i=0

for test in ./tests/*.pj; do
	# === TESTS THAT DO NOT MATTER ===
	# no main
	if [ $test == "./tests/bytecode.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "./tests/integrate.pj" ]; then
		continue
	fi
	# no main
	if [ $test == "./tests/localDecl.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "./tests/sharedchan.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "./tests/sharedread.pj" ]; then
		continue
	fi
	# infinite loop, tested to work
	if [ $test == "./tests/sharedwrite.pj" ]; then
		continue
	fi
	# not needed, breaks on read call
	if [ $test == "./tests/simple.pj" ]; then
		continue
	fi
	# no main
	if [ $test == "./tests/test22.pj" ]; then
		continue
	fi

	# === TESTS THAT NEED WORK ===	
	# no library
	if [ $test == "./tests/mandelbrot.pj" ]; then
		continue
	fi
	# no library
	if [ $test == "./tests/mandelbrot2.pj" ]; then
		continue
	fi
	# TODO: figure this one out
	if [ $test == "./tests/NetworkProc.pj" ]; then
		continue
	fi
	# TODO: figure this one out
	if [ $test == "./tests/santa.pj" ]; then
		continue
	fi
	# channel read in for loop issue
	if [ $test == "./tests/silly.pj" ]; then
		continue
	fi

	echo "compiling $test"
	./blahc $test

	TESTNAME=${TESTNAMES[i]%???}
	./$TESTNAME

	if [ $? != 0 ]; then
		echo "test $TESTNAME failed"
		exit 1
	fi

	make clean NAME=$TESTNAME
	i=$i+1
done