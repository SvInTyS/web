package catalog;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ItemServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/apache-tomcat-9.0.105/webapps/web/WEB-INF/users.db";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        System.out.println("Получен параметр id: " + idParam);

        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Отсутствует параметр id");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items WHERE id = ?")) {

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");

                    System.out.println("Найден товар: " + name + ", " + price);

                    request.setAttribute("name", name);
                    request.setAttribute("description", desc);
                    request.setAttribute("price", price);
                    request.setAttribute("image", image);
                    request.getRequestDispatcher("item.jsp").forward(request, response);
                } else {
                    System.out.println("Товар с id=" + id + " не найден.");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Объект не найден");
                }

            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректный ID");
        } catch (SQLException e) {
            throw new ServletException("Ошибка при загрузке объекта", e);
        }
    }
}