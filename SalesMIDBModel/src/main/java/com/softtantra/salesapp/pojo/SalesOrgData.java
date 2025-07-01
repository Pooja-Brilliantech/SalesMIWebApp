package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sales_org_data")
public class SalesOrgData implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int status;
	private int company_id;
	private String company_code;
	private String salesOrg;
	private String salesOrgText;
	private String distrChannel;
	private String distrChannelText;
	private String division;
	private String divisionText;
	private String salesOffice;
	private String salesOfficeText;
	private String salesGrp;
	private String salesGrpText;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getSalesOrgText() {
		return salesOrgText;
	}

	public void setSalesOrgText(String salesOrgText) {
		this.salesOrgText = salesOrgText;
	}

	public String getDistrChannel() {
		return distrChannel;
	}

	public void setDistrChannel(String distrChannel) {
		this.distrChannel = distrChannel;
	}

	public String getDistrChannelText() {
		return distrChannelText;
	}

	public void setDistrChannelText(String distrChannelText) {
		this.distrChannelText = distrChannelText;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDivisionText() {
		return divisionText;
	}

	public void setDivisionText(String divisionText) {
		this.divisionText = divisionText;
	}

	public String getSalesOffice() {
		return salesOffice;
	}

	public void setSalesOffice(String salesOffice) {
		this.salesOffice = salesOffice;
	}

	public String getSalesOfficeText() {
		return salesOfficeText;
	}

	public void setSalesOfficeText(String salesOfficeText) {
		this.salesOfficeText = salesOfficeText;
	}

	public String getSalesGrp() {
		return salesGrp;
	}

	public void setSalesGrp(String salesGrp) {
		this.salesGrp = salesGrp;
	}

	public String getSalesGrpText() {
		return salesGrpText;
	}

	public void setSalesGrpText(String salesGrpText) {
		this.salesGrpText = salesGrpText;
	}

}

