package javademo.service;

import javademo.entities.Book;
import javademo.entities.Borrow;
import org.apache.commons.beanutils.converters.SqlDateConverter;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    public void addBook(Book book) throws SQLException;

    public void alertBook(Book book) throws SQLException;

    public void deleteBook(String bookId) throws SQLException;

    public List<Book> searchAllBooks() throws SQLException;

    public List<Book> searchBookByName(String name) throws SQLException;

    public boolean isIdExist(String id) throws SQLException;

    public List<Book> searchBookById(String id) throws SQLException;

}
