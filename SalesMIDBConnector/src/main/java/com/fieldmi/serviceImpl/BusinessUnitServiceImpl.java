package com.fieldmi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.BusinessUnitDao;
import com.fieldmi.service.BusinessUnitService;
import com.fieldmi.stubs.WsBusinessUnitOutput;
import com.softtantra.ws.Credentials;

public class BusinessUnitServiceImpl implements BusinessUnitService {

	@Autowired
	BusinessUnitDao businessUnitDao;
	
	
	public void setBusinessUnitDao(BusinessUnitDao businessUnitDao) {
		this.businessUnitDao = businessUnitDao;
	}


	@Override
	public WsBusinessUnitOutput getBusinessUnitList(Credentials cre) throws Exception{
		// TODO Auto-generated method stub
		return businessUnitDao.getBusinessUnitList(cre);
	}

}
