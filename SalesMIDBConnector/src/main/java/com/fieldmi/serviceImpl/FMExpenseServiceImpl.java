package com.fieldmi.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMExpenseDao;
import com.fieldmi.service.FMExpenseService;
import com.fieldmi.service.FMWorkflowConfigurationService;
import com.fieldmi.service.WorkFlowAuditLogService;
import com.fieldmi.service.WorkFlowTaskStatusService;
import com.fieldmi.stubs.WsExpense;
import com.fieldmi.stubs.WsExpenseMasterListForManager;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.softtantra.salesapp.pojo.ExpenseConfiguration;
import com.softtantra.salesapp.pojo.ExpenseDetails;
import com.softtantra.salesapp.pojo.ExpenseMaster;
import com.softtantra.ws.WsExpenseMasterList;

public class FMExpenseServiceImpl implements FMExpenseService {
	
	@Autowired
	FMExpenseDao fmExpenseDao;

	public void setFmExpenseDao(FMExpenseDao fmExpenseDao) {
		this.fmExpenseDao = fmExpenseDao;
	}

	@Override
	public WsExpense getExpenseMaster(int e_id) {
		return fmExpenseDao.getExpenseMaster(e_id);
	}

	@Override
	public ExpenseMaster getExpensePieChartData(int c_id, int u_id,String value) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getExpensePieChartData(c_id,u_id,value);
	}

	@Override
	public boolean aproveExpense(String expenseId, String comment, double dapprovedamount,int user_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.aproveExpense(expenseId,comment,dapprovedamount,user_id);
	}

	@Override
	public List<ExpenseConfiguration> getExpenseConfigList(int c_id, int roleId) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getExpenseConfigList(c_id,roleId);
	}

	@Override
	public boolean deleteExpenseConfig1(int detailId, int c_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.deleteExpenseConfig1(detailId,c_id);
	}

	@Override
	public ExpenseConfiguration addExpenseConfig(String expense_type_id, int country, int role_id, int c_id,int state_id, int city_id,
			int isDefault, int isDistance, String expense_mode_type, double amt,int u_id,String cityType,String expenseCode,String modeOfTravel,int isfixed) {
		// TODO Auto-generated method stub
		return fmExpenseDao.addExpenseConfig(expense_type_id,country,role_id,c_id,isDefault,isDistance,amt,expense_mode_type,state_id,city_id,u_id,cityType,expenseCode,modeOfTravel,isfixed);
	}

	@Override
	public WsExpenseMasterListForManager getExpenseReportForSil(WsSalesOrderReportInput wsExpenseReportInput, Long id,int rId) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getExpenseReportForSil(wsExpenseReportInput,id,rId);
	}
	
	public double getClaimAmount(String expenseId, int user_id, int company_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getClaimAmount(expenseId,user_id,company_id);
	}

	@Override
	public boolean checkExpenseConfiguration(String expnese_mode_type, String city_type, int c_id,int role_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.checkExpenseConfiguration(expnese_mode_type,city_type,c_id,role_id);
	}

	@Override
	public boolean updateExpenseMaster(int company_id, String approveStatus, int parseInt) {
		// TODO Auto-generated method stub
		return fmExpenseDao.updateExpenseMaster(company_id,approveStatus,parseInt);
	}

	@Override
	public boolean updateExpenseConfig(String expense_type_id, int isDefault, int isDistance, String expense_mode_type,
			double amt, String cityType, String expenseCode, String modeOfTravel, int isfixed, int expenseConfigId1,
			int c_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.updateExpenseConfig( expense_type_id,  isDefault,  isDistance,  expense_mode_type,
			 amt,  cityType,  expenseCode,  modeOfTravel,  isfixed,  expenseConfigId1,
			 c_id);
	}

	@Override
	public List<WsExpense> expenseListNew(int c_id, 
			String next_approver_id, String fromDate, String toDate, int u_id, String sqlCountry, String sqlState,
			String sqlCity, String sqlUser, String sqlUserName) {
		// TODO Auto-generated method stub
		return fmExpenseDao.expenseListNew(c_id,
			 next_approver_id,  fromDate,  toDate,  u_id,  sqlCountry,  sqlState,
			 sqlCity,  sqlUser,  sqlUserName);
	}

	@Override
	public List<ExpenseDetails> getDetailsDayWise(Date sDate, Date edate,  String expenseId,
			int c_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getDetailsDayWise(sDate,edate,expenseId,c_id);
	}

	@Override
	public HashMap<String, Object> getData(List<ExpenseDetails> eDetails, List<Date> days) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getData(eDetails,days);
	}

	@Override
	public boolean updateExpenseDetails(ExpenseDetails eDetails, int expenseId, Date day, int c_id) {
		// TODO Auto-generated method stub
		return fmExpenseDao.updateExpenseDetails(eDetails,expenseId,day,c_id);
	}

	@Override
	public List<Date> getUniqueExpenseDetailsDate(int eId) {
		// TODO Auto-generated method stub
		return fmExpenseDao.getUniqueExpenseDetailsDate(eId);
	}

}
