package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.ReasonForm;

public interface FMReasonFormService {
	
	boolean addReason (ReasonForm reasonForm);
	
	boolean editReason(ReasonForm reasonForm);
	
	boolean deleteReason(ReasonForm reasonForm);
	
	public boolean checkUniqueReason(String reason, int c_id);
	
	public List<ReasonForm> getReasonList(String sql);

	List<ReasonForm> getVisitList(String sql);


}
