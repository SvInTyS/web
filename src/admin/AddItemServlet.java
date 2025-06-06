package admin;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddItemServlet extends HttpServlet {

    private String DB_URL;

    @Override
    public void init() {
        String path = getServletContext().getRealPath("/WEB-INF/users.db");
        DB_URL = "jdbc:sqlite:" + path;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // <-- важная строка
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"admin".equals(role)) {
            response.sendRedirect("catalog");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO items (name, description, price, image) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setString(4, image);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Ошибка добавления товара", e);
        }

        response.sendRedirect("admin");
    }
}