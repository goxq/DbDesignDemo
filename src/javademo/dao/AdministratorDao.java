package javademo.dao;

import javademo.entities.Administrator;

import java.sql.SQLException;
import java.util.List;

public interface AdministratorDao {
    public boolean loginAdministrator(String userName, String password) throws SQLException;
    public void addAdministrator(Administrator admin) throws SQLException;
    public List<Administrator> queryAllAdministrator() throws SQLException;
    public Administrator queryAdministratorById(String id) throws SQLException;
}
