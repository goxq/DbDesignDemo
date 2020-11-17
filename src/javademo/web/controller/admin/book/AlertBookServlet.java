package javademo.web.controller.admin.book;
import javademo.entities.Book;
import javademo.service.BookService;
import javademo.service.impl.BookServiceImpl;
import javademo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AlertBookServlet")
public class AlertBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("0")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        Book book = WebUtils.request2Bean(req,Book.class);
        BookService service = new BookServiceImpl();
        try {
            service.alertBook(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
