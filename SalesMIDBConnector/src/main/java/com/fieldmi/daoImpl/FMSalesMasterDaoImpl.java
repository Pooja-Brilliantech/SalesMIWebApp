package com.fieldmi.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMSalesMasterDao;
import com.fieldmi.sap.SAPUrls;
import com.fieldmi.sap.SapIntegrationDao;
import com.fieldmi.service.FMSalesMasterService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.fieldmi.stubs.SalesOrderReport;
import com.fieldmi.stubs.WSUpdateSalesMaster;
import com.fieldmi.stubs.WsCustomer;
import com.fieldmi.stubs.WsPriceDetails;
import com.fieldmi.stubs.WsSAPMaterialDetails;
import com.fieldmi.stubs.WsSalesDetailsReportInput;
import com.fieldmi.stubs.WsSalesExportWithDetails;
import com.fieldmi.stubs.WsSalesOrderDetailsNew2;
import com.fieldmi.stubs.WsSalesOrderOutput;
import com.fieldmi.stubs.WsSalesOrderProductList;
import com.fieldmi.stubs.WsSalesOrderReport;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.fieldmi.stubs.WsSalesOrderwithEachItemOffer2;
import com.fieldmi.stubs.WsSalesReportOutput;
import com.fieldmi.stubs.tally.SO_Details;
import com.fieldmi.stubs.tally.SalesOrder;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.SalesMIDBUtils;
import com.softtantra.salesapp.pojo.Citys;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.SalesDetails;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;
import com.softtantra.ws.Credentials;

public class FMSalesMasterDaoImpl implements FMSalesMasterDao {

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
	FMSalesMasterService fmSalesMasterService;
	@Autowired
	CommonMethods commonMethods;

	public void setCommonMethods(CommonMethods commonMethods) {
		this.commonMethods = commonMethods;
	}

	static Properties fileProperties = new Properties();
	static {
		try {
			fileProperties.load(FMSalesMasterDaoImpl.class.getClassLoader().getResourceAsStream("/sapUrls.properties"));
		} catch (Exception e) {

			FieldMILogger.error(FMSalesMasterDaoImpl.class.getName(), e);
		}
	}
	int no_of_records_per_page = 20;

	@Override
	@Transactional
	public List<WsSalesOrderOutput> getPendingSalesOrder(List<WorkFlowTasksStatus> workFlowTaskStatusList,
			int company_id, String user, String plant) {

		Session session = initiateSession();
		try {

			String userq = "";
			String plantq = "";
			if (user != null && !user.equals("")) {
				userq = " and sm.user_id=" + user;
			}
			if (plant != null && !plant.equals("")) {
				plantq = " and sm.plant='" + plant + "' ";
			}
			StringBuilder sqlForWorkFlowStatus = new StringBuilder();
			sqlForWorkFlowStatus.append(
					"select sm.sales_master_id, sm.user_id, c.customer_name, sm.created_date, sm.status, sm.totalamount, c.address,c.code,c.customer_group,sm.cashDiscountOrQuaterlyDiscount,sm.freight_applicable,sm.plant from sales_master sm");
			sqlForWorkFlowStatus.append(" inner join customer_details c on c.customer_id=sm.customer_id  ");
			sqlForWorkFlowStatus.append(" where sm.status=1 and sm.company_id=" + company_id + userq + " " + plantq
					+ " and sales_master_id IN (");

			int salesOrderCnt = 0;
			HashMap<String, String> salesMasterVsStatus = new HashMap<>();
			for (WorkFlowTasksStatus workFlowTasksStatus : workFlowTaskStatusList) {

				if (salesOrderCnt > 0)
					sqlForWorkFlowStatus.append(",");

				sqlForWorkFlowStatus.append(workFlowTasksStatus.getSales_master_id());
				salesMasterVsStatus.put("" + workFlowTasksStatus.getSales_master_id(),
						workFlowTasksStatus.getTask_status());
				salesOrderCnt++;
			}

			sqlForWorkFlowStatus.append(")");

			ArrayList<WsSalesOrderOutput> returnList = new ArrayList<>();
			if (salesOrderCnt == 0 || sqlForWorkFlowStatus.toString().endsWith("()")) {

				return returnList;
			}
			List list = session.createSQLQuery(sqlForWorkFlowStatus.toString()).list();

			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsSalesOrderOutput salesOrderOutput = new WsSalesOrderOutput();
				salesOrderOutput.setSales_master_id((int) obj[0]);
				salesOrderOutput.setUser_id((int) obj[1]);
				salesOrderOutput.setCustomer_name((String) obj[2]);
				salesOrderOutput.setStatus("" + obj[4]);
				salesOrderOutput.setAddress((String) obj[6]);
				salesOrderOutput.setTotalamount((double) obj[5]);
				salesOrderOutput
						.setWorkFowStatus(salesMasterVsStatus.get(("" + salesOrderOutput.getSales_master_id())));
				String userName = getUserName((int) obj[1]);
				salesOrderOutput.setUserName(userName);
				salesOrderOutput.setCustomerCode((String) obj[7]);
				salesOrderOutput.setCustomerGroup((String) obj[8]);
				salesOrderOutput.setCashDiscountOrQuaterlyDiscount((String) obj[9]);
				salesOrderOutput.setSalesOrderType((String) obj[10]);
				salesOrderOutput.setCreated_date((Date) obj[3]);
				salesOrderOutput.setPlantName((String) obj[11]);

				String sql = "select product_details.name,(sales_details.product_stock) as qty,sales_details.product_rate,(sales_details.product_stock*sales_details.product_rate) as total,category_details.category_name,product_details.product_code,product_details.packtitle,sales_details.pack,product_details.packunit,sales_details.pricemaster_id,sales_details.discount,offer_id,scheme_id,scheme_name,scheme_type,offer_desc,tax,rate,percentage,sales_master.totalamount,sales_master.discount as finaldiscount, sales_details.pack_qty, sales_details.pack_rate"
						+ " from sales_master,sales_details left outer JOIN pricemaster_details ON pricemaster_details.pricemaster_id=sales_details.pricemaster_id left outer join manage_tax on manage_tax.manage_tax_id=pricemaster_details.tax  INNER JOIN product_details on sales_details.product_id = product_details.product_id  INNER Join category_details on product_details.category_id=category_details.category_id where sales_details.sales_master_id="
						+ (int) obj[0]
						+ " and sales_master.sales_master_id=sales_details.sales_master_id and sales_master.status=1 and sales_master.company_id="
						+ company_id;

				List listOfProduct = session.createSQLQuery(sql.toString()).list();
				ArrayList<WsSalesOrderProductList> productList = new ArrayList<>();
				for (Object objectOfProduct : listOfProduct) {
					Object[] productObject = (Object[]) objectOfProduct;
					WsSalesOrderProductList pList = new WsSalesOrderProductList();
					pList.setProductName((String) productObject[0]);
					pList.setQty((double) productObject[1]);
					pList.setPrice((double) productObject[2]);
					pList.setDiscount((double) productObject[10]);
					pList.setAmount((double) productObject[3]);
					productList.add(pList);
					salesOrderOutput.setWsSalesOrderProductList(productList);

				}
				returnList.add(salesOrderOutput);
			}

			return returnList;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;

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
	public boolean updateSalesMasterWithSapOrderId(WSUpdateSalesMaster wsUpdateSalesMaster) {

		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("from SalesMaster where sales_master_id=" + wsUpdateSalesMaster.getSalesMasterId());

			SalesMaster salesMaster = (SalesMaster) query.uniqueResult();
			if (salesMaster != null && wsUpdateSalesMaster.getSapOrderId() != null) {

				salesMaster.setSapOrderId(wsUpdateSalesMaster.getSapOrderId());
				salesMaster.setExportedToSAP(1);
				session.update(salesMaster);
				return true;
			}
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return false;
	}

	@Override
	public String sendSOToSAP(int sales_master_id) throws Exception {

		Session session = initiateSession();
		Date d = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updatedDate = format1.format(d);
		String returnSOId = "Not Generated";
		try {
			if (session != null) {

				Query salesMasterQuery = session
						.createQuery("From SalesMaster where sales_master_id=" + sales_master_id);
				SalesMaster currSalesMaser = (SalesMaster) salesMasterQuery.uniqueResult();

				if (currSalesMaser != null && (currSalesMaser.getSapOrderId() == null
						|| currSalesMaser.getSapOrderId().trim().length() == 0)) {

					String userName = fileProperties.getProperty(SAPUrls.SAP_USERNAME + currSalesMaser.getCompany_id());
					String password = fileProperties.getProperty(SAPUrls.SAP_PASSWORD + currSalesMaser.getCompany_id());
					SapIntegrationDao sapIntegrationDao = new SapIntegrationDao(userName, password, fileProperties);

					FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
							"Fetching customer details for SO posting for customer id: "
									+ currSalesMaser.getCustomer_id());
					Query customerQuery = session
							.createQuery("From CustomerDetails where customer_id=" + currSalesMaser.getCustomer_id());
					CustomerDetails customer = (CustomerDetails) customerQuery.uniqueResult();

					CustomerDetails shipToPartyCustomer = null;
					if (currSalesMaser.getShipToParty() != null
							&& currSalesMaser.getShipToParty().trim().length() > 0) {
						Query shipToPartyQuery = session
								.createQuery("From CustomerDetails where code='" + currSalesMaser.getShipToParty()
										+ "' and company_id=" + currSalesMaser.getCompany_id());
						shipToPartyCustomer = (CustomerDetails) shipToPartyQuery.uniqueResult();
					}

					Query salesDetailsQuery = session
							.createQuery("From SalesDetails where sales_master_id=" + sales_master_id);
					List<SalesDetails> salesDetails = salesDetailsQuery.list();

					// check if material is in stock
					boolean allMaterialStockAvailable = true;
					List<Integer> materialIds = new ArrayList<>();
					List<String> materialCodes = new ArrayList<>();
					List<String> quantity = new ArrayList<>();
					List<String> units = new ArrayList<>();
					List<WsSalesOrderDetailsNew2> wsSalesDetailsList = new ArrayList<>();
					for (SalesDetails salesDetail : salesDetails) {

						FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
								"Fetching product details for SO posting for product id: "
										+ salesDetail.getProduct_id());
						Query productQuery = session.createQuery("From ProductDetails where product_id="
								+ salesDetail.getProduct_id() + " and company_id= " + currSalesMaser.getCompany_id());
						ProductDetails product = (ProductDetails) productQuery.uniqueResult();

						materialIds.add(salesDetail.getProduct_id());
						quantity.add("" + salesDetail.getProduct_stock());
						materialCodes.add(product.getProduct_code());

						WsSalesOrderDetailsNew2 wsSalesdetails = new WsSalesOrderDetailsNew2();
						wsSalesdetails.setItem_discount_type(salesDetail.getItem_discount_type());
						wsSalesdetails.setItem_discounted_price(salesDetail.getItem_discounted_price());
						wsSalesdetails.setProduct_rate(salesDetail.getProduct_rate());
						wsSalesdetails.setExwRate(salesDetail.getExwRate());
						wsSalesdetails.setProduct_stock(salesDetail.getProduct_stock());
						wsSalesdetails.setProductCode(product.getProduct_code());
						if (product.getPackunit() != null && product.getPackunit().trim().length() > 0)
							wsSalesdetails.setPack_unit(product.getPackunit().toUpperCase());
						else
							wsSalesdetails.setPack_unit("EA");

						units.add(wsSalesdetails.getPack_unit());
						wsSalesDetailsList.add(wsSalesdetails);
					}

					allMaterialStockAvailable = sapIntegrationDao.checkMaterialStockAvailability(materialCodes, units,
							quantity, currSalesMaser.getCompany_id(), currSalesMaser.getPlant());

					// isCreditAvailable=sb.getCreditStatus(workFlowTaskStatusService,sales_master_id,
					// currSalesMaser.getSoldToParty(), currSalesMaser.getCompany_id(),
					// currSalesMaser.getTotalamount());

					FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
							"Is Material Stock avaialble?: " + allMaterialStockAvailable);
					if (allMaterialStockAvailable) {

						FieldMILogger.info(this.getClass().getName(), "Sales Order to be sent to SAP: "
								+ sales_master_id + " for customer: " + customer.getCustomer_id());

						WsSalesOrderwithEachItemOffer2 wsSalesOrderwithEachItemOffer = new WsSalesOrderwithEachItemOffer2();
						wsSalesOrderwithEachItemOffer
								.setCompany_id(Integer.parseInt(currSalesMaser.getCompany_id() + ""));
						wsSalesOrderwithEachItemOffer.setFreight_applicable(currSalesMaser.getFreight_applicable());
						if (currSalesMaser.getPoDate() != null)
							wsSalesOrderwithEachItemOffer.setPoDate(currSalesMaser.getPoDate());
						if (currSalesMaser.getDeliveryDate() != null)
							wsSalesOrderwithEachItemOffer.setDeliveryDate(currSalesMaser.getDeliveryDate());
						if (currSalesMaser.getCreated_date() != null)
							wsSalesOrderwithEachItemOffer.setCreated_date_time(currSalesMaser.getCreated_date());

						wsSalesOrderwithEachItemOffer.setShipToParty(currSalesMaser.getShipToParty());
						wsSalesOrderwithEachItemOffer.setSoldToParty(currSalesMaser.getSoldToParty());
						wsSalesOrderwithEachItemOffer.setPlant(currSalesMaser.getPlant());
						wsSalesOrderwithEachItemOffer.setPoNumber(currSalesMaser.getPoNumber());
						wsSalesOrderwithEachItemOffer.setFreightRate(currSalesMaser.getFreightRate());
						wsSalesOrderwithEachItemOffer.setSales_master_id(currSalesMaser.getSales_master_id());
						if (shipToPartyCustomer != null) {

							Query cityQuery = session
									.createQuery("From Citys where city_id=" + shipToPartyCustomer.getNew_city());
							if (cityQuery.uniqueResult() != null)
								wsSalesOrderwithEachItemOffer
										.setShipToPartyCity(((Citys) cityQuery.uniqueResult()).getCity_name());
						}
						wsSalesOrderwithEachItemOffer.setRemark(currSalesMaser.getRemark());
						WsCustomer wsCustomer = new WsCustomer();
						wsCustomer.setCode(customer.getCode());
						wsCustomer.setCustomer_id(customer.getCustomer_id());
						wsCustomer.setCustomerAccountGroup(customer.getCustomerAccountGroup());
						wsCustomer.setDistributor_channel(customer.getDistributor_channel());
						wsCustomer.setDistrict(customer.getDistrict());
						wsCustomer.setDivision(customer.getDivision());
						wsCustomer.setPlant(customer.getPlant());
						wsCustomer.setSales_group(customer.getSales_group());
						wsCustomer.setSales_office(customer.getSales_office());
						wsCustomer.setSales_organization(customer.getSales_organization());
						wsCustomer.setSalesDistrict(customer.getSalesDistrict());
						wsCustomer.setTown(customer.getTown());

						returnSOId = sapIntegrationDao.sendSOToSAP(wsSalesOrderwithEachItemOffer, wsSalesDetailsList,
								wsCustomer);
						if (returnSOId != null && returnSOId.trim().length() > 0) {

							FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
									"Sales Order response from SAP: " + returnSOId);
							Query salesMasterUpdateQuery = session.createSQLQuery("Update sales_master set sapOrderId='"
									+ returnSOId + "', exportedToSAP=1,updated_date = '" + updatedDate
									+ "',sapSentDate='" + updatedDate + "' , message='Success'  where sales_master_id="
									+ sales_master_id);

							salesMasterUpdateQuery.executeUpdate();
							// update work flow

							FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
									"Updating material stock  available in SAP for SO :" + sales_master_id);
							Query workFlowUpdateQuery = session.createSQLQuery(
									"Update work_flow_task_status set externalComment=null where sales_master_id="
											+ sales_master_id + " and task_name='"
											+ SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK + "'");

							workFlowUpdateQuery.executeUpdate();

							return returnSOId;
						}
					} else {
						FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
								"Updating material stock not available in SAP for SO :" + sales_master_id);
						Query workFlowUpdateQuery = session.createSQLQuery(
								"Update work_flow_task_status set externalComment='Stock unavailble in SAP' where sales_master_id="
										+ sales_master_id + " and task_name='"
										+ SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK + "'");

						workFlowUpdateQuery.executeUpdate();
					}

				}
			}

			return returnSOId;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public CustomerDetails getCustomerDetails(int c_id, String customerCode) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"from CustomerDetails c" + " WHERE c.company_id=" + c_id + " and c.code='" + customerCode + "'");
			List<CustomerDetails> list = query.list();

			if (list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public String getSaporderId(int c_id, int salesMasterId) {
		Session session = initiateSession();
		try {

			Query query = session.createQuery("select sapOrderId from SalesMaster where sales_master_id="
					+ salesMasterId + " and company_id=" + c_id);
			String name = (String) query.uniqueResult();
			if (name != null && name.length() > 0) {
				return name;
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
	public WsSalesReportOutput getSalesOrderReportForSil(WsSalesOrderReportInput wSSalesOrderReportInput, Long id) {

		Session session = initiateSession();
		WsSalesReportOutput wSalesReportOutput = new WsSalesReportOutput();
		List<WsSalesOrderReport> report = new ArrayList<>();
		try {

			String user_list = wSSalesOrderReportInput.getUser_list();
			String targetMonth = wSSalesOrderReportInput.getTarget_month();
			String year = targetMonth.substring(0, Math.min(targetMonth.length(), 4));
			String month = targetMonth.substring(5, 7);
			String toDays = "31";
			String sql = "";
			List list = null;
			switch (month) {
			case "01":
			case "03":
			case "05":
			case "07":
			case "08":
			case "10":
			case "12":
				toDays = "31";
				break;
			case "04":
			case "06":
			case "09":
			case "11":
				toDays = "30";
				break;
			}

			if (month.equals("02")) {
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

			int count = 0;

			List<Integer> users = commonMethods.getUserHirachyWise(wSSalesOrderReportInput.getCompany_id(),
					id.intValue(), session, "", "", "", "");

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
							"select sm.sales_master_id, sm.user_id, c.customer_name, sm.created_date, sm.status, sm.totalamount, c.address,c.code,c.customer_group,sm.cashDiscountOrQuaterlyDiscount,sm.freight_applicable,sm.sapOrderId,sm.shipToParty from sales_master sm");
					query.append(" inner join customer_details c on c.customer_id=sm.customer_id  ");
					query.append(" where sm.company_id=" + wSSalesOrderReportInput.getCompany_id()
							+ " and sm.user_id IN (" + userid + ") and Date(sm.created_date) between '" + fromDate
							+ "' and '" + toDate + "' and sm.status=1  order by sm.created_date desc");

					sql = "select count(*) from sales_master sm inner join customer_details c on c.customer_id=sm.customer_id where sm.company_id="
							+ wSSalesOrderReportInput.getCompany_id() + " and sm.user_id IN (" + userid
							+ ") and Date(sm.created_date) between '" + fromDate + "' and '" + toDate
							+ "' and sm.status=1 ";

					list = commonMethods.quarySQLListPagination(session, query.toString(),
							wSSalesOrderReportInput.getPage_no(), no_of_records_per_page);
					count = commonMethods.getTotalPagesCountSql(session, sql);
					for (Object object : list) {
						WsSalesOrderReport salesOrderOutput = new WsSalesOrderReport();
						Object[] obj = (Object[]) object;
						salesOrderOutput.setSales_master_id((int) obj[0]);
						salesOrderOutput.setUser_id((int) obj[1]);
						salesOrderOutput.setCustomer_name((String) obj[2]);
						salesOrderOutput.setStatus("" + obj[4]);
						salesOrderOutput.setAddress((String) obj[6]);
						salesOrderOutput.setTotalamount((double) obj[5]);
						String userName = getUserName((int) obj[1]);
						salesOrderOutput.setUserName(userName);
						salesOrderOutput.setCustomerCode((String) obj[7]);
						salesOrderOutput.setCustomerGroup((String) obj[8]);
						salesOrderOutput.setCashDiscountOrQuaterlyDiscount((String) obj[9]);
						salesOrderOutput.setSalesOrderType((String) obj[10]);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date d = (Date) obj[3];
						String s = df.format(d);
						salesOrderOutput.setCreated_date(s);
						salesOrderOutput.setSapOrderId((String) obj[11]);
						String pricingDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
								Integer.parseInt("" + wSSalesOrderReportInput.getCompany_id()), (int) obj[0],
								SalesMIDBUtils.PRICING_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
						String materialDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
								Integer.parseInt("" + wSSalesOrderReportInput.getCompany_id()), (int) obj[0],
								SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
						String loadDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
								Integer.parseInt("" + wSSalesOrderReportInput.getCompany_id()), (int) obj[0],
								SalesMIDBUtils.LOAD_CONDITION_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
						HashMap<String, String> soTaskVsStatus = new HashMap<>();
						soTaskVsStatus.put(SalesMIDBUtils.PRICING_ISSUE_TASK, pricingDetailsStatus);
						soTaskVsStatus.put(SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK, materialDetailsStatus);
						soTaskVsStatus.put(SalesMIDBUtils.LOAD_CONDITION_ISSUE_TASK, loadDetailsStatus);
						salesOrderOutput.setWorkflow_status(soTaskVsStatus);
						CustomerDetails cDetails = getCustomerDetails(wSSalesOrderReportInput.getCompany_id(),
								(String) obj[12]);
						if (cDetails != null && !cDetails.equals("")) {
							salesOrderOutput.setShipToParty(cDetails.getCustomer_name() + "|" + cDetails.getCode());
						} else {
							salesOrderOutput.setShipToParty("");
						}

						count++;
						report.add(salesOrderOutput);
					}
					wSalesReportOutput.setWsSalesOrderReportList(report);
					wSalesReportOutput.setTotalRecords(count);
					wSalesReportOutput.setRecordsPerPage(no_of_records_per_page);
					wSalesReportOutput.setPage_no(wSSalesOrderReportInput.getPage_no());
					wSalesReportOutput.setTotal_pages(count);
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
							"select sm.sales_master_id, sm.user_id, c.customer_name, sm.created_date, sm.status, sm.totalamount, c.address,c.code,c.customer_group,sm.cashDiscountOrQuaterlyDiscount,sm.freight_applicable,sm.sapOrderId,sm.shipToParty from sales_master sm");
					query.append(" inner join customer_details c on c.customer_id=sm.customer_id  ");
					query.append(" where sm.company_id=" + wSSalesOrderReportInput.getCompany_id()
							+ " and sm.user_id IN (" + userid1 + ") and Date(sm.created_date) between '" + fromDate
							+ "' and '" + toDate + "' and sm.status=1 order by sm.created_date desc");
					sql = "select count(*) from sales_master sm inner join customer_details c on c.customer_id=sm.customer_id where sm.company_id="
							+ wSSalesOrderReportInput.getCompany_id() + " and sm.user_id IN (" + userid1
							+ ") and Date(sm.created_date) between '" + fromDate + "' and '" + toDate
							+ "' and sm.status=1 ";
					list = commonMethods.quarySQLListPagination(session, query.toString(),
							wSSalesOrderReportInput.getPage_no(), no_of_records_per_page);
					count = commonMethods.getTotalPagesCountSql(session, sql);

					count = commonMethods.getTotalPagesCountSql(session, sql);
					for (Object object : list) {
						WsSalesOrderReport salesOrderOutput = new WsSalesOrderReport();
						Object[] obj = (Object[]) object;
						salesOrderOutput.setSales_master_id((int) obj[0]);
						salesOrderOutput.setUser_id((int) obj[1]);
						salesOrderOutput.setCustomer_name((String) obj[2]);
						salesOrderOutput.setStatus("" + obj[4]);
						salesOrderOutput.setAddress((String) obj[6]);
						salesOrderOutput.setTotalamount((double) obj[5]);
						String userName = getUserName((int) obj[1]);
						salesOrderOutput.setUserName(userName);
						salesOrderOutput.setCustomerCode((String) obj[7]);
						salesOrderOutput.setCustomerGroup((String) obj[8]);
						salesOrderOutput.setCashDiscountOrQuaterlyDiscount((String) obj[9]);
						salesOrderOutput.setSalesOrderType((String) obj[10]);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date d = (Date) obj[3];
						String s = df.format(d);
						salesOrderOutput.setCreated_date(s);
						salesOrderOutput.setSapOrderId((String) obj[11]);
						String pricingDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
								Integer.parseInt("" + wSSalesOrderReportInput.getCompany_id()), (int) obj[0],
								SalesMIDBUtils.PRICING_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
						String materialDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
								Integer.parseInt("" + wSSalesOrderReportInput.getCompany_id()), (int) obj[0],
								SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
						String loadDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(
								Integer.parseInt("" + wSSalesOrderReportInput.getCompany_id()), (int) obj[0],
								SalesMIDBUtils.LOAD_CONDITION_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
						HashMap<String, String> soTaskVsStatus = new HashMap<>();
						soTaskVsStatus.put(SalesMIDBUtils.PRICING_ISSUE_TASK, pricingDetailsStatus);
						soTaskVsStatus.put(SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK, materialDetailsStatus);
						soTaskVsStatus.put(SalesMIDBUtils.LOAD_CONDITION_ISSUE_TASK, loadDetailsStatus);
						salesOrderOutput.setWorkflow_status(soTaskVsStatus);
						CustomerDetails cDetails = getCustomerDetails(wSSalesOrderReportInput.getCompany_id(),
								(String) obj[12]);

						if (cDetails != null) {
							salesOrderOutput.setShipToParty(cDetails.getCustomer_name() + "|" + cDetails.getCode());
						} else {
							salesOrderOutput.setShipToParty("");
						}

						report.add(salesOrderOutput);
					}
					wSalesReportOutput.setWsSalesOrderReportList(report);
					wSalesReportOutput.setTotalRecords(count);
					wSalesReportOutput.setRecordsPerPage(no_of_records_per_page);
					wSalesReportOutput.setPage_no(wSSalesOrderReportInput.getPage_no());
					wSalesReportOutput.setTotal_pages(count);
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
	public List<WsSalesOrderProductList> getSalesDetailsReportForSil(WsSalesDetailsReportInput credentials, Long id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			String sql = "select product_details.name,(sales_details.product_stock) as qty,sales_details.product_rate,(sales_details.product_stock*sales_details.product_rate) as total,category_details.category_name,product_details.product_code,product_details.packtitle,sales_details.pack,product_details.packunit,sales_details.pricemaster_id,sales_details.discount,offer_id,scheme_id,scheme_name,scheme_type,offer_desc,tax,rate,percentage,sales_master.totalamount,sales_master.discount as finaldiscount, sales_details.pack_qty, sales_details.pack_rate"
					+ " from sales_master,sales_details left outer JOIN pricemaster_details ON pricemaster_details.pricemaster_id=sales_details.pricemaster_id left outer join manage_tax on manage_tax.manage_tax_id=pricemaster_details.tax  INNER JOIN product_details on sales_details.product_id = product_details.product_id  INNER Join category_details on product_details.category_id=category_details.category_id where sales_details.sales_master_id="
					+ credentials.getSales_master_id()
					+ " and sales_master.sales_master_id=sales_details.sales_master_id";
			List listOfProduct = session.createSQLQuery(sql.toString()).list();
			ArrayList<WsSalesOrderProductList> productList = new ArrayList<>();
			if (listOfProduct != null) {

				for (Object objectOfProduct : listOfProduct) {
					Object[] productObject = (Object[]) objectOfProduct;
					WsSalesOrderProductList pList = new WsSalesOrderProductList();
					pList.setProductName((String) productObject[0]);
					pList.setQty((double) productObject[1]);
					pList.setPrice((double) productObject[2]);
					pList.setDiscount((double) productObject[10]);
					pList.setAmount((double) productObject[3]);
					productList.add(pList);
				}
			}

			return productList;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public List<SalesMaster> getSalesOrderList(int c_id) {

		Session session = initiateSession();

		List<SalesMaster> salesMaster = new ArrayList<SalesMaster>();

		try {
			String hql = "select sales_master_id,total_weight_in_ton From SalesMaster where company_id=" + c_id
					+ " and status=1";
			Query query = session.createQuery(hql);
			// salesMaster = query.list();
			List list = query.list();
			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				SalesMaster sm = new SalesMaster();

				sm.setSales_master_id((int) obj1[0]);
				sm.setTotal_weight_in_ton((double) obj1[1]);
				salesMaster.add(sm);
			}

			return salesMaster;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public String getInvoiceNumberFromSAP(int sales_master_id) throws Exception {
		Session session = initiateSession();
		try {
			String returnSOId = "";
			if (session != null) {
				Date d = new Date();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String updatedDate = format1.format(d);

				Query salesMasterQuery = session
						.createQuery("From SalesMaster where sales_master_id=" + sales_master_id);
				SalesMaster currSalesMaser = (SalesMaster) salesMasterQuery.uniqueResult();

				if (currSalesMaser != null && currSalesMaser.getSapOrderId() != null
						&& currSalesMaser.getSapOrderId().trim().length() != 0) {

					String userName = fileProperties.getProperty(SAPUrls.SAP_USERNAME + currSalesMaser.getCompany_id());
					String password = fileProperties.getProperty(SAPUrls.SAP_PASSWORD + currSalesMaser.getCompany_id());
					SapIntegrationDao sapIntegrationDao = new SapIntegrationDao(userName, password, fileProperties);

					returnSOId = sapIntegrationDao.getInvoiceNumberFromSAP(currSalesMaser.getSapOrderId(),
							currSalesMaser.getCompany_id());
					if (returnSOId != null && returnSOId.trim().length() > 0) {

						FieldMILogger.info(FMSalesMasterDaoImpl.class.getName(),
								"Sales Order response from SAP: " + returnSOId);
						Query salesMasterUpdateQuery = session.createSQLQuery(
								"Update sales_master set sapInvoiceNumber='" + returnSOId + "' , updated_date = '"
										+ updatedDate + "',invoiceMessage='Invoice Generated'  where sales_master_id="
										+ sales_master_id);

						salesMasterUpdateQuery.executeUpdate();

						return returnSOId;
					}
				}
			}
			return returnSOId;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {

			destroySession(session);
		}

	}

	@Override
	public SalesMaster getSOObject(int sales_master_id) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery("from SalesMaster s" + " WHERE s.sales_master_id=" + sales_master_id + "");
			List<SalesMaster> list = query.list();

			if (list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public boolean deleteSilSalesOrder(int sales_master_id_reject, int c_id) {
		Session session = initiateSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query1 = session.createSQLQuery("update sales_master set status = 0  where sales_master_id="
					+ sales_master_id_reject + " and company_id=" + c_id);
			query1.executeUpdate();

			Query query2 = session
					.createSQLQuery("delete from sales_details where sales_master_id=" + sales_master_id_reject);
			query2.executeUpdate();

			Query query3 = session.createSQLQuery("delete from work_flow_task_status where sales_master_id="
					+ sales_master_id_reject + " and company_id=" + c_id);
			query3.executeUpdate();

			transaction.commit();
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
	public HashMap<String, Double> getMaterialStatus(int salesMasterId, int c_id) {

		Session session = initiateSession();

		HashMap<String, Double> materialVsStock = null;
		try {
			if (session != null) {

				Query salesMasterQuery = session.createQuery("From SalesMaster where sales_master_id=" + salesMasterId);
				SalesMaster currSalesMaser = (SalesMaster) salesMasterQuery.uniqueResult();
				if (currSalesMaser != null) {

					String userName = fileProperties.getProperty(SAPUrls.SAP_USERNAME + c_id);
					String password = fileProperties.getProperty(SAPUrls.SAP_PASSWORD + c_id);
					SapIntegrationDao sapIntegrationDao = new SapIntegrationDao(userName, password, fileProperties);
					Query salesDetailsQuery = session
							.createQuery("From SalesDetails where sales_master_id=" + salesMasterId);
					List<SalesDetails> salesDetails = salesDetailsQuery.list();

					List<Integer> materialIds = new ArrayList<>();
					List<String> materialCodes = new ArrayList<>();
					List<String> quantity = new ArrayList<>();
					List<String> units = new ArrayList<>();
					List<WsSalesOrderDetailsNew2> wsSalesDetailsList = new ArrayList<>();
					for (SalesDetails salesDetail : salesDetails) {
						Query productQuery = session.createQuery("From ProductDetails where product_id="
								+ salesDetail.getProduct_id() + " and company_id= " + c_id + " and status=1");
						ProductDetails product = (ProductDetails) productQuery.uniqueResult();
						materialIds.add(salesDetail.getProduct_id());
						quantity.add("" + salesDetail.getProduct_stock());
						materialCodes.add(product.getProduct_code());
						WsSalesOrderDetailsNew2 wsSalesdetails = new WsSalesOrderDetailsNew2();
						wsSalesdetails.setItem_discount_type(salesDetail.getItem_discount_type());
						wsSalesdetails.setItem_discounted_price(salesDetail.getItem_discounted_price());
						wsSalesdetails.setProduct_rate(salesDetail.getProduct_rate());
						wsSalesdetails.setExwRate(salesDetail.getExwRate());
						wsSalesdetails.setProduct_stock(salesDetail.getProduct_stock());
						wsSalesdetails.setProductCode(product.getProduct_code());
						if (product.getPackunit() != null && product.getPackunit().trim().length() > 0)
							wsSalesdetails.setPack_unit(product.getPackunit().toUpperCase());
						else
							wsSalesdetails.setPack_unit("EA");

						units.add(wsSalesdetails.getPack_unit());
						wsSalesDetailsList.add(wsSalesdetails);
					}
					WsPriceDetails priceDetails = new WsPriceDetails();
					List<WsSAPMaterialDetails> maeterialDetails = new ArrayList<>();
					if (materialCodes != null && materialCodes.size() > 0) {

						for (int i = 0; i < materialCodes.size(); i++) {
							WsSAPMaterialDetails wsSAPMaterialDetails = new WsSAPMaterialDetails();
							wsSAPMaterialDetails.setMaterialNo(materialCodes.get(i));
							wsSAPMaterialDetails.setQty(Double.parseDouble(quantity.get(i)));
							wsSAPMaterialDetails.setUnit(units.get(i));
							maeterialDetails.add(wsSAPMaterialDetails);
						}

						priceDetails.setMaterialDetails(maeterialDetails);
						priceDetails.setCompany_id(c_id);
						materialVsStock = sapIntegrationDao.getMaterialStockFromSAP(priceDetails,
								currSalesMaser.getPlant());

					}
				}
			}
			return materialVsStock;

		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			// throw ex;
		} finally {

			destroySession(session);
		}
		return materialVsStock;

	}

	@Override
	public List<SalesDetails> getSalesDetailsList(List<String> salesDetailsIdList) {

		Session session = initiateSession();
		try {

			StringBuilder sqlForWorkFlowStatus = new StringBuilder();
			sqlForWorkFlowStatus.append("From SalesDetails");
			sqlForWorkFlowStatus.append(" where sales_details_id IN (");

			int cnt = 0;
			for (String salesDetailsID : salesDetailsIdList) {

				if (cnt > 0 && cnt != (salesDetailsIdList.size()))
					sqlForWorkFlowStatus.append(",");

				sqlForWorkFlowStatus.append(salesDetailsID);
				cnt++;
			}
			sqlForWorkFlowStatus.append(")");

			return session.createQuery(sqlForWorkFlowStatus.toString()).list();
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {

			destroySession(session);
		}

	}

	@Override
	public List<SalesMaster> unSyncedList(int c_id, String sqluser, String sqltype, String sqlplant) {

		Session session = initiateSession();

		List<SalesMaster> salesMaster = new ArrayList<SalesMaster>();

		try {
			String sql = "";
			sql = "SELECT s.sales_master_id,s.customer_id,concat(u.first_name,' ',u.last_name) as sales_person,s.totalamount,s.created_date,s.freight_applicable,s.plant,s.message FROM sales_master s join user_details u on s.created_by=u.user_id    where s.company_id="
					+ c_id + " and s.exportedToSAP=0  and s.status=1 and s.company_id=" + c_id + " " + sqluser + " "
					+ sqltype + " " + sqlplant + "  order by s.created_date";
			Query query = session.createSQLQuery(sql);
			// salesMaster = query.list();
			List list = query.list();
			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				SalesMaster sm = new SalesMaster();

				sm.setSales_master_id((int) obj1[0]);
				sm.setCustomer_id((int) obj1[1]);
				sm.setAddress((String) obj1[2]);
				sm.setTotalamount((double) obj1[3]);
				sm.setCreated_date((Date) obj1[4]);
				sm.setFreight_applicable((String) obj1[5]);
				CustomerDetails cDetails = getCustomerName((int) obj1[1]);
				sm.setApproval_status(cDetails.getCustomer_name());
				sm.setCustomer_name(cDetails.getCode());
				sm.setPlant((String) obj1[6]);
				if ((String) obj1[7] != null) {
					sm.setMessage((String) obj1[7]);
				}
				long startTimePageLoad = Calendar.getInstance().getTimeInMillis();
				FieldMILogger.warn(FMSalesMasterDaoImpl.class.getName(),
						"Start time for UnsyncedOrder" + startTimePageLoad);

				String pricingDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(c_id,
						(int) obj1[0], SalesMIDBUtils.PRICING_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				String materialDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(c_id,
						(int) obj1[0], SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK,
						SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				String loadDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(c_id,
						(int) obj1[0], SalesMIDBUtils.LOAD_CONDITION_ISSUE_TASK,
						SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				String creditDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask(c_id,
						(int) obj1[0], SalesMIDBUtils.CREDIT_ISSUE, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);

				FieldMILogger.warn(FMSalesMasterDaoImpl.class.getName(),
						"End time for UnsyncedOrder" + (Calendar.getInstance().getTimeInMillis() - startTimePageLoad));
				if (pricingDetailsStatus.startsWith(SalesMIDBUtils.PENDING)) {
					sm.setSoldToParty("<p style='color:red'><i>" + pricingDetailsStatus + "</i></p>");
					sm.setPricingIssue(pricingDetailsStatus);
				} else {
					sm.setSoldToParty("<p style='color:green'><i>" + pricingDetailsStatus + "</i></p>");
					sm.setPricingIssue(pricingDetailsStatus);
				}

				if (materialDetailsStatus.startsWith(SalesMIDBUtils.PENDING)) {

					sm.setShipToParty("<p style='color:red'><i>" + materialDetailsStatus + "</i></p>");
					sm.setMaterialIssue(materialDetailsStatus);
				} else {
					sm.setShipToParty("<p style='color:green'><i>" + materialDetailsStatus + "</i></p>");
					sm.setMaterialIssue(materialDetailsStatus);

				}
				if (loadDetailsStatus.startsWith(SalesMIDBUtils.PENDING)) {
					sm.setCashDiscountOrQuaterlyDiscount("<p style='color:red'><i>" + loadDetailsStatus + "</i></p>");
					sm.setUnderLoad(loadDetailsStatus);
				} else {
					sm.setCashDiscountOrQuaterlyDiscount("<p style='color:green'><i>" + loadDetailsStatus + "</i></p>");
					sm.setUnderLoad(loadDetailsStatus);
				}
				if (creditDetailsStatus.startsWith(SalesMIDBUtils.PENDING)) {
					sm.setContact_no("<p style='color:red'><i>" + creditDetailsStatus + "</i></p>");
					sm.setCreditIssue(creditDetailsStatus);
				} else {
					sm.setContact_no("<p style='color:green'><i>" + creditDetailsStatus + "</i></p>");
					sm.setCreditIssue(creditDetailsStatus);
				}
				List<WorkFlowTasksStatus> tasks = workFlowTaskStatusService.getSalesOrderStatusBasedOnTaskName(c_id,
						(int) obj1[0], SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK,
						SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				if (tasks.size() > 0) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (tasks.get(0).getCustom_field_date() != null) {

						sm.setDespatch_document_number(sdf.format(tasks.get(0).getCustom_field_date()));
					} else {
						sm.setDespatch_document_number("");
					}
					if (tasks.get(0).getComment() != null) {
						sm.setLat(tasks.get(0).getComment() + " " + tasks.get(0).getExternalComment());
					}

				}

				salesMaster.add(sm);
			}

			return salesMaster;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	private CustomerDetails getCustomerName(int i) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query = session.createQuery("from CustomerDetails where customer_id=" + i + "");

			return (CustomerDetails) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<WsSalesOrderReport> getTomorrowsOrders(Credentials credentials, Long id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			List<WsSalesOrderReport> salesMaster = new ArrayList<WsSalesOrderReport>();
			Date dt = new Date();
			String ids = "";
			DateTime dtOrg = new DateTime(dt);
			DateTime dtPlusOne = dtOrg.plusDays(1);
			Date dt1 = dtPlusOne.toDate();
			List<Integer> salesMasterIds = getMasterIds(dt1, id.intValue(), session,
					credentials.getCompany_id().intValue());

			if (salesMasterIds != null && salesMasterIds.size() > 0) {
				for (int i = 0; i < salesMasterIds.size(); i++) {
					if (i == salesMasterIds.size() - 1) {
						ids = ids + salesMasterIds.get(i);
					} else {
						ids = ids + salesMasterIds.get(i) + " ,";
					}
				}
				String sql = "select sales_master_id,customer_id,totalamount from sales_master where sales_master_id in ("
						+ ids + ") and company_id=" + credentials.getCompany_id().intValue() + " and user_id="
						+ id.intValue() + " and status=1";
				Query query = session.createSQLQuery(sql);
				List list = query.list();
				for (Object obj : list) {
					Object[] obj1 = (Object[]) obj;
					WsSalesOrderReport sm = new WsSalesOrderReport();

					sm.setSales_master_id((int) obj1[0]);
					CustomerDetails cName = getCustomerName((int) obj1[1]);
					sm.setCustomer_name(cName.getCustomer_name());
					sm.setTotalamount((double) obj1[2]);

					salesMaster.add(sm);
				}
				return salesMaster;
			} else {
				return salesMaster;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}

	}

	private List<Integer> getMasterIds(Date dt1, int id, Session session, int company_id) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String q = "select Distinct(sales_master_id) from work_flow_task_status where custom_field_date='"
				+ sdf.format(dt1) + "' and created_by=" + id + " and company_id=" + company_id + " and task_name='"
				+ SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK + "'";
		Query q1 = session.createSQLQuery(q);
		List<Integer> masterIds = q1.list();
		return masterIds;
	}

	@Override
	public List<SalesMaster> getSalesData(int c_id, String month, int userId) {
		Session session = initiateSession();
		List<SalesMaster> objectToBeShowed = new ArrayList<SalesMaster>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			Query datesQuery = null;
			Query userQuery = null;

			String userSql = "";

			if (userId != 0) {
				userSql = " and sm.user_id=" + userId;
			}

			datesQuery = session.createSQLQuery("select fmc.db_date from fm_calendar fmc where  fmc.year="
					+ month.split(" ")[1] + " and fmc.month_name='" + month.split(" ")[0].toLowerCase() + "'");
			List<Date> dList = datesQuery.list();
			String sql = "select distinct(sm.customer_id),sum(sm.totalamount),cd.customer_name,cd.code,sm.created_date from sales_master sm ,customer_details cd where cd.customer_id=sm.customer_id and sm.company_id="
					+ c_id + " and  DATE(sm.created_date) between '" + dList.get(0) + "' and '"
					+ dList.get(dList.size() - 1) + "' " + userSql + " group by sm.customer_id order by sm.created_date"
					+ "";
			userQuery = session.createSQLQuery(sql);

			List list = userQuery.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				SalesMaster master = new SalesMaster();
				master.setTotal_amount(CommonMethods.roundDouble((double) obj[1], 2));
				master.setCustomer_name((String) obj[2] + "|" + (String) obj[3]);
				master.setAddress(format.format(obj[4]));
				objectToBeShowed.add(master);
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
		return objectToBeShowed;
	}

	@Override
	public List<SalesMaster> unSyncedList(int c_id) {

		Session session = initiateSession();

		List<SalesMaster> salesMaster = new ArrayList<SalesMaster>();

		try {
			String sql = "";
			sql = "SELECT s.sales_master_id,s.customer_id,concat(u.first_name,' ',u.last_name) as sales_person,s.totalamount,s.created_date,s.freight_applicable,s.plant FROM sales_master s join user_details u on s.created_by=u.user_id    where s.company_id="
					+ c_id + " and s.exportedToSAP=0  and s.status=1 and s.company_id=" + c_id
					+ " order by s.created_date";
			Query query = session.createSQLQuery(sql);
			// salesMaster = query.list();
			List list = query.list();
			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				SalesMaster sm = new SalesMaster();

				sm.setSales_master_id((int) obj1[0]);
				sm.setCustomer_id((int) obj1[1]);
				sm.setAddress((String) obj1[2]);
				sm.setTotalamount((double) obj1[3]);
				sm.setCreated_date((Date) obj1[4]);
				sm.setFreight_applicable((String) obj1[5]);
				sm.setPlant((String) obj1[6]);

				salesMaster.add(sm);
			}

			return salesMaster;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public void updateSalesMaster(String message, int salesMasterId, int c_id, int value) {

		Session session = initiateSession();
		try {
			if (value == 1) {
				Query salesMasterUpdateQuery = session.createSQLQuery("Update sales_master set  message='" + message
						+ "'  where sales_master_id=" + salesMasterId + " and company_id=" + c_id);

				salesMasterUpdateQuery.executeUpdate();
			} else {
				Query salesMasterUpdateQuery = session.createSQLQuery("Update sales_master set  invoiceMessage='"
						+ message + "'  where sales_master_id=" + salesMasterId + " and company_id=" + c_id);

				salesMasterUpdateQuery.executeUpdate();
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

	}

	@Override
	public List<WsSalesExportWithDetails> exportData(String sql) {
		// TODO Auto-generated method stub
		Session session = initiateSession();

		try {
			List<WsSalesExportWithDetails> wsSalesExportWithDetails = new ArrayList<>();
			Query query = session.createSQLQuery(sql);

			List list = query.list();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				WsSalesExportWithDetails ws = new WsSalesExportWithDetails();
//				HashMap<String, Double> stock = fmSalesMasterService.getMaterialStatus((int)obj1[0], (int)obj1[18]);
				ws.setSalesMasterId((int) obj1[0]);
				ws.setSalesPerson((String) obj1[1]);
				ws.setSoldToPartyName((String) obj1[2]);
				ws.setSoldToPartyCode((String) obj1[3]);
				ws.setCreatedDate(format.format(obj1[4]).toString());
				ws.setTotal(Double.toString((double) obj1[6]));
				ws.setSapOrderNumber((String) obj1[8]);
				ws.setSapInvoiceNumber((String) obj1[9]);
				ws.setPlant((String) obj1[10]);
				ws.setSalesOrderType((String) obj1[11]);
				ws.setPack((String) obj1[13]);
				ws.setProductCode((String) obj1[14]);
				ws.setQty(Double.toString((double) obj1[15]));
//				if(stock.containsKey(obj1[14].toString())) {
//					double qty = stock.get(obj1[14].toString());
//					if(qty==0||Double.parseDouble(ws.getQty()) > qty) {
//						ws.setIsMaterialAvailable("NO");
//					}else {
//						ws.setIsMaterialAvailable("YES");
//					}
//				}

				String pricingDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask((int) obj1[18],
						(int) obj1[0], SalesMIDBUtils.PRICING_ISSUE_TASK, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				String materialDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask((int) obj1[18],
						(int) obj1[0], SalesMIDBUtils.MATERIAL_STOCK_ISSUE_TASK,
						SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				String loadDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask((int) obj1[18],
						(int) obj1[0], SalesMIDBUtils.LOAD_CONDITION_ISSUE_TASK,
						SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				String creditDetailsStatus = workFlowTaskStatusService.getFinalSalesOrderStatusForTask((int) obj1[18],
						(int) obj1[0], SalesMIDBUtils.CREDIT_ISSUE, SalesMIDBUtils.SALES_ORDER_WORKFLOW_NAME);
				ws.setPricingIssue(pricingDetailsStatus);
				ws.setMaterialIssue(materialDetailsStatus);
				ws.setUnderLoadIssue(loadDetailsStatus);
				ws.setCreditIssue(creditDetailsStatus);
				ws.setDiscount(Double.toString((double) obj1[17]));
				ws.setDistributor((String) obj1[19]);
				ws.setRoute((String) obj1[20]);

				wsSalesExportWithDetails.add(ws);

			}
			return wsSalesExportWithDetails;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<SalesMaster> checkExists(int custmerId, int c_id, double totalAmount) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query;
			query = session
					.createQuery("from SalesMaster where customer_id=:customer_id"
							+ " and company_id=:id and totalamount=:totalamount")
					.setParameter("id", c_id).setParameter("customer_id", custmerId)
					.setParameter("totalamount", totalAmount);

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
	public List<SalesOrder> exportSalesData(int company_id, int u_id, Date fromDate, Date toDate) {

		Session session = initiateSession();
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tallyFormat = new SimpleDateFormat("dd-MMM-yy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fromDate);
			Query<SalesMaster> query = session.createQuery(
					"from SalesMaster where status=1 and company_id=" + company_id + " and Date(created_date) between '"
							+ sdf.format(fromDate) + "' and '" + sdf.format(toDate) + "' and tallystatus=0",
					SalesMaster.class);
			List<SalesMaster> salesMasterList = query.list();
			if (salesMasterList != null && salesMasterList.size() > 0) {

				List<SalesOrder> salesDataList = new ArrayList<SalesOrder>();
				for (SalesMaster salesMaster : salesMasterList) {

					SalesOrder salesOrder = new SalesOrder();
					salesOrder.setSo_details(new SO_Details());
					salesOrder.getSo_details().setUniqueID("" + salesMaster.getSales_master_id());
					salesOrder.getSo_details().setVchNumber("" + salesMaster.getSales_master_id());
					salesOrder.getSo_details().setVchDate(tallyFormat.format(salesMaster.getCreated_date()));

					Query<SalesDetails> detailsQuery = session.createQuery(
							"from SalesDetails where status=1 and sales_master_id=" + salesMaster.getSales_master_id(),
							SalesDetails.class);
					List<SalesDetails> salesDetailsList = detailsQuery.list();
					if (salesDetailsList != null && salesDetailsList.size() > 0) {

					}

				}

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
	public SalesDetails checkSalesDetailsExist(int sales_master_id, int pricemaster_id, int product_id) {
		Session session = initiateSession();
		try {

			Query query = session.createQuery("from SalesDetails sd" + " WHERE sd.pricemaster_id=" + pricemaster_id
					+ " and sd.product_id=" + product_id + " and sd.sales_master_id=" + sales_master_id);
			List<SalesDetails> list = query.list();

			if (list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public List<SalesOrderReport> getSalesOrderReport(int company_id, int u_id, String fromDate, String toDate) {

		List<SalesOrderReport> listOfSalesOrderReport = new ArrayList<SalesOrderReport>();
		Session session = initiateSession();
		try {

			// SELECT approveRejectStatus,sum(totalamount),count(sales_master_id) from
			// sales_master where company_id= '10156' and Date(created_date) between
			// '2017-01-01' and '2022-11-30' group by approveRejectStatus
			Query orderReportQuery = session.createSQLQuery(
					"SELECT approveRejectStatus,sum(totalamount),count(sales_master_id) from sales_master where company_id="
							+ company_id + " and Date(created_date) between '" + fromDate + "' and '" + toDate
							+ "' group by approveRejectStatus");
			List queryResult = orderReportQuery.list();
			HashMap<String, SalesOrderReport> statusVsReportMap = new HashMap<String, SalesOrderReport>();
			for (Object resultObject : queryResult) {

				SalesOrderReport sor = null;
				Object[] row = (Object[]) resultObject;
				if (row[0] != null) {

					int status = Integer.parseInt(row[0].toString());
					if (status == 0 || status == 1 || status == 2) {

						if (statusVsReportMap.containsKey("Pending")) {

							sor = statusVsReportMap.get("Pending");
							
						} else {
							sor = new SalesOrderReport();
							sor.setOrderStatus("Pending");
							statusVsReportMap.put("Pending", sor);
						}

					} else if (status == 3) {

						if (statusVsReportMap.containsKey("Approved")) {

							sor = statusVsReportMap.get("Approved");
						} else {
							sor = new SalesOrderReport();
							sor.setOrderStatus("Approved");
							statusVsReportMap.put("Approved", sor);
						}
					}

				}
				if (row[1] != null) {

					double totalOfAllSalesOrderAmount = Double.parseDouble(row[1].toString());
					sor.setTotalAmount(sor.getTotalAmount() +totalOfAllSalesOrderAmount);
				}
				if (row[2] != null) {

					int countOfSalesOrders = Integer.parseInt(row[2].toString());
					sor.setCountOfSalesOrder(sor.getCountOfSalesOrder() +countOfSalesOrders);
				}
			}

			listOfSalesOrderReport.addAll(statusVsReportMap.values());
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return listOfSalesOrderReport;
		} finally {
			destroySession(session);
		}

		return listOfSalesOrderReport;
	}
}