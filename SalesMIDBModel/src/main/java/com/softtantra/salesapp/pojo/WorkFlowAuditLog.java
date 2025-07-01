package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "work_flow_audit_log")
public class WorkFlowAuditLog implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// connected to WorkFlowTaskStatus.id
	private int workflow_task_id;
	private int company_id;
	// will be mapped to WorkFLowTaskStatus.task_status
	private String task_status;
	// will be mapped to WorkFLowTaskStatus.task_status
	private String task_name;

	// 0-user //1-Role based approval
	// will be mapped to WorkFLowTaskStatus.approver_type
	private int approver_type;
	@Transient
	private String approver_type_name;

	// will be mapped to WorkFLowTaskStatus.old_approver_id
	private int approver_id;
	@Transient
	private String approver_name;

	// default for all tables
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	
	String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWorkflow_task_id() {
		return workflow_task_id;
	}

	public void setWorkflow_task_id(int workflow_task_id) {
		this.workflow_task_id = workflow_task_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public int getApprover_type() {
		return approver_type;
	}

	public void setApprover_type(int approver_type) {
		this.approver_type = approver_type;
	}

	public int getApprover_id() {
		return approver_id;
	}

	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
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

	public String getApprover_type_name() {
		return approver_type_name;
	}

	public void setApprover_type_name(String approver_type_name) {
		this.approver_type_name = approver_type_name;
	}

	public String getApprover_name() {
		return approver_name;
	}

	public void setApprover_name(String approver_name) {
		this.approver_name = approver_name;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
