package auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/apache-tomcat-9.0.105/webapps/web/WEB-INF/users.db";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

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

            try (Connection conn = DriverManager.getConnection(DB_URL)) {

                PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                checkStmt.setString(1, username);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    out.println("Пользователь с таким логином уже существует!");
                    return;
                }

                PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO users(username, password, role) VALUES (?, ?, ?)");
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.setString(3, "user"); // 👈 добавлено: роль по умолчанию

                insertStmt.executeUpdate();
                response.sendRedirect("login.jsp"); // Перенаправление после успешной регистрации

            } catch (SQLException e) {
                out.println("Ошибка базы данных: " + e.getMessage());
            }
        }
    }
}
