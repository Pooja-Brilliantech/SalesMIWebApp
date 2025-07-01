package com.fieldmi.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.WorkFlowAuditLogDao;
import com.softtantra.salesapp.pojo.WorkFlowAuditLog;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;
import com.softtantra.servicemi.pojo.SM_LeadDetails;
import com.softtantra.servicemi.pojo.SM_ServiceTypeDetails;

public class WorkFlowAuditLogDaoImpl implements WorkFlowAuditLogDao {

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
	@Transactional
	public boolean addWorkFlowAuditLog(WorkFlowAuditLog workFlowAuditLog) {

		Session session = initiateSession();
		try {
			Date date = new Date();
			workFlowAuditLog.setCreated_date(date);
			workFlowAuditLog.setUpdated_date(date);
			session.save(workFlowAuditLog);
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
	public List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id) {

		List<WorkFlowAuditLog> workFlowLogList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && workflow_task_id != 0) {

				query = session.createQuery(
						"select id,workflow_task_id,company_id,task_status,approver_id,approver_type,created_date,task_name from WorkFlowAuditLog where company_id=:c_id and workflow_task_id=:workflow_task_id ORDER BY created_date DESC")
						.setParameter("c_id", c_id).setParameter("workflow_task_id", workflow_task_id);
			} else {

				return workFlowLogList;
			}

			setWorkFlowLogObjects(session, query, workFlowLogList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowLogList;
	}

	@Override
	@Transactional
	public List<WorkFlowAuditLog> getWorkFlowLogs(int c_id, int workflow_task_id, int iNITIAL, int rECORD_SIZE) {

		List<WorkFlowAuditLog> workFlowLogList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (rECORD_SIZE > 0 && c_id != 0 && workflow_task_id != 0) {

				query = session.createQuery(
						"select id,workflow_task_id,company_id,task_status,approver_id,approver_type,created_date,task_name from WorkFlowAuditLog where company_id=:c_id and workflow_task_id=:workflow_task_id ORDER BY created_date DESC")
						.setParameter("c_id", c_id).setParameter("workflow_task_id", workflow_task_id)
						.setFirstResult(iNITIAL).setMaxResults(rECORD_SIZE);
			} else if (c_id != 0 && workflow_task_id != 0) {

				query = session.createQuery(
						"select id,workflow_task_id,company_id,task_status,approver_id,approver_type,created_date,task_name from WorkFlowAuditLog where company_id=:c_id and workflow_task_id=:workflow_task_id ORDER BY created_date DESC")
						.setParameter("c_id", c_id).setParameter("workflow_task_id", workflow_task_id);
			}

			setWorkFlowLogObjects(session, query, workFlowLogList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowLogList;
	}

	private void setWorkFlowLogObjects(Session session, Query query, List<WorkFlowAuditLog> workFlowLogList) {

		List logList = query.list();
		if (logList != null) {

			Iterator itr = logList.iterator();
			while (itr.hasNext()) {

				Object obj = itr.next();
				Object o[] = (Object[]) obj;
				WorkFlowAuditLog logDetail = new WorkFlowAuditLog();
				logDetail.setId((Integer) o[0]);
				logDetail.setWorkflow_task_id((int) o[1]);
				logDetail.setCompany_id((int) o[2]);
				logDetail.setTask_status((String) o[3]);

				logDetail.setApprover_id((int) o[4]);
				Query userQuery = null;
				if ((int) o[5] == 1) {

					logDetail.setApprover_type_name("Role");
					// find user from role
					userQuery = session
							.createQuery(
									"SELECT first_name,last_name FROM User WHERE company_id=:c_id AND role_id=:r_id")
							.setParameter("c_id", logDetail.getCompany_id())
							.setParameter("r_id", logDetail.getApprover_id());

				} else if ((int) o[5] == 0) {

					logDetail.setApprover_type_name("User");
					// find user from user id
					userQuery = session
							.createQuery(
									"SELECT first_name,last_name FROM User WHERE company_id=:c_id AND user_id=:u_id")
							.setParameter("c_id", logDetail.getCompany_id())
							.setParameter("u_id", logDetail.getApprover_id());
				}

				// find the user name
				if (userQuery != null) {

					List userList = userQuery.list();
					Iterator usrItr = userList.iterator();
					while (usrItr.hasNext()) {

						Object usrObj = usrItr.next();
						Object usrObjArr[] = (Object[]) usrObj;
						if (usrObjArr.length == 2)
							logDetail.setApprover_name(usrObjArr[0] + " " + usrObjArr[1]);
						else
							logDetail.setApprover_name("Anonymous");
					}
				} else {

					logDetail.setApprover_name("Anonymous");
				}

				logDetail.setCreated_date((Date) o[6]);
				logDetail.setTask_name((String) o[7]);
				workFlowLogList.add(logDetail);
			}
		}

	}

	@Override
	@Transactional
	public int getTotalRecordCount(int c_id, int workflow_task_id) {

		Session session = initiateSession();
		try {
			return ((BigInteger) session
					.createSQLQuery("select count(*) from work_flow_audit_log where workflow_task_id="
							+ workflow_task_id + " and company_id=" + c_id)
					.uniqueResult()).intValue();
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return 0;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public void addWorkFlowLog(List<WorkFlowTasksStatus> workFlowTasksStatusForUpdate, int updated_by) {

		for (WorkFlowTasksStatus workFlowTasksStatus : workFlowTasksStatusForUpdate) {

			addWorkFlowLog(workFlowTasksStatus, updated_by);
		}

	}

	@Override
	public void addWorkFlowLog(WorkFlowTasksStatus workFlowTasksStatus, int updated_by) {

		WorkFlowAuditLog workFlowAuditLog = new WorkFlowAuditLog();
		workFlowAuditLog.setApprover_id(updated_by);
		workFlowAuditLog.setApprover_type(workFlowTasksStatus.getApprover_type());
		workFlowAuditLog.setCompany_id(workFlowTasksStatus.getCompany_id());
		workFlowAuditLog.setCreated_by(updated_by);
		workFlowAuditLog.setTask_name(workFlowTasksStatus.getTask_name());
		workFlowAuditLog.setComment(workFlowTasksStatus.getComment());
		if (workFlowTasksStatus.getTask_status() != null)
			workFlowAuditLog.setTask_status(workFlowTasksStatus.getTask_status());
		workFlowAuditLog.setWorkflow_task_id(workFlowTasksStatus.getId());

		addWorkFlowAuditLog(workFlowAuditLog);

	}
}
