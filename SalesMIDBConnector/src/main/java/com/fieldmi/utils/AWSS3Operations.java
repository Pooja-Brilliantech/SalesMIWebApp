package com.fieldmi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.fieldmi.FieldMILogger;

public class AWSS3Operations extends S3Operations {

	private AmazonS3 mS3Client = null;

	public AWSS3Operations() {

		super();
		try {

			FieldMILogger.warn(this.getClass().getName(), "Connecting to AWS S3");
			connectToAWS();

		} catch (Exception ex) {
			FieldMILogger.error(this.getClass().getName(), ex);
		}

	}

	private void connectToAWS() {

		String s3Endpoint = CommonMethods.getFileProperties().getProperty("s3Url");
		String bucketName = CommonMethods.getFileProperties().getProperty("bucketName");
		String regionName = CommonMethods.getFileProperties().getProperty("s3region");
		String sessionName = CommonMethods.getFileProperties().getProperty("sessionName");
		String roleARN = "arn:aws:iam::980214949273:role/aws-elasticbeanstalk-ec2-role";

		if (sessionName.equalsIgnoreCase("salesmi-webapp")) {

			mS3Client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
					.withChunkedEncodingDisabled(true).withRegion(regionName).build();
		} else {

			AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard().withRegion(regionName)
					.build();

			AssumeRoleRequest roleRequest = new AssumeRoleRequest().withRoleArn(roleARN)
					.withRoleSessionName(sessionName);
			AssumeRoleResult roleResponse = stsClient.assumeRole(roleRequest);
			Credentials sessionCredentials = roleResponse.getCredentials();
			BasicSessionCredentials awsCredentials = new BasicSessionCredentials(sessionCredentials.getAccessKeyId(),
					sessionCredentials.getSecretAccessKey(), sessionCredentials.getSessionToken());

			EndpointConfiguration endpointConfiguration = new EndpointConfiguration(s3Endpoint, regionName);
			mS3Client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
					.withChunkedEncodingDisabled(true).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					.withEndpointConfiguration(endpointConfiguration).build();

			// check if bucket exists
			List<Bucket> buckets = mS3Client.listBuckets();
			boolean bucketExists = false;
			for (Bucket bucket : buckets) {

				if (bucket.getName().equalsIgnoreCase(bucketName)) {
					bucketExists = true;
					break;
				}

			}

			if (!bucketExists) {

				try {
					mS3Client.createBucket(bucketName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public String getSignedURL(String bucketName, String objectKey) {

		objectKey = objectKey.toLowerCase();
		bucketName = bucketName.toLowerCase();
		Date expiration = Calendar.getInstance().getTime();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		GeneratePresignedUrlRequest generatePresignedUrlReq = new GeneratePresignedUrlRequest(bucketName, objectKey)
				.withMethod(HttpMethod.GET).withExpiration(expiration);
		URL url = mS3Client.generatePresignedUrl(generatePresignedUrlReq);

		FieldMILogger.info(this.getClass().getName(), "S3 Url: " + url.toString());
		return url.toString();
	}

	/**
	 * Uploads the photo file to S3 Bucket.
	 * 
	 * @param filePath - The file to upload to S3
	 * @return boolean - The result after uploading to S3 Bucket and creating Photo
	 *         object entry.
	 */
	@Override
	public boolean addFilesToS3(String bucketName, String objectKey, InputStream inputStream, long sizeOfContent) {

		try {

			if (inputStream != null) {

				objectKey = objectKey.toLowerCase();
				bucketName = bucketName.toLowerCase();
				ObjectMetadata meta = new ObjectMetadata();
				// explicitly set the content length here
				meta.setContentLength(sizeOfContent);
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName.toLowerCase(), objectKey,
						inputStream, meta);
				PutObjectResult uploadResult = mS3Client.putObject(putObjectRequest);
				if (uploadResult != null) {

					return true;
				}

			}

		} catch (Exception e) {

			FieldMILogger.error(this.getClass().getName(), e);
		}

		return false;
	}

	@Override
	public boolean deleteFilesFromS3(String bucketName, String objectKey) {

		try {

			objectKey = objectKey.toLowerCase();
			bucketName = bucketName.toLowerCase();
			mS3Client.deleteObject(bucketName, objectKey);
			return true;
		} catch (Exception ex) {

			FieldMILogger.error(this.getClass().getName(), ex);
			return false;
		}
	}

	@Override
	public File downloadFile(String bucketName, String fileName, String objectKey, String arogyanTemp) {

		try {

			objectKey = objectKey.toLowerCase();
			File downloadedFile = new File(arogyanTemp, fileName);
			S3Object s3Object = mS3Client.getObject(bucketName.toLowerCase(), objectKey);
			if (s3Object == null)
				return null;

			S3ObjectInputStream s3is = s3Object.getObjectContent();
			FileOutputStream fos = new FileOutputStream(downloadedFile);
			byte[] read_buf = new byte[1024];
			int read_len = 0;
			while ((read_len = s3is.read(read_buf)) > 0) {
				fos.write(read_buf, 0, read_len);
			}
			s3is.close();
			fos.close();
			return downloadedFile;
		} catch (AmazonServiceException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} catch (FileNotFoundException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} catch (IOException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		}
		return null;
	}

	@Override
	public boolean checkFilesInS3(String bucketName, String objectKey) {

		objectKey = objectKey.toLowerCase();
		bucketName = bucketName.toLowerCase();
		return mS3Client.doesObjectExist(bucketName, objectKey);
	}

	@Override
	public byte[] downloadStream(String bucketName, String objectKey) {

		try {

			objectKey = objectKey.toLowerCase();
			bucketName = bucketName.toLowerCase();
			if (checkFilesInS3(bucketName.toLowerCase(), objectKey)) {

				S3Object s3Object = mS3Client.getObject(bucketName, objectKey);
				if (s3Object != null) {

					return IOUtils.toByteArray(s3Object.getObjectContent());
				}
			}
		} catch (AmazonServiceException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} catch (FileNotFoundException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		} catch (IOException e) {

			FieldMILogger.error(this.getClass().getName(), e);
		}
		return null;
	}

	@Override
	public List<String> listFileWithPrefix(String bucketName, String prefix, int limit) {

		List<String> fileNames = new ArrayList<String>();

		prefix = prefix.toLowerCase();
		bucketName = bucketName.toLowerCase();
		ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(limit)
				.withPrefix(prefix);

		ListObjectsV2Result result = null;
		try {
			do {
				result = mS3Client.listObjectsV2(request);

				for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
					fileNames.add(objectSummary.getKey());
				}
				String token = result.getNextContinuationToken();
				request.setContinuationToken(token);
			} while (result.isTruncated());
		} catch (Exception e) {
			return null;
		}
		return fileNames;
	}

	public static void main(String[] args) throws FileNotFoundException {

		try {

			S3Operations s3O = new AWSS3Operations();
			String prefix = 10156 + S3Operations.CUSTOMER_FOLDER + 113275;
			List<String> files = s3O.listFileWithPrefix("testfieldmi", prefix, 1);
			for (String file : files) {

				System.out.println("File name: " + file);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
