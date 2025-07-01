package com.fieldmi.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMEOD_SpecialDao;
import com.softtantra.salesapp.pojo.EOD_SpecialReport;
import com.softtantra.ws.WsEOD_Report;
import com.softtantra.ws.WsEOD_Special_Report;

public class FMEOD_SpecialDaoImpl implements FMEOD_SpecialDao {

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
	public int addEOD_Special_Report(WsEOD_Report eodReport, int user_id) {
		Session session = initiateSession();
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	
			int special_report_id = 0;

			List<WsEOD_Special_Report> eod_special_report_list = new ArrayList<WsEOD_Special_Report>();
			eod_special_report_list = eodReport.getAddEodSpecialReport();

			for (WsEOD_Special_Report list : eod_special_report_list) {

				EOD_SpecialReport eodDetails = new EOD_SpecialReport();
				eodDetails.setCreated_by(user_id);
				eodDetails.setUpdated_by(user_id);
				eodDetails.setStatus(1);
				
				Date creationDate = null;
				if( list.getCreated_at() != null )
					creationDate = sdf.parse(list.getCreated_at());
				else if( list.getCreated_date() != null )
					creationDate = sdf.parse(list.getCreated_date());
				else {
					
					Calendar cal = Calendar.getInstance();
					cal.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
					creationDate = cal.getTime();
				}
				eodDetails.setCreated_date(creationDate);
				eodDetails.setUpdate_date(creationDate);
				eodDetails.setUser_id(user_id);
				if( list.getRemark() != null && list.getRemark().trim().length() > 255 ) {
					
					eodDetails.setRemark(list.getRemark().substring(0, 250));
				} else
					eodDetails.setRemark(list.getRemark());
				eodDetails.setLogin_forthe_day(list.getLogin_forthe_day());
				eodDetails.setDisbursed_forthe_day(list.getDisbursed_forthe_day());
				eodDetails.setClosing_forthe_day(list.getClosing_forthe_day());
				eodDetails.setNon_starter_solved(list.getNon_starter_solved());
				eodDetails.setPpd_clearance(list.getPpd_clearance());
				eodDetails.setDeactive_NACH_clearance(list.getDeactive_NACH_clearance());
				eodDetails.setCompany_id(Integer.parseInt(eodReport.getCompany_id()));

				special_report_id = (int) session.save(eodDetails);
			}
			if (special_report_id > 0)
				return special_report_id;
			else
				return 0;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return 0;
	}

}
