package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "login_details")
public class LoginDetails implements Serializable{

	@Id
	@Column(name = "login_id")
	@GeneratedValue
	private Long login_id;
	private int company_id;
	private int user_id;
	private int role_id;
	private String user_name;
	private String password;

	@Transient
	private int manager_permission;

	public int getManager_permission() {
		return manager_permission;
	}

	public void setManager_permission(int manager_permission) {
		this.manager_permission = manager_permission;
	}

	@Transient
	private BigInteger total_user;

	@Transient
	private BigInteger login_user;

	@Transient
	private BigInteger login_id1;

	public BigInteger getLogin_id1() {
		return login_id1;
	}

	public void setLogin_id1(BigInteger login_id1) {
		this.login_id1 = login_id1;
	}

	public BigInteger getLogin_user() {
		return login_user;
	}

	public void setLogin_user(BigInteger login_user) {
		this.login_user = login_user;
	}

	public BigInteger getTotal_user() {
		return total_user;
	}

	public void setTotal_user(BigInteger total_user) {
		this.total_user = total_user;
	}

	/*
	 * active of partycontact 1=active
	 */

	@Column(name = "is_active", columnDefinition = "TINYINT", length = 1)
	private boolean isActive;

	public Long getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Long login_id) {
		this.login_id = login_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}