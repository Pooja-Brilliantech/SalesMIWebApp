package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "iit_residential")
public class IIT_Residential implements Serializable{
	@Id
	@Column(name = "IIT_res_id")
	@GeneratedValue
	private int IIT_res_id;
	private String location;
	private String country;
	private String city;
	private String district;
	private String name;
	private String village;
	private String state;
	private String house_id;
	private String no_of_people;
	private String member_owns_agri_land;
	private String volumn_of_water;
	private String no_of_rooms_space_heating;
	private String use_of_warmwater_otherpurpose;
	private String type_of_stove;
	private String fule_for_keeping_bugs_away;
	private String month_practice_keeping_bugs_away;
	private String other_source_of_conbusion_of_fule;
	private String cooks_per_day;
	private String breakfast_time;
	private String lunch_time;
	private String dinner_time;
	private String company_id;
	private String type_of_kitchen;
	private String windows_in_kitchen;
	private String door_in_kitchen;
	private String size_of_kitchen;
	private String survery_no;
	private String stove_used;
	private String secondary_stove_used;
	private String months_use_stove;
	private String months_use_secondary_stove;
	private String fule_used_for_biomass;
	private String other_fule_used_for_biomass;
	private String fule_used_for_cooking;
	private String fule_used_for_boiling;
	private String fule_used_for_frying;
	private String fule_used_for_baking;
	private String fule_used_for_heating;
	private String is_warm_water_for_bathing;
	private String no_of_people_warmwater;
	private String stove_used_for_water_heating;
	private String time_for_water_heating;
	private String warm_water_months;
	private String heating_indoor_space;
	private String device_use;
	private String fuel_used_for_space_heating;
	private String weight_of_fule_for_spheating;
	private String weight_of_fule_for_heating;
	private String avg_hr_indoor_space;
	private String months_for_indoor_heating;
	private String source_of_lighting;
	private String other_source_of_lighting;
	private String availability_of_electricity;
	private String load_shedding;
	private String type_of_kerosene_lamp;
	private String no_of_lamps;
	private String kerosene_use;
	private String monthly_kerosene_use;
	private String amount_of_fule_required_for_lamp;
	private String lamp_last_hr;
	private String counducting_measurement;
	private String comments;
	private String status;
	private String lat;
	private String longi;
	private String image_path;
	private long created_by;
	@CreationTimestamp
	private Date created_date;;
	private long updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String answer_to_question;
	private String rely_traditional_stove;
	private String reason_to_continue_biomass_fuel;
	private String contact;
	private String use_of_warmwater_otherpurpose_reason;
	private String is_burn_biomass;
	private String referenceNumber;
	private String dry_garden_waste;
	private String amount_dry_waste;
	private String diesel_gensets;
	private String fuel_for_irrigation;

	@Transient
	private String username;

	public String getDry_garden_waste() {
		return dry_garden_waste;
	}

	public void setDry_garden_waste(String dry_garden_waste) {
		this.dry_garden_waste = dry_garden_waste;
	}

	public String getAmount_dry_waste() {
		return amount_dry_waste;
	}

	public void setAmount_dry_waste(String amount_dry_waste) {
		this.amount_dry_waste = amount_dry_waste;
	}

	public String getDiesel_gensets() {
		return diesel_gensets;
	}

	public void setDiesel_gensets(String diesel_gensets) {
		this.diesel_gensets = diesel_gensets;
	}

	public String getFuel_for_irrigation() {
		return fuel_for_irrigation;
	}

	public void setFuel_for_irrigation(String fuel_for_irrigation) {
		this.fuel_for_irrigation = fuel_for_irrigation;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getName() {
		return name;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNo_of_rooms_space_heating() {
		return no_of_rooms_space_heating;
	}

	public void setNo_of_rooms_space_heating(String no_of_rooms_space_heating) {
		this.no_of_rooms_space_heating = no_of_rooms_space_heating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType_of_stove() {
		return type_of_stove;
	}

	public void setType_of_stove(String type_of_stove) {
		this.type_of_stove = type_of_stove;
	}

	public String getOther_source_of_conbusion_of_fule() {
		return other_source_of_conbusion_of_fule;
	}

	public void setOther_source_of_conbusion_of_fule(String other_source_of_conbusion_of_fule) {
		this.other_source_of_conbusion_of_fule = other_source_of_conbusion_of_fule;
	}

	public String getFule_for_keeping_bugs_away() {
		return fule_for_keeping_bugs_away;
	}

	public void setFule_for_keeping_bugs_away(String fule_for_keeping_bugs_away) {
		this.fule_for_keeping_bugs_away = fule_for_keeping_bugs_away;
	}

	public String getMonth_practice_keeping_bugs_away() {
		return month_practice_keeping_bugs_away;
	}

	public void setMonth_practice_keeping_bugs_away(String month_practice_keeping_bugs_away) {
		this.month_practice_keeping_bugs_away = month_practice_keeping_bugs_away;
	}

	public String getUse_of_warmwater_otherpurpose() {
		return use_of_warmwater_otherpurpose;
	}

	public void setUse_of_warmwater_otherpurpose(String use_of_warmwater_otherpurpose) {
		this.use_of_warmwater_otherpurpose = use_of_warmwater_otherpurpose;
	}

	public String getVolumn_of_water() {
		return volumn_of_water;
	}

	public void setVolumn_of_water(String volumn_of_water) {
		this.volumn_of_water = volumn_of_water;
	}

	public String getMember_owns_agri_land() {
		return member_owns_agri_land;
	}

	public void setMember_owns_agri_land(String member_owns_agri_land) {
		this.member_owns_agri_land = member_owns_agri_land;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getOther_fule_used_for_biomass() {
		return other_fule_used_for_biomass;
	}

	public void setOther_fule_used_for_biomass(String other_fule_used_for_biomass) {
		this.other_fule_used_for_biomass = other_fule_used_for_biomass;
	}

	public String getFule_used_for_heating() {
		return fule_used_for_heating;
	}

	public void setFule_used_for_heating(String fule_used_for_heating) {
		this.fule_used_for_heating = fule_used_for_heating;
	}

	private String fule_used_for_tme;

	public String getFule_used_for_frying() {
		return fule_used_for_frying;
	}

	public void setFule_used_for_frying(String fule_used_for_frying) {
		this.fule_used_for_frying = fule_used_for_frying;
	}

	public int getIIT_res_id() {
		return IIT_res_id;
	}

	public void setIIT_res_id(int iIT_res_id) {
		IIT_res_id = iIT_res_id;
	}

	public String getWeight_of_fule_for_heating() {
		return weight_of_fule_for_heating;
	}

	public void setWeight_of_fule_for_heating(String weight_of_fule_for_heating) {
		this.weight_of_fule_for_heating = weight_of_fule_for_heating;
	}

	public String getWeight_of_fule_for_spheating() {
		return weight_of_fule_for_spheating;
	}

	public void setWeight_of_fule_for_spheating(String weight_of_fule_for_spheating) {
		this.weight_of_fule_for_spheating = weight_of_fule_for_spheating;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public String getNo_of_people() {
		return no_of_people;
	}

	public void setNo_of_people(String no_of_people) {
		this.no_of_people = no_of_people;
	}

	public String getCooks_per_day() {
		return cooks_per_day;
	}

	public void setCooks_per_day(String cooks_per_day) {
		this.cooks_per_day = cooks_per_day;
	}

	public String getBreakfast_time() {
		return breakfast_time;
	}

	public void setBreakfast_time(String breakfast_time) {
		this.breakfast_time = breakfast_time;
	}

	public String getLunch_time() {
		return lunch_time;
	}

	public void setLunch_time(String lunch_time) {
		this.lunch_time = lunch_time;
	}

	public String getDinner_time() {
		return dinner_time;
	}

	public void setDinner_time(String dinner_time) {
		this.dinner_time = dinner_time;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getType_of_kitchen() {
		return type_of_kitchen;
	}

	public void setType_of_kitchen(String type_of_kitchen) {
		this.type_of_kitchen = type_of_kitchen;
	}

	public String getWindows_in_kitchen() {
		return windows_in_kitchen;
	}

	public void setWindows_in_kitchen(String windows_in_kitchen) {
		this.windows_in_kitchen = windows_in_kitchen;
	}

	public String getDoor_in_kitchen() {
		return door_in_kitchen;
	}

	public void setDoor_in_kitchen(String door_in_kitchen) {
		this.door_in_kitchen = door_in_kitchen;
	}

	public String getSize_of_kitchen() {
		return size_of_kitchen;
	}

	public void setSize_of_kitchen(String size_of_kitchen) {
		this.size_of_kitchen = size_of_kitchen;
	}

	public String getStove_used() {
		return stove_used;
	}

	public void setStove_used(String stove_used) {
		this.stove_used = stove_used;
	}

	public String getMonths_use_stove() {
		return months_use_stove;
	}

	public void setMonths_use_stove(String months_use_stove) {
		this.months_use_stove = months_use_stove;
	}

	public String getMonths_use_secondary_stove() {
		return months_use_secondary_stove;
	}

	public void setMonths_use_secondary_stove(String months_use_secondary_stove) {
		this.months_use_secondary_stove = months_use_secondary_stove;
	}

	public String getFule_used_for_biomass() {
		return fule_used_for_biomass;
	}

	public void setFule_used_for_biomass(String fule_used_for_biomass) {
		this.fule_used_for_biomass = fule_used_for_biomass;
	}

	public String getFule_used_for_cooking() {
		return fule_used_for_cooking;
	}

	public void setFule_used_for_cooking(String fule_used_for_cooking) {
		this.fule_used_for_cooking = fule_used_for_cooking;
	}

	public String getFule_used_for_boiling() {
		return fule_used_for_boiling;
	}

	public void setFule_used_for_boiling(String fule_used_for_boiling) {
		this.fule_used_for_boiling = fule_used_for_boiling;
	}

	public String getFule_used_for_baking() {
		return fule_used_for_baking;
	}

	public void setFule_used_for_baking(String fule_used_for_baking) {
		this.fule_used_for_baking = fule_used_for_baking;
	}

	public String getFule_used_for_tme() {
		return fule_used_for_tme;
	}

	public void setFule_used_for_tme(String fule_used_for_tme) {
		this.fule_used_for_tme = fule_used_for_tme;
	}

	public String getIs_warm_water_for_bathing() {
		return is_warm_water_for_bathing;
	}

	public void setIs_warm_water_for_bathing(String is_warm_water_for_bathing) {
		this.is_warm_water_for_bathing = is_warm_water_for_bathing;
	}

	public String getNo_of_people_warmwater() {
		return no_of_people_warmwater;
	}

	public void setNo_of_people_warmwater(String no_of_people_warmwater) {
		this.no_of_people_warmwater = no_of_people_warmwater;
	}

	public String getStove_used_for_water_heating() {
		return stove_used_for_water_heating;
	}

	public void setStove_used_for_water_heating(String stove_used_for_water_heating) {
		this.stove_used_for_water_heating = stove_used_for_water_heating;
	}

	public String getTime_for_water_heating() {
		return time_for_water_heating;
	}

	public void setTime_for_water_heating(String time_for_water_heating) {
		this.time_for_water_heating = time_for_water_heating;
	}

	public String getWarm_water_months() {
		return warm_water_months;
	}

	public void setWarm_water_months(String warm_water_months) {
		this.warm_water_months = warm_water_months;
	}

	public String getHeating_indoor_space() {
		return heating_indoor_space;
	}

	public void setHeating_indoor_space(String heating_indoor_space) {
		this.heating_indoor_space = heating_indoor_space;
	}

	public String getDevice_use() {
		return device_use;
	}

	public void setDevice_use(String device_use) {
		this.device_use = device_use;
	}

	public String getFuel_used_for_space_heating() {
		return fuel_used_for_space_heating;
	}

	public void setFuel_used_for_space_heating(String fuel_used_for_space_heating) {
		this.fuel_used_for_space_heating = fuel_used_for_space_heating;
	}

	public String getAvg_hr_indoor_space() {
		return avg_hr_indoor_space;
	}

	public void setAvg_hr_indoor_space(String avg_hr_indoor_space) {
		this.avg_hr_indoor_space = avg_hr_indoor_space;
	}

	public String getMonths_for_indoor_heating() {
		return months_for_indoor_heating;
	}

	public void setMonths_for_indoor_heating(String months_for_indoor_heating) {
		this.months_for_indoor_heating = months_for_indoor_heating;
	}

	public String getSource_of_lighting() {
		return source_of_lighting;
	}

	public void setSource_of_lighting(String source_of_lighting) {
		this.source_of_lighting = source_of_lighting;
	}

	public String getAvailability_of_electricity() {
		return availability_of_electricity;
	}

	public void setAvailability_of_electricity(String availability_of_electricity) {
		this.availability_of_electricity = availability_of_electricity;
	}

	public String getType_of_kerosene_lamp() {
		return type_of_kerosene_lamp;
	}

	public void setType_of_kerosene_lamp(String type_of_kerosene_lamp) {
		this.type_of_kerosene_lamp = type_of_kerosene_lamp;
	}

	public String getNo_of_lamps() {
		return no_of_lamps;
	}

	public void setNo_of_lamps(String no_of_lamps) {
		this.no_of_lamps = no_of_lamps;
	}

	public String getKerosene_use() {
		return kerosene_use;
	}

	public void setKerosene_use(String kerosene_use) {
		this.kerosene_use = kerosene_use;
	}

	public String getMonthly_kerosene_use() {
		return monthly_kerosene_use;
	}

	public void setMonthly_kerosene_use(String monthly_kerosene_use) {
		this.monthly_kerosene_use = monthly_kerosene_use;
	}

	public String getAmount_of_fule_required_for_lamp() {
		return amount_of_fule_required_for_lamp;
	}

	public void setAmount_of_fule_required_for_lamp(String amount_of_fule_required_for_lamp) {
		this.amount_of_fule_required_for_lamp = amount_of_fule_required_for_lamp;
	}

	public String getLamp_last_hr() {
		return lamp_last_hr;
	}

	public void setLamp_last_hr(String lamp_last_hr) {
		this.lamp_last_hr = lamp_last_hr;
	}

	public String getCounducting_measurement() {
		return counducting_measurement;
	}

	public void setCounducting_measurement(String counducting_measurement) {
		this.counducting_measurement = counducting_measurement;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
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

	public String getOther_source_of_lighting() {
		return other_source_of_lighting;
	}

	public String getLoad_shedding() {
		return load_shedding;
	}

	public void setLoad_shedding(String load_shedding) {
		this.load_shedding = load_shedding;
	}

	public void setOther_source_of_lighting(String other_source_of_lighting) {
		this.other_source_of_lighting = other_source_of_lighting;
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

	public String getSurvery_no() {
		return survery_no;
	}

	public void setSurvery_no(String survery_no) {
		this.survery_no = survery_no;
	}

	public String getSecondary_stove_used() {
		return secondary_stove_used;
	}

	public void setSecondary_stove_used(String secondary_stove_used) {
		this.secondary_stove_used = secondary_stove_used;
	}

	public String getAnswer_to_question() {
		return answer_to_question;
	}

	public void setAnswer_to_question(String answer_to_question) {
		this.answer_to_question = answer_to_question;
	}

	public String getRely_traditional_stove() {
		return rely_traditional_stove;
	}

	public void setRely_traditional_stove(String rely_traditional_stove) {
		this.rely_traditional_stove = rely_traditional_stove;
	}

	public String getReason_to_continue_biomass_fuel() {
		return reason_to_continue_biomass_fuel;
	}

	public void setReason_to_continue_biomass_fuel(String reason_to_continue_biomass_fuel) {
		this.reason_to_continue_biomass_fuel = reason_to_continue_biomass_fuel;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUse_of_warmwater_otherpurpose_reason() {
		return use_of_warmwater_otherpurpose_reason;
	}

	public void setUse_of_warmwater_otherpurpose_reason(String use_of_warmwater_otherpurpose_reason) {
		this.use_of_warmwater_otherpurpose_reason = use_of_warmwater_otherpurpose_reason;
	}

	public String getIs_burn_biomass() {
		return is_burn_biomass;
	}

	public void setIs_burn_biomass(String is_burn_biomass) {
		this.is_burn_biomass = is_burn_biomass;
	}

}
