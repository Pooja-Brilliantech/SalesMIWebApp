package com.softtantra.servicemi.pojo;

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
@Table(name = "sm_recurring_service_details")
public class SM_RecurringServiceDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_id;
	private int service_type_id;
	private int quantity;
	private int user_id;
	private String description;
	private String customer_type;
	private String priority;
	private String service_status;
	private String field1;
	private String field2;

	private int recurring_Type;// 0-Daily ,1-weekly,2 -ForthNight 3-Monthly,4-Quarterly,5-Other
	private int recurring_days;
	private int recurring_duration;
	private int is_recurring_service;// 0--No 1---Yes
	private int recurring_service_id;
	private int serviceCenter_id;

	// added by swapnil 15-12-2018
	private int symptom_code_id;
	private int fault_area_id;
	private int fault_code_id;
	private int resolution_id;
	private String instruction;
	private int tat;
	private int asset_id;
	private int serial_number_id;
	private String actual_fault;
	private int spare_part_to_be_used_id;

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public int getSymptom_code_id() {
		return symptom_code_id;
	}

	public void setSymptom_code_id(int symptom_code_id) {
		this.symptom_code_id = symptom_code_id;
	}

	public int getFault_area_id() {
		return fault_area_id;
	}

	public void setFault_area_id(int fault_area_id) {
		this.fault_area_id = fault_area_id;
	}

	public int getFault_code_id() {
		return fault_code_id;
	}

	public void setFault_code_id(int fault_code_id) {
		this.fault_code_id = fault_code_id;
	}

	public int getResolution_id() {
		return resolution_id;
	}

	public void setResolution_id(int resolution_id) {
		this.resolution_id = resolution_id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public int getTat() {
		return tat;
	}

	public void setTat(int tat) {
		this.tat = tat;
	}

	public int getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}

	public int getSerial_number_id() {
		return serial_number_id;
	}

	public void setSerial_number_id(int serial_number_id) {
		this.serial_number_id = serial_number_id;
	}

	public String getActual_fault() {
		return actual_fault;
	}

	public void setActual_fault(String actual_fault) {
		this.actual_fault = actual_fault;
	}

	public int getSpare_part_to_be_used_id() {
		return spare_part_to_be_used_id;
	}

	public void setSpare_part_to_be_used_id(int spare_part_to_be_used_id) {
		this.spare_part_to_be_used_id = spare_part_to_be_used_id;
	}

	public int getServiceCenter_id() {
		return serviceCenter_id;
	}

	public void setServiceCenter_id(int serviceCenter_id) {
		this.serviceCenter_id = serviceCenter_id;
	}

	@Transient
	private String rescheduleDate;

	public int getRecurring_Type() {
		return recurring_Type;
	}

	public void setRecurring_Type(int recurring_Type) {
		this.recurring_Type = recurring_Type;
	}

	public int getRecurring_days() {
		return recurring_days;
	}

	public void setRecurring_days(int recurring_days) {
		this.recurring_days = recurring_days;
	}

	public int getRecurring_service_id() {
		return recurring_service_id;
	}

	public void setRecurring_service_id(int recurring_service_id) {
		this.recurring_service_id = recurring_service_id;
	}

	public int getRecurring_duration() {
		return recurring_duration;
	}

	public void setRecurring_duration(int recurring_duration) {
		this.recurring_duration = recurring_duration;
	}

	public int getIs_recurring_service() {
		return is_recurring_service;
	}

	public void setIs_recurring_service(int is_recurring_service) {
		this.is_recurring_service = is_recurring_service;
	}

	public String getRescheduleDate() {
		return rescheduleDate;
	}

	public void setRescheduleDate(String rescheduleDate) {
		this.rescheduleDate = rescheduleDate;
	}

	public String getService_type_name() {
		return service_type_name;
	}

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Transient
	private String customer_name;

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	private String job_name;
	private int status;
	private int invoice_status;
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int company_id;
	private int work_order_id;
	private Date start_date;
	private Date end_date;
	@Transient
	private String user_name;
	@Transient
	private String startDate;
	@Transient
	private String endDate;
	@Transient
	private String address;
	@Transient
	private String service_type_name;
	private String material_to_be_use;

	public int getReference_service_id() {
		return reference_service_id;
	}

	public void setReference_service_id(int reference_service_id) {
		this.reference_service_id = reference_service_id;
	}

	public String getReschedule_reason() {
		return reschedule_reason;
	}

	public void setReschedule_reason(String reschedule_reason) {
		this.reschedule_reason = reschedule_reason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReschedule_date() {
		return reschedule_date;
	}

	public void setReschedule_date(Date reschedule_date) {
		this.reschedule_date = reschedule_date;
	}

	public String getReschedule_signature() {
		return reschedule_signature;
	}

	public void setReschedule_signature(String reschedule_signature) {
		this.reschedule_signature = reschedule_signature;
	}

	private int reference_service_id;

	private String reschedule_reason, comment;

	private Date reschedule_date;

	private String reschedule_signature;

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getService_type_id() {
		return service_type_id;
	}

	public void setService_type_id(int service_type_id) {
		this.service_type_id = service_type_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
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

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
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

	public int getWork_order_id() {
		return work_order_id;
	}

	public void setWork_order_id(int work_order_id) {
		this.work_order_id = work_order_id;
	}

	public int getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(int invoice_status) {
		this.invoice_status = invoice_status;
	}

	public String getMaterial_to_be_use() {
		return material_to_be_use;
	}

	public void setMaterial_to_be_use(String material_to_be_use) {
		this.material_to_be_use = material_to_be_use;
	}

}