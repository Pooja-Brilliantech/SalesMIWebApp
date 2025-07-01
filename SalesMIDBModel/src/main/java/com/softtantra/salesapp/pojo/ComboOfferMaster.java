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
@Table(name = "combo_offer_masterinfo")
public class ComboOfferMaster implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int combo_offer_master_id;
	private String combo_offer_name;
	private int company_id;
	private int quantity;
	private String combo_offer_unit;
	private String description;
	private int status;
	private int activestatus;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int country_id;
	private int state_id;
	private int city_id;
	private String combo_offer_type;

	public String getCombo_offer_type() {
		return combo_offer_type;
	}

	public void setCombo_offer_type(String combo_offer_type) {
		this.combo_offer_type = combo_offer_type;
	}

	@Transient
	private String cityId;
	@Transient
	private int stateId;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getCombo_offer_master_id() {
		return combo_offer_master_id;
	}

	public void setCombo_offer_master_id(int combo_offer_master_id) {
		this.combo_offer_master_id = combo_offer_master_id;
	}

	public String getCombo_offer_name() {
		return combo_offer_name;
	}

	public void setCombo_offer_name(String combo_offer_name) {
		this.combo_offer_name = combo_offer_name;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCombo_offer_unit() {
		return combo_offer_unit;
	}

	public void setCombo_offer_unit(String combo_offer_unit) {
		this.combo_offer_unit = combo_offer_unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getActivestatus() {
		return activestatus;
	}

	public void setActivestatus(int activestatus) {
		this.activestatus = activestatus;
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

}
