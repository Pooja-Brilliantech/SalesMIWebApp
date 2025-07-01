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

@Entity
@Table(name = "product_details")
public class ProductDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	private int company_id;
	private String product_code;
	private String name;

	@Column(nullable = true)
	private String hsncode;

	@Column(nullable = true)
	private String pack;

	@ColumnDefault("0")
	private int brand_id = 0;

	@ColumnDefault("0")
	private int category_id = 0;

	@ColumnDefault("0")
	private int sub_category = 0;

	@ColumnDefault("0")
	private double mrp = 0.0d;

	@Column(length = 1000, nullable = true)
	private String details;

	@ColumnDefault("1")
	private int status;

	@ColumnDefault("0")
	private int created_by;

	@ColumnDefault("0")
	private int updated_by;

	@ColumnDefault("0")
	private double productWeight;

	@ColumnDefault("0")
	private int material_group;

	@ColumnDefault("0")
	private int productListSync;

	@CreationTimestamp
	private Date created_date;;

	@UpdateTimestamp
	private Date updated_date;

	@Column(nullable = true)
	private String image_link;

	@Column(nullable = true)
	private String packtitle;

	@Column(nullable = true)
	private String packunit;

	@Column(nullable = true)
	private String barCode;

	@Column(nullable = true)
	private String field1;

	@Column(nullable = true)
	private String field2;

	@Column(nullable = true)
	private String division;

	@Transient
	private String category_id1;

	@Transient
	private String sub_category1;

	@Transient
	private String brand_id1;

	@ColumnDefault("1")
	private double uomSize =1.0d;
	public double getProductWeight() {
		return productWeight;
	}
	

	public void setProductWeight(double productWeight) {
		this.productWeight = productWeight;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getPacktitle() {
		return packtitle;
	}

	public void setPacktitle(String packtitle) {
		this.packtitle = packtitle;
	}

	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getBrand_id1() {
		return brand_id1;
	}

	public void setBrand_id1(String brand_id1) {
		this.brand_id1 = brand_id1;
	}

	public String getCategory_id1() {
		return category_id1;
	}

	public void setCategory_id1(String category_id1) {
		this.category_id1 = category_id1;
	}

	public String getSub_category1() {
		return sub_category1;
	}

	public void setSub_category1(String sub_category1) {
		this.sub_category1 = sub_category1;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getSub_category() {
		return sub_category;
	}

	public void setSub_category(int sub_category) {
		this.sub_category = sub_category;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public int getMaterial_group() {
		return material_group;
	}

	public void setMaterial_group(int material_group) {
		this.material_group = material_group;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
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

	public int getProductListSync() {
		return productListSync;
	}

	public void setProductListSync(int productListSync) {
		this.productListSync = productListSync;
	}


	public double getUomSize() {
		return uomSize;
	}


	public void setUomSize(double uomSize) {
		this.uomSize = uomSize;
	}

}
