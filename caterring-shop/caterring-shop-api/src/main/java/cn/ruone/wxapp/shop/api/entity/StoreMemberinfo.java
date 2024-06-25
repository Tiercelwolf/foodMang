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
@ApiModel(description = "客户信息")
public class StoreMemberinfo extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private Timestamp starttime;
    @ApiModelProperty(value = "")
    private String image;
    @ApiModelProperty(value = "")
    private String openid;
    @ApiModelProperty(value = "")
    private String score;
    @ApiModelProperty(value = "")
    private float wallet;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String membername;
    @ApiModelProperty(value = "")
    private String telephone;
    @ApiModelProperty(value = "")
    private String points;
    @ApiModelProperty(value = "")
    private String gender;
    @ApiModelProperty(value = "")
    private String membertype;
    @ApiModelProperty(value = "")
    private int memberadmin;

}
