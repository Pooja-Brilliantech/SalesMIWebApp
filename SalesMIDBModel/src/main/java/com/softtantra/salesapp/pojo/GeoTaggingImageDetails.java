package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geo_tagging_image_details")
public class GeoTaggingImageDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int geo_tagging_image_details_id;
	private int geo_id;
	private String image_link;
	private int type_id;

	public int getGeo_tagging_image_details_id() {
		return geo_tagging_image_details_id;
	}

	public void setGeo_tagging_image_details_id(int geo_tagging_image_details_id) {
		this.geo_tagging_image_details_id = geo_tagging_image_details_id;
	}

	public int getGeo_id() {
		return geo_id;
	}

	public void setGeo_id(int geo_id) {
		this.geo_id = geo_id;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

}
