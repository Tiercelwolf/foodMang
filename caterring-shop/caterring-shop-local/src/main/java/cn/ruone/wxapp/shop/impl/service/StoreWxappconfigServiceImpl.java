package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreWxappconfig;
import cn.ruone.wxapp.shop.api.service.StoreWxappconfigService;
import cn.ruone.wxapp.shop.dao.StoreWxappconfigDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreWxappconfigService")
public class StoreWxappconfigServiceImpl extends GenericEntityService<StoreWxappconfig,String> implements StoreWxappconfigService {
    @Autowired
    private StoreWxappconfigDao storeWxappconfigDao;
    @Override
    protected IDGenerator<String> getIDGenerator() { return null;}
    @Override
    public StoreWxappconfigDao getDao() {return storeWxappconfigDao;}
}
