/**
 * 
 */
package com.mazy.response.domain;

/**
 * @ClassName:     IKongJianVO.java
 * @Description:   ikongjian测试接口使用的vo 
 * 
 * @author         mazhaoyang
 * @version        V1.0  
 * @Date           2020年10月26日 下午5:23:35 
 */
public class IKongJianVO {
	int code;
	String msg;
	String result;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
