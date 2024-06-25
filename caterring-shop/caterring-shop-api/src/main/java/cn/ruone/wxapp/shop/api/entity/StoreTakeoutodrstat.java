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
@ApiModel(description = "购物车")
public class StoreTakeoutodrstat extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String odrnum;
    @ApiModelProperty(value = "")
    private float income;
    @ApiModelProperty(value = "")
    private float wxpaysum;
    @ApiModelProperty(value = "")
    private float recvpay;
    @ApiModelProperty(value = "")
    private float onsitepay;
    @ApiModelProperty(value = "")
    private float walletpay;
    @ApiModelProperty(value = "")
    private String date;

}
