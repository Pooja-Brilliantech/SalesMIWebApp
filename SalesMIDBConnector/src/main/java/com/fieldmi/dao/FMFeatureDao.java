package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.DistributorPermissions;
import com.softtantra.salesapp.pojo.MobilePermissions;
import com.softtantra.salesapp.pojo.RolePermissions;

public interface FMFeatureDao {

	public boolean addFeature(RolePermissions objclassRolePermissions);

	public boolean deleteFeature(RolePermissions objclassRolePermissions);

	public boolean updateFeature(RolePermissions objclassRolePermissions);

	public List<RolePermissions> getFeatures(int product_id);

	public boolean addMobileFeature(MobilePermissions objMobilePermissions);

	public boolean updateMobileFeature(MobilePermissions objMobilePermissions);

	public boolean deleteMobileFeature(MobilePermissions objMobilePermissions);
	
	public boolean addDistFeature(DistributorPermissions objclassDistributorPermissions);

	public boolean deleteDistFeature(DistributorPermissions objclassDistributorPermissions);

	public boolean updateDistFeature(DistributorPermissions objclassDistributorPermissions);

	public List getObjectList(String query, int iNITIAL, int rECORD_SIZE);
}
