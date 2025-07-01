package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distributor_scheme_import_records")
public class DistributorSchemeImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;

	private String scheme_name;
	private String start_date;
	private String end_date;
	private String description;
	private String terms_conditions;
	private double discount_value;
	private int distributor_id;
	private int distributor_scheme_master_id;
	private String scheme_apply_type;
	private double scheme_apply_value;
	private String scheme_discount_type_id;
	private String distributor_name;

	public int getRecords_id() {
		return records_id;
	}

	public void setRecords_id(int records_id) {
		this.records_id = records_id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getImport_id() {
		return import_id;
	}

	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTerms_conditions() {
		return terms_conditions;
	}

	public void setTerms_conditions(String terms_conditions) {
		this.terms_conditions = terms_conditions;
	}

	public double getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(double discount_value) {
		this.discount_value = discount_value;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getDistributor_scheme_master_id() {
		return distributor_scheme_master_id;
	}

	public void setDistributor_scheme_master_id(int distributor_scheme_master_id) {
		this.distributor_scheme_master_id = distributor_scheme_master_id;
	}

	public double getScheme_apply_value() {
		return scheme_apply_value;
	}

	public void setScheme_apply_value(double scheme_apply_value) {
		this.scheme_apply_value = scheme_apply_value;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getScheme_apply_type() {
		return scheme_apply_type;
	}

	public void setScheme_apply_type(String scheme_apply_type) {
		this.scheme_apply_type = scheme_apply_type;
	}

	public String getScheme_discount_type_id() {
		return scheme_discount_type_id;
	}

	public void setScheme_discount_type_id(String scheme_discount_type_id) {
		this.scheme_discount_type_id = scheme_discount_type_id;
	}

}
