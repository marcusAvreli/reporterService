package reporterService.jaxrs.filters;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;



public class ElaborateResourceFilterFactory implements ResourceFilterFactory {
	
	private static final Logger logger = LogManager.getLogger(ElaborateResourceFilterFactory.class);
	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		logger.info("Elaborate_Resource_Filter");
		List<ResourceFilter> singletonList = Lists.newArrayList();
		singletonList.add(new LoggingResourceFilter());	
		return singletonList;
	}

	

}