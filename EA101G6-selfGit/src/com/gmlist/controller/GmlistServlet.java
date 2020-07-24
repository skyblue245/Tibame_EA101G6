package com.gmlist.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.gmlist.model.*;
import com.shop.model.ShopVO;

@MultipartConfig
public class GmlistServlet extends HttpServlet {
	String saveDirectory = "/images_uploaded";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			String errorMsgs = "";
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String gmno = req.getParameter("gmno1");
				String gmnoReg = "[D][G]\\d{5}";
				if (gmno == null || (gmno.trim()).length() == 0) {
					errorMsgs+="店家編號不能為空";
					req.setAttribute("errorMsgs", errorMsgs);
				} else if (!gmno.trim().matches(gmnoReg)) {
					errorMsgs+="店家編號格式錯誤";
					req.setAttribute("errorMsgs", errorMsgs);
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGmlist.jsp");
					failureView.forward(req, res);
					return;
				}
				String shopno = req.getParameter("shopno1");
				String shopnoReg = "[D][S]\\d{5}";
				if (shopno == null || (shopno.trim()).length() == 0) {
					errorMsgs+="店家編號不能為空";
					req.setAttribute("errorMsgs", errorMsgs);
				} else if (!shopno.trim().matches(shopnoReg)) {
					errorMsgs+="店家編號格式錯誤";
					req.setAttribute("errorMsgs", errorMsgs);
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGmlist.jsp");
					failureView.forward(req, res);
					return;
				}


				GmlistService gmlistSvc = new GmlistService();
				GmlistVO gmlistVO = gmlistSvc.getOneGmlist(gmno, shopno);
				if (gmlistVO == null) {
					errorMsgs+="查無資料";
					req.setAttribute("errorMsgs", errorMsgs);
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGmlist.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.(Send the Success view) *************/
				req.setAttribute("gmlistVO", gmlistVO);
				String url = "listOneGmlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnegmlist.jsp
				successView.forward(req, res);

				/***************************
				 * ��L�i�઺���~�B�z
				 *************************************/
			} catch (Exception e) {
				errorMsgs+="無法取得資料:" + e.getMessage();
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGmlist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getSome_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

			GmlistService gmlistSvc = new GmlistService();
			List<GmlistVO> gmlistVO = null;
			String shopno = req.getParameter("shopno");
			String gmno = req.getParameter("gmno");
			if(gmno == null)
				gmlistVO = gmlistSvc.getSomeGmlistByShop(shopno);	
			if(shopno == null)
				gmlistVO = gmlistSvc.getSomeGmlistByShop(gmno);

			if (gmlistVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGmlist.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.(Send the Success view) *************/
			String url = "listSomeGmlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGmlist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // addgmlist.jsp���ШD

				String errorMsgs = "";
				
				if(req.getParameterValues("gmno2") == null) {
					errorMsgs+="請選擇要增加的遊戲";
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("addGmlist.jsp");
					failureView.forward(req, res);
					return;
				}
				try {
				String[] gmno = req.getParameterValues("gmno2");
				ShopVO shopVO = (ShopVO)session.getAttribute("shopAcount");
				

				GmlistService gmlistSvc = new GmlistService();
				for(String s:gmno) {
					gmlistSvc.addGmlist(s, shopVO.getShopno());
				}

				
				/*************************** 3.(Send the Success view) *************/
				String url = "addGmlist.jsp";
				req.setAttribute("successMsgs", "新增成功!");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				} catch (Exception e) {
					errorMsgs+="無法取得資料" + e.getMessage();
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("addGmlist.jsp");
					failureView.forward(req, res);
				}
		}
		
		if ("delete".equals(action)) { // �Ӧ�select_page.jsp���ШD

			String errorMsgs = "";
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			

			try {
			String[] gmno = req.getParameterValues("gmno");
			ShopVO shopVO = (ShopVO)session.getAttribute("shopAcount");
			if(gmno == null) {
				errorMsgs+="請選擇要刪除的遊戲";
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("addGmlist.jsp");
				failureView.forward(req, res);
				return;
			}
			// Send the use back to the form, if there were errors
			GmlistService gmlistSvc = new GmlistService();
			for(String s:gmno) {
				gmlistSvc.deleteGmlist(s, shopVO.getShopno());
			}
			
			
			/*************************** 3.(Send the Success view) *************/
			String url = "addGmlist.jsp";
			req.setAttribute("successMsgs", "刪除成功!");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs+="無法取得資料" + e.getMessage();
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("addGmlist.jsp");
				failureView.forward(req, res);
			}
		}


	}

}
