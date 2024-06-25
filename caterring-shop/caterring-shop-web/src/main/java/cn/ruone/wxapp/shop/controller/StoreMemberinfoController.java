package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.entity.WxappConfig;
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

import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "个人中心",value = "数据库操作")
public class StoreMemberinfoController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreMemberinfoService storeMemberinfoService;
    private StoreUsercouponsService storeUsercouponsService;
    private StoreGoodsService storeGoodsService;
    private StoreGoodstypeService storeGoodstypeService;
    private StoreUserpointService storeUserpointService;
    private WxminiappService wxminiappService;
    private StoreInfoService storeInfoService;
    private StoreShopenvironService storeShopenvironService;
    private StoreWxappconfigService storeWxappconfigService;
    @Autowired
    public void setStoreWxappconfigService(StoreWxappconfigService storeWxappconfigService){
        this.storeWxappconfigService = storeWxappconfigService;
    }
    @Autowired
    public void setWxminiappService(WxminiappService wxminiappService) {
        this.wxminiappService = wxminiappService;
    }
    @Autowired
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreUserpointService(StoreUserpointService storeUserpointService){
        this.storeUserpointService = storeUserpointService;
    }
    @Autowired
    public void  setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }
    @Autowired
    public void setStoreGoodstypeService(StoreGoodstypeService storeGoodstypeService){
        this.storeGoodstypeService = storeGoodstypeService;
    }
    @Autowired
    public void setStoreUsercouponsService(StoreUsercouponsService storeUsercouponsService){
        this.storeUsercouponsService = storeUsercouponsService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreShopenvironService(StoreShopenvironService storeShopenvironService){
        this.storeShopenvironService = storeShopenvironService;
    }

    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }
    @RequestMapping(value = "/app/index/Openid", method = RequestMethod.GET)
    @ApiOperation(value = "个人中心-登录者及头像",notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "i",value = "商家编号",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "code",value = "用户编号",required = true,dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public Map Openidselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String code = request.getParameter("code");
        Map OpenidMap = wxminiappService.getSessioninfo(bizid,code);
        HashMap OpenidhashMap = new HashMap();
//        OpenidhashMap.put("access_token",sessionId);
        OpenidhashMap.put("openid",OpenidMap.get("openid"));
        OpenidhashMap.put("session_key",OpenidMap.get("session_key"));
        return OpenidhashMap;
    }
    //个人中心-用户登录
    @RequestMapping(value = "/app/index/login", method = RequestMethod.GET)
    @ApiOperation(value = "个人中心-用户登录",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap loginselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String openid = request.getParameter("openid");
        if (!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(openid)){
            response.setStatus(400);
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",bizid).and("openid",openid);
        List<StoreMemberinfo> storeMemberinfoList = storeMemberinfoService.select(queryParamEntity);
        if (storeMemberinfoList.isEmpty()){
            StoreMemberinfo storeMemberinfo = new StoreMemberinfo();
            storeMemberinfo.setOpenid(openid);
            storeMemberinfo.setBizid(bizid);
            storeMemberinfo.setScore("0");
            storeMemberinfo.setWallet(0);
            storeMemberinfo.setMemberadmin(0);
            storeMemberinfo.setStarttime(Timestamp.valueOf(StoreDateutil.getStringDate()));
            storeMemberinfoService.insert(storeMemberinfo);
            HashMap hashMap = new HashMap();
            hashMap.put("openid",openid);
            hashMap.put("uniacid",bizid);
            return hashMap;
        }
        HashMap loginhashMap = new HashMap();
        if (!storeMemberinfoList.isEmpty() && storeMemberinfoList.size() == 1){
            for (StoreMemberinfo storeMemberinfo : storeMemberinfoList) {
                String images = storeMemberinfo.getImage();
                String names = storeMemberinfo.getName();
                if ((images == null) || (names == null)) {
                    String image = request.getParameter("img");
                    String name = request.getParameter("name");
                    String userid = storeMemberinfo.getId();
                    storeMemberinfo.setImage(image);
                    storeMemberinfo.setName(name);
                    storeMemberinfoService.updateByPk(userid, storeMemberinfo);
                }
                loginhashMap.put("id", storeMemberinfo.getId()); //登录用户的id
                loginhashMap.put("name", storeMemberinfo.getName());//登录用户的姓名
                loginhashMap.put("join_time", storeMemberinfo.getTelephone());
                loginhashMap.put("img", storeMemberinfo.getImage());//图片
                loginhashMap.put("openid", storeMemberinfo.getOpenid());
                loginhashMap.put("total_score", storeMemberinfo.getScore());//积分
                loginhashMap.put("wallet", storeMemberinfo.getWallet());//钱包
//                loginhashMap.put("commission", "0.00");
//                loginhashMap.put("day","0");
//                loginhashMap.put("order_money", "");
//                loginhashMap.put("order_number", "0");
//                loginhashMap.put("dq_time", "");
//                loginhashMap.put("user_name", "");
//                loginhashMap.put("user_tel", "");
//                loginhashMap.put("hy_day", "0");
                loginhashMap.put("uniacid",storeMemberinfo.getBizid());

            }
        }
        return loginhashMap;
    }
    @RequestMapping(value = "/app/index/UserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "个人中心",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    //个人中心——页面信息  及 //“个人中心”内“登录者及头像”中“会员卡”
    public HashMap memberinfo(HttpServletRequest request, HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String userid = request.getParameter("user_id");
        if(!StoresaUtls.blutls(userid)) {
            log.error("数据错误—/app/index/UserInfo");
            return null;
        }
        StoreMemberinfo storeMemberinfo =  storeMemberinfoService.selectByPk(userid);
        //对收到的请求进行判断 如果storeMemberinfoList=0则用户还没有登录
        HashMap memberinhashMap = new HashMap();
        if (storeMemberinfo != null) {
            memberinhashMap.put("admin", Integer.valueOf(storeMemberinfo.getMemberadmin())); //"": 0, //是否为管理员   1是  0不是   //1---设置为管理员，0—不设置为管理员
            memberinhashMap.put("id", storeMemberinfo.getId()); //登录用户的id
            memberinhashMap.put("name", storeMemberinfo.getName());//登录用户的姓名
            memberinhashMap.put("join_time", storeMemberinfo.getTelephone());
            memberinhashMap.put("img", storeMemberinfo.getImage());//图片
            memberinhashMap.put("openid", storeMemberinfo.getOpenid());
            memberinhashMap.put("total_score", storeMemberinfo.getScore());//积分
            memberinhashMap.put("wallet", storeMemberinfo.getWallet());//钱包
//            memberinhashMap.put("commission", "0.00");
//            memberinhashMap.put("day", "0");
//            memberinhashMap.put("order_money", "");
//            memberinhashMap.put("order_number", "0");
            memberinhashMap.put("uniacid", storeMemberinfo.getBizid());
//            memberinhashMap.put("dq_time", "");
//            memberinhashMap.put("user_name", "");
//            memberinhashMap.put("user_tel", "");
//            memberinhashMap.put("hy_day", "0");
        }
        return memberinhashMap;

    }

    //loginindex.js 页面（登录前）
    //版权
    //收货地址-手动添加-onload()第一个
    //在线客服/app/index/system
    @RequestMapping(value = "/app/index/system", method = RequestMethod.GET)
    @ApiOperation(value = "个人中心-用户登录",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = Void.class)
    })
    public HashMap loginindex(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }

        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreShopenviron> storeShopenvironList = storeShopenvironService.select(queryParamEntity);
        List<StoreWxappconfig> storeWxappconfigList = storeWxappconfigService.select(queryParamEntity);
        if (storeWxappconfigList.isEmpty()){
            return null;
        }
        ArrayList<String> gsimg =new  ArrayList<String>();
        for (StoreShopenviron storeShopenviron : storeShopenvironList){
            gsimg.add(storeShopenviron.getPic());
        }
        WxappConfig wxappConfig =  wxminiappService.getWxappConfig(shopid);
        String mapkey = wxappConfig.getMapkey();
        HashMap index = new HashMap();
        index.put("id",storeInfo.getId());
        index.put("appid",storeWxappconfigList.get(0).getAppid());
//        index.put("appsecret","1f254b7f9362b2e1727f57f4c0b9c2b3");
        index.put("url_name",storeInfo.getName());
//        index.put("details","<p>订单</p>");
        index.put("url_logo",storeInfo.getLogo());
        index.put("color",storeInfo.getColor4());
//        index.put("link_name","");
//        index.put("link_logo","");
//        index.put("model","2");
        index.put("default_store",storeInfo.getShopid());
//        index.put("support", "外卖老大");
//        index.put("bq_logo", "https://sysoss.360yingketong.com/images/12486/2018/06/J78at1OZdOm8ivAx67gODV7Xao1SOM.png");
//        index.put("bq_name", "宁夏强三特色美食");
        index.put("map_key",mapkey);//接口
//        index.put("tz_appid","");
//        index.put("tz_name","");
//        index.put("tel","09532253994");
//        index.put("dada_key","");
//        index.put("dada_secret","");
//        index.put("is_psxx","1");
//        index.put("jfgn","2");
//        index.put("msgn","2");
//        index.put("fxgn","2");
        index.put("uniacid",storeInfo.getShopid());
//        index.put("typeset", "1");
//        index.put("wm_name","菜单");
//        index.put("dc_name","");
//        index.put("yd_name","");
//        index.put("gs_img",gsimg);
//        index.put("gs_details","");
        index.put("gs_tel",storeInfo.getTelephone());
//        index.put("gs_time","07：00-23：00");
//        index.put("gs_add","吴忠市利通区吴灵西路256号");
//        index.put("gs_zb","37.990670,106.198100");
//        index.put("is_brand","1");
//        index.put("kfw_appid","gh_135ec6208295");
//        index.put("kfw_appsecret","1f254b7f9362b2e1727f57f4c0b9c2b3");
//        index.put("fl_more","1");
//        index.put("tx_zdmoney","0.00");
//        index.put("tx_notice","");
//        index.put("is_tj","2");
//        index.put("is_mdrz","2");
//        index.put("md_sh","0");
//        index.put("md_sf","1");
//        index.put("rz_details","");
//        index.put("rz_title","强三");
//        index.put("rz_ms","");
//        index.put("countdown","5");
//        index.put("distance","10000");
//        index.put("integral","0");
//        index.put("integral2","0");
//        index.put("is_jf","2");
//        index.put("fx_title","");
//        index.put("is_hy","2");
//        index.put("hygn","2");
//        index.put("hy_discount","0");
//        index.put("hy_details","");
//        index.put("kt_details","");
//        index.put("cz_notice","");
//        index.put("is_cz","2");
//        index.put("hy_note","");
//        index.put("is_yuepay","2");
//        index.put("ps_name","");
//        index.put("is_tzms","2");
//        index.put("is_wx","1");
//        index.put("is_yhk","1");
//        index.put("is_sj","1");
//        index.put("is_dada","1");
//        index.put("is_kfw","1");
//        index.put("is_pt","1");
//        index.put("sh_time","0");
//        index.put("attachurl","http://yktwe7.oss-cn-hangzhou.aliyuncs.com/");
        return index;
    }


}
