package com.art.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.art.model.*;


public class ArtPicServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String artno = req.getParameter("artno");
			ArtService artSvc = new ArtService();
			ArtVO artVO = artSvc.getOneArt(artno);
			byte[] buf = artVO.getApic();
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
