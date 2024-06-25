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
@ApiModel(description = "收货地址")
public class StoreAddresses extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private String address;
    @ApiModelProperty(value = "")
    private String phone;
    @ApiModelProperty(value = "")
    private int fixed;
    @ApiModelProperty(value = "")
    private float lat;
    @ApiModelProperty(value = "")
    private float lng;
    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String gender;
    @ApiModelProperty(value = "")
    private String area;

}
