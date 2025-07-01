package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product_scheme_details")
public class ProductSchemeDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_scheme_detail_id;
	private int product_scheme_master_id;
	private int product_id;
	private String pack_name;
	private int pack_id;
	private String pack_unit;
	private int scheme_apply_type;
	private double scheme_apply_value;
	private int scheme_discount_type_id;
	private double discount_value;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@Transient
	private String product_name;
	@Transient
	private String scheme_discount_type_name;
	@Transient
	private String scheme_name;
	@Transient
	private String start_date;
	@Transient
	private String end_date;
	@Transient
	private String terms_and_conditions;
	@Transient
	private String description;
	@Transient
	private String scheme_on_name;

	public int getProduct_scheme_detail_id() {
		return product_scheme_detail_id;
	}

	public void setProduct_scheme_detail_id(int product_scheme_detail_id) {
		this.product_scheme_detail_id = product_scheme_detail_id;
	}

	public int getProduct_scheme_master_id() {
		return product_scheme_master_id;
	}

	public void setProduct_scheme_master_id(int product_scheme_master_id) {
		this.product_scheme_master_id = product_scheme_master_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getScheme_apply_type() {
		return scheme_apply_type;
	}

	public void setScheme_apply_type(int scheme_apply_type) {
		this.scheme_apply_type = scheme_apply_type;
	}

	public double getScheme_apply_value() {
		return scheme_apply_value;
	}

	public void setScheme_apply_value(double scheme_apply_value) {
		this.scheme_apply_value = scheme_apply_value;
	}

	public int getScheme_discount_type_id() {
		return scheme_discount_type_id;
	}

	public void setScheme_discount_type_id(int scheme_discount_type_id) {
		this.scheme_discount_type_id = scheme_discount_type_id;
	}

	public double getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(double discount_value) {
		this.discount_value = discount_value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getScheme_discount_type_name() {
		return scheme_discount_type_name;
	}

	public void setScheme_discount_type_name(String scheme_discount_type_name) {
		this.scheme_discount_type_name = scheme_discount_type_name;
	}

	public String getPack_name() {
		return pack_name;
	}

	public void setPack_name(String pack_name) {
		this.pack_name = pack_name;
	}

	public int getPack_id() {
		return pack_id;
	}

	public void setPack_id(int pack_id) {
		this.pack_id = pack_id;
	}

	public String getPack_unit() {
		return pack_unit;
	}

	public void setPack_unit(String pack_unit) {
		this.pack_unit = pack_unit;
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

	public String getTerms_and_conditions() {
		return terms_and_conditions;
	}

	public void setTerms_and_conditions(String terms_and_conditions) {
		this.terms_and_conditions = terms_and_conditions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getScheme_on_name() {
		return scheme_on_name;
	}

	public void setScheme_on_name(String scheme_on_name) {
		this.scheme_on_name = scheme_on_name;
	}

}
