package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.TargetDetails;
import com.softtantra.salesapp.pojo.TargetImportRecords;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasCategory;

public interface FMTargetService {
	
	public boolean saveTargets(TargetDetails targetDetails, int c_id, int u_id);
	
	public boolean saveUsersCategory(UserHasCategory userHasCategory, int c_id, int u_id);
	
	public boolean deleteTarget(int user_id, int c_id);
	
	public boolean checkExist(TargetDetails targetDetails, int c_id);
	
	public List<TargetDetails> getTargetDetails(int c_id,int u_id);

	public List<TargetImportRecords> getCalculatedRecords(TargetImportRecords targetImportRecords,List<User> userList, List<String> monthNamesYears, int c_id,
			int u_id,List<String> monthNames);

	
	
}
