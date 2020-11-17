package javademo.dao.impl;

import javademo.MySQLConUtils;
import javademo.dao.StudentDao;
import javademo.entities.Administrator;
import javademo.entities.Book;
import javademo.entities.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public boolean loginStudent(String userName, String password) throws SQLException {
        String sql = "select * from student where stu_id = ? and stu_password = ?";
        Connection ct = MySQLConUtils.getConnection();
        PreparedStatement ps = ct.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        boolean rsNext = rs.next();
        rs.close();
        ps.close();
        ct.close();
        return rsNext;
    }

    @Override
    public void addStudent(Student student) throws SQLException {

    }

    @Override
    public List<Student> queryAllStudent() throws SQLException {
        String sql = "SELECT * FROM `student`";
        Connection con = MySQLConUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Student> studentList = new ArrayList<>();
        while(rs.next()){
            Student student = new Student();
            student.setStuId(rs.getString(1));
            student.setStuName(rs.getString(2));
            student.setStuPassword(rs.getString(3));
            studentList.add(student);
        }
        rs.close();
        ps.close();
        con.close();
        return studentList;
    }

    @Override
    public Student queryStudentById(String id) throws SQLException {
        String sql = "select * from student where stu_id = ?";
        Connection ct = MySQLConUtils.getConnection();
        PreparedStatement ps = ct.prepareStatement(sql);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        Student student = new Student();
        while (rs.next()){
            student.setStuId(id);
            student.setStuName(rs.getString(2));
            student.setStuPassword(rs.getString(3));
        }
        rs.close();
        ps.close();
        ct.close();
        return student;
    }
}
