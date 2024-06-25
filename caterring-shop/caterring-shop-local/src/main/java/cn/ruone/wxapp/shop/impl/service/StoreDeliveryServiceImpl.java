package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreDelivery;
import cn.ruone.wxapp.shop.api.service.StoreDeliveryService;
import cn.ruone.wxapp.shop.dao.StoreDeliveryDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreDeliveryService")
public class StoreDeliveryServiceImpl extends GenericEntityService<StoreDelivery, String> implements StoreDeliveryService {

    @Autowired
    private StoreDeliveryDao storeDeliveryDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public StoreDeliveryDao getDao() {
        return storeDeliveryDao;
    }

}
