package com.dalroy.wm.services;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dalroy.wm.dataaccess.DataAccessBean;
import com.dalroy.wm.entities.User;

@Stateless
@Path("administration/users")
public class UserService {
	
	@EJB
	DataAccessBean dab;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("create")
	//@RolesAllowed("admin")
	@PermitAll
	public Response createUser(User user) {
		try {
			dab.createUser(user);
		} catch (Exception ex) {
			String message = "Sorry, something went wrong";
			return Response.status(404).type("text/plain").entity(message).build();
		}
		String message = "Everything is Ok";
		return Response.status(200).type("text/plain").entity(message).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("update/{id}")
	//@RolesAllowed("admin")
	@PermitAll
	public Response updateUser(@PathParam("id") int id, User user) {
		try {
			dab.updateUser(id, user);
		} catch (Exception ex) {
			String message = "Sorry, something went wrong";
			return Response.status(404).type("text/plain").entity(message).build();
		}
		String message = "Everything is ok";
		return Response.status(200).type("text/plain").entity(message).build();
	}

}
