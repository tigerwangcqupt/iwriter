package com.yryz.writer.modules.articlefavorite.vo;
import java.io.Serializable;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;

/**
 * @ClassName: ArticleFavoriteVo
 * @Description: ArticleFavoriteVo
 * @author huyangyang
 * @date 2018-01-02 20:52:42
 *
 */
public class ArticleFavoriteVo implements Serializable {

    private String successNum;

    public void setSuccessNum(String successNum) {
        this.successNum = successNum;
    }

    public String getSuccessNum() {

        return successNum;
    }
}
