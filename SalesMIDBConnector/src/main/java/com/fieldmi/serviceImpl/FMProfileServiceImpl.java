package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.dao.FMProfileDao;
import com.fieldmi.service.FMProfileService;
import com.softtantra.salesapp.pojo.Profile;
import com.softtantra.salesapp.pojo.ProfileName;

public class FMProfileServiceImpl implements FMProfileService {

	@Autowired
	FMProfileDao fmProfileDao;

	public void setFmProfileDao(FMProfileDao fmProfileDao) {
		this.fmProfileDao = fmProfileDao;
	}

	@Override
	@Transactional
	public int getTotalRecordCount(String string) {
		return fmProfileDao.getTotalRecordCount(string);
	}

	@Override
	@Transactional
	public List<?> getDataList(String sql, int iNITIAL, int rECORD_SIZE) {
		return fmProfileDao.getDataList(sql, iNITIAL, rECORD_SIZE);
	}

	@Override
	public List<Profile> getProfilesById(int profileNameId) {
		return fmProfileDao.getProfilesById(profileNameId);
	}

	@Override
	public boolean updateProfileName(ProfileName profileName) {
		return fmProfileDao.updateProfileName(profileName);
	}

	@Override
	public void updateProfile(List<Profile> profileList) {
		fmProfileDao.updateProfile(profileList);
	}

	@Override
	public void addProfileName(ProfileName profileName) {
		fmProfileDao.addProfileName(profileName);
	}

	@Override
	public void addProfile(List<Profile> profileList) {
		fmProfileDao.addProfile(profileList);
	}

	@Override
	public List<ProfileName> getProfileList(int status, int c_id) {
		return fmProfileDao.getProfileList(status, c_id);
	}

	@Override
	public boolean deleteProfile(int profileId) {
		return fmProfileDao.deleteProfile(profileId);
	}

	@Override
	public boolean checkIfProfileIsAssigned(int profileId) {
		return fmProfileDao.checkIfProfileIsAssigned(profileId);
	}

}
