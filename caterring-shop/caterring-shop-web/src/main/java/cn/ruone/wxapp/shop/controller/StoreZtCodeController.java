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

@RestController
@Api(tags = "核销取货",value = "数据库操作")
public class StoreZtCodeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreOrderinfoService storeOrderinfoService;
    private WxminiappService wxminiappService;
    private StoreOrdertablesService storeOrdertablesService;
    private StoreInfoService storeInfoService;
    private StoreTableinfoService storeTableinfoService;
    private StoreMemberinfoService storeMemberinfoService;
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreTableinfoService(StoreTableinfoService storeTableinfoService){
        this.storeTableinfoService = storeTableinfoService;
    }
    @Autowired
    public void  setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreOrdertablesService(StoreOrdertablesService storeOrdertablesService){
        this.storeOrdertablesService = storeOrdertablesService;
    }
    @Autowired
    public void setWxminiappService(WxminiappService wxminiappService){
        this.wxminiappService = wxminiappService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }

    @RequestMapping(value = "/app/index/ZtCode", method = RequestMethod.GET)
    @ApiOperation(value = "核销取货",notes = "编辑")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String ZtCode(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String bizId = request.getParameter("store_id");
        String orderId = request.getParameter("order_id");
        String userId = request.getParameter("user_id");
        if (!StoresaUtls.blutls(bizId)||!StoresaUtls.blutls(orderId)||!StoresaUtls.blutls(userId)){
            response.setStatus(400);
            log.error("数据错误—/app/index/ZtCode—getParameter");
            return null;
        }
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderId,bizid,storeOrderinfoService))==null){ return null;}
        String page = "zh_cjdianc/pages/sjzx/wmddxq";
        String randstr = StoreDateutil.getrandom();
//        public String getWxCode(String bizId, String orderId, String userId, String page, String randstr)
        String wxminiapp = wxminiappService.getWxCode( bizId, orderId, userId, page, randstr);
//        String OpenidMap =  wxminiappService.wxPaymentapi(bizId, orderId, userId, page, randstr));
        return wxminiapp;
    }

    @RequestMapping(value = "/app/index/sweepCode", method = RequestMethod.GET)
    @ApiOperation(value = "sweepCode",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap sweepCodeselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String tableid = request.getParameter("tableid");
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(tableid)||!StoresaUtls.blutls(userid)){
            log.error("数据错误—/app/index/sweepCode—getParameter");
            return null;
        }


        //查看和预约是否冲突
        QueryParamEntity queryParamEntity1 = new QueryParamEntity();
        queryParamEntity1.and("tableid",tableid);
        queryParamEntity1.where("starttime","let",StoreDateutil.getdateafter());
        List<StoreTableinfo> storeTableinfoList = storeTableinfoService.select(queryParamEntity1);
        if (!storeTableinfoList.isEmpty()){
            log.error("数据错误—/app/index/sweepCode—getParameter——此座被预定");
            return null;
        }
        StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(tableid);
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
        if (!storeTableinfo.getStatus().equals("1") && !storeTableinfo.getStatus().equals("2") ){
            QueryParamEntity queryParamEntitytables = new QueryParamEntity();
            queryParamEntitytables.and("tableid",tableid);
            String format = StoreDateutil.getStringDate();
            queryParamEntitytables.where("starttime","lte",format);// 表示2018年11月13日10点10分10秒及其以后的数据11
            queryParamEntitytables.where("endtime","gte",format);// 10点10分10 13
            List<StoreOrdertables> storeOrdertablesList = storeOrdertablesService.select(queryParamEntitytables);
            if (storeOrdertablesList.size()==1){
                String orderid = storeOrdertablesList.get(0).getOrderid();
                StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
                if (storeOrderinfo.getUserid().equals(userid)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("table_num",storeTableinfo.getTableno());
                    hashMap.put("order_id", storeOrdertablesList.get(0).getOrderid());
                    return hashMap;
                }
            }
            log.error("没有查询到该商家桌号——或者该餐桌正在使用中");
            return null;
        }
        StoreOrderinfo storeOrderinfo = new StoreOrderinfo();
        storeOrderinfo.setUserid(userid);
        storeOrderinfo.setStatus("2");
        storeOrderinfo.setOdtype("3");
        storeOrderinfo.setName(storeMemberinfo.getName());
        storeOrderinfo.setPhone(storeMemberinfo.getTelephone());
        storeOrderinfo.setBizid(storeTableinfo.getBizid());
        storeOrderinfo.setAddress(storeInfo.getAddress());

        storeOrderinfo.setClientsts("1");//客户删除 1--- 未删除， 2----已删除
        storeOrderinfo.setEntersts("1"); //商家删除： 1---未删除， 2---已删除
        String orderno = StoreDateutil.getnumber()+StoreDateutil.getrandom();
        storeOrderinfo.setOrderno(orderno);//日期加随机数组成编号
        storeOrderinfo.setPrepay("3");
        storeOrderinfo.setTabtype(storeTableinfo.getTabtype());
        storeOrderinfo.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeOrderinfoService.insert(storeOrderinfo);
        storeTableinfo.setStatus("2");
        storeTableinfoService.updateByPk(tableid,storeTableinfo);

        QueryParamEntity queryParamEntity = new  QueryParamEntity();
        queryParamEntity.and("orderno",orderno);
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        String orderid = storeOrderinfoList.get(0).getId();

        StoreOrdertables storeOrdertables = new StoreOrdertables();
        storeOrdertables.setOrderid(orderid);
        storeOrdertables.setTableid(tableid);
        storeOrdertables.setStarttime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeOrdertables.setEndtime(Timestamp.valueOf(StoreDateutil.getdateafter()));
        storeOrdertablesService.insert(storeOrdertables);
        HashMap hashMap = new HashMap();
        hashMap.put("table_num",storeTableinfo.getTableno());
        hashMap.put("order_id",orderid);
        return hashMap;
    }
}
