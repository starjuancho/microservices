package com.globant.pruebas.profileservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhoneNumber implements Entity {

	private boolean main;
	
	String phoneNumber;

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
//		for(int i =0; i<phoneNumber.length();) {
//			sb.append(phoneNumber.subSequence(i, i+=(phoneNumber.length()-i)%3));
//		}
		return phoneNumber;
	}
}
