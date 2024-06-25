package cn.ruone.wxapp.shop.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hswebframework.web.commons.entity.SimpleGenericEntity;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "积分明细")
public class StoreUserpoint extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String flag;
    @ApiModelProperty(value = "")
    private String orderidorgoodsid;
    @ApiModelProperty(value = "")
    private String point;
    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private Timestamp createdtime;
}
