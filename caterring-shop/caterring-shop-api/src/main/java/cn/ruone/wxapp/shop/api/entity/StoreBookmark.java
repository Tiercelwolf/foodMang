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
@ApiModel(description = "收货地址")
public class StoreBookmark extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private String goodsid;
    @ApiModelProperty(value = "")
    private Timestamp collecttime;
}
