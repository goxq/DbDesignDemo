package javademo.service.impl;

import javademo.dao.BookDao;
import javademo.dao.BorrowDao;
import javademo.dao.impl.BookDaoImpl;
import javademo.dao.impl.BorrowDaoImpl;
import javademo.entities.Book;
import javademo.entities.Borrow;
import javademo.service.BorrowService;
import javademo.exception.*;

import java.sql.SQLException;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    BorrowDao borrowDao = new BorrowDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBorrow(Borrow borrow) throws SQLException {
        borrowDao.addBorrow(borrow);
    }

    @Override
    public void alertBorrow(Borrow borrow) throws SQLException {
        borrowDao.alertBorrow(borrow);
    }

    @Override
    public void deleteBorrow(String stuId, String bookId) throws SQLException {
        Borrow borrow = new Borrow();
        borrow.setStuId(stuId);
        borrow.setBookId(bookId);
        borrowDao.deleteBorrow(borrow);
    }

    @Override
    public List<Borrow> searchAllBorrows() throws SQLException{
        return borrowDao.queryAllBorrows();
    }

    @Override
    public List<Borrow> searchBorrowsByStuId(String stuId) throws SQLException {
        return borrowDao.queryBorrowsByStuId(stuId);
    }

    @Override
    public List<Borrow> searchBorrowsByBookId(String bookId) throws SQLException {
        return borrowDao.queryBorrowsByBookId(bookId);
    }

    @Override
    public boolean isBorrowExist(String stuId, String bookId) throws SQLException {
        Borrow borrow = new Borrow();
        borrow.setStuId(stuId);
        borrow.setBookId(bookId);
        return borrowDao.borrowIsExist(borrow);
    }

    @Override
    public void stuBorrow(Borrow borrow) throws SQLException, BookRemainingZeroException {
        Book book = bookDao.queryBookById(borrow.getBookId());
        if(book.getRemainingNum()==0)
            throw new BookRemainingZeroException();
        borrowDao.stuBorrow(borrow);
    }
}
