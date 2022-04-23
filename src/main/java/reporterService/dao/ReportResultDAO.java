package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import reporterService.entity.Report;
import reporterService.entity.ReportResult;

public interface ReportResultDAO {
	List<ReportResult> getReportResult(Connection connection,String procedureName,HashMap<String,Object> inputParams) throws SQLException;
}
