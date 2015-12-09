package com.dalroy.wm.entities;

import javax.persistence.*;


@Entity
@Table(name="workers", schema="WMDatabase")
public class Worker {
	
	public Worker() {}
	
	public Worker(String name, String lastName, int age, String sex, String position, int yearEmployed, double salary, Section section) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.sex = sex;
		this.position = position;
		this.yearEmployed = yearEmployed;
		this.salary = salary;
		this.section = section;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="age")
	private int age;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="position")
	private String position;
	
	@Column(name="year_employed")
	private int yearEmployed;
	
	@Column(name="salary")
	private double salary;
	
	@ManyToOne
	private Section section;
	
	@Transient
	private int sectionId;
	
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
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Section getSection() {
		return section;
	}
	
	public void setSection(Section section) {
		this.section = section;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getYearEmployed() {
		return yearEmployed;
	}
	
	public void setYearEmployed(int yearEmployed) {
		this.yearEmployed = yearEmployed;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public int getSectionId() {
		return sectionId;
	}
	
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
}
