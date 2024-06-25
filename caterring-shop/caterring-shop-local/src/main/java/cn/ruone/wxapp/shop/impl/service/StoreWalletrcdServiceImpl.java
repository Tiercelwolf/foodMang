package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreWalletrcd;
import cn.ruone.wxapp.shop.api.service.StoreWalletrcdService;
import cn.ruone.wxapp.shop.dao.StoreWalletrcdDao;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("StoreWalletrcdService")
public class StoreWalletrcdServiceImpl extends GenericEntityService<StoreWalletrcd,String> implements StoreWalletrcdService {

    @Autowired
    private StoreWalletrcdDao storeWalletrcdDao;
    @Override
    protected IDGenerator<String> getIDGenerator() { return null;}
    @Override
    public StoreWalletrcdDao getDao() {return storeWalletrcdDao;}
    //钱包退款——添加
    @Override
    public boolean Walletrefund(String userid, String orderno, float money ,String Judge) {
        StoreWalletrcd storeWalletrcd = new StoreWalletrcd();
        storeWalletrcd.setUserid(userid);
        storeWalletrcd.setStatus("1");//1.成功2.失败3.交易中
        storeWalletrcd.setDeposittype("5");//支付方式 2充值  3支付成功  4积分兑换  5商家退款
        storeWalletrcd.setOrderno(orderno);
        if (Judge.equals("2")){
            storeWalletrcd.setDescrp("充值");
        }
        if (Judge.equals("4")){
            storeWalletrcd.setDescrp("积分兑换");
        }
        if (Judge.equals("5")){
            storeWalletrcd.setDescrp("商家退款");
        }
        storeWalletrcd.setMoney(money);
        storeWalletrcd.setInputtime(Timestamp.valueOf(StoreDateutil.getStringDate()));//
        storeWalletrcdDao.insert(storeWalletrcd);
        return true;
    }
    //钱包支付——添加
    @Override
    public boolean Walletpayment(String userid, String orderno, float moneyse) {
        StoreWalletrcd storeWalletrcd = new StoreWalletrcd();
        storeWalletrcd.setUserid(userid);
        storeWalletrcd.setStatus("1");//1.成功2.失败3.交易中
        storeWalletrcd.setInputtime(Timestamp.valueOf(StoreDateutil.getStringDate()));
        storeWalletrcd.setDeposittype("3");//支付方式 1 微信支付，2 现金支付， 3 钱包支付 4 积分支付 10.混合支付
        storeWalletrcd.setOrderno(orderno);
        storeWalletrcd.setDescrp("钱包支付");
        storeWalletrcd.setMoney(-moneyse);
        storeWalletrcdDao.insert(storeWalletrcd);
        return true;
    }
}
