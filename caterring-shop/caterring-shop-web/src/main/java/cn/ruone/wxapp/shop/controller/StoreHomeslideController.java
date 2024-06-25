package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import io.swagger.annotations.*;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zeroturnaround.zip.transform.ByteArrayZipEntryTransformer;

import static org.hswebframework.web.controller.message.MapResponseMessage.ok;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@RestController

@Api(tags = "数据库广告操作", value = "数据")
public class StoreHomeslideController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());//log日志
    private StoreHomeslideService storeHomeslideService;
    private StoreShopenvironService storeShopenvironService;
    private StoreInfoService storeInfoService;
    private StoreGoodsService storeGoodsService;
    @Autowired
    private void setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreShopenvironService(StoreShopenvironService storeShopenvironService){
        this.storeShopenvironService = storeShopenvironService;
    }
    @Autowired
    public void setStoreHomeslideService(StoreHomeslideService storeHomeslideService){
        this.storeHomeslideService = storeHomeslideService;
    }

    @RequestMapping(value = "/app/index/StoreAd" ,method = RequestMethod.GET)
    @ApiOperation(value = "查询数据", notes = "id查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>>  select(HttpServletRequest request, HttpServletResponse response){
                String bizid = request.getParameter("bizid");
                if (!StoresaUtls.blutls(bizid)){
                    log.error("数据错误");
                    return null;
                }
                QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
                List<StoreHomeslide> storeHomeslideList = storeHomeslideService.select(queryParamEntity);//List集合
                ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
                for(StoreHomeslide storeHomeslide:storeHomeslideList) {
                       HashMap hash = new HashMap();
                       hash.put("logo", storeHomeslide.getPic());
                       arrayList.add(hash);
                 }
                return arrayList;
    }

    //餐厅环境轮播
    @RequestMapping(value = "/app/index/cantin" ,method = RequestMethod.GET)
    @ApiOperation(value = "餐厅环境轮播", notes = "id查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap  cantin(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreShopenviron> storeShopenvironList = storeShopenvironService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        ArrayList<String> img = new ArrayList<>();//定义一个接受数组数组
        for (StoreShopenviron storeShopenviron : storeShopenvironList){
            img.add(storeShopenviron.getPic());
        }
        hashMap.put("banner",img);
        return hashMap;
    }

    //商家首页轮播
    @RequestMapping(value = "/app/index/oneTwo" ,method = RequestMethod.GET)
    @ApiOperation(value = "商家首页轮播", notes = "id查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap  oneTwo(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreHomeslide> storeHomeslideList = storeHomeslideService.select(queryParamEntity);//List集合
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        HashMap hashMap = new HashMap();
        ArrayList<String> img = new ArrayList<>();//定义一个接受数组数组
        for(StoreHomeslide storeHomeslide:storeHomeslideList) {
            img.add(storeHomeslide.getPic());
        }
        hashMap.put("banner",img );
        hashMap.put("name",storeInfo.getName());//商家名称
        hashMap.put("starttime1",storeInfo.getStarttime1().substring(0,5));
        hashMap.put("endtime1",storeInfo.getEndtime1().substring(0,5));
        hashMap.put("starttime2",storeInfo.getStarttime2().substring(0,5));
        hashMap.put("endtime2",storeInfo.getEndtime2().substring(0,5));
        hashMap.put("address",storeInfo.getAddress()); //地址
//        hashMap.put("img1","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1806358550,3318775299&fm=200&gp=0.jpg");  //扫码点餐图片
//        hashMap.put("img2","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3295881961,2615262410&fm=200&gp=0.jpg"); //外卖点餐图片
//        hashMap.put("img3","https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4134768659,380313032&fm=200&gp=0.jpg");  //店内评论图片
        return hashMap;
    }
    //招牌推荐显示
    @RequestMapping(value = "/app/index/tuijian" ,method = RequestMethod.GET)
    @ApiOperation(value = "招牌推荐显示", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>>  tuijian(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        if (!StoresaUtls.blutls(bizid)) {
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid", bizid).and("recommend","1");
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);

        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreGoods storeGoods : storeGoodsList){
            HashMap hashMap = new HashMap();
            hashMap.put("img",storeGoods.getMiniimage());//推荐图片
            hashMap.put("name",storeGoods.getName()); //商品名称
            hashMap.put("price",storeGoods.getOriginprice());//商品价格
            hashMap.put("pack_money",storeGoods.getPackfee());//商品价格
            hashMap.put("buy",storeGoods.getSoldnum());//已售多少件
            hashMap.put("id",storeGoods.getId());//商品ID
            arrayList.add(hashMap);
        }
        return arrayList;
    }


}
