package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile_name_details")
public class ProfileName implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileNameId;
	private String profileName;
	private int status;
	private int company_id;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getProfileNameId() {
		return profileNameId;
	}

	public void setProfileNameId(int profileNameId) {
		this.profileNameId = profileNameId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

}
