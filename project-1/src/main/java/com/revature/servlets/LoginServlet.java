package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Users;
import com.revature.service.LoginService;

import jdk.internal.org.jline.utils.Log;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		
		LoginService login = new LoginService();
		
		Users user = new Users();
		
		user.setUsername(request.getParameter("username"));
		String password = request.getParameter("password");
		
		user = login.validateUser(user, password);
		
		if(user != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("user_id", user.getUserId());
			session.setAttribute("problem", null);
			
			if(user.getRole().getRoleId()==1) {
				response.sendRedirect("employeeDash");
			}else if(user.getRole().getRoleId()==2){
				response.sendRedirect("manager.html");
			}
		}else {
			session.setAttribute("problem", "incorrect username or password");
			response.sendRedirect("login.html");
		}
		
		
	}

}
