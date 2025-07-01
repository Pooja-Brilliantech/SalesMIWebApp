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
@Table(name = "ticket_details")
public class Ticket implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticket_id;
	private int company_id;
	private int user_id;
	private String title;
	private String description;
	private int status;// (0-open ,1-resolved,2-close)
	private Date applied_datetime;
	private String image_url;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String comment;
	private String lat;
	private String longi;
	private String address;
	private String added_by;
	private String inProgressComment;
	private String subModule;
	private String issueType;
	private String ticketMode;
	private String ticketType;
	private String resolution;
	private String related_to;
	private String rca;
	
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getRelated_to() {
		return related_to;
	}

	public void setRelated_to(String related_to) {
		this.related_to = related_to;
	}

	public String getRca() {
		return rca;
	}

	public void setRca(String rca) {
		this.rca = rca;
	}

	

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getAdded_by() {
		return added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	private Date resolved_date;
	@Transient
	private String other_title;

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getApplied_datetime() {
		return applied_datetime;
	}

	public void setApplied_datetime(Date applied_datetime) {
		this.applied_datetime = applied_datetime;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getResolved_date() {
		return resolved_date;
	}

	public void setResolved_date(Date resolved_date) {
		this.resolved_date = resolved_date;
	}

	public String getOther_title() {
		return other_title;
	}

	public void setOther_title(String other_title) {
		this.other_title = other_title;
	}

	public String getInProgressComment() {
		return inProgressComment;
	}

	public void setInProgressComment(String inProgressComment) {
		this.inProgressComment = inProgressComment;
	}

	public String getTicketMode() {
		return ticketMode;
	}

	public void setTicketMode(String ticketMode) {
		this.ticketMode = ticketMode;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

}
