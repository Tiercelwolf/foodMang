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
@ApiModel(description = "商家入口-点击接单")
public class StoreTableinfo extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String tabtype;
    @ApiModelProperty(value = "")
    private String roomno;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private float minexpense;
    @ApiModelProperty(value = "")
    private String roomname;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private String tableno;
    @ApiModelProperty(value = "")
    private String personnum;
}
