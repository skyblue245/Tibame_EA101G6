package com.mbrpf.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mbrpf.model.*;

public class MbrimgServlet extends HttpServlet {

	

	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

			MbrpfVO mbrpfVO = null;
			String mbrno = req.getParameter("mbrno");
			MbrpfService ser = new MbrpfService();
			mbrpfVO = ser.getOneMbrpf(mbrno);
			out.write(mbrpfVO.getMbrimg());


			
			
			
		}

}
