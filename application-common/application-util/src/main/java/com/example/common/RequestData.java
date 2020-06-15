package com.example.common;



import com.example.util.BaseUtil;

import java.util.Map;

public class RequestData {
	/* 平台 0:android 1:IOS 2:PC */
	private String platform;

	/* 请求人 为空的情况不需要请求人 */
	private String requestUser;

	/** 授权码 */
	private String token;
	/** 签名 */
	private String sign;


	private String eId;

	private String cId;


	private Map<String, Object> requestBody;

	private String requestUrl;

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public Map<String, Object> getRequestBody() {
		return requestBody;
	}

	public String getpk() {
		Map<String, Object> requestBody = this.getRequestBody();
		return BaseUtil.toString(requestBody.get("pk"));
	}

	public String get(String key) {
		Map<String, Object> requestBody = this.getRequestBody();
		return BaseUtil.toString(requestBody.get(key));
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = (Map<String, Object>) requestBody;
	}

	public <T> T getEntity(Class<T> entity) {
		return BaseUtil.populate(this.getRequestBody(), entity);
	}

	public String geteId() {
		return eId;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}


}
