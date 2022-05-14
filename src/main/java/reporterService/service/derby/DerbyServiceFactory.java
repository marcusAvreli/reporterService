package reporterService.service.derby;


import reporterService.service.ReportService;

import reporterService.service.ServiceFactory;


/**
 * This class inherits from ServiceFactory
 * Contains implementation of based class that return implementation of service interfaces
 * for service a specific entity
 */
public class DerbyServiceFactory extends ServiceFactory {
    private static DerbyServiceFactory instance;

    private DerbyServiceFactory() {
    }

    public static synchronized DerbyServiceFactory getInstance() {
        if (instance == null) {
            instance = new DerbyServiceFactory();
        }
        return instance;
    }

    

    @Override
    public ReportService getReportService() {
        return new  DerbyReportService();
    }

    
}