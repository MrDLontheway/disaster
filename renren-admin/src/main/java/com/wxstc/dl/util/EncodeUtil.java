package com.wxstc.dl.util;

import java.io.UnsupportedEncodingException;

public class EncodeUtil {
    public static String toUtf8(String s){
        try {
            byte[] bytes = s.getBytes("ISO-8859-1");
            String str2 = new String(bytes,"UTF-8");
            return str2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
