package models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Crypto;

public class Transaction {
	// public key of owner
	protected String owner;
	// public key of receiver
	private String receiver;
	private int coins;
	private String id;
	protected String signature;

	public Transaction(String owner, String receiver, int coins, String id, String signature) {
		super();
		this.owner = owner;
		this.receiver = receiver;
		this.coins = coins;
		this.id = id;
		this.signature = signature;
	}

	public Transaction(String owner, String receiver, int coins, String id) {
		super();
		this.owner = owner;
		this.receiver = receiver;
		this.coins = coins;
		this.id = id;
	}

	public void sign(String key) {
		String sign = Crypto.signMessage(this.toString(), key);
		this.setSignature(sign);
	}

	public boolean isSignatureValid() {
		String message = this.toString();
		if (Crypto.verifyMessageSignature(message, this.signature, this.owner)) {
			return true;
		} else {
			return false;
		}
	}

	public String toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("owner", owner);
		json.addProperty("receiver", receiver);
		json.addProperty("coins", coins);
		json.addProperty("id", id);
		json.addProperty("signature", signature);
		return json.toString();
	}

	public String toString() {
		JsonObject json = new JsonObject();
		json.addProperty("owner", owner);
		json.addProperty("receiver", receiver);
		json.addProperty("coins", coins);
		json.addProperty("id", id);
		return json.toString();
	}

	public static Transaction fromJSON(String obj) {
		String s = "wwww";
		JsonParser parser = new JsonParser();
		JsonObject jsonobj = parser.parse(obj).getAsJsonObject();
		return new Transaction(jsonobj.get("owner").getAsString(), jsonobj.get("receiver").getAsString(),
				Integer.parseInt(jsonobj.get("coins").getAsString()), jsonobj.get("id").getAsString(),
				jsonobj.get("signature").getAsString());
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}