package com.fieldmi.service;

import java.util.List;

import com.fieldmi.stubs.WsAddAdditionalCustomer;
import com.fieldmi.stubs.WsCustomerDetails;
import com.fieldmi.stubs.WsCustomerReturnData;
import com.fieldmi.stubs.WsUpdateCustomer;
import com.softtantra.salesapp.pojo.CustomerContactDetails;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.CustomerImportRecords;
import com.softtantra.salesapp.pojo.CustomerTargetRecords;
import com.softtantra.salesapp.pojo.CustomerType;
import com.softtantra.ws.WsCustomer360Data;

public interface FMCustomerService {

	public boolean updateCustomerForLatLong(WsUpdateCustomer wsUpdateCustomer);

	public List<CustomerDetails> getShipToPartyList(int c_id,int value);

	public CustomerDetails getCustomerObject(String customer_id, int c_id);

	public boolean addAdditionalCustomer(WsAddAdditionalCustomer wsAddAdditionalCustomer);
	
	public boolean rejectCustomer(int customerId);
	
	List<CustomerDetails> getCustomerListDistributorwise(int c_id,String distributorId,String routeId);
	
	public List<CustomerDetails> getAllCustomerList(int c_id,String distributors_ids);
	
	public CustomerContactDetails getCustomerContactObject(String customer_id, int c_id);
	
	public List<CustomerType> getCustomerTypeObject(int c_id);
	
	public WsCustomer360Data getCustomer360Data(int c_id, int customer_id,String start_month, String end_month);
	
	public List<WsCustomer360Data> getLastSixMonthsSalesOrderData(int c_id, int customer_id);
	
	public List<WsCustomer360Data> getLastSixMonthsSalesVisitsData(int c_id, int customer_id);
	
	public boolean activateCustomer(int customerId, int c_id, int r_id);
	
	public CustomerDetails checkCustomerQuery(String query);
	
	public List<CustomerImportRecords> getCustomerImportedRecords(int c_id);
	
	List<CustomerImportRecords> importCustomers(List<WsCustomerDetails> customerList);
	
	public List<CustomerDetails> getCustomers(int c_id);

	public boolean updateMultipleCustomers(String user_ids, String distributorId, String routeId, int c_id);

	public List<CustomerTargetRecords> getCustomerTargetRecords(int c_id, int custId);

	public List<CustomerTargetRecords> checkExistTargetData(int c_id, String customerCode, String month,
			double totalTarget);
}

