package com.game.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.game.model.*;

@WebServlet("/GameShowImg")
public class GameShowImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			ServletOutputStream out=res.getOutputStream();
            res.setContentType("image/jpeg");
            
            try {
            	String gmno=req.getParameter("gmno");
            	GameService gameSvc = new GameService();
            	GameVO gameVo = gameSvc.getOneGame(gmno);
                byte[] buf = gameVo.getGmimg();
                out.write(buf);            
            }catch(Exception e){
            	InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
            	byte[] b = new byte[in.available()];
            	in.read(b);
            	out.write(b);
            	in.close();
            }	
            
		
	}

}
