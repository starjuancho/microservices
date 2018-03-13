package com.globant.pruebas.policyservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Policy implements Entity {

	private String policyNumber;
	private String userId;
	private String policyHolder; 
	private CollectionStatus collectionStatu;
	private Date validityStartDate;
	private Date validityEndDate;
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(String policyHolder) {
		this.policyHolder = policyHolder;
	}
	public CollectionStatus getCollectionStatu() {
		return collectionStatu;
	}
	public void setCollectionStatu(CollectionStatus collectionStatu) {
		this.collectionStatu = collectionStatu;
	}
	public Date getValidityStartDate() {
		return validityStartDate;
	}
	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}
	public Date getValidityEndDate() {
		return validityEndDate;
	}
	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}
	
	
}
