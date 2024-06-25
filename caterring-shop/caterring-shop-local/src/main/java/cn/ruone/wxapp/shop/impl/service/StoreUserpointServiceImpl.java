package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreUserpoint;
import cn.ruone.wxapp.shop.api.service.StoreUserpointService;
import cn.ruone.wxapp.shop.dao.StoreUserpointDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("StoreUserpointService")
public class StoreUserpointServiceImpl extends GenericEntityService<StoreUserpoint,String> implements StoreUserpointService {

    @Autowired
    private StoreUserpointDao storeUserpointDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreUserpointDao getDao() {return storeUserpointDao;}
    //积分兑换
    @Override
    public boolean Paypoints(String Flag, String goodid, String userpoint, String name, String userid) {
        StoreUserpoint storeUserpoint = new StoreUserpoint();
        storeUserpoint.setFlag(Flag);
        storeUserpoint.setOrderidorgoodsid(goodid);
        Integer A = Integer.valueOf(userpoint);
        Integer B = -A;
        storeUserpoint.setPoint(String.valueOf(B));
        storeUserpoint.setName(name);
        storeUserpoint.setUserid(userid);
        storeUserpoint.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeUserpointDao.insert(storeUserpoint);
        return true;
    }
    //获取积分
    @Override
    public boolean Getpoints(String point, String orderid, String userid) {
        StoreUserpoint storeUserpoint = new StoreUserpoint();
        storeUserpoint.setName("交易获取");
        storeUserpoint.setPoint(point);
        storeUserpoint.setOrderidorgoodsid(orderid);
        storeUserpoint.setUserid(userid);
        storeUserpoint.setFlag("2");
        storeUserpoint.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeUserpointDao.insert(storeUserpoint);
        return true;
    }

    @Override
    public boolean Consumptionpoints(String point, String orderid, String userid) {
        StoreUserpoint storeUserpoint = new StoreUserpoint();
        storeUserpoint.setOrderidorgoodsid(orderid);
        Integer A = Integer.valueOf(point);
        Integer B = -A;
        storeUserpoint.setPoint(String.valueOf(B));
        storeUserpoint.setName("积分抵现");
        storeUserpoint.setUserid(userid);
        storeUserpoint.setCreatedtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeUserpointDao.insert(storeUserpoint);
        return true;
    }
}
