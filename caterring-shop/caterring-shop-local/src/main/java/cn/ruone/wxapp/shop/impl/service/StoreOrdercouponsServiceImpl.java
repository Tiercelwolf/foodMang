package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreOrdercoupons;
import cn.ruone.wxapp.shop.api.service.StoreOrdercouponsService;
import cn.ruone.wxapp.shop.dao.StoreOrdercouponsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreOrdercouponsService")
public class StoreOrdercouponsServiceImpl extends GenericEntityService<StoreOrdercoupons,String> implements StoreOrdercouponsService {

    @Autowired
    private StoreOrdercouponsDao storeOrdercouponsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return  null;}
    @Override
    public StoreOrdercouponsDao getDao() {return storeOrdercouponsDao;}
    //添加订单使用折扣活动
    @Override
    public boolean AddActivity(String orderid, String actid) {
        StoreOrdercoupons storeOrdercoupons = new StoreOrdercoupons();
        storeOrdercoupons.setCouponsid(actid);
        storeOrdercoupons.setOrderid(orderid);
        storeOrdercouponsDao.insert(storeOrdercoupons);
        return true;
    }
}
