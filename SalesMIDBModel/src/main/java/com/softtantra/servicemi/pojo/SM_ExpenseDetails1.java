package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SM_ExpenseDetails1 implements Serializable{
	private int expense_details_id;
	private int expense_master_id;
	private String expense_date;
	private int type;// (1=local,2=outstation)
	private String mode;
	private String from_place;
	private String to_place;
	private double distance_in_km;// in km
	private BigDecimal rate;
	private BigDecimal amount;
	private String other_details;
	private String bill_img;

	private String startTravelTime;
	private String endTravelTime;

	private String startTravel_img_url;
	private String endTravel_img_url;

	private String actualDistance;

	public String getActualDistance() {
		return actualDistance;
	}

	public void setActualDistance(String actualDistance) {
		this.actualDistance = actualDistance;
	}

	public String getStartTravelTime() {
		return startTravelTime;
	}

	public void setStartTravelTime(String startTravelTime) {
		this.startTravelTime = startTravelTime;
	}

	public String getEndTravelTime() {
		return endTravelTime;
	}

	public void setEndTravelTime(String endTravelTime) {
		this.endTravelTime = endTravelTime;
	}

	public String getStartTravel_img_url() {
		return startTravel_img_url;
	}

	public void setStartTravel_img_url(String startTravel_img_url) {
		this.startTravel_img_url = startTravel_img_url;
	}

	public String getEndTravel_img_url() {
		return endTravel_img_url;
	}

	public void setEndTravel_img_url(String endTravel_img_url) {
		this.endTravel_img_url = endTravel_img_url;
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

	public String getExpense_date() {
		return expense_date;
	}

	public void setExpense_date(String expense_date) {
		this.expense_date = expense_date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOther_details() {
		return other_details;
	}

	public void setOther_details(String other_details) {
		this.other_details = other_details;
	}

	public String getBill_img() {
		return bill_img;
	}

	public void setBill_img(String bill_img) {
		this.bill_img = bill_img;
	}

}
