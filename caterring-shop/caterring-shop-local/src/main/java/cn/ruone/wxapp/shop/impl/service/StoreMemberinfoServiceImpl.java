package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreMemberinfo;
import cn.ruone.wxapp.shop.api.service.StoreMemberinfoService;
import cn.ruone.wxapp.shop.dao.StoreMemberinfoDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StoreMemberinfoService")
public class StoreMemberinfoServiceImpl extends GenericEntityService<StoreMemberinfo,String> implements StoreMemberinfoService {

    @Autowired
    private StoreMemberinfoDao storeMemberinfoDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreMemberinfoDao getDao() {return storeMemberinfoDao;}

}
