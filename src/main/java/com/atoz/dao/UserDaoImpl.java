package com.atoz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.atoz.schr.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User findById(Integer id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		String sql = "SELECT * FROM userinfo WHERE id=:id";

		User result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}

		return result;

	}

	@Override
	public List<User> findAll() {

		String sql = "SELECT * FROM userinfo";
		List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());
		
		Iterator<User> userList = result.iterator();
		while(userList.hasNext()){
			System.out.println("findAll ::"+userList.next());
		}
		
		return result;

	}

	@Override
	public void save(User user) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO USERinfo(NAME, EMAIL, ADDRESS) "
				+ "VALUES ( :name, :email, :address)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);
		user.setId(keyHolder.getKey().intValue());
		
	}

	@Override
	public void update(User user) {

		Integer id = user.getId();
		String sql = "UPDATE USERINFO SET NAME=:name, EMAIL=:email, ADDRESS=:address WHERE id= :id";

		//namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		System.out.println("Dao id: "+id);
		namedParameters.addValue("name", user.getName());
		System.out.println("Dao name: "+user.getName());
		namedParameters.addValue("address", user.getAddress());
		System.out.println("Dao address: "+user.getAddress());
		namedParameters.addValue("email", user.getEmail());
		System.out.println("Dao email: "+user.getEmail());
		namedParameterJdbcTemplate.update(sql, namedParameters);		

	}

	@Override
	public void delete(Integer id) {

		String sql = "DELETE FROM USERINFO WHERE id= :id";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));

	}

	private SqlParameterSource getSqlParameterByModel(User user) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
		
		
		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			//logger.info("rowNumb:"+rs.getInt("id"));
			user.setId((rs.getInt("id")));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			return user;
		}
	}


}