package dao;

import model.Admin;
import java.sql.*;

public class AdminDAO {
    private Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    // Get admin by email for login
    public Admin getAdminByEmail(String email) {
        String query = "SELECT * FROM admins WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
