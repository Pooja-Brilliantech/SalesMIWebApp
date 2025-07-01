package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.FMReasonFormDao;
import com.fieldmi.service.FMReasonFormService;
import com.softtantra.salesapp.pojo.ReasonForm;

public class FMReasonFormServiceImpl implements FMReasonFormService{

	@Autowired
	FMReasonFormDao reasonFormDao;
	
	
	public void setReasonFormDao(FMReasonFormDao reasonFormDao) {
		this.reasonFormDao = reasonFormDao;
	}

	@Override
	@Transactional
	public boolean addReason(ReasonForm reasonForm) {
		return reasonFormDao.addReason(reasonForm);
	}
	
	@Override
	@Transactional
	public boolean editReason(ReasonForm reasonForm) {
		return reasonFormDao.editReason(reasonForm);
	}

	@Override
	@Transactional
	public boolean deleteReason(ReasonForm reasonForm) {
		return reasonFormDao.deleteReason(reasonForm);
	}

	@Override
	@Transactional
	public boolean checkUniqueReason(String reason, int c_id) {
		return reasonFormDao.checkUniqueReason(reason, c_id);
	}

	@Override
	@Transactional
	public List<ReasonForm> getReasonList(String sql) {
		return reasonFormDao.getReasonList(sql);
	}

	@Override
	public List<ReasonForm> getVisitList(String sql) {
		// TODO Auto-generated method stub
		return reasonFormDao.getReasonList(sql);
	}

}
