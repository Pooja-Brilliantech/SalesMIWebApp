package com.fieldmi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMSalesOrgDao;
import com.fieldmi.service.FMSalesOrgService;
import com.fieldmi.stubs.WsDistOrg;
import com.fieldmi.stubs.WsDistrictDetailsOutput;
import com.fieldmi.stubs.WsFetchDistrictTaluka;
import com.fieldmi.stubs.WsFetchSalesOrg;
import com.fieldmi.stubs.WsSalesDistrict;
import com.fieldmi.stubs.WsSalesOrgOutPut;

public class FMSalesOrgServiceImpl implements FMSalesOrgService {

	@Autowired
	FMSalesOrgDao fmSalesOrgDao;

	public void setFmSalesOrgDao(FMSalesOrgDao fmSalesOrgDao) {
		this.fmSalesOrgDao = fmSalesOrgDao;
	}

	@Override
	public WsSalesOrgOutPut getSalesOrgList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		return fmSalesOrgDao.getSalesOrgList(wsFetchSalesOrgInput);
	}

	@Override
	public WsSalesOrgOutPut getDistributorChannelList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		return fmSalesOrgDao.getDistributorChannelList(wsFetchSalesOrgInput);
	}

	@Override
	public WsSalesOrgOutPut getDivisionList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		return fmSalesOrgDao.getDivisionList(wsFetchSalesOrgInput);
	}

	@Override
	public WsDistrictDetailsOutput getDistrictList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput) {

		return fmSalesOrgDao.getDistrictList(wsFetchDistrictTalukaInput);
	}

	@Override
	public WsSalesDistrict getSalesDistrictList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput) {

		return fmSalesOrgDao.getSalesDistrictList(wsFetchDistrictTalukaInput);
	}

	@Override
	public WsDistOrg getSalesDistWiseSalesGroupList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput) {
		// TODO Auto-generated method stub
		return fmSalesOrgDao.getSalesDistWiseSalesGroupList(wsFetchDistrictTalukaInput);
	}

	@Override
	public WsSalesOrgOutPut getSalesGroupWiseSalesOfficeList(WsFetchSalesOrg wsFetchSalesOrgInput) {
		// TODO Auto-generated method stub
		return fmSalesOrgDao.getSalesGroupWiseSalesOfficeList(wsFetchSalesOrgInput);
	}

}
