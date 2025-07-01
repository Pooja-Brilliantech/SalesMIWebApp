package com.fieldmi.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMUserActivityDao;
import com.fieldmi.service.FMUserActivityService;
import com.softtantra.salesapp.pojo.UserActivityLog;

public class FMUserActivityServiceImpl implements FMUserActivityService {

	@Autowired
	FMUserActivityDao fmUserActivityDao;

	public FMUserActivityDao getFmUserActivityDao() {
		return fmUserActivityDao;
	}

	public void setFmUserActivityDao(FMUserActivityDao fmUserActivityDao) {
		this.fmUserActivityDao = fmUserActivityDao;
	}

	@Override
	public boolean addUpdateUserActivityLog(UserActivityLog userActivityLog) {

		return fmUserActivityDao.addUpdateUserActivityLog(userActivityLog);
	}

	@Override
	public List<UserActivityLog> getUserActivityLog(int user_id, int company_id, String logMessage,
			String notificationType, Date fromDate, Date toDate) {

		return fmUserActivityDao.getUserActivityLog(user_id, company_id, logMessage, notificationType, fromDate,
				toDate);
	}

}
