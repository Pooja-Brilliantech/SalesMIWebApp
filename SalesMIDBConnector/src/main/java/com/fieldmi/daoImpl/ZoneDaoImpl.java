package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.ZoneDao;
import com.fieldmi.stubs.WSZoneListOutput;
import com.fieldmi.stubs.WsDistrictDetailsOutput;
import com.fieldmi.stubs.WsFetchSalesOrg;
import com.fieldmi.stubs.WsZoneDetailsList;
import com.fieldmi.stubs.WsZoneList;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.ZoneWiseStates;
import com.softtantra.ws.Credentials;

public class ZoneDaoImpl implements ZoneDao{

	
	@Autowired
	SessionFactory sessionFactory;
	
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
	@Autowired
	CommonMethods commonMethods;
	
	public void setCommonMethods(CommonMethods commonMethods) {
		this.commonMethods = commonMethods;
	}
	
	@Override
	public WSZoneListOutput getZoneList(Credentials cre)throws Exception  {
		// TODO Auto-generated method stub

		Session sessionForDB = initiateSession();
		
		
		try {
			
			List list = null;
			List list2=null;
			WSZoneListOutput outPut=new WSZoneListOutput();
			String query = "";
			String queryForDetails="";
			query = "select zId,zoneName,zoneCode From ZoneMaster where company_id=" + cre.getCompany_id() + " and status=1";
			
			list = commonMethods.quaryListPagination(sessionForDB, query, cre.getPage_no(),1000);
			if(list!=null)
			{
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					WsZoneList zList=new WsZoneList();
					zList.setzId((Integer)obj[0]);
					zList.setZoneName((String)obj[1]);
					zList.setZoneCode((String)obj[2]);
					zList.setBuId(0);
					
					queryForDetails="select countryId,stateId,cityId From ZoneDetails where zId="+obj[0];
					list2=commonMethods.quaryListPagination(sessionForDB, queryForDetails, cre.getPage_no(),1000);
					for(Object o:list2)
					{
						Object[] obj1 = (Object[]) o;
						WsZoneDetailsList dList=new WsZoneDetailsList();
						dList.setCountryId((Integer)obj1[0]);
						dList.setState((Integer)obj1[1]);
						dList.setCity((Integer)obj1[2]);
						zList.getZoneDetailsList().add(dList);
					}
					outPut.getZoneList().add(zList);
					outPut.setRecordsPerPage(1000);
					outPut.setPage_No(cre.getPage_no());
				}
				return outPut;
			}
			
		}catch(Exception e)
		{
			throw e;
		}finally
		{
			destroySession(sessionForDB);
		}
		return null;
	}

	@Override
	public boolean deleteZoneState(int id) {
		Session session = initiateSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query1 = session
					.createQuery("delete from   ZoneWiseStates   where id=" + id + "");
			query1.executeUpdate();

			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(),e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public List<ZoneWiseStates> getZoneStateData(int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			Query query;
			
			query = session
						.createQuery("From ZoneWiseStates where company_id=" + c_id + "");
			
			List list = query.list();
			if(list!=null && list.size()>0) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<States> getStates(int countryId, String statesIds) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<States> states = new ArrayList<States>();
		try {
			Query query;
			if (!statesIds.equals("")) {
				query = session
						.createSQLQuery("select state_id,state_name From states where country_id=" + countryId + " and state_id not in ("+statesIds+")");
			} else {
				query = session.createSQLQuery("select state_id,state_name From states where country_id="+countryId);
			}
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				States s = new States();
				Object[] object = (Object[]) list.get(i);
				s.setState_id((Integer) object[0]);
				s.setState_name((String) object[1]);
				states.add(s);
			}
			return states;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<ZoneWiseStates> getZoneWiseStateData(int c_id) {
		// TODO Auto-generated method stub

		Session sessionForDB = initiateSession();
		
		List<ZoneWiseStates> zoneWiseStates = new ArrayList<ZoneWiseStates>();
		try {
			
			List list = null;
			
			
			String sql = "";
			
			
			sql ="select z.zoneCode,z.zoneName,s.state_name,zs.id,zs.stateId,zs.zoneId from zone_master z,states s,zone_wise_states zs where z.zId=zs.zoneId and zs.stateId=s.state_id and z.status=1 and z.company_id="+c_id+ " and zs.company_id="+c_id+" and zs.status=1";

			
			Query q = sessionForDB.createSQLQuery(sql);
			list=q.list();
			if(list!=null)
			{
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					ZoneWiseStates zList=new ZoneWiseStates();
					zList.setZoneCode((String)obj[0]);
					zList.setZoneName((String)obj[1]);
					zList.setStateName((String)obj[2]);
					zList.setId((Integer)obj[3]);
					
					zList.setStateId((Integer)obj[3]);
					zList.setZoneId((Integer)obj[4]);
					
					zoneWiseStates.add(zList);
				}
				
			}
			return zoneWiseStates;
			
		}catch(Exception e)
		{
			throw e;
		}finally
		{
			destroySession(sessionForDB);
		}
		
	}

	@Override
	public WsDistrictDetailsOutput getStateDistrictList(Credentials cre) {

		Session session = initiateSession();
		WsDistrictDetailsOutput wsDistrictDetails = new WsDistrictDetailsOutput();
		WsFetchSalesOrg wsFetchSalesOrgInput = new WsFetchSalesOrg();
		wsFetchSalesOrgInput.setPage_no(cre.getPage_no());
		List list = null;
		int total_pages = 0;
		String query = "";

		query = "select district.districtCode,district.distDescription,district.state_id,district.district_id,states.state_name FROM district,states where district.state_id=states.state_id";
		// FieldMILogger.debug(this.getClass().getName(),query);
		try {

			list = commonMethods.quaryListPaginationForSalesOrg(session, query, wsFetchSalesOrgInput);
			List<District> districtList = new ArrayList<>();
			for (Object object : list) {

				District district = new District();
				Object[] salesDisObj = (Object[]) object;
				district.setDistrictCode((String) salesDisObj[0]);
				district.setDistDescription((String) salesDisObj[1]);
				district.setState_id((int)salesDisObj[2]);
				district.setDistrict_id((int)salesDisObj[3]);
				district.setStateName((String)salesDisObj[4]);
				districtList.add(district);
			}

			String queryForRecords = "select count(*) FROM district,states where district.state_id=states.state_id";

			total_pages = commonMethods.getTotalPagesCountSql(session, queryForRecords);

			wsDistrictDetails.getDistList().addAll(districtList);
			wsDistrictDetails.setTotalRecords(total_pages);
			wsDistrictDetails.setPage_No(cre.getPage_no());
			wsDistrictDetails.setRecordsPerPage(500);

			return wsDistrictDetails;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {

			destroySession(session);
		}

	}

}
