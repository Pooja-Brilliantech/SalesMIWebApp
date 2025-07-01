package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "risk_category")
public class RiskCategory implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int company_id;
	private String riskCategory;
	private String riskCategoryDescription;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getRiskCategory() {
		return riskCategory;
	}
	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}
	public String getRiskCategoryDescription() {
		return riskCategoryDescription;
	}
	public void setRiskCategoryDescription(String riskCategoryDescription) {
		this.riskCategoryDescription = riskCategoryDescription;
	}
	
	
}
