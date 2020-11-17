package javademo.web.controller.admin.borrow;

import com.google.gson.Gson;
import javademo.entities.Book;
import javademo.entities.Borrow;
import javademo.entities.Return;
import javademo.service.BookService;
import javademo.service.BorrowService;
import javademo.service.ReturnService;
import javademo.service.impl.BookServiceImpl;
import javademo.service.impl.BorrowServiceImpl;
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
import java.util.*;

@WebServlet("/SearchBorrowServlet")
public class SearchBorrowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("0")){
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        Integer page = Integer.parseInt(req.getParameter("page"));
        String search = req.getParameter("search");
        BorrowService borrowService = new BorrowServiceImpl();
        List<Borrow> borrowList = new ArrayList<>();
        Integer listCount = null;
        if(search.matches("\\d+")){
            try {
                borrowList = borrowService.searchBorrowsByStuId(search);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            listCount = borrowList.size();
        }else {
            try {
                String s = search.substring(1);
                borrowList = borrowService.searchBorrowsByBookId(s);//前端搜索book_id时前要加字母b
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            listCount = borrowList.size();
        }
        Integer pages = PageUtils.pagesControl(listCount);//分的总页数
        List<Integer> pageNum = PageUtils.pageNumListControl(page,listCount);
        if(listCount!=0){
            borrowList = page.equals(pages) ? borrowList.subList((page - 1) * PageUtils.A_PAGE_SIZE, listCount)
                    : borrowList.subList((page - 1) * PageUtils.A_PAGE_SIZE, page * PageUtils.A_PAGE_SIZE);
        }
        Integer prePage = PageUtils.prePageControl(page);
        Integer nextPage = PageUtils.nextPageControl(page,listCount);
        for (Borrow borrow : borrowList) {
            Date nowDate = new Date();
            if(borrow.getRealReturnDate()!=null){
                if(borrow.getRealReturnDate().after(borrow.getExpectReturnDate())){
                    //逾期了
                    borrow.setIsExceeded("逾期");
                }else {
                    borrow.setIsExceeded("未逾期");
                }
            }else{
                if(nowDate.after(borrow.getExpectReturnDate())){
                    //逾期了
                    borrow.setIsExceeded("逾期");
                }else{
                    borrow.setIsExceeded("未逾期");
                }
            }
        }
        resp.setHeader("Content-Type", "application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("borrowList",borrowList);
        map.put("allBorrowCount",listCount);
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
