package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.FMFeatureDao;
import com.fieldmi.service.FMFeatureService;
import com.softtantra.salesapp.pojo.DistributorPermissions;
import com.softtantra.salesapp.pojo.MobilePermissions;
import com.softtantra.salesapp.pojo.RolePermissions;

public class FMFeatureServiceImpl implements FMFeatureService {

	@Autowired
	FMFeatureDao fmFeatureDao;

	public void setFmFeatureDao(FMFeatureDao fmFeatureDao) {
		this.fmFeatureDao = fmFeatureDao;
	}

	@Override
	@Transactional
	public boolean addFeature(RolePermissions objclassRolePermissions) {
		
		return fmFeatureDao.addFeature(objclassRolePermissions);
	}

	@Override
	@Transactional
	public boolean deleteFeature(RolePermissions objclassRolePermissions) {
		
		return fmFeatureDao.deleteFeature(objclassRolePermissions);
	}

	@Override
	@Transactional
	public boolean updateFeature(RolePermissions objclassRolePermissions) {
		
		return fmFeatureDao.updateFeature(objclassRolePermissions);
	}

	@Override
	@Transactional
	public List<RolePermissions> getFeatures(int product_id) {
		
		return fmFeatureDao.getFeatures(product_id);
	}

	@Override
	public boolean addMobileFeature(MobilePermissions objRolePerm) {
		// TODO Auto-generated method stub
		return fmFeatureDao.addMobileFeature(objRolePerm);
	}

	@Override
	public boolean updateMobileFeature(MobilePermissions objRolePerm) {
		// TODO Auto-generated method stub
		return fmFeatureDao.updateMobileFeature(objRolePerm);
	}

	@Override
	public boolean deleteMobileFeature(MobilePermissions rolePermissions) {
		// TODO Auto-generated method stub
		return fmFeatureDao.deleteMobileFeature(rolePermissions);
	}

	@Override
	public List getObjectList(String query, int iNITIAL, int rECORD_SIZE) {
		
		return fmFeatureDao.getObjectList(query,iNITIAL,rECORD_SIZE);
	}

	@Override
	public boolean addDistFeature(DistributorPermissions objclassDistributorPermissions) {
		return fmFeatureDao.addDistFeature(objclassDistributorPermissions);
	}

	@Override
	public boolean deleteDistFeature(DistributorPermissions objclassDistributorPermissions) {
		return fmFeatureDao.deleteDistFeature(objclassDistributorPermissions);
	}

	@Override
	public boolean updateDistFeature(DistributorPermissions objclassDistributorPermissions) {
		return fmFeatureDao.updateDistFeature(objclassDistributorPermissions);
	}

}
