package com.mall.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.gmType.model.GmTypeVO;
import com.gmTypeDt.model.GmTypeDtService;
import com.gmTypeDt.model.GmTypeDtVO;
import com.mall.model.MallService;
import com.mall.model.MallVO;


@MultipartConfig
public class BackMallServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> erroMsg = new LinkedList<String>();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html ; Charset=UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		/*****************************************************/
		/**													**/
		/**						以下是新增              				**/
		/**													**/
		/**													**/
		/*****************************************************/
		if ("mallAdd".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				MallVO mallVo = new MallVO();
				List<String> tampTypeNolist = new ArrayList<String>();
				// commName部分
				String commName = req.getParameter("commName").trim();
				String commNameReg = "^[(\u4e00-\u9fa5) a-zA-Z0-9_]{1,20}$";
				if (commName.length() != 0 && commName.matches(commNameReg))
					mallVo.setCommName(commName);
				else
					erroMsg.add("商品名稱格式輸入錯誤，請輸入20字以內，請不要有特殊字元。");
				// price部分
				Integer price = null;
				try {
					price = Integer.parseInt(req.getParameter("price"));
					if (price > 0 && price < 1000000)
						mallVo.setPrice(price);
					else
						erroMsg.add("價錢請不要小於0或大於1000000元");
				} catch (NumberFormatException e) {
					erroMsg.add("價錢請輸入數字");
				}
				// quantity 部分
				Integer quantity = null;
				try {
					quantity = Integer.parseInt(req.getParameter("quantity"));
					if (quantity > 0 && quantity <= 100)
						mallVo.setQuantity(quantity);
					else
						erroMsg.add("數量請不要小於0或大於100");

				} catch (NumberFormatException e) {
					erroMsg.add("數量請輸入數字");
				}

				// intro部分
				String intro = req.getParameter("intro").trim();
				if (intro.length() != 0 && intro.length() <= 200)
					mallVo.setIntro(intro);
				else
					erroMsg.add("商品介紹請不要輸入小於0或大於200");

				// age部分
				String age = req.getParameter("age").trim();
				String ageReg = "[0-2]{0,1}[0-9]{1}";
				if (age.length() != 0 && age.matches(ageReg)) {
					mallVo.setAge(age);
				} else
					erroMsg.add("適合年齡請勿輸入小於0歲或大於29歲");

				// player部分
				String player = req.getParameter("player").trim();
				String playerRex = "^\\d{1,2}[-~]{1}\\d{1,2}$";
				if (player.length() != 0 && player.matches(playerRex)) {
					mallVo.setPlayer(player);
				} else
					erroMsg.add("建議人數格式請輸入正確");
				
				// status 有選中==on 沒=null
				int status = 0;
				if (!(req.getParameter("status") == null)) {
					status = 1;
				}
				mallVo.setStatus(status);

				// img
				byte[] img = null;
				Part part = req.getPart("img");
				String imgReg = ".*(.jpg|.png|.gif|.jpeg)$";
				if (part.getSize() != 0 && getFileNameFromPart(part).matches(imgReg)) {
					InputStream in = part.getInputStream();
					// 如果大於1MB
					if (in.available() < 2000000) {
						img = new byte[in.available()];
						in.read(img);
						mallVo.setImg(img);
					} else
						erroMsg.add("圖片請勿超過2MB");

					in.close();
				} else
					erroMsg.add("請傳入圖片或確認符合圖片格式");
				
				//gmtypedt 遊戲類型部分 checkbox不是自己輸入
					String[] typeNoArr=req.getParameterValues("typeNo");
					if(typeNoArr==null) {
						erroMsg.add("遊戲類型請至少勾選一個");
					}else
						for(int i=0;i<typeNoArr.length;i++) {
							tampTypeNolist.add(typeNoArr[i]);
						}
					
				// 如果錯誤訊息不等於空回到首頁
				if (!erroMsg.isEmpty()) {
					req.setAttribute("tempmallVo", mallVo);
					req.setAttribute("tampTypeNolist", tampTypeNolist);
					req.setAttribute("erroMsg", erroMsg);
					req.getRequestDispatcher("/back-end/mall/mallGetAll.jsp").forward(req, res);
					return;
				} else {
					/*************************** 2.開始新增mall的資料 ***************************************/
					MallService mallSvc = new MallService();
					MallVO addMall=mallSvc.add(commName, price, quantity, img, intro, age, player, status);
					/*************************** 2.開始新增gmtypedt的資料 ***************************************/
					/************************因為必須先拿到mall自增鍵才能新增所以放到後面 ******************************/
					GmTypeDtService GmTypeDtSvc=new GmTypeDtService();
					for(int i=0;i<typeNoArr.length;i++) {
						GmTypeDtSvc.add(typeNoArr[i], addMall.getCommNo());
					}
					/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					session.setAttribute("successMsg","新增成功");
					String whichPage="?whichPage="+req.getParameter("whichPage");
					res.sendRedirect(req.getContextPath()+"/back-end/mall/mallGetAll.jsp"+whichPage); 
					return;
				}


		}
		
		/*****************************************************/
		/**													**/
		/**						以下是修改               				**/
		/**													**/
		/**													**/
		/*****************************************************/
		
		if ("update".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				MallVO mallVo = new MallVO();
				MallService mallSvc=new MallService();
				//updateTampTypeNolist小心名稱不要重複會有bug
				List<GmTypeVO> updateTampTypeNolist = new ArrayList<GmTypeVO>();
				// commNo
				String commNo = req.getParameter("commNo");
				mallVo.setCommNo(commNo);
				// commName部分
				String commName = req.getParameter("commName").trim();
				String commNameReg = "^[(\u4e00-\u9fa5) _\\w]{1,20}$";
				if (commName.length() != 0 && commName.matches(commNameReg))
					mallVo.setCommName(commName);
				else
					erroMsg.add("商品名稱格式輸入錯誤，請輸入20字以內，請不要有特殊字元。");

				// price部分
				Integer price = null;
				try {
					price = Integer.parseInt(req.getParameter("price"));
					if (price > 0 && price < 1000000)
						mallVo.setPrice(price);
					else
						erroMsg.add("價錢請不要小於0或大於1000000元");
				} catch (NumberFormatException e) {
					erroMsg.add("價錢請輸入數字");
				}
				// quantity 部分
				Integer quantity = null;
				try {
					quantity = Integer.parseInt(req.getParameter("quantity"));
					if (quantity > 0 && quantity <= 100)
						mallVo.setQuantity(quantity);
					else
						erroMsg.add("數量請不要小於0或大於100");

				} catch (NumberFormatException e) {
					erroMsg.add("數量請輸入數字");
				}

				// intro部分
				String intro = req.getParameter("intro").trim();
				if (intro.length() != 0 && intro.length() <= 200)
					mallVo.setIntro(intro);
				else
					erroMsg.add("商品介紹請不要輸入小於0或大於200字");

				// age部分
				String age = req.getParameter("age").trim();
				String ageReg = "[0-2]{0,1}[0-9]{1}";
				if (age.length() != 0 && age.matches(ageReg)) {
					mallVo.setAge(age);
				} else
					erroMsg.add("適合年齡請勿輸入小於0歲或大於29歲");

				// player部分
				String player = req.getParameter("player").trim();
				String playerRex = "^\\d{1,2}[-~]{1}\\d{1,2}$";
				if (player.length() != 0 && player.matches(playerRex)) {
					mallVo.setPlayer(player);
				} else
					erroMsg.add("建議人數格式請輸入正確");

				// status 有選中==on 沒=null
				int status = 0;
				if (!(req.getParameter("status") == null)) {
					status = 1;
				}
				mallVo.setStatus(status);

				// img
				byte[] img = null;
				Part part = req.getPart("img");
				String imgReg = ".*(.jpg|.png|.gif|.jpeg)$";

				if (part.getSize() != 0) {

					if (getFileNameFromPart(part).matches(imgReg)) {
						InputStream in = part.getInputStream();
						// 如果大於1MB
						if (in.available() < 2000000) {
							img = new byte[in.available()];
							in.read(img);
							mallVo.setImg(img);
						} else
							erroMsg.add("圖片請勿超過2MB");

						in.close();
					} else
						erroMsg.add("確認符合圖片格式");

				} else {
					img = mallSvc.findOneByNo(commNo).getImg();
				}
				/**gmtypedt 遊戲類型部分 checkbox不是自己輸入，
				因為修改頁面的list用的是gmTypeVo，所以new一個gmTypeVo
				專門存gmTypeVo的no，好讓修改頁面輸入的值能站存並顯示**/
				String[] typeNoArr=req.getParameterValues("typeNo");
				if(typeNoArr==null) {
					erroMsg.add("遊戲類型請至少勾選一個");
				}else {
					for(int i=0;i<typeNoArr.length;i++) {
						GmTypeVO gmTypeVo = new GmTypeVO();
						gmTypeVo.setTypeNo(typeNoArr[i]);
						updateTampTypeNolist.add(gmTypeVo);
					}
				}
				// 如果錯誤訊息不等於空回到首頁
				if (!erroMsg.isEmpty()) {
					//可以拿到前一次輸入的值，並且傳到修改頁面並顯示
					req.setAttribute("updateMallVo", mallVo);
					req.setAttribute("updateTampTypeNolist", updateTampTypeNolist);
					req.setAttribute("updateerroMsg", erroMsg);
					if(req.getParameter("isGetOne").trim().length()!=0) {
						req.getRequestDispatcher("/back-end/mall/mallGetOne.jsp").forward(req, res);
						return;
					}else{
						req.getRequestDispatcher("/back-end/mall/mallGetAll.jsp").forward(req, res);
						return;
					}
					
				} else {
					/*************************** 2.開始修改資料 ***************************************/
						//mallSvc在最前面就宣告了
						MallVO updateMall=mallSvc.update(commNo,commName, price, quantity, img, intro, age, player, status);
					/*************************** 2.開始修改gmtypedt的資料 ***************************************/
					/************************因為必須先拿到mall自增鍵才能新增所以放到後面 ******************************/
						GmTypeDtService GmTypeDtSvc=new GmTypeDtService();
						GmTypeDtSvc.deleteByCommNo(commNo);
						for(int i=0;i<typeNoArr.length;i++) {
							GmTypeDtSvc.add(typeNoArr[i], commNo);
						}
					/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
					session.setAttribute("successMsg", "更新成功");
					String whichPage="?whichPage="+req.getParameter("whichPage");
					//確認他是哪個頁面傳的，是空字串就傳到getall，並把session.remove掉讓他更新，
					//如果不是把action="selectone"在搜尋一次
					if(req.getParameter("isGetOne").trim().length()!=0) {
						String selName=(String)session.getAttribute("selName");
						Set<MallVO> selNameMallVoSet = mallSvc.findByName(selName);
						session.setAttribute("selNameMallVoSet", selNameMallVoSet);
						res.sendRedirect(req.getContextPath()+"/back-end/mall/mallGetOne.jsp"+whichPage); 
						return;
					}else{
						res.sendRedirect(req.getContextPath()+"/back-end/mall/mallGetAll.jsp"+whichPage); 
						return;
					}
					
				}
			} catch (Exception e) {
				e.getStackTrace();
				req.setAttribute("showupdate", "showupdate");
				req.setAttribute("updateerroMsg", "目前系統忙碌中，請稍後!");
				req.getRequestDispatcher("/back-end/mall/mallGetAll.jsp").forward(req, res);
				return;
			}


		}
		
		/*****************************************************/
		/**													**/
		/**						查詢單筆          					**/
		/**													**/
		/**													**/
		/*****************************************************/
		if ("selectone".equals(action)) {
			try {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				MallService mallSvc = new MallService();
				String selErroMsg="";
				String selName = req.getParameter("selName").trim();
				session.setAttribute("selName", selName);
				String selNameReg = "^[(\u4e00-\u9fa5) _\\w]{1,20}$";
				Set<MallVO> selNameMallVoSet=null;
				if (selName.length() != 0 && selName.matches(selNameReg)){
					selNameMallVoSet = mallSvc.findByName(selName);
				}else {
					selErroMsg="商品名稱格式輸入錯誤，請輸入20字以內，請不要有特殊字元。";
					req.setAttribute("selErroMsg",selErroMsg);
					req.getRequestDispatcher("/back-end/mall/mallGetAll.jsp").forward(req, res);
					return;
				}
		/*************************** 2.查詢完成,準備轉交(Send the Success view) ***********/	
				if(selNameMallVoSet.isEmpty()) {
					selErroMsg="查無此資料";
					req.setAttribute("selErroMsg",selErroMsg);
					req.getRequestDispatcher("/back-end/mall/mallGetAll.jsp").forward(req, res);
					return;
				}else {
					session.setAttribute("selNameMallVoSet", selNameMallVoSet);
					req.getRequestDispatcher("/back-end/mall/mallGetOne.jsp").forward(req, res);
					return;
				}
				
			}catch (Exception e) {
				e.getStackTrace();
				res.sendRedirect(req.getContextPath() + "/back-end/mall/mallGetAll.jsp");
				return;
			}	
				
			
		}
		
		
	}

	// 得到檔案名子
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
