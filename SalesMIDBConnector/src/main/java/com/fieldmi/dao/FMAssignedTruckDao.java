package com.fieldmi.dao;

import java.util.ArrayList;
import java.util.List;

import com.fieldmi.stubs.WsRouteWiseVehicleList;
import com.fieldmi.stubs.WsUnderLoad;
import com.fieldmi.stubs.WsgetVehicleRouteList;
import com.softtantra.salesapp.pojo.AssignedTruckList;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.servicemi.pojo.SM_ManageVehicles;
import com.softtantra.ws.WsAddAssignedTruckInput;
import com.softtantra.ws.WsUpdateAssignedTruckInput;
import com.softtantra.ws.WsgetOrderTruckAssignedDetails;
import com.softtantra.ws.WsgetOrderTruckAssignedDetailsOutput;

public interface FMAssignedTruckDao {

	WsgetVehicleRouteList getVehicleRouteList(int companyId, int pageNo);

	WsRouteWiseVehicleList getRouteWiseVehicleList(int company_id,String route,int pageNo);

	AssignedTruckList addAssignedTruckList(WsAddAssignedTruckInput wsAddAssignedTruckInput) throws Exception;

	boolean updateTruckStatus(int assigned_truck_id, int c_id, String status);

	AssignedTruckList updateAssignedTruckList(WsUpdateAssignedTruckInput wsUpdateAssignedTruckInput) throws Exception;

	List<Number> getSalesOrderListForTruck(int assigned_truck_id, int c_id);

	ArrayList<WsgetOrderTruckAssignedDetailsOutput> getOrderTruckAssignedDetails(
			WsgetOrderTruckAssignedDetails wsgetOrderTruckAssignedDetails);

	WsUnderLoad getUnderLoadDetails(String assigned_truck_id, int c_id);

	List<SalesMaster> getSalesOrderObjectListForTruck(int assigned_truck_id, int c_id);
	
	boolean deleteEmptyTrucks(int c_id);
	
	boolean updateCapacityForTruck(int assigned_truck_id, double occupiedCapacity, int c_id);
	
	SM_ManageVehicles getManageVehicle(int vehicle_master_id, int c_id);

}
