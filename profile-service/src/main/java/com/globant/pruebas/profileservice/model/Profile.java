package com.globant.pruebas.profileservice.model;

import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile implements Entity {

	private String userId;
	private String givenName;
	private String paternalName;
	private String maternalName;
	private Date birthDate;
	private String TaxId;

	private Collection<Address> addressess;
	private Collection<PhoneNumber> phoneNumbers;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getPaternalName() {
		return paternalName;
	}

	public void setPaternalName(String paternalName) {
		this.paternalName = paternalName;
	}

	public String getMaternalName() {
		return maternalName;
	}

	public void setMaternalName(String maternalName) {
		this.maternalName = maternalName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getTaxId() {
		return TaxId;
	}

	public void setTaxId(String taxId) {
		TaxId = taxId;
	}

	public Collection<Address> getAddressess() {
		return addressess;
	}

	public void setAddressess(Collection<Address> addressess) {
		this.addressess = addressess;
	}

	public Collection<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Collection<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("User ").append(userId).append(": ");
		sb.append(givenName).append(" ");
		sb.append(paternalName).append(" ");
		sb.append(maternalName);
		return sb.toString();
	}

}
