package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreCommentpic;
import cn.ruone.wxapp.shop.api.service.StoreCommentpicService;
import cn.ruone.wxapp.shop.dao.StoreCommentpicDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("StoreCommentpicService")
public class StoreCommentpicServiceImpl extends GenericEntityService<StoreCommentpic,String> implements StoreCommentpicService {

    @Autowired
    private StoreCommentpicDao storeCommentpicDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreCommentpicDao getDao() {return storeCommentpicDao;}
}
