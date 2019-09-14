package com.mytest.register.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 13:59
 */
public class EncryptionUtils {
    /**
     *
     * @param rawStr
     * @return md5(rawStr)
     */
    public static String getMd5(String rawStr) {
        return DigestUtils.md5Hex(null == rawStr ? StringUtils.EMPTY : rawStr);
    }
}
