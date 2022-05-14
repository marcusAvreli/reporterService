package reporterService.dao.derby;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.derby.jdbc.ClientDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import reporterService.dao.DaoFactory;
import reporterService.dao.StoredProcedureDAO;
import reporterService.dao.ReportDAO;
import reporterService.dao.ReportFieldsDAO;
import reporterService.dao.ReportResultDAO;
import reporterService.dao.mysql.MySqlReportFieldsDAO;
import reporterService.dao.mysql.MysqlDaoFactory;

public class DerbyDaoFactory extends DaoFactory {
	private static final Logger logger = LogManager.getLogger(DerbyDaoFactory.class);
	private static DerbyDaoFactory instance;
	private static DataSource ds;

	private DerbyDaoFactory() {
		Context initContext;
		Context envContext;
		// try {
		logger.info("start connection to db");
		// initContext = new InitialContext();
		// envContext = (Context)initContext.lookup("java:/comp/env");
		// ds = (DataSource)envContext.lookup("jdbc/librdb");
		System.setProperty("java.security.policy","file:/C:/dbDerby/demo/templates/server.policy");
		System.setSecurityManager(new SecurityManager());

		ClientDataSource mysqlDS = new ClientDataSource();
		// The host on which Network Server is running
		mysqlDS.setServerName("localhost");

		// port on which Network Server is listening
		mysqlDS.setPortNumber(1527);
		
		mysqlDS.setDatabaseName("C:/dbDerby/demo/databases/MyDbTest;create=true");

		mysqlDS.setUser("root");
		mysqlDS.setPassword("root");
		ds = mysqlDS;
		logger.info("connection to db");
		if (null != mysqlDS) {
			logger.info("got connection");
		}
		if (null != ds) {
			logger.info("got connection");
		}
	}

	public static synchronized DerbyDaoFactory getInstance() {
		if (instance == null) {
			instance = new DerbyDaoFactory();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	@Override
	public ReportFieldsDAO getReportFieldsDAO() {
		return MySqlReportFieldsDAO.getInstance();
	}

	@Override
	public ReportDAO getReportDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoredProcedureDAO getMyStoredProcedureDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReportResultDAO getReportResultDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
