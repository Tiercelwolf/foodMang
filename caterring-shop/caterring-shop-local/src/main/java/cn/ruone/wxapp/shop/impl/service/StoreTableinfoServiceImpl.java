package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreTableinfo;
import cn.ruone.wxapp.shop.api.service.StoreTableinfoService;
import cn.ruone.wxapp.shop.dao.StoreTableinfoDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreTableinfoService")
public class StoreTableinfoServiceImpl extends GenericEntityService<StoreTableinfo,String> implements StoreTableinfoService {

    @Autowired
    private StoreTableinfoDao storeTableinfoDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreTableinfoDao getDao() {return storeTableinfoDao;}
}
