package cn.ruone.wxapp.shop.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hswebframework.web.commons.entity.SimpleGenericEntity;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "商家入口-点击接单-完成订单-订单详情")
public class StoreOrdertables extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String orderid;

    @ApiModelProperty(value = "")
    private String tableid;
    @ApiModelProperty(value = "")
    private Timestamp starttime;
    @ApiModelProperty(value = "")
    private Timestamp endtime;

}
