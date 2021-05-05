#ifndef PJ_PAR_FOR_TEST_HPP
#define PJ_PAR_FOR_TEST_HPP

#include <cstdlib>

namespace pj
{

namespace testbed
{

static inline int calpixel(double cimag, double creal)
{
	int count = 0;
	int max = 256;
	double zreal = 0;
	double zimag = 0;
	double temp = 0;
	double len = 0;

	do
	{
        temp = zreal * zreal - zimag * zimag + creal;
        zimag = 2 * zreal * zimag + cimag;
        zreal = temp;
        len = zreal * zreal + zimag * zimag;
        ++count;
	}
	while ((len < 4.0) && (count < max));
	return count;
}

class parfor_main : public process
{
public:

	std::shared_ptr<par> m_par;
	std::vector<process*> m_parfor_processes;
	std::size_t count = 0;
	int m_result = 0;

	int m_width = 4000;
	int m_height = 3000;
	std::shared_ptr<nd_array<int, 2>> m_mandelbrot;
	double m_realmin = -0.7801785714285;
	double m_imagmin = -0.1279296875000;
	double m_realmax = -0.7676785714285;
	double m_imagmax = -0.1181640625000;
	double m_scalereal = (m_realmax - m_realmin) / m_width;
	double m_scaleimag = (m_imagmax - m_imagmin) / m_height;
	double m_creal = 0;
	double m_cimag = 0;

	FILE* m_f = nullptr;
	unsigned char* m_out = nullptr;
	int m_k = 0;

	parfor_main()
	{

	}

	virtual ~parfor_main() = default;

	void operator ()()
	{
		switch(get_run_label())
		{
			case 0: goto L0; break;
			case 1: goto L1; break;
		}

	L0:
		std::cout << "parfor_main starting" << std::endl;

		m_mandelbrot = std::make_shared<nd_array<int, 2>>(m_height, m_width);
		
		m_par = std::make_shared<par>(this, 0);

		class anonymous_parfor : public process
		{
		public:
			anonymous_parfor() = delete;

			anonymous_parfor(double p_creal,
				             double p_realmin,
				             int p_x,
				             double p_scalereal,
				             double p_cimag,
				             double p_imagmin,
				             int p_y,
				             double p_scaleimag,
				             std::shared_ptr<nd_array<int, 2>> p_mandelbrot)
			: m_creal(p_creal),
			  m_realmin(p_realmin),
			  x(p_x),
			  m_scalereal(p_scalereal),
			  m_cimag(p_cimag),
			  m_imagmin(p_imagmin),
			  y(p_y),
			  m_scaleimag(p_scaleimag),
			  m_mandelbrot(p_mandelbrot)
			{

			}

			virtual ~anonymous_parfor()= default;

			void operator ()()
			{
				m_creal = m_realmin + static_cast<double>(x * m_scalereal);
				m_cimag = m_imagmin + static_cast<double>(y * m_scaleimag);
				(*m_mandelbrot)[y][x] = 256 - calpixel(m_cimag, m_creal);
				terminate();
			}

		protected:
			double m_creal;
			double m_realmin;
			int x;
			double m_scalereal;
			double m_cimag;
			double m_imagmin;
			int y;
			double m_scaleimag;
			std::shared_ptr<nd_array<int, 2>> m_mandelbrot;
		private:
		};

		// FIXME: are these finalizes generated separately or can we
		// actually inline both definitions for anonymous procs?
		class anonymous_parfor_finalize : public anonymous_parfor
		{
		public:
			parfor_main* m_parent;

			anonymous_parfor_finalize() = delete;

			anonymous_parfor_finalize(double p_creal,
						              double p_realmin,
						              int p_x,
						              double p_scalereal,
						              double p_cimag,
						              double p_imagmin,
						              int p_y,
						              double p_scaleimag,
						              std::shared_ptr<nd_array<int, 2>> p_mandelbrot,
						              parfor_main* parent)
			: anonymous_parfor{p_creal,
				               p_realmin,
				               p_x,
				               p_scalereal,
				               p_cimag,
				               p_imagmin,
				               p_y,
				               p_scaleimag,
				               p_mandelbrot},
              m_parent(parent)
			{

			}

			virtual ~anonymous_parfor_finalize() = default;

			void finalize()
			{
				m_parent->m_par->decrement();
			}
		protected:
		private:
		};

		for (int y = 0; y < m_height; ++y)
		{
			for (int x = 0; x < m_width; ++x)
			{
				m_parfor_processes.push_back(new anonymous_parfor_finalize(m_creal,
					 													   m_realmin,
					 													   x,
					 													   m_scalereal,
					 													   m_cimag,
					 													   m_imagmin,
					 													   y,
					 													   m_scaleimag,
					 													   m_mandelbrot,
					 													   this));
				++count;
			}
		}

		m_par->set_process_count(count);

		std::cout << "count is " << count << ", size of vector is "
		          << m_parfor_processes.size() << std::endl;

		for (unsigned int i = 0; i < m_parfor_processes.size(); ++i)
		{
			m_sched->enqueue(m_parfor_processes[i]);
			m_parfor_processes[i] = nullptr;
		}

		if (m_par->should_yield())
		{
			set_run_label(1);
			return;
		}

	L1:

		m_f = fopen("mandelbrot.ppm", "wb");
		fprintf(m_f, "P6\n%d %d\n255\n", m_width, m_height);

		m_out = (unsigned char*)malloc(sizeof(unsigned char) * m_width * m_height * 3);

		for (int y = 0; y < m_height; ++y)
		{
			for (int x = 0; x < m_width; ++x)
			{
				m_out[m_k++] = (*m_mandelbrot)[y][x];
				m_out[m_k++] = (*m_mandelbrot)[y][x];
				m_out[m_k++] = (*m_mandelbrot)[y][x];
			}
		}

		fwrite(m_out, sizeof(unsigned char), m_width * m_height * 3, m_f);
		fclose(m_f);
		free(m_out);

		std::cout << "parfor_main terminating" << std::endl;
		terminate();
		return;
	}
protected:
private:
};

class parfor_test
{
public:
	parfor_test()
	{

	}

	~parfor_test() = default;

	void operator ()()
	{
		std::stringstream ss;
		logger::log(logger::DEBUG) << "instantiating scheduler for parfor test.";
		policy_sched sched(4);
		sched.init();

		logger::log(logger::DEBUG) << "inserting main process into scheduler.";
		sched.insert(new parfor_main());

		logger::log(logger::DEBUG) << "starting scheduler.";
		sched.start();
	}
protected:
private:
};

} // namespace testbed

} // namespace pj

#endif