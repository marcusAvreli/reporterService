package reporterService.service.mysql;


import reporterService.service.ReportService;

import reporterService.service.ServiceFactory;


/**
 * This class inherits from ServiceFactory
 * Contains implementation of based class that return implementation of service interfaces
 * for service a specific entity
 */
public class MySqlServiceFactory extends ServiceFactory {
    private static MySqlServiceFactory instance;

    private MySqlServiceFactory() {
    }

    public static synchronized MySqlServiceFactory getInstance() {
        if (instance == null) {
            instance = new MySqlServiceFactory();
        }
        return instance;
    }

    

    @Override
    public ReportService getReportService() {
        return new  MySqlReportService();
    }

    
}