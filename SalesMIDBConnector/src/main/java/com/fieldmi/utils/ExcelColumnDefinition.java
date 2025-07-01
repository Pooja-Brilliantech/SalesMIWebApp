package com.fieldmi.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;

public class ExcelColumnDefinition {

	private String columnName;
	
	private String sheetName;

	private int columnNo;
	
	private int sheetNo;
	
	private int refSheetNo;
	
	private String refSheetName;
	
	private List<String> refDataList = new ArrayList<>();
	
	private HashMap<String, List<String>> refDependentLov = new HashMap<String, List<String>>();
	
	private List<ExcelColumnDefinition> dependentColumnDef = new ArrayList<ExcelColumnDefinition>();

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}

	public int getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(int sheetNo) {
		this.sheetNo = sheetNo;
	}

	public int getRefSheetNo() {
		return refSheetNo;
	}

	public void setRefSheetNo(int refSheetNo) {
		this.refSheetNo = refSheetNo;
	}

	public String getRefSheetName() {
		return refSheetName;
	}

	public void setRefSheetName(String refSheetName) {
		this.refSheetName = refSheetName;
	}

	public List<String> getRefDataList() {
		return refDataList;
	}

	public void setRefDataList(List<String> refDataList) {
		this.refDataList = refDataList;
	}

	public HashMap<String, List<String>> getRefDependentLov() {
		return refDependentLov;
	}

	public void setRefDependentLov(HashMap<String, List<String>> refDependentLov) {
		this.refDependentLov = refDependentLov;
	}

	public List<ExcelColumnDefinition> getDependentColumnDef() {
		return dependentColumnDef;
	}

	public void setDependentColumnDef(List<ExcelColumnDefinition> dependentColumnDef) {
		this.dependentColumnDef = dependentColumnDef;
	}
	
}
