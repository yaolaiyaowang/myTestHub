package com.mazy.tools;

import com.alibaba.fastjson.JSONObject;
import com.mazy.response.domain.MovieResponseVO;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @Auther: mazhaoyang
 * @Date: 2020/9/22 20:26
 * @Description:  单独VO对象转换所使用的转换类别，可在HTTPbase中直接进行类型转换，若response设计多个VO，无需调用本类，测试用例中直接进行转换即可
 */
public class RespVoConverterFactory extends Converter.Factory {
    public static RespVoConverterFactory create() {
        return new RespVoConverterFactory();
    }

    private RespVoConverterFactory() {

    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new RespVoConverter();
    }

    public class RespVoConverter implements Converter<ResponseBody, MovieResponseVO> {
        @Override
        public MovieResponseVO convert(ResponseBody value) throws IOException {
            String temp = value.string();
            return JSONObject.parseObject(temp, MovieResponseVO.class);
        }
    }

}
