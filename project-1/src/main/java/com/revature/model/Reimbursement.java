package com.revature.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reimbursement")
public class Reimbursement {
	
	@Id
	@Column(name="reimbursement_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reimbursmentId;
	
	@Column(name="reimbursement_amount")
	private double amount;
	
	@Column(name="submitted")
	private Timestamp submitted;
	
	@Column(name="resolved")
	private Timestamp resolved;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_FK_Author", referencedColumnName="user_id")
	private Users author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_FK_Resolver", referencedColumnName="user_id")
	private Users resolver;	//I believe these are who makes and approves the request.

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Status_FK", referencedColumnName="status_id")
	private ReimbursementStatus status;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="Type_FK", referencedColumnName="type_id")
	private ReimbursementType type;
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(double amount, String submitted, String resolved, String description, Users author,
			Users resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		setSubmitted();
		setResolved();
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	
	public Reimbursement(int reimbursmentId, double amount, String submitted, String resolved, String description,
			Users author, Users resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.reimbursmentId = reimbursmentId;
		this.amount = amount;
		setSubmitted();
		setResolved();
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(double amount, String submitted, String description, Users author,
			ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		setSubmitted();
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public int getReimbursmentId() {
		return reimbursmentId;
	}

	public void setReimbursmentId(int reimbursmentId) {
		this.reimbursmentId = reimbursmentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted() {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		this.submitted = time;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved() {
//		if(resolved == null) {
//			this.resolved = null;
//		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		this.resolved = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public Users getResolver() {
		return resolver;
	}

	public void setResolver(Users resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public String toString() {	//TODO fix this later.
		return "Reimbursement [reimbursmentId=" + reimbursmentId + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", author=" + author.getUsername() +/* ", resolver="
				+ resolver.getUsername() + */", status=" + status.getStatusName() + ", type=" + type.getTypeName() + "]";
	}	
	
	
	
}
