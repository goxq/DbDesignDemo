package javademo.web.controller.student;

import com.google.gson.Gson;
import javademo.entities.Book;
import javademo.entities.Return;
import javademo.service.BookService;
import javademo.service.ReturnService;
import javademo.service.impl.BookServiceImpl;
import javademo.service.impl.ReturnServiceImpl;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/StuSearchReturnServlet")
public class StuSearchReturnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("2")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        Integer page = Integer.parseInt(req.getParameter("page"));
        String search = req.getParameter("search");
        ReturnService returnService = new ReturnServiceImpl();
        List<Return> returnList = new ArrayList<>();
        Integer listCount = null;
        try {
                returnList = returnService.searchReturnsByBookId(search);
        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        listCount = returnList.size();
        Integer pages = PageUtils.pagesControl(listCount);//分的总页数
        List<Integer> pageNum = PageUtils.pageNumListControl(page,listCount);
        if(listCount!=0){
            returnList = page.equals(pages) ? returnList.subList((page - 1) * PageUtils.A_PAGE_SIZE, listCount)
                    : returnList.subList((page - 1) * PageUtils.A_PAGE_SIZE, page * PageUtils.A_PAGE_SIZE);
        }
        Integer prePage = PageUtils.prePageControl(page);
        Integer nextPage = PageUtils.nextPageControl(page,listCount);
        resp.setHeader("Content-Type", "application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("returnList",returnList);
        map.put("allReturnCount",listCount);
        map.put("prePage",prePage);
        map.put("nextPage",nextPage);
        map.put("pageNum",pageNum);
        map.put("page",page);
        map.put("search",search);
        String result = gson.toJson(map);
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
