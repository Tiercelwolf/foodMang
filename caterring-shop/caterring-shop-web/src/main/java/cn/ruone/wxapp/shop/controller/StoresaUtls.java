package cn.ruone.wxapp.shop.controller;

import org.apache.commons.lang.StringUtils;

public class StoresaUtls {
    public static boolean blutls(String str){
        if(StringUtils.isBlank(str) || "undefined".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
