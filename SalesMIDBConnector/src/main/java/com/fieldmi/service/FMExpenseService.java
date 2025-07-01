package com.fieldmi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import com.fieldmi.stubs.WsExpense;
import com.fieldmi.stubs.WsExpenseMasterListForManager;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.fieldmi.stubs.WsSalesReportOutput;
import com.softtantra.salesapp.pojo.ExpenseConfiguration;
import com.softtantra.salesapp.pojo.ExpenseDetails;
import com.softtantra.salesapp.pojo.ExpenseMaster;
import com.softtantra.ws.WsExpenseMasterList;

public interface FMExpenseService {
	
	
	WsExpense getExpenseMaster(int e_id);
	
	ExpenseMaster getExpensePieChartData(int c_id,int u_id,String value);
	
	public boolean aproveExpense(String expenseId, String comment, double dapprovedamount,int user_id);
	
	List<ExpenseConfiguration> getExpenseConfigList(int c_id,int roleId);
	
	boolean deleteExpenseConfig1(int detailId,int c_id);
	
	ExpenseConfiguration addExpenseConfig( String expense_type_id,  int country, int role_id,int c_id, int state_id, int city_id, int isDefault,int isDistance, String expense_mode_type, double amt,int u_id,String cityType, String expenseCode,String modeOfTravel,int isfixed);
	
	public WsExpenseMasterListForManager getExpenseReportForSil(WsSalesOrderReportInput wsExpenseReportInput, Long id,int rId);
	
	double getClaimAmount(String expenseId, int user_id, int company_id);
	
	public boolean checkExpenseConfiguration(String expnese_mode_type, String city_type,int c_id,int role_id);

	boolean updateExpenseMaster(int company_id, String approveStatus, int parseInt);
	
	boolean updateExpenseConfig(String expense_type_id, int isDefault,int isDistance,String expense_mode_type,double amt,String cityType,String expenseCode,String modeOfTravel,int isfixed, int expenseConfigId1,int c_id);
	
	public List<WsExpense> expenseListNew(int c_id, String next_approver_id, String fromDate, String toDate, int u_id, String sqlCountry, String sqlState,
			String sqlCity, String sqlUser, String sqlUserName);
	
	public List<ExpenseDetails> getDetailsDayWise(Date sDate,Date edate,String expenseId,int c_id);
	
	public HashMap<String,Object>getData(List<ExpenseDetails> eDetails,List<Date> days);
	
	public boolean updateExpenseDetails(ExpenseDetails eDetails, int expenseId, Date day, int c_id);

	public List<Date> getUniqueExpenseDetailsDate(int eId);
}
