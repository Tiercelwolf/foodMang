package cn.ruone.wxapp.shop.impl.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StoreDateutil {

    /**
     * 获取系统时间
     * 转换为yyyy-MM-dd HH:mm:ss格式
     * @return
     */
    public static String getStringDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = format.format(new Date());
        return newDate;
    }
    /**
     * 获取系统时间
     * 转换为yyyyMMddHHmmss格式
     * @return
     */
    public static String getnumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String numberDate = format.format(new Date());
        return numberDate;
    }

    /**
     * 获取系统时间两个小时以后的时间
     * 转换为yyyyMMddHHmmss格式
     * @return
     */
    public static String getdateafter() {
        long curren = System.currentTimeMillis();
        curren += 2* 60 * 60 * 1000;
        Date da = new Date(curren);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datef= dateFormat.format(da);
        return datef;
    }
    /**
     * 获取四位随机数
     * 四位随机数
     * @return
     */
    public static String getrandom() {
        Random random = new Random();
        int sj = random.nextInt(9000) + 1000;//获取四位随机数
        return String.valueOf(sj);
    }

}
