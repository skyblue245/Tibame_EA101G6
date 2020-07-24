package com.news.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.news.model.*;

public class NewsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String newstt = req.getParameter("newstt");
				if(newstt == null || newstt.trim().length() == 0) {
					errorMsgs.add("標題不可空白!");
				}
				
				String detail = req.getParameter("detail");
				if(detail == null || detail.trim().length() == 0) {
					errorMsgs.add("內容不可空白");
				}
				
				NewsVO newsVO = new NewsVO();
				newsVO.setNewstt(newstt);
				newsVO.setDetail(detail);
				
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************2.開始新增資料******************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews(newstt, detail);
				
				/*************************3.新增完成，準備轉交******************************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
				successView.forward(req, res);
				
				/*************************其他錯誤處理******************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/addNews.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		if("getOne_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String newsno = req.getParameter("newsno");
				
				/***************************2.開始查詢資料*****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/news/listOneNews.jsp");
				successView.forward(req, res);
				
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String newsno = req.getParameter("newsno");
				
				/***************************2.開始刪除資料*****************************************/
				NewsService newsSvc = new NewsService();
				newsSvc.deleteNews(newsno);
				
				
				/***************************3.刪除完成,準備轉交(Send the Success view)*************/
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
				successView.forward(req, res);
				
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		if ("getOne_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********1.接收參數***********/
				String newsno = req.getParameter("newsno");
				
				/*********2.開始查詢資料***********/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOneNews(newsno);
				
				/*************3.查詢完成，準備轉交************/
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/updateNews.jsp");
				successView.forward(req, res);
				
				/**********其他可能的錯誤處理***********/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/********1.接收參數*********/
				String newsno = req.getParameter("newsno");
				
				String newstt = req.getParameter("newstt");
				if (newstt == null || newstt.trim().length() == 0) {
					errorMsgs.add("標題不可空白");
				}
				
				String detail = req.getParameter("detail");
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("內容不可空白");
				}
				
				NewsVO newsVO = new NewsVO();
				newsVO.setNewsno(newsno);
				newsVO.setNewstt(newstt);
				newsVO.setDetail(detail);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/updateNews.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*********2.開始修改資料***********/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.updateNews(newsno, newstt, detail);
				
				/***********3.修改完成，開始轉交***********/
				req.setAttribute("newsVO", newsVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/news/listAllNews.jsp");
				successView.forward(req, res);
				
				/********其他可能的錯誤處理**********/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/news/updateNews.jsp");
				failureView.forward(req, res);
			}
			
			
			
		} 
			
		
		
		
		
	}
	
	

}
