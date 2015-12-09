package com.dalroy.wm.helpers;

import java.util.Comparator;

import com.dalroy.wm.entities.Section;
import com.dalroy.wm.entities.Worker;

class ComparatorFactory {
	//public ComparatorFactory(Class<?> type, String property) {
	//	this.type = type;
	//	this.property = property;
	//}
	enum workerProperties {AGE, SALARY, YEAREMPLOYED};
	public Comparator<Worker> getWorkersComparator(workerProperties property) {
		if (property == workerProperties.SALARY) return new WorkersSalaryComparator();
		if (property == workerProperties.YEAREMPLOYED) return new WorkersYearOfEmploymentComparator();
		else return new WorkersAgeComparator();
	}
	public Comparator<Section> getSectionsComparator() {
		return new SectionComparator();
	}
	
}

class WorkersSalaryComparator implements Comparator<Worker> {
	
	public int compare(Worker worker1, Worker worker2) {
		if (worker1.getSalary() > worker2.getSalary()) return 1;
		if (worker1.getSalary() < worker2.getSalary()) return -1;
		else return 0;
	}
}

class WorkersYearOfEmploymentComparator implements Comparator<Worker> {
	public int compare(Worker worker1, Worker worker2) {
		if (worker1.getYearEmployed() < worker2.getYearEmployed()) return 1;
		if (worker1.getYearEmployed() > worker2.getYearEmployed()) return -1;
		else return 0;
		
	}
}

class WorkersAgeComparator implements Comparator<Worker> {
	public int compare(Worker worker1, Worker worker2) {
		if (worker1.getAge() > worker2.getAge()) return 1;
		if (worker1.getAge() < worker2.getAge()) return-1;
		else return 0;
	}
}

class SectionComparator implements Comparator<Section> {
	public int compare(Section sc1, Section sc2) {
		if (sc1.getNumberOfWorkers() > sc2.getNumberOfWorkers()) return 1;
		if (sc1.getNumberOfWorkers() < sc2.getNumberOfWorkers()) return -1;
		else return 0;
	}
}
