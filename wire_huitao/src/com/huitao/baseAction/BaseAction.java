package com.huitao.baseAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.huitao.sys.commons.Constants4System;
import com.huitao.sys.vo.LoginSystemVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5421662557779942577L;
	public LoginSystemVO getCurrMember() {
		return (LoginSystemVO) getSession().getAttribute(Constants4System.LOGIN_SYSTEM_USER_IN_SESSION);
	}
	@JSON(serialize = false)
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	@JSON(serialize = false)
	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	@JSON(serialize = false)
	protected Map getRequestMap() {
		return ActionContext.getContext().getParameters();
	}

	@JSON(serialize = false)
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	@JSON(serialize = false)
	protected void getPrintWriter(Map<?, ?> mapdata) {
		getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject json = JSONObject.fromObject(mapdata);// 
		String result = json.toString();// 
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
		out.flush();
		out.close();
	}

	@JSON(serialize = false)
	protected void getPrintWriter(List<?> lstData) {
		getResponse().setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(lstData);
		String result = json.toString();// 
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
		out.flush();
		out.close();
	}
	

}
