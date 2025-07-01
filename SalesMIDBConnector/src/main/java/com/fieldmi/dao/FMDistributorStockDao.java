package com.fieldmi.dao;

import com.fieldmi.stubs.WsDStockDetailsList;
import com.fieldmi.stubs.WsDistributorLocationList;
import com.softtantra.salesapp.pojo.DistributorStockDetails;
import com.softtantra.salesapp.pojo.SecondaryStockGRN;

public interface FMDistributorStockDao {

	boolean addSecondaryStockGRN(SecondaryStockGRN secondaryStockGRN, String data);

	DistributorStockDetails addNewDStock(DistributorStockDetails distributorStockDetails, int c_id, int u_id,
			String username);

	WsDistributorLocationList getDistributorLocationList(WsDistributorLocationList wsDistributorLocationList);

	WsDStockDetailsList getDistributorStockDetails(WsDistributorLocationList wsDistributorLocationList);

}
