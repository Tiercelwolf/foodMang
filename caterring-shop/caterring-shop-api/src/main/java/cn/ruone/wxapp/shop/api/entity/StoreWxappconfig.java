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
@ApiModel(description = "商家微信设置")
public class StoreWxappconfig extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String appid;
    @ApiModelProperty(value = "")
    private String appsecret;
    @ApiModelProperty(value = "")
    private String mchid;
    @ApiModelProperty(value = "")
    private String mchkey;
    @ApiModelProperty(value = "")
    private String mapkey;
}
