package com.fieldmi.daoImpl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMCompanyDao;
import com.softtantra.salesapp.pojo.CircleMaster;
import com.softtantra.salesapp.pojo.CompanyDetails;
import com.softtantra.salesapp.pojo.CompanyLocations;
import com.softtantra.salesapp.pojo.Companydistributorpermission;
import com.softtantra.salesapp.pojo.Companymobilepermission;
import com.softtantra.salesapp.pojo.Companyrolepermission;
import com.softtantra.salesapp.pojo.Configure_Leave;
import com.softtantra.salesapp.pojo.CustomerClassification;
import com.softtantra.salesapp.pojo.CustomerType;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.DistributorPermissions;
import com.softtantra.salesapp.pojo.DivisionMaster;
import com.softtantra.salesapp.pojo.EmailConfiguration;
import com.softtantra.salesapp.pojo.LeaveDetails;
import com.softtantra.salesapp.pojo.LeaveType;
import com.softtantra.salesapp.pojo.LoginDetails;
import com.softtantra.salesapp.pojo.ManagePackUnit;
import com.softtantra.salesapp.pojo.MobilePermissions;
import com.softtantra.salesapp.pojo.RenewalDetails;
import com.softtantra.salesapp.pojo.RenewalRequestDetails;
import com.softtantra.salesapp.pojo.Role;
import com.softtantra.salesapp.pojo.RolePermissions;
import com.softtantra.salesapp.pojo.Route;
import com.softtantra.salesapp.pojo.ShiftList;
import com.softtantra.salesapp.pojo.Tax;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasDistributor;
import com.softtantra.salesapp.pojo.UserHasRoutes;
import com.softtantra.salesapp.pojo.ZoneMaster;
import com.softtantra.servicemi.pojo.BusinessSegment;
import com.softtantra.servicemi.pojo.FirmType;
import com.softtantra.servicemi.pojo.SM_User;
import com.softtantra.servicemi.pojo.SM_UserWeekOfDetails;

public class FMCompanyDaoImpl implements FMCompanyDao {

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

	@Override
	public boolean saveCompanyEmails(EmailConfiguration emailDetails) {

		Session session = initiateSession();

		try {
			String sql = "select id from email_configuration where company_id=" + emailDetails.getCompany_id()
					+ " and from_email='" + emailDetails.getFrom_email() + "' and from_password='"
					+ emailDetails.getFrom_password() + "' and pricing_email='" + emailDetails.getPricing_email()
					+ "' and salesorder_email='" + emailDetails.getSalesorder_email() + "' and port_no="
					+ emailDetails.getPort_no() + " and host_name='" + emailDetails.getHost_name() + "' and isSSL="
					+ emailDetails.getIsSSL();
			Query query = session.createSQLQuery(sql);

			if (query.list().size() > 0) {
				return false;
			} else {
				session.save(emailDetails);
				return true;
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public EmailConfiguration getCompanyEmailDetails(int c_id) {

		Session session = initiateSession();
		try {
			String sql = "select from_email,from_password,pricing_email,salesorder_email,port_no,host_name,id,isSSL from email_configuration where company_id="
					+ c_id;
			Query query = session.createSQLQuery(sql);
			List list1 = query.list();
			for (Object object : list1) {
				EmailConfiguration details = new EmailConfiguration();
				Object[] obj = (Object[]) object;
				details.setFrom_email((String) obj[0]);
				details.setFrom_password((String) obj[1]);
				details.setPricing_email((String) obj[2]);
				details.setSalesorder_email((String) obj[3]);
				details.setPort_no((int) obj[4]);
				details.setHost_name((String) obj[5]);
				details.setId((int) obj[6]);
				details.setIsSSL((int) obj[7]);
				return details;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);

		}
		return null;
	}

	@Override
	public int EmailDetails(int c_id, String from_email, String from_password, String salesorder_email,
			String pricing_email, String host_name, int port_no, int id, int isSSL) {

		Session session = initiateSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String sql = "update email_configuration set from_email='" + from_email + "', from_password='"
					+ from_password + "', pricing_email='" + pricing_email + "', salesorder_email='" + salesorder_email
					+ "', port_no=" + port_no + ", host_name='" + host_name + "', updated_date='"
					+ sdf.format(new Date()) + "',isSSL=" + isSSL + " where id= " + id + " and company_id=" + c_id;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			return 1;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return 0;
		} finally {
			destroySession(session);

		}
	}

	@Override
	public boolean addCompanyConfiguration(CompanyDetails companyDetails, String concateString) {
		Session session = initiateSession();

		try {

			String[] RowSplit = concateString.split("<###>");
			for (int i = 0; i < RowSplit.length; i++) {
				String str = RowSplit[i];
				String[] columnSaparator = str.split("<<<<>>>>");
				if (columnSaparator.length > 1) {
					CustomerClassification contactDetails = new CustomerClassification();
					contactDetails.setCompany_id(companyDetails.getCompany_id());
					contactDetails.setCustomerType(columnSaparator[0]);
					contactDetails.setClassificationName(columnSaparator[1]);
					contactDetails.setCreated_date(companyDetails.getCreated_date());
					contactDetails.setUpdated_date(companyDetails.getUpdated_date());
					contactDetails.setStatus(1);

					session.save(contactDetails);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addFirmType(FirmType objfirmtype) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			Date date = new Date();
			objfirmtype.setCreated_date(date);
			objfirmtype.setUpdated_date(date);
			session.save(objfirmtype);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean addBusinessType(BusinessSegment objbusinesstype) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			Date date = new Date();
			objbusinesstype.setCreated_date(date);
			objbusinesstype.setUpdated_date(date);
			session.save(objbusinesstype);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean deleteFirmType(FirmType objfirmtype) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update FirmType set status=:status,updated_by=:updated_by,updated_date=:updated_date where firm_type_id=:firm_type_id")
					.setParameter("firm_type_id", objfirmtype.getFirm_type_id())
					.setParameter("updated_by", objfirmtype.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("status", 0);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateFirmType(FirmType objfirmtype) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query = session.createQuery(
					"update FirmType set firm_type_name=:firm_type_name,updated_by=:updated_by,updated_date=:updated_date where firm_type_id=:firm_type_id and company_id=:cid")
					.setParameter("firm_type_name", objfirmtype.getFirm_type_name())
					.setParameter("cid", objfirmtype.getCompany_id())
					.setParameter("updated_by", objfirmtype.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("firm_type_id", objfirmtype.getFirm_type_id());
			query.executeUpdate();

			//// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean deleteBusinessType(BusinessSegment objbusinesstype) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update BusinessSegment set status=:status,updated_by=:updated_by,updated_date=:updated_date where bsegment_id=:bsegment_id")
					.setParameter("bsegment_id", objbusinesstype.getBsegment_id())
					.setParameter("updated_by", objbusinesstype.getUpdated_by())
					.setParameter("updated_date", new Date()).setParameter("status", 0);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateBusinessType(BusinessSegment objbusinesstype) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {

			Query query = session.createQuery(
					"update BusinessSegment set bsegment_name=:bsegment_name,updated_by=:updated_by,updated_date=:updated_date where bsegment_id=:bsegment_id and company_id=:cid")
					.setParameter("bsegment_name", objbusinesstype.getBsegment_name())
					.setParameter("cid", objbusinesstype.getCompany_id())
					.setParameter("updated_by", objbusinesstype.getUpdated_by())
					.setParameter("updated_date", new Date())
					.setParameter("bsegment_id", objbusinesstype.getBsegment_id());
			query.executeUpdate();

			//// FieldMILogger.debug(this.getClass().getName(),query.getQueryString());
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public List<CompanyLocations> getCompanyLocationObjectList(int company_id) {
		Session session = initiateSession();
		List<CompanyLocations> companyLocationsDetails = new ArrayList<>();
		try {
			Query query = session.createQuery(
					"select companyLocationId,companyLocationAddress,companyLocationCode,companyLocationName,lat,longi,officeLocation from CompanyLocations where company_id="
							+ company_id);
			List list = query.list();

			if (list != null && list.size() > 0) {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					CompanyLocations companyLocations = new CompanyLocations();
					companyLocations.setCompanyLocationId(((int) obj[0]));
					companyLocations.setCompanyLocationAddress((String) obj[1]);
					companyLocations.setCompanyLocationCode((String) obj[2]);
					companyLocations.setCompanyLocationName((String) obj[3]);
					companyLocations.setLat((String) obj[4]);
					companyLocations.setLongi((String) obj[5]);
					companyLocations.setOfficeLocation((String) obj[6]);
					companyLocations.setCompany_id(company_id);

					companyLocationsDetails.add(companyLocations);
				}

			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return companyLocationsDetails;
	}

	@Override
	public List<String> getCompanyCodeList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"select companyLocationCode from CompanyLocations where company_id=" + company_id + "");
			return query.list();

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<String> getCompanyNameList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"select companyLocationName  from CompanyLocations where company_id=" + company_id + "");
			return query.list();

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean addNewCircle(CircleMaster circleMaster) {
		Session session = initiateSession();
		try {
			Date date = new Date();
			circleMaster.setCreated_date(date);
			circleMaster.setUpdated_date(date);
			session.save(circleMaster);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateCircleInfo(CircleMaster circleMaster) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update CircleMaster set circleName=:circleName,updated_by=:updated_by,updated_date=:updated_date,circleCode=:circleCode where cId=:cId and company_id=:cid")
					.setParameter("circleName", circleMaster.getCircleName())
					.setParameter("cid", circleMaster.getCompany_id())
					.setParameter("updated_by", circleMaster.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("circleCode", circleMaster.getCircleCode())
					.setParameter("cId", circleMaster.getcId());
			query.executeUpdate();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean deleteCircle(CircleMaster circleMaster) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update CircleMaster set status=:status,updated_by=:updated_by,updated_date=:updated_date where cId=:cId and company_id=:company_id")
					.setParameter("cId", circleMaster.getcId()).setParameter("updated_by", circleMaster.getUpdated_by())
					.setParameter("updated_date", new Date()).setParameter("status", 0)
					.setParameter("company_id", circleMaster.getCompany_id());
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
	public boolean addNewZone(ZoneMaster zoneMaster) {
		Session session = initiateSession();
		try {
			Date date = new Date();
			zoneMaster.setCreated_date(date);
			zoneMaster.setUpdated_date(date);
			session.save(zoneMaster);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateZoneInfo(ZoneMaster zoneMaster) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update ZoneMaster set zoneName=:zoneName,updated_by=:updated_by,updated_date=:updated_date,zoneCode=:zoneCode where zId=:zId and company_id=:cid")
					.setParameter("zoneName", zoneMaster.getZoneName()).setParameter("cid", zoneMaster.getCompany_id())
					.setParameter("updated_by", zoneMaster.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("zoneCode", zoneMaster.getZoneCode()).setParameter("zId", zoneMaster.getzId());
			query.executeUpdate();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean deleteZone(ZoneMaster zoneMaster) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update ZoneMaster set status=:status,updated_by=:updated_by,updated_date=:updated_date where zId=:zId and company_id=:company_id")
					.setParameter("zId", zoneMaster.getzId()).setParameter("updated_by", zoneMaster.getUpdated_by())
					.setParameter("updated_date", new Date()).setParameter("status", 0)
					.setParameter("company_id", zoneMaster.getCompany_id());
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
	public boolean addNewDivision(DivisionMaster divisionMaster) {
		Session session = initiateSession();
		try {
			Date date = new Date();
			divisionMaster.setCreated_date(date);
			divisionMaster.setUpdated_date(date);
			session.save(divisionMaster);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateDivisionInfo(DivisionMaster divisionMaster) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update DivisionMaster set divName=:divName,updated_by=:updated_by,updated_date=:updated_date,divCode=:divCode where divId=:divId and company_id=:cid")
					.setParameter("divName", divisionMaster.getDivName())
					.setParameter("cid", divisionMaster.getCompany_id())
					.setParameter("updated_by", divisionMaster.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("divCode", divisionMaster.getDivCode())
					.setParameter("divId", divisionMaster.getDivId());
			query.executeUpdate();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean deleteDivision(DivisionMaster divisionMaster) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update DivisionMaster set status=:status,updated_by=:updated_by,updated_date=:updated_date where divId=:divId and company_id=:company_id")
					.setParameter("divId", divisionMaster.getDivId())
					.setParameter("updated_by", divisionMaster.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("status", 0).setParameter("company_id", divisionMaster.getCompany_id());
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
	public boolean addNewShift(ShiftList objshiftList) {
		Session session = initiateSession();
		try {
			Date date = new Date();
			objshiftList.setCreated_date(date);
			objshiftList.setUpdated_date(date);
			session.save(objshiftList);
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean updateShiftInfo(ShiftList objshiftList) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update ShiftList set shift_name=:shift_name,updated_by=:updated_by,updated_date=:updated_date,in_time=:in_time,out_time=:out_time where shift_id=:shift_id and company_id=:cid")
					.setParameter("shift_name", objshiftList.getShift_name())
					.setParameter("cid", objshiftList.getCompany_id())
					.setParameter("updated_by", objshiftList.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("in_time", objshiftList.getIn_time())
					.setParameter("out_time", objshiftList.getOut_time())
					.setParameter("shift_id", objshiftList.getShift_id());
			query.executeUpdate();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean deleteShift(ShiftList objshiftList) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(
					"update ShiftList set status=:status,updated_by=:updated_by,updated_date=:updated_date where shift_id=:shift_id and company_id=:company_id")
					.setParameter("shift_id", objshiftList.getShift_id())
					.setParameter("updated_by", objshiftList.getUpdated_by()).setParameter("updated_date", new Date())
					.setParameter("status", 0).setParameter("company_id", objshiftList.getCompany_id());
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
	public List<CircleMaster> getCircleList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from CircleMaster where company_id=" + company_id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<ZoneMaster> getZoneList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from ZoneMaster where company_id=" + company_id + " order by zoneCode");
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<DivisionMaster> getDivisionList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from DivisionMaster where company_id=" + company_id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<BusinessSegment> getBsegmentList(int company_id) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from BusinessSegment where company_id=" + company_id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<ShiftList> getShiftList(int c_id) {
		Session sessionoofDb = initiateSession();
		try {
			Query query = sessionoofDb.createQuery("from ShiftList where company_id=" + c_id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			destroySession(sessionoofDb);
		}
	}

	@Override
	public boolean checkDuplicate(String sql) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(sql);
			if (query.uniqueResult() == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean renewAnyObject(String sql) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery(sql);
			if (query.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public int checkCompanyLocationIfExist(String companyLocationName, int c_id) {
		Session session = initiateSession();
		try {
			Query query = session
					.createQuery("select companyLocationId from  CompanyLocations where companyLocationName='"
							+ companyLocationName + "'" + " and company_id=" + c_id + "");

			if (query.uniqueResult() == null) {
				return 0;
			} else {
				return (int) query.uniqueResult();
			}

		} catch (Exception e) {
			FieldMILogger.warn(this.getClass().getName(), e.getMessage());
			return 0;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public ShiftList getShiftObject(int shift_id) {
		Session session = initiateSession();
		try {
			ShiftList shiftList = session.get(ShiftList.class, shift_id);
			if (shiftList != null) {
				return shiftList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return null;
	}

	@Override
	public CompanyDetails getCompanyObject(int c_id) {

		Session session = initiateSession();
		try {

			return session.get(CompanyDetails.class, c_id);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}
	}

	@Override
	public CompanyDetails saveCompany(CompanyDetails company, String user_email, String expirydate, String user_pass,
			User user, int createdby) {
		Session session = initiateSession();
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String company_type = company.getServicetype() + "";
			company.setCreated_by(createdby);
			company.setUpdated_by(createdby);
			company.setCreated_date(new Date());
			company.setUpdated_date(new Date());
			company.setStatus(1);
			int user_id = 0;

			session.save(company);

			if (createdby == 0) {

				Role role = new Role();

				role.setCompany_id(company.getCompany_id());

				if (company_type.equals("2")) {
					role.setName("Service Manager");
					role.setDescription("Service Manager");
				} else {
					role.setName("Sales Manager");
					role.setDescription("Sales Manager");
				}

				role.setStatus(1);
				role.setCreated_by(createdby);
				role.setUpdated_by(createdby);
				role.setCreated_date(new Date());
				role.setUpdated_date(new Date());

				session.save(role);

				Role role1 = new Role();

				role1.setCompany_id(company.getCompany_id());
				role1.setName("Reporting Manager");
				role1.setDescription("Reporting Manager");
				role1.setStatus(1);
				role1.setCreated_by(createdby);
				role1.setUpdated_by(createdby);
				role1.setCreated_date(new Date());
				role1.setUpdated_date(new Date());

				session.save(role1);

				Role role2 = new Role();

				role2.setCompany_id(company.getCompany_id());
				role2.setName("System Admin");
				role2.setDescription("System Admin");
				role2.setStatus(1);
				role2.setCreated_by(createdby);
				role2.setUpdated_by(createdby);
				role2.setCreated_date(new Date());
				role2.setUpdated_date(new Date());

				session.save(role2);

				// add leave type here
				LeaveType objdefaultleavetype = new LeaveType();
				objdefaultleavetype.setCompany_id(company.getCompany_id());
				objdefaultleavetype.setCreated_by(createdby);
				objdefaultleavetype.setLeave_type_name("Paid Leave");
				objdefaultleavetype.setUpdated_by(createdby);
				objdefaultleavetype.setStatus(1);
				objdefaultleavetype.setUser_id(createdby);
				Date date = new Date();
				objdefaultleavetype.setCreated_date(date);
				objdefaultleavetype.setUpdated_date(date);
				session.save(objdefaultleavetype);

				Configure_Leave cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				cLeave.setRole_id(role1.getId());
				session.save(cLeave);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				cLeave.setRole_id(role2.getId());
				session.save(cLeave);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				session.save(cLeave);

				LeaveType objdefaultleavetype1 = new LeaveType();
				objdefaultleavetype1.setCompany_id(company.getCompany_id());
				objdefaultleavetype1.setCreated_by(createdby);
				objdefaultleavetype1.setLeave_type_name("Unpaid Leave");
				objdefaultleavetype1.setUpdated_by(createdby);
				objdefaultleavetype1.setStatus(1);
				objdefaultleavetype1.setUser_id(createdby);
				objdefaultleavetype1.setCreated_date(date);
				objdefaultleavetype1.setUpdated_date(date);
				session.save(objdefaultleavetype1);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype1.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				cLeave.setRole_id(role1.getId());
				session.save(cLeave);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype1.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				cLeave.setRole_id(role2.getId());
				session.save(cLeave);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype1.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				session.save(cLeave);

				LeaveType objdefaultleavetype2 = new LeaveType();
				objdefaultleavetype2.setCompany_id(company.getCompany_id());
				objdefaultleavetype2.setCreated_by(createdby);
				objdefaultleavetype2.setLeave_type_name("Compensatory Leave");
				objdefaultleavetype2.setUpdated_by(createdby);
				objdefaultleavetype2.setStatus(1);
				objdefaultleavetype2.setUser_id(createdby);
				objdefaultleavetype2.setCreated_date(date);
				objdefaultleavetype2.setUpdated_date(date);

				session.save(objdefaultleavetype2);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype2.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				cLeave.setRole_id(role1.getId());
				session.save(cLeave);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype2.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				cLeave.setRole_id(role2.getId());
				session.save(cLeave);

				cLeave = new Configure_Leave();
				cLeave.setLeave_type_id(objdefaultleavetype2.getLeave_type_id());
				cLeave.setNo_of_leaves(2);
				session.save(cLeave);

//				List<BigInteger> list;
//				if (company_type.equals("2")) {
//
//					list = session.createNativeQuery("select id from role_permissions where  company_id=1").list();
//				} else {
//					list = session.createNativeQuery("select id from role_permissions where  company_id=2").list();
//				}
//				for (BigInteger object : list) {
//
//					// Object[] obj= (Object[]) object;
//
//					session.createNativeQuery("insert into role_has_rolepermission(role_id,permission_id) values("
//							+ role2.getId() + "," + (object).intValue() + ")").executeUpdate();
//
//				}

				if (company_type.equals("2")) {
					if (user == null) {
						user = new User();
					}
					SM_User sm_user = new SM_User();
					sm_user.setCity(1);
					sm_user.setCity_name("");
					sm_user.setCompany_id(company.getCompany_id());
					sm_user.setCreated_by(createdby);
					sm_user.setCreated_date(new Date());
					sm_user.setDistributor_id(0);
					sm_user.setEmail(user_email);
					sm_user.setField1(company.getField1());
					sm_user.setField2(company.getField2());
					sm_user.setFirst_name(company.getFirst_name());
					sm_user.setLast_name(company.getLast_name());
					sm_user.setLast_login(new Date());
					sm_user.setMobile_no(company.getPhone());
					sm_user.setPassword(user_pass);
					sm_user.setProfile_img("");
					sm_user.setReporting_manager_id(0);
					sm_user.setReporting_role_id(0);
					sm_user.setRole_id(role2.getId());
					sm_user.setRole_name(role2.getName());
					sm_user.setRoutes("");
					sm_user.setState(1);
					sm_user.setState_name("");
					sm_user.setStatus(1);
					sm_user.setTime_zone(company.getTimezone());
					sm_user.setUpdated_by(createdby);
					sm_user.setUpdated_date(new Date());

					session.save(sm_user);
					user_id = sm_user.getUser_id();
					Transaction tx = session.beginTransaction();

					Query query = session.createNativeQuery("update  sm_user_details set reporting_manager_id="
							+ sm_user.getUser_id() + ",reporting_role_id=" + role2.getId()
							+ " where status=1 and user_id=" + user.getUser_id() + "");
					query.executeUpdate();
					tx.commit();

					LoginDetails logindetails = new LoginDetails();

					logindetails.setActive(true);
					logindetails.setCompany_id(company.getCompany_id());
					logindetails.setPassword(user_pass);
					logindetails.setRole_id(role2.getId());
					logindetails.setUser_name(user_email);
					logindetails.setUser_id(user.getUser_id());

					session.save(logindetails);
				} else {
					if (user == null) {
						user = new User();
					}
					user.setCompany_id(company.getCompany_id());
					user.setCreated_by(createdby);
					user.setCreated_date(new Date());
					user.setDistributor_id(0);
					user.setEmail(user_email);
					user.setField1(company.getField1());
					user.setField2(company.getField2());
					user.setFirst_name(company.getFirst_name());
					user.setLast_name(company.getLast_name());
					user.setLast_login(new Date());
					user.setMobile_no(company.getPhone());
					user.setPassword(user_pass);
					user.setProfile_img("");
					user.setReporting_manager_id(0);
					user.setReporting_role_id(0);
					user.setRole_id(role2.getId());
					user.setRole_name(role2.getName());
					user.setRoutes("");
					user.setState(company.getState_id());
					user.setNew_state(company.getState_id());
					user.setState_name("");
					user.setCity(company.getCity_id());
					user.setNew_city(company.getCity_id());
					user.setCity_name("");
					user.setCountry_id(company.getCountry_id());
					user.setCountry_name("");
					user.setStatus(1);
					user.setTime_zone(company.getTimezone());
					user.setUpdated_by(createdby);
					user.setUpdated_date(new Date());

					session.save(user);
					user_id = user.getUser_id();

					SM_UserWeekOfDetails details = new SM_UserWeekOfDetails();
					details.setUser_id(user.getUser_id());
					details.setWeekoff_id(0);
					details.setStatus(1);
					details.setCreated_by(user.getCreated_by());
					details.setCreated_date(new Date());
					details.setUpdated_by(user.getUpdated_by());
					details.setUpdated_date(new Date());

					session.save(details);

					Transaction tx = session.beginTransaction();

					Query query = session.createSQLQuery(
							"update  user_details set reporting_manager_id=" + user.getUser_id() + ",reporting_role_id="
									+ role2.getId() + " where status=1 and user_id=" + user.getUser_id() + "");
					query.executeUpdate();
					tx.commit();

					LoginDetails logindetails = new LoginDetails();

					logindetails.setActive(true);
					logindetails.setCompany_id(company.getCompany_id());
					logindetails.setPassword(user_pass);
					logindetails.setRole_id(role2.getId());
					logindetails.setUser_name(user_email);
					logindetails.setUser_id(user.getUser_id());

					session.save(logindetails);
				}

				RenewalRequestDetails renewalreq = new RenewalRequestDetails();

				renewalreq.setCompany_id(company.getCompany_id());
				renewalreq.setCreated_by(createdby);
				renewalreq.setCreated_date(new Date());
				renewalreq.setDescription("initial Entry");
				renewalreq.setPackage_id(company.getPackage_id());
				renewalreq.setRequestor_user_id(user_id);
				renewalreq.setStatus(2);
				renewalreq.setUpdated_by(createdby);
				renewalreq.setUpdated_date(new Date());

				session.save(renewalreq);

				RenewalDetails renewalDetails = new RenewalDetails();

				renewalDetails.setCompany_id(company.getCompany_id());
				renewalDetails.setCreated_by(createdby);
				renewalDetails.setCreated_date(new Date());
				renewalDetails.setEnd_date(sdf.parse(expirydate));
				renewalDetails.setStatus(1);
				renewalDetails.setUpdated_by(createdby);
				renewalDetails.setUpdated_date(new Date());
				renewalDetails.setStart_date(sdf.parse(sdf.format(new Date())));
				renewalDetails.setRenewal_request_id(renewalreq.getRenewal_request_id());

				session.save(renewalDetails);
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
		return company;
	}

	@Override
	public boolean addPermissionToCompany(CompanyDetails company, List<String> webPermissionsList,
			List<String> mobilePermissionsList, List<String> distributorPermissionsList) {

		Session session = initiateSession();
		try {

			String rolePackages = getInClause(webPermissionsList);
			if (rolePackages != null && rolePackages.length() > 2) {

				Query<RolePermissions> rolePermissionQuery = session
						.createQuery("from RolePermissions where status=1 and company_id=2 and UPPER(packageName) in "
								+ rolePackages, RolePermissions.class);
				List<RolePermissions> rolePermissions = rolePermissionQuery.list();
				for (RolePermissions rolePermission : rolePermissions) {

					Companyrolepermission companyHasRole = new Companyrolepermission();
					companyHasRole.setCompany_id(company.getCompany_id());
					companyHasRole.setPermission_id(rolePermission.getId().intValue());
					session.save(companyHasRole);
				}
			}

			String mobilePackages = getInClause(mobilePermissionsList);
			if (mobilePackages != null && mobilePackages.length() > 2) {

				Query<MobilePermissions> mobilePermissionQuery = session.createQuery(
						"from MobilePermissions where status=1 and UPPER(packageName) in " + mobilePackages,
						MobilePermissions.class);
				List<MobilePermissions> mobilePermissions = mobilePermissionQuery.list();
				for (MobilePermissions mobilePermission : mobilePermissions) {

					Companymobilepermission companyHasRole = new Companymobilepermission();
					companyHasRole.setCompany_id(company.getCompany_id());
					companyHasRole.setMobile_permission_id(mobilePermission.getMobile_permissions_id());
					session.save(companyHasRole);
				}
			}

			String distributorPackages = getInClause(distributorPermissionsList);
			if (distributorPackages != null && distributorPackages.length() > 2) {

				Query<DistributorPermissions> distributorPermissionQuery = session.createQuery(
						"from DistributorPermissions where status=1 and company_id=2 and UPPER(packageName) in "
								+ distributorPackages,
						DistributorPermissions.class);
				List<DistributorPermissions> distributorPermissions = distributorPermissionQuery.list();
				for (DistributorPermissions distributorPermission : distributorPermissions) {

					Companydistributorpermission companyHasRole = new Companydistributorpermission();
					companyHasRole.setCompany_id(company.getCompany_id());
					companyHasRole.setPermission_id(distributorPermission.getId().intValue());
					session.save(companyHasRole);
				}
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			throw e;
		} finally {

			destroySession(session);
		}

		return true;
	}

	private String getInClause(List<String> permissionsList) {

		if (permissionsList != null && permissionsList.size() > 0) {

			StringBuffer permissionQuery = new StringBuffer("(");
			Iterator<String> itr = permissionsList.iterator();
			while (itr.hasNext()) {

				String permission = itr.next().toUpperCase();
				permissionQuery.append("'").append(permission.toUpperCase()).append("'");
				if (itr.hasNext())
					permissionQuery.append(",");

			}
			permissionQuery.append(")");
			return permissionQuery.toString();
		} else {

			return null;
		}

	}

	@Override
	public boolean deleteCompany(CompanyDetails companyDetails) {

		Session session = initiateSession();
		try {

			session.delete(companyDetails);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addNewDistributor(DistributorDetails distributorDetails) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(distributorDetails);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addNewRoute(Route route) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(route);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addNewTax(Tax tax) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(tax);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addPackUnit(ManagePackUnit packUnit) {
		Session session = initiateSession();
		try {

			session.saveOrUpdate(packUnit);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addUserDistributor(UserHasDistributor uhd) {
		Session session = initiateSession();
		try {

			session.saveOrUpdate(uhd);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addUserRoute(UserHasRoutes uhr) {
		Session session = initiateSession();
		try {

			session.saveOrUpdate(uhr);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean saveUser(User adminUser) {
		Session session = initiateSession();
		try {

			session.saveOrUpdate(adminUser);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean addPermissionToRole(User adminUser, CompanyDetails companyDetails) {

		Session session = initiateSession();
		try {

			Query<Companyrolepermission> rolePermissionQuery = session.createQuery(
					"from Companyrolepermission where company_id=" + companyDetails.getCompany_id(),
					Companyrolepermission.class);
			List<Companyrolepermission> rolePermissions = rolePermissionQuery.list();
			if (rolePermissions != null) {
				for (Companyrolepermission rolePermission : rolePermissions) {

					session.createNativeQuery("insert into role_has_rolepermission(role_id,permission_id) values("
							+ adminUser.getRole_id() + "," + rolePermission.getPermission_id() + ")").executeUpdate();
				}
			}

			Query<Companymobilepermission> mobilPermissionQuery = session.createQuery(
					"from Companymobilepermission where company_id=" + companyDetails.getCompany_id(),
					Companymobilepermission.class);
			List<Companymobilepermission> mobilePermissions = mobilPermissionQuery.list();
			if (mobilePermissions != null) {
				for (Companymobilepermission mobilePermission : mobilePermissions) {

					session.createNativeQuery("insert into role_has_mobilepermission(role_id,mobile_permission_id) values("
							+ adminUser.getRole_id() + "," + mobilePermission.getMobile_permission_id() + ")")
							.executeUpdate();
				}
			}
			Query<Companydistributorpermission> distPermissionQuery = session.createQuery(
					"from Companydistributorpermission where company_id=" + companyDetails.getCompany_id(),
					Companydistributorpermission.class);
			List<Companydistributorpermission> distPermissions = distPermissionQuery.list();
			if (distPermissions != null) {

				for (Companydistributorpermission distPermission : distPermissions) {

					session.createNativeQuery(
							"insert into role_has_distributorpermission(role_id,id) values("
									+ adminUser.getRole_id() + "," + distPermission.getPermission_id() + ")")
							.executeUpdate();
				}
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;

	}

	@Override
	public boolean addCustomerType(CustomerType customerType) {
		
		Session session = initiateSession();
		try {

			session.saveOrUpdate(customerType);
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {

			destroySession(session);
		}
		return true;
	}

}
