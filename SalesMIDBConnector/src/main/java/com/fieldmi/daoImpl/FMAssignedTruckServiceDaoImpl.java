package com.fieldmi.daoImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMAssignedTruckDao;
import com.fieldmi.dao.WorkFlowAuditLogDao;
import com.fieldmi.dao.WorkFlowTaskStatusDao;
import com.fieldmi.stubs.WsRouteWiseVehicle;
import com.fieldmi.stubs.WsRouteWiseVehicleList;
import com.fieldmi.stubs.WsUnderLoad;
import com.fieldmi.stubs.WsVehicleRouteListOutput;
import com.fieldmi.stubs.WsgetVehicleRouteList;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.SalesMIDBUtils;
import com.softtantra.salesapp.pojo.AssignedTruckList;
import com.softtantra.salesapp.pojo.SalesMaster;
import com.softtantra.salesapp.pojo.VehicleRoute;
import com.softtantra.servicemi.pojo.SM_ManageVehicles;
import com.softtantra.ws.WsAddAssignedTruckInput;
import com.softtantra.ws.WsAssignedTruck;
import com.softtantra.ws.WsAssignedTruckList;
import com.softtantra.ws.WsUpdateAssignedTruckInput;
import com.softtantra.ws.WsgetOrderTruckAssignedDetails;
import com.softtantra.ws.WsgetOrderTruckAssignedDetailsOutput;

public class FMAssignedTruckServiceDaoImpl implements FMAssignedTruckDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CommonMethods commonMethods;

	@Autowired
	WorkFlowTaskStatusDao workFlowTaskStatusDao;

	@Autowired
	WorkFlowAuditLogDao workFlowAuditLogDao;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session initiateSession() {
		return sessionFactory.openSession();
	}

	private void destroySession(Session session) {
		if (session != null) {

			if (session.isDirty())
				session.flush();
			session.close();
		}
	}

	public void setCommonMethods(CommonMethods commonMethods) {
		this.commonMethods = commonMethods;
	}

	static Properties sapUrlProperties = new Properties();
	static {
		try {
			sapUrlProperties.load(
					FMAssignedTruckServiceDaoImpl.class.getClassLoader().getResourceAsStream("/sapUrls.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FieldMILogger.error(FMAssignedTruckServiceDaoImpl.class.getName(), e);
		}
	}

	@Override
	public WsgetVehicleRouteList getVehicleRouteList(int companyId, int pageNo) {
		Session session = initiateSession();

		WsgetVehicleRouteList routeList = new WsgetVehicleRouteList();

		List<VehicleRoute> list = null;
		int total_pages = 0;

		String query = "";
		query = "From VehicleRoute vr where company_id=" + companyId
				+ " and status=1 and EXISTS( SELECT  1 FROM  SM_ManageVehicles sm WHERE  sm.route = vr.route )";

		try {

			if (pageNo != -1)
				list = commonMethods.quaryListPagination(session, query, pageNo, 1000);
			else
				list = session.createQuery(query).list();

			for (VehicleRoute vehicleRoute : list) {

				WsVehicleRouteListOutput wsvehicleRoute = new WsVehicleRouteListOutput();
				wsvehicleRoute.setShippingType(vehicleRoute.getShippingType());
				wsvehicleRoute.setShippingTypeDesc(vehicleRoute.getShippingTypeDesc());
				wsvehicleRoute.setDescription(vehicleRoute.getDescription());
				wsvehicleRoute.setStageNumber(vehicleRoute.getStageNumber());
				wsvehicleRoute.setDeparturePoint(vehicleRoute.getDeparturePoint());
				wsvehicleRoute.setDestinationPoint(vehicleRoute.getDestinationPoint());
				wsvehicleRoute.setCountry(vehicleRoute.getCountry());
				wsvehicleRoute.setRouteId(vehicleRoute.getRouteId());
				wsvehicleRoute.setRoute(vehicleRoute.getRoute());
				routeList.getWsVehicleRouteList().add(wsvehicleRoute);
			}

			total_pages = commonMethods.getTotalPagesCountSql(session,
					"select count(vehicleRouteId) FROM  vehicle_route where company_id=" + companyId + " and status=1");

			routeList.setTotalRecords(total_pages);
			routeList.setPage_No(pageNo);
			routeList.setRecordsPerPage(1000);

			return routeList;

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return routeList;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public WsRouteWiseVehicleList getRouteWiseVehicleList(int company_id, String route, int pageNo) {

		Session session = initiateSession();
		long cId = company_id;
		WsRouteWiseVehicleList routeList = new WsRouteWiseVehicleList();

		List list = null;
		int total_pages = 0;

		String query = "select s.sm_manage_vehicles_id,s.vehicle_no,s.driver_name,s.driver_mobile,s.tonnage,s.margin,s.country_id,s.state_id,s.city_id,s.route,s.description,s.departurePoint,s.destinationPoint,a.available_capacity,a.total_capacity,a.occupied_capacity,a.assigned_truck_id from SM_ManageVehicles s,AssignedTruckList a where s.route='"
				+ route + "' and s.company_id=" + company_id
				+ " and a.vehicle_master_id=s.sm_manage_vehicles_id and a.status_of_truck!='Completed'";

		try {

			if (pageNo != -1)
				list = commonMethods.quaryListPagination(session, query, pageNo,1000);
			else
				list = session.createQuery(query).list();

			total_pages = commonMethods.getTotalPagesCountSql(session,
					"select count(sm_manage_vehicles_id) from sm_manage_vehicles where company_id=" + company_id
							+ " and status=1 and route='" + route + "'");

			if (list != null && list.size() > 0) {

				ArrayList<String> vehicleList = new ArrayList();
				for (Object object : list) {

					WsRouteWiseVehicle wsRouteWiseVehicle = new WsRouteWiseVehicle();
					Object[] obj = (Object[]) object;
					wsRouteWiseVehicle.setSm_manage_vehicles_id((int) obj[0]);
					wsRouteWiseVehicle.setVehicle_no((String) obj[1]);
					vehicleList.add("" + ((int)obj[0]));
					wsRouteWiseVehicle.setDriver_name((String) obj[2]);
					wsRouteWiseVehicle.setDriver_mobile((String) obj[3]);
					
					wsRouteWiseVehicle.setTonnage((int) obj[4]);
					wsRouteWiseVehicle.setMargin((int) obj[5]);
					wsRouteWiseVehicle.setCountry_id((int) obj[6]);
					wsRouteWiseVehicle.setState_id((int) obj[7]);
					wsRouteWiseVehicle.setCity_id((int) obj[8]);
					wsRouteWiseVehicle.setRoute((String) obj[9]);
					wsRouteWiseVehicle.setDescription((String) obj[10]);
					wsRouteWiseVehicle.setDeparturePoint((String) obj[11]);
					wsRouteWiseVehicle.setDestinationPoint((String) obj[12]);
					
					BigDecimal avaCapd = new BigDecimal(obj[13].toString());
					BigDecimal avCap = avaCapd.setScale(2, BigDecimal.ROUND_UP);
					wsRouteWiseVehicle.setAvailable_capacity(avCap.doubleValue());
					
					wsRouteWiseVehicle.setTotal_capacity((double) obj[14]);
					
					BigDecimal occCapD = new BigDecimal(obj[15].toString());
					BigDecimal occCap = occCapD.setScale(2, BigDecimal.ROUND_UP);
					wsRouteWiseVehicle.setOccupied_capacity(occCap.doubleValue());
					wsRouteWiseVehicle.setAssigned_truck_id((int) obj[16]);
					routeList.getWsRouteWiseVehicle().add(wsRouteWiseVehicle);

				}
				
				List<SM_ManageVehicles> vMasterList = commonMethods.paginationForVehicleMaster(session, 0, route, cId);

				for (SM_ManageVehicles obj : vMasterList) {
					
					if (!vehicleList.contains(""+obj.getSm_manage_vehicles_id())) {
						
						WsRouteWiseVehicle wsRouteWiseVehicle = new WsRouteWiseVehicle();
						wsRouteWiseVehicle.setSm_manage_vehicles_id(obj.getSm_manage_vehicles_id());
						wsRouteWiseVehicle.setVehicle_no(obj.getVehicle_no());
						wsRouteWiseVehicle.setDriver_name(obj.getDriver_name());
						wsRouteWiseVehicle.setDriver_mobile(obj.getDriver_mobile());
						wsRouteWiseVehicle.setTonnage(obj.getTonnage());
						wsRouteWiseVehicle.setMargin(obj.getMargin());
						wsRouteWiseVehicle.setCountry_id(obj.getCountry_id());
						wsRouteWiseVehicle.setState_id(obj.getState_id());
						wsRouteWiseVehicle.setCity_id(obj.getCity_id());
						wsRouteWiseVehicle.setRoute(obj.getRoute());
						wsRouteWiseVehicle.setDescription(obj.getDescription());
						wsRouteWiseVehicle.setDeparturePoint(obj.getDeparturePoint());
						wsRouteWiseVehicle.setDestinationPoint(obj.getDestinationPoint());
						wsRouteWiseVehicle.setOccupied_capacity(0.0);
						wsRouteWiseVehicle.setAssigned_truck_id(0);
						
						
						BigDecimal avaCapd = new BigDecimal(obj.getTonnage());
						BigDecimal avCap = avaCapd.setScale(2, BigDecimal.ROUND_UP);
						wsRouteWiseVehicle.setAvailable_capacity(avCap.doubleValue());
						wsRouteWiseVehicle.setTotal_capacity(obj.getTonnage());

						routeList.getWsRouteWiseVehicle().add(wsRouteWiseVehicle);
					}
					
				}

			} else {

				List<SM_ManageVehicles> vMasterList = new ArrayList<SM_ManageVehicles>();
				if (pageNo != -1)
					vMasterList =  commonMethods.paginationForVehicleMaster(session, pageNo, route, cId);
				else
					vMasterList =  commonMethods.paginationForVehicleMaster(session, 0, route, cId);

				for (SM_ManageVehicles obj : vMasterList) {
					
					WsRouteWiseVehicle wsRouteWiseVehicle = new WsRouteWiseVehicle();
					wsRouteWiseVehicle.setSm_manage_vehicles_id(obj.getSm_manage_vehicles_id());
					wsRouteWiseVehicle.setVehicle_no(obj.getVehicle_no());
					wsRouteWiseVehicle.setDriver_name(obj.getDriver_name());
					wsRouteWiseVehicle.setDriver_mobile(obj.getDriver_mobile());
					wsRouteWiseVehicle.setTonnage(obj.getTonnage());
					wsRouteWiseVehicle.setMargin(obj.getMargin());
					wsRouteWiseVehicle.setCountry_id(obj.getCountry_id());
					wsRouteWiseVehicle.setState_id(obj.getState_id());
					wsRouteWiseVehicle.setCity_id(obj.getCity_id());
					wsRouteWiseVehicle.setRoute(obj.getRoute());
					wsRouteWiseVehicle.setDescription(obj.getDescription());
					wsRouteWiseVehicle.setDeparturePoint(obj.getDeparturePoint());
					wsRouteWiseVehicle.setDestinationPoint(obj.getDestinationPoint());
					wsRouteWiseVehicle.setOccupied_capacity(0.0);
					wsRouteWiseVehicle.setAssigned_truck_id(0);
					
					
					BigDecimal avaCapd = new BigDecimal(obj.getTonnage());
					BigDecimal avCap = avaCapd.setScale(2, BigDecimal.ROUND_UP);
					wsRouteWiseVehicle.setAvailable_capacity(avCap.doubleValue());
					wsRouteWiseVehicle.setTotal_capacity(obj.getTonnage());

					routeList.getWsRouteWiseVehicle().add(wsRouteWiseVehicle);

				}

			}
			routeList.setPage_No(pageNo);
			routeList.setTotalRecords(total_pages);
			routeList.setRecordsPerPage(1000);

			return routeList;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return routeList;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public AssignedTruckList addAssignedTruckList(WsAddAssignedTruckInput wsAddAssignedTruckInput) throws Exception {

		Session session = initiateSession();
		try {

			WsAssignedTruck wsAssignedtruck = wsAddAssignedTruckInput.getAssignedTruck();
			if (wsAssignedtruck != null) {

				AssignedTruckList assignedtruckList = new AssignedTruckList();
				double availabelCapcity = wsAssignedtruck.getTotal_capacity() - wsAssignedtruck.getOccupied_capacity();
				assignedtruckList.setAvailable_capacity(availabelCapcity);
				assignedtruckList.setCity_id(wsAssignedtruck.getCity_id());
				assignedtruckList.setCompany_id(wsAssignedtruck.getCompany_id());
				assignedtruckList.setOccupied_capacity(wsAssignedtruck.getOccupied_capacity());
				assignedtruckList.setTotal_capacity(wsAssignedtruck.getTotal_capacity());
				assignedtruckList.setCountry_id(wsAssignedtruck.getCountry_id());
				assignedtruckList.setState_id(wsAssignedtruck.getState_id());
				assignedtruckList.setCreated_by(wsAddAssignedTruckInput.getUser_id());

				Calendar cal = Calendar.getInstance();
				assignedtruckList.setCreated_date(cal.getTime());
				assignedtruckList.setUpdated_date(cal.getTime());
				assignedtruckList.setStatus(1);
				assignedtruckList.setVehicle_master_id(wsAssignedtruck.getVehicle_master_id());
				assignedtruckList.setStatus_of_truck(SalesMIDBUtils.COMPLETED);

				int margin = (int) session
						.createSQLQuery("select margin from sm_manage_vehicles where sm_manage_vehicles_id="
								+ wsAssignedtruck.getVehicle_master_id())
						.uniqueResult();
				assignedtruckList.setMargin(margin);
				if ((wsAssignedtruck.getTotal_capacity() + assignedtruckList.getMargin() >= wsAssignedtruck
						.getOccupied_capacity())
						&& (wsAssignedtruck.getOccupied_capacity() >= wsAssignedtruck.getTotal_capacity())) {

					assignedtruckList.setStatus_of_truck(SalesMIDBUtils.COMPLETED);
				}
				
				if( assignedtruckList.getOccupied_capacity() <= (assignedtruckList.getTotal_capacity() + assignedtruckList.getMargin()) ) {
					
					session.saveOrUpdate(assignedtruckList);

					Query hQuery = session.createQuery(
							"update SalesMaster set assigned_truck_id=" + assignedtruckList.getAssigned_truck_id()
									+ ", isOrderClubbed=" + wsAddAssignedTruckInput.getIsOrderClubbed() + ", freightRate="
									+ wsAddAssignedTruckInput.getFreight_rate() + " where sales_master_id="
									+ wsAddAssignedTruckInput.getSales_master_id_to_add());

					hQuery.executeUpdate();

					return assignedtruckList;
				} else {
					
					throw new Exception("Truck overload; cannot add the sales order");
				}
				
			}

			return null;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public AssignedTruckList updateAssignedTruckList(WsUpdateAssignedTruckInput wsUpdateAssignedTruckInput) throws Exception {

		Session session = initiateSession();
		try {

			String queryBuilder = "select ass.assigned_truck_id,ass.total_capacity,ass.available_capacity,ass.occupied_capacity,ass.city_id,ass.country_id,ass.state_id,ass.vehicle_master_id,ass.status_of_truck,mv.margin from "
					+ "AssignedTruckList ass,SM_ManageVehicles mv where ass.vehicle_master_id=mv.sm_manage_vehicles_id and "
					+ "ass.company_id = " + wsUpdateAssignedTruckInput.getCompany_id() + " and"
					+ " ass.assigned_truck_id=" + wsUpdateAssignedTruckInput.getAssigned_truck_id();

			WsAssignedTruckList wsAssignedTruckList = new WsAssignedTruckList();

			List list = session.createQuery(queryBuilder.toString()).list();
			converToObject(session, list, wsAssignedTruckList);
			if (wsAssignedTruckList.getWsAssignedTruckList() != null
					&& wsAssignedTruckList.getWsAssignedTruckList().size() > 0) {

				WsAssignedTruck wsAssignedtruck = wsAssignedTruckList.getWsAssignedTruckList().get(0);
				if (wsAssignedtruck != null) {

					if (wsAssignedtruck.getTotal_capacity() > wsAssignedtruck.getAvailable_capacity()) {

						double newAvailableCapacity = (((wsAssignedtruck.getTotal_capacity()
								+ wsAssignedtruck.getMargin()) - wsUpdateAssignedTruckInput.getSales_master_weight())
								- wsAssignedtruck.getAvailable_capacity());
						double availableCapacity = wsAssignedtruck.getAvailable_capacity()
								- wsUpdateAssignedTruckInput.getSales_master_weight();
						if (availableCapacity < 0) {
							availableCapacity = 0;
						}

						if (availableCapacity >= 0) {

							double occupiedCapacity = wsAssignedtruck.getOccupied_capacity()
									+ wsUpdateAssignedTruckInput.getSales_master_weight();

							AssignedTruckList assignedtruckList = new AssignedTruckList();
							assignedtruckList.setAssigned_truck_id(wsAssignedtruck.getAssigned_truck_id());
							assignedtruckList.setAvailable_capacity(availableCapacity);
							assignedtruckList.setCity_id(wsAssignedtruck.getCity_id());
							assignedtruckList.setCompany_id(wsUpdateAssignedTruckInput.getCompany_id().intValue());
							assignedtruckList.setOccupied_capacity(occupiedCapacity);
							assignedtruckList.setTotal_capacity(wsAssignedtruck.getTotal_capacity());
							assignedtruckList.setCountry_id(wsAssignedtruck.getCountry_id());
							assignedtruckList.setState_id(wsAssignedtruck.getState_id());
							assignedtruckList.setUpdated_by(wsUpdateAssignedTruckInput.getUser_id());
							Calendar cal = Calendar.getInstance();
							assignedtruckList.setUpdated_date(cal.getTime());
							assignedtruckList.setStatus(1);
							assignedtruckList.setStatus_of_truck(SalesMIDBUtils.COMPLETED);
							assignedtruckList.setMargin(wsAssignedtruck.getMargin());

							if ((wsAssignedtruck.getTotal_capacity() + wsAssignedtruck.getMargin() >= wsAssignedtruck
									.getOccupied_capacity())
									&& (occupiedCapacity >= wsAssignedtruck.getTotal_capacity())) {

								assignedtruckList.setStatus_of_truck(SalesMIDBUtils.COMPLETED);
							}
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							if( assignedtruckList.getOccupied_capacity() <= (assignedtruckList.getTotal_capacity() + assignedtruckList.getMargin()) ) {
								
								StringBuilder updateAssignedTruckquery = new StringBuilder(
										"update AssignedTruckList set available_capacity=")
												.append(assignedtruckList.getAvailable_capacity())
												.append(", occupied_capacity=")
												.append(assignedtruckList.getOccupied_capacity()).append(", updated_by=")
												.append(wsUpdateAssignedTruckInput.getUser_id()).append(", updated_date='")
												.append(sdf.format(cal.getTime())).append("', status_of_truck='")
												.append(assignedtruckList.getStatus_of_truck())
												.append("' where assigned_truck_id=")
												.append(wsAssignedtruck.getAssigned_truck_id());

								session.createQuery(updateAssignedTruckquery.toString()).executeUpdate();

								String query = "update SalesMaster set assigned_truck_id="
										+ assignedtruckList.getAssigned_truck_id() + ", isOrderClubbed="
										+ wsUpdateAssignedTruckInput.getIsOrderClubbed() + ", freightRate="
										+ wsUpdateAssignedTruckInput.getFreight_rate() + " where sales_master_id="
										+ wsUpdateAssignedTruckInput.getSales_master_id_to_add();
								session.createQuery(query).executeUpdate();

								return assignedtruckList;
							} else {
								
								throw new Exception("Truck overload; cannot add the sales order");
							}
							
							
						} else {
							FieldMILogger.warn(this.getClass().getName(), "New Available capacity is < 0"
									+ newAvailableCapacity + " for truck:" + wsAssignedtruck.getAssigned_truck_id());
						}
					} else {
						FieldMILogger.warn(this.getClass().getName(),
								"Total capacity is < Avaialble capacity" + wsAssignedtruck.getAvailable_capacity()
										+ " for truck:" + wsAssignedtruck.getAssigned_truck_id());
					}
				} else {
					FieldMILogger.warn(this.getClass().getName(), "Assigned truck is null");
				}

			} else {
				FieldMILogger.warn(this.getClass().getName(), "Assigned truck list size is 0");
			}

			return null;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public boolean updateTruckStatus(int assigned_truck_id, int c_id, String status) {

		Session session = initiateSession();
		try {

			Query query = session.createQuery(
					"update AssignedTruckList set status_of_truck=:status_of_truck where assigned_truck_id=:assigned_truck_id and company_id=:c_id")
					.setParameter("assigned_truck_id", assigned_truck_id).setParameter("status_of_truck", status)
					.setParameter("c_id", c_id);
			query.executeUpdate();
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public ArrayList<WsgetOrderTruckAssignedDetailsOutput> getOrderTruckAssignedDetails(
			WsgetOrderTruckAssignedDetails wsgetOrderTruckAssignedDetails) {

		Session session = initiateSession();

		try {

			String query = "select available_capacity,occupied_capacity,total_capacity,ass.state_id,ass.city_id,mv.vehicle_no,ass.assigned_truck_id,mv.margin from "
					+ "sales_master sm,assigned_truck_list ass,sm_manage_vehicles mv where sm.assigned_truck_id = ass.assigned_truck_id and"
					+ " ass.vehicle_master_id=mv.sm_manage_vehicles_id and sales_master_id="
					+ wsgetOrderTruckAssignedDetails.getSales_master_id();
			FieldMILogger.debug(this.getClass().getName(), query);

			List list = session.createSQLQuery(query).list();

			ArrayList<WsgetOrderTruckAssignedDetailsOutput> returnList = new ArrayList<>();

			for (Object object : list) {

				Object[] obj = (Object[]) object;
				WsgetOrderTruckAssignedDetailsOutput assignedTruckList = new WsgetOrderTruckAssignedDetailsOutput();
				String stateName = getStateName((int) obj[3]);
				String cityName = getCityName((int) obj[4]);
				assignedTruckList.setAvailable_capacity((double) obj[0]);
				assignedTruckList.setOccupied_capacity((double) obj[1]);
				assignedTruckList.setTotal_capacity((double) obj[2]);
				assignedTruckList.setState_id((int) obj[3]);
				assignedTruckList.setCity_id((int) obj[4]);
				assignedTruckList.setVehicle_no((String) obj[5]);
				assignedTruckList.setAssigned_truck_id((int) obj[6]);
				assignedTruckList.setMargin((int) obj[7]);
				assignedTruckList.setState(stateName);
				assignedTruckList.setCity(cityName);

				returnList.add(assignedTruckList);
			}

			return returnList;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}

	}

	private String getCityName(int cityId) {

		Session session = initiateSession();
		try {

			Query query = session.createSQLQuery("select city_name from cities where city_id=" + cityId);
			// FieldMILogger.debug(this.getClass().getName(),query);
			String name = (String) query.uniqueResult();
			return name;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	private String getStateName(int stateId) {
		Session session = initiateSession();
		try {

			Query query = session.createSQLQuery("select state_name from state where state_id=" + stateId);
			// FieldMILogger.debug(this.getClass().getName(),query);
			String name = (String) query.uniqueResult();
			return name;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	private void converToObject(Session session, List list, WsAssignedTruckList wsAssignedTruckList) {

		for (Object object : list) {

			Object[] obj = (Object[]) object;
			String vehicleNo = (String) session
					.createSQLQuery(
							"select vehicle_no from sm_manage_vehicles where sm_manage_vehicles_id=" + (Integer) obj[7])
					.uniqueResult();

			int margin = (int) session
					.createSQLQuery(
							"select margin from sm_manage_vehicles where sm_manage_vehicles_id=" + (Integer) obj[7])
					.uniqueResult();
			WsAssignedTruck assignedTruckDetail = new WsAssignedTruck();
			assignedTruckDetail.setAssigned_truck_id((Integer) obj[0]);
			assignedTruckDetail.setTotal_capacity((double) obj[1]);
			assignedTruckDetail.setAvailable_capacity((double) obj[2]);
			assignedTruckDetail.setOccupied_capacity((double) obj[3]);
			assignedTruckDetail.setCity_id((Integer) obj[4]);
			assignedTruckDetail.setCountry_id((Integer) obj[5]);
			assignedTruckDetail.setState_id((Integer) obj[6]);
			assignedTruckDetail.setVehicle_master_id((Integer) obj[7]);
			assignedTruckDetail.setVehicle_no(vehicleNo);
			assignedTruckDetail.setMargin(margin);
			assignedTruckDetail.setStatus_of_truck(obj[8].toString());
			wsAssignedTruckList.getWsAssignedTruckList().add(assignedTruckDetail);
		}

	}

	private List<Number> getSalesOrderListForTruck(Session session, int assigned_truck_id, int c_id) {

		try {

			List<Number> salesMasterIdList = new ArrayList<>();

			String query = "SELECT sales_master_id from" + " sales_master where" + " company_id="
					+ c_id + " and assigned_truck_id=" + assigned_truck_id;

			Query query1 = session.createSQLQuery(query);
			salesMasterIdList = query1.list();

			return salesMasterIdList;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		}

	}

	@Override
	public List<Number> getSalesOrderListForTruck(int assigned_truck_id, int c_id) {

		Session session = initiateSession();
		try {

			return getSalesOrderListForTruck(session, assigned_truck_id, c_id);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public WsUnderLoad getUnderLoadDetails(String assigned_truck_id, int c_id) {

		Session session = initiateSession();

		try {

			String query = "SELECT sm_manage_vehicles_id,mv.vehicle_no,ass.available_capacity,ass.total_capacity,ass.assigned_truck_id from"
					+ " sm_manage_vehicles mv,assigned_truck_list ass where"
					+ " mv.sm_manage_vehicles_id=ass.vehicle_master_id and ass.company_id=" + c_id
					+ " and ass.assigned_truck_id=" + assigned_truck_id + "";

			Query query1 = session.createSQLQuery(query);
			List list = query1.list();
			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				WsUnderLoad wsUnderLoad = new WsUnderLoad();
				try {

					wsUnderLoad.setVehicle_no((String) obj1[1]);
					wsUnderLoad.setSm_manage_vehicles_id((int) obj1[0]);
					
					BigDecimal avaCapd = new BigDecimal(obj1[2].toString());
					BigDecimal avCap = avaCapd.setScale(2, BigDecimal.ROUND_UP);
					wsUnderLoad.setAvailable_capacity(avCap.doubleValue());
					
					wsUnderLoad.setTotal_capacity((double) obj1[3]);
					wsUnderLoad.setAssigned_truck_id((int) obj1[4]);

				} catch (Exception e) {
					FieldMILogger.error(this.getClass().getName(), e);
				}
				return wsUnderLoad;

			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return null;
	}

	@Override
	public List<SalesMaster> getSalesOrderObjectListForTruck(int assigned_truck_id, int c_id) {

		Session session = initiateSession();
		try {

			String query = "from SalesMaster where company_id=" + c_id + " and assigned_truck_id="
					+ assigned_truck_id;

			Query query1 = session.createQuery(query);
			return query1.list();

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public SM_ManageVehicles getManageVehicle(int vehicle_master_id, int c_id) {
		
		Session session = initiateSession();
		try {

			String query = "from SM_ManageVehicles where company_id=" + c_id + " and sm_manage_vehicles_id="
					+ vehicle_master_id;

			Query query1 = session.createQuery(query);
			return ((SM_ManageVehicles)query1.uniqueResult());

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public boolean deleteEmptyTrucks(int c_id) {
		
		Session session = initiateSession();
		try {

			Query query1 = session.createSQLQuery("select atl.assigned_truck_id FROM assigned_truck_list atl WHERE company_id="+ c_id +" and NOT EXISTS(SELECT NULL FROM sales_master sm WHERE sm.assigned_truck_id = atl.assigned_truck_id)");
			
			List truckList = query1.list();
			for (Object assignedTruckList : truckList) {
				
				Query deleteQuery = session.createSQLQuery("delete from assigned_truck_list where company_id=" + c_id + " and assigned_truck_id="+((int)assignedTruckList));
				deleteQuery.executeUpdate();
			}
			
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public boolean updateCapacityForTruck(int assigned_truck_id, double occupiedCapacity,
			int c_id) {
		Session session = initiateSession();
		try {

			
			Query<AssignedTruckList> query1 = session.createQuery("FROM AssignedTruckList WHERE company_id="+ c_id +" and assigned_truck_id="+ assigned_truck_id);
			
			AssignedTruckList assignedTruck = query1.uniqueResult();
			double availableCapactity = assignedTruck.getTotal_capacity() - occupiedCapacity;
			
			assignedTruck.setOccupied_capacity(occupiedCapacity);
			assignedTruck.setAvailable_capacity(availableCapactity);
			session.update(assignedTruck);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}
}
