package javademo.service.impl;

import javademo.dao.ReturnDao;
import javademo.dao.impl.ReturnDaoImpl;
import javademo.entities.Return;
import javademo.service.ReturnService;

import java.sql.SQLException;
import java.util.List;

public class ReturnServiceImpl implements ReturnService {
    ReturnDao dao = new ReturnDaoImpl();
    @Override
    public void addReturn(Return returnX) throws SQLException {
        dao.addReturn(returnX);
    }

    @Override
    public void alertReturn(Return returnX) throws SQLException {
        dao.alertReturn(returnX);
    }

    @Override
    public void deleteReturn(String stuId, String bookId) throws SQLException {
        Return returnX = new Return();
        returnX.setStuId(stuId);
        returnX.setBookId(bookId);
        dao.deleteReturn(returnX);
    }

    @Override
    public List<Return> searchAllReturns() throws SQLException {
        return dao.queryAllReturns();
    }

    @Override
    public List<Return> searchReturnsByStuId(String stuId) throws SQLException {
        return dao.queryReturnsByStuId(stuId);
    }

    @Override
    public List<Return> searchReturnsByBookId(String bookId) throws SQLException {
        return dao.queryReturnsByBookId(bookId);
    }

    @Override
    public boolean isReturnExist(String stuId, String bookId) throws SQLException {
        Return returnX = new Return();
        returnX.setStuId(stuId);
        returnX.setBookId(bookId);
        return dao.ReturnIsExist(returnX);
    }
}
