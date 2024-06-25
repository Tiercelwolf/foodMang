package cn.ruone.wxapp.shop.impl.service;

import cn.ruone.wxapp.shop.api.entity.StoreTakeoutodrstat;
import cn.ruone.wxapp.shop.api.service.StoreTakeoutodrstatService;
import cn.ruone.wxapp.shop.dao.StoreTakeoutodrstatDao;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.commons.entity.param.UpdateParamEntity;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("StoreTakeoutodrstatService")
public class StoreTakeoutodrstatServiceImpl extends GenericEntityService<StoreTakeoutodrstat,String> implements StoreTakeoutodrstatService {

    @Autowired
    private StoreTakeoutodrstatDao storeTakeoutodrstatDao;
    @Override
    protected IDGenerator<String> getIDGenerator() {return null;}
    @Override
    public StoreTakeoutodrstatDao getDao() {return storeTakeoutodrstatDao;}

    @Override
    public boolean Receipts(String bizid, String paytype, float money) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = format.format(new Date()); // new Date()为获取当前系统时间
        QueryParamEntity queryParamEntity = QueryParamEntity.single("date",date).and("bizid",bizid);
        List<StoreTakeoutodrstat> storeTakeoutodrstatList = storeTakeoutodrstatDao.query(queryParamEntity);
        if (storeTakeoutodrstatList.isEmpty()) {
            StoreTakeoutodrstat storeTakeoutodrstat = new StoreTakeoutodrstat();
            storeTakeoutodrstat.setBizid(bizid);
            storeTakeoutodrstat.setDate(date);
            storeTakeoutodrstat.setName("销量统计");
            storeTakeoutodrstat.setOdrnum("1");
            if (paytype.equals("1")) {
                storeTakeoutodrstat.setWxpaysum(money);
            }
            if (paytype.equals("3")){
                storeTakeoutodrstat.setWalletpay(money);
            }
            storeTakeoutodrstat.setIncome(money);

            storeTakeoutodrstatDao.insert(storeTakeoutodrstat);
            return true;
        }
        if (!storeTakeoutodrstatList.isEmpty() && storeTakeoutodrstatList.size()==1) {
            for (StoreTakeoutodrstat storeTakeoutodrstat : storeTakeoutodrstatList){
                storeTakeoutodrstat.setOdrnum(String.valueOf(Integer.valueOf(storeTakeoutodrstat.getOdrnum())+1));
                if (paytype.equals("1")) {
                    storeTakeoutodrstat.setWxpaysum(storeTakeoutodrstat.getWxpaysum() + money);
                }
                if (paytype.equals("3")){
                    storeTakeoutodrstat.setWalletpay(storeTakeoutodrstat.getWalletpay() + money);
                }
                storeTakeoutodrstat.setIncome(storeTakeoutodrstat.getIncome()+money);
                storeTakeoutodrstatDao.update(UpdateParamEntity.build(storeTakeoutodrstat,"id",storeTakeoutodrstat.getId()));
            }
            return true;
        }
           return false;
    }


}
