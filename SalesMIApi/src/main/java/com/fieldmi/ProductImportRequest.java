package com.fieldmi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fieldmi.stubs.WsProductDetails;

public class ProductImportRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<WsProductDetails> productsToImport = new ArrayList<WsProductDetails>();


	public List<WsProductDetails> getProductsToImport() {
		return productsToImport;
	}

	public void setProductsToImport(List<WsProductDetails> productsToImport) {
		this.productsToImport = productsToImport;
	}
	
	
}
