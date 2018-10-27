package com.jk;
/**
 * 常量类
 * */
public class ConstantsConf {

	
	/**
	 * 短信平台地址
	 * */
	public static final String SMS_URL="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	/**
	 * 开发者主账号ID
	 * */
	public static final String ACCOUNT_SID="3de53bd1c48948bfb6c59c1d32430ea7";
	/**
	 * 模板ID
	 * */
	public static final String TEMPLATE_ID="516119210";
	
	/**
	 * 身份验证令牌
	 * */
	public static final String AUTH_TOKEN="a5ed31ad89894b8c95ede93e27d1e502";
	
	
	/**
	 * 短信验证码缓存key
	 * */
	public static final String VERIFICATION_CODE = "verificationCode";
	
	/**
	 * 一分钟内不能重复获取标识符
	 * */
	public static final String PHOEO_CODE_FLAG = "phoeoCodeFlag";

	/**
	 * 短信验证码有效时间 单位分钟
	 * */
	public static final Integer PHOEO_CODE_TIME_OUT = 5;
	
	/**
	 * cxf安全认证密码
	 * */
	public static final String AUTH = "admin";

	public static final String WEATHERKEY="weatherkey";
	
}
