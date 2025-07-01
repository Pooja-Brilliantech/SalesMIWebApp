package com.fieldmi.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.fieldmi.dao.FMBeatPlanDao;

import com.fieldmi.service.FMBeatPlanService;
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

public class FMBeatPlanServiceImpl implements FMBeatPlanService{

	@Autowired
	FMBeatPlanDao fmbeatplanDao;

	public void setFmbeatplanDao(FMBeatPlanDao fmbeatplanDao) {
		this.fmbeatplanDao = fmbeatplanDao;
	}

	@Override
	public BeatPlanMaster addBeatPlanDistributorWise(WsBeatPlanInput wsBeatPlanInput,int id) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.addBeatPlanDistributorWise(wsBeatPlanInput,id);
	}

	@Override
	public WsBeat getBeatMaster(int bId) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getBeatMaster(bId);
	}


	@Override
	public int addBeatPlanDetailsDistributorWise(WsBeatPlanDetailsInput wsBeatPlanDetailsInput, int id, List<BeatPlanDetails> beatPlanDetailsList)
			throws Exception {
		// TODO Auto-generated method stub
		return fmbeatplanDao.addBeatPlanDetailsDistributorWise(wsBeatPlanDetailsInput,id,beatPlanDetailsList);
	}

	@Override
	public BeatPlanMaster addBeatPlanDetailsCustomerWise(WsBeatInputCustomerWise wsBeatInputCustomerWise,int id, List<BeatPlanDetails> beatPlanDetailsList, BeatPlanMaster beatPlanMaster) throws Exception {
		
		return fmbeatplanDao.addBeatPlanDetailsCustomerWise(wsBeatInputCustomerWise,id, beatPlanDetailsList, beatPlanMaster);
	}

	@Override
	public BeatPlanMaster getBeatPieChartData(int c_id, int u_id, String value) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getBeatPieChartData( c_id, u_id, value);
	}


	@Override
	public WsBeatMasterList getBeatPlanListWithMaster(Credentials creadentials)throws Exception {
		return fmbeatplanDao.getBeatPlanListWithMaster(creadentials);
	}





	@Override
	public boolean updateBeatPlanMaster(WsUpdateBeat wsupdateBeat) throws Exception {
		// TODO Auto-generated method stub
		return fmbeatplanDao.updateBeatPlanMaster(wsupdateBeat);
	}





	@Override
	public WsBeatMasterList getBeatMasterReportForSil(WsSalesOrderReportInput wsSalesOrderReportInput, int id) throws Exception {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getBeatMasterReportForSil(wsSalesOrderReportInput,id);
	}





	@Override
	public List<WsBeatList> getBeatDetailsReportForSil(WsSalesDetailsReportInput wsSalesDetailsReportInput, int id) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getBeatDetailsReportForSil(wsSalesDetailsReportInput,id);
	}





	@Override
	public boolean deleteBeatMaster(int beatPlanMasterId, int c_id,int someValue) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.deleteBeatMaster(beatPlanMasterId,c_id,someValue);
	}





	@Override
	public int checkIfExists(int beatPlanMasterId) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.checkIfExists(beatPlanMasterId);
	}





	@Override
	public List<Route> getRouteList(String distributor_id, int c_id, int user_id) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getRouteList(distributor_id,c_id,user_id);
	}





	@Override
	public List<CustomerDetails> getFilterCustomerList(String distributor_id, int c_id, int user_id, String route_id) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getFilterCustomerList(distributor_id,c_id,user_id,route_id);
	}





	@Override
	public BeatPlanMaster getBeatPlanMaster(int expenseMasterId) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getBeatPlanMaster(expenseMasterId);
	}

	@Override
	public List<BeatPlanMaster> getBeatPlanMasterListDateWise(Date sDate, Date eDate, int eId) {
		// TODO Auto-generated method stub
		return fmbeatplanDao.getBeatPlanMasterListDateWise(sDate,eDate,eId);
	}

	

}
