package com.revature.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.utilities.HibernateUtil;

public class ReimbursementDao {
	
	public static Logger log = Logger.getLogger(ReimbursementDao.class);
	
	//Insert a new request.
	public void insert(Reimbursement reim) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(reim);
		tx.commit();
	}
	
	//Update a request.
	public void update(Reimbursement reim) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.update(reim);
		tx.commit();
	}
	
	//Get a request by id.
	public Reimbursement selectById(int id) {
		
		Session session = HibernateUtil.getSession();
		Reimbursement reim = session.get(Reimbursement.class, id);
		
		return reim;
		
	}
	
	//Get requests by their status and user. Meant for employees or by employee search.
	public List<Reimbursement> selectRequests(int status_id, int id){
		
		Session session = HibernateUtil.getSession();
		List<Reimbursement> pending = null;
		
//		pending = session.createQuery("from reimbursement where status_fk=1 and user_fk_author="+id, Reimbursement.class).list(); This didn't work.
		
		//While being much wordier, Hibernate 5's Criteria worked. 
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reimbursement> query = builder.createQuery(Reimbursement.class);
		
		Root<Reimbursement> root = query.from(Reimbursement.class);
		Path<Integer> statusFK = root.get("status");	//make sure it's the variable name, not the column name.
		Path<Integer> userFK = root.get("author");
		
		Predicate statusFKPredicate = builder.equal(statusFK, status_id);
		Predicate userFKPredicate = builder.equal(userFK, id);
		
		Predicate predicate = builder.and(statusFKPredicate, userFKPredicate);
		query.select(root).where(predicate);
		
		pending = session.createQuery(query).getResultList();
		
		if(pending.size() == 0) {
			log.info("There are no pending requests.");
			return null;
		}
		
		return pending;
	}
	
	//Get all requests by their status, meant for managers.
	public List<Reimbursement> getAllRequests(int status_id){
		
		Session session = HibernateUtil.getSession();
		List<Reimbursement> requestList = new ArrayList<>();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reimbursement> query = builder.createQuery(Reimbursement.class);
		
		Root<Reimbursement> root = query.from(Reimbursement.class);
		Path<Integer> statusFK = root.get("status");
		
		Predicate statusFKPredicate = builder.equal(statusFK, status_id);
		
		query.select(root).where(statusFKPredicate);
		
		requestList = session.createQuery(query).getResultList();
		
		if(requestList.size() == 0) {
			log.info("There are no pending requests.");
			return null;
		}
		
		return requestList;
	}

}
