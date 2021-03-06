/**
* author:XXX
*/
package com.yryz.writer.modules.article;


import com.yryz.writer.common.entity.GenericEntity;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;

import java.util.Date;
import java.util.List;

public class Article extends GenericEntity {
	private Long tid;

	private Long appId;

	private Long columnId;

	private String coverPlanUrl;

	private String title;

	private String description;

	private String audioUrl;

	private String videoUrl;

	private String videoThumbnailUrl;

	private String recommendTitle;

	private String recommendBref;

	private String recommendBigmap;

	private Integer sort;

	private Integer recommend;

	private Integer shelveFlag;

	private Integer delFlag;

	private Integer viewCount;

	private String content;

	private String contentSource;

	private String imgUrl;

	private Integer sendFlag;

	private String articleType;

	// ext
	private String columnName;
	private String appliName;

	private int maxRecommendSort;

	private int minRecommendSort;

	private Integer systemType;

	private Long writerKid;

	private String coverImgUrl;

	private Integer coverImgType;

	private String contentHtml;

	private Integer contentFlag;

	private String videoDescription;

	private Double draftFee;

	private Integer taskFlag;

	private Integer payFlag;

	private Date startTime;

	private Date endTime;

	private String classifyIds;

	private String classifyName;

	private String labelIds;

	private List<ArticleLabel> labelList;

	private String contentHtml2;

	private Integer waitShelve;

	// 发布文章的作者类型:0 写手,1 悠然一指用户,2 运营人员
	private Integer createUserType;
	// 视频时长
	private Integer videoDuration;
	// 文章标签管理表id
	private Long articleLabelKid;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public String getCoverPlanUrl() {
		return coverPlanUrl;
	}

	public void setCoverPlanUrl(String coverPlanUrl) {
		this.coverPlanUrl = coverPlanUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoThumbnailUrl() {
		return videoThumbnailUrl;
	}

	public void setVideoThumbnailUrl(String videoThumbnailUrl) {
		this.videoThumbnailUrl = videoThumbnailUrl;
	}

	public String getRecommendTitle() {
		return recommendTitle;
	}

	public void setRecommendTitle(String recommendTitle) {
		this.recommendTitle = recommendTitle;
	}

	public String getRecommendBref() {
		return recommendBref;
	}

	public void setRecommendBref(String recommendBref) {
		this.recommendBref = recommendBref;
	}

	public String getRecommendBigmap() {
		return recommendBigmap;
	}

	public void setRecommendBigmap(String recommendBigmap) {
		this.recommendBigmap = recommendBigmap;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getShelveFlag() {
		return shelveFlag;
	}

	public void setShelveFlag(Integer shelveFlag) {
		this.shelveFlag = shelveFlag;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentSource() {
		return contentSource;
	}

	public void setContentSource(String contentSource) {
		this.contentSource = contentSource;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getAppliName() {
		return appliName;
	}

	public void setAppliName(String appliName) {
		this.appliName = appliName;
	}

	public int getMaxRecommendSort() {
		return maxRecommendSort;
	}

	public void setMaxRecommendSort(int maxRecommendSort) {
		this.maxRecommendSort = maxRecommendSort;
	}

	public int getMinRecommendSort() {
		return minRecommendSort;
	}

	public void setMinRecommendSort(int minRecommendSort) {
		this.minRecommendSort = minRecommendSort;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	public Long getWriterKid() {
		return writerKid;
	}

	public void setWriterKid(Long writerKid) {
		this.writerKid = writerKid;
	}

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public Integer getCoverImgType() {
		return coverImgType;
	}

	public void setCoverImgType(Integer coverImgType) {
		this.coverImgType = coverImgType;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public Integer getContentFlag() {
		return contentFlag;
	}

	public void setContentFlag(Integer contentFlag) {
		this.contentFlag = contentFlag;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public Double getDraftFee() {
		return draftFee;
	}

	public void setDraftFee(Double draftFee) {
		this.draftFee = draftFee;
	}

	public Integer getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(Integer taskFlag) {
		this.taskFlag = taskFlag;
	}

	public Integer getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(Integer payFlag) {
		this.payFlag = payFlag;
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

	public String getClassifyIds() {
		return classifyIds;
	}

	public void setClassifyIds(String classifyIds) {
		this.classifyIds = classifyIds;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getLabelIds() {
		return labelIds;
	}

	public void setLabelIds(String labelIds) {
		this.labelIds = labelIds;
	}

	public List<ArticleLabel> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<ArticleLabel> labelList) {
		this.labelList = labelList;
	}

	public String getContentHtml2() {
		return contentHtml2;
	}

	public void setContentHtml2(String contentHtml2) {
		this.contentHtml2 = contentHtml2;
	}

	public Integer getWaitShelve() {
		return waitShelve;
	}

	public void setWaitShelve(Integer waitShelve) {
		this.waitShelve = waitShelve;
	}

	public Integer getCreateUserType() {
		return createUserType;
	}

	public void setCreateUserType(Integer createUserType) {
		this.createUserType = createUserType;
	}

	public Integer getVideoDuration() {
		return videoDuration;
	}

	public void setVideoDuration(Integer videoDuration) {
		this.videoDuration = videoDuration;
	}

	public Long getArticleLabelKid() {
		return articleLabelKid;
	}

	public void setArticleLabelKid(Long articleLabelKid) {
		this.articleLabelKid = articleLabelKid;
	}
}