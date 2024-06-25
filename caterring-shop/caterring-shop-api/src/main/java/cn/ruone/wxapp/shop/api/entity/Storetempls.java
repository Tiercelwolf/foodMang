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
@ApiModel(description = "商家魔板")
public class Storetempls extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String navtmplid;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private int useornot;
}
