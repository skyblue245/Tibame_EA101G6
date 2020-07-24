package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.authority.model.*;
import com.emp.model.*;

//@WebServlet("/EmpServlet")此為@annotation註解 Servlet3.0 開始可以用的註冊方式
@MultipartConfig()//如果有檔案的傳送，一定要加這條
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpServlet() {
        super();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)){// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>(); //LinkedList ： 適合用於資料插拔
			req.setAttribute("errorMsgs", errorMsgs);		   //ArrayList  ： 適合用於從後面加資料
															   //Vector     ：有鎖定的功能，較安全，但速度也因此較慢
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String empno = req.getParameter("empno");
				String empnoReg = "^LE[0-9]{5}$";
				
				if(empno == null || (empno.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}else if(!empno.trim().matches(empnoReg)) {
					errorMsgs.add("員工編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {//如果錯誤訊息不是空的，代表有錯誤，回到select_page的畫面
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failView.forward(req, res);
					return;//程式中斷，因為下面沒有寫else，if做完會繼續往下做，預防他往下做這邊要用return
					       //如果下面有寫else包起來，就可以不用寫return，但因為這樣要包一大包，所以不建議這樣做
				}
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
				if(empVO == null) {
					errorMsgs.add("查無此員工");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO);// 資料庫取出的empVO物件,存入req，轉交後的頁面會getAttribute取得empVO，做後續動作
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/	
			}catch(Exception e){
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failView.forward(req, res);
			}
			
		}
		
		if("getOneName_For_Display".equals(action)){// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>(); 
			req.setAttribute("errorMsgs", errorMsgs);		   
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String empname = req.getParameter("empname");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				if(empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}else if(!empname.trim().matches(enameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failView.forward(req, res);
					return;
				}
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmpForName(empname);
				if(empVO == null) {
					errorMsgs.add("查無此員工");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO);// 資料庫取出的empVO物件,存入req，轉交後的頁面會getAttribute取得empVO，做後續動作
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/	
			}catch(Exception e){
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failView.forward(req, res);
			}
			
		}
		
		if("getOne_For_Update".equals(action)) {// 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String empno = req.getParameter("empno");
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
				//將我們要修改的員工，藉由getOneEmp方法傳入empno，取得要修改的員工物件
				
				//將該員工所擁有的權限setAttribute
				List<String> empAuthority = empSvc.getAuthorityByEmpno(empno);
				req.setAttribute("empAuthority", empAuthority);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				//將我們要修改的員工存入req(req.setAttribute("empVO", empVO);)，轉交給 update_emp_input.jsp
				//讓那個jsp有辦法將原有的員工資料顯示出來
				req.setAttribute("empVO", empVO);
				
				String url = "/back-end/emp/update_emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {// 來自update_emp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			List<String> empAuthority = new ArrayList<String>();
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String empno = req.getParameter("empno");
				
				//沒有讀圖片的方式：
				//byte[] pic = (req.getParameter("pic")).getBytes();//將字串用getBytes()的方法，得到byte[]
				
				byte[] pic;
				Part picPart = req.getPart("pic");
				InputStream in = picPart.getInputStream();
				if(in.available() > 0) {//如果有傳進新圖片
					pic = new byte[in.available()];
					in.read(pic);//就將新圖片讀進資料庫
				}else {//如果沒有要傳新的圖片進去
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.getOneEmp(empno);//用傳入empno找到單一員工的方法，取出員工物件
					pic = empVO.getPic();//用getPic()取出 員工物件原本的照片再放進去一次
				}
				in.close();
				
				String empname = req.getParameter("empname");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}else if(!empname.trim().matches(enameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String mail = req.getParameter("mail");
				String mailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";//\w{1,63}的意思等於[a-zA-Z0-9_]{1,63}，就是允許大小寫字母，數字和底線，至少1到63個字
				if(mail == null || mail.trim().length() == 0) {                                       //[a-zA-Z0-9]{2,63}的意思是允許大小寫字母和數字，至少2到63個字
					errorMsgs.add("信箱: 請勿空白");                                                   //(\.[a-zA-Z]{2,63})?表示一個.後接至少2到63個大小寫字母
				}else if(!mail.trim().matches(mailReg)) {											  //?的意思表示括弧內的規則可以存在0個或1個
					errorMsgs.add("信箱: 請輸入正確的信箱格式");
				}
				
				String sex = req.getParameter("sex");
				if(sex == null) {
					errorMsgs.add("性別: 請選擇性別");
				}
				
				Integer empstatus = new Integer(req.getParameter("empstatus"));
				
				String[] ftno = req.getParameterValues("features");

				for(String ftnoStr :ftno) {
					empAuthority.add(ftnoStr);
				}
				
				req.setAttribute("empAuthority",empAuthority);
				
				EmpVO empVO = new EmpVO();
				empVO.setEmpno(empno);
				empVO.setEmpname(empname);
				empVO.setMail(mail);
				empVO.setSex(sex);
				empVO.setEmpstatus(empstatus);
				
				if(!errorMsgs.isEmpty()) {
					System.out.println("emter here");
					req.setAttribute("empVO", empVO); //將含有錯誤格式的物件empVO存入req
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/update_emp.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmpSTAT(pic, empname, mail, sex, empstatus, empno, ftno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗：" + e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/update_emp.jsp");
				failView.forward(req, res);
			}	
		}
		
		if("insert".equals(action)) {// 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empname = req.getParameter("empname");
				String empnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}else if(!empname.trim().matches(empnameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String mail = req.getParameter("mail");
				String mailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if(mail == null || mail.trim().length() == 0) {
					errorMsgs.add("信箱: 請勿空白");
				}
				if(!mail.trim().matches(mailReg)) {
					errorMsgs.add("信箱: 請輸入正確的信箱格式");
				}
				
				String sex = req.getParameter("sex");
				if(sex == null) {
					errorMsgs.add("性別: 請選擇性別");
				}
				
				Integer empstatus = new Integer(req.getParameter("empstatus"));
				
				String[] ftno = req.getParameterValues("features");
				req.setAttribute("empftno", ftno);
				
//以下確定有取到
//				for(String ftname : ftno) {
//					System.out.println(ftname);
//				}
				
				//送圖片讀進資料庫					
				Part picPart = req.getPart("pic");	//透過 HttpServletRequest 的 getPart() 取得 Part 物件
													//此處的pic即為我們於頁面上要上傳的檔案(pic為資料庫的欄位名)
													//Part有一個方法為getInputStream()，可以取得InputStream
													//new一個長度為檔案大小的byte[]
				InputStream in=null;
				byte[] pic=null;
				if(picPart.getSize() == 0) {
					in = getServletContext().getResourceAsStream("/NoData/EmpPic.jpg");
					pic = new byte[in.available()];
					
				}else {
					 in= picPart.getInputStream();
					 pic= new byte[in.available()];
				}
				
				in.read(pic);//將byte[]讀進資料庫
				in.close();
				
				/*
				 	Part類別：Servlet處理"檔案"的類別，只有Servlet才有，Java沒有，所以得透過InputStream，並將檔案變成byte[]
				 	可以透過 HttpServletRequest 的 getPart() 取得 Part 物件
				 	getPart()中得輸入字串，字串為：檔案上傳欄位的 name 屬性
				 	Part 有一個方法為 getInputStream()，可以取得InputStream
				 	InputStream有一個方法 invailable()，可以取得輸入資料流的大小
				 */
				
				EmpVO empVO = new EmpVO();		
				empVO.setEmpname(empname);
				empVO.setMail(mail);
				empVO.setSex(sex);
				empVO.setEmpstatus(empstatus);
				empVO.setPic(pic);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(pic, empname, mail, sex, empstatus, ftno);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failView.forward(req, res);
			}
		}
		
		if("tryLogin".equals(action)) {// 來自loginBack.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String accountBack = req.getParameter("accountBack");
				String password = req.getParameter("password");
				req.setAttribute("accountBack", accountBack);
				req.setAttribute("password", password);
				
				if(accountBack == null || accountBack.trim().length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				
				if(password == null || password.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/loginBack.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始驗證是否為員工***************************************/
					EmpService empSvc = new EmpService();//此處的accountBack為empno，如果可以透過這個empno取得員工物件，代表這accountBack確實為員工
					EmpVO eVO = empSvc.checkLogin(accountBack);//透過傳empno進去可以取得empVO物件的方法，取得要更改密的員工物件
					String emppwd = eVO.getEmppwd();
					if(password.equals(emppwd)) {//帳號正確，取出的密碼也和輸入的一樣
						HttpSession session =req.getSession();
						session.setAttribute("accountBack", accountBack);//將帳號存進session，之後可以藉由這個取得他所擁有的權限
						session.setAttribute("eVO", eVO);//將登入的員工物件存進session						
						List<String> authList = empSvc.getAuthorityByEmpno(accountBack);
						session.setAttribute("authList", authList);
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/
						try {//查看是否有來源網頁
							String location = (String)session.getAttribute("backLocation");
							if(location != null) {//如果有來源網頁
								session.removeAttribute("backLocation");
								res.sendRedirect(location);//重導至該網頁
								return;
							}
						}catch(Exception e) {
							res.sendRedirect(req.getContextPath()+"/back-end/index.jsp");
							return;
						}
						//沒有來源網頁的話就去首頁
						res.sendRedirect(req.getContextPath()+"/back-end/index.jsp");
						return;
					}else {
						errorMsgs.add("帳號、密碼錯誤");
						RequestDispatcher failView = req.getRequestDispatcher("/loginBack.jsp");
						failView.forward(req, res);
					}
					
					/***************************其他可能的錯誤處理**********************************/
				}catch(Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/loginBack.jsp");
					failureView.forward(req, res);
				}
		}
		
		if("changePwd".equals(action)) {// 來自updatePwd.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session = req.getSession();
				String empno = (String) session.getAttribute("accountBack");//將登入時setAttribute的accountBack取出，accountBack為empno
				
				/***************************2.開始查詢員工****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);//透過傳empno進去可以取得empVO物件的方法，取得要更改密的員工物件
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);
				String url = "/back-end/emp/updatePwd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				String url = "/loginBack.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if("updatePwd".equals(action)) {// 來自updatePwd.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			EmpService empSvc = new EmpService();
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empno = req.getParameter("empno");
				String emppwd = req.getParameter("emppwd");
				String chkpwd = req.getParameter("chkpwd");
				
				EmpVO empVO = empSvc.getOneEmp(empno);//利用傳empno取的empVO的方法，取得該empVO員工物件
				req.setAttribute("empVO", empVO);
				//一開始forward到updatePwd時，有將398行req.setAttribute帶過去，
				//但updatePwd按下修改，回到controller時，因為不是forward，所以req中的Attribute傳不過來
				//當頁面有錯誤訊息時，要又forward回到updatePwd頁面，就會因為沒有req中的Attribute，而在上方取出empVO物件時，出現NullPointerException
				//所以這裡才再【req.setAttribute("empVO", empVO);】一次
				
				if(emppwd == null || (emppwd.trim()).length() == 0) {
					errorMsgs.add("密碼不可為空");
				}
				
				if(chkpwd == null || (chkpwd.trim()).length() == 0) {
					errorMsgs.add("確認密碼不可為空");
				}
				
				if(!emppwd.equals(chkpwd)) {
					errorMsgs.add("密碼與確認密碼不相符");
				}
					
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/emp/updatePwd.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				empSvc.updateEmpPwd(emppwd, empno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ok",true);
				String url = "/loginBack.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交loginBack.jsp，重新登入
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("更新密碼失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/updatePwd.jsp");
				failureView.forward(req, res);
			}
		}
				
		if("forget".equals(action)) {	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empno = req.getParameter("empno");
				String mail = req.getParameter("mail");
				req.setAttribute("mail", mail);
				req.setAttribute("empno", empno);
				
				if(empno == null || empno.trim().length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				
				if(mail == null || mail.trim().length() == 0) {
					errorMsgs.add("請輸入設定的信箱");
				}
				
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmpForMail(mail,empno);//透過信箱和員工編號取得相對應得員工物件
				String empEmpno =  empVO.getEmpno();
				String empMail = empVO.getMail();
				
				if(!empEmpno.equals(empno)) {
					errorMsgs.add("查無此員工編號");
				}
				
				if(!empMail.equals(mail)) {
					errorMsgs.add("信箱不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = req.getRequestDispatcher("/forgetPwd.jsp");
					failView.forward(req, res);
					return;
				}
				
				/***************************2.開始取得新密碼並寄送信件*****************************************/
				String newPwd = empSvc.getNewPwd(mail,empno);//透過信箱更改密碼
				EmpMailService empMailSvc = new EmpMailService(empVO, mail, newPwd);//將寄信改成用執行緒去跑，畫面會比較快顯示出來
				empMailSvc.start();//將上面取出的員工物件和信箱，跟上面取得的新密碼傳給empMailSvc，用start()呼叫執行緒的run()啟動
				req.setAttribute("pwdok", true);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/loginBack.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交loginBack.jsp，重新登入
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("取得新密碼失敗");
				RequestDispatcher failView = req.getRequestDispatcher("/forgetPwd.jsp");
				failView.forward(req, res);
			}		
		}
		
		if("logout".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);
				/***************************1.登出*****************************************/
			try {
				HttpSession session = req.getSession();
				session.removeAttribute("accountBack");
				/***************************2.登出成功,準備轉交(Send the Success view)*************/
				successMsgs.add("帳號已登出");
				RequestDispatcher failView = req.getRequestDispatcher("/loginBack.jsp");
				failView.forward(req, res);
				return;
				/***************************其他可能的錯誤處理*************************************/	
			}catch(Exception e) {
				errorMsgs.add("登出失敗");
				RequestDispatcher failView = req.getRequestDispatcher("/loginBack.jsp");
				failView.forward(req, res);
			}	
		}
	}

}
