package com.fieldmi.dao;

import java.util.List;

import com.fieldmi.stubs.WSZoneListOutput;
import com.fieldmi.stubs.WsDistrictDetailsOutput;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.ZoneWiseStates;
import com.softtantra.ws.Credentials;

public interface ZoneDao {

	WSZoneListOutput getZoneList(Credentials cre)throws Exception;

	boolean deleteZoneState(int id);

	List<ZoneWiseStates> getZoneStateData(int c_id);

	List<States> getStates(int countryId, String statesIds);

	List<ZoneWiseStates> getZoneWiseStateData(int intValue);

	WsDistrictDetailsOutput getStateDistrictList(Credentials cre);

}
