package javademo.web.controller.student;

import javademo.entities.Book;
import javademo.entities.Borrow;
import javademo.exception.BookRemainingZeroException;
import javademo.service.BookService;
import javademo.service.BorrowService;
import javademo.service.impl.BookServiceImpl;
import javademo.service.impl.BorrowServiceImpl;
import javademo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("2")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        resp.setCharacterEncoding("UTF-8");
        Borrow borrow = WebUtils.request2Bean(req,Borrow.class);
        java.util.Date date = new java.util.Date();
        borrow.setBorrowDate(new java.sql.Date(date.getTime()));
        BorrowService borrowService = new BorrowServiceImpl();
        try{
            borrowService.stuBorrow(borrow);
        } catch (BookRemainingZeroException brze){
            resp.setStatus(401);
            resp.getWriter().print("书籍余量为0，请刷新后重试");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
