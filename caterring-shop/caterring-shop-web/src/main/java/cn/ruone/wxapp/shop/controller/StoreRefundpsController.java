package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.service.WxminiappService;
import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

import static org.hswebframework.web.controller.message.ResponseMessage.ok;

@RestController
@Api(tags = "退款",value ="数据库接口" )
public class StoreRefundpsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreOrderinfoService storeOrderinfoService;
    private StoreRefundpsService storeRefundpsService;
    private StoreRefundpicService storeRefundpicService;
    private StoreWalletrcdService storeWalletrcdService;
    private StoreMemberinfoService storeMemberinfoService;
    private WxminiappService wxminiappService;

    @Autowired
    public void setWxminiappService(WxminiappService wxminiappService){
        this.wxminiappService = wxminiappService;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService) {
        this.storeMemberinfoService = storeMemberinfoService;
    }

    @Autowired
    public void setStoreWalletrcdService(StoreWalletrcdService storeWalletrcdService) {
        this.storeWalletrcdService = storeWalletrcdService;
    }

    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService) {
        this.storeOrderinfoService = storeOrderinfoService;
    }

    @Autowired
    public void setStoreRefundpsService(StoreRefundpsService storeRefundpsService) {
        this.storeRefundpsService = storeRefundpsService;
    }

    @Autowired
    public void setStoreRefundpicService(StoreRefundpicService storeRefundpicService) {
        this.storeRefundpicService = storeRefundpicService;
    }


    @RequestMapping(value = "/app/index/Appsubmission", method = RequestMethod.GET)
    @ApiOperation(value = "退款——发起退款申请", notes = "修改")
    @ApiImplicitParams({
    })
    public String[] appsubmission(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        String userid = request.getParameter("userid");
        String munt = request.getParameter("mrefundamount");//退款金额
        String orderid = request.getParameter("order_id");  //订单编号
        String tkreason = request.getParameter("tk_reason");//退款原因
        String tknr = request.getParameter("tknr");//退款说明
        String image = request.getParameter("images");
        String phone = request.getParameter("phone");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(munt)||!StoresaUtls.blutls(tkreason)||!StoresaUtls.blutls(tknr)||!StoresaUtls.blutls(phone)||!StoresaUtls.blutls(orderid)) {
            log.error("数据错误—/app/index/Appsubmission");
            String[] ssion = {"2"};
            return ssion;
    }
        StoreRefundps storeRefundps = new StoreRefundps();
        storeRefundps.setOrderid(orderid);
        storeRefundps.setBizid(bizid);
        storeRefundps.setReason(tkreason);
        storeRefundps.setMoney(Float.valueOf(munt));
        storeRefundps.setNotes(tknr);
        storeRefundps.setTel(phone);
        storeRefundps.setApplytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeRefundps.setStatus("1");// 1--退款申请，2---商家同意退款，3---退款完成， 4---商家拒绝退款， 5---客户取消退款，6-退款关闭
        storeRefundpsService.insert(storeRefundps);

        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        storeOrderinfo.setStatus("5");
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);

        if (StoresaUtls.blutls(image)) {
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid", orderid);
            List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity1);
            String picid = storeRefundpsList.get(0).getId();
            String[] strarr = image.split(",");//根据“，”转换成String数组
            StoreRefundpic storeRefundpic = new StoreRefundpic();
            for (int i = 0; i < strarr.length; i++) {
                String imgs = strarr[i];
                storeRefundpic.setRefundid(picid);
                storeRefundpic.setImage(imgs);
                storeRefundpic.setSequ(String.valueOf(i + 1));
                storeRefundpicService.insert(storeRefundpic);
            }
        }
        String[] ssion = {"1"};
        return ssion;
    }
    //退款进度
    @RequestMapping(value = "/app/index/Refschedule",method = RequestMethod.GET)
    @ApiOperation(value = "退款——退款进度",notes = "查询")
    @ApiImplicitParams({
    })
    public HashMap refschedule(HttpServletRequest request,HttpServletResponse response){
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)) {
            log.error("数据错误—/app/index/Refschedule");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid",orderid);
        List<StoreRefundps> storeRefundps = storeRefundpsService.select(queryParamEntity);
        if (storeRefundps.size() != 1){
            log.error("数据错误—/app/index/Refschedule——没有查询到该订单");
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Refundamount",storeRefundps.get(0).getMoney()); //退款金额
        hashMap.put("status",storeRefundps.get(0).getStatus());// 1--退款申请，2---商家同意退款，3---退款完成， 4---商家拒绝退款， 5---客户取消退款，6-退款关闭
        hashMap.put("Timesubmission",storeRefundps.get(0).getApplytime());//提交申请时间
        if (storeRefundps.get(0).getStatus().equals("3")) {
            hashMap.put("Completiontime", storeRefundps.get(0).getCompletetime());//退款完成时间
        }
        return hashMap;
    }
    //商家同意退款——钱包支付
    @RequestMapping(value = "/app/index/TkTg",method = RequestMethod.GET)
    @ApiOperation(value = "退款——同意退款",notes = "修改")
    @ApiImplicitParams({
    })
    public String[]  TkTgupdate(HttpServletRequest request,HttpServletResponse response) {
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)) {
            log.error("数据错误——/app/index/TkTg—处理");
            String[] arlist = {"2"};
            return arlist;
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid", orderid);
        List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity);
        if (!storeRefundpsList.isEmpty() && storeRefundpsList.size() ==1) {
            String funid = storeRefundpsList.get(0).getId();
            float money = storeRefundpsList.get(0).getMoney();
            String orderno = storeOrderinfo.getOrderno();
            String patype = storeOrderinfo.getPaytype();
            String userid = storeOrderinfo.getUserid();
            String bizId = storeOrderinfo.getBizid();
            String orderNo = storeOrderinfo.getOrderno();
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
            float wallet = storeMemberinfo.getWallet();
            StoreRefundps storeRefundps = storeRefundpsService.selectByPk(funid);
            if (patype.equals("3")) {//钱包支付
                //1.商家同意退款金额与用户账号所余金额相加，并跟新到用户表中
                storeMemberinfo.setWallet(wallet + money);
                storeMemberinfoService.updateByPk(userid, storeMemberinfo);
                //2.将订单表里状态改为8.交易关闭，
                storeOrderinfo.setStatus("11");
                storeOrderinfo.setIncome(0);
                storeOrderinfo.setLastrsn("4");
                storeOrderinfo.setFinishtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeOrderinfoService.updateByPk(orderid, storeOrderinfo);
                storeRefundps.setStatus("3");
                storeRefundps.setRefundmoney(Float.valueOf(money));
                storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeRefundps.setCompletetime(Timestamp.valueOf(StoreDateutil.getStringDate()));//退款完成时间
                storeRefundpsService.updateByPk(funid, storeRefundps);
                //钱包退款——添加记录
                String Judge = "5";
                storeWalletrcdService.Walletrefund(userid,orderno, Float.valueOf(money),Judge);
            }
            if (patype.equals("1")) {//微信支付
                int totalFee = (int) (money * 100);
                int refundFee = (int) (money * 100);
                boolean refund = wxminiappService.applyRefund(bizId, orderNo, totalFee, refundFee);
                if (refund) {
                    storeRefundps.setStatus("3");
                    storeRefundps.setRefundmoney(money);
                    storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                    storeRefundpsService.updateByPk(funid, storeRefundps);
                    storeOrderinfo.setStatus("11");
                    storeOrderinfo.setLastrsn("4");
                    storeOrderinfoService.updateByPk(orderid, storeOrderinfo);
                }
            }
            String[] arlist = {"1"};
            return arlist;
        }
        String[] arlist = {"2"};
        return arlist;
    }

//    商家拒绝退款
    @RequestMapping(value = "/app/index/JjTk",method = RequestMethod.GET)
    @ApiOperation(value = "退款——拒绝退款",notes = "修改")
    @ApiImplicitParams({
    })
    public String[]  updateJjTk(HttpServletRequest request,HttpServletResponse response) {
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)) {
            log.error("数据错误——/app/index/JjTk—处理");
            String[] arlist = {"2"};
            return arlist;
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid", orderid);
        List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity);
        if (!storeRefundpsList.isEmpty()) {
            String funid = storeRefundpsList.get(0).getId();
            StoreRefundps storeRefundps = storeRefundpsService.selectByPk(funid);
            storeRefundps.setStatus("4");
            storeRefundps.setOpsnotes("商家拒绝退款");
            storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));//商家处理时间
            storeRefundpsService.updateByPk(funid, storeRefundps);
            storeOrderinfo.setStatus("3");
            storeOrderinfo.setLastrsn("3");
            storeOrderinfoService.updateByPk(orderid, storeOrderinfo);
            String[] arlist = {"1"};
            return arlist;
        }
        String[] arlist = {"2"};
        return arlist;
    }

}
