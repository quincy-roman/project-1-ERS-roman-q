package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.dto.ReimbursementDTO;

public class ReimbursementService {
	
	private static Logger log = Logger.getLogger(ReimbursementService.class);
	private ReimbursementDao rd = new ReimbursementDao();
	
	public boolean insert(Reimbursement reim) {
		try {
			
			ReimbursementStatus status = new ReimbursementStatus(1, "Pending");
			reim.setStatus(status);
			
			rd.insert(reim);
			
			//Return true provided it doesn't fail.
			return true;
		}catch(HibernateException e) {
			log.warn("Insert request failed.");
			//return false with failure.
			return false;
		}
	}
	
	public List<Reimbursement> getAll(int status_id, int user_id){
		
		List<Reimbursement> reimList = new ArrayList<>();
		
		reimList = rd.selectRequests(status_id, user_id);
		
		return reimList; 
	}
	
	public List<ReimbursementDTO> getAllManager(int status_id){
		
		List<Reimbursement> reimList = new ArrayList<>();
		List<ReimbursementDTO> reim = new ArrayList<>();
		
		reimList = rd.getAllRequests(status_id);
		
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
		
		return reim;
	}

}
