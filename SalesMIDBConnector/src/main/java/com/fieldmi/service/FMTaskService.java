package com.fieldmi.service;

import com.fieldmi.stubs.WSTaskDetails;
import com.fieldmi.utils.AjaxResponse;


@SuppressWarnings("rawtypes")
public interface FMTaskService {
	
	public AjaxResponse updateTaskStatus( WSTaskDetails wsTaskDetails);
	
	public AjaxResponse getTaskListUserAndStatusWise( WSTaskDetails wsTaskDetails);
	
	public AjaxResponse uploadFiles( WSTaskDetails wsTaskDetails);
	
	public AjaxResponse getSubTaskList( WSTaskDetails wsTaskDetails);
	
	public AjaxResponse getSubTaskList1( WSTaskDetails wsTaskDetails);
	
	public AjaxResponse getTaskImageLinks(WSTaskDetails wsTaskDetails);

}
