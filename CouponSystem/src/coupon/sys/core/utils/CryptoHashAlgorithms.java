package coupon.sys.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.CryptoHashException;

/**
 * The Class CryptoHashAlgorithms.
 */
public class CryptoHashAlgorithms {

	/*
	 * Hash password.
	 * 
	 * Get user input + desired hash algorithm and returns the hash equivalent
	 * string Method works with the following hash algorithms: MD2, MD5, SHA-224,
	 * SHA-256, SHA-384, SHA-512
	 * 
	 * @param password the password
	 * 
	 * @param algorithm the algorithm
	 * 
	 * @return the string
	 * 
	 * @throws CouponSystemExceptions
	 */
	public String hashPassword(byte[] password, String algorithm) throws CouponSystemExceptions {
		String hashValue = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(password);
			byte[] digestedBytes = messageDigest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoHashException("Algorithm Not supported", e);
		}
		return hashValue;
	}
}
