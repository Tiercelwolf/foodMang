package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import io.swagger.annotations.*;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hswebframework.web.controller.message.ResponseMessage.ok;

@RestController
@Api(tags = "推荐操作/商品分类/商品详细信息/产品规格",value = "数据库推荐操作")
public class StoreGoodsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreGoodsService storeGoodsService;
    private StoreGoodstypeService storeGoodstypeService;
    private StoreGoodsflavorService storeGoodsflavorService;
    private StoreInfoService storeInfoService;
    private StoreGoodspicsService storeGoodspicsService;
    private StoreBookmarkService storeBookmarkService;
    @Autowired
    public void setStoreBookmarkService(StoreBookmarkService storeBookmarkService){
        this.storeBookmarkService = storeBookmarkService;
    }
    @Autowired
    public void setStoreGoodspicsService(StoreGoodspicsService storeGoodspicsService){
        this.storeGoodspicsService = storeGoodspicsService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }
    @Autowired
    public void  setStoerGoodstypeService( StoreGoodstypeService storeGoodstypeService){
        this.storeGoodstypeService = storeGoodstypeService;
    }
    @Autowired
    public void setStoreGoodsflavorService(StoreGoodsflavorService storeGoodsflavorService){
        this.storeGoodsflavorService = storeGoodsflavorService;
    }
    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }
    //商家详情
    @RequestMapping(value = "/app/index/TjGoods", method = RequestMethod.GET)
    @ApiOperation(value = "推荐商品信息",notes = "查询")
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
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreGoods storeGoods : storeGoodsList){
            HashMap hashMap = new HashMap();
            hashMap.put("name",storeGoods.getName());
            hashMap.put("logo",storeGoods.getMiniimage());
            hashMap.put("money",storeGoods.getSiteprice());
            hashMap.put("money2",storeGoods.getOriginprice());
            hashMap.put("sales",storeGoods.getSoldnum());
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    //商品分类DishesType
    @RequestMapping(value = "/app/index/DishesType", method = RequestMethod.GET)
    @ApiOperation(value = "商品分类",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>>  StoerGoodstype(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误/app/index/DishesType");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreGoodstype> storeGoodstypeList  = storeGoodstypeService.select(queryParamEntity);
        ArrayList<Map<String,String>> goodstypearrayList = new ArrayList<Map<String,String>>();
        for (StoreGoodstype storeGoodstype : storeGoodstypeList){
            HashMap goodstypehashMap = new HashMap();
            goodstypehashMap.put("id",storeGoodstype.getId());//点击时的id数据
            goodstypehashMap.put("type_name",storeGoodstype.getName());//热销特色名字
            goodstypehashMap.put("good","");
            goodstypearrayList.add(goodstypehashMap);
        }
        return goodstypearrayList;

    }

    //商品详细信息Dishes
    @RequestMapping(value = "/app/index/Dishes", method = RequestMethod.GET)
    @ApiOperation(value = "商品详细信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> dishesTypeselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String goodstype = request.getParameter("id");
        if (!StoresaUtls.blutls(goodstype)){
            log.error("数据错误——/app/index/Dishes");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("goodstype",goodstype);
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreGoods storeGoods : storeGoodsList){
            HashMap hashMap = new HashMap();
            hashMap.put("id",storeGoods.getId());//商品id
            hashMap.put("name",storeGoods.getName());//名称 特色热牛肉
            String typeid = storeGoods.getGoodstype();
            StoreGoodstype storeGoodstype = storeGoodstypeService.selectByPk(typeid);
            hashMap.put("type_id",storeGoodstype.getId());//
//            hashMap.put("label_id","18");//
            hashMap.put("logo",storeGoods.getMiniimage());//地址
            hashMap.put("money",storeGoods.getOriginprice());//现价
//            hashMap.put("money2","80.00");//原价
            hashMap.put("vip_money",storeGoods.getMemberprice());//
            hashMap.put("dn_money",storeGoods.getSiteprice());//
            hashMap.put("is_show",storeGoods.getOnsale());//
            hashMap.put("inventory",storeGoods.getStocknum());//条件判断
            hashMap.put("content",storeGoods.getDescrip());//描述
            String goodsid = storeGoods.getId();
            StoreGoodspics stoerGoodspics = storeGoodspicsService.selectByPk(goodsid);
            if (stoerGoodspics == null){
                response.setStatus(400);
                log.error("数据错误");
                return null;
            }
            hashMap.put("details",stoerGoodspics.getGoodspic1());//
            hashMap.put("sales",storeGoods.getSoldnum());//月销多少笔
//            hashMap.put("num","2");//
            hashMap.put("is_gg","");//条件判断
            hashMap.put("is_hot",storeGoods.getHot());//
//            hashMap.put("is_zp","");//是否显示招牌
            hashMap.put("store_id",storeGoods.getBizid());//
            hashMap.put("uniacid",storeGoods.getBizid());//
//            hashMap.put("type","3");//写死
//            hashMap.put("quantity","0");//条件判断
            hashMap.put("box_money",storeGoods.getPackfee());//
            hashMap.put("is_new",storeGoods.getNewer());//
//            hashMap.put("is_tj","0");//写死
            hashMap.put("restrict_num",storeGoods.getMaxnum());//限购
            hashMap.put("start_num",storeGoods.getMinnum());//多少数量起售
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    @RequestMapping(value = "/app/index/class", method = RequestMethod.GET)
    @ApiOperation(value = "菜品分类",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> cpfl(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误——/app/index/class");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreGoodstype> stoerGoodstypeList  = storeGoodstypeService.select(queryParamEntity);
        ArrayList<Map<String,String>> goodstypearrayList = new ArrayList<Map<String,String>>();
        for (StoreGoodstype storeGoodstype : stoerGoodstypeList){
            HashMap goodstypehashMap = new HashMap();
            goodstypehashMap.put("id",storeGoodstype.getId());//点击时的id数据
            goodstypehashMap.put("name",storeGoodstype.getName());//热销特色名字
            goodstypehashMap.put("sequ",storeGoodstype.getSequ());//热销特色名字
            goodstypearrayList.add(goodstypehashMap);
        }
        return goodstypearrayList;
    }
    @RequestMapping(value = "/app/index/classIndexInfo", method = RequestMethod.GET)
    @ApiOperation(value = "菜品分类——商品",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> goodsinfo(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String goodstype = request.getParameter("type_id");
        if (!StoresaUtls.blutls(bizid)|| !StoresaUtls.blutls(goodstype)){
            log.error("数据错误——/app/index/classIndexInfo");
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("goodstype",goodstype).and("bizid",bizid);
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storeGoodsList.isEmpty()) {
            for (StoreGoods storeGoods : storeGoodsList) {
                HashMap hashMap = new HashMap();
                hashMap.put("img", storeGoods.getMiniimage());
                hashMap.put("name", storeGoods.getName());
                hashMap.put("price", storeGoods.getOriginprice());
                hashMap.put("id", storeGoods.getId());
                hashMap.put("pack_money", storeGoods.getPackfee());
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }
    @RequestMapping(value = "/app/index/detail", method = RequestMethod.GET)
    @ApiOperation(value = "商品详细信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap detail(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String goodsid = request.getParameter("id");
        String userid = request.getParameter("userid");
        if (!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(goodsid)||!StoresaUtls.blutls(userid)){
            log.error("数据错误/app/index/detail");
            return null;
        }
        StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
        if (storeGoods == null || !storeGoods.getBizid().equals(bizid)){
            log.error("非法参数/app/index/detail");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("goodsid",goodsid);
        List<StoreGoodspics> storeGoodspicsList = storeGoodspicsService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        ArrayList<String> annerarrayList = new ArrayList<>();//定义一个接受数组数组
        for(StoreGoodspics storeGoodspics :storeGoodspicsList){
            annerarrayList.add(storeGoodspics.getGoodspic1());
        }
        QueryParamEntity queryParamEntity1 = new QueryParamEntity().and("userid",userid).and("goodsid",goodsid);
        List<StoreBookmark> storeBookmarkList = storeBookmarkService.select(queryParamEntity1);
        if (storeBookmarkList.isEmpty()){
            hashMap.put("isClick","2");
        }
        if (!storeBookmarkList.isEmpty()){
            hashMap.put("isClick","1");
        }
        hashMap.put("name",storeGoods.getName());//商品名称
        hashMap.put("id",storeGoods.getId());//商品名称
        hashMap.put("pack_money", storeGoods.getPackfee());//商品名称
        hashMap.put("img",storeGoods.getMiniimage());//商品名称
        hashMap.put("price",storeGoods.getOriginprice()); //商品价格
        hashMap.put("numnumber",storeGoods.getSoldnum());//销量
        hashMap.put("sjname",storeInfo.getName());//商家名称
        hashMap.put("miaoshu",storeGoods.getDescrip());//商品的描述
        hashMap.put("banner",annerarrayList);
        hashMap.put("detailImg",annerarrayList);
        return hashMap;
    }
    @RequestMapping(value = "/app/index/inputKeyword", method = RequestMethod.GET)
    @ApiOperation(value = "商家——搜索商品",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String, String>> AllList(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String input = request.getParameter("input");
        if (!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(input)){
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = new  QueryParamEntity().and("bizid",bizid).and("flag","1");
        queryParamEntity.where("name","like","%"+input+"%");
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storeGoodsList.isEmpty()) {
            for (StoreGoods storeGoods : storeGoodsList) {
                HashMap hashMap = new HashMap();
                hashMap.put("img", storeGoods.getMiniimage());
                hashMap.put("price", storeGoods.getOriginprice());
                hashMap.put("name", storeGoods.getName());
                hashMap.put("id", storeGoods.getId());
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }
    @RequestMapping(value = "/app/index/collections", method = RequestMethod.GET)
    @ApiOperation(value = "用户——收藏商品",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String, String>> collectionsselect0(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String userid = request.getParameter("user_id");//page=0&pagesize=10
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        if (!StoresaUtls.blutls(userid)){
            log.error("收藏商品——userID为空");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid",userid);
        queryParamEntity.setPageSize(Integer.valueOf(pagesize));
        queryParamEntity.setPageIndex(Integer.valueOf(page));
        List<StoreBookmark> storeBookmarkList = storeBookmarkService.select(queryParamEntity);
        ArrayList<Map<String, String>> arrayList = new  ArrayList<Map<String, String>>();
        if (!storeBookmarkList.isEmpty()) {
            for (StoreBookmark storeBookmark : storeBookmarkList) {
                String goodsid = storeBookmark.getGoodsid();
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
                HashMap hashMap = new HashMap();
                hashMap.put("img", storeGoods.getMiniimage());
                hashMap.put("name", storeGoods.getName());
                hashMap.put("price", storeGoods.getOriginprice());
                hashMap.put("id", storeGoods.getId());
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }
}
