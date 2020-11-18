package javademo.dao;

import javademo.entities.Return;

import java.sql.SQLException;
import java.util.List;

public interface ReturnDao {
    public List<Return> queryAllReturns() throws SQLException;

    public List<Return> queryReturnsByStuId(String stuId) throws SQLException;

    public List<Return> queryReturnsByBookId(String bookId) throws SQLException;

    public void addReturn(Return returnX) throws SQLException;

    public void alertReturn(Return returnX) throws SQLException;

    public void deleteReturn(Return returnX) throws SQLException;

    public boolean returnIsExist(Return returnX) throws SQLException;

    public void stuReturn(Return returni) throws SQLException;
}
