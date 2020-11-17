package javademo.web.ui.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginUIServlet")
public class LoginUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null){//如果本地有cookie存储着JSESSIONID，则session为原session而不生成新的
            resp.sendRedirect(req.getContextPath()+"/HomeUIServlet");
            return;
        }
        //如果session是新的，则转发到登陆界面
        req.getRequestDispatcher("/WEB-INF/jsp/admin/Login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
