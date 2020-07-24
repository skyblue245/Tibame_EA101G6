package com.shop.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.model.ShopService;
import com.shop.model.ShopVO;

@WebServlet("/ShopShowImg")
public class ShopShowImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			ServletOutputStream out=res.getOutputStream();	
            res.setContentType("image/jpeg");
                      
         
            try {
            	String shopno=req.getParameter("shopno");
            	ShopService shopSvc = new ShopService();
            	ShopVO shopVo = shopSvc.getOneShop(shopno);
                byte[] buf = shopVo.getShopimg();
                out.write(buf);  
            }catch(Exception e){
            	HttpSession session = req.getSession();
            	ShopVO shopVO = (ShopVO) session.getAttribute("shopVO");
            	byte[] b = shopVO.getShopimg();
            	out.write(b);
            }		
	}

}
