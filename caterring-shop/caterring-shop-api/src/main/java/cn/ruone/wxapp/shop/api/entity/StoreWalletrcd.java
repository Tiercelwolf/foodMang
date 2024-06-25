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
@ApiModel(description = "个人充值")
public class StoreWalletrcd extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private java.sql.Timestamp inputtime;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private String orderno;
    @ApiModelProperty(value = "")
    private String deposittype;
    @ApiModelProperty(value = "")
    private String descrp;
    @ApiModelProperty(value = "")
    private float money;
}
