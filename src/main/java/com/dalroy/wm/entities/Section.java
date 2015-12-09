package com.dalroy.wm.entities;

import javax.persistence.*;

@Entity
@Table(name="sections")
public class Section {

	public Section() {}
	
	public Section(String name, int numberOfWorkers) {
		this.name = name;
		this.numberOfWorkers = numberOfWorkers;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="number_of_workers")
	private int numberOfWorkers;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfWorkers() {
		return numberOfWorkers;
	}
	
	public void setNumberOfWorkers(int numberOfWorkers) {
		this.numberOfWorkers = numberOfWorkers;
	}
}
