package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMCustomerDao;
import com.fieldmi.service.FMCustomerService;
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

public class FMCustomerServiceImpl implements FMCustomerService {

	@Autowired
	FMCustomerDao fmCustomerDao;

	public void setFmCustomerDao(FMCustomerDao fmCustomerDao) {
		this.fmCustomerDao = fmCustomerDao;
	}

	@Override
	public boolean updateCustomerForLatLong(WsUpdateCustomer wsUpdateCustomer) {

		return fmCustomerDao.updateCustomerForLatLong(wsUpdateCustomer);
	}

	@Override
	public List<CustomerDetails> getShipToPartyList(int c_id,int value) {

		return fmCustomerDao.getShipToPartyList(c_id,value);
	}

	@Override
	public CustomerDetails getCustomerObject(String customer_id, int c_id) {

		return fmCustomerDao.getCustomerObject(customer_id, c_id);
	}

	@Override
	public boolean addAdditionalCustomer(WsAddAdditionalCustomer wsAddAdditionalCustomer) {

		return fmCustomerDao.addAdditionalCustomer(wsAddAdditionalCustomer);
	}

	@Override
	public boolean rejectCustomer(int customerId) {
		// TODO Auto-generated method stub
		return fmCustomerDao.rejectCustomer(customerId);
	}

	@Override
	public List<CustomerDetails> getCustomerListDistributorwise(int c_id, String distributorId, String routeId) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomerListDistributorwise(c_id,distributorId,routeId);
	}

	@Override
	public List<CustomerDetails> getAllCustomerList(int c_id,String distributors_ids) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getAllCustomerList(c_id,distributors_ids);
	}

	@Override
	public CustomerContactDetails getCustomerContactObject(String customer_id, int c_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomerContactObject(customer_id,c_id);
	}

	@Override
	public List<CustomerType> getCustomerTypeObject(int c_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomerTypeObject(c_id);
	}

	@Override
	public WsCustomer360Data getCustomer360Data(int c_id, int customer_id,String start_month, String end_month) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomer360Data(c_id,customer_id,start_month,end_month);
	}

	@Override
	public List<WsCustomer360Data> getLastSixMonthsSalesOrderData(int c_id, int customer_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getLastSixMonthsSalesOrderData(c_id,customer_id);
	}

	@Override
	public List<WsCustomer360Data> getLastSixMonthsSalesVisitsData(int c_id, int customer_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getLastSixMonthsSalesVisitsData(c_id,customer_id);
	}

	@Override
	public boolean activateCustomer(int customerId, int c_id, int r_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.activateCustomer(customerId,c_id,r_id);
	}

	@Override
	public CustomerDetails checkCustomerQuery(String query) {
		// TODO Auto-generated method stub
		return fmCustomerDao.checkCustomerQuery(query);
	}

	@Override
	public List<CustomerImportRecords> getCustomerImportedRecords(int c_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomerImportedRecords(c_id);
	}

	@Override
	public List<CustomerImportRecords> importCustomers( List<WsCustomerDetails> customerListToImport) {
		
		return fmCustomerDao.importCustomers( customerListToImport);
	}

	@Override
	public List<CustomerDetails> getCustomers(int c_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomers(c_id);
	}

	@Override
	public boolean updateMultipleCustomers(String user_ids, String distributorId, String routeId, int c_id) {
		// TODO Auto-generated method stub
		return fmCustomerDao.updateMultipleCustomers(user_ids,distributorId,routeId,c_id);
	}

	@Override
	public List<CustomerTargetRecords> getCustomerTargetRecords(int c_id, int custId) {
		// TODO Auto-generated method stub
		return fmCustomerDao.getCustomerTargetRecords(c_id,custId);
	}

	@Override
	public List<CustomerTargetRecords> checkExistTargetData(int c_id, String customerCode, String month,
			double totalTarget) {
		// TODO Auto-generated method stub
		return fmCustomerDao.checkExistTargetData(c_id,customerCode,month,totalTarget);
	}

}
