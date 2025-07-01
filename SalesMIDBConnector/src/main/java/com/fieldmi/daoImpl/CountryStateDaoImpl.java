package com.fieldmi.daoImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.CountryStateDao;
import com.softtantra.salesapp.pojo.Citys;
import com.softtantra.salesapp.pojo.Country;
import com.softtantra.salesapp.pojo.District;
import com.softtantra.salesapp.pojo.States;
import com.softtantra.salesapp.pojo.Taluka;

public class CountryStateDaoImpl implements CountryStateDao {

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
	public boolean addCSCDataObject(Object cscObject, int value) {
		Session session = initiateSession();
		// TODO Auto-generated method stub
		try {
			if (value == 0) {
				session.save(cscObject);
				return true;
			} else {
				session.update(cscObject);
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean checkUniqueCountry(String cscName) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		// int c_id=CommonController.getCompanyId();
		try {
			Query query = session.createQuery("select count(*) from Country where country_name='" + cscName + "' ");

			long count = (Long) query.uniqueResult();
			//// FieldMILogger.debug(this.getClass().getName(),"count "+count);
			if (count == 0) {
				return false;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	@Transactional
	public List<District> getStateWiseDistrict(int state_id) {

		Session session = initiateSession();
		List<District> districts = new ArrayList<District>();
		try {

			Query<District> query;
			if (state_id != 0) {
				query = session.createQuery("From District where state_id=" + state_id + "");
			} else {
				query = session.createQuery("From District");
			}
			districts = query.list();
			return districts;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	@Transactional
	public List<Taluka> getDistrictWiseTaluka(int district_id) {

		Session session = initiateSession();
		List<Taluka> talukas = new ArrayList<Taluka>();
		try {

			Query<Taluka> query;
			if (district_id != 0) {
				query = session.createQuery("From Taluka where district_id=" + district_id + "");
			} else {
				query = session.createQuery("From Taluka");
			}
			talukas = query.list();
			return talukas;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	@Transactional
	public List<Citys> getTalukaWiseCity(int taluka_id) {

		Session session = initiateSession();
		List<Citys> citys = new ArrayList<Citys>();
		try {

			Query<Citys> query;
			if (taluka_id != 0) {
				query = session.createQuery("From Citys where taluka_id=" + taluka_id + "");
			} else {
				query = session.createQuery("From Citys");
			}
			citys = query.list();
			return citys;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	@Transactional
	public List<Citys> getStateWiseCity(int state_id) {

		Session session = initiateSession();
		List<Citys> citys = new ArrayList<Citys>();
		try {

			Query<Citys> query;
			if (state_id != 0) {
				query = session.createQuery("From Citys where state_id=" + state_id + "");
			} else {
				query = session.createQuery("From Citys");
			}
			citys = query.list();
			return citys;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	@Transactional
	public List<States> getCountryWiseState(int country_id) {

		Session session = initiateSession();
		List<States> states = new ArrayList<States>();
		try {

			Query<States> query;
			if (country_id != 0) {
				query = session.createQuery("From States where country_id=" + country_id + "");
			} else {
				query = session.createQuery("From States");
			}
			states = query.list();
			return states;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	@Transactional
	public List<Country> getCountryList() {

		Session session = initiateSession();
		List<Country> countries = new ArrayList<Country>();
		try {

			Query<Country> query;
			query = session.createQuery("From Country");

			countries = query.list();
			return countries;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean importDistricts(String districtXlsFile) {

		Session session = initiateSession();
		DataFormatter formatter = new DataFormatter();
		String districtCode = null;
		String villageCode = null;
		String subDistrictCode = null;
		String subDistrictName = null;
		String districtName = null;
		String villageName = null;
		Calendar calendar = Calendar.getInstance();

		try {

			File folder = new File(districtXlsFile);
			File[] files = null;
			if (folder.isDirectory()) {

				files = folder.listFiles();
			} else {

				files = new File[1];
				files[0] = folder;
			}

			if (files != null && files.length > 0) {

				for (File file : files) {

					FieldMILogger.warn(CountryStateDaoImpl.class.getName(), "File:" + file.getName());
					if (file.getName().equals(".DS_Store")) {

						continue;
					}
					Workbook workbook = new XSSFWorkbook(file);
					Sheet worksheet = workbook.getSheetAt(0);
					List<District> districts = new ArrayList<District>();
					List<States> states = new ArrayList<States>();
					List<Citys> cities = new ArrayList<Citys>();
					List<Taluka> talukas = new ArrayList<Taluka>();
					int i = 1;
					int newStateCounter = 0;
					int oldStateCounter = 0;
					int newDistrictCounter = 0;
					int oldDistrictCounter = 0;
					int newSubDistrictCounter = 0;
					int oldSubDistrictCounter = 0;
					int newCityCounter = 0;
					int oldCityCounter = 0;
					int currStateId = 0;
					while (i < worksheet.getLastRowNum()) {

						Row row = worksheet.getRow(i);
						i++;
						String stateCode = formatter.formatCellValue(row.getCell(0)).trim().replace("'", "");
						if (currStateId == 0) {
							Query<States> stateQuery = session
									.createQuery("From States where state_code='" + stateCode + "'");
							states = stateQuery.list();
							if (states != null && states.size() > 0)
								currStateId = states.get(0).getState_id();

							Query<Citys> cityQuery = session.createQuery("From Citys where state_id=" + currStateId);
							cities = cityQuery.list();

							Query<District> districtQuery = session
									.createQuery("From District where state_id=" + currStateId);
							districts = districtQuery.list();
							
							Query<Taluka> talukaQuery = session.createQuery(
									"From Taluka where status=0" );
							talukas = talukaQuery.list();
						}

						districtCode = formatter.formatCellValue(row.getCell(1)).trim().replace("'", "")
								.replace("&", "AND").trim();
						districtName = formatter.formatCellValue(row.getCell(2)).trim().toUpperCase()
								.replace(" *", "").replace("'", "").replace("&", "AND").trim();
						subDistrictCode = formatter.formatCellValue(row.getCell(3)).trim().replace("'", "")
								.replace("&", "AND").trim();
						subDistrictName = formatter.formatCellValue(row.getCell(4)).trim().toUpperCase()
								.replace(" *", "").replace("'", "").replace("&", "AND").trim();
						villageCode = formatter.formatCellValue(row.getCell(5)).trim().replace("'", "")
								.replace("&", "AND").trim();
						villageName = formatter.formatCellValue(row.getCell(6)).trim().toUpperCase()
								.replace(" *", "").replace("'", "").replace("&", "AND").trim();
						FieldMILogger.debug(CountryStateDaoImpl.class.getName(),
								"villageName:" + villageName + " villageCode:" + villageCode + " subDistrictName:"
										+ subDistrictName + " subDistrictCode:" + subDistrictCode + " districtName:"
										+ districtName + " districtCode:" + districtCode);
						if (districtCode.equals("000") && subDistrictCode.equals("00000")
								&& villageCode.equals("000000")) {

							// its a state
							boolean stateExists = false;
							if (currStateId != 0) {

								stateExists = true;
								states.get(0).setState_name(villageName);
								states.get(0).setState_code(stateCode);
								states.get(0).setCreated_date(calendar.getTime());
								states.get(0).setUpdated_date(calendar.getTime());
								session.beginTransaction();
								session.saveOrUpdate(states.get(0));
								session.getTransaction().commit();
								currStateId = states.get(0).getState_id();
								oldStateCounter++;

							}
							if (!stateExists) {

								// create new state
								States newState = new States();
								newState.setState_name(villageName);
								newState.setState_code(stateCode);
								states.get(0).setCreated_date(calendar.getTime());
								states.get(0).setUpdated_date(calendar.getTime());
								session.beginTransaction();
								session.saveOrUpdate(newState);
								session.getTransaction().commit();
								currStateId = newState.getState_id();
								newStateCounter++;
							}

						} else if (!districtCode.equals("000") && subDistrictCode.equals("00000")
								&& villageCode.equals("000000")) {

							// its a district
							boolean districtExists = false;
							if (districts != null && districts.size() > 0) {

								Iterator<District> distrItr = districts.iterator();
								while (distrItr.hasNext()) {

									District dbDistrict = distrItr.next();
									if (dbDistrict.getDistDescription().trim().toUpperCase().equals(districtName)) {

										dbDistrict.setDistrictCode(districtCode);
										dbDistrict.setDistDescription(districtName);
										dbDistrict.setState_id(currStateId);
										dbDistrict.setStatus(1);
										dbDistrict.setCreated_date(calendar.getTime());
										dbDistrict.setUpdated_date(calendar.getTime());

										session.beginTransaction();
										session.saveOrUpdate(dbDistrict);
										session.getTransaction().commit();

										distrItr.remove();
										districtExists = true;
										oldDistrictCounter++;
										break;
									}
								}
							}

							if (!districtExists) {

								District dbDistrict = new District();
								dbDistrict.setDistrictCode(districtCode);
								dbDistrict.setDistDescription(districtName);
								dbDistrict.setState_id(currStateId);
								dbDistrict.setStatus(1);
								dbDistrict.setCreated_date(calendar.getTime());
								dbDistrict.setUpdated_date(calendar.getTime());

								session.beginTransaction();
								session.saveOrUpdate(dbDistrict);
								session.getTransaction().commit();
								if( districts == null ) {
									districts = new ArrayList<District>();
								}
								newDistrictCounter++;
							}

						} else if (!districtCode.equals("000") && !subDistrictCode.equals("00000")
								&& villageCode.equals("000000")) {

							// its a taluka
							boolean talukaExists = false;
							Query<District> districtQuery = session
									.createQuery("From District where districtCode='" + districtCode + "'");
							List<District> shortDistricts = districtQuery.list();

							if (talukas != null && talukas.size() > 0) {

								Iterator<Taluka> talkuaItr = talukas.iterator();
								while (talkuaItr.hasNext()) {

									Taluka dbTaluka = talkuaItr.next();
									if (dbTaluka.getTalukaDescription().trim().toUpperCase().equals(subDistrictName)) {

										talukaExists = true;
										dbTaluka.setDistrict_id(shortDistricts.get(0).getDistrict_id());
										dbTaluka.setStatus(1);
										dbTaluka.setTalukaDescription(subDistrictName);
										dbTaluka.setTalukaCode(subDistrictCode);
										dbTaluka.setCreated_date(calendar.getTime());
										dbTaluka.setUpdated_date(calendar.getTime());
										session.beginTransaction();
										session.saveOrUpdate(dbTaluka);
										session.getTransaction().commit();
										talkuaItr.remove();
										oldSubDistrictCounter++;
										break;
									}
								}
							}

							if (!talukaExists) {

								Taluka dbTaluka = new Taluka();
								dbTaluka.setDistrict_id(shortDistricts.get(0).getDistrict_id());
								dbTaluka.setStatus(1);
								dbTaluka.setTalukaDescription(subDistrictName);
								dbTaluka.setTalukaCode(subDistrictCode);
								dbTaluka.setCreated_date(calendar.getTime());
								dbTaluka.setUpdated_date(calendar.getTime());
								session.beginTransaction();
								session.saveOrUpdate(dbTaluka);
								session.getTransaction().commit();								
								newSubDistrictCounter++;
							}

						} else if (!districtCode.equals("000") && !subDistrictCode.equals("00000")
								&& !villageCode.equals("000000")) {

							// its a city
							boolean cityExists = false;

							Query<Taluka> talukaQuery = session
									.createQuery("From Taluka where talukaCode='" + subDistrictCode + "'");
							List<Taluka> shortTalukas = talukaQuery.list();

							if (cities != null && cities.size() > 0) {

								Iterator<Citys> cityItr = cities.iterator();
								while (cityItr.hasNext()) {

									Citys dbCity = cityItr.next();
									if (dbCity.getCity_name().trim().toUpperCase().equals(villageName)
											 && (dbCity.getCity_code() == null || dbCity.getCity_code().equals(villageCode))) {

										cityExists = true;
										dbCity.setCity_code(villageCode);
										dbCity.setCity_name(villageName);
										dbCity.setState_id(currStateId);
										dbCity.setCreated_date(calendar.getTime());
										dbCity.setUpdated_date(calendar.getTime());
										dbCity.setDistrict_id(shortTalukas.get(0).getDistrict_id());
										dbCity.setTaluka_id(shortTalukas.get(0).getTaluka_id());
										session.saveOrUpdate(dbCity);
										cityItr.remove();
										oldCityCounter++;
										break;
									}
								}
							}

							if (!cityExists) {

								Citys dbCity = new Citys();
								dbCity.setCity_code(villageCode);
								dbCity.setCity_name(villageName);
								dbCity.setState_id(currStateId);
								dbCity.setCreated_date(calendar.getTime());
								dbCity.setUpdated_date(calendar.getTime());
								dbCity.setDistrict_id(shortTalukas.get(0).getDistrict_id());
								dbCity.setTaluka_id(shortTalukas.get(0).getTaluka_id());
								session.saveOrUpdate(dbCity);
								newCityCounter++;
							}

						}

					}
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(), "Total New States:" + newStateCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(), "Total Old States:" + oldStateCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(),
							"Total New Districts:" + newDistrictCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(),
							"Total Old Districts:" + oldDistrictCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(),
							"Total New Talukas:" + newSubDistrictCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(),
							"Total Old Talukas:" + oldSubDistrictCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(), "Total New Cities:" + newCityCounter);
					FieldMILogger.warn(CountryStateDaoImpl.class.getName(), "Total Old Cities:" + oldCityCounter);
				}
			}

			return true;
		} catch (Exception e) {
			
			FieldMILogger.warn(CountryStateDaoImpl.class.getName(),
					"villageName:" + villageName + " villageCode:" + villageCode + " subDistrictName:"
							+ subDistrictName + " subDistrictCode:" + subDistrictCode + " districtName:"
							+ districtName + " districtCode:" + districtCode);
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
	}

}
