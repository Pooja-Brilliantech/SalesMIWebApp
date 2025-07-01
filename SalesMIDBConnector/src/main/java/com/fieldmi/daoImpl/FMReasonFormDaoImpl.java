package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMReasonFormDao;
import com.softtantra.salesapp.pojo.ReasonForm;

public class FMReasonFormDaoImpl  implements FMReasonFormDao  {

	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session initiateSession(){
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
	@Transactional
	public boolean addReason(ReasonForm reasonForm) {
		Session session = initiateSession();

		try {
			Date date = new Date();
			reasonForm.setCreated_date(date);
			reasonForm.setUpdated_date(date);

			session.save(reasonForm);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return false;
	}
	

	@Override
	@Transactional
	public boolean editReason(ReasonForm reasonForm) {
		
		Session session =initiateSession();
		try {
			Query query = session.createQuery(
					"update ReasonForm set reason=:reason,updated_by=:updated_by,updated_date=:updated_date where  reason_id=:reason_id")
					.setParameter("reason_id", reasonForm.getReason_id())
					.setParameter("updated_by",reasonForm.getUpdated_by())
					.setParameter("updated_date",new Date())
					.setParameter("reason",reasonForm.getReason());
			
			query.executeUpdate();
			return true;
			
		}catch(Exception e) {
			
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean deleteReason(ReasonForm reasonForm) {

		Session session = initiateSession();
		try {
			
			Query query = session.createQuery(
					"update ReasonForm set status=:status,updated_by=:updated_by,updated_date=:updated_date,reason=:reason where reason_id=:reason_id")
					.setParameter("reason_id", reasonForm.getReason_id())
					.setParameter("updated_by", reasonForm.getUpdated_by())
					.setParameter("reason", reasonForm.getReason())
					.setParameter("updated_date", new Date())
					.setParameter("status", 0);
			query.executeUpdate();
			
			return true;
				
		}catch(Exception e) {
			
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean checkUniqueReason(String reason, int c_id) {
		
		Session session =initiateSession();
		
		try {
			
			Query query = session.createQuery(
					"selcet count(*) from ReasonForm where UPPER(reason)=:reason and company_id=:c_id and status=1")
					.setParameter("reason", reason.toUpperCase())
					.setParameter("c_id", c_id);
			
			long count = (Long) query.uniqueResult();
			if(count==0) {
				return false;
			}
		}catch(Exception e) {
			
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		
		return true;
	}

	@Override
	@Transactional
	public List<ReasonForm> getReasonList(String sql) {

		List<ReasonForm> reasonList = new ArrayList<>();
		
		Session session = initiateSession();
		
		try {
			
			Query query = session.createQuery(sql, ReasonForm.class);
			reasonList = query.list();
			
		}catch(Exception e){
			
			FieldMILogger.error(this.getClass().getName(),e);
			
		}finally {
			
			destroySession(session);	
		}
		
		return reasonList;
	}

}
