package javademo.web.controller.student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/StuReturnManagementServlet")
public class StuReturnManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("2")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        ReturnService returnService = new ReturnServiceImpl();
        List<Return> returnList = null;
        try {
            returnList = returnService.searchReturnsByStuId(session.getAttribute("user").toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Integer listCount = returnList.size();
        System.out.println(returnList);
        //下面是分页
        Integer page = Integer.parseInt(req.getParameter("page"));
        Integer prePage = PageUtils.prePageControl(page);
        Integer nextPage = PageUtils.nextPageControl(page,listCount);
        Integer pages = PageUtils.pagesControl(listCount);//分的总页数
        List<Integer> pageNum = PageUtils.pageNumListControl(page,listCount);
        returnList = page.equals(pages) ? returnList.subList((page - 1) * PageUtils.A_PAGE_SIZE, listCount)
                : returnList.subList((page - 1) * PageUtils.A_PAGE_SIZE, page * PageUtils.A_PAGE_SIZE);
        resp.setHeader("Content-Type", "application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Map<String,Object> map = new HashMap<>();
        map.put("returnList",returnList);
        map.put("allReturnCount",listCount);
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
