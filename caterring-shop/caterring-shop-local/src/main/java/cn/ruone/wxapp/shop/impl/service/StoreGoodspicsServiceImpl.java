package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreGoodspics;
import cn.ruone.wxapp.shop.api.service.StoreGoodspicsService;
import cn.ruone.wxapp.shop.dao.StoreGoodspicsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreGoodspicsService")
public class StoreGoodspicsServiceImpl extends GenericEntityService<StoreGoodspics,String> implements StoreGoodspicsService {
    @Autowired
    private StoreGoodspicsDao storeGoodspicsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreGoodspicsDao getDao() {return storeGoodspicsDao;}
}
