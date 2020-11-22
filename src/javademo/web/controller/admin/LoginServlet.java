package javademo.web.controller.admin;

import javademo.entities.Administrator;
import javademo.service.*;
import javademo.service.impl.AdministratorServiceImpl;
import javademo.utils.PasswordEncryptionUtils;
import javademo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Administrator admin = WebUtils.request2Bean(req,Administrator.class);
        AdministratorService service = new AdministratorServiceImpl();
        Integer permission = service.searchAdministratorById(admin.getUser()).getPermission();
        HttpSession session = req.getSession();
        String user = admin.getUser();
        String password = admin.getPassword();
        resp.setHeader("Content-type","text/html;charset=UTF-8");

        boolean loginResult = service.login(user, PasswordEncryptionUtils.plainText2MD5Encrypt(password));
        if(loginResult){
            if(req.getParameter("rememberMe")==null){
                //没选“记住我”，使用非cookie登录
                session.setAttribute("user",user);
                session.setAttribute("permission",permission);
                resp.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+req.getContextPath()+"/HomeUIServlet'</script>");
            }else{
                //选了记住我，存cookie
                session.setAttribute("user",user);
                session.setAttribute("permission",permission);
                session.setMaxInactiveInterval(30 * 60);//session保存半小时
                Cookie cookie = new Cookie("JSESSIONID", session.getId());
                cookie.setMaxAge(30 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
                resp.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='"+req.getContextPath()+"/HomeUIServlet'</script>");
            }

        }else{
            resp.getWriter().write("<script language='JavaScript'>alert('您的用户名或密码有误，请重新输入或者注册');window.location.href='"+req.getContextPath()+"/LoginUIServlet'</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
