package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sub_task_details")
public class SubTasksDetails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int company_id;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private int sub_task_id;
	private int created_by;
	private Date created_date;
	private int updated_by;
	private Date updated_date;
	private Date end_date;
	private String sequence;
	private String task_name;
	private String days;
	private String hours;
	private String task_files;
	@Transient
	private double total_assign_days;
	@Transient
	private double total_assign_hrs;
	@Transient
	private double total_assign_mins;
	
	public double getTotal_assign_days() {
		return total_assign_days;
	}
	public void setTotal_assign_days(double total_assign_days) {
		this.total_assign_days = total_assign_days;
	}
	public double getTotal_assign_hrs() {
		return total_assign_hrs;
	}
	public void setTotal_assign_hrs(double total_assign_hrs) {
		this.total_assign_hrs = total_assign_hrs;
	}
	public double getTotal_assign_mins() {
		return total_assign_mins;
	}
	public void setTotal_assign_mins(double total_assign_mins) {
		this.total_assign_mins = total_assign_mins;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	private String minutes;
	private String task_type;
	private int task_type_id;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public int getTask_type_id() {
		return task_type_id;
	}
	public void setTask_type_id(int task_type_id) {
		this.task_type_id = task_type_id;
	}
	
	public int getSub_task_id() {
		return sub_task_id;
	}
	public void setSub_task_id(int sub_task_id) {
		this.sub_task_id = sub_task_id;
	}
	public String getTask_files() {
		return task_files;
	}
	public void setTask_files(String task_files) {
		this.task_files = task_files;
	}
	

}