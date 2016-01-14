package com.dalroy.wm.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.core.interception.PostMatchContainerRequestContext;
import org.jboss.resteasy.util.Base64;

import com.dalroy.wm.dataaccess.DataAccessBean;
import com.dalroy.wm.entities.User;
import com.dalroy.wm.utilities.Password;
import com.dalroy.wm.utilities.Token;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	@EJB
	DataAccessBean dab;
	@EJB
	Password pwd;
	@EJB
	Token token;
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
	private static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied", 401, new Headers<Object>());
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Access for this resource is fobidden", 403, new Headers<Object>());
	private static final ServerResponse SERVER_ERROR = new ServerResponse("Internal Server Error", 500, new Headers<Object>());
	
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException  { 
		PostMatchContainerRequestContext context=(PostMatchContainerRequestContext)requestContext;
				
		ResourceMethodInvoker methodInvoker = context.getResourceMethod(); 
		Method method = methodInvoker.getMethod();
		if(!method.isAnnotationPresent(PermitAll.class)) {
			
			if(method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(ACCESS_FORBIDDEN);
				return;
			}
			
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();
			
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
			
			if(authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(ACCESS_DENIED);
				return;
			}
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME_BASIC + " "," ");
			//final String tkn = authorization.get(0).replaceFirst("AUTHENTICATION_SCHEME_BEARER" + " ", "");
			String usernameAndPassword;
			try {
				usernameAndPassword = new String(Base64.decode(encodedUserPassword));
			} catch(IOException ex) {
				requestContext.abortWith(SERVER_ERROR);
				return;
			}
			//String role = token.readRole(tkn);
			//System.out.println(role);
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();
			
			if(method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
				
				if(!isUserAllowed(username, password, rolesSet)) {
					requestContext.abortWith(ACCESS_DENIED);
					return;
				} 
				//if (!rolesSet.contains(role))
			}
		}
	}


	private boolean isUserAllowed(String username, String password,
			Set<String> rolesSet) {
		boolean isAllowed = false;		
			String hashedPassword = pwd.getHash(password);
			User user = dab.getUser(username);
			String userRole = user.getRole();		
			if (rolesSet.contains(userRole) && user.getPassword().equals(pwd.getPassword(hashedPassword, user.getSalt()))); 
				isAllowed = true; 
		return isAllowed;
	}
	
	

} 
