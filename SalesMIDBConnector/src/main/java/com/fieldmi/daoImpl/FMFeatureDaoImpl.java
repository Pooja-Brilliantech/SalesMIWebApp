package com.fieldmi.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMFeatureDao;
import com.softtantra.salesapp.pojo.DistributorPermissions;
import com.softtantra.salesapp.pojo.MobilePermissions;
import com.softtantra.salesapp.pojo.Role;
import com.softtantra.salesapp.pojo.RolePermissions;

public class FMFeatureDaoImpl implements FMFeatureDao {

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

	private int checkIfAdminPermission(Session session, RolePermissions objclassRolePermission) throws Exception {

		if (objclassRolePermission.getCompany_id() == 0) {

			Query permissionQuery = session
					.createQuery(
							"FROM RolePermissions where name=:name and company_id=:company_id and mainmenu=:mainmenu")
					.setParameter("name", objclassRolePermission.getName())
					.setParameter("company_id", objclassRolePermission.getCompany_id())
					.setParameter("mainmenu", objclassRolePermission.getMainmenu());

			Query roleQuery = session.createQuery("FROM Role where name=:name and company_id=:company_id")
					.setParameter("name", "Super Admin")
					.setParameter("company_id", objclassRolePermission.getCompany_id());

			List<Role> roleList = roleQuery.list();
			List<RolePermissions> permissionsList = permissionQuery.list();
			if (permissionsList.size() > 0 && roleList.size() > 0) {

				RolePermissions rolePermission = permissionsList.get(0);
				Role role = roleList.get(0);

				Query rolePermissionIQuery = session
						.createSQLQuery("insert into role_has_rolepermission (role_id, permission_id) VALUES ("
								+ role.getId() + "," + rolePermission.getId() + ")");
				return rolePermissionIQuery.executeUpdate();
			}

		}

		return 0;

	}

	@Override
	@Transactional
	public boolean addFeature(RolePermissions objclassRolePermission) {

		Session session = initiateSession();
		try {

			session.save(objclassRolePermission);

			checkIfAdminPermission(session, objclassRolePermission);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}
	
	@Override
	@Transactional
	public boolean deleteFeature(RolePermissions objclassRolePermission) {

		Session session = initiateSession();
		try {

			Query query = session.createQuery("update RolePermissions set status=:status where id=:id")
					.setParameter("id", objclassRolePermission.getId()).setParameter("status", 2);
			query.executeUpdate();

			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateFeature(RolePermissions objclassRolePermission) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(objclassRolePermission);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	@Transactional
	public List<RolePermissions> getFeatures(int product_id) {

		List<RolePermissions> featureList = new ArrayList<>();
		Session session = initiateSession();
		try {

			Query query = null;
			if (product_id != 0) {

				query = session.createQuery("FROM RolePermissions rp where product_id=:product_id ORDER BY rp.id ASC")
						.setParameter("product_id", product_id);
			} else {

				query = session.createQuery("FROM RolePermissions rp where product_id=:product_id ORDER BY rp.id ASC");
			}
			if (query.list() != null) {

				featureList = (List<RolePermissions>) query.list();
			}

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}

		return featureList;
	}

	@Override
	public boolean addMobileFeature(MobilePermissions objRolePerm) {

		Session session = initiateSession();
		try {

			boolean result = checkIfMobilePermission(session, objRolePerm);
			if (result) {
				session.save(objRolePerm);

				return true;
			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	private boolean checkIfMobilePermission(Session session, MobilePermissions objRolePerm) {

		Query permissionQuery = session
				.createQuery("FROM MobilePermissions where permission_name=:name and  status=:status")
				.setParameter("name", objRolePerm.getPermission_name()).setParameter("status", objRolePerm.getStatus());
		List<MobilePermissions> permissionsList = permissionQuery.list();
		if (permissionsList.size() > 0) {

			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean updateMobileFeature(MobilePermissions objRolePerm) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(objRolePerm);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	public boolean deleteMobileFeature(MobilePermissions rolePermissions) {

		Session session = initiateSession();
		try {

			Query query = session
					.createQuery("update MobilePermissions set status=:status where mobile_permissions_id=:id")
					.setParameter("id", rolePermissions.getMobile_permissions_id()).setParameter("status", 2);
			query.executeUpdate();

			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}
	
	
	@Override
	@Transactional
	public boolean addDistFeature(DistributorPermissions objclassRolePermission) {

		Session session = initiateSession();
		try {

			session.save(objclassRolePermission);

			checkIfDistPermission(session, objclassRolePermission);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}
	
	@Override
	@Transactional
	public boolean deleteDistFeature(DistributorPermissions objclassRolePermission) {

		Session session = initiateSession();
		try {

			Query query = session.createQuery("update DistributorPermissions set status=:status where id=:id")
					.setParameter("id", objclassRolePermission.getId()).setParameter("status", 2);
			query.executeUpdate();

			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateDistFeature(DistributorPermissions objclassRolePermission) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(objclassRolePermission);
			return true;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return false;
	}
	
	private int checkIfDistPermission(Session session, DistributorPermissions objclassRolePermission) throws Exception {

		if (objclassRolePermission.getCompany_id() == 0) {

			Query permissionQuery = session
					.createQuery(
							"FROM DistributorPermissions where name=:name and company_id=:company_id and mainmenu=:mainmenu")
					.setParameter("name", objclassRolePermission.getName())
					.setParameter("company_id", objclassRolePermission.getCompany_id())
					.setParameter("mainmenu", objclassRolePermission.getMainmenu());

			Query roleQuery = session.createQuery("FROM Role where name=:name and company_id=:company_id")
					.setParameter("name", "Super Admin")
					.setParameter("company_id", objclassRolePermission.getCompany_id());

			List<Role> roleList = roleQuery.list();
			List<RolePermissions> permissionsList = permissionQuery.list();
			if (permissionsList.size() > 0 && roleList.size() > 0) {

				RolePermissions rolePermission = permissionsList.get(0);
				Role role = roleList.get(0);

				Query rolePermissionIQuery = session
						.createSQLQuery("insert into role_has_rolepermission (role_id, permission_id) VALUES ("
								+ role.getId() + "," + rolePermission.getId() + ")");
				return rolePermissionIQuery.executeUpdate();
			}

		}

		return 0;

	}

	@Override
	@Transactional
	public List getObjectList(String fromQuery, int iNITIAL, int rECORD_SIZE) {

		Session session = initiateSession();
		try {
			if (rECORD_SIZE > 0) {
				Query query = session.createQuery(fromQuery).setFirstResult(iNITIAL).setMaxResults(rECORD_SIZE);
				return query.list();
			} else {
				Query query = session.createQuery(fromQuery);
				return query.list();
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}

	}
}
