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
@ApiModel(description = "购物车商店用户信息")
public class StoreShopcart extends SimpleGenericEntity<String>{
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private int vacuum;
}
