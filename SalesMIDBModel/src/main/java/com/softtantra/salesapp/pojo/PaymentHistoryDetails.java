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
@Table(name = "paymenthistory_details")
public class PaymentHistoryDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_id;
	private int invoice_id;
	private Date payment_date;
	private String cheque_dd_no;
	private int payment_mode;// (1.Cash, 2.cheque,3.card, 4.DD)
	private int sales_order_id;
	private int payment_type;// (0.full/1.partial)
	private int user_id;
	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	private int updated_by;
	private int status;
	private int payment_status;// (0.pending, 1.approved,2.rejected)
	private double total_amount;
	private double paid_amount;
	private String comment;
	@Transient
	private String customer_name;

	@Transient
	private String p_date;

	private int company_id;
	private String receiptno;
	private int istally_record; // 1--yes its tally record , 0- no its not tally reacord

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public int getSales_order_id() {
		return sales_order_id;
	}

	public void setSales_order_id(int sales_order_id) {
		this.sales_order_id = sales_order_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	private int customer_id;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	private String image_link;

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public int getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(int payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getCheque_dd_no() {
		return cheque_dd_no;
	}

	public void setCheque_dd_no(String cheque_dd_no) {
		this.cheque_dd_no = cheque_dd_no;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public int getIstally_record() {
		return istally_record;
	}

	public void setIstally_record(int istally_record) {
		this.istally_record = istally_record;
	}

	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

}