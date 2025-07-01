package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.BusinessUnitDao;
import com.fieldmi.stubs.WsBusinessUnitOutput;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.ws.Credentials;

public class BusinessUnitDaoImpl implements BusinessUnitDao{

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
	public WsBusinessUnitOutput getBusinessUnitList(Credentials cre) throws Exception {
		// TODO Auto-generated method stub
		Session sessionForDB = initiateSession();
		try {
			List list = null;
			int total_pages = 0;
			WsBusinessUnitOutput output=new WsBusinessUnitOutput();
			String query = "";
			query = "From BusinessUnitMaster where company_id=" + cre.getCompany_id() + " and status=1";
			
			list = commonMethods.quaryListPagination(sessionForDB, query, cre.getPage_no(),1000);

			total_pages = commonMethods.getTotalPagesCountSql(sessionForDB,
					"select count(buId) FROM  business_unit_master where company_id=" + cre.getCompany_id() + " and status=1");
			if(list!=null && list.size() > 0)
			{
				output.setWsBusinessUnitList(list);
				output.setPage_No(cre.getPage_no());
				output.setRecordsPerPage(1000);
				output.setTotalRecords(total_pages);
			} else {
				
				output.setWsBusinessUnitList(new ArrayList<>());
				output.setPage_No(cre.getPage_no());
				output.setRecordsPerPage(1000);
				output.setTotalRecords(total_pages);
			}

			return output;
		}catch(Exception e)
		{
			throw e;
		}
		finally
		{
			destroySession(sessionForDB);
		}
	}

}
