package com.fieldmi.service;

import java.util.List;

import com.fieldmi.stubs.WSUpdateSyncStatusInput;
import com.fieldmi.stubs.WsUserDashboardDetails;
import com.softtantra.salesapp.pojo.CompanyLocations;
import com.softtantra.salesapp.pojo.CustomerClassification;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.LoginDetails;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.WsUserAppInfo;

public interface FMUserService {

	User getUserInfoForEdit(int user_id, int c_id);

	int updateUserForSyncing(int c_id, String coloumnName, String title, String body);

	public boolean updateUserSyncStatus(WSUpdateSyncStatusInput wsUpdateSyncStatusInput, long id);

	List<String> getEmails(List<Integer> list, int c_id);
	
	public boolean renewUserFromEmail(String email, int c_id);
	
	List<CustomerClassification> getCustomerClassifcation(int c_id);
	
	public boolean deleteMulipleUsers(String user_ids,int c_id);
	
	public List<CompanyLocations> companyLocationList(int c_id);
	
	List<WsUserAppInfo> getUserAppDetails(int company_id, int user_id);
	
	public boolean updateUserAppVerionAndPermissions(WsUserAppInfo wsUserAppInfo);
	
	public boolean reActivateUser(int user_id,int c_id);
	
	public List<DistributorDetails> getDistributorAndRoute(int company_id,int u_id);
	
	boolean insertDistributorsRouteToUser(String distributor_id, String user_ids,int c_id);
	
	public boolean deleteDistributorRoute(int c_id,int u_id, int dist_id,int route_id);
	
	public List<WsUserDashboardDetails> dailyTrendUserLoginData(int c_id);
		
	public List<WsUserDashboardDetails> monthlyTrendUserLoginData(int c_id,String start_month, String end_month);
	
	public List<User> getUsers(int distributorId, int c_id);

	List<User> getHigherAuthority(int i, int intValue);

	boolean updateUserObject(User user);

	LoginDetails getLoginDetails(String username, String password, int company_id);
	
	public List<WsUserDashboardDetails> monthlyTrendUserLogin(int c_id,int year,String activityName);
	
	public List<WsUserDashboardDetails> getUserData(int c_id,String notifcationType,String month,int account);

	public int getUserCount();
	
}
