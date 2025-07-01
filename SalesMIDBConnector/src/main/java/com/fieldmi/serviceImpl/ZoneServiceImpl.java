package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.ZoneDao;
import com.fieldmi.service.ZoneService;
import com.fieldmi.stubs.WSZoneListOutput;
import com.fieldmi.stubs.WsDistrictDetailsOutput;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.ZoneWiseStates;
import com.softtantra.ws.Credentials;

public class ZoneServiceImpl implements ZoneService{

	@Autowired 
	ZoneDao zoneDao;
	
	
	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}


	@Override
	public WSZoneListOutput getZoneList(Credentials cre) throws Exception{
		// TODO Auto-generated method stub
		return zoneDao.getZoneList(cre);
	}


	@Override
	public boolean deleteZoneState(int id) {
		// TODO Auto-generated method stub
		return zoneDao.deleteZoneState(id);
	}


	@Override
	public List<ZoneWiseStates> getZoneStateData(int c_id) {
		// TODO Auto-generated method stub
		return zoneDao.getZoneStateData(c_id);
	}


	@Override
	public List<States> getStates(int countryId, String statesIds) {
		// TODO Auto-generated method stub
		return zoneDao.getStates(countryId,statesIds);
	}


	@Override
	public List<ZoneWiseStates> getZoneWiseStateData(int intValue) {
		// TODO Auto-generated method stub
		return zoneDao.getZoneWiseStateData(intValue);
	}


	@Override
	public WsDistrictDetailsOutput getStateDistrictList(Credentials cre) {
		// TODO Auto-generated method stub
		return zoneDao.getStateDistrictList(cre);
	}

}
