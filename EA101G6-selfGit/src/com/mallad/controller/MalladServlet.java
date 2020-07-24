package com.mallad.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.mallad.model.*;

@MultipartConfig
public class MalladServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*******1.接收參數*********/
				String commno = req.getParameter("commno");
				
				String gmadtt = req.getParameter("gmadtt");
				if (gmadtt == null || (gmadtt.trim()).length() == 0) {
					errorMsgs.add("標題不可空白");
				}
				
				
				Part part = req.getPart("detail");
				InputStream in = part.getInputStream();
				byte[] detail = new byte[in.available()];
				in.read(detail);
				
				
				java.sql.Date startt = null;
				try {
					startt = java.sql.Date.valueOf(req.getParameter("startt").trim());
				} catch (IllegalArgumentException e) {
					startt = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}
				
				java.sql.Date stopt = null;
				try {
					stopt = java.sql.Date.valueOf(req.getParameter("stopt").trim());
				} catch (IllegalArgumentException e) {
					stopt = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期");
				}
				
				
				MalladVO malladVO = new MalladVO();
				malladVO.setCommno(commno);
				malladVO.setGmadtt(gmadtt);
				malladVO.setDetail(detail);
				malladVO.setStartt(startt);
				malladVO.setStopt(stopt);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("malladVO", malladVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mallad/addMallad.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************2.開始新增資料****************/
				MalladService malladSvc = new MalladService();
				malladVO = malladSvc.addMallad(commno, gmadtt, detail, startt, stopt);
				
				/*****************3.新增完成，開始轉交*******************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/mallad/listAllMallad.jsp");
				successView.forward(req, res);
				
				/**************其他可能的錯誤處理****************/
			} catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mallad/addMallad.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*******************1.接收參數***********************/
				String malladno = req.getParameter("malladno");
				
				/****************2.開始刪除資料*******************/
				MalladService malladSvc = new MalladService();
				malladSvc.deleteMallad(malladno);
				
				/*****************3.刪除完成，開始轉交*******************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/mallad/listAllMallad.jsp");
				successView.forward(req, res);
				
				/**************其他可能的錯誤處理***************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" +  e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mallad/listAllMallad.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**************1.接收參數****************/
				String malladno = req.getParameter("malladno");
				
				/************2.開始查詢資料***************/
				MalladService malladSvc = new MalladService();
				MalladVO malladVO = malladSvc.getOneMallad(malladno);
				if (malladVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mallad/listAllMallad.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************3.查詢完成，開始轉交**************/
				req.setAttribute("malladVO", malladVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/mall/mallGetOne.jsp");
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mallad/listAllMallad.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
	}

}
