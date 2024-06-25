package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.service.WxminiappService;
import cn.ruone.wxapp.shop.api.entity.StoreOrderinfo;
import cn.ruone.wxapp.shop.api.entity.StoreRefundps;
import cn.ruone.wxapp.shop.api.service.ScheduleTaskImplService;
import cn.ruone.wxapp.shop.api.service.StoreMemberinfoService;
import cn.ruone.wxapp.shop.api.service.StoreOrderinfoService;
import cn.ruone.wxapp.shop.api.service.StoreRefundpsService;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ScheduleTaskImplService")
public class ScheduleTaskImplServiceImpl implements ScheduleTaskImplService {
    private WxminiappService wxminiappService;
    private StoreRefundpsService storeRefundpsService;
    private StoreOrderinfoService storeOrderinfoService;
    @Autowired
    public void setStoreOrderinfoService(StoreOrderinfoService storeOrderinfoService){
        this.storeOrderinfoService = storeOrderinfoService;
    }
    @Autowired
    public void setStoreRefundpsService(StoreRefundpsService storeRefundpsService){
        this.storeRefundpsService = storeRefundpsService;
    }
    @Autowired
    public void setWxminiappService(WxminiappService wxminiappService){
        this.wxminiappService = wxminiappService;

    }
    @Override
    public void refund() {
//        String bizId, String orderNo（）订单编号, int totalFee（总金额）, int refundFee（退款金额）
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("status","2");
        List<StoreRefundps> storeRefundpsList = storeRefundpsService.select(queryParamEntity);
        int num = 0;
        int fail = 0;
        for (StoreRefundps storeRefundps : storeRefundpsList) {
            String refunid = storeRefundps.getId();
            String orderid = storeRefundps.getOrderid();
            StoreOrderinfo storeOrderinfo = storeOrderinfoService.selectByPk(orderid);
            String bizId = storeOrderinfo.getBizid();
            String orderNo = storeOrderinfo.getOrderno();
            int totalFee = (int)(storeRefundps.getMoney()*100);
            int refundFee = (int)(storeRefundps.getRefundmoney()*100);
            boolean refundup = wxminiappService.applyRefund(bizId,orderNo, totalFee, refundFee);
            if (refundup){
                num++;
                if(num==150){
                    try {
                        Thread.sleep(1000);// 睡眠100毫秒
                        num=0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                storeRefundps.setStatus("3");
                storeRefundpsService.updateByPk(refunid,storeRefundps);
                storeOrderinfo.setStatus("11");
                storeOrderinfo.setIncome(0);
                storeOrderinfoService.updateByPk(orderid,storeOrderinfo);
            }else {
                fail++;
                if(fail==6){
                    try {
                        Thread.sleep(1000);// 睡眠100毫秒
                        fail=0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
