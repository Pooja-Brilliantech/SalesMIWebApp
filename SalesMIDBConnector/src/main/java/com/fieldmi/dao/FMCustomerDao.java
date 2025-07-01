package com.fieldmi.dao;

import java.util.List;

import org.hibernate.Session;

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

public interface FMCustomerDao {

	boolean updateCustomerForLatLong(WsUpdateCustomer wsUpdateCustomer);

	List<CustomerDetails> getShipToPartyList(int c_id,int value);

	CustomerDetails getCustomerObject(String customer_id, int c_id);

	boolean addAdditionalCustomer(WsAddAdditionalCustomer wsAddAdditionalCustomer);

	boolean rejectCustomer(int customerId);

	List<CustomerDetails> getCustomerListDistributorwise(int c_id, String distributorId, String routeId);

	List<CustomerDetails> getAllCustomerList(int c_id,String distributors_ids);

	CustomerContactDetails getCustomerContactObject(String customer_id, int c_id);

	List<CustomerType> getCustomerTypeObject(int c_id);

	WsCustomer360Data getCustomer360Data(int c_id, int customer_id,String start_month, String end_month);

	List<WsCustomer360Data> getLastSixMonthsSalesOrderData(int c_id, int customer_id);

	List<WsCustomer360Data> getLastSixMonthsSalesVisitsData(int c_id, int customer_id);

	boolean activateCustomer(int customerId, int c_id, int r_id);

	CustomerDetails checkCustomerQuery(String query);

	List<CustomerImportRecords> getCustomerImportedRecords(int c_id);
	
	List<CustomerImportRecords> importCustomers( List<WsCustomerDetails> customerListToImport);

	List<CustomerDetails> getCustomers(int c_id);

	boolean updateMultipleCustomers(String user_ids, String distributorId, String routeId, int c_id);

	List<CustomerTargetRecords> getCustomerTargetRecords(int c_id, int custId);

	List<CustomerTargetRecords> checkExistTargetData(int c_id, String customerCode, String month, double totalTarget);

}
