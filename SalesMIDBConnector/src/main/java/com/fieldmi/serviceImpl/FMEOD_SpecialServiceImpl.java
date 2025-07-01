package com.fieldmi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMEOD_SpecialDao;
import com.fieldmi.service.FMEOD_SpecialService;
import com.softtantra.ws.WsEOD_Report;

public class FMEOD_SpecialServiceImpl implements FMEOD_SpecialService {
	
	@Autowired
	FMEOD_SpecialDao fmEOD_SpecialDao;

	public void setFmEOD_SpecialDao(FMEOD_SpecialDao fmEOD_SpecialDao) {
		this.fmEOD_SpecialDao = fmEOD_SpecialDao;
	}

	@Override
	public int addEOD_Special_Report(WsEOD_Report eodReport, int user_id) {
		// TODO Auto-generated method stub
		return fmEOD_SpecialDao.addEOD_Special_Report(eodReport,user_id);
	}
	

}
