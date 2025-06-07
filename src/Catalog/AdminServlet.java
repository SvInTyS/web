package catalog;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Item;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (!"admin".equals(role)) {
            response.sendRedirect("catalog");
            return;
        }

        // 🔧 Перемещаем вызов внутрь метода
        String dbPath = getServletContext().getRealPath("/WEB-INF/users.db");
        String DB_URL = "jdbc:sqlite:" + dbPath;

        List<Item> items = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM items")) {

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setImage(rs.getString("image"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new ServletException("Ошибка загрузки товаров", e);
        }

        request.setAttribute("items", items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}