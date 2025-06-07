package catalog;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteItemServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/apache-tomcat-9.0.105/webapps/web/WEB-INF/users.db";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"admin".equals(role)) {
            response.sendRedirect("catalog");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Ошибка удаления товара", e);
        }

        response.sendRedirect("admin");
    }
}