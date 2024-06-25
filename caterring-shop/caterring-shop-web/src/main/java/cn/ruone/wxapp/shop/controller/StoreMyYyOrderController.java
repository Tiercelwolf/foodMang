package cn.ruone.wxapp.shop.controller;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "我的预约",value = "数据库操作")
public class StoreMyYyOrderController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreOrderinfoService storeOrderinfoService;
    private StoreGoodsService storeGoodsService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private StoreInfoService storeInfoService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreOrdertablesService storeOrdertablesService;
    @Autowired
    public void  setStoreOrdertablesService(StoreOrdertablesService storeOrdertablesService){
        this.storeOrdertablesService = storeOrdertablesService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }
    @Autowired
    public void setStoreOrdergoodsService(StoreOrdergoodsService storeOrdergoodsService){
        this.storeOrdergoodsService = storeOrdergoodsService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    //我的预约-订单列表-reload()
    @RequestMapping(value = "/app/index/MyYyOrder", method = RequestMethod.GET)
    @ApiOperation(value = "我的预约-订单列表-查询",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> MyYyOrder(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String userid = request.getParameter("user_id");
        String statu = request.getParameter("state");
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(statu)){
            log.error("数据错误——/app/index/MyYyOrder");
            return arrayList;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("userid",userid).and("status",statu).and("clientsts","1").and("odtype","4");
        if (statu.equals("8")){
            queryParamEntity.and("lastrsn","2");
        }
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);//根据用户编号，和订单状态进行查询
        //查询结果等于0，则返回一个空数组
        if (!storeOrderinfoList.isEmpty()) {
            for (StoreOrderinfo storeOrderinfo : storeOrderinfoList) {
                HashMap hashMap = new HashMap();
                HashMap orderhashMap = new HashMap();
                orderhashMap.put("id", storeOrderinfo.getId());
                orderhashMap.put("user_id", storeOrderinfo.getUserid());
                orderhashMap.put("order_num", storeOrderinfo.getOrderno());//订单编号
                orderhashMap.put("state", storeOrderinfo.getStatus());
                orderhashMap.put("time", storeOrderinfo.getCreatedtime());//下单F
                orderhashMap.put("pay_time", storeOrderinfo.getPaytime());
                orderhashMap.put("jd_time", storeOrderinfo.getAdmittime());
                orderhashMap.put("cancel_time", storeOrderinfo.getCanceltime());
                orderhashMap.put("complete_time", storeOrderinfo.getFinishtime());
                orderhashMap.put("money", storeOrderinfo.getIncome()); //实付
                orderhashMap.put("box_money", storeOrderinfo.getPackfee());
                orderhashMap.put("ps_money", storeOrderinfo.getDeliveryfee());
//                orderhashMap.put("mj_money", "0.00");
//                orderhashMap.put("xyh_money", "0.00");
                orderhashMap.put("tel", storeOrderinfo.getPhone());
                orderhashMap.put("name", storeOrderinfo.getName());
                orderhashMap.put("address", storeOrderinfo.getAddress());
                orderhashMap.put("type", storeOrderinfo.getStatus());
                orderhashMap.put("store_id", storeOrderinfo.getBizid());//、、、
                orderhashMap.put("note", storeOrderinfo.getNotes());
//                orderhashMap.put("jj_note", "");
//                orderhashMap.put("area", "");
//                String [] strs = storeInfo.getLocation().split("[,]");
//                orderhashMap.put("lat", strs[0]);
//                orderhashMap.put("lng", strs[1]);
//                orderhashMap.put("del", "2");
                orderhashMap.put("pay_type", storeOrderinfo.getPaytype());
//                orderhashMap.put("form_id", "");
//                orderhashMap.put("form_id2", "");
//                orderhashMap.put("code", "");
                orderhashMap.put("order_type", storeOrderinfo.getOdtype());
//                orderhashMap.put("delivery_time", "2018-08-23 07:00");
                orderhashMap.put("sex", storeOrderinfo.getPackfee());
//                orderhashMap.put("discount", "0.00");
                orderhashMap.put("tableware", storeOrderinfo.getDishware());
//                orderhashMap.put("dd_info", "");
                orderhashMap.put("uniacid", storeInfo.getId());
//                orderhashMap.put("yhq_money", "0.00");
//                orderhashMap.put("coupon_id", "0");
//                orderhashMap.put("yhq_money2", "0.00");
//                orderhashMap.put("coupon_id2", "0");
//                orderhashMap.put("table_id", "10");//、、、
//                orderhashMap.put("dn_state", "0");
//                orderhashMap.put("dm_state", "0");
//                orderhashMap.put("yy_state", "4");// 2或3提醒商家  4删除
//                orderhashMap.put("deposit", "0.00");
//                orderhashMap.put("ship_id", "");
//                orderhashMap.put("zk_money", "0.00");
//                orderhashMap.put("is_dd", "2");
                orderhashMap.put("store_name", storeInfo.getName());//店铺名称、、、
                orderhashMap.put("logo", storeInfo.getLogo());
                orderhashMap.put("store_tel", storeInfo.getTelephone());
                hashMap.put("order", orderhashMap);
                ArrayList<Map<String, String>> goodsArrayList = new ArrayList<Map<String, String>>();
                QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid", storeOrderinfo.getId());
                List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity1);//根据订单id查询订单数量
                Integer num = 0;//定义一个变量值为0
                if (!storeOrdergoodsList.isEmpty()) {
                    for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {
                        num += Integer.valueOf(storeOrdergoods.getNum());//每次循环取出商品数量
                        String goodsid = storeOrdergoods.getGoodsid();
                        StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                        HashMap goodshashMap = new HashMap();
                        goodshashMap.put("good_id", storeGoods.getId());
                        goodshashMap.put("img", storeGoods.getMiniimage());
                        goodshashMap.put("number", storeGoods.getStocknum());//剩余数量
                        goodshashMap.put("name", storeGoods.getName());
                        goodshashMap.put("money", storeGoods.getOriginprice());
                        goodshashMap.put("spec", "规格:一斤");
                        goodsArrayList.add(goodshashMap);
                    }
                }
                hashMap.put("good", goodsArrayList);
                hashMap.put("num", num);
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    //我的预约-订单列表--删除
    @RequestMapping(value = "/app/index/DelOrder", method = RequestMethod.GET)
    @ApiOperation(value = "我的预约-订单列表--删除",notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "order_id",value = "用户编号",required = true,dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] DelOrder(HttpServletRequest request, HttpServletResponse response){
        String[] success = {"1"};
        String[] fail = {"2"};
        String orderid = request.getParameter("order_id");
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        if (!StoresaUtls.blutls(orderid) ){
            log.error("数据错——/app/index/DelOrder");
            return fail;
        }
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){ return fail; }
        storeOrderinfo.setClientsts("2");
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return success;
    }

    @RequestMapping(value = "/app/index/MyOrder", method = RequestMethod.GET)
    @ApiOperation(value = "订单-订单列表-查询",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> MyOrder(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String userid = request.getParameter("user_id");//用户编号
        String status = request.getParameter("state");//订单状态1
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!StoresaUtls.blutls(userid)  || !StoresaUtls.blutls(status)  || !StoresaUtls.blutls(bizid)){
            log.error("数据错误——/app/index/MyOrder");
            return arrayList;
        }
        QueryParamEntity orderParamEntity = new QueryParamEntity();
        orderParamEntity.and("userid",userid).and("status","1").and("clientsts","1").and("bizid",bizid);
        QueryParamEntity orderParamEntityodertype = new QueryParamEntity();
        orderParamEntityodertype.or("odtype","1").or("odtype","2");
        orderParamEntity.nest().setTerms(orderParamEntityodertype.getTerms());
        List<StoreOrderinfo> storeOrderinfoselect = storeOrderinfoService.select(orderParamEntity);
        if (!storeOrderinfoselect.isEmpty()&& storeOrderinfoselect.size() == 1) {
            Date date = new Date();
            Timestamp dfttimestamp = new Timestamp(date.getTime());
            if (storeOrderinfoselect.get(0).getOdtype().equals("1")||storeOrderinfoselect.get(0).getOdtype().equals("2")) {
                if (dfttimestamp.getTime() - storeOrderinfoselect.get(0).getCreatedtime().getTime() > (60 * 15 * 1000)) {
                    storeOrderinfoselect.get(0).setStatus("8");
                    storeOrderinfoselect.get(0).setLastrsn("5");
                    storeOrderinfoService.updateByPk(storeOrderinfoselect.get(0).getId(), storeOrderinfoselect.get(0));
                }
            }
        }
//        标识订单状态： 1 待付款 2 待接单（待确认）  3 待发货（已确认） 4 待收货（待自提） 5 退款申请中 6 已接收（已到店） 7 待评价 8 关闭交易 9交易成功，10待退款，11 退款完成
//        lastrsn：1--客户取消订单，2--商家拒绝订单，3--商家拒绝退款 4---商家已同意退款
        String[] strarr = status.split(",");//根据“，”转换成String数组
        int[] strarrs = new int[strarr.length];
        for(int i=0;i<strarr.length;i++){
            strarrs[i]=Integer.parseInt(strarr[i]);
        }
        //.and("odtype",odtype)
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        if (strarrs.length==1) {
            if (strarrs[0] != 1) {
                queryParamEntity.and("clientsts", "1").and("userid", userid).and("status", strarrs[0]);
                if (strarrs[0] ==2) {
                    queryParamEntity.and("odtype","not","4");
                }
            }
        }
        if (strarrs.length>1){//1.12
            queryParamEntity.and("clientsts", "1").and("userid", userid);
            QueryParamEntity queryParamEntitystatus = new QueryParamEntity();
            for (int i =0; i <strarrs.length; i++) {
                queryParamEntitystatus.or("status", strarrs[i]);
            }
            queryParamEntity.nest().setTerms(queryParamEntitystatus.getTerms());
        }
        if(status.equals("1")){//
            QueryParamEntity queryParamEntistatus1 = new QueryParamEntity();
            queryParamEntistatus1.and("clientsts", "1").and("userid", userid).and("status", "1");

            QueryParamEntity queryParamEntistatus2 = new QueryParamEntity();
            queryParamEntistatus2.and("userid", userid).and("status","2").and("clientsts", "1");

            QueryParamEntity queryParamEntityodtype = new QueryParamEntity();
            queryParamEntityodtype.or("odtype", "3");
            queryParamEntistatus2.nest().setTerms(queryParamEntityodtype.getTerms());

            queryParamEntistatus1.orNest().setTerms(queryParamEntistatus2.getTerms());
            queryParamEntity.nest().setTerms(queryParamEntistatus1.getTerms());

        }
        queryParamEntity.setPageIndex(Integer.valueOf(page));//当前页数从零开始
        queryParamEntity.setPageSize(Integer.valueOf(pagesize));//每页数据最大个数
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        //查询结果等于0，则返回一个空数组
        if (!storeOrderinfoList.isEmpty()) {
            for (StoreOrderinfo storeOrderinfo : storeOrderinfoList) {
                HashMap hashMap = new HashMap();
                HashMap orderhashMap = new HashMap();
                orderhashMap.put("id", storeOrderinfo.getId());
                orderhashMap.put("lastrsn", storeOrderinfo.getLastrsn());//、、
                orderhashMap.put("user_id", storeOrderinfo.getUserid());//用户id
                orderhashMap.put("order_num", storeOrderinfo.getOrderno());//订单编号
                orderhashMap.put("state", storeOrderinfo.getStatus());//状态
                orderhashMap.put("jd_time", storeOrderinfo.getCreatedtime());//接单时间2
                orderhashMap.put("complete_time", storeOrderinfo.getFinishtime());//自提时间
                orderhashMap.put("pay_type", storeOrderinfo.getPaytype());//支付类型
                orderhashMap.put("order_type", storeOrderinfo.getOdtype()); //订单类型
                orderhashMap.put("store_id", storeOrderinfo.getBizid());///商店id
                orderhashMap.put("prepay", storeOrderinfo.getPrepay());///是否付款prepay
                orderhashMap.put("store_tel", storeInfo.getTelephone());//商家电话
                orderhashMap.put("paymoney", storeOrderinfo.getIncome());//支付金额
                if (StoresaUtls.blutls(storeOrderinfo.getPaytype())){
                    if (storeOrderinfo.getPaytype().equals("4")) {
                        orderhashMap.put("integral_money", storeOrderinfo.getPaypoint());
                    }
                }
                if (storeOrderinfo.getStatus().equals("1")) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date now = df.parse(StoreDateutil.getStringDate());
                    java.util.Date datee = df.parse(String.valueOf(storeOrderinfo.getCreatedtime()));
                    long between = (now.getTime() - datee.getTime())/1000;
                    Integer datese = 15*60 - Integer.valueOf(String.valueOf(between));
                    orderhashMap.put("reseconds",datese);
                }
                hashMap.put("order", orderhashMap);
                ArrayList<Map<String, String>> goodsArrayList = new ArrayList<Map<String, String>>();
                QueryParamEntity queryParamEntity2 = QueryParamEntity.single("orderid", storeOrderinfo.getId());
                List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity2);//根据订单id查询订单数量
                Integer num = 0;//定义一个变量值为0
                if (!storeOrdergoodsList.isEmpty()) {
                    for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {
                        num += Integer.valueOf(storeOrdergoods.getNum());//每次循环取出商品数量
                        String goodsid = storeOrdergoods.getGoodsid();
                        StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                        HashMap goodshashMap = new HashMap();
                        goodshashMap.put("number", storeOrdergoods.getNum());
                        goodshashMap.put("name", storeGoods.getName());
                        goodshashMap.put("money", storeGoods.getOriginprice());
                        goodsArrayList.add(goodshashMap);
                    }
                }
                hashMap.put("good", goodsArrayList);
                hashMap.put("num", num);
                arrayList.add(hashMap);
            }

        }
        return arrayList;
    }

//    订单—订单列表-申请退款()
    @RequestMapping(value = "/app/index/TkOrder", method = RequestMethod.GET)
    @ApiOperation(value = "订单-订单列表-申请退款",notes = "申请退款")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] TkOrder(HttpServletRequest request, HttpServletResponse response){
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误/app/index/TkOrder");
            return fail;
        }
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){ return fail; }
        storeOrderinfo.setStatus("5");
        storeOrderinfo.setIncome(storeOrderinfo.getIncome());
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return success;
    }
}
