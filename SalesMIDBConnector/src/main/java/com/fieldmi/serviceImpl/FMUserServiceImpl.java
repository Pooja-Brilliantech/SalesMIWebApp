package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMUserServiceDao;
import com.fieldmi.service.FMUserService;
import com.fieldmi.stubs.WSUpdateSyncStatusInput;
import com.fieldmi.stubs.WsUserDashboardDetails;
import com.softtantra.salesapp.pojo.CompanyLocations;
import com.softtantra.salesapp.pojo.CustomerClassification;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.LoginDetails;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.ws.WsUserAppInfo;

public class FMUserServiceImpl implements FMUserService {

	@Autowired
	FMUserServiceDao fmUserServiceDao;

	public void setFmUserServiceDao(FMUserServiceDao fmUserServiceDao) {
		this.fmUserServiceDao = fmUserServiceDao;
	}

	@Override
	public User getUserInfoForEdit(int user_id, int c_id) {

		return fmUserServiceDao.getUserInfoForEdit(user_id, c_id);
	}

	@Override
	public int updateUserForSyncing(int c_id, String coloumnName, String title, String body) {

		return fmUserServiceDao.updateUserForSyncing(c_id, coloumnName, title, body);
	}

	@Override
	public boolean updateUserSyncStatus(WSUpdateSyncStatusInput wsUpdateSyncStatusInput, long id) {

		return fmUserServiceDao.updateUserSyncStatus(wsUpdateSyncStatusInput, id);
	}

	@Override
	public List<String> getEmails(List<Integer> list, int c_id) {

		return fmUserServiceDao.getEmails(list, c_id);
	}

	@Override
	public boolean renewUserFromEmail(String email, int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.renewUserFromEmail(email,c_id);
	}

	@Override
	public List<CustomerClassification> getCustomerClassifcation(int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.getCustomerClassifcation(c_id);
	}

	@Override
	public boolean deleteMulipleUsers(String user_ids, int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.deleteMulipleUsers(user_ids,c_id);
	}

	@Override
	public List<CompanyLocations> companyLocationList(int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.companyLocationList(c_id);
	}

	@Override
	public List<WsUserAppInfo> getUserAppDetails(int company_id, int user_id) {
		return fmUserServiceDao.getUserAppDetails(company_id,user_id);
	}

	@Override
	public boolean updateUserAppVerionAndPermissions(WsUserAppInfo wsUserAppInfo) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.updateUserAppVerionAndPermissions(wsUserAppInfo);
	}

	@Override
	public boolean reActivateUser(int user_id, int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.reActivateUser(user_id,c_id);
	}

	@Override
	public List<DistributorDetails> getDistributorAndRoute(int company_id,int u_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.getDistributorAndRoute(company_id,u_id);
	}

	@Override
	public boolean insertDistributorsRouteToUser(String distributor_id, String user_ids, int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.insertDistributorsRouteToUser(distributor_id,user_ids,c_id);
	}

	@Override
	public boolean deleteDistributorRoute(int c_id, int u_id, int dist_id, int route_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.deleteDistributorRoute(c_id,u_id,dist_id, route_id);
	}

	@Override
	public List<WsUserDashboardDetails> dailyTrendUserLoginData(int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.dailyTrendUserLoginData(c_id);
	}

	@Override
	public List<WsUserDashboardDetails> monthlyTrendUserLoginData(int c_id,String start_month, String end_month) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.monthlyTrendUserLoginData(c_id,start_month,end_month);
	}

	@Override
	public List<User> getUsers(int distributorId, int c_id) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.getUsers(distributorId,c_id);
	}

	@Override
	public List<User> getHigherAuthority(int i, int intValue) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.getHigherAuthority(i,intValue);
	}

	@Override
	public boolean updateUserObject(User user) {
		
		return fmUserServiceDao.updateUserObject(user);
	}
	
	@Override
	public LoginDetails getLoginDetails(String username, String password, int company_id) {
		
		return fmUserServiceDao.getLoginDetails(username, password, company_id);
	}

	@Override
	public List<WsUserDashboardDetails> monthlyTrendUserLogin(int c_id,int year,String activityName) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.monthlyTrendUserLogin(c_id,year,activityName);
	}

	@Override
	public List<WsUserDashboardDetails> getUserData(int c_id, String notifcationType, String month,int account) {
		// TODO Auto-generated method stub
		return fmUserServiceDao.getUserData(c_id,notifcationType,month,account);
	}

	@Override
	public int getUserCount() {
		// TODO Auto-generated method stub
		return  fmUserServiceDao.getUserCount();
	}
}
