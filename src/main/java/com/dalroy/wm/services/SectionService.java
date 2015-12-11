package com.dalroy.wm.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dalroy.wm.entities.Section;
import com.dalroy.wm.dataaccess.DataAccessBean;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.EJB;

@Stateless
@Path("resources/management/section")
public class SectionService {

	@EJB
	DataAccessBean dab;
	
	public SectionService() {}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("specific/{id}")
	@RolesAllowed({"admin", "user"})
	public Section getSpecificSection(@PathParam("id") int id) {
		Section section = dab.getSpecificSection(id);
		return section;
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("create")
	@RolesAllowed({"admin", "user"})
	public Response createSection(Section section) {
		try {
			dab.addSection(section);
		} catch (Exception ex) {
			String message = "Sorry, something went wrong";
			return Response.status(404).type("application/json").entity(message).build();
		}
		String message = "Everything is Ok";
		return Response.status(200).type("application/json").entity(message).build();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("update/{id}")
	@RolesAllowed({"admin","user"})
	public Response updateSection(@PathParam("id") int id, Section section) {
		try {
			dab.updateSection(id, section);
		} catch (Exception ex) {
			String message = "Sorry, something went wrong";
			return Response.status(404).type("text/plain").entity(message).build();
		}
		String message = "Everything is Ok";
		return Response.status(200).type("text/plain").entity(message).build();
	}
}
