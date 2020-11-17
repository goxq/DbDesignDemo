package javademo.dao.impl;

import javademo.MySQLConUtils;
import javademo.dao.BorrowDao;
import javademo.entities.Book;
import javademo.entities.Borrow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {
    @Override
    public List<Borrow> queryAllBorrows() throws SQLException {
        String sql = "select*from borrow natural join book natural join student natural left outer join returni";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while(rs.next()){
            Borrow borrow = new Borrow();
            borrow.setStuId(rs.getString(1));
            borrow.setStuName(rs.getString(12));
            borrow.setBookId(rs.getString(2));
            borrow.setBookName(rs.getString(5));
            borrow.setBorrowDate(rs.getDate(3));
            borrow.setExpectReturnDate(rs.getDate(4));
            borrow.setRealReturnDate(rs.getDate(14));
            borrowList.add(borrow);
        }
        rs.close();
        ps.close();
        con.close();
        return borrowList;
    }

    @Override
    public List<Borrow> queryBorrowsByStuId(String stuId) throws SQLException {
        String sql = "select*from borrow natural join book natural join student natural left outer join returni where stu_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,stuId);
        ResultSet rs = ps.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while(rs.next()){
            Borrow borrow = new Borrow();
            borrow.setStuId(rs.getString(1));
            borrow.setStuName(rs.getString(12));
            borrow.setBookId(rs.getString(2));
            borrow.setBookName(rs.getString(5));
            borrow.setBorrowDate(rs.getDate(3));
            borrow.setExpectReturnDate(rs.getDate(4));
            borrow.setRealReturnDate(rs.getDate(14));
            borrowList.add(borrow);
        }
        rs.close();
        ps.close();
        con.close();
        return borrowList;
    }

    @Override
    public List<Borrow> queryBorrowsByBookId(String bookId) throws SQLException {
        String sql = "select*from borrow natural join book natural join student natural left outer join returni where book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,bookId);
        ResultSet rs = ps.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while(rs.next()){
            Borrow borrow = new Borrow();
            borrow.setStuId(rs.getString(1));
            borrow.setStuName(rs.getString(12));
            borrow.setBookId(rs.getString(2));
            borrow.setBookName(rs.getString(5));
            borrow.setBorrowDate(rs.getDate(3));
            borrow.setExpectReturnDate(rs.getDate(4));
            borrow.setRealReturnDate(rs.getDate(14));
            borrowList.add(borrow);
        }
        rs.close();
        ps.close();
        con.close();
        return borrowList;
    }

    @Override
    public void addBorrow(Borrow borrow) throws SQLException {
        String sql = "INSERT INTO borrow (`stu_id`, `book_id`, `borrow_date`,`expect_return_date`) VALUES (?,?,?,?)";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, borrow.getStuId());
        ps.setString(2, borrow.getBookId());
        ps.setDate(3, borrow.getBorrowDate());
        ps.setDate(4, borrow.getExpectReturnDate());

        ps.execute();

        ps.close();
        con.close();
    }

    @Override
    public void alertBorrow(Borrow borrow) throws SQLException {//book_id,stu_id不能改
        String sql = "UPDATE borrow SET borrow_date = ?,expect_return_date = ? WHERE stu_id = ? AND book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, borrow.getBorrowDate());
        ps.setDate(2, borrow.getExpectReturnDate());
        ps.setString(3, borrow.getStuId());
        ps.setString(4, borrow.getBookId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    @Override
    public void deleteBorrow(Borrow borrow) throws SQLException {
        String sql = "DELETE FROM borrow WHERE stu_id = ? AND book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, borrow.getStuId());
        ps.setString(2, borrow.getBookId());

        ps.execute();

        ps.close();
        con.close();
    }

    @Override
    public boolean borrowIsExist(Borrow borrow) throws SQLException {
        String sql = "SELECT * FROM book WHERE stu_id = ? AND book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, borrow.getStuId());
        ps.setString(2,borrow.getBookId());

        ResultSet rs = ps.executeQuery();
        boolean rsNext = rs.next();

        rs.close();
        ps.close();
        con.close();
        return rsNext;
    }

    @Override
    public void stuBorrow(Borrow borrow) throws SQLException {
        String stuId = borrow.getStuId();
        String bookId = borrow.getBookId();
        java.sql.Date borrowDate = borrow.getBorrowDate();
        java.sql.Date expectReturnDate = borrow.getExpectReturnDate();
        String sql1 = "INSERT INTO borrow (`stu_id`, `book_id`, `borrow_date`,`expect_return_date`) VALUES ("+stuId+","+bookId+",\""+borrowDate.toString()+"\",\""+expectReturnDate.toString()+"\")";
        String sql2 = "update book set book_remaining_num = book_remaining_num - 1 where book_id =" + bookId;
        Connection con = MySQLConUtils.getConnection();
        Statement sm = con.createStatement();
        sm.addBatch(sql1);
        sm.addBatch(sql2);
        sm.executeBatch();

        sm.close();
        con.close();
    }
}
