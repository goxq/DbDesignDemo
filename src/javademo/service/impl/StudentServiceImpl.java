package javademo.service.impl;

import javademo.dao.StudentDao;
import javademo.dao.impl.StudentDaoImpl;
import javademo.entities.Administrator;
import javademo.entities.Student;
import javademo.service.StudentService;

import java.sql.SQLException;

public class StudentServiceImpl implements StudentService {
    StudentDao dao = new StudentDaoImpl();
    @Override
    public boolean login(String user, String password)throws SQLException {
        //String encryptionPassword = PasswordEncryptionUtils.plainText2MD5Encrypt(password);
        return dao.loginStudent(user, password);
    }

    @Override
    public Student searchStudentById(String id) {
        Student student = null;
        if (id == null) {
            return null;
        }
        try {
            student = dao.queryStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public boolean register(Student student) throws SQLException {
        if(dao.isStuExist(student))
            return false;
        dao.addStudent(student);
        return true;
    }
}
