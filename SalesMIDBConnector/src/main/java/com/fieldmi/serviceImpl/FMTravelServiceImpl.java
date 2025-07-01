package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMTravelDao;
import com.fieldmi.service.FMTravelService;
import com.softtantra.salesapp.pojo.ModeOfTravel;

public class FMTravelServiceImpl implements FMTravelService{

	@Autowired
	FMTravelDao fmTravelDao;
	
	
	public void setFmTravelDao(FMTravelDao fmTravelDao) {
		this.fmTravelDao = fmTravelDao;
	}


	@Override
	public boolean saveModeOfTravel(ModeOfTravel travel, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmTravelDao.saveModeOfTravel(travel,c_id,u_id);
	}


	@Override
	public List<ModeOfTravel> getModeOfTravelList(int c_id, int roleId) {
		// TODO Auto-generated method stub
		return fmTravelDao.getModeOfTravelList(c_id,roleId);
	}


	@Override
	public boolean checkModeOfTravelConfiguration(String modeOfTravel, double modeOfTravelRate, int role_id, int c_id) {
		// TODO Auto-generated method stub
		return fmTravelDao.checkModeOfTravelConfiguration(modeOfTravel, modeOfTravelRate, role_id,c_id);
	}


	@Override
	public boolean updateModeOfTravelConfiguration(ModeOfTravel travel, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmTravelDao.updateModeOfTravelConfiguration(travel,c_id,u_id);
	}


	@Override
	public boolean deleteModeOfTravel(ModeOfTravel travel) {
		// TODO Auto-generated method stub
		return fmTravelDao.deleteModeOfTravel(travel);
	}

	
}
