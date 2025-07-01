package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.ModeOfTravel;

public interface FMTravelDao {

	boolean saveModeOfTravel(ModeOfTravel travel, int c_id, int u_id);

	List<ModeOfTravel> getModeOfTravelList(int c_id, int roleId);

	boolean checkModeOfTravelConfiguration(String modeOfTravel, double modeOfTravelRate, int role_id, int c_id);

	boolean updateModeOfTravelConfiguration(ModeOfTravel travel, int c_id, int u_id);

	boolean deleteModeOfTravel(ModeOfTravel travel);

}
