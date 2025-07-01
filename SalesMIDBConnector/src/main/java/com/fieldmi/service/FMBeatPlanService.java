package com.fieldmi.service;

import java.util.Date;
import java.util.List;

import com.fieldmi.stubs.WsBeat;
import com.fieldmi.stubs.WsBeatList;
import com.fieldmi.stubs.WsBeatMasterList;
import com.fieldmi.stubs.WsSalesDetailsReportInput;
import com.fieldmi.stubs.WsSalesOrderReportInput;
import com.fieldmi.stubs.WsUpdateBeat;
import com.softtantra.salesapp.pojo.BeatPlanDetails;
import com.softtantra.salesapp.pojo.BeatPlanMaster;
import com.softtantra.salesapp.pojo.CustomerDetails;
import com.softtantra.salesapp.pojo.Route;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.WsBeatInputCustomerWise;
import com.softtantra.ws.WsBeatPlanDetailsInput;
import com.softtantra.ws.WsBeatPlanInput;

public interface FMBeatPlanService {

	public BeatPlanMaster addBeatPlanDistributorWise(WsBeatPlanInput wsBeatPlanInput,int id);
	
	public WsBeat getBeatMaster(int bId);
	
	public int addBeatPlanDetailsDistributorWise(WsBeatPlanDetailsInput wsBeatPlanDetailsInput,int id, List<BeatPlanDetails> beatPlanDetailsList) throws Exception;
	
	public BeatPlanMaster addBeatPlanDetailsCustomerWise(WsBeatInputCustomerWise wsBeatInputCustomerWise,int id,List<BeatPlanDetails> beatPlanDetailsList, BeatPlanMaster beatPlanMaster)throws Exception;
	
	BeatPlanMaster getBeatPieChartData(int c_id,int u_id,String value);
	
	public WsBeatMasterList getBeatPlanListWithMaster(Credentials creadentials)throws Exception;
	
	public boolean updateBeatPlanMaster (WsUpdateBeat wsupdateBeat) throws Exception;
	
	public WsBeatMasterList getBeatMasterReportForSil(WsSalesOrderReportInput creadentials,int id)throws Exception;
	
	public List<WsBeatList> getBeatDetailsReportForSil(WsSalesDetailsReportInput wsSalesDetailsReportInput,int id);
	
	public boolean deleteBeatMaster(int beatPlanMasterId,int c_id,int someValue);
	
	public int checkIfExists(int beatPlanMssterId);
	
	List<Route> getRouteList(String distributor_id, int c_id, int user_id);

	List<CustomerDetails> getFilterCustomerList(String distributor_id, int c_id, int user_id, String route_id);
	
	public BeatPlanMaster getBeatPlanMaster(int expenseMasterId);
	
	public List<BeatPlanMaster> getBeatPlanMasterListDateWise(Date sDate,Date eDate,int eId);

}
