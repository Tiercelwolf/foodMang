package cn.ruone.wxapp.shop.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hswebframework.web.commons.entity.SimpleGenericEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "派送信息配置")
public class StoreDelivery extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "商店唯一标识")
    private String bizid;
    @ApiModelProperty(value = "")
    private int deliveryfee;

    @ApiModelProperty(value = "")
    private int minconsume;

    @ApiModelProperty(value = "")
    private int scope;
}
