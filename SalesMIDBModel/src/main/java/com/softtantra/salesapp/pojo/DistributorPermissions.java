package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "distributor_permissions")
public class DistributorPermissions implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int company_id;
	private int status;
	private String displayname;
	private String mainmenu;
	private int sequence;
	private int count;
	
	private String packageName;
	
	private String module;

	@Transient
	private String permissionLink;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getPermissionLink() {
		return permissionLink;
	}

	public void setPermissionLink(String permissionLink) {
		this.permissionLink = permissionLink;
	}

	@Override
	public String toString() {
		return "DistributorPermissions [id=" + id + ", name=" + name + ", company_id=" + company_id + ", status="
				+ status + "]";
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
