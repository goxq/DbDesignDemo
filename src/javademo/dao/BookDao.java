package javademo.dao;

import javademo.entities.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public List<Book> queryAllBooks() throws SQLException;

    public Book queryBookById(String id) throws SQLException;

    public List<Book> queryBookByName(String name) throws SQLException;

    public void addBook(Book book) throws SQLException;

    public void alertBook(Book book) throws SQLException;

    public void deleteBook(String id) throws SQLException;

    public boolean bookIsExist(String id) throws SQLException;
}
