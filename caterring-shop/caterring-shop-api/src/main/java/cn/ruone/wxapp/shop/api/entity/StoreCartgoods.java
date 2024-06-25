package cn.ruone.wxapp.shop.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hswebframework.web.commons.entity.SimpleGenericEntity;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "购物车商品数量信息")
public class StoreCartgoods extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String cartid;
    @ApiModelProperty(value = "")
    private String goodsid;
    @ApiModelProperty(value = "")
    private Timestamp addtime;
    @ApiModelProperty(value = "")
    private String num;
    @ApiModelProperty(value = "")
    private String flavorid;


}
