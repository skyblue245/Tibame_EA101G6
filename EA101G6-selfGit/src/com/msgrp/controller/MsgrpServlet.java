package com.msgrp.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.msg.model.*;
import com.msgrp.model.*;

public class MsgrpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("msgrpno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/msgrp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String msgrpno = null;
				try {
					msgrpno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/msgrp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MsgrpService msgrpSvc = new MsgrpService();
				MsgrpVO msgrpVO = msgrpSvc.getOneMsgrp(msgrpno);
				if (msgrpVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/msgrp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("msgrpVO", msgrpVO); // 資料庫取出的msgVO物件,存入req
				String url = "/msgrp/listOneMsgrp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMsg.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/msgrp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	if ("getOne_For_Update".equals(action)) { // 來自listAllMsg.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String msgrpno = new String(req.getParameter("msgrpno"));
				
				/***************************2.開始查詢資料****************************************/
				MsgrpService msgrpSvc = new MsgrpService();
				MsgrpVO msgrpVO = msgrpSvc.getOneMsgrp(msgrpno);
				System.out.println(msgrpVO.getMsgrpno());				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("msgrpVO", msgrpVO);         // 資料庫取出的msgVO物件,存入req
				String url = "/msgrp/update_msgrp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_msg_input.jsp
				successView.forward(req, res);

				//注意get跟set的名稱
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/msgrp/listAllMsgrp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_msg_input.jsp的請求
			System.out.println(4);	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String msgrpno = new String(req.getParameter("msgrpno"));
				System.out.println(msgrpno);	
				String msgno = req.getParameter("msgno");
				String msgnoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (msgno == null || msgno.trim().length() == 0) {
					errorMsgs.add("留言編號請勿空白");
				} else if(!msgno.trim().matches(msgnoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("留言編號只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String detail = req.getParameter("detail").trim();
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("細節請勿空白");
				}	
				
				String mbrno = req.getParameter("mbrno").trim();
				if (mbrno == null || mbrno.trim().length() == 0) {
					errorMsgs.add("文章編號請勿空白");
				}	
				
				Integer status = null;
				try {
					status = new Integer(req.getParameter("status").trim());
				} catch (NumberFormatException e) {
					status = null;
					errorMsgs.add("格式錯誤.");
				}	
					
				System.out.println(1);	
				MsgrpVO msgrpVO = new MsgrpVO();
				msgrpVO.setMsgrpno(msgrpno);
				msgrpVO.setMsgno(msgno);
				msgrpVO.setDetail(detail);
				msgrpVO.setMbrno(mbrno);
				msgrpVO.setStatus(status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("msgrpVO", msgrpVO); // 含有輸入格式錯誤的msgVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/msgrp/update_msgrp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				System.out.println(2);	
				/***************************2.開始修改資料*****************************************/
				MsgrpService msgrpSvc = new MsgrpService();
				msgrpVO = msgrpSvc.updateMsgrp(msgrpno, msgno, detail, mbrno, status);
				System.out.println(3);	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("msgrpVO", msgrpVO); // 資料庫update成功後,正確的的msgVO物件,存入req
				String url = "/back-end/msgrp/listOneMsgrp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMsg.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/msgrp/update_msgrp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			System.out.println(1);
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String msgno = req.getParameter("msgno");
				
			
				
				
				String detail = req.getParameter("rp_detail2").trim();
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("檢舉內容不可空白!");
				}	
				
				String mbrno = req.getParameter("mbrno").trim();
				
				
				Integer status = new Integer(req.getParameter("status").trim());
			
				
				


				System.out.println(2);
				
				
				/***************************2.開始新增資料***************************************/
				MsgrpService msgrpSvc = new MsgrpService();
				msgrpSvc.addMsgrp(msgno, detail, mbrno, status);
//				MsgService msgSvc = new MsgService();
//				MsgVO msgVO = msgSvc.getOneMsg(msgno);
				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("msgVO", msgVO); // 含有輸入格式錯誤的msgVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/msg/listOneMsg.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				req.setAttribute("msgVO", msgVO);
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/msg/listOneMsg.jsp"); // 新增成功後轉交listAllMsg.jsp
//				successView.forward(req, res);				
//				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println(2);
				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/msg/listOneMsg.jsp");
//				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMsg.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String msgrpno = new String(req.getParameter("msgrpno").trim());
				
				/***************************2.開始刪除資料***************************************/
				MsgrpService msgrpSvc = new MsgrpService();
				msgrpSvc.deleteMsgrp(msgrpno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/msgrp/listAllMsgrp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/msgrp/listAllMsgrp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
