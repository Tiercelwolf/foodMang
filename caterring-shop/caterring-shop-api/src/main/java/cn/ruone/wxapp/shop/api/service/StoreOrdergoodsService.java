package cn.ruone.wxapp.shop.api.service;

import cn.ruone.wxapp.shop.api.entity.StoreOrdergoods;
import cn.ruone.wxapp.shop.api.entity.StoreOrderinfo;
import org.hswebframework.web.service.CrudService;

import java.util.List;

public interface StoreOrdergoodsService extends CrudService<StoreOrdergoods,String> {
    /**
     * 商品确认
     * @param
     */
    boolean confirmdoods(String orderid);
    /**
     * 添加订单商品
     * @param
     */
    boolean Addmerchandise(String orderid,String goodid,String num);


}
