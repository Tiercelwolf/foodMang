package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hswebframework.web.controller.message.MapResponseMessage.ok;

@RestController
@Api(tags = "商家信息",value="数据库接口")
public class StoreInfoController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());//log日志
    private StoreInfoService storeInfoService;
    private StoreFunctionlistService storeFunctionlistService;
    private StoreHomeslideService storeHomeslideService;
    private StoreShopenvironService storeShopenvironService;
    private StoreDeliveryrulesService storeDeliveryrulesService;
    private StoretemplsService storetemplsService;
    private StoreNavtmplService storeNavtmplService;
    private StoreNavitemService storeNavitemService;
    @Autowired
    public void setStoreNavitemService(StoreNavitemService storeNavitemService){
        this.storeNavitemService = storeNavitemService;
    }
    @Autowired
    public void setStoreNavtmplService(StoreNavtmplService storeNavtmplService){
        this.storeNavtmplService = storeNavtmplService;
    }
    @Autowired
    public void setStoretemplsService(StoretemplsService storetemplsService){
        this.storetemplsService = storetemplsService;
    }
    @Autowired
    public void setStoreDeliveryrules(StoreDeliveryrulesService storeDeliveryrulesService){
        this.storeDeliveryrulesService = storeDeliveryrulesService;
    }
    @Autowired
    public void setStoreShopenvironService(StoreShopenvironService storeShopenvironService){
        this.storeShopenvironService=storeShopenvironService;
    }
    @Autowired
    public void setStoreHomeslideService(StoreHomeslideService storeHomeslideService){
        this.storeHomeslideService = storeHomeslideService;
    }
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService) {
        this.storeInfoService = storeInfoService;
    }

    @Autowired
    public void setStoreFunctionlistService(StoreFunctionlistService storeFunctionlistService) {
        this.storeFunctionlistService = storeFunctionlistService;
    }
     //商家入口——商家中心
    @RequestMapping(value = "/app/index/StoreInfo", method = RequestMethod.GET)
    @ApiOperation(value = "商家信息", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public HashMap select(HttpServletRequest request, HttpServletResponse response) {
        String bizid = request.getParameter("i");
        if(!StoresaUtls.blutls(bizid)) {
            log.error("数据错误——商家信息/app/index/StoreInfo——查询");
            return null;
        }
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);//list1查询的条件 ，list2显示查询的结果

        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid", bizid);
        List<StoreFunctionlist> storeFunctionlistList = storeFunctionlistService.select(queryParamEntity);
//          ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String, String>>();
        HashMap storeInfoindex = new HashMap();
//        storeInfoindex.put("stauts", null);
//        storeInfoindex.put("table_name", null);
//        storeInfoindex.put("type_name", null);
        HashMap storehashMap = new HashMap();
        storehashMap.put("id", storeInfo.getId());
        storehashMap.put("name", storeInfo.getName());
        storehashMap.put("address", storeInfo.getAddress());
        storehashMap.put("time", storeInfo.getStarttime1());
        storehashMap.put("time2", storeInfo.getEndtime1());
        storehashMap.put("time3", storeInfo.getStarttime2());
        storehashMap.put("time4", storeInfo.getEndtime2());
        storehashMap.put("tel", storeInfo.getTelephone());
        storehashMap.put("announcement", storeInfo.getAnnouncement());
        storehashMap.put("is_rest", storeInfo.getStatus());
//        storehashMap.put("img","");
//        storehashMap.put("start_at","0");
//        storehashMap.put("freight","");
        storehashMap.put("logo", storeInfo.getLogo());
        storehashMap.put("details", storeInfo.getIntroduce());
//        storehashMap.put("color","#34AAFF");
        storehashMap.put("coordinates", storeInfo.getLocation());
//        String[] yyzz = {"images/12486/2018/06/G7KpC7lGqCZpIs5DPpipG7dSQQ6Dpm.jpg"};
//        storehashMap.put("yyzz",yyzz);
//        storehashMap.put("version", storeInfo.getVersion());
//        storehashMap.put("md_area", "0");
//        storehashMap.put("md_type", "359");
//        storehashMap.put("sales", "0.0");
//        storehashMap.put("capita", storeInfo.getAvgfee());//写死
//        storehashMap.put("tx_user", "0");
//        storehashMap.put("is_open", "0");
//        storehashMap.put("uniacid", "0");
//        storehashMap.put("number", "0");
//        storehashMap.put("user_id", "0");
//        String[] environment ={"images/12486/2018/06/bu3MLPzhNSM8mN35TwmjCnZL3f0SJY.jpg",
//                "images/12486/2018/06/IzwI65pGnaiV6MD6ajJIMzuXVdJopo.jpg",
//                "images/12486/2018/06/p5X5N8PShnesj8ABN3uj5BWEB5B6lc.jpg",
//                "images/12486/2018/06/js2jeOeYy1FQyoionEOufCaIyuj9oN.jpg",
//                "images/12486/2018/06/ACP9bvb2q5pVoA2h2BG90XPp2275z0.jpg",
//                "images/12486/2018/06/O0eEKI1zJU3KIeZk06REEE9in0i630.jpg"
//        };
//        ArrayList<String> img = new ArrayList<>();//定义一个接受数组数组
//        for(Object object : objectlist){
//                    img.add(storeCommentpic.getImg());
//        }

//        storehashMap.put("environment", environment);
//        storehashMap.put("is_brand", "1");
        storeInfoindex.put("store", storehashMap);
        storehashMap.put("rz_time", storeInfo.getEstabtime());
//        storehashMap.put("rzdq_time","2019-06-27 00:00:00");
//        storehashMap.put("sq_id", "0");
//        storehashMap.put("zm_img", "");
//        storehashMap.put("fm_img", "");
//        storehashMap.put("zf_state", "0");
//        storehashMap.put("code", "");
//        storehashMap.put("link_name", "");
//        storehashMap.put("link_tel", "");
//        storehashMap.put("sq_time", "0000-00-00 00:00:00");
//        storehashMap.put("money", "0.00");
//        storehashMap.put("admin_id", "31");
//        storehashMap.put("is_mp3", "2");
//        storehashMap.put("is_video", "2");
//        storehashMap.put("store_mp3", "");
//        storehashMap.put(" store_video", "");
//        storehashMap.put("store_video", "?vkey=");
//        storehashMap.put("ps_poundage", "");
//        storehashMap.put("qrcode", "");
        storehashMap.put("sttype", storeInfo.getSttype());//平台内部

/**storehashMap-------------------end*/
        String[] reduction = {};
        storeInfoindex.put("reduction", reduction);
//
//
        ArrayList<Map<String,String>> psfarrayList = new ArrayList<Map<String,String>>();
        QueryParamEntity queryParamEntity1 =QueryParamEntity.single("bizid",bizid);
        List<StoreDeliveryrules> storeDeliveryrulesList = storeDeliveryrulesService.select(queryParamEntity1);
        if (!storeDeliveryrulesList.isEmpty()) {
            HashMap psfhashMap = new HashMap();
            psfhashMap.put("money", storeDeliveryrulesList.get(0).getDeliveryfee());
            psfarrayList.add(psfhashMap);
        }
        storeInfoindex.put("psf", psfarrayList);


        HashMap storesethashMap = new HashMap();
//        storesethashMap.put("id", "53");
//        storesethashMap.put( "xyh_money","0.00");
//        storesethashMap.put("xyh_open","2");
//        storesethashMap.put("integral","0");
//        storesethashMap.put("integral2","0");
//        storesethashMap.put("is_jd","2");
//        storesethashMap.put("store_mp3","");
//        storesethashMap.put("store_video","");
//        storesethashMap.put("is_mp3","2");
//        storesethashMap.put("is_video","2");
//        storesethashMap.put("is_jfpay","2");
//        storesethashMap.put("is_yuepay","2");
//        storesethashMap.put("is_yuejf","2");
//        storesethashMap.put("is_wxpay","1");
//        storesethashMap.put("poundage","0");
//        storesethashMap.put("is_pj","1");
//        storesethashMap.put("is_chzf","2");
//        storesethashMap.put("box_name","");
//        storesethashMap.put("yhq_name","");
//        每次StoreFunctionlist所获得的值，是由storeFunctionlistList单次给予
//        第一次循环 nameimg = dn_name
//        第二次循环 nameimg = sy_name
//        第三次循环 nameimg = wm_name
//        第四次循环 nameimg = yy_img
//        for (StoreFunctionlist storeFunctionlist : storeFunctionlistList) {
//            String nameimg = storeFunctionlist.getName();
//            if (nameimg.equals("4")) {
//                storesethashMap.put("sy_name", "收银付款");
//                storesethashMap.put("sy_img", "https://sysoss.360yingketong.com/images/12486/2018/06/a8b3323212bnb918j82KP7r2Br483j.png");
//            } else if (nameimg.equals("1")) {
//                storesethashMap.put("dn_name", "扫码点餐");
//                storesethashMap.put("dn_img", "https://sysoss.360yingketong.com/images/12486/2018/06/n91B5Nki5NW0DM9MnIKwgG5fxBrgNg.png");
//            } else if (nameimg.equals("2")) {
//                storesethashMap.put("wm_name", "菜单点餐");
//                storesethashMap.put("wm_img", "https://sysoss.360yingketong.com/images/12486/2018/08/Y7gEV5keSeGYFYbfj8EEgS7gXtEZGV.png");
//            } else if (nameimg.equals("3")) {
//                storesethashMap.put("yy_name", "预约订座");
//                storesethashMap.put("yy_img", "https://sysoss.360yingketong.com/images/12486/2018/06/E99N5uPqQi5AnLLa9L0AO4tY8y8n3I.png");
//            }
//        }
//        storesethashMap.put("is_yhq","1");
//        storesethashMap.put("is_sy", "1");
//        storesethashMap.put("is_dn", "1");
//        storesethashMap.put("is_wm", "1");
//        storesethashMap.put("is_yy", "1");
//        storesethashMap.put("store_id","53");
//        storesethashMap.put("ps_time","暂时不支持外卖");
//        storesethashMap.put("ps_mode","商家配送");
        if (!storeDeliveryrulesList.isEmpty()) {
            storesethashMap.put("ps_jl", storeDeliveryrulesList.get(0).getDeliveryfee());
        }
//        storesethashMap.put("is_hdfk","2");
//        storesethashMap.put( "print_type","2");
//        storesethashMap.put( "ztxy","<p>暂无</p>");
//        storesethashMap.put("top_style","1");
//        storesethashMap.put("info_style","1");
//        storesethashMap.put("store_id","53");
//        storesethashMap.put("ps_time","暂时不支持外卖");
//        storesethashMap.put("ps_mode","商家配送");
//        storesethashMap.put("ps_jl","1");
//        storesethashMap.put("is_hdfk","2");
//        storesethashMap.put( "print_type","2");
//        storesethashMap.put( "ztxy","<p>暂无</p>");
//        storesethashMap.put("top_style","1");
//        storesethashMap.put("info_style", "1");
//        storesethashMap.put("yysm", "");
//        storesethashMap.put("wmsm", "");
//        storesethashMap.put("dnsm", "");
//        storesethashMap.put("sysm", "");
//        storesethashMap.put("is_wxzf","1");
//        storesethashMap.put("print_mode","1");
//        storesethashMap.put("is_yydc","1");
//        storesethashMap.put("is_zt","1");
//        storesethashMap.put("is_ps","1");
//        storesethashMap.put("is_dd","2");
//        storesethashMap.put("is_cj","2");
//        storesethashMap.put("cj_name","餐具用量");
//        storesethashMap.put("wmps_name","外卖配送");
//        storesethashMap.put("is_czztpd","1");
//        storesethashMap.put("is_dcyhq","1");
//        storesethashMap.put("is_pt","2");
//        storesethashMap.put("pt_name","拼团");
//        storesethashMap.put("ptsm","");
//        storesethashMap.put("pt_img","https://sysoss.360yingketong.com/images/12486/2018/08/U6rAtLAA2F6rBOAhawH6O2sjt2xZ1w.png");
//        storesethashMap.put("qg_name","抢购");
//        storesethashMap.put("qgsm","");
//        storesethashMap.put("qg_img","https://sysoss.360yingketong.com/images/12486/2018/08/EY6A8A6v0sKb41Av7zKvVcv9lRV84V.png");
//        storesethashMap.put("is_qg","2");
//        storesethashMap.put("is_ydtime","1");
//        storesethashMap.put("is_yyzw","1");
//        storesethashMap.put("is_pd","1");
//        storesethashMap.put( "is_call","2");
        storeInfoindex.put("storeset", storesethashMap);
        return storeInfoindex;
    }

    @RequestMapping(value = "/wxappback/storeinfo", method = RequestMethod.POST)
    @ApiOperation(value = "商家信息—增加", notes = "增加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })

    public String[] intsetStoreInfo(HttpServletRequest request,@RequestBody Map<String, Object> mapparam,HttpServletResponse response){
        String name = mapparam.get("name").toString(); //获取参数值
        String address =  mapparam.get("address").toString();
        String lnglat =  mapparam.get("lnglat").toString();
        String tel =  mapparam.get("tel").toString();
        String desc =  mapparam.get("desc").toString();
        String starttime1 =  mapparam.get("starttime1").toString();
        String endtime1 =  mapparam.get("endtime1").toString();
        String starttime2 =  mapparam.get("starttime2").toString();
        String endtime2 =  mapparam.get("endtime2").toString();
        String rtfileList =  mapparam.get("rtfileList").toString();
        String stfileList =  mapparam.get("stfileList").toString();
        String bizfileList =  mapparam.get("bizfileList").toString();
        String logofileList =  mapparam.get("logofileList").toString();
        if(name==null||name.equals("undefined")||address==null||address.equals("undefined")||lnglat==null ||lnglat.equals("undefined")
        ||tel==null||tel.equals("undefined")||desc==null||desc.equals("undefined")){
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        String file = "/file/download/";
        //==bizfileList============================================================//
        JSONArray  bizfileArray = JSONArray.fromObject(bizfileList);//数组为二
        if (bizfileArray.size()==0){
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        String biz = null;
        for(int i=0;i<bizfileArray.size();i++){
            // 遍历 jsonarraysys 数组，把每一个对象转成 json 对象
            JSONObject bizjob = bizfileArray.getJSONObject(i);
            // 得到 每个对象中的属性值
            biz = (String) bizjob.get("name");
        }
        //==bizfileList==end===================================================================//
        //==logofileList============================================================//
        JSONArray  logofileArray = JSONArray.fromObject(logofileList);//数组为二
        if (logofileArray.size()==0){
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        String logo = null;
        for(int i=0;i<logofileArray.size();i++){
            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            JSONObject logojob = logofileArray.getJSONObject(i);
            // 得到 每个对象中的属性值
            logo = (String) logojob.get("name");
        }
        //==logofileList== end==========================================================//

        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setName(name);
        storeInfo.setAddress(address);
        storeInfo.setStarttime1(starttime1);
        storeInfo.setEndtime1(endtime1);
        storeInfo.setStarttime2(starttime2);
        storeInfo.setEndtime2(endtime2);
        storeInfo.setTelephone(tel);
        storeInfo.setAnnouncement(desc);
        String flielogo = file+logo;
        storeInfo.setLogo(flielogo);
        storeInfo.setLocation(lnglat);
        String fliebiz = file+biz;
        storeInfo.setBusinesslicense(fliebiz);
        storeInfoService.insert(storeInfo);
        //shopid暂无生成方法 故手动添加
        //==storeInfo==end=========================================================================//
        QueryParamEntity queryParamEntity = QueryParamEntity.single("telephone","1234567890");
        //通过商家唯一编号拿到商家db id；
        List<StoreInfo> storeInfoList = storeInfoService.select(queryParamEntity);
        if (storeInfoList.size() == 0) {
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        String id = storeInfoList.get(0).getId();
        //==rtfileList============================================================//
        JSONArray  rtfileArray = JSONArray.fromObject(rtfileList);
        if (rtfileArray.size()==0){
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        for(int i= 0;i < rtfileArray.size();i++){
            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            JSONObject rtfilejob = rtfileArray.getJSONObject(i);
            // 得到 每个对象中的属性值
            String pic = (String) rtfilejob.get("name");
            StoreHomeslide storeHomeslide = new StoreHomeslide();
            String filepic = file+pic;
            storeHomeslide.setPic(filepic);
            storeHomeslide.setSequence(String.valueOf((i+1)));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String setupTime = dateFormat.format(new Date()); // new Date()为获取当前系统时间
            storeHomeslide.setSetuptime(Timestamp.valueOf(setupTime));
            storeHomeslide.setBizid(id);
            storeHomeslideService.insert(storeHomeslide);
        }

        //==rtfileList== end==========================================================//
        //==stfileList============================================================//
        JSONArray  stfileArray = JSONArray.fromObject(stfileList);
        if (stfileArray.size()==0){
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        for(int i=0;i <stfileArray.size();i++){
            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            JSONObject stfilejob = stfileArray.getJSONObject(i);
            // 得到 每个对象中的属性值
            String pics = (String) stfilejob.get("name");
            StoreShopenviron storeShopenviron = new StoreShopenviron();
            String filepic = file+pics;
            storeShopenviron.setPic(filepic);
            storeShopenviron.setSequ(String.valueOf((i+1)));
            storeShopenviron.setBizid(id);
            storeShopenvironService.insert(storeShopenviron);
        }
        //==stfileList== end==========================================================//
        String[] hashMap = {"1"};
        return hashMap;
    }
    @RequestMapping(value = "/app/index/ad", method = RequestMethod.GET)
    @ApiOperation(value = "商家信息—轮播特效图片", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> binding(HttpServletRequest request ,HttpServletResponse response){
        String bizid = request.getParameter("i");
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreShopenviron> storeShopenvironList = storeShopenvironService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new  ArrayList<Map<String,String>>();//定义一个接受数组数组
        for(StoreShopenviron storeShopenviron :storeShopenvironList){
            HashMap hashMap = new HashMap();
            hashMap.put("logo",storeShopenviron.getPic());
            arrayList.add(hashMap);
        }
        return arrayList;
    }
    @RequestMapping(value = "/app/index/Switchingbranches", method = RequestMethod.GET)
    @ApiOperation(value = "商家信息—切换分店", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public HashMap switchingbranches(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        if (!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(shopid)){
            log.error("数据错误——/app/index/Switchingbranches");
            return null;
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        QueryParamEntity queryParamEntity= QueryParamEntity.single("shopid",shopid);
        List<StoreInfo> storeInfoList = storeInfoService.select(queryParamEntity);
        if (!storeInfoList.isEmpty()) {
            for (StoreInfo storeInfo : storeInfoList) {
                if (!storeInfo.getId().equals(bizid)) {
                    HashMap hashMapinfo = new HashMap();
                    hashMapinfo.put("shopid", storeInfo.getId());
                    hashMapinfo.put("name", storeInfo.getName());
                    hashMapinfo.put("logo", storeInfo.getLogo());
                    arrayList.add(hashMapinfo);
                }
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("storelist",arrayList);
        return hashMap;
    }
    @RequestMapping(value = "/app/index/nav", method = RequestMethod.GET)
    @ApiOperation(value = "商家信息——模板信息查询", notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "参数错误", response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> navselect(HttpServletRequest request,HttpServletResponse response){
        String bizid = request.getParameter("i");
        ArrayList<Map<String,String>> arrayList = new  ArrayList<Map<String,String>>();
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid).and("useornot","1");
        List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity);
        if (!storetemplsList.isEmpty()&&storetemplsList.size()==1){
            StoreNavtmpl storeNavtmpl = storeNavtmplService.selectByPk(storetemplsList.get(0).getNavtmplid());
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("navtemplid",storeNavtmpl.getId());
            List<StoreNavitem> storeNavitemList = storeNavitemService.select(queryParamEntity1);
            for (StoreNavitem storeNavitem : storeNavitemList) {

                HashMap hashMap = new HashMap();
                hashMap.put("id",storeNavitem.getId() );//标题的id(40首页、44订单、39个人中心)
                hashMap.put("title",storeNavitem.getTitle()); //标题
                hashMap.put("title_color", storeNavitem.getClolorsel() );
                hashMap.put("title_color2", storeNavitem.getColor());
                hashMap.put("logo",storeNavitem.getIconsel());
                hashMap.put("logo2",storeNavitem.getIcon());
                hashMap.put("url",storeNavitem.getPage());//导航地址
                hashMap.put("num",storeNavitem.getSequ());
                hashMap.put("state",storeNavtmpl.getStatus());
                hashMap.put("uniacid",bizid);//商家编号
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

}
