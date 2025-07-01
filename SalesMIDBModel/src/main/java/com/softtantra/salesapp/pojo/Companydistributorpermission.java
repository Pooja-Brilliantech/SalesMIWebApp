package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_has_distributor_permission")
public class Companydistributorpermission implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int company_id;
	private int permission_id;
	
	@Column(nullable=true)
	private String permissionLink;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionLink() {
		return permissionLink;
	}

	public void setPermissionLink(String permissionLink) {
		this.permissionLink = permissionLink;
	}

}
