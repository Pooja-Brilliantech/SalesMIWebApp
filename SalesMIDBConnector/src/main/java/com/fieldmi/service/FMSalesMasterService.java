package com.fieldmi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

public interface FMSalesMasterService {

	public List<WsSalesOrderOutput> getPendingSalesOrder(List<WorkFlowTasksStatus> workFlowTaskStatusList,
			int company_id,String user,String plant);

	public boolean updateSalesMasterWithSapOrderId(WSUpdateSalesMaster wsUpdateSalesMaster);

	String sendSOToSAP(int sales_master_id) throws Exception;

	CustomerDetails getCustomerDetails(int c_id, String customerCode);

	public String getSaporderId(int c_id, int salesMasterId);

	public WsSalesReportOutput getSalesOrderReportForSil(WsSalesOrderReportInput wSSalesOrderReportInput, Long id);

	public List<WsSalesOrderProductList> getSalesDetailsReportForSil(WsSalesDetailsReportInput credentials, Long id);

	public List<SalesMaster> getSalesOrderList(int c_id);
	
	String getInvoiceNumberFromSAP(int sales_master_id) throws Exception;
	
	SalesMaster getSOObject(int sales_master_id);
	
	public boolean deleteSilSalesOrder(int sales_master_id_reject,int c_id);

	public HashMap<String, Double> getMaterialStatus(int salesMasterId, int c_id);
	
	public List<SalesDetails> getSalesDetailsList(List<String> salesDetailsId);
	
	public List<SalesMaster> unSyncedList(int c_id,String sqluser,String sqltype,String sqlplant);

	public List<WsSalesOrderReport> getTomorrowsOrders(Credentials credentials, Long id);
	
	public List<SalesMaster> getSalesData(int c_id,String month,int userId);
	
	public List<SalesMaster> unSyncedList(int c_id);
	
	public void updateSalesMaster(String message,int salesMasterId,int c_id,int value);
	
	public List<WsSalesExportWithDetails> exportData(String sql);
	
	public List<SalesMaster> checkExists(int custmerId, int c_id,double totalAmount);
	
	public List<SalesOrder> exportSalesData(int company_id, int u_id, Date fromDate, Date toDate);

	public SalesDetails checkSalesDetailsExist(int sales_master_id, int pricemaster_id, int product_id);
	
	public List<SalesOrderReport> getSalesOrderReport(int company_id, int u_id, String fromDate, String toDate);
	
}
