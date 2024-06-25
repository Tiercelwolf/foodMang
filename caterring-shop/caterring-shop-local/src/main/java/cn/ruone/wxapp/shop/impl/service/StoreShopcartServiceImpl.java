package cn.ruone.wxapp.shop.impl.service;


import cn.ruone.wxapp.shop.api.entity.StoreShopcart;
import cn.ruone.wxapp.shop.api.service.StoreShopcartService;
import cn.ruone.wxapp.shop.dao.StoreShopcartDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreShopcartService")
public class StoreShopcartServiceImpl extends GenericEntityService<StoreShopcart,String> implements StoreShopcartService {
    @Autowired
    private StoreShopcartDao storeShopcartDao;
    @Override
    protected IDGenerator<String> getIDGenerator(){return null;}
    @Override
    public StoreShopcartDao getDao() {return storeShopcartDao;}
}
