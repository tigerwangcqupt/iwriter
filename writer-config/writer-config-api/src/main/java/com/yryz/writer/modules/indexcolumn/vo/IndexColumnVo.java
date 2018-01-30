package com.yryz.writer.modules.indexcolumn.vo;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: IndexColumnVo
 * @Description: IndexColumnVo
 * @author huyangyang
 * @date 2018-01-02 10:04:46
 *
 */
public class IndexColumnVo implements Serializable {

    /**
     * 栏目列表
     */
    private List<IndexItemVo> indexItems;

    /**
     * 消息总数
     */
    private String messageSum;

    public List<IndexItemVo> getIndexItems() {
        return indexItems;
    }

    public void setIndexItems(List<IndexItemVo> indexItems) {
        this.indexItems = indexItems;
    }

    public String getMessageSum() {
        return messageSum;
    }

    public void setMessageSum(String messageSum) {
        this.messageSum = messageSum;
    }
}
