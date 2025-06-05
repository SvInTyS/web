package auth;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/1kusrmaga/2sem/web/users.db"; // укажем позже

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            response.getWriter().println("Ошибка регистрации: " + e.getMessage());
        }
    }
}