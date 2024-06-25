package cn.ruone.wxapp.shop.controller;

import static org.hswebframework.web.controller.message.MapResponseMessage.ok;

import cn.ruone.wxapp.service.WxminiappService;
import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.shop.impl.service.StoreDateutil;
import io.swagger.annotations.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.omg.CORBA.StringHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "商家接口——外部接口调用",value = "数据库操作")
public class StoreWxappStoreController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreInfoService storeInfoService;
    private StoreHomeslideService storeHomeslideService;
    private StoreShopenvironService storeShopenvironService;
    private StoreDeliveryrulesService storeDeliveryrulesService;
    private StorePaytypesService storePaytypesService;
    private StoreFunctionlistService storeFunctionlistService;
    private StoreOrderinfoService storeOrderinfoService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreRefundpsService storeRefundpsService;
    private StoreWalletrcdService storeWalletrcdService;
    private StoreAddressesService storeAddressesService;
    private StoreGoodsService storeGoodsService;
    private  StoreGoodspicsService storeGoodspicsService;
    private StoreTableinfoService storeTableinfoService;
    private StoreCouponsService storeCouponsService;
    private StoreGoodstypeService storeGoodstypeService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private WxminiappService wxminiappService;
    private StoreOrdertablesService storeOrdertablesService;
    private StoreCommentsService storeCommentsService;
    private StoreCommentpicService storeCommentpicService;
    private StoreNavitemService storeNavitemService;
    private StoreNavtmplService storeNavtmplService;
    private StoretemplsService storetemplsService;
    private StoreRefundpicService storeRefundpicService;
    private StoreWxappconfigService storeWxappconfigService;
    private StoreTakeoutodrstatService storeTakeoutodrstatService;
    private StoreUserpointService storeUserpointService;
    private StoreOrdercouponsService storeOrdercouponsService;
    @Autowired
    public void setStoreOrdercouponsService(StoreOrdercouponsService storeOrdercouponsService){
        this.storeOrdercouponsService = storeOrdercouponsService;
    }
    @Autowired
    public void setStoreUserpointService(StoreUserpointService storeUserpointService){
        this.storeUserpointService = storeUserpointService;
    }
    @Autowired
    public void setStoreTakeoutodrstatService(StoreTakeoutodrstatService storeTakeoutodrstatService){
        this.storeTakeoutodrstatService = storeTakeoutodrstatService;
    }
    @Autowired
    public void setStoreWxappconfigService(StoreWxappconfigService storeWxappconfigService){
        this.storeWxappconfigService = storeWxappconfigService;
    }
    @Autowired
    public void setStoreRefundpicServiceI(StoreRefundpicService storeRefundpicService){
        this.storeRefundpicService = storeRefundpicService;
    }
    @Autowired
    public void setStoretemplsService(StoretemplsService storetemplsService){
        this.storetemplsService = storetemplsService;
    }
    @Autowired
    public void setStoreNavtmplService(StoreNavtmplService storeNavtmplService){
        this.storeNavtmplService = storeNavtmplService;
    }
    @Autowired
    public void setStoreNavitemService(StoreNavitemService storeNavitemService){
        this.storeNavitemService = storeNavitemService;
    }
    @Autowired
    public void setStoreCommentpicService(StoreCommentpicService storeCommentpicService){
        this.storeCommentpicService = storeCommentpicService;
    }
    @Autowired
    public void setStoreCommentsService(StoreCommentsService storeCommentsService){
        this.storeCommentsService  = storeCommentsService;
    }
    @Autowired
    public void setStoreOrdertablesService(StoreOrdertablesService storeOrdertablesService){
        this.storeOrdertablesService = storeOrdertablesService;
    }
    @Autowired
    public void setWxminiappService(WxminiappService wxminiappService){
        this.wxminiappService = wxminiappService;
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
    public void setStoreCouponsService(StoreCouponsService storeCouponsService){
        this.storeCouponsService = storeCouponsService;
    }
    @Autowired
    public void setStoreTableinfoService(StoreTableinfoService storeTableinfoService){
        this.storeTableinfoService = storeTableinfoService;
    }
    @Autowired
    public void setStoreGoodspicsService( StoreGoodspicsService storeGoodspicsService){
        this.storeGoodspicsService = storeGoodspicsService;
    }
    @Autowired
    public void setStoreGoodsService(StoreGoodsService storeGoodsService){
        this.storeGoodsService = storeGoodsService;
    }
    @Autowired
    public void setStoreAddressesService(StoreAddressesService storeAddressesService){
        this.storeAddressesService = storeAddressesService;
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
    public void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreFunctionlistService(StoreFunctionlistService storeFunctionlistService){
        this.storeFunctionlistService = storeFunctionlistService;
    }
    @Autowired
    public void setStorePaytypesService(StorePaytypesService storePaytypesService){
        this.storePaytypesService = storePaytypesService;
    }
    @Autowired
    public void setStoreDeliveryrulesService(StoreDeliveryrulesService storeDeliveryrulesService){
        this.storeDeliveryrulesService = storeDeliveryrulesService;
    }
    @Autowired
    public void setStoreShopenvironService(StoreShopenvironService storeShopenvironService){
        this.storeShopenvironService = storeShopenvironService;
    }
    @Autowired
    public void setStoreHomeslideService(StoreHomeslideService storeHomeslideService){
        this.storeHomeslideService = storeHomeslideService;
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
    @RequestMapping(value = "/wxapp/store/all", method = RequestMethod.GET)
    @ApiOperation(value = "商家——全部商商家",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    //查询表里所有数据即全部商商家
    public ResponseMessage all(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(page)||!StoresaUtls.blutls(limit)){
            log.error("分页异常");
            System.out.print(page+"/n"+limit);
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        List<StoreInfo> storeInfoList = storeInfoService.select(queryParamEntity);
        Integer num = storeInfoService.count();
        HashMap hashMap = new HashMap();
        hashMap.put("total", num);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreInfo storeInfo : storeInfoList){
            String bizidid = storeInfo.getShopid();
            StoreInfo infoLists = storeInfoService.selectByPk(bizidid);
            String idnaem = infoLists.getName();
            HashMap pageshashMap = new HashMap();
            pageshashMap.put("id",storeInfo.getId());
            pageshashMap.put("name",storeInfo.getName());
            pageshashMap.put("createTime",storeInfo.getEstabtime());
            pageshashMap.put("contact",storeInfo.getContact());
            pageshashMap.put("tel",storeInfo.getTelephone());
            pageshashMap.put("status",Integer.valueOf(storeInfo.getStatus()));
            pageshashMap.put("type",Integer.valueOf(storeInfo.getSttype()));//判断是否是总店
            pageshashMap.put("storeid",bizidid); //总店编号
            pageshashMap.put("storename",idnaem); //总店名称
            arrayList.add(pageshashMap);
            }
        hashMap.put("pages",arrayList);
        return ok(hashMap);
    }
    //添加商家——总店
    @RequestMapping(value = "/wxapp/store/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加商家——总店",notes = "增加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wsadd(HttpServletRequest request, @RequestBody Map<String, Object> add, HttpServletResponse response){
        String name = add.get("name").toString();
        String contact = add.get("contact").toString();
        String tel  = add.get("tel").toString();
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(contact)||!StoresaUtls.blutls(tel)){
            response.setStatus(400);
            log.error("数据错误——/wxapp/store/add");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("name",name);
        List<StoreInfo> storeInfoList = storeInfoService.select(queryParamEntity);
        if (!storeInfoList.isEmpty()){
            log.error("总店店铺名重复——/wxapp/store/add");
            return ok(false);
        }
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setName(name);
        storeInfo.setContact(contact);
        storeInfo.setTelephone(tel);
        storeInfo.setSttype("1");//此接口添加店铺为总店
        storeInfo.setStatus("1");//设置分店为禁用
        storeInfo.setAutorecv("2");//默认手动接单
        storeInfo.setEstabtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeInfoService.insert(storeInfo);
        List<StoreInfo> storeInfoList1 = storeInfoService.select(queryParamEntity);
        if (!storeInfoList1.isEmpty() && storeInfoList1.size() ==1){
            StoreInfo storeInfo1 = storeInfoService.selectByPk(storeInfoList1.get(0).getId());
            storeInfo1.setShopid(storeInfoList1.get(0).getId());
            storeInfoService.updateByPk(storeInfoList1.get(0).getId(),storeInfo1);
        }

        return ok(true);
    }

    //添加商家——分店
    @RequestMapping(value = "/wxapp/store/addsubstore", method = RequestMethod.POST)
    @ApiOperation(value = "添加商家——分店",notes = "增加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wsaddsubstore(HttpServletRequest request, @RequestBody Map<String,String> addsubstore, HttpServletResponse response){
        String name = addsubstore.get("name");
        String contact = addsubstore.get("contact");
        String tel  = addsubstore.get("tel");
        String storeid  = addsubstore.get("storeid");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(contact)||!StoresaUtls.blutls(tel)||!StoresaUtls.blutls(storeid)){
            response.setStatus(400);
            log.error("数据错误——/wxapp/store/addsubstore");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",storeid).and("sttype","1");
        List<StoreInfo> storeInfoList = storeInfoService.select(queryParamEntity);
        if (!storeInfoList.isEmpty()){
            StoreInfo storeInfo = new StoreInfo();
            storeInfo.setName(name);
            storeInfo.setContact(contact);
            storeInfo.setTelephone(tel);
            storeInfo.setStatus("1");//设置分店为运营
            storeInfo.setSttype("2");//此接口添加店铺为分店
            storeInfo.setAutorecv("2");//默认手动接单
            storeInfo.setEstabtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
            storeInfo.setShopid(storeid);
            storeInfoService.insert(storeInfo);
            return ok(true);
        }
        return ok(false);
    }

     //商家——删除  如果存在分店，不能删除总店
    @RequestMapping(value = "/wxapp/store/{bizid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——删除",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage<Boolean> wsdelete(@PathVariable String bizid) {
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误——/wxapp/store/{id}");
            return ok(false);
        }
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);//
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        if (!storeOrderinfoList.isEmpty()) {
            log.error("存在订单，不能删除店铺");
            return ok(false);
        }
        if (storeInfo.getSttype().equals("1")) {//判断所有删除的店铺是总店还是分店 //为1时为总店，
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("shopid", storeInfo.getShopid());
            List<StoreInfo> storeInfoList = storeInfoService.select(queryParamEntity1);
            if (storeInfoList.size() == 1) {
                storeInfoService.deleteByPk(bizid);
                return ok(true);
            }
            log.error("存在分店，不能删除总店");
            return ok(false);
        }
        storeInfoService.deleteByPk(bizid);
        return ok(true);
    }

    //商家——修改
//    @PutMapping(path = "/wxapp/store/edit/{id}")
    @RequestMapping(value = "/wxapp/store/edit/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——修改",notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "id",value = "名称",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "tel",value = "电话",required = true,dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wsedit(HttpServletRequest request,@PathVariable String id, HttpServletResponse response){
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String tel = request.getParameter("tel");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(contact)||!StoresaUtls.blutls(tel)||!StoresaUtls.blutls(id)){
            log.error("数据错误——/wxapp/store/edit/{id}");
            return ok(false);
        }
        StoreInfo storeInfo = storeInfoService.selectByPk(id);
        storeInfo.setName(name);
        storeInfo.setContact(contact);
        storeInfo.setTelephone(tel);
        storeInfoService.updateByPk(id,storeInfo);
        return ok(true);
    }

    //商家——启用店铺
//    @PutMapping(path = "/wxapp/store/{id}/enable")
    @RequestMapping(value = "/wxapp/store/{id}/enable", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——启用",notes = "启用")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "id",value = "名称",required = true,dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage<Boolean> wsenable(@PathVariable String id) {
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——/wxapp/store/{id}/enable");
            return ok(false);
        }
        StoreInfo storeInfo = storeInfoService.selectByPk(id);
        if (storeInfo==null){
            log.error("没有找到该店铺");
            return ok(false);
        }
        storeInfo.setStatus("1");//1---营业，2-停业，3--测试
        storeInfoService.updateByPk(id,storeInfo);
        return ok(true);
    }

    //商家——禁用店铺
//    @RequestMapping(value = "/wxapp/store/{id}/disable", method = RequestMethod.GET)
    @RequestMapping(value = "/wxapp/store/{id}/disable", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——禁用",notes = "禁用")
    @ApiImplicitParams({

    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage<Boolean> wsdisable(@PathVariable String id ) {
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——/wxapp/store/{id}/disable");
            return ok(false);
        }
        StoreInfo storeInfo = storeInfoService.selectByPk(id);
        storeInfo.setStatus("2");//1---营业，2-停业，3--测试
        storeInfoService.updateByPk(id,storeInfo);
        return ok(true);
    }

    //商家——获取单个店铺信息
//    @PutMapping(path = " /wxapp/store/{id}")
    @RequestMapping(value = "/wxapp/store/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——查询信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wsselectid(HttpServletRequest request,@PathVariable String id, HttpServletResponse response){
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——/wxapp/store/{id}");
            return ok(false);
        }
        StoreInfo storeInfo = storeInfoService.selectByPk(id);
        HashMap hashMap = new HashMap();
        hashMap.put("bizid",id);
        hashMap.put("name",storeInfo.getName());
        hashMap.put("address",storeInfo.getAddress());
        hashMap.put("lnglat",storeInfo.getLocation());
        hashMap.put("tel",storeInfo.getTelephone());
        hashMap.put("announcement", storeInfo.getAnnouncement());
        hashMap.put("desc",storeInfo.getIntroduce());
        hashMap.put("starttime1",storeInfo.getStarttime1());
        hashMap.put("endtime1",storeInfo.getEndtime1());
        hashMap.put("starttime2",storeInfo.getStarttime2());
        hashMap.put("endtime2",storeInfo.getEndtime2());
        hashMap.put("endtime2",storeInfo.getEndtime2());
        hashMap.put("color4",storeInfo.getColor4());
        QueryParamEntity queryParamEntity1 = new QueryParamEntity();
        queryParamEntity1.and("bizid",storeInfo.getId()).and("useornot","1");
        List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity1);
        if (!storetemplsList.isEmpty()){
            hashMap.put("navtempl",storetemplsList.get(0).getNavtmplid());
        }
        String bizlist = storeInfo.getBusinesslicense();//""
        ArrayList<Map<String,String>> bizarrayList = new ArrayList<Map<String,String>>();
        if (StoresaUtls.blutls(bizlist)){
            HashMap bizhashMap = new HashMap();
            bizhashMap.put("name", "biz");
            bizhashMap.put("url",bizlist);
            bizarrayList.add(bizhashMap);
        }
        hashMap.put("bizfileList", bizarrayList);

        String logolist =storeInfo.getLogo();
        ArrayList<Map<String,String>> logofilearrayList = new ArrayList<Map<String,String>>();
        if (StoresaUtls.blutls(logolist)){
            HashMap logohashMap = new HashMap();
            logohashMap.put("name", "logo");
            logohashMap.put("url",logolist);
            logofilearrayList.add(logohashMap);
        }
        hashMap.put("logofileList", logofilearrayList);
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",id);//rtfileList.stfileList
        List<StoreHomeslide> storeHomeslideList = storeHomeslideService.select(queryParamEntity);
        ArrayList<Map<String,String>> rtfilearrayList = new ArrayList<Map<String,String>>();
        if (!storeHomeslideList.isEmpty()){
            for (StoreHomeslide storeHomeslide : storeHomeslideList) {
                HashMap rtfilehashMap = new HashMap();
                rtfilehashMap.put("url", storeHomeslide.getPic());
                rtfilehashMap.put("name",storeHomeslide.getName() );
                rtfilearrayList.add(rtfilehashMap);
            }
            hashMap.put("rtfileList",rtfilearrayList);
        }


        List<StoreShopenviron> storeShopenvironList = storeShopenvironService.select(queryParamEntity);//stfileList
        ArrayList<Map<String,String>> stfilearrayList = new ArrayList<Map<String,String>>();
        if (!storeShopenvironList.isEmpty()){
            for (StoreShopenviron storeShopenviron : storeShopenvironList) {
                HashMap stfilehashMap = new HashMap();
                stfilehashMap.put("url", storeShopenviron.getPic());
                stfilehashMap.put("name",storeShopenviron.getName() );
                stfilearrayList.add(stfilehashMap);
            }
            hashMap.put("stfileList", stfilearrayList);
        }
        return ok(hashMap);
    }


    //商家——信息修改
    @RequestMapping(value = "/wxapp/store/{id}/update", method = RequestMethod.POST)
    @ApiOperation(value = "商家——信息——修改",notes = "修改")
    @ApiImplicitParams({

    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wsdisable(@PathVariable String id,@RequestBody Map<String, String> mapparam ){
        String name = mapparam.get("name"); //获取参数值
        String address =  mapparam.get("address");
        String lnglat =  mapparam.get("lnglat");
        String tel =  mapparam.get("tel");
        String desc =  mapparam.get("desc");
        String announcemen =  mapparam.get("announcemen");
        String starttime1 =  mapparam.get("starttime1");
        String endtime1 =  mapparam.get("endtime1");
        String starttime2 =  mapparam.get("starttime2");
        String endtime2 =  mapparam.get("endtime2");
        String rtfileList =  mapparam.get("rtfileList");
        String stfileList =  mapparam.get("stfileList");
        String bizfileList =  mapparam.get("bizfileList");
        String logofileList =  mapparam.get("logofileList");
        String color4 =  mapparam.get("color4");
        String navtempl =  mapparam.get("navtempl");
        if(!StoresaUtls.blutls(id)){
            log.error("数据错误");
            return ok(false);
        }
//        String file = "/file/download/";
        //==bizfileList============================================================//
        String biz = null;
        JSONArray bizfileArray = JSONArray.fromObject(bizfileList);//数组为二
        if (bizfileArray != null) {
            for (int i = 0; i < bizfileArray.size(); i++) {
                // 遍历 jsonarraysys 数组，把每一个对象转成 json 对象
                JSONObject bizjob = bizfileArray.getJSONObject(i);
                // 得到 每个对象中的属性值
                biz = (String) bizjob.get("url");
            }
        }
        //==bizfileList==end===================================================================//
        //==logofileList============================================================//
        String logo = null;
        JSONArray  logofileArray = JSONArray.fromObject(logofileList);//数组为二
        if (logofileArray != null) {
            for (int i = 0; i < logofileArray.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject logojob = logofileArray.getJSONObject(i);
                // 得到 每个对象中的属性值
                logo = (String) logojob.get("url");
            }
        }
        //==logofileList== end==========================================================//
        StoreInfo storeInfo = storeInfoService.selectByPk(id);
        if (StoresaUtls.blutls(name)){
            storeInfo.setName(name);
        }
        if (StoresaUtls.blutls(address)){
            storeInfo.setAddress(address);
        }
        if (StoresaUtls.blutls(starttime1)){
            storeInfo.setStarttime1(starttime1);
        }
        if (StoresaUtls.blutls(starttime2)){
            storeInfo.setStarttime2(starttime2);
        }
        if (StoresaUtls.blutls(endtime1)){
            storeInfo.setEndtime1(endtime1);
        }
        if (StoresaUtls.blutls(endtime2)){
            storeInfo.setEndtime2(endtime2);
        }
        if (StoresaUtls.blutls(tel)){
            storeInfo.setTelephone(tel);
        }
        if (StoresaUtls.blutls(announcemen)){
            storeInfo.setAnnouncement(announcemen);
        }
        if (StoresaUtls.blutls(desc)){
            storeInfo.setIntroduce(desc);
        }
        if (StoresaUtls.blutls(logo)){
            storeInfo.setLogo(logo);
        }
        if (StoresaUtls.blutls(lnglat)){
            storeInfo.setLocation(lnglat);
        }
        if (StoresaUtls.blutls(biz)){
            storeInfo.setBusinesslicense(biz);
        }
        if (StoresaUtls.blutls(color4)){
            storeInfo.setColor4(color4);
        }
        storeInfoService.updateByPk(id,storeInfo);
        //shopid暂无生成方法 故手动添加
        //==storeInfo==end=========================================================================//
        //==rtfileList============================================================//
        JSONArray  rtfileArray = JSONArray.fromObject(rtfileList);
        if (rtfileArray != null) {
            QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",id);
            List<StoreHomeslide> storeHomeslideList = storeHomeslideService.select(queryParamEntity);
            if (!storeHomeslideList.isEmpty()) {
                for (StoreHomeslide storeHomeslide : storeHomeslideList) {
                    String hsid = storeHomeslide.getId();
                    storeHomeslideService.deleteByPk(hsid);
                }
            }
            for (int i = 0; i < rtfileArray.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject rtfilejob = rtfileArray.getJSONObject(i);
                // 得到 每个对象中的属性值
                String pic = (String)rtfilejob.get("url");
                String rtname = (String)rtfilejob.get("name");
                StoreHomeslide storeHomeslide = new StoreHomeslide();
                storeHomeslide.setPic(pic);
                storeHomeslide.setName(rtname);
                storeHomeslide.setSequence(String.valueOf((i + 1)));
                storeHomeslide.setSetuptime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeHomeslide.setBizid(id);
                storeHomeslideService.insert(storeHomeslide);
            }
        }
        //==rtfileList== end==========================================================//
        //==stfileList============================================================//
        JSONArray  stfileArray = JSONArray.fromObject(stfileList);
        if (stfileArray != null) {
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("bizid", id);
            List<StoreShopenviron> storeShopenvironList = storeShopenvironService.select(queryParamEntity1);
            if (!storeShopenvironList.isEmpty()) {
                for (StoreShopenviron storeShopenviron : storeShopenvironList) {
                    String hsvnid = storeShopenviron.getId();
                    storeShopenvironService.deleteByPk(hsvnid);
                }
            }
            for (int i = 0; i < stfileArray.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject stfilejob = stfileArray.getJSONObject(i);
                // 得到 每个对象中的属性值
                String pics = (String) stfilejob.get("url");
                String stfname = (String) stfilejob.get("name");
                StoreShopenviron storeShopenviron = new StoreShopenviron();
                storeShopenviron.setPic(pics);
                storeShopenviron.setName(stfname);
                storeShopenviron.setSequ(String.valueOf((i + 1)));
                storeShopenviron.setBizid(id);
                storeShopenvironService.insert(storeShopenviron);
            }
        }
        if (StoresaUtls.blutls(navtempl)) {
            QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid", storeInfo.getId());
            List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity);
            if (!storetemplsList.isEmpty()) {
                for (Storetempls storetempls : storetemplsList) {
                    String lsid = storetempls.getId();
                    if (storetempls.getNavtmplid().equals(navtempl)) {
                        storetempls.setUseornot(1);
                    } else {
                        storetempls.setUseornot(0);
                    }
                    storetemplsService.updateByPk(lsid, storetempls);
                }
            }
        }
        //==stfileList== end==========================================================//
     return ok(true);
    }


    @RequestMapping(value = "/wxapp/storeservicesetting/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——获取店铺设置信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage storeservicesetting(@PathVariable String id) {
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——/wxapp/storeservicesetting/{id}—查询");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",id);
        List<StoreDeliveryrules> storeDeliveryrulesList = storeDeliveryrulesService.select(queryParamEntity);
        if(!storeDeliveryrulesList.isEmpty()) {
            String did = storeDeliveryrulesList.get(0).getId();
            HashMap hashMap = new HashMap();
            hashMap.put("id", did);
            hashMap.put("storeid", id);
            HashMap dlyhashMap = new HashMap();
            dlyhashMap.put("fee", storeDeliveryrulesList.get(0).getDeliveryfee());
            dlyhashMap.put("range", storeDeliveryrulesList.get(0).getScope());
            dlyhashMap.put("time", Integer.valueOf(storeDeliveryrulesList.get(0).getTimerange()));
            dlyhashMap.put("channel", storeDeliveryrulesList.get(0).getDeliverycmp());
            hashMap.put("deliveryrule", dlyhashMap);
            ArrayList<String> paytype = new ArrayList<>();//定义一个接受数组数组
            List<StorePaytypes> storePaytypesList = storePaytypesService.select(queryParamEntity);
            for (StorePaytypes storePaytypes : storePaytypesList) {
                paytype.add(storePaytypes.getName());
            }
            hashMap.put("paytype", paytype);
            ArrayList<String> functions = new ArrayList<>();//定义一个接受数组数组
            List<StoreFunctionlist> storeFunctionlistList = storeFunctionlistService.select(queryParamEntity);
            for (StoreFunctionlist storeFunctionlist : storeFunctionlistList) {
                functions.add(storeFunctionlist.getName());
            }
            hashMap.put("functions", functions);

            StoreInfo storeInfo = storeInfoService.selectByPk(id);
            HashMap hashMappointrule = new HashMap();
            hashMappointrule.put("value",storeInfo.getOnepointvalue());
            hashMap.put("pointrule",hashMappointrule);

            HashMap hashMapwifi = new HashMap();
            hashMapwifi.put("account",storeInfo.getWifiact());
            hashMapwifi.put("passwd",storeInfo.getWifipasswd());
            hashMap.put("wifi",hashMapwifi);
            return ok(hashMap);
        }
        log.error("不存在该商店店铺设置");
        return ok(false);
    }

    @RequestMapping(value = "/wxapp/storeservicesetting/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——修改店铺设置信息",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage storeservicesettingput(@PathVariable String id, @RequestBody Map<String, String> svtupdate) {
        String bizid = svtupdate.get("storeid");
        String deliveryrule = svtupdate.get("deliveryrule");
        String paytype = svtupdate.get("paytype");
        String functions = svtupdate.get("functions");
        String pointrule = svtupdate.get("pointrule");
        String wifi = svtupdate.get("wifi");
        if (!StoresaUtls.blutls(id)||!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(deliveryrule)||!StoresaUtls.blutls(paytype)||!StoresaUtls.blutls(functions)){
            log.error("数据错误——/wxapp/storeservicesetting/{id}——修改");
            return ok(false);
        }QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreDeliveryrules> storeDeliveryrulesList = storeDeliveryrulesService.select(queryParamEntity);
        if (!storeDeliveryrulesList.isEmpty()) {

            String deid = storeDeliveryrulesList.get(0).getId();

            JSONObject delejson = JSONObject.fromObject(deliveryrule);
            String fee = delejson.getString("fee");
            String range = delejson.getString("range");
            String time = delejson.getString("time");
            String channel = delejson.getString("channel");

            StoreDeliveryrules storeDeliveryrules = storeDeliveryrulesService.selectByPk(deid);
            if (StoresaUtls.blutls(bizid)) {
                storeDeliveryrules.setBizid(bizid);
            }
            if (StoresaUtls.blutls(range)) {
                storeDeliveryrules.setScope(Float.valueOf(range));
            }
            if (StoresaUtls.blutls(fee)) {
                storeDeliveryrules.setDeliveryfee(Float.valueOf(fee));
            }
            if (StoresaUtls.blutls(channel)) {
                storeDeliveryrules.setDeliverycmp(channel);
            }
            if (StoresaUtls.blutls(time)) {
                storeDeliveryrules.setTimerange(time);
            }
            storeDeliveryrulesService.updateByPk(deid, storeDeliveryrules);

            List<StorePaytypes> storePaytypesList = storePaytypesService.select(queryParamEntity);
            if (!storePaytypesList.isEmpty()) {
                for (StorePaytypes storePaytypes : storePaytypesList) {
                    String payid = storePaytypes.getId();
                    storePaytypesService.deleteByPk(payid);
                }
            }
            JSONArray paytjson = JSONArray.fromObject(paytype);
            for (int i = 0; i < paytjson.size(); i++) {
                String num = paytjson.getString(i);
                StorePaytypes storePayty = new StorePaytypes();
                storePayty.setBizid(bizid);
                storePayty.setName(String.valueOf(num));
                storePaytypesService.insert(storePayty);
            }

            List<StoreFunctionlist> storeFunctionlistList = storeFunctionlistService.select(queryParamEntity);
            if (!storeFunctionlistList.isEmpty()) {
                for (StoreFunctionlist storeFunctionlist : storeFunctionlistList) {
                    String funid = storeFunctionlist.getId();
                    storeFunctionlistService.deleteByPk(funid);
                }
            }
            JSONArray functjson = JSONArray.fromObject(functions);
            for (int i = 0; i < functjson.size(); i++) {
                String fnum = functjson.getString(i);
                StoreFunctionlist storeFunction = new StoreFunctionlist();
                storeFunction.setBizid(bizid);
                storeFunction.setName(String.valueOf(fnum));
                storeFunctionlistService.insert(storeFunction);
            }
            JSONObject jsonObjectwifi = JSONObject.fromObject(wifi);
            String account = jsonObjectwifi.getString("account");
            String passwd = jsonObjectwifi.getString("passwd");
            JSONObject jsonObject  = JSONObject.fromObject(pointrule);
            String pointrulese = jsonObject.getString("value");
            StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
            if (StoresaUtls.blutls(pointrulese)) {
                storeInfo.setOnepointvalue(Float.valueOf(pointrulese));
            }
            if (StoresaUtls.blutls(account)) {
                storeInfo.setWifiact(account);
            }
            if (StoresaUtls.blutls(passwd)) {
                storeInfo.setWifipasswd(passwd);
            }
            storeInfoService.updateByPk(bizid,storeInfo);
            return ok(true);
        }
        log.error("storeDeliveryrulesList——不存在该商家");
        return ok(false);
    }

    @RequestMapping(value = "/wxapp/storeservicesetting", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加店铺设置信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage storeservicesettingpost(HttpServletRequest request, @RequestBody Map<String, String> addsvt, HttpServletResponse response) {
        String storeid = addsvt.get("storeid");
        String deliveryrule = addsvt.get("deliveryrule");
        String paytype = addsvt.get("paytype");
        String functions = addsvt.get("functions");
        String pointrule = addsvt.get("pointrule");
        String wifi = addsvt.get("wifi");
        if (!StoresaUtls.blutls(storeid)|| !StoresaUtls.blutls(deliveryrule)||!StoresaUtls.blutls(paytype)||!StoresaUtls.blutls(functions)){
            log.error("数据错误——/wxapp/storeservicesetting/{id}——添加");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",storeid);
        List<StoreDeliveryrules> storeDeliveryrulesList = storeDeliveryrulesService.select(queryParamEntity);
        if(storeDeliveryrulesList.isEmpty()){
            JSONObject jsonObject = JSONObject.fromObject(deliveryrule);
            String fee = jsonObject.getString("fee");
            String range = jsonObject.getString("range");
            String time = jsonObject.getString("time");
            String channel = jsonObject.getString("channel");

            StoreDeliveryrules storeDeliveryrules = new StoreDeliveryrules();
            storeDeliveryrules.setBizid(storeid);
            storeDeliveryrules.setScope(Float.valueOf(range));
            storeDeliveryrules.setDeliveryfee(Float.valueOf(fee));
            storeDeliveryrules.setDeliverycmp(channel);
            storeDeliveryrules.setTimerange(time);
            storeDeliveryrulesService.insert(storeDeliveryrules);
            JSONArray paytypejson = JSONArray.fromObject(paytype);

//        String[] strarr = paytype.split(",");//根据“，”转换成String数组
            for (int i= 0;i< paytypejson.size();i++) {
                String num = paytypejson.getString(i);
                StorePaytypes storePaytypes = new StorePaytypes();
                storePaytypes.setBizid(storeid);
                storePaytypes.setName(String.valueOf(num));
                storePaytypesService.insert(storePaytypes);
            }
            JSONArray functionsjson = JSONArray.fromObject(functions);
//        String[] strarrs = functions.split(",");//根据“，”转换成String数组
            for (int i= 0;i< functionsjson.size();i++) {
                String fnum = functionsjson.getString(i);
                StoreFunctionlist storeFunctionlist = new StoreFunctionlist();
                storeFunctionlist.setBizid(storeid);
                storeFunctionlist.setName(String.valueOf(fnum));
                storeFunctionlistService.insert(storeFunctionlist);
            }
            JSONObject jsonObjectwifi = JSONObject.fromObject(wifi);
            String account = jsonObjectwifi.getString("account");
            String passwd = jsonObjectwifi.getString("passwd");
            StoreInfo storeInfo = storeInfoService.selectByPk(storeid);
            storeInfo.setOnepointvalue(Float.valueOf(pointrule));
            storeInfo.setWifiact(account);
            storeInfo.setWifipasswd(passwd);
            storeInfoService.updateByPk(storeid,storeInfo);
            return ok(true);
        }
        return ok(false);

    }
    @RequestMapping(value = "/wxapp/wxsettinginfo/{bizid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——查询店铺微信设置信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wxsettinginfo(HttpServletRequest request,HttpServletResponse response,@PathVariable String bizid){
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreWxappconfig> storeWxappconfigList = storeWxappconfigService.select(queryParamEntity);
        if (!storeWxappconfigList.isEmpty() && storeWxappconfigList.size() == 1 ){
            HashMap hashMap = new HashMap();
            for (StoreWxappconfig storeWxappconfig : storeWxappconfigList){
                hashMap.put("appid",storeWxappconfig.getAppid());
                hashMap.put("appsecret",storeWxappconfig.getAppsecret());
                hashMap.put("mchid",storeWxappconfig.getMchid());
                hashMap.put("mchkey",storeWxappconfig.getMchkey());
                hashMap.put("mapkey",storeWxappconfig.getMapkey());
            }
            return ok(hashMap);
        }
        return  ok(false);
    }
    @RequestMapping(value = "/wxapp/wxsettinginfo", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加店铺微信设置信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage wxsettinginfopost(@RequestBody Map<String, String> tinginfo){
        String appid = tinginfo.get("appid");
        String appsecret = tinginfo.get("appsecret");
        String mchid = tinginfo.get("mchid");
        String mchkey = tinginfo.get("mchkey");
        String mapkey = tinginfo.get("mapkey");
        String bizid = tinginfo.get("storeid");
        if (!StoresaUtls.blutls(appid)||!StoresaUtls.blutls(appsecret)||!StoresaUtls.blutls(mchid)||!StoresaUtls.blutls(mchkey)||!StoresaUtls.blutls(mapkey)||!StoresaUtls.blutls(bizid)){
            log.error("数据错误——/wxapp/wxsettinginfo——添加");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreWxappconfig> storeWxappconfigList = storeWxappconfigService.select(queryParamEntity);
        if (!storeWxappconfigList.isEmpty() && storeWxappconfigList.size() == 1 ){
            String id = storeWxappconfigList.get(0).getId();
            StoreWxappconfig storeWxappconfig = storeWxappconfigService.selectByPk(id);
            storeWxappconfig.setAppid(appid);
            storeWxappconfig.setAppsecret(appsecret);
            storeWxappconfig.setMchid(mchid);
            storeWxappconfig.setMchkey(mchkey);
            storeWxappconfig.setMapkey(mapkey);
            storeWxappconfig.setBizid(bizid);
            storeWxappconfigService.updateByPk(id,storeWxappconfig);
            return ok(true);
        }
        if (storeWxappconfigList.isEmpty()) {
            StoreWxappconfig storeWxappconfig = new StoreWxappconfig();
            storeWxappconfig.setAppid(appid);
            storeWxappconfig.setAppsecret(appsecret);
            storeWxappconfig.setMchid(mchid);
            storeWxappconfig.setMchkey(mchkey);
            storeWxappconfig.setMapkey(mapkey);
            storeWxappconfig.setBizid(bizid);
            storeWxappconfigService.insert(storeWxappconfig);
            return ok(true);
        }
        return ok(false);
    }
    //商家处理订单=====================================================================================================
    @RequestMapping(value = "/wxapp/orderlist",method = RequestMethod.GET)
    @ApiOperation(value = "商家接口——数据统计",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage orderlist(HttpServletRequest request,HttpServletResponse response){
        String shopid = request.getParameter("shopid");
        String odtype = request.getParameter("odtype");//订单类型
        String odstatus = request.getParameter("odstatus");//订单状态11
        String orderno = request.getParameter("orderno");//订单编号
        String rname = request.getParameter("rname");//收件人名字
        String rtel = request.getParameter("rtel");//收件人名字
        String paytype = request.getParameter("paytype");//支付类型
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(page)||!StoresaUtls.blutls(limit)){
            log.error("分页异常");
            System.out.print(page+"/n"+limit);
            return ok(false);
        }
        if (!StoresaUtls.blutls(shopid)||!StoresaUtls.blutls(odtype)||!StoresaUtls.blutls(odstatus)){
            log.error("数据错误——/wxapp/orderlist——查询");
            return ok(false);
        }
        QueryParamEntity shopidEntity = QueryParamEntity.single("bizid",shopid);
        List<StoreOrderinfo> shopidList = storeOrderinfoService.select(shopidEntity);
        //判断订单表里是否有这家单的订单
        if (shopidList.isEmpty()){
            log.error("数据错误——/wxapp/orderlist——数据库订单中没有该家店铺订单");
            return ok(false);
        }
        //处理好座位查询条件的数据
        String odtypese = null;//用了存储db里的数据
        //        odtype: 订单类型，all表示所有类型，1 外卖，2 自提，3 在店消费 4 预约到店
        switch (odtype){
            case "1":
                odtypese="1";
                break;
            case "2":
                odtypese="2";
                break;
            case "3":
                odtypese="3";
                break;
            case "4":
                odtypese="4";
                break;
        }
        //dstatus: 订单状态： all表示所有状态，1 待付款 2 待发货（已确认） 3 待收货（待自提）4 关闭交易 5交易成功， 6 退款申请中 7待退款，8 退款完成  9 待接单  收款确认状态 10     待到店11.

//        .status:   标识订单状态： 1 待付款 2 待接单（待确认）  3 待发货（已确认） 4 待收货（待自提） 5 退款申请中 6 待到店（已到店） 7 评价完成 8 关闭交易 9交易成功，10待退款，11 退款完成   12 收款确认状态

        String odstatusse = null;//用了存储db里的数据
        switch (odstatus){
            case "1":
                odstatusse="1";
                break;
            case "2":
                odstatusse="3";
                break;
            case "3":
                odstatusse="4";
                break;
            case "4":
                odstatusse="8";
                break;
            case "5":
                odstatusse="9";
                break;
            case "6":
                odstatusse="5";
                break;
            case "7":
                odstatusse="10";
                break;
            case "8":
                odstatusse="11";
                break;
            case "9":
                odstatusse="2";
                break;
            case "10":
                odstatusse="12";
                break;
            case "11":
                odstatusse="6";
                break;
        }
        QueryParamEntity queryParamEntity = new  QueryParamEntity();
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始2
        queryParamEntity.and("entersts","1");
        if(StoresaUtls.blutls(orderno)){
            queryParamEntity.where("orderno","like","%"+orderno+"%");
        }
        if(StoresaUtls.blutls(rname) ){
            queryParamEntity.where("name","like","%"+rname+"%");
        }
        if(StoresaUtls.blutls(rtel)){
            queryParamEntity.where("phone","like","%"+rtel+"%");
        }
        if(paytype != null && paytype.length()==1 ){
            queryParamEntity.and("paytype",paytype);
        }

        if (odtype.equals("all") && odstatus.equals("all")){//订单类型为所有，订单状态为所有
            queryParamEntity.and("bizid",shopid);
        }
        if (odtype.equals("all") && !odstatus.equals("all")){//订单类型为所有，根据订单状态查询
            queryParamEntity.and("bizid",shopid).and("status",odstatusse);
        }
        if (!odtype.equals("all") && odstatus.equals("all")){//订单状态为所有，根据订单类型查询
            queryParamEntity.and("bizid",shopid).and("odtype",odtypese);
        }
        if (!odtype.equals("all") && !odstatus.equals("all")){//订单类型不为所有，订单不状态为所有
            queryParamEntity.and("bizid",shopid).and("entersts","1").and("status",odstatusse).and("odtype",odtypese);
        }


        List<StoreOrderinfo> storeOrderinfoList = storeOrderinfoService.select(queryParamEntity);
        if (storeOrderinfoList.isEmpty()){
            log.error("数据错误——/wxapp/orderlist——数据库没有查询该家店铺此类型订单");
            return ok(false);
        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, -7);//日期回滚7天
        String str =sdf.format(lastDate.getTime());
        queryParamEntity.where("createdtime","gte",str+" "+"00:00:00");

        HashMap ordershashMap = new HashMap();
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreOrderinfo storeOrderinfo : storeOrderinfoList){
                HashMap hashMap = new HashMap();
                hashMap.put("id", storeOrderinfo.getId());
                hashMap.put("name", storeOrderinfo.getName());
                hashMap.put("date", storeOrderinfo.getCreatedtime());
                hashMap.put("address", storeOrderinfo.getAddress());
                hashMap.put("tel", storeOrderinfo.getPhone());
                hashMap.put("orderno", storeOrderinfo.getOrderno());
                hashMap.put("price", storeOrderinfo.getIncome());
                hashMap.put("notes", storeOrderinfo.getNotes());
                hashMap.put("odtype", storeOrderinfo.getOdtype());
                hashMap.put("odstatus", storeOrderinfo.getStatus());
                arrayList.add(hashMap);
        }
        ordershashMap.put("orders",arrayList);
        ordershashMap.put("total",arrayList.size());
        return ok(ordershashMap);
    }
    @RequestMapping(value = "/wxapp/orderinfo/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "商家接口——数据统计——删除订单",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage deleteorderid(@PathVariable String id){
       if (!StoresaUtls.blutls(id)){
           log.error("数据错误——/wxapp/orderinfo/{id}——删除");
           return ok(false);
       }
       StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(id);
       storeOrderinfo.setEntersts("2");
       storeOrderinfoService.updateByPk(id,storeOrderinfo);
       return ok(true);
    }
   ///wxapp/orderinfo/?id=1&name=%E5%8D%8E%E4%B8%AD&tel=13900001111&notes=%E8%AF%B4%E6%98%2
   @RequestMapping(value = "/wxapp/orderinfo",method = RequestMethod.PUT)
   @ApiOperation(value = "商家接口——数据统计——修改",notes = "修改")
   @ApiImplicitParams({
   })
   @ApiResponses({
           @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
   })
   public ResponseMessage orderinfo(HttpServletRequest request,HttpServletResponse response) {
       String bizid = request.getParameter("i");
       String orderid = request.getParameter("id");
       String name = request.getParameter("name");//null
       String tel = request.getParameter("tel");
       String notes = request.getParameter("notes");
       String status = request.getParameter("odstatus");
       if (!StoresaUtls.blutls(orderid)) {
           log.error("数据错误——/wxapp/orderinfo——修改");
           return ok(false);
       }
       StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
       if (StoresaUtls.blutls(name)) {
           storeOrderinfo.setName(name);
       }
       if (StoresaUtls.blutls(tel)) {
           storeOrderinfo.setPhone(tel);
       }
       if (StoresaUtls.blutls(notes)) {
           storeOrderinfo.setNotes(notes);
       }
       if (StoresaUtls.blutls(status)){
           //dstatus: 订单状态： all表示所有状态，1 待付款 2 待发货（已确认） 3 待收货（待自提）4 关闭交易 5交易成功， 6 退款申请中 7待退款，8 退款完成  9 待接单  10
           if (status.equals("9")){
               if (storeOrderinfo.getOdtype().equals("3") || storeOrderinfo.getOdtype().equals("4") ) {
                   storeOrderinfo.setPaytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
               }
               storeOrderinfo.setFinishtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
               //获取积分
               storeUserpointService.Getpoints(storeOrderinfo.getGetpoint(),storeOrderinfo.getId(),storeOrderinfo.getUserid());
               //添加统计
               storeTakeoutodrstatService.Receipts(bizid,storeOrderinfo.getPaytype(),storeOrderinfo.getIncome());

           }
           if (status.equals("2")||storeOrderinfo.getOdtype().equals("3")) {
               storeOrderinfo.setStatus("1");
           }
           if (status.equals("3")||storeOrderinfo.getOdtype().equals("1")) {
               storeOrderinfo.setStatus(status);
           }
           if (status.equals("4")||storeOrderinfo.getOdtype().equals("1")) {
               storeOrderinfo.setStatus(status);
           }
           if (status.equals("1")) {
               storeOrderinfo.setStatus(status);
           }
           if (status.equals("8") && storeOrderinfo.getStatus().equals("2")) {
               String userid = storeOrderinfo.getUserid();
               StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
               float wallet = storeMemberinfo.getWallet();
               if (storeOrderinfo.getOdtype().equals("2")||storeOrderinfo.getOdtype().equals("1")){
                   String patype = storeOrderinfo.getPaytype();
                   float money =  storeOrderinfo.getIncome();
                   String bizId = storeOrderinfo.getBizid();
                   String orderNo = storeOrderinfo.getOrderno();
                   if (patype.equals("3")){//钱包支付
                       storeMemberinfo.setWallet(wallet+money);
                       storeMemberinfoService.updateByPk(userid,storeMemberinfo);
                       //2.将订单表里状态改为8.交易关闭，
                       storeOrderinfo.setStatus("11");
                       storeOrderinfo.setIncome(0);
                       storeOrderinfo.setSubscription(0);
                       storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
                       //商家拒绝接单 --添加记录
                       storeRefundpsService.Refuseorders(orderid,storeOrderinfo.getBizid(),storeOrderinfo.getPhone(),money,patype);
//                       Refuseorders(String orderid, String bizid, String phone, float money, String patype)

                       //钱包退款——添加记录
                       String Judge = "5";
                       storeWalletrcdService.Walletrefund(userid,storeOrderinfo.getOrderno(), Float.valueOf(money),Judge);

                       storeOrderinfo.setStatus("11");
                       storeOrderinfo.setLastrsn("2");//1--客户取消订单，2--商家拒绝订单，3--商家拒绝退款 4---商家已同意退款
                       storeOrderinfo.setCanceltime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                       storeOrderinfoService.updateByPk(orderid, storeOrderinfo);
                   }
                   if (patype.equals("1")){
                       //商家拒绝接单 --添加记录
                       storeRefundpsService.Refuseorders(orderid,storeOrderinfo.getBizid(),storeOrderinfo.getPhone(),money,patype);
                       int totalFee = (int) (money * 100);
                       int refundFee = (int) (money * 100);
                       boolean refund = wxminiappService.applyRefund(bizId, orderNo, totalFee, refundFee);
                           if (refund) {
                               QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid", orderid);
                               List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity);
                               if (!storeRefundpsList.isEmpty() && storeRefundpsList.size() ==1) {
                               String funid = storeRefundpsList.get(0).getId();
                               StoreRefundps storeRefundps = storeRefundpsService.selectByPk(funid);
                               storeRefundps.setStatus("3");
                               storeRefundps.setCompletetime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                               storeRefundpsService.updateByPk(funid, storeRefundps);
                               storeOrderinfo.setStatus("11");
                               storeOrderinfo.setLastrsn("2");
                               storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
                           }
                       }
                   }
               }
               if (storeOrderinfo.getOdtype().equals("4")||storeOrderinfo.getOdtype().equals("3")){
                   storeOrderinfo.setStatus("8");
                   storeOrderinfo.setLastrsn("2");
                   storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
               }
           }
//           在店消费：接单操作，后台发送2，表示待付款
//           外卖订单：接单操作，后台发送3，表示待发货
//           自提订单：接单操作，后台发送4，表示待自提
       }
       storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
       return ok(true);
   }
    @RequestMapping(value = "/wxapp/ordersendout/{orderid}",method = RequestMethod.PUT)
    @ApiOperation(value = "商家接口——商家处理订单——发货",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage updateordersendout(@PathVariable String orderid){
        if (orderid == null || orderid.equals("undefined")){
            log.error("商家处理订单——发货——订单号为空");
            return ok(false);
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        if (!storeOrderinfo.getOdtype().equals("1")){
           log.error("商家处理订单——发货——此订单不是外卖订单");
            return ok(false);
        }
        storeOrderinfo.setStatus("4");//由代发货改为待收货
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/ordercancelsend/{orderid}",method = RequestMethod.PUT)
    @ApiOperation(value = "商家接口——商家处理订单——取消发货",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage ordersendoutsse(@PathVariable String orderid){
        if (!StoresaUtls.blutls(orderid)){
            log.error("商家处理订单——取消发货——订单号为空");
            return ok(false);
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        if (!storeOrderinfo.getOdtype().equals("1") && !storeOrderinfo.getStatus().equals("4")){
            log.error("商家处理订单——发货——此订单不是外卖订单");
            return ok(false);
        }
        storeOrderinfo.setStatus("3");
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return ok(true);
    }
    //商家处理用户==================================================================================================================
    @RequestMapping(value = "/wxapp/mdfyuserpoint",method = RequestMethod.PUT)
    @ApiOperation(value = "商家接口——用户积分——修改积分",notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid",value = "商家编号",required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "point",value = "商家编号",required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage mdfyuserpoint(HttpServletRequest request,HttpServletResponse response){
        String userid = request.getParameter("userid");
        String point = request.getParameter("point");
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(userid)){
            log.error("数据错误——/wxapp/mdfyuserpoint");
            return ok(false);
        }
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
        storeMemberinfo.setScore(point);
        storeMemberinfoService.updateByPk(userid,storeMemberinfo);
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/userlist/{shopid}",method = RequestMethod.GET)
    @ApiOperation(value = "商家接口——用户信息——查询用户信息",notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "shopid",value = "商家编号",required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage userlist(@PathVariable String shopid, HttpServletRequest request,HttpServletResponse response){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
//        name：用户名， phone：电话，nick：微信昵称， class：金卡会员
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String nick = request.getParameter("nick");
        String classse = request.getParameter("class");
        if (!StoresaUtls.blutls(shopid)) {
            log.error("数据错误——/wxapp/userlist");
        }
        if (!StoresaUtls.blutls(page)||!StoresaUtls.blutls(limit)){
            log.error("分页异常");
            System.out.print(page+"/n"+limit);
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid",shopid);
        if (StoresaUtls.blutls(nick)){
            queryParamEntity.where("name" ,"like" ,"%"+nick+"%");
        }
        if (StoresaUtls.blutls(phone)){
            queryParamEntity.where("telephone" ,"like" ,"%"+phone+"%");
        }
        if (StoresaUtls.blutls(name)){
            queryParamEntity.where("membername" ,"like" ,"%"+name+"%");
        }
        if (StoresaUtls.blutls(classse)){
            queryParamEntity.and("membertype",classse);
        }
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        List<StoreMemberinfo> storeMemberinfoList = storeMemberinfoService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreMemberinfo storeMemberinfo : storeMemberinfoList){
            HashMap userhashMap = new HashMap();
            userhashMap.put("id",storeMemberinfo.getId());
            userhashMap.put("name",storeMemberinfo.getMembername());
            userhashMap.put("date",storeMemberinfo.getStarttime());
            userhashMap.put("nickname",storeMemberinfo.getName());
            userhashMap.put("phone",storeMemberinfo.getTelephone());
            userhashMap.put("openid",storeMemberinfo.getOpenid());
            userhashMap.put("point",Integer.valueOf(storeMemberinfo.getScore()));
            userhashMap.put("wallet",storeMemberinfo.getWallet());
            userhashMap.put("class",storeMemberinfo.getMembertype()); //1—非会员，2-金卡，3-银卡，4-铜卡
            arrayList.add(userhashMap);
        }
        hashMap.put("users",arrayList);
        hashMap.put("total",arrayList.size());
        return ok(hashMap);
    }
    @RequestMapping(value = "/wxapp/userlisttype", method = RequestMethod.POST)
    @ApiOperation(value = "商家接口——批量设置用户会员级别",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage userlisttypeupdate(HttpServletRequest request, @RequestBody Map<String,String> usergender, HttpServletResponse response) {
        String ids = usergender.get("ids");//"[“2345”,”1234”]"
        String type = usergender.get("type");
        JSONArray bidsArray = JSONArray.fromObject(ids);
//        String[] strarr = status.split(",");//根据“，”转换成String数组
        for (int i= 0; i<bidsArray.size();i++){
            String id = bidsArray.getString(i);
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(id);
            storeMemberinfo.setMembertype(type);
            storeMemberinfoService.updateByPk(id,storeMemberinfo);
        }
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/usertype", method = RequestMethod.PUT)
    @ApiOperation(value = "商家接口——设置用户会员级别",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage usertypeupdate(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");//"[“2345”,”1234”]"
        String type = request.getParameter("type");
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(id);
            storeMemberinfo.setMembertype(type);
            storeMemberinfoService.updateByPk(id,storeMemberinfo);
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/userrcpaddresses/{userid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——获取用户收货地址",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage storeservicesettingput(@PathVariable String userid){
        if (!StoresaUtls.blutls(userid)){
            log.error("获取用户收货地址——userid为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid",userid);
        List<StoreAddresses> storeAddressesList = storeAddressesService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreAddresses storeAddresses : storeAddressesList){
            HashMap hashMap = new HashMap();
            hashMap.put("address",storeAddresses.getAddress());
            hashMap.put("default",String.valueOf(storeAddresses.getFixed()));
            arrayList.add(hashMap);
        }
        return ok(arrayList);
    }
    @RequestMapping(value = "/wxapp/userasshopadmin/{userid}", method = RequestMethod.PUT)
    @ApiOperation(value = "商家接口——设置用户为管理员",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage userasshopadminupdate(HttpServletRequest request, @PathVariable String userid) {
        String flag = request.getParameter("flag");//"[“2345”,”1234”]"
        if (!StoresaUtls.blutls(userid) ||!StoresaUtls.blutls(flag)){
            log.error("设置用户为管理员—/wxapp/userasshopadmin/{userid}—userid为空");
            return ok(false);
        }
//        memberadmin： 1--为店铺管理员，2--不是管理员 db
//        1---设置为管理员，0—不设置为管理员 js
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
        if (flag.equals("1")) {
            storeMemberinfo.setMemberadmin(1);
        }
        if (flag.equals("0")) {
            storeMemberinfo.setMemberadmin(2);
        }
        storeMemberinfoService.updateByPk(userid,storeMemberinfo);
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/orderpayinfo/{orderid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家接口——获取订单支付信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage orderpayinfoselect(HttpServletRequest request, @PathVariable String orderid) {
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        HashMap hashMap = new HashMap();
        hashMap.put("total",storeOrderinfo.getIncome());
        hashMap.put("wxpay",storeOrderinfo.getWxpay());
        hashMap.put("walletpay:",storeOrderinfo.getWalletpay());
        hashMap.put("pointpay",storeOrderinfo.getPaypoint());
        return ok(hashMap);
    }
    @RequestMapping(value = "/wxapp/confirmpayment", method = RequestMethod.PUT)
    @ApiOperation(value = "商家接口——确认现金收款",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage orderpayinfo(HttpServletRequest request,HttpServletResponse response){
        String orderid = request.getParameter("id");
        String money = request.getParameter("money");
        if (!StoresaUtls.blutls(orderid) ||!StoresaUtls.blutls(money)){
            log.error("设置用户为管理员—/wxapp/userasshopadmin/{userid}—userid为空");
            return ok(false);
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        storeOrderinfo.setCashpay(Float.valueOf(money));
        storeOrderinfo.setStatus("9");
        storeOrderinfo.setFinishtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return ok(true);
    }

    //商家处理店铺商品==================================================================================================================
    @RequestMapping(value = "/wxapp/goodsinfo/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加商品",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodsinoinset(@PathVariable String shopid,@RequestBody Map<String, String> goodsinfo){
        String name = goodsinfo.get("name");
        String catg = goodsinfo.get("catg");
        String selltype = goodsinfo.get("selltype");
        String onsiteprice = goodsinfo.get("onsiteprice");
        String outsideprice = goodsinfo.get("outsideprice");
        String desc = goodsinfo.get("desc");
        String soldnum = goodsinfo.get("soldnum");
        String stocknum  = goodsinfo.get("stocknum");
        String sequ = goodsinfo.get("sequ");
        String onsaledrop = goodsinfo.get("onsaledrop");
        String recmd = goodsinfo.get("recmd");
        String packfee = goodsinfo.get("packfee");
        String prodfileList = goodsinfo.get("prodfileList");
        String userpoint = goodsinfo.get("pointprice");
        String usepointflag = goodsinfo.get("point");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(catg)||!StoresaUtls.blutls(selltype)||!StoresaUtls.blutls(onsiteprice)||!StoresaUtls.blutls(outsideprice)||!StoresaUtls.blutls(desc)
                ||!StoresaUtls.blutls(soldnum)||!StoresaUtls.blutls(stocknum)||!StoresaUtls.blutls(sequ)||!StoresaUtls.blutls(packfee) ||!StoresaUtls.blutls(onsaledrop)||!StoresaUtls.blutls(recmd)||!StoresaUtls.blutls(prodfileList)) {
            log.error("添加商品——值为空");
            return ok(false);
        }
        StoreGoods storeGoods = new StoreGoods();
        storeGoods.setBizid(shopid);
        storeGoods.setName(name);
        storeGoods.setGoodstype(catg);
        storeGoods.setSelltype(selltype);
        storeGoods.setSiteprice(Float.valueOf(onsiteprice));
        storeGoods.setOriginprice(Float.valueOf(outsideprice));
        storeGoods.setDescrip(desc);
        storeGoods.setSoldnum(soldnum);
        storeGoods.setStocknum(stocknum);
        storeGoods.setSequ(sequ);
        storeGoods.setFlag("1");
        storeGoods.setPackfee(Float.valueOf(packfee));
        storeGoods.setOnsale(Integer.valueOf(onsaledrop));
        storeGoods.setRecommend(Integer.valueOf(recmd));
        if (usepointflag.equals("1")){
            if (!StoresaUtls.blutls(userpoint)){
                log.error("添加商品——值为空");
                return ok(false);
            }
            storeGoods.setUserpoint(userpoint);
            storeGoods.setUserpointflag(usepointflag);
        }
        storeGoods.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));//创建
        storeGoods.setOnoffsaletime(Timestamp.valueOf(StoreDateutil.getStringDate()));//上下架时间
        storeGoodsService.insert(storeGoods);
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",shopid).and("goodstype",catg).and("sequ",sequ);
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        String goodid = storeGoodsList.get(0).getId();
        JSONArray pdfArray = JSONArray.fromObject(prodfileList);
        String urlse = null;
        for (int i= 0; i<pdfArray.size();i++){
            JSONObject objects = pdfArray.getJSONObject(i);
            if (i==0){
                 urlse = objects.getString("url");
            }
            String url = objects.getString("url");
            String namese = objects.getString("name");
            StoreGoodspics storeGoodspics = new StoreGoodspics();
            storeGoodspics.setGoodsid(goodid);
            storeGoodspics.setGoodspic1(url);
            storeGoodspics.setName(namese);
            storeGoodspics.setText(name);
            storeGoodspics.setSequ(String.valueOf(i+1));
            storeGoodspicsService.insert(storeGoodspics);
        }
        StoreGoods storeGoods1 = storeGoodsService.selectByPk(goodid);
        storeGoods1.setMiniimage(urlse);
        storeGoodsService.updateByPk(goodid,storeGoods1);
        return ok(true);
    }

    @RequestMapping(value = "/wxapp/goodsinfo/update", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——修改商品",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodsinfoupdate(@RequestBody Map<String, String> goodsinfo ){
        String name = goodsinfo.get("name");
        String id =  goodsinfo.get("id");
        String catg =  goodsinfo.get("catg");
        String selltype = goodsinfo.get("selltype");
        String onsiteprice =  goodsinfo.get("onsiteprice");
        String outsideprice =  goodsinfo.get("outsideprice");
        String desc =  goodsinfo.get("desc");
        String soldnum =  goodsinfo.get("soldnum");
        String stocknum  =  goodsinfo.get("stocknum");
        String sequ =  goodsinfo.get("sequ");
        String onsaledrop =  goodsinfo.get("onsaledrop");
        String packfee =  goodsinfo.get("packfee");
        String recmd =  goodsinfo.get("recmd");
        String prodfileList =  goodsinfo.get("prodfileList");
        String userpoint = goodsinfo.get("pointprice");
        String usepointflag = goodsinfo.get("point");
        StoreGoods storeGoods = storeGoodsService.selectByPk(id);
        if (!StoresaUtls.blutls(id)){
            log.error("需要修改商品为空");
            return ok(false);
        }

        if (StoresaUtls.blutls(name)) {
            storeGoods.setName(name);
        }
        if (StoresaUtls.blutls(catg)) {
            storeGoods.setGoodstype(catg);
        }
        if (StoresaUtls.blutls(selltype)) {
            storeGoods.setSelltype(selltype);
        }
        if(StoresaUtls.blutls(onsiteprice)) {
            storeGoods.setSiteprice(Float.valueOf(onsiteprice));
        }
        if (StoresaUtls.blutls(outsideprice)) {
            storeGoods.setOriginprice(Float.valueOf(outsideprice));
        }
        if (StoresaUtls.blutls(desc)) {
            storeGoods.setDescrip(desc);
        }
        if (StoresaUtls.blutls(soldnum)) {
            storeGoods.setSoldnum(soldnum);
        }
        if (StoresaUtls.blutls(stocknum)) {
            storeGoods.setStocknum(stocknum);
        }
        if (StoresaUtls.blutls(sequ)) {
            storeGoods.setSequ(sequ);
        }
//        storeGoods.setFlag("1");
        if (StoresaUtls.blutls(packfee)) {
            storeGoods.setPackfee(Float.valueOf(packfee));
        }
        if (StoresaUtls.blutls(onsaledrop)) {
            if (onsaledrop.equals("1")){
                storeGoods.setOnsale(1);
            }
            if (onsaledrop.equals("2")){
                storeGoods.setOnsale(0);
            }
        }
        if (StoresaUtls.blutls(recmd)) {
            storeGoods.setRecommend(Integer.valueOf(recmd));
        }
        if (StoresaUtls.blutls(usepointflag)) {
            if (usepointflag.equals("1")) {
                if (!StoresaUtls.blutls(usepointflag)) {
                    log.error("添加商品——值为空");
                    return ok(false);
                }
                storeGoods.setUserpoint(userpoint);
            }
            storeGoods.setUserpointflag(usepointflag);
        }
        storeGoodsService.updateByPk(id,storeGoods);
        if (StoresaUtls.blutls(prodfileList)) {
            QueryParamEntity queryParamEntity = new QueryParamEntity();
            queryParamEntity.and("goodsid",id);
            List<StoreGoodspics> storeGoodspicsList = storeGoodspicsService.select(queryParamEntity);
            for (StoreGoodspics storeGoodspics : storeGoodspicsList){
                String goodpicid = storeGoodspics.getId();
                storeGoodspicsService.deleteByPk(goodpicid);
            }
            String urlse = null;
            JSONArray pdfArray = JSONArray.fromObject(prodfileList);
            for (int i = 0; i < pdfArray.size(); i++) {
                JSONObject objects = pdfArray.getJSONObject(i);
                if (i==0){
                    urlse = objects.getString("url");
                }
                String url = objects.getString("url");
                String namese = objects.getString("name");
                StoreGoodspics storeGoodspics = new StoreGoodspics();
                storeGoodspics.setGoodsid(id);
                storeGoodspics.setGoodspic1(url);
                storeGoodspics.setName(namese);
                storeGoodspics.setText(name);
                storeGoodspics.setSequ(String.valueOf(i + 1));
                storeGoodspicsService.insert(storeGoodspics);
            }
            StoreGoods storeGoods1 = storeGoodsService.selectByPk(id);
            storeGoods1.setMiniimage(urlse);
            storeGoodsService.updateByPk(id,storeGoods1);
        }
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/goodslistonsale/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——批量发布商品",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodslistonsale(@PathVariable String shopid,@RequestBody ArrayList<String> goodsupdate) {
        if (!StoresaUtls.blutls(shopid) || goodsupdate == null) {
            log.error("批量发布商品——shopid or id值为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid", shopid);
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        if (storeGoodsList.isEmpty()) {
            log.error("批量发布商品——该商店尚没有商品");
            return ok(false);
        }
            for (int i = 0; i < goodsupdate.size(); i++) {
                String goodid = goodsupdate.get(i);
                StoreGoods storeGoods = storeGoodsService.selectByPk(goodid);
                storeGoods.setOnsale(1);
                storeGoods.setOnoffsaletime(Timestamp.valueOf(StoreDateutil.getStringDate()));//上下架时间
                storeGoodsService.updateByPk(goodid, storeGoods);
            }
            return ok(true);
    }

    @RequestMapping(value = "/wxapp/goodsinfo/{shopid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——删除商品",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodsinfoupd(@PathVariable String shopid,HttpServletRequest request){
        String prdid = request.getParameter("prdid");
        if (!StoresaUtls.blutls(shopid)||!StoresaUtls.blutls(prdid)){
            log.error("删除商品——shopid or prdid值为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("goodsid",prdid);

        List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity);
        if (!storeOrdergoodsList.isEmpty()){
            log.error("删除商品——没有查询到该商品");
            return ok(false);
        }
        List<StoreGoodspics> storeGoodspicsList = storeGoodspicsService.select(queryParamEntity);
        for (StoreGoodspics storeGoodspics : storeGoodspicsList){
            String goodpicid = storeGoodspics.getId();
            storeGoodspicsService.deleteByPk(goodpicid);
        }
        storeGoodsService.deleteByPk(prdid);
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/batchdeletegoods/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——批量删除商品",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage batchdeletegoodsdelet(@PathVariable String shopid,@RequestBody ArrayList<String> batchdate) {
        if (!StoresaUtls.blutls(shopid) || batchdate == null) {
            log.error("批量删除商品——shopid or id值为空");
            return ok(false);
        }
        for (int i = 0; i < batchdate.size(); i++) {
            String goodid = batchdate.get(i);
            QueryParamEntity queryParamEntity = new QueryParamEntity();
            queryParamEntity.and("goodsid",goodid);
            List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsService.select(queryParamEntity);
            if (!storeOrdergoodsList.isEmpty()){
                log.error("删除商品——没有查询到该商品");
                return ok(false);
            }
            List<StoreGoodspics> storeGoodspicsList = storeGoodspicsService.select(queryParamEntity);
            for (StoreGoodspics storeGoodspics : storeGoodspicsList){
                String goodpicid = storeGoodspics.getId();
                storeGoodspicsService.deleteByPk(goodpicid);
            }
            storeGoodsService.deleteByPk(goodid);

        }
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/goodslistdrop/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——批量下架商品",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodslistdrop(@PathVariable String shopid,@RequestBody ArrayList<String> goodslisupdate) {
        if (!StoresaUtls.blutls(shopid) || goodslisupdate == null) {
            log.error("批量下架商品——shopid or id值为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid", shopid);
        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        if (storeGoodsList.isEmpty()) {
            log.error("批量下架商品——该商店尚没有商品");
            return ok(false);
        }
        for (int i = 0; i < goodslisupdate.size(); i++) {
            String goodid = goodslisupdate.get(i);
            StoreGoods storeGoods = storeGoodsService.selectByPk(goodid);
            storeGoods.setOnsale(0);
            storeGoods.setOnoffsaletime(Timestamp.valueOf(StoreDateutil.getStringDate()));//上下架时间
            storeGoodsService.updateByPk(goodid, storeGoods);
        }
        return ok(true);
    }

    @RequestMapping(value = "/wxapp/goodsinfolist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——查询商品",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodsinfolistselect(@PathVariable String shopid ,HttpServletRequest request,HttpServletResponse response){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(page)||!StoresaUtls.blutls(limit)){
            log.error("分页异常");
            System.out.print(page+"/n"+limit);
            return ok(false);
        }
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String prdCatg = request.getParameter("prdCatg");
        String soldnum1 = request.getParameter("soldnum1");
        String soldnum2 = request.getParameter("soldnum2");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        if (StoresaUtls.blutls(shopid)){
            queryParamEntity.and("bizid",shopid);
        }
        if (StoresaUtls.blutls(status)){
            if (status.equals("2")) {
                queryParamEntity.and("onsale", "0");
            }
            if (status.equals("1")){
                queryParamEntity.and("onsale", "1");
            }
        }
        if (StoresaUtls.blutls(prdCatg)){
            queryParamEntity.and("goodstype",prdCatg);
        }
        if (StoresaUtls.blutls(name)){
            queryParamEntity.where("name","like","%"+name+"%");
        }
//        @哥不是很帅的  参数大于等于100， queryParamEntity.where('price','gte',100.00)， 小于等于，用lte
        if (StoresaUtls.blutls(soldnum1)){
            queryParamEntity.where("soldnum","gte",Integer.valueOf(soldnum1));
        }
        if (StoresaUtls.blutls(soldnum2)){
            queryParamEntity.where("soldnum","lte",Integer.valueOf(soldnum2));
        }
        if (StoresaUtls.blutls(price1)){
            queryParamEntity.where("originprice","gte",Float.valueOf(price1));
        }
        if (StoresaUtls.blutls(price2)){
            queryParamEntity.where("originprice","lte",Float.valueOf(price2));
        }

        if (StoresaUtls.blutls(price1)){
            queryParamEntity.where("siteprice","gte",Float.valueOf(price1));
        }
        if (StoresaUtls.blutls(price2)){
            queryParamEntity.where("siteprice","lte",Float.valueOf(price2));
        }

        List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);
        if (storeGoodsList.isEmpty()){
            log.error("商家——查询商品——没有查询到");
            return ok(false);
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreGoods storeGoods : storeGoodsList){
            HashMap ListhashMap = new HashMap();
            ListhashMap.put("id",storeGoods.getId());
            ListhashMap.put("name",storeGoods.getName());
            ListhashMap.put("catg",Integer.valueOf(storeGoods.getGoodstype()));
            ListhashMap.put("selltype",storeGoods.getSelltype());
            ListhashMap.put("onsiteprice",storeGoods.getSiteprice());
            ListhashMap.put("outsideprice",storeGoods.getOriginprice());
            ListhashMap.put("packfee",storeGoods.getPackfee());
            ListhashMap.put("date",storeGoods.getOnoffsaletime());
            ListhashMap.put("stocknum",Integer.valueOf(storeGoods.getStocknum()));
            ListhashMap.put("soldnum",Integer.valueOf(storeGoods.getSoldnum()));
            ListhashMap.put("desc",storeGoods.getDescrip());
            ListhashMap.put("sequ",Integer.valueOf(storeGoods.getSequ()));

            if (storeGoods.getOnsale()==1) {
                ListhashMap.put("onsaledrop", "1");
            }
            if (storeGoods.getOnsale()==0) {
                ListhashMap.put("onsaledrop", "2");
            }
            if (storeGoods.getUserpointflag().equals("1")) {
                ListhashMap.put("point", "1");
            }
            if (storeGoods.getUserpointflag().equals("2")) {
                ListhashMap.put("point", "2");
            }
            ListhashMap.put("recmd", storeGoods.getRecommend());
            ArrayList<Map<String,String>> arrayListimg = new  ArrayList<Map<String,String>>();
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("goodsid",storeGoods.getId());
            List<StoreGoodspics> storeGoodspicsList = storeGoodspicsService.select(queryParamEntity1);
;           for (StoreGoodspics storeGoodspics : storeGoodspicsList){
                 HashMap hashMapimg = new HashMap();
                 hashMapimg.put("url",storeGoodspics.getGoodspic1());
                 hashMapimg.put("name",storeGoodspics.getName());
                 arrayListimg.add(hashMapimg);
            }
            ListhashMap.put("prodfileList",arrayListimg);
            arrayList.add(ListhashMap);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("goodsList",arrayList);
        hashMap.put("total",arrayList.size());
        return ok(hashMap);
    }
    @RequestMapping(value = "/wxapp/goodssequincatg/{catgid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——查询商品最大值",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodssequincatgselsect(@PathVariable String catgid){
           if (!StoresaUtls.blutls(catgid)){
               log.error("商家——查询商品——没有查询到");
               return ok(false);
           }
           QueryParamEntity queryParamEntity = new QueryParamEntity();
           queryParamEntity.and("goodstype",catgid);
           List<StoreGoods> storeGoodsList = storeGoodsService.select(queryParamEntity);

        if (!storeGoodsList.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<>();//定义一个接受数组数组
            for (StoreGoods storeGoods : storeGoodsList) {
                arrayList.add(Integer.valueOf(storeGoods.getSequ()));
            };
            int max=arrayList.get(0);
            for(int i = 0;i<arrayList.size();i++) {
                if(max<arrayList.get(i)) {
                    max = arrayList.get(i);
                }
            }
            return ok(String.valueOf(max));
        }
        return ok(false);
    }
    //商品分类==================================================================================================================
    @RequestMapping(value = "/wxapp/goodstypelist", method = RequestMethod.GET)
    @ApiOperation(value = "商家——商品分类——查询",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodstypelistselect(HttpServletRequest request,HttpServletResponse response) {
        String shopid = request.getParameter("shopid");
        String goodstype = request.getParameter("goodstype");
        if (!StoresaUtls.blutls(shopid)) {
            response.setStatus(400);
            log.error("数据错误——/wxapp/goodstypelist—处理");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid", shopid);
        if (StoresaUtls.blutls(goodstype)) {
            queryParamEntity.where("id", "like", "%" + goodstype + "%");
        }
        List<StoreGoodstype> storeGoodstypeList = storeGoodstypeService.select(queryParamEntity);
        if (storeGoodstypeList.isEmpty()) {
            log.error("数据错误——没有查询到该商店有商品分类");
            return ok(false);
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreGoodstype storeGoodstype : storeGoodstypeList) {
            HashMap typehashMap = new HashMap();
            typehashMap.put("id",storeGoodstype.getId());
            typehashMap.put("name",storeGoodstype.getName());
            typehashMap.put("sequ",storeGoodstype.getSequ());
            typehashMap.put("status",storeGoodstype.getStatus());
            arrayList.add(typehashMap);
            }
        HashMap hashMap = new HashMap();
        hashMap.put("goodsTypes",arrayList);
        return ok(hashMap);
        }
    @RequestMapping(value = "/wxapp/goodstype", method = RequestMethod.POST)
    @ApiOperation(value = "商家——商品分类——添加",notes = "添加")
    @ApiImplicitParams({
//            @PathVariable String ordered,
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodstypeinset(@RequestBody Map<String, String> goodstypeinset){
        String shopid = goodstypeinset.get("shopid");
        String name = goodstypeinset.get("name");
        String sequ = goodstypeinset.get("sequ");
        String status = goodstypeinset.get("status");
        if (!StoresaUtls.blutls(shopid)||!StoresaUtls.blutls(name)||!StoresaUtls.blutls(sequ)||!StoresaUtls.blutls(status)){
            log.error("数据错误——/wxapp/goodstype——为空");
            return ok(false);
        }
        StoreGoodstype storeGoodstype = new StoreGoodstype();
        storeGoodstype.setBizid(shopid);
        storeGoodstype.setName(name);
        storeGoodstype.setSequ(sequ);
        storeGoodstype.setStatus(status);
        storeGoodstypeService.insert(storeGoodstype);
        return ok();
    }
    @RequestMapping(value = "/wxapp/goodstype", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——商品分类——修改",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodstypeupdate(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sequ = request.getParameter("sequ");
        String status = request.getParameter("status");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——/wxapp/goodstype——修改id为空");
            return ok(false);
        }
        StoreGoodstype storeGoodstype = storeGoodstypeService.selectByPk(id);
        if (StoresaUtls.blutls(name)){
            storeGoodstype.setName(name);
        }
        if (StoresaUtls.blutls(sequ)){
            storeGoodstype.setSequ(sequ);
        }
        if (StoresaUtls.blutls(status)){
            storeGoodstype.setStatus(status);
        }
        storeGoodstypeService.updateByPk(id,storeGoodstype);
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/goodstype/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——商品分类——添加",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage goodstypelistselect(@PathVariable String id){
        if (!StoresaUtls.blutls(id)) {
            log.error("数据错误——/wxapp/goodstype/{id}—删除id为空");
            return ok(false);
        }
        storeGoodstypeService.deleteByPk(id);
        return ok(true);
    }

    //商家处理退款==================================================================================================================

    @RequestMapping(value = "/wxapp/orderrefundreply", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——退款——商家处理",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage storeservicesetting(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String options = request.getParameter("options");
        if (!StoresaUtls.blutls(id)||!StoresaUtls.blutls(options)) {
            response.setStatus(400);
            log.error("数据错误——/wxapp/orderrefundreply—处理");
            return ok(false);
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(id);
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid",id);
        List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity);
        if (storeRefundpsList.isEmpty() || storeOrderinfo == null){
            log.error("数据错误——storeRefundpsList ==storeOrderinfo —为空");
            return ok(false);
        }
        String funid = storeRefundpsList.get(0).getId();
        String orderno = storeOrderinfo.getOrderno();
        String patype = storeOrderinfo.getPaytype();
        String userid = storeOrderinfo.getUserid();
        String bizId = storeOrderinfo.getBizid();
        float moneyse = storeOrderinfo.getIncome();
        String orderNo = storeOrderinfo.getOrderno();
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(userid);
        float wallet = storeMemberinfo.getWallet();
        StoreRefundps storeRefundps = storeRefundpsService.selectByPk(funid);
        if (options.equals("1")) {//1---同意退款
            String money = request.getParameter("money");
            if (!StoresaUtls.blutls(money)) {
                log.error("数据错误——/wxapp/orderrefundreply—处理");
                return ok(false);
            }
            if (patype.equals("3")) {//钱包支付
                //1.商家同意退款金额与用户账号所余金额相加，并跟新到用户表中
                storeMemberinfo.setWallet(wallet+Float.valueOf(money));
                storeMemberinfoService.updateByPk(userid,storeMemberinfo);

                //2.将订单表里状态改为8.交易关闭，
                storeOrderinfo.setStatus("11");
                storeOrderinfo.setIncome(0);
                storeOrderinfoService.updateByPk(id,storeOrderinfo);

                storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeRefundps.setStatus("3");
                storeRefundps.setRefundmoney(Float.valueOf(money));
                storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                storeRefundps.setCompletetime(Timestamp.valueOf(StoreDateutil.getStringDate()));//退款完成时间
                storeRefundpsService.updateByPk(funid,storeRefundps);
                //钱包退款——添加记录
                String Judge = "5";
                storeWalletrcdService.Walletrefund(userid,orderno, Float.valueOf(money),Judge);

            }
            if (patype.equals("1")){//微信支付
                int totalFee= (int) (Float.valueOf(moneyse)*100);
                int refundFee= (int) (Float.valueOf(money)*100);

                boolean refund = wxminiappService.applyRefund(bizId, orderNo, totalFee, refundFee);

                if(refund){
                    storeRefundps.setStatus("3");
                    storeRefundps.setRefundmoney(Float.valueOf(money));
                    storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                    storeRefundps.setCompletetime(Timestamp.valueOf(StoreDateutil.getStringDate()));
                    storeRefundpsService.updateByPk(funid,storeRefundps);
                    storeOrderinfo.setStatus("11");
                    storeOrderinfo.setLastrsn("4");
                    storeOrderinfoService.updateByPk(id,storeOrderinfo);
                }else {
                    log.error("数据错误——/wxapp/orderrefundreply—处理——退款失败");
                }
            }
        }
        if (options.equals("2")) {//2---拒绝退款
            String notes = request.getParameter("notes");
            if (!StoresaUtls.blutls(notes)){
            log.error("数据错误——商家拒绝退款备注为空");
            return ok(false);
            }
            storeRefundps.setStatus("4");
            storeRefundps.setOpsnotes(notes);
            storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));//商家处理时间
            storeRefundpsService.updateByPk(funid,storeRefundps);
            storeOrderinfo.setStatus("3");
            storeOrderinfo.setLastrsn("3");
            storeOrderinfoService.updateByPk(id,storeOrderinfo);
        }
        return ok(true);
    }
    @RequestMapping(value = "/wxapp/orderrefundinfo/{ordered}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——退款——获取退款信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage orderrefundinfo(@PathVariable String ordered) {
        if (!StoresaUtls.blutls(ordered)) {
            log.error("数据错误——orderid为空");
            return ok(false);
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(ordered);
        StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(storeOrderinfo.getUserid());
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid", ordered);
        List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity);
        if (!storeRefundpsList.isEmpty()) {
            HashMap hashMap = new HashMap();
            hashMap.put("reasons", storeRefundpsList.get(0).getNotes());
            hashMap.put("money", storeRefundpsList.get(0).getMoney());
            hashMap.put("name", storeMemberinfo.getName());
            hashMap.put("phone", storeRefundpsList.get(0).getTel());
            hashMap.put("applytime", storeRefundpsList.get(0).getApplytime());
            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("refundid", storeRefundpsList.get(0).getId());
            ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
            List<StoreRefundpic> storeRefundpicList = storeRefundpicService.select(queryParamEntity1);
            if (!storeRefundpicList.isEmpty()){
                for (StoreRefundpic storeRefundpic : storeRefundpicList) {
                    HashMap hashMap1 = new HashMap();
                    hashMap1.put("img", storeRefundpic.getImage());
                    hashMap1.put("key", storeRefundpic.getSequ());
                    arrayList.add(hashMap1);
                }
            }
            hashMap.put("images", arrayList);
            return ok(hashMap);
        }
        log.error("数据错误——获取退款信息为空，或多值");
        return ok(false);
    }

    //==餐桌信息=============================================================================================================
    @RequestMapping(value = "/wxapp/tableroominfo/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加餐桌",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage tableroominfo(@PathVariable String shopid,@RequestBody Map<String, String> tableinfoinse){
        String roomorhall = tableinfoinse.get("roomorhall");//1—包间， 2—大厅
        String name = tableinfoinse.get("name"); //包间或大厅名字
        String tableno = tableinfoinse.get("tableno");//餐桌编号
        String roomno = tableinfoinse.get("roomno");  //包间编号
        String personnum = tableinfoinse.get("personnum");  //就餐人数
        String minexps = tableinfoinse.get("minexps"); //最低消费

        if (!StoresaUtls.blutls(shopid)|| !StoresaUtls.blutls(roomorhall)|| !StoresaUtls.blutls(name)|| !StoresaUtls.blutls(tableno)
                || !StoresaUtls.blutls(personnum)|| !StoresaUtls.blutls(minexps)){
            log.error("数据错误——添加餐桌");
            return ok(false);
        }
        StoreTableinfo storeTableinfo = new StoreTableinfo();
        storeTableinfo.setBizid(shopid);
        storeTableinfo.setRoomname(name);
        storeTableinfo.setTableno(tableno);
        storeTableinfo.setRoomno(roomno);
        storeTableinfo.setTabtype(roomorhall);
        storeTableinfo.setMinexpense(Float.valueOf(minexps));
        storeTableinfo.setStatus("1");
        storeTableinfo.setPersonnum(personnum);
        storeTableinfoService.insert(storeTableinfo);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/tableroomlist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——餐桌——查询所有包间餐桌",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage tableroomlistupdate(@PathVariable String shopid,HttpServletRequest request,HttpServletResponse response){
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误——商家——餐桌——查询所有包间餐桌");
            return ok(false);
        }
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String dft = df.format(new Date()); // new Date()为获取当前系统时间
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",shopid);
        List<StoreTableinfo> storeTableinfoList = storeTableinfoService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
        if (!storeTableinfoList.isEmpty()) {
            for (StoreTableinfo storeTableinfo : storeTableinfoList) {
                HashMap tabhashMap = new HashMap();
                tabhashMap.put("id", storeTableinfo.getId());
                tabhashMap.put("name", storeTableinfo.getRoomname());
                tabhashMap.put("tableno", storeTableinfo.getTableno());
                tabhashMap.put("roomno", storeTableinfo.getRoomno());
                tabhashMap.put("roomorhall", storeTableinfo.getTabtype());// 1--包间， 2---散桌
                tabhashMap.put("minexps", storeTableinfo.getMinexpense());
                tabhashMap.put("personnum", storeTableinfo.getPersonnum());
                tabhashMap.put("status", storeTableinfo.getStatus());// 1--- 空闲   2---已预定 3---使用中,10----下架
                arrayList.add(tabhashMap);
            }
        }
        hashMap.put("tables",arrayList);
        hashMap.put("total",storeTableinfoList.size());
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/tableallotlist/{orderid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——餐桌——查询所有预约订单客户需求包间or餐桌",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage tableallotlistupdate(@PathVariable String orderid,HttpServletRequest request,HttpServletResponse response){
        if (!StoresaUtls.blutls(orderid)){
            log.error("数据错误——商家——餐桌——查询所有包间餐桌");
            return ok(false);
        }
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        String tabtype = storeOrderinfo.getTabtype();
        String bizid = storeOrderinfo.getBizid();

        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid",bizid).and("status","1");
        if (tabtype.equals("1")){
            queryParamEntity.and("tabtype",tabtype);
        }
        if (tabtype.equals("2")){
            queryParamEntity.and("tabtype",tabtype);
        }

        List<StoreTableinfo> storeTableinfoList = storeTableinfoService.select(queryParamEntity);
        if (storeTableinfoList.isEmpty()){
            log.error("数据错误——商家——餐桌——查询所有包间餐桌—值为空");
            return ok(false);
        }
        HashMap hashMap = new HashMap();
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String, String>>();
        for(StoreTableinfo storeTableinfo : storeTableinfoList){
            HashMap tabhashMap = new HashMap();
            tabhashMap.put("id",storeTableinfo.getId());
            tabhashMap.put("name",storeTableinfo.getRoomname());
            tabhashMap.put("tableno",storeTableinfo.getTableno());
            tabhashMap.put("roomno",storeTableinfo.getRoomno());
            tabhashMap.put("roomorhall",storeTableinfo.getTabtype());// 1--包间， 2---散桌
            tabhashMap.put("minexps",storeTableinfo.getMinexpense());
            tabhashMap.put("personnum",storeTableinfo.getPersonnum());
            tabhashMap.put("status",storeTableinfo.getStatus());// 1--- 空闲   2---已预定 3---使用中,10----下架
            arrayList.add(tabhashMap);
        }

        QueryParamEntity queryParamEntity1 = new  QueryParamEntity();
        queryParamEntity1.and("orderid",orderid);
        ArrayList<String> tabidlist = new ArrayList<>();//定义一个接受数组数组
        List<StoreOrdertables> storeOrdertablesList = storeOrdertablesService.select(queryParamEntity1);
        if (!storeOrdertablesList.isEmpty()) {
            for (StoreOrdertables storeOrdertables : storeOrdertablesList) {
                String id = storeOrdertables.getTableid();
                tabidlist.add(id);
                StoreTableinfo storeTableinfo1 = storeTableinfoService.selectByPk(id);
                HashMap ortabhashMap = new HashMap();
                ortabhashMap.put("id",storeTableinfo1.getId());
                ortabhashMap.put("name",storeTableinfo1.getRoomname());
                ortabhashMap.put("tableno",storeTableinfo1.getTableno());
                ortabhashMap.put("roomno",storeTableinfo1.getRoomno());
                ortabhashMap.put("roomorhall",storeTableinfo1.getTabtype());// 1--包间， 2---散桌
                ortabhashMap.put("minexps",storeTableinfo1.getMinexpense());
                ortabhashMap.put("personnum",storeTableinfo1.getPersonnum());
                ortabhashMap.put("status",storeTableinfo1.getStatus());// 1--- 空闲   2---已预定 3---使用中,10----下架
                arrayList.add(ortabhashMap);
            }
        }
        hashMap.put("tableList",arrayList);
        hashMap.put("seletedIds",tabidlist);
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/tableroominfo/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——餐桌——修改餐桌信息或包间",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
//    url：/wxapp/tableroominfo/12486/?id=1&roomorhall=1&name=%E7%BA%A2%E7%81%AB%E5%8E%85&tableno=10011&personnum=10&minexps=100.00
    public ResponseMessage tableroominfoupdate(@PathVariable String id, HttpServletRequest request ,HttpServletResponse response){
        String roomorhall = request.getParameter("roomorhall");
        String taid = request.getParameter("id");
        String name = request.getParameter("name");
        String tableno = request.getParameter("tableno");//餐桌编号
        String personnum = request.getParameter("personnum");
        String minexps = request.getParameter("minexps");
        String roomno = request.getParameter("roomno");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误——商家——餐桌——修改餐桌信息或包间—值为空");
            return ok(false);
        }
        StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(taid);
        if (storeTableinfo==null){
            log.error("数据错误—没有查询到餐桌或包间");
            return ok(false);
        }
        if (StoresaUtls.blutls(name)){
            storeTableinfo.setRoomname(name);
        }
        if (StoresaUtls.blutls(tableno)){
            storeTableinfo.setTableno(tableno);
        }
        if (StoresaUtls.blutls(roomno)){
            storeTableinfo.setRoomno(roomno);
        }
        if (StoresaUtls.blutls(roomorhall)){
            storeTableinfo.setTabtype(roomorhall);
        }
        if (StoresaUtls.blutls(minexps)){
            storeTableinfo.setMinexpense(Float.valueOf(minexps));
        }
        if (StoresaUtls.blutls(personnum)){
            storeTableinfo.setPersonnum(personnum);
        }
        storeTableinfoService.updateByPk(taid,storeTableinfo);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/tableroominfo/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——餐桌——删除餐桌信息或包间",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage tableroominfodelete(@PathVariable String id){
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—删除id餐桌或包间为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("tableid",id);
        List<StoreOrdertables> storeOrdertablesList = storeOrdertablesService.select(queryParamEntity);
        if (!storeOrdertablesList.isEmpty()){
            storeTableinfoService.deleteByPk(id);
            return ok(true);
        }
        return ok(false);
    }

    @RequestMapping(value ="/wxapp/tableroominfo{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——餐桌——修改餐桌状态",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage tableroominfodelete(@PathVariable  String id,HttpServletRequest request,HttpServletResponse response){
        String status = request.getParameter("status");
        String tasid = request.getParameter("id");
        if (!StoresaUtls.blutls(tasid) || !StoresaUtls.blutls(status)){
            log.error("数据错误—商家——餐桌——修改餐桌状态");
            return ok(false);
        }
        StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(tasid);
        storeTableinfo.setStatus(status);
        storeTableinfoService.updateByPk(tasid,storeTableinfo);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/batchresettablestatus", method = RequestMethod.POST)
    @ApiOperation(value = "商家——餐桌——批量修改餐桌状态",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage batchresettablestatus(@RequestBody ArrayList<String> bhttstausid){
        if (bhttstausid == null){
            log.error("数据错误—商家——餐桌——批量修改餐桌状态");
            return ok(false);
        }
        JSONArray jsonArray = JSONArray.fromObject(bhttstausid);
        for (int i = 0; i< jsonArray.size();i++){
            String id =  jsonArray.getString(i);
            StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(id);
            storeTableinfo.setStatus("1");
            storeTableinfoService.updateByPk(id,storeTableinfo);
        }
        return ok(true);
    }

    @RequestMapping(value ="/wxapp/tableallocate/{orderid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——餐桌——为用户分配餐桌",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage tableallocateupdate (@PathVariable String orderid,@RequestBody ArrayList<String> tavlelist ){
        if(!StoresaUtls.blutls(orderid) ||  tavlelist==null){
            log.error("数据错误—商家——餐桌——为用户分配餐桌");
            return ok(false);
        }
        QueryParamEntity queryParamEntity1 = QueryParamEntity.single("orderid",orderid);
        StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
        List<StoreOrdertables> storeOrdertablesList = storeOrdertablesService.select(queryParamEntity1);
        if (!storeOrdertablesList.isEmpty()) {
            for (StoreOrdertables storeOrdertables : storeOrdertablesList) {
                String id = storeOrdertables.getTableid();
                storeOrdertablesService.deleteByPk(id);
                StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(id);
                storeTableinfo.setStatus("1");
                storeTableinfoService.updateByPk(id,storeTableinfo);
            }
        }
        for (int i = 0; i<tavlelist.size();i++){
            String id = tavlelist.get(i);
            StoreTableinfo storeTableinfo = storeTableinfoService.selectByPk(id);
            if (storeTableinfo == null){
                log.error("数据错误—商家——餐桌——为用户分配餐桌—没有查询到该餐桌");
                return ok(false);
            }
            if (!storeTableinfo.getStatus().equals("1")){
                log.error("数据错误—商家——餐桌——为用户分配餐桌—该餐桌已预定 or 使用中 or下架");
                return ok(false);
            }
            StoreOrdertables storeOrdertables = new StoreOrdertables();
            storeOrdertables.setOrderid(orderid);
            storeOrdertables.setTableid(id);
            storeOrdertables.setStarttime(storeOrderinfo.getArrivaltime());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=null;
            try {
                date = sdf.parse(String.valueOf(storeOrderinfo.getArrivaltime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar ca=Calendar.getInstance();
            ca.setTime(date);
            ca.add(Calendar.HOUR_OF_DAY, 2);
            storeOrdertables.setEndtime(Timestamp.valueOf(sdf.format(ca.getTime())));

            storeOrdertablesService.insert(storeOrdertables);
            storeTableinfo.setStatus("2");
            storeTableinfoService.updateByPk(id,storeTableinfo);
        }
        storeOrderinfo.setStatus("6");
        storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/tableqrcode", method = RequestMethod.GET)
    @ApiOperation(value = "商家——餐桌——获取餐桌二维码",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage batchresettablestatus(HttpServletRequest request, HttpServletResponse response){
        String bizId = request.getParameter("shopId");
        String tableid = request.getParameter("tableId");
        if (!StoresaUtls.blutls(bizId) ||!StoresaUtls.blutls(tableid)){
            log.error("数据错误—商家——餐桌——获取餐桌二维码");
            return ok(false);
        }
        String page = "zh_cjdianc/pages/seller/showGoods";
        Random random = new Random();
        int sj = random.nextInt(9000) + 1000;//获取四位随机数
        String randstr = String.valueOf(sj);
        String userId = null;
        String wxminiapp = wxminiappService.getWxCode( bizId, tableid, userId, page, randstr);
        return ok(wxminiapp);
    }
    //==满减活动信息=============================================================================================================
//    url: /wxapp/fullcutslist/{shopid}/？page=1&limit=10
    @RequestMapping(value ="/wxapp/fullcutslist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——获取满减活动信息列表",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage fullcutslistupdate(@PathVariable String shopid,HttpServletRequest request){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——获取满减活动信息列表id为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",shopid).and("cptype","2");
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        if (storeCouponsList.isEmpty()){
            log.error("数据错误—商家——没有查询到满减活动信息");
            return ok(false);
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreCoupons storeCoupons : storeCouponsList){
            HashMap scshashMap = new HashMap();
            scshashMap.put("id",storeCoupons.getId());
            scshashMap.put("name",storeCoupons.getName());
            scshashMap.put("datests",storeCoupons.getBegintime());
            scshashMap.put("dateend",storeCoupons.getEndtime());
            scshashMap.put("price1",Float.valueOf(storeCoupons.getStartline()));// 满金额
            scshashMap.put("scope",storeCoupons.getScope()); // 1--店内，2--外卖， 3--店内外卖
            scshashMap.put("price2",Float.valueOf(storeCoupons.getSubtract()));// 满金额减金额
            arrayList.add(scshashMap);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("fullcuts",arrayList);
        hashMap.put("total",arrayList.size());
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/fullcutsinfo", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——更新满减活动信息",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
//    /wxapp/fullcutsinfo/?id=1&name=100%E5%87%8F20&price1=100&price2=15&scope=1&datests=2018-12-05&dateend=2018-12-25
    public ResponseMessage fullcutsinfoupdate(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");
        String scope = request.getParameter("scope");
        String datests = request.getParameter("datests");
        String dateend = request.getParameter("dateend");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——修改活动信息列表id为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——修改活动信息列表—没有查到信息");
            return ok(false);
        }
        if(StoresaUtls.blutls(name)){
            storeCoupons.setName(name);
        }
        if(StoresaUtls.blutls(price1)){
            storeCoupons.setStartline(Float.valueOf(price1));
        }
        if(StoresaUtls.blutls(price2)){
            storeCoupons.setSubtract(Float.valueOf(price2));
        }
        if(StoresaUtls.blutls(scope)){
            storeCoupons.setStatus(scope);
        }
        if(StoresaUtls.blutls(datests)){
            String datestsse =  datests.substring(0,10);
            storeCoupons.setBegintime(Timestamp.valueOf((datestsse+" "+"00:00:00")));
        }
        if(StoresaUtls.blutls(dateend)){
            String dateendse =  dateend.substring(0,10);
            storeCoupons.setEndtime(Timestamp.valueOf((dateendse+" "+"23:59:59")));
        }
        storeCouponsService.updateByPk(id,storeCoupons);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/fullcutsinfo/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——删除满减活动信息",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage fullcutsinfodelete(@PathVariable String id){
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——删除满减活动信息id为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——删除活动信息列表—没有查到信息");
            return ok(false);
        }
        storeCouponsService.deleteByPk(id);
        return ok(false);
    }
    @RequestMapping(value ="/wxapp/fullcutsinfo/{bizid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加满减活动信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage fullcutsinfoinset(@PathVariable String bizid,@RequestBody Map<String, String> copsinset){
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误—商家——添加满减活动信息id为空");
            return ok(false);
        }
        String name = copsinset.get("name");
        String price1 = copsinset.get("price1");
        String price2 = copsinset.get("price2");
        String scope = copsinset.get("scope");
        String datests = copsinset.get("datests");
        String dateend = copsinset.get("dateend");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(price1)||!StoresaUtls.blutls(price2)||!StoresaUtls.blutls(scope)||!StoresaUtls.blutls(datests)||!StoresaUtls.blutls(dateend)){
            log.error("数据错误—商家——添加满减活动信息为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = new StoreCoupons();
        storeCoupons.setBizid(bizid);
        storeCoupons.setCptype("2");//1--优惠券，2--满减券，3--打折劵，4---红包，
        storeCoupons.setName(name);
        storeCoupons.setScope(scope);
        storeCoupons.setStatus("1");
        storeCoupons.setStartline(Float.valueOf(price1));
        storeCoupons.setSubtract(Float.valueOf(price2));
        String[] datestsse =  datests.split("T");
        String[] dateendse =  dateend.split("T");
        storeCoupons.setBegintime(Timestamp.valueOf((datestsse[0]+" "+"00:00:00")));
        storeCoupons.setEndtime(Timestamp.valueOf((dateendse[0]+" "+"23:59:59")));
        storeCouponsService.insert(storeCoupons);
        return ok(true);
    }



    //==打折活动信息=============================================================================================================
//    url: /wxapp/fullcutslist/{shopid}/？page=1&limit=10
    @RequestMapping(value ="/wxapp/discountlist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——获取打折活动信息列表",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage discountlistselect(@PathVariable String shopid,HttpServletRequest request){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——获取满减活动信息列表id为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",shopid).and("cptype","3");
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        if (storeCouponsList.isEmpty()){
            log.error("数据错误—商家——没有查询到打折活动信息");
            return ok(false);
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreCoupons storeCoupons : storeCouponsList){
            HashMap scshashMap = new HashMap();
            scshashMap.put("id",storeCoupons.getId());
            scshashMap.put("name",storeCoupons.getName());
            scshashMap.put("datests",storeCoupons.getBegintime());
            scshashMap.put("dateend",storeCoupons.getEndtime());
            scshashMap.put("price",Float.valueOf(storeCoupons.getStartline()));// 满金额
            scshashMap.put("scope",storeCoupons.getScope()); // 1--店内，2--外卖， 3--店内外卖
            scshashMap.put("discount",Integer.valueOf(storeCoupons.getDiscount()));// 满金额减金额
            arrayList.add(scshashMap);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("discounts",arrayList);
        hashMap.put("total",arrayList.size());
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/discountinfo", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——更新打折活动信息",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
///wxapp/discountinfo/?id=1&name=7%E6%8A%98%E4%BC%98%E6%83%A0&price=100&discount=75&scope=1&datests=2018-12-05&dateend=2018-12-25
    public ResponseMessage discountinfoupdate(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String scope = request.getParameter("scope");
        String datests = request.getParameter("datests");
        String dateend = request.getParameter("dateend");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——修改活动打折信息列表id为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——修改活动信息列表—没有查到信息");
            return ok(false);
        }
        if(StoresaUtls.blutls(name)){
            storeCoupons.setName(name);
        }
        if(StoresaUtls.blutls(price)){
            storeCoupons.setStartline(Float.valueOf(price));
        }
        if(StoresaUtls.blutls(scope)){
            storeCoupons.setStatus(scope);
        }
        if(StoresaUtls.blutls(discount)){
            storeCoupons.setDiscount(discount);
        }
        if(StoresaUtls.blutls(datests)){
            String datestsse =  datests.substring(0,10);
            storeCoupons.setBegintime(Timestamp.valueOf((datestsse+" "+"00:00:00")));
        }
        if(StoresaUtls.blutls(dateend)){
            String dateendse =  dateend.substring(0,10);
            storeCoupons.setEndtime(Timestamp.valueOf((dateendse+" "+"23:59:59")));
        }
        storeCouponsService.updateByPk(id,storeCoupons);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/discountinfo/{deid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——删除打折活动信息",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage discountinfodelect(@PathVariable String deid){
        if (!StoresaUtls.blutls(deid)){
            log.error("数据错误—商家——删除打折活动信息id为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(deid);
        if (storeCoupons==null){
            log.error("数据错误—商家——删除打折活动信息列表—没有查到信息");
            return ok(false);
        }

        storeCouponsService.deleteByPk(deid);
        return ok(false);
    }
    @RequestMapping(value ="/wxapp/discountinfo/{bizid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加打折活动信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage discountinfoinset(@PathVariable String bizid,@RequestBody Map<String, String> copsinset){
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误—商家——添加打折活动信息id为空");
            return ok(false);
        }
        String name = copsinset.get("name");
        String price = copsinset.get("price");
        String discount = copsinset.get("discount");
        String scope = copsinset.get("scope");
        String datests = copsinset.get("datests");
        String dateend = copsinset.get("dateend");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(price)||!StoresaUtls.blutls(discount)||!StoresaUtls.blutls(scope)||!StoresaUtls.blutls(datests)||!StoresaUtls.blutls(dateend)){
            log.error("数据错误—商家——添加打折活动信息为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = new StoreCoupons();
        storeCoupons.setBizid(bizid);
        storeCoupons.setCptype("3");//1--优惠券，2--满减券，3--打折劵，4---红包，
        storeCoupons.setName(name);
        storeCoupons.setScope(scope);
        storeCoupons.setStatus("1");
        storeCoupons.setSubtract(Float.valueOf(price));
        storeCoupons.setDiscount(discount);
        String[] datestsse =  datests.split("T");
        String[] dateendse =  dateend.split("T");
        storeCoupons.setBegintime(Timestamp.valueOf((datestsse[0]+" "+"00:00:00")));
        storeCoupons.setEndtime(Timestamp.valueOf((dateendse[0]+" "+"23:59:59")));
        storeCouponsService.insert(storeCoupons);
        return ok(true);
    }



    //==优惠卷活动信息=============================================================================================================
//    url: /wxapp/fullcutslist/{shopid}/？page=1&limit=10
    @RequestMapping(value ="/wxapp/couponslist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——获取优惠卷活动信息列表",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage couponslistselect(@PathVariable String shopid,HttpServletRequest request){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——获取优惠卷活动信息列表id为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",shopid).and("cptype","1");
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        if (storeCouponsList.isEmpty()){
            log.error("数据错误—商家——没有查询到优惠卷活动信息");
            return ok(false);
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreCoupons storeCoupons : storeCouponsList){
            HashMap scshashMap = new HashMap();
            scshashMap.put("id",storeCoupons.getId());
            scshashMap.put("name",storeCoupons.getName());
            scshashMap.put("datests",storeCoupons.getBegintime());
            scshashMap.put("dateend",storeCoupons.getEndtime());
            scshashMap.put("price1",Float.valueOf(storeCoupons.getStartline()));// 满金额
            scshashMap.put("scope",storeCoupons.getScope()); // 1--店内，2--外卖， 3--店内外卖
            scshashMap.put("price2",Float.valueOf(storeCoupons.getSubtract()));// 满金额减金额
            scshashMap.put("totalnum",Integer.valueOf(storeCoupons.getTotal()));// 优惠券总数
            scshashMap.put("freenum",Integer.valueOf(storeCoupons.getFree()));// 可领用数
            arrayList.add(scshashMap);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("coupons",arrayList);
        hashMap.put("total",arrayList.size());
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/couponsinfo", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——更新满减活动信息",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
///?id=1&name=10%E5%85%83%E4%BC%98%E6%83%A0&price1=10&price2=10&scope=1&datests=2018-12-05&dateend=2018-12-25&total=10000
    public ResponseMessage couponsinfoupdate(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price1 = request.getParameter("price1");
        String price2 = request.getParameter("price2");
        String scope = request.getParameter("scope");
        String datests = request.getParameter("datests");
        String dateend = request.getParameter("dateend");
        String total = request.getParameter("total");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——修改活动信息列表id为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——修改活动信息列表—没有查到信息");
            return ok(false);
        }
        if(StoresaUtls.blutls(name)){
            storeCoupons.setName(name);
        }
        if(StoresaUtls.blutls(price1)){
            storeCoupons.setStartline(Float.valueOf(price1));
        }
        if(StoresaUtls.blutls(price2)){
            storeCoupons.setSubtract(Float.valueOf(price2));
        }
        if(StoresaUtls.blutls(scope)){
            storeCoupons.setStatus(scope);
        }
        if(StoresaUtls.blutls(datests)){
            String datestsse = datests.substring(0,10);
            storeCoupons.setBegintime(Timestamp.valueOf((datestsse+" "+"00:00:00")));
        }
        if(StoresaUtls.blutls(dateend)){
            String dateendse = dateend.substring(0,10);
            storeCoupons.setEndtime(Timestamp.valueOf((dateendse+" "+"23:59:59")));
        }
        if (StoresaUtls.blutls(total)){
            storeCoupons.setTotal(total);
            storeCoupons.setFree(total);
        }
        storeCouponsService.updateByPk(id,storeCoupons);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/couponsinfo/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——删除优惠卷活动信息",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage couponsinfodelete(@PathVariable String id){
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——删除优惠卷活动信息id为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("couponsid",id);
        List<StoreOrdercoupons> storeOrdercouponsList = storeOrdercouponsService.select(queryParamEntity);
        if (!storeOrdercouponsList.isEmpty()){
            log.error("数据错误活动信息id不为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——删除优惠卷活动信息列表—没有查到信息");
            return ok(false);
        }
        storeCouponsService.deleteByPk(id);
        return ok(false);
    }
    @RequestMapping(value ="/wxapp/couponsinfo/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加优惠卷活动信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
//    {"name":"大优惠","price1":"1000","price2":"100","scope":"2","datests":"2018-12-23T16:00:00.000Z","dateend":"2018-12-26T16:00:00.000Z","total":"2000"}
    public ResponseMessage couponsinfoinset(@PathVariable String shopid,@RequestBody Map<String, String> copsinset){
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——添加满减活动信息id为空");
            return ok(false);
        }
        String name = copsinset.get("name");
        String price1 = copsinset.get("price1");
        String price2 = copsinset.get("price2");
        String scope = copsinset.get("scope");
        String datests = copsinset.get("datests");
        String dateend = copsinset.get("dateend");
        String total = copsinset.get("total");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(price1)||!StoresaUtls.blutls(price2)||!StoresaUtls.blutls(scope)||!StoresaUtls.blutls(datests)||!StoresaUtls.blutls(dateend)){
            log.error("数据错误—商家——添加满减活动信息为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = new StoreCoupons();
        storeCoupons.setBizid(shopid);
        storeCoupons.setCptype("1");//1--优惠券，2--满减券，3--打折劵，4---红包，
        storeCoupons.setName(name);
        storeCoupons.setScope(scope);
        storeCoupons.setStatus("1");
        storeCoupons.setFree(total);
        storeCoupons.setStartline(Float.valueOf(price1));
        storeCoupons.setSubtract(Float.valueOf(price2));
        storeCoupons.setTotal(total);
        String[] datestsse =  datests.split("T");
        String[] dateendse =  dateend.split("T");
        storeCoupons.setBegintime(Timestamp.valueOf((datestsse[0]+" "+"00:00:00")));
        storeCoupons.setEndtime(Timestamp.valueOf((dateendse[0]+" "+"23:59:59")));
        storeCouponsService.insert(storeCoupons);
        return ok(true);
    }
    //==红包活动信息=============================================================================================================
//    url: /wxapp/fullcutslist/{shopid}/？page=1&limit=10
    @RequestMapping(value ="/wxapp/redpacketlist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——获取红包活动信息列表",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage redpacketlistselect(@PathVariable String shopid,HttpServletRequest request){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——获取红包活动信息列表id为空");
            return ok(false);
        }
        if (!StoresaUtls.blutls(page)||!StoresaUtls.blutls(limit)){
            log.error("分页异常");
            System.out.print(page+"/n"+limit);
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",shopid).and("cptype","4");
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始
        List<StoreCoupons> storeCouponsList = storeCouponsService.select(queryParamEntity);
        if (storeCouponsList.isEmpty()){
            log.error("数据错误—商家——没有查询到红包活动信息");
            return ok(false);
        }
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreCoupons storeCoupons : storeCouponsList){
            HashMap scshashMap = new HashMap();
            scshashMap.put("id",storeCoupons.getId());
            scshashMap.put("name",storeCoupons.getName());
            scshashMap.put("datests",storeCoupons.getBegintime());
            scshashMap.put("dateend",storeCoupons.getEndtime());
            scshashMap.put("scope",storeCoupons.getScope()); // 1--店内，2--外卖， 3--店内外卖
            scshashMap.put("price",Float.valueOf(storeCoupons.getSubtract()));// 满金额减金额
            scshashMap.put("totalnum",Integer.valueOf(storeCoupons.getTotal()));// 优惠券总数
            scshashMap.put("freenum",Integer.valueOf(storeCoupons.getFree()));// 可领用数
            arrayList.add(scshashMap);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("redpackets",arrayList);
        hashMap.put("total",arrayList.size());
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/redpacketinfo", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——更新红包活动信息",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
//    id=1&name=10%E5%85%83%E7%BA%A2%E5%8C%85&price=100&scope=1&datests=2018-12-05&dateend=2018-12-25&total=10000
    public ResponseMessage redpacketinfoupdate(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String scope = request.getParameter("scope");
        String datests = request.getParameter("datests");
        String dateend = request.getParameter("dateend");
        String total = request.getParameter("total");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——修改活动信息列表id为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——修改活动信息列表—没有查到信息");
            return ok(false);
        }
        if(StoresaUtls.blutls(name)){
            storeCoupons.setName(name);
        }
        if(StoresaUtls.blutls(price)){
            storeCoupons.setSubtract(Float.valueOf(price));
        }
        if(StoresaUtls.blutls(scope)){
            storeCoupons.setStatus(scope);
        }
        if(StoresaUtls.blutls(datests)){
            String datesrsse = datests.substring(0,10);
            storeCoupons.setBegintime(Timestamp.valueOf((datesrsse+" "+"00:00:00")));
        }
        if(StoresaUtls.blutls(dateend)){
            String dateendse = dateend.substring(0,10);
            storeCoupons.setEndtime(Timestamp.valueOf((dateendse+" "+"23:59:59")));
        }
        if (StoresaUtls.blutls(total)){
            storeCoupons.setTotal(total);
            storeCoupons.setFree(total);
        }
        storeCouponsService.updateByPk(id,storeCoupons);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/redpacketinfo/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——删除红包活动信息",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage redpacketinfodelete(@PathVariable String id){
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——删除红包活动信息id为空");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("couponsid",id);
        List<StoreOrdercoupons> storeOrdercouponsList = storeOrdercouponsService.select(queryParamEntity);
        if (!storeOrdercouponsList.isEmpty()){
            log.error("数据错误活动信息id不为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = storeCouponsService.selectByPk(id);
        if (storeCoupons==null){
            log.error("数据错误—商家——删除红包活动信息列表—没有查到信息");
            return ok(false);
        }
        storeCouponsService.deleteByPk(id);
        return ok(false);
    }
    @RequestMapping(value ="/wxapp/redpacketinfo/{shopid}", method = RequestMethod.POST)
    @ApiOperation(value = "商家——添加红包卷活动信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
// {"name":"大红包","price":"10","scope":"2","datests":"2018-12-23T16:00:00.000Z","dateend":"2018-12-25T16:00:00.000Z","total":"1000"}
    public ResponseMessage redpacketinfoinset(@PathVariable String shopid,@RequestBody Map<String, String> copsinset){
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——添加满减活动信息id为空");
            return ok(false);
        }
        String name = copsinset.get("name");
        String price = copsinset.get("price");
        String scope = copsinset.get("scope");
        String datests = copsinset.get("datests");
        String dateend = copsinset.get("dateend");
        String total = copsinset.get("total");
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(price)||!StoresaUtls.blutls(price)||!StoresaUtls.blutls(scope)||!StoresaUtls.blutls(datests)||!StoresaUtls.blutls(dateend)){
            log.error("数据错误—商家——添加满减活动信息为空");
            return ok(false);
        }
        StoreCoupons storeCoupons = new StoreCoupons();
        storeCoupons.setBizid(shopid);
        storeCoupons.setCptype("4");//1--优惠券，2--满减券，3--打折劵，4---红包，
        storeCoupons.setName(name);
        storeCoupons.setScope(scope);
        storeCoupons.setStatus("1");
        storeCoupons.setSubtract(Float.valueOf(price));
        storeCoupons.setTotal(total);
        storeCoupons.setFree(total);
        String[] datestsse =  datests.split("T");
        String[] dateendse =  dateend.split("T");
        storeCoupons.setBegintime(Timestamp.valueOf((datestsse[0]+" "+"00:00:00")));
        storeCoupons.setEndtime(Timestamp.valueOf((dateendse[0]+" "+"00:00:00")));
        storeCouponsService.insert(storeCoupons);
        return ok(true);
    }

    //==评价 or 回复信息=============================================================================================================
    @RequestMapping(value ="/wxapp/commentinfolist/{shopid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——查询评论信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage commentinfolistselect(@PathVariable String shopid,HttpServletRequest request) throws UnsupportedEncodingException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (!StoresaUtls.blutls(page)||!StoresaUtls.blutls(limit)){
            log.error("分页异常");
            System.out.print(page+"/n"+limit);
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("bizid",shopid);
        queryParamEntity.setPageSize(Integer.valueOf(limit));//每页数据最大个数
        queryParamEntity.setPageIndex(Integer.valueOf(page)-1);//当前页数从零开始



        List<StoreComments> storeCommentsList = storeCommentsService.select(queryParamEntity);
        HashMap onhashMap = new HashMap();
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreComments storeComments : storeCommentsList){
            String orderid = storeComments.getOrderid();
            StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
            if (storeOrderinfo == null){
                log.error("数据错误—商家——查询评论信息——没有查询到该订单");
                return ok(false);
            }

            String mementid = storeOrderinfo.getUserid();
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(mementid);
            if (storeMemberinfo == null){
                log.error("数据错误—商家——查询评论信息——没有查询到该订单——该用户");
                return ok(false);
            }
            HashMap hashMap = new HashMap();

            hashMap.put("id",Integer.valueOf(storeComments.getId()));
            hashMap.put("createdtime",storeComments.getCreatedtime());

            Base64.Decoder decoder = Base64.getDecoder();
            String content  =  new String(decoder.decode(storeComments.getContent()), "UTF-8");
            hashMap.put("content", content);//评论内容

            hashMap.put("stars",storeComments.getStars());
            hashMap.put("name",storeMemberinfo.getMembername());
            hashMap.put("sum",Float.valueOf(storeOrderinfo.getIncome()));
            hashMap.put("reply",storeComments.getReply());
            if (StoresaUtls.blutls(storeComments.getReply())){
                hashMap.put("status","2"); // 1--未答复， 2--已答复
                hashMap.put("replytime",storeComments.getCreatedtime());
            }else {
                hashMap.put("status","1"); // 1--未答复， 2--已答复
            }
            hashMap.put("orderno",storeOrderinfo.getOrderno());

            QueryParamEntity queryParamEntity1 = QueryParamEntity.single("commentid",storeComments.getId());
            List<StoreCommentpic> storeCommentpicList = storeCommentpicService.select(queryParamEntity1);
            ArrayList<Map<String,String>> imgarrayList = new ArrayList<Map<String,String>>();
            if (!storeCommentpicList.isEmpty()){
                for (StoreCommentpic storeCommentpic : storeCommentpicList) {
                    HashMap imghashMap = new HashMap();
                    imghashMap.put("img", storeCommentpic.getImg());
                    imghashMap.put("sequ", storeCommentpic.getSequ());
                    imgarrayList.add(imghashMap);
                }
            }
            hashMap.put("images",imgarrayList);
            arrayList.add(hashMap);
        }
        onhashMap.put("comments",arrayList);
        onhashMap.put("total",arrayList.size());
        return ok(onhashMap);
    }
    @RequestMapping(value ="/wxapp/commentinfo", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——回复评论",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage commentinfoupadte(HttpServletRequest request){
        String id = request.getParameter("id");//id，
        String content = request.getParameter("content");//
        if (!StoresaUtls.blutls(id)||!StoresaUtls.blutls(content)){
            log.error("数据错误—商家——回复评论信息——没有查询到该订单");
            return ok(false);
        }
        StoreComments storeComments = storeCommentsService.selectByPk(id);
        if (storeComments==null){
            log.error("数据错误—商家——回复评论信息——没有查询到该评论");
            return ok(false);
        }
        if (StoresaUtls.blutls(storeComments.getReply())){
            log.error("数据错误—商家——回复评论信息——该评论已回复");
            return ok(false);
        }
        storeComments.setReply(content);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String dft = df.format(new Date()); // new Date()为获取当前系统时间
        storeComments.setReplytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeCommentsService.updateByPk(id,storeComments);
        return ok(true);
    }
    //商家——模版相关==================================================================================================================
    @RequestMapping(value ="/wxapp/navtemplateinfo", method = RequestMethod.POST)
    @ApiOperation(value = "商家——模版相关——添加",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage navtemplateinfo(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String, String> lateinfo ){
        String name = lateinfo.get("name");
        String backimg = lateinfo.get("backimg");
        String backcolor = lateinfo.get("backcolor");
        String items = lateinfo.get("items");
        String status = lateinfo.get("status");
        JSONArray jsonArrayitemes = JSONArray.fromObject(items);
        if (!StoresaUtls.blutls(name)||!StoresaUtls.blutls(backcolor)||!StoresaUtls.blutls(backimg)||jsonArrayitemes==null||!StoresaUtls.blutls(status)){
            log.error("数据错误—商家——/wxapp/navtemplateinfo——模版相关");
            return ok(false);
        }
        StoreNavtmpl storeNavtmpl = new StoreNavtmpl();
        storeNavtmpl.setBackimg(backimg);
        storeNavtmpl.setBackcolor(backcolor);
        storeNavtmpl.setName(name);
        storeNavtmpl.setStatus(status);
        String id = storeNavtmplService.insert(storeNavtmpl);
            for (int i = 0; i < jsonArrayitemes.size(); i++) {
                JSONObject jsonObjectitemes = jsonArrayitemes.getJSONObject(i);
                String title = jsonObjectitemes.getString("title");
                String page = jsonObjectitemes.getString("page");
                String iconsel = jsonObjectitemes.getString("iconsel");
                String icon = jsonObjectitemes.getString("icon");
                String colorsel = jsonObjectitemes.getString("colorsel");
                String clolor = jsonObjectitemes.getString("clolor");
                String sequ = jsonObjectitemes.getString("sequ");
                StoreNavitem storeNavitem = new StoreNavitem();
                storeNavitem.setTitle(title);
                storeNavitem.setPage(page);
                storeNavitem.setIconsel(iconsel);
                storeNavitem.setIcon(icon);
                storeNavitem.setClolorsel(colorsel);
                storeNavitem.setColor(clolor);
                storeNavitem.setSequ(sequ);
                storeNavitem.setNavtemplid(id);
                storeNavitemService.insert(storeNavitem);
            }
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/navtemplatelist", method = RequestMethod.GET)
    @ApiOperation(value = "商家——模版相关——获取所有模版信息",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage navtemplatelist(HttpServletRequest request,HttpServletResponse response){
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        List<StoreNavtmpl> storeNavtmplList = storeNavtmplService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storeNavtmplList.isEmpty()){
            for (StoreNavtmpl storeNavtmpl : storeNavtmplList){
                HashMap hashMap = new HashMap();
                hashMap.put("id",storeNavtmpl.getId());
                hashMap.put("name",storeNavtmpl.getName());
                hashMap.put("backimg",storeNavtmpl.getBackimg());
                hashMap.put("backcolor",storeNavtmpl.getBackcolor());
                hashMap.put("status",storeNavtmpl.getStatus());
                QueryParamEntity queryParamEntity1 = QueryParamEntity.single("navtemplid",storeNavtmpl.getId());
                ArrayList<Map<String,String>> arrayListnavitem = new ArrayList<Map<String,String>>();
                List<StoreNavitem> storeNavitemList = storeNavitemService.select(queryParamEntity1);
                if (!storeNavitemList.isEmpty()) {
                    for (StoreNavitem storeNavitem : storeNavitemList) {
                        HashMap hashMapnavitem = new HashMap();
                        hashMapnavitem.put("id", storeNavitem.getId());
                        hashMapnavitem.put("title", storeNavitem.getTitle());
                        hashMapnavitem.put("page", storeNavitem.getPage());
                        hashMapnavitem.put("iconsel", storeNavitem.getIconsel());
                        hashMapnavitem.put("icon", storeNavitem.getIcon());
                        hashMapnavitem.put("colorsel", storeNavitem.getClolorsel());
                        hashMapnavitem.put("color", storeNavitem.getColor());
                        hashMapnavitem.put("sequ", storeNavitem.getSequ());
                        arrayListnavitem.add(hashMapnavitem);
                    }
                }
                hashMap.put("items",arrayListnavitem);
                arrayList.add(hashMap);
            }
        }
        return ok(arrayList);
    }
    @RequestMapping(value ="/wxapp/navtemplatelist/{bizid}", method = RequestMethod.GET)
    @ApiOperation(value = "商家——模版相关——获取可选模版信息",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage navtemplatelistwxapp(@PathVariable String bizid,HttpServletRequest request,HttpServletResponse response) {
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误—商家——/wxapp/navtemplatelist/{bizid}——模版相关");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("bizid",bizid);
        List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storetemplsList.isEmpty()){
            for (Storetempls storetempls : storetemplsList){
                StoreNavtmpl storeNavtmpl = storeNavtmplService.selectByPk(storetempls.getNavtmplid());
                if (storeNavtmpl.getStatus().equals("1")){
                    HashMap hashMap1 = new HashMap();
                    hashMap1.put("id",storeNavtmpl.getId());
                    hashMap1.put("name",storeNavtmpl.getName());
                    arrayList.add(hashMap1);
                }
            }
        }
        hashMap.put("navtempls",arrayList);
        return ok(hashMap);
    }
    @RequestMapping(value ="/wxapp/navtemplateinfo", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——模版相关——修改导航模版",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage navtemplatelistwxapp(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String, String> navitemlist){
        String id = navitemlist.get("id");
        String name = navitemlist.get("name");
        String backimg = navitemlist.get("backimg");
        String backcolor = navitemlist.get("backcolor");
        String items = navitemlist.get("items");
        String status = navitemlist.get("status");
        JSONArray jsonArrayitemes = JSONArray.fromObject(items);
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——/wxapp/navtemplateinfo——修改导航模版");
            return ok(false);
        }
        StoreNavtmpl storeNavtmpl = storeNavtmplService.selectByPk(id);
        if (StoresaUtls.blutls(backimg)){
            storeNavtmpl.setBackimg(backimg);
        }
        if (StoresaUtls.blutls(backcolor)){
            storeNavtmpl.setBackcolor(backcolor);
        }
        if (StoresaUtls.blutls(name)){
            storeNavtmpl.setName(name);
        }
        if (StoresaUtls.blutls(status)){
            storeNavtmpl.setStatus(status);
        }
        storeNavtmplService.updateByPk(id,storeNavtmpl);
        if (jsonArrayitemes.size()!=0) {
            for (int i = 0; i < jsonArrayitemes.size(); i++) {
                JSONObject jsonObjectitemes = jsonArrayitemes.getJSONObject(i);
                String ids = jsonObjectitemes.getString("id");
                String title = jsonObjectitemes.getString("title");
                String page = jsonObjectitemes.getString("page");
                String iconsel = jsonObjectitemes.getString("iconsel");
                String icon = jsonObjectitemes.getString("icon");
                String colorsel = jsonObjectitemes.getString("colorsel");
                String color = jsonObjectitemes.getString("color");
                String sequ = jsonObjectitemes.getString("sequ");
                StoreNavitem storeNavitem = new StoreNavitem();
                if (StoresaUtls.blutls(title)) {
                    storeNavitem.setTitle(title);
                }
                if (StoresaUtls.blutls(page)) {
                    storeNavitem.setPage(page);
                }
                if (StoresaUtls.blutls(iconsel)) {
                    storeNavitem.setIconsel(iconsel);
                }
                if (StoresaUtls.blutls(icon)) {
                    storeNavitem.setIcon(icon);
                }
                if (StoresaUtls.blutls(colorsel)) {
                    storeNavitem.setClolorsel(colorsel);
                }
                if (StoresaUtls.blutls(color)) {
                    storeNavitem.setColor(color);
                }
                if (StoresaUtls.blutls(sequ)) {
                    storeNavitem.setSequ(sequ);
                }
                storeNavitemService.updateByPk(ids,storeNavitem);
            }
        }
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/navtemplateinfo/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "商家——模版相关——删除导航模版",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage navtemplateinfoiddelect(@PathVariable String id,HttpServletRequest request){
        String bizid = request.getParameter("i");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误—商家——/wxapp/navtemplateinfo/{id}——修改导航模版");
            return ok(false);
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("navtemplid",id);
        List<StoreNavitem> storeNavitemList = storeNavitemService.select(queryParamEntity);
        if (!storeNavitemList.isEmpty()) {
            for (StoreNavitem storeNavitem : storeNavitemList) {
                storeNavitemService.deleteByPk(storeNavitem.getId());
            }
        }
        QueryParamEntity queryParamEntity1 = QueryParamEntity.single("navtmplid",id);
        List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity1);
        if (!storetemplsList.isEmpty()) {
            for (Storetempls storetempls : storetemplsList) {
                storetemplsService.deleteByPk(storetempls.getId());
            }
        }
        storeNavtmplService.deleteByPk(id);
        return ok(true);
    }
    @RequestMapping(value ="/wxapp/distnavtemplatelist/{shopid}", method = RequestMethod.PUT)
    @ApiOperation(value = "商家——模版相关——分配导航模版",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ResponseMessage distnavtemplatelistput(@PathVariable String shopid,HttpServletRequest request,HttpServletResponse response,@RequestBody ArrayList<String> navitem){
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",shopid);
        if (navitem.isEmpty()){
            List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity);
            if (!storetemplsList.isEmpty()){
                for (Storetempls storetempls : storetemplsList){
                    storetemplsService.deleteByPk(storetempls.getId());
                }
                return ok(true);
            }
            return ok(false);
        }
        if (!StoresaUtls.blutls(shopid)){
            log.error("数据错误—商家——/wxapp/navtemplateinfo/{id}——修改导航模版");
            return ok(false);
        }

        List<Storetempls> storetemplsList = storetemplsService.select(queryParamEntity);
        if (!storetemplsList.isEmpty()) {
            for (Storetempls storetempls : storetemplsList) {
                storetemplsService.deleteByPk(storetempls.getId());
            }
        }
        for (int i = 0; i < navitem.size(); i++) {
            String id = navitem.get(i);
            Storetempls storetempls = new Storetempls();
            storetempls.setBizid(shopid);
            storetempls.setNavtmplid(id);
            storetempls.setUseornot(0);
            storetemplsService.insert(storetempls);
        }
        return ok(true);
    }
}