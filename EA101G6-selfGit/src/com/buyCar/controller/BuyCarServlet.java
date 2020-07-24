package com.buyCar.controller;

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
import javax.servlet.http.HttpSession;

import com.mall.model.MallService;
import com.mall.model.MallVO;


public class BuyCarServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;CharSet=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		
		
		if ("buyOne".equals(action)) {
			try {
				String commNo = req.getParameter("commNo");
				String commName = req.getParameter("commName");
				Integer buyQuantity = new Integer(req.getParameter("buyQuantity"));
				Integer buyPrice = new Integer(req.getParameter("buyPrice"));

				// 商品的物件看庫存夠嗎
				MallService mallSvc = new MallService();
				MallVO mallVo = mallSvc.findOneByNo(commNo);
				// new物件
				MallVO buyCarMall = new MallVO();
				buyCarMall.setCommNo(commNo);
				buyCarMall.setCommName(commName);
				buyCarMall.setQuantity(buyQuantity);
				buyCarMall.setPrice(buyPrice);

				List<MallVO> buyCarList = null;
				// 如果session有list就拿出否則new 1個
				if (session.getAttribute("buyCarList") == null) {
					buyCarList = new LinkedList<MallVO>();
					session.setAttribute("buyCarList", buyCarList);
				} else {
					buyCarList = (LinkedList<MallVO>) session.getAttribute("buyCarList");
				}
				// 確認list有沒有那個商品沒有就新增，有的話因為是購買所以數量改成購買的數量並加入購物車
				int index = buyCarList.indexOf(buyCarMall);
				if (index < 0) {
					buyCarList.add(buyCarMall);
				} else {
					buyCarMall = buyCarList.get(index);
					Integer tempquantity = Integer.valueOf(buyQuantity);
					buyCarMall.setQuantity(tempquantity);
				}
				Integer totalPrice=buyCarList.stream()
						.mapToInt(p -> p.getPrice()*p.getQuantity())
						.sum();
				session.setAttribute("totalPrice", totalPrice);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/mallOr/mallOr.jsp");
				dispatcher.forward(req, res);
				return;

			} catch (Exception e) {
				out.print("新增失敗!請稍後在試");
			}
			
		}
		
		if ("add".equals(action)) {

			try {
				String commNo = req.getParameter("commNo");
				String commName = req.getParameter("commName");
				Integer buyQuantity = new Integer(req.getParameter("buyQuantity"));
				Integer buyPrice = new Integer(req.getParameter("buyPrice"));

				// 商品的物件看庫存夠嗎
				MallService mallSvc = new MallService();
				MallVO mallVo = mallSvc.findOneByNo(commNo);
				// new物件
				MallVO buyCarMall = new MallVO();
				buyCarMall.setCommNo(commNo);
				buyCarMall.setCommName(commName);
				buyCarMall.setQuantity(buyQuantity);
				buyCarMall.setPrice(buyPrice);

				List<MallVO> buyCarList = null;
				// 如果session有list就拿出否則new 1個
				if (session.getAttribute("buyCarList") == null) {
					buyCarList = new LinkedList<MallVO>();
					session.setAttribute("buyCarList", buyCarList);
				} else {
					buyCarList = (LinkedList<MallVO>) session.getAttribute("buyCarList");
				}
				// 確認list有沒有那個商品沒有就新增，有就拿出原本數量並+-
				int index = buyCarList.indexOf(buyCarMall);
				if (index < 0) {
					buyCarList.add(buyCarMall);
					out.print("新增成功");
				} else {
					buyCarMall = buyCarList.get(index);
					Integer tempquantity = Integer.valueOf(buyQuantity) + Integer.valueOf(buyCarMall.getQuantity());
					if (mallVo.getQuantity() >= tempquantity) {
						buyCarMall.setQuantity(tempquantity);
						out.print("新增成功");
					} else {
						out.print("購物車數量超過庫存");
					}
				}

			} catch (Exception e) {
				out.print("新增失敗!請稍後在試");
			}

		}

		if ("delete".equals(action)) {
			try {

				String commNo = req.getParameter("commNo");
				String commName = req.getParameter("commName");
				Integer buyQuantity = new Integer(req.getParameter("buyQuantity"));
				Integer buyPrice = new Integer(req.getParameter("buyPrice"));

				MallVO buyCarMall = new MallVO();
				buyCarMall.setCommNo(commNo);
				buyCarMall.setCommName(commName);
				buyCarMall.setQuantity(buyQuantity);
				buyCarMall.setPrice(buyPrice);

				List<MallVO> buyCarList = null;

				if (session.getAttribute("buyCarList") != null) {
					buyCarList = (LinkedList<MallVO>) session.getAttribute("buyCarList");
					buyCarList.remove(buyCarMall);
					out.print("移除成功");
				}
			} catch (Exception e) {
				out.print("移除失敗!請稍後在試");
			}

		}

		if ("update".equals(action)) {

			try {
				String commNo = req.getParameter("commNo");
				String commName = req.getParameter("commName");
				Integer buyQuantity = new Integer(req.getParameter("buyQuantity"));
				Integer buyPrice = new Integer(req.getParameter("buyPrice"));
				// new物件
				MallVO buyCarMall = new MallVO();
				buyCarMall.setCommNo(commNo);
				buyCarMall.setCommName(commName);
				buyCarMall.setQuantity(buyQuantity);
				buyCarMall.setPrice(buyPrice);

				List<MallVO> buyCarList = null;
				// 如果session有list就拿出
				if (session.getAttribute("buyCarList") != null) {
					buyCarList = (LinkedList<MallVO>) session.getAttribute("buyCarList");
				}
				// 確認物件在哪個index，然後拿出更改
				int index = buyCarList.indexOf(buyCarMall);
				buyCarMall = buyCarList.get(index);
				buyCarMall.setQuantity(buyQuantity);

				out.print("修改成功");
			} catch (Exception e) {
				out.print("修改失敗!請稍後在試");
			}

		}

	}

}
