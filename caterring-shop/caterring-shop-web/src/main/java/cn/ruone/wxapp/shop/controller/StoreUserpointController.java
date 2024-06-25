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
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "积分类",value = "数据库操作")
public class StoreUserpointController {
    private StoreGoodstypeService storeGoodstypeService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreGoodsService storeGoodsService;
    private StoreOrderinfoService storeOrderinfoService;
    private StoreUserpointService storeUserpointService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private StoreWalletrcdService storeWalletrcdService;
    private StoreInfoService storeInfoService;
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreWalletrcdService(StoreWalletrcdService storeWalletrcdService){
        this.storeWalletrcdService = storeWalletrcdService;
    }
    @Autowired
    public void setStoreOrdergoodsService(StoreOrdergoodsService storeOrdergoodsService){
        this.storeOrdergoodsService = storeOrdergoodsService;
    }
    @Autowired
    public void setStoreGoodstypeService(StoreGoodstypeService storeGoodstypeService){
        this.storeGoodstypeService = storeGoodstypeService;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreGoodsService( StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreUserpointService(StoreUserpointService storeUserpointService){
        this.storeUserpointService = storeUserpointService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }
    @RequestMapping(value = "/app/index/Exchange",method = RequestMethod.GET)
    @ApiOperation(value = "积分商城-商品详情-兑换物品",notes = "兑换物品")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] Exchange(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        String[] success = {"1"};
        String[] fail = {"2"};
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return fail; }
        String userid = request.getParameter("user_id");
        String goodid = request.getParameter("good_id");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(goodid)){
            log.error("数据错误——/app/index/Exchange");
            return fail;
        }
        StoreGoods storeGoods = storeGoodsService.selectByPk(goodid);//根据商品id 获取商品的所有信息
        if (storeGoods == null || !storeGoods.getBizid().equals("")|| !String.valueOf(storeGoods.getOnsale()).equals("1")){
            log.error("非法参数——/app/index/Exchange");
            return fail;
        }
        float mepric = storeGoods.getMemberprice();//拿出兑换商品，即红包金所等于的金额
        String stocknum = storeGoods.getStocknum();//拿到商品所剩数量
        String userpoint = storeGoods.getUserpoint();//兑换商品所需积分
        if (storeGoods.getFlag().equals("2")){//判断兑换的物品是否为虚拟物品，是则执行以下操作
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);//根据用户id查询用户
            float wallet = storeMemberinfo.getWallet();//拿出用户余额
            storeMemberinfo.setWallet(wallet+mepric);//余额+商品所剩金额，两者相加 并更新到用户表
            String score = storeMemberinfo.getScore();//拿出用户所剩积分
            storeMemberinfo.setScore(String.valueOf(Integer.valueOf(score)- Integer.valueOf(userpoint)));//用户积分-商品兑换所需积分，并更新
            storeMemberinfoService.updateByPk(userid,storeMemberinfo);//根据用户id跟新到memberinfo表中

            storeGoods.setStocknum(String.valueOf(Integer.valueOf(stocknum)-1));//商品所剩数量减去1
            storeGoodsService.updateByPk(goodid,storeGoods);//根据商品id跟新到goods表中

//            StoreWalletrcd storeWalletrcd = new StoreWalletrcd();
//            storeWalletrcd.setUserid(userid);
//            storeWalletrcd.setInputtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
//            storeWalletrcd.setOrderno(StoreDateutil.getnumber());//
//            storeWalletrcd.setDeposittype("4");
//            storeWalletrcd.setDescrp("积分兑换");
//            storeWalletrcd.setMoney(+mepric);
//            storeWalletrcd.setStatus("1");
//            storeWalletrcdService.insert(storeWalletrcd);
            String Judge = "4";
            storeWalletrcdService.Walletrefund(userid,StoreDateutil.getnumber(),Float.valueOf(mepric),Judge);
        }
        //storeUserpoint end============================================================================//
        //调用Impl支付积分接口——积分兑换
        storeUserpointService.Paypoints(storeGoods.getFlag(),goodid,userpoint,storeGoods.getName(),userid);
        //storeUserpoint end============================================================================//
        return success;
    }
    //积分商城 ——商品详情
    @RequestMapping(value = "/app/index/JfGoodsInfo", method = RequestMethod.GET)
    @ApiOperation(value = "积分商城—— 商品详情 ",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> JfGoodsInfoselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String id = request.getParameter("id");//StoreDateutil.getStringDate()
        if(!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(id)) {
            log.error("数据错误——/app/index/JfGoodsInfo——积分商城—— 商品详情");
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",bizid).and("id",id).and("userpointflag","1");
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> jfgoodsarrayList = new ArrayList<Map<String,String>>();
        if (!storeGoodsList.isEmpty()) {
            for (StoreGoods storeGoods : storeGoodsList) {
                HashMap jfgoodshashMap = new HashMap();
                jfgoodshashMap.put("id", storeGoods.getId());
                jfgoodshashMap.put("name", storeGoods.getName());//商品名称
                jfgoodshashMap.put("number", storeGoods.getStocknum());//商品剩余数
                jfgoodshashMap.put("integral_money", storeGoods.getUserpoint());  //需要多少积分
                jfgoodshashMap.put("price", storeGoods.getOriginprice());   //价格
                jfgoodshashMap.put("pack_money", storeGoods.getPackfee());   //价格
                //前端1.实2.虚，
                // db 2.实 1.虚
                String flag = storeGoods.getFlag();
                if (Integer.valueOf(flag) == 2) {
                    jfgoodshashMap.put("type", "1");//判断（1兑换成功后将自动为您余额充值   2兑换实物将使用您的收货地址邮寄给您）
                }
                if (Integer.valueOf(flag) == 1) {
                    jfgoodshashMap.put("type", "2");
                }
//                jfgoodshashMap.put("hb_money", "10");
                jfgoodshashMap.put("goods_details", storeGoods.getDescrip());//商品简介
//                jfgoodshashMap.put("process_details", "自己想"); //兑换流程
//                jfgoodshashMap.put("attention_details", "没啥注意的");//注意事项
                jfgoodshashMap.put("img", storeGoods.getMiniimage());//图片
                jfgoodsarrayList.add(jfgoodshashMap);
            }
        }
        return jfgoodsarrayList;
    }

    //积分商城 兑换记录对应表
    @RequestMapping(value = "/app/index/Dhmx", method = RequestMethod.GET)
    @ApiOperation(value = "积分商城——兑换记录",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> Dhmxselect(HttpServletRequest request,HttpServletResponse response){
        String userid = request.getParameter("user_id");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        if(!StoresaUtls.blutls(userid)) {
            log.error("数据错误——/app/index/Dhmx");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid",userid);
        queryParamEntity.setPageIndex(Integer.valueOf(page));//当前页数从零开始
        queryParamEntity.setPageSize(Integer.valueOf(pagesize));//每页数据最大个数
        List<StoreUserpoint> storeUserpointList = storeUserpointService.select(queryParamEntity);
        ArrayList<Map<String,String>> DhmxarrayList = new  ArrayList<Map<String,String>>();
        if (!storeUserpointList.isEmpty()) {
            for (StoreUserpoint storeUserpoint : storeUserpointList) {
                HashMap DhmxhashMap = new HashMap();
                String id = storeUserpoint.getOrderidorgoodsid();
                StoreGoods storeGoods = storeGoodsService.selectByPk(id);
                DhmxhashMap.put("good_img", storeGoods.getMiniimage());//兑换的物品图片
                DhmxhashMap.put("good_name", storeGoods.getName());//兑换的物品名称
                DhmxhashMap.put("time", storeUserpoint.getCreatedtime());//兑换的时间
                DhmxhashMap.put("integral", Integer.valueOf(storeUserpoint.getPoint())); //兑换物品所需的积分
                DhmxarrayList.add(DhmxhashMap);
            }
        }
        return DhmxarrayList;

    }

    // 积分商城-点击任意商品分类-
    @RequestMapping(value = "/app/index/JftypeGoods", method = RequestMethod.GET)
    @ApiOperation(value = "积分商城-点击任意商品分类",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> JftypeGoods(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String typeid = request.getParameter("type_id");//取出id为100
        if(!StoresaUtls.blutls(typeid)) {
            log.error("数据错误——/app/index/JftypeGoods");
            return null;
        }

        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",bizid).and("goodstype",typeid).and("userpointflag","1");
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> JftypearrayList = new  ArrayList<Map<String,String>>();
        if (!storeGoodsList.isEmpty()) {
            for (StoreGoods storeGoods : storeGoodsList) {
                HashMap JftypehashMap = new HashMap();
                JftypehashMap.put("name", storeGoods.getName());//兑换的物品名称
                JftypehashMap.put("money", storeGoods.getUserpoint());//兑换物品所需的积分
                //        前端1.虚  2.实，
                //         db 2..虚  1实
                if (storeGoods.getFlag().equals(2)) {
                    JftypehashMap.put("type", "1");
                }
                if (storeGoods.getFlag().equals(1)) {
                    JftypehashMap.put("type", "2");
                }
                JftypehashMap.put("img", storeGoods.getMiniimage());//兑换的物品图片
                JftypehashMap.put("id", storeGoods.getId()); //兑换的物品id
                JftypearrayList.add(JftypehashMap);
            }
        }
        return JftypearrayList;
    }
    //积分明细
    @RequestMapping(value = "/app/index/Jfmx", method = RequestMethod.GET)
    @ApiOperation(value = "积分明细",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<HashMap<String,String>> userpointselect(HttpServletRequest request, HttpServletResponse response){
        String userid = request.getParameter("user_id");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        if(!StoresaUtls.blutls(userid)) {
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid",userid);
        queryParamEntity.setPageIndex(Integer.valueOf(page));//当前页数从零开始
        queryParamEntity.setPageSize(Integer.valueOf(pagesize));//每页数据最大个数
        List<StoreUserpoint> storeUserpointList = storeUserpointService.select(queryParamEntity);
        ArrayList<HashMap<String,String>> userpointarrayList = new ArrayList<HashMap<String,String>>();
        if (!storeUserpointList.isEmpty()) {
            for (StoreUserpoint storeUserpoint : storeUserpointList) {
                HashMap userpointhashMap = new HashMap();
                userpointhashMap.put("note", storeUserpoint.getName());//记录
                userpointhashMap.put("cerated_time", storeUserpoint.getCreatedtime());//购买时间
                String point = storeUserpoint.getPoint();
                if (Integer.valueOf(point) > 0) {
                    userpointhashMap.put("type", "1");  //1加 2减
                }
                if (Integer.valueOf(point) < 0) {
                    userpointhashMap.put("type", "2");  //1加 2减
                }
                userpointhashMap.put("score", Math.abs(Integer.valueOf(point)));//积分
                userpointarrayList.add(userpointhashMap);
            }
        }
        return userpointarrayList;
    }
    //积分商城 ——商品分类
    @RequestMapping(value = "/app/index/Jftype", method = RequestMethod.GET)
    @ApiOperation(value = "积分商城——商品分类",notes = "查询")
    @ApiImplicitParams({

    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> select(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        if(!StoresaUtls.blutls(bizid)) {
            log.error("数据错误—/app/index/Jftype—积分商城——商品分类");
            return null;
        }
        ArrayList<Map<String,String>> arrayList = new  ArrayList<Map<String,String>>();
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid).and("userpointflag","1");
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);//查询有多少条数据
        Set set =new HashSet();
        List list = new ArrayList();
        for (StoreGoods storeGoods : storeGoodsList){
            list.add(storeGoods.getGoodstype());//将goodstype的值，存入到list中
        }
        set.addAll(list);//出去list中的重复数据

        for(Iterator it = set.iterator();  it.hasNext(); ){
            String id = it.next().toString();
            StoreGoodstype storeGoodstype = storeGoodstypeService.selectByPk(id);
            HashMap hashMap = new HashMap();
            hashMap.put("id",storeGoodstype.getId());//分类商品的id
            hashMap.put("img","");//分类商品的图片
            hashMap.put("name",storeGoodstype.getName());//分类商品的名称
            arrayList.add(hashMap);
        }
        return arrayList;
    }
    //积分商城——商品详情
    @RequestMapping(value = "/app/index/JfGoods", method = RequestMethod.GET)
    @ApiOperation(value = "积分商城——商品详情",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> JfGoods(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        if(!StoresaUtls.blutls(bizid)) {
            log.error("数据错误—/app/index/JfGoods—积分商城——商品分类");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid).and("userpointflag","1");
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new  ArrayList<Map<String,String>>();
        if (!storeGoodsList.isEmpty()) {
            for (StoreGoods storeGoods : storeGoodsList) {
                HashMap hashMap = new HashMap();
                hashMap.put("name", storeGoods.getName());//兑换的物品名称
                hashMap.put("integral_money", storeGoods.getUserpoint());//兑换物品所需的积分
                hashMap.put("price", storeGoods.getOriginprice());//价格
                hashMap.put("pack_money", storeGoods.getPackfee());//价格
                String flag = storeGoods.getFlag();
                if (Integer.valueOf(flag) == 2) {
                    hashMap.put("type", "1");//判断（1兑换成功后将自动为您余额充值   2兑换实物将使用您的收货地址邮寄给您）
                }
                if (Integer.valueOf(flag) == 1) {
                    hashMap.put("type", "2");
                }
                hashMap.put("img", storeGoods.getMiniimage());//兑换的物品图片
                hashMap.put("id", storeGoods.getId()); //兑换的物品id
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

}
