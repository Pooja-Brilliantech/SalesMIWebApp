package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.JourneyType;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.WsJourneyTypes;

public interface JourneyTypeDao {

	List<JourneyType> getJourneyList(int c_id, int roleId);

	boolean editJourneyType(String journeyName, int callTarget, int role_id,int c_id,String expense_config,int u_id);

	boolean deleteJourneyType(int detailId, int c_id);

	boolean updateJourneyInformation(int detailId, String journeyName, int calltarget, int c_id);
}
