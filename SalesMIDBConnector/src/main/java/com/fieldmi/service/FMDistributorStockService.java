package com.fieldmi.service;
import com.fieldmi.stubs.WsDStockDetailsList;
import com.fieldmi.stubs.WsDistributorLocationList;
import com.softtantra.salesapp.pojo.DistributorStockDetails;
import com.softtantra.salesapp.pojo.SecondaryStockGRN;

public interface FMDistributorStockService {
	
	boolean addSecondaryStockGRN(SecondaryStockGRN secondaryStockGRN,String data);
	
	public DistributorStockDetails addNewDStock(DistributorStockDetails distributorStockDetails, int c_id, int u_id,
			String username);
	
	public WsDistributorLocationList getDistributorLocationList(WsDistributorLocationList wsDistributorLocationList);
	
	public WsDStockDetailsList getDistributorStockDetails(WsDistributorLocationList wsDistributorLocationList);

}
