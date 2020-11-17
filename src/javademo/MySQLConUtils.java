package javademo;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConUtils {
    public static Connection getConnection(){
        Connection ct = null;
        try{
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.得到链接
            ct= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_design?useUnicode = true " +
                    "& characterEncoding = utf-8&useSSL = false&serverTimezone = Asia/Shanghai","root","root");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ct;
    }
}
