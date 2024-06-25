package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.StoreCoupons;
import cn.ruone.wxapp.shop.api.entity.StoreInfo;
import cn.ruone.wxapp.shop.api.entity.StoreUsercoupons;
import cn.ruone.wxapp.shop.api.service.StoreCouponsService;
import cn.ruone.wxapp.shop.api.service.StoreInfoService;
import cn.ruone.wxapp.shop.api.service.StoreUsercouponsService;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import io.swagger.annotations.*;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@Api(tags = "优惠卷操作",value = "数据操作")
public class StoreCouponsController {
private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreCouponsService storeCouponsService;
    private StoreInfoService storeInfoService;
    private StoreUsercouponsService storeUsercouponsService;
    @Autowired
    public void setStoreUsercouponsService(StoreUsercouponsService storeUsercouponsService){
        this.storeUsercouponsService = storeUsercouponsService;
    }
    @Autowired
    public void setStoreCouponsService(StoreCouponsService storeCouponsService){
        this.storeCouponsService = storeCouponsService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @RequestMapping(value = "/app/index/Coupons",method = RequestMethod.GET)
    @ApiOperation(value = "优惠卷信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> select(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreCoupons storeCoupons : storeCouponsList){
            int a = Integer.valueOf(storeCoupons.getTotal());//1000
            int b = Integer.valueOf(storeCoupons.getFree());//800
            int c = a-b;
            HashMap hashMap = new HashMap();
            hashMap.put("name",storeCoupons.getName());
            hashMap.put("start_time",storeCoupons.getBegintime());
            hashMap.put("end_time",storeCoupons.getEndtime());
            hashMap.put("reduce",storeCoupons.getSubtract());
            hashMap.put("full",storeCoupons.getStartline());
            hashMap.put("store_name",storeInfo.getName());
            hashMap.put("instruction",storeCoupons.getGuider());
            hashMap.put("type",storeCoupons.getCptype());
            hashMap.put("sysl",c);
            hashMap.put("state",storeCoupons.getStatus());
            hashMap.put("coupon_id",storeCoupons.getId());
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    @RequestMapping(value = "/app/index/getCoupons",method = RequestMethod.GET)
    @ApiOperation(value = "优惠卷信息——",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> getCouponsselect(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(userid)) {
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid", bizid);
        String date = StoreDateutil.getStringDate();
        queryParamEntity.where("begintime","lte",date);
        queryParamEntity.where("endtime","gte",date);
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
        if (!storeCouponsList.isEmpty()) {
            for (StoreCoupons storeCoupons : storeCouponsList) {
                HashMap hashMap = new HashMap();
                if (storeCoupons.getCptype().equals("1") || storeCoupons.getCptype().equals("4")) {
                    hashMap.put("type", Integer.valueOf(storeCoupons.getCptype()));//类型（1优惠券  4红包）
                    hashMap.put("id", Integer.valueOf(storeCoupons.getId()));//优惠券id
                    hashMap.put("name", storeCoupons.getName());//优惠券名称
                    hashMap.put("start_time", String.valueOf(storeCoupons.getBegintime()).substring(0, 10));//优惠券开始时间
                    hashMap.put("end_time", String.valueOf(storeCoupons.getEndtime()).substring(0, 10));//优惠券有效期至
                    hashMap.put("reduce", storeCoupons.getSubtract());//优惠券减少xxx元；
                    hashMap.put("full", storeCoupons.getStartline());//优惠券满xxx元可用
                    hashMap.put("store_name", storeInfo.getName());//店铺名称
                    hashMap.put("instruction", storeCoupons.getGuider());//优惠券介绍
                    QueryParamEntity queryParamEntity1 = new QueryParamEntity();
                    queryParamEntity1.and("userid", userid).and("couponid", storeCoupons.getId());
                    List<StoreUsercoupons> storeUsercouponsList = storeUsercouponsService.select(queryParamEntity1);
                    if (storeUsercouponsList.isEmpty()) {
                        hashMap.put("receive", 1);//是否领取（1未领取   2已领取)
                    } else {
                        hashMap.put("receive", 2);//是否领取（1未领取   2已领取)
                    }
                    arrayList.add(hashMap);
                }
            }
        }
        return arrayList;
    }

    @RequestMapping(value = "/app/index/isReceive",method = RequestMethod.GET)
    @ApiOperation(value = "优惠卷信息——领取优惠卷",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] isReceive(HttpServletRequest request, HttpServletResponse response) {
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String id = request.getParameter("id");
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(id)||!StoresaUtls.blutls(userid)){
            log.error("数据错误——/app/index/isReceive");
            return fail;
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons == null || !storeCoupons.getBizid().equals(bizid)){
            log.error("非法参数——/app/index/isReceive");
            return fail;
        }
        storeCoupons.setFree(String.valueOf(Integer.valueOf(storeCoupons.getFree())-1));
        storeCouponsService.updateByPk(id,storeCoupons);
        StoreUsercoupons storeUsercoupons = new StoreUsercoupons();
        storeUsercoupons.setCouponid(id);
        storeUsercoupons.setUserid(userid);
        storeUsercoupons.setNum("1");
        storeUsercouponsService.insert(storeUsercoupons);
        return success;
    }

    @RequestMapping(value = "/app/index/usedCoupon",method = RequestMethod.GET)
    @ApiOperation(value = "优惠卷信息——已使用的优惠券或者红包",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] usedCoupon(HttpServletRequest request,HttpServletResponse response){
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String hyid = request.getParameter("hyid");
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(userid)){
            log.error("数据错误——/app/index/usedCoupon");
            return fail;

        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("userid",userid).and("couponid",hyid);
        List<StoreUsercoupons> storeUsercouponsList = storeUsercouponsService.select(queryParamEntity);
        if (!storeUsercouponsList.isEmpty() && storeUsercouponsList.size() ==1) {
            String couponid =  storeUsercouponsList.get(0).getCouponid();
            StoreCoupons storeCoupons = storeCouponsService.selectByPk(couponid);
            if (storeCoupons == null || !storeCoupons.getBizid().equals(bizid)){
                log.error("非法参数——/app/index/usedCoupon");
                return fail;
            }
            String uscoupid = storeUsercouponsList.get(0).getId();
            StoreUsercoupons storeUsercoupons = storeUsercouponsService.selectByPk(uscoupid);
            if (storeUsercoupons != null && storeUsercoupons.getUserid().equals(userid)) {
                storeUsercoupons.setNum("0");
                storeUsercouponsService.updateByPk(uscoupid, storeUsercoupons);
                return success;
            }
            return fail;
        }
        return fail;
    }
    @RequestMapping(value = "/app/index/Fullreduct", method = RequestMethod.GET)
    @ApiOperation(value = "商家信息——满减", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> Fullreductselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        ArrayList<Map<String,String>> arrayList = new  ArrayList<Map<String,String>>();
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid",bizid).and("cptype","2");
        String format = StoreDateutil.getStringDate();
        queryParamEntity.where("begintime","lte",format);// 表示2018年11月13日10点10分10秒及其以后的数据11
        queryParamEntity.where("endtime","gte",format);// 10点10分10 13
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        if (!storeCouponsList.isEmpty()) {
            for (StoreCoupons storeCoupons : storeCouponsList) {
                HashMap hashMap = new HashMap();
                hashMap.put("full",storeCoupons.getStartline());
                hashMap.put("reduction",storeCoupons.getSubtract());
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }
}
