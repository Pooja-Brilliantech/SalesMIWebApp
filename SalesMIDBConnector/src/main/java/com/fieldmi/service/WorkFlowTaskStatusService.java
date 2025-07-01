package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public interface WorkFlowTaskStatusService {

	boolean addWorkFlowTask(WorkFlowTasksStatus workFlowStatus);

	List<WorkFlowTasksStatus> getSalesOrderStatus(int c_id, int sales_master_id, String workflowName);

	List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnTaskName(int c_id, int sales_master_id, String task_name, String workflowName);

	List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, int sales_master_id, String task_name, String workflowName, 
			int approver_type, int next_approver_id);

	List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, String task_name, String workflowName, int approver_type,
			int next_approver_id);

	List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_status, String workflowName);

	List<WorkFlowTasksStatus> getSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName);

	List<WorkFlowTasksStatus> getSalesDetailsStatusBasedOnTaskName(int c_id, int sales_master_id, int sales_details_id,
			String task_name, String workflowName);

	List<WorkFlowTasksStatus> getSalesDetailsTasksBasedOnStatus(int c_id, int sales_master_id, int sales_details_id,
			String task_status, String workflowName);

	String getFinalSalesOrderStatus(int c_id, int sales_master_id, String workflowName);

	String getFinalSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName);

	List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_name,
			String task_status, String workflowName);

	String getFinalSalesOrderStatusForTask(int c_id, int sales_master_id, String task_name, String workflowName);

	String getFinalSalesDetailsStatusForTask(int c_id, int sales_master_id, int sales_details_id, String task_name, String workflowName);

	int roleIdFromRoleName(String roleName, int c_id);
	
	User getUserFromUserId( int user_id );

	String getComment(int c_id, int salesmasterId, String workflowName);

	void setTaskToRejected(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);
	
	void setTaskToApproved(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);
	
	void updateStatusToPending(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);
	
	void updateStatusToRecommend(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService);

	void updateStatus(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService, String newStatus);

}
