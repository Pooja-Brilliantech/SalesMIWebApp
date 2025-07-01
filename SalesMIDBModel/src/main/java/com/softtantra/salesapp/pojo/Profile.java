package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "profile_details")
public class Profile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;

	private String profileNameId;

	@Column(nullable = true)
	private String permissionType;

	@Column(nullable = true)
	private String pageUrl;

	@Column(nullable = true)
	private boolean isPermitted;

	@Column(nullable = true)
	private int rolePermissionId;

	@Transient
	@Column(nullable = true)
	private ProfileName profileName;

	@Transient
	private List<String> rolePermissionIdPageUrl;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getProfileNameId() {
		return profileNameId;
	}

	public void setProfileNameId(String profileNameId) {
		this.profileNameId = profileNameId;
	}

	public ProfileName getProfileName() {
		return profileName;
	}

	public void setProfileName(ProfileName profileName) {
		this.profileName = profileName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public boolean isPermitted() {
		return isPermitted;
	}

	public void setPermitted(boolean isPermitted) {
		this.isPermitted = isPermitted;
	}

	public int getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(int rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public List<String> getRolePermissionIdPageUrl() {
		return rolePermissionIdPageUrl;
	}

	public void setRolePermissionIdPageUrl(List<String> rolePermissionIdPageUrl) {
		this.rolePermissionIdPageUrl = rolePermissionIdPageUrl;
	}

}
