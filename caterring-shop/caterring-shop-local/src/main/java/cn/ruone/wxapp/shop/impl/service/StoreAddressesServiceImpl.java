package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreAddresses;
import cn.ruone.wxapp.shop.api.service.StoreAddressesService;
import cn.ruone.wxapp.shop.dao.StoreAddressesDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreAddressesService")
public class StoreAddressesServiceImpl extends GenericEntityService<StoreAddresses,String> implements StoreAddressesService {

    @Autowired
    private StoreAddressesDao storeAddressesDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreAddressesDao getDao() {return storeAddressesDao;}
}
