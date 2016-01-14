package com.dalroy.wm.services;

import java.security.NoSuchAlgorithmException;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dalroy.wm.dataaccess.DataAccessBean;
import com.dalroy.wm.entities.User;
import com.dalroy.wm.helpers.Password;
import com.dalroy.wm.helpers.Token;
import com.dalroy.wm.helpers.UserCredentials;

@Path("")
public class SecurityService {
	@EJB
	DataAccessBean dab;
	@EJB
	Password pwd;
	@EJB
	Token token;
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("login")
	@PermitAll
	public Response authenticateUser(UserCredentials credentials) {
		User user = dab.getUser(credentials.getUsername());
		String hash;
			hash = pwd.getHash(credentials.getPassword());
			String password = pwd.getPassword(hash, user.getSalt());
			if (user.getPassword().equals(password)) {
				String authenticationToken = token.getToken(Integer.toString(user.getId()), user.getRole(), "WManager", 1000 /*600000*/);
				return Response.status(200).type("text/plain").entity(authenticationToken).build();
			} else {
				return Response.status(401).type("text/plain").entity("something gone wrong").build();
			}
	}
}
