/* package ServiceTests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.*;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.dalroy.wm.helpers.Statistics;
import com.dalroy.wm.entities.Worker;

public class StatisticsTests {;
	int sum = 0;
	List<Worker> firstList = new ArrayList<Worker>();
	List<Worker> secondList = new ArrayList<Worker>();
	@Before public void prepareData() {
		firstList.clear();
		secondList.clear();
		for (int i = 20; i < 50; i++) {
			Worker worker = mock(Worker.class);
			when(worker.getAge()).thenReturn(i);
			//worker.setAge(i);
			double j = (double)((i % 3)+3)*1000;
			sum +=j;
			when(worker.getSalary()).thenReturn(j);
			//worker.setSalary(j);
			if (i % 3 == 0) {
				when(worker.getYearEmployed()).thenReturn(2010);
				//worker.setYearEmployed(2010);
			} 
			if (i % 3 == 1) {
				when(worker.getYearEmployed()).thenReturn(2011);
				//worker.setYearEmployed(2011);
			} else when(worker.getYearEmployed()).thenReturn(2012);
			firstList.add(worker);
		}
		/* for (int j = 0; j < 5; j++) {
			Worker worker = mock(Worker.class);
			when(worker.getSalary()).thenReturn((double)2500+j*500);
			secondList.add(worker);
			System.out.println(j);
		} 
	}
	//@SuppressWarnings("deprecation")
	@Test
	public void testAverage() {
		assertEquals(sum/firstList.size(), Statistics.averageSalary(firstList), 0);
	}
	@Test
	public void testQuartiles() {
		for (int j = 0; j < 5; j++) {
			Worker worker = mock(Worker.class);
			when(worker.getSalary()).thenReturn((double)2500+j*500);
			secondList.add(worker);
			System.out.println(j);
		}
		assertEquals(4000.00, Statistics.upperQuartileSalary(secondList), 0);
		assertEquals(3000.00, Statistics.lowerQuartileSalary(secondList), 0);
		assertEquals(3500.00, Statistics.medianSalary(secondList), 0);
		//assertEquals(4000.00, Statistics.upperQuartileSalary(secondList), 0);
		
	}
}
*/