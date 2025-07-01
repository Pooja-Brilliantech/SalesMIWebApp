package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.ModeOfTravel;

public interface FMTravelService {
	
	public boolean saveModeOfTravel(ModeOfTravel travel,int c_id,int u_id);
	
	public List<ModeOfTravel> getModeOfTravelList(int c_id,int roleId);
	
	public boolean checkModeOfTravelConfiguration(String modeOfTravel,double modeOfTravelRate, int role_id,int c_id);
	
	public boolean updateModeOfTravelConfiguration(ModeOfTravel travel,int c_id,int u_id);
	
	public boolean deleteModeOfTravel(ModeOfTravel travel);
}
