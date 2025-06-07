package admin;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class AddItemServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/web/WEB-INF/users.db";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Защита от неадминов
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String image = request.getParameter("image");

        try {
            double price = Double.parseDouble(priceStr);

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO items (name, description, price, image) VALUES (?, ?, ?, ?)")) {

                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setString(4, image);
                stmt.executeUpdate();

                // После добавления — редирект обратно в админку
                response.sendRedirect("admin");

            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Неверный формат цены.");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при добавлении товара", e);
        }
    }
}