package com.fieldmi.service;

import java.util.Date;
import java.util.List;

import com.softtantra.salesapp.pojo.UserActivityLog;

public interface FMUserActivityService {

	boolean addUpdateUserActivityLog(UserActivityLog userActivityLog);

	List<UserActivityLog> getUserActivityLog(int user_id, int company_id, String logMessage, String notificationType,
			Date fromDate, Date toDate);

}
