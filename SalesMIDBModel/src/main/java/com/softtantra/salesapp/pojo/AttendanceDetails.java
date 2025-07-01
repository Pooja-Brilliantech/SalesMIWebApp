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
@Table(name = "attendance_details")
public class AttendanceDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attendance_id;
	private int company_id;
	private Long user_id;
	@Column(name = "inout_type", columnDefinition = "int default '0'")
	private int inout_type;
	private Date inout_time;
	private String inout_location_name;
	private String inout_lat;
	private String inout_longi;
	private String city;
	private String state;
	private String distributor_name;
	private String route_name;
	@Column(name = "km_travelled", columnDefinition = "double default '0.0d'")
	private double km_travelled;
	@Column(name = "accuracy", columnDefinition = "double default '0.0d'")
	private double accuracy;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	@Column(name = "flag", columnDefinition = "int default '0'")
	private int flag;
	@Column(name = "retry_attempt", columnDefinition = "int default '0'")
	private int retry_attempt;
	private Date location_updated_time;
	private String selfieImage;

	@Transient
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSelfieImage() {
		return selfieImage;
	}

	public void setSelfieImage(String selfieImage) {
		this.selfieImage = selfieImage;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getRetry_attempt() {
		return retry_attempt;
	}

	public void setRetry_attempt(int retry_attempt) {
		this.retry_attempt = retry_attempt;
	}

	public Date getLocation_updated_time() {
		return location_updated_time;
	}

	public void setLocation_updated_time(Date location_updated_time) {
		this.location_updated_time = location_updated_time;
	}

	@Transient
	private String timeOutSelfie;

	public String getTimeOutSelfie() {
		return timeOutSelfie;
	}

	public void setTimeOutSelfie(String timeOutSelfie) {
		this.timeOutSelfie = timeOutSelfie;
	}

	@Transient
	private String user_name;
	@Transient
	private String out_location;
	@Transient
	private String duration;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getOut_location() {
		return out_location;
	}

	public void setOut_location(String out_location) {
		this.out_location = out_location;
	}

	public long getAttendance_id() {
		return attendance_id;
	}

	public void setAttendance_id(long attendance_id) {
		this.attendance_id = attendance_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public int getInout_type() {
		return inout_type;
	}

	public void setInout_type(int inout_type) {
		this.inout_type = inout_type;
	}

	public Date getInout_time() {
		return inout_time;
	}

	public void setInout_time(Date inout_time) {
		this.inout_time = inout_time;
	}

	public String getInout_location_name() {
		return inout_location_name;
	}

	public void setInout_location_name(String inout_location_name) {
		this.inout_location_name = inout_location_name;
	}

	public String getInout_lat() {
		return inout_lat;
	}

	public void setInout_lat(String inout_lat) {
		this.inout_lat = inout_lat;
	}

	public String getInout_longi() {
		return inout_longi;
	}

	public void setInout_longi(String inout_longi) {
		this.inout_longi = inout_longi;
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

	public int getDevice_details_id() {
		return device_details_id;
	}

	public void setDevice_details_id(int device_details_id) {
		this.device_details_id = device_details_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@Column(name = "device_details_id", columnDefinition = "int default '0'")
	private int device_details_id;

	@Transient
	private String in_time;
	@Transient
	private String out_time;
	@Transient
	private String attedance_date;

	@Transient
	private String distance;
	@Transient
	private String epense_date;
	@Transient
	private String from_place;
	@Transient
	private String to_place;

	@Transient
	private String distance_in_km;
	
	
	public String getDistance_in_km() {
		return distance_in_km;
	}

	public void setDistance_in_km(String distance_in_km) {
		this.distance_in_km = distance_in_km;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getEpense_date() {
		return epense_date;
	}

	public void setEpense_date(String epense_date) {
		this.epense_date = epense_date;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public String getAttedance_date() {
		return attedance_date;
	}

	public void setAttedance_date(String attedance_date) {
		this.attedance_date = attedance_date;
	}

	public String getFrom_place() {
		return from_place;
	}

	public void setFrom_place(String from_place) {
		this.from_place = from_place;
	}

	public String getTo_place() {
		return to_place;
	}

	public void setTo_place(String to_place) {
		this.to_place = to_place;
	}

	public double getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(double km_travelled) {
		this.km_travelled = km_travelled;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	

}
