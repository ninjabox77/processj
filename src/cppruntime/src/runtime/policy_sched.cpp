#include <runtime/scheduler.hpp>

namespace pj
{

policy_sched::policy_sched(unsigned int CPU)
: NUM_CPU(CPU)
{
	sched_flag = new unsigned int[NUM_CPU];
}

policy_sched::~policy_sched()
{
	if (m_sched.joinable())
	{
		m_sched.join();
	}

	for (unsigned int i = 0; i < NUM_CPU; ++i)
	{
		sched_set[i]->kill();
	}

	for (unsigned int i = 0; i < NUM_CPU; ++i)
	{
		delete sched_set[i];
	}

	delete[] sched_flag;
 }

void policy_sched::init(){
	std::stringstream ss;
	ss << "policy_sched::init called from thread "
	   << std::this_thread::get_id() << ".";
	logger::log(logger::DEBUG) << ss.str();

	for (unsigned int i = 0; i < NUM_CPU; ++i)
	{
		ss.str(std::string());
		ss << "creating new scheduler on cpu " << i << ".";
		logger::log(logger::DEBUG) << ss.str();
		sched_set.push_back(new scheduler(i, this));
		sched_flag[i] = false;
	}

	logger::log(logger::DEBUG) << "child schedulers created and started.";
}

void policy_sched::start(){
	std::stringstream ss;
	ss << "policy_sched::start called from thread "
	   << std::this_thread::get_id() << ".";
	m_sched = std::thread(&policy_sched::run, this);
	ss.str(std::string());
	ss << "policy scheduler thread started with thread id "
	   << m_sched.get_id()
	   << ".";
   	logger::log(logger::DEBUG) << ss.str();
}

void policy_sched::run()
{
	std::stringstream ss;
	ss << "policy_sched::run called from thread "
	   << std::this_thread::get_id() << ".";
	logger::log(logger::DEBUG) << ss.str();

	ss.str(std::string());
	ss << "isolating policy_sched thread to cpu "
	   << std::thread::hardware_concurrency() - 1
	   << ".";
	logger::log(logger::DEBUG) << ss.str();

	unsigned long nh = 0;
	while (!nh)
	{
		nh = m_sched.native_handle();
	}
	ss.str(std::string());
	ss << "native handle is " << nh << std::endl;
	logger::log(logger::DEBUG) << ss.str();
	ss.str(std::string());

	isolate_thread(m_s_mutex,
		           nh,
		           std::thread::hardware_concurrency() - 1,
		           std::thread::hardware_concurrency());

	unsigned int target_sched = 0;

	for (unsigned int i = 0; i < NUM_CPU; ++i)
	{
		sched_set[i]->start();
	}

	while (true)
	{
		if (!process_set.empty())
		{
			process* proc = process_set.pop();
			ss.str(std::string());
			ss << "inserting proc " << proc << " into scheduler "
			   << target_sched << "'s run queue.";
			logger::log(logger::DEBUG) << ss.str();
			proc->m_sched = sched_set[target_sched];
			sched_set[target_sched]->insert(proc);

			if (++target_sched >= NUM_CPU)
			{
				target_sched = 0;
			}
		}
		else
		{
			if (process_set.empty() && killed_children())
			{
				logger::log(logger::DEBUG) << "children done running.";
				break;
			}
		}
	}
}

void policy_sched::insert(process* p)
{
	std::stringstream ss;
	ss << "inserting process " << p << " into process_set.";
	logger::log(logger::DEBUG) << ss.str();
	process_set.push(p);
}	

bool policy_sched::killed_children()
{
	unsigned int killed = 0;

	for (unsigned int i = 0; i < NUM_CPU; ++i)
	{
		killed += sched_flag[i];
	}

	if (killed < NUM_CPU)
	{
		return false;
	}

	return true;
}

bool policy_sched::notify_empty(unsigned int cpu)
{
	std::stringstream ss;
	ss << "scheduler on cpu " << cpu << " is empty.";
	logger::log(logger::DEBUG) << ss.str();
	std::lock_guard<std::mutex> lg(m_s_mutex);
	sched_flag[cpu] = true;
	return false;
}

bool policy_sched::notify_running(unsigned int cpu)
{
	std::stringstream ss;
	ss << "scheduler on cpu " << cpu << "is running.";
	logger::log(logger::DEBUG) << ss.str();
	std::lock_guard<std::mutex> lg(m_s_mutex);
	sched_flag[cpu] = false;
	return true;
}
} // namespace pj

