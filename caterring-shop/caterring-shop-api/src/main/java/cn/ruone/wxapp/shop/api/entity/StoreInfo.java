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
@ApiModel(description = "商店基本信息")
public class StoreInfo extends SimpleGenericEntity<String> {

    @ApiModelProperty(value = "商店唯一标识")
    private String id;


    @ApiModelProperty(value = "商店名称")
    private String name;

    @ApiModelProperty(value = "商店地址")
    private String address;

    @ApiModelProperty(value = "上午开业时间")
    private String starttime1;

    @ApiModelProperty(value = "上午停业时间")
    private String endtime1;

    @ApiModelProperty(value = "下午开业时间")
    private String starttime2;

    @ApiModelProperty(value = "下午停业时间")
    private String endtime2;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "广告词")
    private String announcement;

    @ApiModelProperty(value = "门店logo")
    private String logo;

    @ApiModelProperty(value = "门店位置")
    private String location;

    @ApiModelProperty(value = "总店编号")
    private String shopid;
    @ApiModelProperty(value = "营业执照")
    private String businesslicense;
    @ApiModelProperty(value = "")
    private String version;
    @ApiModelProperty(value = "")
    private String introduce;
    @ApiModelProperty(value = "")
    private String sttype;
    @ApiModelProperty(value = "")
    private Timestamp estabtime;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private float avgfee;
    @ApiModelProperty(value = "")
    private String autorecv;
    @ApiModelProperty(value = "")
    private String contact;
    @ApiModelProperty(value = "")
    private String wifiact;
    @ApiModelProperty(value = "")
    private String wifipasswd;
    @ApiModelProperty(value = "")
    private float onepointvalue;
    @ApiModelProperty(value = "")
    private String color4;


}
