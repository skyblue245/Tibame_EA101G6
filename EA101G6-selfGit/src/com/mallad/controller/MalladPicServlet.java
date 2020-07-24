package com.mallad.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mallad.model.*;


public class MalladPicServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String malladno = req.getParameter("malladno");
			MalladService malladSvc = new MalladService();
			MalladVO malladVO = malladSvc.getOneMallad(malladno);
			byte[] buf = malladVO.getDetail();
			out.write(buf);
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/images/rocket.gif");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
		
		
			
		
	}

}
