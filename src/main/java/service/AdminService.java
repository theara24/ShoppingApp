// File: src/main/java/service/AdminService.java
package service;

import dao.AdminDAO;
import model.Admin;
import java.sql.Connection;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService(Connection connection) {
        this.adminDAO = new AdminDAO(connection);
    }

    public Admin loginAdmin(String email, String password) {
        return adminDAO.findAdminByEmailAndPassword(email, password);
    }
}