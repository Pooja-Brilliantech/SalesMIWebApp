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
import com.fieldmi.dao.FMVisitReasonDao;
import com.softtantra.salesapp.pojo.VisitForm;

public class FMVisitReasonDaoImpl implements FMVisitReasonDao  {

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
	public boolean addVisitReason(VisitForm visitForm) {
		Session session = initiateSession();

		try {
			Date date = new Date();
			visitForm.setCreated_date(date);
			visitForm.setUpdated_date(date);

			session.save(visitForm);
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
	public boolean editVisitReason(VisitForm visitForm) {
		
		Session session =initiateSession();
		try {
			Query query = session.createQuery(
					"update VisitForm set visitreason=:visitreason,updated_by=:updated_by,updated_date=:updated_date where  visit_reason_id=:visit_reason_id")
					.setParameter("visit_reason_id", visitForm.getVisit_reason_id())
					.setParameter("updated_by",visitForm.getUpdated_by())
					.setParameter("updated_date",new Date())
					.setParameter("visitreason",visitForm.getVisitreason());
			
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
	public boolean deleteVisitReason(VisitForm visitForm) {

		Session session = initiateSession();
		try {
			
			Query query = session.createQuery(
					"update VisitForm set status=:status,updated_by=:updated_by,updated_date=:updated_date,visitreason=:visitreason where visit_reason_id=:visit_reason_id")
					.setParameter("visit_reason_id", visitForm.getVisit_reason_id())
					.setParameter("updated_by", visitForm.getUpdated_by())
					.setParameter("visitreason", visitForm.getVisitreason())
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
	public boolean checkUniqueVisitReason(String visitreason, int c_id) {
		
		Session session =initiateSession();
		
		try {
			
			Query query = session.createQuery(
					"selcet count(*) from VisitForm where UPPER(visitreason)=:visitreason and company_id=:c_id and status=1")
					.setParameter("visitreason", visitreason.toUpperCase())
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

	public List<VisitForm> getVisitList(String sql) {

		List<VisitForm> visitReasonList = new ArrayList<>();
		
		Session session = initiateSession();
		
		try {
			
			Query query = session.createQuery(sql, VisitForm.class);
			visitReasonList = query.list();
			
		}catch(Exception e){
			
			FieldMILogger.error(this.getClass().getName(),e);
			
		}finally {
			
			destroySession(session);	
		}
		
		return visitReasonList;
	}

}
