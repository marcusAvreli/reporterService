package reporterService.providers;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;


import reporterService.service.ServiceFactory;

@Provider
public class ReportServiceProvider extends SingletonTypeInjectableProvider<Context, ServiceFactory> {
	
		public ReportServiceProvider() {
			
			//super(MysqlDaoFactory.class, MysqlDaoFactory.getInstance());
			//super(MySqlServiceFactory.class, MySqlServiceFactory.getInstance());
			super(ServiceFactory.class, ServiceFactory.getServiceFactory("Derby"));
		}
	
}
