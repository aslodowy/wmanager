package com.dalroy.wm.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Collections;

import com.dalroy.wm.entities.Worker;
import com.dalroy.wm.entities.Section;
import com.dalroy.wm.utilities.ComparatorFactory.workerProperties;

public class Statistics {
	public static double averageSalary(List<Worker> workers) {
		double sum = 0;
		for (Worker worker : workers) {
			sum += worker.getSalary();
		}
		return sum/workers.size();
	}
	
	public static double lowerQuartileSalary(List<Worker> workers) {
		Collections.sort(workers, new ComparatorFactory().getWorkersComparator(workerProperties.SALARY));
		ArrayList<Worker> wrk = new ArrayList<Worker>(workers);
		List<Worker> half;
		if (wrk.size() % 2 == 0) {
			half = wrk.subList(0, wrk.size()/2-1);
		} else {
			half = wrk.subList(0, (wrk.size()+1)/2+1);
		}
		return medianSalary(half);
	}
	
	public static double upperQuartileSalary(List<Worker> workers) {
		Collections.sort(workers, new ComparatorFactory().getWorkersComparator(workerProperties.SALARY));
		ArrayList<Worker> wrk = new ArrayList<Worker>(workers);
		List<Worker> half;
		if (wrk.size() % 2 == 0) {
			half = wrk.subList(wrk.size()/2, wrk.size()-1);
		} else {
			half = wrk.subList((wrk.size()+1)/2+1, wrk.size()-1);
		}
		return medianSalary(half);
	}
	
	public static double medianSalary(List<Worker> workers) {
		Collections.sort(workers, new ComparatorFactory().getWorkersComparator(workerProperties.SALARY));
		ArrayList<Worker> wrk = new ArrayList<Worker>(workers);
		if (wrk.size() % 2 != 0){
			int medianIndex = (wrk.size()+1)/2+1;
			return wrk.get(medianIndex).getSalary();
		}
		else {
			int firstIndex = wrk.size()/2;
			int secondIndex = wrk.size()/2 - 1;
			return (wrk.get(firstIndex).getSalary() + wrk.get(secondIndex).getSalary())/2;
		}
	}
	
	public static double medianAge(List<Worker> workers) {
		Collections.sort(workers, new ComparatorFactory().getWorkersComparator(workerProperties.AGE));
		ArrayList<Worker> wrk = new ArrayList<Worker>(workers);
		if (wrk.size() % 2 != 0){
			int medianIndex = (wrk.size()+1)/2+1;
			return wrk.get(medianIndex).getAge();
		}
		else {
			int firstIndex = wrk.size()/2;
			int secondIndex = wrk.size()/2 - 1;
			return (wrk.get(firstIndex).getAge() + wrk.get(secondIndex).getAge())/2;
		}
	}
	
	public static double medianLengthOfService(List<Worker> workers) {
		Collections.sort(workers, new ComparatorFactory().getWorkersComparator(workerProperties.YEAREMPLOYED));
		ArrayList<Worker> wrk = new ArrayList<Worker>(workers);
		ArrayList<Integer> serviceOfWorkers = new ArrayList<Integer>();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (Worker wk : wrk) {
			serviceOfWorkers.add(currentYear - wk.getYearEmployed());
		}
		if (serviceOfWorkers.size() % 2 != 0){
			int medianIndex = (serviceOfWorkers.size()+1)/2+1;
			return serviceOfWorkers.get(medianIndex);
		}
		else {
			int firstIndex = serviceOfWorkers.size()/2;
			int secondIndex = serviceOfWorkers.size()/2 - 1;
			return (serviceOfWorkers.get(firstIndex) + serviceOfWorkers.get(secondIndex))/2;
		}
	}
	
	public static double averageAge(List<Worker> workers) {
		int sum = 0;
		for (Worker wrk : workers) {
			sum += wrk.getAge();
		}
		return sum/workers.size();
	}

	public static double averageLengthOfService(List<Worker> workers) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int sum = 0;
		for (Worker wrk : workers) {
			sum += (currentYear - wrk.getYearEmployed());
		}
		return sum/workers.size();
	}
	
	public static double totalNumberOfWorkers(List<Section> sections) {
		int all = 0;
		for (Section section : sections) {
			all += section.getNumberOfWorkers();
		}
		return all;
	}
}
