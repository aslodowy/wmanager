package com.dalroy.wm.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter {
	
	private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
			logger.log(Level.INFO, requestContext.getEntityStream().toString());
			
	}

}
