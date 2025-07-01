package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.Profile;
import com.softtantra.salesapp.pojo.ProfileName;

public interface FMProfileService {

	int getTotalRecordCount(String string);

	List<?> getDataList(String sql, int iNITIAL, int rECORD_SIZE);

	List<Profile> getProfilesById(int profileNameId);

	public boolean updateProfileName(ProfileName profileName);
	
	void updateProfile(List<Profile> profileList);

	void addProfileName(ProfileName profileName);

	void addProfile(List<Profile> profileList);
	
	public List<ProfileName> getProfileList(int status, int c_id);
	
	boolean deleteProfile(int profileId);

	boolean checkIfProfileIsAssigned(int profileId);
	
}
