package com.jk.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="t_shop_log")
public class LogBean extends Page   implements Serializable{
	/**
	 * 
	 */
	private String id;
	
	private String className;
	
	private String methodName;
	
	private String userId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date createTime;
	
	private String ip;
	
	private String param;
	
	private String atime1;
	
	private String atime2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getAtime1() {
		return atime1;
	}

	public void setAtime1(String atime1) {
		this.atime1 = atime1;
	}

	public String getAtime2() {
		return atime2;
	}

	public void setAtime2(String atime2) {
		this.atime2 = atime2;
	}
}
