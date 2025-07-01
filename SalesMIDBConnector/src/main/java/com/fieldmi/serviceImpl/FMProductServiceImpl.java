package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMProductDao;
import com.fieldmi.service.FMProductService;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.Tax;

public class FMProductServiceImpl implements FMProductService {

	@Autowired
	FMProductDao fmProductDao;

	public void setFmProductDao(FMProductDao fmProductDao) {
		this.fmProductDao = fmProductDao;
	}

	@Override
	public ProductDetails addNewProduct(ProductDetails newproductMaster, int c_id, int u_id) {
		
		return fmProductDao.addNewProduct(newproductMaster, c_id, u_id);
	}

	@Override
	public NewPriceMaster addNewPriceMaster(NewPriceMaster priceMaster, int c_id, int u_id) {
		
		return fmProductDao.addNewPriceMaster(priceMaster, c_id, u_id);
	}

	@Override
	public List<ProductDetails> getProductDetails(String productCode, int c_id) {
		return fmProductDao.getProductDetails(productCode, c_id);
	}

	@Override
	public List<NewPriceMaster> getPriceDetailsForProduct(int product_id) {
		return fmProductDao.getPriceDetailsForProduct(product_id);
	}

	@Override
	public List<Tax> getTaxList(int c_id) {
		return fmProductDao.getTaxList(c_id);
	}

	

}
