package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreFunctionlist;
import cn.ruone.wxapp.shop.api.service.StoreFunctionlistService;
import cn.ruone.wxapp.shop.dao.StoreFunctionlistDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreFunctionlistService")
public class StoreFunctionlistServiceImpl extends GenericEntityService<StoreFunctionlist,String> implements StoreFunctionlistService {
    @Autowired

    private StoreFunctionlistDao storeFunctionlistDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreFunctionlistDao getDao() {return storeFunctionlistDao;}
}
