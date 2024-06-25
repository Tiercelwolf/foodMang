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
@ApiModel(description = "购物车商品详情")
public class StoreOrdergoods extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String orderid;

    @ApiModelProperty(value = "")
    private String goodsid;
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String num;
    @ApiModelProperty(value = "")
    private String flavorid;
    @ApiModelProperty(value = "")
    private String status;
}
