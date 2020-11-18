package javademo.dao;

import javademo.entities.Book;
import javademo.entities.Borrow;

import java.sql.SQLException;
import java.util.List;

public interface BorrowDao {
    public List<Borrow> queryAllBorrows() throws SQLException;

    public List<Borrow> queryBorrowsByStuId(String stuId) throws SQLException;

    public List<Borrow> queryBorrowsByBookId(String bookId) throws SQLException;

    public Borrow queryBorrowByBookIdAndStuId(String stuId,String bookId) throws SQLException;

    public void addBorrow(Borrow borrow) throws SQLException;

    public void alertBorrow(Borrow borrow) throws SQLException;

    public void deleteBorrow(Borrow borrow) throws SQLException;

    public boolean borrowIsExist(Borrow borrow) throws SQLException;

    public void stuBorrow(Borrow borrow) throws SQLException;

}
