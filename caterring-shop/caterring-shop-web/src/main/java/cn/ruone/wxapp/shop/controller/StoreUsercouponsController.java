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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "个人中心",value = "数据库操作")
public class StoreUsercouponsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreUsercouponsService storeUsercouponsService;
    private StoreCouponsService storeCouponsService;
    private StoreInfoService storeInfoService;
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

    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }
    @RequestMapping(value = "/app/index/MyCoupons",method = RequestMethod.GET)
    @ApiOperation(value = "个人中心优惠卷",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> select(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(userid)) {
            log.error("数据错误——/app/index/MyCoupons");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid", userid);
        queryParamEntity.and("num","1");
        List<StoreUsercoupons> storeUsercouponsList = storeUsercouponsService.select(queryParamEntity);
        ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
        for (StoreUsercoupons storeUsercoupons : storeUsercouponsList) {
            StoreCoupons storeCoupons = storeCouponsService.selectByPk(storeUsercoupons.getCouponid());
            QueryParamEntity queryParamEntity2 = QueryParamEntity.single("id", storeUsercoupons.getCouponid());
            String date = StoreDateutil.getStringDate();
            queryParamEntity2.where("begintime","lte",date);
            queryParamEntity2.where("endtime","gte",date);
            List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity2);
            if (!storeCouponsList.isEmpty()) {
                HashMap hashMap = new HashMap();
                hashMap.put("uscoupid", storeUsercoupons.getId());
                hashMap.put("type", Integer.valueOf(storeCoupons.getCptype()));//类型（1优惠券  4红包）
                hashMap.put("id", Integer.valueOf(storeCoupons.getId()));//优惠券id
                hashMap.put("name", storeCoupons.getName());//优惠券名称
                hashMap.put("start_time", String.valueOf(storeCoupons.getBegintime()).substring(0, 10));//优惠券开始时间
                hashMap.put("end_time", String.valueOf(storeCoupons.getEndtime()).substring(0, 10));//优惠券有效期至
                hashMap.put("reduce", storeCoupons.getSubtract());//优惠券减少xxx元；
                hashMap.put("full", storeCoupons.getStartline());//优惠券满xxx元可用
                hashMap.put("store_name", storeInfo.getName());//店铺名称
                hashMap.put("instruction", storeCoupons.getGuider());//优惠券介绍
                hashMap.put("receive", 2);//是否领取（1未领取   2已领取)
                hashMap.put("isUse", 1);//是否领取（1未使用    2已使用 )
                arrayList.add(hashMap);
            }
            }
        QueryParamEntity queryParamEntity1 = QueryParamEntity.single("bizid", bizid).and("cptype","2");
        String date2 = StoreDateutil.getStringDate();
        queryParamEntity1.where("begintime","lte",date2);
        queryParamEntity1.where("endtime","gte",date2);
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity1);
        for (StoreCoupons storeCoupons : storeCouponsList){
            HashMap hashMap1 = new HashMap();
            hashMap1.put("type", Integer.valueOf(storeCoupons.getCptype()));//类型（1优惠券  4红包）
            hashMap1.put("id", Integer.valueOf(storeCoupons.getId()));//优惠券id
            hashMap1.put("name", storeCoupons.getName());//优惠券名称
            hashMap1.put("start_time", String.valueOf(storeCoupons.getBegintime()).substring(0, 10));//优惠券开始时间
            hashMap1.put("end_time", String.valueOf(storeCoupons.getEndtime()).substring(0, 10));//优惠券有效期至
            hashMap1.put("reduce", storeCoupons.getSubtract());//优惠券减少xxx元；
            hashMap1.put("full", storeCoupons.getStartline());//优惠券满xxx元可用
            hashMap1.put("store_name", storeInfo.getName());//店铺名称
            hashMap1.put("instruction", storeCoupons.getGuider());//优惠券介绍
            hashMap1.put("receive", 2);//是否领取（1未领取   2已领取)
            hashMap1.put("isUse", 1);//是否领取（1未使用    2已使用 )
            arrayList.add(hashMap1);
        }
        return arrayList;
    }
}
