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
@ApiModel(description = "配送信息")
public class StoreDeliveryrules extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private float deliveryfee;
    @ApiModelProperty(value = "")
    private float minexpense;
    @ApiModelProperty(value = "")
    private float scope;
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String deliverycmp;
    @ApiModelProperty(value = "")
    private String timerange;

}
