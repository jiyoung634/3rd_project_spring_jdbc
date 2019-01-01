package com.test.dao;

import com.test.domain.Login;

public interface LoginDAO {

	public Login login(Login login);

	public int pwchange(String login_id, String current_pw, String new_pw);	
}
