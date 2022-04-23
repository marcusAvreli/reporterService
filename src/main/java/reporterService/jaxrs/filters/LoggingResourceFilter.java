package reporterService.jaxrs.filters;

/*
 * #%L
 * elab4-backend
 * =======
 * Copyright (C) 2011 - 2019 Huygens ING
 * =======
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import org.joda.time.DateTime;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;



class LoggingResourceFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {
	// private final StopWatch sw = new StopWatch();
	private static final Logger logger = LogManager.getLogger(LoggingResourceFilter.class);
	@Override
	public ContainerRequestFilter getRequestFilter() {
		logger.info("Filter1");
		return this;
	}

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		logger.info("Filter2");
		// Log.info("request={}", verbalize(request));
		// sw.reset();
		// sw.start();
		return request;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		logger.info("Filter3");
		
		return this;
	}

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		// sw.stop();
		// Log.info("request took {} ms", sw.getTime());
		//System.out.println(commonLogLine(request, response));
		  /*if("OPTIONS".equalsIgnoreCase(request.getMethod())) {//The browser will first confirm whether the server can be accessed normally through the options request, and it should be released at this time
			  response.setStatus(HttpServletResponse.SC_OK);
			}*/
		logger.info("container response filter");
		/*response.setStatus(200);
        //response.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
    	response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHttpHeaders().add("Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin, content-type, accept, authorization");
        response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHttpHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.getHttpHeaders().add("Access-Control-Max-Age", "1111139");*/
		return response;
	}
	

	private String commonLogLine(ContainerRequest request, ContainerResponse response) {
		// 127.0.0.1 user-identifier frank [10/Oct/2000:13:55:36 -0700] "GET /apache_pb.gif HTTP/1.0" 200 2326
		return MessageFormat.format(//
				"{0} {1} {2} [{3}] \"{4} /{5} {6}\" {7} {8}", //
				"?", //
				// req.getRemoteAddr(),//
				"-", //
				"?", //
				// req.getRemoteUser(),//
				"test",
				//new DateTime().toString("dd/MMM/yyyy:HH:mm:ss ZZ"), //
				request.getMethod(), //
				request.getPath(), //
				"?", //
				// req.getProtocol(),//
				response.getStatus(), //
				"?"//
		);
	}

}