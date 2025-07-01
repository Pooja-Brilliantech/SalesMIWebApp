package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "eod_special_report") // End Of The Day Special Report
public class EOD_SpecialReport implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int special_report_id;
	
	private int total_call=0;
	private int productive_call=0;
	
	private String key_product_sales;
	private double total_paste_sale=0.0;
	private double total_hanger_sale=0.0;
	private double total_kg_sale=0.0;
	private double amount=0.0;
	private int new_destri_ss_search=0;
	
	private Date report_date;
	private String area;
	private int no_of_distri_ss_visited;
	private String name;
	private String work;
	private String remark;
	private double target_per_day=0.0;
	private double target_per_month=0.0;
	private double avg_sale_per_day=0.0;
	private double avg_sale_per_month=0.0;

	private int company_id;
	private int created_by;
	private int updated_by;
	private int status;
	private String country;
	private String state;
	private String city;
	private String location;
	private int user_id;
	
	private Date created_date;
	private Date update_date;
	// swapnil
	private String retailer_town;
	private String retailer_area;
	
	private int product_of_the_month=0;
	private double product_of_month_traget=0.0;
	private double product_of_month_sale=0.0;
	private int new_distributor_ss_searching_d=0;
	
	private String distributor_area_d;
	private int no_of_ss_visited_d=0;
	private int customer_id;
	private int distributor_id=0;
	private int route_id=0;
	private double login_forthe_day=0.0;
	private double disbursed_forthe_day=0.0;
	private double closing_forthe_day=0.0;
	private double non_starter_solved=0.0;
	private double ppd_clearance=0.0;
	private double deactive_NACH_clearance=0.0;
	
	

	public double getLogin_forthe_day() {
		return login_forthe_day;
	}

	public void setLogin_forthe_day(double login_forthe_day) {
		this.login_forthe_day = login_forthe_day;
	}

	public double getDisbursed_forthe_day() {
		return disbursed_forthe_day;
	}

	public void setDisbursed_forthe_day(double disbursed_forthe_day) {
		this.disbursed_forthe_day = disbursed_forthe_day;
	}

	public double getClosing_forthe_day() {
		return closing_forthe_day;
	}

	public void setClosing_forthe_day(double closing_forthe_day) {
		this.closing_forthe_day = closing_forthe_day;
	}

	public double getNon_starter_solved() {
		return non_starter_solved;
	}

	public void setNon_starter_solved(double non_starter_solved) {
		this.non_starter_solved = non_starter_solved;
	}

	public double getPpd_clearance() {
		return ppd_clearance;
	}

	public void setPpd_clearance(double ppd_clearance) {
		this.ppd_clearance = ppd_clearance;
	}

	public double getDeactive_NACH_clearance() {
		return deactive_NACH_clearance;
	}

	public void setDeactive_NACH_clearance(double deactive_NACH_clearance) {
		this.deactive_NACH_clearance = deactive_NACH_clearance;
	}

	public String getRetailer_town() {
		return retailer_town;
	}

	public void setRetailer_town(String retailer_town) {
		this.retailer_town = retailer_town;
	}

	public String getRetailer_area() {
		return retailer_area;
	}

	public void setRetailer_area(String retailer_area) {
		this.retailer_area = retailer_area;
	}

	public int getProduct_of_the_month() {
		return product_of_the_month;
	}

	public void setProduct_of_the_month(int product_of_the_month) {
		this.product_of_the_month = product_of_the_month;
	}

	public double getProduct_of_month_traget() {
		return product_of_month_traget;
	}

	public void setProduct_of_month_traget(double product_of_month_traget) {
		this.product_of_month_traget = product_of_month_traget;
	}

	public double getProduct_of_month_sale() {
		return product_of_month_sale;
	}

	public void setProduct_of_month_sale(double product_of_month_sale) {
		this.product_of_month_sale = product_of_month_sale;
	}

	public int getNew_distributor_ss_searching_d() {
		return new_distributor_ss_searching_d;
	}

	public void setNew_distributor_ss_searching_d(int new_distributor_ss_searching_d) {
		this.new_distributor_ss_searching_d = new_distributor_ss_searching_d;
	}

	public String getDistributor_area_d() {
		return distributor_area_d;
	}

	public void setDistributor_area_d(String distributor_area_d) {
		this.distributor_area_d = distributor_area_d;
	}

	public int getNo_of_ss_visited_d() {
		return no_of_ss_visited_d;
	}

	public void setNo_of_ss_visited_d(int no_of_ss_visited_d) {
		this.no_of_ss_visited_d = no_of_ss_visited_d;
	}

	public int getSpecial_report_id() {
		return special_report_id;
	}

	public void setSpecial_report_id(int special_report_id) {
		this.special_report_id = special_report_id;
	}

	public int getTotal_call() {
		return total_call;
	}

	public void setTotal_call(int total_call) {
		this.total_call = total_call;
	}

	public int getProductive_call() {
		return productive_call;
	}

	public void setProductive_call(int productive_call) {
		this.productive_call = productive_call;
	}

	public String getKey_product_sales() {
		return key_product_sales;
	}

	public void setKey_product_sales(String key_product_sales) {
		this.key_product_sales = key_product_sales;
	}

	public double getTotal_paste_sale() {
		return total_paste_sale;
	}

	public void setTotal_paste_sale(double total_paste_sale) {
		this.total_paste_sale = total_paste_sale;
	}

	public double getTotal_hanger_sale() {
		return total_hanger_sale;
	}

	public void setTotal_hanger_sale(double total_hanger_sale) {
		this.total_hanger_sale = total_hanger_sale;
	}

	public double getTotal_kg_sale() {
		return total_kg_sale;
	}

	public void setTotal_kg_sale(double total_kg_sale) {
		this.total_kg_sale = total_kg_sale;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getNew_destri_ss_search() {
		return new_destri_ss_search;
	}

	public void setNew_destri_ss_search(int new_destri_ss_search) {
		this.new_destri_ss_search = new_destri_ss_search;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getNo_of_distri_ss_visited() {
		return no_of_distri_ss_visited;
	}

	public void setNo_of_distri_ss_visited(int no_of_distri_ss_visited) {
		this.no_of_distri_ss_visited = no_of_distri_ss_visited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getTarget_per_day() {
		return target_per_day;
	}

	public void setTarget_per_day(double target_per_day) {
		this.target_per_day = target_per_day;
	}

	public double getTarget_per_month() {
		return target_per_month;
	}

	public void setTarget_per_month(double target_per_month) {
		this.target_per_month = target_per_month;
	}

	public double getAvg_sale_per_day() {
		return avg_sale_per_day;
	}

	public void setAvg_sale_per_day(double avg_sale_per_day) {
		this.avg_sale_per_day = avg_sale_per_day;
	}

	public double getAvg_sale_per_month() {
		return avg_sale_per_month;
	}

	public void setAvg_sale_per_month(double avg_sale_per_month) {
		this.avg_sale_per_month = avg_sale_per_month;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

}
