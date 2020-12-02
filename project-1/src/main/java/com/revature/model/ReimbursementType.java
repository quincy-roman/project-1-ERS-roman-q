package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reimbursement_type")
public class ReimbursementType {
	
	@Id
	@Column(name="type_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int typeId;
	
	@Column(name="type_name", unique=true, nullable=false)
	private String typeName;
	
	public ReimbursementType() {}

	public ReimbursementType(int typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	

}
