package com.dalroy.wm.application;

//import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.*;
import javax.ws.rs.ApplicationPath;

import com.dalroy.wm.filters.SecurityFilter;
import com.dalroy.wm.services.SectionService;
import com.dalroy.wm.services.StatisticsService;
import com.dalroy.wm.services.UserService;
import com.dalroy.wm.services.WorkerService;

@ApplicationPath("")
public class Workers extends Application {
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(SecurityFilter.class);
		resources.add(WorkerService.class);
		resources.add(StatisticsService.class);
		resources.add(SectionService.class);
		resources.add(UserService.class);
		return resources;
	}	
}