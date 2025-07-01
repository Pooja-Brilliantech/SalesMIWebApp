package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.FMTargetDao;
import com.fieldmi.service.FMTargetService;
import com.softtantra.salesapp.pojo.TargetDetails;
import com.softtantra.salesapp.pojo.TargetImportRecords;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasCategory;

public class FMTargetServiceImpl implements FMTargetService {

	@Autowired
	FMTargetDao fmTargetDao;
	
	public void setFmTargetDao(FMTargetDao fmTargetDao) {
		this.fmTargetDao = fmTargetDao;
	}

	@Override
	public boolean saveTargets(TargetDetails targetDetails, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmTargetDao.saveTargets(targetDetails,c_id,u_id);
	}

	@Override
	public boolean saveUsersCategory(UserHasCategory userHasCategory, int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmTargetDao.saveUsersCategory(userHasCategory,c_id,u_id);
	}

	@Override
	public boolean deleteTarget(int user_id, int c_id) {
		// TODO Auto-generated method stub
		return fmTargetDao.deleteTarget(user_id,c_id);
	}

	@Override
	public boolean checkExist(TargetDetails targetDetails, int c_id) {
		// TODO Auto-generated method stub
		return fmTargetDao.checkExist(targetDetails,c_id);
	}

	@Override
	public List<TargetDetails> getTargetDetails(int c_id, int u_id) {
		// TODO Auto-generated method stub
		return fmTargetDao.getTargetDetails(c_id,u_id);
	}

	@Override
	public List<TargetImportRecords> getCalculatedRecords(TargetImportRecords targetImportRecords,List<User> userList, List<String> monthNamesYears, int c_id,
			int u_id,List<String> monthNames) {
		// TODO Auto-generated method stub
		return fmTargetDao.getCalculatedRecords(targetImportRecords,userList,monthNamesYears,c_id,u_id,monthNames);
	}

}
