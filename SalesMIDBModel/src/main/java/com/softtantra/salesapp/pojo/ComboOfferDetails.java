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
@Table(name = "combo_offer_details")
public class ComboOfferDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int combo_offer_details_id;
	private int activestatus;
	private int combo_offer_master_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private String pack_name;
	private int product_id;
	private int status;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	@Transient
	private String product_code;

	public int getCombo_offer_details_id() {
		return combo_offer_details_id;
	}

	public void setCombo_offer_details_id(int combo_offer_details_id) {
		this.combo_offer_details_id = combo_offer_details_id;
	}

	public int getActivestatus() {
		return activestatus;
	}

	public void setActivestatus(int activestatus) {
		this.activestatus = activestatus;

	}

	public int getCombo_offer_master_id() {
		return combo_offer_master_id;
	}

	public void setCombo_offer_master_id(int combo_offer_master_id) {
		this.combo_offer_master_id = combo_offer_master_id;
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

	public String getPack_name() {
		return pack_name;
	}

	public void setPack_name(String pack_name) {
		this.pack_name = pack_name;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

}
