package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreInfo;
import cn.ruone.wxapp.shop.api.service.StoreInfoService;
import cn.ruone.wxapp.shop.dao.StoreInfoDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreInfoService")
public class StoreInfoServiceImpl extends GenericEntityService<StoreInfo, String> implements StoreInfoService {

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return null;
    }

    @Override
    public StoreInfoDao getDao() {
        return storeInfoDao;
    }

}