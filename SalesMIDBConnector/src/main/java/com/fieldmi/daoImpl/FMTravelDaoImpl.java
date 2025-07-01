package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMTravelDao;
import com.softtantra.salesapp.pojo.JourneyType;
import com.softtantra.salesapp.pojo.ModeOfTravel;

public class FMTravelDaoImpl implements FMTravelDao{

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
	public boolean saveModeOfTravel(ModeOfTravel travel, int c_id, int u_id) {
		Session session = initiateSession();
		try {
			travel.setStatus(1);
			travel.setCompany_id(c_id);
			travel.setUser_id(u_id);
			travel.setCreated_date(new Date());
			travel.setUpdated_date(new Date());
			session.save(travel);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(),e);
			return false;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<ModeOfTravel> getModeOfTravelList(int c_id, int roleId) {
		Session Dbsession = initiateSession();
		List<ModeOfTravel> tarvelList=new ArrayList<ModeOfTravel>();
		
		try {
			String tQuery="select modeOfTravel,modeOfTravelRate,modeOfTravelId from ModeOfTravel where  company_id="+c_id+" and status=1";
			Query query = Dbsession.createQuery(tQuery);
			
			List list = query.list();
			
				for (Object object : list) {
					ModeOfTravel r=new ModeOfTravel();
					Object[] obj = (Object[]) object;
					r.setModeOfTravel((String)obj[0]);
					r.setModeOfTravelRate((double)obj[1]);
					r.setModeOfTravelId((int)obj[2]);
					
					tarvelList.add(r);
					
				}
				return tarvelList;
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(Dbsession);
		}
		
	}

	@Override
	public boolean checkModeOfTravelConfiguration(String modeOfTravel, double modeOfTravelRate, int role_id, int c_id) {
		Session session = initiateSession();
		// int c_id=CommonController.getCompanyId();
		try {
			Query query = session.createQuery(
					"select count(*) from ModeOfTravel where  modeOfTravel=:modeOfTravel and  modeOfTravelRate=:modeOfTravelRate  and company_id=:id and status=1")
					
					.setParameter("id", role_id)
					.setParameter("modeOfTravel", modeOfTravel)
					.setParameter("modeOfTravelRate", modeOfTravelRate);

			long count = (Long) query.uniqueResult();
			
			if (count == 0) {
				return false;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean updateModeOfTravelConfiguration(ModeOfTravel travel, int c_id, int u_id) {Session session = initiateSession();
	try {

		org.hibernate.Query query = session.createQuery("update ModeOfTravel"
				+ " set modeOfTravel=:modeOfTravel,modeOfTravelRate=:modeOfTravelRate where modeOfTravelId=:modeOfTravelId")
				.setParameter("modeOfTravel", travel.getModeOfTravel())
				.setParameter("modeOfTravelRate",travel.getModeOfTravelRate())
				.setParameter("modeOfTravelId", travel.getModeOfTravelId());

		int val = query.executeUpdate();
		if (val == 0) {
			return false;
		}
	} catch (Exception e) {
		FieldMILogger.error(this.getClass().getName(),e);
		return false;
	} finally {
		destroySession(session);
	}
	return true;}

	@Override
	public boolean deleteModeOfTravel(ModeOfTravel travel) {Session session = initiateSession();
	try {

		org.hibernate.Query query = session.createQuery("update ModeOfTravel"
				+ " set status=:status where modeOfTravelId=:modeOfTravelId")
				.setParameter("status", 0)
				.setParameter("modeOfTravelId", travel.getModeOfTravelId());

		int val = query.executeUpdate();
		if (val == 0) {
			return false;
		}
	} catch (Exception e) {
		FieldMILogger.error(this.getClass().getName(),e);
		return false;
	} finally {
		destroySession(session);
	}
	return true;}

}
