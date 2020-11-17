package javademo.web.ui.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BookManagementUIServlet")
public class BookManagementUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        if(!session.getAttribute("permission").toString().equals("0")){//不是管理员权限
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/admin/BookManagement.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
