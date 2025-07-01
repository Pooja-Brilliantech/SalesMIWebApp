package com.fieldmi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fieldmi.FieldMILogger;
import com.google.api.gax.paging.Page;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ImpersonatedCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.UserCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;

public class GoogleS3Operations extends S3Operations {

	private Storage storage = null;

	private String project_id = null;

	public GoogleS3Operations() {

		super();
		try {

			FieldMILogger.warn(this.getClass().getName(), "Connecting to Google Cloud Storage");

			connectToGoogle(null);

		} catch (Exception ex) {
			FieldMILogger.error(this.getClass().getName(), ex);
		}

	}

	public GoogleS3Operations(String projectID) {

		super();
		try {

			FieldMILogger.warn(this.getClass().getName(), "Connecting to Google Cloud Storage");

			connectToGoogle(projectID);

		} catch (Exception ex) {
			FieldMILogger.error(this.getClass().getName(), ex);
		}

	}

	private void connectToGoogle(String projectID) {

		try {

			if (projectID == null)
				projectID = CommonMethods.getFileProperties().getProperty("googleProjectID");


			project_id = projectID;
			FieldMILogger.warn(this.getClass().getName(), "Connecting to " + projectID);
			

			storage = StorageOptions.newBuilder().setProjectId(projectID).build().getService();

			if (storage == null) {
				FieldMILogger.warn(this.getClass().getName(), "Unable to connect to storage");
			} else {
				FieldMILogger.warn(this.getClass().getName(),
						"Service account email: " + storage.getServiceAccount(project_id).getEmail());
			}
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			ex.printStackTrace();
		}
	}

	@Override
	public String getSignedURL(String bucketName, String objectKey) {

		try {

			if( objectKey != null && objectKey.trim().length() > 0 ) {
			
				objectKey = objectKey.toLowerCase();
				bucketName = bucketName.toLowerCase();
				Date expiration = Calendar.getInstance().getTime();
				long expTimeMillis = expiration.getTime();
				expTimeMillis += 1000 * 60 * 60;
				expiration.setTime(expTimeMillis);
				BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectKey)).build();
				FieldMILogger.warn(this.getClass().getName(), "Fetching Signed Url for : " + objectKey);

				URL url =
				        storage.signUrl(blobInfo, 60, TimeUnit.MINUTES, Storage.SignUrlOption.withVirtualHostedStyle(), Storage.SignUrlOption.withV4Signature());

				FieldMILogger.info(this.getClass().getName(), "S3 Url: " + url.toString());
				
				return url.toString();
			} else {
				
				FieldMILogger.warn(this.getClass().getName(), "Object key sent is either empty or null.");
				return "";
			}
			
		} catch (StorageException e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return null;
		} catch (Exception ioe) {

			FieldMILogger.error(this.getClass().getName(), ioe);
			return null;
		}
	}

	@Override
	public boolean addFilesToS3(String bucketName, String objectKey, InputStream inputStream, long sizeOfContent) {

		try {

			if (inputStream != null) {

				objectKey = objectKey.toLowerCase();
				bucketName = bucketName.toLowerCase();
				BlobId blobId = BlobId.of(bucketName, objectKey);
				BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

				Storage.BlobWriteOption precondition;
				if (storage.get(bucketName, objectKey) == null) {

					precondition = Storage.BlobWriteOption.doesNotExist();
				} else {

					precondition = Storage.BlobWriteOption
							.generationMatch(storage.get(bucketName, objectKey).getGeneration());
				}
				storage.createFrom(blobInfo, inputStream, precondition);
				return true;
			}

			return false;
		} catch (StorageException e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		}
	}

	@Override
	public boolean deleteFilesFromS3(String bucketName, String objectKey) {

		try {

			objectKey = objectKey.toLowerCase();
			bucketName = bucketName.toLowerCase();
			Blob blob = storage.get(bucketName, objectKey);
			if (blob == null) {
				System.out.println("The object " + objectKey + " wasn't found in " + bucketName);
				return false;
			}
			Storage.BlobSourceOption precondition = Storage.BlobSourceOption.generationMatch(blob.getGeneration());

			storage.delete(bucketName, objectKey, precondition);
			return true;
		} catch (StorageException e) {

			FieldMILogger.error(this.getClass().getName(), e);
			return false;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			return false;
		}
	}

	@Override
	public File downloadFile(String bucketName, String fileName, String objectKey, String arogyanTemp) {

		try {

			objectKey = objectKey.toLowerCase();
			bucketName = bucketName.toLowerCase();
			File downloadedFile = new File(arogyanTemp, fileName);
			Blob blob = storage.get(bucketName, objectKey);
			if (blob == null)
				return null;

			blob.downloadTo(Paths.get(downloadedFile.getAbsolutePath()));
			return downloadedFile;
		} catch (StorageException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		}
		return null;
	}

	@Override
	public boolean checkFilesInS3(String bucketName, String objectKey) {

		try {
			objectKey = objectKey.toLowerCase();
			bucketName = bucketName.toLowerCase();
			Blob blob = storage.get(bucketName, objectKey);
			if (blob != null) {
				return true;
			}
		} catch (StorageException ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
		}

		return false;
	}

	@Override
	public byte[] downloadStream(String bucketName, String objectKey) {

		try {

			objectKey = objectKey.toLowerCase();
			bucketName = bucketName.toLowerCase();
			if (checkFilesInS3(bucketName.toLowerCase(), objectKey)) {

				byte[] content = storage.readAllBytes(bucketName, objectKey);
				if (content != null)
					return content;
			}
		} catch (StorageException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
		}
		return null;
	}

	@Override
	public List<String> listFileWithPrefix(String bucketName, String prefix, int limit) {

		try {

			FieldMILogger.warn(this.getClass().getName(),
					"Listing files from bucket: " + bucketName + " and prefix as " + prefix);
			List<String> fileNames = new ArrayList<String>();

			prefix = prefix.toLowerCase();
			bucketName = bucketName.toLowerCase();
			Page<Blob> blobs = storage.list(bucketName, Storage.BlobListOption.prefix(prefix));

			for (Blob blob : blobs.iterateAll()) {

				FieldMILogger.warn(this.getClass().getName(), "Files found: " + blob.getMediaLink());
				fileNames.add(blob.getName());

			}

			return fileNames;

		} catch (StorageException ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
		}
		return (new ArrayList<String>());
	}

	public static void main(String[] args) throws FileNotFoundException {

		try {

			GoogleS3Operations s3O = new GoogleS3Operations("fieldmi");
			String prefix = "10156" + S3Operations.CUSTOMER_FOLDER + "113275";
			List<String> files = s3O.listFileWithPrefix("jswfieldmi", prefix, 1);
			for (String file : files) {

				String signedUrl = s3O.getSignedURL("jswfieldmi", file);

				System.out.println("File name: " + signedUrl);

			}
			if (files.size() == 0) {

				File fileToUpload = new File("/Users/parag/Downloads/ActDBI_Full.png");
				FileInputStream fis = new FileInputStream(fileToUpload);
				String objectKey = prefix + "/" + fileToUpload.getName().toLowerCase();
				boolean isUploaded = s3O.addFilesToS3("jswfieldmi", objectKey, fis, fileToUpload.length());
				if (isUploaded) {

					String signedUrl = s3O.getSignedURL("jswfieldmi", objectKey);
					System.out.println("Signed Url:" + signedUrl);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
