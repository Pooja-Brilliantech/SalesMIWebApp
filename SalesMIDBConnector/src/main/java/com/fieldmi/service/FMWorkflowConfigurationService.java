package com.fieldmi.service;



import java.util.List;

import com.softtantra.salesapp.pojo.WorkFlowConfiguration;

public interface FMWorkflowConfigurationService {

	public boolean addNewWorkflow(WorkFlowConfiguration workFlowConfiguration, String data);
	
	public boolean updateWorkflowInfo(WorkFlowConfiguration workFlowConfiguration);
	
	public boolean deleteWorkflow(WorkFlowConfiguration workFlowConfiguration);	
	
	public List<WorkFlowConfiguration> getWorkflowConfigs(String workflow_name, String workflow_task_name, int company_id,String objectName);

	WorkFlowConfiguration checkWorkFlowConfigure(String workflow_type, String input_object_name, int c_id);
	
}
