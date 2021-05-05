#include <runtime/channels.hpp>

namespace pj
{

channel_type::channel_type()
: m_type(NONE)
{
	m_type_str = "NONE";
}

channel_type::channel_type(channel_types type)
: m_type(type)
{
	switch(m_type)
	{
		case NONE:
			m_type_str = "NONE"; break;
		case ONE2ONE:
			m_type_str = "ONE2ONE"; break;
		case ONE2MANY:
			m_type_str = "ONE2MANY"; break;
		case MANY2ONE:
			m_type_str = "MANY2ONE"; break;
		case MANY2MANY:
			m_type_str = "MANY2MANY"; break;
		default:
			m_type_str = "BAD"; break;
	}
}

channel_type::~channel_type()
{

}

channel_types channel_type::get_type()
{
	return m_type;
}

std::string channel_type::get_type_string()
{
	return m_type_str;
}

} // namespace pj