package admin;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteItemServlet extends HttpServlet {

    private String DB_URL;

    @Override
    public void init() {
        String path = getServletContext().getRealPath("/WEB-INF/users.db");
        DB_URL = "jdbc:sqlite:" + path;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"admin".equals(role)) {
            response.sendRedirect("catalog");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM items WHERE id = ?")) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            response.sendRedirect("admin");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный ID");
        } catch (SQLException e) {
            throw new ServletException("Ошибка удаления товара", e);
        }
    }
}