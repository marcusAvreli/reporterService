package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import reporterService.entity.IdentityRequest;
import reporterService.entity.Report;

public interface IdentityRequestDAO {
	
	    List<IdentityRequest> getIdentityRequesReport(Connection connection,String procedureName,HashMap<String,Object> inputParams) throws SQLException;

}
