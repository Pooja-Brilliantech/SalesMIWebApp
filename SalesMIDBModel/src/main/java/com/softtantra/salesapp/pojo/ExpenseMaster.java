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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "expense_master")
public class ExpenseMaster implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expense_master_id;
	private int company_id;
	private Date expense_claim_date;
	private int user_id;
	private int approver_id;
	private int status;// 0=deleted,1=pending,2=approved,3=rejected,4=money transferred
	private String address;
	private String comment;
	private double total;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private double approvedamount;
	private int isDefault;
	/// added by savita
	private int approveRejectStatus; // 1-pending 2-1st level approved 3-2nd level approved
	@Column(nullable = true)
	private String documentNumber;
	private String expenseClaimId;
	
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public double getApprovedamount() {
		return approvedamount;
	}

	public void setApprovedamount(double approvedamount) {
		this.approvedamount = approvedamount;
	}

	public int getApprover_id() {
		return approver_id;
	}

	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getExpense_master_id() {
		return expense_master_id;
	}

	public void setExpense_master_id(int expense_master_id) {
		this.expense_master_id = expense_master_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Date getExpense_claim_date() {
		return expense_claim_date;
	}

	public void setExpense_claim_date(Date expense_claim_date) {
		this.expense_claim_date = expense_claim_date;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

	public int getApproveRejectStatus() {
		return approveRejectStatus;
	}

	public void setApproveRejectStatus(int approveRejectStatus) {
		this.approveRejectStatus = approveRejectStatus;
	}

	@Transient
	private long pending;
	@Transient
	private long approved;
	@Transient
	private long rejected;
	@Transient
	private long submitted;

	public long getPending() {
		return pending;
	}

	public void setPending(long pending) {
		this.pending = pending;
	}

	public long getApproved() {
		return approved;
	}

	public void setApproved(long approved) {
		this.approved = approved;
	}

	public long getRejected() {
		return rejected;
	}

	public void setRejected(long rejected) {
		this.rejected = rejected;
	}

	public long getSubmitted() {
		return submitted;
	}

	public void setSubmitted(long submitted) {
		this.submitted = submitted;
	}

	public String getExpenseClaimId() {
		return expenseClaimId;
	}

	public void setExpenseClaimId(String expenseClaimId) {
		this.expenseClaimId = expenseClaimId;
	}
}
