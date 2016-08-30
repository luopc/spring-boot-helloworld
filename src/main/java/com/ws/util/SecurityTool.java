package com.ws.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;

public class SecurityTool {

	

	/*
	 * Base64 使用US-ASCII子集的64个字符,即大小写的26个英文字母，0－9，＋，/。
	 * 编码总是基于3个字符，每个字符用8位二进制表示，因此一共24位，再分为4四组，每组6位，表示一个Base64的值。
	 * Base64值为0就是A，为27的就是b。这样，每3个字符产生4位的Base64字符。如果被加密的字符串每3个一组，还剩1或2个字符，
	 * 使用特殊字符"="补齐Base64的4字。
	 */

	private SecurityTool() {
	}

	/**
	 * 设置加密的key
	 * 
	 * @return byte[] 返回加密解密用的byte[]
	 * @throws Exception
	 */
	private byte[] getKey() throws Exception {
		return "[Z@!K]{/}x".getBytes(); // //return "[B@170888e".getBytes();
	}

	/**
	 * 加密字符串
	 * 
	 * @param encryptdata
	 *            String 要加密的内容
	 * @return String 返回加密后的字符串
	 * 
	 * @throws Exception
	 */
	public String encryptData(String encryptdata) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte[] rawKeyData = getKey();

		DESKeySpec dks = new DESKeySpec(rawKeyData);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		byte[] data = encryptdata.getBytes();
		byte[] encryptedData = cipher.doFinal(data);

		return byte2hex(encryptedData);
	}

	/**
	 * 解密字符串
	 * 
	 * @param decryptString
	 *            String 要解密的字符串
	 * 
	 * @return String 返回解密后的字符串
	 * 
	 * @throws Exception
	 */
	public String decryptData(String decryptString) throws Exception {
		byte[] decryptdata = hex2byte(decryptString);
		SecureRandom sr = new SecureRandom();
		byte[] rawKeyData = getKey();
		DESKeySpec dks = new DESKeySpec(rawKeyData);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);

		byte[] encryptedData = decryptdata;
		byte[] decryptedData = cipher.doFinal(encryptedData);
		return new String(decryptedData);
	}

	/**
	 * 对字符串进行Hash
	 * 
	 * @param toHashString
	 *            String 要hash的字符串
	 * @return String Hash后的字符串
	 */
	public static String getHashString(String toHashString) {
		try {
			return byte2hex(toHashString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			
			return null;
		}
	}

	/**
	 * Convert a byte array to a hexified string.
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i] & 0xff;
			if (val < 16) { // prepend leading zero
				sb.append('0');
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * Convert a hexified string into a byte array.
	 */
	private static byte[] hex2byte(String str) {
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			int index = i * 2;
			int val = Integer.parseInt(str.substring(index, index + 2), 16);
			bytes[i] = (byte) val;
		}
		return bytes;
	}

	/**
	 * MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * 验证输入的密码是否正确
	 * 
	 * @param md5Password
	 *            加密后的密码
	 * @param inputPassword
	 *            输入的字符串
	 * @return 验证结果，TRUE:正确 FALSE:错误
	 */
	public static boolean validatePassword(String md5Password,
			String inputPassword) {
		if (md5Password.equals(getMD5Str(inputPassword))) {
			return true;
		} else {
			return false;
		}
	}

	public static void Test001() {
		try {
			SecurityTool securityTool = new SecurityTool();
			String data = "12312312312312";
			

			String enpasswd = securityTool.encryptData(data);
			
			String depasswd = securityTool.decryptData(enpasswd);
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将 s 进行 BASE64 编码
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

}
