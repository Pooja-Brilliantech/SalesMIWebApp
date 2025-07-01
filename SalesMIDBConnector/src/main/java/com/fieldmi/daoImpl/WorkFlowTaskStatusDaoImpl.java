package com.fieldmi.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.WorkFlowTaskStatusDao;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.utils.SalesMIDBUtils;
import com.softtantra.salesapp.pojo.Role;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public class WorkFlowTaskStatusDaoImpl implements WorkFlowTaskStatusDao {

	private static final String QUERY_COLUMNS = "select id,sales_master_id,company_id,sales_details_id,task_name,task_status,old_task_status,next_approver_id,old_approver_id,approver_type,created_by,created_date,comment,custom_field_date,workflow_name,externalComment,recursive_level from WorkFlowTasksStatus where";

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
	public boolean addWorkFlowTask(WorkFlowTasksStatus workFlowTaskStatus) {

		Session session = initiateSession();
		try {
			Date date = new Date();
			workFlowTaskStatus.setCreated_date(date);
			workFlowTaskStatus.setUpdated_date(date);
			session.save(workFlowTaskStatus);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	private boolean updateWorkFlowStatus(WorkFlowTasksStatus objclassWorkFlowStatus) {

		Session session = initiateSession();
		try {
			Date date = new Date();
			objclassWorkFlowStatus.setUpdated_date(date);
			session.saveOrUpdate(objclassWorkFlowStatus);
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
	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, int sales_master_id,
			String task_name, String workflowName, int approver_type, int next_approver_id) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_name=:task_name and workflow_name=:workflow_name and next_approver_id=:next_approver_id and approver_type=:approver_type ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_name", task_name).setParameter("approver_type", approver_type)
						.setParameter("next_approver_id", next_approver_id).setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnNextApproverId(int c_id, String task_name,
			String workflowName, int approver_type, int next_approver_id) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();

		try {

			Query query = null;
			if (c_id != 0) {
				if (next_approver_id == -1 && approver_type == -1) {
					query = session.createQuery(QUERY_COLUMNS
							+ " company_id=:c_id  and task_name=:task_name and task_status='Pending' and workflow_name=:workflow_name ORDER BY updated_date DESC")
							.setParameter("c_id", c_id).setParameter("task_name", task_name)
							.setParameter("workflow_name", workflowName);
				} else {
					query = session.createQuery(QUERY_COLUMNS
							+ " company_id=:c_id  and task_name=:task_name and next_approver_id=:next_approver_id and approver_type=:approver_type and workflow_name=:workflow_name ORDER BY updated_date DESC")
							.setParameter("c_id", c_id).setParameter("task_name", task_name)
							.setParameter("approver_type", approver_type)
							.setParameter("next_approver_id", next_approver_id)
							.setParameter("workflow_name", workflowName);
				}

			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatusBasedOnTaskName(int c_id, int sales_master_id, String task_name,
			String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_name=:task_name and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_name", task_name).setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_status,
			String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {
			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_status", task_status).setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderStatus(int c_id, int sales_master_id, String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesDetailsStatusBasedOnTaskName(int c_id, int sales_master_id,
			int sales_details_id, String task_name, String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and task_name=:task_name and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("sales_details_id", sales_details_id).setParameter("task_name", task_name)
						.setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesDetailsTasksBasedOnStatus(int c_id, int sales_master_id,
			int sales_details_id, String task_status, String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("sales_details_id", sales_details_id).setParameter("task_status", task_status)
						.setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id,
			String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("sales_details_id", sales_details_id).setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	/**
	 * Generic method for query where below columns are needed.
	 * id,sales_master_id,company_id,sales_details_id,task_name,task_status,old_task_status,next_approver_id,old_approver_id.approver_type
	 *
	 * @param Session                 session - session for hibernate
	 * @param query                   - query with above columns in a sequence and
	 *                                added condition
	 * @param workFlowTasksStatusList - the list of tasks that will be returned
	 */
	private void converToObjectList(Session session, Query query, List<WorkFlowTasksStatus> workFlowTasksStatusList) {

		List logList = query.list();
		if (logList != null) {

			Iterator itr = logList.iterator();
			while (itr.hasNext()) {

				Object obj = itr.next();
				Object o[] = (Object[]) obj;
				WorkFlowTasksStatus taskStatusDetail = new WorkFlowTasksStatus();
				taskStatusDetail.setId((Integer) o[0]);
				taskStatusDetail.setSales_master_id((int) o[1]);
				taskStatusDetail.setCompany_id((int) o[2]);
				taskStatusDetail.setSales_details_id((int) o[3]);
				taskStatusDetail.setTask_name((String) o[4]);
				taskStatusDetail.setTask_status((String) o[5]);
				taskStatusDetail.setOld_task_status((String) o[6]);
				taskStatusDetail.setNext_approver_id((int) o[7]);
				taskStatusDetail.setOld_approver_id((int) o[8]);
				taskStatusDetail.setApprover_type((int) o[9]);
				taskStatusDetail.setCreated_by((int) o[10]);
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date date = sdf.parse(o[11].toString());
					taskStatusDetail.setCreated_date(date);
				} catch (Exception ex) {

					FieldMILogger.error(this.getClass().getName(), ex);
				}
				if (o[12] != null) {
					taskStatusDetail.setComment(o[12].toString());
				}
				if (o[13] != null) {
					try {

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Date date = sdf.parse(o[13].toString());
						taskStatusDetail.setCustom_field_date(date);
					} catch (Exception ex) {

						FieldMILogger.error(this.getClass().getName(), ex);
					}
				}
				if (o[14] != null) {

					taskStatusDetail.setWorkflow_name(o[14].toString());
				}

				if (o[15] != null) {

					taskStatusDetail.setExternalComment(o[15].toString());
				}
				if (o[16] != null) {

					taskStatusDetail.setRecursive_level((int) o[16]);
				}

				String nextApproverName = getApproverName(session, taskStatusDetail,
						taskStatusDetail.getApprover_type(), taskStatusDetail.getNext_approver_id());
				taskStatusDetail.setNext_approver_name(nextApproverName);

				String oldApproverName = getApproverName(session, taskStatusDetail, taskStatusDetail.getApprover_type(),
						taskStatusDetail.getOld_approver_id());
				taskStatusDetail.setOld_approver_name(oldApproverName);

				workFlowTasksStatusList.add(taskStatusDetail);
			}
		}

	}

	private String getApproverName(Session session, WorkFlowTasksStatus taskStatusDetail, int approver_type,
			int approver_id) {

		String approverName = "Anonymous";
		if (approver_type == 1) {

			taskStatusDetail.setApprover_type_name("Role");
			String sql = "from Role where id=" + approver_id + "";
			Query query = session.createQuery(sql);
			Role role = (Role) query.uniqueResult();
			if (role != null) {
				approverName = role.getName();
			} else {
				approverName = "Anonymous";
			}

		} else if (approver_type == 0) {

			taskStatusDetail.setApprover_type_name("User");
		}

		Query userQuery = session
				.createQuery("SELECT first_name,last_name FROM User WHERE company_id=:c_id AND user_id=:u_id")
				.setParameter("c_id", taskStatusDetail.getCompany_id()).setParameter("u_id", approver_id);
		if (userQuery != null) {

			List userList = userQuery.list();
			if (userList != null && userList.size() > 0) {

				Iterator usrItr = userList.iterator();
				while (usrItr.hasNext()) {

					Object usrObj = usrItr.next();
					Object usrObjArr[] = (Object[]) usrObj;
					if (usrObjArr.length == 2)
						approverName = (usrObjArr[0] + " " + usrObjArr[1]);
				}
			}
		}

		return approverName;
	}

	@Override
	@Transactional
	public String getFinalSalesOrderStatus(int c_id, int sales_master_id, String workflowName) {

		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_status", SalesMIDBUtils.REJECTED)
						.setParameter("workflow_name", workflowName);

				List<WorkFlowTasksStatus> rejectedTasksList = new ArrayList<>();
				converToObjectList(session, query, rejectedTasksList);
				if (rejectedTasksList.size() > 0) {
					return SalesMIDBUtils.REJECTED_BY + rejectedTasksList.get(0).getOld_approver_name();
				} else {

					query = session.createQuery(QUERY_COLUMNS
							+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
							.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
							.setParameter("task_status", SalesMIDBUtils.RECOMMEND)
							.setParameter("workflow_name", workflowName);

					List<WorkFlowTasksStatus> recommendTasksList = new ArrayList<>();
					converToObjectList(session, query, recommendTasksList);

					if (recommendTasksList.size() > 0) {

						return SalesMIDBUtils.RECOMMEND;

					} else {

						query = session.createQuery(QUERY_COLUMNS
								+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
								.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
								.setParameter("task_status", SalesMIDBUtils.PENDING)
								.setParameter("workflow_name", workflowName);

						List<WorkFlowTasksStatus> pendingTasksList = new ArrayList<>();
						converToObjectList(session, query, pendingTasksList);
						if (pendingTasksList.size() > 0) {

							return SalesMIDBUtils.PENDING_AT + pendingTasksList.get(0).getNext_approver_name();
						} else {

							query = session.createQuery(QUERY_COLUMNS
									+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
									.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
									.setParameter("task_status", SalesMIDBUtils.TO_SUBMIT)
									.setParameter("workflow_name", workflowName);

							List<WorkFlowTasksStatus> submitTasksList = new ArrayList<>();
							converToObjectList(session, query, submitTasksList);
							if (submitTasksList.size() > 0) {

								return SalesMIDBUtils.TO_SUBMIT;
							} else {

								query = session.createQuery(QUERY_COLUMNS
										+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
										.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
										.setParameter("task_status", SalesMIDBUtils.APPROVED)
										.setParameter("workflow_name", workflowName);
								List<WorkFlowTasksStatus> approvedTasksList = new ArrayList<>();
								converToObjectList(session, query, approvedTasksList);
								if (approvedTasksList.size() > 0) {

									if (approvedTasksList.get(0).getOld_approver_name().equals("Anonymous"))
										return SalesMIDBUtils.APPROVED;
									else
										return SalesMIDBUtils.APPROVED_BY + " "
												+ approvedTasksList.get(0).getOld_approver_name();
								}
							}
						}
					}
				}
			} else {

				return SalesMIDBUtils.PENDING;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return SalesMIDBUtils.PENDING;
	}

	@Override
	@Transactional
	public String getFinalSalesDetailsStatus(int c_id, int sales_master_id, int sales_details_id, String workflowName) {

		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("sales_details_id", sales_details_id)
						.setParameter("task_status", SalesMIDBUtils.REJECTED)
						.setParameter("workflow_name", workflowName);

				List<WorkFlowTasksStatus> rejectedTasksList = new ArrayList<>();
				converToObjectList(session, query, rejectedTasksList);
				if (rejectedTasksList.size() > 0) {
					return SalesMIDBUtils.REJECTED_BY + rejectedTasksList.get(0).getOld_approver_name();
				} else {

					query = session.createQuery(QUERY_COLUMNS
							+ " company_id=:c_id and sales_details_id=:sales_details_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
							.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
							.setParameter("sales_details_id", sales_details_id)
							.setParameter("task_status", SalesMIDBUtils.RECOMMEND)
							.setParameter("workflow_name", workflowName);

					List<WorkFlowTasksStatus> recommendTasksList = new ArrayList<>();
					converToObjectList(session, query, recommendTasksList);

					if (recommendTasksList.size() > 0) {

						return SalesMIDBUtils.RECOMMEND;
					} else {

						query = session.createQuery(QUERY_COLUMNS
								+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
								.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
								.setParameter("sales_details_id", sales_details_id)
								.setParameter("task_status", SalesMIDBUtils.PENDING)
								.setParameter("workflow_name", workflowName);

						List<WorkFlowTasksStatus> pendingTasksList = new ArrayList<>();
						converToObjectList(session, query, pendingTasksList);
						if (pendingTasksList.size() > 0) {

							return SalesMIDBUtils.PENDING_AT + pendingTasksList.get(0).getNext_approver_name();
						} else {

							query = session.createQuery(QUERY_COLUMNS
									+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
									.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
									.setParameter("sales_details_id", sales_details_id)
									.setParameter("task_status", SalesMIDBUtils.TO_SUBMIT)
									.setParameter("workflow_name", workflowName);

							List<WorkFlowTasksStatus> submitTasksList = new ArrayList<>();
							converToObjectList(session, query, submitTasksList);
							if (submitTasksList.size() > 0) {

								return SalesMIDBUtils.TO_SUBMIT;
							} else {

								query = session.createQuery(QUERY_COLUMNS
										+ " company_id=:c_id and sales_master_id=:sales_master_id and sales_details_id=:sales_details_id and task_status=:task_status and workflow_name=:workflow_name ORDER BY updated_date DESC")
										.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
										.setParameter("sales_details_id", sales_details_id)
										.setParameter("task_status", SalesMIDBUtils.APPROVED)
										.setParameter("workflow_name", workflowName);
								List<WorkFlowTasksStatus> approvedTasksList = new ArrayList<>();
								converToObjectList(session, query, approvedTasksList);
								if (approvedTasksList.size() > 0) {

									if (approvedTasksList.get(0).getOld_approver_name().equals("Anonymous"))
										return SalesMIDBUtils.APPROVED;
									else
										return SalesMIDBUtils.APPROVED_BY + " "
												+ approvedTasksList.get(0).getOld_approver_name();
								}
							}
						}
					}
				}
			} else {

				return SalesMIDBUtils.PENDING;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return SalesMIDBUtils.PENDING;
	}

	@Override
	@Transactional
	public String getFinalSalesOrderStatusForTask(int c_id, int sales_master_id, String task_name,
			String workflowName) {

		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and task_name=:task_name ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_status", SalesMIDBUtils.REJECTED).setParameter("task_name", task_name)
						.setParameter("workflow_name", workflowName);

				List<WorkFlowTasksStatus> rejectedTasksList = new ArrayList<>();
				converToObjectList(session, query, rejectedTasksList);
				if (rejectedTasksList.size() > 0) {
					return SalesMIDBUtils.REJECTED_BY + rejectedTasksList.get(0).getOld_approver_name();
				} else {

					query = session.createQuery(QUERY_COLUMNS
							+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and task_name=:task_name ORDER BY updated_date DESC")
							.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
							.setParameter("task_name", task_name).setParameter("task_status", SalesMIDBUtils.RECOMMEND)
							.setParameter("workflow_name", workflowName);

					List<WorkFlowTasksStatus> recommendTasksList = new ArrayList<>();
					converToObjectList(session, query, recommendTasksList);

					if (recommendTasksList.size() > 0) {

						return SalesMIDBUtils.RECOMMEND;
					} else {

						query = session.createQuery(QUERY_COLUMNS
								+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and task_name=:task_name ORDER BY updated_date DESC")
								.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
								.setParameter("task_status", SalesMIDBUtils.PENDING)
								.setParameter("task_name", task_name).setParameter("workflow_name", workflowName);

						List<WorkFlowTasksStatus> pendingTasksList = new ArrayList<>();
						converToObjectList(session, query, pendingTasksList);
						if (pendingTasksList.size() > 0) {

							return SalesMIDBUtils.PENDING_AT + pendingTasksList.get(0).getNext_approver_name();
						} else {

							query = session.createQuery(QUERY_COLUMNS
									+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and task_name=:task_name ORDER BY updated_date DESC")
									.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
									.setParameter("task_status", SalesMIDBUtils.TO_SUBMIT)
									.setParameter("task_name", task_name).setParameter("workflow_name", workflowName);

							List<WorkFlowTasksStatus> submitTasksList = new ArrayList<>();
							converToObjectList(session, query, submitTasksList);
							if (submitTasksList.size() > 0) {

								return SalesMIDBUtils.TO_SUBMIT;
							} else {

								query = session.createQuery(QUERY_COLUMNS
										+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and task_name=:task_name ORDER BY updated_date DESC")
										.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
										.setParameter("task_status", SalesMIDBUtils.APPROVED)
										.setParameter("task_name", task_name)
										.setParameter("workflow_name", workflowName);
								List<WorkFlowTasksStatus> approvedTasksList = new ArrayList<>();
								converToObjectList(session, query, approvedTasksList);
								if (approvedTasksList.size() > 0) {

									if (approvedTasksList.get(0).getOld_approver_name().equals("Anonymous"))
										return SalesMIDBUtils.APPROVED;
									else
										return SalesMIDBUtils.APPROVED_BY + " "
												+ approvedTasksList.get(0).getOld_approver_name();
								}
							}
						}
					}
				}
			} else {

				return SalesMIDBUtils.PENDING;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return SalesMIDBUtils.PENDING;
	}

	@Override
	@Transactional
	public String getFinalSalesDetailsStatusForTask(int c_id, int sales_master_id, int sales_details_id,
			String task_name, String workflowName) {

		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and sales_details_id=:sales_details_id and task_name=:task_name ORDER BY created_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_status", SalesMIDBUtils.REJECTED)
						.setParameter("sales_details_id", sales_details_id).setParameter("task_name", task_name)
						.setParameter("workflow_name", workflowName);

				List<WorkFlowTasksStatus> rejectedTasksList = new ArrayList<>();
				converToObjectList(session, query, rejectedTasksList);
				if (rejectedTasksList.size() > 0) {
					return SalesMIDBUtils.REJECTED_BY + rejectedTasksList.get(0).getOld_approver_name();
				} else {

					query = session.createQuery(QUERY_COLUMNS
							+ " company_id=:c_id and sales_details_id=:sales_details_id and task_name=:task_name and workflow_name=:workflow_name and sales_master_id=:sales_master_id and task_status=:task_status ORDER BY updated_date DESC")
							.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
							.setParameter("sales_details_id", sales_details_id)
							.setParameter("task_status", SalesMIDBUtils.RECOMMEND).setParameter("task_name", task_name)
							.setParameter("workflow_name", workflowName);

					List<WorkFlowTasksStatus> recommendTasksList = new ArrayList<>();
					converToObjectList(session, query, recommendTasksList);

					if (recommendTasksList.size() > 0) {

						return SalesMIDBUtils.RECOMMEND;
					} else {

						query = session.createQuery(QUERY_COLUMNS
								+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and sales_details_id=:sales_details_id and task_name=:task_name ORDER BY created_date DESC")
								.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
								.setParameter("task_status", SalesMIDBUtils.PENDING)
								.setParameter("sales_details_id", sales_details_id).setParameter("task_name", task_name)
								.setParameter("workflow_name", workflowName);

						List<WorkFlowTasksStatus> pendingTasksList = new ArrayList<>();
						converToObjectList(session, query, pendingTasksList);
						if (pendingTasksList.size() > 0) {

							return SalesMIDBUtils.PENDING_AT + pendingTasksList.get(0).getNext_approver_name();
						} else {

							query = session.createQuery(QUERY_COLUMNS
									+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and sales_details_id=:sales_details_id and task_name=:task_name ORDER BY created_date DESC")
									.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
									.setParameter("task_status", SalesMIDBUtils.TO_SUBMIT)
									.setParameter("sales_details_id", sales_details_id)
									.setParameter("task_name", task_name).setParameter("workflow_name", workflowName);

							List<WorkFlowTasksStatus> submitTasksList = new ArrayList<>();
							converToObjectList(session, query, submitTasksList);
							if (submitTasksList.size() > 0) {

								return SalesMIDBUtils.TO_SUBMIT;
							} else {

								query = session.createQuery(QUERY_COLUMNS
										+ " company_id=:c_id and sales_master_id=:sales_master_id and task_status=:task_status and workflow_name=:workflow_name and sales_details_id=:sales_details_id and task_name=:task_name ORDER BY created_date DESC")
										.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
										.setParameter("task_status", SalesMIDBUtils.APPROVED)
										.setParameter("sales_details_id", sales_details_id)
										.setParameter("task_name", task_name)
										.setParameter("workflow_name", workflowName);
								List<WorkFlowTasksStatus> approvedTasksList = new ArrayList<>();
								converToObjectList(session, query, approvedTasksList);
								if (approvedTasksList.size() > 0) {

									if (approvedTasksList.get(0).getOld_approver_name().equals("Anonymous"))
										return SalesMIDBUtils.APPROVED;
									else
										return SalesMIDBUtils.APPROVED_BY + " "
												+ approvedTasksList.get(0).getOld_approver_name();
								}
							}
						}
					}
				}
			} else {

				return SalesMIDBUtils.PENDING;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return SalesMIDBUtils.PENDING;
	}

	@Override
	@Transactional
	public List<WorkFlowTasksStatus> getSalesOrderTasksBasedOnStatus(int c_id, int sales_master_id, String task_name,
			String task_status, String workflowName) {

		List<WorkFlowTasksStatus> workFlowTasksStatusList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (c_id != 0 && sales_master_id != 0) {

				query = session.createQuery(QUERY_COLUMNS
						+ " company_id=:c_id and sales_master_id=:sales_master_id and task_name=:task_name and workflow_name=:workflow_name and task_status=:task_status ORDER BY updated_date DESC")
						.setParameter("c_id", c_id).setParameter("sales_master_id", sales_master_id)
						.setParameter("task_name", task_name).setParameter("task_status", task_status)
						.setParameter("workflow_name", workflowName);
			} else {

				return workFlowTasksStatusList;
			}

			FieldMILogger.info(this.getClass().getName(), "query " + query.getQueryString());
			converToObjectList(session, query, workFlowTasksStatusList);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return workFlowTasksStatusList;
	}

	@Override
	@Transactional
	public int roleIdFromRoleName(String roleName, int c_id) {

		Session session = initiateSession();
		try {
			Query query = session.createSQLQuery(
					"SELECT r.id FROM company_has_rolepermission c inner join role_permissions r on r.id=c.permission_id "
							+ "  where c.company_id= " + c_id + " and r.name='" + roleName + "' and r.status=1");

			// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
			Object obj = query.uniqueResult();
			if (obj != null)
				return Integer.parseInt("" + obj);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {

			destroySession(session);
		}
		return -1;
	}

	@Override
	@Transactional
	public String getComment(int c_id, int salesmasterId, String workflowName) {
		/*
		 * Session session =initiateSession(); try { String
		 * q="select comment from work_flow_task_status where company_id="
		 * +c_id+" and sales_master_id="
		 * +salesmasterId+" and task_status='Approved' and task_name='Material Stock Issue'"
		 * ;
		 *
		 * Query query=session.createSQLQuery(q);
		 *
		 * List comment=query.list(); return comment;
		 *
		 * } catch(Exception e) { FieldMILogger.error(this.getClass().getName(),e); }
		 * finally { destroySession(session); }
		 */
		return null;
	}

	@Override
	public void setTaskToRejected(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService) {

		for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

			String old_status = workFlowTasksStatus.getTask_status();
			int old_approver_id = workFlowTasksStatus.getNext_approver_id();
			workFlowTasksStatus.setOld_task_status(old_status);
			workFlowTasksStatus.setOld_approver_id(old_approver_id);
			workFlowTasksStatus.setTask_status(SalesMIDBUtils.REJECTED);
			workFlowTasksStatus.setUpdated_by(updated_by);
			if (comment != null && comment.trim().length() > 0) {
				workFlowTasksStatus.setComment(comment);
			}
			workFlowTasksStatus.setNext_approver_id(-1);
			updateWorkFlowStatus(workFlowTasksStatus);

			workFlowAuditLogService.addWorkFlowLog(workFlowTasksStatus, updated_by);
		}
	}

	@Override
	public void setTaskToApproved(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService) {

		for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

			if (updated_by == workFlowTasksStatus.getNext_approver_id()
					|| workFlowTasksStatus.getNext_approver_id() == -1
					|| workFlowTasksStatus.getNext_approver_id() == 0) {

				String old_status = workFlowTasksStatus.getTask_status();
				int old_approver_id = workFlowTasksStatus.getNext_approver_id();
				workFlowTasksStatus.setOld_task_status(old_status);
				workFlowTasksStatus.setOld_approver_id(old_approver_id);
				workFlowTasksStatus.setTask_status(SalesMIDBUtils.APPROVED);
				workFlowTasksStatus.setUpdated_by(updated_by);
				if (comment != null && comment.trim().length() > 0) {
					workFlowTasksStatus.setComment(comment);
				}
				workFlowTasksStatus.setNext_approver_id(-1);
				updateWorkFlowStatus(workFlowTasksStatus);

				workFlowAuditLogService.addWorkFlowLog(workFlowTasksStatus, updated_by);
			}
		}
	}

	@Override
	public void updateStatusToPending(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by,
			String comment, WorkFlowAuditLogService workFlowAuditLogService) {

		for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {
			
			String old_status = workFlowTasksStatus.getTask_status();
			workFlowTasksStatus.setOld_task_status(old_status);
			workFlowTasksStatus.setOld_approver_id(updated_by);
			workFlowTasksStatus.setTask_status(SalesMIDBUtils.PENDING);
			workFlowTasksStatus.setUpdated_by(updated_by);
			if (comment != null && comment.trim().length() > 0) {
				workFlowTasksStatus.setComment(comment);
			}
			updateWorkFlowStatus(workFlowTasksStatus);

			workFlowAuditLogService.addWorkFlowLog(workFlowTasksStatus, updated_by);
		}
	}

	@Override
	public void updateStatus(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by, String comment,
			WorkFlowAuditLogService workFlowAuditLogService, String newStatus) {

		for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

			String old_status = workFlowTasksStatus.getTask_status();
			workFlowTasksStatus.setOld_task_status(old_status);
			workFlowTasksStatus.setOld_approver_id(updated_by);
			workFlowTasksStatus.setTask_status(newStatus);
			workFlowTasksStatus.setUpdated_by(updated_by);
			if (comment != null && comment.trim().length() > 0) {
				workFlowTasksStatus.setComment(comment);
			}
			updateWorkFlowStatus(workFlowTasksStatus);

			workFlowAuditLogService.addWorkFlowLog(workFlowTasksStatus, updated_by);
		}
	}

	@Override
	public User getUserFromUserId(int user_id) {

		Session session = initiateSession();
		try {
			Query query = session.createQuery("from User u where u.user_id=" + user_id + " and u.status=1");

			// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
			Object userObj = query.uniqueResult();
			if (userObj != null)
				return (User) userObj;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {

			destroySession(session);
		}
		return null;

	}

	@Override
	public void updateStatusToRecommend(List<WorkFlowTasksStatus> workTaskStatusDetailList, int updated_by,
			String comment, WorkFlowAuditLogService workFlowAuditLogService) {

		for (WorkFlowTasksStatus workFlowTasksStatus : workTaskStatusDetailList) {

			String old_status = workFlowTasksStatus.getTask_status();
			workFlowTasksStatus.setOld_task_status(old_status);
			workFlowTasksStatus.setOld_approver_id(updated_by);
			workFlowTasksStatus.setTask_status(SalesMIDBUtils.RECOMMEND);
			workFlowTasksStatus.setUpdated_by(updated_by);
			if (comment != null && comment.trim().length() > 0) {
				workFlowTasksStatus.setComment(comment);
			}
			updateWorkFlowStatus(workFlowTasksStatus);

			workFlowAuditLogService.addWorkFlowLog(workFlowTasksStatus, updated_by);
		}

	}
}
