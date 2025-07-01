package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "import_manager")
public class ImportManager implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int import_id;
	private String import_template;
	private Date import_date;
	private int user_id;
	private int company_id;
	private int status;
	private long record_count;
	private String file_name;

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public long getRecord_count() {
		return record_count;
	}

	public void setRecord_count(long record_count) {
		this.record_count = record_count;
	}

	public int getImport_id() {
		return import_id;
	}

	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}

	public String getImport_template() {
		return import_template;
	}

	public void setImport_template(String import_template) {
		this.import_template = import_template;
	}

	public Date getImport_date() {
		return import_date;
	}

	public void setImport_date(Date import_date) {
		this.import_date = import_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

}
