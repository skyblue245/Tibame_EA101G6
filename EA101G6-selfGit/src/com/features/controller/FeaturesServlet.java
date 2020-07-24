package com.features.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.features.model.*;


//@WebServlet("/FeaturesServlet")
public class FeaturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FeaturesServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ftno = req.getParameter("ftno");
				String ftnoReg = "^LF[0-9]{5}$";
				
				if(ftno == null || (ftno.trim()).length() == 0) {
					errorMsgs.add("請輸入功能編號");
				}else if(!ftno.trim().matches(ftnoReg)) {
					errorMsgs.add("功能編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/features/select_page_Features.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				FeaturesService feaSvc = new FeaturesService();
				FeaturesVO feaVO = feaSvc.getOneFeatures(ftno);
				if(feaVO == null) {
					errorMsgs.add("查無此功能");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/features/select_page_Features");
					failView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("feaVO", feaVO);
				String url = "/back-end/features/listOneFeatures.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/	
				
			}catch(Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/features/select_page_Features.jsp");
				failView.forward(req, res);
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
