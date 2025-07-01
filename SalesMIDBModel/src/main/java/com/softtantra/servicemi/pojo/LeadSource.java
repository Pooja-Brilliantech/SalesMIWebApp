package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "lead_source")
public class LeadSource implements Serializable{
		@Id
		@Column(name = "lead_source_id")
		@GeneratedValue
		private int lead_source_id;
		private String lead_source_name;
		private int company_id;
		private int user_id;
		private int status;
		private int created_by;
		@CreationTimestamp
		private Date created_date;;
		private int updated_by;
		@UpdateTimestamp
		private Date updated_date;
		private int sequence;
		public int getLead_source_id() {
			return lead_source_id;
		}
		public void setLead_source_id(int lead_source_id) {
			this.lead_source_id = lead_source_id;
		}
		public String getLead_source_name() {
			return lead_source_name;
		}
		public void setLead_source_name(String lead_source_name) {
			this.lead_source_name = lead_source_name;
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
		public int getSequence() {
			return sequence;
		}
		public void setSequence(int sequence) {
			this.sequence = sequence;
		}
		
		
}
