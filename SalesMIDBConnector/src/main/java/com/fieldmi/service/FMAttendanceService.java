package com.fieldmi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fieldmi.stubs.WsUserDashboardDetails;
import com.softtantra.salesapp.pojo.AttendanceRegister;
import com.softtantra.salesapp.pojo.ShiftList;
import com.softtantra.ws.UserAttendanceReportForToday;

public interface FMAttendanceService {
	
	UserAttendanceReportForToday getAttendanceReportPieChartData(int c_id,int country_id,int state,int city,int user,String from_date,String to_date,String sqluserlist);

	HashMap<String, Object>  getAttendanceForCompany(Date from_date, Date to_date, int c_id, int pageNumber, int pageSize, boolean isSummary,String country,String state,String city,String divId);
	
	HashMap<String, Object>  getAttendanceForReportees(Date from_date, Date to_date, int c_id, int u_id, int pageNumber, int pageSize, boolean isSummary,String country,String state,String city,String divId);
	
	HashMap<String, Object>  getAttendanceForUser(Date from_date, Date to_date, int c_id, int u_id, int pageNumber, int pageSize, boolean isSummary);
	
	HashMap<String, Object>  getDayWiseAttendanceDetailsForCompany(Date from_date, Date to_date, int c_id, int pageNumber, int pageSize,String country,String state,String city,String divId);
	
	HashMap<String, Object>  getDayWiseAttendanceDetailsForReportees(Date from_date, Date to_date, int c_id, int u_id, int pageNumber, int pageSize,String country,String state,String city,String divId);
	
	HashMap<String, Object>  getDayWiseAttendanceDetailsForUser(Date from_date, Date to_date, int c_id, int u_id, int pageNumber, int pageSize);

	AttendanceRegister addAttendance(AttendanceRegister attendanceRegister, List<ShiftList> shiftList) throws Exception;
	
	List<Date> getCalendarDaysForMonth( Date fromDate, Date toDate );
	
	List<WsUserDashboardDetails> dailyTrendUserAttendanceData(int c_id);
	
	List<WsUserDashboardDetails> monthlyTrendUserAttendanceData(int c_id,String start_month, String end_month);
	
	List<UserAttendanceReportForToday> getSlotWiseLocationForCompany(int userId, String pageNo, String pageSize, int c_id, String fromDate, String toDate, int stateId);
}
