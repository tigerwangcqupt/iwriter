package com.yryz.writer.modules.trian.dto;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.trian.entity.WriterTrian;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.trian.dto
 * @Desc:
 * @Date: 2018/5/29.
 */
public class WriterTrianDto extends PageList {

    private Long kid;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 擅长文章类型（诗歌,散文,说明文,记叙文）
     */
    private String specialtyArticle;
    /**
     * 培训形式(1.线上培训 2.线下培训)
     */
    private Integer trainMode;
    /**
     * 培训时间(1.周一至周五10:00-11:00;2.周一至周五15:00-16:00;3.周一至周五19:00-20:00;4.周六至周日20:00-21:00)
     */
    private Integer trainTime;
    /**
     * 培训内容
     */
    private String trainContent;
    /**
     * 过稿计划
     */
    private String draftPlan;

    private String startTime;

    private String endTime;

    private String verifyCode;

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialtyArticle() {
        return specialtyArticle;
    }

    public void setSpecialtyArticle(String specialtyArticle) {
        this.specialtyArticle = specialtyArticle;
    }

    public Integer getTrainMode() {
        return trainMode;
    }

    public void setTrainMode(Integer trainMode) {
        this.trainMode = trainMode;
    }

    public Integer getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Integer trainTime) {
        this.trainTime = trainTime;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getDraftPlan() {
        return draftPlan;
    }

    public void setDraftPlan(String draftPlan) {
        this.draftPlan = draftPlan;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
