package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreCartgoods;
import cn.ruone.wxapp.shop.api.service.StoreCartgoodsService;
import cn.ruone.wxapp.shop.dao.StoreCartgoodsDao;

import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreCartgoodsService")
public class StoreCartgoodsServiceImpl extends GenericEntityService<StoreCartgoods,String> implements StoreCartgoodsService {
    @Autowired
    private StoreCartgoodsDao storeCartgoodsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreCartgoodsDao getDao() {return storeCartgoodsDao;}
}
