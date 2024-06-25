package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreSQLoperation {
    private final Logger log = LoggerFactory.getLogger(this.getClass());//log日志
    public static boolean booleanstoreCoupons(String coupid,String bizid ,StoreCouponsService storeCouponsService){
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("id",coupid);
        String format = StoreDateutil.getStringDate();
        queryParamEntity.where("begintime","lte",format);// 表示2018年11月13日10点10分10秒及其以后的数据11
        queryParamEntity.where("endtime","gte",format);// 10点10分10 13
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        if (!storeCouponsList.isEmpty() && storeCouponsList.size() == 1 && storeCouponsList.get(0).getBizid().equals(bizid)) {
            return true;
        }
        return false;
    }
    public static StoreOrderinfo booleanstoreorderinfo(String orderid, String bizid, StoreOrderinfoService storeOrderinfoService){
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        if (storeOrderinfo == null || !storeOrderinfo.getBizid().equals(bizid)||!storeOrderinfo.getClientsts().equals("1")){
            return null;
        }
        return storeOrderinfo;
    }
    public  static  StoreInfo booleanstoreinfo(String bizid,String shopid,StoreInfoService storeInfoService){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            return null;
        }
        return storeInfo;
    }
    public static boolean booleangoods(String carinfo, String bizid, StoreGoodsService storeGoodsService){
        JSONArray carinfoJSONArray = JSONArray.fromObject(carinfo);
        for (int i = 0; i < carinfoJSONArray.size(); i++) {
            JSONObject carinfojob = carinfoJSONArray.getJSONObject(i);
            String goodid = (String) carinfojob.get("id");
            StoreGoods storeGoods = storeGoodsService.selectByPk(goodid);
            if (storeGoods == null && !storeGoods.getBizid().equals(bizid) && !String.valueOf(storeGoods.getOnsale()).equals("1")){
                return false;
            }
        }
        return true;
    }
    public static JSONArray booleanJSONArray(String json){
        JSONArray selectGoodsJSONArray = JSONArray.fromObject(json);
        if (selectGoodsJSONArray.size() != 0){
            return selectGoodsJSONArray;
        }
        return null;
    }
}
