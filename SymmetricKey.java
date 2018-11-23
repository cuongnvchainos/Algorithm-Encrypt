package com.algorithm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

//This use for AES,DES,DESede..
//CuongNV

public class SymmetricKey {
	private SecretKeySpec secretKey;
	private Cipher cipher;
	
	public SymmetricKey(String secret, int length, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		this.cipher=Cipher.getInstance(algorithm);
		byte[] key = new byte[length];
		key =fixSecret(secret, length);
		this.secretKey=new SecretKeySpec(key, algorithm);
	}
	private byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
		if (s.length() < length) {
			int missingLength = length - s.length();
			for (int i = 0; i < missingLength; i++) {
				s += " ";
			}
		}
		return s.substring(0, length).getBytes("UTF-8");
	}
	
	public String encrypt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
		byte[] input = message.getBytes();
		byte[] encode = this.cipher.doFinal(input);
		String result=new String(encode);
		System.out.println(result);
		
		return result;
	}
	
	public void decrypt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
		byte[] input = message.getBytes();
		byte[] decode = this.cipher.doFinal(input);
		String result=new String(decode);
		System.out.println(result);
		System.out.println(this.secretKey);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		SymmetricKey st = new SymmetricKey("123abc!@#", 16, "AES");
		try {
			String mess =st.encrypt("test 123 123123 avasd");
			st.decrypt(mess);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

	}
	
	

}
