package javademo.service.impl;

import javademo.dao.AdministratorDao;
import javademo.dao.impl.AdministratorDaoImpl;
import javademo.entities.Administrator;
import javademo.service.AdministratorService;

import java.sql.SQLException;

public class AdministratorServiceImpl implements AdministratorService {
    AdministratorDao dao = new AdministratorDaoImpl();
    @Override
    public boolean login(String user, String password) {
        //String encryptionPassword = PasswordEncryptionUtils.plainText2MD5Encrypt(password);
        try {
            return dao.loginAdministrator(user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Administrator searchAdministratorById(String id) {
        Administrator admin = null;
        if (id == null) {
            return null;
        }
        try {
            admin = dao.queryAdministratorById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
