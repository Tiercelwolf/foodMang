package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import io.swagger.annotations.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.MapResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "购物车——订单查询",value ="数据库接口" )
public class StoreOrderinfoController  {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreOrderinfoService storeOrderinfoService;
    private StoreInfoService storeInfoService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private StoreGoodsService storeGoodsService;
    private StoreTableinfoService storeTableinfoService;
    private StoreOrdertablesService storeOrdertablesService;
    private StoreShopcartService storeShopcartService;
    private StoreCartgoodsService storeCartgoodsService;
    private StoreUsercouponsService storeUsercouponsService;
    private StoreOrdercouponsService storeOrdercouponsService;
    private StoreUserpointService storeUserpointService;
    private StoreCouponsService storeCouponsService;
    @Autowired
    public void setStoreCouponsService(StoreCouponsService storeCouponsService){
        this.storeCouponsService = storeCouponsService;
    }
    @Autowired
    public void setStoreUserpointService(StoreUserpointService storeUserpointService){
        this.storeUserpointService = storeUserpointService;
    }
    @Autowired
    public void setStoreOrdercouponsService(StoreOrdercouponsService storeOrdercouponsService){
        this.storeOrdercouponsService = storeOrdercouponsService;
    }
    @Autowired
    public void setStoreUsercouponsService(StoreUsercouponsService storeUsercouponsService){
        this.storeUsercouponsService = storeUsercouponsService;
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
    public void setStoreOrdertablesService(StoreOrdertablesService storeOrdertablesService){
        this.storeOrdertablesService = storeOrdertablesService;
    }
    @Autowired
    public void setStoreTableinfoService(StoreTableinfoService storeTableinfoService){
        this.storeTableinfoService = storeTableinfoService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void  setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreOrdergoodsService(StoreOrdergoodsService storeOrdergoodsService){
        this.storeOrdergoodsService = storeOrdergoodsService;
    }
    @Autowired
    public void setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }

//    private boolean booleanstoreCoupons(String coupid,String bizid){
//        QueryParamEntity queryParamEntity = new QueryParamEntity();
//        queryParamEntity.and("id",coupid);
//        String format = StoreDateutil.getStringDate();
//        queryParamEntity.where("begintime","lte",format);// 表示2018年11月13日10点10分10秒及其以后的数据11
//        queryParamEntity.where("endtime","gte",format);// 10点10分10 13
//        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
//        if (!storeCouponsList.isEmpty() && storeCouponsList.size() == 1 && storeCouponsList.get(0).getBizid().equals(bizid)) {
//            return true;
//        }
//        log.error("非法折扣优惠活动——StoreOrderinfoController——/app/index/generateOrder");
//        return false;
//    }
//
    public boolean insetgoods(String JSONOString,String orderid,String cartid){
        JSONArray selectGoodsJSONArray = JSONArray.fromObject(JSONOString);
        for (int i = 0;i<selectGoodsJSONArray.size();i++) {
            JSONObject carinfojob = selectGoodsJSONArray.getJSONObject(i);
            //得到 每个对象中的属性值实例：
            String goodid = (String) carinfojob.get("id");
            Integer num = (Integer) carinfojob.get("num");
            //添加订单商品记录
            storeOrdergoodsService.Addmerchandise(orderid, goodid, String.valueOf(num));
            QueryParamEntity queryParamEntity2 = new QueryParamEntity().and("cartid",cartid).and("goodsid",goodid);
            List<StoreCartgoods> storeCartgoodsList = storeCartgoodsService.select(queryParamEntity2);
            if (!storeCartgoodsList.isEmpty()){
                String cagid = storeCartgoodsList.get(0).getId();
                storeCartgoodsService.deleteByPk(cagid);
            }
        }
        return true;
    }

    @RequestMapping(value = "/app/index/MyCar",method = RequestMethod.GET)
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public HashMap orderinfoselect(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String userid = request.getParameter("user_id");//用户id
        if (!StoresaUtls.blutls(bizid) ||!StoresaUtls.blutls(userid) ){
            log.error("数据错误—/app/index/MyCar");
            return null;
        }
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return null; }
        //1.根据商店id，用户id，以及订单状态查询订单
        QueryParamEntity queryParamEntities = new QueryParamEntity().and("bizid",bizid).and("clientsts","1")
                .and("userid",userid).and("status","1");
        //2.获取到符合条件的订单信息
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntities);
        if (storeOrderinfoList.isEmpty() && storeOrderinfoList.size() != 1){
            log.error("数据错误");
            return null;
        }
        String orderid =  storeOrderinfoList.get(0).getId();//2339
        float money = storeOrderinfoList.get(0).getIncome();
        //获取到ordergoods的orderid
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid",orderid);
        //根据获取到的orderid查询符合信息的数据
        List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity);
        HashMap orderinfohashMap = new HashMap();//
        orderinfohashMap.put("box_money", 0);
        ArrayList<Map<String,String>> resarrayList = new ArrayList<Map<String,String>>();
        Integer num = 0;
        for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList){
            String goodsid = storeOrdergoods.getGoodsid();//拿到goods表中的的goodsid
            StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);//以goodsid作为查询条件，拿到数据
            HashMap reshashMap = new HashMap();
            reshashMap.put("id",storeOrdergoods.getId());
            reshashMap.put("good_id",storeOrdergoods.getGoodsid());
            reshashMap.put("user_id",userid);
            reshashMap.put("store_id",storeGoods.getBizid());
            num += Integer.valueOf(storeOrdergoods.getNum());//每次循环取出商品数量
            reshashMap.put("num",num);
            reshashMap.put("spec","规格:一斤");
            reshashMap.put("combination_id","770");
            reshashMap.put("money",storeGoods.getOriginprice());
            reshashMap.put("box_money",storeGoods.getPackfee());
            reshashMap.put("son_id","0");
            reshashMap.put("type","1");
            reshashMap.put("dr_id","0");
            reshashMap.put("number","100000000");
            reshashMap.put("logo",storeGoods.getMiniimage());
            reshashMap.put("name",storeGoods.getName());
            reshashMap.put("inventory",storeGoods.getStocknum());
            resarrayList.add(reshashMap);
        }
        orderinfohashMap.put("res",resarrayList);

        orderinfohashMap.put("num",num);//购买商品总数量
        orderinfohashMap.put("money",money);//购买商品总价格
        return orderinfohashMap;
    }

    @RequestMapping(value = "/app/index/Hot",method = RequestMethod.GET)
    public ArrayList<Map<String,String>> hot(HttpServletRequest request, HttpServletResponse response){
        ArrayList<Map<String,String>> hotarrayList = new ArrayList<Map<String,String>>();
        return hotarrayList;
    }
    @RequestMapping(value = "/app/index/generateOrder", method = RequestMethod.POST)
    @ApiOperation(value = "订单生成—外卖或自提", notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public String[] generateOrder(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String userid = request.getParameter("user_id");
        String address = request.getParameter("address");
        String finalPrice = request.getParameter("finalPrice");
        String timetime = request.getParameter("timetime");
        String note = request.getParameter("note");
        String index = request.getParameter("index");
        String selectGoods = request.getParameter("selectGoods");
        String getIntegral = request.getParameter("getIntegral");
        String hyid = request.getParameter("hyid");
        String mjcouponid  = request.getParameter("mjcoupon_id");
        String deliveryfee = request.getParameter("deliveryfee");
        String packmoney = request.getParameter("pack_money");
        String integralmoney = request.getParameter("integral_money");
        String paytype = request.getParameter("pay_type");

        if (!StoresaUtls.blutls(bizid) || !StoresaUtls.blutls(bizid)|| !StoresaUtls.blutls(address)|| !StoresaUtls.blutls(finalPrice)
                || !StoresaUtls.blutls(timetime)|| !StoresaUtls.blutls(index)|| !StoresaUtls.blutls(selectGoods) || !StoresaUtls.blutls(packmoney)){
            log.error("数据错误—/app/index/generateOrde");
            return fail;
        }

        //将接收到的字符串转化成JSON数组，为null则不继续执行
        JSONArray selectGoodsJSONArray= null;
        if ((selectGoodsJSONArray = StoreSQLoperation.booleanJSONArray(selectGoods)) == null){ return fail; }
        //检查商品合法性
         if(StoresaUtls.blutls(selectGoods)) {
             if (!StoreSQLoperation.booleangoods(selectGoods,bizid,storeGoodsService)){return fail;}
        }
        //优惠折扣活动不为空，检查活动合法性
        if (StoresaUtls.blutls(hyid)){
            if (!StoreSQLoperation.booleanstoreCoupons(hyid,bizid,storeCouponsService)){return fail;}
        }
        if (StoresaUtls.blutls(mjcouponid)){
            if (!StoreSQLoperation.booleanstoreCoupons(mjcouponid,bizid,storeCouponsService)){return fail;}
        }
        //将地址信息转化成为object
        JSONObject addressjsont = JSONObject.fromObject(address);
        String username = (String) addressjsont.get("user_name");
        String area = (String) addressjsont.get("area");
        String addresse = (String) addressjsont.get("address");
        String tel = (String) addressjsont.get("tel");

        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);//根据用户id查询用户
        if (StoresaUtls.blutls(integralmoney)) {
            if ( Integer.valueOf( storeMemberinfo.getScore()) < Integer.valueOf(integralmoney)){
                log.error("数据错误—/app/index/generateOrde——兑换物品积分不足 或者 接受积与商品兑换积分不等");
                return fail;
            }
        }
        if (StoresaUtls.blutls(paytype)) {
            Integer number = 0;
            for (int i = 0; i < selectGoodsJSONArray.size(); i++) {
                JSONObject carinfojob = selectGoodsJSONArray.getJSONObject(i);
                String goodid = (String) carinfojob.get("id");
                Integer num = (Integer) carinfojob.get("num");
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodid);//根据商品id 获取商品的所有信息
                String stocknum = storeGoods.getStocknum();//拿到商品所剩数量
                String userpoint = storeGoods.getUserpoint();//兑换商品所需积分
                number += number = Integer.valueOf(userpoint)*num;
                if (i == selectGoodsJSONArray.size() - 1) {
                    if (Integer.valueOf(storeMemberinfo.getScore()) > Integer.valueOf(number)) {

                        StoreOrderinfo storeOrderinfo = new StoreOrderinfo();
                        storeOrderinfo.setUserid(userid);
                        storeOrderinfo.setPackfee(Float.valueOf(packmoney));
                        storeOrderinfo.setStatus("2");//兑换成功即为待接单
                        storeOrderinfo.setPhone(tel);
                        if (!StoresaUtls.blutls(note)) {
                            storeOrderinfo.setNotes("该用户没有填写备注");
                        } else {
                            storeOrderinfo.setNotes(note);
                        }
                        storeOrderinfo.setBizid(bizid);
                        storeOrderinfo.setName(username);
                        String orderno = StoreDateutil.getnumber() + StoreDateutil.getrandom();
                        storeOrderinfo.setOrderno(orderno);//日期加随机数组成编号
                        storeOrderinfo.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));//生成订单时间
                        storeOrderinfo.setIncome(Float.valueOf(finalPrice));//订单总价
                        storeOrderinfo.setPaytype("4");
                        storeOrderinfo.setPaypoint(integralmoney);
                        if (index.equals("0")){//0=index外卖
                            storeOrderinfo.setOdtype("1");//1 外卖，2 自提，3 在店消费 4 预约到店
                            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                            String dfte = df1.format(new Date()); // new Date()为获取当前系统时间
                            storeOrderinfo.setArrivaltime(Timestamp.valueOf(dfte+" "+timetime+":00"));
                            storeOrderinfo.setAddress(area+addresse);
                            storeOrderinfo.setDeliveryfee(Float.valueOf(deliveryfee));
                        }
                        if (index.equals("1")){//1=index自提
                            storeOrderinfo.setOdtype("2");
                            storeOrderinfo.setArrivaltime(Timestamp.valueOf( timetime+":00"));
                            storeOrderinfo.setAddress(storeInfo.getAddress());
                        }
                        storeOrderinfo.setDeliveryfee(Float.valueOf(deliveryfee));
                        storeOrderinfo.setGetpoint("0");
                        storeOrderinfo.setClientsts("1");//客户删除 1--- 未删除， 2----已删除
                        storeOrderinfo.setEntersts("1"); //商家删除： 1---未删除， 2---已删除
                        storeOrderinfoService.insert(storeOrderinfo);
                        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderno", orderno);
                        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
                        String orderid = storeOrderinfoList.get(0).getId();
                        //添加订单商品
                        storeOrdergoodsService.Addmerchandise(orderid, goodid, String.valueOf(num));

                        storeMemberinfo.setScore(String.valueOf(Integer.valueOf(storeMemberinfo.getScore()) - Integer.valueOf(userpoint)));//用户积分-商品兑换所需积分，并更新
                        storeMemberinfoService.updateByPk(userid, storeMemberinfo);//根据用户id跟新到memberinfo表中

                        storeGoods.setStocknum(String.valueOf(Integer.valueOf(stocknum) - 1));//商品所剩数量减去1
                        storeGoodsService.updateByPk(goodid, storeGoods);//根据商品id跟新到goods表中

                        //增加一条兑换记录
                        storeUserpointService.Paypoints(storeGoods.getFlag(),goodid,userpoint,storeGoods.getName(),userid);

                        return success;
                    }
                    return fail;
                }
            }
        }
        //正常生成外卖或自提订单
        QueryParamEntity orderParamEntity = new QueryParamEntity();
        orderParamEntity.and("userid",userid).and("status","1").and("clientsts","1").and("bizid",bizid);
        QueryParamEntity orderParamEntityodertype = new QueryParamEntity();
        orderParamEntityodertype.or("odtype","1").or("odtype","2");
        orderParamEntity.nest().setTerms(orderParamEntityodertype.getTerms());
        List<StoreOrderinfo> storeOrderinfoselect = storeOrderinfoService.select(orderParamEntity);
        if (!storeOrderinfoselect.isEmpty() && storeOrderinfoselect.size() == 1 ){
            Date date = new Date();
            Timestamp dfttimestamp = new Timestamp(date.getTime());
            if (storeOrderinfoselect.get(0).getOdtype().equals("1")||storeOrderinfoselect.get(0).getOdtype().equals("2")) {
                if (dfttimestamp.getTime() - storeOrderinfoselect.get(0).getCreatedtime().getTime() > (60 * 15 * 1000)) {
                    storeOrderinfoselect.get(0).setStatus("8");
                    storeOrderinfoselect.get(0).setLastrsn("5");
                    storeOrderinfoService.updateByPk(storeOrderinfoselect.get(0).getId(), storeOrderinfoselect.get(0));
                }else  if (dfttimestamp.getTime() - storeOrderinfoselect.get(0).getCreatedtime().getTime() < (60 * 15 * 1000)) {
                    log.error("有待付款订单不能生成新的订单—/app/index/generateOrde");
                    return fail;
                }
            }
        }

        StoreOrderinfo storeOrderinfo = new StoreOrderinfo();
        storeOrderinfo.setUserid(userid);
        storeOrderinfo.setPackfee(Float.valueOf(packmoney));
        storeOrderinfo.setStatus("1");//生成订单状态值为1，即待付款
        storeOrderinfo.setPhone(tel);
        if (!StoresaUtls.blutls(note)){
            storeOrderinfo.setNotes("该用户没有填写备注");
        }else {
            storeOrderinfo.setNotes(note);
        }
        storeOrderinfo.setBizid(bizid);
        storeOrderinfo.setName(username);
        String orderno = StoreDateutil.getnumber()+StoreDateutil.getrandom();
        storeOrderinfo.setOrderno(orderno);//日期加随机数组成编号
        storeOrderinfo.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));//生成订单时间
        storeOrderinfo.setIncome(Float.valueOf(finalPrice));//订单总价
        if (index.equals("0")){//0=index外卖
            if (!StoresaUtls.blutls(deliveryfee)){
                log.error("数据错误—/app/index/generateOrde——配送费为空");
                return fail;
            }
            storeOrderinfo.setOdtype("1");//1 外卖，2 自提，3 在店消费 4 预约到店
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String dfte = df1.format(new Date()); // new Date()为获取当前系统时间
            storeOrderinfo.setArrivaltime(Timestamp.valueOf(dfte+" "+timetime+":00"));
            storeOrderinfo.setAddress(area+addresse);
            storeOrderinfo.setDeliveryfee(Float.valueOf(deliveryfee));
        }
        if (index.equals("1")){//1=index自提
            storeOrderinfo.setOdtype("2");
            storeOrderinfo.setArrivaltime(Timestamp.valueOf( timetime+":00"));
            storeOrderinfo.setAddress(storeInfo.getAddress());
        }
        if (!StoresaUtls.blutls(getIntegral)){
            storeOrderinfo.setGetpoint("0");
        }else {
            storeOrderinfo.setGetpoint(getIntegral);
        }
        storeOrderinfo.setClientsts("1");//客户删除 1--- 未删除， 2----已删除
        storeOrderinfo.setEntersts("1"); //商家删除： 1---未删除， 2---已删除
        storeOrderinfoService.insert(storeOrderinfo);
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderno",orderno);
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        String orderid = storeOrderinfoList.get(0).getId();
        if (StoresaUtls.blutls(integralmoney)) {
            //添加积分抵现记录
            storeUserpointService.Consumptionpoints(integralmoney,orderid,userid);
        }
        if (StoresaUtls.blutls(mjcouponid)){
            //添加订单使用折扣活动
            storeOrdercouponsService.AddActivity(orderid,mjcouponid);
        }
        if (StoresaUtls.blutls(hyid)){
            //添加订单使用折扣活动
            storeOrdercouponsService.AddActivity(orderid,hyid);
        }


        QueryParamEntity queryParamEntity1 = new QueryParamEntity().and("bizid",bizid).and("userid",userid).and("clientsts","1");
        List<StoreShopcart> storeShopcartList = storeShopcartService.select(queryParamEntity1);
        String cartid = storeShopcartList.get(0).getId();
        //添加到订单商品表，并且删除购物车对应数据
        if (!insetgoods(selectGoods,orderid,cartid)){return fail;}
        return success;
    }


    @RequestMapping(value = "/app/index/UpdateGenerateOrder", method = RequestMethod.POST)
    @ApiOperation(value = "订单生成—在店消费", notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public String[] UpdateGenerateOrder(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String userid = request.getParameter("user_id");
        String orderid = request.getParameter("orderid");
        String finalPrice = request.getParameter("finalPrice");
        String note = request.getParameter("note");
        String tableid = request.getParameter("tableid");
        String selectGoods = request.getParameter("selectGoods");//积分
        String getIntegral = request.getParameter("getIntegral"); //满减id
        String mjcouponid = request.getParameter("mjcoupon_id");//红包优惠券id
        String hyid = request.getParameter("hyid");
        //检查订单合法性
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService)) == null){ return fail;}
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
        //优惠折扣活动不为空，检查活动合法性
        if (StoresaUtls.blutls(hyid)){
            if (!StoreSQLoperation.booleanstoreCoupons(hyid,bizid,storeCouponsService)){return fail;}
            //优惠无问题 添加订单使用记录
            storeOrdercouponsService.AddActivity(orderid, hyid);
        }
        if (StoresaUtls.blutls(mjcouponid)){
            if (!StoreSQLoperation.booleanstoreCoupons(mjcouponid,bizid,storeCouponsService)){return fail;}
            //折扣无问题 添加订单使用记录
            storeOrdercouponsService.AddActivity(orderid, mjcouponid);
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
        //添加到订单商品表，并且删除购物车对应数据
        if (!insetgoods(selectGoods,orderid,cartid)){return fail;}

        return success;
    }
    @RequestMapping(value = "/app/index/CancelOrder", method = RequestMethod.GET)
    @ApiOperation(value = "购物车——生成订单——取消", notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)

    })
    public String[] CancelOrder(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String orderid = request.getParameter("order_id");
        StoreOrderinfo storeOrderinfo = null;
        if ((storeOrderinfo = StoreSQLoperation.booleanstoreorderinfo(orderid,bizid,storeOrderinfoService))==null){ return fail;}
        storeOrderinfo.setStatus("8");//关闭交易
        storeOrderinfo.setLastrsn("1");
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return success;
    }

    @RequestMapping(value = "/app/index/reservation", method = RequestMethod.POST)
    @ApiOperation(value = "订单——预约", notes = "增加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)

    })
    public String[] liji(HttpServletRequest request,HttpServletResponse response ){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = StoreSQLoperation.booleanstoreinfo(bizid,shopid,storeInfoService)) == null){ return fail; }
        String userid = request.getParameter("user_id"); //用户id
        String zuowei = request.getParameter("zuowei"); //座位选择
        String preprice =  request.getParameter("preprice");//座位预定费用
        String data = request.getParameter("data");  //选择日期
        String time =  request.getParameter("time"); //选择到店时间
        String people = request.getParameter("people"); //选择人数
        String name =  request.getParameter("lxr");//联系人姓名
        String sex = request.getParameter("sex");//联系性别
        String phone =  request.getParameter("iphone");//联系人姓名
        String price = request.getParameter("price");//价格
        String beizhu =  request.getParameter("beizhu");//备注
        String carinfo =  request.getParameter("carinfo"); //提前选择商品的购物车信息

        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(data)||!StoresaUtls.blutls(time)||!StoresaUtls.blutls(people)||!StoresaUtls.blutls(name)
                ||!StoresaUtls.blutls(sex)||!StoresaUtls.blutls(phone)||!StoresaUtls.blutls(price)||!StoresaUtls.blutls(zuowei)){
            log.error("数据错误——/app/index/reservation");
            return fail;
        }//商品不为空 — 检查商品合法性
        if (StoresaUtls.blutls(carinfo)) {
            if (!StoreSQLoperation.booleangoods(carinfo, bizid,storeGoodsService)) {return fail; }
        }
        //=======storeOrderinfo=====================================================================================================//
        StoreOrderinfo storeOrderinfo = new StoreOrderinfo();
        storeOrderinfo.setStatus("2");
        storeOrderinfo.setGender(sex);
        storeOrderinfo.setPeoplenum(people);;
        storeOrderinfo.setUserid(userid);
        storeOrderinfo.setArrivaltime(Timestamp.valueOf(data+" "+time+":00"));
        storeOrderinfo.setOdtype("4");//1 外卖，2 自提，3 在店消费 4 预约到店
        storeOrderinfo.setName(name);
        storeOrderinfo.setPhone(phone);
        storeOrderinfo.setBizid(bizid);
        storeOrderinfo.setAddress(storeInfo.getAddress());
        if (zuowei.equals("0")) {
            storeOrderinfo.setTabtype("2");//散座
        }
        if (zuowei.equals("1")) {
            storeOrderinfo.setTabtype("1");//包间
        }
        if (zuowei.equals("2")) {
            storeOrderinfo.setTabtype("3");//混合
        }
        storeOrderinfo.setSubscription(Float.valueOf(preprice));
        storeOrderinfo.setClientsts("1");//客户删除:1---未删除， 2----已删除
        storeOrderinfo.setEntersts("1"); //商家删除:1---未删除， 2---已删除
        if (!StoresaUtls.blutls(beizhu)) {
            storeOrderinfo.setNotes("此用户没有填写备注");
        }else {
            storeOrderinfo.setNotes(beizhu);
        }
        storeOrderinfo.setIncome(Float.valueOf(price));//付款的总价
        storeOrderinfo.setPrepay("3");//1--预付， 2---货到付，用于识别到付
        String orderno = StoreDateutil.getnumber()+StoreDateutil.getrandom();
        storeOrderinfo.setOrderno(orderno);//日期加随机数组成编号
        storeOrderinfo.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeOrderinfoService.insert(storeOrderinfo);
        //==storeOrderinfo==end===================================================================================================//
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderno",orderno);
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        String orderid = storeOrderinfoList.get(0).getId();
        QueryParamEntity queryParamEntity1 = new QueryParamEntity().and("bizid",bizid).and("userid",userid);
        List<StoreShopcart> storeShopcartList = storeShopcartService.select(queryParamEntity1);
        String cartid = storeShopcartList.get(0).getId();
        //将接收到的字符串转化成JSON数组实例：
        if (StoresaUtls.blutls(carinfo)) {
            //添加到订单商品表，并且删除购物车对应数据
            if (!insetgoods(carinfo,orderid,cartid)){return fail;}
        }
        return success;
    }

}
