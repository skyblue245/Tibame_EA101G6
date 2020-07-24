package com.shop.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.shop.model.*;

@MultipartConfig
public class ShopServlet extends HttpServlet {
	String saveDirectory = "/images_uploaded";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)||"getOne_For_Display2".equals(action)||"getOne_For_Display3".equals(action)) { 
//			try {
				System.out.println("test");
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String shopno = req.getParameter("shopno");
				String uu = req.getParameter("requestURL");
				System.out.println(uu);
				/*************************** 2.�}�l�d�߸�� *****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shopno);
				System.out.println(shopVO.getShopact());
				
				
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				String url = null;
				boolean openModal = false;
				if("getOne_For_Display".equals(action)) {
					url = "listOneShop.jsp";	
					}
				
				if("getOne_For_Display2".equals(action)) {
					openModal=true;
					url = "/front-end/shopbk/listAllShopbk.jsp";
					System.out.println("123");
				}
				if("getOne_For_Display3".equals(action)) {
					openModal=true;
					url=uu;
					System.out.println("456");
				}
				System.out.println();
				req.setAttribute("openModal", openModal);
				req.setAttribute("shopVO", shopVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/listAllShop.jsp");
//				failureView.forward(req, res);
//			}
		}
		if("getSome_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
			Integer status = new Integer(req.getParameter("status").trim());
			ShopService shopSvc = new ShopService();
			List<ShopVO> shopVO = shopSvc.getByStatus2(status);
			if (shopVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("listAllShop.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
			String url = "listSomeShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			System.out.println(url);
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ShopVO shopVO = (ShopVO) session.getAttribute("shopAcount");
			try {
				
				String city = shopVO.getShoploc().substring(0,3);
				String area = shopVO.getShoploc().substring(4,8);
				String addr = shopVO.getShoploc().substring(9);
 				System.out.println(shopVO.getShoploc().substring(0,3));
 				System.out.println(shopVO.getShoploc().substring(4,9));
 				System.out.println(shopVO.getShoploc().substring(9));
 				
 				
				req.setAttribute("shopVO", shopVO); 
				HashMap<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("city", city);
				hashmap.put("area", area);
				hashmap.put("addr", addr);
				req.setAttribute("cityarea", hashmap);
				String url = "update_shop_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_shop_input.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllShop.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)||"update_back".equals(action)) { // �Ӧ�update_shop_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ShopVO shopVO = new ShopVO();
			ShopService shopSvc = new ShopService();
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			if("update".equals(action)) {
         		System.out.println("front");
				String shopno = req.getParameter("shopno");
				String shopname = req.getParameter("shopname");
				String shopnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (shopname == null || shopname.trim().length() == 0) {
					errorMsgs.add("店家名稱請勿空白");
				} else if (!shopname.trim().matches(shopnameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("店家名稱格式錯誤");
				}

				String shopact = req.getParameter("shopact");
				String shopactReg = "^[(a-zA-Z0-9)]{3,10}$";
				if (shopact == null || shopact.trim().length() == 0) {
					errorMsgs.add("店家帳號請勿空白");
				} else if (!shopact.trim().matches(shopactReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("店家帳號格式錯誤");
				}

				String shoppw = req.getParameter("shoppw");
				String shoppwReg = "^[(a-zA-Z0-9)]{3,10}$";
				if (shoppw == null || shoppw.trim().length() == 0) {
					errorMsgs.add("店家密碼請勿空白");
				} else if (!shoppw.trim().matches(shoppwReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("店家密碼格式錯誤");
				}
				String shoploc = "";
				String city = req.getParameter("city");
				String area = req.getParameter("area");
				String addr = req.getParameter("addr");
				String addrReg = "^[(\u4e00-\u9fa5) a-zA-Z0-9_]{2,50}$";
				if (city != null && city.trim().length() != 0 && area != null && area.trim().length() != 0
						&& addr != null && addr.trim().length() != 0) {

					shoploc = city + area + addr;
					if (!shoploc.matches(addrReg)) {
						errorMsgs.add("地址格式錯誤");
					}
				} else {
					errorMsgs.add("請輸入地址");

				}

				String shopcy = req.getParameter("shopcy");
				String shopcyReg = "^[(\\u4e00-\\u9fa5)(0-9\\*)]{3,9}$";
				if (shopcy == null || shopcy.trim().length() == 0) {
					errorMsgs.add("店家場地請勿空白");
				} else if (!shopcy.trim().matches(shopcyReg)) { 
					errorMsgs.add("場地格式錯誤");
				}
				Integer shopphone = null;
				try {
					shopphone = new Integer(req.getParameter("shopphone"));

				} catch (NumberFormatException e) {
					shopphone = 912345678;
					errorMsgs.add("電話號碼格式錯誤");
				}
				
				byte[] shopimg = null;
				Part part ;
				InputStream in = null;
				
					
					part = req.getPart("shopimg");

					

					try {
						 if(part.getSize() == 0) {			            	
			         		ShopVO shopVo = shopSvc.getOneShop(shopno);
			         		shopimg = shopVo.getShopimg(); 
			         		in = part.getInputStream();
			         		in.read(shopimg);
						 }else{
			            	in = part.getInputStream();
			            	shopimg = new byte[in.available()];
			          		in.read(shopimg);
				    	  } 
					}catch (IOException e) {
						errorMsgs.add("上傳失敗");
						in = getServletContext().getResourceAsStream("/NoData/null.jpg");
						shopimg = new byte[in.available()];
		          		in.read(shopimg);
					}finally {
						in.close();
					}
						
				Integer status = new Integer(req.getParameter("status").trim());
				
				
				System.out.println(shopno);
				System.out.println(shopact);
				System.out.println(shoppw);
				System.out.println(shopname);
				System.out.println(shoploc);
				System.out.println(shopcy);
				System.out.println(shopphone);
				System.out.println(shopimg);
				System.out.println(status);
				shopVO.setShopno(shopno);
				shopVO.setShopact(shopact);
				shopVO.setShoppw(shoppw);
				shopVO.setShopname(shopname);
				shopVO.setShoploc(shoploc);
				shopVO.setShopcy(shopcy);
				shopVO.setShopphone(shopphone);
				shopVO.setShopimg(shopimg);
				shopVO.setStatus(status);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopVO", shopVO); 
					HashMap<String, String> hashmap = new HashMap<String, String>();
					hashmap.put("city", city);
					hashmap.put("area", area);
					hashmap.put("addr", addr);
					req.setAttribute("cityarea", hashmap);
					RequestDispatcher failureView = req.getRequestDispatcher("update_shop_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				shopVO = shopSvc.updateShop(shopno, shopact, shoppw, shopname, shoploc, shopcy, shopphone, shopimg,
						status);
			}
				
         	if("update_back".equals(action)) {
         		System.out.println("back");
         		String shopno = req.getParameter("shopno");
         		Integer status = new Integer(req.getParameter("status").trim());
         		shopVO.setShopno(shopno);
         		shopVO.setStatus(status);
         		System.out.println(status);
         		System.out.println(shopno);
         		shopVO = shopSvc.updateShop_back(status, shopno);
         	}
//				req.setAttribute("shopVO", shopVO);
         	
         	
				session.removeAttribute("shopVO");
				session.setAttribute("shopVO", shopVO);
				String url = "shopArea.jsp";
				System.out.println(req.getParameter("URL"));
				if("update_back".equals(action))url =req.getParameter("URL");
				req.setAttribute("successMsgs", "修改成功!");
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("update_shop_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addshop.jsp���ШD
			System.out.println("test");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			
			System.out.println("1111111111");
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			System.out.println("222222222222");
				String shopname = req.getParameter("shopname");
				String shopnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if (shopname == null || shopname.trim().length() == 0) {
					errorMsgs.add("店家名稱請勿空白");
				} else if (!shopname.trim().matches(shopnameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("店家名稱格式錯誤");
				}

				
				System.out.println("33333333");
				String shopact = req.getParameter("shopact");
				String shopactReg = "^[(a-zA-Z0-9)]{3,10}$";
				if (shopact == null || shopact.trim().length() == 0) {
					errorMsgs.add("店家帳號請勿空白");
				} else if (!shopact.trim().matches(shopactReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("店家帳號格式錯誤");
				}

//				String shoppw = req.getParameter("shoppw");
				int passwdRandom = (int)(Math.random()*99999999+1);
				String passRandom = ""+passwdRandom;
				
				System.out.println("4444444444444");
				String shoploc = "";
				String city = req.getParameter("city");
				String area = req.getParameter("area");
				String addr = req.getParameter("addr");
				String addrReg = "^[(\u4e00-\u9fa5) a-zA-Z0-9_]{2,50}$";
				if (city != null && city.trim().length() != 0 && area != null && area.trim().length() != 0
						&& addr != null && addr.trim().length() != 0) {

					shoploc = city + area + addr;
					if (!shoploc.matches(addrReg)) {
						errorMsgs.add("地址格式錯誤");
					}
				} else {
					errorMsgs.add("請輸入地址");

				}

				String shopcy = req.getParameter("shopcy");
				String shopcyReg = "^[(\\u4e00-\\u9fa5)(0-9\\*)]{3,9}$";
				if (shopcy == null || shopcy.trim().length() == 0) {
					errorMsgs.add("店家場地請勿空白");
				} else if (!shopcy.trim().matches(shopcyReg)) { 
					errorMsgs.add("場地格式錯誤");
				}
				Integer shopphone = null;
				try {
					shopphone = new Integer(req.getParameter("shopphone"));

				} catch (NumberFormatException e) {
					shopphone = 912345678;
					errorMsgs.add("電話號碼格式錯誤");
				}
				
				System.out.println("5555555");
				System.out.println("shopimg");
				byte[] shopimg = null;
				Part part = req.getPart("shopimg");
				System.out.println("55555666665");
				InputStream in = null;
				System.out.println("5555555555555");
				try {
					in = part.getInputStream();
					shopimg = new byte[in.available()];
					in.read(shopimg);
				} catch (IOException e) {

				} finally {
					in.close();
				}
				
				
				
				System.out.println("666666666666");
				Integer status = new Integer(req.getParameter("status").trim());

				ShopVO shopVO = new ShopVO();
				shopVO.setShopact(shopact);
				shopVO.setShopname(shopname);
				shopVO.setShoploc(shoploc);
				shopVO.setShopcy(shopcy);
				shopVO.setShopphone(shopphone);
				shopVO.setShopimg(shopimg);
				shopVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("error");
					req.setAttribute("shopVO", shopVO); // �t����J�榡���~��shopVO����,�]�s�Jreq
					HashMap<String, String> hashmap = new HashMap<String, String>();
					hashmap.put("city", city);
					hashmap.put("area", area);
					hashmap.put("addr", addr);
					req.setAttribute("cityarea", hashmap);
					RequestDispatcher failureView = req.getRequestDispatcher("addShop.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String to = req.getParameter("email");		      
			    String subject = "密碼通知";			      
			    String ch_name = shopname;
			    String messageText = "Hello! " + shopname + " 桌遊列國提醒您，請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
			    System.out.println(passRandom); 
			    ShopMailService mailService = new ShopMailService(to, subject, messageText);
			    mailService.start();
			    
			    
			    
			    System.out.println("end.......");
				/*************************** 2.�}�l�s�W��� ***************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.addShop(shopact, passRandom, shopname, shoploc, shopcy, shopphone, shopimg, status);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "login.jsp";
				req.setAttribute("successMsgs", "a");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllshop.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addShop.jsp");
				failureView.forward(req, res);
			}
		}
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			
			ShopVO shopVO = new ShopVO();
			shopVO.setShopact(account);
			shopVO.setShoppw(password);
			
			ShopService shopSvc = new ShopService();
			shopVO = shopSvc.compare(account, password);
			if (shopVO == null) {
				errorMsgs.add("帳號密碼錯誤");
			}			
			if (!errorMsgs.isEmpty()) {
				System.out.println("req");
				req.setAttribute("shopVO", shopVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("login.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}else {
				shopVO = shopSvc.getOneShop(shopVO.getShopno());
				session.setAttribute("shopAcount", shopVO);
			      
			       try {                                                        
			         String location = (String) session.getAttribute("location");
			         if (location != null) {
			           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
			           res.sendRedirect(location);            
			           return;
			         }
			       }catch (Exception ignored) { }
			       res.sendRedirect(req.getContextPath()+"/front-end/index.jsp");
			       return;
			}
		}
		if ("logout".equals(action)) {
			session.removeAttribute("shopAcount");
			String url = req.getParameter("requestURL");
			if(url.equals("/front-end/shop/shopArea.jsp") || url.equals("/front-end/gmlist/addGmlist.jsp")|| url.equals("/front-end/room/shop_roomList.jsp")|| url.equals("/front-end/shopbk/listSomeShopbk2.jsp")){
				url ="/front-end/index.jsp";
			}
			res.sendRedirect(req.getContextPath()+url);
			return;
		}
			
	}

}
