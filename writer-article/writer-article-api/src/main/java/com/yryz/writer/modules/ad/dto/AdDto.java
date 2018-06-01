package com.yryz.writer.modules.ad.dto;

import com.yryz.component.rpc.dto.PageList;

import java.util.Date;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.ad.dto
 * @Desc:
 * @Date: 2018/6/1.
 */
public class AdDto extends PageList {

    private Long kid;

    private String adTitle;

    private String adUrl;

    private String adPicture;

    private String uriType;

    private Byte adDisplay;

    private String adKeyword;

    private Date startTime;

    private Date endTime;

    private Byte recommend;

    private Integer sort;

    private Byte delFlag;

    private Byte shelveFlag;

    /**
     * 1.待开始，2.进行中，3.已下架
     */
    private Integer status;

    public enum adStatus {
        ToStart(1),//待开始
        Start(2),//进行中
        End(3),//已结束
        Shelve(4);//已下架

        private int status;

        adStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdPicture() {
        return adPicture;
    }

    public void setAdPicture(String adPicture) {
        this.adPicture = adPicture;
    }

    public String getUriType() {
        return uriType;
    }

    public void setUriType(String uriType) {
        this.uriType = uriType;
    }

    public Byte getAdDisplay() {
        return adDisplay;
    }

    public void setAdDisplay(Byte adDisplay) {
        this.adDisplay = adDisplay;
    }

    public String getAdKeyword() {
        return adKeyword;
    }

    public void setAdKeyword(String adKeyword) {
        this.adKeyword = adKeyword;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getRecommend() {
        return recommend;
    }

    public void setRecommend(Byte recommend) {
        this.recommend = recommend;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public Byte getShelveFlag() {
        return shelveFlag;
    }

    public void setShelveFlag(Byte shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
