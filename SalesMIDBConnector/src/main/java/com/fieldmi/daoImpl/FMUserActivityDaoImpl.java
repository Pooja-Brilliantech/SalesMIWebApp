package com.fieldmi.daoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMUserActivityDao;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.salesapp.pojo.UserActivityLog;

public class FMUserActivityDaoImpl implements FMUserActivityDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session initiateSession() {
		return sessionFactory.openSession();
	}

	private void destroySession(Session session) {
		if (session != null) {

			if (session.isDirty())
				session.flush();
			session.close();
		}
	}

	@Override
	public boolean addUpdateUserActivityLog(UserActivityLog userActivityLog) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(userActivityLog);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public List<UserActivityLog> getUserActivityLog(int user_id, int company_id, String logMessage,
			String notificationType, Date fromDate, Date toDate) {

		Session session = initiateSession();
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			CommonMethods fMCUtils = new CommonMethods();
			String partitionToFetch = fMCUtils.partitionToFetch(sdf.format(fromDate), sdf.format(toDate));

			StringBuffer query = new StringBuffer("select * from user_activity_log ").append(partitionToFetch).append(" u where u.company_id=").append(company_id);
			if (user_id != -1)
				query.append(" and u.created_by='").append(user_id).append("'");
			if (logMessage != null && logMessage.length() > 0)
				query.append(" and u.logMessage='").append(logMessage).append("'");
			if (notificationType != null && notificationType.length() > 0)
				query.append(" and u.notificationType='").append(notificationType).append("'");
			if (fromDate != null && toDate != null) {

				String fromDateStr = sdf.format(fromDate);
				String toDateStr = sdf.format(fromDate);
				query.append(" and Date(u.created_date) BETWEEN '").append(fromDateStr).append("' and '")
						.append(toDateStr).append("'");
			}

			Query sqlQuery = session.createNativeQuery(query.toString(), UserActivityLog.class);
			return sqlQuery.list();

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}
}
