package com.yryz.writer.modules.bank.vo;
import java.io.Serializable;


/**
 * @ClassName: BankVo
 * @Description: BankVo
 * @author zhongying
 * @date 2017-12-29 15:38:16
 *
 */
public class BankVo implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * kid
     */
    private Long kid;

    /**
     * 提现银行卡
     */
    private  String userName;


    /**
     * 身份证
     */
    private  String userCart;


    /**
     * 银行卡号
     */
    private  String userBankCart;


    /**
     * 交易银行
     */
    private  String userTradeBankCart;


    /**
     * 省
     */
    private  String provice;


    /**
     * 城市
     */
    private  String city;

    /**
     * 省
     */
    private  String proviceName;


    /**
     * 城市
     */
    private  String cityName;


    /**
     * 开发支行
     */
    private  String userAccountOpenBank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCart() {
        return userCart;
    }

    public void setUserCart(String userCart) {
        this.userCart = userCart;
    }

    public String getUserBankCart() {
        return userBankCart;
    }

    public void setUserBankCart(String userBankCart) {
        this.userBankCart = userBankCart;
    }

    public String getUserTradeBankCart() {
        return userTradeBankCart;
    }

    public void setUserTradeBankCart(String userTradeBankCart) {
        this.userTradeBankCart = userTradeBankCart;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserAccountOpenBank() {
        return userAccountOpenBank;
    }

    public void setUserAccountOpenBank(String userAccountOpenBank) {
        this.userAccountOpenBank = userAccountOpenBank;
    }

    public String getProviceName() {
        return proviceName;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
