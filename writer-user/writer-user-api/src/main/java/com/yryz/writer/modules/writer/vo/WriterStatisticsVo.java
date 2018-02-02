package com.yryz.writer.modules.writer.vo;
import java.io.Serializable;
import com.yryz.writer.modules.writer.entity.WriterStatistics;

/**
 * @ClassName: WriterStatisticsVo
 * @Description: WriterStatisticsVo
 * @author liuyanjun
 * @date 2018-02-02 14:15:21
 *
 */
public class WriterStatisticsVo implements Serializable  {
	
	private Long id;
	
	private Long kid;

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

	public Long getVisitQty() {
		return visitQty;
	}


	public void setVisitQty(Long visitQty) {
		this.visitQty = visitQty;
	}


	public Long getCommentQty() {
		return commentQty;
	}


	public void setCommentQty(Long commentQty) {
		this.commentQty = commentQty;
	}


	public Long getCollectQty() {
		return collectQty;
	}


	public void setCollectQty(Long collectQty) {
		this.collectQty = collectQty;
	}


	public Long getShareQty() {
		return shareQty;
	}


	public void setShareQty(Long shareQty) {
		this.shareQty = shareQty;
	}


	public Long getPraiseQty() {
		return praiseQty;
	}


	public void setPraiseQty(Long praiseQty) {
		this.praiseQty = praiseQty;
	}
    

    
}
