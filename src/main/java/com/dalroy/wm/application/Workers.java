package com.dalroy.wm.application;

//import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.*;
import javax.ws.rs.ApplicationPath;

import com.dalroy.wm.services.SecurityFilter;
import com.dalroy.wm.services.StatisticsService;
import com.dalroy.wm.services.WorkerService;

@ApplicationPath("")
public class Workers extends Application {
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(SecurityFilter.class);
		resources.add(WorkerService.class);
		resources.add(StatisticsService.class);
		if (resources.size() == 3) System.out.println("zarejestrowało gunwo");
		return resources;
      // return Collections.emptySet();
	}	
}