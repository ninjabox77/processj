#ifndef PJ_ARRAY_HPP
#define PJ_ARRAY_HPP

#include <iostream>
#include <vector>

namespace pj
{

template <typename T, std::size_t T_levels>
struct nested_initializer_list
{
	using type = std::initializer_list<
		typename nested_initializer_list<T, T_levels - 1>::type
	>;
};

template <typename T>
struct nested_initializer_list<T, 0>
{
	using type = T;
};

template <typename T, std::size_t T_levels>
using nested_initializer_list_t = 
	typename nested_initializer_list<T, T_levels>::type;

template <typename T, std::size_t... Dims>
class nd_array
{

};

template <typename T>
class nd_array<T, 1>
{
public:
	using item_type = T;
	using array_type = std::vector<T>;

	nd_array()
	: m_length(0)
	{

	}

	nd_array(std::size_t length)
	: m_length(length)
	{
		m_vec.reserve(length);
	}

	nd_array(nested_initializer_list_t<T, 1> list)
	: m_length(list.size())
	{
		m_vec.insert(m_vec.begin(), list.begin(), list.end());
	}

	nd_array(nd_array<T, 1>& that)
	: m_vec(that.m_vec),
	  m_length(that.m_length)
	{

	}

	nd_array(nd_array<T, 1>&& that)
	: m_vec(std::move(that.m_vec)),
	  m_length(std::move(that.m_length))
	{
		that.m_length = 0;
		that.m_vec.clear();
	}

	item_type& operator [](std::size_t index)
	{
		std::lock_guard<std::mutex> lg(m_mtx);
		return m_vec[index];
	}

	nd_array<T, 1>& operator =(nd_array<T, 1>& that)
	{
		m_vec = that.m_vec;
		m_length = that.m_length;
		return *this;
	}

	nd_array<T, 1>& operator =(nd_array<T, 1>&& that)
	{
		m_vec = std::move(that.m_vec);
		m_length = that.m_length;
		that.m_length = 0;
		that.m_vec.clear();
		return *this;
	}

	const std::size_t length()
	{
		return m_length;
	}

	const std::size_t dimensions()
	{
		return 1;
	}

private:
	array_type m_vec;
	std::size_t m_length;
	std::mutex m_mtx;
};

template <typename T, std::size_t Dims>
class nd_array<T, Dims>
{
public:
	using item_type = typename nd_array<T, Dims - 1>::item_type;
	using array_type = std::vector<nd_array<item_type, Dims - 1>>;

	template <typename U = std::size_t, typename... Us>
	nd_array(U dim, Us... dims)
	: m_length(dim)
	{
		init_with_dims(dim, dims...);
		for (std::size_t i = 0; i < m_length; ++i)
		{
			m_vec.push_back(nd_array<item_type, Dims - 1>(dims...));
		}
	}

	nd_array(nested_initializer_list_t<T, Dims> list)
	: m_length(list.size())
	{
		m_vec.reserve(list.size());
		for (auto nested_list : list)
		{
			m_vec.push_back(nd_array<item_type, Dims - 1>(nested_list));
		}
	}

	nd_array(nd_array& that)
	: m_length(that.m_length)
	{
		m_vec.insert(m_vec.begin(), that.m_vec.begin(), that.m_vec.end());
	}

	nd_array(nd_array&& that)
	: m_vec(std::move(that.m_vec)),
	  m_length(std::move(that.m_length))
	{
		that.m_length = 0;
		that.m_vec.clear();
	}

	nd_array<item_type, Dims - 1>& operator [](std::size_t index)
	{
		std::lock_guard<std::mutex> lg(m_mtx);
		return m_vec[index];
	}

	nd_array<item_type, Dims>& operator =(nd_array<item_type, Dims>& that)
	{
		this->m_vec = that.m_vec;
		this->m_length = that.m_length;
		return *this;
	}

	nd_array<item_type, Dims>& operator =(nd_array<item_type, Dims>&& that)
	{
		this->m_vec = std::move(that.m_vec);
		this->m_length = std::move(that.m_length);
		that.m_length = 0;
		that.m_vec.clear();
		return *this;
	}

	const std::size_t length()
	{
		return m_length;
	}

	const std::size_t dimensions()
	{
		return m_dims;
	}

private:
	array_type m_vec;
	std::size_t m_length;
	static constexpr std::size_t m_dims = Dims;
	std::mutex m_mtx;
	std::vector<std::size_t> m_vec_lengths;

	template <typename U = std::size_t>
	inline void init_with_dims(U dim)
	{
		m_vec_lengths.push_back(dim);
	}

	template <typename U = std::size_t, typename... Us>
	inline void init_with_dims(U dim, Us... dims)
	{
		m_vec_lengths.push_back(dim);
		init_with_dims(dims...);
	}

};

} // namespace pj

#endif