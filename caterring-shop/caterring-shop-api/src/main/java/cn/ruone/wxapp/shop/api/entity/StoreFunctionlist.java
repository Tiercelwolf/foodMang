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
@ApiModel(description = "支付方式")
public class StoreFunctionlist extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private String img;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String id;
}
