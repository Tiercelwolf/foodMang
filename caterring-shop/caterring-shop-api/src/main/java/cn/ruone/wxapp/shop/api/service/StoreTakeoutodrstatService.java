package cn.ruone.wxapp.shop.api.service;

import cn.ruone.wxapp.shop.api.entity.StoreTakeoutodrstat;
import org.hswebframework.web.service.CrudService;

public interface StoreTakeoutodrstatService extends CrudService<StoreTakeoutodrstat,String> {

    /**
     * 微信收款
     * @param
     */
    boolean Receipts(String bizid,String paytype,float money);

}
