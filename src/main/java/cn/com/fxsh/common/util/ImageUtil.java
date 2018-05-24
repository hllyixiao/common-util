package cn.com.fxsh.common.util;

import sun.misc.BASE64Encoder;

import java.util.Objects;

/**
 * 图片处理工具
 * @author hell
 */
public class ImageUtil {

    /**
     * 前端识别Base64位码的头
     */
    private static final String BASE64_IMAGE_HEADER = "data:image/png;base64,";
    private static final BASE64Encoder encoder = new BASE64Encoder();

    /**
     * 将图片流转Base64位码的String
     * @param bytes
     * @return
     */
    public static String verifyCodeImageToBase64(byte[] bytes) {
        String base64Image = imageToBase64(bytes);
        return BASE64_IMAGE_HEADER + base64Image;
    }

    public static String imageToBase64(byte[] bytes) {
        if (Objects.nonNull(bytes)) {
            return encoder.encodeBuffer(bytes).trim();
        }
        return "";
    }
}
