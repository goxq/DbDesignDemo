package javademo.service;


import javademo.entities.Administrator;

public interface AdministratorService {
    public boolean login(String user, String password);
    public Administrator searchAdministratorById(String id);
}
