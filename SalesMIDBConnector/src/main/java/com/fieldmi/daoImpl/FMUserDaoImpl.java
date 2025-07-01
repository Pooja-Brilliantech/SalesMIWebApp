package com.fieldmi.daoImpl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMUserServiceDao;
import com.fieldmi.stubs.WSUpdateSyncStatusInput;
import com.fieldmi.stubs.WsUserDashboardDetails;
import com.fieldmi.utils.GcmNotification;
import com.softtantra.salesapp.pojo.CompanyLocations;
import com.softtantra.salesapp.pojo.CustomerClassification;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.LoginDetails;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasDistributor;
import com.softtantra.salesapp.pojo.UserHasRoutes;
import com.softtantra.ws.WsUserAppInfo;

public class FMUserDaoImpl implements FMUserServiceDao {

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
	public User getUserInfoForEdit(int user_id, int c_id) {

		Session session = initiateSession();
		try {
			String main_query = "SELECT distinct u.user_id,u.new_city,u.distributor_id,u.email,u.field1,u.field2,u.first_name,u.last_name,u.mobile_no,  u.profile_img,u.reporting_manager_id,u.reporting_role_id,u.role_id,u.signature,u.new_state,u.status,u.time_zone,s.state_name,c.city_name,u.created_by,u.created_date,login.password,u.country_id ,o.country_name,vehicle_no,account_state,u.product_of_the_month_id,u.target_for_product_value,u.target_for_product_unit,u.passport_copy,"
					+ "u.outlet_badge,u.health_card,u.insurance,u.allowed_discount,u.userLogin,u.useEmailAsUserName FROM user_details u inner join  login_details login on u.user_id=login.user_id and login.company_id="
					+ c_id
					+ " left outer join country o on o.country_id=u.country_id   left outer join  states s on u.new_state=s.state_id  left outer join  cities c on u.new_city=c.city_id where  u.user_id="
					+ user_id + "  and u.company_id=" + c_id + "";
			Query query = session.createSQLQuery(main_query);

			if (query.uniqueResult() != null) {
				User u = new User();
				Object[] obj = (Object[]) query.uniqueResult();

				u.setUser_id((Integer) obj[0]);
				u.setNew_city((int) obj[1]);
				u.setDistributor_id((int) obj[2]);
				u.setEmail((String) obj[3]);
				u.setField1((String) obj[4]);
				u.setField2((String) obj[5]);
				u.setFirst_name((String) obj[6]);
				u.setLast_name((String) obj[7]);
				u.setMobile_no((String) obj[8]);
				u.setProfile_img((String) obj[9]);
				u.setReporting_manager_id((int) obj[10]);
				u.setReporting_role_id((int) obj[11]);
				u.setRole_id((int) obj[12]);
				u.setSignature((String) obj[13]);
				u.setState((int) obj[14]);
				u.setStatus((int) obj[15]);
				u.setTime_zone((String) obj[16]);
				u.setState_name((String) obj[17]);
				u.setCity_name((String) obj[18]);
				u.setCreated_by((int) obj[19]);
				u.setCreated_date((Date) obj[20]);
				u.setPassword((String) obj[21]);
				u.setCountry_id((int) obj[22]);
				u.setCountry_name((String) obj[23]);
				if (obj[24] != null)
					u.setVehicle_no((String) obj[24]);

				if (obj[25] != null)
					u.setAccount_state((String) obj[25]);

				u.setProduct_of_the_month_id((int) obj[26]);
				u.setTarget_for_product_value((double) obj[27]);
				u.setTarget_for_product_unit((int) obj[28]);
				u.setPassport_copy((String) obj[29]);
				u.setOutlet_badge((String) obj[30]);
				u.setHealth_card((String) obj[31]);
				u.setInsurance((String) obj[32]);
				u.setAllowed_discount(obj[33] != null ? ((double) obj[33]) : 0.0d);
				u.setUserLogin((String) obj[34]);
				u.setUseEmailAsUserName((String) obj[35]);
				return u;
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
	public int updateUserForSyncing(int c_id, String coloumnName, String title, String body) {
		Session session = initiateSession();
		try {

			Query query = session.createQuery("update User  set " + coloumnName + " =1  where company_id=" + c_id + "");
			query.executeUpdate();

			return 1;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return 0;

	}

	public boolean sendSyncNotification(String title, String deviceIdstring, String body) {

		try {

			HashMap<String, Object> msgbody = new HashMap<>();
			msgbody.put("Common Notification", body);
			msgbody.put("Title", title);

			HashMap<String, Object> msgObject = new HashMap<>();
			msgObject.put("notificationdetails", msgbody);

			String serverKey = "AIzaSyB6eAzSs1SPo7dG3hNEBx-LIBe_2PKtUUo";

			GcmNotification gcmNotifier = new GcmNotification(serverKey, deviceIdstring);

			gcmNotifier.setNotification(title, body);

			gcmNotifier.setMessage("Common Notification", msgObject);

			return gcmNotifier.sendNotification();

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		}
	}

	@Override
	public boolean updateUserSyncStatus(WSUpdateSyncStatusInput wsUpdateSyncStatusInput, long id) {

		Session session = initiateSession();
		try {

			Query query = session.createQuery("update User set productListSync='"
					+ wsUpdateSyncStatusInput.getProductListSync() + "' where user_id=" + id);

			query.executeUpdate();

			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public List<String> getEmails(List<Integer> list, int c_id) {

		Session session = initiateSession();

		int counter = 0;
		StringBuilder ids = new StringBuilder();
		for (Object object : list) {
			if (counter > 0) {
				ids.append(",");

			}
			ids.append(object);
			counter++;
		}
		String deviceIdQuery = "select email from user_details where company_id=" + c_id
				+ " and status=1 and user_id in (" + ids + ")";
		try {
			Query q = session.createSQLQuery(deviceIdQuery);
			return q.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean renewUserFromEmail(String email, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("update User set status=:status where email=:email and company_id=:c_id");
			query.setParameter("status", 1);
			query.setParameter("email", email);
			query.setParameter("c_id", c_id);
			int id = (int) query.executeUpdate();

			if (id > 0 && id != 0) {
				return true;
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
	public List<CustomerClassification> getCustomerClassifcation(int c_id) {
		Session sessionoofDb = initiateSession();
		List<CustomerClassification> list1 = new ArrayList<>();

		try {

			Query query = sessionoofDb.createQuery(
					"select classificationName,customerType from CustomerClassification where company_id=" + c_id);

			List list = query.list();

			for (Object object : list) {

				Object[] obj = (Object[]) object;
				CustomerClassification master = new CustomerClassification();
				master.setClassificationName((String) obj[0]);
				master.setCustomerType((String) obj[1]);

				list1.add(master);
			}

			return list1;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(sessionoofDb);
		}
	}

	@Override
	public boolean deleteMulipleUsers(String user_ids, int c_id) {
		Session session = initiateSession();
		try {

			String sqlQuery = "update user_details set status=2  where user_id in ( " + user_ids + ") and company_id="
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
	public List<CompanyLocations> companyLocationList(int c_id) {
		Session sessionoofDb = initiateSession();
		List<CompanyLocations> list1 = new ArrayList<>();

		try {

			Query query = sessionoofDb.createQuery(
					"select companyLocationId,companyLocationName,companyLocationCode from CompanyLocations where company_id="
							+ c_id + " order by companyLocationName");

			List list = query.list();

			for (Object object : list) {

				Object[] obj = (Object[]) object;
				CompanyLocations master = new CompanyLocations();
				master.setCompanyLocationId((int) obj[0]);
				master.setCompanyLocationName((String) obj[1]);
				master.setCompanyLocationCode((String) obj[2]);

				list1.add(master);
			}

			return list1;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(sessionoofDb);
		}
	}

	@Override
	public List<WsUserAppInfo> getUserAppDetails(int company_id, int user_id) {
		Session session = initiateSession();
		List<WsUserAppInfo> infoList = new ArrayList<>();
		try {
			Query query = session.createQuery("select apk_version,app_permissions from User where company_id="
					+ company_id + " and user_id=" + user_id);

			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsUserAppInfo user = new WsUserAppInfo();
				if (obj[0] != null)
					user.setApk_version((String) obj[0]);
				if (obj[1] != null)
					user.setApp_permissions((String) obj[1]);

				infoList.add(user);
			}
			return infoList;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean updateUserAppVerionAndPermissions(WsUserAppInfo wsUserAppInfo) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update User set apk_version=:apk_version,app_permissions=:app_permissions where company_id=:company_id and user_id=:user_id");
			query.setParameter("apk_version", wsUserAppInfo.getApk_version())
					.setParameter("app_permissions", wsUserAppInfo.getApp_permissions())
					.setParameter("company_id", wsUserAppInfo.getCompany_id())
					.setParameter("user_id", wsUserAppInfo.getUser_id());

			query.executeUpdate();
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean reActivateUser(int user_id, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery("update User set status=1  where user_id=" + user_id + " and company_id=" + c_id);
			int id = (int) query.executeUpdate();
			Query query1 = session.createQuery(
					"update  LoginDetails set is_active=1  where user_id=" + user_id + "  and company_id=" + c_id);
			query1.executeUpdate();
			if (id > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<DistributorDetails> getDistributorAndRoute(int company_id, int u_id) {
		Session session = initiateSession();
		List<DistributorDetails> list2 = new ArrayList<>();
		try {
			String sql = "SELECT DISTINCT(concat(d.distributor_id, '-' ,r.route_id)) AS dist_route_id,concat(d.distributor_name, ' -> ' ,r.route_name) AS dist_route_name from route_details r inner join distributor_details d on r.distributor_id=d.distributor_id inner join user_has_distributor uhd on uhd.distributor_id=d.distributor_id WHERE r.company_id="
					+ company_id + " and r.status=1 and uhd.user_id=" + u_id + " and d.status=1 and d.company_id="
					+ company_id + " ORDER BY dist_route_name";
			// Query query = session.createQuery("SELECT concat(d.distributor_id, '-'
			// ,r.route_id) AS dist_route_id,concat(d.distributor_name, ' -> '
			// ,r.route_name) AS dist_route_name from Route r RIGHT JOIN DistributorDetails
			// d on r.distributor_id=d.distributor_id WHERE r.company_id="+company_id+" and
			// r.status=1");
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				DistributorDetails distributorDetails = new DistributorDetails();
				if (obj[0] != null)
					distributorDetails.setField1(((String) obj[0]));
				if (obj[1] != null)
					distributorDetails.setField2(((String) obj[1]));

				list2.add(distributorDetails);
			}
			return list2;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean insertDistributorsRouteToUser(String distributor_id, String user_ids, int c_id) {
		Session session = initiateSession();

		try {
			if (distributor_id != null && user_ids != null) {
				// distributor_id = distributor_id.substring(0, 0) + "" +
				// distributor_id.substring(0 + 1);
				String[] distRoutes = distributor_id.split(",");
				String[] users = user_ids.split(",");

				for (int i = 0; i < users.length; i++) {
					for (int j = 0; j < distRoutes.length; j++) {

						String[] distRoutes1 = distRoutes[j].split("-");
						int userId = Integer.parseInt(users[i]);

						int distributorId = Integer.parseInt(distRoutes1[0]);
						int routeId = Integer.parseInt(distRoutes1[1]);

						Query query = session
								.createQuery("select user_has_routes_id from UserHasRoutes where distributor_id="
										+ distributorId + " and company_id=" + c_id + " and user_id=" + userId
										+ " and route_id=" + routeId);

						List list = query.list();
						if (list.size() == 0 || list.isEmpty()) {
							UserHasRoutes userHasRoutes = new UserHasRoutes();

							userHasRoutes.setCompany_id(c_id);
							userHasRoutes.setDistributor_id(distributorId);
							userHasRoutes.setRoute_id(routeId);
							userHasRoutes.setUser_id(userId);
							session.save(userHasRoutes);

							UserHasDistributor userHasDistributor = new UserHasDistributor();

							userHasDistributor.setCompany_id(c_id);
							userHasDistributor.setDistributor_id(distributorId);
							userHasDistributor.setUser_id(userId);
							session.save(userHasDistributor);
						}
					}
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
	public boolean deleteDistributorRoute(int c_id, int u_id, int dist_id, int route_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("delete from UserHasRoutes where distributor_id=" + dist_id
					+ " and company_id=" + c_id + " and user_id=" + u_id + " and route_id=" + route_id);

			Query query1 = session.createQuery("delete from UserHasDistributor where distributor_id=" + dist_id
					+ " and company_id=" + c_id + " and user_id=" + u_id);
			query.executeUpdate();
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
	public boolean updateUserObject(User user) {
		Session session = initiateSession();
		try {
			session.update(user);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public List<WsUserDashboardDetails> dailyTrendUserLoginData(int c_id) {
		Session session = initiateSession();
		List<WsUserDashboardDetails> list2 = new ArrayList<WsUserDashboardDetails>();
		try {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			String startDate = "", endDate = "";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			startDate = df.format(c.getTime());
			endDate = df.format(new Date());
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yy");
			String sql = "SELECT count(user_id),lastlogin FROM `user_details` WHERE company_id=" + c_id
					+ " and status=1 and Date(lastlogin) BETWEEN '" + startDate + "' and '" + endDate
					+ "' GROUP BY Date(lastlogin) ORDER BY Date(lastlogin)";
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsUserDashboardDetails details = new WsUserDashboardDetails();
				Date date = (Date) obj[1];
				String newDate = format.format(date);
				details.setDateWithYear((String) newDate);
				details.setTotalUsers((BigInteger) obj[0]);

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
	public List<WsUserDashboardDetails> monthlyTrendUserLoginData(int c_id, String start_month, String end_month) {
		Session session = initiateSession();
		List<WsUserDashboardDetails> list2 = new ArrayList<WsUserDashboardDetails>();
		try {
			String lastYearStartDate = "";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			Date date1 = new SimpleDateFormat("MMMM").parse(start_month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			int month = cal.get(Calendar.MONTH);
			int year = 0;
			year = Calendar.getInstance().get(Calendar.YEAR);
			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year - 1);

			Date lastYearDate = cal.getTime();
			lastYearStartDate = df.format(lastYearDate);

			LocalDate bday = LocalDate.parse(lastYearStartDate);
			LocalDate today = LocalDate.now();
			Period age = Period.between(bday, today);
			int months = Math.abs(age.getMonths() + 1);

			String sql = "select months.year1 as year1,months.month1 as month1,concat(months.month1,' ',months.year1) AS monthq,ifnull(count(ud.user_id),0) AS totalUsers from (select MonthName( @curDate \\:= Date_SUB(@curDate, interval 1 MONTH)) as month1, Month( @curDate1 \\:= Date_SUB(@curDate1, interval 1 MONTH)) as month2, Year( @curDate2 \\:= Date_SUB(@curDate2, interval 1 MONTH)) as year1 from ( select @curDate \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate1 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate2 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month) ) sqlvars, user_details limit "
					+ months
					+ ") months LEFT JOIN user_details ud on months.month1=MonthName(ud.lastlogin) AND ud.company_id="
					+ c_id + " and ud.status=1 GROUP BY months.month1 order by months.year1, months.month2";
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsUserDashboardDetails details = new WsUserDashboardDetails();

				String s2 = (String) obj[1];
				String s3 = String.valueOf((int) obj[0]);
				details.setMonthWithYear(s2.substring(0, 3) + "-" + s3.substring(s3.length() - 2));
				details.setTotalUsers((BigInteger) obj[3]);

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
	public List<User> getUsers(int distributorId, int c_id) {
		Session session = initiateSession();

		List<User> list2 = new ArrayList<User>();
		try {

			org.hibernate.Query query = session.createSQLQuery(
					"SELECT DISTINCT u.user_id,u.first_name,u.last_name FROM user_details u INNER JOIN user_has_distributor uhd ON uhd.user_id = u.user_id WHERE u.company_id="
							+ c_id + " AND u.status=1 AND uhd.distributor_id=" + distributorId);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				User route = new User();
				route.setUser_id((int) obj[0]);
				route.setFirst_name((String) obj[1] + " " + (String) obj[2]);
				list2.add(route);
			}

		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public List<User> getHigherAuthority(int companyId, int userId) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query<User> userData_query = session
					.createQuery("from User where user_id=" + userId + " and company_id=" + companyId, User.class);
			User u = userData_query.uniqueResult();
			int reportManagerId = 0;
			int reportManagerId1 = 0;
			int uId = 0;
			List<User> uListNew = new ArrayList<>();
			int flag = 0;
			if (u != null) {
				reportManagerId = u.getReporting_manager_id();
				do {
					flag++;
					Query<User> reportData_query = null;
					reportData_query = session
							.createQuery("from User where user_id=" + reportManagerId + " and company_id=" + companyId, User.class);
					User u1 =  reportData_query.uniqueResult();
					if( u1 != null ) {
						
						uId = u1.getUser_id();
						reportManagerId1 = u1.getReporting_manager_id();
						if (reportManagerId1 != 0) {
							uListNew.add(u1);
						}
						reportManagerId = reportManagerId1;
						if (flag == 5) {
							break;
						}
					} else {
						
						FieldMILogger.warn(FMUserDaoImpl.class.getName(),new Exception("User not found with user id: " + reportManagerId));
						break;
					}
					
				} while (uId != reportManagerId1);

				return uListNew;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public LoginDetails getLoginDetails(String username, String password, int company_id) {
		LoginDetails details = new LoginDetails();
		Session session = initiateSession();
		try {
			// FieldMILogger.debug(this.getClass().getName(),username+password+company_id);
			String sql = "from LoginDetails where  user_name='" + username + "'" + " and  password='" + password
					+ "' and company_id=" + company_id + " and is_active=1";

			details = (LoginDetails) session.createQuery(sql).uniqueResult();

			if (details != null) {
				return details;
			} else {
				details = null;
			}

			return details;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),
					new Exception("User-->" + username + " Company_id==>" + company_id + " not found"));
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<WsUserDashboardDetails> monthlyTrendUserLogin(int c_id, int year, String  activityName) {
		Session session = initiateSession();
		List<WsUserDashboardDetails> objectToBeShowed = new ArrayList<WsUserDashboardDetails>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Query datesQuery = null;
			Query webQuery = null;
			
			
			for (int i = 1; i <= 12; i++) {
				datesQuery = session.createSQLQuery(
						"select fmc.db_date from fm_calendar fmc where  fmc.year=" + year + " and fmc.month=" + i);
				List<Date> dList = datesQuery.list();
				
					String webSql = "SELECT distinct(count(created_by)) FROM user_activity_log WHERE company_id=" + c_id
							+ "   and logMessage='"+activityName+"' and Date(created_date) BETWEEN '"
							+ dList.get(0) + "' and '" + dList.get(dList.size() - 1) + "' ";
					
					webQuery = session.createSQLQuery(webSql);
					
					BigInteger webCount = (BigInteger) webQuery.uniqueResult();
					
					WsUserDashboardDetails details = new WsUserDashboardDetails();
					details.setWebUsers(webCount);
					
					LocalDate currentDate = LocalDate.parse(sdf.format(dList.get(0)));
					Month m = currentDate.getMonth();
					details.setZoneName(m + " " + year);
					objectToBeShowed.add(details);
				

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
	public List<WsUserDashboardDetails> getUserData(int c_id, String notifcationType, String month,int account) {
		Session session = initiateSession();
		List<WsUserDashboardDetails> objectToBeShowed = new ArrayList<WsUserDashboardDetails>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			Query datesQuery = null;
			Query userQuery = null;
			Query webQuery = null;
			String logMessage=notifcationType;
			
			
			datesQuery = session.createSQLQuery(
					"select fmc.db_date from fm_calendar fmc where  fmc.year=" + month.split(" ")[1] + " and fmc.month_name='" + month.split(" ")[0].toLowerCase()+"'");
			List<Date> dList = datesQuery.list();
			userQuery = session.createSQLQuery("SELECT distinct(ul.created_by) FROM user_activity_log ul WHERE ul.company_id="+c_id+"  and ul.logMessage='"+logMessage+"' and DATE(ul.created_date) BETWEEN '"+dList.get(0)+"' and '"+dList.get(dList.size() - 1)+"' ") ;
			List<Integer> uIds=userQuery.list();
			for (int i = 0; i < uIds.size(); i++) {
				
				
					String webSql = "SELECT concat(u.first_name,' ',u.last_name) as username,ul.created_date,ul.notificationType FROM user_activity_log ul ,user_details u WHERE ul.company_id=" + c_id
							+ " and ul.created_by=u.user_id and ul.created_by="+uIds.get(i)+" and u.company_id= "+c_id+"  and ul.logMessage='"+logMessage+"' and DATE(ul.created_date) BETWEEN '"+dList.get(0)+"' and '"+dList.get(dList.size() - 1)+"' order by ul.created_date desc";
					
					webQuery = session.createSQLQuery(webSql);
					List l=webQuery.list();
					for (Object object : l) {
						Object[] obj = (Object[]) object;
						WsUserDashboardDetails details = new WsUserDashboardDetails();
						details.setMonthWithYear((String) obj[0]);
						Date d=(Date) obj[1];
						details.setDateWithYear(format.format(d));
						details.setZoneName((String)obj[2]);
						objectToBeShowed.add(details);
					}
	
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
	public int getUserCount() {
		
		Session session = initiateSession();
		
		try {
			
			String sql = "SELECT count(*) FROM user_details";
			Query query = session.createSQLQuery(sql);
			BigInteger count = (BigInteger)query.uniqueResult();
			
			return count.intValue();
			
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return -1;
		} finally {
			destroySession(session);
		}
	}

}
