package javademo.web.ui.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/StuBookManagementUIServlet")
public class StuBookManagementUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        if(!session.getAttribute("permission").toString().equals("2")){//不是学生权限
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/student/StuBookManagement.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
