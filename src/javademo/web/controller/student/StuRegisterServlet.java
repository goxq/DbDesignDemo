package javademo.web.controller.student;

import javademo.entities.Student;
import javademo.service.AdministratorService;
import javademo.service.StudentService;
import javademo.service.impl.AdministratorServiceImpl;
import javademo.service.impl.StudentServiceImpl;
import javademo.utils.PasswordEncryptionUtils;
import javademo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/StuRegisterServlet")
public class StuRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Student student = WebUtils.request2Bean(req,Student.class);
        StudentService service = new StudentServiceImpl();
//      String stuId = req.getParameter("user");
//      String password = req.getParameter("password");
        resp.setHeader("Content-type","text/html;charset=UTF-8");

        boolean registerResult = false;
        student.setStuPassword(PasswordEncryptionUtils.plainText2MD5Encrypt(student.getStuPassword()));//加密
        try {
            registerResult = service.register(student);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(registerResult){
            resp.getWriter().write("<script language='JavaScript'>alert('注册成功');window.location.href='"+req.getContextPath()+"/StuHomeUIServlet'</script>");
        }else{
            resp.getWriter().write("<script language='JavaScript'>alert('id已存在，请更换后重试');window.location.href='"+req.getContextPath()+"/StuLoginUIServlet'</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
