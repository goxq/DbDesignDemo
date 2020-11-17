package javademo.web.controller.admin.book;

import com.google.gson.Gson;
import javademo.entities.Book;
import javademo.service.BookService;
import javademo.service.impl.BookServiceImpl;
import javademo.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/BookManagementServlet")
public class BookManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user")==null){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        //和其他不一样，学生也要通过这个得到书籍列表
//        if(!session.getAttribute("permission").toString().equals("0")){
//            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
//            return;
//        }
        BookService service = new BookServiceImpl();
        List<Book> bookList = null;
        try {
            bookList = service.searchAllBooks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Integer listCount = bookList.size();

        //下面是分页
        Integer page = Integer.parseInt(req.getParameter("page"));
        Integer prePage = PageUtils.prePageControl(page);
        Integer nextPage = PageUtils.nextPageControl(page,listCount);
        Integer pages = PageUtils.pagesControl(listCount);//分的总页数
        List<Integer> pageNum = PageUtils.pageNumListControl(page,listCount);
        bookList = page.equals(pages) ? bookList.subList((page - 1) * PageUtils.A_PAGE_SIZE, listCount)
                : bookList.subList((page - 1) * PageUtils.A_PAGE_SIZE, page * PageUtils.A_PAGE_SIZE);
        resp.setHeader("Content-Type", "application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("bookList",bookList);
        map.put("allBookCount",listCount);
        map.put("prePage",prePage);
        map.put("nextPage",nextPage);
        map.put("pageNum",pageNum);
        map.put("page",page);
        String result = gson.toJson(map);
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
