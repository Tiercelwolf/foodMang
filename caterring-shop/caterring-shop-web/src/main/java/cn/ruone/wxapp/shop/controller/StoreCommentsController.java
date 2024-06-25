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
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags ="评价" ,value = "数据库操作")
public class StoreCommentsController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private StoreCommentsService storeCommentsService;
    private StoreCommentpicService storeCommentpicService;
    private StoreMemberinfoService storeMemberinfoService;
    private StoreOrderinfoService storeOrderinfoService;
    private StoreInfoService storeInfoService;
    @Autowired
    public void setStoreInfoService(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    private void setStoreCommentsService(StoreCommentsService storeCommentsService){
        this.storeCommentsService = storeCommentsService;
    }
    @Autowired
    private void setStoreCommentpicService(StoreCommentpicService storeCommentpicService){
        this.storeCommentpicService = storeCommentpicService;
    }
    @Autowired
    private void setStoreMemberinfoService(StoreMemberinfoService storeMemberinfoService){
        this.storeMemberinfoService = storeMemberinfoService;
    }
    public  StoreInfo booleanstoreinfo(String bizid,String shopid){
        StoreInfo storeInfo = storeInfoService.selectByPk(bizid);
        if (!storeInfo.getShopid().equals(shopid)){
            log.error("数据错误—/app/index/system");
            return null;
        }
        return storeInfo;
    }
    @RequestMapping(value = "/app/index/Url",method = RequestMethod.GET)
    @ApiOperation(value = "商家评价",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })

    public String[] Url(HttpServletRequest request, HttpServletResponse response){
        String[] strArray={"http://yktwe7.oss-cn-hangzhou.aliyuncs.com/"};
        return strArray;
    }
    @RequestMapping(value = "/app/index/AssessList",method = RequestMethod.GET)
    @ApiOperation(value = "商家评价",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })

    public HashMap select(HttpServletRequest request, HttpServletResponse response){
        String bizid  = request.getParameter("i");
        if (!StoresaUtls.blutls(bizid)){
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        List<StoreComments> storeCommentsList = storeCommentsService.select(queryParamEntity);
        HashMap commentshashMap = new HashMap();
//        commentshashMap.put("all", 2);
//        commentshashMap.put("ok",2);
//        commentshashMap.put("no",0);
        ArrayList<Map<String,String>> commentsarrayList = new ArrayList<Map<String,String>>();
        for (StoreComments storeComments : storeCommentsList){
            HashMap assesshashMap = new HashMap();
            assesshashMap.put("id",storeComments.getId());
            assesshashMap.put("user_id",storeComments.getUserid());
            assesshashMap.put("store_id",storeComments.getBizid());
            assesshashMap.put( "content",storeComments.getContent());
            String commentid = storeComments.getId();//获取到commentid值
            QueryParamEntity picqueryParamEntity = QueryParamEntity.single("commentid",commentid);
            List<StoreCommentpic> storeCommentspicList = storeCommentpicService.select(picqueryParamEntity);
            ArrayList<String> img = new ArrayList<>();//定义一个接受数组数组
            for(StoreCommentpic storeCommentpic :storeCommentspicList){
                img.add(storeCommentpic.getImg());
            }
            assesshashMap.put("img",img);
            assesshashMap.put("stars",storeComments.getStars());
            assesshashMap.put("uniacid",storeComments.getBizid());
            assesshashMap.put("time",storeComments.getCreatedtime());
//            assesshashMap.put("state","1");
            assesshashMap.put("order_id",storeComments.getOrderid());
            assesshashMap.put( "hf",storeComments.getReply());
            assesshashMap.put( "hf_time",storeComments.getReplytime());
//            String memberinfouserid  = ;//获取用户信息表memberinfo的userid值
            StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(storeComments.getUserid());//根据id查询用户信息
            assesshashMap.put("name",storeMemberinfo.getName());//用户名称
            assesshashMap.put("user_img", storeMemberinfo.getImage());//用户头像
            commentsarrayList.add(assesshashMap);
        }
        commentshashMap.put("assess",commentsarrayList);
        return commentshashMap;
    }
    //评价
    @RequestMapping(value = "/app/index/Assess",method = RequestMethod.GET)
    @ApiOperation(value = "评价",notes = "添加")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public String[] Assess(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String[] success = {"1"};
        String[] fail = {"2"};
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return fail; }
        String userid = request.getParameter("user_id");
        String orderid = request.getParameter("order_id");
        String stars = request.getParameter("stars");
        String content = request.getParameter("content");
        String img = request.getParameter("img");
        if ( !StoresaUtls.blutls(userid) || !StoresaUtls.blutls(orderid) || !StoresaUtls.blutls(stars) || !StoresaUtls.blutls(content)) {
            log.error("数据错误——/app/index/Assess");
            return fail;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid", orderid).and("bizid",bizid).and("userid",userid).and("clientsts","1");
        List<StoreComments> storeCommentsList = storeCommentsService.select(queryParamEntity);
        if (storeCommentsList.isEmpty()) {
            StoreComments storeComments = new StoreComments();
            storeComments.setUserid(userid);
            storeComments.setBizid(bizid);
            storeComments.setOrderid(orderid);
            Base64.Encoder encoder = Base64.getEncoder();
//            String text = "字串文字";
            byte[] textByte = content.getBytes("UTF-8");
            String contentText = encoder.encodeToString(textByte);
            storeComments.setContent(contentText);

            storeComments.setStars(stars);
            storeComments.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
            storeCommentsService.insert(storeComments);
            if (StoresaUtls.blutls(img)) {
                List<StoreComments> storeComment = storeCommentsService.select(queryParamEntity);
                String id = storeComment.get(0).getId();
                String[] strarr = img.split(",");//根据“，”转换成String数组
                for (int i = 0; i < strarr.length; i++) {
                    String imgs = strarr[i];
                    StoreCommentpic storeCommentpic = new StoreCommentpic();
                    storeCommentpic.setCommentid(id);
                    storeCommentpic.setImg(imgs);
                    storeCommentpic.setSequ(String.valueOf(i + 1));
                    storeCommentpicService.insert(storeCommentpic);
                }
            }
            StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
            storeOrderinfo.setStatus("7");
            storeOrderinfoService.updateByPk(orderid, storeOrderinfo);
            return success;
        }
        return fail;
    }
    @RequestMapping(value = "/app/index/pl",method = RequestMethod.GET)
    @ApiOperation(value = "评价",notes = "查询")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400,message = "参数错误",response = java.lang.Void.class)
    })
    public ArrayList<Map<String,String>> pl(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        String bizid = request.getParameter("i");
        String shopid = request.getParameter("s");
        StoreInfo storeInfo = null;
        if ((storeInfo = booleanstoreinfo(bizid,shopid)) == null){ return null; }
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        if (!StoresaUtls.blutls(bizid)||!StoresaUtls.blutls(page)||!StoresaUtls.blutls(pagesize)){
            log.error("数据错误");
            return null;
        }
        QueryParamEntity queryParamEntity = QueryParamEntity.single("bizid",bizid);
        queryParamEntity.setPageIndex(Integer.valueOf(page));//当前页数从零开始
        queryParamEntity.setPageSize(Integer.valueOf(pagesize));//每页数据最大个数
        List<StoreComments> storeCommentsList = storeCommentsService.select(queryParamEntity);
        ArrayList<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
        if (!storeCommentsList.isEmpty()) {
            for (StoreComments storeComments : storeCommentsList) {
                StoreMemberinfo storeMemberinfo = storeMemberinfoService.selectByPk(storeComments.getUserid());
                HashMap hashMap = new HashMap();
                hashMap.put("img", storeMemberinfo.getImage());//用户头像
                hashMap.put("name", storeMemberinfo.getName());//用户名
                hashMap.put("date", storeComments.getCreatedtime());//评论时间

                Base64.Decoder decoder = Base64.getDecoder();
                String content  =  new String(decoder.decode(storeComments.getContent()), "UTF-8");
                hashMap.put("content", content);//评论内容

                hashMap.put("zan", storeComments.getStars());//星
                hashMap.put("id", storeComments.getUserid());//用户id
                ArrayList arrayList1 = new ArrayList();
                QueryParamEntity queryParamEntity1 = QueryParamEntity.single("commentid", storeComments.getId());
                List<StoreCommentpic> storeCommentpicList = storeCommentpicService.select(queryParamEntity1);
                if (!storeCommentpicList.isEmpty()) {
                    for (StoreCommentpic storeCommentpic : storeCommentpicList) {
                        arrayList1.add(storeCommentpic.getImg());
                    }
                }
                hashMap.put("plimages", arrayList1);//用户评论上传的图片
                hashMap.put("replycontent", storeComments.getReply());////商家回复的内容
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }
}