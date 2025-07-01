package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.Profile;
import com.softtantra.salesapp.pojo.ProfileName;

public interface FMProfileDao {

	public int getTotalRecordCount(String string);

	public List getDataList(String sql, int iNITIAL, int rECORD_SIZE);
	
	public List<Profile> getProfilesById(int profileNameId);

	public boolean getProfilesForCompany(int companyId);

	public boolean addProfileName(ProfileName profileName);

	public boolean addProfile(List<Profile> profileList);

	public boolean updateProfileName(ProfileName profileName);

	public boolean updateProfile(List<Profile> profileList);

	public List<ProfileName> getProfileList(int status, int c_id);

	public boolean deleteProfile(int profileId);

	public boolean checkIfProfileIsAssigned(int profileId);

}
