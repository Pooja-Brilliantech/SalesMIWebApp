package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.DocumentUploadDao;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.S3Operations;
import com.softtantra.salesapp.pojo.CustomerRequiredDocuments;
import com.softtantra.salesapp.pojo.Documents;
import com.softtantra.salesapp.pojo.RenewalMaster;

public class DocumentUploadDaoImpl implements DocumentUploadDao{

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
	public int saveDocument(Documents documents) {

		Session session = initiateSession();
		try {

			session.save(documents);
			if(documents.getDocumentsId()>0) {
				return documents.getDocumentsId();
			}else {
				return 0;
			}
			
			
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return 0;
		} finally {
			destroySession(session);
		}
		
	}
	
	@Override
	public boolean deleteDocument(Documents documents) {

		Session session = initiateSession();
		try {

			session.delete(documents);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		
		return true;
	}

	@Override
	public boolean updateDocumentUrl(int documentId, String objectKey) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery("update Documents set documentUrl=:url where documentsId=:id")
					.setParameter("url", objectKey).setParameter("id", documentId);
			query.executeUpdate();
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
	public boolean checkDocument_Name(String documentName, int c_id) {
		Session session = initiateSession();
		
		try {
			Query query = session
					.createQuery("select count(*) from Documents where documentName=:mn and status=1 and company_id=:cid")
					.setParameter("mn", documentName).setParameter("cid", c_id);
			// return query.list();
			long count = (Long) query.uniqueResult();
			if (count == 0) {
				return true;
			}else {
				return false;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		
	}

	@Override
	public List<Documents> getDocumentList(int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<Documents> list2 = new ArrayList<Documents>();
		try {
			String sql="From Documents where company_id="+c_id;
			Query q=session.createQuery(sql);
			return q.list();
		}catch(Exception e){
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public int saveCustomerDocument(CustomerRequiredDocuments documents) {

		Session session = initiateSession();
		try {

			session.save(documents);
			if(documents.getId()>0) {
				return documents.getId();
			}else {
				return 0;
			}
			
			
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return 0;
		} finally {
			destroySession(session);
		}
		
	}



	@Override
	public List<CustomerRequiredDocuments> getCustomerRequiredDocumentsList(int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<CustomerRequiredDocuments> list2 = new ArrayList<CustomerRequiredDocuments>();
		try {
			String sql="select chd.id,crd.requiredDocumentName,chd.customer_type_name,chd.is_mandatory,chd.customer_sub_type from CustomerRequiredDocuments crd JOIN CustomerHasDocuments chd on crd.requiredDocumentName=chd.document_name where crd.companyId="+c_id+" and chd.company_id="+c_id+" and chd.status=1 and crd.status=1";
			Query q=session.createQuery(sql);
			List list = q.list();
			for (Object object : list) {

				Object[] obj = (Object[]) object;
				CustomerRequiredDocuments master = new CustomerRequiredDocuments();

				master.setId((int) obj[0]);
				master.setRequiredDocumentName((String) obj[1]);
				master.setCustomer_type((String) obj[2]);
				master.setIs_mandatory((boolean) obj[3]);
				master.setCustomer_sub_type((String) obj[4]);
				
				
				list2.add(master);
			}
		}catch(Exception e){
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public boolean editCustomerDocument(String doc_name, int c_id,int id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("update CustomerRequiredDocuments set requiredDocumentName=:doc_name where companyId=:c_id and id=:id")
					.setParameter("doc_name", doc_name).setParameter("c_id", c_id).setParameter("id", id);
			int val = query.executeUpdate();
			if (val == 0) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public List<CustomerRequiredDocuments> getCustRequiredDocumentsList(int c_id) {
		Session session = initiateSession();
		List<CustomerRequiredDocuments> list2 = new ArrayList<CustomerRequiredDocuments>();
		try {
			String sql="select id,requiredDocumentName from CustomerRequiredDocuments where companyId="+c_id;
			Query q=session.createQuery(sql);
			List list = q.list();
			for (Object object : list) {

				Object[] obj = (Object[]) object;
				CustomerRequiredDocuments master = new CustomerRequiredDocuments();

				master.setId((int) obj[0]);
				master.setRequiredDocumentName((String) obj[1]);
				
				list2.add(master);
			}
		}catch(Exception e){
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public List<String> getCustRequiredDocumentsListCustomerTypeWise(int c_id,
			String customer_type,String customer_sub_type) {
		Session session = initiateSession();
		try {
			Query q=null;
			String sql="";
			String sub_type_sql="";
			
			if(customer_sub_type!= null) {
				
				if(customer_sub_type.contains("0001"))
					customer_sub_type="Sold to Party";
				
				if(customer_sub_type.contains("0002"))
					customer_sub_type="Shift to Party";
				
				sub_type_sql=" and customer_sub_type='"+customer_sub_type+"'";
			}
			
			if(customer_type!=null) {
				sql="select document_name from CustomerHasDocuments where company_id="+c_id+" and customer_type_name='"+customer_type+"'  "+sub_type_sql+" and is_mandatory=1 and status=1";
				q=session.createQuery(sql);
				
				if(!q.list().isEmpty() || q.list()!=null)
					return q.list();
					else
						return null;
			}
			
			
		}catch(Exception e){
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public List<String> getCustomerSubType(int c_id, String customer_type) {
		Session session = initiateSession();
		try {
			String sql="select customer_sub_type from CustomerType where company_id="+c_id+" and customer_type='"+customer_type+"' and status=1";
			Query q=session.createQuery(sql);
			if(q.list()!= null)
			return q.list();
			
		}catch(Exception e){
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public boolean checkCustomerDocument_Name(String documentName, int c_id) {
		Session session = initiateSession();
		
		try {
			Query query = session
					.createQuery("select count(*) from CustomerRequiredDocuments where requiredDocumentName=:mn and status=1 and companyId=:cid")
					.setParameter("mn", documentName).setParameter("cid", c_id);
			// return query.list();
			long count = (Long) query.uniqueResult();
			if (count == 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		
	}
	
}
