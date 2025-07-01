package com.fieldmi.daoImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.query.Query;
import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMBeatPlanDao;
import com.fieldmi.service.FMWorkflowConfigurationService;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.fieldmi.stubs.ReturnDate;
import com.fieldmi.stubs.WsBeat;
import com.fieldmi.stubs.WsBeatList;
import com.fieldmi.stubs.WsBeatMasterList;
import com.fieldmi.stubs.WsExpense;
import com.fieldmi.stubs.WsExpenseDetails;
import com.fieldmi.stubs.WsSalesDetailsReportInput;
import com.fieldmi.stubs.WsSalesOrderReport;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.fieldmi.stubs.WsSalesReportOutput;
import com.fieldmi.stubs.WsUpdateBeat;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.SalesMIDBUtils;
import com.softtantra.salesapp.pojo.BeatPlanDetails;
import com.softtantra.salesapp.pojo.BeatPlanMaster;
import com.softtantra.salesapp.pojo.BeatPlanMasterDetails;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.ExpenseDetails;
import com.softtantra.salesapp.pojo.ExpenseMaster;
import com.softtantra.salesapp.pojo.Route;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowAuditLog;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.WsAddedBeatPlanList;
import com.softtantra.ws.WsBeatInputCustomerWise;
import com.softtantra.ws.WsBeatInputMasterDetails;
import com.softtantra.ws.WsBeatPlanDetails;
import com.softtantra.ws.WsBeatPlanDetailsCustomerWise;
import com.softtantra.ws.WsBeatPlanDetailsInput;
import com.softtantra.ws.WsBeatPlanInput;

public class FMBeatPlanDaoImpl implements FMBeatPlanDao {

	int no_of_records_per_page = 20;

	CommonMethods commonMethods = new CommonMethods();

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

	public void setWorkFlowTaskStatusService(WorkFlowTaskStatusService workFlowTaskStatusService) {
		this.workFlowTaskStatusService = workFlowTaskStatusService;
	}

	@Autowired
	WorkFlowAuditLogService workFlowAuditLogService;

	public void setWorkFlowAuditLogService(WorkFlowAuditLogService workFlowAuditLogService) {
		this.workFlowAuditLogService = workFlowAuditLogService;
	}

	@Override
	public BeatPlanMaster addBeatPlanDistributorWise(WsBeatPlanInput wsBeatPlanInput, int id) {
		// TODO Auto-generated method stub

		Session dbSession = initiateSession();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		List<WsBeatInputMasterDetails> wsBeatPlanDetailsCustomerWise = wsBeatPlanInput.getWsBeatInputMasterDetails();
		BeatPlanMasterDetails beatPlanMasterDetails = null;
		try {

			if (wsBeatPlanInput.getDistributor_id() != null && wsBeatPlanInput.getPlanDate() != null
					&& wsBeatPlanInput.getPlanDate().trim().length() > 0 && wsBeatPlanInput.getDistributor_id().trim().length() > 0) {

				BeatPlanMaster beatPlanMaster = null;
				Query<BeatPlanMaster> queryBM = dbSession
						.createQuery(
								" from BeatPlanMaster ed where DATE(ed.beatPlanDate)='"
										+ sdf1.format(sdf.parse(wsBeatPlanInput.getPlanDate()))
										+ "' and ed.status=1 and ed.distributor_id="
										+ wsBeatPlanInput.getDistributor_id() + " and ed.user_id=:id and ed.route_id=0",
								BeatPlanMaster.class)
						.setParameter("id", id);

				List<BeatPlanMaster> existingMasterlist = queryBM.list();
				if (existingMasterlist != null && existingMasterlist.size() > 0) {

					beatPlanMaster = existingMasterlist.get(0);
					beatPlanMaster.setUpdated(true);
				} else {

					beatPlanMaster = new BeatPlanMaster();
					beatPlanMaster.setCreated_date(new Date());
					beatPlanMaster.setUpdated_date(new Date());
					beatPlanMaster.setUpdated(false);
				}

				beatPlanMaster.setBeatPlanDate(sdf.parse(wsBeatPlanInput.getPlanDate()));
				beatPlanMaster.setCompany_id(wsBeatPlanInput.getCompany_id());
				if (wsBeatPlanInput.getDistributor_id() != null
						&& wsBeatPlanInput.getDistributor_id().trim().length() > 0)
					beatPlanMaster.setDistributor_id(Integer.parseInt(wsBeatPlanInput.getDistributor_id()));
				else
					beatPlanMaster.setDistributor_id(0);
				beatPlanMaster.setRemark(wsBeatPlanInput.getRemark());
				beatPlanMaster.setCallTarget(0);
				beatPlanMaster.setCreated_by(id);
				beatPlanMaster.setUpdated_by(id);
				beatPlanMaster.setUser_id(id);
				beatPlanMaster.setStatus(1);
				beatPlanMaster.setJourneyType(wsBeatPlanInput.getJourneyType());
				beatPlanMaster.setModeOfTravel(wsBeatPlanInput.getModeOfTravel());
				beatPlanMaster.setRoute_id(0);
				if (wsBeatPlanInput.getCoTravellers() != null) {
					beatPlanMaster.setCoTravellers(wsBeatPlanInput.getCoTravellers());
				}
				beatPlanMaster.setCity(wsBeatPlanInput.getCity());
				dbSession.saveOrUpdate(beatPlanMaster);

				if (wsBeatPlanDetailsCustomerWise != null) {

					for (WsBeatInputMasterDetails list : wsBeatPlanDetailsCustomerWise) {

						beatPlanMasterDetails = new BeatPlanMasterDetails();

						beatPlanMasterDetails.setDistributor_id(list.getDistributor_id());
						beatPlanMasterDetails.setRoute_id(list.getRoute_id());
						beatPlanMasterDetails.setBeatPlanMasterId(beatPlanMaster.getBeatPlanMasterId());
						beatPlanMasterDetails.setRouteName(getRouteAndCustomerName(list.getRoute_id(), 1));
						beatPlanMasterDetails.setDistributrorName(getRouteAndCustomerName(list.getDistributor_id(), 3));
						beatPlanMasterDetails.setStatus(1);
						beatPlanMasterDetails.setCompany_id(wsBeatPlanInput.getCompany_id());
						dbSession.save(beatPlanMasterDetails);

					}
				}

				return beatPlanMaster;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(dbSession);
		}
		return null;
	}

	@Override
	public WsBeat getBeatMaster(int bId) {

		Session session = initiateSession();
		WsBeat wsBeatMaster = new WsBeat();
		List<WsBeatList> WsBeatDetailsList = new ArrayList<WsBeatList>();
		List<WsBeatInputMasterDetails> wsBeatInputMasterDetails1 = new ArrayList<WsBeatInputMasterDetails>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss EEEEE");

			Query query = session
					.createQuery("from BeatPlanMaster e where e.beatPlanMasterId=" + bId + " and e.status=1");

			BeatPlanMaster beatMaster = (BeatPlanMaster) query.uniqueResult();

			wsBeatMaster.setJourneyType(beatMaster.getJourneyType());
			wsBeatMaster.setModeOfTravel(beatMaster.getModeOfTravel());
			wsBeatMaster.setDistributor_id(beatMaster.getDistributor_id());
			wsBeatMaster.setCreated_date(sdf.format(beatMaster.getCreated_date()));
			wsBeatMaster.setBeatPlanDate((sdf.format(beatMaster.getBeatPlanDate())));
			wsBeatMaster.setUser_id(beatMaster.getUser_id());
			wsBeatMaster.setBeatPlanMasterId(beatMaster.getBeatPlanMasterId());
			wsBeatMaster.setRoute_id(beatMaster.getRoute_id());
			wsBeatMaster.setCoTravellers(beatMaster.getCoTravellers());
			wsBeatMaster.setCity(beatMaster.getCity());
			// FieldMILogger.debug(this.getClass().getName(),"expenseMaster.getApprovedamount()"+expenseMaster.getApprovedamount());
			Query query1 = session.createQuery(" from BeatPlanDetails ed where ed.beatPlanMasterId="
					+ wsBeatMaster.getBeatPlanMasterId() + " and ed.status=1");

			List<BeatPlanDetails> list = query1.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {

				BeatPlanDetails beatPlanDetails = (BeatPlanDetails) iterator.next();

				WsBeatList beatList = new WsBeatList();
				beatList.setBeat_plan_id(beatPlanDetails.getBeat_plan_id());
				beatList.setBeat_plan_reason_id(beatPlanDetails.getBeat_plan_id());
				beatList.setCreated_date(sdf.format(beatPlanDetails.getCreated_date()));
				beatList.setCustomer_id(beatPlanDetails.getCustomer_id());
				beatList.setDistributor_id(beatPlanDetails.getDistributor_id());
				beatList.setRoute_id(beatPlanDetails.getRoute_id());
				String routeName = getRouteAndCustomerName(beatPlanDetails.getRoute_id(), 1);
				String customerName = getRouteAndCustomerName(beatPlanDetails.getCustomer_id(), 2);
				beatList.setDistributorName(getRouteAndCustomerName(beatPlanDetails.getDistributor_id(), 3));
				beatList.setRouteName(routeName);
				beatList.setCustomerName(customerName);
				WsBeatDetailsList.add(beatList);

			}

			String query3 = "select beatPlanMasterDetailsId,beatPlanMasterId,route_id,distributor_id,distributrorName,routeName from BeatPlanMasterDetails where company_id="
					+ beatMaster.getCompany_id() + " and beatPlanMasterId=" + beatMaster.getBeatPlanMasterId();
			Query q1 = session.createQuery(query3);
			List<BeatPlanMasterDetails> list3 = q1.list();
			for (Object object1 : list3) {
				Object[] obj1 = (Object[]) object1;
				WsBeatInputMasterDetails wsBeatInputMasterDetails = new WsBeatInputMasterDetails();
				wsBeatInputMasterDetails.setDistributor_id((Integer) obj1[3]);
				wsBeatInputMasterDetails.setRoute_id((Integer) obj1[2]);
				wsBeatInputMasterDetails.setDistributorName((String) obj1[4]);
				wsBeatInputMasterDetails.setRouteName((String) obj1[5]);
				wsBeatInputMasterDetails1.add(wsBeatInputMasterDetails);
			}

			wsBeatMaster.setBeatDetails(WsBeatDetailsList);
			wsBeatMaster.setWsBeatInputMasterDetails(wsBeatInputMasterDetails1);
			return wsBeatMaster;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	private String getRouteAndCustomerName(int route_id, int i) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		Query query = null;
		try {

			if (i == 1) {
				query = session
						.createQuery("select route_name from Route where route_id=" + route_id + " and status=1");
				return (String) query.uniqueResult();
			} else if (i == 2) {
				query = session.createQuery(
						"select customer_name from CustomerDetails where customer_id=" + route_id + " and status=1");
				return (String) query.uniqueResult();
			} else if (i == 3) {
				query = session.createQuery("select distributor_name from DistributorDetails where distributor_id="
						+ route_id + " and status=1");
				return (String) query.uniqueResult();
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public int addBeatPlanDetailsDistributorWise(WsBeatPlanDetailsInput wsBeatPlanDetailsInput, int id,
			List<BeatPlanDetails> beatPlanDetailsList) throws Exception {
		// TODO Auto-generated method stub

		Session dbSession = initiateSession();
		try {

			Query<BeatPlanDetails> query1 = dbSession.createQuery(" from BeatPlanDetails ed where ed.beatPlanMasterId="
					+ wsBeatPlanDetailsInput.getBeatplanMasterId() + " and ed.status=1", BeatPlanDetails.class);

			List<BeatPlanDetails> existingDetailslist = query1.list();
			if (wsBeatPlanDetailsInput.getDistributor_id() != 0 && wsBeatPlanDetailsInput.getCustomer_id() != 0) {

				// check if beat plan details exists
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				BeatPlanDetails beatPlanDetails = null;
				if (existingDetailslist != null && existingDetailslist.size() > 0) {

					for (BeatPlanDetails existingBeatPlanDetails : existingDetailslist) {

						if (existingBeatPlanDetails.getCustomer_id() == wsBeatPlanDetailsInput.getCustomer_id()
								&& existingBeatPlanDetails.getUser_id() == id
								&& sdf.format(existingBeatPlanDetails.getBeat_plan_date())
										.equals(sdf.format(Calendar.getInstance().getTime()))) {

							beatPlanDetails = existingBeatPlanDetails;
							beatPlanDetails.setUpdated(true);
							break;
						}

					}

				}

				// if does not exists create a new one
				if (beatPlanDetails == null) {

					beatPlanDetails = new BeatPlanDetails();
					beatPlanDetails.setCreated_date(new Date());
					beatPlanDetails.setUpdated_date(new Date());
					beatPlanDetails.setUpdated(false);
				}

				beatPlanDetails.setCustomer_id(wsBeatPlanDetailsInput.getCustomer_id());
				beatPlanDetails.setDistributor_id(wsBeatPlanDetailsInput.getDistributor_id());
				beatPlanDetails.setRoute_id(wsBeatPlanDetailsInput.getRoute_id());
				beatPlanDetails.setBeatPlanMasterId(wsBeatPlanDetailsInput.getBeatplanMasterId());
				beatPlanDetails.setCreated_by(id);
				beatPlanDetails.setUpdated_by(id);
				beatPlanDetails.setUser_id(id);
				beatPlanDetails.setStatus(1);
				beatPlanDetails.setCompany_id(wsBeatPlanDetailsInput.getCompany_id());
				beatPlanDetails.setAchived_target(0.0);
				beatPlanDetails.setBeat_plan_date(new Date());
				beatPlanDetails.setReason("");
				beatPlanDetails.setBeat_plan_reason("");
				beatPlanDetails.setBeat_plan_reason_id(0);
				beatPlanDetails.setSequence(1);
				beatPlanDetails.setCollection_achived_target(0.0);
				beatPlanDetails.setCollection_target(0.0);
				beatPlanDetails.setSales_target_type("");
				beatPlanDetails.setUnit("");
				dbSession.saveOrUpdate(beatPlanDetails);

				int beatid = beatPlanDetails.getBeat_plan_id();
				beatPlanDetailsList.add(beatPlanDetails);
				return beatid;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(dbSession);
		}
		return 0;
	}

	@Override
	public BeatPlanMaster addBeatPlanDetailsCustomerWise(WsBeatInputCustomerWise wsBeatInputCustomerWise, int id,
			List<BeatPlanDetails> beatPlanDetailsList, BeatPlanMaster beatPlanMaster) throws Exception {

		Session dbSession = initiateSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		List<WsBeatPlanDetailsCustomerWise> wsBeatPlanDetailsCustomerWise = wsBeatInputCustomerWise
				.getWsBeatPlanDetailsCustomerWise();
		List<WsBeatInputMasterDetails> wsBeatPlanMasterDetailsCustomerWise = wsBeatInputCustomerWise
				.getWsBeatInputMasterDetails();

		try {
			Query<BeatPlanMaster> queryBM = dbSession.createQuery(
					" from BeatPlanMaster ed where DATE(ed.beatPlanDate)='"
							+ sdf1.format(sdf.parse(wsBeatInputCustomerWise.getPlanDate()))
							+ "' and ed.status=1 and ed.distributor_id=" + wsBeatInputCustomerWise.getDistributor_id()
							+ " and ed.user_id=" + id + " and ed.route_id=" + wsBeatInputCustomerWise.getRoute_id(),
					BeatPlanMaster.class);

			List<BeatPlanMaster> existingMasterlist = queryBM.list();
			if (existingMasterlist != null && existingMasterlist.size() > 0) {

				beatPlanMaster = existingMasterlist.get(0);
				beatPlanMaster.setUpdated(true);
			} else {

				beatPlanMaster = new BeatPlanMaster();
				beatPlanMaster.setCreated_date(new Date());
				beatPlanMaster.setUpdated_date(new Date());
				beatPlanMaster.setUpdated(false);
			}

			beatPlanMaster.setBeatPlanDate(sdf.parse(wsBeatInputCustomerWise.getPlanDate()));
			beatPlanMaster.setCompany_id(wsBeatInputCustomerWise.getCompany_id());
			beatPlanMaster.setDistributor_id(wsBeatInputCustomerWise.getDistributor_id());
			beatPlanMaster.setRemark(wsBeatInputCustomerWise.getRemark());
			beatPlanMaster.setCallTarget(0);
			beatPlanMaster.setCreated_by(id);
			beatPlanMaster.setUpdated_by(id);
			beatPlanMaster.setUser_id(id);
			beatPlanMaster.setStatus(1);
			beatPlanMaster.setJourneyType(wsBeatInputCustomerWise.getJourneyType());
			beatPlanMaster.setModeOfTravel(wsBeatInputCustomerWise.getModeOfTravel());
			beatPlanMaster.setRoute_id(wsBeatInputCustomerWise.getRoute_id());
			if (wsBeatInputCustomerWise.getCoTravellers() != null) {
				beatPlanMaster.setCoTravellers(wsBeatInputCustomerWise.getCoTravellers());
			}
			beatPlanMaster.setCity(wsBeatInputCustomerWise.getCity());
			dbSession.saveOrUpdate(beatPlanMaster);

			Query<BeatPlanDetails> query1 = dbSession.createQuery(" from BeatPlanDetails ed where ed.beatPlanMasterId="
					+ beatPlanMaster.getBeatPlanMasterId() + " and ed.status=1", BeatPlanDetails.class);

			List<BeatPlanDetails> existingDetailslist = query1.list();
			for (WsBeatPlanDetailsCustomerWise list : wsBeatPlanDetailsCustomerWise) {

				BeatPlanDetails beatPlanDetails = null;

				if (existingDetailslist != null && existingDetailslist.size() > 0) {

					Date planDate = sdf.parse(wsBeatInputCustomerWise.getPlanDate());
					for (BeatPlanDetails existingBeatPlanDetails : existingDetailslist) {

						if (existingBeatPlanDetails.getCustomer_id() == list.getCustomer_id()
								&& existingBeatPlanDetails.getUser_id() == id
								&& sdf1.format(existingBeatPlanDetails.getBeat_plan_date())
										.equals(sdf1.format(planDate))) {

							beatPlanDetails = existingBeatPlanDetails;
							beatPlanDetails.setUpdated(true);
							break;
						}
					}
				}

				// if does not exists create a new one
				if (beatPlanDetails == null) {

					beatPlanDetails = new BeatPlanDetails();
					beatPlanDetails.setCreated_date(new Date());
					beatPlanDetails.setUpdated_date(new Date());
					beatPlanDetails.setUpdated(false);
				}

				beatPlanDetails.setCustomer_id(list.getCustomer_id());
				beatPlanDetails.setDistributor_id(list.getDistributor_id());
				beatPlanDetails.setRoute_id(list.getRoute_id());
				beatPlanDetails.setBeatPlanMasterId(beatPlanMaster.getBeatPlanMasterId());
				beatPlanDetails.setCreated_by(id);
				beatPlanDetails.setUpdated_by(id);
				beatPlanDetails.setUser_id(id);
				beatPlanDetails.setStatus(1);
				beatPlanDetails.setCompany_id(wsBeatInputCustomerWise.getCompany_id());
				beatPlanDetails.setAchived_target(0.0);
				beatPlanDetails.setBeat_plan_date(sdf.parse(wsBeatInputCustomerWise.getPlanDate()));
				beatPlanDetails.setReason("");
				beatPlanDetails.setBeat_plan_reason("");
				beatPlanDetails.setBeat_plan_reason_id(0);
				beatPlanDetails.setSequence(1);
				beatPlanDetails.setCollection_achived_target(0.0);
				beatPlanDetails.setCollection_target(0.0);
				beatPlanDetails.setSales_target_type("");
				beatPlanDetails.setUnit("");
				dbSession.saveOrUpdate(beatPlanDetails);

				beatPlanDetailsList.add(beatPlanDetails);
			}
			for (WsBeatInputMasterDetails list2 : wsBeatPlanMasterDetailsCustomerWise) {

				BeatPlanMasterDetails beatPlanMasterDetails = new BeatPlanMasterDetails();

				beatPlanMasterDetails.setDistributor_id(list2.getDistributor_id());
				beatPlanMasterDetails.setRoute_id(list2.getRoute_id());
				beatPlanMasterDetails.setBeatPlanMasterId(beatPlanMaster.getBeatPlanMasterId());
				beatPlanMasterDetails.setRouteName(getRouteAndCustomerName(list2.getRoute_id(), 1));
				beatPlanMasterDetails.setDistributrorName(getRouteAndCustomerName(list2.getDistributor_id(), 3));
				beatPlanMasterDetails.setStatus(1);
				beatPlanMasterDetails.setCompany_id(wsBeatInputCustomerWise.getCompany_id());
				dbSession.save(beatPlanMasterDetails);

			}

			return beatPlanMaster;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(dbSession);
		}
		return null;
	}

	@Override
	public BeatPlanMaster getBeatPieChartData(int c_id, int u_id, String value) {
		Session session = initiateSession();
		try {
			long pending, approved, submitted, rejected = 0;
			if (value.equals("my_list")) {
				Query query = session.createQuery(
						"select count(*) from BeatPlanMaster e JOIN WorkFlowTasksStatus w on e.beatPlanMasterId=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Pending'  and w.created_by=" + u_id);
				pending = (long) query.uniqueResult();

				Query query1 = session.createQuery(
						"select count(*) from BeatPlanMaster e JOIN WorkFlowTasksStatus w on e.beatPlanMasterId=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Approved'  and w.created_by=" + u_id);
				approved = (long) query1.uniqueResult();

				Query query2 = session.createQuery(
						"select count(*) from BeatPlanMaster e JOIN WorkFlowTasksStatus w on e.beatPlanMasterId=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='To be submitted'  and w.created_by=" + u_id);
				submitted = (long) query2.uniqueResult();

				Query query3 = session.createQuery(
						"select count(*) from BeatPlanMaster e JOIN WorkFlowTasksStatus w on e.beatPlanMasterId=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Rejected'  and w.created_by=" + u_id);
				rejected = (long) query3.uniqueResult();

				BeatPlanMaster details = new BeatPlanMaster();
				details.setApproved(approved);
				details.setPending(pending);
				details.setRejected(rejected);
				details.setSubmitted(submitted);

				return details;
			} else {
				Query query = session.createQuery(
						"select count(*) from BeatPlanMaster e JOIN WorkFlowTasksStatus w on e.beatPlanMasterId=w.sales_master_id JOIN User u on e.user_id=u.user_id  where e.user_id=u.user_id and e.status<>0 and e.company_id="
								+ c_id + " and w.task_status='Pending'  and w.next_approver_id=" + u_id);
				pending = (long) query.uniqueResult();

				BeatPlanMaster details = new BeatPlanMaster();
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
	public WsBeatMasterList getBeatPlanListWithMaster(Credentials creadentials) throws Exception {
		// TODO Auto-generated method stub
		Session dbsession = initiateSession();
		WsBeatMasterList wsBeatMasterList = new WsBeatMasterList();
		WsBeat wsBeat = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WsBeatList wsBeatPlanDetails = null;
		WsBeatInputMasterDetails wsBeatInputMasterDetails = null;

		String lastDate = creadentials.getLast_date();
		List list = null;
		List list2 = null;
		List list3 = null;
		String query = "";
		String totalCountquery = "";
		String lastUpdateQuery = "";
		int total_pages = 0;

		if (lastDate != null && lastDate.trim().length() > 0) {

			String updatedDate = ReturnDate.UTCDateTime(creadentials.getLast_date());
			lastUpdateQuery = " and updated_date > '" + updatedDate + "' ";
		}

		query = "select beatPlanMasterId,distributor_id,beatPlanDate,journeyType,modeOfTravel,user_id,approvalStatus,created_date,startLat,startLong,endLat,endLongi,status,coTravellers,city from BeatPlanMaster where user_id="
				+ creadentials.getUser_id() + " and company_id=" + creadentials.getCompany_id() + " and status=1"
				+ lastUpdateQuery;

		totalCountquery = "select count(*) from BeatPlanMaster where user_id=" + creadentials.getUser_id()
				+ " and company_id=" + creadentials.getCompany_id() + " and status=1" + lastUpdateQuery;

		try {

			list = commonMethods.quaryListForPagination(dbsession, query, creadentials);
			total_pages = commonMethods.getTotalPagesCountHql(dbsession, totalCountquery);
			for (Object object : list) {

				Object[] obj = (Object[]) object;

				wsBeat = new WsBeat();
				wsBeat.setBeatPlanMasterId((Integer) obj[0]);
				wsBeat.setJourneyType((String) obj[3]);
				wsBeat.setModeOfTravel((String) obj[4]);
				wsBeat.setDistributor_id((int) obj[1]);
				wsBeat.setCreated_date(sdf.format((Date) obj[7]));
				wsBeat.setBeatPlanDate((sdf.format((Date) obj[2])));
				wsBeat.setUser_id((Integer) obj[5]);
				wsBeat.setStartLat((String) obj[8]);
				wsBeat.setStartLong((String) obj[9]);
				wsBeat.setEndLat((String) obj[10]);
				wsBeat.setEndLongi((String) obj[11]);
				wsBeat.setStatus((Integer) obj[12]);
				if ((String) obj[13] != null) {
					wsBeat.setCoTravellers((String) obj[13]);
				}
				wsBeat.setCity((String) obj[14]);
				// wsBeat.setCoTravellers(totalCountquery);
				String finalStatus = workFlowTaskStatusService.getFinalSalesOrderStatus(
						creadentials.getCompany_id().intValue(), (Integer) obj[0], SalesMIDBUtils.BEAT_PLAN_NAME);

				wsBeat.setApproveStatus(finalStatus);

				String query2 = "select beat_plan_id,customer_id,route_id,distributor_id,status from BeatPlanDetails where company_id="
						+ creadentials.getCompany_id() + " and beatPlanMasterId=" + (Integer) obj[0];
				Query q = dbsession.createQuery(query2);
				list2 = q.list();
				for (Object object1 : list2) {
					Object[] obj1 = (Object[]) object1;
					wsBeatPlanDetails = new WsBeatList();
					wsBeatPlanDetails.setBeat_plan_id((Integer) obj1[0]);
					wsBeatPlanDetails.setCustomer_id((Integer) obj1[1]);
					wsBeatPlanDetails.setDistributor_id((Integer) obj1[3]);
					wsBeatPlanDetails.setRoute_id((Integer) obj1[2]);
					wsBeatPlanDetails.setStatus((Integer) obj1[4]);
					String routeName = getRouteAndCustomerName((Integer) obj1[2], 1);
					String customerName = getRouteAndCustomerName((Integer) obj1[1], 2);
					wsBeatPlanDetails.setRouteName(routeName);
					wsBeatPlanDetails.setCustomerName(customerName);
					wsBeat.getBeatDetails().add(wsBeatPlanDetails);
				}

				String query3 = "select beatPlanMasterDetailsId,beatPlanMasterId,route_id,distributor_id,distributrorName,routeName from BeatPlanMasterDetails where company_id="
						+ creadentials.getCompany_id() + " and beatPlanMasterId=" + (Integer) obj[0];
				Query q1 = dbsession.createQuery(query3);
				list3 = q1.list();
				for (Object object1 : list3) {
					Object[] obj1 = (Object[]) object1;
					wsBeatInputMasterDetails = new WsBeatInputMasterDetails();
					wsBeatInputMasterDetails.setDistributor_id((Integer) obj1[3]);
					wsBeatInputMasterDetails.setRoute_id((Integer) obj1[2]);
					wsBeatInputMasterDetails.setDistributorName((String) obj1[4]);
					wsBeatInputMasterDetails.setRouteName((String) obj1[5]);

					wsBeat.getWsBeatInputMasterDetails().add(wsBeatInputMasterDetails);
				}

				wsBeatMasterList.getWsbeat().add(wsBeat);
			}

			wsBeatMasterList.setTotalRecords(total_pages);
			wsBeatMasterList.setPage_No(creadentials.getPage_no());
			wsBeatMasterList.setRecordsPerPage(20);

			return wsBeatMasterList;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(dbsession);
		}

	}

	@Override
	public boolean updateBeatPlanMaster(WsUpdateBeat wsupdateBeat) throws Exception {

		Session session = initiateSession();
		try {

			Query query = session.createQuery("from BeatPlanMaster where beatPlanMasterId="
					+ wsupdateBeat.getBeatPlanMasterId() + " and company_id=" + wsupdateBeat.getCompanyId());

			BeatPlanMaster beatPlanMaster = (BeatPlanMaster) query.uniqueResult();
			if (beatPlanMaster != null && wsupdateBeat.getBeatPlanMasterId() != 0) {
				if (wsupdateBeat.getType() == 1) {
					beatPlanMaster.setJourneyType(wsupdateBeat.getJourneyType());
					beatPlanMaster.setModeOfTravel(wsupdateBeat.getModeOfTravel());
					beatPlanMaster.setStartLat(wsupdateBeat.getStartLat());
					beatPlanMaster.setStartLong(wsupdateBeat.getStartLongi());
					beatPlanMaster.setCallTarget(wsupdateBeat.getCallTarget());
					beatPlanMaster.setUpdated_date(new Date());
					if (wsupdateBeat.getCoTravellers() != null) {
						beatPlanMaster.setCoTravellers(wsupdateBeat.getCoTravellers());
					}

					session.update(beatPlanMaster);
					return true;
				} else {
					beatPlanMaster.setEndLat(wsupdateBeat.getStartLat());
					beatPlanMaster.setEndLongi(wsupdateBeat.getStartLongi());
					beatPlanMaster.setUpdated_date(new Date());
					beatPlanMaster.setExpenseMasterId(wsupdateBeat.getExpenseMasterId());
					session.update(beatPlanMaster);
					return true;
				}

			}
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return false;
	}

	@Override
	public WsBeatMasterList getBeatMasterReportForSil(WsSalesOrderReportInput wsSalesOrderReportInput, int id) {

		Session session = initiateSession();
		WsBeatMasterList wSalesReportOutput = new WsBeatMasterList();
		List<WsBeat> report = new ArrayList<>();
		List list2 = null;
		List list3 = null;
		String distributorName = "";
		WsBeatList wsBeatPlanDetails = null;
		WsBeatInputMasterDetails wsBeatInputMasterDetails = null;
		try {

			String sql = "";
			List list = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String user_list = wsSalesOrderReportInput.getUser_list();

			String targetMonth = wsSalesOrderReportInput.getTarget_month();
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(targetMonth));
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR, 00);
			cal.set(Calendar.MINUTE, 00);
			cal.set(Calendar.SECOND, 00);
			Date monthStart = cal.getTime();

			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			Date monthEnd = cal.getTime();

			SimpleDateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fromDate = dfTime.format(monthStart);
			String toDate = dfTime.format(monthEnd);

			List<Integer> users = commonMethods.getUserHirachyWise(wsSalesOrderReportInput.getCompany_id(), id, session,
					"", "", "", "");

			String userid = "";
			for (int i = 0; i < users.size(); i++) {
				userid = userid + users.get(i).intValue() + ",";

			}
			if (userid != null && userid.length() > 0 && userid.charAt(userid.length() - 1) == ',') {
				userid = userid.substring(0, userid.length() - 1);
			}
			if (user_list == null || user_list == "") {

				if (userid != null && userid.length() > 0) {

					StringBuilder query = new StringBuilder();
					query.append(
							"select beatPlanMasterId,beat_plan_master.distributor_id,beatPlanDate,journeyType,modeOfTravel,beat_plan_master.user_id,approvalStatus,beat_plan_master.created_date,concat(first_name,' ',last_name) as user_name,beat_plan_master.city,coTravellers   from beat_plan_master ");
					query.append(" inner join user_details  on beat_plan_master.user_id=user_details.user_id  ");
					query.append(" where beat_plan_master.company_id=" + wsSalesOrderReportInput.getCompany_id()
							+ " and beat_plan_master.user_id IN (" + userid
							+ ") and beat_plan_master.created_date between '" + fromDate + "' and '" + toDate
							+ "' and beat_plan_master.status=1  order by beat_plan_master.created_date desc");

					sql = "select count(*) from beat_plan_master sm inner join user_details c on c.user_id=sm.user_id where sm.company_id="
							+ wsSalesOrderReportInput.getCompany_id() + " and sm.user_id IN (" + userid
							+ ") and sm.created_date between '" + fromDate + "' and '" + toDate + "' and sm.status=1 ";

					list = commonMethods.quarySQLListPagination(session, query.toString(),
							wsSalesOrderReportInput.getPage_no(), no_of_records_per_page);
					commonMethods.getTotalPagesCountSql(session, sql);
					for (Object object : list) {
						WsBeat wsBeat = new WsBeat();
						Object[] obj = (Object[]) object;
						wsBeat = new WsBeat();
						wsBeat.setBeatPlanMasterId((Integer) obj[0]);
						wsBeat.setJourneyType((String) obj[3]);
						wsBeat.setModeOfTravel((String) obj[4]);
						wsBeat.setDistributor_id((int) obj[1]);
						wsBeat.setCreated_date(df.format((Date) obj[7]));
						wsBeat.setBeatPlanDate((df.format((Date) obj[2])));
						wsBeat.setUser_id((Integer) obj[5]);
						wsBeat.setUser_name((String) obj[8]);
						wsBeat.setCity((String) obj[9]);
						if ((String) obj[10] != null) {
							wsBeat.setCoTravellers((String) obj[10]);
						} else {
							wsBeat.setCoTravellers("");
						}
						distributorName = getRouteAndCustomerName((Integer) obj[1], 3);
						wsBeat.setDistributorName(distributorName);
						String finalStatus = workFlowTaskStatusService.getFinalSalesOrderStatus(
								wsSalesOrderReportInput.getCompany_id(), (Integer) obj[0],
								SalesMIDBUtils.BEAT_PLAN_NAME);

						wsBeat.setApproveStatus(finalStatus);
						String query2 = "select beat_plan_id,customer_id,route_id,distributor_id,status from BeatPlanDetails where company_id="
								+ wsSalesOrderReportInput.getCompany_id() + " and beatPlanMasterId=" + (Integer) obj[0]
								+ "";
						Query q = session.createQuery(query2);
						list2 = q.list();
						for (Object object1 : list2) {
							Object[] obj1 = (Object[]) object1;
							wsBeatPlanDetails = new WsBeatList();
							wsBeatPlanDetails.setBeat_plan_id((Integer) obj1[0]);
							wsBeatPlanDetails.setCustomer_id((Integer) obj1[1]);
							wsBeatPlanDetails.setDistributor_id((Integer) obj1[3]);
							wsBeatPlanDetails.setRoute_id((Integer) obj1[2]);
							String routeName = getRouteAndCustomerName((Integer) obj1[2], 1);
							String customerName = getRouteAndCustomerName((Integer) obj1[1], 2);
							distributorName = getRouteAndCustomerName((Integer) obj1[3], 3);
							wsBeatPlanDetails.setRouteName(routeName);
							wsBeatPlanDetails.setCustomerName(customerName);
							wsBeatPlanDetails.setDistributorName(distributorName);
							wsBeatPlanDetails.setStatus((Integer) obj1[4]);
							wsBeat.getBeatDetails().add(wsBeatPlanDetails);
						}

						String query3 = "select beatPlanMasterDetailsId,beatPlanMasterId,route_id,distributor_id,distributrorName,routeName from BeatPlanMasterDetails where company_id="
								+ wsSalesOrderReportInput.getCompany_id() + " and beatPlanMasterId=" + (Integer) obj[0];
						Query q1 = session.createQuery(query3);
						list3 = q1.list();
						for (Object object1 : list3) {
							Object[] obj1 = (Object[]) object1;
							wsBeatInputMasterDetails = new WsBeatInputMasterDetails();
							wsBeatInputMasterDetails.setDistributor_id((Integer) obj1[3]);
							wsBeatInputMasterDetails.setRoute_id((Integer) obj1[2]);

							wsBeatInputMasterDetails.setDistributorName((String) obj1[4]);
							wsBeatInputMasterDetails.setRouteName((String) obj1[5]);
							wsBeat.getWsBeatInputMasterDetails().add(wsBeatInputMasterDetails);
						}
						if (finalStatus.startsWith(SalesMIDBUtils.PENDING)) {
							report.add(wsBeat);
						}
					}
					wSalesReportOutput.setWsbeat(report);
					wSalesReportOutput.setTotalRecords(report.size());
					wSalesReportOutput.setRecordsPerPage(no_of_records_per_page);
					wSalesReportOutput.setPage_No(wsSalesOrderReportInput.getPage_no());

					return wSalesReportOutput;
				}
			} else {

				if (userid != null && userid.length() > 0) {

					String[] userArray = user_list.split(",");

					String userid1 = user_list;

					List<String> user_ids = Arrays.asList(userArray);
					for (String str : user_ids) {
						FieldMILogger.debug(this.getClass().getName(), str);
					}

					StringBuilder query = new StringBuilder();
					query.append(
							"select beatPlanMasterId,beat_plan_master.distributor_id,beatPlanDate,journeyType,modeOfTravel,beat_plan_master.user_id,approvalStatus,beat_plan_master.created_date,concat(first_name,' ',last_name) as user_name ,beat_plan_master.city,coTravellers  from beat_plan_master ");
					query.append(" inner join user_details  on beat_plan_master.user_id=user_details.user_id  ");
					query.append(" where beat_plan_master.company_id=" + wsSalesOrderReportInput.getCompany_id()
							+ " and beat_plan_master.user_id IN (" + userid1
							+ ") and beat_plan_master.created_date between '" + fromDate + "' and '" + toDate
							+ "' and beat_plan_master.status=1  order by beat_plan_master.created_date desc");

					sql = "select count(*) from beat_plan_master sm inner join user_details c on c.user_id=sm.user_id where sm.company_id="
							+ wsSalesOrderReportInput.getCompany_id() + " and sm.user_id IN (" + userid1
							+ ") and sm.created_date between '" + fromDate + "' and '" + toDate + "' and sm.status=1 ";

					list = commonMethods.quarySQLListPagination(session, query.toString(),
							wsSalesOrderReportInput.getPage_no(), no_of_records_per_page);
					for (Object object : list) {

						WsBeat wsBeat = new WsBeat();
						Object[] obj = (Object[]) object;
						wsBeat = new WsBeat();
						wsBeat.setBeatPlanMasterId((Integer) obj[0]);
						wsBeat.setJourneyType((String) obj[3]);
						wsBeat.setModeOfTravel((String) obj[4]);
						wsBeat.setDistributor_id((int) obj[1]);
						wsBeat.setCreated_date(df.format((Date) obj[7]));
						wsBeat.setBeatPlanDate((df.format((Date) obj[2])));
						wsBeat.setUser_id((Integer) obj[5]);
						wsBeat.setUser_name((String) obj[8]);
						wsBeat.setCity((String) obj[9]);
						if ((String) obj[10] != null) {
							wsBeat.setCoTravellers((String) obj[10]);
						} else {
							wsBeat.setCoTravellers("");
						}
						distributorName = getRouteAndCustomerName((Integer) obj[1], 3);
						wsBeat.setDistributorName(distributorName);
						String finalStatus = workFlowTaskStatusService.getFinalSalesOrderStatus(
								wsSalesOrderReportInput.getCompany_id(), (Integer) obj[0],
								SalesMIDBUtils.BEAT_PLAN_NAME);

						wsBeat.setApproveStatus(finalStatus);

						String query2 = "select beat_plan_id,customer_id,route_id,distributor_id,status from BeatPlanDetails where company_id="
								+ wsSalesOrderReportInput.getCompany_id() + " and beatPlanMasterId=" + (Integer) obj[0]
								+ " ";
						Query q = session.createQuery(query2);
						list2 = q.list();
						for (Object object1 : list2) {
							Object[] obj1 = (Object[]) object1;
							wsBeatPlanDetails = new WsBeatList();
							wsBeatPlanDetails.setBeat_plan_id((Integer) obj1[0]);
							wsBeatPlanDetails.setCustomer_id((Integer) obj1[1]);
							wsBeatPlanDetails.setDistributor_id((Integer) obj1[3]);
							wsBeatPlanDetails.setRoute_id((Integer) obj1[2]);
							String routeName = getRouteAndCustomerName((Integer) obj1[2], 1);
							String customerName = getRouteAndCustomerName((Integer) obj1[1], 2);
							distributorName = getRouteAndCustomerName((Integer) obj1[3], 3);
							wsBeatPlanDetails.setRouteName(routeName);
							wsBeatPlanDetails.setCustomerName(customerName);
							wsBeatPlanDetails.setDistributorName(distributorName);
							wsBeatPlanDetails.setStatus((Integer) obj1[4]);
							wsBeat.getBeatDetails().add(wsBeatPlanDetails);
						}
						String query3 = "select beatPlanMasterDetailsId,beatPlanMasterId,route_id,distributor_id,distributrorName,routeName from BeatPlanMasterDetails where company_id="
								+ wsSalesOrderReportInput.getCompany_id() + " and beatPlanMasterId=" + (Integer) obj[0];
						Query q1 = session.createQuery(query3);
						list3 = q1.list();
						for (Object object1 : list3) {
							Object[] obj1 = (Object[]) object1;
							wsBeatInputMasterDetails = new WsBeatInputMasterDetails();
							wsBeatInputMasterDetails.setDistributor_id((Integer) obj1[3]);
							wsBeatInputMasterDetails.setRoute_id((Integer) obj1[2]);
							wsBeatInputMasterDetails.setDistributorName((String) obj1[4]);
							wsBeatInputMasterDetails.setRouteName((String) obj1[5]);
							wsBeat.getWsBeatInputMasterDetails().add(wsBeatInputMasterDetails);
						}
						if (finalStatus.startsWith(SalesMIDBUtils.PENDING)) {
							report.add(wsBeat);
						}

					}
					wSalesReportOutput.setWsbeat(report);
					wSalesReportOutput.setTotalRecords(report.size());
					wSalesReportOutput.setRecordsPerPage(no_of_records_per_page);
					wSalesReportOutput.setPage_No(wsSalesOrderReportInput.getPage_no());
					// wSalesReportOutput.setTotal_pages(count);
					return wSalesReportOutput;
				}

			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return wSalesReportOutput;

	}

	@Override
	public List<WsBeatList> getBeatDetailsReportForSil(WsSalesDetailsReportInput wsSalesDetailsReportInput, int id) {
		// TODO Auto-generated method stub
		Session dbSession = initiateSession();
		WsBeatList wsBeatPlanDetails = null;
		List<WsBeatList> bList = new ArrayList<>();
		try {

			String query2 = "select beat_plan_id,customer_id,route_id,distributor_id from BeatPlanDetails where company_id="
					+ wsSalesDetailsReportInput.getCompany_id() + " and beatPlanMasterId="
					+ wsSalesDetailsReportInput.getSales_master_id();
			Query q = dbSession.createQuery(query2);
			List list2 = q.list();
			for (Object object1 : list2) {
				Object[] obj1 = (Object[]) object1;
				wsBeatPlanDetails = new WsBeatList();
				wsBeatPlanDetails.setBeat_plan_id((Integer) obj1[0]);
				wsBeatPlanDetails.setCustomer_id((Integer) obj1[1]);
				wsBeatPlanDetails.setDistributor_id((Integer) obj1[3]);
				wsBeatPlanDetails.setRoute_id((Integer) obj1[2]);
				String routeName = getRouteAndCustomerName((Integer) obj1[2], 1);
				String customerName = getRouteAndCustomerName((Integer) obj1[1], 2);
				wsBeatPlanDetails.setRouteName(routeName);
				wsBeatPlanDetails.setCustomerName(customerName);
				bList.add(wsBeatPlanDetails);
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(dbSession);
		}
		return bList;
	}

	@Override
	public boolean deleteBeatMaster(int beatPlanMasterId, int c_id, int someValue) {
		// TODO Auto-generated method stub
		// FieldMILogger.debug(this.getClass().getName(),"Inside
		// DeleteProductListDaoImpl..!!");
		Session session = initiateSession();
		try {
			if (someValue == 1) {
				Query query = session.createQuery(
						"update BeatPlanMaster set status=:st,updated_date=:ud where beatPlanMasterId=:id and company_id=:c_id")
						.setParameter("st", 0).setParameter("id", beatPlanMasterId).setParameter("c_id", c_id)
						.setParameter("ud", new Date());
				int val = query.executeUpdate();

				Query query1 = session.createQuery(
						"update BeatPlanDetails set status=:st,updated_date=:ud where beatPlanMasterId=:id and company_id=:c_id")
						.setParameter("st", 0).setParameter("id", beatPlanMasterId).setParameter("c_id", c_id)
						.setParameter("ud", new Date());
				query1.executeUpdate();

				Query query2 = session.createQuery(
						"update BeatPlanMasterDetails set status=:st where beatPlanMasterId=:id and company_id=:c_id")
						.setParameter("st", 0).setParameter("id", beatPlanMasterId).setParameter("c_id", c_id);
				query2.executeUpdate();

				if (val == 0) {
					return false;
				}

			} else {
				Query query = session.createQuery(
						"update BeatPlanDetails set status=:st,updated_date=:ud where beat_plan_id=:id and company_id=:c_id")
						.setParameter("st", 0).setParameter("id", beatPlanMasterId).setParameter("c_id", c_id)
						.setParameter("ud", new Date());
				int val = query.executeUpdate();
				if (val == 0) {
					return false;
				}

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
	public int checkIfExists(int beatPlanMasterId) {
		// TODO Auto-generated method stub
		Session dbSession = initiateSession();
		String sql = "select beatPlanMasterId from beat_plan_master where beatPlanMasterId=" + beatPlanMasterId;
		try {
			Query q = dbSession.createSQLQuery(sql);
			List list = q.list();
			if (list != null) {
				if (list.size() > 0) {

					return (Integer) list.get(0);
				} else {
					return 0;
				}
			} else
				return 0;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return 0;
		} finally {
			destroySession(dbSession);
		}

	}

	@Override
	public List<CustomerDetails> getFilterCustomerList(String distributor_id, int c_id, int user_id, String route_id) {
		Session session = initiateSession();
		List<CustomerDetails> customerDetails = new ArrayList<CustomerDetails>();
		Query query = null;
		try {

			if (user_id != 0) {
				query = session.createSQLQuery(
						"select distinct c.customer_id,c.customer_name,c.distributor_id,c.route_id From customer_details c inner join route_details r on r.route_id=c.route_id  inner join user_has_distributor ud on ud.distributor_id=r.distributor_id  where ud.user_id="
								+ user_id + " and c.company_id=" + c_id + " and ud.distributor_id in ( "
								+ distributor_id + " ) and c.route_id in ( " + route_id + " ) and c.status=1");
				List list = query.list();
				for (int i = 0; i < list.size(); i++) {
					CustomerDetails c = new CustomerDetails();
					Object[] object = (Object[]) list.get(i);
					c.setCustomer_id((Integer) object[0]);
					String dName = getRouteAndCustomerName((Integer) object[2], 3);
					String rName = getRouteAndCustomerName((Integer) object[3], 1);
					c.setCustomer_name((String) object[1] + "-->" + dName + "-->" + rName);
					customerDetails.add(c);
				}
			}

			return customerDetails;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<Route> getRouteList(String distributor_id, int c_id, int user_id) {
		Session session = initiateSession();
		List<Route> routeList = new ArrayList<Route>();
		Query query = null;
		try {

			if (user_id != 0) {
				query = session.createSQLQuery(
						"select distinct r.route_id,r.route_name From route_details r Inner join user_has_routes ur on ur.route_id=r.route_id where ur.user_id="
								+ user_id + " and ur.company_id=" + c_id + " and ur.distributor_id in ( "
								+ distributor_id + " ) and r.status =1");
				List list = query.list();
				for (int i = 0; i < list.size(); i++) {
					Route r = new Route();
					Object[] object = (Object[]) list.get(i);
					r.setRoute_id((Integer) object[0]);
					r.setRoute_name((String) object[1]);
					routeList.add(r);
				}
			}

			return routeList;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public BeatPlanMaster getBeatPlanMaster(int expenseMasterId) {

		BeatPlanMaster details = new BeatPlanMaster();
		Session session = initiateSession();

		try {
			Query beatQuery = session.createQuery("From BeatPlanMaster where expenseMasterId=" + expenseMasterId);
			details = (BeatPlanMaster) beatQuery.uniqueResult();

			return details;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);

		}

	}

	@Override
	public List<BeatPlanMaster> getBeatPlanMasterListDateWise(Date sDate, Date eDate, int eId) {
		Session session = initiateSession();
		List<BeatPlanMaster> bList = new ArrayList<BeatPlanMaster>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("EEE dd MMM yyyy");
		Query query = null;
		try {

			if (eId != 0) {
				String q = "select journeyType,modeOfTravel,beatPlanDate from BeatPlanMaster where beatPlanDate between '"
						+ sdf.format(sDate) + "' and '" + sdf.format(eDate) + "' and expenseMasterId=" + eId;
				query = session.createQuery(q);
				List list = query.list();
				for (int i = 0; i < list.size(); i++) {
					BeatPlanMaster r = new BeatPlanMaster();
					Object[] object = (Object[]) list.get(i);
					if ((String) object[0] != null) {
						r.setJourneyType((String) object[0]);
					} else {
						r.setJourneyType("");
					}
					if ((String) object[1] != null) {
						r.setModeOfTravel((String) object[1]);
					} else {
						r.setModeOfTravel("");
					}
					r.setStartLat(sdf1.format((Date) object[2]));
					bList.add(r);
				}
			}

			return bList;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

}
