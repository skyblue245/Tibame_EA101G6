package com.rminfo.controller;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rminfo.model.*;
import com.joinrm.model.*;
import com.shopbk.model.*;


public class RminfoServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
if ("insert".equals(action)) { // 來自addEmp.jsp的請求 
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String shopno = req.getParameter("shopno");
				if (shopno == null || shopno.trim().length() == 0) {
					errorMsgs.add("店家: 請選擇店家");
				}
				
				String naming = req.getParameter("naming");
				String namingReg = "^(.|\\r|\\n){1,10}$";
				if (naming == null || naming.trim().length() == 0) {
					errorMsgs.add("房名請勿空白");
				} else if(!naming.trim().matches(namingReg)) { 
					errorMsgs.add("房名只能在10個字元以內");
	            }
				
				String mbrno= req.getParameter("mbrno");
				
				String game= req.getParameter("game");
				String gameReg = "^(.|\\r|\\n){1,30}$";
				if (game == null || game.trim().length() == 0) {
					errorMsgs.add("請輸入遊玩遊戲");
				} else if(!game.trim().matches(gameReg)) {
					errorMsgs.add("遊玩遊戲: 請控制在30個字元之內");
	            }
				
				String remarks= req.getParameter("remarks");
				String remarksReg = "^(.|\\r|\\n){0,100}$";
				if(!game.trim().matches(remarksReg)) {
					errorMsgs.add("備註: 字數過多");
	            }
								
				java.sql.Timestamp starttime = null;
				try {
					starttime = java.sql.Timestamp.valueOf(req.getParameter("starttime").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇開始時間!");
				}
				
				java.sql.Timestamp endtime = null;
				try {
					endtime = java.sql.Timestamp.valueOf(req.getParameter("endtime").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇結束時間!");
				}
				
				ShopbkService shopbkSvc = new ShopbkService();
				List<ShopbkVO> shopbkList = shopbkSvc.getShopbkByShop(shopno);
				for(int i = 0;i < shopbkList.size(); i++) {
					ShopbkVO shopbk = shopbkList.get(i);
					if(starttime.getTime() >= shopbk.getShoppds().getTime() && starttime.getTime() <= shopbk.getShoppde().getTime()) {
						break;
					}else {
						if(i == (shopbkList.size()-1)) {
							errorMsgs.add("遊玩開始時間不在營業時間內");
						}
					}
				}
				
				for(int i = 0;i < shopbkList.size(); i++) {
					ShopbkVO shopbk = shopbkList.get(i);
					if(endtime.getTime() >= shopbk.getShoppds().getTime() && endtime.getTime() <= shopbk.getShoppde().getTime()) {
						break;
					}else {
						if(i == (shopbkList.size()-1)) {
							errorMsgs.add("遊玩結束時間不在營業時間內");
						}
					}
				}
				
				if((endtime.getTime()-starttime.getTime()) <= 0) {
					errorMsgs.add("遊玩時間:結束時間不可早於或等於開始時間!");
				}
				
				java.sql.Timestamp cutoff= null;
				try {
					cutoff = new Timestamp(endtime.getTime()-(1000*60*60*24*4));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("截止時間錯誤");
				}
			
				Integer uplimit = new Integer(req.getParameter("uplimit").trim());
				try {
					uplimit = new Integer(req.getParameter("uplimit").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇人數上限.");
				}
				
				Integer lowlimit = new Integer(req.getParameter("lowlimit").trim());
				try {
					lowlimit = new Integer(req.getParameter("lowlimit").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請選擇人數下限.");
				}
				Integer restriction = new Integer(req.getParameter("restriction").trim());
				
				RminfoVO rminfoVO = new RminfoVO();
				rminfoVO.setShopno(shopno);
				rminfoVO.setCutoff(cutoff);
				rminfoVO.setNaming(naming);
				rminfoVO.setUplimit(uplimit);
				rminfoVO.setLowlimit(lowlimit);
				rminfoVO.setStarttime(starttime);
				rminfoVO.setEndtime(endtime);
				rminfoVO.setMbrno(mbrno);
				rminfoVO.setGame(game);
				rminfoVO.setRemarks(remarks);
				rminfoVO.setRestriction(restriction);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rminfoVO", rminfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room/create.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RminfoService rminfoSvc = new RminfoService();
				rminfoVO = rminfoSvc.createRm(shopno, cutoff, naming, uplimit, lowlimit, starttime, endtime, mbrno, game, remarks, restriction);
				List<RminfoVO> list = rminfoSvc.getAll();
				RminfoVO rminfo1 = list.get(0);
				String rmno = rminfo1.getRmno();
				JoinrmService joinrmSvc = new JoinrmService();
				joinrmSvc.insertMbr(rmno, mbrno);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				List<String> successMsgs = new LinkedList<String>();
				req.setAttribute("successMsgs", successMsgs);
				successMsgs.add("建立成功!!");
				String url = "/front-end/room/myRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println(e);
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/create.jsp");
//				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自addEmp.jsp的請求 
			
			List<String> errorMsgs = new LinkedList<String>();
			List<String> updateMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("updateMsgs", updateMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String rmno = req.getParameter("rmno");
				
				Integer status = new Integer(req.getParameter("status").trim());
				Integer report = new Integer(req.getParameter("report").trim());
				
				RminfoVO rminfoVO = new RminfoVO();
				rminfoVO.setRmno(rmno);
				rminfoVO.setStatus(status);
				rminfoVO.setReport(report);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rminfoVO", rminfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room/create.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				RminfoService rminfoSvc = new RminfoService();
				rminfoVO = rminfoSvc.update(status,report,rmno);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				if(status == 4) {
					updateMsgs.add("取消成功");
				}else if(status == 3){
					updateMsgs.add("訂位成功");
				}
				String url = "/front-end/room/myRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println(e);
		//		errorMsgs.add(e.getMessage());
		//		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/create.jsp");
		//		failureView.forward(req, res);
			}
		}
		
	}
}