package com.fieldmi.dao;

import java.util.List;

import com.softtantra.salesapp.pojo.Citys;
import com.softtantra.salesapp.pojo.Country;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.Taluka;

public interface CountryStateDao {

	boolean addCSCDataObject(Object cscObject,int value);

	boolean checkUniqueCountry(String cscName);

	List<District> getStateWiseDistrict(int state_id);

	List<Taluka> getDistrictWiseTaluka(int district_id);

	List<Citys> getTalukaWiseCity(int taluka_id);

	List<Citys> getStateWiseCity(int state_id);

	List<Country> getCountryList();

	List<States> getCountryWiseState(int country_id);

	boolean importDistricts(String districtXlsFile);

}
