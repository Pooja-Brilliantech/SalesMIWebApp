package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Created by IntelliJ IDEA. User: Minal Date: Feb 24, 2012 Time: 11:38:49 AM To
 * change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	private int company_id;

	private int status;
	private int profile_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	@Transient
	private int permission_id;

	@Transient
	private String permissionLink;

	public int getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	/* @Fetch(value = FetchMode.SELECT) */
	@JoinTable(name = "role_has_rolepermission", joinColumns = @JoinColumn(name = "role_id"/*
																							 * , referencedColumnName =
																							 * "id"
																							 */), inverseJoinColumns = @JoinColumn(name = "permission_id"/*
																																							 * ,
																																							 * referencedColumnName
																																							 * =
																																							 * "id"
																																							 */))
	private List<RolePermissions> RolePermissions = new ArrayList<RolePermissions>();

	public List<RolePermissions> getRolePermissions() {
		return RolePermissions;
	}

	public void setRolePermissions(List<RolePermissions> rolePermissions) {
		RolePermissions = rolePermissions;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SELECT)
	@JoinTable(name = "role_has_mobilepermission", joinColumns = @JoinColumn(name = "role_id"/*
																								 * ,
																								 * referencedColumnName
																								 * = "id"
																								 */), inverseJoinColumns = @JoinColumn(name = "mobile_permission_id"/*
																																									 * ,
																																									 * referencedColumnName
																																									 * =
																																									 * "id"
																																									 */))
	private List<MobilePermissions> mobilePermissions = new ArrayList<MobilePermissions>();

	public List<MobilePermissions> getMobilePermissions() {
		return mobilePermissions;
	}

	public void setMobilePermissions(List<MobilePermissions> mobilePermissions) {
		this.mobilePermissions = mobilePermissions;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SELECT)
	@JoinTable(name = "role_has_distributorpermission", joinColumns = @JoinColumn(name = "role_id"/*
																									 * ,
																									 * referencedColumnName
																									 * = "id"
																									 */), inverseJoinColumns = @JoinColumn(name = "id"/*
																																						 * ,
																																						 * referencedColumnName
																																						 * =
																																						 * "id"
																																						 */))
	private List<DistributorPermissions> disPermissions = new ArrayList<DistributorPermissions>();

	public List<DistributorPermissions> getDisPermissions() {
		return disPermissions;
	}

	public void setDisPermissions(List<DistributorPermissions> disPermissions) {
		this.disPermissions = disPermissions;
	}

}
