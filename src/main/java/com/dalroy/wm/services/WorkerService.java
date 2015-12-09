package com.dalroy.wm.services;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dalroy.wm.entities.*;
import com.dalroy.wm.dataaccess.*;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.EJB;

@Stateless
@Path("resources/management")
public class WorkerService {
	
	@EJB
	DataAccessBean dab;
	
	public WorkerService() {}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("worker/specific/{id}")
	@RolesAllowed({"admin", "user"})
	public Worker getSpecificWorker(@PathParam("id") int id) {
		Worker worker = dab.getSpecificWorker(id);
		return worker;
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("section/specific/{id}")
	@RolesAllowed({"admin", "user"})
	public Section getSpecificSection(@PathParam("id") int id) {
		Section section = dab.getSpecificSection(id);
		return section;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("worker/create")
	@RolesAllowed({"admin", "user"})
	public Response createWorker(Worker worker) {
		try {
		dab.addWorker(worker);
		} catch(Exception ex) {
			String message = "Sorry, something went wrong";
			return Response.status(404).type("application/json").entity(message).build();
		}
		String message = "Everything is Ok";
		return Response.status(200).type("application/json").entity(message).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("worker/update/{id}")
	@RolesAllowed({"admin", "user"})
	public Response updateWorker(@PathParam("id") int id, Worker worker) {
		try {
			dab.updateWorker(id, worker);
		} catch (Exception ex) {
			String message = "Sorry, something went wrong";
			return Response.status(500).type("application/json").entity(message).build();
		}
		String message = "Everything is Ok";
		return Response.status(200).type("application/json").entity(message).build();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("section/create")
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
	@Path("section/update/{id}")
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
	
	//@DELETE
	//@Produces(MediaType.TEXT_PLAIN)
	//@Path("DeleteWorker")
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("worker/find")
	@RolesAllowed({"admin", "user"})
	public List<Worker> getWorkers(@QueryParam("name") @DefaultValue("") String name,
								   @QueryParam("lastname") @DefaultValue("") String lastName,
								   @QueryParam("minage") @DefaultValue("") String minAge,
								   @QueryParam("maxage") @DefaultValue("") String maxAge,
								   @QueryParam("minworkedyears") @DefaultValue("") String minYearEmployed,
								   @QueryParam("maxworkedyears") @DefaultValue("") String maxYearEmployed,
								   @QueryParam("minsalary") @DefaultValue("") String minSalary,
								   @QueryParam("maxsalary") @DefaultValue("") String maxSalary,
								   @QueryParam("position") @DefaultValue("") String position,
								   @QueryParam("sex") @DefaultValue("") String sex) {
		
		List<Worker> list =
		dab.findWorkers(name, lastName, minSalary, maxSalary, minAge, maxAge, minYearEmployed, maxYearEmployed, position, sex);
		
		return list;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("users/create")
	@RolesAllowed("admin")
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
	@Path("users/update/{id}")
	@RolesAllowed("admin")
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

