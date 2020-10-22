package com.mazy.douban.domain;

import java.util.List;

/**
 * @Auther: mazhaoyang
 * @Date: 2020/9/22 20:26
 * @Description:
 */
public class MovieResponseVO {

    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
