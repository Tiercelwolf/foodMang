package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StorePaytypes;
import cn.ruone.wxapp.shop.api.service.StorePaytypesService;
import cn.ruone.wxapp.shop.dao.StorePaytypesDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StorePaytypesService")
public class StorePaytypesServiceImpl  extends GenericEntityService<StorePaytypes,String> implements StorePaytypesService {
    @Autowired
    private StorePaytypesDao storePaytypesDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StorePaytypesDao getDao() {return storePaytypesDao;}
}
