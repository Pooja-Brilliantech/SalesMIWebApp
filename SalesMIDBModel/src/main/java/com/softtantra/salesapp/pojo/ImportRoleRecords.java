package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "import_role_records")
public class ImportRoleRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int created_by;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;
	private String roleName;
	private String roleDescription;
	private String cityName;
	private Date created_date;
	private Date updated_date;
	private int updated_by;
	private String permissionType;
	String access = "";
	public String getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}
	private String permissionPackageName;
	private String permissionModuleName;
	private String permisssionSubModuleName;
	
	public String getPermissionPackageName() {
		return permissionPackageName;
	}
	public void setPermissionPackageName(String permissionPackageName) {
		this.permissionPackageName = permissionPackageName;
	}
	public String getPermissionModuleName() {
		return permissionModuleName;
	}
	public void setPermissionModuleName(String permissionModuleName) {
		this.permissionModuleName = permissionModuleName;
	}
	public String getPermisssionSubModuleName() {
		return permisssionSubModuleName;
	}
	public void setPermisssionSubModuleName(String permisssionSubModuleName) {
		this.permisssionSubModuleName = permisssionSubModuleName;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getImport_id() {
		return import_id;
	}
	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	
	
}
