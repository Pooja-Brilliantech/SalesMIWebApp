package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.WorkFlowAuditLogDao;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.softtantra.salesapp.pojo.WorkFlowAuditLog;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public class WorkFlowAuditLogServiceImpl implements WorkFlowAuditLogService {

	@Autowired
	WorkFlowAuditLogDao workFlowAuditLogDao;

	public void setWorkFlowAuditLogDao(WorkFlowAuditLogDao workFlowAuditLogDao) {
		this.workFlowAuditLogDao = workFlowAuditLogDao;
	}

	@Override
	@Transactional
	public boolean addWorkFlowAuditLog(WorkFlowAuditLog workFlowAuditLog) {

		return workFlowAuditLogDao.addWorkFlowAuditLog(workFlowAuditLog);
	}

	@Override
	@Transactional
	public List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id) {

		return workFlowAuditLogDao.getWorkFlowLogs(c_id, workflow_task_id);
	}

	@Override
	@Transactional
	public List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id, int iNITIAL, int rECORD_SIZE) {

		return workFlowAuditLogDao.getWorkFlowLogs(c_id, workflow_task_id, iNITIAL, rECORD_SIZE);
	}

	@Override
	@Transactional
	public int getTotalRecordCount(int c_id, int workflow_task_id) {

		return workFlowAuditLogDao.getTotalRecordCount(c_id, workflow_task_id);
	}

	@Override
	@Transactional
	public void addWorkFlowLog(List<WorkFlowTasksStatus> workFlowTasksStatus, int updated_by) {

		workFlowAuditLogDao.addWorkFlowLog(workFlowTasksStatus, updated_by);
	}

	@Override
	@Transactional
	public void addWorkFlowLog(WorkFlowTasksStatus workFlowTasksStatus, int updated_by) {

		workFlowAuditLogDao.addWorkFlowLog(workFlowTasksStatus, updated_by);
	}

}
