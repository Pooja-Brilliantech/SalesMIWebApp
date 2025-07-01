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
@Table(name = "geo_tagging")
public class Geo_Tagging implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int geo_tagging_id;
	private int company_id;
	private String imei_no;
	private String username;
	private String password;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private String device_type;
	private String apk_version;
	private String lat;
	private String longi;
	private int accuracy;
	private String address;
	private String location;
	private String customer_name;
	private String contact_number;
	private String district;
	private String tahasil;
	private String existing_cutomer;
	private String market_name;
	private String monthly_bussiness;
	private String delas_in_color_coated;
	private String color_coated_brands_sold;
	private String monthly_volume_colour_coated;
	private String brand_price;
	private String retailer_margine;
	private String is_jsw_color_coated_brand_sold;
	private String why_jsw_product_not_sold;
	private String customer_prefrence_of_color_coated_brands;
	private String is_retailer_interested_in_jsw_color_brand;
	private String specific_reason;
	private String company_name;
	private String company_address;
	private String company_pincode;
	private String company_locality;
	private String company_latitude;
	private String company_longitude;
	private String company_country;
	private String company_state;
	private String company_city;
	private String comapny_location_accuracy;
	private String image_retailer_brand;
	private String image_visiting_card;
	private Date created_at;
	private String country_code;
	private String color_brands_retailer_buy;

	@Transient
	private String created_name;
	@Transient
	private String created_mobno;
	@Transient
	private String created_email;

	public String getCreated_name() {
		return created_name;
	}

	public void setCreated_name(String created_name) {
		this.created_name = created_name;
	}

	public String getCreated_mobno() {
		return created_mobno;
	}

	public void setCreated_mobno(String created_mobno) {
		this.created_mobno = created_mobno;
	}

	public String getCreated_email() {
		return created_email;
	}

	public void setCreated_email(String created_email) {
		this.created_email = created_email;
	}

	public String getColor_brands_retailer_buy() {
		return color_brands_retailer_buy;
	}

	public void setColor_brands_retailer_buy(String color_brands_retailer_buy) {
		this.color_brands_retailer_buy = color_brands_retailer_buy;
	}

	public int getGeo_tagging_id() {
		return geo_tagging_id;
	}

	public void setGeo_tagging_id(int geo_tagging_id) {
		this.geo_tagging_id = geo_tagging_id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getImei_no() {
		return imei_no;
	}

	public void setImei_no(String imei_no) {
		this.imei_no = imei_no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getApk_version() {
		return apk_version;
	}

	public void setApk_version(String apk_version) {
		this.apk_version = apk_version;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTahasil() {
		return tahasil;
	}

	public void setTahasil(String tahasil) {
		this.tahasil = tahasil;
	}

	public String getExisting_cutomer() {
		return existing_cutomer;
	}

	public void setExisting_cutomer(String existing_cutomer) {
		this.existing_cutomer = existing_cutomer;
	}

	public String getMarket_name() {
		return market_name;
	}

	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}

	public String getMonthly_bussiness() {
		return monthly_bussiness;
	}

	public void setMonthly_bussiness(String monthly_bussiness) {
		this.monthly_bussiness = monthly_bussiness;
	}

	public String getDelas_in_color_coated() {
		return delas_in_color_coated;
	}

	public void setDelas_in_color_coated(String delas_in_color_coated) {
		this.delas_in_color_coated = delas_in_color_coated;
	}

	public String getColor_coated_brands_sold() {
		return color_coated_brands_sold;
	}

	public void setColor_coated_brands_sold(String color_coated_brands_sold) {
		this.color_coated_brands_sold = color_coated_brands_sold;
	}

	public String getMonthly_volume_colour_coated() {
		return monthly_volume_colour_coated;
	}

	public void setMonthly_volume_colour_coated(String monthly_volume_colour_coated) {
		this.monthly_volume_colour_coated = monthly_volume_colour_coated;
	}

	public String getBrand_price() {
		return brand_price;
	}

	public void setBrand_price(String brand_price) {
		this.brand_price = brand_price;
	}

	public String getRetailer_margine() {
		return retailer_margine;
	}

	public void setRetailer_margine(String retailer_margine) {
		this.retailer_margine = retailer_margine;
	}

	public String getIs_jsw_color_coated_brand_sold() {
		return is_jsw_color_coated_brand_sold;
	}

	public void setIs_jsw_color_coated_brand_sold(String is_jsw_color_coated_brand_sold) {
		this.is_jsw_color_coated_brand_sold = is_jsw_color_coated_brand_sold;
	}

	public String getWhy_jsw_product_not_sold() {
		return why_jsw_product_not_sold;
	}

	public void setWhy_jsw_product_not_sold(String why_jsw_product_not_sold) {
		this.why_jsw_product_not_sold = why_jsw_product_not_sold;
	}

	public String getCustomer_prefrence_of_color_coated_brands() {
		return customer_prefrence_of_color_coated_brands;
	}

	public void setCustomer_prefrence_of_color_coated_brands(String customer_prefrence_of_color_coated_brands) {
		this.customer_prefrence_of_color_coated_brands = customer_prefrence_of_color_coated_brands;
	}

	public String getIs_retailer_interested_in_jsw_color_brand() {
		return is_retailer_interested_in_jsw_color_brand;
	}

	public void setIs_retailer_interested_in_jsw_color_brand(String is_retailer_interested_in_jsw_color_brand) {
		this.is_retailer_interested_in_jsw_color_brand = is_retailer_interested_in_jsw_color_brand;
	}

	public String getSpecific_reason() {
		return specific_reason;
	}

	public void setSpecific_reason(String specific_reason) {
		this.specific_reason = specific_reason;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getCompany_pincode() {
		return company_pincode;
	}

	public void setCompany_pincode(String company_pincode) {
		this.company_pincode = company_pincode;
	}

	public String getCompany_locality() {
		return company_locality;
	}

	public void setCompany_locality(String company_locality) {
		this.company_locality = company_locality;
	}

	public String getCompany_latitude() {
		return company_latitude;
	}

	public void setCompany_latitude(String company_latitude) {
		this.company_latitude = company_latitude;
	}

	public String getCompany_longitude() {
		return company_longitude;
	}

	public void setCompany_longitude(String company_longitude) {
		this.company_longitude = company_longitude;
	}

	public String getCompany_country() {
		return company_country;
	}

	public void setCompany_country(String company_country) {
		this.company_country = company_country;
	}

	public String getCompany_state() {
		return company_state;
	}

	public void setCompany_state(String company_state) {
		this.company_state = company_state;
	}

	public String getCompany_city() {
		return company_city;
	}

	public void setCompany_city(String company_city) {
		this.company_city = company_city;
	}

	public String getComapny_location_accuracy() {
		return comapny_location_accuracy;
	}

	public void setComapny_location_accuracy(String comapny_location_accuracy) {
		this.comapny_location_accuracy = comapny_location_accuracy;
	}

	public String getImage_retailer_brand() {
		return image_retailer_brand;
	}

	public void setImage_retailer_brand(String image_retailer_brand) {
		this.image_retailer_brand = image_retailer_brand;
	}

	public String getImage_visiting_card() {
		return image_visiting_card;
	}

	public void setImage_visiting_card(String image_visiting_card) {
		this.image_visiting_card = image_visiting_card;
	}

}
