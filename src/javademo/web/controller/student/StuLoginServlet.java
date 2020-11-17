package javademo.web.controller.student;

import javademo.entities.Student;
import javademo.service.AdministratorService;
import javademo.service.StudentService;
import javademo.service.impl.AdministratorServiceImpl;
import javademo.service.impl.StudentServiceImpl;
import javademo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/StuLoginServlet")
public class StuLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Student student = WebUtils.request2Bean(req,Student.class);
        StudentService service = new StudentServiceImpl();
        Integer permission = 2;
        HttpSession session = req.getSession();
        String stuId = req.getParameter("user");
        String password = req.getParameter("password");
        resp.setHeader("Content-type","text/html;charset=UTF-8");

        boolean loginResult = false;
        try {
            loginResult = service.login(stuId,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(loginResult){
            if(req.getParameter("rememberMe")==null){
                //没选“记住我”，使用非cookie登录
                session.setAttribute("user",stuId);
                session.setAttribute("permission",permission);
                resp.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+req.getContextPath()+"/StuHomeUIServlet'</script>");
            }else{
                //选了记住我，存cookie
                session.setAttribute("user",stuId);
                session.setAttribute("permission",permission);
                session.setMaxInactiveInterval(30 * 60);//session保存半小时
                Cookie cookie = new Cookie("JSESSIONID", session.getId());
                cookie.setMaxAge(30 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
                resp.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+req.getContextPath()+"/StuHomeUIServlet'</script>");
            }

        }else{
            resp.getWriter().write("<script language='JavaScript'>alert('您的用户名或密码有误，请重新输入或者注册');window.location.href='"+req.getContextPath()+"/StuLoginUIServlet'</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
