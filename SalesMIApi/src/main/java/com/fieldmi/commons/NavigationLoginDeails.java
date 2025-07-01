package com.fieldmi.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;


import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
/*

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;*/


import com.fieldmi.FieldMILogger;

public class NavigationLoginDeails {

	public String GetSoapDetails(String url) throws IOException, Exception {

		try {

			String requestUrl = url;
			String userName = "abs3";
			String password = "KciMx9102";
			String domain = "KCMAPPLIANCES";
			String responseText = getAuthenticatedResponse(requestUrl, domain, userName, password);

			// FieldMILogger.debug(this.getClass().getName(),"response: " + responseText);
			return responseText;

		} catch (Exception e) {
			FieldMILogger.debug(this.getClass().getName(),"e = " + e);
			return null;
		}

	}

	private static String getAuthenticatedResponse(final String urlStr, final String domain, final String userName,
			final String password) throws IOException {

		StringBuilder response = new StringBuilder();

		Authenticator.setDefault(new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(domain + "\\" + userName, password.toCharArray());
			}
		});

		URL urlRequest = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Basic", "secure@puneit");
		conn.setRequestProperty("Content-Type", "text/xml");
		conn.setRequestProperty("Accept", "application/atom+xml");

		InputStream stream = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String str = "";
		while ((str = in.readLine()) != null) {
			response.append(str);
		}
		in.close();

		return response.toString();
	}

	public static String PostAuthenticatedResponse(final String urlStr, final String soapAction, final String envelope1,
			final String domain, final String userName, final String password) throws IOException {

		StringBuilder response = new StringBuilder();

		FieldMILogger.debug("NavigationLoginDeails","INNNm  ");
		Authenticator.setDefault(new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(domain + "\\" + userName, password.toCharArray());
			}
		});

		try {
			URL urlRequest = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.setRequestProperty("Basic", "secure@puneit");
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			// conn.setRequestProperty("Authorization", "NTLM Authentication[Beta]");
			conn.setRequestProperty("Accept", "text/xml");
			conn.setRequestProperty("SOAPAction", soapAction);
			conn.setRequestMethod("POST");
			final OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.append(envelope1);
			writer.close();

			InputStream stream = conn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			String str = "";
			while ((str = in.readLine()) != null) {
				response.append(str);
			}
			in.close();

			return response.toString();
		} catch (Exception e) {
			
			FieldMILogger.error("NavigationLoginDetails",e);
			return "";
		}
	}

	public String invoke() {

		StringBuilder bodyAsString = new StringBuilder();
		bodyAsString.append(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"urn:microsoft-dynamics-schemas/page/servicelines_crm\">");
		bodyAsString.append("<soapenv:Header/>");

		bodyAsString.append("<soapenv:Body>");
		bodyAsString.append("<ser:Create>");

		bodyAsString.append("<ser:SelectionFilter>0</ser:SelectionFilter>");

		bodyAsString.append("<ser:ServiceLines_CRM>");
		bodyAsString.append("<ser:Key></ser:Key>");

		bodyAsString.append("<ser:Service_Item_Line_No>10000</ser:Service_Item_Line_No>");
		bodyAsString.append("<ser:Service_Item_No></ser:Service_Item_No>");
		bodyAsString.append("<ser:Service_Item_Serial_No></ser:Service_Item_Serial_No>");
		bodyAsString.append("<ser:Service_Item_Line_Description></ser:Service_Item_Line_Description>");
		bodyAsString.append("<ser:Discount_Code>20</ser:Discount_Code>");
		bodyAsString.append("<ser:Type>Item</ser:Type>");
		bodyAsString.append("<ser:No>RM000002</ser:No>");
		// bodyAsString.append("<ser:Service_Tax_Group></ser:Service_Tax_Group>");
		//
		// bodyAsString.append("<ser:Description></ser:Description>");
		// bodyAsString.append("<ser:Description_2></ser:Description_2>");
		//
		// bodyAsString.append("<ser:Location_Code></ser:Location_Code>");
		bodyAsString.append("<ser:Bin_Code></ser:Bin_Code>");
		bodyAsString.append("<ser:Quantity>1</ser:Quantity>");
		// bodyAsString.append("<ser:Unit_of_Measure_Code></ser:Unit_of_Measure_Code>");
		// bodyAsString.append("<ser:Unit_Cost_LCY>10.50</ser:Unit_Cost_LCY>");
		// bodyAsString.append("<ser:Unit_Price>10.50</ser:Unit_Price>");
		// bodyAsString.append("<ser:Line_Amount>50</ser:Line_Amount>");
		bodyAsString.append("<ser:Line_Discount_Percent>0</ser:Line_Discount_Percent>");
		// bodyAsString.append("<ser:Line_Discount_Amount>0</ser:Line_Discount_Amount>");
		// bodyAsString.append("<ser:Assessable_Value>0</ser:Assessable_Value>");
		//
		// bodyAsString.append("<ser:Qty_to_Ship>1</ser:Qty_to_Ship>");
		// bodyAsString.append("<ser:Quantity_Shipped>0</ser:Quantity_Shipped>");
		// bodyAsString.append("<ser:Qty_to_Invoice>1</ser:Qty_to_Invoice>");
		// bodyAsString.append("<ser:Quantity_Invoiced>1</ser:Quantity_Invoiced>");
		//
		// bodyAsString.append("<ser:Qty_to_Consume>0</ser:Qty_to_Consume>");
		// bodyAsString.append("<ser:Quantity_Consumed>0</ser:Quantity_Consumed>");
		// bodyAsString.append("<ser:Work_Type_Code></ser:Work_Type_Code>");
		// bodyAsString.append("<ser:Fault_Reason_Code></ser:Fault_Reason_Code>");
		// bodyAsString.append("<ser:Fault_Area_Code></ser:Fault_Area_Code>");
		// bodyAsString.append("<ser:Symptom_Code></ser:Symptom_Code>");
		// bodyAsString.append("<ser:Fault_Code></ser:Fault_Code>");
		// bodyAsString.append("<ser:Resolution_Code></ser:Resolution_Code>");
		// bodyAsString.append("<ser:Exclude_Warranty>Yes</ser:Exclude_Warranty>");
		// bodyAsString.append("<ser:Warranty>Yes</ser:Warranty>");

		// bodyAsString.append("<ser:Serv_Price_Adjmt_Gr_Code></ser:Serv_Price_Adjmt_Gr_Code>");
		// bodyAsString.append("<ser:Allow_Invoice_Disc>0</ser:Allow_Invoice_Disc>");
		// bodyAsString.append("<ser:Planned_Delivery_Date></ser:Planned_Delivery_Date>");
		// bodyAsString.append("<ser:Needed_by_Date></ser:Needed_by_Date>");
		// bodyAsString.append("<ser:Posting_Date></ser:Posting_Date>");
		// bodyAsString.append("<ser:GST_Place_Of_Supply></ser:GST_Place_Of_Supply>");
		// bodyAsString.append("<ser:GST_Group_Code></ser:GST_Group_Code>");
		// bodyAsString.append("<ser:GST_Group_Type></ser:GST_Group_Type>");
		// bodyAsString.append("<ser:GST_Base_Amount></ser:GST_Base_Amount>");
		// bodyAsString.append("<ser:Total_GST_Amount></ser:Total_GST_Amount>");
		// bodyAsString.append("<ser:HSN_SAC_Code></ser:HSN_SAC_Code>");
		// bodyAsString.append("<ser:GST_Jurisdiction_Type></ser:GST_Jurisdiction_Type>");
		// bodyAsString.append("<ser:Invoice_Type></ser:Invoice_Type>");
		// bodyAsString.append("<ser:Exempted></ser:Exempted>");
		// bodyAsString.append("<ser:Shortcut_Dimension_1_Code></ser:Shortcut_Dimension_1_Code>");
		// bodyAsString.append("<ser:Shortcut_Dimension_2_Code></ser:Shortcut_Dimension_2_Code>");
		// bodyAsString.append("<ser:ShortcutDimCode_x005B_3_x005D_></ser:ShortcutDimCode_x005B_3_x005D_>");
		// bodyAsString.append("<ser:ShortcutDimCode_x005B_4_x005D_></ser:ShortcutDimCode_x005B_4_x005D_>");
		// bodyAsString.append("<ser:ShortcutDimCode_x005B_5_x005D_></ser:ShortcutDimCode_x005B_5_x005D_>");
		// bodyAsString.append("<ser:ShortcutDimCode_x005B_6_x005D_></ser:ShortcutDimCode_x005B_6_x005D_>");
		// bodyAsString.append("<ser:ShortcutDimCode_x005B_7_x005D_></ser:ShortcutDimCode_x005B_7_x005D_>");
		// bodyAsString.append("<ser:ShortcutDimCode_x005B_8_x005D_></ser:ShortcutDimCode_x005B_8_x005D_>");
		bodyAsString.append("<ser:Document_Type>Order</ser:Document_Type>");
		//
		bodyAsString.append("<ser:Document_No>SV-00026</ser:Document_No>");
		bodyAsString.append("<ser:Line_No>22000</ser:Line_No>");

		bodyAsString.append("</ser:ServiceLines_CRM>");
		bodyAsString.append("</ser:Create>");
		bodyAsString.append("</soapenv:Body>");
		bodyAsString.append("</soapenv:Envelope>");

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY,
				new NTCredentials("abs3", "KciMx9102", "52.172.47.254", "KCMAPPLIANCES"));

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credsProvider).build();

		HttpPost post = new HttpPost("http://52.172.47.254:8048/KCM/WS/Service/Page/ServiceLines_CRM"); // Provide

		try {

			StringEntity input = new StringEntity(bodyAsString.toString());
			input.setContentType("application/xml; charset=utf-8");
			post.setEntity(input);

			post.setHeader("Content-type", "application/xml; charset=utf-8");
			post.setHeader("SOAPAction", "urn:microsoft-dynamics-schemas/page/servicelines_crm:Create"); // Provide Soap
																											// action
			// post.setHeader("Accept", "text/xml");
			org.apache.http.HttpResponse response = client.execute(post);

			HttpEntity responseEntity = response.getEntity();

			if (responseEntity != null) {
				return EntityUtils.toString(responseEntity);
			}

		} catch (IOException ex) {
			FieldMILogger.error(this.getClass().getName(),ex);
		}

		return null;
	}
}
