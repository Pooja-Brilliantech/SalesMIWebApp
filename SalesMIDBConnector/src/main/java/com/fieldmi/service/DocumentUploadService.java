package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.CustomerRequiredDocuments;
import com.softtantra.salesapp.pojo.CustomerType;
import com.softtantra.salesapp.pojo.Documents;

public interface DocumentUploadService {

	public int saveDocument(Documents documents);
	
	boolean updateDocumentUrl(int documentId,String objectKey);
	
	boolean checkDocument_Name(String documentName,int c_id);
	
	boolean deleteDocument(Documents document);
	
	List<Documents> getDocumentList(int c_id);
	
	public int saveCustomerDocument(CustomerRequiredDocuments documents);
	
	boolean checkCustomerDocument_Name(String documentName,int c_id);
	
	List<CustomerRequiredDocuments> getCustomerRequiredDocumentsList(int c_id);
	
	boolean editCustomerDocument(String doc_name, int c_id,int id);
	
	List<CustomerRequiredDocuments> getCustRequiredDocumentsList(int c_id);
	
	List<String> getCustRequiredDocumentsListCustomerTypeWise(int c_id,String customer_type,String customer_sub_type);
	
	List<String> getCustomerSubType(int c_id,String customer_type);
}
