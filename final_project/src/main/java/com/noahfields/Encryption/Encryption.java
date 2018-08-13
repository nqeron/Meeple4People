package com.noahfields.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class Encryption {

	public static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public static String getHexBinaryString(byte[] salt) {
		// TODO Auto-generated method stub
		return DatatypeConverter.printHexBinary(salt);
	}

	public static String getPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(salt);
		byte[] digest = md.digest(password.getBytes());
		
		return DatatypeConverter.printHexBinary(digest);
	}

	public static byte[] getBytesFromString(String salt) {
		// TODO Auto-generated method stub
		return DatatypeConverter.parseHexBinary(salt);
	}

}
