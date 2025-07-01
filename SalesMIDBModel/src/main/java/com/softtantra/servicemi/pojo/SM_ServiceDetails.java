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
@Table(name = "sm_service_details")
public class SM_ServiceDetails implements Serializable{
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
	private int recurring_service_id;
	private int rescheduled_service_id;

	private String fsr_uploaded;
	private String callno;
	private String logDate;
	private String L2Status;
	private String site_id;
	private String buyer_name;
	private String service_status1;
	private String amc_scope;
	private String serial_no_remarks;
	private String captured_by_agent;
	private String cli;
	private String cust_tt_no;
	private String call_type;
	private String site_name;
	private String l1status;
	private String remote_support_engg;
	private String rs_company;
	private String rs_mobile;
	private String probable_spare;
	private String rs_remark;
	private String os_company;
	private String os_mobile;
	private String os_email;
	private String agent_start_date;
	private String agent_end_date;
	private String agent_to_rs_datetime;
	private String agent_name;
	private String agent_remark;
	private String fsr_no;
	private String next_visit_date;
	private String final_status;
	private String call_updated_on_crm_date;
	private String sla;
	private String suspended;
	private String service_hold_reason;
	private String sla_date;
	private String pending;
	private String suspended_start_date;
	private String suspended_end_date;
	private String amc_po_number;
	private String amc_po_date;
	private String spare_request_sent;

	private String po_number;
	private String po_date;
	private String invoice_number;
	private String invoice_date;

	private int serviceCenter_id;

	// added by swapnil
	private int symptom_code_id;
	private int fault_area_id;
	private int fault_code_id;
	private int resolution_id;

	// added by swapnil 15-12-2018
	private String instruction;
	private int tat;
	private int asset_id;
	private int serial_number_id;
	private String actual_fault;
	private int spare_part_to_be_used_id;
	private String sub_status_name;

	@Transient
	private String symptom_code;
	@Transient
	private String fault_area;
	@Transient
	private String fault_code;
	@Transient
	private String resolution;

	private int kcmupload_status;

	public int getKcmupload_status() {
		return kcmupload_status;
	}

	public void setKcmupload_status(int kcmupload_status) {
		this.kcmupload_status = kcmupload_status;
	}

	public String getSub_status_name() {
		return sub_status_name;
	}

	public void setSub_status_name(String sub_status_name) {
		this.sub_status_name = sub_status_name;
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

	public String getSymptom_code() {
		return symptom_code;
	}

	public void setSymptom_code(String symptom_code) {
		this.symptom_code = symptom_code;
	}

	public String getFault_area() {
		return fault_area;
	}

	public void setFault_area(String fault_area) {
		this.fault_area = fault_area;
	}

	public String getFault_code() {
		return fault_code;
	}

	public void setFault_code(String fault_code) {
		this.fault_code = fault_code;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
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

	public int getServiceCenter_id() {
		return serviceCenter_id;
	}

	public void setServiceCenter_id(int serviceCenter_id) {
		this.serviceCenter_id = serviceCenter_id;
	}

	// added by ashutosh
	private int type_2_id;

	public int getType_2_id() {
		return type_2_id;
	}

	public void setType_2_id(int type_2_id) {
		this.type_2_id = type_2_id;
	}

	public int getRecurring_service_id() {
		return recurring_service_id;
	}

	public void setRecurring_service_id(int recurring_service_id) {
		this.recurring_service_id = recurring_service_id;
	}

	@Transient
	private String rescheduleDate;

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

	private String user_name;

	private String startDate;

	private String endDate;

	private String address;

	private String service_type_name;
	private String material_to_be_use;
	private String userCustomer;
	private String district;
	private String siteaddress;
	private String rs_to_os_datetime;

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getUserCustomer() {
		return userCustomer;
	}

	public void setUserCustomer(String userCustomer) {
		this.userCustomer = userCustomer;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSiteaddress() {
		return siteaddress;
	}

	public void setSiteaddress(String siteaddress) {
		this.siteaddress = siteaddress;
	}

	public String getRs_to_os_datetime() {
		return rs_to_os_datetime;
	}

	public void setRs_to_os_datetime(String rs_to_os_datetime) {
		this.rs_to_os_datetime = rs_to_os_datetime;
	}

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

	public int getRescheduled_service_id() {
		return rescheduled_service_id;
	}

	public void setRescheduled_service_id(int rescheduled_service_id) {
		this.rescheduled_service_id = rescheduled_service_id;
	}

	public String getFsr_uploaded() {
		return fsr_uploaded;
	}

	public void setFsr_uploaded(String fsr_uploaded) {
		this.fsr_uploaded = fsr_uploaded;
	}

	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getL2Status() {
		return L2Status;
	}

	public void setL2Status(String l2Status) {
		L2Status = l2Status;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getService_status1() {
		return service_status1;
	}

	public void setService_status1(String service_status1) {
		this.service_status1 = service_status1;
	}

	public String getAmc_scope() {
		return amc_scope;
	}

	public void setAmc_scope(String amc_scope) {
		this.amc_scope = amc_scope;
	}

	public String getSerial_no_remarks() {
		return serial_no_remarks;
	}

	public void setSerial_no_remarks(String serial_no_remarks) {
		this.serial_no_remarks = serial_no_remarks;
	}

	public String getCaptured_by_agent() {
		return captured_by_agent;
	}

	public void setCaptured_by_agent(String captured_by_agent) {
		this.captured_by_agent = captured_by_agent;
	}

	public String getCli() {
		return cli;
	}

	public void setCli(String cli) {
		this.cli = cli;
	}

	public String getCust_tt_no() {
		return cust_tt_no;
	}

	public void setCust_tt_no(String cust_tt_no) {
		this.cust_tt_no = cust_tt_no;
	}

	public String getCall_type() {
		return call_type;
	}

	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getL1status() {
		return l1status;
	}

	public void setL1status(String l1status) {
		this.l1status = l1status;
	}

	public String getRemote_support_engg() {
		return remote_support_engg;
	}

	public void setRemote_support_engg(String remote_support_engg) {
		this.remote_support_engg = remote_support_engg;
	}

	public String getRs_company() {
		return rs_company;
	}

	public void setRs_company(String rs_company) {
		this.rs_company = rs_company;
	}

	public String getRs_mobile() {
		return rs_mobile;
	}

	public void setRs_mobile(String rs_mobile) {
		this.rs_mobile = rs_mobile;
	}

	public String getProbable_spare() {
		return probable_spare;
	}

	public void setProbable_spare(String probable_spare) {
		this.probable_spare = probable_spare;
	}

	public String getRs_remark() {
		return rs_remark;
	}

	public void setRs_remark(String rs_remark) {
		this.rs_remark = rs_remark;
	}

	public String getOs_company() {
		return os_company;
	}

	public void setOs_company(String os_company) {
		this.os_company = os_company;
	}

	public String getOs_mobile() {
		return os_mobile;
	}

	public void setOs_mobile(String os_mobile) {
		this.os_mobile = os_mobile;
	}

	public String getOs_email() {
		return os_email;
	}

	public void setOs_email(String os_email) {
		this.os_email = os_email;
	}

	public String getAgent_start_date() {
		return agent_start_date;
	}

	public void setAgent_start_date(String agent_start_date) {
		this.agent_start_date = agent_start_date;
	}

	public String getAgent_end_date() {
		return agent_end_date;
	}

	public void setAgent_end_date(String agent_end_date) {
		this.agent_end_date = agent_end_date;
	}

	public String getAgent_to_rs_datetime() {
		return agent_to_rs_datetime;
	}

	public void setAgent_to_rs_datetime(String agent_to_rs_datetime) {
		this.agent_to_rs_datetime = agent_to_rs_datetime;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getAgent_remark() {
		return agent_remark;
	}

	public void setAgent_remark(String agent_remark) {
		this.agent_remark = agent_remark;
	}

	public String getFsr_no() {
		return fsr_no;
	}

	public void setFsr_no(String fsr_no) {
		this.fsr_no = fsr_no;
	}

	public String getNext_visit_date() {
		return next_visit_date;
	}

	public void setNext_visit_date(String next_visit_date) {
		this.next_visit_date = next_visit_date;
	}

	public String getFinal_status() {
		return final_status;
	}

	public void setFinal_status(String final_status) {
		this.final_status = final_status;
	}

	public String getCall_updated_on_crm_date() {
		return call_updated_on_crm_date;
	}

	public void setCall_updated_on_crm_date(String call_updated_on_crm_date) {
		this.call_updated_on_crm_date = call_updated_on_crm_date;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public String getSuspended() {
		return suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	public String getService_hold_reason() {
		return service_hold_reason;
	}

	public void setService_hold_reason(String service_hold_reason) {
		this.service_hold_reason = service_hold_reason;
	}

	public String getSla_date() {
		return sla_date;
	}

	public void setSla_date(String sla_date) {
		this.sla_date = sla_date;
	}

	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
	}

	public String getSuspended_start_date() {
		return suspended_start_date;
	}

	public void setSuspended_start_date(String suspended_start_date) {
		this.suspended_start_date = suspended_start_date;
	}

	public String getSuspended_end_date() {
		return suspended_end_date;
	}

	public void setSuspended_end_date(String suspended_end_date) {
		this.suspended_end_date = suspended_end_date;
	}

	public String getAmc_po_number() {
		return amc_po_number;
	}

	public void setAmc_po_number(String amc_po_number) {
		this.amc_po_number = amc_po_number;
	}

	public String getAmc_po_date() {
		return amc_po_date;
	}

	public void setAmc_po_date(String amc_po_date) {
		this.amc_po_date = amc_po_date;
	}

	public String getSpare_request_sent() {
		return spare_request_sent;
	}

	public void setSpare_request_sent(String spare_request_sent) {
		this.spare_request_sent = spare_request_sent;
	}

	public String getPo_number() {
		return po_number;
	}

	public void setPo_number(String po_number) {
		this.po_number = po_number;
	}

	public String getPo_date() {
		return po_date;
	}

	public void setPo_date(String po_date) {
		this.po_date = po_date;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	private String problem_Description;

	public String getProblem_Description() {
		return problem_Description;
	}

	public void setProblem_Description(String problem_Description) {
		this.problem_Description = problem_Description;
	}

	public String product_description;

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String callerName;

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String severity;

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String product_type;

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String mobile;

	// public int city;
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
