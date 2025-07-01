package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "iit_agri_kharif")
public class IIT_Agri1 implements Serializable{

	@Id
	@Column(name = "IIT_agri_kharif_id")
	@GeneratedValue
	private int IIT_agri_kharif_id;

	private int IIT_agri_id;

	@Column(length = 65536)
	private String k_days_after_samplae_collected;

	@Column(length = 65536)
	private String k_season;

	@Column(length = 65536)
	private String k_primary_crops;

	@Column(length = 65536)
	private String k_area_of_land;

	@Column(length = 65536)
	private String k_avg_crop_production;

	@Column(length = 65536)
	private String k_harvesting_procedure;

	@Column(length = 65536)
	private String k_crop_weighed;

	@Column(length = 65536)
	private String k_mass_of_residue;

	@Column(length = 65536)
	private String k_weight_of_sample;

	@Column(length = 65536)
	private String k_uses_of_crop_residue;

	@Column(length = 65536)
	private String k_reasons;

	@Column(length = 65536)
	private String k_approx_days;

	@Column(length = 65536)
	private String k_primary_months;

	@Column(length = 65536)
	private String k_diff_crops;

	public int getIIT_agri_kharif_id() {
		return IIT_agri_kharif_id;
	}

	public void setIIT_agri_kharif_id(int iIT_agri_kharif_id) {
		IIT_agri_kharif_id = iIT_agri_kharif_id;
	}

	public int getIIT_agri_id() {
		return IIT_agri_id;
	}

	public void setIIT_agri_id(int iIT_agri_id) {
		IIT_agri_id = iIT_agri_id;
	}

	public String getK_days_after_samplae_collected() {
		return k_days_after_samplae_collected;
	}

	public void setK_days_after_samplae_collected(String k_days_after_samplae_collected) {
		this.k_days_after_samplae_collected = k_days_after_samplae_collected;
	}

	public String getK_season() {
		return k_season;
	}

	public void setK_season(String k_season) {
		this.k_season = k_season;
	}

	public String getK_primary_crops() {
		return k_primary_crops;
	}

	public void setK_primary_crops(String k_primary_crops) {
		this.k_primary_crops = k_primary_crops;
	}

	public String getK_area_of_land() {
		return k_area_of_land;
	}

	public void setK_area_of_land(String k_area_of_land) {
		this.k_area_of_land = k_area_of_land;
	}

	public String getK_avg_crop_production() {
		return k_avg_crop_production;
	}

	public void setK_avg_crop_production(String k_avg_crop_production) {
		this.k_avg_crop_production = k_avg_crop_production;
	}

	public String getK_harvesting_procedure() {
		return k_harvesting_procedure;
	}

	public void setK_harvesting_procedure(String k_harvesting_procedure) {
		this.k_harvesting_procedure = k_harvesting_procedure;
	}

	public String getK_crop_weighed() {
		return k_crop_weighed;
	}

	public void setK_crop_weighed(String k_crop_weighed) {
		this.k_crop_weighed = k_crop_weighed;
	}

	public String getK_mass_of_residue() {
		return k_mass_of_residue;
	}

	public void setK_mass_of_residue(String k_mass_of_residue) {
		this.k_mass_of_residue = k_mass_of_residue;
	}

	public String getK_weight_of_sample() {
		return k_weight_of_sample;
	}

	public void setK_weight_of_sample(String k_weight_of_sample) {
		this.k_weight_of_sample = k_weight_of_sample;
	}

	public String getK_uses_of_crop_residue() {
		return k_uses_of_crop_residue;
	}

	public void setK_uses_of_crop_residue(String k_uses_of_crop_residue) {
		this.k_uses_of_crop_residue = k_uses_of_crop_residue;
	}

	public String getK_reasons() {
		return k_reasons;
	}

	public void setK_reasons(String k_reasons) {
		this.k_reasons = k_reasons;
	}

	public String getK_approx_days() {
		return k_approx_days;
	}

	public void setK_approx_days(String k_approx_days) {
		this.k_approx_days = k_approx_days;
	}

	public String getK_primary_months() {
		return k_primary_months;
	}

	public void setK_primary_months(String k_primary_months) {
		this.k_primary_months = k_primary_months;
	}

	public String getK_diff_crops() {
		return k_diff_crops;
	}

	public void setK_diff_crops(String k_diff_crops) {
		this.k_diff_crops = k_diff_crops;
	}
}