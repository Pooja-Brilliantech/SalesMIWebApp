package com.fieldmi.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMInvoiceDao;
import com.softtantra.salesapp.pojo.InvoiceDetails;
import com.softtantra.salesapp.pojo.InvoiceMaster;

public class FMInvoiceDaoImpl implements FMInvoiceDao {

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
	public List<InvoiceMaster> checkIfInvoiceExist(String invoiceNumber) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query;
			query = session.createQuery("from InvoiceMaster where invoice_number=:id").setParameter("id",
					invoiceNumber);

			if (query.list().size() != 0) {
				return query.list();
			} else {
				return null;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<InvoiceMaster> checkCustomerExist(int custmerId, int c_id, double totalAmount) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query;
			query = session
					.createQuery("from InvoiceMaster where customer_id=:customer_id"
							+ " and company_id=:id and total_amount=:total_amount")
					.setParameter("id", c_id).setParameter("customer_id", custmerId)
					.setParameter("total_amount", totalAmount);

			if (query.list().size() != 0) {
				return query.list();
			} else {
				return null;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean saveInvoiceDetails(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {

			session.save(invoiceDetails);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

}
