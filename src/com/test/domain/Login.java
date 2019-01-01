package com.test.domain;

// 로그인 정보 자료형 클래스
public class Login {
	
	// 로그인 액션 진행에 필요한 필드 구성
	// (사용자) 이름, (최초 비밀번호로 사용할) 주민번호, 비밀번호, 전화번호, 로그인id, 
	private String name_, ssn, pw, phone, login_id;
	// 최초 등록일, 등급id, 등급명, 프로필 이미지
	private String initial_reg_date, access_id, access_name, profile_img;

	// getter, setter 구성
	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getAccess_id() {
		return access_id;
	}

	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}

	public String getAccess_name() {
		return access_name;
	}

	public void setAccess_name(String access_name) {
		this.access_name = access_name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInitial_reg_date() {
		return initial_reg_date;
	}

	public void setInitial_reg_date(String initial_reg_date) {
		this.initial_reg_date = initial_reg_date;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
		
}
