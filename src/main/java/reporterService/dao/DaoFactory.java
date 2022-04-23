package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;

import reporterService.dao.mysql.MySqlReportResultDao;
import reporterService.dao.mysql.MysqlDaoFactory;

public abstract class DaoFactory {

    public static DaoFactory getDaoFactory(String name) {
        switch (name) {
            case "MySQL":
                return MysqlDaoFactory.getInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

    //public abstract UserDao getUserDao();

    public abstract ReportDao getReportDao();
    public abstract MyStoredProcedureDAO getMyStoredProcedureDAO();
    public abstract MySqlReportResultDao getMySqlReportResultDao();
    public abstract ReportFieldsDAO getReportFieldsDAO();
    /*public abstract DeliveryDeskDao getDeliveryDeskDao();

    public abstract GenreDao getGenreDao();

    public abstract AuthorDao getAuthorDao();

    public abstract PublisherDao getPublisherDao();*/

    public abstract Connection getConnection() throws SQLException;
}