package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.WorkFlowTaskStatusDao;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public class WorkFlowTaskStatusServiceImpl implements WorkFlowTaskStatusService {

	@Autowired
	WorkFlowTaskStatusDao workFlowTaskStatusDao;

	public void setWorkFlowTaskStatusDao(WorkFlowTaskStatusDao workFlowTaskStatusDao) {
		this.workFlowTaskStatusDao = workFlowTaskStatusDao;
	}

	@Override
	@Transactional
	public boolean addWorkFlowTask(WorkFlowTasksStatus workFlowStatus) {

		return workFlowTaskStatusDao.addWorkFlowTask(workFlowStatus);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatus(int c_id, int sales_master_id, String workflowName ) {

		return workFlowTaskStatusDao.getSalesOrderStatus(c_id, sales_master_id, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnTaskName(int c_id, int sales_master_id,
			String task_name,String workflowName) {

		return workFlowTaskStatusDao.getSalesOrderStatusBasedOnTaskName(c_id, sales_master_id, task_name, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id,
			String task_status, String workflowName) {

		return workFlowTaskStatusDao.getSalesOrderTasksBasedOnStatus(c_id, sales_master_id, task_status, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName) {

		return workFlowTaskStatusDao.getSalesDetailsStatus(c_id, sales_master_id, sales_details_id, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesDetailsStatusBasedOnTaskName(int c_id, int sales_master_id,
			int sales_details_id, String task_name, String workflowName) {

		return workFlowTaskStatusDao.getSalesDetailsStatusBasedOnTaskName(c_id, sales_master_id, sales_details_id,
				task_name, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesDetailsTasksBasedOnStatus(int c_id, int sales_master_id,
			int sales_details_id, String task_status, String workflowName) {

		return workFlowTaskStatusDao.getSalesDetailsTasksBasedOnStatus(c_id, sales_master_id, sales_details_id,
				task_status, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, int sales_master_id,
			String task_name, String workflowName, int approver_type, int next_approver_id) {

		return workFlowTaskStatusDao.getSalesOrderStatusBasedOnNextApproverId(c_id, sales_master_id, task_name, workflowName,
				approver_type, next_approver_id);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, String task_name, String workflowName,
			int approver_type, int next_approver_id) {

		return workFlowTaskStatusDao.getSalesOrderStatusBasedOnNextApproverId(c_id, task_name, workflowName, approver_type,
				next_approver_id);
	}

	@Override
	@Transactional
	public String getFinalSalesOrderStatus(int c_id, int sales_master_id, String workflowName) {

		return workFlowTaskStatusDao.getFinalSalesOrderStatus(c_id, sales_master_id, workflowName);
	}

	@Override
	@Transactional
	public String getFinalSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName) {

		return workFlowTaskStatusDao.getFinalSalesDetailsStatus(c_id, sales_master_id, sales_details_id, workflowName);
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_name,
			String task_status, String workflowName) {

		return workFlowTaskStatusDao.getSalesOrderTasksBasedOnStatus(c_id, sales_master_id, task_name, task_status, workflowName);
	}

	@Override
	@Transactional
	public String getFinalSalesOrderStatusForTask(int c_id, int sales_master_id, String task_name, String workflowName) {

		return workFlowTaskStatusDao.getFinalSalesOrderStatusForTask(c_id, sales_master_id, task_name, workflowName);
	}

	@Override
	@Transactional
	public String getFinalSalesDetailsStatusForTask(int c_id, int sales_master_id, int sales_details_id,
			String task_name, String workflowName) {

		return workFlowTaskStatusDao.getFinalSalesDetailsStatusForTask(c_id, sales_master_id, sales_details_id,
				task_name, workflowName);
	}

	@Override
	@Transactional
	public int roleIdFromRoleName(String roleName, int c_id) {

		return workFlowTaskStatusDao.roleIdFromRoleName(roleName, c_id);
	}

	@Override
	@Transactional
	public String getComment(int c_id, int salesmasterId, String workflowName) {

		return workFlowTaskStatusDao.getComment(c_id, salesmasterId, workflowName);
	}

	@Override
	public void setTaskToRejected(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService) {

		workFlowTaskStatusDao.setTaskToRejected(workTaskStatusDetailList, updated_by, comment, workFlowAuditLogService);
	}
	
	@Override
	public void setTaskToApproved(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService) {

		workFlowTaskStatusDao.setTaskToApproved(workTaskStatusDetailList, updated_by, comment, workFlowAuditLogService);
	}

	@Override
	public void updateStatusToPending(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by,
			String comment, WorkFlowAuditLogService workFlowAuditLogService) {
		
		workFlowTaskStatusDao.updateStatusToPending(workTaskStatusDetailList, updated_by, comment, workFlowAuditLogService);
	}

	@Override
	public User getUserFromUserId(int user_id) {
		
		return workFlowTaskStatusDao.getUserFromUserId(user_id);
	}

	@Override
	public void updateStatusToRecommend(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by,
			String comment, WorkFlowAuditLogService workFlowAuditLogService) {
		
		workFlowTaskStatusDao.updateStatusToRecommend(workTaskStatusDetailList, updated_by, comment, workFlowAuditLogService);
		
	}
	
	@Override
	public void updateStatus(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by,
			String comment, WorkFlowAuditLogService workFlowAuditLogService, String newStatus) {
		
		workFlowTaskStatusDao.updateStatus(workTaskStatusDetailList, updated_by, comment, workFlowAuditLogService, newStatus);
		
	}

}
