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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "user_details")
public class User implements Serializable {

	private static final long serialVersionUID = -1841637322227518866L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private int company_id;
	private String first_name;
	private String last_name;
	private String email;
	private int role_id;
	private String time_zone;
	private String mobile_no;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int state;
	private int city;
	private int new_state;
	private int new_city;
	private int reporting_role_id;
	private int reporting_manager_id;
	private int distributor_id;
	private String field1;
	private String field2;
	private String profile_img;
	private String signature;
	private int companyLocationId;
	private Date lastlogin;
	private String lat;
	private String longi;
	private Date activity_date_time;
	private String activity_name;
	private int firstPwdReset = 0;

	@ColumnDefault(value = "'Mobile'")
	private String lastLoginApplication = "Mobile";
	private String vehicle_no;
	private String account_state;

	// new parameter added by omkar k
	private Date in_time;
	private Date out_time;
	private int inout_type;
	private double km_travelled;
	private double current_battery_level;
	private int gps_status;
	private double speed;
	private String location;

	// new Parameter added by Swapnil
	private int product_of_the_month_id;
	private double target_for_product_value;
	private int target_for_product_unit;
	private String passport_copy;
	private String outlet_badge;
	// adhar and pan parameter added by priya
	private String adhar_card;
	private String health_card;
	private String insurance;
	private String pan_card;
	private String userLogin;
	private String useEmailAsUserName = "true";
	private int isAttendanceAllowed = 1;
	private int productListSync = 0;
	// column to store allowed discount
	private double allowed_discount;

	private String address;
	private String homeLat, homeLongi;
	@Column(nullable = true)
	private String apk_version;
	@Column(nullable = true)
	private String app_permissions;
	@ColumnDefault(value = "0")
	private int shift_id = 0;
	@ColumnDefault(value = "0")
	private int circle_id = 0;
	@ColumnDefault(value = "0")
	private int zone_id = 0;
	@ColumnDefault(value = "0")
	private int division_id = 0;
	@Column(nullable = true)
	private String employee_code;
	@Column(nullable = true)
	private String vendor_code;
	@ColumnDefault(value = "0")
	private int bsegment = 0;
	@ColumnDefault(value = "0")
	private int districtId = 0;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	private Date dateOfBirth;

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	@Column(nullable = true)
	private Date dateOfJoining;

	@Column(nullable = true)
	private String bloodGroup;

	@Column(nullable = true)
	private String bankName;

	@Column(nullable = true)
	private String bankAccountNumber;

	@Column(nullable = true)
	private String ifscCode;

	@Column(nullable = true)
	private String countryCode;

	@Column(nullable = true)
	private int profile_id;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getBsegment() {
		return bsegment;
	}

	public void setBsegment(int bsegment) {
		this.bsegment = bsegment;
	}

	public String getEmployee_code() {
		return employee_code;
	}

	public void setEmployee_code(String employee_code) {
		this.employee_code = employee_code;
	}

	public String getVendor_code() {
		return vendor_code;
	}

	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}

	public int getCircle_id() {
		return circle_id;
	}

	public void setCircle_id(int circle_id) {
		this.circle_id = circle_id;
	}

	public int getZone_id() {
		return zone_id;
	}

	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}

	public int getDivision_id() {
		return division_id;
	}

	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}

	public int getShift_id() {
		return shift_id;
	}

	public void setShift_id(int shift_id) {
		this.shift_id = shift_id;
	}

	public String getApk_version() {
		return apk_version;
	}

	public void setApk_version(String apk_version) {
		this.apk_version = apk_version;
	}

	public String getApp_permissions() {
		return app_permissions;
	}

	public void setApp_permissions(String app_permissions) {
		this.app_permissions = app_permissions;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getOutlet_badge() {
		return outlet_badge;
	}

	public void setOutlet_badge(String outlet_badge) {
		this.outlet_badge = outlet_badge;
	}

	public String getHealth_card() {
		return health_card;
	}

	public void setHealth_card(String health_card) {
		this.health_card = health_card;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getPassport_copy() {
		return passport_copy;
	}

	public void setPassport_copy(String passport_copy) {
		this.passport_copy = passport_copy;
	}

	public int getProduct_of_the_month_id() {
		return product_of_the_month_id;
	}

	public void setProduct_of_the_month_id(int product_of_the_month_id) {
		this.product_of_the_month_id = product_of_the_month_id;
	}

	public double getTarget_for_product_value() {
		return target_for_product_value;
	}

	public void setTarget_for_product_value(double target_for_product_value) {
		this.target_for_product_value = target_for_product_value;
	}

	public int getTarget_for_product_unit() {
		return target_for_product_unit;
	}

	public void setTarget_for_product_unit(int target_for_product_unit) {
		this.target_for_product_unit = target_for_product_unit;
	}

	public String getAccount_state() {
		return account_state;
	}

	public void setAccount_state(String account_state) {
		this.account_state = account_state;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	@Column(name = "country_id", columnDefinition = "int default '0'")
	private int country_id;

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public Date getActivity_date_time() {
		return activity_date_time;
	}

	public void setActivity_date_time(Date activity_date_time) {
		this.activity_date_time = activity_date_time;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public int getTotal_leaves() {
		return total_leaves;
	}

	public void setTotal_leaves(int total_leaves) {
		this.total_leaves = total_leaves;
	}

	public int getBalance_leaves() {
		return balance_leaves;
	}

	public void setBalance_leaves(int balance_leaves) {
		this.balance_leaves = balance_leaves;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Column(name = "total_leaves", columnDefinition = "int default '0'")
	private int total_leaves;
	@Column(name = "balance_leaves", columnDefinition = "int default '0'")
	private int balance_leaves;
	@Transient
	private String state_name;
	@Transient
	private String city_name;

	@Transient
	private String country_name;

	@Transient
	private String routes;
	@Transient
	private String password;

	@Transient
	private String role_name;
	@Transient
	private Date last_login;

	@Transient
	private String last_login1;

	public String getLast_login1() {
		return last_login1;
	}

	public void setLast_login1(String last_login1) {
		this.last_login1 = last_login1;
	}

	@Transient
	private String created_date1;

	@Transient
	private int stateId;

	@Transient
	private int cityId;

	@Transient
	private int route_id;
	private String address1;
	private String address2;

	public String getCreated_date1() {
		return created_date1;
	}

	public void setCreated_date1(String created_date1) {
		this.created_date1 = created_date1;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getReporting_role_id() {
		return reporting_role_id;
	}

	public void setReporting_role_id(int reporting_role_id) {
		this.reporting_role_id = reporting_role_id;
	}

	public int getReporting_manager_id() {
		return reporting_manager_id;
	}

	public void setReporting_manager_id(int reporting_manager_id) {
		this.reporting_manager_id = reporting_manager_id;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
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

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNew_state() {
		return new_state;
	}

	public void setNew_state(int new_state) {
		this.new_state = new_state;
	}

	public int getNew_city() {
		return new_city;
	}

	public void setNew_city(int new_city) {
		this.new_city = new_city;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public int getInout_type() {
		return inout_type;
	}

	public void setInout_type(int inout_type) {
		this.inout_type = inout_type;
	}

	public double getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(double km_travelled) {
		this.km_travelled = km_travelled;
	}

	public double getCurrent_battery_level() {
		return current_battery_level;
	}

	public void setCurrent_battery_level(double current_battery_level) {
		this.current_battery_level = current_battery_level;
	}

	public int getGps_status() {
		return gps_status;
	}

	public void setGps_status(int gps_status) {
		this.gps_status = gps_status;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getAllowed_discount() {
		return allowed_discount;
	}

	public void setAllowed_discount(double allowed_discount) {
		this.allowed_discount = allowed_discount;
	}

	public String getUseEmailAsUserName() {
		return useEmailAsUserName;
	}

	public void setUseEmailAsUserName(String useEmailAsUserName) {
		this.useEmailAsUserName = useEmailAsUserName;
	}

	public int getProductListSync() {
		return productListSync;
	}

	public void setProductListSync(int productListSync) {
		this.productListSync = productListSync;
	}

	public String getHomeLat() {
		return homeLat;
	}

	public void setHomeLat(String homeLat) {
		this.homeLat = homeLat;
	}

	public String getHomeLongi() {
		return homeLongi;
	}

	public void setHomeLongi(String homeLongi) {
		this.homeLongi = homeLongi;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getCompanyLocationId() {
		return companyLocationId;
	}

	public void setCompanyLocationId(int companyLocationId) {
		this.companyLocationId = companyLocationId;
	}

	public int getIsAttendanceAllowed() {
		return isAttendanceAllowed;
	}

	public void setIsAttendanceAllowed(int isAttendanceAllowed) {
		this.isAttendanceAllowed = isAttendanceAllowed;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getLastLoginApplication() {
		return lastLoginApplication;
	}

	public void setLastLoginApplication(String lastLoginApplication) {
		this.lastLoginApplication = lastLoginApplication;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getFirstPwdReset() {
		return firstPwdReset;
	}

	public void setFirstPwdReset(int firstPwdReset) {
		this.firstPwdReset = firstPwdReset;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public String getAdhar_card() {
		return adhar_card;
	}

	public void setAdhar_card(String adhar_card) {
		this.adhar_card = adhar_card;
	}

	public String getPan_card() {
		return pan_card;
	}

	public void setPan_card(String pan_card) {
		this.pan_card = pan_card;
	}

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

}
