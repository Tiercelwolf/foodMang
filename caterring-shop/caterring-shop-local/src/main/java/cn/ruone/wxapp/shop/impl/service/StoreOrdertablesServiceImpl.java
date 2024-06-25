package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreOrdertables;
import cn.ruone.wxapp.shop.api.service.StoreOrdertablesService;
import cn.ruone.wxapp.shop.dao.StoreOrdertablesDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreOrdertablesService")
public class StoreOrdertablesServiceImpl extends GenericEntityService<StoreOrdertables,String> implements StoreOrdertablesService {

    @Autowired
    private StoreOrdertablesDao storeOrdertablesDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreOrdertablesDao getDao() {return storeOrdertablesDao;}
}
