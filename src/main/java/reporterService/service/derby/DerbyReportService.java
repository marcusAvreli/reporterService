package reporterService.service.derby;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.resource.Singleton;

import reporterService.App;
import reporterService.dao.DaoFactory;
import reporterService.dao.StoredProcedureDAO;
import reporterService.dao.derby.DerbyDaoFactory;
import reporterService.dao.mysql.MysqlDaoFactory;
import reporterService.dao.ReportDAO;
import reporterService.dao.ReportFieldsDAO;
import reporterService.dao.ReportResultDAO;
import reporterService.entity.MyStoredProcedure;
import reporterService.entity.Report;
import reporterService.entity.ReportResult;
import reporterService.service.ReportService;

@Singleton
public class DerbyReportService implements ReportService{

    private static DaoFactory daoFactory;
    private static ReportDAO reportDao;
    private static StoredProcedureDAO myStoredProcedureDao;
    private static ReportResultDAO reportResultDao;
    private static ReportFieldsDAO reportFieldsDao;
    
    private static final Logger logger = LoggerFactory.getLogger(DerbyReportService.class);

	/**
     * Block initializes objects for working with dao layer
     */
    {
        try {
            daoFactory = DerbyDaoFactory.getInstance();
            reportDao = daoFactory.getReportDao();
            myStoredProcedureDao = daoFactory.getMyStoredProcedureDAO();
            reportResultDao = daoFactory.getReportResultDAO();            							
            reportFieldsDao = daoFactory.getReportFieldsDAO();
            
            //authorDao = daoFactory.getAuthorDao();
            //publisherDao = daoFactory.getPublisherDao();
            //genreDao = daoFactory.getGenreDao();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
    }

	@Override
	public List<Report> getReport() {
		// TODO Auto-generated method stub
        Connection connection = null;
        List<Report> resultList= null;
        try {
			connection = daoFactory.getConnection();
			resultList = reportDao.getReport(connection,"GetReports");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("I here");
		return resultList;
	}
	@Override
	public List<MyStoredProcedure> getMyStoredProcedures(String sqlCall, HashMap<String,Object> inputParams) {
		// TODO Auto-generated method stub
        Connection connection = null;
        List<MyStoredProcedure> resultList= null;
        try {
			connection = daoFactory.getConnection();
			resultList = myStoredProcedureDao.getMyStoredProcedures(connection, sqlCall, inputParams);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("I here");
		return resultList;
	}
	@Override
	public List<ReportResult> getResult(String sqlCall, HashMap<String,Object> inputParams) {
		// TODO Auto-generated method stub
        Connection connection = null;
        List<ReportResult> resultList= null;
        try {
			connection = daoFactory.getConnection();
			resultList = reportResultDao.getReportResult(connection, sqlCall, inputParams);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("I here");
		return resultList;
	}
	@Override
	public int createReport(Report report) {
		String sql = "insert into report (name,description,type) values ('report_3','report3_description','identity') ";
		logger.info("create report");
		 Connection connection = null;
		int generatedId = -1;
		try {
			logger.info("checkpost_1");
			connection = daoFactory.getConnection();
			logger.info("checkpost_2");
			generatedId = reportDao.create(connection, sql, report);
			logger.info("checkpost_3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return generatedId;
		
	}

}
