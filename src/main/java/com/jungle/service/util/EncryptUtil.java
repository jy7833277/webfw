package com.jungle.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

public class EncryptUtil {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    private final static String KEY_DES = "DES";
    public static final String KEY_HMD5_256 = "HmacSHA256";


    private static Logger LOG = LoggerFactory.getLogger(EncryptUtil.class);

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String encryptMD5_16(String content) {
        String resultString = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(content.getBytes());

            resultString = byteArrayToHexString(md5.digest()).substring(8, 24);
        } catch (Exception e) {
            LOG.error("encryptMD5 fail", e);
        }
        return resultString.toLowerCase();
    }
    /**
     * 标准MD5加密
     * @param content
     * @return
     */
    public static String encryptMD5_Standard(String content) {
        String resultString = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(content.getBytes());

            // 将得到的字节数组变成字符串返回
            resultString = byteArrayToHexString(md5.digest());
        } catch (Exception e) {
            LOG.error("encryptMD5 fail", e);
        }

        return resultString.toLowerCase();
    }
    /**
     * SID标准MD5加密
     * @param content
     * @return
     */
    public static String encryptMD5_STD(String content) {
        return EncryptUtil.encryptMD5_Standard(content);
    }

    /**
     * MD5+Salt加密
     * @param content
     * @return
     */
    public static String encryptMD5_Salt(String content) {

        String resultString = "";
        String appkey = "jungle,dklfgsi";

        byte[] a = appkey.getBytes();
        byte[] datSource = content.getBytes();
        byte[] b = new byte[a.length + 4 + datSource.length];

        int i;
        for (i = 0; i < datSource.length; i++)
        {
            b[i] = datSource[i];
        }

        b[i++] = (byte)163;
        b[i++] = (byte)172;
        b[i++] = (byte)161;
        b[i++] = (byte)163;

        for (int k = 0; k < a.length; k++)
        {
            b[i] = a[k];
            i++;
        }

        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(b);

            // 将得到的字节数组变成字符串返回
            resultString =new HexBinaryAdapter().marshal(md5.digest());//转为十六进制的字符串
        } catch (Exception e) {
            LOG.error("encryptMD5 fail", e);
        }

        return resultString.toLowerCase();
    }

    /**
     * HMac256加密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptHMac256(String content, String key) {

        String resultString = "";
        try {
            // 还原密钥
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            // 初始化mac
            mac.init(secretKey);
            // 执行消息摘要
            byte[] digest = mac.doFinal(content.getBytes());
            resultString = new String(byteArrayToBase64(digest, false));
        } catch (Exception e) {
            LOG.error("encryptHMac256 fail", e);
        }

        return  resultString;
    }

    //copy from com.alibaba.druid.util.Base64  避免依赖

    /**
     * This array is a lookup table that translates 6-bit positive integer index values into their "Base64 Alphabet"
     * equivalents as specified in Table 1 of RFC 2045.
     */
    private static final char intToBase64[]    = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '+', '/' };

    /**
     * This array is a lookup table that translates 6-bit positive integer index values into their
     * "Alternate Base64 Alphabet" equivalents. This is NOT the real Base64 Alphabet as per in Table 1 of RFC 2045. This
     * alternate alphabet does not use the capital letters. It is designed for use in environments where "case folding"
     * occurs.
     */
    private static final char intToAltBase64[] = { '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':',
            ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '+', '?' };


    public static String byteArrayToBase64(byte[] a, boolean alternate) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuilder result = new StringBuilder(resultLen);
        char[] intToAlpha = (alternate ? intToAltBase64 : intToBase64);

        // Translate all full groups from byte array elements to Base64
        int inCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            int byte0 = a[inCursor++] & 0xff;
            int byte1 = a[inCursor++] & 0xff;
            int byte2 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
            result.append(intToAlpha[(byte1 << 2) & 0x3f | (byte2 >> 6)]);
            result.append(intToAlpha[byte2 & 0x3f]);
        }

        // Translate partial group if present
        if (numBytesInPartialGroup != 0) {
            int byte0 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte0 << 4) & 0x3f]);
                result.append("==");
            } else {
                // assert numBytesInPartialGroup == 2;
                int byte1 = a[inCursor++] & 0xff;
                result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
                result.append(intToAlpha[(byte1 << 2) & 0x3f]);
                result.append('=');
            }
        }
        // assert inCursor == a.length;
        // assert result.length() == resultLen;
        return result.toString();
    }

    public static byte[] encryptSHA(String content) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(content.getBytes());

        return sha.digest();

    }

    public static byte[] encryptDes(byte[] src, byte[] key)throws Exception {

        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(KEY_DES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);
    }

    public static byte[] decryptDes(byte[] src, byte[] key)throws Exception {

        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(KEY_DES);
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);

    }

    /**
     * 转换字节数组为十六进制字符串
     * @param     b 字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String genRandomNum(int pwd_len){
        //35是因为数组是从0开始的，26个字母+10个 数字
        final int  maxNum = 36;
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < pwd_len){
            //生成随机数，取绝对值，防止 生成负数，

            i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }

        return pwd.toString();
    }
}
