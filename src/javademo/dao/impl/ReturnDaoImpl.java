package javademo.dao.impl;

import javademo.MySQLConUtils;
import javademo.dao.ReturnDao;
import javademo.entities.Return;
import javademo.entities.Return;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnDaoImpl implements ReturnDao {
    @Override
    public List<Return> queryAllReturns() throws SQLException {
        String sql = "select * from returni natural join borrow natural join student natural join book";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Return> returniList = new ArrayList<>();
        while(rs.next()){
            Return returni = new Return();
            returni.setStuId(rs.getString(2));
            returni.setBookId(rs.getString(1));
            returni.setRealReturnDate(rs.getDate(3));
            returni.setBorrowDate(rs.getDate(4));
            returni.setExpectReturnDate(rs.getDate(5));
            returni.setBookName(rs.getString(8));
            returni.setStuName(rs.getString(6));
            returniList.add(returni);
        }
        rs.close();
        ps.close();
        con.close();
        return returniList;
    }

    @Override
    public List<Return> queryReturnsByStuId(String stuId) throws SQLException {
        String sql = "select * from returni natural join borrow natural join student natural join book where stu_id = ? ";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,stuId);
        ResultSet rs = ps.executeQuery();
        List<Return> returniList = new ArrayList<>();
        while(rs.next()){
            Return returni = new Return();
            returni.setStuId(rs.getString(2));
            returni.setBookId(rs.getString(1));
            returni.setRealReturnDate(rs.getDate(3));
            returni.setBorrowDate(rs.getDate(4));
            returni.setExpectReturnDate(rs.getDate(5));
            returni.setBookName(rs.getString(8));
            returni.setStuName(rs.getString(6));
            returniList.add(returni);
        }
        rs.close();
        ps.close();
        con.close();
        return returniList;
    }

    @Override
    public List<Return> queryReturnsByBookId(String bookId) throws SQLException {
        String sql = "select * from returni natural join borrow natural join student natural join book where book_id = ? ";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,bookId);
        ResultSet rs = ps.executeQuery();
        List<Return> returniList = new ArrayList<>();
        while(rs.next()){
            Return returni = new Return();
            returni.setStuId(rs.getString(2));
            returni.setBookId(rs.getString(1));
            returni.setRealReturnDate(rs.getDate(3));
            returni.setBorrowDate(rs.getDate(4));
            returni.setExpectReturnDate(rs.getDate(5));
            returni.setBookName(rs.getString(8));
            returni.setStuName(rs.getString(6));
            returniList.add(returni);
        }
        rs.close();
        ps.close();
        con.close();
        return returniList;
    }

    @Override
    public void addReturn(Return returni) throws SQLException {
        String sql = "INSERT INTO return (`stu_id`, `book_id`,`real_return_date`) VALUES (?,?,?)";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, returni.getStuId());
        ps.setString(2, returni.getBookId());
        ps.setDate(3, returni.getRealReturnDate());
        
        ps.execute();

        ps.close();
        con.close();
    }

    @Override
    public void alertReturn(Return returni) throws SQLException {
        String sql = "UPDATE return SET real_return_date = ? WHERE stu_id = ? AND book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, returni.getRealReturnDate());
        ps.setString(2, returni.getStuId());
        ps.setString(3, returni.getBookId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    @Override
    public void deleteReturn(Return returni) throws SQLException {
        String sql = "DELETE FROM return WHERE stu_id = ? AND book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, returni.getStuId());
        ps.setString(2, returni.getBookId());

        ps.execute();

        ps.close();
        con.close();
    }

    @Override
    public boolean ReturnIsExist(Return returni) throws SQLException {
        String sql = "SELECT * FROM return WHERE stu_id = ? AND book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, returni.getStuId());
        ps.setString(2,returni.getBookId());

        ResultSet rs = ps.executeQuery();
        boolean rsNext = rs.next();

        rs.close();
        ps.close();
        con.close();
        return rsNext;
    }
}
