package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.TargetDetails;
import com.softtantra.salesapp.pojo.TargetImportRecords;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasCategory;

public interface FMTargetDao {

	boolean saveTargets(TargetDetails targetDetails, int c_id, int u_id);

	boolean saveUsersCategory(UserHasCategory userHasCategory, int c_id, int u_id);

	boolean deleteTarget(int user_id, int c_id);

	boolean checkExist(TargetDetails targetDetails, int c_id);

	List<TargetDetails> getTargetDetails(int c_id, int u_id);

	List<TargetImportRecords> getCalculatedRecords(TargetImportRecords targetImportRecords,List<User> userList, List<String> monthNamesYears, int c_id, int u_id,List<String> monthNames);
}
