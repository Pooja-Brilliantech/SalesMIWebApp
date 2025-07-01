package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SM_Expense implements Serializable{
	private List<SM_ExpenseDetails1> expenseDetails = new ArrayList<SM_ExpenseDetails1>();
	private int expense_master_id;
	private int company_id;
	private String expense_claim_date;
	private int user_id;
	private int approver_id;
	private int status;// 0=pending,1=approved,2=rejected,3=money transferred
	private String address;
	private String comment;
	private BigDecimal total;
	private int created_by;
	private String created_date;
	private int updated_by;
	private String updated_date;
	private double approvedamount;

	public double getApprovedamount() {
		return approvedamount;
	}

	public void setApprovedamount(double approvedamount) {
		this.approvedamount = approvedamount;
	}

	public List<SM_ExpenseDetails1> getExpenseDetails() {
		return expenseDetails;
	}

	public void setExpenseDetails(List<SM_ExpenseDetails1> expenseDetails) {
		this.expenseDetails = expenseDetails;
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

	public String getExpense_claim_date() {
		return expense_claim_date;
	}

	public void setExpense_claim_date(String expense_claim_date) {
		this.expense_claim_date = expense_claim_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getApprover_id() {
		return approver_id;
	}

	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

}
