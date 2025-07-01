
package com.fieldmi.daoImpl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.dao.FMPrimaryOrderDao;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.S3Operations;
import com.softtantra.salesapp.pojo.DistributorStockDetailsHistory;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.PrimaryStockDetails;
import com.softtantra.salesapp.pojo.PrimaryStockDetailsHistory;
import com.softtantra.salesapp.pojo.PrimaryStockGRN;

public class FMPrimaryOrderDaoImpl implements FMPrimaryOrderDao {

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
	public boolean addPrimaryStockGRN(PrimaryStockGRN primaryStockGRN, String data) {
		Session session = initiateSession();
		try {
			String[] RowSplit = data.split("<###>");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int id=0;

			for (int i = 0; i < RowSplit.length; i++) {
				String str = RowSplit[i];
				String[] columnSaparator = str.split("<<<<>>>>");
				if (columnSaparator.length > 1) {
					primaryStockGRN.setProduct_id(Integer.parseInt(columnSaparator[0]));
					primaryStockGRN.setProduct_code(columnSaparator[1]);
					primaryStockGRN.setProduct_name(columnSaparator[2]);
					primaryStockGRN.setQuantity_received(Double.parseDouble(columnSaparator[3]));
					if (!columnSaparator[4].equals(""))
						primaryStockGRN.setExpiry_date(sdf.parse(columnSaparator[4]));
					else
						primaryStockGRN.setExpiry_date(null);
					primaryStockGRN.setPricemasterid(Integer.parseInt(columnSaparator[5]));
					String pack = columnSaparator[6];
					primaryStockGRN.setStatus(1);
					String objectKey = "";
					Query query = session
							.createQuery("from PrimaryStockDetails where product_id='" + primaryStockGRN.getProduct_id()
									+ "' and pricemaster_id=" + primaryStockGRN.getPricemasterid() + " and pack='"
									+ pack + "' and status=1 and company_id=" + primaryStockGRN.getCompany_id()
									+ "  and company_location_id=" + primaryStockGRN.getCompany_location_id());
					PrimaryStockDetails primaryStockDetails = (PrimaryStockDetails) query.uniqueResult();

					if (primaryStockDetails != null) {
						double quantity = 0;
						quantity = primaryStockDetails.getQuantity();
						quantity = quantity + Double.parseDouble(columnSaparator[3]);
						Query query1 = session.createQuery(
								"update PrimaryStockDetails set quantity=:quantity,updated_date=:updated_date,minimum_stock_updated_date=:minimum_stock_updated_date where primary_stock_details_id=:primary_stock_details_id");
						query1.setParameter("primary_stock_details_id", primaryStockDetails.getPrimary_stock_details_id());
						query1.setParameter("quantity", quantity);
						query1.setParameter("updated_date", new Date());
						query1.setParameter("minimum_stock_updated_date", new Date());
						
						int num= query1.executeUpdate();
						if(num>0)
						id=(int) session.save(primaryStockGRN);
						if (primaryStockGRN.getImage().length > 0) {

							String bucketName = CommonMethods.getFileProperties().getProperty("bucketName");
							objectKey =primaryStockGRN.getCompany_id() + S3Operations.PRIMARY_STOCK_IMAGES + id + ".png";
							S3Operations s3Operatons = CommonMethods.getS3OperationClient();
							s3Operatons.addFilesToS3(bucketName, objectKey, new ByteArrayInputStream(primaryStockGRN.getImage()),
									primaryStockGRN.getImage().length);
						}

					} else {
						PrimaryStockDetails primarystockdetails = new PrimaryStockDetails();
						primarystockdetails.setCompany_id(primaryStockGRN.getCompany_id());
						primarystockdetails
								.setCompany_location_id(primaryStockGRN.getCompany_location_id());
						primarystockdetails.setProduct_name(primaryStockGRN.getProduct_name());
						primarystockdetails.setQuantity(Double.parseDouble(columnSaparator[3]));
						primarystockdetails.setUpdated_date(new Date());
						primarystockdetails.setCreated_date(new Date());
						primarystockdetails.setCreated_by(primaryStockGRN.getCreated_by());
						primarystockdetails.setUpdated_by(primaryStockGRN.getUpdated_by());
						primarystockdetails.setProduct_id(Integer.parseInt(columnSaparator[0]));
						primarystockdetails.setPricemaster_id(primaryStockGRN.getPricemasterid());
						primarystockdetails.setPack(pack);
						primarystockdetails.setMinimum_stock_updated_date(new Date());
						primarystockdetails.setStatus(1);

						int num=(int) session.save(primarystockdetails);
						if(num>0)
						id=(int) session.save(primaryStockGRN);
						if (primaryStockGRN.getImage().length > 0) {

							String bucketName = CommonMethods.getFileProperties().getProperty("bucketName");
							objectKey =primaryStockGRN.getCompany_id() + S3Operations.PRIMARY_STOCK_IMAGES +id+ ".png";
							S3Operations s3Operatons = CommonMethods.getS3OperationClient();
							s3Operatons.addFilesToS3(bucketName, objectKey, new ByteArrayInputStream(primaryStockGRN.getImage()),
									primaryStockGRN.getImage().length);
						}
					}

				}
			}
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} finally {
			destroySession(session);
		}
		return true;
	}

	@Override
	public List<NewPriceMaster> getProductWiseItemCode(String product_code, int c_id) {
		Session session = initiateSession();
		List<NewPriceMaster> newPriceMaster = new ArrayList<NewPriceMaster>();
		try {
			Query query = session.createQuery("from NewPriceMaster where activestatus=1 and company_id=" + c_id
					+ " and status=1 and product_code='" + product_code + "'");
			newPriceMaster = query.list();
			return newPriceMaster;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public int verifyImportedData(int c_id, int location_id, String product_code,
			String product_name, String item_code, String pack) {
		Session session = initiateSession();
		try {
			String sql ="";
			Query query =null;
			int l_id,pack_id,p_code;
				sql = "select companyLocationId from CompanyLocations where company_id=" + c_id +" and companyLocationId="+location_id;
				query = session.createQuery(sql);
				l_id=(int) query.uniqueResult();
			if(l_id>0){
				sql = "select product_id from ProductDetails where company_id=" + c_id +" and name='"+product_name+"' and product_code='"+product_code+"'";
				query = session.createQuery(sql);
				p_code=(int) query.uniqueResult();
			}else{
				return 0;
			}
			if(p_code>0){
				sql = "select pricemaster_id from NewPriceMaster where company_id=" + c_id +" and displaypackname='"+pack+"' and activestatus=1 and itemcode='"+item_code+"' and product_code='"+product_code+"'";
				query = session.createQuery(sql);
				pack_id=(int) query.uniqueResult();
				if(pack_id>0)
					return 1;
				else
					return 0;
					
			}else{
				return 0;
			}
				
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(),e);
			return 0;
		} finally {
			destroySession(session);
		}
	}

	@Override
	public PrimaryStockDetails addNewPStock(PrimaryStockDetails pDetails, int c_id, int u_id, String username) {
		Session session = initiateSession();
		try {
			String main_query = "Delete FROM PrimaryStockDetails d" + " where d.company_id=" + c_id
					+ " and d.status<>0" + " and d.company_location_id=" + pDetails.getCompany_location_id()
					+ " and d.product_id=" + pDetails.getProduct_id() + " and d.pack='"
					+ pDetails.getPack() + "' and pricemaster_id="
					+ pDetails.getPricemaster_id();
			Query q = session.createQuery(main_query);
			q.executeUpdate();

			pDetails.setCreated_by(u_id);
			pDetails.setCreated_date(new Date());
			pDetails.setUpdated_by(u_id);
			pDetails.setUpdated_date(new Date());
			pDetails.setStatus(1);
			pDetails.setCompany_id(c_id);
			session.save(pDetails);

			PrimaryStockDetailsHistory details = new PrimaryStockDetailsHistory();
			details.setCompany_id(pDetails.getCompany_id());
			details.setCreated_by(pDetails.getCreated_by());
			details.setCreated_date(pDetails.getCreated_date());
			details.setCompany_location_id(pDetails.getCompany_location_id());
			details.setMinimum_stock(pDetails.getMinimum_stock());
			details.setMinimum_stock_updated_date(new Date());
			details.setPack(pDetails.getPack());
			details.setPricemaster_id(pDetails.getPricemaster_id());
			details.setProduct_id(pDetails.getProduct_id());
			details.setQuantity(pDetails.getQuantity());
			details.setStatus(pDetails.getStatus());
			details.setUpdated_by(pDetails.getUpdated_by());
			details.setUpdated_date(pDetails.getUpdated_date());
			
			session.save(details);

			return pDetails;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}
}
