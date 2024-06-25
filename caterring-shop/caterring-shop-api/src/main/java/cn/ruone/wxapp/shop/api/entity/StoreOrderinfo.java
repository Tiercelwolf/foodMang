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
@ApiModel(description = "购物车")
public class StoreOrderinfo extends SimpleGenericEntity<String> {
    @ApiModelProperty(value = "")
    private String id;
    @ApiModelProperty(value = "")
    private String userid;
    @ApiModelProperty(value = "")
    private String status;
    @ApiModelProperty(value = "")
    private Timestamp createdtime;
    @ApiModelProperty(value = "")
    private Timestamp paytime;
    @ApiModelProperty(value = "")
    private Timestamp canceltime;
    @ApiModelProperty(value = "")
    private Timestamp finishtime;
    @ApiModelProperty(value = "")
    private float income;
    @ApiModelProperty(value = "")
    private float packfee;

    @ApiModelProperty(value = "")
    private float deliveryfee;
    @ApiModelProperty(value = "")
    private float price;
    @ApiModelProperty(value = "")
    private String phone;
    @ApiModelProperty(value = "")
    private String address;
    @ApiModelProperty(value = "")
    private String name;
    @ApiModelProperty(value = "")
    private String paytype;
    @ApiModelProperty(value = "")
    private String bizid;
    @ApiModelProperty(value = "")
    private String notes;
    @ApiModelProperty(value = "")
    private Timestamp arrivaltime;
    @ApiModelProperty(value = "")
    private float minexpense;
    @ApiModelProperty(value = "")
    private float subscription;
    @ApiModelProperty(value = "")
    private String odtype;
    @ApiModelProperty(value = "")
    private String getpoint;
    @ApiModelProperty(value = "")
    private String orderno;
    @ApiModelProperty(value = "")
    private String paypoint;
    @ApiModelProperty(value = "")
    private String dishware;
    @ApiModelProperty(value = "")
    private float dishfee;
    @ApiModelProperty(value = "")
    private Timestamp admittime;
    @ApiModelProperty(value = "")
    private String clientsts;
    @ApiModelProperty(value = "")
    private String entersts;
    @ApiModelProperty(value = "")
    private String lastrsn;
    @ApiModelProperty(value = "")
    private String prepay;
    @ApiModelProperty(value = "")
    private String peoplenum;
    @ApiModelProperty(value = "")
    private String tabtype;
    @ApiModelProperty(value = "")
    private String gender;
    @ApiModelProperty(value = "")
    private float cashpay;
    @ApiModelProperty(value = "")
    private float walletpay;
    @ApiModelProperty(value = "")
    private float wxpay;
    
}
