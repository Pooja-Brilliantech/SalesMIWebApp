package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.SfdcDao;
import com.fieldmi.service.SfdcService;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.CustomerTargetRecords;
import com.softtantra.salesapp.pojo.SalesMaster;

public class SfdcServiceImpl implements SfdcService{

	@Autowired 
	SfdcDao sfdcDao;

	public void setSfdcDao(SfdcDao sfdcDao) {
		this.sfdcDao = sfdcDao;
	}

	@Override
	public List<CustomerDetails> importCustomerFromSFDC(List<String> codes, int company_id, int u_id,
			StringBuilder importMessage) throws Exception {
		
		return sfdcDao.importCustomerFromSFDC(codes, company_id, u_id, importMessage);
	}

	@Override
	public List<CustomerDetails> importCustomerFromSFDCLastFetchDate( int company_id, int u_id,
			StringBuilder importMessage) throws Exception {
		return sfdcDao.importCustomerFromSFDCLastFetchDate( company_id, u_id, importMessage);
	}

	@Override
	public List<CustomerTargetRecords> importCustomerTargetFromSFDC(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importCustomerTargetFromSFDC(c_id,u_id);
	}

	@Override
	public List<SalesMaster> importSalesOrderImportFromSFDC(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importSalesOrderImportFromSFDC(c_id, u_id);
	}

	@Override
	public List<CustomerTargetRecords> importCustomerTargetFromSFDCforYesterday(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importCustomerTargetFromSFDCforYesterday(c_id,u_id);
	}

	@Override
	public List<SalesMaster> importSalesDataFromSFDCforYesterday(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importSalesDataFromSFDCforYesterday(c_id, u_id);
	}

	@Override
	public List<CustomerDetails> importInfluencersFromSFDC(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importInfluencersFromSFDC(c_id, u_id);
	}

	@Override
	public List<CustomerDetails> importCustomersFromSFDC(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importCustomersFromSFDC(c_id, u_id);
	}

	@Override
	public List<CustomerDetails> importCustomersFromSFDCforYesterday(int c_id, int u_id) throws Exception {
		// TODO Auto-generated method stub
		return sfdcDao.importCustomersFromSFDCforYesterday(c_id,u_id);
	}

}
