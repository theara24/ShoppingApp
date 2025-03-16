package service;

import dao.UserDAO;
import model.User;
import java.sql.Connection;
import java.sql.Timestamp;

public class UserService {
    private final UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public boolean registerUser(String username, String email, String password) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(0, username, email, password, "", "", "", "user", currentTimestamp, currentTimestamp);
        return userDAO.saveUser(user);
    }

    public User loginUser(String email, String password) {
        return userDAO.getUserByEmailAndPassword(email, password);
    }
}