package com.fieldmi.daoImpl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMLeadDao;
import com.fieldmi.service.FMCustomerService;
import com.fieldmi.service.FMLeadService;
import com.fieldmi.stubs.ReturnDate;
import com.fieldmi.stubs.WSLeadDetails;
import com.fieldmi.stubs.WsLeadIndustry;
import com.fieldmi.stubs.WsLeadList;
import com.fieldmi.stubs.WsLeadProducts;
import com.fieldmi.stubs.WsLeadSource;
import com.fieldmi.stubs.WsLeadTypeList;
import com.fieldmi.stubs.WsOpportunityList;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.S3Operations;
import com.softtantra.salesapp.pojo.FieldMIConfiguration;
import com.softtantra.salesapp.pojo.LeadHasProducts;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.servicemi.pojo.ActivityDetails;
import com.softtantra.servicemi.pojo.LeadProduct;
import com.softtantra.servicemi.pojo.OpportunityDetails;
import com.softtantra.servicemi.pojo.OpportunityStages;
import com.softtantra.servicemi.pojo.QuotationDetails;
import com.softtantra.servicemi.pojo.SM_LeadDetails;
import com.softtantra.servicemi.pojo.SM_UserWeekOfDetails;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.NewOpportunityList;
import com.softtantra.ws.WSActivityList;
import com.softtantra.ws.WSOpportunityList;

public class FMLeadDaoImpl implements FMLeadDao {

	private static final String QUERY_COLUMNS = "select c.lead_id,c.first_name,c.last_name,c.company_address ,c.company_name,c.close_comment,c.close_leadstatus,c.contact_no,c.created_date,c.created_location,c.email,c.lead_details,c.lead_type,c.lead_source,c.lead_industry,c.lat,c.longi,c.designation,c.website,c.salvation,c.address,s.state_name,ct.city_name,con.country_name,c.close_status,c.close_date,c.assign_user_id,u.first_name,u.last_name,c.company_id,c.pincode,c.image_url,c.customer_id,c.opportunity_id,c.opportunity_IdRange,c.id_range";
	private static final int no_of_records_per_page = 200;
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	FMLeadService fmLeadService;
	
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
	public boolean saveLead(SM_LeadDetails leadDetails, int c_id, int u_id, String data) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = initiateSession();
		try {
			leadDetails.setCompany_id(c_id);
			leadDetails.setCreated_by(u_id);
			if (leadDetails.getCreated_date_time() != "" && leadDetails.getCreated_date_time() != null)
				leadDetails.setCreated_date(sdf.parse(leadDetails.getCreated_date_time()));
			else
				leadDetails.setCreated_date(new Date());
			leadDetails.setUpdated_by(u_id);
			leadDetails.setUpdated_date(new Date());
			leadDetails.setStatus(1);
			leadDetails.setClose_leadstatus("1");
			leadDetails.setClose_status(1);
			leadDetails.setAssign_user_id(leadDetails.getUser());
			leadDetails.setCustomer_id(leadDetails.getCustomer_id());

			if (leadDetails.getId_range() == null || leadDetails.getId_range().trim().length() == 0) {
				String parent_name = "Lead";
				String newIdRange = getNewIdRange(session, c_id, parent_name);
				leadDetails.setId_range(newIdRange);
			}
			session.save(leadDetails);

			if (data != "" && data.length() > 0) {
				String[] RowSplit = data.split("<###>");
				for (int i = 0; i < RowSplit.length; i++) {
					String str = RowSplit[i];
					String[] columnSaparator = str.split("<<<<>>>>");
					if (columnSaparator.length > 1) {
						LeadHasProducts details = new LeadHasProducts();
						details.setUser_id(u_id);
						details.setLead_id(leadDetails.getLead_id());
						details.setProduct_id(Integer.parseInt(columnSaparator[0]));
						details.setQuantity(Integer.parseInt(columnSaparator[1]));
						details.setStatus(1);
						details.setCreated_by(u_id);
						details.setCreated_date(new Date());
						details.setUpdated_by(u_id);
						details.setUpdated_date(new Date());
						details.setCompany_id(leadDetails.getCompany_id());
						details.setUom(columnSaparator[2]);
						session.save(details);
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;

	}

	@Override
	public String getNewIdRange(int c_id, String parent_name) {

		Session session = initiateSession();
		try {

			return getNewIdRange(session, c_id, parent_name);
		} catch (Exception e) {

			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {

			destroySession(session);
		}
	}

	private String getNewIdRange(Session session, int c_id, String parent_name) {

		String newIdRange = "";
		Query query = session
				.createQuery("from FieldMIConfiguration where company_id=:c_id and parent_name=:parent_name")
				.setParameter("c_id", c_id).setParameter("parent_name", parent_name);
		int counter;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(date);
		List list = query.list();
		synchronized (list) {

			if (list != null && list.size() > 0) {
				Iterator itr = list.iterator();
				while (itr.hasNext()) {
					FieldMIConfiguration fmi = (FieldMIConfiguration) itr.next();

					counter = (int) fmi.getCounter();
					newIdRange = fmi.getPrefix() + "/" + today + "/" + counter + "/" + fmi.getPostfix();

					boolean samedate = DateUtils.isSameDay(date, fmi.getUpdated_date());
					if (samedate) {
						counter++;
					} else {
						counter = Integer.parseInt(fmi.getStart_range());
						newIdRange = fmi.getPrefix() + "/" + today + "/" + counter + "/" + fmi.getPostfix();
					}
					Query query1 = session.createQuery(
							"update FieldMIConfiguration set counter=:counter, updated_date=:updated_date where company_id=:c_id and status=:status and parent_name=:parent_name ");
					query1.setParameter("c_id", c_id);
					query1.setParameter("status", 1);
					query1.setParameter("counter", counter);
					query1.setParameter("updated_date", date);
					query1.setParameter("parent_name", parent_name);
					query1.executeUpdate();

				}
			}

		}

		return newIdRange;
	}

	@Override
	public SM_LeadDetails editLeadDetails(int lead_id, int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			SM_LeadDetails sm_LeadDetails = session.get(SM_LeadDetails.class, lead_id);
			if (sm_LeadDetails != null) {
				return sm_LeadDetails;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			
			destroySession(session);
		}
		return null;
	}

	@Override
	public boolean updateLead(SM_LeadDetails leadDetails, String data) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			if (leadDetails != null) {

				org.hibernate.Query query = session.createQuery("update SM_LeadDetails"
						+ " set salvation=:salvation,first_name=:first_name,last_name=:last_name,assign_user_id=:assign_user_id,city=:city,"
						+ "company_address=:company_address,company_name=:company_name,status=:status,state=:state,"
						+ "country_id=:country,lead_details=:lead_details,pincode=:pincode,lead_type=:lead_type,email=:email,contact_no=:contact_no,designation=:designation,website=:website,lead_source=:lead_source,lead_industry=:lead_industry,address=:address,lat=:lat,longi=:longi,image_url=:image_url,customer_id=:customer_id"
						+ "  where lead_id=:id and company_id=:company_id")
						.setParameter("salvation", leadDetails.getSalvation())
						.setParameter("first_name", leadDetails.getFirst_name())
						.setParameter("last_name", leadDetails.getLast_name())
						.setParameter("assign_user_id", leadDetails.getUser())
						.setParameter("city", leadDetails.getCity())
						.setParameter("company_address", leadDetails.getCompany_address())
						.setParameter("company_name", leadDetails.getCompany_name()).setParameter("status", 1)
						.setParameter("state", leadDetails.getState())
						.setParameter("country", leadDetails.getCountry_id())
						.setParameter("lead_details", leadDetails.getLead_details())
						.setParameter("lead_type", leadDetails.getLead_type())
						.setParameter("email", leadDetails.getEmail())
						.setParameter("contact_no", leadDetails.getContact_no())
						.setParameter("pincode", leadDetails.getPincode())
						.setParameter("designation", leadDetails.getDesignation())
						.setParameter("website", leadDetails.getWebsite())
						.setParameter("lead_source", leadDetails.getLead_source())
						.setParameter("lead_industry", leadDetails.getLead_industry())
						.setParameter("address", leadDetails.getAddress()).setParameter("lat", leadDetails.getLat())
						.setParameter("longi", leadDetails.getLongi())
						.setParameter("image_url", leadDetails.getImage_url())
						.setParameter("customer_id", leadDetails.getCustomer_id())
						.setParameter("id", leadDetails.getLead_id())
						.setParameter("company_id", leadDetails.getCompany_id());

				FieldMILogger.debug(this.getClass().getName(), "leadDetails" + leadDetails.getLead_id());
				int val = query.executeUpdate();
				if (val == 1) {
					Query query3 = session.createSQLQuery("select * FROM lead_has_products where lead_id="
							+ leadDetails.getLead_id() + " and company_id=" + leadDetails.getCompany_id());
					List list = query3.list();
					if (list != null && list.size() > 0) {
						Query query2 = session.createSQLQuery("delete FROM lead_has_products where lead_id="
								+ leadDetails.getLead_id() + " and company_id=" + leadDetails.getCompany_id());
						query2.executeUpdate();
					}
					if (data != "" && data.length() > 0) {
						String[] RowSplit = data.split("<###>");
						for (int i = 0; i < RowSplit.length; i++) {
							String str = RowSplit[i];
							String[] columnSaparator = str.split("<<<<>>>>");
							if (columnSaparator.length > 1) {
								LeadHasProducts details = new LeadHasProducts();
								details.setUser_id(leadDetails.getUser());
								details.setLead_id(leadDetails.getLead_id());
								details.setProduct_id(Integer.parseInt(columnSaparator[0]));
								details.setQuantity(Integer.parseInt(columnSaparator[1]));
								details.setStatus(1);
								details.setCreated_by(leadDetails.getUser());
								details.setCreated_date(new Date());
								details.setUpdated_by(leadDetails.getUser());
								details.setUpdated_date(new Date());
								details.setCompany_id(leadDetails.getCompany_id());
								session.save(details);
							}
						}
					}
				} else {
					return false;
				}

			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return true;
	}

	@Override
	public boolean closeLead(int lead_id, String close_leadstatus, String close_comment, int u_id, int c_id,
			String close_date) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			int newStatus = 0;
			if (close_leadstatus != null && close_leadstatus != "")
				newStatus = Integer.parseInt(close_leadstatus);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			if (close_date != "")
				date = sdf.parse(close_date);
			else
				date = new Date();

			org.hibernate.Query query = session.createQuery("update SM_LeadDetails"
					+ " set close_comment=:close_comment,close_leadstatus=:close_leadstatus,close_status=:close_status,close_date=:close_date,"
					+ "updated_by=:updated_by,updated_date=:updated_date,status=:status"
					+ "  where lead_id=:id and company_id=:company_id").setParameter("close_comment", close_comment)
					.setParameter("close_leadstatus", close_leadstatus).setParameter("close_status", newStatus)
					.setParameter("close_date", date).setParameter("updated_by", u_id)
					.setParameter("updated_date", date).setParameter("id", lead_id).setParameter("company_id", c_id)
					.setParameter("status", newStatus);

			// FieldMILogger.debug(this.getClass().getName(),"leadDetails"+leadDetails.getLead_id());
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
	public WsLeadList getLeadList(Credentials creadentials) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		WSLeadDetails wsLeadDetails = null;
		WsLeadList wsLeadList = new WsLeadList();

		List list = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String query = "";
		int total_pages = 0;

		try {

			CommonMethods commonMethods = new CommonMethods();
			if (creadentials.getCompany_id() != null && creadentials.getUser_id() != null) {
				List<Integer> users = commonMethods.getUserHirachyWise(creadentials.getCompany_id().intValue(),
						creadentials.getUser_id().intValue(), session,"","","","");

				String userid = "";
				for (int i = 0; i < users.size(); i++) {
					userid = userid + users.get(i).intValue() + ",";

				}
				if (userid != null && userid.length() > 0 && userid.charAt(userid.length() - 1) == ',') {
					userid = userid.substring(0, userid.length() - 1);
				}
				String lastDate = "";
				String outputDate1 = "";
				String outputDate = ReturnDate.returnLastUpdateddate();

				if (creadentials.getLast_date() != null)
				if (!creadentials.getLast_date().contains("null") && creadentials.getLast_date() != "") {
					outputDate1 = ReturnDate.UTCDateTime(creadentials.getLast_date());
					lastDate = " and c.updated_date > '" + outputDate1 + "'  ";
				}

				if (userid != null && userid.length() > 0) {

					query = QUERY_COLUMNS
							+ " from SM_LeadDetails c left outer join User u on u.user_id=c.assign_user_id left outer join States s on c.state=s.state_id left outer join Citys ct on c.city=ct.city_id left outer join Country con on con.country_id=c.country_id where c.close_status!=0 and c.company_id="
							+ creadentials.getCompany_id() + " and (c.created_by IN (" + userid
							+ ") or c.assign_user_id IN (" + userid + ") )" + lastDate + "";
					list = commonMethods.quaryListPagination(session, query, creadentials.getPage_no(),
							no_of_records_per_page);
					if (list != null && list.size() > 0) {
						for (Object object : list) {
							List<WsLeadProducts> details = new ArrayList<WsLeadProducts>();
							Object[] obj = (Object[]) object;
							wsLeadDetails = new WSLeadDetails();
							wsLeadDetails.setLead_id(obj[0] == null ? "" : obj[0] + "");
							wsLeadDetails.setFirst_name(obj[1] == null ? "" : obj[1] + "");
							wsLeadDetails.setLast_name(obj[2] == null ? "" : obj[2] + "");
							wsLeadDetails.setCompany_address(obj[3] == null ? "" : obj[3] + "");
							wsLeadDetails.setCompany_name(obj[4] == null ? "" : obj[4] + "");
							wsLeadDetails.setClose_comment(obj[5] == null ? "" : obj[5] + "");
							wsLeadDetails.setClose_leadstatus(obj[6] == null ? "" : obj[6] + "");
							wsLeadDetails.setContact_no(obj[7] == null ? "" : obj[7] + "");

							if (obj[8] != null)
								wsLeadDetails.setCreated_date_time(sdf.format((Date) obj[8]));
							else
								wsLeadDetails.setCreated_date_time("");

							wsLeadDetails.setCreated_location(obj[9] == null ? "" : obj[9] + "");

							wsLeadDetails.setEmail(obj[10] == null ? "" : obj[10] + "");
							wsLeadDetails.setLead_details(obj[11] == null ? "" : obj[11] + "");
							wsLeadDetails.setLead_type(obj[12] == null ? "" : obj[12] + "");

							wsLeadDetails.setLead_source(obj[13] == null ? "" : obj[13] + "");
							wsLeadDetails.setLead_industry(obj[14] == null ? "" : obj[14] + "");
							wsLeadDetails.setLat(obj[15] == null ? "" : obj[15] + "");
							wsLeadDetails.setLongi(obj[16] == null ? "" : obj[16] + "");
							wsLeadDetails.setDesignation(obj[17] == null ? "" : obj[17] + "");
							wsLeadDetails.setWebsite(obj[18] == null ? "" : obj[18] + "");
							wsLeadDetails.setSalvation(obj[19] == null ? "" : obj[19] + "");
							wsLeadDetails.setAddress(obj[20] == null ? "" : obj[20] + "");

							wsLeadDetails.setState(obj[21] == null ? "" : obj[21] + "");
							wsLeadDetails.setCity(obj[22] == null ? "" : obj[22] + "");
							wsLeadDetails.setCountry(obj[23] == null ? "" : obj[23] + "");
							wsLeadDetails.setClose_status(obj[24] == null ? "" : obj[24] + "");
							if (obj[25] != null)
								wsLeadDetails.setClose_date(sdf1.format((Date) obj[25]));
							else
								wsLeadDetails.setClose_date("");

							wsLeadDetails.setAssign_user_id(obj[26] == null ? 0 : Integer.parseInt(obj[26] + ""));
							if (obj[27] != null && obj[28] != null)
								wsLeadDetails.setAssign_user_name(obj[27] + " " + obj[28] + "");
							else
								wsLeadDetails.setAssign_user_name("");
							if (obj[29] != null)
								wsLeadDetails.setCompany_id((int) obj[29]);
							else
								wsLeadDetails.setCompany_id(0);
							if (obj[30] != null)
								wsLeadDetails.setPincode((String) obj[30]);
							else
								wsLeadDetails.setPincode((String) obj[30]);
							if (obj[31] != null)
								wsLeadDetails.setImage_url((String) obj[31]);
							else
								wsLeadDetails.setImage_url("");
							if (obj[32] != null)
								wsLeadDetails.setCustomer_id((int) obj[32]);
							else
								wsLeadDetails.setCustomer_id(0);

							if (obj[33] != null)
								wsLeadDetails.setOpportunity_id((int) obj[33]);
							else
								wsLeadDetails.setOpportunity_id(0);

							if (obj[34] != null)
								wsLeadDetails.setOpportunity_IdRange((String) obj[34]);
							else
								wsLeadDetails.setOpportunity_IdRange("");

							if (obj[35] != null)
								wsLeadDetails.setId_range((String) obj[35]);
							else
								wsLeadDetails.setId_range("");

							Query query2 = session.createSQLQuery(
									"SELECT lhp.product_id,lp.product_name,lhp.quantity FROM lead_has_products as lhp "

											+ " INNER JOIN lead_product as lp on lhp.product_id=lp.product_id "

											+ " where lhp.status=1 and lhp.lead_id=" + obj[0] + " and lhp.company_id="
											+ creadentials.getCompany_id());
							List list2 = query2.list();

							for (Object object2 : list2) {

								Object[] obj2 = (Object[]) object2;
								WsLeadProducts leadProduct = new WsLeadProducts();
								leadProduct.setProduct_id((int) obj2[0]);
								leadProduct.setProduct_name((String) obj2[1]);
								leadProduct.setQuantity((int) obj2[2]);
								details.add(leadProduct);
							}
							if (details != null && details.size() > 0)
								wsLeadDetails.setWsLeadProducts(details);
							wsLeadList.getLeadList().add(wsLeadDetails);
						}
						String sql = "select count(c.lead_id) from SM_LeadDetails c left outer join SM_User u on u.user_id=c.assign_user_id left outer join States s on c.state=s.state_id left outer join Citys ct on c.city=ct.city_id left outer join Country con on con.country_id=c.country_id where c.close_status!=0 and c.company_id="
								+ creadentials.getCompany_id() + lastDate + " and (c.created_by IN (" + userid
								+ ") or c.assign_user_id IN (" + userid + "))";
						total_pages = commonMethods.getTotalPagesCountHql(session, sql);
					}
					wsLeadList.setTotal_records(total_pages);
					wsLeadList.setRecordsPerPagesNew(no_of_records_per_page);
					wsLeadList.setPage_no(creadentials.getPage_no());
					wsLeadList.setLastUpdatedDate(outputDate);
					return wsLeadList;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
		return wsLeadList;

	}

	@Override
	public WsLeadTypeList getLeadSourceList(int c_id, int page_no) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		WsLeadTypeList wsLeadTypeList = new WsLeadTypeList();

		WsLeadSource wsLeadSource = null;
		List list = null;
		String query = "";
		int total_pages = 0;

		String outputDate = ReturnDate.returnLastUpdateddate();

		try {

			CommonMethods commonMethods = new CommonMethods();
			query = "select lead_source_id,lead_source_name" + " from LeadSource " + " where status=1 and company_id="
					+ c_id;
			total_pages = commonMethods.getTotalPagesCountSql(session,
					"select count(lead_source_id) " + " from lead_source " + "  where status=1 and company_id=" + c_id);
			if (page_no <= 0) {
				page_no = 1;
			}
			list = commonMethods.quaryListPagination(session, query, page_no, no_of_records_per_page);
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				wsLeadSource = new WsLeadSource();
				wsLeadSource.setLead_source_id((int) obj[0]);
				wsLeadSource.setLead_source_name(obj[1] == null ? "" : obj[1] + "");

				wsLeadTypeList.getLeadsourceList().add(wsLeadSource);
			}

			wsLeadTypeList.setTotal_records(total_pages);
			wsLeadTypeList.setRecordsPerPagesNew(no_of_records_per_page);
			wsLeadTypeList.setLastUpdatedDate(outputDate);

			return wsLeadTypeList;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return wsLeadTypeList;
	}

	@Override
	public WsLeadTypeList getLeadIndustryList(int c_id, int page_no) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		WsLeadTypeList wsLeadTypeList = new WsLeadTypeList();

		WsLeadIndustry wsLeadIndustry = null;
		List list = null;
		String query = "";
		int total_pages = 0;

		String outputDate = ReturnDate.returnLastUpdateddate();

		try {

			CommonMethods commonMethods = new CommonMethods();
			query = "select lead_industry_id,lead_industry_name" + " from LeadIndustry "
					+ " where status=1 and company_id=" + c_id;
			total_pages = commonMethods.getTotalPagesCountSql(session, "select count(lead_industry_id) "
					+ " from lead_industry " + "  where status=1 and company_id=" + c_id);
			if (page_no <= 0) {
				page_no = 1;
			}
			list = commonMethods.quaryListPagination(session, query, page_no, no_of_records_per_page);
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				wsLeadIndustry = new WsLeadIndustry();
				wsLeadIndustry.setLead_industry_id((int) obj[0]);
				wsLeadIndustry.setLead_industry_name(obj[1] == null ? "" : obj[1] + "");

				wsLeadTypeList.getLeadindustryList().add(wsLeadIndustry);
			}

			wsLeadTypeList.setTotal_records(total_pages);
			wsLeadTypeList.setRecordsPerPagesNew(no_of_records_per_page);
			wsLeadTypeList.setLastUpdatedDate(outputDate);

			return wsLeadTypeList;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return wsLeadTypeList;
	}

	@Override
	public boolean saveLeadConfigurationData(String email, String prefix, String postfix, String start_range,
			String end_range, int c_id, int u_id, String parent_name) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			Query query = session.createQuery("select id from FieldMIConfiguration where company_id=" + c_id
					+ " and parent_name='" + parent_name + "'");
			if (query.uniqueResult() != null) {
				int id = (int) query.uniqueResult();
				if (id != 0 && id > 0) {

					Query query1 = session.createQuery(
							"update FieldMIConfiguration set email=:email,prefix=:prefix,postfix=:postfix,start_range=:start_range,end_range=:end_range where company_id=:c_id and id=:id and parent_name=:parent_name");
					query1.setParameter("c_id", c_id);
					query1.setParameter("email", email);
					query1.setParameter("prefix", prefix);
					query1.setParameter("postfix", postfix);
					query1.setParameter("start_range", start_range);
					query1.setParameter("end_range", end_range);
					query1.setParameter("id", id);
					query1.setParameter("parent_name", parent_name);
					query1.executeUpdate();
					return true;

				}
			} else {
				FieldMIConfiguration fieldMIConfiguration = new FieldMIConfiguration();
				fieldMIConfiguration.setEmail(email);
				fieldMIConfiguration.setPrefix(prefix);
				fieldMIConfiguration.setPostfix(postfix);
				fieldMIConfiguration.setStart_range(start_range);
				fieldMIConfiguration.setEnd_range(end_range);
				fieldMIConfiguration.setCompany_id(c_id);
				fieldMIConfiguration.setCreated_by(u_id);
				fieldMIConfiguration.setCreated_date(new Date());
				fieldMIConfiguration.setUpdated_date(new Date());
				fieldMIConfiguration.setStatus(1);
				fieldMIConfiguration.setCounter(Integer.parseInt(start_range));
				fieldMIConfiguration.setParent_name(parent_name);
				session.save(fieldMIConfiguration);
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
	public List<User> getLeadAllUsers(int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<User> userList = new ArrayList<>();
		try {
			Query query = session.createQuery(
					"select user_id, first_name,last_name from User where status=1 and company_id=" + c_id);
			List list = query.list();

			if (list != null && list.size() > 0) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					User user = new User();
					user.setUser_id(((int) obj[0]));
					user.setFirst_name((String) obj[1] + " " + obj[2]);
					userList.add(user);
				}

			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return userList;
	}

	@Override
	public Map<String, Long> getDataForLeadPieChart(int assigned_to, int created_by, String value, String lead_type,
			String lead_source, String lead_industry, String type, String industry, String source, int c_id,
			String sqlAssigned_to) {

		Session session = initiateSession();
		try {
			Map<String, Long> Mmap = new HashMap();
			Query query = null;
			if (sqlAssigned_to == null) {
				sqlAssigned_to = "";
			}
			if (lead_industry != "" || lead_source != "" || lead_type != "") {
				if (source.equalsIgnoreCase("lead_source")) {
					if (source != "" && lead_type != "" && lead_industry != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_type='" + lead_type + "' and lead_industry='" + lead_industry
								+ "' GROUP BY " + source);
					else if (source != "" && lead_type != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + " and lead_type='"
								+ lead_type + "' GROUP BY " + source);
					else if (source != "" && lead_industry != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_industry='" + lead_industry + "' GROUP BY " + source);
				}
				if (source.equalsIgnoreCase("lead_type")) {
					if (source != "" && lead_industry != "" && lead_source != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_source='" + lead_source + "' and lead_industry='" + lead_industry
								+ "' GROUP BY " + source);
					else if (source != "" && lead_industry != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_type='" + lead_type + "' GROUP BY " + source);
					else if (source != "" && lead_source != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_source='" + lead_source + "' GROUP BY " + source);
				}
				if (source.equalsIgnoreCase("lead_industry")) {
					if (source != "" && lead_source != "" && lead_type != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_source='" + lead_source + "' and lead_type='" + lead_type + "' GROUP BY "
								+ source);
					else if (source != "" && lead_source != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_source='" + lead_source + "' GROUP BY " + source);
					else if (source != "" && lead_type != "")
						query = session.createQuery("SELECT COUNT(" + source + ")," + source
								+ " FROM SM_LeadDetails WHERE status=1 and company_id=" + c_id + sqlAssigned_to
								+ " and lead_type='" + lead_type + "' GROUP BY " + source);
				}
			} else {
				query = session.createQuery("SELECT COUNT(" + source + ")," + source+ " FROM com.softtantra.servicemi.pojo.SM_LeadDetails " +

						  " WHERE status = 1 AND company_id = " + c_id +sqlAssigned_to+
			             " GROUP BY " + source);
			}
			if (query != null) {

				List list = query.list();
				if (list != null && list.size() > 0) {

					for (Object object : list) {
						Object[] obj = (Object[]) object;
						Map<String, Long> map = new HashMap();
						map.put((String) obj[1], (long) obj[0]);
						Mmap.putAll(map);
					}

					return Mmap;
				}
			}

			return null;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return null;

	}

	@Override
	public boolean saveOpportunity(com.softtantra.servicemi.pojo.OpportunityDetails opportunityDetails, int c_id,
			int u_id) {
		Session session = initiateSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			opportunityDetails.setCompany_id(c_id);
			opportunityDetails.setCreated_by(u_id);

			if (opportunityDetails.getCreated_at() != "" && opportunityDetails.getCreated_at() != null)
				opportunityDetails.setCreated_date(sdf.parse(opportunityDetails.getCreated_at()));
			else
				opportunityDetails.setCreated_date(new Date());

			opportunityDetails.setUpdated_by(u_id);
			opportunityDetails.setUpdated_date(new Date());
			opportunityDetails.setStatus(1);
			opportunityDetails.setClose_status(1);
			if (opportunityDetails.getId_range() == null || opportunityDetails.getId_range().trim().length() == 0) {
				String parent_name = "Opportunity";
				String newIdRange = getNewIdRange(session, c_id, parent_name);
				opportunityDetails.setId_range(newIdRange);
			}
			int id = (int) session.save(opportunityDetails);
			if (id > 0 && opportunityDetails.getLead_id() > 0) {
				Query query1 = session.createQuery(
						"update SM_LeadDetails set status=:status,opportunity_id=:opportunity_id,opportunity_IdRange=:opportunity_IdRange where company_id=:c_id and lead_id=:lead_id");
				query1.setParameter("c_id", c_id);
				query1.setParameter("status", 2);
				query1.setParameter("lead_id", opportunityDetails.getLead_id());
				query1.setParameter("opportunity_id", id);
				query1.setParameter("opportunity_IdRange", opportunityDetails.getId_range());
				query1.executeUpdate();
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return true;

	}

	@Override
	public boolean deleteOpportunity(int c_id, int u_id, OpportunityDetails opportunityDetails) {
		Session session = initiateSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Query query1 = session.createQuery(
					"update OpportunityDetails set status=:status,close_comment=:close_comment,close_status=:close_status,updated_by=:updated_by,updated_date=:updated_date where company_id=:c_id and opportunity_id=:opp_id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("status", opportunityDetails.getClose_status());
			query1.setParameter("opp_id", opportunityDetails.getOpportunity_id());
			query1.setParameter("updated_by", u_id);
			query1.setParameter("updated_date", sdf.parse(opportunityDetails.getClose_date()));
			query1.setParameter("close_comment", opportunityDetails.getClose_comment());
			query1.setParameter("close_status", opportunityDetails.getClose_status());
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public OpportunityDetails editOpportunity(int c_id, int u_id, int opportunity_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			OpportunityDetails opportunityDetails = session.get(OpportunityDetails.class, opportunity_id);
			if (opportunityDetails != null) {
				return opportunityDetails;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public boolean updateOpportunity(OpportunityDetails opportunityDetails, int c_id, int u_id) {
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {
			Query query1 = session.createQuery(
					"update OpportunityDetails set name=:name, stage=:stage, assigned_to=:assigned_to, amount=: amount, expected_close_date=:expected_close_date,first_name=:first_name,last_name=:last_name,company_address=:company_address,contact_no=:contact_no,email=:email,website=:website,designation=:designation,salvation=:salvation,customer_id=:customer_id  where company_id=:c_id and opportunity_id=:opp_id");
			query1.setParameter("c_id", c_id).setParameter("name", opportunityDetails.getName());
			query1.setParameter("stage", opportunityDetails.getStage());
			query1.setParameter("opp_id", opportunityDetails.getOpportunity_id());
			query1.setParameter("assigned_to", opportunityDetails.getAssigned_to());
			query1.setParameter("amount", opportunityDetails.getAmount());
			query1.setParameter("expected_close_date", opportunityDetails.getExpected_close_date());
			query1.setParameter("first_name", opportunityDetails.getFirst_name());
			query1.setParameter("last_name", opportunityDetails.getLast_name());
			query1.setParameter("salvation", opportunityDetails.getSalvation());
			query1.setParameter("designation", opportunityDetails.getDesignation());
			query1.setParameter("website", opportunityDetails.getDesignation());
			query1.setParameter("email", opportunityDetails.getEmail());
			query1.setParameter("contact_no", opportunityDetails.getContact_no());
			query1.setParameter("company_address", opportunityDetails.getCompany_address());
			query1.setParameter("customer_id", opportunityDetails.getCustomer_id());

			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public ActivityDetails editActivity(int c_id, int u_id, int activity_id) {
		Session session = initiateSession();
		try {
			ActivityDetails activityDetails = session.get(ActivityDetails.class, activity_id);
			if (activityDetails != null) {
				return activityDetails;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public boolean updateActivity(ActivityDetails activityDetails, int c_id, int u_id) {
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {
			Query query1 = session.createQuery(
					"update ActivityDetails set subject=:subject, activity_status=:activity_status, assigned_to=:assigned_to, venue=:venue, activity_date=:activity_date,remark=:remark,description=:description where company_id=:c_id and activity_id=:activity_id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("subject", activityDetails.getSubject());
			query1.setParameter("activity_status", activityDetails.getActivity_status());
			query1.setParameter("activity_id", activityDetails.getActivity_id());
			query1.setParameter("assigned_to", activityDetails.getAssigned_to());
			query1.setParameter("venue", activityDetails.getVenue());
			query1.setParameter("activity_date", activityDetails.getActivity_date());
			query1.setParameter("remark", activityDetails.getRemark());
			query1.setParameter("description", activityDetails.getDescription());
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean deleteActivity(int c_id, int u_id, int activity_id) {
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {
			Query query1 = session.createQuery(
					"update ActivityDetails set status=:status where company_id=:c_id and activity_id=:activity_id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("status", 0);
			query1.setParameter("activity_id", activity_id);
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public NewOpportunityList getOpportunityPieChartData(int c_id, int u_id, String sqlAssigned) {
		Session session = initiateSession();
		try {
			if (sqlAssigned == null) {
				sqlAssigned = "";
			}

			BigDecimal sum5 = new BigDecimal(0);
			BigDecimal sum6 = new BigDecimal(0);

			String str5 = " from opportunity_details od, opportunity_stages  os where od.stage=os.id and os.stage='Closed Won' and od.status=1"
					+ sqlAssigned + " and od.company_id=";
			String str6 = " from opportunity_details od, opportunity_stages  os where od.stage=os.id and os.stage='Closed Lost' and od.status=1"
					+ sqlAssigned + " and od.company_id=";

			Query query12 = session.createSQLQuery("select SUM(amount)" + str5 + c_id);
			if (query12.uniqueResult() != null)
				sum5 = (BigDecimal) query12.uniqueResult();
			Query query13 = session.createSQLQuery("select SUM(amount)" + str6 + c_id);
			if (query13.uniqueResult() != null)
				sum6 = (BigDecimal) query13.uniqueResult();

			List<OpportunityStages> stageList = fmLeadService.getOpportunityStagesList(c_id);
			LinkedHashMap<String, Integer> stageCounts = new LinkedHashMap<>();
			LinkedHashMap<String, BigInteger> stageAmounts = new LinkedHashMap<>();

			stageList.sort((o1, o2) -> Integer.compare(o1.getSequence(), o2.getSequence()));

			for (OpportunityStages stage : stageList) {
				String queryString = "select count(*) from OpportunityDetails od where od.stage=:stageId and od.status=1  and od.company_id=:companyId";
				String amountQueryString = "select SUM(od.amount) from OpportunityDetails od where od.stage=:stageId and od.status=1  and od.company_id=:companyId";

				Query querynew = session.createQuery(queryString);
				querynew.setParameter("stageId", stage.getId());
				querynew.setParameter("companyId", c_id);

				Query amountQuery = session.createQuery(amountQueryString);
				amountQuery.setParameter("stageId", stage.getId());
				amountQuery.setParameter("companyId", c_id);

				Number countOfStages = (Number) querynew.uniqueResult();
				if (countOfStages instanceof Long) {
					Long countOfLat = (Long) countOfStages;
					stageCounts.put(stage.getStage_name(), countOfLat.intValue());

				} else if (countOfStages instanceof BigInteger) {
					BigInteger countOfLat = (BigInteger) countOfStages;
					stageCounts.put(stage.getStage_name(), countOfLat.intValue());
				}

				Number sumOfAmounts = (Number) amountQuery.uniqueResult();
				if (sumOfAmounts instanceof Long) {
					BigInteger bigIntegerValue = new BigInteger(String.valueOf(sumOfAmounts));
					stageAmounts.put(stage.getStage_name(), bigIntegerValue);

				} else if (sumOfAmounts instanceof BigInteger) {
					BigInteger amt = (BigInteger) sumOfAmounts;
					stageAmounts.put(stage.getStage_name(), amt);
				}
			}

			NewOpportunityList nol = new NewOpportunityList();
			BigInteger sumNew = stageAmounts.values().stream().flatMap(Stream::of).reduce(BigInteger.ZERO,
					BigInteger::add);

			BigInteger sumOfCount = stageCounts.values().stream().map(BigInteger::valueOf).reduce(BigInteger.ZERO,
					BigInteger::add);

			nol.setStageCounts(stageCounts);
			nol.setStageAmt(stageAmounts);
			nol.setTotal_opportunities(sumOfCount);
			nol.setTotal_amount(sumNew);

			nol.setTotal_closedWon(sum5.toBigInteger());
			nol.setTotal_closedLost(sum6.toBigInteger());

			return nol;

		} catch (Exception e) {
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);

		}
		return null;
	}

	@Override
	public boolean saveQuote(QuotationDetails quotationDetails, int c_id, int u_id) {
		Session session = initiateSession();
		try {

			quotationDetails.setCompany_id(c_id);
			quotationDetails.setCreated_by(u_id);
			quotationDetails.setCreated_date(new Date());
			quotationDetails.setUpdated_by(u_id);
			quotationDetails.setUpdated_date(new Date());
			quotationDetails.setStatus(1);
			session.save(quotationDetails);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return true;

	}

	@Override
	public QuotationDetails edit_quote(int c_id, int u_id, int quote_id) {
		Session session = initiateSession();
		try {
			QuotationDetails quotationDetails = session.get(QuotationDetails.class, quote_id);
			if (quotationDetails != null) {
				return quotationDetails;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public boolean update_quote(QuotationDetails quotationDetails, int c_id, int u_id) {
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {
			Query query1 = session.createQuery(
					"update QuotationDetails set subject=:subject, billing_address=:billing_address, shipping_address=:shipping_address, t_and_c=:t_and_c,description=:description,opportunity_name=:opportunity_name,valid_until=:valid_until where company_id=:c_id and quote_id=:quote_id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("subject", quotationDetails.getSubject());
			query1.setParameter("billing_address", quotationDetails.getBilling_address());
			query1.setParameter("shipping_address", quotationDetails.getShipping_address());
			query1.setParameter("t_and_c", quotationDetails.getT_and_c());
			query1.setParameter("quote_id", quotationDetails.getQuote_id());
			query1.setParameter("description", quotationDetails.getDescription());
			query1.setParameter("opportunity_name", quotationDetails.getOpportunity_name());
			query1.setParameter("valid_until", quotationDetails.getValid_until());
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean deleteQuote(int c_id, int u_id, int quote_id) {
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {
			Query query1 = session.createQuery(
					"update QuotationDetails set status=:status where company_id=:c_id and quote_id=:quote_id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("status", 0);
			query1.setParameter("quote_id", quote_id);
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public List<OpportunityDetails> getOpportunityNameList(int c_id, int u_id) {
		Session session = initiateSession();
		List<OpportunityDetails> opportunitydetails = new ArrayList<>();
		try {
			Query query = session
					.createQuery("select opportunity_id,name from OpportunityDetails where company_id=" + c_id);
			List list = query.list();

			if (list != null && list.size() > 0) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					OpportunityDetails Opportunitydetails = new OpportunityDetails();
					Opportunitydetails.setOpportunity_id(((int) obj[0]));
					Opportunitydetails.setName((String) obj[1]);
					opportunitydetails.add(Opportunitydetails);
				}

			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return opportunitydetails;
	}

	@Override
	public SM_LeadDetails getLeadObject(String opportunity_name, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"from SM_LeadDetails where company_name='" + opportunity_name + "' and company_id=" + c_id);
			SM_LeadDetails sm_LeadDetails = (SM_LeadDetails) query.uniqueResult();
			if (sm_LeadDetails != null) {
				return sm_LeadDetails;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public ProductDetails getProductObject(String product_name, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from ProductDetails where name='" + product_name + "' or product_code='"
					+ product_name + "' and company_id=" + c_id);
			ProductDetails productDetails = (ProductDetails) query.uniqueResult();
			if (productDetails != null) {
				return productDetails;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public FieldMIConfiguration getFieldMIConfigurationObject(int c_id, String parent_name) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			String sql = "select id,email,end_range,postfix,prefix,start_range from FieldMIConfiguration where company_id="
					+ c_id + " and parent_name='" + parent_name + "'";
			Query query = session.createQuery(sql);
			List list1 = query.list();
			for (Object object : list1) {
				FieldMIConfiguration details = new FieldMIConfiguration();
				Object[] obj = (Object[]) object;
				details.setId((int) obj[0]);
				details.setEmail((String) obj[1]);
				details.setEnd_range((String) obj[2]);
				details.setPostfix((String) obj[3]);
				details.setPrefix((String) obj[4]);
				details.setStart_range((String) obj[5]);
				details.setCompany_id(c_id);
				return details;
			}

		} catch (Exception e) {
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);

		}
		return null;
	}

	@Override
	public boolean saveActivity(ActivityDetails activityDetails, int c_id, int u_id) {
		Session session = initiateSession();
		try {
			activityDetails.setCompany_id(c_id);
			activityDetails.setCreated_by(u_id);
			activityDetails.setCreated_date(new Date());
			activityDetails.setUpdated_by(u_id);
			activityDetails.setUpdated_date(new Date());
			activityDetails.setStatus(1);
			session.save(activityDetails);
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
	public WSActivityList getActivityList(ActivityDetails activityDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		WSActivityList details = new WSActivityList();
		List list = null;
		String query = "";
		int total_pages = 0;

		try {
			String lastDate = "";
			String outputDate = ReturnDate.returnLastUpdateddate();
			if (activityDetails.getLast_date() != "")
				lastDate = " and ad.updated_date > '" + activityDetails.getLast_date() + "'";

			CommonMethods commonMethods = new CommonMethods();
			query = "select ad.activity_id,ad.subject,ad.venue,ad.remark,ad.activity_date,ad.parent_type,ad.parent_id,ad.description,ad.activity_status,concat(u.first_name,' ',u.last_name) as assigned_to, ad.assigned_to"
					+ " from ActivityDetails ad join User u on ad.assigned_to=u.user_id"
					+ " where ad.status=1 and ad.company_id=" + c_id + " and ad.parent_type='"
					+ activityDetails.getParent_type() + "' and (ad.created_by=" + activityDetails.getCreated_by()
					+ " or ad.assigned_to=" + activityDetails.getCreated_by() + ")" + lastDate;
			total_pages = commonMethods.getTotalPagesCountHql(session,
					"select count(ad.activity_id) " + " from ActivityDetails ad "
							+ "  where ad.status=1 and ad.company_id=" + c_id + " and ad.parent_type='"
							+ activityDetails.getParent_type() + "' and (ad.created_by="
							+ activityDetails.getCreated_by() + " or ad.assigned_to=" + activityDetails.getCreated_by()
							+ ")" + lastDate);

			list = commonMethods.quaryListPagination(session, query, 1, no_of_records_per_page);
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				ActivityDetails Activitydetails = new ActivityDetails();
				Activitydetails.setActivity_id((int) obj[0]);
				Activitydetails.setSubject((String) obj[1]);
				Activitydetails.setVenue((String) obj[2]);
				Activitydetails.setRemark((String) obj[3]);
				Activitydetails.setActivity_date((String) obj[4]);
				Activitydetails.setParent_type((String) obj[5]);
				Activitydetails.setParent_id((int) obj[6]);
				Activitydetails.setDescription((String) obj[7]);
				Activitydetails.setActivity_status((String) obj[8]);
				Activitydetails.setAssigned_to((String) obj[9]);
				String assigned_to_id = (String) (obj[10]);
				Activitydetails.setAssigned_to_id(Integer.parseInt(assigned_to_id));

				details.getActivityList().add(Activitydetails);
			}

			details.setTotal_records(total_pages);
			details.setRecordsPerPagesNew(no_of_records_per_page);
			details.setPage_no(1);
			details.setLastUpdatedDate(outputDate);
			details.setCompany_id(c_id);

			return details;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return details;
	}

	@Override
	public WSActivityList activityPieChartData(int c_id, int u_id, int parent_id, String parent_type) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			long count = 0;
			long count1 = 0;
			long count2 = 0;
			long count3 = 0;
			long count4 = 0;
			long count5 = 0;

			Query query = session.createQuery(
					"select count(*) from ActivityDetails where parent_type='" + parent_type + "' and parent_id="
							+ parent_id + " and activity_status='Completed' and status=1 and company_id=" + c_id);
			count = (long) query.uniqueResult();

			Query query1 = session.createQuery(
					"select count(*) from ActivityDetails where parent_type='" + parent_type + "' and parent_id="
							+ parent_id + " and activity_status='Deferred' and status=1 and company_id=" + c_id);
			count1 = (long) query1.uniqueResult();

			Query query2 = session.createQuery(
					"select count(*) from ActivityDetails where parent_type='" + parent_type + "' and parent_id="
							+ parent_id + " and activity_status='Planned' and status=1 and company_id=" + c_id);
			count2 = (long) query2.uniqueResult();

			Query query3 = session.createQuery(
					"select count(*) from ActivityDetails where parent_type='" + parent_type + "' and parent_id="
							+ parent_id + " and activity_status='Pending Inputs' and status=1 and company_id=" + c_id);
			count3 = (long) query3.uniqueResult();

			Query query4 = session.createQuery(
					"select count(*) from ActivityDetails where parent_type='" + parent_type + "' and parent_id="
							+ parent_id + " and activity_status='In Progress' and status=1 and company_id=" + c_id);
			count4 = (long) query4.uniqueResult();

			Query query5 = session.createQuery(
					"select count(*) from ActivityDetails where parent_type='" + parent_type + "' and parent_id="
							+ parent_id + " and activity_status='Not Started' and status=1 and company_id=" + c_id);
			count5 = (long) query5.uniqueResult();
			WSActivityList details = new WSActivityList();

			details.setCompleted(count);
			details.setDeferred(count1);
			details.setPlanned(count2);
			details.setPending_inputs(count3);
			details.setInprogress(count4);
			details.setNot_started(count5);

			return details;

		} catch (Exception e) {
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);

		}
		return null;
	}

	@Override
	public List<OpportunityDetails> getOpportunityReportExport(String opportunityListQuery) {
		Session session = initiateSession();
		List<OpportunityDetails> list2 = new ArrayList<OpportunityDetails>();

		try {
			Query query = session.createSQLQuery(opportunityListQuery);
			List list = query.list();

			for (Object object : list) {
				Object[] obj = (Object[]) object;
				OpportunityDetails details = new OpportunityDetails();

				details.setSalvation((String) (obj[0]));
				details.setFirst_name((String) (obj[1]));
				details.setLast_name((String) (obj[2]));
				details.setEmail((String) (obj[3]));
				details.setContact_no((String) (obj[4]));
				details.setCompany_address((String) (obj[5]));
				details.setCustomer_name((String) (obj[6]));
				details.setAssigned_to((String) obj[7]);
				details.setStage_name((String) (obj[8]));
				details.setValue(String.valueOf(obj[9]));
				details.setExpected_close_date((String) obj[10]);
				details.setWebsite((String) (obj[11]));
				details.setDesignation((String) (obj[12]));
				details.setName((String) obj[13]);

				list2.add(details);

			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public boolean saveOpportunityStage(OpportunityStages oppStageObject) {
		Session session = initiateSession();
		try {
			Date date = new Date();
			oppStageObject.setCreated_date(date);
			oppStageObject.setUpdated_date(date);
			session.save(oppStageObject);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateStage(OpportunityStages opportunityStages, int c_id, int u_id) {
		Session session = initiateSession();
		try {
			Query query1 = session.createQuery(
					"update OpportunityStages set stage=:stage, sequence=:sequence, percentage=:percentage where company_id=:c_id and id=:id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("stage", opportunityStages.getStage());
			query1.setParameter("sequence", opportunityStages.getSequence());
			query1.setParameter("percentage", opportunityStages.getPercentage());
			query1.setParameter("id", opportunityStages.getId());
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean deleteStage(int c_id, int u_id, int id) {
		Session session = initiateSession();
		try {
			Query query1 = session
					.createQuery("update OpportunityStages set status=:status where company_id=:c_id and id=:id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("status", 0);
			query1.setParameter("id", id);
			query1.executeUpdate();
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public List<OpportunityStages> getOpportunityStagesList(int c_id) {
		Session session = initiateSession();
		List<OpportunityStages> stageList = new ArrayList<>();
		try {
			Query query = session
					.createQuery("select id,percentage,stage,sequence from OpportunityStages where status=1 and company_id="
							+ c_id + " ORDER BY sequence ASC");
			List list = query.list();

			if (list != null && list.size() > 0) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					OpportunityStages stageDetails = new OpportunityStages();
					stageDetails.setId(((int) obj[0]));
					int percentage = (int) obj[1];
					stageDetails.setStage((String) obj[2] + "(" + percentage + "%" + ")");
					stageDetails.setStage_name((String) obj[2]);
					stageDetails.setSequence( (int) obj[3]);
					System.out.println(stageDetails);
					stageList.add(stageDetails);
				}

			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return stageList;
	}

	@Override
	public long checkPercentageSum(int c_id) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery("select sum(percentage) from OpportunityStages where status=1 and company_id=" + c_id);
			long sum = (long) query.uniqueResult();
			return sum;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return 0;
	}

	@Override
	public List<String> getOpportunity_nameList(int c_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("select name from OpportunityDetails where company_id=" + c_id + "");
			return query.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public SM_LeadDetails getLeadObject(int lead_id, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery(" from SM_LeadDetails where company_id=" + c_id + " and lead_id=" + lead_id);
			SM_LeadDetails leadDetails = (SM_LeadDetails) query.uniqueResult();
			if (leadDetails != null)
				return leadDetails;
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
	public WsOpportunityList getOpportunityList(WsOpportunityList opportunityList) {
		Session session = initiateSession();
		WsOpportunityList details = new WsOpportunityList();
		CommonMethods commonMethods = new CommonMethods();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List list = null;
		String query = "";
		int total_records = 0;
		try {

			List<Integer> users = commonMethods.getUserHirachyWise(opportunityList.getCompany_id(),
					opportunityList.getUser_id(), session,"","","","");

			String userid = "";
			for (int i = 0; i < users.size(); i++) {
				userid = userid + users.get(i).intValue() + ",";

			}
			if (userid != null && userid.length() > 0 && userid.charAt(userid.length() - 1) == ',') {
				userid = userid.substring(0, userid.length() - 1);
			}
			String lastDate = "";
			String outputDate1 = "";
			String outputDate = ReturnDate.returnLastUpdateddate();

			if (opportunityList.getLast_date() != "") {
				outputDate1 = ReturnDate.UTCDateTime(opportunityList.getLast_date());
				lastDate = " and a.updated_date > '" + outputDate1 + "'  ";
			}

			query = "SELECT a.opportunity_id, a.name, CONCAT(u.first_name,' ',u.last_name) AS assigned_to, os.stage, a.expected_close_date, a.amount, a.lead_id, "
					+ "a.company_address, a.contact_no, a.designation, a.email, a.salvation, a.first_name, a.last_name, a.website, a.customer_id, a.close_comment, a.close_status, "
					+ "a.created_date, a.lead_IdRange, a.id_range, c.customer_name, os.sequence "
					+ "FROM OpportunityDetails a "
					+ "LEFT JOIN CustomerDetails c ON a.customer_id = c.customer_id "
					+ "JOIN User u ON u.user_id = a.assigned_to "
					+ "JOIN OpportunityStages os ON os.id = a.stage "
					+ "WHERE a.close_status != 0 AND a.company_id = " + opportunityList.getCompany_id() + " "
					+ "AND (a.assigned_to IN (" + userid + ") OR a.created_by IN (" + userid + ")) " + lastDate;

			total_records = commonMethods.getTotalPagesCountHql(session,
					"select count(*) from OpportunityDetails a where a.close_status!=0 and a.company_id="
							+ opportunityList.getCompany_id() + " and (a.created_by IN (" + userid
							+ ") or a.assigned_to IN " + "(" + userid + "))" + lastDate);

			list = commonMethods.quaryListPagination(session, query, 1, no_of_records_per_page);
			for (Object object : list) {	
				Object[] obj = (Object[]) object;
				OpportunityDetails opportunityDetails = new OpportunityDetails();

				opportunityDetails.setOpportunity_id((int) obj[0]);
				opportunityDetails.setName((String) obj[1]);
				opportunityDetails.setAssigned_to((String) obj[2]);
				opportunityDetails.setStage_name((String) obj[3]);
				opportunityDetails.setExpected_close_date((String) obj[4]);
				opportunityDetails.setAmount((int) obj[5]);
				opportunityDetails.setLead_id((int) obj[6]);
				opportunityDetails.setCompany_address((String) obj[7]);
				opportunityDetails.setContact_no((String) obj[8]);
				opportunityDetails.setDesignation((String) obj[9]);
				opportunityDetails.setEmail((String) obj[10]);
				opportunityDetails.setSalvation((String) obj[11]);
				opportunityDetails.setFirst_name((String) obj[12]);
				opportunityDetails.setLast_name((String) obj[13]);
				opportunityDetails.setWebsite((String) obj[14]);
				opportunityDetails.setCustomer_id((int) obj[15]);
				opportunityDetails.setClose_comment((String) obj[16]);
				opportunityDetails.setClose_status((int) obj[17]);
				opportunityDetails.setCreated_at(sdf.format((Date) obj[18]));
				opportunityDetails.setLead_IdRange((String) obj[19]);
				opportunityDetails.setId_range((String) obj[20]);
				opportunityDetails.setCustomer_name((String) obj[21]);
				opportunityDetails.setSequence((int) obj[22]);

				details.getOpportunityDetails().add(opportunityDetails);
			}
			details.setTotal_records(total_records);
			details.setRecordsPerPagesNew(no_of_records_per_page);
			details.setPage_no(1);
			details.setCompany_id(opportunityList.getCompany_id());
			details.setLastUpdatedDate(outputDate);
			details.setTotal_Pages(total_records);

			return details;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return details;
	}

	@Override
	public List<LeadProduct> getLeadProductList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from LeadProduct where company_id=" + company_id + " and status=1");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<LeadHasProducts> getLeadHasProductDetails(int company_id, int lead_id) {
		Session session2 = initiateSession();
		List<LeadHasProducts> list1 = new ArrayList<>();
		try {
			Query query = session2
					.createSQLQuery("SELECT lhp.product_id,lp.product_name,lhp.quantity,lhp.uom FROM lead_has_products as lhp "

							+ " INNER JOIN lead_product as lp on lhp.product_id=lp.product_id "

							+ " where lhp.status=1 and lhp.lead_id=" + lead_id + " and lhp.company_id=" + company_id);
			List list = query.list();

			for (Object object : list) {

				Object[] obj = (Object[]) object;
				LeadHasProducts details = new LeadHasProducts();
				details.setProduct_id((int) obj[0]);
				details.setProduct_name((String) obj[1]);
				details.setQuantity((int) obj[2]);
				details.setUom((String) obj[3]);

				list1.add(details);
			}
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			destroySession(session2);
		}
		return null;
	}

	@Override
	public WsLeadTypeList getLeadProductListData(Credentials credentials) {
		Session session = initiateSession();
		WsLeadTypeList wsLeadTypeList = new WsLeadTypeList();
		WsLeadProducts wsLeadProduct = null;
		List list = null;
		String query = "";
		int total_pages = 0;
		String lastDate = "";
		String outputDate1 = "";
		String outputDate = ReturnDate.returnLastUpdateddate();

		try {
			int c_id = credentials.getCompany_id().intValue();
			CommonMethods commonMethods = new CommonMethods();
			if (credentials.getLast_date() != null && !"".equals(credentials.getLast_date()) && !credentials.getLast_date().equals("null")) {
				outputDate1 = ReturnDate.UTCDateTime(credentials.getLast_date());
				lastDate = " and updated_date > '" + outputDate1 + "'";
			}
				

			query = "select product_id,product_name,sequence,uomSize  from LeadProduct  where status=1 and company_id=" + c_id
					+ lastDate;
			total_pages = commonMethods.getTotalPagesCountSql(session,
					"select count(product_id) from lead_product  where status=1 and company_id=" + c_id + lastDate);
			if (credentials.getPage_no() <= 0) {
				credentials.setPage_no(1);
			}
			list = commonMethods.quaryListPagination(session, query, credentials.getPage_no(), no_of_records_per_page);
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				wsLeadProduct = new WsLeadProducts();
				wsLeadProduct.setProduct_id((int) obj[0]);
				wsLeadProduct.setProduct_name(obj[1] == null ? "" : obj[1] + "");
				wsLeadProduct.setSequence((int) obj[2]);
				wsLeadProduct.setUomSize(obj[3] == null ? "" : obj[3] + "");
				wsLeadTypeList.getWsLeadProducts().add(wsLeadProduct);
			}

			wsLeadTypeList.setTotal_records(total_pages);
			wsLeadTypeList.setRecordsPerPagesNew(no_of_records_per_page);
			wsLeadTypeList.setLastUpdatedDate(outputDate);

			return wsLeadTypeList;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return wsLeadTypeList;
	}
}
