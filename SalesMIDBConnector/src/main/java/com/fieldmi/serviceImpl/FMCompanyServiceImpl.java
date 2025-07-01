package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMCompanyDao;
import com.fieldmi.service.FMCompanyService;
import com.softtantra.salesapp.pojo.CircleMaster;
import com.softtantra.salesapp.pojo.CompanyDetails;
import com.softtantra.salesapp.pojo.CompanyLocations;
import com.softtantra.salesapp.pojo.CustomerType;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.DivisionMaster;
import com.softtantra.salesapp.pojo.EmailConfiguration;
import com.softtantra.salesapp.pojo.ManagePackUnit;
import com.softtantra.salesapp.pojo.Route;
import com.softtantra.salesapp.pojo.ShiftList;
import com.softtantra.salesapp.pojo.Tax;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasDistributor;
import com.softtantra.salesapp.pojo.UserHasRoutes;
import com.softtantra.salesapp.pojo.ZoneMaster;
import com.softtantra.servicemi.pojo.BusinessSegment;
import com.softtantra.servicemi.pojo.FirmType;

public class FMCompanyServiceImpl implements FMCompanyService {

	@Autowired
	FMCompanyDao fmCompanyDao;

	public void setFmCompanyDao(FMCompanyDao fmCompanyDao) {
		this.fmCompanyDao = fmCompanyDao;
	}

	@Override
	public boolean saveCompanyEmails(EmailConfiguration emailDetails) {

		return fmCompanyDao.saveCompanyEmails(emailDetails);
	}

	@Override
	public EmailConfiguration getCompanyEmailDetails(int c_id) {

		return fmCompanyDao.getCompanyEmailDetails(c_id);
	}

	@Override
	public int EmailDetails(int c_id, String from_email, String from_password, String salesorder_email,
			String pricing_email, String host_name, int port_no, int id, int isSSL) {

		return fmCompanyDao.EmailDetails(c_id, from_email, from_password, salesorder_email, pricing_email, host_name,
				port_no, id, isSSL);
	}

	@Override
	public boolean addCompanyConfiguration(CompanyDetails companyDetails, String concateString) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addCompanyConfiguration(companyDetails, concateString);
	}

	@Override
	public boolean addFirmType(FirmType objfirmtype) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addFirmType(objfirmtype);
	}

	@Override
	public boolean addBusinessType(BusinessSegment objbusinesstype) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addBusinessType(objbusinesstype);
	}

	@Override
	public boolean deleteFirmType(FirmType objfirmtype) {
		// TODO Auto-generated method stub
		return fmCompanyDao.deleteFirmType(objfirmtype);
	}

	@Override
	public boolean updateFirmType(FirmType objfirmtype) {
		// TODO Auto-generated method stub
		return fmCompanyDao.updateFirmType(objfirmtype);
	}

	@Override
	public boolean deleteBusinessType(BusinessSegment objbusinesstype) {
		// TODO Auto-generated method stub
		return fmCompanyDao.deleteBusinessType(objbusinesstype);
	}

	@Override
	public boolean updateBusinessType(BusinessSegment objbusinesstype) {
		// TODO Auto-generated method stub
		return fmCompanyDao.updateBusinessType(objbusinesstype);
	}

	@Override
	public List<CompanyLocations> getCompanyLocationObjectList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getCompanyLocationObjectList(company_id);
	}

	@Override
	public List<String> getCompanyCodeList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getCompanyCodeList(company_id);
	}

	@Override
	public List<String> getCompanyNameList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getCompanyNameList(company_id);
	}

	@Override
	public boolean addNewCircle(CircleMaster circleMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addNewCircle(circleMaster);
	}

	@Override
	public boolean updateCircleInfo(CircleMaster circleMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.updateCircleInfo(circleMaster);
	}

	@Override
	public boolean deleteCircle(CircleMaster circleMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.deleteCircle(circleMaster);
	}

	@Override
	public List<CircleMaster> getCircleList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getCircleList(company_id);
	}

	@Override
	public boolean addNewZone(ZoneMaster zoneMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addNewZone(zoneMaster);
	}

	@Override
	public boolean updateZoneInfo(ZoneMaster zoneMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.updateZoneInfo(zoneMaster);
	}

	@Override
	public boolean deleteZone(ZoneMaster zoneMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.deleteZone(zoneMaster);
	}

	@Override
	public List<ZoneMaster> getZoneList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getZoneList(company_id);
	}

	@Override
	public boolean addNewDivision(DivisionMaster divisionMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addNewDivision(divisionMaster);
	}

	@Override
	public boolean updateDivisionInfo(DivisionMaster divisionMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.updateDivisionInfo(divisionMaster);
	}

	@Override
	public boolean deleteDivision(DivisionMaster divisionMaster) {
		// TODO Auto-generated method stub
		return fmCompanyDao.deleteDivision(divisionMaster);
	}

	@Override
	public List<DivisionMaster> getDivisionList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getDivisionList(company_id);
	}

	@Override
	public List<BusinessSegment> getBsegmentList(int company_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getBsegmentList(company_id);
	}

	@Override
	public boolean addNewShift(ShiftList objshiftList) {
		// TODO Auto-generated method stub
		return fmCompanyDao.addNewShift(objshiftList);
	}

	@Override
	public boolean updateShiftInfo(ShiftList objshiftList) {
		// TODO Auto-generated method stub
		return fmCompanyDao.updateShiftInfo(objshiftList);
	}

	@Override
	public boolean deleteShift(ShiftList objshiftList) {
		// TODO Auto-generated method stub
		return fmCompanyDao.deleteShift(objshiftList);
	}

	@Override
	public List<ShiftList> getShiftList(int c_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getShiftList(c_id);
	}

	@Override
	public boolean checkDuplicate(String query) {
		// TODO Auto-generated method stub
		return fmCompanyDao.checkDuplicate(query);
	}

	@Override
	public boolean renewAnyObject(String sql) {
		// TODO Auto-generated method stub
		return fmCompanyDao.renewAnyObject(sql);
	}

	@Override
	public int checkCompanyLocationIfExist(String companyLocationName, int c_id) {
		return fmCompanyDao.checkCompanyLocationIfExist(companyLocationName, c_id);
	}

	@Override
	public ShiftList getShiftObject(int shift_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getShiftObject(shift_id);
	}

	@Override
	public CompanyDetails getCompanyObject(int c_id) {
		// TODO Auto-generated method stub
		return fmCompanyDao.getCompanyObject(c_id);
	}

	@Override
	public CompanyDetails saveCompany(CompanyDetails company, String user_email, String expirydate, String user_pass,
			User user, int createdBy) {
		// TODO Auto-generated method stub
		return fmCompanyDao.saveCompany(company, user_email, expirydate, user_pass, user, createdBy);
	}

	@Override
	public boolean addPermissionToCompany(CompanyDetails company, List<String> webPermissions,
			List<String> mobilePermissions, List<String> distributorPermissions) {

		return fmCompanyDao.addPermissionToCompany(company, webPermissions, mobilePermissions, distributorPermissions);
	}

	@Override
	public boolean deleteCompany(CompanyDetails companyDetails) {
		return fmCompanyDao.deleteCompany(companyDetails);

	}

	@Override
	public boolean addNewDistributor(DistributorDetails distributorDetails) {

		return fmCompanyDao.addNewDistributor(distributorDetails);
	}

	@Override
	public boolean addNewRoute(Route route) {

		return fmCompanyDao.addNewRoute(route);
	}

	@Override
	public boolean addNewTax(Tax tax) {

		return fmCompanyDao.addNewTax(tax);
	}

	@Override
	public boolean addPackUnit(ManagePackUnit packUnit) {

		return fmCompanyDao.addPackUnit(packUnit);
	}

	@Override
	public boolean addUserDistributor(UserHasDistributor uhd) {
		return fmCompanyDao.addUserDistributor(uhd);
	}

	@Override
	public boolean addUserRoute(UserHasRoutes uhr) {
		return fmCompanyDao.addUserRoute(uhr);
	}

	@Override
	public boolean saveUser(User adminUser) {
		return fmCompanyDao.saveUser(adminUser);
	}

	@Override
	public boolean addPermissionToRole(User adminUser, CompanyDetails companyDetails) {

		return fmCompanyDao.addPermissionToRole(adminUser, companyDetails);
	}

	@Override
	public boolean addCustomerType(CustomerType customerType) {

		return fmCompanyDao.addCustomerType(customerType);

	}
}
