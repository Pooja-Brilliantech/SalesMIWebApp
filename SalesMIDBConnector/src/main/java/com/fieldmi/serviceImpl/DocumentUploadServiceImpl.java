package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.DocumentUploadDao;
import com.fieldmi.service.DocumentUploadService;
import com.softtantra.salesapp.pojo.CustomerRequiredDocuments;
import com.softtantra.salesapp.pojo.CustomerType;
import com.softtantra.salesapp.pojo.Documents;

public class DocumentUploadServiceImpl implements DocumentUploadService{

	@Autowired
	DocumentUploadDao documentUploadDao;
	
	public void setDocumentUploadDao(DocumentUploadDao documentUploadDao) {
		this.documentUploadDao = documentUploadDao;
	}
	@Override
	public int saveDocument(Documents documents) {
		// TODO Auto-generated method stub
		return documentUploadDao.saveDocument(documents);
	}
	@Override
	public boolean updateDocumentUrl(int documentId, String objectKey) {
		// TODO Auto-generated method stub
		return documentUploadDao.updateDocumentUrl(documentId,objectKey);
	}
	@Override
	public boolean checkDocument_Name(String documentName, int c_id) {
		// TODO Auto-generated method stub
		return documentUploadDao.checkDocument_Name(documentName,c_id);
	}
	@Override
	public List<Documents> getDocumentList(int c_id) {
		// TODO Auto-generated method stub
		return documentUploadDao.getDocumentList(c_id);
	}
	@Override
	public int saveCustomerDocument(CustomerRequiredDocuments documents) {
		// TODO Auto-generated method stub
		return documentUploadDao.saveCustomerDocument(documents);
	}
	@Override
	public boolean checkCustomerDocument_Name(String documentName, int c_id) {
		// TODO Auto-generated method stub
		return documentUploadDao.checkCustomerDocument_Name(documentName,c_id);
	}
	@Override
	public List<CustomerRequiredDocuments> getCustomerRequiredDocumentsList(int c_id) {
		// TODO Auto-generated method stub
		return documentUploadDao.getCustomerRequiredDocumentsList(c_id);
	}
	@Override
	public boolean editCustomerDocument(String doc_name, int c_id,int id) {
		// TODO Auto-generated method stub
		return documentUploadDao.editCustomerDocument(doc_name,c_id,id);
	}
	@Override
	public List<CustomerRequiredDocuments> getCustRequiredDocumentsList(int c_id) {
		// TODO Auto-generated method stub
		return documentUploadDao.getCustRequiredDocumentsList(c_id);
	}
	@Override
	public List<String> getCustRequiredDocumentsListCustomerTypeWise(int c_id,
			String customer_type,String customer_sub_type) {
		// TODO Auto-generated method stub
		return documentUploadDao.getCustRequiredDocumentsListCustomerTypeWise(c_id,customer_type,customer_sub_type);
	}
	@Override
	public List<String> getCustomerSubType(int c_id, String customer_type) {
		// TODO Auto-generated method stub
		return documentUploadDao.getCustomerSubType(c_id,customer_type);
	}
	@Override
	public boolean deleteDocument(Documents document) {
		// TODO Auto-generated method stub
		return documentUploadDao.deleteDocument(document);
	}

}
