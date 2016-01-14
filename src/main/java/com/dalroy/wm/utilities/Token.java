package com.dalroy.wm.utilities;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Claims;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

@Stateless
public class Token {
	
	public static final String API_KEY = "fwiufnweor3nooOOInownen98q";
	
	public String getToken(String Id, String subject, String issuer, long expireDateMillis) {
	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	Date expires = new Date(System.currentTimeMillis() + expireDateMillis);
	
		JwtBuilder builder = Jwts.builder().setId(Id)
									   .setIssuedAt(new Date(System.currentTimeMillis()))
									   .setSubject(subject)
									   .setIssuer(issuer)
									   .signWith(signatureAlgorithm, this.getKey(signatureAlgorithm))
									   .setExpiration(expires);
		
		return builder.compact();
	}
	
	public void readRole(String tkn) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(API_KEY)).parseClaimsJws(tkn).getBody();
		System.out.println(claims.getSubject());
		//return claims.getSubject();
	}
	
	public String readId() {
		return null;
	}
	
	private Key getKey(SignatureAlgorithm signatureAlgorithm) {
		try {
			SecretKey key = KeyGenerator.getInstance("AES").generateKey();
		
		byte[] apiKeyBytes = DatatypeConverter.parseBase64Binary(API_KEY);
		Key signingKey = new SecretKeySpec(apiKeyBytes, signatureAlgorithm.getJcaName());
		return signingKey;
		}
		catch(NoSuchAlgorithmException ex) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "lack of AES algorithm", new NoSuchAlgorithmException());
			return null;
		}
	
	}
}
