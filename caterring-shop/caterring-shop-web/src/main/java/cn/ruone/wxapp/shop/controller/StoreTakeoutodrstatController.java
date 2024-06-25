package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.StoreOrderinfo;
import cn.ruone.wxapp.shop.api.entity.StoreTakeoutodrstat;
import cn.ruone.wxapp.shop.api.service.StoreOrderinfoService;
import cn.ruone.wxapp.shop.api.service.StoreTakeoutodrstatService;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "商家入口---登陆",value = "数据从操作")
public class StoreTakeoutodrstatController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreTakeoutodrstatService storeTakeoutodrstatService;
    private StoreOrderinfoService storeOrderinfoService;
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreTakeoutodrstatService(StoreTakeoutodrstatService storeTakeoutodrstatService){
        this.storeTakeoutodrstatService = storeTakeoutodrstatService;
    }
    @RequestMapping(value = "/app/index/StoreLogin",method = RequestMethod.GET)
    @ApiOperation(value = "登陆",notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "i",value = "商家编号",required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap storeLogin(HttpServletRequest request, HttpServletResponse response){
        HashMap hashMap = new HashMap();
        hashMap.put("storeid","53");//管理员登录，店铺id
        return hashMap;
    }
    @RequestMapping(value = "/app/index/StoreStatistics",method = RequestMethod.GET)
    @ApiOperation(value = "数据统计",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap storeStatistics(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");

        //1.判断表里是否有当前日期时间，没有则添加
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = format.format(new Date()); // new Date()为获取当前系统时间
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",bizid).and("date",date);//与数据库的日期列进行比
//        List<StoreTakeoutodrstat> storeTakeoutodrstatList = storeTakeoutodrstatService.select(queryParamEntity);
//        if (storeTakeoutodrstatList.isEmpty()){
//            StoreTakeoutodrstat storeTakeoutodrstat = new StoreTakeoutodrstat();
//            storeTakeoutodrstat.setName("销量统计");
//            storeTakeoutodrstat.setDate(date);
//            storeTakeoutodrstatService.insert(storeTakeoutodrstat);
//
//
//        }
//        List<StoreTakeoutodrstat> storeTakeoutodrstatList1 = storeTakeoutodrstatService.select(queryParamEntity);
//        String id = storeTakeoutodrstatList1.get(0).getId();
//
//
//        QueryParamEntity queryorderinfo = new QueryParamEntity();
//        queryorderinfo.and("bizid",bizid);
//        QueryParamEntity queryParamEntitysub = new QueryParamEntity();
//        queryParamEntitysub.or("status","7").or("status","9");
//        queryorderinfo.nest().setTerms(queryParamEntitysub.getTerms());
//        queryorderinfo.where("starttim","gte",date+" "+"00:00:00");
//        queryorderinfo.where("starttime","lte",date+" "+"23:59:59");
//        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryorderinfo);
//        Integer odnum = storeOrderinfoList.size();//订单数量
//        float wxpaysum=0;//微信支付金额
//        float recvpay=0;//到付金额
//        float onsitepay=0;//到店消费金额
//        //1 微信支付，2 现金支付， 3 钱包支付 4 积分支付
//        for (StoreOrderinfo storeOrderinfo : storeOrderinfoList){
//            String paytype = storeOrderinfo.getPaytype();
//            Timestamp timestamp = storeOrderinfo.getFinishtime();
//            String time = String.valueOf(timestamp).substring(0,10);
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//            String dates = df.format(new Date()); // new Date()为获取当前系统时间
//            if (time.equals(dates)) {
//                if (paytype.equals("1")) {
//                    wxpaysum += storeOrderinfo.getIncome();
//                }
//                if (paytype.equals("2")) {
//                    recvpay += storeOrderinfo.getIncome();
//                }
//                if (paytype.equals("3")) {
//                    onsitepay += storeOrderinfo.getIncome();
//                }
//            }
//        }
//        StoreTakeoutodrstat storeTakeoutodrstat = new StoreTakeoutodrstat();
//        storeTakeoutodrstat.setName("销量统计");
//        storeTakeoutodrstat.setOdrnum(String.valueOf(odnum));
//        storeTakeoutodrstat.setIncome(wxpaysum+recvpay+onsitepay);
//        storeTakeoutodrstat.setWxpaysum(wxpaysum);
//        storeTakeoutodrstat.setRecvpay(recvpay);
//        storeTakeoutodrstat.setOnsitepay(onsitepay);
//        storeTakeoutodrstatService.updateByPk(id,storeTakeoutodrstat);

        List<StoreTakeoutodrstat> storeTakeoutodrstatList = storeTakeoutodrstatService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        if (!storeTakeoutodrstatList.isEmpty()&&storeTakeoutodrstatList.size()==1) {
            hashMap.put("wm_name", storeTakeoutodrstatList.get(0).getName());//标题，xxx订单
            hashMap.put("wmnum", storeTakeoutodrstatList.get(0).getOdrnum());//今日订单量
            hashMap.put("wm", storeTakeoutodrstatList.get(0).getIncome());//今日营业额
            hashMap.put("wxwmnum", storeTakeoutodrstatList.get(0).getWxpaysum());//微信支付
            hashMap.put("hdwmnum", storeTakeoutodrstatList.get(0).getRecvpay());//货到付款
            hashMap.put("ztwmnum", storeTakeoutodrstatList.get(0).getWalletpay());//到店自提
        }
        return hashMap;
    }


}
