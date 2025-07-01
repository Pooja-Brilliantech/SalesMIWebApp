package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_activity_log")
public class UserActivityLog implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userActivityLogId;
	private String logMessage;
	private String notificationType;
	private int status=1;
	private int company_id;
	
	@CreationTimestamp
	private Date created_date;
	
	@UpdateTimestamp
	private Date last_updated_date;
	
	private int created_by;
	
	@Column(nullable = true)
	private String timeDiff;
	
	private int activityCounterForDay = 0;
	
	public int getUserActivityLogId() {
		return userActivityLogId;
	}
	public void setUserActivityLogId(int userActivityLogId) {
		this.userActivityLogId = userActivityLogId;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public String getTimeDiff() {
		return timeDiff;
	}
	public void setTimeDiff(String timeDiff) {
		this.timeDiff = timeDiff;
	}
	public int getActivityCounterForDay() {
		return activityCounterForDay;
	}
	public void setActivityCounterForDay(int activityCounterForDay) {
		this.activityCounterForDay = activityCounterForDay;
	}
	public Date getLast_updated_date() {
		return last_updated_date;
	}
	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}
	
	
}
