package com.fieldmi.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMAttendanceDao;
import com.fieldmi.service.FMAttendanceService;
import com.fieldmi.stubs.WsUserDashboardDetails;
import com.softtantra.salesapp.pojo.AttendanceRegister;
import com.softtantra.salesapp.pojo.ShiftList;
import com.softtantra.ws.UserAttendanceReportForToday;

public class FMAttendanceServiceImpl implements FMAttendanceService{
	
	@Autowired
	FMAttendanceDao fmAttendanceDao;
	
	public void setFmAttendanceDao(FMAttendanceDao fmAttendanceDao) {
		this.fmAttendanceDao = fmAttendanceDao;
	}

	@Override
	public UserAttendanceReportForToday getAttendanceReportPieChartData(int c_id, int country_id, int state, int city,
			int user, String from_date, String to_date,String sqluserlist) {
		
		return fmAttendanceDao.getAttendanceReportPieChartData(c_id,country_id,state,city,user,from_date,to_date,sqluserlist);
	}

	@Override
	public AttendanceRegister addAttendance(AttendanceRegister attendanceRegister, List<ShiftList> shiftList) throws Exception {
		
		return fmAttendanceDao.addAttendance(attendanceRegister,shiftList);
	}

	@Override
	public HashMap<String, Object> getDayWiseAttendanceDetailsForCompany(Date from_date, Date to_date, int c_id,
			int pageNumber, int pageSize,String country,String state,String city,String divId) {
		
		return fmAttendanceDao.getDayWiseAttendanceDetailsForCompany(from_date, to_date, c_id, pageNumber, pageSize,country,state,city,divId);
	}
	@Override
	public HashMap<String, Object> getDayWiseAttendanceDetailsForUser(Date from_date, Date to_date, int c_id, int u_id,
			int pageNumber, int pageSize) {
		
		return fmAttendanceDao.getDayWiseAttendanceDetailsForUser(from_date, to_date, c_id, u_id, pageNumber, pageSize);
	}
	
	@Override
	public HashMap<String, Object> getDayWiseAttendanceDetailsForReportees(Date from_date, Date to_date, int c_id,
			int u_id, int pageNumber, int pageSize,String country,String state,String city,String divId) {
		
		return fmAttendanceDao.getDayWiseAttendanceDetailsForReportees(from_date, to_date, c_id, u_id, pageNumber, pageSize,country,state,city,divId);
	}

	@Override
	public HashMap<String, Object> getAttendanceForCompany(Date from_date, Date to_date, int c_id, int pageNumber,
			int pageSize, boolean isSummary,String country,String state,String city,String divId) {
		
		return fmAttendanceDao.getAttendanceForCompany(from_date, to_date, c_id, pageNumber, pageSize, isSummary, country, state, city,divId);
	}

	@Override
	public HashMap<String, Object> getAttendanceForUser(Date from_date, Date to_date, int c_id, int u_id,
			int pageNumber, int pageSize, boolean isSummary) {
		
		return fmAttendanceDao.getAttendanceForUser(from_date, to_date, c_id, u_id, pageNumber, pageSize,isSummary);
	}
	
	@Override
	public HashMap<String, Object> getAttendanceForReportees(Date from_date, Date to_date, int c_id, int u_id,
			int pageNumber, int pageSize, boolean isSummary,String country,String state,String city,String divId) {
		
		return fmAttendanceDao.getAttendanceForReportees(from_date, to_date, c_id, u_id, pageNumber, pageSize,isSummary, country, state, city,divId);
	}

	@Override
	public List<Date> getCalendarDaysForMonth(Date fromDate, Date toDate) {
		
		return fmAttendanceDao.getCalendarDaysForMonth(fromDate, toDate);
	}

	@Override
	public List<WsUserDashboardDetails> dailyTrendUserAttendanceData(int c_id) {
		// TODO Auto-generated method stub
		return fmAttendanceDao.dailyTrendUserAttendanceData(c_id);
	}

	@Override
	public List<WsUserDashboardDetails> monthlyTrendUserAttendanceData(int c_id, String start_month, String end_month) {
		// TODO Auto-generated method stub
		return fmAttendanceDao.monthlyTrendUserAttendanceData(c_id,start_month,end_month);
	}

	@Override
	public List<UserAttendanceReportForToday> getSlotWiseLocationForCompany(int userId, String pageNo, String pageSize, int c_id,
			String fromDate, String toDate, int stateId) {
		// TODO Auto-generated method stub
		return fmAttendanceDao.getSlotWiseLocationForCompany( userId,  pageNo,  pageSize,  c_id,
			 fromDate,  toDate,  stateId);
	}
}
