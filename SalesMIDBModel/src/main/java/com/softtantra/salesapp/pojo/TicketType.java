package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_type")
public class TicketType implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketTypeId;
	private String ticketTypeName;
	private Date created_date;
	private Date updated_date;
	public int getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public String getTicketTypeName() {
		return ticketTypeName;
	}
	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName;
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
