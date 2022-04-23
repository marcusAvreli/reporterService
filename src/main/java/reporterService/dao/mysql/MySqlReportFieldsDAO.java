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
import reporterService.dao.ReportFieldsDAO;
import reporterService.dao.ReportResultDAO;
import reporterService.entity.Report;
import reporterService.entity.ReportFields;
import reporterService.entity.ReportResult;

public class MySqlReportFieldsDAO extends GenericDao<ReportFields> implements ReportFieldsDAO {
	private static final Logger logger = LogManager.getLogger(MySqlReportFieldsDAO.class);
	private static MySqlReportFieldsDAO instance;

	private MySqlReportFieldsDAO() {
	}

	public static synchronized MySqlReportFieldsDAO getInstance() {
		if (instance == null) {
			instance = new MySqlReportFieldsDAO();
		}
		return instance;
	}

	@Override
	public List<ReportFields> getReportFields(Connection connection, String procedureName,
		HashMap<String, Object> inputParams) throws SQLException {
		List<ReportFields> list = new ArrayList<ReportFields>();
		logger.info("getting report from db");

		list = runStoredProcedure(connection, procedureName, inputParams,ReportFields.class);
		return list;
	}
	
	@Override
	protected ReportFields mapToEntity(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		ReportFields reportFields = new ReportFields();
		reportFields.setId(rs.getInt("id"));
		reportFields.setName(rs.getString("name"));
		reportFields.setDescription(rs.getString("description"));
		reportFields.setDescription(rs.getString("displayName"));
		reportFields.setDisabled(rs.getInt("disabled"));
		reportFields.setDisabledByReport(rs.getInt("disabled_by_report"));
		reportFields.setInvisibleByReport(rs.getInt("invisible_by_report"));
		reportFields.setOutput_ordering(rs.getInt("output_ordering"));
		return reportFields;
	}

	@Override
	protected void mapFromEntity(PreparedStatement ps, ReportFields obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
