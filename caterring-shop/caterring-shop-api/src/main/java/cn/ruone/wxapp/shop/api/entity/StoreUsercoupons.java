package cn.ruone.wxapp.shop.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hswebframework.web.commons.entity.SimpleGenericEntity;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "个人中心内的优惠卷")
public class StoreUsercoupons extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private String couponid;
    @ApiModelProperty(value = "")
    private String num;
}
