/**
 * 2016年2月26日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.authentication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



/**
 * @author hit_lacus@126.com
 */
public class RsaUtil {
	
	
	private static String otherPrivateKey = 
			"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOJmd+tciFDLf4na"
			+"CWsxGqlflcEm4FPf55HHiHt5qSLcmDo+Tx1XfiIIrQAnI/Yp07ZkZZruuWRMxtX6"
			+"ggVb93icERoXMUj6OL3SdCgk0soSrA3k7/te71lUyA5ghkVR7WEDIZPM8nIFbPij"
			+"5SyvhbIJbjLuBeqIj0l/VbkvrE1tAgMBAAECgYEA3EyvJeOtdUj/Oolint5yPhuX"
			+"ohKNOLBoKc9J/DRfI2AZ9JrK6xdG0iE1DjAH6zG6JAG/aKog1ftrg622NTbPLsWT"
			+"jSNVa+l30YN/y0kJzY0jPftI+8q+JRcxi5ehAYQon1MiWUJbGqIv5m7cgwIM0uZU"
			+"/d7OKGDUhqaPD2b/jeECQQD4hlJKKoCQcFCjCOEjtT41ADYwCnDOyzPRrAvCuGRT"
			+"J7fBIhNg75r+xTqz60Q22rox1/YQee8OgS22Rd8EGOclAkEA6TXJFgBZ0msb0iHM"
			+"n4WYpuJRKcFCVnR9nTEz8bja5pbKoVQWM/omHabBhOjJkAEHPJGReA0xIgAbx/J0"
			+"h8H+qQJAPvT3/6XIr85j/wkOz5boTVDiaUUb0qcTV3g3+nnvoEkdeaWCNzo7j3Kz"
			+"LkTZIPRD2sCUqxPYhVogFF6a4lTUqQJBAIhQQviUPexrZPiffv6jVUX+tVhFuWPV"
			+"dXMDRox8856i90/zWYMKLWtZhHYOp3p/z14M7E2S0GxCSfrGcmzD7PkCQQDdChYo"
			+"LNCy/wgbhaWsMUN7hM5biJ6kaz+gyvvzaw1d4VTPuKHZ6eoS4cRTHDK/iwnL7K7Y"
			+"4Plx8bNEXRpZrLE3";

	private static RSAPrivateKey privateKey = loadPrivateKeyByStr(otherPrivateKey);
	
	/**
	 * 使用公钥解密加密后的密文,获得令牌
	 * @param notoken
	 * @return
	 * @throws Exception 私钥解密可能出现异常
	 */
	public static String getRealToken(String notoken) throws Exception{
		byte[] finalBytes = decrypt(privateKey,Base64.decode(notoken));
		return new String(finalBytes);
	}
	
	
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',  
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
  
    /** 
     * 随机生成密钥对 
     */  
    public static void genKeyPair(String filePath) {  
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象  
        KeyPairGenerator keyPairGen = null;  
        try {  
            keyPairGen = KeyPairGenerator.getInstance("RSA");  
        } catch (NoSuchAlgorithmException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        // 初始化密钥对生成器，密钥大小为96-1024位  
        keyPairGen.initialize(1024,new SecureRandom());  
        // 生成一个密钥对，保存在keyPair中  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        // 得到私钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        // 得到公钥  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        try {  
            // 得到公钥字符串  
            String publicKeyString = Base64.encode(publicKey.getEncoded());  
            // 得到私钥字符串  
            String privateKeyString = Base64.encode(privateKey.getEncoded());  
            // 将密钥对写入到文件  
            FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");  
            FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");  
            BufferedWriter pubbw = new BufferedWriter(pubfw);  
            BufferedWriter pribw = new BufferedWriter(prifw);  
            pubbw.write(publicKeyString);  
            pribw.write(privateKeyString);  
            pubbw.flush();  
            pubbw.close();  
            pubfw.close();  
            pribw.flush();  
            pribw.close();  
            prifw.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 从文件中输入流中加载公钥 
     *  
     * @param in 
     *            公钥输入流 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static String loadPublicKeyByFile(String path) throws Exception {  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(path  
                    + "/publicKey.keystore"));  
            String readLine = null;  
            StringBuilder sb = new StringBuilder();  
            while ((readLine = br.readLine()) != null) {  
                sb.append(readLine);  
            }  
            br.close();  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("公钥数据流读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥输入流为空");  
        }  
    }  
  
    /** 
     * 从字符串中加载公钥 
     *  
     * @param publicKeyStr 
     *            公钥数据字符串 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = Base64.decode(publicKeyStr);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    }  
  
    /** 
     * 从文件中加载私钥 
     *  
     * @param keyFileName 
     *            私钥文件名 
     * @return 是否成功 
     * @throws Exception 
     */  
    public static String loadPrivateKeyByFile(String path) throws Exception {  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(path  
                    + "/privateKey.keystore"));  
            String readLine = null;  
            StringBuilder sb = new StringBuilder();  
            while ((readLine = br.readLine()) != null) {  
                sb.append(readLine);  
            }  
            br.close();  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("私钥数据读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥输入流为空");  
        }  
    }  
  
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr){  
        try {  
            byte[] buffer = Base64.decode(privateKeyStr);  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            //throw new Exception("无此算法");
        	return null;
        } catch (InvalidKeySpecException e) {
        	System.out.println(e.getMessage());
            //throw new Exception("私钥非法"); 
        	return null;
        } catch (NullPointerException e) {  
            //throw new Exception("私钥数据为空");  
        	return null;
        }  
    }  
  
    /** 
     * 公钥加密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */  
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("加密公钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] output = cipher.doFinal(plainTextData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    }  
  
    /** 
     * 私钥加密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param plainTextData 
     *            明文数据 
     * @return 
     * @throws Exception 
     *             加密过程中的异常信息 
     */  
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData)  
            throws Exception {  
        if (privateKey == null) {  
            throw new Exception("加密私钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
            byte[] output = cipher.doFinal(plainTextData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    }  
  
    /** 
     * 私钥解密过程 
     *  
     * @param privateKey 
     *            私钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */  
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)  
            throws Exception {  
    	
    	
        if (privateKey == null) {  
            throw new Exception("解密私钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] output = cipher.doFinal(cipherData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    }  
  
    /** 
     * 公钥解密过程 
     *  
     * @param publicKey 
     *            公钥 
     * @param cipherData 
     *            密文数据 
     * @return 明文 
     * @throws Exception 
     *             解密过程中的异常信息 
     */  
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("解密公钥为空, 请设置");  
        }  
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            byte[] output = cipher.doFinal(cipherData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    }  
  
    /** 
     * 字节数据转十六进制字符串 
     *  
     * @param data 
     *            输入数据 
     * @return 十六进制内容 
     */  
    public static String byteArrayToString(byte[] data) {  
        StringBuilder stringBuilder = new StringBuilder();  
        for (int i = 0; i < data.length; i++) {  
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移  
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);  
            // 取出字节的低四位 作为索引得到相应的十六进制标识符  
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);  
            if (i < data.length - 1) {  
                stringBuilder.append(' ');  
            }  
        }  
        return stringBuilder.toString();  
    }  
}


final class Base64 {  
	  
    static private final int     BASELENGTH           = 128;  
    static private final int     LOOKUPLENGTH         = 64;  
    static private final int     TWENTYFOURBITGROUP   = 24;  
    static private final int     EIGHTBIT             = 8;  
    static private final int     SIXTEENBIT           = 16;  
    static private final int     FOURBYTE             = 4;  
    static private final int     SIGN                 = -128;  
    static private final char    PAD                  = '=';  
    static private final boolean fDebug               = false;  
    static final private byte[]  base64Alphabet       = new byte[BASELENGTH];  
    static final private char[]  lookUpBase64Alphabet = new char[LOOKUPLENGTH];  
  
    static {  
        for (int i = 0; i < BASELENGTH; ++i) {  
            base64Alphabet[i] = -1;  
        }  
        for (int i = 'Z'; i >= 'A'; i--) {  
            base64Alphabet[i] = (byte) (i - 'A');  
        }  
        for (int i = 'z'; i >= 'a'; i--) {  
            base64Alphabet[i] = (byte) (i - 'a' + 26);  
        }  
  
        for (int i = '9'; i >= '0'; i--) {  
            base64Alphabet[i] = (byte) (i - '0' + 52);  
        }  
  
        base64Alphabet['+'] = 62;  
        base64Alphabet['/'] = 63;  
  
        for (int i = 0; i <= 25; i++) {  
            lookUpBase64Alphabet[i] = (char) ('A' + i);  
        }  
  
        for (int i = 26, j = 0; i <= 51; i++, j++) {  
            lookUpBase64Alphabet[i] = (char) ('a' + j);  
        }  
  
        for (int i = 52, j = 0; i <= 61; i++, j++) {  
            lookUpBase64Alphabet[i] = (char) ('0' + j);  
        }  
        lookUpBase64Alphabet[62] = (char) '+';  
        lookUpBase64Alphabet[63] = (char) '/';  
  
    }  
  
    private static boolean isWhiteSpace(char octect) {  
        return (octect == 0x20 || octect == 0xd || octect == 0xa || octect == 0x9);  
    }  
  
    private static boolean isPad(char octect) {  
        return (octect == PAD);  
    }  
  
    private static boolean isData(char octect) {  
        return (octect < BASELENGTH && base64Alphabet[octect] != -1);  
    }  
  
    /** 
     * Encodes hex octects into Base64 
     * 
     * @param binaryData Array containing binaryData 
     * @return Encoded Base64 array 
     */  
    public static String encode(byte[] binaryData) {  
  
        if (binaryData == null) {  
            return null;  
        }  
  
        int lengthDataBits = binaryData.length * EIGHTBIT;  
        if (lengthDataBits == 0) {  
            return "";  
        }  
  
        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;  
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;  
        int numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets;  
        char encodedData[] = null;  
  
        encodedData = new char[numberQuartet * 4];  
  
        byte k = 0, l = 0, b1 = 0, b2 = 0, b3 = 0;  
  
        int encodedIndex = 0;  
        int dataIndex = 0;  
        if (fDebug) {  
            System.out.println("number of triplets = " + numberTriplets);  
        }  
  
        for (int i = 0; i < numberTriplets; i++) {  
            b1 = binaryData[dataIndex++];  
            b2 = binaryData[dataIndex++];  
            b3 = binaryData[dataIndex++];  
  
            if (fDebug) {  
                System.out.println("b1= " + b1 + ", b2= " + b2 + ", b3= " + b3);  
            }  
  
            l = (byte) (b2 & 0x0f);  
            k = (byte) (b1 & 0x03);  
  
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);  
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);  
            byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6) : (byte) ((b3) >> 6 ^ 0xfc);  
  
            if (fDebug) {  
                System.out.println("val2 = " + val2);  
                System.out.println("k4   = " + (k << 4));  
                System.out.println("vak  = " + (val2 | (k << 4)));  
            }  
  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | (k << 4)];  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[(l << 2) | val3];  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[b3 & 0x3f];  
        }  
  
        // form integral number of 6-bit groups  
        if (fewerThan24bits == EIGHTBIT) {  
            b1 = binaryData[dataIndex];  
            k = (byte) (b1 & 0x03);  
            if (fDebug) {  
                System.out.println("b1=" + b1);  
                System.out.println("b1<<2 = " + (b1 >> 2));  
            }  
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[k << 4];  
            encodedData[encodedIndex++] = PAD;  
            encodedData[encodedIndex++] = PAD;  
        } else if (fewerThan24bits == SIXTEENBIT) {  
            b1 = binaryData[dataIndex];  
            b2 = binaryData[dataIndex + 1];  
            l = (byte) (b2 & 0x0f);  
            k = (byte) (b1 & 0x03);  
  
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);  
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);  
  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | (k << 4)];  
            encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2];  
            encodedData[encodedIndex++] = PAD;  
        }  
  
        return new String(encodedData);  
    }  
  
    /** 
     * Decodes Base64 data into octects 
     * 
     * @param encoded string containing Base64 data 
     * @return Array containind decoded data. 
     */  
    public static byte[] decode(String encoded) {  
  
        if (encoded == null) {  
            return null;  
        }  
  
        char[] base64Data = encoded.toCharArray();  
        // remove white spaces  
        int len = removeWhiteSpace(base64Data);  
  
        if (len % FOURBYTE != 0) {  
            return null;//should be divisible by four  
        }  
  
        int numberQuadruple = (len / FOURBYTE);  
  
        if (numberQuadruple == 0) {  
            return new byte[0];  
        }  
  
        byte decodedData[] = null;  
        byte b1 = 0, b2 = 0, b3 = 0, b4 = 0;  
        char d1 = 0, d2 = 0, d3 = 0, d4 = 0;  
  
        int i = 0;  
        int encodedIndex = 0;  
        int dataIndex = 0;  
        decodedData = new byte[(numberQuadruple) * 3];  
  
        for (; i < numberQuadruple - 1; i++) {  
  
            if (!isData((d1 = base64Data[dataIndex++])) || !isData((d2 = base64Data[dataIndex++]))  
                || !isData((d3 = base64Data[dataIndex++]))  
                || !isData((d4 = base64Data[dataIndex++]))) {  
                return null;  
            }//if found "no data" just return null  
  
            b1 = base64Alphabet[d1];  
            b2 = base64Alphabet[d2];  
            b3 = base64Alphabet[d3];  
            b4 = base64Alphabet[d4];  
  
            decodedData[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);  
            decodedData[encodedIndex++] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));  
            decodedData[encodedIndex++] = (byte) (b3 << 6 | b4);  
        }  
  
        if (!isData((d1 = base64Data[dataIndex++])) || !isData((d2 = base64Data[dataIndex++]))) {  
            return null;//if found "no data" just return null  
        }  
  
        b1 = base64Alphabet[d1];  
        b2 = base64Alphabet[d2];  
  
        d3 = base64Data[dataIndex++];  
        d4 = base64Data[dataIndex++];  
        if (!isData((d3)) || !isData((d4))) {//Check if they are PAD characters  
            if (isPad(d3) && isPad(d4)) {  
                if ((b2 & 0xf) != 0)//last 4 bits should be zero  
                {  
                    return null;  
                }  
                byte[] tmp = new byte[i * 3 + 1];  
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);  
                tmp[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);  
                return tmp;  
            } else if (!isPad(d3) && isPad(d4)) {  
                b3 = base64Alphabet[d3];  
                if ((b3 & 0x3) != 0)//last 2 bits should be zero  
                {  
                    return null;  
                }  
                byte[] tmp = new byte[i * 3 + 2];  
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);  
                tmp[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);  
                tmp[encodedIndex] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));  
                return tmp;  
            } else {  
                return null;  
            }  
        } else { //No PAD e.g 3cQl  
            b3 = base64Alphabet[d3];  
            b4 = base64Alphabet[d4];  
            decodedData[encodedIndex++] = (byte) (b1 << 2 | b2 >> 4);  
            decodedData[encodedIndex++] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));  
            decodedData[encodedIndex++] = (byte) (b3 << 6 | b4);  
  
        }  
  
        return decodedData;  
    }  
  
    /** 
     * remove WhiteSpace from MIME containing encoded Base64 data. 
     * 
     * @param data  the byte array of base64 data (with WS) 
     * @return      the new length 
     */  
    private static int removeWhiteSpace(char[] data) {  
        if (data == null) {  
            return 0;  
        }  
  
        // count characters that's not whitespace  
        int newSize = 0;  
        int len = data.length;  
        for (int i = 0; i < len; i++) {  
            if (!isWhiteSpace(data[i])) {  
                data[newSize++] = data[i];  
            }  
        }  
        return newSize;  
    }  
} 
