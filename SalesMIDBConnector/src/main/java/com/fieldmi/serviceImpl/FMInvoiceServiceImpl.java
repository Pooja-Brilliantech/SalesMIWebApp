package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMInvoiceDao;
import com.fieldmi.service.FMInvoiceService;
import com.softtantra.salesapp.pojo.InvoiceDetails;
import com.softtantra.salesapp.pojo.InvoiceMaster;

public class FMInvoiceServiceImpl implements FMInvoiceService{
	
	@Autowired
	FMInvoiceDao fmInvoiceDao;

	public void setFmInvoiceDao(FMInvoiceDao fmInvoiceDao) {
		this.fmInvoiceDao = fmInvoiceDao;
	}

	@Override
	public List<InvoiceMaster> checkCustomerExist(int custmerId, int c_id,double totalAmount) {
		// TODO Auto-generated method stub
		return fmInvoiceDao.checkCustomerExist(custmerId,c_id,totalAmount);
	}
	
	@Override
	public List<InvoiceMaster> checkIfInvoiceExist(String invoiceNumber) {
		// TODO Auto-generated method stub
		return fmInvoiceDao.checkIfInvoiceExist(invoiceNumber);
	}


	@Override
	public boolean saveInvoiceDetails(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		return fmInvoiceDao.saveInvoiceDetails(invoiceDetails);
	}
	
	

}
