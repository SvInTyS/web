package auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/web/WEB-INF/users.db"; // Проверь путь к БД

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                out.println("Поля не могут быть пустыми!");
                return;
            }

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)")) {

                stmt.setString(1, username);
                stmt.setString(2, password); // Пока без шифрования

                stmt.executeUpdate();
                out.println("Пользователь зарегистрирован!");
            } catch (SQLException e) {
                out.println("Ошибка базы данных: " + e.getMessage());
            }
        }
    }
}
