package reporterService.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import reporterService.dao.DaoFactory;
import reporterService.dao.ReportDao;
import reporterService.dao.ReportFieldsDAO;

public class MysqlDaoFactory extends DaoFactory {
	 private static final Logger logger = LogManager.getLogger(MysqlDaoFactory.class);
    private static MysqlDaoFactory instance;
    private static DataSource ds ;

    private MysqlDaoFactory() {
        Context initContext;
        Context envContext;
        //try {
        	
        	
            //initContext = new InitialContext();
            //envContext = (Context)initContext.lookup("java:/comp/env");
           // ds = (DataSource)envContext.lookup("jdbc/librdb");
           MysqlDataSource mysqlDS= new MysqlDataSource();
    		mysqlDS.setURL("jdbc:mysql://192.168.134.150:3306/identityiq?noAccessToProcedureBodies=false&useInformationSchema=true");
    		mysqlDS.setUser("root");
    		mysqlDS.setPassword("root");
    		ds = (DataSource)mysqlDS;
            
            
        /*} catch (NamingException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("Can not init MysqlDaoFactory, e");

        }*/
    }

    public static synchronized MysqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MysqlDaoFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

   

    @Override
    public ReportDao getReportDao() {
        return MySqlReportDao.getInstance();
    }
@Override
public MySqlMyStoredProcedureDAO getMyStoredProcedureDAO(){
	return MySqlMyStoredProcedureDAO.getInstance();
}
@Override
public MySqlReportResultDao getMySqlReportResultDao(){
	return MySqlReportResultDao.getInstance();
}
@Override
public ReportFieldsDAO getReportFieldsDAO(){
	return MySqlReportFieldsDAO.getInstance();
} 
}