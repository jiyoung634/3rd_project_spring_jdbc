package com.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.domain.Instructor;
import com.test.domain.Login;

public class LoginJDBCTemplate implements LoginDAO {

	private JdbcTemplate jdbcTemplate;

	// setter를 통한 의존 주입(DI)
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Login login(Login login) {
				
		/*
		 CREATE OR REPLACE VIEW login_access_view
		 AS
		 SELECT name_, ssn, login_id, phone, pw, l.profile_img, initial_reg_date, l.access_id, access_name FROM login l, access_level al WHERE l.access_id = al.access_id;
		*/
		
		String sql = "SELECT name_, ssn, login_id, phone, initial_reg_date, access_id, access_name, profile_img, pw FROM login_access_view WHERE name_ = ? AND pw = ? AND access_id = ?";
		
		Login result = this.jdbcTemplate.queryForObject(sql, new RowMapper<Login>() {

			@Override
			public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				String name_ = rs.getString("name_"); 
				String ssn = rs.getString("ssn");
				String login_id = rs.getString("login_id");
				String phone = rs.getString("phone");
				String initial_reg_date = rs.getString("initial_reg_date");
				String access_id = rs.getString("access_id");
				String access_name = rs.getString("access_name");
				String profile_img = rs.getString("profile_img");
				String pw = rs.getString("pw");
					
				Login l = new Login();
				l.setName_(name_);
				l.setSsn(ssn);
				l.setLogin_id(login_id);
				l.setPhone(phone);
				l.setInitial_reg_date(initial_reg_date);
				l.setAccess_id(access_id);
				l.setAccess_name(access_name);
				l.setProfile_img(profile_img);
				l.setPw(pw);
				
				return l;
			}
			
		
		}, login.getName_(), login.getPw(), login.getAccess_id());
		
		return result;
	}
	
	@Override
	public int pwchange(String login_id, String current_pw, String new_pw) {
		String sql = "UPDATE login SET pw=? WHERE login_id=? AND pw=?";
		int result = this.jdbcTemplate.update(sql, new_pw, login_id, current_pw);
		return result;
	}
	
	
}
