package com.game.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.shop.model.ShopService;
import com.shop.model.ShopVO;
import com.game.model.*;
import com.gmlist.model.GmlistService;
import com.gmlist.model.GmlistVO;


@MultipartConfig
public class GameServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String gmno = req.getParameter("gmno");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				GameService gameSvc = new GameService();
				GameVO gameVO = gameSvc.getOneGame(gmno);
				if (gameVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("gameVO", gameVO); // ��Ʈw���X��shopVO����,�s�Jreq
				String url = "listOneGame.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneshop.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("輸入有錯:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getSome_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String gmname = req.getParameter("gmname").trim();

				GameService gameSvc = new GameService();
				List<GameVO> gameVO = gameSvc.getSomeGames(gmname);
				if (gameVO == null||req.getParameter("gmname").trim()==null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(333);
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				System.out.println(123);
				req.setAttribute("gameVO", gameVO); // ��Ʈw���X��shopVO����,�s�Jreq
				String url = "listSomeGame.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneshop.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("有錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllshop.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String gmno = req.getParameter("gmno");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				GameService gameSvc = new GameService();
				GameVO gameVO = gameSvc.getOneGame(gmno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("gameVO", gameVO); // ��Ʈw���X��shopVO����,�s�Jreq
				String url = "update_game_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_shop_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("有錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_shop_input.jsp���ШD
System.out.println("1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String gmno = req.getParameter("gmno");
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("2");
				String gmname = req.getParameter("gmname");
				byte[] gmimg = null;

				Part part;
				part = req.getPart("gmimg");
				System.out.println("3");
				InputStream in = null;
				try {
					if(part.getSize() == 0) {
						GameService gameSvc = new GameService();
						GameVO gameVo = gameSvc.getOneGame(gmno);
						gmimg = gameVo.getGmimg();
						in = part.getInputStream();
						in.read(gmimg);
					}else {
						in = part.getInputStream();					
						gmimg = new byte[in.available()];				
						in.read(gmimg);		
					}						
				}catch (IOException e) {
					errorMsgs.add("上傳失敗");
					in = getServletContext().getResourceAsStream("/NoData/null.jpg");
					gmimg = new byte[in.available()];
	          		in.read(gmimg);
				} finally {
					in.close();
				}

				System.out.println("123");
				GameVO gameVO = new GameVO();
				gameVO.setGmno(gmno);
				gameVO.setGmname(gmname);
				gameVO.setGmimg(gmimg);
				System.out.println("23");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("3");
					req.setAttribute("gameVO", gameVO); // �t����J�榡���~��shopVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				System.out.println("123");
				/*************************** 2.�}�l�ק��� *****************************************/
				GameService gameSvc = new GameService();
				gameVO = gameSvc.updateGame(gmno, gmname, gmimg);
				System.out.println("1");
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("gameVO", gameVO); // ��Ʈwupdate���\��,���T����shopVO����,�s�Jreq
				String url = requestURL;
				System.out.println(requestURL);
				if("/front-end/game/update_game_input.jsp".equals(requestURL)) {
					url="/front-end/game/listOneGame.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("successMsgs", "修改成功!");
				successView.forward(req, res);
				System.out.println("1");
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllGame.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addshop.jsp���ШD
			System.out.println("test");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String gmname = req.getParameter("gmname");
				if(req.getParameter("gmname")==null||req.getParameter("gmname").trim().length()==0) {
					gmname="";
					errorMsgs.add("遊戲名稱不得為空");
				}
				byte[] gmimg = null;
				Part part = req.getPart("gmimg");
				InputStream in = null;
				if(part.getSize()==0) {
					errorMsgs.add("請選擇圖片");
				}else {
					try {
						in = part.getInputStream();
						gmimg = new byte[in.available()];
						in.read(gmimg);
					} catch (IOException e) {
						e.getMessage();
					} finally {
						in.close();
					}
				}
				

				GameVO gameVO = new GameVO();
				gameVO.setGmname(gmname);
				gameVO.setGmimg(gmimg);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gameVO", gameVO); // �t����J�榡���~��shopVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gmlist/addGmlist.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				GameService gameSvc = new GameService();			
				ShopVO shopVO = (ShopVO)session.getAttribute("shopAcount");
				gameSvc.addGame(gmname, gmimg, shopVO);

				
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/gmlist/addGmlist.jsp";
				req.setAttribute("successMsgs", "新增成功!");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllshop.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addGame.jsp");
				failureView.forward(req, res);
			}
		}
	
	}

}
