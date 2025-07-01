package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_mode")
public class TicketMode implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketModeId;
	private String ticketModeName;
	private Date created_date;
	private Date updated_date;
	public int getTicketModeId() {
		return ticketModeId;
	}
	public void setTicketModeId(int ticketModeId) {
		this.ticketModeId = ticketModeId;
	}
	public String getTicketModeName() {
		return ticketModeName;
	}
	public void setTicketModeName(String ticketModeName) {
		this.ticketModeName = ticketModeName;
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
