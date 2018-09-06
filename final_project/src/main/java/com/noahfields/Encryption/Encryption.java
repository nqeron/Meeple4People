package com.noahfields.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class Encryption {

	/**
	 * getSalt: produces 16 byte cryptographic salt (random byte sequence)
	 * @return 16 bytes
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	
	/**
	 * getHexBinaryString: translates byte array to a hex-binary string 
	 * @param salt: byte array
	 * @return HexBinary string
	 */
	public static String getHexBinaryString(byte[] salt) {
		return DatatypeConverter.printHexBinary(salt);
	}

	/**
	 * getPassword: hashes a password with the given salt, using SHA-256
	 * @param password: password to hash
	 * @param salt: salt to add to the password before hashing
	 * @return HexBinary string of the hashed password
	 * @throws NoSuchAlgorithmException
	 */
	public static String getPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(salt);
		byte[] digest = md.digest(password.getBytes());
		
		return DatatypeConverter.printHexBinary(digest);
	}

	/**
	 * getBytesFromString: translates HexBinary string back to byte array
	 * @param salt: HexBinary string
	 * @return (16-byte) byte array
	 */
	public static byte[] getBytesFromString(String salt) {
		return DatatypeConverter.parseHexBinary(salt);
	}

}
