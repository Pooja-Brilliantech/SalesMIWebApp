package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distributor_has_permissions")
public class DistributorRolePermissions implements Serializable{

	@Id
	@Column(name = "distributor_has_permissions_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long distributor_has_permissions_id;

	private int company_id;
	private int permission_id;
	private String d_id;

	public Long getDistributor_has_permissions_id() {
		return distributor_has_permissions_id;
	}

	public void setDistributor_has_permissions_id(Long distributor_has_permissions_id) {
		this.distributor_has_permissions_id = distributor_has_permissions_id;
	}

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

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	@Override
	public String toString() {
		return "DistributorRolePermissions [distributor_has_permissions_id=" + distributor_has_permissions_id
				+ ", d_id=" + d_id + ", company_id=" + company_id + ", permission_id=" + permission_id + "]";
	}

}
