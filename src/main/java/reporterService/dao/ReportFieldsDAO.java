package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import reporterService.entity.ReportFields;


public interface ReportFieldsDAO {
	List<ReportFields> getReportFields(Connection connection,String procedureName,HashMap<String,Object> inputParams) throws SQLException;
}
