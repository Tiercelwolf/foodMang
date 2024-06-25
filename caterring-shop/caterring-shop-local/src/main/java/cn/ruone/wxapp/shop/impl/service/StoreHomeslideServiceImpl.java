package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreHomeslide;
import cn.ruone.wxapp.shop.api.service.StoreHomeslideService;
import cn.ruone.wxapp.shop.dao.StoreHomeslideDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreHomeslideService")
public class StoreHomeslideServiceImpl extends GenericEntityService<StoreHomeslide, String> implements StoreHomeslideService {

    @Autowired
    private StoreHomeslideDao storeHomeslideDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreHomeslideDao getDao() {return storeHomeslideDao;}
}
