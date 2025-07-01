package com.fieldmi.daoImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMExpenseDao;
import com.fieldmi.service.FMExpenseService;
import com.fieldmi.service.FMWorkflowConfigurationService;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.fieldmi.stubs.WsExpense;
import com.fieldmi.stubs.WsExpenseDetails;
import com.fieldmi.stubs.WsExpenseMasterListForManager;
import com.fieldmi.stubs.WsSalesOrderReport;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.fieldmi.stubs.WsSalesReportOutput;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.SalesMIDBUtils;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.ExpenseConfiguration;
import com.softtantra.salesapp.pojo.ExpenseDetails;
import com.softtantra.salesapp.pojo.ExpenseMaster;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.ws.UserAttendanceReportForToday;
import com.softtantra.ws.WsExpenseMaster;
import com.softtantra.ws.WsExpenseMasterList;

public class FMExpenseDaoImpl implements FMExpenseDao {

	
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

	@Autowired
	WorkFlowTaskStatusService workFlowTaskStatusService;
	@Autowired
	FMWorkflowConfigurationService fmWorkFlowConfigurationService;
	@Autowired
	FMExpenseService fmExpenseService;

	@Autowired
	WorkFlowAuditLogService workFlowAuditLogService;
	int no_of_records_per_page = 20;

	@Override
	@Transactional
	public WsExpense getExpenseMaster(int expenseId) {

		Session session = initiateSession();
		WsExpense wsExpenseMaster = new WsExpense();
		List<WsExpenseDetails> WsExpenseDetailsList = new ArrayList<WsExpenseDetails>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			Query query = session.createQuery("from ExpenseMaster e where e.expense_master_id=" + expenseId);

			ExpenseMaster expenseMaster = (ExpenseMaster) query.uniqueResult();

			wsExpenseMaster.setAddress(expenseMaster.getAddress());
			wsExpenseMaster.setApprover_id(expenseMaster.getApprover_id());
			wsExpenseMaster.setComment(expenseMaster.getComment());
			wsExpenseMaster.setCompany_id(expenseMaster.getCompany_id());
			wsExpenseMaster.setCreated_by(expenseMaster.getCreated_by());
			wsExpenseMaster.setCreated_date(sdf.format(expenseMaster.getCreated_date()));
			wsExpenseMaster.setExpense_claim_date(sdf.format(expenseMaster.getExpense_claim_date()));
			wsExpenseMaster.setExpense_master_id(expenseMaster.getExpense_master_id());
			wsExpenseMaster.setStatus(expenseMaster.getStatus());
			wsExpenseMaster.setTotal(BigDecimal.valueOf(expenseMaster.getTotal()));
			wsExpenseMaster.setUser_id(expenseMaster.getUser_id());
			wsExpenseMaster.setApprovedamount(expenseMaster.getApprovedamount());
			wsExpenseMaster.setComment((expenseMaster.getComment()));
			if(expenseMaster.getDocumentNumber()!=null && !expenseMaster.getDocumentNumber().equals("")) {
				wsExpenseMaster.setDocumentNumber(expenseMaster.getDocumentNumber());
			}else {
				wsExpenseMaster.setDocumentNumber("");
			}
			String userName = getUserName(expenseMaster.getUser_id());
			wsExpenseMaster.setUserName(userName);
			// FieldMILogger.debug(this.getClass().getName(),"expenseMaster.getApprovedamount()"+expenseMaster.getApprovedamount());
			Query query1 = session.createQuery(
					" from ExpenseDetails ed where ed.expense_master_id=" + expenseMaster.getExpense_master_id());

			List<ExpenseDetails> list = query1.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {

				ExpenseDetails expenseDetails = (ExpenseDetails) iterator.next();

				WsExpenseDetails wsExpenseDetails = new WsExpenseDetails();
				wsExpenseDetails.setExpense_details_id(expenseDetails.getExpense_details_id());
				wsExpenseDetails.setExpense_master_id(expenseDetails.getExpense_master_id());
				wsExpenseDetails.setExpense_date(sdf.format(expenseDetails.getExpense_date()));
				wsExpenseDetails.setType(expenseDetails.getType());
				wsExpenseDetails.setMode(expenseDetails.getMode());
				wsExpenseDetails.setFrom_place(expenseDetails.getFrom_place());
				wsExpenseDetails.setTo_place(expenseDetails.getTo_place());
				wsExpenseDetails.setExpense_code(expenseDetails.getExpenseCode());
				wsExpenseDetails.setDistance_in_km(expenseDetails.getDistance_in_km());
				wsExpenseDetails.setRate(expenseDetails.getRate());
				wsExpenseDetails.setAmount(expenseDetails.getAmount());
				wsExpenseDetails.setOther_details(expenseDetails.getOther_details());
				wsExpenseDetails.setBill_img_url(expenseDetails.getBill_img());
				wsExpenseDetails.setApprovedAmount(expenseDetails.getApprovedAmount());
				if (expenseDetails.getComment() != null)
					wsExpenseDetails.setComment(expenseDetails.getComment());
				else
					wsExpenseDetails.setComment("");

				if (wsExpenseDetails.getType() == "3") {
					wsExpenseDetails.setStartTravelTime(sdf.format(expenseDetails.getStartTravelTime()));
					if (expenseDetails.getEndTravelTime() != null)
						wsExpenseDetails.setEndTravelTime(sdf.format(expenseDetails.getEndTravelTime()));
					wsExpenseDetails.setStartTravel_img_url(expenseDetails.getStartTravelimage());
					wsExpenseDetails.setEndTravel_img_url(expenseDetails.getEndTravelimage());
					if (expenseDetails.getActualDistance() != null)
						wsExpenseDetails.setActualDistance(expenseDetails.getActualDistance());
					else
						expenseDetails.setActualDistance("");
				}
				wsExpenseDetails.setActualDistance(expenseDetails.getActualDistance());

				WsExpenseDetailsList.add(wsExpenseDetails);

			}

			wsExpenseMaster.setExpenseDetails(WsExpenseDetailsList);

			return wsExpenseMaster;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	private String getUserName(int u_id) {
		Session session = initiateSession();
		try {

			Query query = session.createSQLQuery(
					"select concat(first_name,' ',last_name) as name  from user_details where user_id=" + u_id + "");

			return (String) query.uniqueResult();
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public ExpenseMaster getExpensePieChartData(int c_id, int u_id, String value) {
		Session session = initiateSession();
		try {
			long pending, approved, submitted, rejected = 0;
			if (value.equals("my_list")) {
				Query query = session.createQuery(
						"select count(*) from ExpenseMaster e JOIN WorkFlowTasksStatus w on e.expense_master_id=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Pending'  and w.created_by=" + u_id);
				pending = (long) query.uniqueResult();

				Query query1 = session.createQuery(
						"select count(*) from ExpenseMaster e JOIN WorkFlowTasksStatus w on e.expense_master_id=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Approved'  and w.created_by=" + u_id);
				approved = (long) query1.uniqueResult();

				Query query2 = session.createQuery(
						"select count(*) from ExpenseMaster e JOIN WorkFlowTasksStatus w on e.expense_master_id=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='To be submitted'  and w.created_by=" + u_id);
				submitted = (long) query2.uniqueResult();

				Query query3 = session.createQuery(
						"select count(*) from ExpenseMaster e JOIN WorkFlowTasksStatus w on e.expense_master_id=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Rejected'  and w.created_by=" + u_id);
				rejected = (long) query3.uniqueResult();

				ExpenseMaster details = new ExpenseMaster();
				details.setApproved(approved);
				details.setPending(pending);
				details.setRejected(rejected);
				details.setSubmitted(submitted);

				return details;
			} else {
				Query query = session.createQuery(
						"select count(*) from ExpenseMaster e JOIN WorkFlowTasksStatus w on e.expense_master_id=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Pending'  and w.next_approver_id=" + u_id);
				pending = (long) query.uniqueResult();

				

				ExpenseMaster details = new ExpenseMaster();
				details.setPending(pending);
				

				return details;

			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public boolean aproveExpense(String expenseId, String comment, double dapprovedamount, int user_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("select approveRejectStatus,expense_master_id"
					+ " from ExpenseMaster e where e.expense_master_id=" + expenseId + "");
			int actualStatus = 0;
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				actualStatus = Integer.parseInt(obj[0] + "");
			}
			if (actualStatus == 2) {
				Query query1 = session.createSQLQuery(
						"update expense_master set status=:st,approver_id=:id,status=:st,approveRejectStatus=:st1,"
								+ "comment=:ms,updated_by=:ub,approvedamount=:approvedamount,updated_date=:ud where expense_master_id=:lid")
						.setParameter("st", 2).setParameter("st1", 3).setParameter("id", user_id)
						.setParameter("ms", comment).setParameter("ub", user_id).setParameter("ud", new Date())
						.setParameter("approvedamount", dapprovedamount).setParameter("lid", expenseId);
				query1.executeUpdate();

				Query query2 = session.createSQLQuery(
						"update expense_details set comment=:comment where expense_master_id=:lid")
						.setParameter("comment", comment)
						.setParameter("lid", expenseId);
				query2.executeUpdate();
			} else {
				Query query1 = session.createSQLQuery(
						"update expense_master set status=:st,approver_id=:id,status=:st,approveRejectStatus=:st1,"
								+ "comment=:ms,updated_by=:ub,approvedamount=:approvedamount,updated_date=:ud where expense_master_id=:lid")
						.setParameter("st", 2).setParameter("st1", 2).setParameter("id", user_id)
						.setParameter("ms", comment).setParameter("ub", user_id).setParameter("ud", new Date())
						.setParameter("approvedamount", dapprovedamount).setParameter("lid", expenseId);
				query1.executeUpdate();

				Query query2 = session.createSQLQuery(
						"update expense_details set comment=:comment where expense_master_id=:lid")
						.setParameter("comment", comment)
						.setParameter("lid", expenseId);
				query2.executeUpdate();
			}
			//// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public List<ExpenseConfiguration> getExpenseConfigList(int c_id, int roleId) {
		// TODO Auto-generated method stub

		Session session = initiateSession();
		List<ExpenseConfiguration> eList = new ArrayList<ExpenseConfiguration>();
		try {
			String sql = "SELECT  e.expense_config_id,e.expense_type_id,e.expense_mode_type,e.isDefault,e.isDistance,e.max_Allowed_Amt ,e.cityType,e.expenseCode,e.modeOfTravel,e.isfixed "
					+ " FROM expense_configuration e left outer join role r on r.id=e.user_role   where e.status=1"

					+ " and  e.company_id=" + c_id + " and e.user_role= " + roleId;

			Query query = session.createSQLQuery(sql);

			List list = query.list();
			for (Object object : list) {
				ExpenseConfiguration e = new ExpenseConfiguration();
				Object[] obj = (Object[]) object;
				e.setExpense_config_id((int) obj[0]);
				e.setExpense_type_id((String) obj[1]);

				e.setExpense_mode_type((String) obj[2]);
				if ((int) obj[3] == 0) {
					e.setIsDefault1("No");
				} else {
					e.setIsDefault1("Yes");
				}
				if ((int) obj[4] == 0) {
					e.setIsDistance1("No");
				} else {
					e.setIsDistance1("Yes");
				}
				e.setMax_Allowed_Amt((double) obj[5]);
				
				e.setCityType((String) obj[6]);
				
				e.setExpenseCode((String) obj[7]);
				e.setModeOfTravel((String) obj[8]);
				e.setIsDefault((int) obj[3]);
				e.setIsDistance((int) obj[4]);
				e.setIsfixed((int) obj[9]);
				if ((int) obj[9] == 0) {
					e.setIsfixed1("No");
				} else {
					e.setIsfixed1("Yes");
				}
				eList.add(e);
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
		return eList;
	}

	@Override
	public boolean deleteExpenseConfig1(int detailId, int c_id) {
		// FieldMILogger.debug(this.getClass().getName(),"Inside deleteTax..!!");
		Session session = initiateSession();
		try {
			Query query = session.createQuery("update ExpenseConfiguration set status=:st where expense_config_id=:id ")
					.setParameter("st", 0).setParameter("id", detailId);
			int val = query.executeUpdate();
			if (val == 0) {
				return false;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public ExpenseConfiguration addExpenseConfig(String expense_type_id, int country, int role_id, int c_id,
			int isDefault, int isDistance, double amt, String expense_mode_type, int state_id, int city_id, int u_id,
			String cityType, String expenseCode, String modeOfTravel, int isfixed) {
		// TODO Auto-generated method stub
		Session session = initiateSession();

		try {
			ExpenseConfiguration expenseConfiguration = new ExpenseConfiguration();
			expenseConfiguration.setExpense_type_id(expense_type_id);
			expenseConfiguration.setCompany_id(c_id);
			expenseConfiguration.setCountry_id(country);
			expenseConfiguration.setState_id(state_id);
			expenseConfiguration.setCity_id(city_id);
			expenseConfiguration.setUser_role(role_id);
			expenseConfiguration.setCreated_date(new Date());
			expenseConfiguration.setUpdated_date(new Date());
			expenseConfiguration.setAmt(amt);
			expenseConfiguration.setMax_Allowed_Amt(amt);
			expenseConfiguration.setExpense_mode_type(expense_mode_type);
			expenseConfiguration.setStatus(1);
			expenseConfiguration.setIsDefault(isDefault);
			expenseConfiguration.setIsDistance(isDistance);
			expenseConfiguration.setCityType(cityType);
			expenseConfiguration.setCreated_by(u_id);
			expenseConfiguration.setUpdated_by(u_id);
			expenseConfiguration.setExpenseCode(expenseCode);
			expenseConfiguration.setModeOfTravel(modeOfTravel);
			expenseConfiguration.setIsfixed(isfixed);
			session.save(expenseConfiguration);
			return expenseConfiguration;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public WsExpenseMasterListForManager getExpenseReportForSil(WsSalesOrderReportInput wsExpenseReportInput, Long id,int rId) {
		Session session = initiateSession();
		WsExpenseMasterListForManager wsExpenseMasterListForManager = new WsExpenseMasterListForManager();
		List<WsExpense> report = new ArrayList<>();
		List<WsExpense> reportNew = new ArrayList<>();
		try {
			
			String targetMonth = wsExpenseReportInput.getTarget_month();
			String year = targetMonth.substring(0, Math.min(targetMonth.length(), 4));
			String month = targetMonth.substring(5, 7);
			String toDays = "31";
			switch (month) {
			case "1":
			case "3":
			case "5":
			case "7":
			case "8":
			case "10":
			case "12":
				toDays = "31";
				break;
			case "4":
			case "6":
			case "9":
			case "11":
				toDays = "30";
				break;
			}

			if (month.equals("2")) {
				int iyear = Integer.parseInt(year);
				if (iyear % 4 == 0) {
					if (iyear % 100 == 0) {
						if (iyear % 400 == 0)
							toDays = "29";
						else
							toDays = "28";
					} else
						toDays = "29";
				} else {
					toDays = "28";
				}
			}

			String fromDate = year + "-" + month + "-" + "01";
			String toDate = year + "-" + month + "-" + toDays;
			
			SalesMIDBUtils dbUtils = new SalesMIDBUtils();
			String next_approver_id = dbUtils.getNextApproverId(fmWorkFlowConfigurationService, id.intValue(), rId, wsExpenseReportInput.getCompany_id(),
					SalesMIDBUtils.EXPENSE_WORKFLOW_NAME, SalesMIDBUtils.EXPENSE_ISSUE_TASK, SalesMIDBUtils.ALL);
//			CommonMethods commonMethods = new CommonMethods();
//			List<Integer> users = commonMethods.getUserHirachyWise(wsExpenseReportInput.getCompany_id(), id.intValue(),
//					session,"","","","");
//			String userid = "";
//			for (int i = 0; i < users.size(); i++) {
//				userid = userid + users.get(i).intValue() + ",";
//			}
//			if (userid != null && userid.length() > 0 && userid.charAt(userid.length() - 1) == ',') {
//				userid = userid.substring(0, userid.length() - 1);
//			}
//			if (user_list == null || user_list == "") {
//				List<Integer> expenseMasterIds = getexpenseMasterIds(userid, fromDate, toDate, session);
//				for (int i = 0; i < expenseMasterIds.size(); i++) {
//					WsExpense wsExpenseMasters = getExpenseMaster(expenseMasterIds.get(i));
//					String finalStatus = workFlowTaskStatusService.getFinalSalesOrderStatus(
//							wsExpenseReportInput.getCompany_id(), expenseMasterIds.get(i), SalesMIDBUtils.EXPENSE_WORKFLOW_NAME);
//					wsExpenseMasters.setApproveStatus(finalStatus);
//					if (finalStatus.startsWith(SalesMIDBUtils.PENDING)) {
//						report.add(wsExpenseMasters);
//					}
//				}
//			} else {
//				List<Integer> expenseMasterIds = getexpenseMasterIds(user_list, fromDate, toDate, session);
//				for (int i = 0; i < expenseMasterIds.size(); i++) {
//					WsExpense wsExpenseMasters = getExpenseMaster(expenseMasterIds.get(i));
//					String finalStatus = workFlowTaskStatusService.getFinalSalesOrderStatus(
//							wsExpenseReportInput.getCompany_id(), expenseMasterIds.get(i), SalesMIDBUtils.EXPENSE_WORKFLOW_NAME);
//					wsExpenseMasters.setApproveStatus(finalStatus);
//					if (finalStatus.startsWith(SalesMIDBUtils.PENDING)) {
//						report.add(wsExpenseMasters);
//					}
//				}
//			}
			CommonMethods cm=new CommonMethods();
			report=cm.expenseList(wsExpenseReportInput.getCompany_id(),session,next_approver_id,fromDate,toDate,0,"","","","","");
			for (int i = 0; i < report.size(); i++) {
				WsExpense wsExpenseMasters = getExpenseMaster(report.get(i).getExpense_master_id());
				reportNew.add(wsExpenseMasters);
			}
			wsExpenseMasterListForManager.setWsExpense(reportNew);
			wsExpenseMasterListForManager.setPage_No(wsExpenseReportInput.getPage_no());
			wsExpenseMasterListForManager.setTotal_pages(20);
			wsExpenseMasterListForManager.setTotalRecords(report.size());
			wsExpenseMasterListForManager.setRecordsPerPageNew(20);
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return wsExpenseMasterListForManager;
	}

	private List<Integer> getexpenseMasterIds(String userid, String fromDate, String toDate, Session session) {
		try {
			Query query = session.createQuery("select expense_master_id from ExpenseMaster where user_id in(" + userid
					+ ") and created_date between '" + fromDate + "' and '" + toDate + "'");
			if (query.list() != null) {
				List<Integer> expenseMasterId = query.list();
				return expenseMasterId;
			} else {
				return null;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		}
	}

	@Override
	public double getClaimAmount(String expenseId, int user_id, int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("select total from ExpenseMaster where company_id=" + company_id
					+ " and expense_master_id=" + expenseId);

			return (double) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean checkExpenseConfiguration(String expnese_mode_type, String city_type, int c_id,int role_id) {
		Session session = initiateSession();
		// int c_id=CommonController.getCompanyId();
		try {
			Query query = session.createQuery(
					"select count(*) from ExpenseConfiguration where expense_mode_type=:expense_mode_type and  cityType=:city_type and company_id=:c_id and status=1 and user_role=:role_id")
					.setParameter("expense_mode_type", expnese_mode_type).setParameter("c_id", c_id).setParameter("role_id", role_id).setParameter("city_type", city_type);
					

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
	public boolean updateExpenseMaster(int company_id, String approveStatus, int parseInt) {
		Session session = initiateSession();
		try {
			
			
				Query query1 = session.createSQLQuery(
						"update expense_master set expenseClaimId=:st "
								+ " where expense_master_id=:lid and company_id=:cId")
						.setParameter("st", approveStatus).setParameter("lid", parseInt).setParameter("cId", company_id);
				query1.executeUpdate();

				return true;
			
			//// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		
	}

	@Override
	public boolean updateExpenseConfig(String expense_type_id, int isDefault, int isDistance, String expense_mode_type,
			double amt, String cityType, String expenseCode, String modeOfTravel, int isfixed, int expenseConfigId1,
			int c_id) {
		Session session = initiateSession();
		try {
			
			
				Query query1 = session.createSQLQuery(
						"update expense_configuration set expense_type_id=:expense_type_id ,isDefault=:isDefault,isDistance=:isDistance,expense_mode_type=:expense_mode_type, max_Allowed_Amt=:max_Allowed_Amt, cityType=:cityType,expenseCode=:expenseCode,modeOfTravel=:modeOfTravel,isfixed=:isfixed "
								+ " where expense_config_id=:expense_config_id and company_id=:cId")
						.setParameter("expense_type_id", expense_type_id).setParameter("expense_config_id", expenseConfigId1).setParameter("cId", c_id)
						.setParameter("isDefault", isDefault).setParameter("isDistance", isDistance).setParameter("expense_mode_type", expense_mode_type)
						.setParameter("max_Allowed_Amt", amt).setParameter("cityType", cityType).setParameter("expenseCode", expenseCode).setParameter("modeOfTravel", modeOfTravel).setParameter("isfixed", isfixed);
				query1.executeUpdate();

				return true;
			
			//// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		
	}

	@Override
	public List<WsExpense> expenseListNew(int c_id, String next_approver_id, String fromDate, String toDate, int u_id, String sqlCountry, String sqlState,
			String sqlCity, String sqlUser, String sqlUserName) {
		
		List<WsExpense> report = new ArrayList<>();
		Session session = initiateSession();
		try {
			
			
			CommonMethods cm=new CommonMethods();
			report=cm.expenseList(c_id,session,next_approver_id,fromDate,toDate,u_id,sqlCountry,sqlState,sqlCity,sqlUser,sqlUserName);
			
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return report;
	}

	@Override
	public List<ExpenseDetails> getDetailsDayWise(Date sDate, Date edate,  String expenseId,
			int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<ExpenseDetails> eDetails=new ArrayList<ExpenseDetails>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Query query = session.createQuery(" from ExpenseDetails where expense_master_id ="+expenseId+" and expense_date between '" + sdf.format(sDate) + "' and '" + sdf.format(edate) + "'");
			if(query.list()!=null && query.list().size()>0) {
				eDetails=query.list();
				return eDetails;
			}else {
				return eDetails;
			}
			
		}catch(Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return eDetails;
	}

	@Override
	public HashMap<String, Object> getData(List<ExpenseDetails> eDetails, List<Date> days) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result=new HashMap<String, Object>();
		List<String> eCodes=new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(int i=0;i<eDetails.size();i++) {
			
			if(!eCodes.contains(eDetails.get(i).getExpenseCode()+"-"+eDetails.get(i).getMode())) {
				eCodes.add(eDetails.get(i).getExpenseCode()+"-"+eDetails.get(i).getMode());
			}
		}
		
		LinkedHashMap<String, List<Double>> ar=new LinkedHashMap<String,List<Double>>();
		for(int j=0;j<eCodes.size();j++) {
			List<Double> amt=new ArrayList();
		for(int i=0;i<days.size();i++) {
			double amt1=0.0;
			for(int k=0;k<eDetails.size();k++) {
				try {
					if(eCodes.get(j).equals(eDetails.get(k).getExpenseCode()+"-"+eDetails.get(k).getMode()) && days.get(i).compareTo(sdf.parse(format.format(eDetails.get(k).getExpense_date())))==0){
						amt1=eDetails.get(k).getAmount();
						amt.add(eDetails.get(k).getAmount());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(amt1==0.0) {
				amt.add(0.0);
			}
		}
		double amt2=0.0;
		for(int i=0;i<amt.size();i++) {
			amt2=amt2+amt.get(i);
		}
		amt.add(amt2);
		ar.put(eCodes.get(j), amt);
		}
		
		result.put("data", ar);
		result.put("iTotalRecords", 0);
		result.put("iTotalDisplayRecords", 0);
		return result;
	}

	@Override
	public boolean updateExpenseDetails(ExpenseDetails eDetails, int expenseId, Date day, int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String q="update expense_details set approvedAmount="+eDetails.getApprovedAmount()+" , amount="+eDetails.getApprovedAmount()+" where expense_master_id="+expenseId+" and expense_date='"+format.format(day)+"' and expenseCode='"+eDetails.getExpenseCode()+"'";
			Query query=session.createSQLQuery(q);
			query.executeUpdate();

			return true;
		
		//// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
	} catch (Exception e) {
		FieldMILogger.error(this.getClass().getName(), e);
		return false;
	} finally {
		destroySession(session);
	}
	}

	@Override
	public List<Date> getUniqueExpenseDetailsDate(int eId) {

		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<Date> dList=new ArrayList<Date>();		
		try {
			Query query = session.createSQLQuery("select Distinct(expense_date) from expense_details where expense_master_id="+eId);
			if(query.list()!=null && query.list().size()>0) {
				dList=query.list();
				return dList;
			}else {
				return dList;
			}
			
		}catch(Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		}finally {
			destroySession(session);
		}
		return dList;
	
	}

	

}
