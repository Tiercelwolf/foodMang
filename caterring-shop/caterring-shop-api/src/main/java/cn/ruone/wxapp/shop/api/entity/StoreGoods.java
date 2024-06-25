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
@ApiModel(description = "推荐商品")
public class StoreGoods extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String goodstype;
    @ApiModelProperty(value = "")
    private String miniimage;
    @ApiModelProperty(value = "")
    private float specialprice;
    @ApiModelProperty(value = "")
    private float originprice;
    @ApiModelProperty(value = "")
    private float memberprice;
    @ApiModelProperty(value = "")
    private float siteprice;
    @ApiModelProperty(value = "")
    private int onsale;
    @ApiModelProperty(value = "")
    private String descrip;
    @ApiModelProperty(value = "")
    private String images;
    @ApiModelProperty(value = "")
    private String soldnum;
    @ApiModelProperty(value = "")
    private int hot;
    @ApiModelProperty(value = "")
    private int recommend;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private int newer;
    @ApiModelProperty(value = "")
    private String maxnum;
    @ApiModelProperty(value = "")
    private String minnum;
    @ApiModelProperty(value = "")
    private float packfee;
    @ApiModelProperty(value = "")
    private String stocknum;
    @ApiModelProperty(value = "")
    private String userpoint;
    @ApiModelProperty(value = "")
    private String userpointflag;
    @ApiModelProperty(value = "")
    private int flavorflag;
    @ApiModelProperty(value = "")
    private String rewardpoint;
    @ApiModelProperty(value = "")
    private String flag;
    @ApiModelProperty(value = "")
    private String sequ;
    @ApiModelProperty(value = "")
    private String selltype;
    @ApiModelProperty(value = "")
    private Timestamp createdtime;
    @ApiModelProperty(value = "")
    private Timestamp onoffsaletime;



}
