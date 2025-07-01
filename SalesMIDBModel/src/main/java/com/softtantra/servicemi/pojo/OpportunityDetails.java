package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "opportunity_details")
public class OpportunityDetails implements Serializable{
	
	@Id
	@Column(name = "opportunity_id")
	@GeneratedValue
	private int opportunity_id;
	@ColumnDefault(value="0")
	private int status;
	
	@ColumnDefault(value="0")
	private int created_by;
	
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	
	@Column(nullable=true)
	private String name;
	
	@ColumnDefault(value="0")
	private int stage;
	
	@Column(nullable=true)
	private String id_range;
	
	@Column(nullable=true)
	private String first_name;
	
	@Column(nullable=true)
	private String last_name;
	
	@Column(nullable=true)
	private String company_address;
	
	@Column(nullable=true)
	private String contact_no;
	
	@Column(nullable=true)
	private String email;
	
	@Column(nullable=true)
	private String website;
	
	@Column(nullable=true)
	private String designation;
	
	@Column(nullable=true)
	private String salvation;
	
	@ColumnDefault(value="0")
	private int amount;
	
	@Column(nullable=true)
	private String assigned_to;
	
	@Column(nullable=true)
	private String expected_close_date;
	
	@ColumnDefault(value="0")
	private int company_id;
	
	@ColumnDefault(value="0")
	private int lead_id;
	
	@ColumnDefault(value="0")
	private int customer_id;
	
	@ColumnDefault(value="0")
	private int source_from;
	
	@Column(nullable=true)
	private String close_comment;
	
	@ColumnDefault(value="0")
	private int close_status;
	
	@Column(nullable=true)
	private String lead_IdRange;
	
	@Transient
	private int sequence =0;
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getLead_IdRange() {
		return lead_IdRange;
	}

	public void setLead_IdRange(String lead_IdRange) {
		this.lead_IdRange = lead_IdRange;
	}

	public int getClose_status() {
		return close_status;
	}

	public void setClose_status(int close_status) {
		this.close_status = close_status;
	}

	public String getClose_Leadcomment() {
		return close_Leadcomment;
	}

	public void setClose_Leadcomment(String close_Leadcomment) {
		this.close_Leadcomment = close_Leadcomment;
	}

	public int getSource_from() {
		return source_from;
	}

	public void setSource_from(int source_from) {
		this.source_from = source_from;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSalvation() {
		return salvation;
	}
	public void setSalvation(String salvation) {
		this.salvation = salvation;
	}
	public String getId_range() {
		return id_range;
	}
	public void setId_range(String id_range) {
		this.id_range = id_range;
	}
	@Transient
	private String stage_name;
	
	@Transient
	private int user_id;
	
	@Transient
	private String close_Leadcomment;

	@Transient
	private String close_leadstatus;
	
	@Transient
	private String close_date;
	
	@Transient
	private String Customer_name;
	
	@Transient
	private String created_at;
	
	@Transient
	private String value;
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getCustomer_name() {
		return Customer_name;
	}

	public void setCustomer_name(String customer_name) {
		Customer_name = customer_name;
	}

	public String getClose_date() {
		return close_date;
	}

	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}

	public String getClose_comment() {
		return close_comment;
	}

	public void setClose_comment(String close_comment) {
		this.close_comment = close_comment;
	}

	public String getClose_leadstatus() {
		return close_leadstatus;
	}

	public void setClose_leadstatus(String close_leadstatus) {
		this.close_leadstatus = close_leadstatus;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStage_name() {
		return stage_name;
	}
	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public int getOpportunity_id() {
		return opportunity_id;
	}
	public void setOpportunity_id(int opportunity_id) {
		this.opportunity_id = opportunity_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getExpected_close_date() {
		return expected_close_date;
	}
	public void setExpected_close_date(String expected_close_date) {
		this.expected_close_date = expected_close_date;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getLead_id() {
		return lead_id;
	}
	public void setLead_id(int lead_id) {
		this.lead_id = lead_id;
	}
	
	

}
