package com.tfcord.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbrpf.model.*;
import com.tfcord.model.*;


//@WebServlet("/TfcordServlet")
public class TfcordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TfcordServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("addTfcord".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mbrno = req.getParameter("mbrno");
				HttpSession session = req.getSession();
				session.setAttribute("mbrno", mbrno);
				
				System.out.println("mbrno="+mbrno);
				
				if(mbrno == null || (mbrno.trim()).length() == 0) {
					errorMsgs.add("請先登入帳號");
				}
				
				String tftype = req.getParameter("tftype");
				req.setAttribute("tftype", tftype);
				
				Integer tfstatus = new Integer (req.getParameter("tfstatus"));
				
				Integer price = null;
				if((req.getParameter("price")) != null) {
					price = new Integer (req.getParameter("price"));
					req.setAttribute("price", price);//給buyPoint.jsp(成功畫面)取得買多少點，後續給會員個人點數 作 加總的動作
				}else {
					errorMsgs.add("請選擇儲值的金額");
				}
				
				
				String card1 = req.getParameter("card1");
				String card2 = req.getParameter("card2");
				String card3 = req.getParameter("card3");
				String card4 = req.getParameter("card4");
				if(card1 == null || (card1.trim()).length() != 4 || 
				   card2 == null || (card2.trim()).length() != 4 || 
				   card3 == null || (card3.trim()).length() != 4 || 
				   card4 == null || (card4.trim()).length() != 4) {
					errorMsgs.add("請輸入正確信用卡格式");
				}
				
				String ccv = req.getParameter("ccv");
				if(ccv == null || (ccv.trim()).length() != 3) {
					errorMsgs.add("請輸入正確ccv格式");
				}
				
				TfcordVO tfcordVO = new TfcordVO();
				tfcordVO.setMbrno(mbrno);
				tfcordVO.setPrice(price);
				tfcordVO.setTfstatus(tfstatus);
				tfcordVO.setTftype(tftype);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("tfcordVO", tfcordVO);
					RequestDispatcher failView = req.getRequestDispatcher("/front-end/tfcord/buyPoint.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增點數轉換紀錄*****************************************/
				MbrpfService mbrpfSvc = new MbrpfService();
				MbrpfVO mbrpfVO = mbrpfSvc.getOneMbrpf(mbrno);
				mbrpfVO.setPoints( mbrpfVO.getPoints() + price);
				
				TfcordService TfcordSvc = new TfcordService();
				String tfno = TfcordSvc.addTfcordPoint(mbrno, tftype, price, tfstatus, mbrpfVO);
				session.setAttribute("mbrpfVO", mbrpfVO);
				session.setAttribute("mbrPoint", mbrpfVO.getPoints());//將已經加值過的點數set進sesssion中，讓帳戶管理頁面(listOneMbrtf.jsp)可以抓到最新的點數
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				try{//查看是否有來源網頁(EX:商城或市集購買東西，發現點數不夠，欲購買)
					
					String pointLocation = (String) session.getAttribute("pointLocation");
					if(pointLocation != null) {//如果有來源網頁
						session.removeAttribute("pointLocation");
						res.sendRedirect(pointLocation);//重導至該網頁
						return;
					}
				}catch(Exception e) {
					String url = "/front-end/tfcord/listOneMbrtf.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				//如果沒有來源網頁，會回到會員的所有購買點數頁面
				String url = "/front-end/tfcord/listOneMbrtf.jsp";
				res.sendRedirect(req.getContextPath() + url);
				return;
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("購買點數失敗");
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/tfcord/buyPoint.jsp");
				failView.forward(req, res);
			}	
		}
		
		if("addTfMoney".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("erroMsgs", errorMsgs);
			String url = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mbrno = req.getParameter("mbrno");
				HttpSession session = req.getSession();
				session.setAttribute("mbrno", mbrno);
				
				//System.out.println("mbrno="+mbrno);
				
				if(mbrno == null || (mbrno.trim()).length() == 0) {
					errorMsgs.add("請先登入帳號");
				}
				
				String tftype = req.getParameter("tftype");
				req.setAttribute("tftype", tftype);
				
				Integer tfstatus = new Integer (req.getParameter("tfstatus"));
				
				Integer price = new Integer (req.getParameter("price"));
				if(price == null || price == 0) {
					errorMsgs.add("請選擇儲值的金額");
				}
				req.setAttribute("price", price);//給listOneMbrtf.jsp(成功畫面)新增一條取得換多少點的資料，後續給會員個人點數 作 加總的動作
				
				TfcordVO tfcordVO = new TfcordVO();
				tfcordVO.setMbrno(mbrno);
				tfcordVO.setPrice(price);
				tfcordVO.setTfstatus(tfstatus);
				tfcordVO.setTftype(tftype);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("tfcordVO", tfcordVO);
					RequestDispatcher failView = req.getRequestDispatcher("/front-end/tfcord/tfMoney.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增點數轉換紀錄*****************************************/
				MbrpfService mbrpfSvc = new MbrpfService();
				MbrpfVO mbrpfVO = mbrpfSvc.getOneMbrpf(mbrno);
				mbrpfVO.setPoints( mbrpfVO.getPoints() - price);
				
				//且此處是新增一筆點數轉換紀錄
				TfcordService TfcordSvc = new TfcordService();
				String tfno = TfcordSvc.addTfcordPoint(mbrno, tftype, price, tfstatus, mbrpfVO);
//				System.out.print("Servlet tfno = " + tfno);
				session.setAttribute("tfno",tfno);//因為下面改成重導，如果照原本的存在request中，重導過去後會取不到這個值，所以改存在session
				session.setAttribute("mbrPoint", mbrpfVO.getPoints());//將已經加值過的點數set進sesssion中，讓帳戶管理頁面(listOneMbrtf.jsp)可以抓到最新的點數
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				//Bootstrap_modal
				boolean openModal = true;
				session.setAttribute("openModal", openModal);//因為下面改成重導，如果照原本的存在request中，重導過去後會取不到這個值，所以改存在session

				res.sendRedirect(req.getContextPath()+url);//原本是forward改成重導
				return;
//				將原本的forward改成重導，這樣重新整理時，就不會再做一次兌換現金的動作
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("兌換現金失敗：" + e.getMessage());
			}
		}
		
		if("getMbr_Tfcord".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String mbrno = req.getParameter("mbrno");
				String mbrnoReg = "^BM[0-9]{5}$";
				
				HttpSession session = req.getSession();
				session.setAttribute("mbrno", mbrno);
				
				if(mbrno == null || (mbrno.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}else if(!mbrno.trim().matches(mbrnoReg)) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料****************************************/
				TfcordService tfcordSvc = new TfcordService();
				List<TfcordVO> tfcordVOAll = tfcordSvc.getWhoAll(mbrno);
				if(tfcordVOAll.size() == 0) {
					errorMsgs.add("此會員沒有點數交易紀錄");
				}
				System.out.println(tfcordVOAll);
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tfcordVOAll", tfcordVOAll);
				String url = "/back-end/tfcord/listMbrtf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/	
			}catch(Exception e) {
				errorMsgs.add("查詢紀錄失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
				failView.forward(req, res);
			}
		}
		
		if("changeStatus".equals(action)) { // 來自 /back-end/tfcord/listAllTfcord.jsp 或  /back-end/tfcord/listOnetf.jsp 或 /back-end/tfcord/notYetTfcord.jsp 或  /back-end/tfcord/listMbrtf.jsp 的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String mbrno = req.getParameter("mbrno");
				
				String tfno = req.getParameter("tfno");
				
				/***************************2.開始修改狀態****************************************/
				TfcordService tfcordSvc = new TfcordService();
				tfcordSvc.changeTfcordStatus(tfno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ok", true);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("確認審核失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
				failView.forward(req, res);
			}		
		}
		
		if("getOne_Tfcord".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				String tfno = req.getParameter("tfno");
				String tfnoReg = "^[0-9]{8}-[0-9]{7}$";
				
				if(tfno == null || (tfno.trim()).length() == 0) {
					errorMsgs.add("請輸入兌換編號");
				}else if(!tfno.trim().matches(tfnoReg)) {
					errorMsgs.add("兌換編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料****************************************/
				TfcordService tfcordSvc = new TfcordService();
				TfcordVO tfcordVO = tfcordSvc.getOneTfcord(tfno);
				if(tfcordVO == null) {
					errorMsgs.add("此兌換編號無效");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tfcordVO", tfcordVO);
				String url = "/back-end/tfcord/listOnetf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/	
			}catch(Exception e) {
				errorMsgs.add("查詢紀錄失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/tfcord/listAllTfcord.jsp");
				failView.forward(req, res);
			}
		}
		
		if("deletetf".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String tfno = req.getParameter("tfno");
				Integer price = new Integer (req.getParameter("price"));
				String requestURL = req.getParameter("requestURL");
				
				
				/***************************2.開始刪除資料*****************************************/
				TfcordService tfcordSvc = new TfcordService();
				TfcordVO tfcordVO = tfcordSvc.getOneTfcord(tfno);
				
				String mbrno = tfcordVO.getMbrno();
				MbrpfService mbrpfSvc = new MbrpfService();
				MbrpfVO mbrpfVO = mbrpfSvc.getOneMbrpf(mbrno);
				mbrpfVO.setPoints(mbrpfVO.getPoints() + price);
				
				mbrpfSvc.updateMbrpf(mbrpfVO);
				tfcordSvc.deleteTfcord(tfno);
				req.setAttribute("mbrPoint", mbrpfVO.getPoints());//將已經加值過的點數set進sesssion中，讓帳戶管理頁面(listOneMbrtf.jsp)可以抓到最新的點數
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				if("/front-end/tfcord/listOnetf.jsp".equals(requestURL)) {
					RequestDispatcher successView = req.getRequestDispatcher("/front-end/tfcord/listOneMbrtf.jsp");
					successView.forward(req, res);
					return;
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("取消兌換失敗" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/front-end/tfcord/listOneMbrtf.jsp");
				failView.forward(req, res);
			}
		}
	}
}
