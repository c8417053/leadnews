package com.heima.utils.common;

/**
 * 自定义的string工具类
 * @author: czx
 * @create: 2020-02-04 21:14
 */
public class HMStringUtils {

    /**
     * 将字符串的首字母转换为小写
     * ascii中 65-90 大写字母 97-122是小写字母
     * @param string
     * @return
     */
    public static String fistToLower(String string){
        char[] chars = string.toCharArray();
        if (chars[0] > 64 && chars[0]<91) {
            chars[0] +=32;
        }
        return String.valueOf(chars);
    }
}
