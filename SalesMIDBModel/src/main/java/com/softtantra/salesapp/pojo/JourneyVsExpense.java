package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "journey_expense")
public class JourneyVsExpense implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int journeyTypeId;
	private int expenseConfigId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJourneyTypeId() {
		return journeyTypeId;
	}
	public void setJourneyTypeId(int journeyTypeId) {
		this.journeyTypeId = journeyTypeId;
	}
	public int getExpenseConfigId() {
		return expenseConfigId;
	}
	public void setExpenseConfigId(int expenseConfigId) {
		this.expenseConfigId = expenseConfigId;
	}
}
