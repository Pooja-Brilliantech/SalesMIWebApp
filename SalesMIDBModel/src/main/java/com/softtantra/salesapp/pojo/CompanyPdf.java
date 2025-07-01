package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_pdf")
public class CompanyPdf implements Serializable{
	@Id
	@Column(name = "company_pdf_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int company_pdfid;
	private int company_id;
	private String company_pdf;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_pdf() {
		return company_pdf;
	}

	public void setCompany_pdf(String company_pdf) {
		this.company_pdf = company_pdf;
	}

	public int getCompany_pdfid() {
		return company_pdfid;
	}

	public void setCompany_pdfid(int company_pdfid) {
		this.company_pdfid = company_pdfid;
	}

}
