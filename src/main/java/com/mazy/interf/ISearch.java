package com.mazy.interf;

import com.mazy.response.domain.IKongJianVO;
import com.mazy.response.domain.MovieResponseVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 **
 * @Auther: mazhaoyang
 * @Date: 2020/9/22 20:26
 * @Description: 豆瓣查询电影分类接口
 */
public interface ISearch {
    @GET("j/search_tags")
    Call<MovieResponseVO> searchTags(@Query("type") String type, @Query("source") String source);
    
    @GET("monitor")
	 Call<IKongJianVO> getStatus();
}
