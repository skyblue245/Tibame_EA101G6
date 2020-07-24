package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//用濾器的方式，就不用每一支程式都打上一段看看session內是否有account這個Attribute的程式碼
public class LoginFilter implements Filter {
	
	private FilterConfig config;
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override//傳進一個FilterConfig物件，轉成實體變數
	public void init(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;//將ServletRequest強轉型成子介面HttpServletRequest，後續才可以用req的方法
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();//取得session
		Object account = session.getAttribute("account");//從session看是否有登入過，如果有通過登入EmpServlet.java會將account存進session中，354行【session.setAttribute("account", account);】
		if(account == null) {//如果沒有登入過
			session.setAttribute("location", req.getRequestURI());//將現在的位址，以location的名字存進session
			res.sendRedirect(req.getContextPath()+"/front-end/login.jsp");//重導至登入頁面 
			return;
		}else {
			chain.doFilter(request, response);//chain的doFilter是傳ServletRequest的request和ServletResponse的response進去
			//此動作是將控制權交給下一個濾器，如果沒有下一個濾器了，就是將控制權交給請求的目標網頁
		}
	}

}
