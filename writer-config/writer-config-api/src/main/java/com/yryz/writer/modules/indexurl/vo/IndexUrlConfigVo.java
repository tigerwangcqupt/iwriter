package com.yryz.writer.modules.indexurl.vo;
import java.io.Serializable;

/**
 * @ClassName: IndexUrlConfigVo
 * @Description: IndexUrlConfigVo
 * @author wangsenyong
 * @date 2018-03-29 15:11:09
 *
 */
public class IndexUrlConfigVo implements Serializable {

    /**
     * 业务主键
     */
    private Long kid;

    /**
     * 0上架，1下架
     */
    private  Integer shelveFlag;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 跳转地址
     */
    private  String configHref;

    /**
     * 配置描述
     */
    private  String configDesc;

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getConfigHref() {
        return configHref;
    }

    public void setConfigHref(String configHref) {
        this.configHref = configHref;
    }
}
