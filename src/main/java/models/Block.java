package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BlockChain.Constants;

public class Block {
	private int height;
	private String timestamp;
	protected JsonArray transactions;
	private String previousHash;
	protected int nonce;
	private Block parent;

	public Block(int height, String timestamp, ArrayList<Transaction> transactions, String previousHash, int nonce,
			Block parent) {
		super();
		this.height = height;
		this.timestamp = timestamp;
		this.transactions = new JsonArray();
		for (int i = 0; i < transactions.size(); i++) {
			this.transactions.add(transactions.get(i).toJSON());
		}
		this.previousHash = previousHash;
		this.nonce = nonce;
		this.parent = parent;
	}

	public Block(int height, String timestamp, JsonArray transactions, String previousHash, int nonce, Block parent) {
		super();
		this.height = height;
		this.timestamp = timestamp;
		this.transactions = transactions;
		this.previousHash = previousHash;
		this.nonce = nonce;
		this.parent = parent;
	}

	public Block(String timestamp, JsonArray transactions, String previousHash) {
		super();
		this.height = -1;
		this.timestamp = timestamp;
		this.transactions = transactions;
		this.previousHash = previousHash;
		this.nonce = 0;
		parent = null;
	}

	public Block(String timestamp, String previousHash) {
		super();
		this.height = -1;
		this.timestamp = timestamp;
		this.transactions = new JsonArray();
		this.previousHash = previousHash;
		this.nonce = 0;
		parent = null;
	}

	public Block(String timestamp, JsonArray transactions, String previousHash, int nonce) {
		super();
		this.height = -1;
		this.timestamp = timestamp;
		this.transactions = transactions;
		this.previousHash = previousHash;
		this.nonce = nonce;
		parent = null;
	}

	public static Block getGenesis() {
		return new Block(utils.General.getTimeStamp(), new JsonArray(), null);
	}

	public void incNonce() {
		this.nonce += 1;
	}

	public void addTransaction(String transaction) {
		transactions.add(transaction);
	}

	public String hashBlock() {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(this.toJSON().getBytes("UTF-8"));
//			return DatatypeConverter.printHexBinary(hash);
			StringBuilder string = new StringBuilder();
			for (byte b : hash) {
				int val = b;
				for (int i = 0; i < 8; i++) {
					string.append((val & 128) == 0 ? 0 : 1);
					val <<= 1;
				}
			}
			return string.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean isvalid() {
		String matchString = StringUtils.repeat("0", Constants.DIFFICULTY);
		String subs = this.hashBlock().substring(0, Constants.DIFFICULTY);
		if (subs.matches(matchString)) {
			// System.out.println("Block Valid with Hash : " + this.hashBlock());
			return true;
		} else {
			return false;
		}
	}

	public boolean mine() {
		// System.out.println("Mining");
		long start = System.currentTimeMillis();
		int hashesDone = 0;
		while (!this.isvalid()) {
			hashesDone++;
			this.incNonce();
			if (hashesDone % 300000 == 0) {
				long end = System.currentTimeMillis();
				double sec = (end - start) / 1000.0;
				System.out.println("Hash Rate : " + 300000 / sec);
				start = end;
			}
		}
		if (this.isvalid()) {
			// System.out.println("Mine Success! :)");
			return true;
		} else {
			// System.out.println("Mine Failure! :(");
			return false;
		}
	}

	public ArrayList<Transaction> getTransactionsArray() {
		ArrayList<Transaction> t = new ArrayList<Transaction>();
		for (int i = 0; i < this.transactions.size(); i++) {
			Transaction tempTrans = Transaction.fromJSON(this.transactions.get(i).getAsString());
			t.add(tempTrans);
		}
		return t;
	}

	public static Block fromJSON(String obj) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject) parser.parse(obj);
		Block bk = new Block(jsonObject.get("timestamp").getAsString(),
				parser.parse(jsonObject.get("transactions").toString()).getAsJsonArray(),
				jsonObject.get("previous_hash").getAsString(), Integer.parseInt(jsonObject.get("nonce").getAsString()));
		bk.setHeight(Integer.parseInt(jsonObject.get("height").getAsString()));
		return bk;
	}

	public String toJSON() {
		JsonObject obj = new JsonObject();
		obj.addProperty("timestamp", this.timestamp);
		obj.add("transactions", this.transactions);
		obj.addProperty("previous_hash", this.previousHash);
		obj.addProperty("nonce", Integer.toString(nonce));
		obj.addProperty("height", Integer.toString(height));
		return obj.toString();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public JsonArray getTransactions() {
		return transactions;
	}

	public void setTransactions(JsonArray transactions) {
		this.transactions = transactions;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public Block getParent() {
		return parent;
	}

	public void setParent(Block parent) {
		this.parent = parent;
		this.height += 1;
	}

}