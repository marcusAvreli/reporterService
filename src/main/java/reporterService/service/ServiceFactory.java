package reporterService.service;

import reporterService.service.derby.DerbyServiceFactory;
import reporterService.service.mysql.MySqlServiceFactory;

public abstract class ServiceFactory {
    public static ServiceFactory getServiceFactory(String db) {
        switch (db) {
            case "MySQL":
                return MySqlServiceFactory.getInstance();
            case "Derby":
                return DerbyServiceFactory.getInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

   // public abstract UserService getUserService();

    public abstract ReportService getReportService();

   // public abstract DeliveryDeskService getDeliveryDeskService();
}