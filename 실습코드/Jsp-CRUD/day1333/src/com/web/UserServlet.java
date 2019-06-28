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
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.user.UserBiz;
import com.vo.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({"/UserServlet", "/user"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Biz<String, User> biz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
    	biz = new UserBiz();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String next = "";
		
		if(cmd.equals("add")) {
			next = "user/add";
		}else if(cmd.equals("list")) {
			ArrayList<User> list = null;
			try {
				next = "user/list";
				list = biz.get();
				request.setAttribute("ulist", list);
			} catch (Exception e) {
				next = "user/rfail";
			}
		}else if(cmd.equals("register")) {
			next = "user/register";
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			
			try {
				biz.register(new User(id, pw, name));
				request.setAttribute("rid", id);
				
				next = "user/rok";
			} catch (Exception e) {
				next = "user/rfail";
			}
		}else if(cmd.equals("detail")) {
			String id = request.getParameter("id");
			User user = null;
			
			try {
				user = biz.get(id);
				request.setAttribute("ud", user);
				next = "user/detail";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("delete")) {
			String id = request.getParameter("id");
			
			try {
				biz.remove(id);
				response.sendRedirect("req?type=user&cmd=list");
				return;						
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("modiform")) {
			String id = request.getParameter("id");
			User user = null;
			
			try {
				user = biz.get(id);
				request.setAttribute("uu", user);
				next = "user/modiform";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("modify")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
						
			User user = new User(id, pw, name);
			System.out.println(user);
			try {
				biz.modify(user);
				response.sendRedirect("req?type=user&cmd=detail&id="+id);
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
