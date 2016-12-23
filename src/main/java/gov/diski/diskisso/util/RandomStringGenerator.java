package gov.diski.diskisso.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class RandomStringGenerator {

	public static String generateRandomString() {
		byte randomBytes[] = new byte[32];
		SecureRandom sr = null;
		try {
			// generate cryptographically secure random number
			sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(randomBytes);
		} catch (NoSuchAlgorithmException ex) {
			Random r = new Random(System.currentTimeMillis());
			r.nextBytes(randomBytes);
		}

		byte[] resultBytes;
		try {
			MessageDigest digester = MessageDigest.getInstance("SHA-256");
			resultBytes = digester.digest(randomBytes);
		} catch (NoSuchAlgorithmException e) {
			resultBytes = randomBytes;
		}

		return Base64.encodeBase64URLSafeString(resultBytes);
	}
}
