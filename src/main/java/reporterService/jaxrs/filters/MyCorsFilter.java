package reporterService.jaxrs.filters;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

public class MyCorsFilter implements  ContainerResponseFilter{

	private static final Logger logger = LogManager.getLogger(MyCorsFilter.class);
	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		// sw.stop();
		// Log.info("request took {} ms", sw.getTime());
		//System.out.println(commonLogLine(request, response));
		  logger.info("response got");
		  if("OPTIONS".equalsIgnoreCase(request.getMethod())) {//The browser will first confirm whether the server can be accessed normally through the options request, and it should be released at this time
			  response.setStatus(HttpServletResponse.SC_OK);
			}
		  if(response.isResponseSet()) {
			  logger.info("response set");
		  }else {
			  logger.info("response not set");
		  }
		logger.info("my cors filter");
		response.setStatus(200);
        response.getHttpHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
    	//response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHttpHeaders().add("Access-Control-Expose-Headers","Location");
        		

        response.getHttpHeaders().add("Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin, content-type, accept, authorization,volumeId");
        response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHttpHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.getHttpHeaders().add("Access-Control-Max-Age", "1111139");
		return response;
	}
}
