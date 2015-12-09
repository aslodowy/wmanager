package com.dalroy.wm.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dalroy.wm.dataaccess.DataAccessBean;
import com.dalroy.wm.entities.Worker;
import com.dalroy.wm.helpers.Statistics;

@Stateless
@Path("resources/statistics")
public class StatisticsService {
	
	@EJB
	DataAccessBean dab;
	
	@GET
	@Path("males/salary/average")
	@Produces(MediaType.APPLICATION_JSON)
	public double getAverageMaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("male");
		return Statistics.averageSalary(workers);
	}
	
	@GET
	@Path("males/salary/median")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMedianMaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("male");
		return Statistics.medianSalary(workers);
	}
	
	@GET
	@Path("male/salary/quartile/upper")
	@Produces(MediaType.APPLICATION_JSON)
	public double getUpperQuartileMaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("male");
		return Statistics.upperQuartileSalary(workers);
	}
	
	@GET 
	@Path("male/salary/quartile/lower")
	@Produces(MediaType.APPLICATION_JSON)
	public double getLowerQuartieMaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("male");
		return Statistics.lowerQuartileSalary(workers);
	}
	
	@GET
	@Path("females/salary/average")
	@Produces(MediaType.APPLICATION_JSON)
	public double getAverageFemaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("female");
		return Statistics.averageSalary(workers);
	}
	
	@GET
	@Path("females/salary/median")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMedianFemaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("female");
		return Statistics.medianSalary(workers);
	}
	
	@GET
	@Path("female/salary/quartile/upper")
	@Produces(MediaType.APPLICATION_JSON)
	public double getUpperQuartileFemaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("female");
		return Statistics.upperQuartileSalary(workers);
	}
	
	@GET 
	@Path("female/salary/quartile/lower")
	@Produces(MediaType.APPLICATION_JSON)
	public double getLowerQuartieFemaleSalary() {
		List<Worker> workers = dab.getAllOfSpecificSex("female");
		return Statistics.lowerQuartileSalary(workers);
	}
}
