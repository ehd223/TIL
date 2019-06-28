package com.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.Biz;
import com.oreilly.servlet.MultipartRequest;
import com.product.ProductBiz;
import com.vo.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet({ "/ProductServlet", "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Biz<Integer, Product> biz = new ProductBiz();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        biz = new ProductBiz();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String next = "";
		
		if(cmd.equals("add")) {
			next = "product/add";
		}else if(cmd.equals("register")) {
			// img 등 multipart로 request가 온 것을 받을 때
			MultipartRequest multiRequest = new MultipartRequest(request, "C:\\web\\day1333\\web\\img\\product", 1024*1024*100, "UTF-8") ;
			String name = multiRequest.getParameter("name");
			double price= Double.parseDouble(multiRequest.getParameter("price"));
			String imgName = multiRequest.getOriginalFileName("imgname");
			
			try {
				biz.register(new Product(name, price, imgName));
				response.sendRedirect("req?type=product&cmd=list");
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("list")) {
			ArrayList<Product> list = null;
			
			try {
				list = biz.get();
				request.setAttribute("plist", list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			next = "product/list";
		}else if(cmd.equals("detail")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Product product = null;
			
			try {
				product = biz.get(id);
				request.setAttribute("pd", product);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			next = "product/detail";
		}else if(cmd.equals("modiform")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Product product = null;
			
			try {
				product = biz.get(id);
				request.setAttribute("pu", product);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			next = "product/modiform";
		}else if(cmd.equals("modify")) {
			MultipartRequest multiRequest = new MultipartRequest(request, "C:\\web\\day1333\\web\\img\\product", 1024*1024*100, "UTF-8") ;
			int id = Integer.parseInt(multiRequest.getParameter("id"));
			String name = multiRequest.getParameter("name");
			double price= Double.parseDouble(multiRequest.getParameter("price"));
			String imgName = multiRequest.getOriginalFileName("imgname");
			
			Product product = new Product(id, name, price, imgName);
			
			try {
				biz.modify(product);
				response.sendRedirect("req?type=product&cmd=detail&id="+id);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				biz.remove(id);
				response.sendRedirect("req?type=product&cmd=list");
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next+".jsp");
		rd.forward(request, response);
	}

}
