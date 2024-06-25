package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreGoodsflavor;
import cn.ruone.wxapp.shop.api.service.StoreGoodsflavorService;
import cn.ruone.wxapp.shop.dao.StoreGoodsflavorDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreGoodsflavorService")
public class StoreGoodsflavorServImpl extends GenericEntityService<StoreGoodsflavor,String> implements StoreGoodsflavorService {
    @Autowired

    private StoreGoodsflavorDao storeGoodsflavorDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreGoodsflavorDao getDao() {return null;}
}
