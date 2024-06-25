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
@ApiModel(description = "退款")
public class StoreRefundps extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String orderid;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String oprid;
    @ApiModelProperty(value = "")
    private float money;
    @ApiModelProperty(value = "")
    private Timestamp confirmtime;
    @ApiModelProperty(value = "")
    private String reason;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private String tel;
    @ApiModelProperty(value = "")
    private String notes;
    @ApiModelProperty(value = "")
    private Timestamp applytime;
    @ApiModelProperty(value = "")
    private Timestamp completetime;
    @ApiModelProperty(value = "")
    private float refundmoney;
    @ApiModelProperty(value = "")
    private String opsnotes;
}
