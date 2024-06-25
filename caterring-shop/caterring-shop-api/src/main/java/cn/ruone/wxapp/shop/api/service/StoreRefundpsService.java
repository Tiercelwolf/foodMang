package cn.ruone.wxapp.shop.api.service;

import cn.ruone.wxapp.shop.api.entity.StoreRefundps;
import org.hswebframework.web.service.CrudService;

public interface StoreRefundpsService  extends CrudService<StoreRefundps,String> {
    //商家拒绝接单 --添加记录
    boolean Refuseorders(String orderid,String bizid,String phone,float money,String patype);
}
