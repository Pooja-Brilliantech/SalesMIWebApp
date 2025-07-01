package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.JourneyTypeDao;
import com.fieldmi.service.JourneyTypeService;
import com.softtantra.salesapp.pojo.JourneyType;

public class JourneyTypeServiceImpl implements JourneyTypeService{

	
	@Autowired
	JourneyTypeDao journeyTypeDao;
	
	
	
	public void setJourneyTypeDao(JourneyTypeDao journeyTypeDao) {
		this.journeyTypeDao = journeyTypeDao;
	}



	@Override
	public List<JourneyType> getJourneyList(int c_id, int roleId) {
		// TODO Auto-generated method stub
		return journeyTypeDao.getJourneyList(c_id,roleId);
	}



	@Override
	public boolean editJourneyType(String journeyName, int callTarget, int role_id,int c_id,String expense_config,int u_id) {
		// TODO Auto-generated method stub
		return journeyTypeDao.editJourneyType(journeyName,callTarget,role_id,c_id,expense_config,u_id);
	}



	@Override
	public boolean deleteJourneyType(int detailId, int c_id) {
		// TODO Auto-generated method stub
		return journeyTypeDao.deleteJourneyType(detailId,c_id);
	}



	@Override
	public boolean updateJourneyInformation(int detailId, String journeyName, int calltarget, int c_id) {
		// TODO Auto-generated method stub
		return journeyTypeDao.updateJourneyInformation(detailId,journeyName,calltarget,c_id);
	}

}
