package reporterService.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.mysql.jdbc.CallableStatement;

import reporterService.App;

public abstract class GenericDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(GenericDao.class);

	public List<T> runStoredProcedure(Connection connection, String sql, HashMap<String, Object> inputParams,
			Class<T> type) throws SQLException {
		logger.info("STARTED_runStoredProcedure");
		logger.info("TYPE:" + type.getName());
		logger.info("inputParams:" + inputParams);

		List<T> list = new ArrayList<>();
		CallableStatement cs = connection.prepareCall(sql);
		if (null != inputParams && !inputParams.isEmpty()) {
			for (Map.Entry<String, Object> mapEntry : inputParams.entrySet()) {
				cs.setObject(mapEntry.getKey(), mapEntry.getValue());

			}
		}

		ResultSet resultSet = cs.executeQuery();
		while (resultSet.next()) {

			try {

				T item = (T) type.newInstance();
				ReflectionExHelper.loadResultSetIntoObject(resultSet, item);
				list.add(item);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		resultSet.close();

		logger.info("FINISHED_runStoredProcedure");
		return list;
	}

	public List<T> findAllItems(Connection connection, String sql) throws SQLException {
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapToEntity(rs));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new SQLException();
		} finally {
			closeStatementsAndResultSet(ps, rs);
		}
		return list;
	}

	@SafeVarargs
	protected final <V> List<T> findByFields(Connection connection, String sql,
			@SuppressWarnings("unchecked") V... values) throws SQLException {
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				V value = values[i - 1];
				dispatchType(ps, i, value);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapToEntity(rs));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new SQLException();
		} finally {
			closeStatementsAndResultSet(ps, rs);
		}
		return list;
	}

	/**
	 * Method dispatches values by type and adds them to prepare statement
	 */
	private <V> void dispatchType(PreparedStatement ps, int i, V value) throws SQLException {
		switch (value.getClass().getSimpleName()) {
		case "Integer":
			ps.setInt(i, (Integer) value);
			break;
		case "Long":
			ps.setLong(i, (Long) value);
			break;
		case "String":
			ps.setString(i, (String) value);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	protected long add(Connection connection, String sql, T item) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			mapFromEntity(ps, item);
			if (ps.executeUpdate() > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					return rs.getLong(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new SQLException();
		} finally {
			closeStatementsAndResultSet(ps, rs);
		}
		throw new SQLException("Not added");
	}

	protected <V> void updateByField(Connection connection, String sql, T item, int parameterIndex, V value)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			dispatchType(ps, parameterIndex, value);
			mapFromEntity(ps, item);
			if (ps.executeUpdate() == 0)
				throw new SQLException("Not updated");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatementsAndResultSet(ps, rs);
		}
	}

	@SafeVarargs
	protected final <V> void deleteByField(Connection connection, String sql, V... values) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				V value = values[i - 1];
				dispatchType(ps, i, value);
			}
			if (ps.executeUpdate() == 0)
				throw new SQLException("Not deleted");

		} finally {
			closeStatementsAndResultSet(ps, rs);
		}
	}

	protected void closeStatementsAndResultSet(PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * The method forms the entity based on resultSet
	 */
	protected abstract T mapToEntity(ResultSet rs) throws SQLException;

	/**
	 * The method forms the resultSet based on entity
	 */
	protected abstract void mapFromEntity(PreparedStatement ps, T obj) throws SQLException;
}