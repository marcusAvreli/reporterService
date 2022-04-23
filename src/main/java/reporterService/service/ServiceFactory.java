package reporterService.service;

import reporterService.service.mysql.MySqlServiceFactory;

public abstract class ServiceFactory {
    public static ServiceFactory getServiceFactory(String db) {
        switch (db) {
            case "MySQL":
                return MySqlServiceFactory.getInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

   // public abstract UserService getUserService();

    public abstract ReportService getReportService();

   // public abstract DeliveryDeskService getDeliveryDeskService();
}