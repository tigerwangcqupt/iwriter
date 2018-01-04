package com.yryz.writer.modules.indexcolumn.vo;
import java.io.Serializable;

/**
 * @ClassName: IndexColumnVo
 * @Description: IndexColumnVo
 * @author huyangyang
 * @date 2018-01-02 10:04:46
 *
 */
public class IndexItemVo implements Serializable {

    /** 栏目访问路径  */
    private String columnName;

    /** 栏目访问路径  */
    private String columnUrl;

    /** 栏目气泡数  */
    private String tipsNum;

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnUrl(String columnUrl) {
        this.columnUrl = columnUrl;
    }

    public void setTipsNum(String tipsNum) {
        this.tipsNum = tipsNum;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnUrl() {
        return columnUrl;
    }

    public String getTipsNum() {
        return tipsNum;
    }
}
