package com.dalroy.wm.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ContainerLoggingFilter implements ContainerRequestFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
			System.out.println("Http method: " + requestContext.getMethod().toString());
			System.out.println("Request date is :" + requestContext.getDate());
			
	}

}
