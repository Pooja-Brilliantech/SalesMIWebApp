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
import com.fieldmi.dao.FMDistributorStockDao;
import com.fieldmi.stubs.ReturnDate;
import com.fieldmi.stubs.WsDStockDetailsList;
import com.fieldmi.stubs.WsDistributorLocationList;
import com.fieldmi.stubs.WsDistributorLocations;
import com.fieldmi.stubs.WsExpenseDetails;
import com.fieldmi.utils.CommonMethods;
import com.fieldmi.utils.S3Operations;
import com.softtantra.salesapp.pojo.DistributorDetails;
import com.softtantra.salesapp.pojo.DistributorOfficeLocations;
import com.softtantra.salesapp.pojo.DistributorStockDetails;
import com.softtantra.salesapp.pojo.DistributorStockDetailsHistory;
import com.softtantra.salesapp.pojo.SecondaryStockGRN;
import com.softtantra.servicemi.pojo.ActivityDetails;
import com.softtantra.servicemi.pojo.OpportunityDetails;
import com.softtantra.ws.WSActivityList;
import com.softtantra.ws.WSDistributorRouteDetails;
import com.softtantra.ws.WsExpenseMaster;

public class FMDistributorStockDaoImpl implements FMDistributorStockDao {

	private static final int no_of_records_per_page = 200;
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	CommonMethods commonMethods;

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
	public boolean addSecondaryStockGRN(SecondaryStockGRN secondaryStockGRN, String data) {
		Session session = initiateSession();
		try {
			String[] RowSplit = data.split("<###>");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String objectKey = "";
			int id = 0;
			for (int i = 0; i < RowSplit.length; i++) {
				String str = RowSplit[i];
				String[] columnSaparator = str.split("<<<<>>>>");
				if (columnSaparator.length > 1) {
					secondaryStockGRN.setProduct_id(Integer.parseInt(columnSaparator[0]));
					secondaryStockGRN.setProduct_code(columnSaparator[1]);
					secondaryStockGRN.setProduct_name(columnSaparator[2]);
					secondaryStockGRN.setQuantity_received(Double.parseDouble(columnSaparator[3]));
					if (!columnSaparator[4].equals(""))
						secondaryStockGRN.setExpiry_date(sdf.parse(columnSaparator[4]));
					else
						secondaryStockGRN.setExpiry_date(null);
					secondaryStockGRN.setPricemasterid(Integer.parseInt(columnSaparator[5]));
					String pack = columnSaparator[6];
					secondaryStockGRN.setStatus(1);

					Query query = session.createQuery(
							"from DistributorStockDetails where product_id='" + secondaryStockGRN.getProduct_id()
									+ "' and pricemaster_id=" + secondaryStockGRN.getPricemasterid() + " and pack='"
									+ pack + "' and status=1 and company_id=" + secondaryStockGRN.getCompany_id()
									+ "  and distributorLocationId=" + secondaryStockGRN.getDistributor_location_id()
									+ " and distributor_id=" + secondaryStockGRN.getDistributor_id());
					DistributorStockDetails distributorStockDetails = (DistributorStockDetails) query.uniqueResult();

					if (distributorStockDetails != null) {
						double quantity = 0;
						quantity = distributorStockDetails.getQuantity();
						quantity = quantity + Double.parseDouble(columnSaparator[3]);

						Query query1 = session.createQuery(
								"update DistributorStockDetails set quantity=:quantity,updated_date=:updated_date where stock_id=:stock_id");
						query1.setParameter("stock_id", distributorStockDetails.getStock_id());
						query1.setParameter("updated_date", new Date());
						query1.setParameter("quantity", quantity);

						int num = query1.executeUpdate();
						if (num > 0)
							id = (int) session.save(secondaryStockGRN);
						if (secondaryStockGRN.getImage().length > 0) {

							String bucketName = CommonMethods.getFileProperties().getProperty("bucketName");
							objectKey = secondaryStockGRN.getCompany_id() + S3Operations.SECONDARY_STOCK_IMAGES + id
									+ ".png";
							S3Operations s3Operatons = CommonMethods.getS3OperationClient();
							s3Operatons.addFilesToS3(bucketName, objectKey,
									new ByteArrayInputStream(secondaryStockGRN.getImage()),
									secondaryStockGRN.getImage().length);
						}

					} else {
						DistributorStockDetails distributorstockdetails = new DistributorStockDetails();
						distributorstockdetails.setCompany_id(secondaryStockGRN.getCompany_id());
						distributorstockdetails
								.setDistributorLocationId(secondaryStockGRN.getDistributor_location_id());
						distributorstockdetails.setProduct_name(secondaryStockGRN.getProduct_name());
						distributorstockdetails.setQuantity(Double.parseDouble(columnSaparator[3]));
						distributorstockdetails.setUpdated_date(new Date());
						distributorstockdetails.setCreated_date(new Date());
						distributorstockdetails.setCreated_by(secondaryStockGRN.getCreated_by());
						distributorstockdetails.setUpdated_by(secondaryStockGRN.getUpdated_by());
						distributorstockdetails.setProduct_id(Integer.parseInt(columnSaparator[0]));
						distributorstockdetails.setPricemaster_id(secondaryStockGRN.getPricemasterid());
						distributorstockdetails.setPack(pack);
						distributorstockdetails.setMinimum_stock_updated_date(new Date());
						distributorstockdetails.setStatus(1);
						distributorstockdetails.setDistributor_id(secondaryStockGRN.getDistributor_id());

						int num = (int) session.save(distributorstockdetails);
						if (num > 0)
							id = (int) session.save(secondaryStockGRN);
						if (secondaryStockGRN.getImage().length > 0) {

							String bucketName = CommonMethods.getFileProperties().getProperty("bucketName");
							objectKey = secondaryStockGRN.getCompany_id() + S3Operations.PRIMARY_STOCK_IMAGES + id
									+ ".png";
							S3Operations s3Operatons = CommonMethods.getS3OperationClient();
							s3Operatons.addFilesToS3(bucketName, objectKey,
									new ByteArrayInputStream(secondaryStockGRN.getImage()),
									secondaryStockGRN.getImage().length);
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
	public DistributorStockDetails addNewDStock(DistributorStockDetails distributorStockDetails, int c_id, int u_id,
			String username) {
		Session session = initiateSession();
		try {
			String main_query = "Delete FROM DistributorStockDetails d" + " where d.company_id=" + c_id
					+ " and d.status<>0" + " and d.distributor_id=" + distributorStockDetails.getDistributor_id()
					+ " and d.product_id=" + distributorStockDetails.getProduct_id() + " and d.pack='"
					+ distributorStockDetails.getPack() + "' and pricemaster_id="
					+ distributorStockDetails.getPricemaster_id();
			Query q = session.createQuery(main_query);
			q.executeUpdate();

			distributorStockDetails.setCreated_by(u_id);
			distributorStockDetails.setCreated_date(new Date());
			distributorStockDetails.setUpdated_by(u_id);
			distributorStockDetails.setUpdated_date(new Date());
			distributorStockDetails.setStatus(1);
			distributorStockDetails.setCompany_id(c_id);
			session.save(distributorStockDetails);

			DistributorStockDetailsHistory details = new DistributorStockDetailsHistory();
			details.setCompany_id(distributorStockDetails.getCompany_id());
			details.setStock_id(distributorStockDetails.getStock_id());
			details.setCreated_by(distributorStockDetails.getCreated_by());
			details.setCreated_date(distributorStockDetails.getCreated_date());
			details.setDistributor_id(distributorStockDetails.getDistributor_id());
			details.setMinimum_stock(distributorStockDetails.getMinimum_stock());
			details.setMinimum_stock_updated_date(new Date());
			details.setPack(distributorStockDetails.getPack());
			details.setPackunit(distributorStockDetails.getPackunit());
			details.setPricemaster_id(distributorStockDetails.getPricemaster_id());
			details.setProduct_id(distributorStockDetails.getProduct_id());
			details.setQuantity(distributorStockDetails.getQuantity());
			details.setStatus(distributorStockDetails.getStatus());
			details.setUpdated_by(distributorStockDetails.getUpdated_by());
			details.setUpdated_date(distributorStockDetails.getUpdated_date());
			details.setOpening_stock(0);
			details.setActual_stock_updated_type(1);
			details.setClosing_stock(distributorStockDetails.getQuantity());
			details.setDiff_stock(distributorStockDetails.getQuantity());
			details.setDistributorLocationId(distributorStockDetails.getDistributorLocationId());
			details.setItem_code(distributorStockDetails.getItem_code());
			details.setDescription_stock_update(
					distributorStockDetails.getQuantity() + " added by " + username + " using import");

			session.save(details);

			return distributorStockDetails;

		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} finally {
			destroySession(session);
		}
	}
	@Override
	public WsDistributorLocationList getDistributorLocationList(WsDistributorLocationList wsDistributorLocationList) {
		Session session = initiateSession();
		WsDistributorLocationList wsdistributorLocations = new WsDistributorLocationList();
		List<WsDistributorLocations> locations1 = new ArrayList<WsDistributorLocations>();
		int total_records = 0;
		int totalrecords = 0;
		try {
			String lastDate = "";
			String outputDate = ReturnDate.returnLastUpdateddate();
			if(wsDistributorLocationList.getLast_date() !="")
				lastDate = " and Date(updated_date) > '" + wsDistributorLocationList.getLast_date() + "'";
		  
			Query query = session.createQuery(
					"select distributor_id, user_id from UserHasDistributor where company_id=" + wsDistributorLocationList.getCompany_id()
							+ " and user_id=" + wsDistributorLocationList.getUser_id());
			List list = query.list();
			for (Object object : list) {
				Object[] obj1 = (Object[]) object;
				
				Query query1 = session.createQuery(
						"select distributorLocationId,distributorLocationName,distributorLocationCode,lat,longi,distributorLocationAddress,distributorOfficeLocation,distributor_name from DistributorOfficeLocations where status=1 and company_id=" + wsDistributorLocationList.getCompany_id()
								+ " and distributor_id=" + (int) obj1[0]+lastDate);
				
				total_records = commonMethods.getTotalPagesCountHql(session,
						"select count(distributorLocationId) from DistributorOfficeLocations  where status=1 and company_id=" + wsDistributorLocationList.getCompany_id()
						+ " and distributor_id=" + (int) obj1[0]+lastDate);
				
				totalrecords=totalrecords+total_records;
				List list1 = query1.list();
				for (Object object1 : list1) {

					Object[] obj = (Object[]) object1;
					WsDistributorLocations locations = new WsDistributorLocations();

					locations.setDistributorLocationId((int) obj[0]);
					locations.setDistributorLocationName((String) obj[1]);
					locations.setDistributorLocationCode((String) obj[2]);
					locations.setLat((String) obj[3]);
					locations.setLongi((String) obj[4]);
					locations.setDistributorLocationAddress((String) obj[5]);
					locations.setDistributorOfficeLocation((String) obj[6]);
					locations.setDistributor_name((String) obj[7]);
					locations.setDistributor_id((int) obj1[0]);
					locations1.add(locations);
				}
					
			}	
			
			wsdistributorLocations.setDistributorLocations(locations1);
			wsdistributorLocations.setCompany_id(wsDistributorLocationList.getCompany_id());
			wsdistributorLocations.setUser_id(wsDistributorLocationList.getUser_id());
			wsdistributorLocations.setRecordsPerPagesNew(no_of_records_per_page);
			wsdistributorLocations.setPage_no(1);
			wsdistributorLocations.setTotal_records(totalrecords);
			wsdistributorLocations.setLastUpdatedDate(outputDate);
			wsdistributorLocations.setTotal_Pages(totalrecords);
			return wsdistributorLocations;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);

		} finally {
			destroySession(session);
		}
		return wsdistributorLocations;
	}

	@Override
	public WsDStockDetailsList getDistributorStockDetails(WsDistributorLocationList wsDistributorLocationList) {
		Session session = initiateSession();
		WsDStockDetailsList details = new WsDStockDetailsList();
		List list = null;
		String query = "";
		int total_records = 0;
		try {
			String outputDate = ReturnDate.returnLastUpdateddate();
			
			query = "select ds.product_id,ds.quantity,ds.pack,ds.packunit,ds.pricemaster_id,ds.minimum_stock,ds.Item_code,ds.distributor_id,d.distributor_name"
					+ ",p.name,p.image_link,ds.distributorLocationId from DistributorStockDetails ds,ProductDetails p,DistributorDetails d  where ds.product_id=p.product_id and"
					+ " ds.distributor_id=d.distributor_id and ds.status=1 and ds.company_id=" + wsDistributorLocationList.getCompany_id()+ " and ds.distributor_id="
					+ wsDistributorLocationList.getDistributor_id() + " and ds.distributorLocationId =" + wsDistributorLocationList.getLocation_id();
			total_records = commonMethods.getTotalPagesCountHql(session,
					"select count(ds.stock_id) from DistributorStockDetails ds,ProductDetails p,DistributorDetails d  where ds.product_id=p.product_id and "
					+ "ds.distributor_id=d.distributor_id and ds.status=1 and ds.company_id=" + wsDistributorLocationList.getCompany_id()
					+ " and ds.distributor_id="+ wsDistributorLocationList.getDistributor_id() + " and ds.distributorLocationId="
					+ wsDistributorLocationList.getLocation_id());
							
			list = commonMethods.quaryListPagination(session, query, 1,no_of_records_per_page);
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				DistributorStockDetails distributorStockDetails = new DistributorStockDetails();
				
				distributorStockDetails.setProduct_id((int) obj[0]);
				distributorStockDetails.setQuantity((double) obj[1]);
				distributorStockDetails.setPack((String) obj[2]);
				distributorStockDetails.setPackunit((String) obj[3]);
				distributorStockDetails.setPricemaster_id((int) obj[4]);
				distributorStockDetails.setMinimum_stock((double) obj[5]);
				distributorStockDetails.setItem_code((String) obj[6]);
				distributorStockDetails.setDistributor_id((int) obj[7]);
				distributorStockDetails.setDistributor_name((String) obj[8]);
				distributorStockDetails.setProduct_name((String) obj[9]);
				distributorStockDetails.setImage_link((String) obj[10]);
				distributorStockDetails.setDistributorLocationId((int) obj[11]);

				details.getDistributorStockDetails().add(distributorStockDetails);
			}
			details.setTotal_records(total_records);
			details.setRecordsPerPagesNew(no_of_records_per_page);
			details.setPage_no(1);
			details.setCompany_id(wsDistributorLocationList.getCompany_id());
			details.setLastUpdatedDate(outputDate);
			details.setTotal_Pages(total_records);

			return details;
		} catch (Exception e) {
			FieldMILogger.error(this.getClass().getName(), e);
		} finally {
			destroySession(session);
		}
		return details;
	}

}
