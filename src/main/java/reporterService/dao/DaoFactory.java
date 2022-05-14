package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;

import reporterService.dao.derby.DerbyDaoFactory;
import reporterService.dao.mysql.MySqlReportResultDao;
import reporterService.dao.mysql.MysqlDaoFactory;

public abstract class DaoFactory {

   

    //public abstract UserDao getUserDao();

    public abstract ReportDAO getReportDao();
    public abstract StoredProcedureDAO getMyStoredProcedureDAO();
    public abstract ReportResultDAO getReportResultDAO();
    public abstract ReportFieldsDAO getReportFieldsDAO();
    /*public abstract DeliveryDeskDao getDeliveryDeskDao();

    public abstract GenreDao getGenreDao();

    public abstract AuthorDao getAuthorDao();

    public abstract PublisherDao getPublisherDao();*/

    public abstract Connection getConnection() throws SQLException;
}