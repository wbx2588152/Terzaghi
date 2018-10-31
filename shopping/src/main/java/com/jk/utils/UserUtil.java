/** 
 * <pre>项目名称:before-consumer 
 * 文件名称:UserUtil.java 
 * 包名:com.jk.utils 
 * 创建日期:2018年9月13日下午7:27:56 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.utils;

import com.jk.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** 
 * <pre>项目名称：before-consumer    
 * 类名称：UserUtil    
 * 类描述：    
 * 创建人：王博潇
 * 创建时间：2018年9月13日 下午7:27:56    
 * 修改人：   
 * 修改时间：2018年9月13日 下午7:27:56    
 * 修改备注：       
 * @version </pre>    
 */
public class UserUtil {

	public static String getUserId(HttpServletRequest request){
		HttpSession session = request.getSession();
		User attribute = (User) session.getAttribute(session.getId());
		if (attribute == null) return "";
		return attribute.getId();
	}
	
	public static User getUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User attribute = (User) session.getAttribute("user");
		return attribute;
	}
	
}
