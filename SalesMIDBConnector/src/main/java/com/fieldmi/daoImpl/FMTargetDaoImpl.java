package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMTargetDao;
import com.softtantra.salesapp.pojo.TargetDetails;
import com.softtantra.salesapp.pojo.TargetImportRecords;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.UserHasCategory;

public class FMTargetDaoImpl implements FMTargetDao {

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
	public boolean saveTargets(TargetDetails targetDetails, int c_id, int u_id) {
		Session session = initiateSession();
		try {
			targetDetails.setCompany_id(c_id);
			targetDetails.setCreated_by(u_id);
			targetDetails.setCreated_date(new Date());
			targetDetails.setUpdated_by(u_id);
			targetDetails.setUpdated_date(new Date());
			targetDetails.setStatus(1);
			session.save(targetDetails);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public boolean saveUsersCategory(UserHasCategory userHasCategory, int c_id, int u_id) {
		Session session = initiateSession();
		try {
			userHasCategory.setCompany_id(c_id);
			userHasCategory.setCreated_by(u_id);
			userHasCategory.setCreated_date(new Date());
			userHasCategory.setUpdated_by(u_id);
			userHasCategory.setUpdated_date(new Date());
			userHasCategory.setStatus(1);
			session.save(userHasCategory);

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}

		return true;
	}

	@Override
	public boolean deleteTarget(int user_id, int c_id) {
		Session session = initiateSession();
		try {

			Query query1 = session
					.createQuery("update TargetDetails set status=:status where company_id=:c_id and user_id=:user_id");
			query1.setParameter("c_id", c_id);
			query1.setParameter("status", 0);
			query1.setParameter("user_id", user_id);
			int id = query1.executeUpdate();

			if (id > 0) {
				Query query = session.createQuery(
						"update UserHasCategory set status=:status where company_id=:c_id and user_id=:user_id");
				query.setParameter("c_id", c_id);
				query.setParameter("status", 0);
				query.setParameter("user_id", user_id);
				query.executeUpdate();
			}

			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean checkExist(TargetDetails targetDetails, int c_id) {
		Session session = initiateSession();
		try {

			Query query1 = session.createQuery("select target_id from TargetDetails where company_id=" + c_id
					+ " and finanacialYear=" + targetDetails.getFinanacialYear() + " and month='"
					+ targetDetails.getMonth() + "' and user_id=" + targetDetails.getUser_id());

			if (query1.uniqueResult() == null) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public List<TargetDetails> getTargetDetails(int c_id, int u_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TargetImportRecords> getCalculatedRecords(TargetImportRecords targetImportRecords, List<User> userList,
			List<String> monthNamesYears, int c_id, int u_id, List<String> monthNames) {
		// TODO Auto-generated method stub
		try {
			List<TargetImportRecords> returnTargetImportRecords = new ArrayList<>();
			double amount = 0;
			double volume = 0;
			double collection = 0;
			double orders = 0;
			double visits = 0;
			for (int i = 0; i < userList.size(); i++) {
				if (targetImportRecords.getMonth().equals("1")) {

					if (userList.get(i).getUser_id() != u_id) {

						if (targetImportRecords.getAmount() != "") {
							amount = (Double.parseDouble(targetImportRecords.getAmount()) / (userList.size() - 1)) / 12;

						}
						if (targetImportRecords.getVolume() != "") {
							volume = (Double.parseDouble(targetImportRecords.getVolume()) / (userList.size() - 1)) / 12;

						}
						if (targetImportRecords.getCollection() != "") {
							collection = (Double.parseDouble(targetImportRecords.getCollection())
									/ (userList.size() - 1)) / 12;

						}
						if (targetImportRecords.getOrders() != "") {
							orders = (Double.parseDouble(targetImportRecords.getOrders()) / (userList.size() - 1)) / 12;

						}
						if (targetImportRecords.getVisits() != "") {
							visits = (Double.parseDouble(targetImportRecords.getVisits()) / (userList.size() - 1)) / 12;

						}
						for (int j = 0; j < monthNamesYears.size(); j++) {
							TargetImportRecords tRecords = new TargetImportRecords();
							String[] splitValue = monthNamesYears.get(j).split("-");
							tRecords.setUser_id(userList.get(i).getUser_id());
							tRecords.setUser_name(userList.get(i).getFirst_name());
							tRecords.setMonth(splitValue[0]);
							tRecords.setFinanacialYear(Integer.parseInt(splitValue[1]));
							tRecords.setCategoryName(targetImportRecords.getCategoryName());
							tRecords.setAmount(String.valueOf(amount));
							tRecords.setVolume(String.valueOf(volume));
							tRecords.setCollection(String.valueOf(collection));
							tRecords.setOrders(String.valueOf(orders));
							tRecords.setVisits(String.valueOf(visits));
							tRecords.setRole(userList.get(i).getRole_name());
							returnTargetImportRecords.add(tRecords);
						}

					}
				} else if (targetImportRecords.getMonth().equals("2")) {
					if (userList.get(i).getUser_id() != u_id) {
						Calendar cal = Calendar.getInstance();
						for (int j = 0; j < monthNamesYears.size(); j++) {
							String[] splitValue = monthNamesYears.get(j).split("-");
							cal.set(Calendar.YEAR, Integer.parseInt(splitValue[1]));
							// cal.set(Calendar.DAY_OF_MONTH, 1);
							if (splitValue[0].equals("JANUARY")) {
								cal.set(Calendar.MONTH, 0);
							} else if (splitValue[0].equals("FEBRUARY")) {
								cal.set(Calendar.MONTH, 1);
							} else if (splitValue[0].equals("MARCH")) {
								cal.set(Calendar.MONTH, 2);
							} else if (splitValue[0].equals("APRIL")) {
								cal.set(Calendar.MONTH, 3);
							} else if (splitValue[0].equals("MAY")) {
								cal.set(Calendar.MONTH, 4);
							} else if (splitValue[0].equals("JUNE")) {
								cal.set(Calendar.MONTH, 5);
							} else if (splitValue[0].equals("JULY")) {
								cal.set(Calendar.MONTH, 6);
							} else if (splitValue[0].equals("AUGUST")) {
								cal.set(Calendar.MONTH, 7);
							} else if (splitValue[0].equals("SEPTEMBER")) {
								cal.set(Calendar.MONTH, 8);
							} else if (splitValue[0].equals("OCTOBER")) {
								cal.set(Calendar.MONTH, 9);
							} else if (splitValue[0].equals("NOVEMBER")) {
								cal.set(Calendar.MONTH, 10);
							} else if (splitValue[0].equals("DECEMBER")) {
								cal.set(Calendar.MONTH, 11);
							}

							int maxWeeknumber = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

							

							for (int i1 = 0; i1 < maxWeeknumber; i1++) {
								TargetImportRecords tRecords = new TargetImportRecords();
								tRecords.setUser_id(userList.get(i).getUser_id());
								tRecords.setUser_name(userList.get(i).getFirst_name());
								tRecords.setMonth(splitValue[0]);
								tRecords.setFinanacialYear(Integer.parseInt(splitValue[1]));
								tRecords.setCategoryName(targetImportRecords.getCategoryName());
								tRecords.setAmount(String.valueOf(amount));
								tRecords.setVolume(String.valueOf(volume));
								tRecords.setCollection(String.valueOf(collection));
								tRecords.setOrders(String.valueOf(orders));
								tRecords.setVisits(String.valueOf(visits));
								int week = i1 + 1;
								tRecords.setWeekNumber("Week -  " + week);
								tRecords.setRole(userList.get(i).getRole_name());
								returnTargetImportRecords.add(tRecords);
							}
							
//							if (targetImportRecords.getAmount() != "") {
//								amount = (Double.parseDouble(targetImportRecords.getAmount()) / (userList.size() - 1)) / returnTargetImportRecords.size();
//
//							}
//							if (targetImportRecords.getVolume() != "") {
//								volume = (Double.parseDouble(targetImportRecords.getVolume()) / (userList.size() - 1)) / returnTargetImportRecords.size();
//
//							}
//							if (targetImportRecords.getCollection() != "") {
//								collection = (Double.parseDouble(targetImportRecords.getCollection())
//										/ (userList.size() - 1)) / returnTargetImportRecords.size();
//
//							}
//							if (targetImportRecords.getOrders() != "") {
//								orders = (Double.parseDouble(targetImportRecords.getOrders()) / (userList.size() - 1)) / returnTargetImportRecords.size();
//
//							}
//							if (targetImportRecords.getVisits() != "") {
//								visits = (Double.parseDouble(targetImportRecords.getVisits()) / (userList.size() - 1)) / returnTargetImportRecords.size();
//
//							}

							//System.out.println(returnTargetImportRecords.size());

						}
					}
				}else if(targetImportRecords.getMonth().equals("3")) {
					Calendar cal = Calendar.getInstance();
					if (userList.get(i).getUser_id() != u_id) {
						
						for (int j = 0; j < monthNamesYears.size(); j++) {
							String[] splitValue = monthNamesYears.get(j).split("-");
							
							if (splitValue[0].equals("JANUARY")) {
								cal.set(Calendar.MONTH, 0);
							} else if (splitValue[0].equals("FEBRUARY")) {
								cal.set(Calendar.MONTH, 1);
							} else if (splitValue[0].equals("MARCH")) {
								cal.set(Calendar.MONTH, 2);
							} else if (splitValue[0].equals("APRIL")) {
								cal.set(Calendar.MONTH, 3);
							} else if (splitValue[0].equals("MAY")) {
								cal.set(Calendar.MONTH, 4);
							} else if (splitValue[0].equals("JUNE")) {
								cal.set(Calendar.MONTH, 5);
							} else if (splitValue[0].equals("JULY")) {
								cal.set(Calendar.MONTH, 6);
							} else if (splitValue[0].equals("AUGUST")) {
								cal.set(Calendar.MONTH, 7);
							} else if (splitValue[0].equals("SEPTEMBER")) {
								cal.set(Calendar.MONTH, 8);
							} else if (splitValue[0].equals("OCTOBER")) {
								cal.set(Calendar.MONTH, 9);
							} else if (splitValue[0].equals("NOVEMBER")) {
								cal.set(Calendar.MONTH, 10);
							} else if (splitValue[0].equals("DECEMBER")) {
								cal.set(Calendar.MONTH, 11);
							}

							//int maxWeeknumber = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
							cal.set(Integer.parseInt(splitValue[1]),cal.get(cal.MONTH),1, 0, 0, 1);
							int workingDays = calculateWorkingDayOfMonth(cal);
							

							for (int i1 = 0; i1 < workingDays; i1++) {
								TargetImportRecords tRecords = new TargetImportRecords();
								tRecords.setUser_id(userList.get(i).getUser_id());
								tRecords.setUser_name(userList.get(i).getFirst_name());
								tRecords.setMonth(splitValue[0]);
								tRecords.setFinanacialYear(Integer.parseInt(splitValue[1]));
								tRecords.setCategoryName(targetImportRecords.getCategoryName());
								tRecords.setAmount(String.valueOf(amount));
								tRecords.setVolume(String.valueOf(volume));
								tRecords.setCollection(String.valueOf(collection));
								tRecords.setOrders(String.valueOf(orders));
								tRecords.setVisits(String.valueOf(visits));
								int week = i1 + 1;
								tRecords.setDaysNumber("Day -  " + week);
								tRecords.setRole(userList.get(i).getRole_name());
								returnTargetImportRecords.add(tRecords);
							}
							
						   

						}
					}
				

				}
			}
			return returnTargetImportRecords;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		}
		return null;
	}
	
	private int calculateWorkingDayOfMonth(Calendar startCal) {

		int workingDays = 0;

		Calendar endCal = Calendar.getInstance();
		int lastDate = startCal.getActualMaximum(Calendar.DATE);
		endCal.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
		endCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH));
		endCal.set(Calendar.DATE, lastDate);
		/* run the while loop until the months are same */
		
		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workingDays++;
			}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		return workingDays;
	}
}
