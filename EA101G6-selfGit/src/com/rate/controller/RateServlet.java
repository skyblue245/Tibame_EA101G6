package com.rate.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.joinrm.model.*;
import com.rate.model.*;

public class RateServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	if ("insert".equals(action)) { 
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		List<String> updateMsgs = new LinkedList<String>();
		req.setAttribute("updateMsgs", updateMsgs);
		try {
//			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			
//
//			
			String[] rmno= req.getParameterValues("rmno");
			String[] ratingmbrno= req.getParameterValues("ratingmbrno");
			String[] ratedmbrno= req.getParameterValues("ratedmbrno");
			String[] detail= req.getParameterValues("detail");
			String[] s = req.getParameterValues("score");
			Integer[] score = new Integer[s.length];
			String[] shop = req.getParameterValues("shopreport");
			Integer[] shopreport = new Integer[s.length];

			
			for(int i = 0; i < s.length; i++) {
				score[i] = Integer.parseInt(s[i]);
				shopreport[i] = Integer.parseInt(shop[i]);
			}
			for(int i = 0; i < ratedmbrno.length; i++) {
				RateVO rateVO = new RateVO();
				rateVO.setRmno(rmno[i]);
				rateVO.setRatingmbrno(ratingmbrno[i]);
				rateVO.setRatedmbrno(ratedmbrno[i]);
				rateVO.setDetail(detail[i]);
				rateVO.setScore(score[i]);
				
				RateService rateSvc = new RateService();
				rateVO = rateSvc.sendRate(rmno[i],ratingmbrno[i],ratedmbrno[i],detail[i],score[i]);
				System.out.println(shopreport[i] + rmno[i] + ratingmbrno[i]);
				JoinrmService joinrmSvc = new JoinrmService();				
				joinrmSvc.update(1,shopreport[i],rmno[i], ratingmbrno[i]);
				
				
			}
			
			
			
			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//				return;
//			}
			
//			/***************************2.開始新增資料***************************************/
//			
//			
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			updateMsgs.add("評價成功");
			String url = "/front-end/room/myRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);				
//			
//			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			System.out.println(e);
//			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/front-end/create.jsp");
//			failureView.forward(req, res);
		}
		}
	
	if ("delete".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1..接收請求參數 - 輸入格式的錯誤處理***************************************/
			String rateno= req.getParameter("rateno");

			/***************************2.開始新增資料***************************************/
			RateService rateSvc = new RateService();
			rateSvc.deleteRate(rateno);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/								
			String url = "/back-end/room/back-end_rateList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			System.out.println(e);
//			errorMsgs.add("�R����ƥ���:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/emp/listAllEmp.jsp");
//			failureView.forward(req, res);
		}
	}
	
	
	}
}	