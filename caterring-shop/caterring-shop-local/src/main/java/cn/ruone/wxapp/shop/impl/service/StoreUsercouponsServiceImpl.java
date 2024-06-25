package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreUsercoupons;
import cn.ruone.wxapp.shop.api.service.StoreUsercouponsService;
import cn.ruone.wxapp.shop.dao.StoreUsercouponsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreUsercouponsService")
public class StoreUsercouponsServiceImpl extends GenericEntityService<StoreUsercoupons,String> implements StoreUsercouponsService {

    @Autowired
    private StoreUsercouponsDao storeUsercouponsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() { return null;}
    @Override
    public StoreUsercouponsDao getDao() {return storeUsercouponsDao;}
}
