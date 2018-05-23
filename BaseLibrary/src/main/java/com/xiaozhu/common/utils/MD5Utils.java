package com.xiaozhu.common.utils;

import java.security.MessageDigest;

/**
 * @说明 MD5数据加密
 * @作者 liuyi
 * @时间 2018/4/10 11:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class MD5Utils {
    /**
     * 字符串加密 不带签名
     *
     * @param pstr 需要加密的字符串
     * @return MD5加密后的数据不带签名
     */
    public static String encryption(String pstr) {
        return encryption("", pstr);
    }

    /**
     * 字符串加密
     *
     * @param secretKey 签名
     * @param pstr      需要加密的字符串
     * @return MD5加密后的数据带签名
     */
    public static String encryption(String secretKey, String pstr) {
        String plainText = secretKey + pstr;
        try {
            byte[] btInput = plainText.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
