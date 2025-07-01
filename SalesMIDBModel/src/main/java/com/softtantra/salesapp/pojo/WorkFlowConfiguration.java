package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "workflow_configuration")
public class WorkFlowConfiguration implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workflow_id;
	
	@ColumnDefault(value="0")
	private int company_id;
	
	@ColumnDefault(value="0")
	private int user_id;
	
	@ColumnDefault(value="1")
	private int status;
	
	@ColumnDefault(value="0")
	private int created_by;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	@CreationTimestamp
	private Date created_date;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private Date updated_date;
	
	@Column(nullable = true)
	private String workflow_name;
	
	@Column(nullable = true)
	private String workflow_task_name;
	
	@Column(nullable = true)
	private String input_object_name;
	
	@Column(nullable = true)
	private String approver_type;
	
	@Column(nullable = true)
	private String approval_type;
	
	@Column(nullable = true)
	private String approval_condition;
	
	@Column(nullable = true)
	private String roles;
	
	@ColumnDefault(value="0")
	private int number_of_level;
	
	@Column(nullable = true)
	private String role_ids;

	@Column(nullable = true)
	private String editableLevels;
	
	public String getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(String role_ids) {
		this.role_ids = role_ids;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getWorkflow_name() {
		return workflow_name;
	}

	public void setWorkflow_name(String workflow_name) {
		this.workflow_name = workflow_name;
	}

	public String getApprover_type() {
		return approver_type;
	}

	public void setApprover_type(String approver_type) {
		this.approver_type = approver_type;
	}

	public String getApproval_type() {
		return approval_type;
	}

	public void setApproval_type(String approval_type) {
		this.approval_type = approval_type;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getNumber_of_level() {
		return number_of_level;
	}

	public void setNumber_of_level(int number_of_level) {
		this.number_of_level = number_of_level;
	}
	public int getWorkflow_id() {
		return workflow_id;
	}

	public void setWorkflow_id(int workflow_id) {
		this.workflow_id = workflow_id;
	}

	public String getWorkflow_task_name() {
		return workflow_task_name;
	}

	public void setWorkflow_task_name(String workflow_task_name) {
		this.workflow_task_name = workflow_task_name;
	}

	public String getInput_object_name() {
		return input_object_name;
	}

	public void setInput_object_name(String input_object_name) {
		this.input_object_name = input_object_name;
	}

	public String getApproval_condition() {
		return approval_condition;
	}

	public void setApproval_condition(String approval_condition) {
		this.approval_condition = approval_condition;
	}

	public String getEditableLevels() {
		return editableLevels;
	}

	public void setEditableLevels(String editableLevels) {
		this.editableLevels = editableLevels;
	}
	
	
}
