package admin;

import model.Item;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class EditItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dbPath = getServletContext().getRealPath("/WEB-INF/users.db");
        String DB_URL = "jdbc:sqlite:" + dbPath;

        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Item item = new Item();
                item.setId(id);
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setImage(rs.getString("image"));
                request.setAttribute("item", item);
                request.getRequestDispatcher("editItem.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Товар не найден");
            }

        } catch (SQLException e) {
            throw new ServletException("Ошибка при загрузке товара", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dbPath = getServletContext().getRealPath("/WEB-INF/users.db");
        String DB_URL = "jdbc:sqlite:" + dbPath;

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE items SET name=?, description=?, price=?, image=? WHERE id=?")) {

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setString(4, image);
            stmt.setInt(5, id);
            stmt.executeUpdate();

            response.sendRedirect("admin");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при обновлении товара", e);
        }
    }
}