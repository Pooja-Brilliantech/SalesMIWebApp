package com.fieldmi.dao;
import com.fieldmi.stubs.WSTaskDetails;
import com.fieldmi.utils.AjaxResponse;

@SuppressWarnings("rawtypes")
public interface FMTaskDao {



	AjaxResponse updateTaskStatus(WSTaskDetails wsTaskDetails);

	AjaxResponse getTaskListUserAndStatusWise(WSTaskDetails wsTaskDetails);

	AjaxResponse uploadFiles(WSTaskDetails wsTaskDetails);

	AjaxResponse getSubTaskList(WSTaskDetails wsTaskDetails);
	
	AjaxResponse getSubTaskList1(WSTaskDetails wsTaskDetails);

	AjaxResponse getTaskImageLinks(WSTaskDetails wsTaskDetails);

}
