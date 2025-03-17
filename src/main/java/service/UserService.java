package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) throws SQLException {
        userDAO.save(user);
    }

    public User getUserById(Long id) throws SQLException {
        return userDAO.findById(id);
    }
}