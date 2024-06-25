package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.service.WxminiappService;
import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(tags = "充值中心",value = "数据库操作")
public class StoreWalletrcdController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreMemberinfoService storeMemberinfoService;
    private StoreWalletrcdService storeWalletrcdService;
    private WxminiappService wxminiappService;
    private StoreOrderinfoService storeOrderinfoService;
    private StoreInfoService storeInfoService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private StoreTakeoutodrstatService storeTakeoutodrstatService;
    @Autowired
    public void setStoreTakeoutodrstatService(StoreTakeoutodrstatService storeTakeoutodrstatService){
        this.storeTakeoutodrstatService = storeTakeoutodrstatService;
    }
    @Autowired
    public void  setStoreOrdergoodsService(StoreOrdergoodsService storeOrdergoodsService){
        this.storeOrdergoodsService = storeOrdergoodsService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreWalletrcdService(StoreWalletrcdService storeWalletrcdService){
        this.storeWalletrcdService = storeWalletrcdService;
    }
    @Autowired
    public void setWxminiappService(WxminiappService wxminiappService){
        this.wxminiappService = wxminiappService;
    }

    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—StoreWalletrcdController");
            return null;
        }
        return storeInfo;
    }

    public StoreOrderinfo booleansorderinfo(String orderid,String bizid,String userid){
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        if (storeOrderinfo == null || !storeOrderinfo.getUserid().equals(userid)|| !storeOrderinfo.getBizid().equals(bizid) || !storeOrderinfo.getClientsts().equals("1")){
            log.error("非法订单—StoreWalletrcdController");
            return null;
        }
        return storeOrderinfo;
    }
    @RequestMapping(value = "/app/index/AddCzorder", method = RequestMethod.GET)
    @ApiOperation(value = "充值中心",notes = "增加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String AddCzorder(HttpServletRequest request, HttpServletResponse response){
        String  bizid = request.getParameter("i");
        String  shopid  = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizid,shopid))==null){ return null;}
        String userid = request.getParameter("user_id");
        String money = request.getParameter("money");
        String wallet = money.replaceAll(" ", "");//去掉所有空格
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(wallet)){
            log.error("数据错误—/app/index/AddCzorder");
            return null;
        }
        StoreWalletrcd storeWalletrcd = new StoreWalletrcd();
        storeWalletrcd.setUserid(userid);
        storeWalletrcd.setInputtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeWalletrcd.setStatus("3");
        String numner = StoreDateutil.getnumber();
        storeWalletrcd.setOrderno(numner);
        storeWalletrcd.setDeposittype("2");
        storeWalletrcd.setDescrp("微信充值");
        storeWalletrcd.setMoney(Float.valueOf(wallet));
        storeWalletrcdService.insert(storeWalletrcd);
        return numner;

    }

    @RequestMapping(value = "/app/index/pay", method = RequestMethod.GET)
    @ApiOperation(value = "充值中心——充值",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap pay(HttpServletRequest request, HttpServletResponse response){
        String bizId = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizId,shopid))==null){ return null;}
        String openId = request.getParameter("openid");
        String orderid = request.getParameter("order_id");
        String productId = "";
        if (!StoresaUtls.blutls(bizId)||!StoresaUtls.blutls(openId)||!StoresaUtls.blutls(orderid)){
            response.setStatus(400);
            log.error("数据错误—/app/index/pay",openId,orderid);
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("orderno",orderid);
        List<StoreWalletrcd> storeWalletrcdList = storeWalletrcdService.select(queryParamEntity);
        if (storeWalletrcdList.size() != 1){
            log.error("数据错误");
            return null;
        }
        String body = storeWalletrcdList.get(0).getDescrp();
        float money  = storeWalletrcdList.get(0).getMoney();
        Integer d =100;
        Integer totalFee = (int)(money*d);
        String outTradeno =  storeWalletrcdList.get(0).getOrderno();
        String limitPay = "no_credit";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String startTime = dateFormat.format(new Date()); // new Date()为获取当前系统时间
        long curren = System.currentTimeMillis();
        curren += 60 * 60 * 1000;//获取一个小时后的
        Date da = new Date(curren);
        String expireTime =dateFormat.format(da);

        Map<String, String> map = wxminiappService.wxPaymentapi(bizId, openId, body, totalFee, outTradeno,limitPay, productId, startTime, expireTime);

        HashMap hashMap = new HashMap();
        Map OpenidMap =  wxminiappService.wxPaymentapi(bizId, openId, body, totalFee, outTradeno,limitPay, productId, startTime, expireTime);
        hashMap.put("timeStamp", OpenidMap.get("timeStamp"));//通过K值获取V值
        hashMap.put("nonceStr",OpenidMap.get("nonceStr"));
        hashMap.put("package",OpenidMap.get("package"));
        hashMap.put("signType","MD5");
        hashMap.put("paySign", OpenidMap.get("paySign"));
        return hashMap;
    }


    @RequestMapping(value = "/app/index/paystate", method = RequestMethod.GET)
    @ApiOperation(value = "充值状态",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] paystate(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizid,shopid))==null){ return null;}
        String userid = request.getParameter("user_id");
        String orderid = request.getParameter("order_id");
        String status = request.getParameter("status");
        String[] success = {"1"};
        String[] fail = {"2"};
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(orderid)||!StoresaUtls.blutls(status)){
            log.error("数据错误—/app/index/paystate",userid,orderid,status);
            return fail;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("userid",userid).and("orderno",orderid);
        List<StoreWalletrcd> storeWalletrcdList = storeWalletrcdService.select(queryParamEntity);
        if (!storeWalletrcdList.isEmpty()) {
            String id = storeWalletrcdList.get(0).getId();
            StoreWalletrcd storeWalletrcd = storeWalletrcdService.selectByPk(id);
            float money = storeWalletrcdList.get(0).getMoney();
            if (status.equals("2")) {
                storeWalletrcd.setStatus("2");
                storeWalletrcd.setDescrp("微信充值失败");
                storeWalletrcd.setMoney(money);
                storeWalletrcdService.updateByPk(id, storeWalletrcd);
            }
            if (status.equals("1")) {
                storeWalletrcd.setStatus("1");
                storeWalletrcd.setMoney(+money);
                storeWalletrcdService.updateByPk(id, storeWalletrcd);
                StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
                float wallets = storeMemberinfo.getWallet();
                storeMemberinfo.setWallet(money + wallets);
                storeMemberinfoService.updateByPk(userid, storeMemberinfo);
            }
        }
        return success;
    }

    @RequestMapping(value = "/app/index/Qbmx", method = RequestMethod.GET)
    @ApiOperation(value = "交易明细——余额",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> Qbmx(HttpServletRequest request, HttpServletResponse response){

        String userid = request.getParameter("user_id");
        String mxtype = request.getParameter("mx_type");
//        String page = request.getParameter("page");
//        String pagesize = request.getParameter("pagesize");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(mxtype)){
            log.error("数据错误—/app/index/Qbmx",userid);
            return null;
        }
        QueryParamEntity queryParamEntity = new  QueryParamEntity().and("userid",userid);
        if (!mxtype.equals("1")){
            queryParamEntity.and("deposittype",mxtype);
        }
//        queryParamEntity.setPageIndex(Integer.valueOf(page));//当前页数从零开始
//        queryParamEntity.setPageSize(Integer.valueOf(pagesize));//每页数据最大个数
        queryParamEntity.setPaging(false);
        List<StoreWalletrcd> storeWalletrcdList = storeWalletrcdService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storeWalletrcdList.isEmpty()) {
            for (StoreWalletrcd storeWalletrcd : storeWalletrcdList) {
                HashMap hashMap = new HashMap();
                hashMap.put("mx_type", Integer.valueOf(storeWalletrcd.getDeposittype()));//明细类型 （1全部  2充值  3支付成功  4积分兑换  5商家退款）
                hashMap.put("note", storeWalletrcd.getDescrp());//记录
                hashMap.put("time", storeWalletrcd.getInputtime()); //时间
                hashMap.put("id", storeWalletrcd.getId()); //时间
                if (storeWalletrcd.getMoney()>0) {
                    hashMap.put("type", 1);  //1.“+”增加（获得的钱数） 2.“-”减去（消费的钱数）
                }
                if (storeWalletrcd.getMoney()<0) {
                    hashMap.put("type", 2);  //1.“+”增加（获得的钱数） 2.“-”减去（消费的钱数）
                }
                hashMap.put("money", Math.abs(storeWalletrcd.getMoney())); //钱数
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    @RequestMapping(value = "/app/index/payment", method = RequestMethod.GET)
    @ApiOperation(value = "支付",notes = "支付")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)

    })
    public HashMap payment(HttpServletRequest request, HttpServletResponse response){
        String bizId = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizId,shopid))==null){ return null;}
        String openId = request.getParameter("openid");
        String orderid = request.getParameter("order_id");
        String money = request.getParameter("money");
        String productId = "";
        if (!StoresaUtls.blutls(bizId)||!StoresaUtls.blutls(openId)||!StoresaUtls.blutls(orderid)||!StoresaUtls.blutls(money)){
            response.setStatus(400);
            log.error("数据错误—/app/index/payment",openId,orderid,money);
            System.out.print(orderid+"\t"+openId+"\t"+money);
            return null;
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        String body = "产品";
        Integer d =100;
        Integer totalFee = (int)(d*Float.valueOf(money));
        String limitPay = "no_credit";
        String outTradeno =  storeOrderinfo.getOrderno();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String startTime = dateFormat.format(new Date()); // new Date()为获取当前系统时间
        long curren = System.currentTimeMillis();
        curren += 60 * 60 * 1000;//获取一个小时后的
        Date da = new Date(curren);
        String expireTime =dateFormat.format(da);

        Map<String, String> map = wxminiappService.wxPaymentapi(bizId, openId, body, totalFee, outTradeno,limitPay, productId, startTime, expireTime);
        Map OpenidMap =  wxminiappService.wxPaymentapi(bizId, openId, body, totalFee, outTradeno,limitPay, productId, startTime, expireTime);
        HashMap hashMap = new HashMap();
        hashMap.put("timeStamp", OpenidMap.get("timeStamp"));//通过K值获取V值
        hashMap.put("nonceStr",OpenidMap.get("nonceStr"));
        hashMap.put("package",OpenidMap.get("package"));
        hashMap.put("signType","MD5");
        hashMap.put("paySign", OpenidMap.get("paySign"));
        if(storeOrderinfo.getOdtype().equals("3")||storeOrderinfo.getOdtype().equals("4")) {
            storeOrderinfo.setWxpay(Float.valueOf(money));
            storeOrderinfoService.updateByPk(orderid, storeOrderinfo);
        }
        return hashMap;
    }


    @RequestMapping(value = "/app/index/successpay", method = RequestMethod.GET)
    @ApiOperation(value = "支付——微信",notes = "微信支付")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)

    })
    public String[] successpay(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizid,shopid))==null){ return null;}
        String status = request.getParameter("status");
        String userid = request.getParameter("user_id");
        String orderid = request.getParameter("order_id");
        String paytype = request.getParameter("pay_type");
        if (!StoresaUtls.blutls(status)||!StoresaUtls.blutls(status)||!StoresaUtls.blutls(orderid)||!StoresaUtls.blutls(paytype)){
            response.setStatus(400);
            log.error("数据错误—/app/index/successpay",status,userid,orderid,paytype);
            return null;

        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        if(storeOrderinfo == null ||!storeOrderinfo.getBizid().equals(bizid)){
            log.error("非法参数—/app/index/successpay",status,userid,orderid,paytype);
            return null;
        }
        if (status.equals("1")) {
            if (storeOrderinfo.getOdtype().equals("1")||storeOrderinfo.getOdtype().equals("2")) {
                if (!StoresaUtls.blutls(storeOrderinfo.getPaytype())) {
                    storeOrderinfo.setPaytype(paytype);//支付方式
                }
                storeOrderinfo.setPaytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                if (storeInfo.getAutorecv().equals("1")) {//1--自动接单， 2--手动接单
                    if (storeOrderinfo.getOdtype().equals("1")) {
                        storeOrderinfo.setStatus("3");
                    }
                    if (storeOrderinfo.getOdtype().equals("2")) {
                        storeOrderinfo.setStatus("4");
                    }
                }
                if (storeInfo.getAutorecv().equals("2")) {
                    storeOrderinfo.setStatus("2");//支付成功后等待商家接单
                }
            }
            //在店消费，确认订单商品状态
            if(storeOrderinfo.getOdtype().equals("3")||storeOrderinfo.getOdtype().equals("4")) {
                storeOrderinfo.setStatus("12");
                storeOrderinfo.setPaytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
            }

            storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        }

        if (status.equals("2")){
            storeOrderinfo.setStatus("1");
            if(storeOrderinfo.getOdtype().equals("3")||storeOrderinfo.getOdtype().equals("4")) {
                storeOrderinfo.setWxpay(0);
            }
            storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        }
        String[] success={status};
        return success;

    }

    @RequestMapping(value = "/app/index/sucwallectpay", method = RequestMethod.GET)
    @ApiOperation(value = "支付——钱包",notes = "钱包支付")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap wallectpay(HttpServletRequest request,HttpServletResponse response){
        String bizId = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizId,shopid))==null){ return null;}
        String userid = request.getParameter("user_id");
        String orderid = request.getParameter("order_id");
        String paytype = request.getParameter("pay_type");
        String money  = request.getParameter("money");
        if (!StoresaUtls.blutls(orderid)|| !StoresaUtls.blutls(userid)|| !StoresaUtls.blutls(orderid)|| !StoresaUtls.blutls(paytype)||!StoresaUtls.blutls(money)){
            log.error("数据错误—/app/index/sucwallectpay",userid,orderid,paytype);
            return null;
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
            if (storeOrderinfo.getOdtype().equals("1")||storeOrderinfo.getOdtype().equals("2")) {
                storeOrderinfo.setStatus("2");//支付成功后等待商家接单
                if (!StoresaUtls.blutls(storeOrderinfo.getPaytype())) {
                    storeOrderinfo.setPaytype(paytype);//支付方式
                }
                storeOrderinfo.setPaytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                if (storeInfo.getAutorecv().equals("1")) {//1--自动接单， 2--手动接单
                    if (storeOrderinfo.getOdtype().equals("1")) {
                        storeOrderinfo.setStatus("3");
                    }
                    if (storeOrderinfo.getOdtype().equals("2")) {
                        storeOrderinfo.setStatus("4");
                    }
                    //确认菜单商品
                    storeOrdergoodsService.confirmdoods(orderid);
                }
                if (storeInfo.getAutorecv().equals("2")) {//1--自动接单， 2--手动接单
                    storeOrderinfo.setStatus("2");//支付成功后等待商家接单
                }
                //钱包支付——添加记录
                storeWalletrcdService.Walletpayment(userid,storeOrderinfo.getOrderno(),storeOrderinfo.getIncome());
            }
        if(storeOrderinfo.getOdtype().equals("3")||storeOrderinfo.getOdtype().equals("4")) {
            storeOrderinfo.setStatus("12");
            storeOrderinfo.setWalletpay(Float.valueOf(money));
            storeOrderinfo.setPaytime(Timestamp.valueOf(StoreDateutil.getStringDate()));

        }
            storeOrderinfoService.updateByPk(orderid,storeOrderinfo);

            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
            storeMemberinfo.setWallet(storeMemberinfo.getWallet() - storeOrderinfo.getIncome());
            storeMemberinfoService.updateByPk(userid,storeMemberinfo);



            HashMap hashMap = new HashMap();
            hashMap.put("status","1");
            hashMap.put("wallectmoney",storeOrderinfo.getIncome());
            return hashMap;
    }

    @RequestMapping(value = "/app/index/wallectmoney", method = RequestMethod.GET)
    @ApiOperation(value = "支付——钱包——所余现金",notes = "钱包支付")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap wallectmoney(HttpServletRequest request,HttpServletResponse response){
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(userid)){
            log.error("数据错误—/app/index/wallectmoney",userid);
            return null;
        }
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
        HashMap hashMap = new HashMap();
        hashMap.put("wallectmoney",storeMemberinfo.getWallet());
        return hashMap ;
    }

    @RequestMapping(value = "/app/index/applyrefund", method = RequestMethod.GET)
    @ApiOperation(value = "退款申请页面",notes = "退款申请页面——查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap applyrefund(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo  = booleanstoreinfo(bizid,shopid))==null){ return null;}
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误——/app/index/applyrefund",orderid);
            return null;
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        if (storeOrderinfo == null || !storeOrderinfo.getBizid().equals(bizid)){
            log.error("非法参数——/app/index/applyrefund",orderid);
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("mrefundamount",storeOrderinfo.getIncome());
        hashMap.put("mbuytime",storeOrderinfo.getPaytime());
        hashMap.put("mordernumber",storeOrderinfo.getOrderno());
        return hashMap;
    }
//    /app/index/jymxDetails
    @RequestMapping(value = "/app/index/jymxDetails", method = RequestMethod.GET)
    @ApiOperation(value = "交易明细详情",notes = "交易明细详情——查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap jymxDetails(HttpServletRequest request,HttpServletResponse response){
        String id  = request.getParameter("id");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——/app/index/jymxDetails");
            return null;
        }
        StoreWalletrcd storeWalletrcd = storeWalletrcdService.selectByPk(id);
        HashMap hashMap = new HashMap();
        hashMap.put("id",storeWalletrcd.getId());
        hashMap.put("time",storeWalletrcd.getInputtime());
        hashMap.put("type",Integer.valueOf(storeWalletrcd.getStatus()));
        hashMap.put("money",storeWalletrcd.getMoney());
        hashMap.put("order_type",Integer.valueOf(storeWalletrcd.getDeposittype()));
        hashMap.put("order_num",storeWalletrcd.getOrderno());
        return hashMap;
    }
}
