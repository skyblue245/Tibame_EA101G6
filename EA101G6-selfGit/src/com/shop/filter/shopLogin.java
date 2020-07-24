package com.shop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter
public class shopLogin implements Filter {

	private FilterConfig config;
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override//傳進一個FilterConfig物件，轉成實體變數
	public void init(FilterConfig config) {
		this.config = config;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		Object account = session.getAttribute("shopAcount");
		if(account == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/front-end/shop/login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
	
	}


}
