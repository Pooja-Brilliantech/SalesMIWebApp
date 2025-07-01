package com.fieldmi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.dao.CountryStateDao;
import com.fieldmi.service.CountryStateService;
import com.softtantra.salesapp.pojo.Citys;
import com.softtantra.salesapp.pojo.Country;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.Taluka;

public class CountryStateServiceImpl implements CountryStateService {

	@Autowired
	CountryStateDao countryStateDao;
	
	
	public void setCountryStateDao(CountryStateDao countryStateDao) {
		this.countryStateDao = countryStateDao;
	}

	@Override
	public boolean addCSCDataObject(Object cscObject,int value) {
		
		return countryStateDao.addCSCDataObject(cscObject,value);
	}

	@Override
	public boolean checkUniqueCountry(String countryName) {
		
		return countryStateDao.checkUniqueCountry(countryName);
	}

	@Override
	public List<District> getStateWiseDistrict(int state_id) {
		
		return countryStateDao.getStateWiseDistrict(state_id);
	}

	@Override
	public List<Taluka> getDistrictWiseTaluka(int district_id) {
		return countryStateDao.getDistrictWiseTaluka(district_id);
	}

	@Override
	public List<Citys> getTalukaWiseCity(int taluka_id) {
		return countryStateDao.getTalukaWiseCity(taluka_id);
	}

	@Override
	public List<Citys> getStateWiseCity(int state_id) {
		
		return countryStateDao.getStateWiseCity(state_id);
	}

	@Override
	public List<Country> getCountryList() {
		
		return countryStateDao.getCountryList();
	}

	@Override
	public List<States> getCountryWiseState(int country_id) {
		
		return countryStateDao.getCountryWiseState(country_id);
	}

	@Override
	public boolean importDistricts(String districtXlsFile) {
		
		return countryStateDao.importDistricts(districtXlsFile);
		
	}

}
