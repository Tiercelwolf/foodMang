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
@ApiModel(description = "商品图片")
public class StoreGoodspics  extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String goodspic1;
    @ApiModelProperty(value = "")
    private String goodsid;
    @ApiModelProperty(value = "")
    private String sequ;
    @ApiModelProperty(value = "")
    private String goodspic2;
    @ApiModelProperty(value = "")
    private String text;
    @ApiModelProperty(value = "")
    private String name;
}
