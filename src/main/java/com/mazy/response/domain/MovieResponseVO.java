package com.mazy.response.domain;

import java.util.List;

/**
 * 根据豆瓣接口返回的body设置的MOV
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
