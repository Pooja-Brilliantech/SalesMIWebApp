package com.fieldmi.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMProfileDao;
import com.softtantra.salesapp.pojo.Profile;
import com.softtantra.salesapp.pojo.ProfileName;

public class FMProfileDaoImpl implements FMProfileDao {

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
	@Transactional
	public int getTotalRecordCount(String string) {
		Session session = initiateSession();
		try {
			return ((BigInteger) session.createSQLQuery(string).uniqueResult()).intValue();
		} catch (Exception e) {
			FieldMILogger.debug(this.getClass().getName(), "\n\t Exception for total count = " + e.toString());
			FieldMILogger.error(this.getClass().getName(), e);
			return 0;
		} finally {
			destroySession(session);
		}
	}

	@Override
	@Transactional
	public List<?> getDataList(String sql, int iNITIAL, int rECORD_SIZE) {
		// FieldMILogger.debug(this.getClass().getName(),"\n\tSales Order List ==>>
		// "+sql);
		// TODO Auto-generated method stub
		Session session = initiateSession();
		try {
			if (rECORD_SIZE > 0) {
				Query query = session.createSQLQuery(sql).setFirstResult(iNITIAL).setMaxResults(rECORD_SIZE);
				return query.list();
			} else {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public boolean addProfileName(ProfileName profileName) {
		Session session1 = initiateSession();
		try {
			profileName.setStatus(1);
			session1.save(profileName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addProfile(List<Profile> profileList) {
		Session session1 = initiateSession();
		try {

			for (Profile profile : profileList) {
				session1.persist(profile);
			}
			session1.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateProfileName(ProfileName profileName) {

		Session session = initiateSession();
		String profileUpdateName = profileName.getProfileName();
		try {
			org.hibernate.Query query = session
					.createQuery("update ProfileName set profileName=:profileName where profileNameId=:profileNameId")
					.setParameter("profileName", profileUpdateName)
					.setParameter("profileNameId", profileName.getProfileNameId());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;

	}

	@Override
	public boolean updateProfile(List<Profile> profileList) {
		Session session1 = initiateSession();
		try {
			org.hibernate.Query query = session1.createQuery("Delete FROM Profile where profileNameId=:profileNameId")
					.setParameter("profileNameId", profileList.get(0).getProfileNameId());
			query.executeUpdate();
			for (Profile profile : profileList) {
				session1.persist(profile);
			}
			session1.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean getProfilesForCompany(int companyId) {
		return false;
	}

	@Override
	public List<Profile> getProfilesById(int profileNameId) {

		Session session = initiateSession();
		List<Profile> profiles = new ArrayList<Profile>();
		try {

			Query query = session.createSQLQuery(
					"Select permissionType, rolepermissionId, pageUrl, isPermitted from profile_details where profileNameId="
							+ profileNameId);

			Query query1 = session.createSQLQuery(
					"Select profileName from profile_name_details where profileNameId=" + profileNameId);
			List list = query.list();
			List list1 = query1.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] object = (Object[]) list.get(i);
				Profile p = new Profile();
				p.setPermissionType((String) object[0]);
				p.setRolePermissionId((int) object[1]);
				p.setPageUrl((String) object[2]);
				ProfileName p1 = new ProfileName();
				p1.setProfileName((String) list1.get(0));
				p1.setProfileNameId(profileNameId);
				p.setProfileName(p1);
				profiles.add(p);
			}
			return profiles;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return new ArrayList<Profile>();
		} finally {
			destroySession(session);
		}

	}

	@Override
	public List<ProfileName> getProfileList(int status, int c_id) {
		// TODO Auto-generated method stub
		Session session = initiateSession();
		List<ProfileName> list2 = new ArrayList<ProfileName>();
		try {
			Query query = session
					.createQuery("from ProfileName where status=" + status + " and company_id=" + c_id + "");

			return query.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return list2;
	}

	@Override
	public boolean deleteProfile(int profileNameId) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("delete from ProfileName where profileNameId=" + profileNameId + "");
			query.executeUpdate();
			query = session.createQuery("delete from Profile where profileNameId=" + profileNameId + "");
			return true;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}

	}

	@Override
	public boolean checkIfProfileIsAssigned(int profileId) {
		Session session = initiateSession();
		try {
			Query query = session.createQuery("from Role where profile_id=" + profileId + "");
			int count = query.list().size();
			query = session.createQuery("Select profile_id from User where profile_id=" + profileId + "");
			count += query.list().size();
			return count > 0;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
	}

}
