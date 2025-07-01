package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "task_details")
public class TaskDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_id;
	private int company_id;
	private int status;
	@Column(nullable=true)
	private int task_status; // 0-open,1-Inprogress,2-close
	private Date from_date;
	private Date to_date;
	private String task_type;
	private String type_details;
	private int created_by;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int user_id;
	private String main_task;
	private String sub_task;
	@Column(nullable=true)
	private String TAT;
	@Column(nullable=true)
	private double rating;
	private String completed_tat;
	
	@Transient
	private String file1;
	@Transient
	private String file2;
	@Transient
	private String file3;
	
	@Transient
	private String filename1;
	@Transient
	private String filename2;
	@Transient
	private String filename3;
	
	@Transient
	private int star;
	
	

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getFilename1() {
		return filename1;
	}

	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}

	public String getFilename2() {
		return filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}

	public String getFilename3() {
		return filename3;
	}

	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getMain_task() {
		return main_task;
	}

	public void setMain_task(String main_task) {
		this.main_task = main_task;
	}

	public String getSub_task() {
		return sub_task;
	}

	public void setSub_task(String sub_task) {
		this.sub_task = sub_task;
	}

	public String getTAT() {
		return TAT;
	}

	public void setTAT(String tAT) {
		TAT = tAT;
	}

	@Transient
	private BigInteger totaltask0_upcoming;
	
	@Transient
	private BigInteger totaltask1_open;

	@Transient
	private BigInteger totaltask2_close;
	
	@Transient
	private BigInteger totaltask3_rejected;

	@Transient
	private BigInteger totaltask4_overdue;
	

	public BigInteger getTotaltask0_upcoming() {
		return totaltask0_upcoming;
	}

	public void setTotaltask0_upcoming(BigInteger totaltask0_upcoming) {
		this.totaltask0_upcoming = totaltask0_upcoming;
	}

	public BigInteger getTotaltask1_open() {
		return totaltask1_open;
	}

	public void setTotaltask1_open(BigInteger totaltask1_open) {
		this.totaltask1_open = totaltask1_open;
	}

	public BigInteger getTotaltask3_rejected() {
		return totaltask3_rejected;
	}

	public void setTotaltask3_rejected(BigInteger totaltask3_rejected) {
		this.totaltask3_rejected = totaltask3_rejected;
	}

	public BigInteger getTotaltask4_overdue() {
		return totaltask4_overdue;
	}

	public void setTotaltask4_overdue(BigInteger totaltask4_overdue) {
		this.totaltask4_overdue = totaltask4_overdue;
	}

	public BigInteger getTotaltask2_close() {
		return totaltask2_close;
	}

	public void setTotaltask2_close(BigInteger totaltask2_close) {
		this.totaltask2_close = totaltask2_close;
	}

	@Transient 
	private String f_date;

	@Transient
	private String username;
	
	@Transient
	private String assign_by;

	public String getAssign_by() {
		return assign_by;
	}

	public void setAssign_by(String assign_by) {
		this.assign_by = assign_by;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	private String t_date;

	@Transient
	private String close_date;

	// close_dateTime, closing_comment, close_img,

	public String getClose_date() {
		return close_date;
	}

	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}

	public String getF_date() {
		return f_date;
	}

	public void setF_date(String f_date) {
		this.f_date = f_date;
	}

	public String getT_date() {
		return t_date;
	}

	public void setT_date(String t_date) {
		this.t_date = t_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	private Date close_dateTime;

	private String closing_comment;

	private String closing_img_url;

	public String getClosing_img_url() {
		return closing_img_url;
	}

	public void setClosing_img_url(String closing_img_url) {
		this.closing_img_url = closing_img_url;
	}

	public Date getClose_dateTime() {
		return close_dateTime;
	}

	public void setClose_dateTime(Date close_dateTime) {
		this.close_dateTime = close_dateTime;
	}

	public String getClosing_comment() {
		return closing_comment;
	}

	public void setClosing_comment(String closing_comment) {
		this.closing_comment = closing_comment;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

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

	public int getTask_status() {
		return task_status;
	}

	public void setTask_status(int task_status) {
		this.task_status = task_status;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public String getType_details() {
		return type_details;
	}

	public void setType_details(String type_details) {
		this.type_details = type_details;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	private String task_files;
	public String getTask_files() {
		return task_files;
	}
	public void setTask_files(String task_files) {
		this.task_files = task_files;
	}

	public String getCompleted_tat() {
		return completed_tat;
	}

	public void setCompleted_tat(String completed_tat) {
		this.completed_tat = completed_tat;
	}

}