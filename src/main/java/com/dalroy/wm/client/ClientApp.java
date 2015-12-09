package com.dalroy.wm.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.dalroy.wm.entities.Worker;

import java.util.Scanner;

public class ClientApp {
	public static void main(String[] args) {
		ClientApp ca = new ClientApp();
		ca.getSpecificWorker();
	}
	
	private void getSpecificWorker() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("podaj id pracownika");
		int id = scanner.nextInt();
		scanner.close();
		Client client = ClientBuilder.newBuilder().build();
		WebTarget specificWorker = client.target("http://localhost:8080/WorkersManager/work/workers/specific/"+id);
		Response response = specificWorker.request().buildGet().invoke();
		Worker worker = response.readEntity(Worker.class);
		System.out.println("Id pracownika: " + worker.getId());
		System.out.println("imię pracownika: "+worker.getName());
		System.out.println("nazwisko pracownika: " + worker.getLastName());
		System.out.println("Zarobki pracownika: "+ worker.getSalary());
	}
}
