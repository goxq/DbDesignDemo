package javademo.web.controller.admin.book;

import com.google.gson.Gson;
import javademo.service.BookService;
import javademo.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/BookIdIsExistServlet")
public class BookIdIsExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("0")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        BookService service = new BookServiceImpl();
        Map<String,String> map = new HashMap<>();
        try {
            boolean isExist = service.isIdExist(id.toString());
            if (isExist)
                map.put("error","ID已被占用");
            else
                map.put("ok","id能够使用！");
            Gson gson = new Gson();
            String res = gson.toJson(map);
            resp.setHeader("Content-Type", "application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
