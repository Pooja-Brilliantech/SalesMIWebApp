package com.fieldmi.service;

import java.util.List;

/*import com.softtantra.salesapp.pojo.ReasonForm;*/
import com.softtantra.salesapp.pojo.VisitForm;

public interface FMVisitReasonService {
	
	public boolean addVisitReason (VisitForm visitForm);
	
	public boolean editVisitReason(VisitForm visitForm);
	
	public boolean deleteVisitReason(VisitForm visitForm);
	
	public boolean checkUniqueReason(String reason, int c_id);
	
	public List<VisitForm> getVisitReasonList(String sql);
	
	List<VisitForm> getVisitList(String sql);

}
