package javademo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/DbTest")
public class DbTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try{
//            resp.setContentType("text/html;charset=UTF-8");
//            Connection con = MySQLConUtils.getConnection();
//            PreparedStatement ps = con.prepareStatement("select * from student");
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            String a = rs.getString(1);
//            PrintWriter out = resp.getWriter();
//            out.println(a);
//            out.println(req.getContextPath());
//
//            rs.close();
//            ps.close();
//            con.close();
//        }catch (SQLException s){
//            s.printStackTrace();
//        }
        resp.setStatus(300);
    }
}
