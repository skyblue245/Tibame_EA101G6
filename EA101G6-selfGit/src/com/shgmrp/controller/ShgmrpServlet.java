package com.shgmrp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shgm.model.ShgmService;
import com.shgm.model.ShgmVO;
import com.shgmrp.model.ShgmrpService;
import com.shgmrp.model.ShgmrpVO;

public class ShgmrpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		if ("get_one".equals(action)) {

			List<String> errormsgs = new LinkedList<String>();
			request.setAttribute("errormsgs", errormsgs);

			try {

				String shgmrpno = request.getParameter("shgmrpno");
				String shgmrpnoreg = "^CB\\d{5}$";

				if (shgmrpno.trim().length() == 0) {
					errormsgs.add("您未輸入市集商品檢舉編號");
				} else if (!shgmrpno.matches(shgmrpnoreg)) {
					errormsgs.add("請依照市集商品檢舉編號格式輸入");
				}
				if (!errormsgs.isEmpty()) {
					String url = "/back-end/shgmrp/shgmrp_select_page.jsp";
					RequestDispatcher failedview = request.getRequestDispatcher(url);
					failedview.forward(request, response);
					return;
				}

				ShgmrpService shgmrpsvc = new ShgmrpService();
				ShgmrpVO shgmrpvo = shgmrpsvc.getOneShgmrp(shgmrpno);
				if (shgmrpvo == null) {
					errormsgs.add("查無資料");
				}
				if (!errormsgs.isEmpty()) {
					String url = "/back-end/shgmrp/shgmrp_select_page.jsp";
					RequestDispatcher failedview = request.getRequestDispatcher(url);
					failedview.forward(request, response);
					return;
				}

				request.setAttribute("shgmrpvo", shgmrpvo);
				String url = "/back-end/shgmrp/listOneShgmrp.jsp";
				RequestDispatcher successview = request.getRequestDispatcher(url);
				successview.forward(request, response);

			} catch (Exception e) {
				errormsgs.add("無法取得個別資料" + e.getMessage());
				String url = "/back-end/shgmrp/shgmrp_select_page.jsp";
				RequestDispatcher failedview = request.getRequestDispatcher(url);
				failedview.forward(request, response);
			}
		}

		if ("insertrp".equals(action)) {

			HashMap<String, String> errormap = new HashMap<String, String>();
			request.setAttribute("errormap", errormap);

			String requestURL = request.getParameter("requestURL");

			try {
				String shgmno = request.getParameter("shgmno");
				
				// EL從session拿到的會員編號
				String suiterno = request.getParameter("suiterno");

				String detail = request.getParameter("detail");
				if (detail.trim().length() == 0)
					errormap.put("rp", "檢舉內容不得為空");
				Integer status = 0;

				ShgmrpVO shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmno(shgmno);
				shgmrpvo.setSuiterno(suiterno);
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(status);
				
				ShgmService shgmsvc = new ShgmService();
				ShgmVO shgmvo = shgmsvc.getOneShgm(shgmno);
				request.setAttribute("shgmvo", shgmvo);
				
				if (!errormap.isEmpty()) {
					RequestDispatcher failedview = request.getRequestDispatcher(requestURL);
					failedview.forward(request, response);
					return;
				}

				ShgmrpService shgmrpsvc = new ShgmrpService();
				shgmrpsvc.addShgmrp(shgmno, suiterno, detail, status);
				request.setAttribute("rpsuccess", "success");

				RequestDispatcher successview = request.getRequestDispatcher(requestURL);
				successview.forward(request, response);
			} catch (Exception e) {
				errormap.put("rp", "無法執行檢舉");
				RequestDispatcher failedview = request.getRequestDispatcher(requestURL);
				failedview.forward(request, response);
			}
		}

		if ("insert".equals(action)) {

			List<String> errormsgs = new LinkedList<String>();
			request.setAttribute("errormsgs", errormsgs);

			try {
				String shgmno = request.getParameter("shgmno");
				String shgmnoreg = "^CA\\d{5}$";

				ShgmService shgmsvc = new ShgmService();

				if (shgmno.trim().length() == 0) {
					errormsgs.add("市集商品編號：您未輸入市集商品編號");
				} else if (!shgmno.matches(shgmnoreg)) {
					errormsgs.add("市集商品編號：請依照市集商品編號格式輸入");
				} else if (shgmsvc.getOneShgm(shgmno) == null) {
					errormsgs.add("市集商品編號：無此市集商品");
				}

				String suiterno = request.getParameter("suiterno");
				String suiternoreg = "^BM\\d{5}$";
				if (suiterno.trim().length() == 0) {
					errormsgs.add("檢舉人會員編號：您的會員編號不得為空");
				} else if (!suiterno.matches(suiternoreg)) {
					errormsgs.add("檢舉人會員編號：請依照會員編號格式輸入");
				}

				String detail = request.getParameter("detail");
				if (detail.trim().length() == 0) {
					errormsgs.add("檢舉內容：檢舉內容不得為空");
				}

				Integer status = new Integer(request.getParameter("status"));

				ShgmrpVO shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmno(shgmno);
				shgmrpvo.setSuiterno(suiterno);
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(status);

				if (!errormsgs.isEmpty()) {
					request.setAttribute("shgmrpvo", shgmrpvo);
					String url = "/back-end/shgmrp/addShgmrp.jsp";
					RequestDispatcher failedview = request.getRequestDispatcher(url);
					failedview.forward(request, response);
					return;
				}

				ShgmrpService shgmrpsvc = new ShgmrpService();
				shgmrpvo = shgmrpsvc.addShgmrp(shgmno, suiterno, detail, status);

				String url = "/back-end/shgmrp/listAllShgmrp.jsp";
				RequestDispatcher successview = request.getRequestDispatcher(url);
				successview.forward(request, response);
			} catch (Exception e) {
				errormsgs.add("無法檢舉市集商品" + e.getMessage());
				String url = "/back-end/shgmrp/addShgmrp.jsp";
				RequestDispatcher failedview = request.getRequestDispatcher(url);
				failedview.forward(request, response);
			}
		}

		if ("getone_update".equals(action)) {

			List<String> errormsgs = new LinkedList<String>();
			request.setAttribute("errormsgs", errormsgs);

			try {
				String shgmrpno = request.getParameter("shgmrpno");

				ShgmrpService shgmrpsvc = new ShgmrpService();
				ShgmrpVO shgmrpvo = shgmrpsvc.getOneShgmrp(shgmrpno);
				shgmrpvo.setShgmrpno(shgmrpno);

				request.setAttribute("shgmrpvo", shgmrpvo);

				String url = "/back-end/shgmrp/updateShgmrp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errormsgs.add("無法取得要修改的資料:" + e.getMessage());
				String url = "/back-end/shgmrp/shgmrp_select_page.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
			}
		}

		if ("update".equals(action)) {
			List<String> errormsgs = new LinkedList<String>();
			request.setAttribute("errormsgs", errormsgs);
			
			String whichPage = request.getParameter("whichPage");

			try {
				String shgmrpno = request.getParameter("shgmrpno");

				String shgmno = request.getParameter("shgmno");
				String shgmnoreg = "^CA\\d{5}$";

				ShgmService shgmsvc = new ShgmService();

				if (shgmno.trim().length() == 0) {
					errormsgs.add("市集商品編號：您未輸入市集商品編號");
				} else if (!shgmno.matches(shgmnoreg)) {
					errormsgs.add("市集商品編號：請依照市集商品編號格式輸入");
				} else if (shgmsvc.getOneShgm(shgmno) == null) {
					errormsgs.add("市集商品編號：無此市集商品");
				}

				String suiterno = request.getParameter("suiterno");
				String suiternoreg = "^BM\\d{5}$";

				if (suiterno.trim().length() == 0) {
					errormsgs.add("檢舉人會員編號：您的會員編號不得為空");
				} else if (!suiterno.matches(suiternoreg)) {
					errormsgs.add("檢舉人會員編號：請依照會員編號格式輸入");
				}

				String detail = request.getParameter("detail");
				if (detail.trim().length() == 0) {
					errormsgs.add("檢舉內容：檢舉內容不得為空");
				}

				Integer status = new Integer(request.getParameter("status"));

				ShgmrpVO shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmrpno(shgmrpno);
				shgmrpvo.setShgmno(shgmno);
				shgmrpvo.setSuiterno(suiterno);
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(status);

				if (!errormsgs.isEmpty()) {
					request.setAttribute("shgmrpvo", shgmrpvo);
					String url = "/back-end/shgmrp/updateShgmrp.jsp?whichPage="+whichPage;
					RequestDispatcher failedview = request.getRequestDispatcher(url);
					failedview.forward(request, response);
					return;
				}

				ShgmrpService shgmrpsvc = new ShgmrpService();
				shgmrpvo = shgmrpsvc.updateShgmrp(shgmrpno, shgmno, suiterno, detail, status);

				String url = "/back-end/shgmrp/listAllShgmrp.jsp?whichPage="+whichPage;
				RequestDispatcher successview = request.getRequestDispatcher(url);
				successview.forward(request, response);
			} catch (Exception e) {
				errormsgs.add("無法修改檢舉的市集商品" + e.getMessage());
				String url = "/back-end/shgmrp/listAllShgmrp.jsp?whichPage="+whichPage;
				RequestDispatcher failedview = request.getRequestDispatcher(url);
				failedview.forward(request, response);
			}
		}
		
		if ("search".equals(action)) {
			List<String> errormsgs = new LinkedList<String>();
			request.setAttribute("errormsgs", errormsgs);
			
			try {
				String word = request.getParameter("word");
				
				ShgmService shgmsvc = new ShgmService();
				Set<ShgmVO> shgmSet = shgmsvc.searchForAll(word);
				
				ShgmrpService shgmrpsvc = new ShgmrpService();
				Set<ShgmrpVO> set = new LinkedHashSet<ShgmrpVO>();
				
				Set<ShgmrpVO> shgmrpset = shgmrpsvc.getAllShgmrp();
				for(ShgmrpVO shgmrpvo:shgmrpset) {
					for(ShgmVO shgmvo:shgmSet) {
						if(shgmrpvo.getShgmno().equals(shgmvo.getShgmno()))
							set.add(shgmrpvo);
					}
				}
				
				
				request.setAttribute("searchRpResult", set);
				request.setAttribute("setRpsize", (long) set.size());
				
				String url = "/back-end/shgmrp/listAllShgmrp.jsp";
				RequestDispatcher successview = request.getRequestDispatcher(url);
				successview.forward(request, response);
			} catch (Exception e) {
				errormsgs.add("無法搜尋檢舉的市集商品" + e.getMessage());
				String url = "/back-end/shgmrp/listAllShgmrp.jsp";
				RequestDispatcher failedview = request.getRequestDispatcher(url);
				failedview.forward(request, response);
			}
		}

		if ("delete".equals(action)) {
			List<String> errormsgs = new LinkedList<String>();
			request.setAttribute("errormsgs", errormsgs);

			try {
				String shgmrpno = request.getParameter("shgmrpno");

				ShgmrpService shgmrpsvc = new ShgmrpService();
				shgmrpsvc.deleteShgmrp(shgmrpno);

				String url = "/back-end/shgmrp/listAllShgmrp.jsp";
				RequestDispatcher successview = request.getRequestDispatcher(url);
				successview.forward(request, response);
			} catch (Exception e) {
				errormsgs.add("無法刪除檢舉的市集商品" + e.getMessage());
				String url = "/back-end/shgmrp/listAllShgmrp.jsp";
				RequestDispatcher failedview = request.getRequestDispatcher(url);
				failedview.forward(request, response);
			}
		}
	}
}
