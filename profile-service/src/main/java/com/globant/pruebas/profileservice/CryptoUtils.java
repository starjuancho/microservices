package com.globant.pruebas.profileservice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class CryptoUtils {

	public String Hash256(final String str) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(str.getBytes());
		return new String(messageDigest.digest());
	}
}
