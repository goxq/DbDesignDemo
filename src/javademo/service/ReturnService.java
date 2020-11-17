package javademo.service;

import javademo.entities.Return;

import java.sql.SQLException;
import java.util.List;

public interface ReturnService {
    public void addReturn(Return returnX) throws SQLException;

    public void alertReturn(Return returnX) throws SQLException;

    public void deleteReturn(String stuId, String bookId) throws SQLException;

    public List<Return> searchAllReturns() throws SQLException;

    public List<Return> searchReturnsByStuId(String stuId) throws SQLException;

    public List<Return> searchReturnsByBookId(String bookId) throws SQLException;

    public boolean isReturnExist(String stuId, String bookId) throws SQLException;
}
