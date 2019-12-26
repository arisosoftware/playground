package com.arisosoftware.rsautil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import org.apache.log4j.Logger;

public class Digest {
	private static final Logger log = Logger.getLogger(Digest.class);
	static MessageDigest md = null;
	
	static MessageDigest GetMD5MessageDigest() throws NoSuchAlgorithmException
	{
		if (md == null)
		{
			md = MessageDigest.getInstance("MD5");
		}
		return md;
	}
	
	 
	public static String signMD5(String aValue, String encoding) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		 
			byte[] input = aValue.getBytes(encoding);
			MessageDigest md = GetMD5MessageDigest();
			return ConvertUtils.toHex(md.digest(input));
	 
	}

	public static String hmacSign(String aValue) {
		byte[] input = aValue.getBytes();
		
		return ConvertUtils.toHex(md.digest(input));
	}

	public static String hmacSign(String aValue, String aKey) throws Throwable {
		return hmacSign(aValue, aKey, ConstName.CHAR_ENCODING_UTF8);
	}

	public static String hmacSign(String aValue, String aKey, String encoding) throws NoSuchAlgorithmException {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encoding);
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}
		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;

		md =GetMD5MessageDigest();

		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return ConvertUtils.toHex(dg);
	}

	public static String hmacSHASign(String aValue, String aKey, String encoding) throws NoSuchAlgorithmException {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encoding);
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}
		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;

		md = MessageDigest.getInstance(ConstName.SHA_Code);

		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 20);
		dg = md.digest();
		return ConvertUtils.toHex(dg);
	}

	public static String digest(String aValue) throws NoSuchAlgorithmException {
		return digest(aValue, ConstName.CHAR_ENCODING_UTF8);

	}

	public static String digest(String aValue, String encoding) throws NoSuchAlgorithmException {
		aValue = aValue.trim();
		byte value[];
		try {
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md = null;

		md = MessageDigest.getInstance("SHA");

		return ConvertUtils.toHex(md.digest(value));
	}

	public static String digest(String aValue, String alg, String encoding) throws NoSuchAlgorithmException {
		aValue = aValue.trim();
		byte value[];
		try {
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md = null;

		md = MessageDigest.getInstance(alg);

		return ConvertUtils.toHex(md.digest(value));
	}

	public static String udpSign(String aValue) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		byte[] input = aValue.getBytes(ConstName.CHAR_ENCODING_UTF8);
		MessageDigest md = MessageDigest.getInstance(ConstName.SHA_Code);

		return Base64.getEncoder().encodeToString(md.digest(input));

	}

}
