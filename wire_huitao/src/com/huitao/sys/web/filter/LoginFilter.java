package com.huitao.sys.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.huitao.sys.commons.Constants4System;
import com.huitao.sys.vo.LoginSystemVO;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest r = (HttpServletRequest)request;
		LoginSystemVO currUser =(LoginSystemVO) r.getSession().getAttribute(Constants4System.LOGIN_SYSTEM_USER_IN_SESSION);
		if(currUser==null||currUser.getTaoBaoAccessVo()==null ||currUser.getTaoBaoAccessVo().getAccess_token()==null||currUser.getTaoBaoAccessVo().getAccess_token().equals("")){
//			r.getRequestDispatcher("http://container.api.taobao.com/container/transform?appkey="+Constants4System.APPKEY).forward(request, response);
			r.getRequestDispatcher("/error.html").forward(request, response);
		}else{
			filterChain.doFilter(request,response);
			return;
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
