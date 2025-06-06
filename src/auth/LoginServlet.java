package auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/web/WEB-INF/users.db"; // путь к users.db

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement(
                         "SELECT * FROM users WHERE username = ? AND password = ?")) {

                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    out.println("Успешный вход! Добро пожаловать, " + username);
                } else {
                    out.println("Неверный логин или пароль.");
                }

            } catch (SQLException e) {
                out.println("Ошибка базы данных: " + e.getMessage());
            }
        }
    }
}
