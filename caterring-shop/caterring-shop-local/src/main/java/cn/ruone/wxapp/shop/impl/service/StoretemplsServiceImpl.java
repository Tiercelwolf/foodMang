package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.Storetempls;
import cn.ruone.wxapp.shop.api.service.StoretemplsService;
import cn.ruone.wxapp.shop.dao.StoretemplsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoretemplsService")
public class StoretemplsServiceImpl extends GenericEntityService<Storetempls,String> implements StoretemplsService {
    @Autowired
    private StoretemplsDao storetemplsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() { return null;}
    @Override
    public StoretemplsDao getDao() {return storetemplsDao;}
}
