











































package models;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.Crypto;

public class Voter {
	private String publicKey;
	private String privateKey;
	private String username;
	private String pwdHash;

	public Voter(String publicKey, String privateKey, String username, String pwdHash) {
		super();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.username = username;
		this.pwdHash = pwdHash;
	}

	public Voter(String username, String pwd) {
		KeyPair key = Crypto.generateKeys();
		this.privateKey = Crypto.getPrivateKeyasString(key);
		this.publicKey = Crypto.getPublicKeyasString(key);
		this.username = username;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
			StringBuilder string = new StringBuilder();
			for (byte b : hash) {
				int val = b;
				for (int i = 0; i < 8; i++) {
					string.append((val & 128) == 0 ? 0 : 1);
					val <<= 1;
				}
			}
			this.pwdHash = string.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Voter() {
		// TODO Auto-generated constructor stub
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

}