package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreBookmark;
import cn.ruone.wxapp.shop.api.service.StoreBookmarkService;
import cn.ruone.wxapp.shop.dao.StoreBookmarkDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreBookmarkService")
public class StoreBookmarkServiceImpl extends GenericEntityService<StoreBookmark,String> implements StoreBookmarkService {
    @Autowired
    private StoreBookmarkDao storeBookmarkDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return  null;}
    @Override
    public StoreBookmarkDao getDao() {return  storeBookmarkDao;}

}
