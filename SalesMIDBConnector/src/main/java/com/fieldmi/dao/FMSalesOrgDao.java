package com.fieldmi.dao;

import com.fieldmi.stubs.WsDistOrg;
import com.fieldmi.stubs.WsDistrictDetailsOutput;
import com.fieldmi.stubs.WsFetchDistrictTaluka;
import com.fieldmi.stubs.WsFetchSalesOrg;
import com.fieldmi.stubs.WsSalesDistrict;
import com.fieldmi.stubs.WsSalesOrgOutPut;

public interface FMSalesOrgDao {

	WsSalesOrgOutPut getSalesOrgList(WsFetchSalesOrg wsFetchSalesOrgInput);

	WsSalesOrgOutPut getDistributorChannelList(WsFetchSalesOrg wsFetchSalesOrgInput);

	WsSalesOrgOutPut getDivisionList(WsFetchSalesOrg wsFetchSalesOrgInput);

	WsDistrictDetailsOutput getDistrictList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput);

	WsSalesDistrict getSalesDistrictList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput);

	WsDistOrg getSalesDistWiseSalesGroupList(WsFetchDistrictTaluka wsFetchDistrictTalukaInput);

	WsSalesOrgOutPut getSalesGroupWiseSalesOfficeList(WsFetchSalesOrg wsFetchSalesOrgInput);

}
