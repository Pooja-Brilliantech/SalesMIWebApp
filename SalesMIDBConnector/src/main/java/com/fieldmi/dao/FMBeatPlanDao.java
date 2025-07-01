package com.fieldmi.dao;

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

public interface FMBeatPlanDao {

	BeatPlanMaster addBeatPlanDistributorWise(WsBeatPlanInput wsBeatPlanInput, int id);

	WsBeat getBeatMaster(int bId);

	int addBeatPlanDetailsDistributorWise(WsBeatPlanDetailsInput wsBeatPlanDetailsInput, int id, List<BeatPlanDetails> beatPlanDetailsList)throws Exception;

	BeatPlanMaster addBeatPlanDetailsCustomerWise(WsBeatInputCustomerWise wsBeatInputCustomerWise, int id,List<BeatPlanDetails> beatPlanDetailsList, BeatPlanMaster beatPlanMaster) throws Exception;

	BeatPlanMaster getBeatPieChartData(int c_id, int u_id, String value);

	WsBeatMasterList getBeatPlanListWithMaster(Credentials creadentials)throws Exception;

	boolean updateBeatPlanMaster(WsUpdateBeat wsupdateBeat)throws Exception;

	WsBeatMasterList getBeatMasterReportForSil(WsSalesOrderReportInput wsSalesOrderReportInput, int id);

	List<WsBeatList> getBeatDetailsReportForSil(WsSalesDetailsReportInput wsSalesDetailsReportInput, int id);

	boolean deleteBeatMaster(int beatPlanMasterId, int c_id,int someValue);

	int checkIfExists(int beatPlanMasterId);

	List<CustomerDetails> getFilterCustomerList(String distributor_id, int c_id, int user_id,String route_id);

	List<Route> getRouteList(String distributor_id, int c_id, int user_id);

	BeatPlanMaster getBeatPlanMaster(int expenseMasterId);

	List<BeatPlanMaster> getBeatPlanMasterListDateWise(Date sDate, Date eDate, int eId);

}
