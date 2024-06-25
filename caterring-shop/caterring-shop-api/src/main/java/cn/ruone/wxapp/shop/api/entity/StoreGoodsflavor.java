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
@ApiModel(description = "规格")
public class StoreGoodsflavor extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String goodsid;
    @ApiModelProperty(value = "")
    private String specs;
    @ApiModelProperty(value = "")
    private float price;
    @ApiModelProperty(value = "")
    private String stocknum;
    @ApiModelProperty(value = "")
    private float packfee;
    @ApiModelProperty(value = "")
    private String userpoint;
    @ApiModelProperty(value = "")
    private String onsale;

}
