package com.fieldmi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fieldmi.stubs.WsCustomerDetails;

public class CustomerImportRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<WsCustomerDetails> customersToImport = new ArrayList<WsCustomerDetails>();
	
	public List<WsCustomerDetails> getCustomersToImport() {
		return customersToImport;
	}

	public void setCustomersToImport(List<WsCustomerDetails> customersToImport) {
		this.customersToImport = customersToImport;
	}

}
