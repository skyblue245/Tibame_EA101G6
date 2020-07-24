package com.artrp.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.art.model.*;
import com.artrp.model.*;


@MultipartConfig
public class ArtrpServlet  extends HttpServlet{
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
				String artno = req.getParameter("artno");
				
				String detail = req.getParameter("rp_detail");
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("檢舉內容不可空白!");
				}
				
				String mbrno = req.getParameter("mbrno");
				
				Integer status = new Integer(req.getParameter("status").trim());
				
				ArtrpVO artrpVO = new ArtrpVO();
				artrpVO.setArtno(artno);
				artrpVO.setDetail(detail);
				artrpVO.setMbrno(mbrno);
				artrpVO.setStatus(status);
				
				
				if(!errorMsgs.isEmpty()) {
					
				}
				
				/***************************2.開始新增資料***************************************/
				ArtrpService artrpSvc = new ArtrpService();
				artrpVO = artrpSvc.addArtrp(artno, detail, mbrno, status);
				
				/***************************3.新增完成,不跳轉(Send the Success view)***********/
//				RequestDispatcher successView = req.getRequestDispatcher("/font-end/art/listOneArt.jsp"); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
//				RequestDispatcher successView = req.getRequestDispatcher("/font-end/art/listOneArt.jsp"); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
			}
		}
		
		
		if ("update0".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String artrpno = req.getParameter("artrpno");
				Integer status = new Integer(req.getParameter("status"));
				
				String artno = req.getParameter("artno");
				
				ArtrpVO artrpVO = new ArtrpVO();
				
				
				
				ArtService artSvc = new ArtService();
				artSvc.updateArtStatus(artno, status);
				
				ArtrpService artrpSvc = new ArtrpService();
				artrpVO = artrpSvc.updateStatus(artrpno, status);
				artrpVO = artrpSvc.getOneArtrp(artrpno);
				ArtVO artVO = artSvc.getOneArt(artrpVO.getArtno());
			
				/***************************2.開始修改資料*****************************************/
				
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("artno", "reportD");
				jsonobj.put("artWriter", artVO.getMbrno());
				jsonobj.put("arttt", artVO.getArttt());
				jsonobj.put("repD", artrpVO.getDetail());
				String jsonStr = jsonobj.toString();
				req.setAttribute("repiii", jsonStr);
				
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				
				String url = "/back-end/artrp/listAllArtrpS1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
			
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/artrp/listAllArtrpS1.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update1".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String artrpno = req.getParameter("artrpno");
				Integer status = new Integer(req.getParameter("status"));
				
				String artno = req.getParameter("artno");
				
				ArtrpVO artrpVO = new ArtrpVO();
				artrpVO.setArtrpno(artrpno);
				artrpVO.setStatus(status);
				
				
				ArtService artSvc = new ArtService();
				artSvc.updateArtStatus(artno, status);
				
				/***************************2.開始修改資料*****************************************/
				
				ArtrpService artrpSvc = new ArtrpService();
				
				artrpVO = artrpSvc.updateStatus(artrpno, status);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				
				String url = "/back-end/artrp/listAllArtrpS0.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("listAllArtrp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String artno = req.getParameter("artno");
				
				
				
				/***************************2.開始查詢資料***************************************/
				ArtrpService artrpSvc = new ArtrpService();
				List<ArtrpVO> artrpVO = artrpSvc.getAllByArtno(artno);
				ArtService artSvc = new ArtService();
				ArtVO artVO = artSvc.getOneArt(artno);
				
				if (artrpVO == null) {
					errorMsgs.add("查無資料!");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllArtrp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**********************3.查詢完成，開始轉交****************************/
				req.setAttribute("artrpVO", artrpVO);
				req.setAttribute("artVO", artVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/art/listOneArt.jsp");
				successView.forward(req, res);
				
				/**********************其他可能的錯誤處理**********************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = req.getRequestDispatcher("listAllArtrp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
