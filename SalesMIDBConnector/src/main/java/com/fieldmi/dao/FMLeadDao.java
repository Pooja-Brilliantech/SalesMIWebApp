package com.fieldmi.dao;

import java.util.List;
import java.util.Map;
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
import com.softtantra.servicemi.pojo.QuotationDetails;
import com.softtantra.servicemi.pojo.SM_LeadDetails;
import com.softtantra.ws.Credentials;
import com.softtantra.ws.NewOpportunityList;
import com.softtantra.ws.WSActivityList;
import com.softtantra.ws.WSOpportunityList;

public interface FMLeadDao {

	public boolean saveLead(SM_LeadDetails leadDetails, int c_id,int u_id,String data);

	public SM_LeadDetails editLeadDetails(int lead_id, int c_id);

	public boolean updateLead(SM_LeadDetails leadDetails,String data);

	public boolean closeLead(int lead_id, String close_leadstatus, String close_comment, int u_id,int c_id,String close_date);

	public WsLeadList getLeadList(Credentials creadentials);

	public WsLeadTypeList getLeadSourceList(int c_id,int page_no);

	public WsLeadTypeList getLeadIndustryList(int c_id,int page_no);

	public boolean saveLeadConfigurationData(String email, String prefix, String postfix, String start_range,
			String end_range,int c_id,int u_id,String parent_name);

	public List<User> getLeadAllUsers(int c_id);

	public Map<String, Long> getDataForLeadPieChart(int assigned_to, int created_by, String value, 
			 String lead_type, String lead_source, String lead_industry,String type,String industry,String source,int c_id,String sqlAssigned_to);

	public boolean saveOpportunity(OpportunityDetails opportunityDetails, int c_id, int u_id);

	public boolean deleteOpportunity(int c_id,int u_id,OpportunityDetails opportunityDetails);

	public OpportunityDetails editOpportunity(int c_id, int u_id, int opportunity_id);

	public boolean updateOpportunity(OpportunityDetails opportunityDetails, int c_id, int u_id);

	public ActivityDetails editActivity(int c_id, int u_id, int activity_id);

	public boolean updateActivity(ActivityDetails activityDetails, int c_id, int u_id);

	public boolean deleteActivity(int c_id, int u_id, int activity_id);

	public NewOpportunityList getOpportunityPieChartData(int c_id, int u_id,String sqlAssigned);

	public boolean saveQuote(QuotationDetails quotationDetails, int c_id, int u_id);

	public QuotationDetails edit_quote(int c_id, int u_id, int quote_id);

	public boolean update_quote(QuotationDetails quotationDetails, int c_id, int u_id);

	public boolean deleteQuote(int c_id, int u_id, int quote_id);

	public List<OpportunityDetails> getOpportunityNameList(int c_id, int u_id);

	public SM_LeadDetails getLeadObject(String opportunity_name, int c_id);

	public ProductDetails getProductObject(String product_name, int c_id);

	public FieldMIConfiguration getFieldMIConfigurationObject(int c_id,String parent_name);

	public boolean saveActivity(ActivityDetails activityDetails, int c_id, int u_id);

	public WSActivityList getActivityList(ActivityDetails activityDetails, int c_id, int u_id);

	public WSActivityList activityPieChartData(int c_id, int u_id, int parent_id, String parent_type);

	public String getNewIdRange(int c_id,String parent_name);

	public List<OpportunityDetails> getOpportunityReportExport(String opportunityListQuery);

	public boolean saveOpportunityStage(OpportunityStages oppStageObject);

	public boolean updateStage(OpportunityStages opportunityStages, int c_id, int u_id);

	public boolean deleteStage(int c_id, int u_id, int id);

	public List<OpportunityStages> getOpportunityStagesList(int c_id);

	public long checkPercentageSum(int c_id);

	public List<String> getOpportunity_nameList(int c_id);

	public SM_LeadDetails getLeadObject(int lead_id, int c_id);

	public WsOpportunityList getOpportunityList(WsOpportunityList opportunityList);

	public List<LeadProduct> getLeadProductList(int company_id);

	public List<LeadHasProducts> getLeadHasProductDetails(int company_id, int lead_id);

	public WsLeadTypeList getLeadProductListData(Credentials credentials);
	

}
