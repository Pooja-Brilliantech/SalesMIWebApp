package com.fieldmi.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fieldmi.FieldMILogger;

import com.softtantra.salesapp.pojo.VisitForm;

public interface FMVisitReasonDao {

	public boolean addVisitReason(VisitForm visitForm);

	public boolean editVisitReason(VisitForm visitForm);

	public boolean deleteVisitReason(VisitForm visitForm);

	public boolean checkUniqueVisitReason(String reason, int c_id);

	public List<VisitForm> getVisitList(String sql);

}
