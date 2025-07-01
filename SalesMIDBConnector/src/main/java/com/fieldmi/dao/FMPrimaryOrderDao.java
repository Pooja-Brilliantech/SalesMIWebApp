package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.PrimaryStockDetails;
import com.softtantra.salesapp.pojo.PrimaryStockGRN;

public interface FMPrimaryOrderDao {

	boolean addPrimaryStockGRN(PrimaryStockGRN primaryStockGRN,String data);

	List<NewPriceMaster> getProductWiseItemCode(String product_code, int c_id);

	int verifyImportedData(int c_id, int location_id, String product_code, String product_name,
			String item_code, String pack);

	PrimaryStockDetails addNewPStock(PrimaryStockDetails pDetails, int c_id, int u_id, String username);
}
