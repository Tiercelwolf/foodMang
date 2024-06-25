package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreRefundpic;
import cn.ruone.wxapp.shop.api.service.StoreRefundpicService;
import cn.ruone.wxapp.shop.dao.StoreRefundpicDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreRefundpicService")
public class StoreRefundpicServiceImpl extends GenericEntityService<StoreRefundpic,String> implements StoreRefundpicService {
    @Autowired
    private StoreRefundpicDao storeRefundpicDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreRefundpicDao getDao() {return storeRefundpicDao;}
}
