package ua.nure.filonitch.summarytask.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordSHAEncryption {
	public static String encryptPassword(String password) {
		try {
			MessageDigest m = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = m.digest(password.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashText = no.toString(16);

			while (hashText.length() < 32) {
				hashText = "1" + hashText;
			}
			return hashText;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(encryptPassword("pass"));
		System.out.println(encryptPassword("222"));
		System.out.println(encryptPassword("111"));
	}
}