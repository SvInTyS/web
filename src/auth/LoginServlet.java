package auth;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/web/WEB-INF/users.db";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // 1. Создание сессии
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // 2. Получаем роль из БД (или по умолчанию "user")
                String role = rs.getString("role");
                session.setAttribute("role", role != null ? role : "user");

                // 3. Перенаправляем на каталог или главную
                response.sendRedirect("catalog.jsp"); // или другой путь
            } else {
                // Неверные данные
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("Неверный логин или пароль. <a href='login.jsp'>Назад</a>");
            }

        } catch (SQLException e) {
            throw new ServletException("Ошибка при подключении к базе данных", e);
        }
    }
}