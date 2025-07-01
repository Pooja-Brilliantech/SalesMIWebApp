package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.DistributorPermissions;
import com.softtantra.salesapp.pojo.MobilePermissions;
import com.softtantra.salesapp.pojo.RolePermissions;

public interface FMFeatureService {

	boolean addFeature(RolePermissions objclassRolePermissions);

	boolean deleteFeature(RolePermissions objclassRolePermissions);

	boolean updateFeature(RolePermissions objclassRolePermissions);
	
	boolean addDistFeature(DistributorPermissions objclassDistributorPermissions);

	boolean deleteDistFeature(DistributorPermissions objclassDistributorPermissions);

	boolean updateDistFeature(DistributorPermissions objclassDistributorPermissions);

	List<RolePermissions> getFeatures(int product_id);

	boolean addMobileFeature(MobilePermissions objRolePerm);

	boolean updateMobileFeature(MobilePermissions objRolePerm);

	boolean deleteMobileFeature(MobilePermissions rolePermissions);
	
	List getObjectList(String query, int iNITIAL, int rECORD_SIZE);
}
