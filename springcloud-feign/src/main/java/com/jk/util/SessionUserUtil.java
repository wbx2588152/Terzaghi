package com.jk.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jk.model.User;



/**
 * 获取session用户信息工具类
 * */
public class SessionUserUtil {

	/**
	 * 获取用户session用户bean
	 * */
	public static User getUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User attribute = (User) session.getAttribute(session.getId());
		return attribute;
	}
	
	/**
	 * 获取session用户的id
	 * */
	public static String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User attribute = (User) session.getAttribute(session.getId());
			if(attribute != null) {
				return attribute.getId();
			}
		return "";
	}
	
	
}
