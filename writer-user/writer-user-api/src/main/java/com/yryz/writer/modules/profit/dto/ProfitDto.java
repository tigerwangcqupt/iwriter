package com.yryz.writer.modules.profit.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ProfitDto
 * @Description: ProfitDto
 * @author zhongying
 * @date 2017-12-29 15:36:15
 *
 */
public class ProfitDto extends PageList {

    //写手id
    private Long createUserId;

    //手机号
    private  String phone;
    //写手姓名
    private  String userName;
    //写手昵称
    private  String nickName;

    //排序字段
    private String orderFiled;
    //排序字段 forward_cnt:转发次数，forward_amount:转发金额，beforward_cnt:被转发次数，
    //integral_total:总收益，account_sum:消费账户余额,      integral_sum:积分账户余额，generalize_num:推广人数
    //排序方式 desc倒序,asc升序
    private String orderValue;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }
}
