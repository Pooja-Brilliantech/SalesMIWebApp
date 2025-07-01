package com.fieldmi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMDistributorStockDao;
import com.fieldmi.service.FMDistributorStockService;
import com.fieldmi.stubs.WsDStockDetailsList;
import com.fieldmi.stubs.WsDistributorLocationList;
import com.softtantra.salesapp.pojo.DistributorStockDetails;
import com.softtantra.salesapp.pojo.SecondaryStockGRN;

public class FMDistributorStockServiceImpl implements FMDistributorStockService{
	
	@Autowired
	FMDistributorStockDao fmDistributorStockDao;

	public void setFmDistributorStockDao(FMDistributorStockDao fmDistributorStockDao) {
		this.fmDistributorStockDao = fmDistributorStockDao;
	}

	@Override
	public boolean addSecondaryStockGRN(SecondaryStockGRN secondaryStockGRN, String data) {
		// TODO Auto-generated method stub
		return fmDistributorStockDao.addSecondaryStockGRN(secondaryStockGRN,data);
	}

	@Override
	public DistributorStockDetails addNewDStock(DistributorStockDetails distributorStockDetails, int c_id, int u_id,
			String username) {
		return fmDistributorStockDao.addNewDStock(distributorStockDetails, c_id, u_id, username);
	}

	@Override
	public WsDistributorLocationList getDistributorLocationList(WsDistributorLocationList wsDistributorLocationList) {
		// TODO Auto-generated method stub
		return fmDistributorStockDao.getDistributorLocationList(wsDistributorLocationList);
	}

	@Override
	public WsDStockDetailsList getDistributorStockDetails(WsDistributorLocationList wsDistributorLocationList) {
		// TODO Auto-generated method stub
		return fmDistributorStockDao.getDistributorStockDetails(wsDistributorLocationList);
	}
	
	

}
