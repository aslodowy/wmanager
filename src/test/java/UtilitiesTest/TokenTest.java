package UtilitiesTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dalroy.wm.utilities.Token;


public class TokenTest {

	Token token;
	Token token2;
	String tkn;
	@Before 
	public void prepareData() {
		String id = "1";
		String subject = "admin";
		String issuer = "wmanager";
		long time = 1000000;
		token = new Token();
		token2 = new Token();
		
		tkn = token.getToken(id, subject, issuer, time);
		 
	}
	
	@Test
	public void testTokenReading() {
		assertTrue("admin".equals(token.readRole(tkn)));
		assertTrue("1".equals(token.readId(tkn)));
		assertTrue("wmanager".equals(token.readIssuer(tkn)));
	}
}
