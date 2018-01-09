package com.yryz.writer.modules.writer.dto;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.entity.Writer;

/**
 * @ClassName: WriterDto
 * @Description: WriterDto
 * @author liuyanjun
 * @date 2018-01-03 10:39:54
 *
 */
public class WriterDto extends PageList {

    //主键
    private Long kid;
    //关键字
    private String keyWord;
    //主体外码
    private String ownerFcode;
    /**
     * 昵称
     */
    private  String nickName;

    /**
     * 姓名
     */
    private  String userName;

    /**
     * 手机号
     */
    private  String phone;

    /**
     * 生成排序字符串
     */
    private String orderStr;

    /**
     *orderFiled
     */
    private String orderFiled;

    /**
     *orderValue
     */
    private String orderValue;

    private String remark;
    
    

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getOwnerFcode() {
        return ownerFcode;
    }

    public void setOwnerFcode(String ownerFcode) {
        this.ownerFcode = ownerFcode;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
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
