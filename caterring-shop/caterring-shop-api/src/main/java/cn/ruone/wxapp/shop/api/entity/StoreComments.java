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
@ApiModel(description = "评价")
public class StoreComments extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String content;
    @ApiModelProperty(value = "")
    private String stars;
    @ApiModelProperty(value = "")
    private Timestamp createdtime;
    @ApiModelProperty(value = "")
    private String orderid;
    @ApiModelProperty(value = "")
    private String reply;
    @ApiModelProperty(value = "")
    private Timestamp replytime;
}
