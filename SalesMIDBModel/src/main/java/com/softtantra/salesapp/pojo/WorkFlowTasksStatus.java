package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "work_flow_task_status")
public class WorkFlowTasksStatus implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int sales_master_id;
	private int company_id;
	private int sales_details_id;
	private String task_name;
	private String task_status;
	
	@Column(nullable = true)
	private String workflow_name;

	// the task_status will be the old status after decision
	private String old_task_status;
	
	@Column(nullable = true)
	private String externalComment;
	public String getExternalComment() {
		return externalComment;
	}

	public void setExternalComment(String externalComment) {
		this.externalComment = externalComment;
	}

	// 0-user //1-Role based approval
	private int approver_type;
	private int next_approver_id;

	// the next_approver_id will be the old approver after decision
	private int old_approver_id;

	// default for all tables
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String comment;
	private Date custom_field_date;

	@ColumnDefault(value="0")
	private int recursive_level;
	// transient variables

	@Transient
	private String next_approver_name;
	@Transient
	private String old_approver_name;
	@Transient
	private String approver_type_name;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCustom_field_date() {
		return custom_field_date;
	}

	public void setCustom_field_date(Date custom_field_date) {
		this.custom_field_date = custom_field_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSales_master_id() {
		return sales_master_id;
	}

	public void setSales_master_id(int sales_master_id) {
		this.sales_master_id = sales_master_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getSales_details_id() {
		return sales_details_id;
	}

	public void setSales_details_id(int sales_details_id) {
		this.sales_details_id = sales_details_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public String getOld_task_status() {
		return old_task_status;
	}

	public void setOld_task_status(String old_task_status) {
		this.old_task_status = old_task_status;
	}

	public int getApprover_type() {
		return approver_type;
	}

	public void setApprover_type(int approver_type) {
		this.approver_type = approver_type;
	}

	public int getNext_approver_id() {
		return next_approver_id;
	}

	public void setNext_approver_id(int next_approver_id) {
		this.next_approver_id = next_approver_id;
	}

	public int getOld_approver_id() {
		return old_approver_id;
	}

	public void setOld_approver_id(int old_approver_id) {
		this.old_approver_id = old_approver_id;
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

	public String getNext_approver_name() {
		return next_approver_name;
	}

	public void setNext_approver_name(String next_approver_name) {
		this.next_approver_name = next_approver_name;
	}

	public String getOld_approver_name() {
		return old_approver_name;
	}

	public void setOld_approver_name(String old_approver_name) {
		this.old_approver_name = old_approver_name;
	}

	public String getApprover_type_name() {
		return approver_type_name;
	}

	public void setApprover_type_name(String approver_type_name) {
		this.approver_type_name = approver_type_name;
	}

	public String getWorkflow_name() {
		return workflow_name;
	}

	public void setWorkflow_name(String workflow_name) {
		this.workflow_name = workflow_name;
	}

	public int getRecursive_level() {
		return recursive_level;
	}

	public void setRecursive_level(int recursive_level) {
		this.recursive_level = recursive_level;
	}

	
	
}
