package com.arisosoftware.rsautil;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

		SecretKeySpec secretKey = new SecretKeySpec(key, ConstName.AES_Code);
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, ConstName.AES_Code);
		Cipher cipher = Cipher.getInstance(ConstName.AES_ALGORITHM);// 创建密码器
		IvParameterSpec iv = new IvParameterSpec(key);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, seckey, iv);// 初始化
		byte[] result = cipher.doFinal(data);
		return result; // 加密

	}

	public static byte[] decrypt(byte[] data, byte[] key) throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {

		SecretKeySpec secretKey = new SecretKeySpec(key, ConstName.AES_Code);
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, ConstName.AES_Code);
		Cipher cipher = Cipher.getInstance(ConstName.AES_ALGORITHM);// 创建密码器
		IvParameterSpec iv = new IvParameterSpec(key);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.DECRYPT_MODE, seckey, iv);// 初始化
		byte[] result = cipher.doFinal(data);
		return result; // 解密

	}

	public static String encryptToBase64(String data, String key) throws Exception {

		byte[] valueByte = encrypt(data.getBytes(ConstName.CHAR_ENCODING_UTF8), key.getBytes(ConstName.CHAR_ENCODING_UTF8));
		return new String(Base64.getEncoder().encodeToString(valueByte));

	}

	public static String decryptFromBase64(String data, String key) throws Exception {

		byte[] originalData = Base64.getDecoder().decode(data.getBytes());
		byte[] valueByte = decrypt(originalData, key.getBytes(ConstName.CHAR_ENCODING_UTF8));
		return new String(valueByte, ConstName.CHAR_ENCODING_UTF8);

	}

	public static String encryptWithKeyBase64(String data, String key) throws Exception {

		byte[] valueByte = encrypt(data.getBytes(ConstName.CHAR_ENCODING_UTF8), Base64.getDecoder().decode(key.getBytes()));
		return new String(Base64.getEncoder().encode(valueByte));

	}

	public static String decryptWithKeyBase64(String data, String key) throws Exception {

		byte[] originalData = Base64.getDecoder().decode(data.getBytes());
		byte[] valueByte = decrypt(originalData, Base64.getDecoder().decode(key.getBytes()));
		return new String(valueByte, ConstName.CHAR_ENCODING_UTF8);

	}

	public static byte[] genarateRandomKey() throws Exception {
		KeyGenerator keygen = null;

		keygen = KeyGenerator.getInstance(ConstName.AES_ALGORITHM);

		SecureRandom random = new SecureRandom();
		keygen.init(random);
		Key key = keygen.generateKey();
		return key.getEncoded();
	}

	public static String genarateRandomKeyWithBase64() throws Exception {
		return new String(Base64.getEncoder().encode(genarateRandomKey()));
	}

}
