package com.fieldmi.daoImpl;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMAttendanceDao;
import com.fieldmi.stubs.WsUserDashboardDetails;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.S3Operations;
import com.softtantra.salesapp.pojo.AttendanceRegister;
import com.softtantra.salesapp.pojo.CompanyLocations;
import com.softtantra.salesapp.pojo.DivisionMaster;
import com.softtantra.salesapp.pojo.LeaveDetails;
import com.softtantra.salesapp.pojo.Role;
import com.softtantra.salesapp.pojo.ShiftList;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.ZoneMaster;
import com.softtantra.ws.UserAttendanceReportForToday;
import com.softtantra.ws.WsAttendanceResgister;

public class FMAttendanceDaoImpl implements FMAttendanceDao {

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

	public UserAttendanceReportForToday getAttendanceReportPieChartData(int c_id, int country_id, int state, int city,
			int user, String from_date, String to_date, String sqluserlist) {

		Session session = initiateSession();
		try {
			long total, present, on_duty, paid_leave, unpaid_leave, absent, holiday, weekly_off = 0;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date newdate = new Date();
			String today = sdf.format(newdate);

			Query query = session
					.createQuery("SELECT count(distinct ud.user_id) FROM  User as ud   where ud.company_id=" + c_id
							+ " and ud.status=1");
			total = (long) query.uniqueResult();

			Query query1 = session
					.createQuery("select count(*) from AttendanceRegister where status=1 and Date(out_time)='" + today
							+ "' and company_id=" + c_id);
			present = (long) query1.uniqueResult();

			/*
			 * Query query2 = session.
			 * createQuery("select count(*) from AttendanceDetails where status=1 and Date(inout_time)='"
			 * +today+"' and inout_type=1 and company_id="+c_id); on_duty=(long)
			 * query2.uniqueResult();
			 */

			Query query2 = session.createSQLQuery(
					"SELECT count1.a-count2.b as cnt  from ((SELECT count(distinct user_id) as a  FROM attendance_register where company_id="
							+ c_id + " and cast(in_time as date)='" + today + "') as count1 ,"
							+ "(SELECT count(distinct user_id) as b  FROM attendance_register  where company_id=" + c_id
							+ " and cast(out_time as date)='" + today + "') as count2)");
			BigInteger quer2Result = ((BigInteger) query2.uniqueResult());
			on_duty = quer2Result.longValue();
			/*
			 * Query query3 = session.
			 * createQuery("select count(*) from AttendanceDetails where status=1 and Date(inout_time)=null and inout_type=0 and company_id="
			 * +c_id); absent=(long) query3.uniqueResult();
			 */

			Query query4 = session.createQuery("select count(*) from LeaveDetails where status=1 and Date(to_date)>='"
					+ today + "' and approval_status=2 and company_id=" + c_id);
			paid_leave = (long) query4.uniqueResult();

			Query query5 = session.createQuery("select count(*) from LeaveDetails where status=1 and Date(to_date)>='"
					+ today + "' and approval_status=1 and company_id=" + c_id);
			unpaid_leave = (long) query5.uniqueResult();

			Query query6 = session.createQuery(
					"SELECT count(user_id) from User u JOIN HolidayDetails h on u.state=h.state_id WHERE h.holidayDate='"
							+ today + "' and u.status=1 and h.status=1 and u.company_id=" + c_id);
			holiday = (long) query6.uniqueResult();

			Calendar calendar = Calendar.getInstance();
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

			Query query7 = session.createQuery(
					"select count(u.user_id) from User u JOIN SM_UserWeekOfDetails w on u.user_id=w.user_id WHERE w.weekoff_id="
							+ dayOfWeek + "  and u.company_id=" + c_id);
			weekly_off = (long) query7.uniqueResult();

			absent = total - (present + paid_leave + on_duty + weekly_off);

			UserAttendanceReportForToday details = new UserAttendanceReportForToday();
			details.setTotal_users(total);
			details.setPresent(present);
			details.setOn_duty(on_duty);
			details.setAbsent(absent);
			details.setPaid_leave(paid_leave);
			details.setUnpaid_leave(unpaid_leave);
			details.setHolidays(holiday);
			details.setWeekly_off(weekly_off);

			return details;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public HashMap<String, Object> getAttendanceForCompany(Date from_date, Date to_date, int c_id, int pageNumber,
			int pageSize, boolean isSummary,String country,String state,String city,String divId) {

		Session session = initiateSession();
		try {

			String sqlCountry="";
			String sqlState="";
			String sqlCity="";
			String sqlDiv="";
			if(country!=null && !"".equals(country)) {
				sqlCountry = " and country_id=" + country + " ";
			}
			if(state!=null && !"".equals(state)) {
				sqlState = " and new_state=" + state + " ";
			}
			if(city!=null && !"".equals(city)) {
				sqlCity = " and new_city=" + city + " ";
			}
			if(divId!=null && !"".equals(divId)) {
				sqlDiv = " and division_id=" + divId + " ";
			}
			HashMap<String, Object> result = new HashMap<String, Object>();

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf.format(from_date);
			String toDate = sdf.format(to_date);

			HashMap<String, String> idVsRole = getCompanyRoles(session, c_id);
			HashMap<String, String> idVsCompanyLocation = getCompanyLocations(session, c_id);
			HashMap<String, String> idVsDivisionName=getDivisionNames(session,c_id);
			HashMap<String, String> idVsZoneName=getZoneNames(session,c_id);
			StringBuilder userid = null;

			Query countQuery = session.createQuery(
					"select user_id from User where company_id=" + c_id + sqlCountry+ sqlState+sqlCity+sqlDiv+" and status=1 order by first_name asc");
			List<Integer> user_ids = countQuery.list();

			long total = user_ids.size();
			long afterSearch = total;
			userid = getUserIdViaPagination(pageSize, pageNumber, user_ids, 0);
			if (userid == null || userid.length() == 0) {

				result.put("data", null);
				result.put("iTotalRecords", total);
				result.put("iTotalDisplayRecords", afterSearch);
				return result;
			}

			return getCalendarDataForUsers(session, fromDate, toDate, idVsRole, idVsCompanyLocation, userid, c_id,
					isSummary, total, afterSearch,idVsDivisionName, idVsZoneName);

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public HashMap<String, Object> getAttendanceForUser(Date from_date, Date to_date, int c_id, int u_id,
			int pageNumber, int pageSize, boolean isSummary) {

		Session session = initiateSession();
		try {

			long total = 1;
			int afterSearch = 1;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf.format(from_date);
			String toDate = sdf.format(to_date);

			HashMap<String, String> idVsRole = getCompanyRoles(session, c_id);
			HashMap<String, String> idVsCompanyLocation = getCompanyLocations(session, c_id);
			HashMap<String, String> idVsDivisionName=getDivisionNames(session, c_id);
			HashMap<String, String> idVsZoneName=getZoneNames(session,c_id);
			StringBuilder userid = new StringBuilder("" + u_id);
			return getCalendarDataForUsers(session, fromDate, toDate, idVsRole, idVsCompanyLocation, userid, c_id,
					isSummary, total, afterSearch,idVsDivisionName, idVsZoneName);

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public HashMap<String, Object> getAttendanceForReportees(Date from_date, Date to_date, int c_id, int u_id,
			int pageNumber, int pageSize, boolean isSummary,String country,String state,String city,String divId) {

		Session session = initiateSession();
		try {

			HashMap<String, Object> result = new HashMap<String, Object>();
			CommonMethods commonMethods = new CommonMethods();
			List<Integer> user_ids = commonMethods.getUserHirachyWise(c_id, u_id, session,country,state,city,divId);

			long total = user_ids.size();
			long afterSearch = total;
			StringBuilder userid = getUserIdViaPagination(pageSize, pageNumber, user_ids, u_id);
			if (userid == null || userid.length() == 0) {

				result.put("data", null);
				result.put("iTotalRecords", total);
				result.put("iTotalDisplayRecords", afterSearch);
				return result;
			}

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf.format(from_date);
			String toDate = sdf.format(to_date);

			HashMap<String, String> idVsRole = getCompanyRoles(session, c_id);
			HashMap<String, String> idVsCompanyLocation = getCompanyLocations(session, c_id);
			HashMap<String, String> idVsDivisionName=getDivisionNames(session, c_id);
			HashMap<String, String> idVsZoneName=getZoneNames(session,c_id);
			return getCalendarDataForUsers(session, fromDate, toDate, idVsRole, idVsCompanyLocation, userid, c_id,
					isSummary, total, afterSearch,idVsDivisionName, idVsZoneName);

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<Date> getCalendarDaysForMonth(Date fromDate, Date toDate) {

		Session session = initiateSession();
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Query datesQuery = session
					.createSQLQuery("select fmc.db_date from fm_calendar fmc where fmc.db_date between '"
							+ sdf.format(fromDate) + "' and '" + sdf.format(toDate) + "'");

			return datesQuery.list();
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	private HashMap<String, Object> getCalendarDataForUsers(Session session, String fromDate, String toDate,
			HashMap<String, String> idVsRole, HashMap<String, String> idVsCompanyLocation, StringBuilder userid,
			int c_id, boolean isSummary, long total, long afterSearch,HashMap<String, String> idVsDivisionName, HashMap<String, String> idVsZoneName) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		Query dataQuery = session.createSQLQuery(
				"select aid.user_id, aid.user_name, aid.db_date, IFNULL(atd.in_time, 'A'), aid.day_name, aid.new_state,aid.mobile_no,aid.updated_date,atd.in_time,atd.out_time,atd.in_location_name,atd.out_location_name,atd.km_travelled,aid.role_id,atd.in_nearest_company_location,atd.out_nearest_company_location,aid.division_id,aid.country_id,aid.new_city,aid.employee_code,aid.zone_id from (select CONCAT(ud.first_name, ' ', ud.last_name) as user_name, ud.user_id,fmc.db_date, CONCAT(ud.user_id, '-', fmc.db_date) as unique_clm, fmc.day_name,ud.new_state,ud.mobile_no,ud.updated_date,ud.role_id,ud.division_id,ud.country_id,ud.new_city,ud.employee_code,ud.zone_id from user_details ud, fm_calendar fmc where fmc.db_date between '"
						+ fromDate + "' and '" + toDate + "' and ud.company_id=" + c_id + " and ud.user_id in ("
						+ userid + ")"
						+ " ) aid LEFT JOIN attendance_register atd ON atd.company_id="
						+ c_id + " and Date(atd.in_time) between '"+ fromDate + "' and '" + toDate + "' and atd.userid_intime=unique_clm ORDER BY aid.user_name,aid.db_date asc");

		List dataList = dataQuery.list();

		Query weeklyOffQuery = session.createSQLQuery(
				"select CONCAT(ud.user_id, ':', wd.days_name) from user_details ud INNER JOIN sm_user_weekof_details wo ON ud.user_id=wo.user_id INNER JOIN sm_days_master wd ON wo.weekoff_id=wd.sm_days_master_id where ud.company_id="
						+ c_id + " and ud.user_id in (" + userid + ")");
		List weeklyOffList = weeklyOffQuery.list();

		Query holdayOffQuery = session.createSQLQuery(
				"select CONCAT(mh.state_id,':',DATE(mh.holidayDate)) from manage_holiday mh where mh.state_id in (select distinct(ud.new_state) as stateid from user_details ud where ud.company_id="
						+ c_id + ") order by mh.state_id ASC");
		List holdayOffList = holdayOffQuery.list();

		Query leaveQuery = session.createQuery("FROM LeaveDetails ld where company_id=" + c_id + " and user_id in ("
				+ userid + ") and ( DATE(from_date) BETWEEN '" + fromDate + "' and '" + toDate
				+ "' OR DATE(to_date) BETWEEN '" + fromDate + "' and '" + toDate + "') and approval_status in (1,2)");
		List<LeaveDetails> leaveOffList = leaveQuery.list();
		List<HashMap<String,StringBuilder>> userVsLeave = parseLeaveData(leaveOffList);

		if (isSummary) {

			LinkedHashMap<String, WsAttendanceResgister> attendanceSummaryMap = parseAttendanceSummaryData(dataList,
					weeklyOffList, holdayOffList, userVsLeave, idVsRole, idVsCompanyLocation, idVsZoneName);
			result.put("data", attendanceSummaryMap);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", afterSearch);
		} else {

			LinkedHashMap<String, List<WsAttendanceResgister>> attendanceRegisterList = parseAttendanceData(dataList,
					weeklyOffList, holdayOffList, userVsLeave, idVsRole, idVsCompanyLocation, idVsDivisionName, idVsZoneName);
			result.put("data", attendanceRegisterList);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", afterSearch);
		}

		return result;
	}

	private StringBuilder getUserIdViaPagination(int pageSize, int pageNumber, List<Integer> user_ids, int u_id) {

		// pagination logic
		StringBuilder userid = new StringBuilder();
		int start = 0;
		int endSize = 1;
		if (pageSize == -1) {

			start = 0;
			endSize = user_ids.size();
		} else {

			start = pageSize * (Math.abs(pageNumber - 1));
			endSize = start + pageSize;
		}

		if (start >= user_ids.size()) {

			return userid;
		}

		if (endSize >= user_ids.size())
			endSize = user_ids.size();

		int cnt = 0;

		while (start < endSize) {

			if (cnt > 0)
				userid.append(",");
			userid.append(user_ids.get(start).intValue());
			start++;
			cnt++;
		}

		if (user_ids.size() == 0 && u_id != 0)
			userid.append(u_id);

		return userid;
	}

	private HashMap<String, String> getCompanyRoles(Session session, int c_id) {

		HashMap<String, String> idVsRoleName = new HashMap<String, String>();

		String roleQuery = "From Role where company_id=" + c_id;
		List<Role> roleList = session.createQuery(roleQuery).list();
		for (Role role : roleList) {

			idVsRoleName.put("" + role.getId(), role.getName());
		}
		return idVsRoleName;
	}
	private HashMap<String, String> getDivisionNames(Session session, int c_id) {

		HashMap<String, String> idVsDivisionName = new HashMap<String, String>();

		String divisionQuery = "From DivisionMaster where company_id=" + c_id;
		List<DivisionMaster> divisionList = session.createQuery(divisionQuery).list();
		for (DivisionMaster division : divisionList) {

			idVsDivisionName.put("" + division.getDivId(), division.getDivName());
		}
		return idVsDivisionName;
	}
	
	private HashMap<String, String> getZoneNames(Session session, int c_id) {

		HashMap<String, String> idVsZoneName = new HashMap<String, String>();

		String zoneQuery = "From ZoneMaster where company_id=" + c_id+" and status=1";
		List<ZoneMaster> zoneList = session.createQuery(zoneQuery).list();
		for (ZoneMaster zone : zoneList) {

			idVsZoneName.put("" + zone.getzId(), zone.getZoneName());
		}
		return idVsZoneName;
	}
	
	private String getCountryName(int country_id) {
		Session session = initiateSession();
		try {

			Query query = session.createQuery("select country_name from Country where country_id=" + country_id + "");
			
			return (String) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			destroySession(session);
		}
	}
	
	private String getStateName(int stateId) {
		Session session = initiateSession();
		try {

			Query query = session.createSQLQuery("select state_name from states where state_id=" + stateId);
			// FieldMILogger.debug(this.getClass().getName(),query);
			String name = (String) query.uniqueResult();
			return name;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}
	private String getCityName(int cityId) {

		Session session = initiateSession();
		try {

			Query query = session.createSQLQuery("select city_name from cities where city_id=" + cityId);
			// FieldMILogger.debug(this.getClass().getName(),query);
			String name = (String) query.uniqueResult();
			return name;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}


	private HashMap<String, String> getCompanyLocations(Session session, int c_id) {

		HashMap<String, String> idVsCompanyLocation = new HashMap<String, String>();

		String locationQuery = "From CompanyLocations where company_id=" + c_id;
		List<CompanyLocations> locationList = session.createQuery(locationQuery).list();
		for (CompanyLocations location : locationList) {

			idVsCompanyLocation.put("" + location.getCompanyLocationId(), location.getCompanyLocationName());
		}
		return idVsCompanyLocation;
	}

	private List<HashMap<String,StringBuilder>> parseLeaveData(List<LeaveDetails> leaveOffList) {

		List<HashMap<String,StringBuilder>> listHasmap=new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (LeaveDetails leaveDetails : leaveOffList) {
			HashMap<String, StringBuilder> userVsLeave = new HashMap<String, StringBuilder>();
			userVsLeave.put("" + leaveDetails.getUser_id(), new StringBuilder());
			Date fromDate = leaveDetails.getFrom_date();
			Calendar c = Calendar.getInstance();
			c.setTime(fromDate);
			int count = 0;
			StringBuilder leave = userVsLeave.get("" + leaveDetails.getUser_id());
			while (count < leaveDetails.getLeave_count()) {

				// TODO form the string as date:isPaid eg: 2020-07-14:P
				if (count == 0)
					leave.append(sdf.format(c.getTime()));
				else
					leave.append(",").append(sdf.format(c.getTime()));

				c.add(Calendar.DATE, 1);
				count++;
			}
			leave.append(" | " + leaveDetails.getLeave_type());		
			listHasmap.add(userVsLeave);
		}
		return listHasmap;
	}

	private LinkedHashMap<String, List<WsAttendanceResgister>> parseAttendanceData(List dataList, List weeklyOffList,
			List holdayOffList, List<HashMap<String,StringBuilder>> userVsLeave, HashMap<String, String> idVsRole,
			HashMap<String, String> idVsCompanyLocation,HashMap<String, String> idVsDivisionName,  HashMap<String, String> idVsZoneName) {

		LinkedHashMap<String, List<WsAttendanceResgister>> userAttendanceSummaryMap = new LinkedHashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date =new Date();
		String d=sdf.format(date);
		Date d1 =null;
		try {
			d1 = sdf.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object attdObj : dataList) {

			Object[] attdObjArr = (Object[]) attdObj;
			int user_id = (Integer) attdObjArr[0];
			List<WsAttendanceResgister> attendanceRegisterList = null;
			if (userAttendanceSummaryMap.containsKey("" + user_id)) {

				attendanceRegisterList = (List<WsAttendanceResgister>) userAttendanceSummaryMap.get("" + user_id);
			} else {

				attendanceRegisterList = new ArrayList<>();
				userAttendanceSummaryMap.put("" + user_id, attendanceRegisterList);
			}

			WsAttendanceResgister wsAttendanceRegister = new WsAttendanceResgister();
			wsAttendanceRegister.setUser_id((Integer) attdObjArr[0]);
			wsAttendanceRegister.setUser_name(attdObjArr[1].toString());
			wsAttendanceRegister.setAttendace_date((Date) attdObjArr[2]);
			if((Date) attdObjArr[2]!=null &&  d1.before((Date) attdObjArr[2])) {
				wsAttendanceRegister.setAttendance("-");
			}else {
				wsAttendanceRegister.setAttendance(attdObjArr[3].toString().equals("A") ? "A" : "P");
			}
			
			wsAttendanceRegister.setDayOfTheWeek(attdObjArr[4].toString());
			wsAttendanceRegister.setUser_state_id((Integer) attdObjArr[5]);
			wsAttendanceRegister.setMobile_no((attdObjArr[6]) != null ? ((String) attdObjArr[6]) : "N.A");
			wsAttendanceRegister.setUpdate_date((attdObjArr[7]) != null ? ((Date) attdObjArr[7]) : null);
			wsAttendanceRegister.setIn_time((attdObjArr[8]) != null ? ((Date) attdObjArr[8]) : null);
			wsAttendanceRegister.setOut_time((attdObjArr[9]) != null ? ((Date) attdObjArr[9]) : null);
			wsAttendanceRegister.setIn_location((attdObjArr[10]) != null ? ((String) attdObjArr[10]) : "N.A");
			wsAttendanceRegister.setOut_location((attdObjArr[11]) != null ? ((String) attdObjArr[11]) : "N.A");
			wsAttendanceRegister.setKm_travelled((attdObjArr[12]) != null ? ((Double) attdObjArr[12]) : 0d);
			wsAttendanceRegister.setRole_name(idVsRole.get(("" + attdObjArr[13])));
			wsAttendanceRegister.setIn_nearest_company_location((attdObjArr[14]) != null ? ((int) attdObjArr[14]) : 0);
			wsAttendanceRegister.setIn_nearest_company_location_name(
					(attdObjArr[14]) != null ? idVsCompanyLocation.get(("" + attdObjArr[14])) : "N.A");
			wsAttendanceRegister.setOut_nearest_company_location((attdObjArr[15]) != null ? ((int) attdObjArr[15]) : 0);
			wsAttendanceRegister.setOut_nearest_company_location_name(
					(attdObjArr[15]) != null ? idVsCompanyLocation.get(("" + attdObjArr[15])) : "N.A");
			
			wsAttendanceRegister.setDivisionName(
					(attdObjArr[16]) != null ? idVsDivisionName.get(("" + attdObjArr[16])) : "No Div Assigned");
			wsAttendanceRegister.setZoneName(
					(attdObjArr[20]) != null ? idVsZoneName.get(("" + attdObjArr[20])) : "No Zone Assigned");
			String countryName=getCountryName((Integer)attdObjArr[17]);
			if(countryName!=null) {
				wsAttendanceRegister.setCountryName(countryName);
			}else {
				wsAttendanceRegister.setCountryName("");
			}
			String stateName=getStateName((Integer)attdObjArr[5]);
			if(stateName!=null) {
				wsAttendanceRegister.setStateName(stateName);
			}else {
				wsAttendanceRegister.setStateName("");
			}
			String cityName=getCityName((Integer)attdObjArr[18]);
			if(stateName!=null) {
				wsAttendanceRegister.setCityName(cityName);
			}else {
				wsAttendanceRegister.setCityName("");
			}
			wsAttendanceRegister.setEmployeeCode((attdObjArr[19]) != null ? ((String) attdObjArr[19]) : "");
			
			if (wsAttendanceRegister.getAttendance().equals("A") || wsAttendanceRegister.getAttendance().equals("-")) {

				String holidayKey = wsAttendanceRegister.getUser_state_id() + ":"
						+ sdf.format(wsAttendanceRegister.getAttendace_date());
				String woKey = wsAttendanceRegister.getUser_id() + ":" + wsAttendanceRegister.getDayOfTheWeek();
				List<String> leaveKey = new ArrayList<String>();
				for(int i=0;i<userVsLeave.size();i++) {
					if (userVsLeave.get(i).containsKey("" + wsAttendanceRegister.getUser_id())) {

						leaveKey.add(userVsLeave.get(i).get("" + wsAttendanceRegister.getUser_id()).toString());
					}
				}
				if (holdayOffList.contains(holidayKey)) {

					wsAttendanceRegister.setAttendance("H");
				} else if (weeklyOffList.contains(woKey)) {

					wsAttendanceRegister.setAttendance("WO");
				} else if (leaveKey != null) {
					for(int i=0;i<leaveKey.size();i++) {
						if(leaveKey.get(i).contains(sdf.format(wsAttendanceRegister.getAttendace_date()).trim())) {
							wsAttendanceRegister.setAttendance("L" + " -" + leaveKey.get(i).split(Pattern.quote("|"))[1]);
						}
					}
					
					
				}
			}

			attendanceRegisterList.add(wsAttendanceRegister);
		}
		return userAttendanceSummaryMap;
	}

	private LinkedHashMap<String, WsAttendanceResgister> parseAttendanceSummaryData(List dataList, List weeklyOffList,
			List holdayOffList, List<HashMap<String,StringBuilder>> userVsLeave, HashMap<String, String> idVsRole,
			HashMap<String, String> idVsCompanyLocation,  HashMap<String, String> idVsZoneName) {

		LinkedHashMap<String, WsAttendanceResgister> userAttendanceSummaryMap = new LinkedHashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Object attdObj : dataList) {

			Object[] attdObjArr = (Object[]) attdObj;

			WsAttendanceResgister wsAttendanceRegister = null;
			int user_id = (Integer) attdObjArr[0];
			if (userAttendanceSummaryMap.containsKey("" + user_id)) {

				wsAttendanceRegister = userAttendanceSummaryMap.get("" + user_id);
			} else {

				wsAttendanceRegister = new WsAttendanceResgister();
				userAttendanceSummaryMap.put("" + user_id, wsAttendanceRegister);
			}
			wsAttendanceRegister.setUser_id((Integer) attdObjArr[0]);
			wsAttendanceRegister.setUser_name(attdObjArr[1].toString());
			wsAttendanceRegister.setAttendace_date((Date) attdObjArr[2]);
			wsAttendanceRegister.setAttendance(attdObjArr[3].toString().equals("A") ? "A" : "P");
			wsAttendanceRegister.setDayOfTheWeek(attdObjArr[4].toString());
			wsAttendanceRegister.setUser_state_id((Integer) attdObjArr[5]);
			wsAttendanceRegister.setMobile_no((attdObjArr[6]) != null ? ((String) attdObjArr[6]) : "N.A");
			wsAttendanceRegister.setIn_time((attdObjArr[8]) != null ? ((Date) attdObjArr[8]) : null);
			wsAttendanceRegister.setOut_time((attdObjArr[9]) != null ? ((Date) attdObjArr[9]) : null);
			// TODO average working hours for a month
			// per user total working hours divided by total present days
			wsAttendanceRegister.setRole_name(idVsRole.get(("" + attdObjArr[13])));
			wsAttendanceRegister.setZoneName(
					(attdObjArr[20]) != null ? idVsZoneName.get(("" + attdObjArr[20])) : "No Zone Assigned");
			wsAttendanceRegister.setTotalWorkingDays((wsAttendanceRegister.getTotalWorkingDays() + 1));
			wsAttendanceRegister.setTotalPresentDays((wsAttendanceRegister.getTotalPresentDays() + 1));
			if (wsAttendanceRegister.getAttendance().equals("A")) {

				String holidayKey = wsAttendanceRegister.getUser_state_id() + ":"
						+ sdf.format(wsAttendanceRegister.getAttendace_date());
				String woKey = wsAttendanceRegister.getUser_id() + ":" + wsAttendanceRegister.getDayOfTheWeek();
				String leaveKey = null;
				for(int i=0;i<userVsLeave.size();i++) {
					if (userVsLeave.get(i).containsKey("" + wsAttendanceRegister.getUser_id())) {

						leaveKey = userVsLeave.get(i).get("" + wsAttendanceRegister.getUser_id()).toString();
					}

				}

				if (holdayOffList.contains(holidayKey)) {

					wsAttendanceRegister.setAttendance("H");
					wsAttendanceRegister.setTotalWorkingDays((wsAttendanceRegister.getTotalWorkingDays() - 1));
					wsAttendanceRegister.setTotalHolidays((wsAttendanceRegister.getTotalHolidays() + 1));
					wsAttendanceRegister.setTotalPresentDays((wsAttendanceRegister.getTotalPresentDays() - 1));
				} else if (weeklyOffList.contains(woKey)) {

					wsAttendanceRegister.setAttendance("WO");
					wsAttendanceRegister.setTotalWorkingDays((wsAttendanceRegister.getTotalWorkingDays() - 1));
					wsAttendanceRegister.setTotalWeeklyOff((wsAttendanceRegister.getTotalWeeklyOff() + 1));
					wsAttendanceRegister.setTotalPresentDays((wsAttendanceRegister.getTotalPresentDays() - 1));
				} else if (leaveKey != null
						&& leaveKey.contains(sdf.format(wsAttendanceRegister.getAttendace_date()))) {

					wsAttendanceRegister.setAttendance("L" + " -" + leaveKey.split(Pattern.quote("|"))[1]);
					// TODO calculate paid and non paid leave separately
					wsAttendanceRegister.setTotalPaidLeaves((wsAttendanceRegister.getTotalPaidLeaves() + 1));

					wsAttendanceRegister.setTotalPresentDays((wsAttendanceRegister.getTotalPresentDays() - 1));
				} else {

					wsAttendanceRegister.setTotalAbsentDays((wsAttendanceRegister.getTotalAbsentDays() + 1));
					wsAttendanceRegister.setTotalPresentDays((wsAttendanceRegister.getTotalPresentDays() - 1));
				}
			}

		}
		return userAttendanceSummaryMap;
	}

	@Override
	public HashMap<String, Object> getDayWiseAttendanceDetailsForCompany(Date from_date, Date to_date, int c_id,
			int pageNumber, int pageSize,String country,String state,String city,String divId) {
		//divId is for divison wise filter,if we select particular divsion then users of that division data will be displayed
		Session session = initiateSession();
		try {

			HashMap<String, Object> result = new HashMap<String, Object>();
			long total = 0;
			int afterSearch = 0;
			String sqlCountry="";
			String sqlState="";
			String sqlCity="";
			String sqlDiv="";
			if(country!=null && !"".equals(country)) {
				sqlCountry = " and country_id=" + country + " ";
			}
			if(state!=null && !"".equals(state)) {
				sqlState = " and new_state=" + state + " ";
			}
			if(city!=null && !"".equals(city)) {
				sqlCity = " and new_city=" + city + " ";
			}
			if(divId!=null && !"".equals(divId)) {
				sqlDiv=" and division_id="+divId+" ";
			}
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf.format(from_date);
			String toDate = sdf.format(to_date);

			HashMap<String, String> idVsRole = getCompanyRoles(session, c_id);
			HashMap<String, String> idVsCompanyLocation = getCompanyLocations(session, c_id);
			HashMap<String, String> idVsDivisionName=getDivisionNames(session,c_id);
			HashMap<String, String> idVsZoneName=getZoneNames(session,c_id);

			Query userIdQuery = session.createQuery(
					"select user_id from User where company_id=" + c_id + sqlCountry+ sqlState+sqlCity+sqlDiv+" and status=1 order by first_name asc");
			List<Integer> user_ids = userIdQuery.list();
			if(user_ids.size()>0) {
				
			
			
			StringBuilder userid = getUserIdViaPagination(-1, 0, user_ids, 0);
			
			Query countQuery = session.createQuery("select count(*) FROM AttendanceRegister ar where ar.company_id="
					+ c_id + "  and DATE(ar.in_time) BETWEEN '" + fromDate + "' and '" + toDate + "' and ar.user_id in ("+userid.toString()+")");

			total = (long) countQuery.uniqueResult();

			Query dataQuery = session.createQuery(
					"FROM AttendanceRegister ar where ar.company_id=" + c_id + "  and DATE(ar.in_time) BETWEEN '"
							+ fromDate + "' and '" + toDate + "' and ar.user_id in ("+userid.toString()+")" + " ORDER BY ar.user_id,ar.in_time asc");

			List<AttendanceRegister> dataList = null;
			if (pageSize == -1) {

				dataList = dataQuery.list();
			} else {

				dataQuery = dataQuery.setFirstResult(pageSize * (pageNumber - 1));
				dataList = dataQuery.setMaxResults(pageSize).list();
			}
			afterSearch = dataList.size();

			HashMap<String, User> userIdVsName = new HashMap<String, User>();
			for (AttendanceRegister attendanceRegister : dataList) {

				if (userIdVsName.containsKey("" + attendanceRegister.getUser_id())) {

					User user = userIdVsName.get("" + attendanceRegister.getUser_id());
					String user_name = user.getFirst_name() + " " + user.getLast_name();
					attendanceRegister.setUser_name(user_name);
					attendanceRegister.setRole_name(idVsRole.get(("" + user.getRole_id())));
					attendanceRegister.setMobileNumber(user.getMobile_no());
					if(user.getDivision_id()!=0) {
						attendanceRegister.setDivisionName(idVsDivisionName.get((""+user.getDivision_id())));
					}else {
						attendanceRegister.setDivisionName("No Division Assigned");
					}
					String countryName=getCountryName(user.getCountry_id());
					if(countryName!=null && !countryName.equals("")) {
						attendanceRegister.setCountryName(countryName);
					}
					String stateName = getStateName(user.getNew_state());
					if(stateName!=null && !stateName.equals("")) {
						attendanceRegister.setStateName(stateName);
					}else {
						attendanceRegister.setStateName("");
					}
					String cityName = getCityName(user.getNew_city());
					if(cityName!=null && !cityName.equals("")) {
						attendanceRegister.setCityName(cityName);
					}else {
						attendanceRegister.setCityName("");
					}
					
					if(user.getEmployee_code()!=null && !user.getEmployee_code().equals("")) {
						attendanceRegister.setEmployeeCode(user.getEmployee_code());	
					}else {
						attendanceRegister.setEmployeeCode("");
					}
					if(user.getZone_id()!=0) {
						attendanceRegister.setZoneName(idVsZoneName.get((""+user.getZone_id())));
					}else {
						attendanceRegister.setZoneName("No Zone Assigned");
					}
					if(user.getEmail()!=null) {
						attendanceRegister.setEmail(user.getEmail());
					}else {
						attendanceRegister.setEmail("");
					}
					

				} else {

					Query userQuery = session
							.createQuery("FROM User ud where ud.user_id=" + attendanceRegister.getUser_id());
					User user = (User) userQuery.uniqueResult();

					attendanceRegister.setUser_name(user.getFirst_name() + " " + user.getLast_name());
					attendanceRegister.setRole_name(idVsRole.get(("" + user.getRole_id())));
					attendanceRegister.setMobileNumber(user.getMobile_no());
					if(user.getDivision_id()!=0) {
						attendanceRegister.setDivisionName(idVsDivisionName.get((""+user.getDivision_id())));
					}else {
						attendanceRegister.setDivisionName("No Division Assigned");
					}
					userIdVsName.put("" + attendanceRegister.getUser_id(), user);
					String countryName=getCountryName(user.getCountry_id());
					if(countryName!=null && !countryName.equals("")) {
						attendanceRegister.setCountryName(countryName);
					}
					String stateName = getStateName(user.getNew_state());
					if(stateName!=null && !stateName.equals("")) {
						attendanceRegister.setStateName(stateName);
					}else {
						attendanceRegister.setStateName("");
					}
					String cityName = getCityName(user.getNew_city());
					if(cityName!=null && !cityName.equals("")) {
						attendanceRegister.setCityName(cityName);
					}else {
						attendanceRegister.setCityName("");
					}
					if(user.getEmployee_code()!=null && !user.getEmployee_code().equals("")) {
						attendanceRegister.setEmployeeCode(user.getEmployee_code());	
					}else {
						attendanceRegister.setEmployeeCode("");
					}
					if(user.getZone_id()!=0) {
						attendanceRegister.setZoneName(idVsZoneName.get((""+user.getZone_id())));
					}else {
						attendanceRegister.setZoneName("No Zone Assigned");
					}
					if(user.getEmail()!=null) {
						attendanceRegister.setEmail(user.getEmail());
					}else {
						attendanceRegister.setEmail("");
					}
					
				}

				String inLocationName = idVsCompanyLocation
						.get(("" + attendanceRegister.getIn_nearest_company_location()));
				String outLocationName = idVsCompanyLocation
						.get(("" + attendanceRegister.getOut_nearest_company_location()));
				if (inLocationName != null)
					attendanceRegister.setIn_nearest_company_location_name(inLocationName);
				else
					attendanceRegister.setIn_nearest_company_location_name("N.A");

				if (outLocationName != null)
					attendanceRegister.setOut_nearest_company_location_name(outLocationName);
				else
					attendanceRegister.setOut_nearest_company_location_name("N.A");

			}

			result.put("iTotalRecords", afterSearch);
			result.put("iTotalDisplayRecords", total);
			result.put("data", dataList);
			return result;
			}else {
				return result;
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public HashMap<String, Object> getDayWiseAttendanceDetailsForUser(Date from_date, Date to_date, int c_id, int u_id,
			int pageNumber, int pageSize) {

		Session session = initiateSession();
		try {

			HashMap<String, Object> result = new HashMap<String, Object>();
			long total = 0;
			int afterSearch = 0;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf.format(from_date);
			String toDate = sdf.format(to_date);

			HashMap<String, String> idVsRole = getCompanyRoles(session, c_id);
			HashMap<String, String> idVsCompanyLocation = getCompanyLocations(session, c_id);
			HashMap<String, String> idVsDivisionName=getDivisionNames(session,c_id);
			HashMap<String, String> idVsZoneName=getZoneNames(session,c_id);
			Query countQuery = session.createQuery("select count(*) FROM AttendanceRegister ar where ar.company_id="
					+ c_id + "  and DATE(ar.in_time) BETWEEN '" + fromDate + "' and '" + toDate + "' and ar.user_id="
					+ u_id);

			total = (long) countQuery.uniqueResult();

			Query dataQuery = session.createQuery("FROM AttendanceRegister ar where ar.company_id=" + c_id
					+ "  and DATE(ar.in_time) BETWEEN '" + fromDate + "' and '" + toDate + "' and ar.user_id=" + u_id
					+ " ORDER BY ar.user_id,ar.in_time asc");

			List<AttendanceRegister> dataList = null;
			if (pageSize == -1) {

				dataList = dataQuery.list();
			} else {

				dataQuery = dataQuery.setFirstResult(pageSize * (pageNumber - 1));
				dataList = dataQuery.setMaxResults(pageSize).list();
			}
			afterSearch = dataList.size();

			Query userQuery = session.createQuery("FROM User ud where ud.user_id=" + u_id);
			User user = (User) userQuery.uniqueResult();
			for (AttendanceRegister attendanceRegister : dataList) {

				attendanceRegister.setUser_name(user.getFirst_name() + " " + user.getLast_name());
				attendanceRegister.setRole_name(idVsRole.get(("" + user.getRole_id())));
				attendanceRegister.setMobileNumber(user.getMobile_no());
				String inLocationName = idVsCompanyLocation
						.get(("" + attendanceRegister.getIn_nearest_company_location()));
				String outLocationName = idVsCompanyLocation
						.get(("" + attendanceRegister.getOut_nearest_company_location()));
				if (inLocationName != null)
					attendanceRegister.setIn_nearest_company_location_name(inLocationName);
				else
					attendanceRegister.setIn_nearest_company_location_name("N.A");

				if (outLocationName != null)
					attendanceRegister.setOut_nearest_company_location_name(outLocationName);
				else
					attendanceRegister.setOut_nearest_company_location_name("N.A");
				if(user.getDivision_id()!=0) {
					attendanceRegister.setDivisionName(idVsDivisionName.get((""+user.getDivision_id())));
				}else {
					attendanceRegister.setDivisionName("No Division Assigned");
				}
				String countryName=getCountryName(user.getCountry_id());
				if(countryName!=null && !countryName.equals("")) {
					attendanceRegister.setCountryName(countryName);
				}
				String stateName = getStateName(user.getNew_state());
				if(stateName!=null && !stateName.equals("")) {
					attendanceRegister.setStateName(stateName);
				}else {
					attendanceRegister.setStateName("");
				}
				String cityName = getCityName(user.getNew_city());
				if(cityName!=null && !cityName.equals("")) {
					attendanceRegister.setCityName(cityName);
				}else {
					attendanceRegister.setCityName("");
				}
				
				if(user.getEmployee_code()!=null && !user.getEmployee_code().equals("")) {
					attendanceRegister.setEmployeeCode(user.getEmployee_code());	
				}else {
					attendanceRegister.setEmployeeCode("");
				}
				if(user.getZone_id()!=0) {
					attendanceRegister.setZoneName(idVsZoneName.get((""+user.getZone_id())));
				}else {
					attendanceRegister.setZoneName("No Zone Assigned");
				}
				if(user.getEmail()!=null) {
					attendanceRegister.setEmail(user.getEmail());
				}else {
					attendanceRegister.setEmail("");
				}
			}

			result.put("iTotalRecords", afterSearch);
			result.put("iTotalDisplayRecords", total);
			result.put("data", dataList);
			return result;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public HashMap<String, Object> getDayWiseAttendanceDetailsForReportees(Date from_date, Date to_date, int c_id,
			int u_id, int pageNumber, int pageSize,String country,String state,String city,String divId) {

		
		//divId is for divison wise filter,if we select particular divsion then users of that division data will be displayed
		Session session = initiateSession();
		try {

			HashMap<String, Object> result = new HashMap<String, Object>();
			long total = 0;
			int afterSearch = 0;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = sdf.format(from_date);
			String toDate = sdf.format(to_date);

			CommonMethods commonMethods = new CommonMethods();
			List<Integer> user_ids = commonMethods.getUserHirachyWise(c_id, u_id, session,country,state,city,divId);
			StringBuilder userid = new StringBuilder();

			for (int i = 0; i < user_ids.size(); i++) {

				if (i > 0)
					userid.append(",");
				userid.append(user_ids.get(i).intValue());
			}

			if (user_ids.size() == 0)
				userid.append(u_id);

			HashMap<String, String> idVsRole = getCompanyRoles(session, c_id);
			HashMap<String, String> idVsCompanyLocation = getCompanyLocations(session, c_id);
			HashMap<String, String> idVsDivisionName=getDivisionNames(session,c_id);
			HashMap<String, String> idVsZoneName=getZoneNames(session,c_id);
			Query countQuery = session.createQuery("select count(*) FROM AttendanceRegister ar where ar.company_id="
					+ c_id + "  and DATE(ar.in_time) BETWEEN '" + fromDate + "' and '" + toDate
					+ "' and ar.user_id in (" + userid + ")");

			total = (long) countQuery.uniqueResult();

			Query dataQuery = session.createQuery("FROM AttendanceRegister ar where ar.company_id=" + c_id
					+ "  and DATE(ar.in_time) BETWEEN '" + fromDate + "' and '" + toDate + "' and ar.user_id in ("
					+ userid + ") ORDER BY ar.user_id,ar.in_time asc");

			List<AttendanceRegister> dataList = null;
			if (pageSize == -1) {

				dataList = dataQuery.list();
			} else {

				dataQuery = dataQuery.setFirstResult(pageSize * (pageNumber - 1));
				dataList = dataQuery.setMaxResults(pageSize).list();
			}
			afterSearch = dataList.size();

			HashMap<String, User> userIdVsName = new HashMap<String, User>();
			for (AttendanceRegister attendanceRegister : dataList) {

				if (userIdVsName.containsKey("" + attendanceRegister.getUser_id())) {

					User user = userIdVsName.get("" + attendanceRegister.getUser_id());
					String user_name = user.getFirst_name() + " " + user.getLast_name();
					attendanceRegister.setUser_name(user_name);
					attendanceRegister.setMobileNumber(user.getMobile_no());
					attendanceRegister.setRole_name(idVsRole.get(("" + user.getRole_id())));
					if(user.getDivision_id()!=0) {
						attendanceRegister.setDivisionName(idVsDivisionName.get((""+user.getDivision_id())));
					}else {
						attendanceRegister.setDivisionName("No Division Assigned");
					}
					String countryName=getCountryName(user.getCountry_id());
					if(countryName!=null && !countryName.equals("")) {
						attendanceRegister.setCountryName(countryName);
					}
					String stateName = getStateName(user.getNew_state());
					if(stateName!=null && !stateName.equals("")) {
						attendanceRegister.setStateName(stateName);
					}else {
						attendanceRegister.setStateName("");
					}
					String cityName = getCityName(user.getNew_city());
					if(cityName!=null && !cityName.equals("")) {
						attendanceRegister.setCityName(cityName);
					}else {
						attendanceRegister.setCityName("");
					}
					
					if(user.getEmployee_code()!=null && !user.getEmployee_code().equals("")) {
						attendanceRegister.setEmployeeCode(user.getEmployee_code());	
					}else {
						attendanceRegister.setEmployeeCode("");
					}
					if(user.getZone_id()!=0) {
						attendanceRegister.setZoneName(idVsZoneName.get((""+user.getZone_id())));
					}else {
						attendanceRegister.setZoneName("No Zone Assigned");
					}
					if(user.getEmail()!=null) {
						attendanceRegister.setEmail(user.getEmail());
					}else {
						attendanceRegister.setEmail("");
					}
				} else {

					Query userQuery = session
							.createQuery("From User ud where ud.user_id=" + attendanceRegister.getUser_id());
					User user = (User) userQuery.uniqueResult();

					attendanceRegister.setUser_name(user.getFirst_name() + " " + user.getLast_name());
					attendanceRegister.setRole_name(idVsRole.get(("" + user.getRole_id())));
					attendanceRegister.setMobileNumber(user.getMobile_no());
					if(user.getDivision_id()!=0) {
						attendanceRegister.setDivisionName(idVsDivisionName.get((""+user.getDivision_id())));
					}else {
						attendanceRegister.setDivisionName("No Division Assigned");
					}
					String countryName=getCountryName(user.getCountry_id());
					if(countryName!=null && !countryName.equals("")) {
						attendanceRegister.setCountryName(countryName);
					}
					String stateName = getStateName(user.getNew_state());
					if(stateName!=null && !stateName.equals("")) {
						attendanceRegister.setStateName(stateName);
					}else {
						attendanceRegister.setStateName("");
					}
					String cityName = getCityName(user.getNew_city());
					if(cityName!=null && !cityName.equals("")) {
						attendanceRegister.setCityName(cityName);
					}else {
						attendanceRegister.setCityName("");
					}
					
					if(user.getEmployee_code()!=null && !user.getEmployee_code().equals("")) {
						attendanceRegister.setEmployeeCode(user.getEmployee_code());	
					}else {
						attendanceRegister.setEmployeeCode("");
					}
					if(user.getZone_id()!=0) {
						attendanceRegister.setZoneName(idVsZoneName.get((""+user.getZone_id())));
					}else {
						attendanceRegister.setZoneName("No Zone Assigned");
					}
					if(user.getEmail()!=null) {
						attendanceRegister.setEmail(user.getEmail());
					}else {
						attendanceRegister.setEmail("");
					}
					userIdVsName.put("" + attendanceRegister.getUser_id(), user);
				}

				String inLocationName = idVsCompanyLocation
						.get(("" + attendanceRegister.getIn_nearest_company_location()));
				String outLocationName = idVsCompanyLocation
						.get(("" + attendanceRegister.getOut_nearest_company_location()));
				if (inLocationName != null)
					attendanceRegister.setIn_nearest_company_location_name(inLocationName);
				else
					attendanceRegister.setIn_nearest_company_location_name("N.A");

				if (outLocationName != null)
					attendanceRegister.setOut_nearest_company_location_name(outLocationName);
				else
					attendanceRegister.setOut_nearest_company_location_name("N.A");

			}

			result.put("iTotalRecords", afterSearch);
			result.put("iTotalDisplayRecords", total);
			result.put("data", dataList);
			return result;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public AttendanceRegister addAttendance(AttendanceRegister attendanceRegister, List<ShiftList> shiftList)
			throws Exception {

		Session session = initiateSession();
		try {

			AttendanceRegister attReg = null;
			Date inTime = attendanceRegister.getIn_time();
			int userId = attendanceRegister.getUser_id();
			String inShiftTime = "10:00";
			String outShiftTime = "19:00";
			if (userId != 0) {

				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				DateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdft.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

				// query if attendance record already exisiting
				Query<AttendanceRegister> attendanceQuery = session.createQuery("From AttendanceRegister where user_id="
						+ attendanceRegister.getUser_id() + " and company_id=" + attendanceRegister.getCompany_id()
						+ " and DATE(in_time) ='" + sdf.format(inTime) + "'", AttendanceRegister.class);
				AttendanceRegister attdObj = attendanceQuery.uniqueResult();
				String userUpdateQUery = null;

				// fetch user
				Query<User> userQuery = session.createQuery("from User where user_id=" + userId, User.class);
				User attUser = userQuery.uniqueResult();
				for (ShiftList shift : shiftList) {

					if (shift.getShift_id() == attUser.getShift_id() && shift.getIn_time() != null
							&& shift.getIn_time().length() > 0 && shift.getOut_time() != null
							&& shift.getOut_time().length() > 0) {

//						inShiftTime = Integer.parseInt(shift.getIn_time().split(":")[0]);
//						outShiftTime = Integer.parseInt(shift.getOut_time().split(":")[0]);
						inShiftTime = shift.getIn_time();
						outShiftTime = shift.getOut_time();
						break;
					}
				}

				if (attdObj == null) {

					if (attendanceRegister.getIn_image() != null && attendanceRegister.getIn_image().length > 0) {

						String inImagePath = uploadImageToPathAttendance(attendanceRegister.getCompany_id(),
								attendanceRegister.getIn_image(), userId, 1, sdf.format(inTime));
						attendanceRegister.setIn_selfieImage(inImagePath);
					}

					if (attendanceRegister.getOut_image() != null && attendanceRegister.getOut_image().length > 0) {

						String outImagePath = uploadImageToPathAttendance(attendanceRegister.getCompany_id(),
								attendanceRegister.getOut_image(), userId, 2,
								sdf.format(attendanceRegister.getOut_time()));
						attendanceRegister.setOut_selfieImage(outImagePath);
					}
					String inEntryTiming = getTimeDifferenceString(sdft.format(inTime), inShiftTime);
					attendanceRegister.setEntryTiming(inEntryTiming);
					if (attendanceRegister.getIn_time() != null && attendanceRegister.getOut_time() == null) {

						userUpdateQUery = "update User set lat='" + attendanceRegister.getIn_lat() + "', longi='"
								+ attendanceRegister.getIn_longi() + "', in_time='"
								+ sdft.format(attendanceRegister.getIn_time()) + "', km_travelled="
								+ attendanceRegister.getKm_travelled() + " where user_id="
								+ attendanceRegister.getUser_id();
					} else if (attendanceRegister.getIn_time() != null && attendanceRegister.getOut_time() != null) {

						userUpdateQUery = "update User set lat='" + attendanceRegister.getOut_lat() + "', longi='"
								+ attendanceRegister.getOut_longi() + "', in_time='"
								+ sdft.format(attendanceRegister.getIn_time()) + "', out_time='"
								+ sdft.format(attendanceRegister.getOut_time()) + "', km_travelled="
								+ attendanceRegister.getKm_travelled() + " where user_id="
								+ attendanceRegister.getUser_id();

						String outEntryTiming = getTimeDifferenceString(sdft.format(attendanceRegister.getOut_time()),
								outShiftTime);
						attendanceRegister.setExitTiming(outEntryTiming);
					}

					attendanceRegister.setUserid_intime(attendanceRegister.getUser_id() + "-" + sdf.format(inTime));
					session.save(attendanceRegister);
					attReg = attendanceRegister;

					if (userUpdateQUery != null) {
						Query updateQuery = session.createQuery(userUpdateQUery);
						updateQuery.executeUpdate();
					}
				} else if (attendanceRegister.getOut_time() != null
						&& ((AttendanceRegister) attdObj).getOut_time() == null) {

					attReg = (AttendanceRegister) attdObj;
					attReg.setOut_time(attendanceRegister.getOut_time());
					attReg.setOut_location_name(attendanceRegister.getOut_location_name());
					attReg.setOut_accuracy(attendanceRegister.getOut_accuracy());
					attReg.setOut_city(attendanceRegister.getOut_city());
					attReg.setOut_lat(attendanceRegister.getOut_lat());
					attReg.setOut_longi(attendanceRegister.getOut_longi());
					attReg.setOut_state(attendanceRegister.getOut_state());
					attReg.setOut_nearest_company_location(attendanceRegister.getOut_nearest_company_location());

					if (attendanceRegister.getOut_image() != null && attendanceRegister.getOut_image().length > 0) {

						String outImagePath = uploadImageToPathAttendance(attendanceRegister.getCompany_id(),
								attendanceRegister.getOut_image(), userId, 2,
								sdf.format(attendanceRegister.getOut_time()));
						attReg.setOut_selfieImage(outImagePath);
					}
					String exitTiming = getTimeDifferenceString(sdft.format(attendanceRegister.getOut_time()),
							outShiftTime);
					attReg.setExitTiming(exitTiming);
					if (attendanceRegister.getIn_time() != null && attendanceRegister.getOut_time() != null) {

						userUpdateQUery = "update User set lat='" + attendanceRegister.getOut_lat() + "', longi='"
								+ attendanceRegister.getOut_longi() + "', in_time='"
								+ sdft.format(attendanceRegister.getIn_time()) + "', out_time='"
								+ sdft.format(attendanceRegister.getOut_time()) + "', km_travelled="
								+ attendanceRegister.getKm_travelled() + " where user_id="
								+ attendanceRegister.getUser_id();
					}

					session.update(attReg);
					if (userUpdateQUery != null) {
						Query updateQuery = session.createQuery(userUpdateQUery);
						updateQuery.executeUpdate();
					}
				}
			}

			return attReg;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

	public String uploadImageToPathAttendance(int company_id, byte[] image, int user_id, int type, String date) {

		try {

			if (image != null && image.length != 0) {

				String bucketName = CommonMethods.getFileProperties().getProperty("bucketName");
				String objectKey = company_id + S3Operations.USER_ATTENDANCE_FOLDER + user_id + "_" + date + "_" + type
						+ ".png";

				S3Operations s3Operatons = CommonMethods.getS3OperationClient();
				s3Operatons.addFilesToS3(bucketName, objectKey, new ByteArrayInputStream(image), image.length);

				if (s3Operatons.checkFilesInS3(bucketName, objectKey)) {
					return objectKey;
				} else {

					FieldMILogger.warn(this.getClass().getName(), "File does not exists:" + objectKey);
					return "";
				}
			} else {

				FieldMILogger.warn(this.getClass().getName(), "Image is null/length is Zero");
				return "";
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		}
	}

	public String getTimeDifferenceString(String inout_time, String hour) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		try {
			FieldMILogger.info(FMAttendanceDaoImpl.class.getName(),
					"IN Time:"
							+ inout_time);
			FieldMILogger.info(FMAttendanceDaoImpl.class.getName(),
					"Shift Time: "
							+ hour);
			Date dbDate = format.parse(inout_time);
			cal.setTime(dbDate);
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.split(":")[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(hour.split(":")[1]));
			cal.set(Calendar.SECOND, 00);

			Date inShift = cal.getTime();

			long diff = dbDate.getTime() - inShift.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;

			if (diffHours == 0 && diffMinutes == 0 && diffSeconds == 0) {

				return "OnTime";
			} else if (diffHours < 0 || diffMinutes < 0 || diffSeconds < 0) {

				return Math.abs(diffHours) + ":" + Math.abs(diffMinutes) + ":" + Math.abs(diffSeconds) + " Early";
			} else {

				return Math.abs(diffHours) + ":" + Math.abs(diffMinutes) + ":" + Math.abs(diffSeconds) + " Late";
			}
		} catch (ParseException e) {

			e.printStackTrace();
			return "N.A";
		}
	}

	@Override
	public List<WsUserDashboardDetails> dailyTrendUserAttendanceData(int c_id) {
		Session session = initiateSession();
		List<WsUserDashboardDetails> list2 = new ArrayList<WsUserDashboardDetails>();
		try {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			String startDate = "", endDate="";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			startDate = df.format(c.getTime());
			endDate=df.format(new Date());
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yy");
			String sql = "SELECT count(attendance_id),in_time FROM `attendance_register` WHERE company_id="+c_id+" and status=1 and Date(in_time) BETWEEN '"+startDate+"' and '"+endDate+"' GROUP BY Date(in_time) ORDER BY Date(in_time)";
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsUserDashboardDetails details = new WsUserDashboardDetails();
				Date date=(Date) obj[1];
				String newDate=format.format(date);
				details.setDateWithYear((String) newDate);
				details.setTotalUsers((BigInteger) obj[0]);
				
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
	public List<WsUserDashboardDetails> monthlyTrendUserAttendanceData(int c_id, String start_month, String end_month) {
		Session session = initiateSession();
		List<WsUserDashboardDetails> list2 = new ArrayList<WsUserDashboardDetails>();
		try {
			String lastYearStartDate="";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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
           
            LocalDate bday = LocalDate.parse(lastYearStartDate);
            LocalDate today = LocalDate.now();
            Period age = Period.between(bday, today); 
            int months = Math.abs(age.getMonths()+1);

			String sql = "select months.year1 as year1,months.month1 as month1,concat(months.month1,' ',months.year1) AS monthq,ifnull(count(DISTINCT ud.user_id),0) AS totalUsers from (select MonthName( @curDate \\:= Date_SUB(@curDate, interval 1 MONTH)) as month1, Month( @curDate1 \\:= Date_SUB(@curDate1, interval 1 MONTH)) as month2, Year( @curDate2 \\:= Date_SUB(@curDate2, interval 1 MONTH)) as year1 from ( select @curDate \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate1 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month), @CurDate2 \\:= Date_Add(CURRENT_DATE, INTERVAL 1 month) ) sqlvars, attendance_register limit "+ months +") months LEFT JOIN attendance_register ud on months.month1=MonthName(ud.in_time) AND ud.company_id="
			+ c_id + " and ud.status=1 GROUP BY months.month1 order by months.year1, months.month2";
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				WsUserDashboardDetails details = new WsUserDashboardDetails();
				String s2=(String) obj[1];
				String s3=String.valueOf((int)obj[0]);
				details.setMonthWithYear(s2.substring(0, 3)+"-"+s3.substring(s3.length()-2));
				
				details.setTotalUsers((BigInteger) obj[3]);
				
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
	public List<UserAttendanceReportForToday> getSlotWiseLocationForCompany(int userId, String pageNo, String pageSize, int c_id,
			String fromDate, String toDate, int stateId) {

		Session session = initiateSession();
		try {

			String sqlCountry="";
			String sqlState="";
			String sqlCity="";
			String sqlDiv="";
			String slot1Loc="No Location Recived";
			String slot2Loc="No Location Recived";
			String slot3Loc="No Location Recived";
			String slot4Loc="No Location Recived";
			Query countQuery = session.createQuery(
					"select user_id from User where company_id=" + c_id + sqlCountry+ sqlState+sqlCity+sqlDiv+" and status=1 order by first_name asc");
			List<Integer> user_ids = countQuery.list();

			
			LocalDate start = LocalDate.parse(fromDate);
			LocalDate end = LocalDate.parse(toDate);
			List<LocalDate> totalDates = new ArrayList<>();
			
			while (!start.isAfter(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			}
			//List Object initialze
			List<UserAttendanceReportForToday> details1 = new ArrayList<UserAttendanceReportForToday>();
			
			if(userId==0) {
				for(int i=0;i<user_ids.size();i++) {
					
					for (int j=0;j<totalDates.size();j++) {
						

						
						String slot1Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+user_ids.get(i)+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 09:00:00"+"' and '"+totalDates.get(j).toString()+" 12:00:00"+"' limit 1";
						String slot2Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+user_ids.get(i)+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 12:01:00"+"' and '"+totalDates.get(j).toString()+" 15:00:00"+"' limit 1";
						String slot3Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+user_ids.get(i)+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 15:01:00"+"' and '"+totalDates.get(j).toString()+" 18:00:00"+"' limit 1";
						String slot4Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+user_ids.get(i)+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 18:01:00"+"' and '"+totalDates.get(j).toString()+" 21:00:00"+"' limit 1";
						
						UserAttendanceReportForToday details = new UserAttendanceReportForToday();
						Query userQuery = session.createQuery("from User where user_id=" + user_ids.get(i));
						User attUser = (User) userQuery.uniqueResult();
						
						Query rptUserQuery = session.createQuery("from User where user_id=" + attUser.getReporting_manager_id());
						User rptUser = (User) rptUserQuery.uniqueResult();
						details.setCurrent_battery_level(attUser.getFirst_name()+" "+attUser.getLast_name());
						if(rptUser!=null) {
							details.setLat(rptUser.getFirst_name()+" "+rptUser.getLast_name());
						}else {
							details.setLat("No Manager Assigned");
						}
						
						details.setLocation(totalDates.get(j).toString());
						Query query = session.createSQLQuery(slot1Query);
						slot1Loc = (String) query.uniqueResult();
						
						details.setIn_time(slot1Loc);
						
						Query query1 = session.createSQLQuery(slot2Query);
						slot2Loc = (String) query1.uniqueResult();
						
						details.setOut_time(slot2Loc);
						
						Query query2 = session.createSQLQuery(slot3Query);
						slot3Loc = (String) query2.uniqueResult();
						
						details.setKm_travelled(slot3Loc);
						
						Query query3 = session.createSQLQuery(slot4Query);
						slot4Loc = (String) query3.uniqueResult();
						
						details.setSpeed(slot4Loc);
						details1.add(details);
						
						
						
						
						
					}
					
				}

			}else {
				
					
					for (int j=0;j<totalDates.size();j++) {
						

						
						String slot1Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+userId+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 09:00:00"+"' and '"+totalDates.get(j).toString()+" 12:00:00"+"' limit 1";
						String slot2Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+userId+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 12:01:00"+"' and '"+totalDates.get(j).toString()+" 15:00:00"+"' limit 1";
						String slot3Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+userId+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 15:01:00"+"' and '"+totalDates.get(j).toString()+" 18:00:00"+"' limit 1";
						String slot4Query="SELECT address from user_location_details WHERE company_id="+c_id+" and user_id="+userId+" and update_date_time BETWEEN '"+totalDates.get(j).toString()+" 18:01:00"+"' and '"+totalDates.get(j).toString()+" 21:00:00"+"' limit 1";
						
						UserAttendanceReportForToday details = new UserAttendanceReportForToday();
						Query userQuery = session.createQuery("from User where user_id=" + userId);
						User attUser = (User) userQuery.uniqueResult();
						
						Query rptUserQuery = session.createQuery("from User where user_id=" + attUser.getReporting_manager_id());
						User rptUser = (User) rptUserQuery.uniqueResult();
						details.setCurrent_battery_level(attUser.getFirst_name()+" "+attUser.getLast_name());
						if(rptUser!=null) {
							details.setLat(rptUser.getFirst_name()+" "+rptUser.getLast_name());
						}else {
							details.setLat("No Manager Assigned");
						}
						
						details.setLocation(totalDates.get(j).toString());
						Query query = session.createSQLQuery(slot1Query);
						slot1Loc = (String) query.uniqueResult();
						
						details.setIn_time(slot1Loc);
						
						Query query1 = session.createSQLQuery(slot2Query);
						slot2Loc = (String) query1.uniqueResult();
						
						details.setOut_time(slot2Loc);
						
						Query query2 = session.createSQLQuery(slot3Query);
						slot3Loc = (String) query2.uniqueResult();
						
						details.setKm_travelled(slot3Loc);
						
						Query query3 = session.createSQLQuery(slot4Query);
						slot4Loc = (String) query3.uniqueResult();
						
						details.setSpeed(slot4Loc);
						details1.add(details);
						
						
						
						
						
					}
					
				

			}
			
			return details1;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {
			destroySession(session);
		}
	}

}
