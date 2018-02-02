package com.yryz.writer.modules.writer.entity;

import java.util.Date;

import com.yryz.writer.common.entity.GenericEntity;

/**
 * 
  * @ClassName: WriterStatistics
  * @Description: 文章统计表实体类
  * @author liuyanjun
  * @date 2018-02-02 14:15:21
  *
 */
public class WriterStatistics extends GenericEntity{
	

	/**
	 * 写手表kid
	 */	 
    private  Long writerKid;
    

	/**
	 * 文章数
	 */	 
    private  Long articleQty;
    

	/**
	 * 视频数
	 */	 
    private  Long videoQty;
    
    /**
	 * 粉丝数
	 */	 
    private  Long fansQty;
    

	/**
	 * 阅读数
	 */	 
    private  Long visitQty;
    

	/**
	 * 评论数
	 */	 
    private  Long commentQty;
    

	/**
	 * 收藏数
	 */	 
    private  Long collectQty;
    

	/**
	 * 分享数
	 */	 
    private  Long shareQty;
    

	/**
	 * 点赞数
	 */	 
    private  Long praiseQty;
    

	
	public Long getWriterKid() {
		return writerKid;
	}

	public void setWriterKid(Long writerKid) {
		this.writerKid = writerKid;
	}

	public Long getArticleQty() {
		return articleQty;
	}

	public void setArticleQty(Long articleQty) {
		this.articleQty = articleQty;
	}

	public Long getVideoQty() {
		return videoQty;
	}

	public void setVideoQty(Long videoQty) {
		this.videoQty = videoQty;
	}

	public Long getFansQty() {
		return fansQty;
	}

	public void setFansQty(Long fansQty) {
		this.fansQty = fansQty;
	}

	public Long getVisitQty() {
		return this.visitQty;
	}
	
	public void setVisitQty(Long visitQty) {
		this.visitQty = visitQty;
	}
		
	public Long getCommentQty() {
		return this.commentQty;
	}
	
	public void setCommentQty(Long commentQty) {
		this.commentQty = commentQty;
	}
		
	public Long getCollectQty() {
		return this.collectQty;
	}
	
	public void setCollectQty(Long collectQty) {
		this.collectQty = collectQty;
	}
		
	public Long getShareQty() {
		return this.shareQty;
	}
	
	public void setShareQty(Long shareQty) {
		this.shareQty = shareQty;
	}
		
	public Long getPraiseQty() {
		return this.praiseQty;
	}
	
	public void setPraiseQty(Long praiseQty) {
		this.praiseQty = praiseQty;
	}
		
}