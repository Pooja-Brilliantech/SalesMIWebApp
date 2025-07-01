package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user_import_records")
public class UserImportRecords implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private String first_name;
	private String last_name;
	private String email;
	private String role;
	private String reporting_role;
	private String reporting_manager;
	private String managerEmail;
	private String distributor;
	private String route;
	private String mobile_no;
	private String country;
	private String state;
	private String city;
	private int import_id;
	private int company_id;
	private String user_login;
	private String employeeCode;
	private String vendorCode;
	private String bankName;
	private String ifscCode;
	private String bankAccountNumber;
	private String password;
	private String homeLocation;
	private String homeLat ,homeLongi;
	private Date dateOfBirth;
	private Date dateOfJoining;
	private String bloodGroup;
	private String weeklyOff;
	private int shiftId;
	private int companyLocationId;
	private int businessSegmentId;
	private int divisionId;
	private int circleId;
	private int zoneId;
	private String shiftName;
	private String companyLocationNames;
	private String businessSegmentName;
	private String divisionName;
	private String circleName;
	private String zoneName;
	private String vehicle_no;
	private String field1;
	private String field2;
    private int product_of_the_month_id;
    private double allowed_discount;
    private Date lastlogin;
    private String apk_version;
	@Column(nullable = true)
	private String app_permissions;
	private int isAttendanceAllowed= 0;
	@Transient
	private String distributorCode;
	@Transient
	private String distributorName;
	@Transient
	private int routeId;
	@Transient
	private int distributorId;
	@Transient
	private Map<String, String> rolesByIdAndName = new HashMap<>();
	
	@ColumnDefault(value="0")
	public int getIsAttendanceAllowed() {
		return isAttendanceAllowed;
	}

	public void setIsAttendanceAllowed(int isAttendanceAllowed) {
		this.isAttendanceAllowed = isAttendanceAllowed;
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
    
    public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	
    public double getAllowed_discount() {
		return allowed_discount;
	}

	public void setAllowed_discount(double allowed_discount) {
		this.allowed_discount = allowed_discount;
	}
    public int getProduct_of_the_month_id() {
		return product_of_the_month_id;
	}

	public void setProduct_of_the_month_id(int product_of_the_month_id) {
		this.product_of_the_month_id = product_of_the_month_id;
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
	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	
	
	
	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getCompanyLocationNames() {
		return companyLocationNames;
	}

	public void setCompanyLocationNames(String companyLocationNames) {
		this.companyLocationNames = companyLocationNames;
	}

	public String getBusinessSegmentName() {
		return businessSegmentName;
	}

	public void setBusinessSegmentName(String businessSegmentName) {
		this.businessSegmentName = businessSegmentName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
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

	public String getWeeklyOff() {
		return weeklyOff;
	}

	public void setWeeklyOff(String weeklyOff) {
		this.weeklyOff = weeklyOff;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public int getCompanyLocationId() {
		return companyLocationId;
	}

	public void setCompanyLocationId(int companyLocationId) {
		this.companyLocationId = companyLocationId;
	}

	public int getBusinessSegmentId() {
		return businessSegmentId;
	}

	public void setBusinessSegmentId(int businessSegmentId) {
		this.businessSegmentId = businessSegmentId;
	}

	public int getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {

		this.company_id = company_id;
	}

	public int getImport_id() {
		return import_id;
	}

	public void setImport_id(int import_id) {
		this.import_id = import_id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReporting_role() {
		return reporting_role;
	}

	public void setReporting_role(String reporting_role) {
		this.reporting_role = reporting_role;
	}

	public String getReporting_manager() {
		return reporting_manager;
	}

	public void setReporting_manager(String reporting_manager) {
		this.reporting_manager = reporting_manager;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
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

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getDistributorCode() {
		return distributorCode;
	}

	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public int getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	public Map<String, String> getRolesByIdAndName() {
		return rolesByIdAndName;
	}

	public void setRolesByIdAndName(Map<String, String> rolesByIdAndName) {
		this.rolesByIdAndName = rolesByIdAndName;
	}

	
}
