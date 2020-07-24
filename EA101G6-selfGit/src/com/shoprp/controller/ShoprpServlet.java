package com.shoprp.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.rminfo.model.*;
import com.shoprp.model.*;


public class ShoprpServlet extends HttpServlet{
	
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

		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			

			
			String[] mbrno= req.getParameterValues("mbrno");
			String[] rmno= req.getParameterValues("rmno");
			String[] detail= req.getParameterValues("detail");
			
			String[] a = req.getParameterValues("attend");
			Integer[] attend = new Integer[a.length];
			
			for(int i = 0; i < a.length; i++) {
				attend[i] = Integer.parseInt(a[i]);
			}
			for(int i = 0; i < a.length; i++) {
				ShoprpVO shoprpVO = new ShoprpVO();
				shoprpVO.setMbrno(mbrno[i]);
				shoprpVO.setRmno(rmno[i]);
				shoprpVO.setDetail(detail[i]);
				shoprpVO.setAttend(attend[i]);
				
				ShoprpService shoprpSvc = new ShoprpService();
				shoprpVO = shoprpSvc.report(mbrno[i],rmno[i],detail[i],attend[i]);
			}
			
			RminfoService rminfoService = new RminfoService();
			rminfoService.update(5, 1, rmno[0]);
			
			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//				return;
//			}
			
			/***************************2.開始新增資料***************************************/
			
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/front-end/room/shop_roomList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			System.out.println(e);
//			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room/shop_roomList.jsp");
			failureView.forward(req, res);
		}
		}
	if ("delete".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1..接收請求參數 - 輸入格式的錯誤處理***************************************/
			String rmno= req.getParameter("rmno");
			String mbrno= req.getParameter("mbrno");

			/***************************2.開始新增資料***************************************/
			ShoprpService shoprpSvc = new ShoprpService();
			shoprpSvc.deleteShoprp(rmno,mbrno);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/								
			String url = "/back-end/room/back-end_shoprpList.jsp";
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