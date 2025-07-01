package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.InvoiceDetails;
import com.softtantra.salesapp.pojo.InvoiceMaster;

public interface FMInvoiceService {

	List<InvoiceMaster> checkCustomerExist(int custmerId, int c_id, double totalAmount);

	public List<InvoiceMaster> checkIfInvoiceExist(String invoiceNumber);

	boolean saveInvoiceDetails(InvoiceDetails invoiceDetails);

}
