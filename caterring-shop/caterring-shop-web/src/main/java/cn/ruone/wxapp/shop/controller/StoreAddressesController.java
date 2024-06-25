package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.entity.*;
import cn.ruone.wxapp.shop.api.service.*;
import cn.ruone.wxapp.utils.SessionManager;
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
@Api(tags ="地址" ,value = "数据库操作")
public class StoreAddressesController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreAddressesService storeAddressesService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreGoodsService storeGoodsService;
    private StoreOrderinfoService storeOrderinfoService;
    private StoreUserpointService storeUserpointService;
    private StoreOrdergoodsService storeOrdergoodsService;
    private StoreWalletrcdService storeWalletrcdService;
    @Autowired
    public void setStoreWalletrcdService(StoreWalletrcdService storeWalletrcdService){
        this.storeWalletrcdService = storeWalletrcdService;
    }
    @Autowired
    public void setStoreOrdergoodsService(StoreOrdergoodsService storeOrdergoodsService){
        this.storeOrdergoodsService = storeOrdergoodsService;
    }
    @Autowired
    public void setStoreAddressesService(StoreAddressesService storeAddressesService){
        this.storeAddressesService = storeAddressesService;
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
    @RequestMapping(value = "/app/index/MyAddress",method = RequestMethod.GET)
    @ApiOperation(value = "用户地址--查询",notes = "查询")//通过
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })

    public ArrayList<Map<String,String>> addressesselect(HttpServletRequest request, HttpServletResponse response){
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(userid) ) {
            response.setStatus(400);
            log.error("can not get sessionkey or openid from platform!");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid",userid);
        List<StoreAddresses> storeAddressesList = storeAddressesService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        for (StoreAddresses storeAddresses : storeAddressesList){
            HashMap hashMap = new HashMap();
            hashMap.put("id",storeAddresses.getId());//编辑地址的id
            hashMap.put("address",storeAddresses.getAddress());//收货地址
            hashMap.put("area",storeAddresses.getArea());//所在地区
            hashMap.put("user_name",storeAddresses.getName());//收货人姓名
            hashMap.put( "user_id",storeAddresses.getUserid());//用户id
            hashMap.put("tel",storeAddresses.getPhone());//收货人电话
            int Fixed =  storeAddresses.getFixed();//从数据拿出fixed（字段类型为布尔类型）字段值进行判断
            if(Fixed == 1) {
                hashMap.put("is_default", 1);//1已设为默认
            }
            if(Fixed == 0) {
                hashMap.put("is_default", 2); // 2还未设为默认
            }
            String gender = storeAddresses.getGender();
            if (Integer.valueOf(gender) == 1) {
                hashMap.put("sex", 1);//代表男生
            }if (Integer.valueOf(gender) == 0){
                hashMap.put("sex", 2);//代表女生
            }
            hashMap.put( "lat",storeAddresses.getLat());
            hashMap.put( "lng",storeAddresses.getLng());
            arrayList.add(hashMap);
        }
        return arrayList;
    }
    @RequestMapping(value = "/app/index/DelAdd",method = RequestMethod.GET)
    @ApiOperation(value = "收货地址-选择收货地址-删除",notes = "删除")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] detelDelAdd(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        if (!StoresaUtls.blutls(id) ) {
            response.setStatus(400);
            log.error("can not get sessionkey or openid from platform!");
        }
        storeAddressesService.deleteByPk(id);
        String[] arrayList = {"1"};
        return arrayList;
    }
    @RequestMapping(value = "/app/index/AddAddress",method = RequestMethod.GET)
    @ApiOperation(value = "收货地址-选择收货地址-增加",notes = "增加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] insetAddAddress(HttpServletRequest request,HttpServletResponse response){
        String userid = request.getParameter("user_id");
        String address = request.getParameter("address");
        String phone = request.getParameter("tel");
        String area = request.getParameter("area");
        String gender = request.getParameter("sex");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String name = request.getParameter("user_name");
//        String address2 = address.replaceAll(" ", "");//去掉所有空格
//        String phone2 = phone.replaceAll(" ", "");//去掉所有空格
//        String name2 = name.replaceAll(" ", "");//去掉所有空格
        //用户id，订单地址、电话、用户名字 都不能为空
        if (!StoresaUtls.blutls(userid)||!StoresaUtls.blutls(address)||!StoresaUtls.blutls(phone)||!StoresaUtls.blutls(area)
                ||!StoresaUtls.blutls(gender)||!StoresaUtls.blutls(lat)||!StoresaUtls.blutls(lng)||!StoresaUtls.blutls(name)) {
            response.setStatus(400);
            log.error("数据错误");
            return null;
        }
        StoreAddresses storeAddresses = new StoreAddresses();
        storeAddresses.setUserid(userid);
        storeAddresses.setAddress(address);
        String arease = area.replaceAll(",","");
        storeAddresses.setArea(arease);
        storeAddresses.setPhone(phone);
        storeAddresses.setFixed(0);//为0 ，不是默认地址
        storeAddresses.setLat(Float.valueOf(lat));
        storeAddresses.setLng(Float.valueOf(lng));
        storeAddresses.setName(name);

        if ( (Integer.valueOf(gender) == 1)) {
            storeAddresses.setGender("1");//代表男生
        }
        if (Integer.valueOf(gender) == 2){
            storeAddresses.setGender("0");//代表表女生
        }
        storeAddressesService.insert(storeAddresses);
        String[] arrayList = {"1"};
        return arrayList;
    }
    @RequestMapping(value = "/app/index/AddDefault",method = RequestMethod.GET)
    @ApiOperation(value = "收货地址-选择收货地址-设为默认值",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] AddDefault(HttpServletRequest request,HttpServletResponse response){
        String id  =  request.getParameter("id");
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(id)|| !StoresaUtls.blutls(userid)) {
            response.setStatus(400);
            log.error("数据错误_——/app/index/AddDefault");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("userid",userid);
        List<StoreAddresses> storeAddressesList = storeAddressesService.select(queryParamEntity);//根据用户id查询用户的所有地址
        for (StoreAddresses  storeAddresses : storeAddressesList){
            String ids = storeAddresses.getId();
            int fixed = storeAddresses.getFixed();
            if (fixed == 1){//判断是否有已舍为默认值得
                storeAddresses.setFixed(0);
                storeAddressesService.updateByPk(ids,storeAddresses);
            }
        }
        StoreAddresses storeAddresses = new StoreAddresses();
        storeAddresses.setFixed(1);
        storeAddressesService.updateByPk(id,storeAddresses);
        String[] arrayList = {"2"};
        return arrayList;
    }

    @RequestMapping(value = "/app/index/UpdAddress",method = RequestMethod.GET)
    @ApiOperation(value = "用户地址--编辑地址",notes = "修改")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] UpdAddress(HttpServletRequest request,HttpServletResponse response){
        String id  =  request.getParameter("id");
        String address = request.getParameter("address");
        String phone = request.getParameter("tel");
        String area = request.getParameter("area");
        String gender = request.getParameter("sex");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String name = request.getParameter("user_name");
//        String address2 = address.replaceAll(" ", "");//去掉所有空格
//        String phone2 = phone.replaceAll(" ", "");//去掉所有空格
//        String name2 = name.replaceAll(" ", "");//去掉所有空格
        //用户id，订单地址、电话、用户名字 都不能为空,再去完空格后进行判断是否抛出异常
        if (!StoresaUtls.blutls(id)||!StoresaUtls.blutls(address)||!StoresaUtls.blutls(phone)||!StoresaUtls.blutls(area)
                ||!StoresaUtls.blutls(gender)||!StoresaUtls.blutls(lat)||!StoresaUtls.blutls(lng)||!StoresaUtls.blutls(name)) {
            log.error("数据错误");
            return null;
        }
        StoreAddresses storeAddresses = storeAddressesService.selectByPk(id);

        String arease = area.replaceAll(",","");
        storeAddresses.setArea(arease);
        storeAddresses.setAddress(address);
        storeAddresses.setPhone(phone);
        storeAddresses.setLat(Float.valueOf(lat));
        storeAddresses.setLng(Float.valueOf(lng));
        storeAddresses.setName(name);
        if (Integer.valueOf(gender) == 1) {
            storeAddresses.setGender("1");//代表男生
        }
        if (Integer.valueOf(gender) == 2){
            storeAddresses.setGender("0");//代表表女生
        }
        storeAddressesService.updateByPk(id,storeAddresses);
        String[] arrayList = {"1"};
        return arrayList;
    }


    @RequestMapping(value = "/app/index/MyDefaultAddress",method = RequestMethod.GET)
    @ApiOperation(value = "积分商城-商品详情-默认地址",notes = "默认地址")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap MyDefaultAddress(HttpServletRequest request,HttpServletResponse response){
        String userid = request.getParameter("user_id");
        if (!StoresaUtls.blutls(userid)) {
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = new QueryParamEntity().and("userid",userid).and("fixed","1");
        List<StoreAddresses> storeAddressesList = storeAddressesService.select(queryParamEntity);
        HashMap hashMap = new HashMap();
        if (!storeAddressesList.isEmpty() && storeAddressesList.size() ==1) {
            for (StoreAddresses storeAddresses : storeAddressesList) {
                hashMap.put("id", storeAddresses.getId());
                hashMap.put("address", storeAddresses.getAddress());
                hashMap.put("area", storeAddresses.getArea());
                hashMap.put("user_name", storeAddresses.getName());
                hashMap.put("user_id", storeAddresses.getUserid());
                hashMap.put("tel", storeAddresses.getPhone());
//                hashMap.put("is_default", "1");
                if (Integer.valueOf(storeAddresses.getGender()) == 1) {
                    hashMap.put("sex", 1);//代表男生
                }
                if (Integer.valueOf(storeAddresses.getGender()) == 0) {
                    hashMap.put("sex", 2);//代表女生
                }
                hashMap.put("lat", storeAddresses.getLat());
                hashMap.put("lng", storeAddresses.getLng());
            }
        }
        return hashMap;
    }
    @RequestMapping(value = "/app/index/MyAddressInfo",method = RequestMethod.GET)
    @ApiOperation(value = "积分商城-商品详情-编辑MyAddressInfo",notes = "编辑默认地址")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public HashMap MyAddressInfo(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        if (!StoresaUtls.blutls(id)){
            log.error("数据错误/app/index/MyAddressInfo");
            return null;
        }
        StoreAddresses storeAddresses = storeAddressesService.selectByPk(id);
        HashMap hashMap = new HashMap();
        hashMap.put("id",storeAddresses.getId());//编辑地址的id
        hashMap.put("address",storeAddresses.getAddress());//收货地址
        hashMap.put("area",storeAddresses.getArea());//所在地区
        hashMap.put("user_name",storeAddresses.getName());//收货人姓名
        hashMap.put( "user_id",storeAddresses.getUserid());//用户id
        hashMap.put("tel",storeAddresses.getPhone());//收货人电话
//        int Fixed =  storeAddresses.getFixed();//从数据拿出fixed（字段类型为布尔类型）字段值进行判断
        if(storeAddresses.getFixed() == 1) {
            hashMap.put("is_default", 1);//1已设为默认
        }
        if(storeAddresses.getFixed() == 0) {
            hashMap.put("is_default", 2); // 2还未设为默认
        }
        String gender = storeAddresses.getGender();
        if (Integer.valueOf(gender) == 1) {
            hashMap.put("sex", 1);//代表男生
        }if (Integer.valueOf(gender) == 0){
            hashMap.put("sex", 2);//代表女生
        }
        hashMap.put( "lat",storeAddresses.getLat());
        hashMap.put( "lng",storeAddresses.getLng());
        return hashMap;
    }

}
