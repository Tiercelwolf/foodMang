package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreShopenviron;
import cn.ruone.wxapp.shop.api.service.StoreShopenvironService;
import cn.ruone.wxapp.shop.dao.StoreShopenvironDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreShopenvironService")
public class StoreShopenvironServiceImpl extends GenericEntityService<StoreShopenviron,String> implements StoreShopenvironService {

    @Autowired
    private StoreShopenvironDao storeShopenvironDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreShopenvironDao getDao() {return storeShopenvironDao;}
}
