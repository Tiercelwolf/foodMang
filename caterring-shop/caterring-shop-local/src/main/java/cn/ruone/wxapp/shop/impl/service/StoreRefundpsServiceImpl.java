package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreRefundps;
import cn.ruone.wxapp.shop.api.service.StoreRefundpsService;
import cn.ruone.wxapp.shop.dao.StoreRefundpsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("StoreRefundpsService")
public class StoreRefundpsServiceImpl extends GenericEntityService<StoreRefundps,String> implements StoreRefundpsService {
    @Autowired
    private StoreRefundpsDao storeRefundpsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreRefundpsDao getDao() {return storeRefundpsDao;}

    @Override
    public boolean Refuseorders(String orderid, String bizid, String phone, float money, String patype) {
        StoreRefundps storeRefundps = new StoreRefundps();
        storeRefundps.setOrderid(orderid);
        storeRefundps.setNotes("商家拒绝接单");
        storeRefundps.setBizid(bizid);
        storeRefundps.setApplytime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        if (patype.equals("3")){
            storeRefundps.setStatus("3");
            storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
            storeRefundps.setCompletetime(Timestamp.valueOf(StoreDateutil.getStringDate()));//退款完成时间
        }else if (patype.equals("1")){
            storeRefundps.setStatus("2");
            storeRefundps.setConfirmtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        }
        storeRefundps.setTel(phone);
        storeRefundps.setMoney(money);
        storeRefundps.setRefundmoney(money);
        storeRefundps.setOpsnotes("商家拒绝接单，钱自动返回");
        storeRefundpsDao.insert(storeRefundps);
        return true;
    }
}
