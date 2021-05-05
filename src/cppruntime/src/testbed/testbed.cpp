#include <iostream>

#include <testbed/testbed.hpp>

pj::logger::settings pj::logger::LOG_SETTINGS = {.headers = true, .level = pj::logger::INFO};

int main(void)
{
	pj::logger::log_handler::start_logging();

	// pj::testbed::singlecore_test sct;
	// sct();

	// pj::testbed::barrier_test bt;
	// bt();

	// pj::testbed::multicore_test mtt;
	// mtt();

	// pj::testbed::o2o_channel_test o2oct;
	// o2oct();

	// pj::testbed::o2m_channel_test o2mct;
	// o2mct();

	// pj::testbed::m2o_channel_test m2oct;
	// m2oct();

	// pj::testbed::m2m_channel_test m2mct;
	// m2mct();

	pj::testbed::parfor_test pft;
	pft();

	pj::logger::log_handler::end_logging();
}