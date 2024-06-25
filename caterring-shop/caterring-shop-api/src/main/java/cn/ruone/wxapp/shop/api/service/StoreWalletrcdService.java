package cn.ruone.wxapp.shop.api.service;

import cn.ruone.wxapp.shop.api.entity.StoreWalletrcd;
import org.hswebframework.web.service.CrudService;

public interface StoreWalletrcdService extends CrudService<StoreWalletrcd,String> {
    //钱包退款——添加
    boolean Walletrefund(String userid,String orderno,float money,String Judge);
    //钱包支付——添加
    boolean Walletpayment(String userid,String orderno,float moneyse);
}
