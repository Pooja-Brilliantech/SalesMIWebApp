package com.fieldmi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMTaskDao;
import com.fieldmi.service.FMTaskService;
import com.fieldmi.stubs.WSTaskDetails;
import com.fieldmi.utils.AjaxResponse;


public class FMTaskServiceImpl implements FMTaskService {

	@Autowired
	FMTaskDao fmTaskDao;
	
	public void setFmTaskDao(FMTaskDao fmTaskDao) {
		this.fmTaskDao = fmTaskDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AjaxResponse updateTaskStatus(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		return fmTaskDao.updateTaskStatus(wsTaskDetails);
	}

	@Override
	public AjaxResponse getTaskListUserAndStatusWise(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		return fmTaskDao.getTaskListUserAndStatusWise(wsTaskDetails);
	}

	@Override
	public AjaxResponse uploadFiles(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		return fmTaskDao.uploadFiles(wsTaskDetails);
	}

	@Override
	public AjaxResponse getSubTaskList(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		return fmTaskDao.getSubTaskList(wsTaskDetails);
	}

	@Override
	public AjaxResponse getSubTaskList1(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		return fmTaskDao.getSubTaskList1(wsTaskDetails);
	}

	@Override
	public AjaxResponse getTaskImageLinks(WSTaskDetails wsTaskDetails) {
		// TODO Auto-generated method stub
		return fmTaskDao.getTaskImageLinks(wsTaskDetails);
	}
	
	

}
