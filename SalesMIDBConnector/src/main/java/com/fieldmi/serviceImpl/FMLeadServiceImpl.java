package com.fieldmi.serviceImpl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.fieldmi.dao.FMLeadDao;
import com.fieldmi.service.FMLeadService;
import com.softtantra.servicemi.pojo.QuotationDetails;
import com.fieldmi.stubs.WsLeadList;
import com.fieldmi.stubs.WsLeadTypeList;
import com.fieldmi.stubs.WsOpportunityList;
import com.softtantra.salesapp.pojo.FieldMIConfiguration;
import com.softtantra.salesapp.pojo.LeadHasProducts;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.servicemi.pojo.ActivityDetails;
import com.softtantra.servicemi.pojo.LeadProduct;
import com.softtantra.servicemi.pojo.OpportunityDetails;
import com.softtantra.servicemi.pojo.OpportunityStages;
import com.softtantra.servicemi.pojo.SM_LeadDetails;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.NewOpportunityList;
import com.softtantra.ws.WSActivityList;
import com.softtantra.ws.WSOpportunityList;

public class FMLeadServiceImpl implements FMLeadService{

	@Autowired
	FMLeadDao fmLeadDao;
	
	public void setFmLeadDao(FMLeadDao fmLeadDao) {
		this.fmLeadDao = fmLeadDao;
	}

	@Override
	public boolean saveLead(SM_LeadDetails leadDetails, int c_id, int u_id,String data) {
		// TODO Auto-generated method stub
		return fmLeadDao.saveLead(leadDetails,c_id,u_id,data);
	}

	@Override
	public SM_LeadDetails editLeadDetails(int lead_id, int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.editLeadDetails(lead_id,c_id);
	}

	@Override
	public boolean updateLead(SM_LeadDetails leadDetails,String data) {
		// TODO Auto-generated method stub
		return fmLeadDao.updateLead(leadDetails,data);
	}

	@Override
	public boolean closeLead(int lead_id, String close_leadstatus, String close_comment, int u_id,int c_id,String close_date) {
		// TODO Auto-generated method stub
		return fmLeadDao.closeLead(lead_id,close_leadstatus,close_comment,u_id,c_id,close_date);
	}

	@Override
	public WsLeadList getLeadList(Credentials creadentials) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadList(creadentials);
	}

	@Override
	public WsLeadTypeList getLeadSourceList(int c_id,int page_no) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadSourceList(c_id,page_no);
	}

	@Override
	public WsLeadTypeList getLeadIndustryList(int c_id,int page_no) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadIndustryList(c_id,page_no);
	}

	@Override
	public boolean saveLeadConfigurationData(String email, String prefix, String postfix, String start_range,
			String end_range,int c_id,int u_id,String parent_name) {
		// TODO Auto-generated method stub
		return fmLeadDao.saveLeadConfigurationData(email,prefix,postfix,start_range,end_range,c_id, u_id,parent_name);
	}

	@Override
	public List<User> getLeadAllUsers(int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadAllUsers(c_id);
	}

	@Override
	public Map<String, Long>  getDataForLeadPieChart(int assigned_to, int created_by, String value,
			 String lead_type, String lead_source, String lead_industry,String type,String industry,String source,int c_id,String sqlAssigned_to) {
		// TODO Auto-generated method stub
		return fmLeadDao.getDataForLeadPieChart(assigned_to,created_by,value,lead_type,lead_source,lead_industry,type,industry,source,c_id,sqlAssigned_to);
	}

	@Override
	public boolean saveOpportunity(com.softtantra.servicemi.pojo.OpportunityDetails opportunityDetails, int c_id,
			int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.saveOpportunity(opportunityDetails,c_id,u_id);
	}

	@Override
	public boolean deleteOpportunity(int c_id,int u_id,OpportunityDetails opportunityDetails) {
		// TODO Auto-generated method stub
		return fmLeadDao.deleteOpportunity(c_id, u_id,opportunityDetails);
	}

	@Override
	public OpportunityDetails editOpportunity(int c_id, int u_id, int opportunity_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.editOpportunity(c_id,u_id,opportunity_id);
	}

	@Override
	public boolean updateOpportunity(OpportunityDetails opportunityDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.updateOpportunity(opportunityDetails,c_id,u_id);
	}

	@Override
	public ActivityDetails editActivity(int c_id, int u_id, int activity_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.editActivity(c_id,u_id,activity_id);
	}

	@Override
	public boolean updateActivity(ActivityDetails activityDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.updateActivity(activityDetails,c_id,u_id);
	}

	@Override
	public boolean deleteActivity(int c_id, int u_id, int activity_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.deleteActivity(c_id,u_id,activity_id);
	}

	@Override
	public NewOpportunityList getOpportunityPieChartData(int c_id, int u_id,String sqlAssigned) {
		// TODO Auto-generated method stub
		return fmLeadDao.getOpportunityPieChartData(c_id,u_id,sqlAssigned);
	}

	@Override
	public boolean saveQuote(QuotationDetails quotationDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.saveQuote(quotationDetails,c_id,u_id);
	}

	@Override
	public QuotationDetails edit_quote(int c_id, int u_id, int quote_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.edit_quote(c_id,u_id,quote_id);
	}

	@Override
	public boolean update_quote(QuotationDetails quotationDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.update_quote(quotationDetails,c_id,u_id);
	}

	@Override
	public boolean deleteQuote(int c_id, int u_id, int quote_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.deleteQuote(c_id,u_id,quote_id);
	}

	@Override
	public List<OpportunityDetails> getOpportunityNameList(int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getOpportunityNameList(c_id,u_id);
	}

	@Override
	public SM_LeadDetails getLeadObject(String opportunity_name, int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadObject(opportunity_name,c_id);
	}

	@Override
	public ProductDetails getProductObject(String product_name, int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getProductObject(product_name,c_id);
	}

	@Override
	public FieldMIConfiguration getFieldMIConfigurationObject(int c_id,String parent_name) {
		// TODO Auto-generated method stub
		return fmLeadDao.getFieldMIConfigurationObject(c_id,parent_name);
	}
	
	@Override
	public boolean saveActivity(ActivityDetails activityDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.saveActivity(activityDetails,c_id,u_id);
	}

	@Override
	public WSActivityList getActivityList(ActivityDetails activityDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getActivityList(activityDetails,c_id,u_id);
	}

	@Override
	public WSActivityList activityPieChartData(int c_id, int u_id, int parent_id, String parent_type) {
		// TODO Auto-generated method stub
		return fmLeadDao.activityPieChartData(c_id,u_id,parent_id,parent_type);
	}

	@Override
	public String getNewIdRange(int c_id,String parent_name) {
		
		return fmLeadDao.getNewIdRange(c_id,parent_name);
	}

	@Override
	public List<OpportunityDetails> getOpportunityReportExport(String opportunityListQuery) {
		
		return fmLeadDao.getOpportunityReportExport(opportunityListQuery);
	}

	@Override
	public boolean saveOpportunityStage(OpportunityStages oppStageObject) {
		// TODO Auto-generated method stub
		return fmLeadDao.saveOpportunityStage(oppStageObject);
	}

	@Override
	public boolean updateStage(OpportunityStages opportunityStages, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.updateStage(opportunityStages,c_id,u_id);
	}

	@Override
	public boolean deleteStage(int c_id, int u_id, int id) {
		// TODO Auto-generated method stub
		return fmLeadDao.deleteStage(c_id,u_id,id);
	}

	@Override
	public List<OpportunityStages> getOpportunityStagesList(int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getOpportunityStagesList(c_id);
	}

	@Override
	public long checkPercentageSum(int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.checkPercentageSum(c_id);
	}

	@Override
	public List<String> getOpportunity_nameList(int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getOpportunity_nameList(c_id);
	}

	@Override
	public SM_LeadDetails getLeadObject(int lead_id, int c_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadObject(lead_id, c_id);
	}

	@Override
	public WsOpportunityList getOpportunityList(WsOpportunityList opportunityList) {
		// TODO Auto-generated method stub
		return fmLeadDao.getOpportunityList(opportunityList);
	}

	@Override
	public List<LeadProduct> getLeadProductList(int company_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadProductList(company_id);
	}

	@Override
	public List<LeadHasProducts> getLeadHasProductDetails(int company_id, int lead_id) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadHasProductDetails(company_id,lead_id);
	}

	@Override
	public WsLeadTypeList getLeadProductListData(Credentials credentials) {
		// TODO Auto-generated method stub
		return fmLeadDao.getLeadProductListData(credentials);
	}

}
