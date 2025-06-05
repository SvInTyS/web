package auth;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/1kusrmaga/2sem/web/users.db"; // укажем позже

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                response.getWriter().println("Успешный вход, привет " + username);
            } else {
                response.getWriter().println("Неверный логин или пароль.");
            }

        } catch (SQLException e) {
            response.getWriter().println("Ошибка входа: " + e.getMessage());
        }
    }
}