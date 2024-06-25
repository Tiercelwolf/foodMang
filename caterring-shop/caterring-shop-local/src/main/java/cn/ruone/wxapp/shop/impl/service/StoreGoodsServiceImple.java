package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreGoods;
import cn.ruone.wxapp.shop.api.service.StoreGoodsService;
import cn.ruone.wxapp.shop.dao.StoreGoodsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreGoodsService")
public class StoreGoodsServiceImple extends GenericEntityService<StoreGoods,String> implements StoreGoodsService {

    @Autowired
    private StoreGoodsDao storeGoodsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreGoodsDao getDao() {return storeGoodsDao;}
}
