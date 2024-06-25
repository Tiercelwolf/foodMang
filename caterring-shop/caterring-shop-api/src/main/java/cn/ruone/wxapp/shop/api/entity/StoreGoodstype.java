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
@ApiModel(description = "商品分类")
public class StoreGoodstype extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String parentid;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String sequ;
    @ApiModelProperty(value = "")
    private String status;
}
