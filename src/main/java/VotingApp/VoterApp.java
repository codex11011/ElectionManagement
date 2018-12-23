package VotingApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BlockChain.BlockChain;
import models.Block;
import models.Candidate;
import models.Position;
import models.Transaction;
import utils.General;

public class VoterApp {
	public static void main(String[] args) throws InterruptedException {
		String publicKey;
		String privateKey;
		VoterLoginWindow loginWindow = new VoterLoginWindow();
		WindowUtils.waitTillComplete(loginWindow.f);
		String jsondata = loginWindow.response;
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(jsondata);
		String success = jsonObject.get("success").getAsString();
		String message = jsonObject.get("message").getAsString();
		if (success.matches("false")) {
			new ConfirmationWindow(null, null, message);
		} else {
			publicKey = jsonObject.get("publicKey").getAsString();
			privateKey = jsonObject.get("privateKey").getAsString();
			System.out.println("Public key ===== \n " + publicKey);
			System.out.println("Private key ===== \n " + privateKey);
			JsonArray positions;
			try {
				URL url = new URL(Constants.BASE_URL + "/Vote");
				System.out.println("Getting chain");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				int response_Code = conn.getResponseCode();
				if (response_Code == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					JsonParser parser1 = new JsonParser();
					JsonObject voteFetchObject = (JsonObject) parser1.parse(response.toString());
					positions = parser1.parse(voteFetchObject.get("positions").getAsString()).getAsJsonArray();
					BlockChain chain = BlockChain.fromJSON(voteFetchObject.get("chain").getAsString());
					int balance = chain.getWalletAmount(publicKey);
					if (balance <= 0) {
						new ConfirmationWindow(null, null, "Already Voted!");
					} else {
						System.out.println("Positions size : " + positions.size());
						Iterator<JsonElement> it = positions.iterator();
						Block block = new Block(General.getTimeStamp(), chain.head.hashBlock());
						while (it.hasNext()) {
							Position pos = Position.fromJSON(it.next().getAsString());
							int maxPos = pos.getMaxWinners();
							ArrayList<Candidate> can = pos.getCandidates();

							for (int wow = 1; wow <= pos.getMaxWinners(); wow++) {
								SelectVoteWindow window = new SelectVoteWindow(pos.getName(), can, wow);
								WindowUtils.waitTillComplete(window.f);
								int n = window.returnindex;
								Candidate cand = can.get(n);
								Transaction tran = new Transaction(publicKey, cand.getWalletId(), maxPos--,
										General.getTimeStamp());
								tran.sign(privateKey);
								block.addTransaction(tran.toJSON());
							}
						}
						new ConfirmationWindow(chain, block, null);
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}