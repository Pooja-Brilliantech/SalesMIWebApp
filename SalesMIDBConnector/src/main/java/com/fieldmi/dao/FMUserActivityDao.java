package com.fieldmi.dao;

import java.util.Date;
import java.util.List;

import com.softtantra.salesapp.pojo.UserActivityLog;

public interface FMUserActivityDao {
	
	boolean addUpdateUserActivityLog(UserActivityLog userActivityLog);

	List<UserActivityLog> getUserActivityLog(int user_id, int company_id, String logMessage, String notificationType,
			Date fromDate, Date toDate);

}
