package com.fieldmi.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMProductDao;
import com.softtantra.salesapp.pojo.BrandMaster;
import com.softtantra.salesapp.pojo.CategoryMaster;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.SubCategoryMaster;
import com.softtantra.salesapp.pojo.Tax;

public class FMProductDaoImpl implements FMProductDao {

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
	public ProductDetails addNewProduct(ProductDetails newproductMaster, int c_id, int u_id) {

		Session session = initiateSession();
		try {

			Date date = new Date();

			updateCategoryAndBrand(session, newproductMaster, date, c_id, u_id);
			newproductMaster.setProductListSync(1);
			newproductMaster.setStatus(1);
			session.saveOrUpdate(newproductMaster);
			return newproductMaster;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	private void updateCategoryAndBrand(Session session, ProductDetails newproductMaster, Date date, int c_id, int u_id)
			throws Exception {

		try {
			int category_id = checkCategory(session, newproductMaster.getCategory_id1(), c_id);

			if (category_id == 0) {

				CategoryMaster categoryMaster = new CategoryMaster();

				categoryMaster.setCategory_name(newproductMaster.getCategory_id1());
				categoryMaster.setCompany_id(c_id);
				categoryMaster.setCreated_by(u_id);
				categoryMaster.setCreated_date(date);
				categoryMaster.setUpdated_by(u_id);
				categoryMaster.setUpdated_date(date);

				categoryMaster.setStatus(1);
				session.save(categoryMaster);

				newproductMaster.setCategory_id(categoryMaster.getCategory_id());

			} else if (category_id != 0) {
				newproductMaster.setCategory_id(category_id);
			}

			if (newproductMaster.getSub_category1() != null) {

				int sub_category = checkSubCategory(session, newproductMaster.getSub_category1());

				if (sub_category == 0) {

					SubCategoryMaster subCategoryMaster = new SubCategoryMaster();

					subCategoryMaster.setSub_category_name(newproductMaster.getSub_category1());
					subCategoryMaster.setCategory_id(newproductMaster.getCategory_id());
					subCategoryMaster.setCompany_id(c_id);
					subCategoryMaster.setCreated_by(u_id);
					subCategoryMaster.setCreated_date(date);
					subCategoryMaster.setUpdated_by(u_id);
					subCategoryMaster.setUpdated_date(date);
					subCategoryMaster.setStatus(1);
					session.save(subCategoryMaster);

					newproductMaster.setSub_category(subCategoryMaster.getSub_category_id());

				} else if (sub_category != 0) {
					newproductMaster.setSub_category(sub_category);
				}
			}

			int brand_id = checkBrand(session, newproductMaster.getBrand_id1());

			if (brand_id == 0) {

				BrandMaster brandMaster = new BrandMaster();
				brandMaster.setBrand_name(newproductMaster.getBrand_id1());
				brandMaster.setCompany_id(c_id);
				brandMaster.setCreated_by(u_id);
				brandMaster.setCreated_date(date);
				brandMaster.setUpdated_by(u_id);
				brandMaster.setUpdated_date(date);				
				brandMaster.setStatus(1);
				session.save(brandMaster);

				newproductMaster.setBrand_id(brandMaster.getBrand_id());
			} else if (brand_id != 0) {
				newproductMaster.setBrand_id(brand_id);
			}
		} catch (Exception e) {

			throw e;
		}

	}

	private int checkCategory(Session session, String category_id1, int c_id) throws Exception {

		if (category_id1 == null || category_id1.length() == 0)
			return 0;

		try {
			Query query = session.createQuery("select category_id from  CategoryMaster where category_name='"
					+ category_id1 + "'" + " and company_id=" + c_id + "");

			if (query.uniqueResult() == null) {
				return 0;
			} else {
				return (int) query.uniqueResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.warn(this.getClass().getName(), e.getMessage());
			throw e;
		}

	}

	//
	private int checkBrand(Session session, String string) throws Exception {

		if (string == null || string.length() == 0)
			return 0;

		try {
			Query query = session.createQuery("select brand_id from BrandMaster where brand_name='" + string + "'");

			if (query.uniqueResult() == null) {
				return 0;
			} else {
				return (int) query.uniqueResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.warn(this.getClass().getName(), e.getMessage());
			throw e;
		}

	}

	//
	private int checkSubCategory(Session session, String string) throws Exception {

		if (string == null || string.length() == 0)
			return 0;

		try {

			Query query = session.createQuery(
					"select sub_category_id from SubCategoryMaster where sub_category_name='" + string + "'");

			if (query.uniqueResult() == null) {
				return 0;
			} else {
				return (int) query.uniqueResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			FieldMILogger.warn(this.getClass().getName(), e.getMessage());
			throw e;
		}

	}

	@Override
	public NewPriceMaster addNewPriceMaster(NewPriceMaster priceMaster, int c_id, int u_id) {

		Session session = initiateSession();
		try {

			session.saveOrUpdate(priceMaster);
			return priceMaster;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<ProductDetails> getProductDetails(String productCode, int c_id) {

		Session session = initiateSession();
		try {

			Query<ProductDetails> query = session.createQuery("From ProductDetails where UPPER(product_code)='" + productCode.toUpperCase() + "'",
					ProductDetails.class);
			
			return query.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<NewPriceMaster> getPriceDetailsForProduct(int product_id) {
		
		Session session = initiateSession();
		try {

			Query<NewPriceMaster> query = session.createQuery("From NewPriceMaster where product_id=" + product_id,
					NewPriceMaster.class);
			
			return query.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public List<Tax> getTaxList(int c_id) {
		
		Session session = initiateSession();
		try {

			Query<Tax> query = session.createQuery("From Tax where status=1 and company_id=" + c_id ,
					Tax.class);
			
			return query.list();
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

}
