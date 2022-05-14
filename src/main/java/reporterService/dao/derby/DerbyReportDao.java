package reporterService.dao.derby;

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
import reporterService.entity.Report;

public class DerbyReportDao extends GenericDao<Report> implements ReportDAO {
	 private static final Logger logger = LogManager.getLogger(DerbyReportDao.class);
//	public class MysqlBookDao extends GenericDao<Book> implements BookDao {
	    private static DerbyReportDao instance;

	    private DerbyReportDao() {
	    }

	    public static synchronized ReportDAO getInstance() {
	        if (instance == null) {
	            instance = new DerbyReportDao();
	        }
	        return instance;
	    }

	    @Override
	    public List<Report> getReport(Connection connection,String procedureName) throws SQLException {
	        List<Report> list = new ArrayList<Report>();
	        logger.info("getting report from db");
	        HashMap<String,Object> inputParams = null;
	        list = runStoredProcedure(connection, procedureName,inputParams,Report.class);
	        return list;
	    }

	  
	    /**
	     * The method forms the entity based on resultSet
	     */
	    @Override
	    protected Report mapToEntity(ResultSet rs) throws SQLException {
	        Report report = new Report();
	        report.setId(rs.getInt("id"));
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
	    protected void mapFromEntity(PreparedStatement ps, Report book) throws SQLException {
	        /*ps.setString(1, book.getName());
	        ps.setString(2, book.getAuthor());
	        ps.setString(3, book.getGenre());
	        ps.setString(4, book.getPublisher());
	        ps.setInt(5, book.getYearOfPublishing());
	        ps.setInt(6, book.getCount());*/
//	        ps.setBytes(7, book.getImage());
	    }

		@Override
		public int create(Connection connection, String sql, Report book) throws SQLException {
			logger.info("report to be created: "+book);
			return -1;
			
		}
	}