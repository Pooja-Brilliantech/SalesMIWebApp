package com.fieldmi.daoImpl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMCustomerDao;
import com.fieldmi.stubs.WsAddAdditionalCustomer;
import com.fieldmi.stubs.WsAddContacts;
import com.fieldmi.stubs.WsCustomerDetails;
import com.fieldmi.stubs.WsUpdateCustomer;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.salesapp.pojo.CustomerContactDetails;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.CustomerImportRecords;
import com.softtantra.salesapp.pojo.CustomerTargetRecords;
import com.softtantra.salesapp.pojo.CustomerType;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.ws.WsCustomer360Data;

public class FMCustomerDaoImpl implements FMCustomerDao {

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
	public boolean updateCustomerForLatLong(WsUpdateCustomer wsUpdateCustomer) {

		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("from CustomerDetails where customer_id=" + wsUpdateCustomer.getCustomer_id());

			CustomerDetails customerDetails = (CustomerDetails) query.uniqueResult();
			if (customerDetails != null && !customerDetails.equals(" ")) {

				customerDetails.setLat(wsUpdateCustomer.getLat());
				customerDetails.setLongi(wsUpdateCustomer.getLongi());
				customerDetails.setAddress(wsUpdateCustomer.getAddress());
				session.update(customerDetails);
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
	public List<CustomerDetails> getShipToPartyList(int c_id,int value) {
		// TODO Auto-generated method stub
		List<CustomerDetails> cDetails = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("select customer_name,code,customer_id from CustomerDetails where company_id=" + c_id
							+ " and distributor_id="+value+" and status=1 and exportedToSAP=1 and sendToSAP=2");

			List list = query.list();

			if (list != null && list.size() > 0) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					CustomerDetails c = new CustomerDetails();
					c.setCustomer_name((String) obj[0] + " " + (String) obj[1]);
					c.setCode((String) obj[1]);
					c.setCustomer_id((Integer) obj[2]);
					cDetails.add(c);

				}
			}
		
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return cDetails;
	}

	@Override
	public CustomerDetails getCustomerObject(String customer_id, int c_id) {

		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("from CustomerDetails where code='" + customer_id + "' and company_id=" + c_id);

			CustomerDetails customerDetails = (CustomerDetails) query.uniqueResult();
			if (customerDetails != null && !customerDetails.equals(" ")) {

				return customerDetails;
			}
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public boolean addAdditionalCustomer(WsAddAdditionalCustomer wsAddAdditionalCustomer) {

		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("from CustomerDetails where customer_id=" + wsAddAdditionalCustomer.getCustomer_id()
							+ " and company_id=" + wsAddAdditionalCustomer.getCompany_id());

			CustomerDetails customerDetails = (CustomerDetails) query.uniqueResult();
			if (customerDetails != null) {

				for (int i = 0; i <= wsAddAdditionalCustomer.getAdditionalContacts().size(); i++) {
					int checkPresent = checkAdditionalPresent(wsAddAdditionalCustomer.getAdditionalContacts().get(i),
							wsAddAdditionalCustomer.getCustomer_id());
					if (checkPresent == 1) {
						CustomerContactDetails c = new CustomerContactDetails();
						c.setCompany_id(wsAddAdditionalCustomer.getCompany_id());
						c.setContact_email(wsAddAdditionalCustomer.getAdditionalContacts().get(i).getEmail());
						c.setContact_name(wsAddAdditionalCustomer.getAdditionalContacts().get(i).getContactName());
						c.setContact_no(wsAddAdditionalCustomer.getAdditionalContacts().get(i).getContactNumber());
						c.setDesignation(wsAddAdditionalCustomer.getAdditionalContacts().get(i).getDesignation());
						c.setCustomer_id(wsAddAdditionalCustomer.getCustomer_id());
						c.setStatus(1);
						session.save(c);
						if (i == wsAddAdditionalCustomer.getAdditionalContacts().size() - 1) {
							return true;
						}
					}
				}

			}
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return false;
	}

	private int checkAdditionalPresent(WsAddContacts wsAddContacts, int id) {
		// TODO Auto-generated method stub

		Session session = initiateSession();
		try {

			Query query = session.createSQLQuery("select details_id from customer_contact_details where customer_id="
					+ id + " and contact_name= '" + wsAddContacts.getContactName() + "' and contact_no='"
					+ wsAddContacts.getContactNumber() + "' and contact_email='" + wsAddContacts.getEmail()
					+ "' and designation='" + wsAddContacts.getDesignation() + "'");
			if (query.uniqueResult() != null) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return 1;
	}

	@Override
	public boolean rejectCustomer(int customerId) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("update CustomerDetails set status=:st where customer_id=:id")
					.setParameter("st", 2).setParameter("id", customerId);
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
	public List<CustomerDetails> getCustomerListDistributorwise(int c_id, String distributorId, String routeId) {
		// TODO Auto-generated method stub
		List<CustomerDetails> cDetails = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("select customer_name,code,customer_id from CustomerDetails where company_id=" + c_id
							+ " and distributor_id in ("+distributorId+") and route_id in ("+ routeId+") and status=1");

			List list = query.list();

			if (list != null && list.size() > 0) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					CustomerDetails c = new CustomerDetails();
					c.setCustomer_name((String) obj[0] + " " + (String) obj[1]);
					c.setCode((String) obj[1]);
					c.setCustomer_id((Integer) obj[2]);
					cDetails.add(c);

				}
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return cDetails;
	}

	@Override
	public List<CustomerDetails> getAllCustomerList(int c_id,String distributors_ids) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from CustomerDetails where company_id="+c_id+" and distributor_id in ("+ distributors_ids +") order by customer_name ASC");
			if(query.list()!= null && query.list().size()>0)
				return query.list();
				else
					return null;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public CustomerContactDetails getCustomerContactObject(String customer_name, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery("select customer_id from CustomerDetails where customer_name='" + customer_name +"' and company_id=" + c_id);
			int cust_id=(int) query.uniqueResult();
			Query query1 = session.createQuery("from CustomerContactDetails where customer_id='" + cust_id +"' and company_id=" + c_id);
			CustomerContactDetails customerContactDetails = (CustomerContactDetails) query1.uniqueResult();
			if (customerContactDetails != null) {
				return customerContactDetails;
			}
		} catch (Exception e) {
			
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public List<CustomerType> getCustomerTypeObject(int c_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from CustomerType where company_id="+c_id+" and status=1 order by customer_type ASC");
			if(query.list()!= null && query.list().size()>0)
			return query.list();
			else
				return null;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public WsCustomer360Data getCustomer360Data(int c_id, int customer_id,String start_month, String end_month) {
		Session session = initiateSession();
		try {
			WsCustomer360Data details =  new WsCustomer360Data();
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			String startDate = "", endDate = "", lastYearStartDate="";
			
			
			Date date1 = new SimpleDateFormat("MMMM").parse(start_month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			int month = cal.get(Calendar.MONTH);
			int year = 0;
			year= Calendar.getInstance().get(Calendar.YEAR);
			cal.set(Calendar.DATE, 1);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.YEAR, year-1);
            Date lastYearDate = cal.getTime();
            lastYearStartDate=df.format(lastYearDate);
           
			startDate = df.format(c.getTime());
			Date date = new Date();
			endDate = df.format(date);
			
			Query query = session.createSQLQuery("SELECT count(*) FROM sales_master where company_id=" + c_id
					+ " and Date(created_date) between '" + startDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			Query query1 = session.createSQLQuery("SELECT count(*) FROM visit_details where company_id=" + c_id
					+ " and Date(created_date) between '" + startDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			Query query2 = session.createSQLQuery("select sum(totalamount) FROM sales_master where company_id=" + c_id
					+ " and Date(created_date) between '" + startDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			Query query3 = session.createSQLQuery("SELECT count(*) FROM invoice_master im join invoice_details id on im.invoice_id=id.invoice_id where im.company_id=" + c_id
					+ " and Date(im.created_date) between '" + startDate + "' and '" + endDate + "' and im.customer_id="+customer_id);
			
			Query query4 = session.createSQLQuery("SELECT sum(id.amount) FROM invoice_master im join invoice_details id on im.invoice_id=id.invoice_id where im.company_id=" + c_id
					+ " and Date(im.created_date) between '" + startDate + "' and '" + endDate + "' and im.customer_id="+customer_id);

			Query query5 = session.createSQLQuery("SELECT count(*) FROM customer_complaint_ticket_details where company_id=" + c_id
					+ " and Date(created_date) between '" + startDate + "' and '" + endDate + "' and customer_id="+customer_id);

			details.setMtdTotalOrders((BigInteger) query.uniqueResult());
			details.setMtdTotalVisits((BigInteger) query1.uniqueResult());
			
			if(query2.uniqueResult()!= null)
			details.setMtdTotalOrderAmount((double) query2.uniqueResult());
			else
				details.setMtdTotalOrderAmount(0);
			
			details.setMtdTotalInvoices((BigInteger) query3.uniqueResult());
			
			if(query2.uniqueResult()!= null)
			details.setMtdTotalInvoiceAmount((double) query4.uniqueResult());
			else
				details.setMtdTotalInvoiceAmount(0);
			
			details.setYtdTotalComplaints((BigInteger) query5.uniqueResult());
			
			
			Query query6 = session.createSQLQuery("SELECT count(*) FROM sales_master where company_id=" + c_id
					+ " and Date(created_date) between '" + lastYearStartDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			Query query7 = session.createSQLQuery("SELECT count(*) FROM visit_details where company_id=" + c_id
					+ " and Date(created_date) between '" + lastYearStartDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			Query query8 = session.createSQLQuery("select sum(totalamount) FROM sales_master where company_id=" + c_id
					+ " and Date(created_date) between '" + lastYearStartDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			Query query9 = session.createSQLQuery("SELECT count(*) FROM invoice_master im join invoice_details id on im.invoice_id=id.invoice_id where im.company_id=" + c_id
					+ " and Date(im.created_date) between '" + lastYearStartDate + "' and '" + endDate + "' and im.customer_id="+customer_id);
			
			Query query10 = session.createSQLQuery("SELECT sum(id.amount) FROM invoice_master im join invoice_details id on im.invoice_id=id.invoice_id where im.company_id=" + c_id
					+ " and Date(im.created_date) between '" + lastYearStartDate + "' and '" + endDate + "' and im.customer_id="+customer_id);

			Query query11= session.createSQLQuery("SELECT count(*) FROM customer_complaint_ticket_details where company_id=" + c_id
					+ " and Date(created_date) between '" + lastYearStartDate + "' and '" + endDate + "' and customer_id="+customer_id);
			
			details.setYtdTotalOrders((BigInteger) query6.uniqueResult());
			details.setYtdTotalVisits((BigInteger) query7.uniqueResult());
			
			if(query8.uniqueResult()!= null)
			details.setYtdTotalOrderAmount((double) query8.uniqueResult());
			else
				details.setYtdTotalOrderAmount(0);
			
			details.setYtdTotalInvoices((BigInteger) query9.uniqueResult());
			
			if(query10.uniqueResult()!= null)
			details.setYtdTotalInvoiceAmount((double) query10.uniqueResult());
			else
				details.setYtdTotalInvoiceAmount(0);
			
			details.setYtdTotalComplaints((BigInteger) query11.uniqueResult());
			
			return details;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public List<WsCustomer360Data> getLastSixMonthsSalesOrderData(int c_id, int customer_id) {
		Session session = initiateSession();
		List<WsCustomer360Data> list2 = new ArrayList<WsCustomer360Data>();
		try {
			String sql = "";
				sql = " select months.year1 as year1,months.month1 as month1,concat(months.month1,' ',months.year1) AS monthq,ifnull(count(sm.customer_id),0) AS totalOrders,ifnull(SUM(sm.totalamount),0) AS totalAmount from (select MonthName( @curDate \\:= Date_SUB(@curDate, interval 1 MONTH)) as month1, Month( @curDate1 \\:= Date_SUB(@curDate1, interval 1 MONTH)) as month2, Year( @curDate2 \\:= Date_SUB(@curDate2, interval 1 MONTH)) as year1 from ( select @curDate \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate1 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate2 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month) ) sqlvars, sales_master limit 6) months LEFT JOIN sales_master sm on months.month1=MonthName(sm.created_date) AND sm.company_id="
						+ c_id + " and sm.customer_id="+customer_id+" and sm.status=1 GROUP BY months.month1 order by months.year1, months.month2";
		
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsCustomer360Data details = new WsCustomer360Data();
				details.setMonthWithYear((String) obj[2]);
				details.setTotalOrders((BigInteger) obj[3]);
				details.setTotalOrderAmount((double) obj[4]);
				
				list2.add(details);
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
			return null;
		} finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public List<WsCustomer360Data> getLastSixMonthsSalesVisitsData(int c_id, int customer_id) {
		Session session = initiateSession();
		List<WsCustomer360Data> list2 = new ArrayList<WsCustomer360Data>();
		try {
			String sql = "";
				sql = "select months.year1 as year1,months.month1 as month1,concat(months.month1,' ',months.year1) AS monthq,ifnull(count(vd.customer_id),0) AS totalVisits from (select MonthName( @curDate \\:= Date_SUB(@curDate, interval 1 MONTH)) as month1, Month( @curDate1 \\:= Date_SUB(@curDate1, interval 1 MONTH)) as month2, Year( @curDate2 \\:= Date_SUB(@curDate2, interval 1 MONTH)) as year1 from ( select @curDate \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate1 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate2 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month) ) sqlvars, visit_details limit 6) months LEFT JOIN visit_details vd on months.month1=MonthName(vd.created_date) AND vd.company_id="
						+ c_id + " and vd.customer_id="+customer_id+" and vd.status=1  GROUP BY months.month1 order by months.year1, months.month2";
			
			Query query1 = session.createSQLQuery(sql);
			List list = query1.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsCustomer360Data details = new WsCustomer360Data();
				details.setMonthWithYear((String) obj[2]);
				details.setTotalVisits((BigInteger) obj[3]);
				
				list2.add(details);
			}
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
			return null;
		} finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public boolean activateCustomer(int customerId, int c_id, int r_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("update CustomerDetails set status=:st where customer_id=:id")
					.setParameter("st", 1).setParameter("id", customerId);
			String rejected="Rejected";
			String pending="Pending";
			Query query1 = session.createQuery("update WorkFlowTasksStatus set next_approver_id=:r_id,task_status=:pending,old_task_status=:rejected where sales_master_id=:id")
					.setParameter("r_id", r_id).setParameter("id", customerId).setParameter("pending", pending).setParameter("rejected", rejected);
			int val = query.executeUpdate();
			int val1 = query1.executeUpdate();
			if (val == 0 && val1 == 0) {
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
	public CustomerDetails checkCustomerQuery(String query) {

		Session session = initiateSession();
		try {

			Query query1 = session
					.createQuery(query);

			CustomerDetails customerDetails = (CustomerDetails) query1.uniqueResult();
			if (customerDetails != null) {

				return customerDetails;
			}
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public List<CustomerImportRecords> getCustomerImportedRecords(int c_id) {

		Session session = initiateSession();
		try {
			List<CustomerImportRecords> ir=new ArrayList<CustomerImportRecords>();
			String q="from CustomerImportRecords where company_id="+c_id+" and status=0 order by records_id desc";
			Query<CustomerImportRecords> query1 = session
					.createQuery(q, CustomerImportRecords.class);

			List<CustomerImportRecords> irList = query1.list();
			for(CustomerImportRecords i:irList) {
				CustomerImportRecords i1=new CustomerImportRecords();
				i1.setCode(i.getCode());
				i1.setCustomer_name(i.getCustomer_name());
				i1.setComment(i.getComment());
				ir.add(i1);
			}
			return ir;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public List<CustomerImportRecords> importCustomers(List<WsCustomerDetails> customerListToImport) {
		
		Session session = initiateSession();
		List<CustomerImportRecords> importMessagesList = new ArrayList<CustomerImportRecords>();
		try {

			StringBuilder importMessage = new StringBuilder();			
			CommonMethods cm = new CommonMethods();
			List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();			
			cm.importCustomers(session, customerListToImport.get(0).getCompany_id(), customerListToImport.get(0).getCreated_by(), customerListToImport, customerList, importMessagesList, importMessage);
			
			return importMessagesList;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return importMessagesList;
	}

	@Override
	public List<CustomerDetails> getCustomers(int c_id) {
		Session Dbsession = initiateSession();
		List<CustomerDetails> customerList=new ArrayList<CustomerDetails>();
		
		try {
			String tQuery="select customer_id,customer_name,code from CustomerDetails where  company_id="+c_id+" and status=1";
			Query query = Dbsession.createQuery(tQuery);
			
			List list = query.list();
			
				for (Object object : list) {
					CustomerDetails r=new CustomerDetails();
					Object[] obj = (Object[]) object;
					r.setCustomer_id((int)obj[0]);
					r.setCustomer_name((String)obj[1]);
					r.setCode((String)obj[2]);
					customerList.add(r);
					
				}
				return customerList;
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(Dbsession);
		}
		
	}

	@Override
	public boolean updateMultipleCustomers(String user_ids, String distributorId, String routeId, int c_id) {
		Session session = initiateSession();
		try {

			String sqlQuery = "update customer_details set  route_id="+routeId+" , distributor_id="+distributorId+"  where customer_id in ( " + user_ids + ") and company_id="
					+ c_id;
			Query query = session.createSQLQuery(sqlQuery);
			int id = (int) query.executeUpdate();

			if (id > 0 && id != 0) {

				int id1 = (int) query.executeUpdate();
				if (id1 > 0 && id != 0) {
					return true;
				} else {
					return false;
				}

			} else {
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
	public List<CustomerTargetRecords> getCustomerTargetRecords(int c_id, int custId) {
		Session session = initiateSession();
		List<CustomerTargetRecords> customerTargetRecordsList = new ArrayList<>();
		try {
			Query query = session.createSQLQuery("select finanacialYear,month,collection,volume,amount,categoryName from customer_target_records where customer_id="+custId+" and company_id="+c_id);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				CustomerTargetRecords customerTargetRecords = new CustomerTargetRecords();
				customerTargetRecords.setFinanacialYear((int) obj[0]);
				customerTargetRecords.setMonth((String) obj[1]);
				customerTargetRecords.setCollection(CommonMethods.roundDouble((double)obj[2], 2));
				customerTargetRecords.setVolume(CommonMethods.roundDouble((double)obj[3], 2));
				customerTargetRecords.setAmount(CommonMethods.roundDouble((double)obj[4], 2));
				customerTargetRecords.setCategoryName((String) obj[5]);
				customerTargetRecordsList.add(customerTargetRecords);

			}
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return customerTargetRecordsList;
	}

	@Override
	public List<CustomerTargetRecords> checkExistTargetData(int c_id, String customerCode, String month,
			double totalTarget) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query;
			query = session
					.createQuery("from CustomerTargetRecords where customerCode=:customerCode"
							+ " and company_id=:id and totalTarget=:totalTarget and month=:month")
					.setParameter("id", c_id).setParameter("customerCode", customerCode)
					.setParameter("totalTarget", totalTarget).setParameter("month", month);

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
}
