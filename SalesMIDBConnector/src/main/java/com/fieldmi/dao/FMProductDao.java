package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.Tax;

public interface FMProductDao {

	
	public ProductDetails addNewProduct(ProductDetails newproductMaster, int c_id, int u_id);
	
	public NewPriceMaster addNewPriceMaster(NewPriceMaster priceMaster, int c_id, int u_id);
	
	public List<ProductDetails> getProductDetails(String productCode, int c_id);
	
	public List<NewPriceMaster> getPriceDetailsForProduct(int product_id);
	
	public List<Tax> getTaxList(int c_id);

}
