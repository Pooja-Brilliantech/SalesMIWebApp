package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.FMVisitReasonDao;
import com.fieldmi.service.FMVisitReasonService;
import com.softtantra.salesapp.pojo.VisitForm;

public class FMVisitReasonServiceImpl implements FMVisitReasonService{
	
	@Autowired
    FMVisitReasonDao visitReasonDao;

	
	public void setVisitReasonDao(FMVisitReasonDao visitReasonDao) {
		this.visitReasonDao = visitReasonDao;
	}

	@Override
	@Transactional
	public boolean addVisitReason(VisitForm visitForm) {
		return visitReasonDao.addVisitReason(visitForm);
	}

	@Override
	@Transactional
	public boolean editVisitReason(VisitForm visitForm) {
		return visitReasonDao.editVisitReason(visitForm);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public boolean deleteVisitReason(VisitForm visitForm) { return
	 * visitReasonDao.deleteVisitReason(visitForm); }
	 */
	@Override
	@Transactional
	public boolean deleteVisitReason(VisitForm visitForm) {
		return visitReasonDao.deleteVisitReason(visitForm);
	}

	@Override
	@Transactional
	public boolean checkUniqueReason(String reason, int c_id) {
		return visitReasonDao.checkUniqueVisitReason(reason, c_id);
	}

	@Override
	@Transactional
	public List<VisitForm> getVisitList(String sql) {
		return visitReasonDao.getVisitList(sql);
	}

	
	  @Override public List<VisitForm> getVisitReasonList(String sql) { return
	  visitReasonDao.getVisitList(sql); }
	 

}
