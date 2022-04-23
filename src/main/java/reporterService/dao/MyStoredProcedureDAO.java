package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import reporterService.entity.MyStoredProcedure;


public interface MyStoredProcedureDAO {
	 List<MyStoredProcedure> getMyStoredProcedures(Connection connection,String procedureName,HashMap<String,Object> inputParams) throws SQLException;
}
