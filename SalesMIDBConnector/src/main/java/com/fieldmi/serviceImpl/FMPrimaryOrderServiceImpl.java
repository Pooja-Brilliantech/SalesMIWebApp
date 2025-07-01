package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMPrimaryOrderDao;
import com.fieldmi.service.FMPrimaryOrderService;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.PrimaryStockDetails;
import com.softtantra.salesapp.pojo.PrimaryStockGRN;


public class FMPrimaryOrderServiceImpl implements FMPrimaryOrderService{

	@Autowired
	FMPrimaryOrderDao fmPrimaryOrderDao;

	public void setFmPrimaryOrderDao(FMPrimaryOrderDao fmPrimaryOrderDao) {
		this.fmPrimaryOrderDao = fmPrimaryOrderDao;
	}

	@Override
	public boolean addPrimaryStockGRN(PrimaryStockGRN primaryStockGRN, String data) {
		// TODO Auto-generated method stub
		return fmPrimaryOrderDao.addPrimaryStockGRN(primaryStockGRN,data);
	}

	@Override
	public List<NewPriceMaster> getProductWiseItemCode(String product_code, int c_id) {
		// TODO Auto-generated method stub
		return fmPrimaryOrderDao.getProductWiseItemCode(product_code,c_id);
	}

	@Override
	public int verifyImportedData(int c_id, int location_id, String product_code,
			String product_name, String item_code, String pack) {
		// TODO Auto-generated method stub
		return fmPrimaryOrderDao.verifyImportedData(c_id, location_id, product_code, product_name,
				item_code, pack);
	}

	@Override
	public PrimaryStockDetails addNewPStock(PrimaryStockDetails pDetails, int c_id, int u_id, String username) {
		// TODO Auto-generated method stub
		return fmPrimaryOrderDao.addNewPStock(pDetails,c_id,u_id,username);
	}


}
