package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="issue_type")
public class IssueType implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int issueTypeId;
	private String issueTypeName;
	private Date created_date;
	private Date updated_date;
	
	
	public int getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(int issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public String getIssueTypeName() {
		return issueTypeName;
	}
	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
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
