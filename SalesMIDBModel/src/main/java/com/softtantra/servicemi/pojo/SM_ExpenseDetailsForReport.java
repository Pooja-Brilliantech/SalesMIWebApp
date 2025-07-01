package com.softtantra.servicemi.pojo;

import java.io.Serializable;

public class SM_ExpenseDetailsForReport implements Serializable{
	private int expense_Master_id;
	private String username;
	private Double total;
	private String created_date;
	private String comment;
	private String status;

	public int getExpense_Master_id() {
		return expense_Master_id;
	}

	public void setExpense_Master_id(int expense_Master_id) {
		this.expense_Master_id = expense_Master_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double obj) {
		this.total = obj;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String string) {
		this.created_date = string;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
