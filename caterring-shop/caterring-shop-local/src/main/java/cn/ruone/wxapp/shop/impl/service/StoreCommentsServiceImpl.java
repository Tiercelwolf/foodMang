package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreComments;
import cn.ruone.wxapp.shop.api.service.StoreCommentsService;
import cn.ruone.wxapp.shop.dao.StoreCommentsDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreCommentsService")
public class StoreCommentsServiceImpl extends GenericEntityService<StoreComments,String> implements StoreCommentsService {

    @Autowired
    private StoreCommentsDao storeCommentsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreCommentsDao getDao() {return storeCommentsDao;}
}
