package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_img_details")
public class CustomerImgDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int customer_img_id;
	private int company_id;
	private int intcustomer_id;
	private byte[] customer_img;

	public int getCustomer_img_id() {
		return customer_img_id;
	}

	public void setCustomer_img_id(int customer_img_id) {
		this.customer_img_id = customer_img_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getIntcustomer_id() {
		return intcustomer_id;
	}

	public void setIntcustomer_id(int intcustomer_id) {
		this.intcustomer_id = intcustomer_id;
	}

	public byte[] getCustomer_img() {
		return customer_img;
	}

	public void setCustomer_img(byte[] customer_img) {
		this.customer_img = customer_img;
	}

}
