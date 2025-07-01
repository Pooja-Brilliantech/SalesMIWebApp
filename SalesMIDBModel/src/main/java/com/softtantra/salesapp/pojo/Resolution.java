package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="resolution")
public class Resolution implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resolutionId;
	private String resolutionName;
	private Date created_date;
	private Date updated_date;
	
	
	public int getResolutionId() {
		return resolutionId;
	}
	public void setResolutionId(int resolutionId) {
		this.resolutionId = resolutionId;
	}
	public String getResolutionName() {
		return resolutionName;
	}
	public void setResolutionName(String resolutionName) {
		this.resolutionName = resolutionName;
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
	
	
	

}
