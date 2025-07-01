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
@Table(name = "sm_service_import_records")
public class SM_ServiceImportRecords implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int service_id;
	private int service_type_id;
	private int quantity;
	private String description;
	private String priority;
	private String service_status;
	private String field1;
	private String field2;
	private int recurring_service_id;
	private int rescheduled_service_id;
	private String job_name;
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
	private String material_to_be_use;
	private int reference_service_id;
	private String reschedule_reason;
	private Date reschedule_date;
	private String reschedule_signature;

	private String fsr_upload;
	private String callno;
	private String log_date;
	private String serial_number;
	private String site_id;
	private String product_decription;
	private String product_type;
	private String buyer_name;

	private String amc_scope;

	private String serialno_remark;
	private String capture_by_agent;
	private String user_customer;
	private String caller_name;
	private String caller_email;
	private String caller_mobile;
	private String cli;
	private String cust_TT_no;
	private String severity;
	private String political_state;
	private String circle;
	private String district;
	private String city;
	private String site_name;
	private String site_address;

	private String l1_status;
	private String remote_support_engg;
	private String on_site_support_engg;
	private String call_type;

	private String rs_company;
	private String problem_description;
	private String probable_spare;
	private String rs_remarks;
	private String rs_to_datetime;
	private String l2status;

	private String os_company;
	private String os_mobile;
	private String os_email;
	private String agent_start_date;
	private String agent_end_date;
	private String rs_date_time;
	private String agent_name;
	private String agent_remark;
	private String actual_problem;
	private String action_taken;

	private String fsr_no;
	private String rca;
	private String site_load_current;
	private String battery_capacity;
	private String rm_installed_qty;
	private String cancelation_result;
	private String part_replace;
	private String parts_faulty;
	private String visit_start_date;
	private String visit_end_date;

	private String next_visit;
	private String l3status;
	private String OS_Remark;
	private String final_status;
	private String updated_on_crm_date;

	private String sla;
	private String Suspended;
	private String service_hold_reason;
	private String sla_date;
	private String pending;
	private String suspended_start_date;
	private String suspended_end_date;
	private String invoice_no;
	private String invoice_date;

	private String spare_rquest_send;
	private String no_of_visites;
	private String service_type;
	private String ponumber;
	private String podate;
	private String service_type_remark;
	private String rs_mobile;
	private String amc_po_number;
	private String amc_po_date;

	@Transient
	private String user_name;

	@Transient
	private String startDate;

	@Transient
	private String customer_name;

	private String RS_to_OS_Date_time;

	public String getLog_date() {
		return log_date;
	}

	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}

	public int getRecords_id() {
		return records_id;
	}

	public void setRecords_id(int records_id) {
		this.records_id = records_id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getImport_id() {
		return import_id;
	}

	public void setImport_id(int import_id) {
		this.import_id = import_id;
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

	public int getRecurring_service_id() {
		return recurring_service_id;
	}

	public void setRecurring_service_id(int recurring_service_id) {
		this.recurring_service_id = recurring_service_id;
	}

	public int getRescheduled_service_id() {
		return rescheduled_service_id;
	}

	public void setRescheduled_service_id(int rescheduled_service_id) {
		this.rescheduled_service_id = rescheduled_service_id;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public int getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(int invoice_status) {
		this.invoice_status = invoice_status;
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getWork_order_id() {
		return work_order_id;
	}

	public void setWork_order_id(int work_order_id) {
		this.work_order_id = work_order_id;
	}

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

	public String getMaterial_to_be_use() {
		return material_to_be_use;
	}

	public void setMaterial_to_be_use(String material_to_be_use) {
		this.material_to_be_use = material_to_be_use;
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

	public String getFsr_upload() {
		return fsr_upload;
	}

	public void setFsr_upload(String fsr_upload) {
		this.fsr_upload = fsr_upload;
	}

	public String getSerialno_remark() {
		return serialno_remark;
	}

	public void setSerialno_remark(String serialno_remark) {
		this.serialno_remark = serialno_remark;
	}

	public String getCapture_by_agent() {
		return capture_by_agent;
	}

	public void setCapture_by_agent(String capture_by_agent) {
		this.capture_by_agent = capture_by_agent;
	}

	public String getCli() {
		return cli;
	}

	public void setCli(String cli) {
		this.cli = cli;
	}

	public String getCust_TT_no() {
		return cust_TT_no;
	}

	public void setCust_TT_no(String cust_TT_no) {
		this.cust_TT_no = cust_TT_no;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getL1_status() {
		return l1_status;
	}

	public void setL1_status(String l1_status) {
		this.l1_status = l1_status;
	}

	public String getRemote_support_engg() {
		return remote_support_engg;
	}

	public void setRemote_support_engg(String remote_support_engg) {
		this.remote_support_engg = remote_support_engg;
	}

	public String getCall_type() {
		return call_type;
	}

	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}

	public String getRs_company() {
		return rs_company;
	}

	public void setRs_company(String rs_company) {
		this.rs_company = rs_company;
	}

	public String getProbable_spare() {
		return probable_spare;
	}

	public void setProbable_spare(String probable_spare) {
		this.probable_spare = probable_spare;
	}

	public String getRs_remarks() {
		return rs_remarks;
	}

	public void setRs_remarks(String rs_remarks) {
		this.rs_remarks = rs_remarks;
	}

	public String getRs_to_datetime() {
		return rs_to_datetime;
	}

	public void setRs_to_datetime(String rs_to_datetime) {
		this.rs_to_datetime = rs_to_datetime;
	}

	public String getL2status() {
		return l2status;
	}

	public void setL2status(String l2status) {
		this.l2status = l2status;
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

	public String getRs_date_time() {
		return rs_date_time;
	}

	public void setRs_date_time(String rs_date_time) {
		this.rs_date_time = rs_date_time;
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

	public String getRca() {
		return rca;
	}

	public void setRca(String rca) {
		this.rca = rca;
	}

	public String getSite_load_current() {
		return site_load_current;
	}

	public void setSite_load_current(String site_load_current) {
		this.site_load_current = site_load_current;
	}

	public String getBattery_capacity() {
		return battery_capacity;
	}

	public void setBattery_capacity(String battery_capacity) {
		this.battery_capacity = battery_capacity;
	}

	public String getRm_installed_qty() {
		return rm_installed_qty;
	}

	public void setRm_installed_qty(String rm_installed_qty) {
		this.rm_installed_qty = rm_installed_qty;
	}

	public String getCancelation_result() {
		return cancelation_result;
	}

	public void setCancelation_result(String cancelation_result) {
		this.cancelation_result = cancelation_result;
	}

	public String getPart_replace() {
		return part_replace;
	}

	public void setPart_replace(String part_replace) {
		this.part_replace = part_replace;
	}

	public String getParts_faulty() {
		return parts_faulty;
	}

	public void setParts_faulty(String parts_faulty) {
		this.parts_faulty = parts_faulty;
	}

	public String getVisit_start_date() {
		return visit_start_date;
	}

	public void setVisit_start_date(String visit_start_date) {
		this.visit_start_date = visit_start_date;
	}

	public String getVisit_end_date() {
		return visit_end_date;
	}

	public void setVisit_end_date(String visit_end_date) {
		this.visit_end_date = visit_end_date;
	}

	public String getNext_visit() {
		return next_visit;
	}

	public void setNext_visit(String next_visit) {
		this.next_visit = next_visit;
	}

	public String getL3status() {
		return l3status;
	}

	public void setL3status(String l3status) {
		this.l3status = l3status;
	}

	public String getFinal_status() {
		return final_status;
	}

	public void setFinal_status(String final_status) {
		this.final_status = final_status;
	}

	public String getUpdated_on_crm_date() {
		return updated_on_crm_date;
	}

	public void setUpdated_on_crm_date(String updated_on_crm_date) {
		this.updated_on_crm_date = updated_on_crm_date;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public String getSuspended() {
		return Suspended;
	}

	public void setSuspended(String suspended) {
		Suspended = suspended;
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

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getSpare_rquest_send() {
		return spare_rquest_send;
	}

	public void setSpare_rquest_send(String spare_rquest_send) {
		this.spare_rquest_send = spare_rquest_send;
	}

	public String getNo_of_visites() {
		return no_of_visites;
	}

	public void setNo_of_visites(String no_of_visites) {
		this.no_of_visites = no_of_visites;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getProduct_decription() {
		return product_decription;
	}

	public void setProduct_decription(String product_decription) {
		this.product_decription = product_decription;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getAmc_scope() {
		return amc_scope;
	}

	public void setAmc_scope(String amc_scope) {
		this.amc_scope = amc_scope;
	}

	public String getUser_customer() {
		return user_customer;
	}

	public void setUser_customer(String user_customer) {
		this.user_customer = user_customer;
	}

	public String getCaller_name() {
		return caller_name;
	}

	public void setCaller_name(String caller_name) {
		this.caller_name = caller_name;
	}

	public String getCaller_email() {
		return caller_email;
	}

	public void setCaller_email(String caller_email) {
		this.caller_email = caller_email;
	}

	public String getPolitical_state() {
		return political_state;
	}

	public void setPolitical_state(String political_state) {
		this.political_state = political_state;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getSite_address() {
		return site_address;
	}

	public void setSite_address(String site_address) {
		this.site_address = site_address;
	}

	public String getProblem_description() {
		return problem_description;
	}

	public void setProblem_description(String problem_description) {
		this.problem_description = problem_description;
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

	public String getActual_problem() {
		return actual_problem;
	}

	public void setActual_problem(String actual_problem) {
		this.actual_problem = actual_problem;
	}

	public String getPonumber() {
		return ponumber;
	}

	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}

	public String getPodate() {
		return podate;
	}

	public void setPodate(String podate) {
		this.podate = podate;
	}

	public String getService_type_remark() {
		return service_type_remark;
	}

	public void setService_type_remark(String service_type_remark) {
		this.service_type_remark = service_type_remark;
	}

	public String getRs_mobile() {
		return rs_mobile;
	}

	public void setRs_mobile(String rs_mobile) {
		this.rs_mobile = rs_mobile;
	}

	public String getOn_site_support_engg() {
		return on_site_support_engg;
	}

	public void setOn_site_support_engg(String on_site_support_engg) {
		this.on_site_support_engg = on_site_support_engg;
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

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getRS_to_OS_Date_time() {
		return RS_to_OS_Date_time;
	}

	public void setRS_to_OS_Date_time(String rS_to_OS_Date_time) {
		RS_to_OS_Date_time = rS_to_OS_Date_time;
	}

	public String getAction_taken() {
		return action_taken;
	}

	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}

	public String getOS_Remark() {
		return OS_Remark;
	}

	public void setOS_Remark(String oS_Remark) {
		OS_Remark = oS_Remark;
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

	public String getCaller_mobile() {
		return caller_mobile;
	}

	public void setCaller_mobile(String caller_mobile) {
		this.caller_mobile = caller_mobile;
	}

}
