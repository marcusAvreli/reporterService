package reporterService.dao.mysql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import reporterService.dao.GenericDao;
import reporterService.dao.ReportDAO;
import reporterService.dao.ReportResultDAO;
import reporterService.entity.Report;
import reporterService.entity.ReportFields;
import reporterService.entity.ReportResult;

public class MySqlReportResultDao extends GenericDao<ReportResult> implements ReportResultDAO {
	 private static final Logger logger = LogManager.getLogger(MySqlReportResultDao.class);
	 private static MySqlReportResultDao instance;

	    private MySqlReportResultDao() {
	    }

	    public static synchronized MySqlReportResultDao getInstance() {
	        if (instance == null) {
	            instance = new MySqlReportResultDao();
	        }
	        return instance;
	    }

	@Override
	public List<ReportResult> getReportResult(Connection connection, String procedureName,
			HashMap<String, Object> inputParams) throws SQLException {
		
		 List<ReportResult> list = new ArrayList<ReportResult>();   
		 
		 list = runStoredProcedure(connection, procedureName, inputParams,ReportResult.class);
		    	
				
		return list;
	}

	@Override
	protected ReportResult mapToEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void mapFromEntity(PreparedStatement ps, ReportResult obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
