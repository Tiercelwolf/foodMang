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
@ApiModel(description = "图片")
public class StoreCommentpic extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String img;
    @ApiModelProperty(value = "")
    private String commentid;
    @ApiModelProperty(value = "")
    private String sequ;
}
