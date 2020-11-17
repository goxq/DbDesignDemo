package javademo.web.ui.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/HomeUIServlet")
public class HomeUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user")!=null){
            //已经登录，直接进入页面
            if(session.getAttribute("permission").toString().equals("0"))
                req.getRequestDispatcher("/WEB-INF/jsp/admin/Main.jsp").forward(req,resp);
            if(session.getAttribute("permission").toString().equals("2")){
                req.getRequestDispatcher("/WEB-INF/jsp/student/StuMain.jsp").forward(req,resp);
            }

        }else{
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
