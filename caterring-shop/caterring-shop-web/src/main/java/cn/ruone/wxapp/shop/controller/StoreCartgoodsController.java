package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import io.swagger.annotations.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
@Api(tags ="购物车" ,value = "数据库操作")
public class StoreCartgoodsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreCartgoodsService storeCartgoodsService;
    private StoreShopcartService storeShopcartService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreInfoService storeInfoService;
    private StoreGoodsService storeGoodsService;
    @Autowired
    public void setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;

    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoServic){
      this.storeInfoService = storeInfoServic;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreCartgoodsService(StoreCartgoodsService storeCartgoodsService){
        this.storeCartgoodsService = storeCartgoodsService;
    }
    @Autowired
    public void setStoreShopcartService(StoreShopcartService storeShopcartService){
        this.storeShopcartService = storeShopcartService;
    }
    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }
    @RequestMapping(value = "/app/index/shoppingCart",method = RequestMethod.POST)
    @ApiOperation(value = "购物车——数据",notes = "收取购物车数据")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] shoppingCart(HttpServletRequest request, HttpServletResponse response){
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return fail; }
        String userid = request.getParameter("user_id");
        String carinfo = request.getParameter("carinfo");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(carinfo)){
            log.error("数据错误—/app/index/shoppingCart—getParameter");
            return fail;
        }
        //检查商品合法性
        JSONArray  carinfoJSONArray = JSONArray.fromObject(carinfo);
        if (carinfoJSONArray.size() != 0) {
            for (int i = 0; i < carinfoJSONArray.size(); i++) {
                JSONObject carinfojob = carinfoJSONArray.getJSONObject(i);
                String goodid = (String) carinfojob.get("id");
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodid);
                if (storeGoods == null || !storeGoods.getBizid().equals(bizid) || !String.valueOf(storeGoods.getOnsale()).equals("1")) {
                    log.error("非法参数—/app/index/shoppingCart—getParameter");
                    return fail;
                }
            }
        }

        //===Shopcart表==============================================================================================//
        //率先判断购物车表中是否有存在购物车，判断条件 bizid && userid ==null
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",bizid).and("userid",userid);
        List<StoreShopcart> storeShopcartList = storeShopcartService.select(queryParamEntity);
        if (storeShopcartList.isEmpty()){
            StoreShopcart storeShopcart = new StoreShopcart();
            storeShopcart.setBizid(bizid);
            storeShopcart.setUserid(userid);
            storeShopcart.setVacuum(1);//vacuum表示购物车是否为空，1--为空， 0---非空,第一次添加默认购物车为空
            storeShopcartService.insert(storeShopcart);
        }
        //===Shopcart表==end=========================================================================================//
        QueryParamEntity queryParamEntitycart = new QueryParamEntity().and("bizid",bizid).and("userid",userid);
        List<StoreShopcart> storeShopcartLists = storeShopcartService.select(queryParamEntitycart);
        String cartid = storeShopcartLists.get(0).getId();

        //将接收到的字符串转化成JSON数组实例：
        if (carinfoJSONArray.size() == 0){//carinfoJSONArray==0将Shopcart表Vacuum字段设为1即购物车为空
            StoreShopcart storeShopcart = storeShopcartService.selectByPk(cartid);
            storeShopcart.setVacuum(1);
            storeShopcartService.updateByPk(cartid,storeShopcart);
            QueryParamEntity querycart1 = QueryParamEntity.single("cartid", cartid);
            List<StoreCartgoods> storeCartgoodsList1 = storeCartgoodsService.select(querycart1);
            if (!storeCartgoodsList1.isEmpty()) {//购物车原本不为空
                for (StoreCartgoods storeCartgoods : storeCartgoodsList1) {//查询原有购物车有多少条数据依次进行删除
                    String deletid = storeCartgoods.getId();
                    storeCartgoodsService.deleteByPk(deletid);
                }
            }
        }
        if (carinfoJSONArray.size() != 0) {
            QueryParamEntity querycart = QueryParamEntity.single("cartid", cartid);
            List<StoreCartgoods> storeCartgoodsList = storeCartgoodsService.select(querycart);
            if (!storeCartgoodsList.isEmpty()) {//购物车原本不为空
                for (StoreCartgoods storeCartgoods : storeCartgoodsList) {//查询原有购物车有多少条数据依次进行删除
                    String deletid = storeCartgoods.getId();
                    storeCartgoodsService.deleteByPk(deletid);
                }
            }
            //遍历 jsonarray 数组，把每一个对象转成 json 对象实例：
            for (int i = 0;i<carinfoJSONArray.size();i++) {
                JSONObject carinfojob = carinfoJSONArray.getJSONObject(i);
                //得到 每个对象中的属性值实例：
                String goodid = (String) carinfojob.get("id");
                Integer num = (Integer) carinfojob.get("num");
                StoreCartgoods storeCartgoods = new StoreCartgoods();
                storeCartgoods.setCartid(cartid);
                storeCartgoods.setGoodsid(goodid);
                storeCartgoods.setFlavorid("1");//规格暂时默认为1
                storeCartgoods.setNum(String.valueOf(num));
                storeCartgoods.setAddtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeCartgoodsService.insert(storeCartgoods);
            }
        }

        return success;
    }

    @RequestMapping(value = "/app/index/myShoppingCart",method = RequestMethod.GET)
    @ApiOperation(value = "购物车——数据—myShoppingCart",notes = "my购物车")
    @ApiImplicitParams({

    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> myShoppingCart(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(userid)){
            log.error("数据错误—/app/index/myShoppingCart");
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("userid",userid).and("bizid",bizid);
        List<StoreShopcart> storeShopcartList = storeShopcartService.select(queryParamEntity);
        String cartid = storeShopcartList.get(0).getId();
        QueryParamEntity queryParamEntity1 = QueryParamEntity.single("cartid",cartid);
        List<StoreCartgoods> storeCartgoodsList = storeCartgoodsService.select(queryParamEntity1);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storeCartgoodsList.isEmpty()) {
            for (StoreCartgoods storeCartgoods : storeCartgoodsList) {
                String goodsid = storeCartgoods.getGoodsid();
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                HashMap hashMap = new HashMap();
                hashMap.put("img", storeGoods.getMiniimage());
                hashMap.put("id", goodsid);
                hashMap.put("num", Integer.valueOf(storeCartgoods.getNum()));
                hashMap.put("price", storeGoods.getOriginprice());
                hashMap.put("name", storeGoods.getName());
                hashMap.put("pack_money", storeGoods.getPackfee());
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

}
