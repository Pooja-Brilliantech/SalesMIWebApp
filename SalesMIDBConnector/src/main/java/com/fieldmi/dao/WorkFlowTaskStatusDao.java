package com.fieldmi.dao;

import java.util.List;

import com.fieldmi.service.WorkFlowAuditLogService;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public interface WorkFlowTaskStatusDao {

	public boolean addWorkFlowTask(WorkFlowTasksStatus workFlowStatus);

	public List<WorkFlowTasksStatus> getSalesOrderStatus(int c_id, int sales_master_id, String workflowName);

	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnTaskName(int c_id, int sales_master_id,
			String task_name, String workflowName);

	public List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_status, String workflowName);

	public List<WorkFlowTasksStatus> getSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName);

	public List<WorkFlowTasksStatus> getSalesDetailsStatusBasedOnTaskName(int c_id, int sales_master_id,
			int sales_details_id, String task_name, String workflowName);

	public List<WorkFlowTasksStatus> getSalesDetailsTasksBasedOnStatus(int c_id, int sales_master_id,
			int sales_details_id, String task_status, String workflowName);

	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, int sales_master_id,
			String task_name, String workflowName, int approver_type, int next_approver_id);

	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, String task_name, String workflowName,
			int approver_type, int next_approver_id);

	public String getFinalSalesOrderStatus(int c_id, int sales_master_id, String workflowName);

	public String getFinalSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName);

	public List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_name,
			String task_status, String workflowName);

	public String getFinalSalesOrderStatusForTask(int c_id, int sales_master_id, String task_name, String workflowName);

	public String getFinalSalesDetailsStatusForTask(int c_id, int sales_master_id, int sales_details_id,
			String task_name, String workflowName);

	public int roleIdFromRoleName(String roleName, int c_id);

	public String getComment(int c_id, int salesmasterId, String workflowName);

	public void setTaskToRejected(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);
	
	public void setTaskToApproved(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);

	public void updateStatusToPending(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);

	public User getUserFromUserId(int user_id);

	public void updateStatusToRecommend(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by,
			String comment, WorkFlowAuditLogService workFlowAuditLogService);

	void updateStatus(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService, String newStatus);

}
