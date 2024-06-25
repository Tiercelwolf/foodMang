package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreGoodstype;
import cn.ruone.wxapp.shop.api.service.StoreGoodstypeService;
import cn.ruone.wxapp.shop.dao.StoreGoodstypeDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreGoodstypeService")
public class StoreGoodstypeServiceImpl extends GenericEntityService<StoreGoodstype,String> implements StoreGoodstypeService {

    @Autowired
    private StoreGoodstypeDao storeGoodstypeDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreGoodstypeDao getDao() {return storeGoodstypeDao;}
}
