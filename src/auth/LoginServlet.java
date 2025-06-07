package auth;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/apache-tomcat-9.0.105/webapps/web/WEB-INF/users.db";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Проверка логина: " + username + ", пароль: " + password);

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                String role = rs.getString("role");
                session.setAttribute("role", role != null ? role : "user");

                System.out.println("Вход выполнен успешно. Роль: " + role);
                response.sendRedirect("catalog.jsp");

            } else {
                request.setAttribute("error", "Неверный логин или пароль");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Ошибка при подключении к базе данных", e);
        }
    }
}
