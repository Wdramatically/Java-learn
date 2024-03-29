package com.imooc.oa.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {

    public static String md5Digest(String source) {
        return DigestUtils.md5Hex(source);
    }

    public static String md5Digest(String source, Integer salt) {
        char[] ca = source.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            ca[i] = (char) (ca[i] + salt);
        }
        String target = new String(ca);
        return Md5Utils.md5Digest(target);
    }
}
