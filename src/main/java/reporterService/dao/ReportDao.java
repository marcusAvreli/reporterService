package reporterService.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import reporterService.entity.Report;

public interface ReportDao {
    List<Report> getReport(Connection connection,String procedureName) throws SQLException;
   

   /* void create(Connection connection, String sql, Book book) throws SQLException;

    void update(Connection connection, String sql, Book book) throws SQLException;

    void delete(Connection connection, String sql, long bookId) throws SQLException;

    List<Book> findAll(Connection connection, String sql) throws SQLException;

    List<Book> findByName(Connection connection, String sql, String name) throws SQLException;

    List<Book> getBooksByGenreId(Connection connection, String sql, long id) throws SQLException;*/
}