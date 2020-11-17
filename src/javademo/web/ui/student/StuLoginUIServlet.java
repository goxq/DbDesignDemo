package javademo.web.ui.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/StuLoginUIServlet")
public class StuLoginUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null){//如果本地有cookie存储着JSESSIONID，则session为原session而不生成新的
            resp.sendRedirect(req.getContextPath()+"/HomeUIServlet");
            return;
        }
        //如果session是新的，则转发到登陆界面
        req.getRequestDispatcher("/WEB-INF/jsp/student/StuLogin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
