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
public class StoreNavtmpl  extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String backimg;
    @ApiModelProperty(value = "")
    private String backcolor;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private String name;
}
