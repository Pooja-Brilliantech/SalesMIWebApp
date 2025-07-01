package com.fieldmi;

import java.io.Serializable;
import java.util.Date;

public class SalesOrderExportRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private int company_id;
	
	private int u_id;
	
	private Date fromDate;
	
	private Date toDate;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}
