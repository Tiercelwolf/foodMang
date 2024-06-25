package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreOrdergoods;
import cn.ruone.wxapp.shop.api.service.StoreOrdergoodsService;
import cn.ruone.wxapp.shop.dao.StoreOrdergoodsDao;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.commons.entity.param.UpdateParamEntity;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StoreOrdergoodsService")
public class StoreOrdergoodsServiceImpl extends GenericEntityService<StoreOrdergoods,String> implements StoreOrdergoodsService {

    @Autowired
    private StoreOrdergoodsDao storeOrdergoodsDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return  null;}
    @Override
    public StoreOrdergoodsDao getDao() {return storeOrdergoodsDao;}
    //商品确认
    @Override
    public boolean confirmdoods(String orderid) {
        QueryParamEntity queryParamEntity = QueryParamEntity.single("orderid",orderid).and("status","2");
        List<StoreOrdergoods> storeOrdergoodsList = storeOrdergoodsDao.query(queryParamEntity);
        if (!storeOrdergoodsList.isEmpty()) {
            for (StoreOrdergoods storeOrdergoods : storeOrdergoodsList) {
                storeOrdergoods.setStatus("1");
                storeOrdergoodsDao.update(UpdateParamEntity.build(storeOrdergoods,"id",storeOrdergoods.getId()));
            }
        }
        return true;
    }

    //添加订单商品
    @Override
    public boolean Addmerchandise(String orderid, String goodid, String num) {
        StoreOrdergoods storeOrdergoods = new StoreOrdergoods();
        storeOrdergoods.setOrderid(orderid);
        storeOrdergoods.setGoodsid(goodid);
        storeOrdergoods.setNum(num);
        storeOrdergoods.setStatus("2");
        storeOrdergoods.setFlavorid("1");//规格
        storeOrdergoodsDao.insert(storeOrdergoods);
        return true;
    }
}
