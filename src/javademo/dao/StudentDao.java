package javademo.dao;

import javademo.entities.Administrator;
import javademo.entities.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    public boolean loginStudent(String userName, String password) throws SQLException;
    public void addStudent(Student student) throws SQLException;
    public List<Student> queryAllStudent() throws SQLException;
    public Student queryStudentById(String id) throws SQLException;
    public boolean isStuExist(Student student) throws SQLException;
}
