package cn.com.fxsh.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author hell
 * @date 2018/5/24
 */
public class Base64Util {

    /**
     * BASE64编码器。
     */
    static final Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     * Base64解码器。
     */
    static final Base64.Decoder DECODER = Base64.getDecoder();


    /**
     * BASE64加密
     *
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(final String plainText) {
        byte[] plainByte = plainText.getBytes();
        return encryptBASE64(plainByte);
    }

    /**
     *
     * @param plainText
     * @param codeType
     * @return
     */
    public static String encryptBASE64(final String plainText,String codeType) {
        try {
            byte[] plainByte = plainText.getBytes(codeType);
            return encryptBASE64(plainByte);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param textByte
     * @return
     */
    public static String encryptBASE64(byte[] textByte) {
        return ENCODER.encodeToString(textByte);
    }

    /**
     * BASE64解密
     *
     * @param encodeStr
     * @return
     */
    public static String decryptBASE64(final String encodeStr) {
        return decryptBASE64(encodeStr.getBytes());
    }

    public static String decryptBASE64(final byte[] encodeSByte) {
        try {
            return new String(DECODER.decode(encodeSByte), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
