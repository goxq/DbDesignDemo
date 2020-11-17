package javademo.dao.impl;

import javademo.MySQLConUtils;
import javademo.dao.BookDao;
import javademo.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> queryAllBooks() throws SQLException {
        String sql = "SELECT * FROM `book`";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while(rs.next()){
            Book book = new Book();
            book.setId(rs.getString(1));
            book.setName(rs.getString(2));
            book.setType(rs.getString(3));
            book.setAuthor(rs.getString(4));
            book.setPress(rs.getString(5));
            book.setTotalNum(rs.getInt(6));
            book.setRemainingNum(rs.getInt(7));
            book.setPrice(rs.getDouble(8));
            bookList.add(book);
        }
        rs.close();
        ps.close();
        con.close();
        return bookList;
    }

    @Override
    public Book queryBookById(String id) throws SQLException {
        String sql = "SELECT * FROM `book` where book_id = ? ";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        Book book = new Book();
        while(rs.next()){
            book.setId(rs.getString(1));
            book.setName(rs.getString(2));
            book.setType(rs.getString(3));
            book.setAuthor(rs.getString(4));
            book.setPress(rs.getString(5));
            book.setTotalNum(rs.getInt(6));
            book.setRemainingNum(rs.getInt(7));
            book.setPrice(rs.getDouble(8));
        }
        rs.close();
        ps.close();
        con.close();
        return book;
    }

    @Override
    public List<Book> queryBookByName(String name) throws SQLException {
        String sql = "SELECT * FROM `book` where book_name like ? ";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,"%"+name+"%");
        ResultSet rs = ps.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while(rs.next()){
            Book book = new Book();
            book.setId(rs.getString(1));
            book.setName(rs.getString(2));
            book.setType(rs.getString(3));
            book.setAuthor(rs.getString(4));
            book.setPress(rs.getString(5));
            book.setTotalNum(rs.getInt(6));
            book.setRemainingNum(rs.getInt(7));
            book.setPrice(rs.getDouble(8));
            bookList.add(book);
        }
        rs.close();
        ps.close();
        con.close();
        return bookList;
    }

    @Override
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (`book_id`, `book_name`, `book_type`,`book_author`,`book_press`,`book_total_num`,`book_remaining_num`,`book_price`) VALUES (?,?,?,?,?,?,?,?)";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, book.getId());
        ps.setString(2, book.getName());
        ps.setString(3, book.getType());
        ps.setString(4, book.getAuthor());
        ps.setString(5, book.getPress());
        ps.setInt(6, book.getTotalNum());
        ps.setInt(7, book.getTotalNum());
        ps.setDouble(8,book.getPrice());

        ps.execute();

        ps.close();
        con.close();
    }

    @Override
    public void alertBook(Book book) throws SQLException {//不能改id
        String sql = "UPDATE book SET book_name = ?, book_type = ?,book_author = ?,book_press = ?,book_total_num = ?,book_remaining_num = ?,book_price = ? WHERE book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, book.getName());
        ps.setString(2, book.getType());
        ps.setString(3, book.getAuthor());
        ps.setString(4, book.getPress());
        ps.setInt(5, book.getTotalNum());
        ps.setInt(6, book.getRemainingNum());
        ps.setDouble(7,book.getPrice());
        ps.setString(8,book.getId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    @Override
    public void deleteBook(String id) throws SQLException {
        String sql = "DELETE FROM book WHERE book_id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);

        ps.execute();

        ps.close();
        con.close();
    }

    @Override
    public boolean bookIsExist(String id) throws SQLException {
        String sql = "SELECT * FROM book WHERE id = ?";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        boolean rsNext = rs.next();

        rs.close();
        ps.close();
        con.close();
        return rsNext;
    }
}
