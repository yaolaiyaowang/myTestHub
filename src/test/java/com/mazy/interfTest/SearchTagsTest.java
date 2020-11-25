package com.mazy.interfTest;

import com.alibaba.fastjson.JSONObject;
import com.mazy.interf.HttpSearch;
import com.mazy.response.domain.IKongJianVO;
import com.mazy.response.domain.MovieResponseVO;
import com.mazy.tools.JsonSchemaUtils;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * @Auther: 马朝阳
 * @Date: 2018/7/5 10:48
 * @Description: 豆瓣首页接口测试
 */
public class SearchTagsTest {
    private static Properties properties;
    private static HttpSearch implSearch;
    private static HttpSearch ikongjianplSearch;
    private static HttpSearch reportSearch;
    private static String SCHEMA_PATH = "parameters/search/schema/SearchTagsMovie.json";
    
    private static String SCHEMA_PATH_IKONGJIAN = "parameters/search/schema/IkongjianResp.json";

    @BeforeSuite
    public void beforeSuite() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("env.properties");
        properties = new Properties();
        properties.load(stream);
        String host = properties.getProperty("douban.host");
        implSearch = new HttpSearch(host);
        stream = this.getClass().getClassLoader().getResourceAsStream("parameters/search/SearchTagsParams.properties");
        properties.load(stream);
        stream = this.getClass().getClassLoader().getResourceAsStream("");
        stream.close();
        
        host = properties.getProperty("ikongjian.host");
        ikongjianplSearch = new HttpSearch(host);
    }

    @Test(description = "电影首页。类别:type=movie source=index")
    public void testcase1() throws IOException {
        String type = properties.getProperty("testcase1.req.type");
        String source = properties.getProperty("testcase1.req.source");

        Response<MovieResponseVO> response = implSearch.searchTags(type, source);
        MovieResponseVO body = response.body();
        Assert.assertNotNull(body, "response.body()");
//        响应返回内容通过schema标准校验
        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
//        将Json化成对象
        Assert.assertNotNull(body.getTags(), "tags");
    }

    @Test(description = "Tv首页。类别:type=tv source=index")
    public void testcase2() throws IOException {
        String type = properties.getProperty("testcase2.req.type");
        String source = properties.getProperty("testcase2.req.source");
        Response<MovieResponseVO> response = implSearch.searchTags(type, source);
        MovieResponseVO body = response.body();
        Assert.assertNotNull(body, "response.body()");
        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH, JSONObject.toJSONString(body));
        Assert.assertNotNull(body.getTags(), "tags");
    }
    
    @Test(description = "测试test7环境是否可用")
    public void testcase3() throws IOException{
    	Response<IKongJianVO> response = ikongjianplSearch.getStues();
    	IKongJianVO IBody = response.body();
    	Assert.assertNotNull(IBody, "response.body()");
        JsonSchemaUtils.assertResponseJsonSchema(SCHEMA_PATH_IKONGJIAN, JSONObject.toJSONString(IBody));
        Assert.assertNotNull(IBody.getMsg(), "msg");
    }
    
    @AfterSuite
    public void aftersuit() throws IOException{
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("env.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String host = properties.getProperty("reportHost");
        reportSearch = new HttpSearch(host);
        
        
    	String username = properties.getProperty("username");
    	String testName = new String(properties.getProperty("testName").getBytes("ISO-8859-1"),"UTF-8");
    	String testDescription = new String(properties.getProperty("testDescription").getBytes("ISO-8859-1"),"UTF-8");
    	System.out.println(testDescription+"加密前");
    	testName = URLEncoder.encode(testName, "utf-8");   		
   		testDescription = URLEncoder.encode(testDescription, "utf-8");
   		System.out.println(testDescription+"加密后");
    	
    	
    	String filepath = System.getProperty("user.dir");
    	File reprotFile = new File(filepath+"/test-output/report.html");
    	
    	RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), reprotFile);
    	RequestBody usernamebody = RequestBody.create(MediaType.parse("multipart/form-data"), username);
    	RequestBody	testNamebody = RequestBody.create(MediaType.parse("multipart/form-data"), testName);
    	RequestBody testDescriptionbody = RequestBody.create(MediaType.parse("multipart/form-data"), testDescription);
    	MultipartBody.Part file = MultipartBody.Part.createFormData("uploadFile", reprotFile.getName(), body);
    	reportSearch.UploadMyReport(usernamebody, testNamebody, testDescriptionbody, file);
    	
    }
}
