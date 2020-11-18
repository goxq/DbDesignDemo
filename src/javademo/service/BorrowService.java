package javademo.service;

import javademo.entities.Borrow;
import javademo.exception.BookRemainingZeroException;
import javademo.exception.BorrowIsExistException;
import javademo.exception.DateException;

import java.sql.SQLException;
import java.util.List;

public interface BorrowService {
    public void addBorrow(Borrow borrow) throws SQLException;

    public void alertBorrow(Borrow borrow) throws SQLException;

    public void deleteBorrow(String stuId, String bookId) throws SQLException;

    public List<Borrow> searchAllBorrows() throws SQLException;

    public List<Borrow> searchBorrowsByStuId(String stuId) throws SQLException;

    public List<Borrow> searchBorrowsByBookId(String bookId) throws SQLException;

    public boolean isBorrowExist(String stuId, String bookId) throws SQLException;

    public void stuBorrow(Borrow borrow) throws SQLException, BookRemainingZeroException, BorrowIsExistException, DateException;
}
