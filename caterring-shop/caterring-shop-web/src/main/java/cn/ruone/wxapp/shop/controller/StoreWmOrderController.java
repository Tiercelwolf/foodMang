package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.dao.StoreMemberinfoDao;
import io.swagger.annotations.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.hswebframework.web.controller.message.MapResponseMessage.ok;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "商家入口后",value = "数据库操作")
public class StoreWmOrderController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreOrderinfoService storeOrderinfoService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private StoreGoodsService storeGoodsService;
    private StoreGoodsflavorService storeGoodsflavorService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreOrdertablesService storeOrdertablesService;
    private StoreTableinfoService storeTableinfoService;
    private StoreInfoService storeInfoService;
    private StoreRefundpsService storeRefundpsService;
    private StoreWalletrcdService storeWalletrcdService;
    private StoreUserpointService storeUserpointService;
    private StoreOrdercouponsService storeOrdercouponsService;
    private StoreCouponsService storeCouponsService;
    private StoreShopcartService storeShopcartService;
    private StoreCartgoodsService storeCartgoodsService;
    private StoreTakeoutodrstatService storeTakeoutodrstatService;
    private StoreUsercouponsService storeUsercouponsService;
    @Autowired
    public void setStoreUsercouponsService(StoreUsercouponsService storeUsercouponsService){
        this.storeUsercouponsService = storeUsercouponsService;
    }
    @Autowired
    public void  setStoreTakeoutodrstatService(StoreTakeoutodrstatService storeTakeoutodrstatService){
        this.storeTakeoutodrstatService = storeTakeoutodrstatService;
    }
    @Autowired
    public void setStoreCartgoodsService(StoreCartgoodsService storeCartgoodsService){
        this.storeCartgoodsService = storeCartgoodsService;
    }
    @Autowired
    public void setStoreShopcartService(StoreShopcartService storeShopcartService){
        this.storeShopcartService = storeShopcartService;
    }
    @Autowired
    public void setStoreCouponsService(StoreCouponsService storeCouponsService){
        this.storeCouponsService = storeCouponsService;
    }
    @Autowired
    public void setStoreOrdercouponsService(StoreOrdercouponsService storeOrdercouponsService){
        this.storeOrdercouponsService = storeOrdercouponsService;
    }
    @Autowired
    public void setStoreUserpointService(StoreUserpointService storeUserpointService){
        this.storeUserpointService = storeUserpointService;
    }
    @Autowired
    public void setStoreWalletrcdService(StoreWalletrcdService storeWalletrcdService){
        this.storeWalletrcdService = storeWalletrcdService;
    }
    @Autowired
    public void setStoreRefundpsService(StoreRefundpsService storeRefundpsService){
        this.storeRefundpsService = storeRefundpsService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService) {
        this.storeOrderinfoService = storeOrderinfoService;
    }

    @Autowired
    public void setStoreOrdergoodsService(StoreOrdergoodsService storeOrdergoodsService) {
        this.storeOrdergoodsService = storeOrdergoodsService;
    }

    @Autowired
    public void setStoreGoodsService(StoreGoodsService storeGoodsService) {
        this.storeGoodsService = storeGoodsService;
    }

    @Autowired
    public void setStoreGoodsflavorService(StoreGoodsflavorService storeGoodsflavorService) {
        this.storeGoodsflavorService = storeGoodsflavorService;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreOrdertablesService(StoreOrdertablesService storeOrdertablesService){
        this.storeOrdertablesService = storeOrdertablesService;
    }
    @Autowired
    public void setStoreTableinfoService(StoreTableinfoService storeTableinfoService){
        this.storeTableinfoService=storeTableinfoService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }


    //商家入口—登录之后
    @RequestMapping(value = "/app/index/StoreWmOrder", method = RequestMethod.GET)
    @ApiOperation(value = "商家入口—登录之后", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })

    public ArrayList<Map<String,String>> storewmOrder(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String status = request.getParameter("state");
        String ordertype = request.getParameter("order_type");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        if (!StoresaUtls.blutls(status) || !StoresaUtls.blutls(bizid)) {
            log.error("数据错误—/app/index/StoreWmOrder");
            return null;
        }
        String[] strarr = status.split(",");//根据“，”转换成String数组
        int[] strarrs = new int[strarr.length];
        for(int i=0;i<strarr.length;i++){
            strarrs[i]=Integer.parseInt(strarr[i]);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();

        if (strarrs.length == 1) {
            queryParamEntity.and("entersts", "1").and("bizid", bizid).and("status", strarrs[0]);
        }
        if (strarrs.length > 1) {
            queryParamEntity.and("entersts", "1").and("bizid", bizid);
            QueryParamEntity queryParamEntitysub = new QueryParamEntity();
            for (int i = 0; i < strarrs.length; i++) {
                queryParamEntitysub.or("status", strarrs[i] );
                if ( strarrs[i]==3|| strarrs[i]==4) {
                    queryParamEntity.and("odtype", ordertype);
                }
            }
            queryParamEntity.nest().setTerms(queryParamEntitysub.getTerms());
        }

        queryParamEntity.setPageIndex(Integer.valueOf(page));//当前页数从零开始
        queryParamEntity.setPageSize(Integer.valueOf(pagesize));//每页数据最大个数
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
        if (!storeOrderinfoList.isEmpty()) {
            for (StoreOrderinfo storeOrderinfo : storeOrderinfoList) {
                String ordeid = storeOrderinfo.getId();
                String statusse = storeOrderinfo.getStatus();
                HashMap hashMap = new HashMap();
                HashMap orderhashMap = new HashMap();//hashMap中的order的集合
                orderhashMap.put("name", storeOrderinfo.getName());//客户名字
                orderhashMap.put("lastrsn", storeOrderinfo.getLastrsn());
                orderhashMap.put("order_num", storeOrderinfo.getOrderno());//订单编号
                orderhashMap.put("time", storeOrderinfo.getCreatedtime());//下单时间
                orderhashMap.put("note", storeOrderinfo.getNotes());//备注
                orderhashMap.put("id", ordeid);//订单i
                orderhashMap.put("state", statusse);//状态
                orderhashMap.put("tel", storeOrderinfo.getPhone());//客户电话
                orderhashMap.put("pay_type", storeOrderinfo.getPaytype());//1微信支付 2货到付款
                orderhashMap.put("address", storeOrderinfo.getAddress()); //收货地址
                orderhashMap.put("order_type", storeOrderinfo.getOdtype());//1送达时间 2自提时间
                orderhashMap.put("delivery_time", storeOrderinfo.getArrivaltime()); //送达时间
                orderhashMap.put("money", storeOrderinfo.getIncome());//客户已支付XXX元
                orderhashMap.put("integral_money", storeOrderinfo.getPaypoint());//客户已支付XXX元
                if (storeOrderinfo.getOdtype().equals("3")) {
                    QueryParamEntity queryParamEntity1 = new QueryParamEntity();
                    queryParamEntity1.and("orderid", ordeid);
                    List<StoreOrdertables> storeOrdertablesList = storeOrdertablesService.select(queryParamEntity1);
                    if (!storeOrdertablesList.isEmpty()) {
                        for (StoreOrdertables storeOrdertables : storeOrdertablesList) {
                            StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(storeOrdertables.getTableid());
                            orderhashMap.put("tableid", storeTableinfo.getTableno());
                        }
                    }
                }
                hashMap.put("order", orderhashMap);
                ArrayList<Map<String, String>> goodarrayList = new ArrayList<Map<String, String>>();
                QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid", ordeid);
                List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity1);
                if (!storeOrdergoodsList.isEmpty()) {
                    Integer number = 0;
                    for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {//根据订单id，查询数据库有几条信息 ，每次循环拿出商品id，和数量
                        String id = storeOrdergoods.getGoodsid();
                        number += number = Integer.valueOf(storeOrdergoods.getNum());//获得商品数量并相加，得到商品订单总数
                        StoreGoods storeGoods = storeGoodsService.selectByPk(id);
                        HashMap goodhashMap = new HashMap();
                        goodhashMap.put("name", storeGoods.getName());//商品名称
                        goodhashMap.put("id", storeGoods.getId());//商品id
                        goodhashMap.put("number", storeOrdergoods.getNum());//商品件数/getOrderInfo
                        goodhashMap.put("money", storeGoods.getOriginprice());//商品价格
                        goodarrayList.add(goodhashMap);
                    }
                    hashMap.put("good", goodarrayList);
                    hashMap.put("num", number);//商品件数，几件商品
                }
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    //商家入口-点击接单-完成订单-订单详情
    @RequestMapping(value = "/app/index/OrderInfo", method = RequestMethod.GET)
    @ApiOperation(value = "商家入口-点击接单-完成订单-订单详情", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    //i=12486
    //order_id=100
    public HashMap orderInfo(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        //检查商家
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String orderid = request.getParameter("order_id");//订单id
        if(!StoresaUtls.blutls(orderid)){
            log.error("数据错误——/app/index/OrderInfo");
            return null;
        }
        //检查商家订单
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){ return null; }
        HashMap hashMap = new HashMap();
        // orderhashMap==start======================================================================================//
        HashMap orderhashMap = new HashMap();
        orderhashMap.put("state",storeOrderinfo.getStatus());//js:
        orderhashMap.put("lastrsn",storeOrderinfo.getLastrsn());//js:
        orderhashMap.put("box_money",storeOrderinfo.getPackfee()); //包装费
        if (StoresaUtls.blutls(storeOrderinfo.getOdtype())) {
            if (storeOrderinfo.getOdtype().equals("1")) {
                orderhashMap.put("ps_money", storeOrderinfo.getDeliveryfee());//配送费
            }
        }
        if (StoresaUtls.blutls(storeOrderinfo.getPaytype())){
            if (storeOrderinfo.getPaytype().equals("4")) {
                orderhashMap.put("integral_money", storeOrderinfo.getPaypoint());
            }
        }
        orderhashMap.put("order_type",storeOrderinfo.getOdtype());
//        orderhashMap.put("zk_money","0");// 折扣金额
//        orderhashMap.put("mj_money","0");//满减金额
        if (StoresaUtls.blutls(storeOrderinfo.getOdtype())) {
            if (storeOrderinfo.getOdtype().equals("2")) {
                orderhashMap.put("selftime", storeOrderinfo.getArrivaltime());//自提时间
            }
        }

        orderhashMap.put("integral",storeOrderinfo.getGetpoint());//积分
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid",orderid);
        List<StoreOrdercoupons> storeOrdercouponsList = storeOrdercouponsService.select(queryParamEntity);
        if (!storeOrdercouponsList.isEmpty()) {
            for (StoreOrdercoupons storeOrdercoupons : storeOrdercouponsList) {
                StoreCoupons storeCoupons = storeCouponsService.selectByPk(storeOrdercoupons.getCouponsid());
                if (storeCoupons.getCptype().equals("1")) {
                    orderhashMap.put("yhq_money", storeCoupons.getSubtract());// 优惠券1
                    orderhashMap.put("discount",storeCoupons.getSubtract());
                }
                if (storeCoupons.getCptype().equals("2")) {
                    orderhashMap.put("mj_money",storeCoupons.getSubtract());//满减金额
                    orderhashMap.put("discount",storeCoupons.getSubtract());
                }
                if (storeCoupons.getCptype().equals("3")) {
                    orderhashMap.put("zk_money",storeCoupons.getSubtract());// 折扣金额
                    orderhashMap.put("discount",storeCoupons.getSubtract());
                }
                if (storeCoupons.getCptype().equals("4")) {
                    orderhashMap.put("yhq_money2", storeCoupons.getSubtract());// 红包4
                    orderhashMap.put("discount",storeCoupons.getSubtract());
                }
            }
        }

        orderhashMap.put("money",storeOrderinfo.getIncome());//小计
//        orderhashMap.put("tableware","");// 份
        orderhashMap.put("note",storeOrderinfo.getNotes());//备注
        orderhashMap.put("address",storeOrderinfo.getAddress());//收货地址
        orderhashMap.put("name",storeOrderinfo.getName());//客户姓名
        orderhashMap.put("sex",storeOrderinfo.getGender());//客户性别：1.先生 2.女士
        orderhashMap.put("tel",storeOrderinfo.getPhone());//客户电话
        orderhashMap.put("order_num",storeOrderinfo.getOrderno());//订单号
        orderhashMap.put("pay_type",storeOrderinfo.getPaytype());// 支付方式：1.微信支付  2.余额支付  3.货到付款  4.餐后支付
        orderhashMap.put("time",storeOrderinfo.getCreatedtime());//下单时间
        orderhashMap.put("paymoney",storeOrderinfo.getIncome());//商品价格
        hashMap.put("order", orderhashMap);
        //orderhashMap==end ===============================================================================//
        // goodarrayList==start======================================================================================//
        QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid",orderid);
        List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity1);
        ArrayList<Map<String,String>> goodarrayList = new ArrayList<Map<String,String>>();
        if (!storeOrdergoodsList.isEmpty()) {
            for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {
                String goodsid = storeOrdergoods.getGoodsid();
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                HashMap goodhashMap = new HashMap();
                goodhashMap.put("good_id", storeGoods.getId());
                goodhashMap.put("img", storeGoods.getMiniimage());
                goodhashMap.put("number", storeOrdergoods.getNum());
                goodhashMap.put("name", storeGoods.getName());
                goodhashMap.put("money", storeGoods.getOriginprice());
                goodhashMap.put("spec", "商品规格");
                goodarrayList.add(goodhashMap);
            }
        }
        hashMap.put("good",goodarrayList);//返回暂时为空
        // goodarrayList==end======================================================================================//
        // store==start======================================================================================//
         HashMap storehashMap = new HashMap();
         storehashMap.put("id",storeOrderinfo.getBizid());//商店id
         storehashMap.put("logo",storeInfo.getLogo());//商店logo
         storehashMap.put("name",storeInfo.getName());//商店名称
         storehashMap.put("address",storeInfo.getAddress());//商店名称
         storehashMap.put("tel",storeInfo.getTelephone());//商店电话
         storehashMap.put("coordinates",storeInfo.getLocation());//商店电话
        hashMap.put("store", storehashMap);//返回暂时为空
        // store==end======================================================================================//
        // store==start======================================================================================//
         HashMap storesethashMap = new HashMap();
//         storesethashMap.put("box_name","");//包装盒
//         storesethashMap.put("is_jc","");//加菜
//         storesethashMap.put("cj_name", storeOrderinfo.getDishware());//餐具用量
//         storesethashMap.put("ps_mode","");//配送方式
        hashMap.put("storeset", storesethashMap);//返回暂时为空
        // store==end======================================================================================//
        return hashMap;
    }
    @RequestMapping(value = "/app/index/makeOrderInfo", method = RequestMethod.GET)
    @ApiOperation(value = "商家入口--预约订单详情", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public HashMap makeOrderInfoinset(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        //检查商家
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误——/app/index/OrderInfo");
            return null;
        }
        //检查商家订单
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){ return null; }
        HashMap hashMap = new HashMap();
        HashMap orderhashMap = new HashMap();
        orderhashMap.put("order_num",storeOrderinfo.getOrderno());//订单号
        orderhashMap.put("time",storeOrderinfo.getCreatedtime());//下单时间
        orderhashMap.put("money",storeOrderinfo.getIncome());//实付款-小计￥XXX
        orderhashMap.put("tel",storeOrderinfo.getPhone());//预约电话
        orderhashMap.put("name",storeOrderinfo.getName());//预约人
        orderhashMap.put("note",storeOrderinfo.getNotes());//备注
        orderhashMap.put("pay_type",storeOrderinfo.getPaytype());//1微信支付 2余额支付
        orderhashMap.put("delivery_time",storeOrderinfo.getArrivaltime());//预约到店时间
        orderhashMap.put("sex",storeOrderinfo.getGender());
        orderhashMap.put("tableware",storeOrderinfo.getPeoplenum());//预约人数
        if (storeOrderinfo.getStatus().equals("2")) {
            orderhashMap.put("yy_state","2" );//（2待确认 3已通过 4已拒绝）
        }
        if (storeOrderinfo.getStatus().equals("6")) {
            orderhashMap.put("yy_state","3" );//（2待确认 3已通过 4已拒绝）
        }
        if (storeOrderinfo.getStatus().equals("8") && storeOrderinfo.getLastrsn().equals("2")) {
            orderhashMap.put("yy_state", "4");//（2待确认 3已通过 4已拒绝）
        }
        orderhashMap.put("deposit",storeOrderinfo.getSubscription());//预定定金，-￥XXX
        hashMap.put("order",orderhashMap);
        // goodarrayList==start======================================================================================//
        QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid",orderid);
        List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity1);
        ArrayList<Map<String,String>> goodarrayList = new ArrayList<Map<String,String>>();
        if (!storeOrdergoodsList.isEmpty()) {
            for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {
                String goodsid = storeOrdergoods.getGoodsid();
                String num = storeOrdergoods.getNum();
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                HashMap goodhashMap = new HashMap();
                goodhashMap.put("img", storeGoods.getMiniimage());
                goodhashMap.put("number", num);
                goodhashMap.put("name", storeGoods.getName());
                goodhashMap.put("money", storeGoods.getOriginprice());
                goodhashMap.put("spec", "商品规格");
                goodarrayList.add(goodhashMap);
            }
        }
        hashMap.put("good",goodarrayList);//返回暂时为空
        // goodarrayList==end======================================================================================//
        // store==start======================================================================================//
        HashMap goodhashMap = new HashMap();
        goodhashMap.put("id",bizid);//商店id
        goodhashMap.put("logo",storeInfo.getLogo());//商店logo
        goodhashMap.put("name",storeInfo.getName());//商店名称
        goodhashMap.put("tel",storeInfo.getTelephone());//商店电话
        hashMap.put("store", goodhashMap);//返回暂时为空
        // store==end======================================================================================//

        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid",orderid);
        List<StoreOrdertables> storeOrdertablesList = storeOrdertablesService.select(queryParamEntity);
        ArrayList<Map<String,String>> taarrayList = new ArrayList<Map<String,String>>();
        if (!storeOrdertablesList.isEmpty()) {
            for (StoreOrdertables storeOrdertables : storeOrdertablesList) {
                String tableid = storeOrdertables.getTableid();
                StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(tableid);
                HashMap tablehashMap = new HashMap();
                tablehashMap.put("id", storeTableinfo.getId());
                tablehashMap.put("name", storeTableinfo.getRoomname());
                tablehashMap.put("num", storeTableinfo.getPersonnum());
                tablehashMap.put("table_no", storeTableinfo.getRoomno());
                tablehashMap.put("table_type", storeTableinfo.getTabtype());
                tablehashMap.put("Minconsumpt", storeTableinfo.getMinexpense());//写死
                taarrayList.add(tablehashMap);
            }
        }
        hashMap.put("tables",taarrayList);
        return hashMap;
    }
    //商家入口-点击接单-拒接接单
    @RequestMapping(value = "/app/index/JjOrder", method = RequestMethod.GET)
    @ApiOperation(value = "商家入口-点击接单——拒绝接单", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public String[] jjOrder(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("/app/index/JjOrder");
            return fail;
        }
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){ return fail; }
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(storeOrderinfo.getUserid());
        float wallet = storeMemberinfo.getWallet();
        if (storeOrderinfo.getOdtype().equals("2")||storeOrderinfo.getOdtype().equals("1")){
            String patype = storeOrderinfo.getPaytype();
            float money =  storeOrderinfo.getIncome();
            if (patype.equals("3")){//钱包支付
                storeMemberinfo.setWallet(wallet+money);
                storeMemberinfoService.updateByPk(storeOrderinfo.getUserid(),storeMemberinfo);
                //2.将订单表里状态改为8.交易关闭，
                storeOrderinfo.setIncome(0);
                storeOrderinfo.setSubscription(0);
                storeOrderinfo.setStatus("11");
                storeOrderinfo.setLastrsn("2");//1--客户取消订单，2--商家拒绝订单，3--商家拒绝退款 4---商家已同意退款
                storeOrderinfo.setCanceltime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeOrderinfoService.updateByPk(orderid, storeOrderinfo);

                //商家拒绝接单，钱自动返回——退款记录表添加记录
                storeRefundpsService.Refuseorders(orderid,bizid,storeOrderinfo.getPhone(),money,storeOrderinfo.getPaytype());

                //商家拒绝接单，钱自动返回——钱包记录表添加记录
                String Judge = "5";//商家退款标识
                storeWalletrcdService.Walletrefund(storeOrderinfo.getUserid(),storeOrderinfo.getOrderno(),money,Judge);


            }
            if (patype.equals("1")){
                //商家拒绝接单，钱自动返回——退款记录表添加记录
                storeRefundpsService.Refuseorders(orderid,bizid,storeOrderinfo.getPhone(),money,storeOrderinfo.getPaytype());
                storeOrderinfo.setStatus("10");
                storeOrderinfo.setLastrsn("4");
                storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
                //Refuseorders(String orderid, String bizid, String phone, float money, String patype)

            }
        }
        if (storeOrderinfo.getOdtype().equals("4")||storeOrderinfo.getOdtype().equals("3")){
            storeOrderinfo.setStatus("8");
            storeOrderinfo.setLastrsn("2");
            storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        }
        return success;
    }
    //商家入口-登录后-点击接单
    @RequestMapping(value = "/app/index/JdOrder",method = RequestMethod.GET)
    @ApiOperation(value = "商家入口-登录后-点击接单  ",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap JdOrder(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误——/app/index/JdOrder");
            return null;
        }
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){return null;}
        if (storeOrderinfo.getOdtype().equals("2")){
            storeOrderinfo.setStatus("4");
        }else if (storeOrderinfo.getOdtype().equals("1")){
            storeOrderinfo.setStatus("3");
        }else if(storeOrderinfo.getOdtype().equals("4")){
            storeOrderinfo.setStatus("6");
        }else if(storeOrderinfo.getOdtype().equals("3")){
            storeOrderinfo.setStatus("1");
        }
        storeOrdergoodsService.confirmdoods(orderid);

        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        HashMap hashMap = new HashMap();
        hashMap.put("getDate","1");//一个标识，返回1
        hashMap.put("order_type",storeOrderinfo.getOdtype());//这个订单的状态值（1外卖 2自提）
        return hashMap;
    }
    //商家入口-点击接单-完成接单
    @RequestMapping(value = "/app/index/OkOrder",method = RequestMethod.GET)
    @ApiOperation(value = "商家入口-点击接单-完成订单",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] OkOrder(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误——/app/index/OkOrder");
            return fail;
        }
        //检查订单合法性
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){return fail;}
        storeOrderinfo.setStatus("9");
        storeOrderinfo.setFinishtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        if(!storeOrderinfo.getPaytype().equals("4")) {
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(storeOrderinfo.getUserid());
            storeMemberinfo.setScore(String.valueOf(Integer.valueOf(storeMemberinfo.getScore()) + Integer.valueOf(storeOrderinfo.getGetpoint())));
            storeMemberinfoService.updateByPk(storeOrderinfo.getUserid(), storeMemberinfo);
            //获取积分
            storeUserpointService.Getpoints(storeOrderinfo.getGetpoint(),storeOrderinfo.getId(),storeOrderinfo.getUserid());
            //添加统计
            storeTakeoutodrstatService.Receipts(bizid,storeOrderinfo.getPaytype(),storeOrderinfo.getIncome());

        }
        return success;
    }

    //商家入口-商家中心-自动接单
    @RequestMapping(value = "/app/index/UpStore",method = RequestMethod.GET)
    @ApiOperation(value = "商家入口-商家中心-自动接单",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] UpStore(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String status = request.getParameter("is_jd");
        if (!StoresaUtls.blutls(status)){
            log.error("数据错误——/app/index/UpStore");
            return fail;
        }
        //is_jd:1开启自动接单 2关闭自动接单
        if (status.equals("1")){
            storeInfo.setAutorecv("1");
        }else if (status.equals("2")){
            storeInfo.setAutorecv("2");
        }
        return success;
    }
    @RequestMapping(value = "/app/index/thOrder", method = RequestMethod.GET)
    @ApiOperation(value = "商家入口-核销取货——用户确认取货", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "order_id", value = "订单id", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public String[] thOrderupdat(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String orderid = request.getParameter("order_id");
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误——商家入口-核销取货——用户确认取货——订单编号为空");
            return fail;
        }
        //检查订单合法性
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){return fail;}
        storeOrderinfo.setStatus("9");//交易成功
        storeOrderinfo.setFinishtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        if(!storeOrderinfo.getPaytype().equals("4")) {
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(storeOrderinfo.getUserid());
            storeMemberinfo.setScore(String.valueOf(Integer.valueOf(storeMemberinfo.getScore()) + Integer.valueOf(storeOrderinfo.getGetpoint())));
            storeMemberinfoService.updateByPk(storeOrderinfo.getUserid(), storeMemberinfo);
            //获取积分
            storeUserpointService.Getpoints(storeOrderinfo.getGetpoint(),storeOrderinfo.getId(),storeOrderinfo.getUserid());
            //添加统计
            storeTakeoutodrstatService.Receipts(bizid,storeOrderinfo.getPaytype(),storeOrderinfo.getIncome());
        }
        return success;
    }
    @RequestMapping(value = "/app/index/getOrderInfo", method = RequestMethod.GET)
    @ApiOperation(value = "订单——获取订单所有信息", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public HashMap getOrderInfo(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        String orderid = request.getParameter("order_id");
        if(!StoresaUtls.blutls(orderid)){
            log.error("数据错误——订单——获取订单所有信息——orderid为空");
            return null;
        }
        //检查订单合法性
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){return null;}
        HashMap hashMap = new HashMap();
        HashMap orderhashMap = new HashMap();
        orderhashMap.put("id",orderid);//、、
        orderhashMap.put("user_id",storeOrderinfo.getUserid());//用户id//
        orderhashMap.put("order_num",storeOrderinfo.getOrderno());//订单编号//
        orderhashMap.put("state",storeOrderinfo.getStatus());//状态//
        orderhashMap.put("jd_time",storeOrderinfo.getCreatedtime());//接单时间2
        orderhashMap.put("order_type",storeOrderinfo.getOdtype()); //订单类型//
        orderhashMap.put("store_id",storeOrderinfo.getBizid());///商店id
        orderhashMap.put("store_tel",storeInfo.getTelephone());//商家电话
        orderhashMap.put("paymoney",storeOrderinfo.getIncome());//支付金额
        orderhashMap.put("psf",storeOrderinfo.getDeliveryfee());//支付金额
        hashMap.put("order",orderhashMap);
        ArrayList<Map<String,String>> goodsArrayList = new ArrayList<Map<String,String>>();
        QueryParamEntity queryParamEntity2 = QueryParamEntity.single("orderid",orderid);
        List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity2);//根据订单id查询订单数量
        Integer num = 0;//定义一个变量值为0
        if (!storeOrdergoodsList.isEmpty()) {
            for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {
                num += Integer.valueOf(storeOrdergoods.getNum());//每次循环取出商品数量
                String goodsid = storeOrdergoods.getGoodsid();
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                HashMap goodshashMap = new HashMap();
                goodshashMap.put("num", storeOrdergoods.getNum());
                goodshashMap.put("id", storeOrdergoods.getId());
                goodshashMap.put("img", storeGoods.getMiniimage());
                goodshashMap.put("name", storeGoods.getName());//
                goodshashMap.put("price", storeGoods.getOriginprice());
                goodshashMap.put("newOld", Integer.valueOf(storeOrdergoods.getStatus()));
                goodshashMap.put("pack_money",storeGoods.getPackfee());//包装费
                goodsArrayList.add(goodshashMap);
            }
        }
        hashMap.put("good",goodsArrayList);
        hashMap.put("num",num);
        return hashMap;
    }
    @RequestMapping(value = "/app/index/addDishs", method = RequestMethod.POST)
    @ApiOperation(value = "订单——加菜", notes = "查询")
    @ApiImplicitParams({

    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public String[] addDishsinset(HttpServletRequest request,HttpServletResponse response) {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String userid = request.getParameter("user_id");
        String orderid = request.getParameter("order_id");
        String finalPrice = request.getParameter("addPrice");
        String note = request.getParameter("note");
        String tableid = request.getParameter("tableid");
        String selectGoods = request.getParameter("selectGoods");//积分
        String getIntegral = request.getParameter("getIntegral"); //满减id
        String mjcouponid = request.getParameter("mjcoupon_id");//红包优惠券id
        String hyid = request.getParameter("hyid");

        if (!StoresaUtls.blutls(orderid) || !StoresaUtls.blutls(userid)|| !StoresaUtls.blutls(finalPrice) || !StoresaUtls.blutls(selectGoods)){
            log.error("数据错误—/app/index/addDishs");
            return fail;
        }
        //检查订单合法性
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){return fail;}
        //将接收到的字符串转化成JSON数组实例：wwwww
        JSONArray selectGoodsJSONArray = JSONArray.fromObject(selectGoods);
        if (selectGoodsJSONArray.size() == 0){
            log.error("数据错误—selectGoodsJSONArray-值为0");
            return fail;
        }
        //检查商品合法性
        if (StoresaUtls.blutls(selectGoods)){
            if (!StoreSQLoperation.booleangoods(selectGoods,bizid,storeGoodsService)){return fail;}
        }

        if (StoresaUtls.blutls(hyid)){
            //优惠折扣活动不为空，检查活动合法性
//           if (!booleanstoreCoupons(hyid,bizid)){return fail;}
           if (!StoreSQLoperation.booleanstoreCoupons(hyid,bizid,storeCouponsService)){return fail;}
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid",orderid);
            List<StoreOrdercoupons> storeOrdercouponsList = storeOrdercouponsService.select(queryParamEntity1);
            //没有查询记录，添加记录
            if (storeOrdercouponsList.isEmpty()) {
                storeOrdercouponsService.AddActivity(orderid, hyid);
            }
            //有记录则修改，并修改个人中心优惠信息
            if (!storeOrdercouponsList.isEmpty() && storeOrdercouponsList.size() == 1){
                String couponsid = storeOrdercouponsList.get(0).getCouponsid();
                if (!couponsid.equals(hyid)) {//两次使用活动id不同则执行
                    //使用的优惠卷减去
                    QueryParamEntity queryParamEntity2 = QueryParamEntity.single("userid", userid).and("couponid", hyid);
                    List<StoreUsercoupons> storeUsercouponsList = storeUsercouponsService.select(queryParamEntity2);
                    if (!storeUsercouponsList.isEmpty()) {
                        StoreUsercoupons storeUsercoupons = storeUsercouponsService.selectByPk(storeUsercouponsList.get(0).getId());
                        storeUsercoupons.setNum("0");
                        storeUsercouponsService.updateByPk(storeUsercouponsList.get(0).getId(), storeUsercoupons);
                    }
                    //将替换使用的优惠卷替换使用
                    QueryParamEntity queryParamEntity3 = QueryParamEntity.single("userid", userid).and("couponid", couponsid);
                    List<StoreUsercoupons> storeUsercouponsList1 = storeUsercouponsService.select(queryParamEntity3);
                    if (!storeUsercouponsList1.isEmpty()) {
                        StoreUsercoupons storeUsercoupons = storeUsercouponsService.selectByPk(storeUsercouponsList1.get(0).getId());
                        storeUsercoupons.setNum("1");
                        storeUsercouponsService.updateByPk(storeUsercouponsList1.get(0).getId(), storeUsercoupons);
                    }
                    StoreOrdercoupons storeOrdercoupons = storeOrdercouponsService.selectByPk(storeOrdercouponsList.get(0).getId());
                    storeOrdercoupons.setCouponsid(hyid);
                    storeOrdercouponsService.updateByPk(storeOrdercouponsList.get(0).getId(), storeOrdercoupons);
                }
            }

        }
        if (StoresaUtls.blutls(mjcouponid)){
            //优惠折扣活动不为空，检查活动合法性
//            if (!booleanstoreCoupons(mjcouponid,bizid)){return fail;}
            if (!StoreSQLoperation.booleanstoreCoupons(mjcouponid,bizid,storeCouponsService)){return fail;}
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid",orderid);
            List<StoreOrdercoupons> storeOrdercouponsList = storeOrdercouponsService.select(queryParamEntity1);
            //没有查询记录，添加记录
            if (storeOrdercouponsList.isEmpty()) {
                storeOrdercouponsService.AddActivity(orderid, mjcouponid);
            }
            //有记录进行修改
            if (!storeOrdercouponsList.isEmpty() && storeOrdercouponsList.size() == 1){
                String couponsid = storeOrdercouponsList.get(0).getCouponsid();
                StoreCoupons storeCoupons1 = storeCouponsService.selectByPk(couponsid);
                //用户在本次以往使用优惠卷或红包，要将已使用的红包优惠卷恢复到个人中心中,
                if (storeCoupons1.getCptype().equals("1")||storeCoupons1.getCptype().equals("4")){
                    QueryParamEntity queryParamEntity3 = QueryParamEntity.single("userid",userid).and("couponid",couponsid);
                    List<StoreUsercoupons> storeUsercouponsList1 = storeUsercouponsService.select(queryParamEntity3);
                    if (!storeUsercouponsList1.isEmpty()){
                        StoreUsercoupons storeUsercoupons = storeUsercouponsService.selectByPk(storeUsercouponsList1.get(0).getId());
                        storeUsercoupons.setNum("1");
                        storeUsercouponsService.updateByPk(storeUsercouponsList1.get(0).getId(),storeUsercoupons);
                    }
                }
                if (!couponsid.equals(hyid)) {//两次使用活动id不同则执行
                    StoreOrdercoupons storeOrdercoupons = storeOrdercouponsService.selectByPk(storeOrdercouponsList.get(0).getId());
                    storeOrdercoupons.setCouponsid(mjcouponid);
                    storeOrdercouponsService.updateByPk(storeOrdercouponsList.get(0).getId(), storeOrdercoupons);
                }
            }
        }

        storeOrderinfo.setUserid(userid);
        if (!StoresaUtls.blutls(note)){
            storeOrderinfo.setNotes("该用户没有填写备注");
        }else {
            storeOrderinfo.setNotes(note);
        }
        storeOrderinfo.setPrepay("3");
        storeOrderinfo.setBizid(bizid);
        if (StoresaUtls.blutls(getIntegral)) {
            storeOrderinfo.setGetpoint(getIntegral);
        }else {
            storeOrderinfo.setGetpoint("0");
        }
        storeOrderinfo.setIncome(Float.valueOf(finalPrice));//订单总价
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);

        QueryParamEntity queryParamEntity1 = new QueryParamEntity().and("bizid",bizid).and("userid",userid);
        List<StoreShopcart> storeShopcartList = storeShopcartService.select(queryParamEntity1);
        String cartid = storeShopcartList.get(0).getId();
        //遍历 jsonarray 数组，把每一个对象转成 json 对象实例：
        for (int i = 0;i<selectGoodsJSONArray.size();i++) {
            JSONObject carinfojob = selectGoodsJSONArray.getJSONObject(i);
            //得到 每个对象中的属性值实例：
            String goodid = (String) carinfojob.get("id");
            Integer num = (Integer) carinfojob.get("num");
            Integer newOld = (Integer) carinfojob.get("newOld");
            //添加订单商品记录
            if (String.valueOf(newOld).equals("2")) {
                storeOrdergoodsService.Addmerchandise(orderid, goodid, String.valueOf(num));
            }
            QueryParamEntity queryParamEntity2 = new QueryParamEntity().and("cartid",cartid).and("goodsid",goodid);
            List<StoreCartgoods> storeCartgoodsList = storeCartgoodsService.select(queryParamEntity2);
            if (!storeCartgoodsList.isEmpty()){
                String cagid = storeCartgoodsList.get(0).getId();
                storeCartgoodsService.deleteByPk(cagid);
            }
        }
        return success;
    }
}
