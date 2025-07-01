package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distributor_city")
public class DistributorCity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int distributor_city_id;
	private int distributor_id;
	private int city;
	private int status;

	public int getDistributor_city_id() {
		return distributor_city_id;
	}

	public void setDistributor_city_id(int distributor_city_id) {
		this.distributor_city_id = distributor_city_id;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

}
