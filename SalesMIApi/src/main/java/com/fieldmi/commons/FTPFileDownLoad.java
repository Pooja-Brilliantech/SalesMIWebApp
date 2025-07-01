package com.fieldmi.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.fieldmi.FieldMILogger;

public class FTPFileDownLoad {

	public static void main(String[] args) {

		// fileDownload.downloadFilesFromServer(server, port, userName, password);

		File sapInFolderPropertyMapF = new File("/Users/sheth/Documents/FieldMI/fieldmi_out/propertyMaps");
		if (sapInFolderPropertyMapF.exists()) {

			File[] propertyFiles = sapInFolderPropertyMapF.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {

					return name.endsWith(".properties");
				}
			});
			Arrays.sort(propertyFiles, NameFileComparator.NAME_COMPARATOR);
			for (File file : propertyFiles) {

				System.out.println("FileName:" + file.getName());
			}
		}

	}

	public boolean downloadFilesFromServer(String server, int port, String userName, String password,
			String destFolder) {

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(userName, password);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			FTPFile[] files1 = ftpClient.listFiles("/fieldmi_out");
			for (int i = 0; i < files1.length; i++) {

				if (files1[i].isFile()) {

					boolean success = false;
					String filename = files1[i].getName();
					String remoteFile = "/fieldmi_out/" + filename;
					String completedFile = "/fieldmi_out/completed/" + filename;
					if (filename.startsWith("CUSTOMER_MASTER")) {

						filename = "01_" + filename;

					} else if (filename.startsWith("CUSTOMER_ORGANIZATION")) {

						filename = "02_" + filename;
					} else if (filename.startsWith("CUSTOMER_PARTNER_ADDRESS")) {

						filename = "03_" + filename;
					}

					filename.replace("_SPR.", ".");
					File downloadFile1 = new File(destFolder, filename);
					OutputStream outputStream1 = new FileOutputStream(downloadFile1);
					success = ftpClient.retrieveFile(remoteFile, outputStream1);
					// System.out.println("successfully."+filename);
					if (success) {

						FieldMILogger.debug(FTPFileDownLoad.class.getName(), "File has been downloaded successfully.");
						boolean moved = ftpClient.rename(remoteFile, completedFile);
						if (moved)
							FieldMILogger.debug(FTPFileDownLoad.class.getName(), "File has been moved successfully.");
					}
					outputStream1.close();
				}

			}

			return true;
		} catch (IOException ex) {

			FieldMILogger.error(FTPFileDownLoad.class.getName(), ex);
			return false;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				FieldMILogger.error(FTPFileDownLoad.class.getName(), ex);
			}
		}
	}
}
