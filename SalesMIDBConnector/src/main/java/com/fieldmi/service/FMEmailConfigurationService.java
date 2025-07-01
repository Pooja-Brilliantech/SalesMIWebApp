package com.fieldmi.service;

import java.util.List;

import com.softtantra.salesapp.pojo.WorkFlowAuditLog;

public interface FMEmailConfigurationService {

	void sendMailForPricingApproval(List<WorkFlowAuditLog> workTaskStatusForUpdate, int u_id, int c_id,
			int sales_master_id);

	void sendMailOfDistributorLogin(String scs, int c_id, String username);
	
	void sendCompanyOnboardingEmail(String scs, int c_id, String username);
}
