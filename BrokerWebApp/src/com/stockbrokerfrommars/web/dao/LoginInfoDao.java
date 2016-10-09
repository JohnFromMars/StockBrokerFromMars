package com.stockbrokerfrommars.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.stockbrokerfrommars.web.bean.LoginInfo;



@Component
public class LoginInfoDao {

	private NamedParameterJdbcTemplate dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new NamedParameterJdbcTemplate(dataSource);
	}

	public LoginInfo getLoginInfo(String username) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("username", username);
		LoginInfo loginInfo = null;

		try {
			loginInfo = dataSource.queryForObject("select * from logininfo where username= :username", param,
					new RowMapper<LoginInfo>() {

						public LoginInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
							LoginInfo loginInfo = new LoginInfo();
							loginInfo.setUsername(rs.getString("username"));
							loginInfo.setPassword(rs.getString("password"));
							return loginInfo;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(" can not find result " + username );
		}

		return loginInfo;
	}
}
