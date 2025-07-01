package com.fieldmi.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fieldmi.FieldMILogger;

import com.softtantra.salesapp.pojo.ReasonForm;

public interface FMReasonFormDao {

	public boolean addReason (ReasonForm reasonForm);
	
	public boolean editReason(ReasonForm reasonForm);
	
	public	boolean deleteReason(ReasonForm reasonForm);
		
	public boolean checkUniqueReason(String reason, int c_id);
	
	public List<ReasonForm> getReasonList(String sql);
	
}


