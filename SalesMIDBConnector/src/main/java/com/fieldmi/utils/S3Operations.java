package com.fieldmi.utils;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public abstract class S3Operations {

	public static final String CUSTOMER_FOLDER = "/customers/";

	public static final String PRODUCT_FOLDER = "/product/";

	public static final String USER_ATTENDANCE_FOLDER = "/attendanceselfie/";

	public static final String COMPANY_FOLDER = "/company/";

	public static final String VISIT_FOLDER = "/visit/";

	public static final String USER_PROFILE_FOLDER = "/users/profile_pics/";

	public static final String USER_OUTLET_FOLDER = "/users/outletbadges/";

	public static final String USER_INSURANCE_FOLDER = "/users/insurance/";

	public static final String USER_PASSPORT_FOLDER = "/users/passport/";

	public static final String USER_SIGNATURE_FOLDER = "/users/signatures/";

	public static final String USER_HEALTHCARD_FOLDER = "/users/health_card/";

	public static final String USER_ADHARCARD_FOLDER = "/users/adharcard/";

	public static final String USER_PANCARD_FOLDER = "/users/pancard/";

	public static final String SALES_SIGNATURE_FOLDER = "/salesorder/signature_pics/";

	public static final String SALES_POIMAGE_FOLDER = "/salesorder/poimages/";

	public static final String GEOTAGGING_FOLDER = "/geotagging/";

	public static final String EXPENSE_CLAIMS_FOLDER = "/expenseclaims/";

	public static final String FARMER_FOLDER = "/farmers/";

	public static final String TASK_IMAGE_FOLDER = "/taskimages/";

	public static final String TICKET_IMAGE_FOLDER = "/ticketimages/";

	public static final String SERVICE_FOLDER = "/serviceimg/";

	public static final String IITIMG_FOLDER = "/IITImg/";

	public static final String SERVICE_RESCHEDULED_PREFIX = "/serviceimg/rescheduled_";

	public static final String SERVICE_CUSTOMER_FOLDER = "/service_customers/";

	public static final String IITIMG_VEHIVLE_REQ_PREFIX = "/iitimg/vehiclerequest_";

	public static final String IITIMG_BRICK_REQ_PREFIX = "/iitimg/bricksrequest_";

	public static final String IITIMG_AGRI_REQ_PREFIX = "/iitimg/agri_";

	public static final String IITIMG_RESI_REQ_PREFIX = "/iitimg/resirequest_";

	public static final String LEAD_IMAGES_FOLDER = "/leadimages/";

	public static final String PRIMARY_STOCK_IMAGES = "/primarystock/";

	public static final String SECONDARY_STOCK_IMAGES = "/secondarystock/";

	public static final String OPPORTUNITY_DOCUMENTS_FOLDER = "/opportunitydocuments/";

	public static final String TASK_FILES_FOLDER = "/taskdocuments/";

	public static final String DOCUMENTS = "/documents/";

	public abstract String getSignedURL(String bucketName, String objectKey);

	public abstract boolean addFilesToS3(String bucketName, String objectKey, InputStream inputStream, long sizeOfContent);

	public abstract boolean deleteFilesFromS3(String bucketName, String objectKey);

	public abstract File downloadFile(String bucketName, String fileName, String objectKey, String arogyanTemp);

	public abstract boolean checkFilesInS3(String bucketName, String objectKey);

	public abstract byte[] downloadStream(String bucketName, String objectKey);

	public abstract List<String> listFileWithPrefix(String bucketName, String prefix, int limit);
	
}
