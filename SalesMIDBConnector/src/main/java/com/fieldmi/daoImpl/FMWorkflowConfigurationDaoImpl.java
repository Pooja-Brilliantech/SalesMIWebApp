package com.fieldmi.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMWorkflowConfigurationDao;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;

public class FMWorkflowConfigurationDaoImpl implements FMWorkflowConfigurationDao {

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
	public boolean addNewWorkflow(WorkFlowConfiguration workFlowConfiguration, String data) {
		Session session = initiateSession();
		try {
			Date date = new Date();
			workFlowConfiguration.setRoles(data);
			workFlowConfiguration.setCreated_date(date);
			workFlowConfiguration.setUpdated_date(date);
			session.save(workFlowConfiguration);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateWorkflowInfo(WorkFlowConfiguration workFlowConfiguration) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update WorkFlowConfiguration set workflow_name=:workflow_name,updated_date=:updated_date,approver_type=:approver_type,approval_type=:approval_type,roles=:roles,number_of_level=:number_of_level,workflow_task_name=:workflow_task_name,input_object_name=:input_object_name where workflow_id=:workflow_id and company_id=:cid")
					.setParameter("workflow_name", workFlowConfiguration.getWorkflow_name())
					.setParameter("cid", workFlowConfiguration.getCompany_id()).setParameter("updated_date", new Date())
					.setParameter("approver_type", workFlowConfiguration.getApprover_type())
					.setParameter("approval_type", workFlowConfiguration.getApproval_type())
					.setParameter("roles", workFlowConfiguration.getRoles())
					.setParameter("number_of_level", workFlowConfiguration.getNumber_of_level())
					.setParameter("workflow_id", workFlowConfiguration.getWorkflow_id())
					.setParameter("workflow_task_name", workFlowConfiguration.getWorkflow_task_name())
					.setParameter("input_object_name", workFlowConfiguration.getInput_object_name());
			query.executeUpdate();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean deleteWorkflow(WorkFlowConfiguration workFlowConfiguration) {
		Session session = initiateSession();
		try {

			Query query = session.createQuery(
					"update WorkFlowConfiguration set status=:status,updated_date=:updated_date where workflow_id=:workflow_id and company_id=:company_id")
					.setParameter("workflow_id", workFlowConfiguration.getWorkflow_id())
					.setParameter("updated_date", new Date()).setParameter("status", 0)
					.setParameter("company_id", workFlowConfiguration.getCompany_id());
			query.executeUpdate();
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
	public WorkFlowConfiguration checkWorkFlowConfigure(String workflow_type, String input_object_name, int c_id) {

		Session session = initiateSession();
		try {
			
			Query query = null;
			query = session.createQuery("from WorkFlowConfiguration where workflow_name='"+workflow_type+"' and company_id="+c_id+" and status=1");
			if (query.uniqueResult() != null) {
				WorkFlowConfiguration workflowDetails = (WorkFlowConfiguration) query.uniqueResult();
				return workflowDetails;
			} else {
				return null;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public List<WorkFlowConfiguration> getWorkflowConfigs(String workflow_name, String workflow_task_name, int company_id,String objectName) {
		
		Session session = initiateSession();
		try {
			
			StringBuffer queryString = new StringBuffer("from WorkFlowConfiguration where workflow_name='"+workflow_name+"' and company_id="+company_id+" and status=1");
			if( workflow_task_name != null )
				queryString.append(" and workflow_task_name='"+workflow_task_name+"'");
			if(objectName!=null)
				queryString.append(" and input_object_name='"+objectName+"'");
			
			Query query = session.createQuery(queryString.toString());
			return query.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

}
