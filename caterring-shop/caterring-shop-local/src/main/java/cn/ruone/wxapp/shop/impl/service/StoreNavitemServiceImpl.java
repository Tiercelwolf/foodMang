package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreNavitem;
import cn.ruone.wxapp.shop.api.service.StoreNavitemService;
import cn.ruone.wxapp.shop.dao.StoreNavitemDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreNavitemService")
public class StoreNavitemServiceImpl extends GenericEntityService<StoreNavitem,String> implements StoreNavitemService {
    @Autowired
    private StoreNavitemDao storeNavitemDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return  null;}
    @Override
    public StoreNavitemDao getDao() {return storeNavitemDao;}
}