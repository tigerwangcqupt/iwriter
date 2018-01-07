package com.yryz.writer.modules.writer.dto;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.writer.entity.Writer;

/**
 * @ClassName: WriterDto
 * @Description: WriterDto
 * @author liuyanjun
 * @date 2018-01-03 10:39:54
 *
 */
public class WriterDto extends PageList {

    private String keyWord;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
