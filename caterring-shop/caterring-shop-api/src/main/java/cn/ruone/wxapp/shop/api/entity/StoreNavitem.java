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
@ApiModel(description = "商家模板")
public class StoreNavitem extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String title;
    @ApiModelProperty(value = "")
    private String page;
    @ApiModelProperty(value = "")
    private String iconsel;
    @ApiModelProperty(value = "")
    private String icon;
    @ApiModelProperty(value = "")
    private String clolorsel;
    @ApiModelProperty(value = "")
    private String color;
    @ApiModelProperty(value = "")
    private String navtemplid;
    @ApiModelProperty(value = "")
    private String sequ;
}
