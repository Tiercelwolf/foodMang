package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreNavtmpl;
import cn.ruone.wxapp.shop.api.service.StoreNavtmplService;
import cn.ruone.wxapp.shop.dao.StoreNavtmplDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreNavtmplService")
public class StoreNavtmplServiceImpl extends GenericEntityService<StoreNavtmpl,String> implements StoreNavtmplService {
    @Autowired
    private StoreNavtmplDao storeNavtmplDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return  IDGenerator.MD5;}
    @Override
    public StoreNavtmplDao getDao() {return storeNavtmplDao;}
}
