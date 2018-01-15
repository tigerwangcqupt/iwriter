package com.yryz.writer.modules.articlearticleclassify.vo;
import java.io.Serializable;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;

/**
 * @ClassName: ArticleArticleClassifyVo
 * @Description: ArticleArticleClassifyVo
 * @author huyangyang
 * @date 2018-01-15 10:34:38
 *
 */
public class ArticleArticleClassifyVo implements Serializable {

    /**  文章标题 */
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
