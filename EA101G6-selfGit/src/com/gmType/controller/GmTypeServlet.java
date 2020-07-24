package com.gmType.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmType.model.GmTypeService;
import com.gmType.model.GmTypeVO;
import com.gmTypeDt.model.GmTypeDtService;


public class GmTypeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;CharSet=UTF-8");
		PrintWriter out=res.getWriter();
		String action=req.getParameter("action");
		String erroMsg ="";
		GmTypeService gmTypeSvc=new GmTypeService();
		GmTypeDtService gmTypeDtSvc=new GmTypeDtService();
		/**1.接收參數**/
		if("add".equals(action)) {
			String typeName=req.getParameter("typeName");
			String typeNameReg="^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(typeName.trim().length()==0||!typeName.matches(typeNameReg)) {
				erroMsg+=("請確認格式請勿有特殊字元或小於2字或大於10字");
			}
			
			if(erroMsg.trim().length()!=0){
				out.write(erroMsg);
				return;
			}
			
		/**2.開始新增資料**/
			GmTypeVO gmType=new GmTypeVO();
			gmType=gmTypeSvc.add(typeName);
		/**3.新增完成,準備轉交***/	
			out.write("{ \"msg\" : \"新增完成\" ,"
					+ " \"typeNo\" : \""+ gmType.getTypeNo() +"\" "
					+ ", \"typeName\" : \""+gmType.getTypeName()+"\" }");
			return;
		}
		
		if("delete".equals(action)) {
			//typeNo事由el得來的不是自己輸入
			String typeNo=req.getParameter("typeNo");
			if(typeNo.trim().length()==0) {
				erroMsg+=("發生錯誤請稍後在試");
			}
			
			if(erroMsg.trim().length()!=0){
				out.write(erroMsg);
				return;
			}
			
		/**2.開始刪除資料**/
			gmTypeDtSvc.deleteByTypeNo(typeNo);
			gmTypeSvc.delete(typeNo);
		/**3.新增完成,準備轉交***/	
			out.write("刪除完成");
			return;
		}
		
		
		
		
	}

}
