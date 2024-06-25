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
@ApiModel(description = "优惠卷信息")

public class StoreCoupons extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private Timestamp begintime;
    @ApiModelProperty(value = "")
    private Timestamp endtime;
    @ApiModelProperty(value = "")
    private float subtract;
    @ApiModelProperty(value = "")
    private  float startline;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String guider;
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String total;
    @ApiModelProperty(value = "")
    private String free;
    @ApiModelProperty(value = "")
    private String cptype;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private String scope;
    @ApiModelProperty(value = "")
    private String discount;
}
