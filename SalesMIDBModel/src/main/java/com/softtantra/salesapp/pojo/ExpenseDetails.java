package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense_details")
public class ExpenseDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expense_details_id;
	private int expense_master_id;
	private Date expense_date;
	private String type;// (1=local,2=outstation)
	private String mode;
	private String from_place;
	private String to_place;
	private double distance_in_km;// in km
	private double rate;
	private double amount;
	private String other_details;
	private String bill_img;

	private String expenseCode;
	private Date startTravelTime;
	private Date endTravelTime;

	private String startTravelimage;
	private String endTravelimage;

	private String actualDistance;
	private double approvedAmount;
	private String comment;
	
	@Column(nullable = true)
	private String approval_status;

	public double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getActualDistance() {
		return actualDistance;
	}

	public void setActualDistance(String actualDistance) {
		this.actualDistance = actualDistance;
	}

	public Date getStartTravelTime() {
		return startTravelTime;
	}

	public void setStartTravelTime(Date startTravelTime) {
		this.startTravelTime = startTravelTime;
	}

	public Date getEndTravelTime() {
		return endTravelTime;
	}

	public void setEndTravelTime(Date endTravelTime) {
		this.endTravelTime = endTravelTime;
	}

	public String getStartTravelimage() {
		return startTravelimage;
	}

	public void setStartTravelimage(String startTravelimage) {
		this.startTravelimage = startTravelimage;
	}

	public String getEndTravelimage() {
		return endTravelimage;
	}

	public void setEndTravelimage(String endTravelimage) {
		this.endTravelimage = endTravelimage;
	}

	public int getExpense_details_id() {
		return expense_details_id;
	}

	public void setExpense_details_id(int expense_details_id) {
		this.expense_details_id = expense_details_id;
	}

	public int getExpense_master_id() {
		return expense_master_id;
	}

	public void setExpense_master_id(int expense_master_id) {
		this.expense_master_id = expense_master_id;
	}

	public Date getExpense_date() {
		return expense_date;
	}

	public void setExpense_date(Date expense_date) {
		this.expense_date = expense_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFrom_place() {
		return from_place;
	}

	public void setFrom_place(String from_place) {
		this.from_place = from_place;
	}

	public String getTo_place() {
		return to_place;
	}

	public void setTo_place(String to_place) {
		this.to_place = to_place;
	}

	public double getDistance_in_km() {
		return distance_in_km;
	}

	public void setDistance_in_km(double distance_in_km) {
		this.distance_in_km = distance_in_km;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBill_img() {
		return bill_img;
	}

	public void setBill_img(String bill_img) {
		this.bill_img = bill_img;
	}

	public String getOther_details() {
		return other_details;
	}

	public void setOther_details(String other_details) {
		this.other_details = other_details;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public String getExpenseCode() {
		return expenseCode;
	}

	public void setExpenseCode(String expenseCode) {
		this.expenseCode = expenseCode;
	}

}
