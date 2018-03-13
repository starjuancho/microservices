package com.globant.pruebas.policyservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CollectionStatus implements Entity{
	
	private String statusCode;
	private String descriotion;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getDescriotion() {
		return descriotion;
	}
	public void setDescriotion(String descriotion) {
		this.descriotion = descriotion;
	}

}
