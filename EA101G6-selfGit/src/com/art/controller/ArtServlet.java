package com.art.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.art.model.*;
@MultipartConfig
public class ArtServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		
		
		//後端功能
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("artno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String artno = null;
				try {
					artno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArtService artSvc = new ArtService();
				ArtVO artVO = artSvc.getOneArt(artno);
				if (artVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			
				req.setAttribute("artVO", artVO);
				String url = "/back-end/art/listOneArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getAW_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mbrno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入作者編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String mbrno = null;
				try {
					mbrno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("作者編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				ArtService artSvc = new ArtService();
				List<ArtVO> artVO = artSvc.getArtsByMbrno(mbrno);
				
				if (artVO.isEmpty()) {
					errorMsgs.add("查無資料");
				} 
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("artVO", artVO);
				String url = "/back-end/art/listMbrArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAT_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("atno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請選擇文章類型");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String atno = null;
				try {
					atno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章類型格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				ArtService artSvc = new ArtService();
				List<ArtVO> artVO = artSvc.getArtsByAtno(atno);
				if (artVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("artVO", artVO);
				String url = "/back-end/art/listMbrArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update_Art_Status".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String artno = req.getParameter("artno");
				Integer status = new Integer(req.getParameter("status"));
				
				
				
				ArtVO artVO = new ArtVO();
				artVO.setArtno(artno);
				artVO.setStatus(status);
				
				
			
				/***************************2.開始修改資料*****************************************/
				
				ArtService artSvc = new ArtService();
				
				artVO = artSvc.updateArtStatus(artno, status);
				
				artVO = artSvc.getOneArt(artno);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/art/listAllArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/art/listMbrArt.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if ("update_Art_Status0".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String artno = req.getParameter("artno");
				Integer status = new Integer(req.getParameter("status"));
				
				
				
				ArtVO artVO = new ArtVO();
				artVO.setArtno(artno);
				artVO.setStatus(status);
				
				
				
				/***************************2.開始修改資料*****************************************/
				
				ArtService artSvc = new ArtService();
				
				artVO = artSvc.updateArtStatus(artno, status);
				
				artVO = artSvc.getOneArt(artno);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/art/listAllStatus0.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				
			}		
		}
		if ("update_Art_Status1".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String artno = req.getParameter("artno");
				Integer status = new Integer(req.getParameter("status"));
				
				
				
				ArtVO artVO = new ArtVO();
				artVO.setArtno(artno);
				artVO.setStatus(status);
				
				
				
				
				/***************************2.開始修改資料*****************************************/
				
				ArtService artSvc = new ArtService();
				
				artVO = artSvc.updateArtStatus(artno, status);
				
				artVO = artSvc.getOneArt(artno);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/art/listAllStatus1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				
			}		
		}
		
		
		
		
		
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.開始刪除資料***************************************/
				ArtService artSvc = new ArtService();
				artSvc.deleteArt(artno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/art/listAllArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mbrno = req.getParameter("mbrno");
				if (mbrno == null || mbrno.trim().length() == 0) {
					errorMsgs.add("請先登入會員");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllArt.jsp");
					failureView.forward(req, res);
					return;
				}
				String arttt = req.getParameter("arttt");
				if (arttt == null || arttt.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String detail = req.getParameter("detail");
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				Integer status = new Integer(req.getParameter("status").trim());
				
				String atno = req.getParameter("atno");
				
				Part part = req.getPart("apic");
				InputStream in = part.getInputStream();
				byte [] apic = new byte[in.available()];
				in.read(apic);
				
				
				
				ArtVO artVO = new ArtVO();
				artVO.setMbrno(mbrno);
				artVO.setArttt(arttt);
				artVO.setDetail(detail);
				artVO.setStatus(status);
				artVO.setAtno(atno);
				artVO.setApic(apic);
				
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artVO", artVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/art/addArt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArtService artSvc = new ArtService();
				artVO = artSvc.addArt(mbrno, detail, arttt, status, atno, apic);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("artno", "backA");
				jsonobj.put("artWriter", artVO.getMbrno());
				jsonobj.put("arttt", artVO.getArttt());
				String strjsonobj = jsonobj.toString();
				req.setAttribute("backAddArt", strjsonobj);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/art/listAllArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/			 
			} catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/art/addArt.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		//////////////////////////前端功能///////////////////////////////
		
		if ("get_One_Detail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*******************1.接收請求參數，處理錯誤訊息************************/
				String artno = req.getParameter("artno");
				
				
				
				
				/***********************2.開始查詢資料***************************/
				ArtService artSvc = new ArtService();
				ArtVO artVO = artSvc.getOneArt(artno);
				
				if (artVO == null) {
					errorMsgs.add("查無資料");
				} 
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**********************3.查詢完成，開始轉交****************************/
				req.setAttribute("artVO", artVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/art/listOneArt.jsp");
				successView.forward(req, res);
				
				
				/**********************其他可能的錯誤處理**********************/
			} catch (Exception e) {
				
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art/listAllArt.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
		
		
		
		
		
		if ("getOne_Key_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("keyWord");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入關鍵字");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art/select_page.jsp");
					failureView.forward(req,  res);
					return;
				}
				
				String keyWord = null;
				try {
					keyWord = new String(str);
				} catch (Exception e) {
					errorMsgs.add("關鍵字格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArtService artSvc = new ArtService();
				List<ArtVO> artVO = artSvc.getArtsByArttt(keyWord);
				
				if (artVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/art/listAllArt.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("keyWord", keyWord);
				req.setAttribute("artVO", artVO);
				String url = "/front-end/art/listKeyWordArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				
			} catch (Exception e) {
				
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("insert_AF".equals(action)) {
			res.setContentType("text/html; charset=utf-8");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Writer out = res.getWriter();
			
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mbrno = req.getParameter("mbrno");
				if (mbrno == null || mbrno.trim().length() == 0) {
					errorMsgs.add("請先登入會員");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String arttt = req.getParameter("arttt");
				if (arttt == null || arttt.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String detail = req.getParameter("detail");
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				Integer status = new Integer(req.getParameter("status").trim());
				
				String atno = req.getParameter("atno");
				
				
				
				Part part = req.getPart("apic");
				InputStream in = part.getInputStream();
				byte [] apic = new byte[in.available()];
				in.read(apic);
				
				
				
				
				ArtVO artVO = new ArtVO();
				artVO.setMbrno(mbrno);
				artVO.setArttt(arttt);
				artVO.setDetail(detail);
				artVO.setStatus(status);
				artVO.setAtno(atno);
				artVO.setApic(apic);
				
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art/addArt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArtService artSvc = new ArtService();
				artVO = artSvc.addArt(mbrno, detail, arttt, status, atno, apic);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("artno","new");
				jsonobj.put("artWriter", mbrno);
				jsonobj.put("arttt", arttt);
				String jsonstr = jsonobj.toString();
				
				req.setAttribute("addArt", jsonstr);
				
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/art/listAllArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/			 
			} catch (Exception e) {
			
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art/addEmp.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String artno = req.getParameter("artno");
				
				/***************************2.開始查詢資料****************************************/
				ArtService artSvc = new ArtService();
				ArtVO artVO = artSvc.getOneArt(artno);
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				if (artVO.getStatus() == 0) {
					req.setAttribute("artVO", artVO);
					String url = "/front-end/art/updateOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else if (artVO.getStatus() == 1) {
					req.setAttribute("artVO", artVO);
					String url = "/front-end/art/reUpdate.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("reUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String arttt = req.getParameter("arttt");
				if (arttt == null || arttt.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String detail = req.getParameter("detail");
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				String artno = req.getParameter("artno");
				String mbrno = req.getParameter("mbrno");
				String atno = req.getParameter("atno");
				
				
			
				byte[] apic = null;
				//類似getParameter()
				Part part = req.getPart("apic");
				//用part物件去接水管
				InputStream in = part.getInputStream();
				//先判斷Part裡面有沒有資料，Part裡面有資料代表有上傳或修改新圖片
				if(in.available() > 0) {
					apic = new byte[in.available()];
				} else {  //若是part裡面沒有資料，則new一個service去找原本VO裡面的byte[]
					ArtService artSvc = new ArtService();
					ArtVO artVO = artSvc.getOneArt(artno);
					apic = artVO.getApic();
				}
				in.read(apic);
				in.close();
			
				ArtVO artVO = new ArtVO();
				artVO.setArtno(artno);
				artVO.setMbrno(mbrno);
				artVO.setArttt(arttt);
				artVO.setDetail(detail);
				artVO.setArtno(atno);
				artVO.setApic(apic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/art/updateOne.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				/***************************2.開始修改資料*****************************************/
				ArtService artSvc = new ArtService();
				artVO = artSvc.updateArt(artno, mbrno, detail, arttt, atno, apic);
				artVO = artSvc.getOneArt(artno);
				
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("artno", "re");
				jsonobj.put("artWriter", artVO.getMbrno());
				jsonobj.put("arttt", artVO.getArttt());
				String jsonStr = jsonobj.toString();
				req.setAttribute("reEdit", jsonStr);
				
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artVO", artVO);
				String url = "/front-end/art/listOneArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
			
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art/updateOne.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String arttt = req.getParameter("arttt");
				if (arttt == null || arttt.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String detail = req.getParameter("detail");
				if (detail == null || detail.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				String artno = req.getParameter("artno");
				String mbrno = req.getParameter("mbrno");
				String atno = req.getParameter("atno");
				
				
				
				byte[] apic = null;
				//類似getParameter()
				Part part = req.getPart("apic");
				//用part物件去接水管
				InputStream in = part.getInputStream();
				//先判斷Part裡面有沒有資料，Part裡面有資料代表有上傳或修改新圖片
				if(in.available() > 0) {
					apic = new byte[in.available()];
				} else {  //若是part裡面沒有資料，則new一個service去找原本VO裡面的byte[]
					ArtService artSvc = new ArtService();
					ArtVO artVO = artSvc.getOneArt(artno);
					apic = artVO.getApic();
				}
				in.read(apic);
				in.close();
				
				
				
				
				
				ArtVO artVO = new ArtVO();
				artVO.setArtno(artno);
				artVO.setMbrno(mbrno);
				artVO.setArttt(arttt);
				artVO.setDetail(detail);
				artVO.setArtno(atno);
				artVO.setApic(apic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/art/updateOne.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArtService artSvc = new ArtService();
				artVO = artSvc.updateArt(artno, mbrno, detail, arttt, atno, apic);
				artVO = artSvc.getOneArt(artno);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artVO", artVO);
				String url = "/front-end/art/listOneArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art/updateOne.jsp");
				failureView.forward(req, res);
			}
		}
		

		
		
		
		if ("delete_M".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.開始刪除資料***************************************/
				ArtService artSvc = new ArtService();
				artSvc.deleteArt(artno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/font-end/art/listAllArt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/art/listAllArt.jsp");
				failureView.forward(req, res);
			}
		}
		
	} 


}
