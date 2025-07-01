package com.fieldmi.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMAssignedTruckDao;
import com.fieldmi.service.FMAssignedTruckService;
import com.fieldmi.stubs.WsRouteWiseVehicleInput;
import com.fieldmi.stubs.WsRouteWiseVehicleList;
import com.fieldmi.stubs.WsUnderLoad;
import com.fieldmi.stubs.WsgetVehicleRouteList;
import com.softtantra.salesapp.pojo.AssignedTruckList;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.servicemi.pojo.SM_ManageVehicles;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.WsAddAssignedTruckInput;
import com.softtantra.ws.WsUpdateAssignedTruckInput;
import com.softtantra.ws.WsgetOrderTruckAssignedDetails;
import com.softtantra.ws.WsgetOrderTruckAssignedDetailsOutput;

public class FMAssignedTruckServiceImpl implements FMAssignedTruckService {

	@Autowired
	FMAssignedTruckDao fmAssignedTruckDao;

	public void setFmAssignedTruckDao(FMAssignedTruckDao fmAssignedTruckDao) {
		this.fmAssignedTruckDao = fmAssignedTruckDao;
	}

	@Override
	public WsgetVehicleRouteList getVehicleRouteList(int companyId, int pageNo) {

		return fmAssignedTruckDao.getVehicleRouteList(companyId, pageNo);
	}

	@Override
	public WsRouteWiseVehicleList getRouteWiseVehicleList(int company_id,String route,int pageNo) {
		
		return fmAssignedTruckDao.getRouteWiseVehicleList(company_id,route,pageNo);
	}

	@Override
	public AssignedTruckList addAssignedTruckList(WsAddAssignedTruckInput wsAddAssignedTruckInput) throws Exception {
		
		return fmAssignedTruckDao.addAssignedTruckList(wsAddAssignedTruckInput);
	}

	@Override
	public boolean updateTruckStatus(int assigned_truck_id, int c_id, String status) {
		
		return fmAssignedTruckDao.updateTruckStatus(assigned_truck_id,c_id,status);
	}

	@Override
	public AssignedTruckList updateAssignedTruckList(WsUpdateAssignedTruckInput wsUpdateAssignedTruckInput) throws Exception{
		
		return fmAssignedTruckDao.updateAssignedTruckList(wsUpdateAssignedTruckInput);
	}

	@Override
	public List<Number> getSalesOrderListForTruck(int assigned_truck_id, int c_id) {
		
		return fmAssignedTruckDao.getSalesOrderListForTruck(assigned_truck_id, c_id);
	}

	@Override
	public ArrayList<WsgetOrderTruckAssignedDetailsOutput> getOrderTruckAssignedDetails(
			WsgetOrderTruckAssignedDetails wsgetOrderTruckAssignedDetails) {
		
		return fmAssignedTruckDao.getOrderTruckAssignedDetails(wsgetOrderTruckAssignedDetails);
	}

	@Override
	public WsUnderLoad getUnderLoadDetails(String getUnderLoadDetails, int c_id) {
		
		return fmAssignedTruckDao.getUnderLoadDetails(getUnderLoadDetails, c_id);
	}

	@Override
	public List<SalesMaster> getSalesOrderObjectListForTruck(int assigned_truck_id, int c_id) {
		
		return fmAssignedTruckDao.getSalesOrderObjectListForTruck(assigned_truck_id, c_id);
	}

	@Override
	public SM_ManageVehicles getManageVehicle(int vehicle_master_id, int c_id) {
		
		return fmAssignedTruckDao.getManageVehicle(vehicle_master_id, c_id);
	}

	@Override
	public boolean deleteEmptyTrucks(int c_id) {
		
		return fmAssignedTruckDao.deleteEmptyTrucks(c_id);
	}

	@Override
	public boolean updateCapacityForTruck(int assigned_truck_id, double occupiedCapacity, int c_id) {
		
		return fmAssignedTruckDao.updateCapacityForTruck(assigned_truck_id,occupiedCapacity,c_id);
	}
	

}
