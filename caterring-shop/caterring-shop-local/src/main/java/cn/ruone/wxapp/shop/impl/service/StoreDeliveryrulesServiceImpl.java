package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreDeliveryrules;
import cn.ruone.wxapp.shop.api.service.StoreDeliveryrulesService;
import cn.ruone.wxapp.shop.dao.StoreDeliveryrulesDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreDeliveryrulesService")
public class StoreDeliveryrulesServiceImpl extends GenericEntityService<StoreDeliveryrules,String> implements StoreDeliveryrulesService {
    @Autowired
    private StoreDeliveryrulesDao storeDeliveryrulesDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return  null;}
    @Override
    public StoreDeliveryrulesDao getDao() {return storeDeliveryrulesDao;}
}
