package reporterService.dao.mysql;

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
import reporterService.dao.IdentityRequestDAO;
import reporterService.dao.StoredProcedureDAO;
import reporterService.entity.IdentityRequest;
import reporterService.entity.MyStoredProcedure;
import reporterService.entity.Report;

public class MySqlMyStoredProcedureDAO extends GenericDao<MyStoredProcedure> implements StoredProcedureDAO{
	private static final Logger logger = LogManager.getLogger(MySqlMyStoredProcedureDAO.class);

	
	 private static MySqlMyStoredProcedureDAO instance;

	    private MySqlMyStoredProcedureDAO() {
	    }

	    public static synchronized MySqlMyStoredProcedureDAO getInstance() {
	        if (instance == null) {
	            instance = new MySqlMyStoredProcedureDAO();
	        }
	        return instance;
	    }

	@Override
	protected MyStoredProcedure mapToEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		MyStoredProcedure report = new MyStoredProcedure();
        report.setId(rs.getInt("id"));
        report.setName(rs.getString("name"));
        report.setParameter_name(rs.getString("parameter_name"));
		return report;
	}

	@Override
	protected void mapFromEntity(PreparedStatement ps, MyStoredProcedure obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<MyStoredProcedure> getMyStoredProcedures(Connection connection, String procedureName,HashMap<String,Object> inputParams)
			throws SQLException {
		// TODO Auto-generated method stub
		List<MyStoredProcedure> list = new ArrayList<MyStoredProcedure>();
        logger.info("getting report from db");
        list = runStoredProcedure(connection, procedureName,inputParams,MyStoredProcedure.class);
        return list;
		
	}
	
	
	

}
