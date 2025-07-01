package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.JourneyTypeDao;
import com.softtantra.salesapp.pojo.CustomerContactDetails;
import com.softtantra.salesapp.pojo.JourneyType;
import com.softtantra.salesapp.pojo.JourneyVsExpense;

public class JourneyTypeDaoImpl implements JourneyTypeDao{

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
	public List<JourneyType> getJourneyList(int c_id, int roleId) {
		Session Dbsession = initiateSession();
		List<JourneyType> journeyList=new ArrayList<JourneyType>();
		
		try {
			String taxQuery="select journeyTypeId,journeyName,callTarget,expenseNames from journey_type where role_id ="+roleId+" and company_id="+c_id+" and status=1";
			Query query = Dbsession.createSQLQuery(taxQuery);
			
			List list = query.list();
			
				for (Object object : list) {
					JourneyType r=new JourneyType();
					Object[] obj = (Object[]) object;
					r.setJourneyTypeId((int)obj[0]);
					r.setJourneyName((String)obj[1]);
					r.setCallTarget((int)obj[2]);
					r.setExpenseNames((String)obj[3]);
					journeyList.add(r);
					
				}
				return journeyList;
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(Dbsession);
		}
		
	}

	@Override
	public boolean editJourneyType(String journeyName, int callTarget, int role_id,int c_id,String expense_config,int u_id) {
		
		Session session = initiateSession();
		Transaction tr = session.beginTransaction();
		try {
			JourneyType journeyType = new JourneyType();
			journeyType.setCompany_id(c_id);
			journeyType.setJourneyName(journeyName);
			journeyType.setCallTarget(callTarget);
			journeyType.setRole_id(role_id);
			journeyType.setStatus(1);
			journeyType.setCreated_date(new Date());
			journeyType.setUpdated_date(new Date());
			journeyType.setCreated_by(u_id);
			journeyType.setUpdated_by(u_id);
			journeyType.setExpenseNames(expense_config);
			session.save(journeyType);
			if(expense_config!=null) {
			String[] s=expense_config.split(",");
			
			for (int i = 0; i < s.length; i++) {
				JourneyVsExpense je=new JourneyVsExpense();
				String[] s1=s[i].split("-");
				je.setExpenseConfigId(Integer.parseInt(s1[0]));
				je.setJourneyTypeId(journeyType.getJourneyTypeId());
				session.save(je);
			}
			}
			
			
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(),e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean deleteJourneyType(int detailId, int c_id) {
		Session session = initiateSession();
		
		try {
			org.hibernate.Query query = session
					.createQuery("update JourneyType set status=0 where company_id=:cid and journeyTypeId=:did")
					.setParameter("cid", c_id).setParameter("did", detailId);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(),e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean updateJourneyInformation(int detailId, String journeyName, int calltarget, int c_id) {
		Session session = initiateSession();
		
		try {
			org.hibernate.Query query = session.createQuery(
					"update JourneyType set journeyName=:nm,callTarget=:em,updated_date=:des where company_id=:cid and journeyTypeId=:did")
					.setParameter("cid", c_id).setParameter("did", detailId).setParameter("nm", journeyName)
					.setParameter("em", calltarget).setParameter("des", new Date());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(),e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;

	}

	

}
