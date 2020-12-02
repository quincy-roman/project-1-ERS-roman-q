package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.model.Reimbursement;
import com.revature.model.dto.ReimbursementDTO;
import com.revature.service.ReimbursementService;

/**
 * Servlet implementation class getRequestsServlet
 */
public class getRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		response.setContentType("application/json;charset=UTF-8");
		
		ReimbursementService rs = new ReimbursementService();
		List<Reimbursement> reimList = new ArrayList<>();
		int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		
		reimList = rs.getAll(1, user_id);
		
		//not good practice, should be in the service layer.
		List<ReimbursementDTO> reim = new ArrayList<>();
		
		for(Reimbursement r: reimList) {
			ReimbursementDTO rdto = new ReimbursementDTO();
			
			rdto.setAmount(r.getAmount());
			rdto.setAuthor_id(r.getAuthor().getUserId());
			rdto.setDescription(r.getDescription());
			rdto.setResolved(r.getResolved());
			rdto.setResolver_id(r.getAuthor().getUserId());
			rdto.setSubmitted(r.getSubmitted());
			
			reim.add(rdto);
		}
		
		try(PrintWriter out = response.getWriter()){
			String json = new Gson().toJson(reim);
			out.print(json);
			out.flush();
		}
	}

}
