package com.globant.pruebas.profileservice.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@XmlRootElement
@Table("User")
public class User implements Entity {
	
	@PrimaryKey
	private String userId;	

	@Column
	private String username;

	@Column
	private String idStatus;

	public String getUserID() {
		return userId;
	}

	public void setUserID(String userID) {
		this.userId = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append(username);
		return sb.toString();
	}

}
