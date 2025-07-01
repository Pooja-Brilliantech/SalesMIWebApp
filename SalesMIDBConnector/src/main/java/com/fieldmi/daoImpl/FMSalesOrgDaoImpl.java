package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMSalesOrgDao;
import com.fieldmi.stubs.WsDistOrg;
import com.fieldmi.stubs.WsDistrictDetailsOutput;
import com.fieldmi.stubs.WsFetchDistrictTaluka;
import com.fieldmi.stubs.WsFetchSalesOrg;
import com.fieldmi.stubs.WsSalesDistrict;
import com.fieldmi.stubs.WsSalesOrgOutPut;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.SalesDistrictGroup;
import com.softtantra.salesapp.pojo.SalesDistrictOrg;

public class FMSalesOrgDaoImpl implements FMSalesOrgDao {

	@Autowired
	CommonMethods commonMethods;
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

	public void setCommonMethods(CommonMethods commonMethods) {
		this.commonMethods = commonMethods;
	}

	@Override
	public WsSalesOrgOutPut getSalesOrgList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		Session session = initiateSession();

		WsSalesOrgOutPut wsSalesOrg = new WsSalesOrgOutPut();

		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(salesOrg) FROM sales_org_data where company_id=" + wsFetchSalesOrgInput.getCompany_id()
				+ " and status=1";

		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);

			total_pages = commonMethods.getTotalPagesCountSql(session,
					"select count(DISTINCT(salesOrg)) as count FROM sales_org_data  where company_id="
							+ wsFetchSalesOrgInput.getCompany_id() + " and status=1");

			wsSalesOrg.getSalesOrgList().addAll(list);
			wsSalesOrg.setTotalRecords(total_pages);
			wsSalesOrg.setPage_No(wsFetchSalesOrgInput.getPage_no());
			wsSalesOrg.setRecordsPerPage(500);

			return wsSalesOrg;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public WsSalesOrgOutPut getDistributorChannelList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		Session session = initiateSession();
		WsSalesOrgOutPut wsSalesOrg = new WsSalesOrgOutPut();

		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(distrChannel) FROM sales_org_data where salesOrg = '"
				+ wsFetchSalesOrgInput.getSalesOrg() + "' and company_id=" + wsFetchSalesOrgInput.getCompany_id()
				+ " and status=1";
		// FieldMILogger.debug(this.getClass().getName(),query);
		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);
			String queryForRecords = "select count(DISTINCT(distrChannel)) as distrChannnel FROM sales_org_data where salesOrg = '"
					+ wsFetchSalesOrgInput.getSalesOrg() + "' and company_id=" + wsFetchSalesOrgInput.getCompany_id()
					+ " and status=1";
			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsSalesOrg.getSalesOrgList().addAll(list);
			wsSalesOrg.setTotalRecords(total_pages);
			wsSalesOrg.setPage_No(wsFetchSalesOrgInput.getPage_no());
			wsSalesOrg.setRecordsPerPage(500);

			return wsSalesOrg;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public WsSalesOrgOutPut getDivisionList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		Session session = initiateSession();
		WsSalesOrgOutPut wsSalesOrg = new WsSalesOrgOutPut();

		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(division) FROM sales_org_data where salesOrg = '" + wsFetchSalesOrgInput.getSalesOrg()
				+ "' and distrChannel='" + wsFetchSalesOrgInput.getDistrChannel() + "'and company_id="
				+ wsFetchSalesOrgInput.getCompany_id() + " and status=1";

		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);

			String queryForRecords = "select count(DISTINCT(division)) as division FROM sales_org_data where salesOrg = '"
					+ wsFetchSalesOrgInput.getSalesOrg() + "' and distrChannel='"
					+ wsFetchSalesOrgInput.getDistrChannel() + "'and company_id=" + wsFetchSalesOrgInput.getCompany_id()
					+ " and status=1";

			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsSalesOrg.getSalesOrgList().addAll(list);
			wsSalesOrg.setTotalRecords(total_pages);
			wsSalesOrg.setPage_No(wsFetchSalesOrgInput.getPage_no());
			wsSalesOrg.setRecordsPerPage(500);

			return wsSalesOrg;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public WsDistrictDetailsOutput getDistrictList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput) {

		Session session = initiateSession();
		WsDistrictDetailsOutput wsDistrictDetails = new WsDistrictDetailsOutput();
		WsFetchSalesOrg wsFetchSalesOrgInput = new WsFetchSalesOrg();
		wsFetchSalesOrgInput.setPage_no(wsFetchDistrictTalukaInput.getPage_no());
		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(districtCode),distDescription FROM district where state_id = "
				+ wsFetchDistrictTalukaInput.getState_id() + " and status=1";
		// FieldMILogger.debug(this.getClass().getName(),query);
		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);
			List<District> districtList = new ArrayList<>();
			for (Object object : list) {

				District district = new District();
				Object[] salesDisObj = (Object[]) object;
				district.setDistrictCode((String) salesDisObj[0]);
				district.setDistDescription((String) salesDisObj[1]+" "+(String) salesDisObj[0]);
				districtList.add(district);
			}

			String queryForRecords = "select count(DISTINCT(districtCode)) as count FROM district where state_id = "
					+ wsFetchDistrictTalukaInput.getState_id() + " and status=1";

			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsDistrictDetails.getDistList().addAll(districtList);
			wsDistrictDetails.setTotalRecords(total_pages);
			wsDistrictDetails.setPage_No(wsFetchDistrictTalukaInput.getPage_no());
			wsDistrictDetails.setRecordsPerPage(500);

			return wsDistrictDetails;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {

			destroySession(session);
		}

	}

	@Override
	public WsSalesDistrict getSalesDistrictList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput) {

		Session session = initiateSession();
		WsSalesDistrict wsDistrictDetails = new WsSalesDistrict();
		WsFetchSalesOrg wsFetchSalesOrgInput = new WsFetchSalesOrg();
		wsFetchSalesOrgInput.setPage_no(wsFetchDistrictTalukaInput.getPage_no());

		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(salesDistrict),description FROM sales_district where district_code = '"
				+ wsFetchDistrictTalukaInput.getDistrict_code() + "' and company_id="
				+ wsFetchDistrictTalukaInput.getCompany_id() + " and status=1";
		// FieldMILogger.debug(this.getClass().getName(),query);
		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);
			List<SalesDistrictGroup> salesDistrictList = new ArrayList<>();
			for (Object object : list) {

				SalesDistrictGroup salesDistrict = new SalesDistrictGroup();
				Object[] salesDisObj = (Object[]) object;
				salesDistrict.setSalesDistrict((String) salesDisObj[0]);
				salesDistrict.setDescription((String) salesDisObj[1]);
				salesDistrictList.add(salesDistrict);
			}

			String queryForRecords = "select count(DISTINCT(salesDistrict)) as count FROM sales_district where district_code = '"
					+ wsFetchDistrictTalukaInput.getDistrict_code() + "' and company_id="
					+ wsFetchDistrictTalukaInput.getCompany_id() + " and status=1";

			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsDistrictDetails.getSalesDistList().addAll(salesDistrictList);
			wsDistrictDetails.setTotalRecords(total_pages);
			wsDistrictDetails.setPage_No(wsFetchDistrictTalukaInput.getPage_no());
			wsDistrictDetails.setRecordsPerPage(500);

			return wsDistrictDetails;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {

			destroySession(session);
		}

	}

	@Override
	public WsDistOrg getSalesDistWiseSalesGroupList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput) {
		Session session = initiateSession();

		WsDistOrg wsDistOrgDetails = new WsDistOrg();

		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(sales_group_code) FROM sales_district_org where district_code = '"
				+ wsFetchDistrictTalukaInput.getDistrict_code() + "' and sales_district_code='"
				+ wsFetchDistrictTalukaInput.getSales_district_code() + "' and company_id="
				+ wsFetchDistrictTalukaInput.getCompany_id() + " and status=1";
		// FieldMILogger.debug(this.getClass().getName(),query);
		try {

			// list = dataAccess.quaryListPaginationForDistrict(query,
			// wsFetchDistrictTalukaInput);
			Query q = session.createSQLQuery(query);
			list = q.list();

			int counter = 0;
			StringBuilder ids = new StringBuilder();
			for (Object object : list) {
				if (counter > 0) {
					ids.append(",");

				}
				ids.append("'").append(object).append("'");
				counter++;
			}

			String salesGrpDescQuery = "select concat(salesGrp,'^',salesGrpText) as salesGroup FROM sales_org_data where salesGrp IN ("
					+ ids.toString() + ") and company_id=" + wsFetchDistrictTalukaInput.getCompany_id()
					+ " group by salesGrp ";

			Query q1 = session.createSQLQuery(salesGrpDescQuery);
			List listNew = q1.list();

			List<SalesDistrictOrg> salesDistrictOrgList = new ArrayList<>();
			for (Object object : listNew) {

				SalesDistrictOrg salesDistrictOrg = new SalesDistrictOrg();
				salesDistrictOrg.setSales_group_code((String) object);
				salesDistrictOrgList.add(salesDistrictOrg);
			}

			String queryForRecords = "select count(DISTINCT(sales_group_code)) as count FROM sales_district_org where district_code = '"
					+ wsFetchDistrictTalukaInput.getDistrict_code() + "' and sales_district_code='"
					+ wsFetchDistrictTalukaInput.getSales_district_code() + "' and company_id="
					+ wsFetchDistrictTalukaInput.getCompany_id() + " and status=1";

			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsDistOrgDetails.getSalesDistOrgList().addAll(salesDistrictOrgList);
			wsDistOrgDetails.setTotalRecords(total_pages);
			wsDistOrgDetails.setPage_No(wsFetchDistrictTalukaInput.getPage_no());
			wsDistOrgDetails.setRecordsPerPage(500);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return wsDistOrgDetails;
	}

	@Override
	public WsSalesOrgOutPut getSalesGroupWiseSalesOfficeList(WsFetchSalesOrg wsFetchSalesOrgInput) {

		Session session = initiateSession();
		WsSalesOrgOutPut wsSalesOrg = new WsSalesOrgOutPut();

		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select DISTINCT(salesOffice) FROM sales_org_data where salesGrp = '"
				+ wsFetchSalesOrgInput.getSalesGroup() + "' and status=1";

		// FieldMILogger.debug(this.getClass().getName(),query);
		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);

			String queryForRecords = "select count(DISTINCT(salesOffice)) FROM sales_org_data where salesGrp = '"
					+ wsFetchSalesOrgInput.getSalesGroup() + "' and status=1";

			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsSalesOrg.getSalesOrgList().addAll(list);
			wsSalesOrg.setTotalRecords(total_pages);
			wsSalesOrg.setPage_No(wsFetchSalesOrgInput.getPage_no());
			wsSalesOrg.setRecordsPerPage(500);

			return wsSalesOrg;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {

			destroySession(session);
		}
	}
}
