package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.WorkFlowAuditLog;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public interface WorkFlowAuditLogDao {

	public boolean addWorkFlowAuditLog(WorkFlowAuditLog workFlowAuditLog);

	public List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id);

	public List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id, int iNITIAL, int rECORD_SIZE);

	public int getTotalRecordCount(int c_id, int workflow_task_id);

	void addWorkFlowLog(List<WorkFlowTasksStatus> workFlowTasksStatus, int updated_by);

	public void addWorkFlowLog(WorkFlowTasksStatus workFlowTasksStatus, int updated_by);	
}
