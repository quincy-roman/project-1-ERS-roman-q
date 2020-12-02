package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;
import com.revature.model.Users;
import com.revature.service.ReimbursementService;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		//set the reimbursement object.
		Reimbursement reim = new Reimbursement();
		ReimbursementType type = new ReimbursementType();
		Users user = new Users();
		
		reim.setAmount(Double.parseDouble(request.getParameter("amount")));
		reim.setDescription(request.getParameter("description"));
		reim.setSubmitted();
		
		user.setUserId(Integer.parseInt(session.getAttribute("user_id").toString()));
		reim.setAuthor(user);
		
		type.setTypeId(Integer.parseInt(request.getParameter("type")));
		switch(type.getTypeId()) {
			case 1:
				type.setTypeName("Lodging");
				break;
			case 2:
				type.setTypeName("Travel");
				break;
			case 3:
				type.setTypeName("Food");
				break;
			case 4:
				type.setTypeName("Other");
				break;
		}
		reim.setType(type);
		
		ReimbursementService rs = new ReimbursementService();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		if(rs.insert(reim)) {
			pw.append("    <div class=\"col-lg-10\">\r\n"
					+ "        <div class=\"card-body\">\r\n"
					+ "            <h6>Successfully submitted request.</h6>\r\n"
					+ "        </div>\r\n"
					+ "    </div>");
		}else {
			pw.append("    <div class=\"col-lg-10\">\r\n"
					+ "        <div class=\"card-body\">\r\n"
					+ "            <h6>Request failed to send. Please try again later.</h6>\r\n"
					+ "        </div>\r\n"
					+ "    </div>");
		}
		
	}

}
