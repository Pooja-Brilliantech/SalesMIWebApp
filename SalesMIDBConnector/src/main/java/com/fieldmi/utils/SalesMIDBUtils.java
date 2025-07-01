package com.fieldmi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import com.fieldmi.FieldMILogger;
import com.fieldmi.daoImpl.FMSalesMasterDaoImpl;
import com.fieldmi.sap.SAPUrls;
import com.fieldmi.sap.SapIntegrationDao;
import com.fieldmi.service.FMSalesMasterService;
import com.fieldmi.service.FMWorkflowConfigurationService;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.fieldmi.stubs.WsSAPCreditInfoRequest;
import com.fieldmi.stubs.WsSAPCreditInfoResponse;
import com.fieldmi.stubs.WsSalesOrderOutput;
import com.softtantra.salesapp.pojo.BeatPlanDetails;
import com.softtantra.salesapp.pojo.BeatPlanMaster;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowConfiguration;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;

public class SalesMIDBUtils {

	// Workflow Names
	public static final String BEAT_PLAN_NAME = "BeatPlan Workflow";
	public static final String EXPENSE_WORKFLOW_NAME = "Expense Claim Workflow";
	public static final String LEAVE_WORKFLOW = "Leave WorkFlow";
	public static final String SALES_ORDER_WORKFLOW_NAME = "Sales Order Workflow";
	public static final String CUSTOMER_WORKFLOW = "Customer WorkFlow";

	// workflow task names
	public static final String BEAT_PLAN_ISSUE_TASK = "BeatPlan Task";
	public static final String NEW_CUSTOMER_TASK = "New Customer Task";
	public static final String CREDIT_ISSUE = "Credit Issue";
	public static final String EXPENSE_ISSUE_TASK = "Expense Claim Task";
	public static final String PRICING_ISSUE_TASK = "Pricing Issue";
	public static final String LOAD_CONDITION_ISSUE_TASK = "Under Load Condition";
	public static final String MATERIAL_STOCK_ISSUE_TASK = "Material Stock Issue";
	public static final String LEAVE_ISSUE_TASK = "Leave Task";

	// Workflow status
	public static final String AVAILABLE = "Available";
	public static final String APPROVED = "Approved";
	public static final String PENDING = "Pending";
	public static final String REJECTED = "Rejected";
	public static final String RECOMMEND = "Recommend";
	public static final String TO_SUBMIT = "To be submitted";
	public static final String COMPLETED = "Completed";
	public static final String NOT_AVAILABLE = "Not Available";

	// Workflow task status append strings
	public static final String APPROVED_BY = "Approved by ";
	public static final String REJECTED_BY = "Rejected by ";
	public static final String PENDING_AT = "Pending at ";
	public static final String RECOMMEND_TO = "Recommended to";
	public static final String MATERIAL_EXTERNAL_COMMENT = "Material External Comment";

	// workflow approval types
	public static final String EXTERNAL = "External"; // external systems checks
	public static final String MULTILEVEL = "MultiLevel"; // Role based 1 or more
	public static final String SINGLELEVEL = "Single Level"; // not used any more for 1 role only
	public static final String SINGLE_USER = "Single User"; // User based for 1 specific user
	public static final String RECURSIVE = "Recursive"; // Recursive
	public static final String ALLOWED_DISCOUNT = "Allowed Discount";
	public static final String AUTOAPPROVED = "autoApproved";

	// workflow input object name
	public static final String CONFIRMED = "Confirmed";
	public static final String PROSPECT = "Prospect";
	public static final String INFLUENCER = "Influencer";
	public static final String ALL = "All";
	
	public static final String LOGIN_WEB_ACTIVITY = "Web Login";
	public static final String LOGIN_MOBILE_ACTIVITY = "Mobile Login";

	static Properties fileProperties = new Properties();
	static {
		try {
			fileProperties.load(FMSalesMasterDaoImpl.class.getClassLoader().getResourceAsStream("/sapUrls.properties"));
		} catch (Exception e) {

			FieldMILogger.error(FMSalesMasterDaoImpl.class.getName(), e);
		}
	}

	public List<WsSalesOrderOutput> returnSalesOrderList(WorkFlowTaskStatusService workFlowTaskStatusService,
			FMSalesMasterService fmSalesMasterService, String task_name, int approver_type, int approver_id, int c_id,
			String status,String user,String plant) {

		List<WorkFlowTasksStatus> workFlowTaskStausList = workFlowTaskStatusService
				.getSalesOrderStatusBasedOnNextApproverId(c_id, task_name, SALES_ORDER_WORKFLOW_NAME, approver_type,
						approver_id);

		Iterator<WorkFlowTasksStatus> workflowItr = workFlowTaskStausList.iterator();
		while (workflowItr.hasNext()) {

			WorkFlowTasksStatus workFlowTaskStatus = workflowItr.next();

			List<WorkFlowTasksStatus> workFlowTaskRejectedList = workFlowTaskStatusService
					.getSalesOrderTasksBasedOnStatus(c_id, workFlowTaskStatus.getSales_master_id(), status,
							SALES_ORDER_WORKFLOW_NAME);
			if (workFlowTaskRejectedList.size() > 0) {

				workflowItr.remove();
			}
		}

		List<WsSalesOrderOutput> returnList = fmSalesMasterService.getPendingSalesOrder(workFlowTaskStausList, c_id,user,plant);

		return returnList;

	}

	/**
	 * This method must be used to set the next approvers, type and task status for
	 * the workflow based on the configuration.
	 * 
	 * @param workflowObject                 - complete workflow object except next
	 *                                       approver id, approver type and task
	 *                                       status set
	 * @param workFlowTaskStatusService
	 * @param workFlowAuditLogService
	 * @param fmWorkflowConfigurationService
	 * @param newStatus
	 * @param userId
	 * @param company_id
	 * @param comment
	 * @return
	 */
	public String updateWorkflowObject(WorkFlowTasksStatus workflowObject,
			WorkFlowTaskStatusService workFlowTaskStatusService, WorkFlowAuditLogService workFlowAuditLogService,
			FMWorkflowConfigurationService fmWorkflowConfigurationService, String newStatus, int userId, int company_id,
			String comment, String custom_field_date, boolean forceApproved, String inputObjectName) {

		if (custom_field_date != null && custom_field_date.trim().length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				Date d = sdf.parse(custom_field_date);
				workflowObject.setCustom_field_date(d);
			} catch (ParseException e) {

				FieldMILogger.error(this.getClass().getName(), e);
			}
		}

		if (newStatus != null && newStatus.equals(REJECTED)) {

			// new status is rejected hence, setting the status directly as rejected.
			List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
			workTaskStatusDetailList.add(workflowObject);
			workFlowTaskStatusService.setTaskToRejected(workTaskStatusDetailList, userId, comment,
					workFlowAuditLogService);

		} else if (newStatus != null) {

			// check if workflow configuration exists.
			List<WorkFlowConfiguration> workflowConfigurationList = fmWorkflowConfigurationService.getWorkflowConfigs(
					workflowObject.getWorkflow_name(), workflowObject.getTask_name(), company_id, inputObjectName);
			if (workflowConfigurationList != null && workflowConfigurationList.size() == 1) {

				// workflow configuration exists hence perform other checks
				WorkFlowConfiguration workflowConfiguration = workflowConfigurationList.get(0);
				if (workflowConfiguration.getApproval_type().equals(RECURSIVE)) {

					// workflow configuration is hirerchical and approval is level based and user
					// based only
					// set approved type as user
					workflowObject.setApprover_type(0);
					User currUser = workFlowTaskStatusService.getUserFromUserId(userId);
					if ((workflowObject.getRecursive_level() == workflowConfiguration.getNumber_of_level())
							|| (newStatus.equals(APPROVED) && forceApproved)) {

						// as workflow approved level == maximum level then set status as approved
						List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
						workTaskStatusDetailList.add(workflowObject);
						workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
								workFlowAuditLogService);
					} else {

						// as workflow approved level < maximum level then set next approver as next
						// level user and even if satus is approved set status to pending
						if (currUser != null && currUser.getReporting_manager_id() != 0) {

							if (newStatus != null && !newStatus.equals(TO_SUBMIT))
								workflowObject.setRecursive_level((workflowObject.getRecursive_level() + 1));
							
							workflowObject.setNext_approver_id(currUser.getReporting_manager_id());

							List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
							workTaskStatusDetailList.add(workflowObject);
							if (newStatus.equals(PENDING) || (newStatus.equals(APPROVED) && !forceApproved)) {

								workFlowTaskStatusService.updateStatusToPending(workTaskStatusDetailList, userId,
										comment, workFlowAuditLogService);
							} else if (newStatus.equals(RECOMMEND)) {

								workFlowTaskStatusService.updateStatusToRecommend(workTaskStatusDetailList, userId,
										comment, workFlowAuditLogService);
							} else {

								workFlowTaskStatusService.updateStatus(workTaskStatusDetailList, userId, comment,
										workFlowAuditLogService, newStatus);
							}

						} else {

							// as curr user is null or reporting manager is 0 then set status as approved as
							// default
							List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
							workTaskStatusDetailList.add(workflowObject);
							workflowObject.setRecursive_level(workflowConfiguration.getNumber_of_level());
							workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
									workFlowAuditLogService);
						}
					}

				} else if (workflowConfiguration.getApproval_type().equals(SINGLE_USER)) {

					// workflow configuration is user level
					workflowObject.setApprover_type(0);
					if (userId == Integer.parseInt(workflowConfiguration.getRole_ids())) {

						// as curr user is same as approving user then set status as approved as default
						workflowObject.setRecursive_level(workflowConfiguration.getNumber_of_level());
						List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
						workTaskStatusDetailList.add(workflowObject);
						workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
								workFlowAuditLogService);
					} else {

						// set the approving user id as the set single level user
						workflowObject.setRecursive_level(1);
						workflowObject.setNext_approver_id(Integer.parseInt(workflowConfiguration.getRole_ids()));

						List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
						workTaskStatusDetailList.add(workflowObject);
						if (newStatus.equals(PENDING) || (newStatus.equals(APPROVED) && !forceApproved))
							workFlowTaskStatusService.updateStatusToPending(workTaskStatusDetailList, userId, comment,
									workFlowAuditLogService);
						else if (newStatus.equals(RECOMMEND))
							workFlowTaskStatusService.updateStatusToRecommend(workTaskStatusDetailList, userId, comment,
									workFlowAuditLogService);
						else
							workFlowTaskStatusService.updateStatus(workTaskStatusDetailList, userId, comment,
									workFlowAuditLogService, newStatus);
					}

				} else if (workflowConfiguration.getApproval_type().equals(EXTERNAL)) {

					// workflow configuration is user level
					workflowObject.setApprover_type(0);
					workflowObject.setNext_approver_id(0);

					List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
					workTaskStatusDetailList.add(workflowObject);
					if (newStatus.equals(PENDING))
						workFlowTaskStatusService.updateStatusToPending(workTaskStatusDetailList, userId, comment,
								workFlowAuditLogService);
					if (newStatus.equals(APPROVED))
						workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
								workFlowAuditLogService);
					else
						workFlowTaskStatusService.updateStatus(workTaskStatusDetailList, userId, comment,
								workFlowAuditLogService, newStatus);

				} else if (workflowConfiguration.getApproval_type().equals(AUTOAPPROVED)) {

					workflowObject.setApprover_type(0);
					workflowObject.setNext_approver_id(0);

					List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
					workTaskStatusDetailList.add(workflowObject);
					workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
							workFlowAuditLogService);
				} else if (workflowConfiguration.getApproval_type().equals(MULTILEVEL)
						|| workflowConfiguration.getApproval_type().equals(SINGLELEVEL)) {

					// workflow configuration is multi level and may have immediate manager and/or
					// roles sequence
					String approvalRoleIds = workflowConfiguration.getRole_ids();
					List<String> roleIds = getRoleIdsFromString(approvalRoleIds);

					if (workflowObject.getRecursive_level() == workflowConfiguration.getNumber_of_level()) {

						// set default status to approved as level has been achieved
						List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
						workTaskStatusDetailList.add(workflowObject);
						workflowObject.setRecursive_level(workflowConfiguration.getNumber_of_level());
						workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
								workFlowAuditLogService);
					} else {

						// as level of approvals is not met, so even if new status is approved it will
						// be set as pending
						// and assign to next approver either role or user
						int nextApprovalLevel = workflowObject.getRecursive_level();
						if (newStatus != null && !newStatus.equals(TO_SUBMIT))
							nextApprovalLevel = workflowObject.getRecursive_level() + 1;
						
						if (nextApprovalLevel <= roleIds.size()) {

							String nextRoleId = null;
							if( nextApprovalLevel == 0 )
								nextRoleId = roleIds.get(0);
							else if( nextApprovalLevel > 0 )
								nextRoleId = roleIds.get((nextApprovalLevel - 1));
							
							if (nextRoleId.equals("0000")) {

								// next level is immediate manager
								workflowObject.setApprover_type(0);

								// the next approver is manager hence find the immediate manager of the created
								// user

								workflowObject.setRecursive_level(nextApprovalLevel);
								User createdUser = workFlowTaskStatusService
										.getUserFromUserId(workflowObject.getCreated_by());
								if (createdUser.getReporting_manager_id() != 0) {

									workflowObject.setNext_approver_id(createdUser.getReporting_manager_id());

									// set status as pending as reporting manager exists
									List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
									workTaskStatusDetailList.add(workflowObject);
									if (newStatus.equals(PENDING) || (newStatus.equals(APPROVED) && !forceApproved))
										workFlowTaskStatusService.updateStatusToPending(workTaskStatusDetailList,
												userId, comment, workFlowAuditLogService);
									else if (newStatus.equals(RECOMMEND))
										workFlowTaskStatusService.updateStatusToRecommend(workTaskStatusDetailList,
												userId, comment, workFlowAuditLogService);
									else
										workFlowTaskStatusService.updateStatus(workTaskStatusDetailList, userId,
												comment, workFlowAuditLogService, newStatus);
								} else {

									// call update workflow again as reporting manager not found so set the next
									// approver again
									updateWorkflowObject(workflowObject, workFlowTaskStatusService,
											workFlowAuditLogService, fmWorkflowConfigurationService, APPROVED, userId,
											company_id, comment, null, forceApproved, inputObjectName);
								}

							} else {

								// next level is role
								workflowObject.setApprover_type(1);

								// the next approver is role hence find the next role in sequence
								workflowObject.setRecursive_level(nextApprovalLevel);
								workflowObject.setNext_approver_id(Integer.parseInt(nextRoleId));

								// set status as pending
								List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
								workTaskStatusDetailList.add(workflowObject);
								if (newStatus.equals(PENDING) || (newStatus.equals(APPROVED) && !forceApproved))
									workFlowTaskStatusService.updateStatusToPending(workTaskStatusDetailList, userId,
											comment, workFlowAuditLogService);
								else if (newStatus.equals(RECOMMEND))
									workFlowTaskStatusService.updateStatusToRecommend(workTaskStatusDetailList, userId,
											comment, workFlowAuditLogService);
								else
									workFlowTaskStatusService.updateStatus(workTaskStatusDetailList, userId, comment,
											workFlowAuditLogService, newStatus);

							}

						}
					}

				}

			} else {

				// Workflow configuration not found hence, setting status as approved directly
				List<WorkFlowTasksStatus> workTaskStatusDetailList = new ArrayList<WorkFlowTasksStatus>();
				workTaskStatusDetailList.add(workflowObject);
				workFlowTaskStatusService.setTaskToApproved(workTaskStatusDetailList, userId, comment,
						workFlowAuditLogService);
			}
		}

		return workflowObject.getTask_status();

	}

	public List<String> getRoleIdsFromString(String approvalRoleIds) {

		List<String> roleIds = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(approvalRoleIds, ",");
		while (tokenizer.hasMoreElements()) {

			roleIds.add(tokenizer.nextToken());
		}

		return roleIds;
	}

	public boolean getCreditStatus(int sales_master_id, String soldToParty, int companyId, double totalamount) {
		boolean isCreditAvailable = false;
		try {

			String userName = fileProperties.getProperty(SAPUrls.SAP_USERNAME + companyId);
			String password = fileProperties.getProperty(SAPUrls.SAP_PASSWORD + companyId);
			SapIntegrationDao sapIntegrationDao = new SapIntegrationDao(userName, password, fileProperties);

			Calendar c = Calendar.getInstance();
			int value = c.get(Calendar.YEAR);
			int valueOld = value;
			int month = c.get(Calendar.MONTH);
			month += 1;
			int day = c.get(Calendar.DAY_OF_MONTH);
			WsSAPCreditInfoRequest wsSapCreditInfoRequest = new WsSAPCreditInfoRequest();
			wsSapCreditInfoRequest.setCompany_id(companyId);
			wsSapCreditInfoRequest.setCompany_code("SIL");
			wsSapCreditInfoRequest.setFiscalYear(String.valueOf(value));
			wsSapCreditInfoRequest.setPeriod(valueOld + "" + month + "" + day);
			wsSapCreditInfoRequest.setCreditControl("SIL");
			wsSapCreditInfoRequest.setCustomer_code(soldToParty);
			List<String> slabs = new ArrayList<>();
			slabs.add("15");
			slabs.add("30");
			slabs.add("60");
			slabs.add("90");
			slabs.add("120");
			slabs.add("180");
			wsSapCreditInfoRequest.setSlabs(slabs);

			WsSAPCreditInfoResponse wsSAPCreditInfoResponse = sapIntegrationDao
					.getCreditInformationFromSAP(wsSapCreditInfoRequest);
			if (wsSAPCreditInfoResponse.getRisk_category().equals(null) || wsSAPCreditInfoResponse.getRisk_category().equals("")) {
				isCreditAvailable = true;
			} else {
				double creditLeft = wsSAPCreditInfoResponse.getDelta();
						
				if (creditLeft > totalamount) {
					isCreditAvailable = true;
				} else {
					isCreditAvailable = false;
				}
			}
			return isCreditAvailable;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		}
		return isCreditAvailable;
	}

	public void addBeatPlanToWorkFlow(WorkFlowTaskStatusService workFlowTaskStatusService,
			WorkFlowAuditLogService workFlowAuditLogService,
			FMWorkflowConfigurationService fmWorkflowConfigurationService, BeatPlanMaster beatPlanMaster, int id,
			String status) {

		try {
			if (beatPlanMaster != null && !beatPlanMaster.isUpdated()) {

				WorkFlowTasksStatus workFlowStatus = new WorkFlowTasksStatus();
				workFlowStatus.setCompany_id(beatPlanMaster.getCompany_id());
				workFlowStatus.setSales_details_id(0);
				workFlowStatus.setSales_master_id(beatPlanMaster.getBeatPlanMasterId());
				workFlowStatus.setTask_name(SalesMIDBUtils.BEAT_PLAN_ISSUE_TASK);
				workFlowStatus.setWorkflow_name(SalesMIDBUtils.BEAT_PLAN_NAME);
				workFlowStatus.setUpdated_by(id);
				workFlowStatus.setCreated_by(id);

				SalesMIDBUtils dbUtils = new SalesMIDBUtils();
				dbUtils.updateWorkflowObject(workFlowStatus, workFlowTaskStatusService, workFlowAuditLogService,
						fmWorkflowConfigurationService, status, workFlowStatus.getCreated_by(),
						Integer.parseInt("" + beatPlanMaster.getCompany_id()), "Add new beat plan", null, false,
						SalesMIDBUtils.ALL);

			}
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
		}

	}

	public void addBeatPlanDetailsToWorkflow(WorkFlowTaskStatusService workFlowTaskStatusService,
			WorkFlowAuditLogService workFlowAuditLogService,
			FMWorkflowConfigurationService fmWorkflowConfigurationService, List<BeatPlanDetails> beatPlanDetailsList,
			Long userId, int company_id, String status) {

		for (BeatPlanDetails bDetails : beatPlanDetailsList) {

			if(!bDetails.isUpdated()) {
				
				WorkFlowTasksStatus workFlowStatus = new WorkFlowTasksStatus();
				workFlowStatus.setCompany_id(company_id);

				workFlowStatus.setSales_details_id(bDetails.getBeat_plan_id());
				workFlowStatus.setSales_master_id(bDetails.getBeatPlanMasterId());
				workFlowStatus.setTask_name(SalesMIDBUtils.BEAT_PLAN_ISSUE_TASK);
				workFlowStatus.setWorkflow_name(SalesMIDBUtils.BEAT_PLAN_NAME);
				workFlowStatus.setUpdated_by(userId.intValue());
				workFlowStatus.setCreated_by(userId.intValue());
				String beatDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
						company_id, bDetails.getBeatPlanMasterId(),SalesMIDBUtils.BEAT_PLAN_ISSUE_TASK, SalesMIDBUtils.BEAT_PLAN_NAME);
				SalesMIDBUtils dbUtils = new SalesMIDBUtils();
				if(beatDetailsStatus!=null && beatDetailsStatus.startsWith(SalesMIDBUtils.APPROVED)) {
					
					dbUtils.updateWorkflowObject(workFlowStatus, workFlowTaskStatusService, workFlowAuditLogService,
							fmWorkflowConfigurationService, beatDetailsStatus, workFlowStatus.getCreated_by(), company_id,
							"Add new beat plan", null, true, SalesMIDBUtils.ALL);
				} else if (beatDetailsStatus!=null) {
					
					dbUtils.updateWorkflowObject(workFlowStatus, workFlowTaskStatusService, workFlowAuditLogService,
							fmWorkflowConfigurationService, beatDetailsStatus, workFlowStatus.getCreated_by(), company_id,
							"Add new beat plan", null, false, SalesMIDBUtils.ALL);
				} else {
					
					dbUtils.updateWorkflowObject(workFlowStatus, workFlowTaskStatusService, workFlowAuditLogService,
							fmWorkflowConfigurationService, status, workFlowStatus.getCreated_by(), company_id,
							"Add new beat plan", null, false, SalesMIDBUtils.ALL);
				}
			}
		}

	}

	public String getNextApproverId(FMWorkflowConfigurationService fmWorkflowConfigurationService, int u_id, int r_id,
			int c_id, String workFlowName, String taskName, String objectName) {

		List<WorkFlowConfiguration> workflowConfigs = fmWorkflowConfigurationService.getWorkflowConfigs(workFlowName,
				taskName, c_id, objectName);
		if (workflowConfigs != null && workflowConfigs.size() > 0) {

			StringBuffer nextApproverIdBuffer = new StringBuffer();
			nextApproverIdBuffer.append("(");
			for (WorkFlowConfiguration workFlowConfiguration : workflowConfigs) {

				if (workFlowConfiguration.getApproval_type().equals(SalesMIDBUtils.SINGLE_USER)
						|| workFlowConfiguration.getApproval_type().equals(SalesMIDBUtils.RECURSIVE)) {

					nextApproverIdBuffer.append(u_id);
				} else if (workFlowConfiguration.getApproval_type().equals(SalesMIDBUtils.MULTILEVEL)
						|| workFlowConfiguration.getApproval_type().equals(SalesMIDBUtils.SINGLELEVEL)) {
					nextApproverIdBuffer.append(r_id).append(",").append(u_id);
				}
			}
			nextApproverIdBuffer.append(")");

			if (nextApproverIdBuffer.length() > 2)
				;
			return nextApproverIdBuffer.toString();

		}
		return "(-1)";

	}
}
