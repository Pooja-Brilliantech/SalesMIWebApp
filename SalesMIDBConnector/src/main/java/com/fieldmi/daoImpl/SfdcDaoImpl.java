package com.fieldmi.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.SfdcDao;
import com.fieldmi.sfdc.SfdcIntegrationDao;
import com.fieldmi.stubs.WsCustomerDetails;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.CustomerImportRecords;
import com.softtantra.salesapp.pojo.CustomerTargetRecords;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.ws.WsInfluencerDetails;

public class SfdcDaoImpl implements SfdcDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	Session initiateSession() {
		return sessionFactory.openSession();
	}

	private void destroySession(Session session) {

		if (session != null && session.isOpen()) {

			FieldMILogger.debug(SfdcDaoImpl.class.getName(), "Closing session");
			if (session.isDirty())
				session.flush();

			session.close();
		}
	}

	static Properties fileProperties = new Properties();
	static {
		try {
			fileProperties.load(SfdcDaoImpl.class.getClassLoader().getResourceAsStream("/sfdcUrls.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FieldMILogger.error(SfdcDaoImpl.class.getName(), e);
		}
	}

	@Override
	public List<CustomerDetails> importCustomerFromSFDC(List<String> codes, int company_id, int u_id,
			StringBuilder importMessage) throws Exception {

		Session session = initiateSession();
		try {

			if (session != null) {

				if (importMessage == null) {
					importMessage = new StringBuilder();
				}

				if (codes != null && codes.size() > 0) {

					SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
					List<WsCustomerDetails> customerListFromSAP = sfdcIntegrationDao.importCustomerFromSFDC(codes,
							company_id);

					List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
					List<CustomerImportRecords> importMessagesList = new ArrayList<CustomerImportRecords>();

					CommonMethods cm = new CommonMethods();
					cm.importCustomers(session, company_id, u_id, customerListFromSAP, customerList, importMessagesList,
							importMessage);

					FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" + customerList.size());
					return customerList;
				}
			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public List<CustomerDetails> importCustomersFromSFDC(int company_id, int u_id) throws Exception {

		Session session = initiateSession();
		try {

			if (session != null) {

				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<WsCustomerDetails> customerListFromSAP = sfdcIntegrationDao.importCustomersFromSFDC(company_id,"");

				List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
				List<CustomerImportRecords> importMessagesList = new ArrayList<CustomerImportRecords>();

				StringBuilder importMessage = new StringBuilder();
				CommonMethods cm = new CommonMethods();
				cm.importCustomers(session, company_id, u_id, customerListFromSAP, customerList, importMessagesList,importMessage);

				FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" + customerList.size());
				return customerList;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public List<CustomerDetails> importCustomerFromSFDCLastFetchDate(int company_id, int u_id,
			StringBuilder importMessage) throws Exception {

		Session session = initiateSession();
		try {

			if (session != null) {

				if (importMessage == null) {
					importMessage = new StringBuilder();
				}

				Date lastFetchDate = Calendar.getInstance().getTime();
				Query<CustomerDetails> customerDetailsQuery = session.createQuery(
						"from CustomerDetails where company_id=" + company_id + " order by created_date desc",
						CustomerDetails.class);
				customerDetailsQuery.setMaxResults(1);
				List<CustomerDetails> lastSalesMasterList = customerDetailsQuery.list();
				if (lastSalesMasterList != null && lastSalesMasterList.size() > 0) {

					lastFetchDate = lastSalesMasterList.get(0).getCreated_date();
				}

				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<WsCustomerDetails> customerListFromSAP = sfdcIntegrationDao
						.importCustomerFromSFDCWithTimeStamp(lastFetchDate, company_id);

				List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
				List<CustomerImportRecords> importMessagesList = new ArrayList<CustomerImportRecords>();

				CommonMethods cm = new CommonMethods();
				cm.importCustomers(session, company_id, u_id, customerListFromSAP, customerList, importMessagesList,
						importMessage);

				FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" + customerList.size());
				return customerList;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<CustomerTargetRecords> importCustomerTargetFromSFDC(int c_id, int u_id) throws Exception {
		Session session = initiateSession();
		try {

			if (session != null) {

				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<CustomerTargetRecords> customerTargetListFromSAP = sfdcIntegrationDao
						.importCustomerTargetFromSFDC(c_id, u_id);

				CommonMethods cm = new CommonMethods();
				cm.importCustomersTarget(session, c_id, u_id, customerTargetListFromSAP);

				// FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" +
				// customerList.size());
				return customerTargetListFromSAP;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<SalesMaster> importSalesOrderImportFromSFDC(int c_id, int u_id) throws Exception {
		Session session = initiateSession();
		try {

			if (session != null) {
				
				Query<SalesMaster> query = session.createQuery("from SalesMaster where company_id=:companyId order by orderImportDate desc", SalesMaster.class)						
						.setParameter("companyId", c_id);
				query.setMaxResults(1);
				List<SalesMaster>  salesMasterList = query.list();
				String lastImportDate = null;				
				if(salesMasterList != null && salesMasterList.size() > 0 )
					lastImportDate = salesMasterList.get(0).getOrderImportDate();
				
				if( lastImportDate == null || lastImportDate.length() == 0) {
					
					Calendar currentImportDate = Calendar.getInstance();
					currentImportDate.add(Calendar.MONTH, -3);
					currentImportDate.set(Calendar.DAY_OF_MONTH, 1);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					lastImportDate = sdf.format(currentImportDate.getTime());
					
				}

				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<SalesMaster> customerTargetListFromSAP = sfdcIntegrationDao.importSalesMasterFromSFDC(c_id, lastImportDate);

				CommonMethods cm = new CommonMethods();
				cm.importSalesMasters(session, c_id, u_id, customerTargetListFromSAP);

				// FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" +
				// customerList.size());
				return customerTargetListFromSAP;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<CustomerTargetRecords> importCustomerTargetFromSFDCforYesterday(int c_id, int u_id) throws Exception {
		Session session = initiateSession();
		try {

			if (session != null) {

				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<CustomerTargetRecords> customerTargetListFromSAP = sfdcIntegrationDao
						.importCustomerTargetFromSFDCforYesterday(c_id, u_id);

				CommonMethods cm = new CommonMethods();
				cm.importCustomersTarget(session, c_id, u_id, customerTargetListFromSAP);

				// FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" +
				// customerList.size());
				return customerTargetListFromSAP;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<SalesMaster> importSalesDataFromSFDCforYesterday(int c_id, int u_id) throws Exception {
		Session session = initiateSession();
		try {

			if (session != null) {

				Query<SalesMaster> query = session.createQuery("from SalesMaster where company_id=:companyId order by orderImportDate desc", SalesMaster.class)						
						.setParameter("companyId", c_id);
				query.setMaxResults(1);
				List<SalesMaster>  salesMasterList = query.list();
				String lastImportDate = null;				
				if(salesMasterList != null && salesMasterList.size() > 0 )
					lastImportDate = salesMasterList.get(0).getOrderImportDate();
				
				if( lastImportDate == null || lastImportDate.length() == 0) {
					
					Calendar currentImportDate = Calendar.getInstance();
					currentImportDate.add(Calendar.MONTH, -3);
					currentImportDate.set(Calendar.DAY_OF_MONTH, 1);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					lastImportDate = sdf.format(currentImportDate.getTime());
					
				}
				
				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<SalesMaster> customerTargetListFromSAP = sfdcIntegrationDao
						.importSalesMasterFromSFDCforYesterday(c_id, lastImportDate);

				CommonMethods cm = new CommonMethods();
				cm.importSalesMasters(session, c_id, u_id, customerTargetListFromSAP);

				// FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" +
				// customerList.size());
				return customerTargetListFromSAP;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<CustomerDetails> importInfluencersFromSFDC(int c_id, int u_id) throws Exception {
		Session session = initiateSession();
		try {

			if (session != null) {

				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<WsInfluencerDetails> infListFromSFDC = sfdcIntegrationDao.importInfluencersFromSFDC(c_id, "");
				List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();

				CommonMethods cm = new CommonMethods();
				cm.importInfluencersCustomers(session, c_id, u_id, infListFromSFDC, customerList);

				// FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" +
				// customerList.size());
				return customerList;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<CustomerDetails> importCustomersFromSFDCforYesterday(int c_id, int u_id) throws Exception {

		Session session = initiateSession();
		try {

			if (session != null) {
				SfdcIntegrationDao sfdcIntegrationDao = new SfdcIntegrationDao(fileProperties);
				List<WsCustomerDetails> customerListFromSAP = sfdcIntegrationDao
						.importCustomersFromSFDCforYesterday(c_id, "");

				List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
				List<CustomerImportRecords> importMessagesList = new ArrayList<CustomerImportRecords>();
				
				StringBuilder importMessage = new StringBuilder();
				CommonMethods cm = new CommonMethods();				
				cm.importCustomers(session, c_id, u_id, customerListFromSAP, customerList, importMessagesList, importMessage);

				FieldMILogger.info(this.getClass().getName(), "Update Customer List Size:" + customerList.size());
				return customerList;

			}
			return null;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			throw ex;
		} finally {
			destroySession(session);
		}

	}
}