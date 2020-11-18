package javademo.web.controller.admin.returni;

import javademo.entities.Book;
import javademo.entities.Borrow;
import javademo.entities.Return;
import javademo.exception.BookRemainingZeroException;
import javademo.exception.BorrowIsExistException;
import javademo.exception.DateException;
import javademo.exception.ReturnIsExistException;
import javademo.service.BookService;
import javademo.service.BorrowService;
import javademo.service.ReturnService;
import javademo.service.impl.BookServiceImpl;
import javademo.service.impl.BorrowServiceImpl;
import javademo.service.impl.ReturnServiceImpl;
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

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("0")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        resp.setCharacterEncoding("UTF-8");
        Return returni = WebUtils.request2Bean(req,Return.class);
        ReturnService returnService = new ReturnServiceImpl();
        try{
            returnService.stuReturn(returni);
        }catch (ReturnIsExistException riee){
            resp.setStatus(401);
            resp.getWriter().print("已经归还，请勿重复归还");
        }
        catch (DateException de){
            resp.setStatus(401);
            resp.getWriter().print("日期错误");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
