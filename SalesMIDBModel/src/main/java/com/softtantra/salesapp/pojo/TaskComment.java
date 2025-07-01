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
@Table(name = "task_comment")
public class TaskComment implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_comment_id;
	private int task_id;
	private String address;
	private String lat;
	private String longi;
	private String comment;
	private Date comment_date;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;

	private String c_date;

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public int getTask_comment_id() {
		return task_comment_id;
	}

	public void setTask_comment_id(int task_comment_id) {
		this.task_comment_id = task_comment_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
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

	@Transient
	private String taskcomment_date;

	public String getTaskcomment_date() {
		return taskcomment_date;
	}

	public void setTaskcomment_date(String taskcomment_date) {
		this.taskcomment_date = taskcomment_date;
	}

}