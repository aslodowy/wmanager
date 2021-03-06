package com.dalroy.wm.utilities;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;

@Stateless
public class Password {
	public String getHash(String password) {
		try{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] byteArray = md.digest(password.getBytes(StandardCharsets.UTF_8));
		return Base64.encodeBase64String(byteArray);
		} catch (NoSuchAlgorithmException ex) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "lack of SHA-256 algorithm", new NoSuchAlgorithmException());
			return null;
		}
	}
	
	public String getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] byteArray = new byte[20];
		random.nextBytes(byteArray);
		return Base64.encodeBase64String(byteArray);
	}
	
	public String getPassword(String hash, String salt) {
		return hash + salt;
	}
}
