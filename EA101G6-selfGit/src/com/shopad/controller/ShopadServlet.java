package com.shopad.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.shopad.model.*;

public class ShopadServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************1.接收參數******************/
				String shopno = req.getParameter("shopno");
				if(shopno.isEmpty()) {
					System.out.println(13);
				} 
				String shopadtt = req.getParameter("shopadtt");
				if (shopadtt == null || shopadtt.trim().length() == 0) {
					errorMsgs.add("標題不可空白");
				}
				
				java.sql.Date startt = null;
				try {
					startt = java.sql.Date.valueOf(req.getParameter("startt").trim());
				} catch (IllegalArgumentException e) {
					startt = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				java.sql.Date stopt = null;
				try {
					stopt = java.sql.Date.valueOf(req.getParameter("stopt").trim());
				} catch (IllegalArgumentException e) {
					stopt = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				Integer status = new Integer(req.getParameter("status").trim());
				
				
				ShopadVO shopadVO = new ShopadVO();
				shopadVO.setShopno(shopno);
				shopadVO.setShopadtt(shopadtt);
				shopadVO.setStartt(startt);
				shopadVO.setStopt(stopt);
				shopadVO.setStatus(status);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("shopadVO", shopadVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopad/addShopad.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/********************2.開始新增資料***********************/
				ShopadService shopadSvc = new ShopadService();
				shopadVO = shopadSvc.addShopad(shopno, shopadtt, startt, stopt, status);
				
				/*****************3.新增完成，開始轉交*********************/
				req.setAttribute("shopadVO", shopadVO);
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shopad/listOneShopad.jsp");
//				successView.forward(req, res);
				
				/**************4.其他可能的錯誤處理**********************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shopad/addShopad.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*******************1.接收參數***************************/
				String shopadno = req.getParameter("shopadno");
				
				/*************2.開始刪除資料***************/
				ShopadService shopadSvc = new ShopadService();
				shopadSvc.deleteShopad(shopadno);
				
				/*************3.刪除完成，開始轉交*******************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/shopad/listAllShopadS2.jsp");
				successView.forward(req, res);
				
				/***************其他可能的錯誤處理********************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shopad/listAllShopadS2.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****************1.接收參數*********************/
				String shopadno = req.getParameter("shopadno");
				
				/**************2.開始查詢資料*******************/
				ShopadService shopadSvc = new ShopadService();
				ShopadVO shopadVO = shopadSvc.getOneShopad(shopadno);
				
				/**************3.查詢完成，開始轉交*********************/
				req.setAttribute("shopadVO", shopadVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/shop/listOneShop.jsp");
				successView.forward(req, res);
				
				/**************其他可能的錯誤處理***********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		if ("update0".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****************1.接收參數*********************/
				String shopadno = req.getParameter("shopadno");
				Integer status = new Integer(req.getParameter("status").trim());
				
				
				/**************2.開始更新資料*******************/
				ShopadService shopadSvc = new ShopadService();
				shopadSvc.updateStatus(shopadno, status);
				
				/*****************3.更新成功，開始轉交*********************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/shopad/listAllShopadS0.jsp");
				successView.forward(req, res);
				
				/**************其他可能的錯誤處理***********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		if ("update1".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/****************1.接收參數*********************/
				String shopadno = req.getParameter("shopadno");
				Integer status = new Integer(req.getParameter("status").trim());
				
				
				/**************2.開始更新資料*******************/
				ShopadService shopadSvc = new ShopadService();
				shopadSvc.updateStatus(shopadno, status);
				
				/*****************3.更新成功，開始轉交*********************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/shopad/listAllShopadS1.jsp");
				successView.forward(req, res);
				
				/**************其他可能的錯誤處理***********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
	}

}
