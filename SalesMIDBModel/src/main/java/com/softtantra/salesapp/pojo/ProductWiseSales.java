package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.List;

public class ProductWiseSales implements Serializable{
	private int product_id;
	private String product_name;
	private List<String> distributor_ids;
	private List<String> distributor_names;
	private List<String> sale;
	private List<String> amount;
	private String pack;

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public List<String> getDistributor_ids() {
		return distributor_ids;
	}

	public void setDistributor_ids(List<String> distributor_ids) {
		this.distributor_ids = distributor_ids;
	}

	public List<String> getDistributor_names() {
		return distributor_names;
	}

	public void setDistributor_names(List<String> distributor_names) {
		this.distributor_names = distributor_names;
	}

	public List<String> getSale() {
		return sale;
	}

	public void setSale(List<String> sale) {
		this.sale = sale;
	}

	public List<String> getAmount() {
		return amount;
	}

	public void setAmount(List<String> amount) {
		this.amount = amount;
	}
}
