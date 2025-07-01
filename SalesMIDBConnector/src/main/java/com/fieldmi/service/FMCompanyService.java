package com.fieldmi.service;

import java.util.List;

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

public interface FMCompanyService {

	boolean saveCompanyEmails(EmailConfiguration emailDetails);

	EmailConfiguration getCompanyEmailDetails(int c_id);

	int EmailDetails(int c_id, String from_email, String from_password, String salesorder_email, String pricing_email,
			String host_name, int port_no, int id, int isSSL);

	boolean addCompanyConfiguration(CompanyDetails companyDetails, String concateString);

	boolean addFirmType(FirmType objfirmtype);

	boolean addBusinessType(BusinessSegment objbusinesstype);

	boolean deleteFirmType(FirmType objfirmtype);

	boolean updateFirmType(FirmType objfirmtype);

	boolean deleteBusinessType(BusinessSegment objbusinesstype);

	boolean updateBusinessType(BusinessSegment objbusinesstype);

	public List<CompanyLocations> getCompanyLocationObjectList(int company_id);

	public List<String> getCompanyCodeList(int company_id);

	public List<String> getCompanyNameList(int company_id);

	boolean addNewCircle(CircleMaster circleMaster);

	boolean updateCircleInfo(CircleMaster circleMaster);

	boolean deleteCircle(CircleMaster circleMaster);

	public List<CircleMaster> getCircleList(int company_id);

	boolean addNewZone(ZoneMaster zoneMaster);

	boolean updateZoneInfo(ZoneMaster zoneMaster);

	boolean deleteZone(ZoneMaster zoneMaster);

	public List<ZoneMaster> getZoneList(int company_id);

	boolean addNewDivision(DivisionMaster divisionMaster);

	boolean updateDivisionInfo(DivisionMaster divisionMaster);

	boolean deleteDivision(DivisionMaster divisionMaster);

	public List<DivisionMaster> getDivisionList(int company_id);

	public List<BusinessSegment> getBsegmentList(int company_id);

	public boolean addNewShift(ShiftList objshiftList);

	public boolean updateShiftInfo(ShiftList objshiftList);

	public boolean deleteShift(ShiftList objshiftList);

	public List<ShiftList> getShiftList(int c_id);

	public boolean checkDuplicate(String query);

	public boolean renewAnyObject(String sql);

	public int checkCompanyLocationIfExist(String locationName, int c_id);

	public ShiftList getShiftObject(int shift_id);

	public CompanyDetails getCompanyObject(int c_id);

	public CompanyDetails saveCompany(CompanyDetails company, String user_email, String expirydate, String user_pass,
			User adminUser, int createdBy);

	public boolean addPermissionToCompany(CompanyDetails company, List<String> webPermissions,
			List<String> mobilePermissions, List<String> distributorPermissions);

	boolean deleteCompany(CompanyDetails companyDetails);

	boolean addNewDistributor(DistributorDetails distributorDetails);

	boolean addNewRoute(Route route);

	boolean addNewTax(Tax defaultTax);

	boolean addPackUnit(ManagePackUnit packUnit);

	boolean addUserDistributor(UserHasDistributor uhd);

	boolean addUserRoute(UserHasRoutes uhr);

	boolean saveUser(User adminUser);

	boolean addPermissionToRole(User adminUser, CompanyDetails companyDetails);

	boolean addCustomerType(CustomerType customerType);

}
