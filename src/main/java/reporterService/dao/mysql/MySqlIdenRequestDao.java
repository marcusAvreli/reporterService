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
import reporterService.dao.ReportDao;

import reporterService.entity.IdentityRequest;
import reporterService.entity.Report;

public class MySqlIdenRequestDao extends GenericDao<IdentityRequest> implements IdentityRequestDAO{
	 private static final Logger logger = LogManager.getLogger(MySqlIdenRequestDao.class);
//		public class MysqlBookDao extends GenericDao<Book> implements BookDao {
		    private static MySqlIdenRequestDao instance;

		    private MySqlIdenRequestDao() {
		    }

		    public static synchronized MySqlIdenRequestDao getInstance() {
		        if (instance == null) {
		            instance = new MySqlIdenRequestDao();
		        }
		        return instance;
		    }

		    @Override
		    public List<IdentityRequest> getIdentityRequesReport(Connection connection,String procedureName,HashMap<String,Object> inputParams) throws SQLException {
		        List<IdentityRequest> list = new ArrayList<IdentityRequest>();
		        logger.info("getting report from db");
		        list = runStoredProcedure(connection, procedureName,inputParams,IdentityRequest.class);
		        return list;
		    }

		  
		    /**
		     * The method forms the entity based on resultSet
		     */
		    @Override
		    protected IdentityRequest mapToEntity(ResultSet rs) throws SQLException {
		    	IdentityRequest report = new IdentityRequest();
		        report.setId(rs.getString("id"));
		        report.setName(rs.getString("name"));
		        /*book.setAuthor(rs.getString("author"));
		        book.setGenre(rs.getString("genre"));
		        book.setPublisher(rs.getString("publisher"));
		        book.setYearOfPublishing(rs.getInt("year_of_publishing"));
		        book.setCount(rs.getInt("count"));
		        book.setImage(rs.getBytes("image"));*/
		        return report;
		    }

		    /**
		     * The method forms the resultSet based on entity
		     */
		  

			@Override
			protected void mapFromEntity(PreparedStatement ps, IdentityRequest obj) throws SQLException {
				// TODO Auto-generated method stub
				
			}
}
