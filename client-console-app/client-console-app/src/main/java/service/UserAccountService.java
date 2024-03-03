package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import account.UserAccount;

public class UserAccountService {
	private static final String API_URL = "https://localhost:8080/accounts/";
	private static final String slash = "/";
	private static UserAccountService instance;

	private UserAccountService() {}

	public static UserAccountService getInstance() {
		if (instance == null) {
			instance = new UserAccountService();
		}
		return instance;
	}

	public UserAccount login(String cardNumber, String PIN) throws IOException {
		String apiUrl = API_URL + "login" + slash + cardNumber + slash + PIN;
		String jsonResponse = sendGetRequest(apiUrl);
		return buildAccountFromResponse(jsonResponse);
	}

	public double getBalance(String cardNumber) throws IOException {
		String apiUrl = API_URL + "balance" + slash + cardNumber;
		String jsonResponse = sendGetRequest(apiUrl);
		JSONObject jsonObject = new JSONObject(jsonResponse);
		return jsonObject.getDouble("balance");
	}

	public boolean changeCardPin(String cardNumber, String newPIN) throws IOException {
		String apiUrl = API_URL + "changePin" + cardNumber + slash + newPIN;
		String jsonResponse = sendPutRequest(apiUrl);
		return jsonResponse.equals("success");
	}

	public boolean deposit(String cardNumber, double amount) throws IOException {
		String apiUrl = API_URL + "deposit" + slash + cardNumber + slash + amount;
		String jsonResponse = sendPutRequest(apiUrl);
		return jsonResponse.equals("success");
	}

	public boolean withdraw(String cardNumber, double amount) throws IOException {
		String apiUrl = API_URL + "withdraw" + slash + cardNumber + slash + amount;
		String jsonResponse = sendPutRequest(apiUrl);
		return jsonResponse.equals("success");
	}

	private String sendGetRequest(String apiUrl) throws IOException {
		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			return response.toString();
		} finally {
			conn.disconnect();
		}
	}

	private String sendPutRequest(String apiUrl) throws IOException {
		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			return response.toString();
		} finally {
			conn.disconnect();
		}
	}

	public UserAccount buildAccountFromResponse(String response) {
		JSONObject jsonObject = new JSONObject(response);
		double balance = jsonObject.getDouble("balance");
		String cardNumber = jsonObject.getString("cardNumber");
		String pinCode = jsonObject.getString("pinCode");

		if (balance < 0L || cardNumber == null || pinCode == null) {
			return null;
		} else {
			return new UserAccount(balance, cardNumber, pinCode);
		}
	}
}
