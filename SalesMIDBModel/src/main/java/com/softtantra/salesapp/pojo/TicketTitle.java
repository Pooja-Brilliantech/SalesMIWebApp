package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ticket_title")
public class TicketTitle implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketTitleId;
	private String ticketTitleName;
	private Date created_date;
	private Date updated_date;
	public int getTicketTitleId() {
		return ticketTitleId;
	}
	public void setTicketTitleId(int ticketTitleId) {
		this.ticketTitleId = ticketTitleId;
	}
	public String getTicketTitleName() {
		return ticketTitleName;
	}
	public void setTicketTitleName(String ticketTitleName) {
		this.ticketTitleName = ticketTitleName;
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
	
	
}
