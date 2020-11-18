package javademo.web.controller.student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javademo.entities.Book;
import javademo.entities.Borrow;
import javademo.service.BookService;
import javademo.service.impl.BookServiceImpl;
import javademo.service.impl.BorrowServiceImpl;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/StuBorrowManagementServlet")
public class StuBorrowManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(!session.getAttribute("permission").toString().equals("2")){//不是学生权限
            resp.sendRedirect(req.getContextPath()+"/LoginUIServlet");
            return;
        }
        BorrowServiceImpl borrowService = new BorrowServiceImpl();
        List<Borrow> borrowList = null;
        try {
            borrowList = borrowService.searchBorrowsByStuId(session.getAttribute("user").toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Integer listCount = borrowList.size();

        //下面是分页
        Integer page = Integer.parseInt(req.getParameter("page"));
        Integer prePage = PageUtils.prePageControl(page);
        Integer nextPage = PageUtils.nextPageControl(page,listCount);
        Integer pages = PageUtils.pagesControl(listCount);//分的总页数
        List<Integer> pageNum = PageUtils.pageNumListControl(page,listCount);
        borrowList = page.equals(pages) ? borrowList.subList((page - 1) * PageUtils.A_PAGE_SIZE, listCount)
                : borrowList.subList((page - 1) * PageUtils.A_PAGE_SIZE, page * PageUtils.A_PAGE_SIZE);
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
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Map<String,Object> map = new HashMap<>();
        map.put("borrowList",borrowList);
        map.put("allBorrowCount",listCount);
        map.put("prePage",prePage);
        map.put("nextPage",nextPage);
        map.put("pageNum",pageNum);
        map.put("page",page);
        String result = gson.toJson(map);
        System.out.println();
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
