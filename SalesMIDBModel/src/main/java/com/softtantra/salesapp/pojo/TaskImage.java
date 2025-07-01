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

@Entity
@Table(name = "task_image")
public class TaskImage implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_image_id;
	private int task_id;
	private String image_url;
	private String address;
	private String lat;
	private String longi;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private Date image_datetime;
	private String image_comment;

	public String getImage_comment() {
		return image_comment;
	}

	public void setImage_comment(String image_comment) {
		this.image_comment = image_comment;
	}

	@Transient
	private String i_date;

	public String getI_date() {
		return i_date;
	}

	public void setI_date(String i_date) {
		this.i_date = i_date;
	}

	public Date getImage_datetime() {
		return image_datetime;
	}

	public void setImage_datetime(Date image_datetime) {
		this.image_datetime = image_datetime;
	}

	public int getTask_image_id() {
		return task_image_id;
	}

	public void setTask_image_id(int task_image_id) {
		this.task_image_id = task_image_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}