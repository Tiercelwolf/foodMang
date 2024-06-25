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
@ApiModel(description = "广告信息")
public class StoreHomeslide extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "序号标识")
    private String id;

    @ApiModelProperty(value = "")
    private String pic;
    @ApiModelProperty(value = "")
    private String sequence;
    @ApiModelProperty(value = "")
    private Timestamp setuptime;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String name;
}
