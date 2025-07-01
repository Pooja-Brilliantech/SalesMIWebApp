package com.fieldmi.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.fieldmi.FieldMILogger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class GcmNotification {
	URL url;
	String deviceID;
	HttpURLConnection conn;
	String serverKey;
	HashMap<String, Object> message;
	JsonElement jsonmessage;
	HashMap<String, Object> notification = new HashMap<>();

	boolean success;

	public GcmNotification(String serverKey, String deviceID) throws Exception {
		url = new URL("https://fcm.googleapis.com/fcm/send");
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		this.serverKey = serverKey;
		conn.setRequestProperty("Authorization", "key=" + serverKey);
		message = new HashMap<>();

		this.deviceID = deviceID;

	}

	public void setMessage(String title, Object body) throws Exception {
		HashMap<String, Object> data = new HashMap<>();

		HashMap<String, Object> innerMessage = new HashMap<>();
		innerMessage.put("title", title);
		innerMessage.put("body", body);

		data.put("message", innerMessage);

		message.put("to", deviceID);
		message.put("data", data);
		message.put("content_available", true);
		jsonmessage = new Gson().toJsonTree(message);
	}

	public void setNotification(String title, Object body) throws Exception {
		notification = new HashMap<>();

		notification.put("title", title);
		notification.put("body", body);
		notification.put("sound", "default");
		message.put("notification", notification);

	}

	public boolean sendNotification() throws Exception {
		Thread t = new Thread() {
			@Override
			public void run() {
				String Resp = "Default";

				try {
					String input = jsonmessage.toString();

					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();

					if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {

					}

					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;

					while ((output = br.readLine()) != null) {
						Resp = Resp + "\n" + output;
					}

					conn.disconnect();

					if (Resp.contains("\"success\":1")) {
						success = true;
					}
				} catch (Exception e) {
					Resp = e.getMessage();
					FieldMILogger.error(this.getClass().getName(), e);
				}
			};
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			FieldMILogger.error(this.getClass().getName(), iex);
		}
		return success;
	}

}
