package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.*;


//@WebServlet("/EmpImgServlet")
public class EmpImgServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);	
	}
    
    //因為這支程式是要用網址列餵食請求方式
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");//setContentType() 方法必須在任何回應確認前執行
		
		/*
		 	直接對瀏覽器進行位元組輸出
		  	HttpServletResponse中有一個方法：getOutputStream() 方法，可以取得 ServletOutputStream
		  	ServletOutputStream 是 OutputStream 的子類別
		  	透過 write() 將要輸出的檔案送到指定的地點(以此為例：寫到欄位裡面)
		 */
		
		ServletOutputStream out = res.getOutputStream();
														
		try {
			//法一：此方法是不放進session，可以立即看到圖片的修改
			String empno = req.getParameter("empno");
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(empno);
			out.write(empVO.getPic());
	
		}catch(Exception ne) {//沒有給參數 ID = 多少
			InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
			//在Servlet的情況下，因為out作為出口，所以out不能close
		}	
	}
}



//法二：			
//將讀到的圖片放在session，如果有get到就讀session內圖，如果get不到才去從資料庫讀出來
// //但要注意的是，這樣修改圖片第一時間不會顯示
//HttpSession session = req.getSession();
//String empno = req.getParameter("empno");
//if(session.getAttribute(empno) == null) {
//	EmpService empSvc = new EmpService();
//	EmpVO empVO = empSvc.getOneEmp(empno);
//	out.write(empVO.getPic());
//	session.setAttribute(empno, (Object)empVO);
//}else{
//	EmpVO empVO = (EmpVO) session.getAttribute(empno);
//	out.write(empVO.getPic());
//}
