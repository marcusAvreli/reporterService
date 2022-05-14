package reporterService.service;

import java.util.HashMap;
import java.util.List;

import reporterService.entity.MyStoredProcedure;
import reporterService.entity.Report;
import reporterService.entity.ReportResult;

public interface ReportService {

  //  void createBook(Book book);
   // void updateBook(Book book);
    //void deleteBook(long id);
  //  Book getBookById(long id);
    //List<Book> getBooksBySearch(String searchStr, SearchType type);
   // List<Book> getBooksByLetter(String letter);
   // List<Book> getBooksByGenreId(long id);
    List<Report> getReport();
    int createReport(Report report);
    List<MyStoredProcedure> getMyStoredProcedures(String sqlCall, HashMap<String,Object> inputParams);
    List<ReportResult> getResult(String sqlCall, HashMap<String,Object> inputParams);
    
   // List<Book> getAllBooksSortedByName();
   // List<Book> getAllBooksSortedByAuthor();
   // List<Book> getAllBooksSortedByPublisher();
   // List<Book> getAllBooksSortedByYearOfPublishing();
}