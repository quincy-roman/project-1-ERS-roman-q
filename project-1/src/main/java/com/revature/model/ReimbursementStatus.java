package com.revature.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="reimbursement_status")
public class ReimbursementStatus {
	
	@Id
	@Column(name="status_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int statusId;
	
	@Column(name="status_name", unique=true, nullable=false)
	private String statusName;
	
	//TODO, again this might be wrong.
	@OneToMany(mappedBy="status", fetch=FetchType.LAZY)
	private Set<Reimbursement> reimburse;
	
	public ReimbursementStatus() {}

	public ReimbursementStatus(int statusId, String statusName) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	

}