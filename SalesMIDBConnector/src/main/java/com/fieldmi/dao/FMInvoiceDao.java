package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.InvoiceDetails;
import com.softtantra.salesapp.pojo.InvoiceMaster;

public interface FMInvoiceDao {

	List<InvoiceMaster> checkCustomerExist(int custmerId, int c_id, double totalAmount);

	List<InvoiceMaster> checkIfInvoiceExist(String invoiceNumber);

	boolean saveInvoiceDetails(InvoiceDetails invoiceDetails);

}
