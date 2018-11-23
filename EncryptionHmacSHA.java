package com.algorithm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class EncryptionHmacSHA {
	 public static void main(String[] args) {
		 EncryptionHmacSHA encrypt = new EncryptionHmacSHA();
		 String message="This is my message.";
		 String key="your_key";
		 encrypt.endcode(message,key);
		 
	 }
	 public void endcode(String message,String key) {
		 String algorithm="HmacSHA384";
		 
		 try {
			Mac sha256_hmac=Mac.getInstance(algorithm);
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
			sha256_hmac.init(secret_key);
			String hash = org.apache.commons.codec.binary.Base64.encodeBase64String(sha256_hmac.doFinal(message.getBytes("UTF-8")));
			//String hash = Base64.encode(sha256_hmac.doFinal(message.getBytes("UTF-8"))); 
		    //SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), algorithm); 
			System.out.println(hash);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	 }
	 public void decode(String hash) {
		
		 
	 }
}
