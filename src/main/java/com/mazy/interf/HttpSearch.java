package com.mazy.interf;

import com.mazy.common.HttpBase;
import com.mazy.interf.ISearch;
import com.mazy.response.domain.IKongJianVO;
import com.mazy.response.domain.MovieResponseVO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Part;

import java.io.IOException;

/**
 **
 * @Auther: mazhaoyang
 * @Date: 2020/9/22 20:26
 * @Description:
 */
public class HttpSearch extends HttpBase {

    private ISearch iSearch;

    public HttpSearch(String host) {
        super(host);
        iSearch = super.create(ISearch.class);
    }

    public Response<MovieResponseVO> searchTags(String type, String source) throws IOException {
        Call<MovieResponseVO> call = iSearch.searchTags(type, source);
        return call.execute();
    }
    
    public Response<IKongJianVO> getStues() throws IOException {
        Call<IKongJianVO> call = iSearch.getStatus();
        return call.execute();
    }
    
    
    public Response<Object> UploadMyReport(@Part("username") RequestBody username,@Part("testName") RequestBody testName,@Part("testDescription") RequestBody testDescription,
            @Part MultipartBody.Part file) throws IOException {
        Call<Object> call = iSearch.UploadMyReport(username,testName,testDescription,file);
        return call.execute();
    }

}
