package javademo.service;

import javademo.entities.Administrator;
import javademo.entities.Student;

import java.sql.SQLException;

public interface StudentService {
    public boolean login(String user, String password) throws SQLException;
    public Student searchStudentById(String id);
    public boolean register(Student student) throws SQLException;
}
