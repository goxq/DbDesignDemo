package javademo.dao.impl;

import javademo.MySQLConUtils;
import javademo.dao.AdministratorDao;
import javademo.entities.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdministratorDaoImpl implements AdministratorDao {
    @Override
    public boolean loginAdministrator(String userName, String password) throws SQLException {
        String sql = "select * from administrator where admin_id = ? and admin_password = ?";
        Connection ct = MySQLConUtils.getConnection();
        PreparedStatement ps = ct.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        boolean rsNext = rs.next();
        rs.close();
        ps.close();
        ct.close();
        return rsNext;
    }

    @Override
    public void addAdministrator(Administrator admin) throws SQLException {

    }

    @Override
    public List<Administrator> queryAllAdministrator() throws SQLException {
        return null;
    }

    @Override
    public Administrator queryAdministratorById(String id) throws SQLException {
        String sql = "select * from administrator where admin_id = ?";
        Connection ct = MySQLConUtils.getConnection();
        PreparedStatement ps = ct.prepareStatement(sql);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        Administrator admin = new Administrator();
        while (rs.next()){
            admin.setUser(id);
            admin.setPermission(rs.getInt(3));
        }
        rs.close();
        ps.close();
        ct.close();
        return admin;
    }
}
