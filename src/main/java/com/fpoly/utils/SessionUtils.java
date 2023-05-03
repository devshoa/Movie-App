package com.fpoly.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fpoly.entity.Account;

public class SessionUtils {

	public static Account account = null;
	
	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	
	public static Object get(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		return session.getAttribute(name);
	}
	
	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("acc");
		session.invalidate();
	}
	
	public static boolean isLogin(HttpServletRequest request) {
		return get(request, "acc") != null;
	}
	
	public static String getLoginedUsername(HttpServletRequest request) {
		Object username = get(request, "acc");
		return username == null ? null : username.toString();
	}
	
	
	public static boolean isAdmin() {
		return isAdmin() && account.isRole() == true;
	}
	
}
