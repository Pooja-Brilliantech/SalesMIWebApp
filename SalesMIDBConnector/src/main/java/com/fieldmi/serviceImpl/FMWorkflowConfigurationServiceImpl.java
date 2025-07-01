package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.FMWorkflowConfigurationDao;
import com.fieldmi.service.FMWorkflowConfigurationService;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;

public class FMWorkflowConfigurationServiceImpl implements FMWorkflowConfigurationService {

	
	@Autowired
	FMWorkflowConfigurationDao fmWorkflowConfigurationDao;
	
	
	public FMWorkflowConfigurationDao getFmWorkflowConfigurationDao() {
		return fmWorkflowConfigurationDao;
	}

	public void setFmWorkflowConfigurationDao(FMWorkflowConfigurationDao fmWorkflowConfigurationDao) {
		this.fmWorkflowConfigurationDao = fmWorkflowConfigurationDao;
	}

	@Override
	public boolean addNewWorkflow(WorkFlowConfiguration workFlowConfiguration, String data) {
		
		return fmWorkflowConfigurationDao.addNewWorkflow(workFlowConfiguration, data);
	}

	@Override
	public boolean updateWorkflowInfo(WorkFlowConfiguration workFlowConfiguration) {
		
		return fmWorkflowConfigurationDao.updateWorkflowInfo(workFlowConfiguration);
	}

	@Override
	public boolean deleteWorkflow(WorkFlowConfiguration workFlowConfiguration) {
		
		return fmWorkflowConfigurationDao.deleteWorkflow(workFlowConfiguration);
	}

	@Override
	public List<WorkFlowConfiguration> getWorkflowConfigs(String workflow_name, String workflow_task_name, int company_id,String objectName) {
		// TODO Auto-generated method stub
		return fmWorkflowConfigurationDao.getWorkflowConfigs(workflow_name, workflow_task_name, company_id,objectName);
	}
	
	@Override
	@Transactional
	public WorkFlowConfiguration checkWorkFlowConfigure(String workflow_type, String input_object_name, int c_id) {
		
		return fmWorkflowConfigurationDao.checkWorkFlowConfigure(workflow_type, input_object_name, c_id);
	}

}
