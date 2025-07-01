package com.softtantra.salesapp.pojo;

import java.io.Serializable;
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
@Table(name = "invoice_master")
public class InvoiceMaster implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoice_id;
	private String invoice_number;
	private int company_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int status;
	private int customer_id;
	private int salesmaster_id;
	private double total_amount;
	private double paid_amount;
	private int payment_status; // 0-pending , 1 aprroved, 2-rejected
	private int payment_mode;// 1-cash,2-cheque,3-dd 4-credit card
	private int payment_type; // 1-full,2-partial
	private int payable_invoice; // 1-payable

	private int tally_invoice_status;
	@Column(nullable = true)
	private String sapSyncNumber;

	public String getSapSyncNumber() {
		return sapSyncNumber;
	}

	public void setSapSyncNumber(String sapSyncNumber) {
		this.sapSyncNumber = sapSyncNumber;
	}

	public int getTally_invoice_status() {
		return tally_invoice_status;
	}

	public void setTally_invoice_status(int tally_invoice_status) {
		this.tally_invoice_status = tally_invoice_status;
	}

	public int getPayable_invoice() {
		return payable_invoice;
	}

	public void setPayable_invoice(int payable_invoice) {
		this.payable_invoice = payable_invoice;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getSalesmaster_id() {
		return salesmaster_id;
	}

	public void setSalesmaster_id(int salesmaster_id) {
		this.salesmaster_id = salesmaster_id;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public int getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(int payment_mode) {
		this.payment_mode = payment_mode;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	@Transient
	private String c_date;

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	@Transient
	private String due_amt;

	public String getDue_amt() {
		return due_amt;
	}

	public void setDue_amt(String due_amt) {
		this.due_amt = due_amt;
	}

	@Transient
	private String p_status;

	public String getP_status() {
		return p_status;
	}

	public void setP_status(String p_status) {
		this.p_status = p_status;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	private String tallyinvoice_number;

	public String getTallyinvoice_number() {
		return tallyinvoice_number;
	}

	public void setTallyinvoice_number(String tallyinvoice_number) {
		this.tallyinvoice_number = tallyinvoice_number;
	}

}
