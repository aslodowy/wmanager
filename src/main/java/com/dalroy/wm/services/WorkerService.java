package com.dalroy.wm.services;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dalroy.wm.entities.Worker;
import com.dalroy.wm.dataaccess.DataAccessBean;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.EJB;

@Stateless
@Path("resources/management/worker")
public class WorkerService {
	
	@EJB
	DataAccessBean dab;
	
	public WorkerService() {}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("specific/{id}")
	@RolesAllowed({"admin", "user"})
	public Worker getSpecificWorker(@PathParam("id") int id) {
		Worker worker = dab.getSpecificWorker(id);
		return worker;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("create")
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
	@Path("update/{id}")
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
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("find")
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
}

