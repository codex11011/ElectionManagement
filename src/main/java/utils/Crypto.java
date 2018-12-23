package utils;


import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Crypto {
	
	public static KeyPair generateKeys() {
		KeyPair pair;
		KeyPairGenerator keyGen;
		SecureRandom ran;
		try {
			// Use a digital signature algorithm
			keyGen = KeyPairGenerator.getInstance("DSA");
			// Random algorithm is SHA-1
			ran = SecureRandom.getInstance("SHA1PRNG");
			ran.setSeed(System.currentTimeMillis());
			keyGen.initialize(512, ran);
			pair = keyGen.generateKeyPair();
			return pair;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getPrivateKeyasString(KeyPair pair) {
		return Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
	}
	
	public static String getPublicKeyasString(KeyPair pair) {
		return Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
	}
	
	public static byte[] loadKey(String key) {
		return Base64.getDecoder().decode(key);
	}
	
	public static String signMessage(String message, String key) {
			Signature sig;
			try {
				PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Crypto.loadKey(key));
				KeyFactory keyFactory = KeyFactory.getInstance( "DSA");
				sig = Signature.getInstance("SHA1withDSA");
				sig.initSign(keyFactory.generatePrivate(keySpec));
				sig.update(message.getBytes());
				return Base64.getEncoder().encodeToString(sig.sign());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;			
	}
	
	public static boolean verifyMessageSignature(String message, String signature, String key) {
		Signature sig;
			try {
				X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Crypto.loadKey(key));
				KeyFactory keyFactory = KeyFactory.getInstance( "DSA");
				sig = Signature.getInstance("SHA1withDSA");
				sig.initVerify(keyFactory.generatePublic(keySpec));
				sig.update(message.getBytes());
				return sig.verify(Base64.getDecoder().decode(signature));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;			
	}
}