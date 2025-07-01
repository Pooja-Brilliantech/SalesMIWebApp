package com.fieldmi.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;

import com.fieldmi.FieldMILogger;
import com.fieldmi.service.FMCompanyService;
import com.fieldmi.service.FMEmailConfigurationService;
import com.fieldmi.service.FMUserService;
import com.fieldmi.utils.CommonMethods;
import com.softtantra.salesapp.pojo.EmailConfiguration;
import com.softtantra.salesapp.pojo.User;
import com.softtantra.salesapp.pojo.WorkFlowAuditLog;

public class FMEmailConfigurationServiceImpl implements FMEmailConfigurationService {

	@Autowired
	private FMUserService fmUserService;

	@Autowired
	private FMCompanyService fmCompanyService;

	@Override
	public void sendMailForPricingApproval(List<WorkFlowAuditLog> workTaskStatusForUpdate, int u_id, int c_id,
			int sales_master_id) {

		EmailConfiguration configuration = fmCompanyService.getCompanyEmailDetails(c_id);
		User u = fmUserService.getUserInfoForEdit(workTaskStatusForUpdate.get(0).getApprover_id(), c_id);
		String subject = "Pricing Approval Alert";
		String content = "Dear User,<br> " + "This is to Notify that Sales Order No:" + sales_master_id
				+ " is Approved for Pricing By: " + u.getFirst_name() + " " + u.getLast_name();
		if (configuration != null && !configuration.equals("")) {

			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < workTaskStatusForUpdate.size(); i++) {
				list.add(workTaskStatusForUpdate.get(i).getApprover_id());

			}
			List<String> emailIds = fmUserService.getEmails(list, c_id);

			String mailFrom = configuration.getFrom_email();
			String mailPassword = configuration.getFrom_password();
			String fromMailID = configuration.getFrom_email();

			final String from = mailFrom;
			final String password = mailPassword;

			CommonMethods cm = new CommonMethods();
			Properties props = new Properties();
			props = cm.configureEmailProperties(props, configuration);

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});
			try {
				for (String email : emailIds) {
					try {

						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(fromMailID));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
						// message.setRecipients(Message.RecipientType.CC,
						// InternetAddress.parse(configuration.getPricing_email()));
						message.setSubject(subject);
						// message.setContent(content, "teext/html; charset=utf-8");

						// Create the message part
						BodyPart messageBodyPart = new MimeBodyPart();

						// Now set the actual message
						messageBodyPart.setContent(content, "text/html");

						// Create a multipar message
						Multipart multipart = new MimeMultipart();

						// Set text message part
						multipart.addBodyPart(messageBodyPart);

						// Send the complete message parts
						message.setContent(multipart);

						// Send message
						Transport.send(message);

						FieldMILogger.debug(this.getClass().getName(), "Sent message successfully....");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						FieldMILogger.error(this.getClass().getName(), e);
					}
				}

			} catch (Exception e) {
				FieldMILogger.error(this.getClass().getName(), e);
			}
		}

	}

	@Override
	public void sendMailOfDistributorLogin(String scs, int c_id, String username) {

		EmailConfiguration configuration = fmCompanyService.getCompanyEmailDetails(c_id);

		if (configuration != null && !configuration.equals("")) {

			String mailFrom = configuration.getFrom_email();
			String mailPassword = configuration.getFrom_password();
			String fromMailID = configuration.getFrom_email();

			final String from = mailFrom;
			final String password = mailPassword;

			CommonMethods cm = new CommonMethods();
			Properties props = new Properties();
			props = cm.configureEmailProperties(props, configuration);

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});
			try {

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromMailID));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
					// message.setRecipients(Message.RecipientType.CC,
					// InternetAddress.parse(configuration.getPricing_email()));
					message.setSubject("Sales App Registration");
					// message.setContent(content, "teext/html; charset=utf-8");

					// Create the message part
					BodyPart messageBodyPart = new MimeBodyPart();

					// Now set the actual message
					messageBodyPart.setContent(scs, "text/html");

					// Create a multipar message
					Multipart multipart = new MimeMultipart();

					// Set text message part
					multipart.addBodyPart(messageBodyPart);

					// Send the complete message parts
					message.setContent(multipart);

					// Send message
					Transport.send(message);

					FieldMILogger.debug(this.getClass().getName(), "Sent message successfully....");

				} catch (Exception e) {

					FieldMILogger.error(this.getClass().getName(), e);
				}

			} catch (Exception e) {
				FieldMILogger.error(this.getClass().getName(), e);
			}
		}

	}
	
	@Override
	public void sendCompanyOnboardingEmail(String scs, int c_id, String username) {

		EmailConfiguration configuration = fmCompanyService.getCompanyEmailDetails(c_id);

		if (configuration != null) {

			String mailFrom = configuration.getFrom_email();
			String mailPassword = configuration.getFrom_password();
			String fromMailID = configuration.getFrom_email();


			CommonMethods cm = new CommonMethods();
			Properties props = new Properties();
			props = cm.configureEmailProperties(props, configuration);

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailFrom, mailPassword);
				}
			});
			try {

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromMailID));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
					// message.setRecipients(Message.RecipientType.CC,
					// InternetAddress.parse(configuration.getPricing_email()));
					message.setSubject("Welcome to SalesMI!");
					// message.setContent(content, "teext/html; charset=utf-8");

					// Create the message part
					BodyPart messageBodyPart = new MimeBodyPart();

					// Now set the actual message
					messageBodyPart.setContent(scs, "text/html");

					// Create a multipar message
					Multipart multipart = new MimeMultipart();

					// Set text message part
					multipart.addBodyPart(messageBodyPart);

					// Send the complete message parts
					message.setContent(multipart);

					Transport transport=session.getTransport("smtp");
					transport.connect(configuration.getHost_name(), mailFrom,mailPassword );
					// Send message
					Transport.send(message);

					transport.close();
					
					FieldMILogger.debug(this.getClass().getName(), "Sent message successfully....");

				} catch (Exception e) {

					FieldMILogger.error(this.getClass().getName(), e);
				}

			} catch (Exception e) {
				FieldMILogger.error(this.getClass().getName(), e);
			}
		}

	}

}
