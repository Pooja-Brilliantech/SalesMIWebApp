package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.CustomerTargetRecords;
import com.softtantra.salesapp.pojo.SalesMaster;

public interface SfdcService {

	List<CustomerDetails> importCustomerFromSFDC(List<String> codes, int company_id, int u_id, StringBuilder importMessage) throws Exception;
	
	List<CustomerDetails> importCustomerFromSFDCLastFetchDate(int company_id, int u_id, StringBuilder importMessage) throws Exception;

	List<CustomerTargetRecords> importCustomerTargetFromSFDC(int c_id, int u_id) throws Exception;

	List<SalesMaster> importSalesOrderImportFromSFDC(int c_id, int u_id)throws Exception;

	List<CustomerTargetRecords> importCustomerTargetFromSFDCforYesterday(int c_id, int u_id)throws Exception;

	List<SalesMaster> importSalesDataFromSFDCforYesterday(int c_id, int u_id)throws Exception;

	List<CustomerDetails> importInfluencersFromSFDC(int c_id, int u_id)throws Exception;

	List<CustomerDetails> importCustomersFromSFDC(int c_id, int u_id)throws Exception;

	List<CustomerDetails> importCustomersFromSFDCforYesterday(int c_id, int u_id)throws Exception;
	
}
