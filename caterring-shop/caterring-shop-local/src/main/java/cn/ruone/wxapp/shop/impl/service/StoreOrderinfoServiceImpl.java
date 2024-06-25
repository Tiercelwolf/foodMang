package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreOrderinfo;
import cn.ruone.wxapp.shop.api.service.StoreOrderinfoService;
import cn.ruone.wxapp.shop.dao.StoreOrderinfoDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreOrderinfoService")
public class StoreOrderinfoServiceImpl extends GenericEntityService<StoreOrderinfo,String> implements StoreOrderinfoService {

    @Autowired
    private StoreOrderinfoDao storeOrderinfoDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreOrderinfoDao getDao() {return storeOrderinfoDao;}

}
