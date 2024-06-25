package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.StoreBookmark;
import cn.ruone.wxapp.shop.api.entity.StoreGoods;
import cn.ruone.wxapp.shop.api.entity.StoreInfo;
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
import java.util.Date;
import java.util.List;

@RestController
@Api(tags ="收藏" ,value = "数据库操作")
public class StoreBookmarkController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreInfoService storeInfoService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreGoodsService storeGoodsService;
    private StoreBookmarkService storeBookmarkService;
    @Autowired
    public void setStoreBookmarkService(StoreBookmarkService storeBookmarkService){
        this.storeBookmarkService = storeBookmarkService;
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
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }

    @RequestMapping(value ="/app/index/Collection",method = RequestMethod.GET)
    @ApiOperation(value = "用户——收藏——添加",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] ollection(HttpServletRequest request, HttpServletResponse response){
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String userid = request.getParameter("user_id");
        String goodsid = request.getParameter("goods_id");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(goodsid)){
            log.error("数据错误——用户——收藏——添加——值为空");
            return fail;
        }
        StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
        if (storeGoods == null || !storeGoods.getBizid().equals(bizid)){
            log.error("非法参数——用户——收藏——添加——值为空");
            return fail;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("userid",userid).and("goodsid",goodsid);
        List<StoreBookmark> storeBookmarkList = storeBookmarkService.select(queryParamEntity);
        if (storeBookmarkList.isEmpty()) {
            StoreBookmark storeBookmark = new StoreBookmark();
            storeBookmark.setUserid(userid);
            storeBookmark.setGoodsid(goodsid);
            storeBookmark.setCollecttime(Timestamp.valueOf(StoreDateutil.getStringDate()));
            storeBookmarkService.insert(storeBookmark);
            return success;
        }
        return fail;
    }

    @RequestMapping(value = "/app/index/Cancelcollection",method = RequestMethod.GET)
    @ApiOperation(value = "用户——收藏——取消收藏",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] cancelcollectiondelect(HttpServletRequest request, HttpServletResponse response){
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String userid = request.getParameter("user_id");
        String goodsid = request.getParameter("goods_id");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(goodsid)){
            log.error("数据错误——用户——收藏——取消——值为空");
            return fail;
        }
        StoreGoods storeGoods = storeGoodsService.selectByPk(goodsid);
        if (storeGoods == null || !storeGoods.getBizid().equals(bizid)){
            log.error("非法参数——用户——收藏——取消——值为空");
            return fail;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("userid",userid).and("goodsid",goodsid);
        List<StoreBookmark> storeBookmarkList = storeBookmarkService.select(queryParamEntity);
        if (!storeBookmarkList.isEmpty() && storeBookmarkList.size() == 1){
            String id = storeBookmarkList.get(0).getId();
            storeBookmarkService.deleteByPk(id);
            return success;
        }
        return fail;
    }
}
