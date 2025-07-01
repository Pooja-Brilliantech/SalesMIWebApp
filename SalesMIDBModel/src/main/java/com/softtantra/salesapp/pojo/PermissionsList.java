package com.softtantra.salesapp.pojo;

import java.io.Serializable;

public class PermissionsList implements Serializable {

	private int company_id;
	private String user_name;
	private String permission_link;
	private String dispalay_name;
	private String mainmenu;
	private int sequence;
	private int count;

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMainmenu() {
		return mainmenu;
	}

	public void setMainmenu(String mainmenu) {
		this.mainmenu = mainmenu;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getDispalay_name() {
		return dispalay_name;
	}

	public void setDispalay_name(String dispalay_name) {
		this.dispalay_name = dispalay_name;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPermission_link() {
		return permission_link;
	}

	public void setPermission_link(String permission_link) {
		this.permission_link = permission_link;
	}

	@Override
	public String toString() {
		return "PermissionsList [company_id=" + company_id + ", user_name=" + user_name + ", permission_link="
				+ permission_link + ", dispalay_name=" + dispalay_name + ", mainmenu=" + mainmenu + ", sequence="
				+ sequence + ", count=" + count + ", status=" + status + "]";
	}
}
