package com.mallOr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.model.MallVO;
import com.mallOr.model.MallOrService;
import com.mallOr.model.MallOrVO;
import com.mallOrDt.model.MallOrDtService;
import com.mallOrDt.model.MallOrDtVO;
import com.mbrpf.model.MbrpfService;
import com.mbrpf.model.MbrpfVO;

public class MallOrServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;Charset:UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		List<MallVO> buyCarList = (LinkedList<MallVO>) session.getAttribute("buyCarList");

		if ("showCheckOut".equals(action)) {
			if (buyCarList == null || buyCarList.size() == 0) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/buyCar/buyCar.jsp");
				req.setAttribute("noMallAlert", "購物車無商品無法結帳");
				dispatcher.forward(req, res);
				return;
			} else {
				
				Integer totalPrice=buyCarList.stream()
						.mapToInt(p -> p.getPrice()*p.getQuantity())
						.sum();
				session.setAttribute("totalPrice", totalPrice);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/mallOr/mallOr.jsp");
				dispatcher.forward(req, res);
				return;
			}

		}

		if ("checkOut".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				
				String mbract = (String)session.getAttribute("account");
				MbrpfService mbrpfSvc = new MbrpfService();
				MbrpfVO mbrpfVo=mbrpfSvc.checkLogin(mbract);
				
				List<MallOrDtVO> mallOrDtList = new ArrayList<MallOrDtVO>();
				
				if (buyCarList != null && buyCarList.size() != 0) {
					for (MallVO mallVo : buyCarList) {
						MallOrDtVO mallOrDtVo = new MallOrDtVO();
						mallOrDtVo.setCommNo(mallVo.getCommNo());
						mallOrDtVo.setPrice(mallVo.getPrice());
						mallOrDtVo.setQuantity(mallVo.getQuantity());
						mallOrDtList.add(mallOrDtVo);
					}
				} else {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/buyCar/buyCar.jsp");
					req.setAttribute("noMallAlert", "購物車無商品無法結帳");
					dispatcher.forward(req, res);
					return;
				}
				Boolean pointErro=false;
				List<String> erroList = new LinkedList<String>();
				MallOrService mallOrSvc = new MallOrService();
							
				// 時間的部分現在時間 並不是讓使用者輸入沒有錯誤驗證
				java.sql.Timestamp orDate = new java.sql.Timestamp(System.currentTimeMillis());
				// 取貨方式
				String take = "";
				if (req.getParameter("take") != null) {
					take = req.getParameter("take");
				} else {
					erroList.add("請勾選取貨方式");
				}
				// 地址
				String address = "";
				String city = req.getParameter("city");
				String area = req.getParameter("area");
				String addr = req.getParameter("addr");
				String addrReg = "^[(\u4e00-\u9fa5) a-zA-Z0-9_]{2,50}$";
				if (city != null && city.trim().length() != 0 && area != null && area.trim().length() != 0
						&& addr != null && addr.trim().length() != 0) {

					address = city + area + addr;
					if (!address.matches(addrReg)) {
						erroList.add("地址格式錯誤");
					}
				} else {
					erroList.add("請輸入地址");

				}
				// 一開始是0未完成的狀態
				Integer status = 0;
				// 是1已完成繳款
				Integer payStatus = 1;
				// 一開始是0未出貨的狀態
				Integer boxStatus = 0;
				// 價錢el計算不是使用者輸入，只確定是不是大於8位數
				String priceReg = "[0-9]{1,8}";
				String strPrice = req.getParameter("price");
				Integer price = null;
				if (strPrice.matches(priceReg)) {
					price = new Integer(strPrice);
				} else {
					erroList.add("金額過於龐大請分次處理");
				}
				
				if (price<=mbrpfVo.getPoints()) {
					mbrpfVo.setPoints(mbrpfVo.getPoints()-price);
				} else{
					erroList.add("點數不夠請儲值");
					pointErro=true;
				}

				if (!erroList.isEmpty()) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/mallOr/mallOr.jsp");
					req.setAttribute("erroList", erroList);
					req.setAttribute("city", city);
					req.setAttribute("area", area);
					req.setAttribute("addr", addr);
					req.setAttribute("take", take);
					req.setAttribute("totalPrice", price);
					req.setAttribute("pointErro",pointErro);
					dispatcher.forward(req, res);
					return;
				}

				/***************************
				 * 2.開始新增or的資料同時新增dt同時扣會員點數
				 ***************************************/
				MallOrVO mallOrVo = new MallOrVO();
				mallOrVo = mallOrSvc.add(mbrpfVo.getMbrno(), orDate, take, address, status, payStatus, boxStatus, price,
						mallOrDtList,mbrpfVo);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				session.removeAttribute("buyCarList");
				req.setAttribute("mallOrDtList", mallOrDtList);
				req.setAttribute("mallOrVo", mallOrVo);
				req.setAttribute("mbrName", mbrpfVo.getMbrname());
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/mallOr/mallOrGetOne.jsp");
				dispatcher.forward(req, res);
				return;

			} catch (Exception e) {
				e.getStackTrace();
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/mallOr/mallOr.jsp");
				req.setAttribute("noMallAlert", "請稍後在試");
				dispatcher.forward(req, res);
				return;
			}

		}

		if ("updateBox".equals(action)) {
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer payStatus = null;
				Integer status = null;
				Integer boxStatus = null;
				String mallOrNo = null;
				String mbrNo=null;
				try {
					// 訂單狀態
					if (req.getParameter("status") != null && req.getParameter("status").trim().length() != 0) {
						status = new Integer(req.getParameter("status"));
					}
					// 繳款
					if (req.getParameter("payStatus") != null && req.getParameter("payStatus").trim().length() != 0) {
						payStatus = new Integer(req.getParameter("payStatus"));
					}
					// 出貨的狀態
					if (req.getParameter("boxStatus") != null && req.getParameter("boxStatus").trim().length() != 0) {
						boxStatus = new Integer(1);
					}
					if (req.getParameter("mallOrNo") != null && req.getParameter("mallOrNo").trim().length() != 0) {
						mallOrNo = req.getParameter("mallOrNo");
					}
					
					if (req.getParameter("mbrNo") != null && req.getParameter("mbrNo").trim().length() != 0) {
						mbrNo = req.getParameter("mbrNo");
					}
					
				} catch (Exception e) {
					e.getStackTrace();
				}
				/**************************** 2.開始修改,********************************************/
				MallOrService mallOrSvc = new MallOrService();
				mallOrSvc.update(mallOrNo, status, payStatus, boxStatus);
			    //因為傳送郵件需要時間所以我改成多執行緒版
				
				MbrpfService mbrpfSvc = new MbrpfService();
				MbrpfVO mbrpfVo=mbrpfSvc.getOneMbrpf(mbrNo);
				if(mbrpfVo.getMail()!=null) {
					String to = mbrpfVo.getMail();  
					String subject = "您好!您的訂單"+mallOrNo+"已出貨"; 
					String mbrName = mbrpfVo.getMbrname();
					String messageText = "Hello! " + mbrName + subject; 
					OrderMail orderMail = new OrderMail(to, subject, messageText);
					orderMail.start();
				}
				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				session.setAttribute("msg","出貨成功");
				res.sendRedirect(req.getContextPath() + "/back-end/mallOr/mallOrGet.jsp?active=getByBox");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("msg","系統忙碌中請稍後在試!");
				res.sendRedirect(req.getContextPath() + "/back-end/mallOr/mallOrGet.jsp?active=getByBox");
				return;
			}

		}

		if ("updateStatus".equals(action))

		{
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				Integer payStatus = null;
				Integer status = null;
				Integer boxStatus = null;
				String mallOrNo = null;
				try {
					//會員編號
					if (req.getParameter("mallOrNo") != null && req.getParameter("mallOrNo").trim().length() != 0) {
						mallOrNo = req.getParameter("mallOrNo");
					}
					// 訂單狀態如果是取消順便把款項還給會員
					if (req.getParameter("status") != null && req.getParameter("status").trim().length() != 0) {
						status = new Integer(req.getParameter("status"));
					}
					// 繳款狀態
					if (req.getParameter("payStatus") != null && req.getParameter("payStatus").trim().length() != 0) {
						payStatus = new Integer(req.getParameter("payStatus"));
					}
					// 出貨的狀態
					if (req.getParameter("boxStatus") != null && req.getParameter("boxStatus").trim().length() != 0) {
						boxStatus = new Integer(req.getParameter("boxStatus"));
					}

				} catch (NumberFormatException e) {
					e.getStackTrace();
				}
				/********************************************
				 * 2.開始修改,
				 ********************************************/
				MallOrService mallOrSvc = new MallOrService();
				mallOrSvc.update(mallOrNo, status, payStatus, boxStatus);
				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/mallOr/mbrMallOr.jsp");
				dispatcher.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				res.sendRedirect(req.getContextPath() + "/front-end/mallOr/mbrMallOr.jsp");
				return;
			}

		}
		
		if ("cancelOr".equals(action))

		{
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				Integer payStatus = null;
				Integer status = null;
				Integer boxStatus = null;
				String mallOrNo = null;
				Integer price = null;
				String mbrNo=null;
				try {
					//訂單編號
					if (req.getParameter("mallOrNo") != null && req.getParameter("mallOrNo").trim().length() != 0) {
						mallOrNo = req.getParameter("mallOrNo");
					}
					// 訂單狀態如果是取消順便把款項還給會員
					if (req.getParameter("status") != null && req.getParameter("status").trim().length() != 0) {
						status = new Integer(req.getParameter("status"));
					}
					// 繳款狀態
					if (req.getParameter("payStatus") != null && req.getParameter("payStatus").trim().length() != 0) {
						payStatus = new Integer(req.getParameter("payStatus"));
					}
					// 出貨的狀態
					if (req.getParameter("boxStatus") != null && req.getParameter("boxStatus").trim().length() != 0) {
						boxStatus = new Integer(req.getParameter("boxStatus"));
					}
					//總價錢
					if(req.getParameter("price") != null && req.getParameter("price").trim().length() != 0) {
						price=new Integer(req.getParameter("price"));
					}
					//會員編號
					if(req.getParameter("mbrNo") != null && req.getParameter("mbrNo").trim().length() != 0) {
						mbrNo=req.getParameter("mbrNo");
					}
				} catch (NumberFormatException e) {
					e.getStackTrace();
				}
				/********************************************
				 * 2.開始修改以及退款
				 ********************************************/
				MbrpfService mbrSvc=new MbrpfService();
				MbrpfVO mbrpfVO = mbrSvc.getOneMbrpf(mbrNo);
				mbrpfVO.setPoints(mbrpfVO.getPoints()+price);
				mbrSvc.updateMbrpf(mbrpfVO);
				MallOrService mallOrSvc = new MallOrService();
				mallOrSvc.update(mallOrNo, status, payStatus, boxStatus);
				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				session.setAttribute("msg","取消訂單成功，退款成功，會員目前點數:"+mbrpfVO.getPoints()+"點");
				res.sendRedirect(req.getContextPath()+"/front-end/mallOr/mbrMallOr.jsp");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("msg","發生錯誤請稍後在試");
				res.sendRedirect(req.getContextPath() + "/front-end/mallOr/mbrMallOr.jsp");
				return;
			}

		}

		if ("selectone".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String mallOrNo = req.getParameter("mallOrNo");
			if (mallOrNo == null || mallOrNo.trim().length() == 0) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/back-end/mallOr/mallOrGet.jsp");
				req.setAttribute("erroAlert", "查無此訂單請稍後在試");
				dispatcher.forward(req, res);
				return;
			}
			String mbrNo= req.getParameter("mbrNo");
			MbrpfService mbrpfSvc = new MbrpfService();
			MbrpfVO mbrpfVo=mbrpfSvc.getOneMbrpf(mbrNo);
			/***************************
			 * 2.開始查詢,
			 ********************************************/
			MallOrService mallOrSvc = new MallOrService();
			MallOrDtService mallOrDtSvc = new MallOrDtService();
			MallOrVO mallOrVo = mallOrSvc.findOneByOrNo(mallOrNo);
			Set<MallOrDtVO> mallOrDtSet = mallOrDtSvc.getByOrNo(mallOrNo);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			RequestDispatcher dispatcher = req.getRequestDispatcher("/back-end/mallOr/mallOrGet.jsp");
			req.setAttribute("showDetail", true);
			req.setAttribute("mbrpfVo", mbrpfVo);
			req.setAttribute("mallOrVo", mallOrVo);
			req.setAttribute("mallOrDtSet", mallOrDtSet);
			dispatcher.forward(req, res);
			return;

		}

	}

}
