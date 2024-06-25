package cn.ruone.wxapp.shop.api.service;

import cn.ruone.wxapp.shop.api.entity.StoreUserpoint;
import org.hswebframework.web.service.CrudService;

public interface StoreUserpointService extends CrudService<StoreUserpoint,String> {
    //积分支出
    boolean Paypoints(String Flag,String goodid,String userpoint,String name,String userid);
    //获取积分
    boolean Getpoints(String point,String orderid,String userid);
    //积分消费
    boolean  Consumptionpoints(String point,String orderid,String userid);

}
