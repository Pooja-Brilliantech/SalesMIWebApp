package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.WorkFlowAuditLog;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public interface WorkFlowAuditLogService {

	boolean addWorkFlowAuditLog(WorkFlowAuditLog workFlowAuditLog);

	List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id);

	List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id, int iNITIAL, int rECORD_SIZE);

	int getTotalRecordCount(int c_id, int workflow_task_id);

	void addWorkFlowLog(List<WorkFlowTasksStatus> workFlowTasksStatus, int updated_by);

	void addWorkFlowLog(WorkFlowTasksStatus workFlowTasksStatus, int updated_by);
}
