package javademo.service.impl;

import javademo.dao.BookDao;
import javademo.dao.impl.BookDaoImpl;
import javademo.entities.Book;
import javademo.service.BookService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) throws SQLException {
        bookDao.addBook(book);
    }

    @Override
    public void alertBook(Book book) throws SQLException {
        bookDao.alertBook(book);
    }

    @Override
    public void deleteBook(String bookId)throws SQLException {
        bookDao.deleteBook(bookId);
    }

    @Override
    public List<Book> searchAllBooks() throws SQLException{
        List<Book> bookList = null;
        bookList = bookDao.queryAllBooks();
        return bookList;
    }

    @Override
    public List<Book> searchBookByName(String name) throws SQLException {
        List<Book> bookList;
        bookList = bookDao.queryBookByName(name);
        return bookList;
    }

    @Override
    public boolean isIdExist(String id) throws SQLException {
        Book book = bookDao.queryBookById(id);
        return book.getName()!=null;
    }

    @Override
    public List<Book> searchBookById(String id) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Book book = bookDao.queryBookById(id);
        bookList.add(book);
        return bookList;
    }
}
