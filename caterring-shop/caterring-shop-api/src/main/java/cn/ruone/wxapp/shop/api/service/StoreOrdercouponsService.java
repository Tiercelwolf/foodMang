package cn.ruone.wxapp.shop.api.service;

import cn.ruone.wxapp.shop.api.entity.StoreOrdercoupons;
import org.hswebframework.web.service.CrudService;

public interface StoreOrdercouponsService extends CrudService<StoreOrdercoupons,String> {
    //添加订单使用折扣活动
    boolean AddActivity(String orderid,String actid);
}
