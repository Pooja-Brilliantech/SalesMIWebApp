package com.fieldmi.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.FMSalesMasterDao;
import com.fieldmi.service.FMSalesMasterService;
import com.fieldmi.stubs.SalesOrderReport;
import com.fieldmi.stubs.WSUpdateSalesMaster;
import com.fieldmi.stubs.WsSalesDetailsReportInput;
import com.fieldmi.stubs.WsSalesExportWithDetails;
import com.fieldmi.stubs.WsSalesOrderOutput;
import com.fieldmi.stubs.WsSalesOrderProductList;
import com.fieldmi.stubs.WsSalesOrderReport;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.fieldmi.stubs.WsSalesReportOutput;
import com.fieldmi.stubs.tally.SalesOrder;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.SalesDetails;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.salesapp.pojo.View_Sales_Order_Master;
import com.softtantra.salesapp.pojo.WorkFlowTasksStatus;
import com.softtantra.ws.Credentials;

public class FMSalesMasterServiceImpl implements FMSalesMasterService {

	@Autowired
	FMSalesMasterDao fmSalesMasterDao;

	public void setFmSalesMasterDao(FMSalesMasterDao fmSalesMasterDao) {
		this.fmSalesMasterDao = fmSalesMasterDao;
	}

	@Override
	@Transactional
	public List<WsSalesOrderOutput> getPendingSalesOrder(List<WorkFlowTasksStatus> workFlowTaskStatusList,
			int company_id,String user,String plant) {

		return fmSalesMasterDao.getPendingSalesOrder(workFlowTaskStatusList, company_id,user,plant);
	}

	@Override
	@Transactional
	public boolean updateSalesMasterWithSapOrderId(WSUpdateSalesMaster wsUpdateSalesMaster) {

		return fmSalesMasterDao.updateSalesMasterWithSapOrderId(wsUpdateSalesMaster);
	}

	@Override
	public String sendSOToSAP(int sales_master_id) throws Exception {

		return fmSalesMasterDao.sendSOToSAP(sales_master_id);
	}

	@Override
	public CustomerDetails getCustomerDetails(int c_id, String customerCode) {

		return fmSalesMasterDao.getCustomerDetails(c_id, customerCode);
	}

	@Override
	public String getSaporderId(int c_id, int salesMasterId) {

		return fmSalesMasterDao.getSaporderId(c_id, salesMasterId);
	}

	@Override
	public WsSalesReportOutput getSalesOrderReportForSil(WsSalesOrderReportInput wSSalesOrderReportInput, Long id) {

		return fmSalesMasterDao.getSalesOrderReportForSil(wSSalesOrderReportInput, id);
	}

	@Override
	public List<WsSalesOrderProductList> getSalesDetailsReportForSil(WsSalesDetailsReportInput credentials, Long id) {

		return fmSalesMasterDao.getSalesDetailsReportForSil(credentials, id);
	}

	@Override
	public List<SalesMaster> getSalesOrderList(int c_id) {

		return fmSalesMasterDao.getSalesOrderList(c_id);
	}

	@Override
	public String getInvoiceNumberFromSAP(int sales_master_id) throws Exception {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.getInvoiceNumberFromSAP(sales_master_id);
	}

	@Override
	public SalesMaster getSOObject(int sales_master_id) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.getSOObject(sales_master_id);
	}

	@Override
	public boolean deleteSilSalesOrder(int sales_master_id_reject, int c_id) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.deleteSilSalesOrder(sales_master_id_reject,c_id);
	}

	@Override
	public HashMap<String, Double> getMaterialStatus(int salesMasterId, int c_id) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.getMaterialStatus(salesMasterId,c_id) ;
	}

	@Override
	public List<SalesDetails> getSalesDetailsList(List<String> salesDetailsIdList) {

		// TODO Auto-generated method stub
		return fmSalesMasterDao.getSalesDetailsList(salesDetailsIdList) ;
	}

	@Override
	public List<SalesMaster> unSyncedList(int c_id,String sqluser,String sqltype,String sqlplant) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.unSyncedList(c_id,sqluser,sqltype,sqlplant);
	}

	@Override
	public List<WsSalesOrderReport> getTomorrowsOrders(Credentials credentials, Long id) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.getTomorrowsOrders(credentials,id);
	}

	@Override
	public List<SalesMaster> getSalesData(int c_id, String month, int userId) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.getSalesData(c_id,month, userId);
	}

	@Override
	public List<SalesMaster> unSyncedList(int c_id) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.unSyncedList(c_id);
	}
	
	@Override
	public void updateSalesMaster(String message,int salesMasterId,int c_id,int value) {
		fmSalesMasterDao.updateSalesMaster(message,salesMasterId,c_id, value);
	}

	@Override
	public List<WsSalesExportWithDetails> exportData(String sql) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.exportData(sql);
	}

	@Override
	public List<SalesMaster> checkExists(int custmerId, int c_id, double totalAmount) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.checkExists(custmerId,c_id,totalAmount);
	}
	
	@Override
	public List<SalesOrder> exportSalesData(int company_id, int u_id, Date fromDate, Date toDate) {
		
		return fmSalesMasterDao.exportSalesData(company_id, u_id, fromDate, toDate);
		
	}

	@Override
	public SalesDetails checkSalesDetailsExist(int sales_master_id, int pricemaster_id, int product_id) {
		// TODO Auto-generated method stub
		return fmSalesMasterDao.checkSalesDetailsExist(sales_master_id,pricemaster_id,product_id);
	}

	@Override
	public List<SalesOrderReport> getSalesOrderReport(int company_id, int u_id, String fromDate, String toDate) {
		
		return fmSalesMasterDao.getSalesOrderReport(company_id, u_id, fromDate, toDate);
	}
	

}
